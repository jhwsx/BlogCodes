package com.example.customattributesstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Choreographer
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.setOnFocusChangeListener { v, hasFocus ->

        }
        window.decorView.requestFocus()
        Choreographer.getInstance().postFrameCallback {  }
    }
}