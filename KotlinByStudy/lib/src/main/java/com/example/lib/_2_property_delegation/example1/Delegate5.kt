package com.example.lib._2_property_delegation.example1

class Delegate5  {
    var formattedString = ""
    fun get(): String {
        return formattedString + "-" + formattedString.length
    }

    fun set(thisRef: Any, value: String) {
        if (thisRef is Person8) {
            thisRef.updateCount++
        }
        formattedString = value.lowercase().replaceFirstChar { it.uppercase() }
    }
}