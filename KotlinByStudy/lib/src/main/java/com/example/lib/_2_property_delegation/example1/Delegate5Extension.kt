package com.example.lib._2_property_delegation.example1

import kotlin.reflect.KProperty

operator fun Delegate5.setValue(thisRef: Any, property: KProperty<*>, value: String) =
    set(thisRef, value)

operator fun Delegate5.getValue(thisRef: Any, property: KProperty<*>) = get()