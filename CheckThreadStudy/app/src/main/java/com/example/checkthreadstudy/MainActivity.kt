package com.example.checkthreadstudy

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewParent
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.tv)
        var parent: ViewParent? = tv.parent
        while (parent != null) {
            Log.d(TAG, "onCreate: parent=$parent")
            parent = parent.parent
        }
        thread {
//            Thread.sleep(2000)
            tv.text = Thread.currentThread().name
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}