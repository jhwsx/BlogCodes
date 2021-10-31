package com.example.lib._2_property_delegation.example1

class Delegate2 {
    var formattedString: String = ""
    fun setValue(thisRef: Any, value: String) {
        if (thisRef is Person5) {
            thisRef.updateCount++
        } else if (thisRef is Student2) {
            thisRef.updateCount++
        }
        formattedString = value.lowercase().replaceFirstChar { it.uppercase() }
    }

    fun getValue(): String {
        return formattedString + "-" + formattedString.length
    }
}