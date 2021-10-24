package com.example.lib._3_builtin_delegates

import com.example.lib._2_property_delegation.example2.PropertyChangeAware
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// 通过Delegates.vetoable来实现属性否决更新
class Person3(val name: String, _age: Int, _salary: Int) : PropertyChangeAware() {

    var age: Int by Delegates.vetoable(_age) { property, oldValue, newValue ->
        newValue in 1..127
    }
    var salary: Int by Delegates.observable(_salary) { property, oldValue, newValue ->
        newValue > 2000
    }
}

fun main() {
    val p = Person3("John", 34, 2500)
    println("age=${p.age}, salary=${p.salary}")
    p.age = 35
    p.salary = 3000
    println("age=${p.age}, salary=${p.salary}")
    p.age = 0
    println("age=${p.age}, salary=${p.salary}")
    p.salary = 1000
}