package com.example.lib._2_property_delegation.example1
// 手动优化重复代码
class Person3 {
    var name: String = ""
        set(value) {
            field = format(value)
        }
        get() {
            return getter(field)
        }

    var lastname: String = ""
        set(value) {
            field = format(value)
        }
        get() {
            return getter(field)
        }
    var updateCount = 0

    fun format(value: String): String {
        updateCount++
        return value.lowercase().replaceFirstChar { it.uppercase() }
    }
    fun getter(value: String): String {
        return value + "-" + value.length
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