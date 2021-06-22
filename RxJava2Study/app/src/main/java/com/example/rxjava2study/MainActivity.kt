package com.example.rxjava2study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjava2study.databinding.MainActivityBinding
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNormalObserverPattern.setOnClickListener { NormalObserverPatternDemo.show() }
        binding.btnRxjavaObserverPattern.setOnClickListener { RxJavaObserverPatternDemo.show() }
        binding.btnRxjavaMapOperator.setOnClickListener { RxJavaMapOperatorDemo.show() }
    }
}