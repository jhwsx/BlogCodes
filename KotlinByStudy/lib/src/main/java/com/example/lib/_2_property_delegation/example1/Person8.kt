package com.example.lib._2_property_delegation.example1

// 使用委托属性解决重复代码问题
class Person8 {
    var name: String by Delegate5()
    var lastname: String by Delegate5()
    var updateCount = 0
}


fun main() {
    val person = Person8()
    person.name = "peter"
    person.lastname = "wang"
    println("name=${person.name}")
    println("lastname=${person.lastname}")
    println("updateCount=${person.updateCount}")
}