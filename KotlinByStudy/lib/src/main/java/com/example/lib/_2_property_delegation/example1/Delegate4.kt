package com.example.lib._2_property_delegation.example1

import kotlin.reflect.KProperty

class Delegate4  {
    var formattedString = ""
    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return formattedString + "-" + formattedString.length
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        if (thisRef is Person7) {
            thisRef.updateCount++
        }
        formattedString = value.lowercase().replaceFirstChar { it.uppercase() }
    }
}