package com.example.refactordownloadbystatepattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refactordownloadbystatepattern.after.DownloadAfterActivity
import com.example.refactordownloadbystatepattern.before.DownloadBeforeActivity
import com.example.refactordownloadbystatepattern.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBefore.setOnClickListener {
            DownloadBeforeActivity.start(this@MainActivity)
        }
        binding.btnAfter.setOnClickListener {
            DownloadAfterActivity.start(this@MainActivity)
        }
    }
}