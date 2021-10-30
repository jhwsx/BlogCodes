package com.example.lib._2_property_delegation.example4

class Person1(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

fun main() {
    val person1 = Person1(
        mapOf(
            "name" to "Peter",
            "age" to 33
        )
    )
    println(person1.name)
    println(person1.age)
}