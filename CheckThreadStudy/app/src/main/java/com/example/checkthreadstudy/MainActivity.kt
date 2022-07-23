package com.example.checkthreadstudy

import android.app.Activity
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.ViewGroup
import android.view.ViewParent
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.tv)
        var parent: ViewParent? = tv.parent
        Log.d(TAG, "onCreate: ${tv.isHardwareAccelerated}" )
        while (parent != null) {
            Log.d(TAG, "onCreate: parent=$parent")
            parent = parent.parent
        }
        tv.setOnClickListener {
            var parent1: ViewParent? = tv.parent
            var lastParent: ViewParent? = null
            while (parent1 != null) {
                Log.d(TAG, "onCreate: parent1=$parent1")
                lastParent = parent1
                parent1 = parent1.parent
            }

//            lastParent?.requestLayout()
            thread {
//            Thread.sleep(2000)
                tv.text = Thread.currentThread().name
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}