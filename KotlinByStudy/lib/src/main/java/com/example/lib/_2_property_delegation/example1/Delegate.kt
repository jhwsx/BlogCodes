package com.example.lib._2_property_delegation.example1

class Delegate {
    fun format(thisRef: Any, value: String): String {
        if (thisRef is Person4) {
            thisRef.updateCount++
        } else if (thisRef is Student2) {
            thisRef.updateCount++
        }
        return value.lowercase().replaceFirstChar { it.uppercase() }
    }

    fun getter(value: String): String {
        return value + "-" + value.length
    }
}