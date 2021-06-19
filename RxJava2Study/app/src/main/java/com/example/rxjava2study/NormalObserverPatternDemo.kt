package com.example.rxjava2study

import android.util.Log
import com.example.rxjava2study.NormalObserverPatternDemo.TAG

/**
 * 普通的观察者模式
 *
 * @author wangzhichao
 * @since 2021/6/19
 */
object NormalObserverPatternDemo {
    const val TAG = "NormalObserverPattern"
    fun show() {
        // 1，创建观察者
        val student = Student()
        val engineer = Engineer()
        // 2，创建被观察者
        val hongyangOfficialAccount = HongyangOfficialAccount()
        // 3，订阅
        hongyangOfficialAccount.addObserver(student)
        hongyangOfficialAccount.addObserver(engineer)
        // 4，发送事件
        hongyangOfficialAccount.notifyObservers("2021/6/19 推文：HarmonyOS 开发者日又双来了")
    }
}

/**
 * 抽象被观察者
 *
 */
interface Observable {

    fun addObserver(observer: Observer)

    fun removeObserver(observer: Observer)

    fun notifyObservers(arg: Any)
}

/**
 * 抽象观察者
 */
interface Observer {
    fun update(arg: Any)
}

/**
 * 鸿洋公众号，作为具体被观察者
 */
class HongyangOfficialAccount : Observable {
    private val observers = mutableListOf<Observer>()
    override fun addObserver(observer: Observer) {
        if (!observers.contains(observer)) {
            observers.add(observer)
        }
    }

    override fun removeObserver(observer: Observer) {
        if (observers.contains(observer)) {
            observers.remove(observer)
        }
    }

    override fun notifyObservers(arg: Any) {
        for (observer in observers) {
            observer.update(arg)
        }
    }
}

/**
 * 学生，作为具体观察者
 */
class Student : Observer {
    override fun update(arg: Any) {
        Log.d(TAG, "学生收到了推文：$arg，赶紧学习吧！")
    }
}
/**
 * 码农，作为具体观察者
 */
class Engineer : Observer {
    override fun update(arg: Any) {
        Log.d(TAG, "码农收到了推文：$arg，浏览一下，有个印象吧！")
    }
}