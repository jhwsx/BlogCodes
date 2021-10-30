package com.example.lib._2_property_delegation.example4

class Person2(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int by map
}

fun main() {
    val map: MutableMap<String, Any?> = mutableMapOf(
        "name" to "Peter",
        "age" to 33
    )
    val person2 = Person2(
        map
    )
    println(person2.name)
    println(person2.age)
    person2.name = "John"
    println(map)
}