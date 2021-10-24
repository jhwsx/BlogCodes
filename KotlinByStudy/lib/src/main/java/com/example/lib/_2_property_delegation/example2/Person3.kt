package com.example.lib._2_property_delegation.example2

import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

// 通过属性委托来实现属性变化的通知
class ObservableProperty2(
    var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(p: Person3, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: Person3, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class Person3(val name: String, _age: Int, _salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty2(_age, changeSupport)
    var salary: Int by ObservableProperty2(_salary, changeSupport)
}

fun main() {
    val p = Person3("John", 34, 2000)
    p.addPropertyChangeListener { event ->
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    }
    p.age = 35
    p.salary = 0
}