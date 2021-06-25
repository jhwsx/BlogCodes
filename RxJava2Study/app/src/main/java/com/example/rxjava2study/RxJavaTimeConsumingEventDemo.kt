package com.example.rxjava2study

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * @author wangzhichao
 * @since 2021/6/24
 */
object RxJavaTimeConsumingEventDemo {
    private const val TAG = "RxJavaTimeConsuming"
    fun show() {
        // 1, 创建观察者
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: currThread=${Thread.currentThread().name}")
            }

            override fun onNext(t: String) {
                Log.d(TAG, "onNext: t=$t, currThread=${Thread.currentThread().name}")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: ", e)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete: currThread=${Thread.currentThread().name}")
            }
        }
        // 2, 创建被观察者
        val observableCreate = Observable.create(
            object : ObservableOnSubscribe<String> {
                override fun subscribe(emitter: ObservableEmitter<String>) {
                    // 4, 发送事件
                    TimeUnit.SECONDS.sleep(3L)
                    Log.d(TAG, "subscribe: onNext：发送值 a, currThread=${Thread.currentThread().name}")
                    emitter.onNext("a")
                    TimeUnit.SECONDS.sleep(3L)
                    Log.d(TAG, "subscribe: onComplete, currThread=${Thread.currentThread().name}")
                    emitter.onComplete()
                }
            }
        )
        // 3，订阅
        observableCreate.subscribe(observer)
    }
}