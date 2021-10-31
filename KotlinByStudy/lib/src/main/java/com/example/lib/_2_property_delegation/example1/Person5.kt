package com.example.lib._2_property_delegation.example1

// 手动使用委托解决重复代码问题
class Person5 {

    private val nameDelegate = Delegate2()
    private val lastnameDelegate = Delegate2()
    var name: String
        set(value) {
            nameDelegate.setValue(this, value)
        }
        get() {
            return nameDelegate.getValue()
        }

    var lastname: String
        set(value) {
            lastnameDelegate.setValue(this, value)
        }
        get() {
            return lastnameDelegate.getValue()
        }
    var updateCount = 0
}


fun main() {
    val person = Person5()
    person.name = "peter"
    person.lastname = "wang"
    println("name=${person.name}")
    println("lastname=${person.lastname}")
    println("updateCount=${person.updateCount}")
}