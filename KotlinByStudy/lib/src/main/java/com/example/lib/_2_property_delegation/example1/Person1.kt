package com.example.lib._2_property_delegation.example1

class Person1 {
    var name : String = ""
        set(value) {
            field = value.lowercase().replaceFirstChar { it.uppercase() }
            updateCount++
        }
    var updateCount = 0
}

fun main() {
    val person1 = Person1()
    person1.name = "peter"
    println(person1.name)
    println(person1.updateCount)
}