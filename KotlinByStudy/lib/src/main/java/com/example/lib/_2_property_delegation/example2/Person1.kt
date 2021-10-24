package com.example.lib._2_property_delegation.example2

class Person1(val name: String, _age: Int, _salary: Int) : PropertyChangeAware() {
    var age: Int = _age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = _salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}

fun main() {
    val p = Person1("John", 34, 2000)
    p.addPropertyChangeListener { event ->
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    }
    p.age = 35
    p.salary = 0
}