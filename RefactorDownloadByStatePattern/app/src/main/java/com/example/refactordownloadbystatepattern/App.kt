package com.example.refactordownloadbystatepattern

import android.app.Application
import kotlin.properties.Delegates

/**
 *
 * @author wangzhichao
 * @since 2022/8/14
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: Application by Delegates.notNull<Application>()
    }
}