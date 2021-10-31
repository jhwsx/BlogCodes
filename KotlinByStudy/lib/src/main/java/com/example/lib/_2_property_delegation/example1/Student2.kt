package com.example.lib._2_property_delegation.example1

class Student2 {
    private val delegate = Delegate()

    var name: String = ""
        set(value) {
            field = delegate.format(this, value)
        }
        get() {
            return delegate.getter(field)
        }
    var address: String = ""
        set(value) {
            field = delegate.format(this, value)
        }
        get() {
            return delegate.getter(field)
        }
    var updateCount = 0
}

fun main() {
    val s = Student2()
    s.name = "student"
    s.address = "shanghai"
    println("name=${s.name}")
    println("address=${s.address}")
    println("updateCount=${s.updateCount}")
}