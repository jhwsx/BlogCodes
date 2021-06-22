package com.example.rxjava2study

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

/**
 * RxJava 的 map 操作符
 *
 * @author wangzhichao
 * @since 2021/6/22
 */
object RxJavaMapOperatorDemo {
    private const val TAG = "RxJavaMapOperator"
    fun show() {
        // 1, 创建观察者
        val observer = object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onNext(t: Int) {
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
        val observableCreate = Observable.create(
                object : ObservableOnSubscribe<String> {
                    override fun subscribe(emitter: ObservableEmitter<String>) {
                        // 5, 发送事件
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
                }
        )
        // 3，使用 map 操作符对发送的数据进行变换
        val observableMap = observableCreate.map(object : Function<String, Int> {
            override fun apply(t: String): Int {
                // 取出索引为0的元素，数据类型是 Char，然后转换为 Int 类型。
                return t[0].toInt()
            }
        })
        // 4，订阅
        observableMap.subscribe(observer)
    }
}