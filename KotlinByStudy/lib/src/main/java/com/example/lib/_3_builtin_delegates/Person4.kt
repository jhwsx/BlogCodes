package com.example.lib._3_builtin_delegates

import kotlin.properties.Delegates
// Delegates.notNull 实现属性的延迟初始化，和 lateinit 类似
// notNull 会给每个属性额外创建一个对象，而 lateinit 不会；
// notNull 可以用于基本数据类型的延迟初始化，而 lateinit 不可以。
class Person4 {
    val fullname: String by Delegates.notNull<String>()
    lateinit var fullname2: String
    // lateinit var age: Int

    companion object {
        lateinit var list: MutableList<String>
        val l : List<String> by Delegates.notNull<List<String>>()
    }
}