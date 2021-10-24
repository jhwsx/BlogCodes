package com.example.lib._2_property_delegation.example1

class Person2 {
    var name: String = ""
        set(value) {
            field = value.lowercase().replaceFirstChar { it.uppercase() }
            updateCount++
        }

    var lastname: String = ""
        set(value) {
            field = value.lowercase().replaceFirstChar { it.uppercase() }
            updateCount++
        }
    var updateCount = 0
}

fun main() {
    val person2 = Person2()
    person2.name = "peter"
    person2.lastname = "wang"
    println(person2.name)
    println(person2.lastname)
    println(person2.updateCount)
}