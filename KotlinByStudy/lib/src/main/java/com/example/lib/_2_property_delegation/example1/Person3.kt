package com.example.lib._2_property_delegation.example1
// 手动优化重复代码
class Person3 {
    var name: String = ""
        set(value) {
            field = format(value)
        }

    var lastname: String = ""
        set(value) {
            field = format(value)
        }
    var updateCount = 0

    fun format(value: String): String {
        updateCount++
        return value.lowercase().replaceFirstChar { it.uppercase() }
    }
}

fun main() {
    val person = Person3()
    person.name = "peter"
    person.lastname = "wang"
    println("name=${person.name}")
    println("lastname=${person.lastname}")
    println("updateCount=${person.updateCount}")
}