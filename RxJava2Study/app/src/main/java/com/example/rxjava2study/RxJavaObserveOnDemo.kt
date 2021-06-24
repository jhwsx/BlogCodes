package com.example.rxjava2study

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 *
 * @author wangzhichao
 * @since 2021/6/24
 */
object RxJavaObserveOnDemo {
    private const val TAG = "RxJavaObserveOnDemo"
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
                    // 5, 发送事件
                    thread {
                        TimeUnit.SECONDS.sleep(3L)
                        Log.d(TAG, "subscribe: onNext：发送值 a, currThread=${Thread.currentThread().name}")
                        emitter.onNext("a")
                        TimeUnit.SECONDS.sleep(3L)
                        Log.d(TAG, "subscribe: onNext：发送值 b, currThread=${Thread.currentThread().name}")
                        emitter.onNext("b")
                        TimeUnit.SECONDS.sleep(3L)
                        Log.d(TAG, "subscribe: onNext：发送值 c, currThread=${Thread.currentThread().name}")
                        emitter.onNext("c")
                        TimeUnit.SECONDS.sleep(3L)
                        Log.d(TAG, "subscribe: onNext：发送值 d, currThread=${Thread.currentThread().name}")
                        emitter.onNext("d")
                        TimeUnit.SECONDS.sleep(3L)
                        Log.d(TAG, "subscribe: onComplete, currThread=${Thread.currentThread().name}")
                        emitter.onComplete()
                    }
                }
            }
        )
        // 3，指定在 main 线程里观察事件
        val observableSubscribeOn = observableCreate.observeOn(AndroidSchedulers.mainThread())
        // 4，订阅
        observableSubscribeOn.subscribe(observer)
    }
}