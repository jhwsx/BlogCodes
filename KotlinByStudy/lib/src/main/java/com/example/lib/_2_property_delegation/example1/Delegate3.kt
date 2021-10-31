package com.example.lib._2_property_delegation.example1

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Delegate3 : ReadWriteProperty<Any, String> {
    var formattedString = ""
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return formattedString + "-" + formattedString.length
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        if (thisRef is Person6) {
            thisRef.updateCount++
        }
        formattedString = value.lowercase().replaceFirstChar { it.uppercase() }
    }
}