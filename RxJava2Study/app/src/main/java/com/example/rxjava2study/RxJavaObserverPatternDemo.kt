package com.example.rxjava2study

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * RxJava 的观察者模式
 *
 * @author wangzhichao
 * @since 2021/6/19
 */
object RxJavaObserverPatternDemo {
    private const val TAG = "RxJavaObserverPattern"
    fun show() {
        // 1, 创建观察者
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onNext(t: String) {
                Log.d(TAG, "onNext: t=$t")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: ", e)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }
        }
        // 2, 创建被观察者
        val observableCreate = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                // 4, 发送事件
                Log.d(TAG, "subscribe: onNext：发送值 a")
                emitter.onNext("a")
                Log.d(TAG, "subscribe: onNext：发送值 b")
                emitter.onNext("b")
                Log.d(TAG, "subscribe: onNext：发送值 c")
                emitter.onNext("c")
                Log.d(TAG, "subscribe: onNext：发送值 d")
                emitter.onNext("d")
                Log.d(TAG, "subscribe: onComplete")
                emitter.onComplete()
            }
        })
        // 3，订阅
        observableCreate.subscribe(observer)
    }
}