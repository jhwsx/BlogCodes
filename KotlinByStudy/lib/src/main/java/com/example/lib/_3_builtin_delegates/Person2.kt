package com.example.lib._3_builtin_delegates

import com.example.lib._2_property_delegation.example2.PropertyChangeAware
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// 通过Delegates.observable来实现属性变化的通知
class Person2(val name: String, _age: Int, _salary: Int) : PropertyChangeAware() {
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
    var age: Int by Delegates.observable(_age, observer)
    var salary: Int by Delegates.observable(_salary, observer)
}

fun main() {
    val p = Person2("John", 34, 2000)
    p.addPropertyChangeListener { event ->
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    }
    p.age = 35
    p.salary = 0
}