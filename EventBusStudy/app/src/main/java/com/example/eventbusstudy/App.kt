package com.example.eventbusstudy

import android.app.Application
import android.util.Log

import org.greenrobot.eventbus.EventBus




/**
 *
 * @author wangzhichao
 * @since 2022/4/7
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        EventBus.builder()
//            // .ignoreGeneratedIndex(false) // 默认就是 false
//            .addIndex(MyEventBusIndex()).installDefaultEventBus()
        val eventBus = EventBus.builder()
            .ignoreGeneratedIndex(false)
            .logSubscriberExceptions(true)
            .strictMethodVerification(true)
            .installEventBus1()
        Log.d(TAG, "onCreate: eventBus=${eventBus.hashCode()}")
        Log.d(TAG, "onCreate: EventBus1.getDefault()=${EventBus1.getDefault().hashCode()}")
    }

    companion object {
        private const val TAG = "App"
    }
}