package com.example.lib._3_builtin_delegates

class Person(name: String) {
    val emails: List<String> by lazy { loadEmailsByName(name) }

    private fun loadEmailsByName(name: String): List<String> {
        println("loadEmailsByName called")
        return listOf("Email1", "Email2", "Email3")
    }
}

fun main() {
    val p = Person("Peter")
    println(p.emails)
    println(p.emails)
}