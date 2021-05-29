package com.mqtt.mqttstudy.gate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GateMqttService.startService(this)
    }

    fun turnGate(view: View) {
        // 闸机转动，进入一个游客
        GateMqttService.publish("tourist enter")
    }

}