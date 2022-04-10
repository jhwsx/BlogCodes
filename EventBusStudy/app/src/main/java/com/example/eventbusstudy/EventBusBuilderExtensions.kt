package com.example.eventbusstudy

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.EventBusBuilder
import org.greenrobot.eventbus.EventBusException

/**
 *
 * @author wangzhichao
 * @since 2022/4/10
 */
fun EventBusBuilder.installEventBus1(): EventBus {
    synchronized(EventBus::class.java) {
        if (EventBus1.defaultInstance != null) {
            throw EventBusException(
                "EventBus1 instance already exists." +
                        " It may be only set once before it's used the first time to ensure consistent behavior."
            )
        }
        EventBus1.defaultInstance = build()
        return EventBus1.defaultInstance
    }
}