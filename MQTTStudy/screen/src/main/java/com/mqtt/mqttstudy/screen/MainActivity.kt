package com.mqtt.mqttstudy.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tv: TextView
    private var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById<TextView>(R.id.tv)
        tv.text = "景区目前游客数：$count"
        ScreenMqttService.startService(this)
        MessageArriveListenerManager.addListener(messageArriveListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        MessageArriveListenerManager.removeListener(messageArriveListener)
    }

    private val messageArriveListener = object : MessageArriveListener {
        override fun onMessageArrived() {
            count++
            tv.text = "景区目前游客数：$count"
        }
    }
}