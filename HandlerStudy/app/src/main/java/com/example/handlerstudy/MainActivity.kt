package com.example.handlerstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.handlerstudy.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.btnSyncBarrierTest.setOnClickListener { SyncBarrierTestActivity.start(this@MainActivity) }
        binding.btnListenMainLooperMessageQueue.setOnClickListener { ListenMainLooperMessageQueueActivity.start(this@MainActivity) }
    }
}