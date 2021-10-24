package com.example.lib._2_property_delegation.example2

import java.beans.PropertyChangeSupport

// 通过辅助类来实现属性变化的通知
class ObservableProperty(
    val propName: String,
    var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class Person2(val name: String, _age: Int, _salary: Int) : PropertyChangeAware() {
    val ageObservableProperty = ObservableProperty("age", _age, changeSupport)
    var age: Int
        get() = ageObservableProperty.getValue()
        set(value) {
            ageObservableProperty.setValue(value)
        }

    val salaryObservableProperty = ObservableProperty("salary", _salary, changeSupport)
    var salary: Int
        get() = salaryObservableProperty.getValue()
        set(value) {
            salaryObservableProperty.setValue(value)
        }
}

fun main() {
    val p = Person2("John", 34, 2000)
    p.addPropertyChangeListener { event ->
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    }
    p.age = 35
    p.salary = 0
}