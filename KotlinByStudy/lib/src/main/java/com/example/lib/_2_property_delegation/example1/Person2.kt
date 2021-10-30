package com.example.lib._2_property_delegation.example1
// 重复代码需要优化
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
    println("name=${person2.name}")
    println("lastname=${person2.lastname}")
    println("updateCount=${person2.updateCount}")
}