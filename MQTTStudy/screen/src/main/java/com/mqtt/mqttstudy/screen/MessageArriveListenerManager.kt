package com.mqtt.mqttstudy.screen

/**
 *
 * @author wangzhichao
 * @since 2021/5/29
 */
object MessageArriveListenerManager {
    private val list = mutableListOf<MessageArriveListener>()

    fun addListener(listener: MessageArriveListener) {
        if (!list.contains(listener)) {
            list.add(listener)
        }
    }

    fun removeListener(listener: MessageArriveListener) {
        list.remove(listener)
    }

    fun notifyMessageArrived() {
        for (messageArriveListener in list) {
            messageArriveListener.onMessageArrived()
        }
    }
}