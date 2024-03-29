package com.example.lib._2_property_delegation.example1

// 手动使用委托解决重复代码问题
class Person4 {

    private val delegate = Delegate()

    var name: String = ""
        set(value) {
            field = delegate.format(this, value)
        }
        get() {
            return delegate.getter(field)
        }

    var lastname: String = ""
        set(value) {
            field = delegate.format(this, value)
        }
        get() {
            return delegate.getter(field)
        }

    var updateCount = 0
}


fun main() {
    val person = Person4()
    person.name = "peter"
    person.lastname = "wang"
    println("name=${person.name}")
    println("lastname=${person.lastname}")
    println("updateCount=${person.updateCount}")
}