package com.example.lib._2_property_delegation.example1

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class Person4 {
    // 通过关键字 by 对其后的表达式求值来获取 FormatDelegate 对象。
    // 关键字 by 可以用于任何符合属性委托约定规则的对象。
    var name: String by FormatDelegate()

    var lastname : String by FormatDelegate()

    var updateCount = 0
}

class FormatDelegate : ReadWriteProperty<Any?, String> {

    private var formattedString: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("getValue: thisRef=$thisRef, property=$property")
        return formattedString
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("setValue: thisRef=$thisRef, property=$property, value=$value")
        if (thisRef is Person4) {
            thisRef.updateCount++
        }
        formattedString = value.lowercase().replaceFirstChar { it.uppercase() }
    }
}

fun main() {
    val person = Person4()
    person.name = "lei"
    person.lastname = "li"
    println("name=${person.name}")
    println("lastname=${person.lastname}")
    println("updateCount=${person.updateCount}")
}