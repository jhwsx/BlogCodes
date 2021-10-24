package com.example.lib._2_property_delegation.example1

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class Person3 {
    // 通过关键字 by 对其后的表达式求值来获取 FormatDelegate 对象。
    // 关键字 by 可以用于任何符合属性委托约定规则的对象。
    var name: String by FormatDelegate()

    var lastname : String by FormatDelegate()

    var updateCount = 0
}

class FormatDelegate : ReadWriteProperty<Any?, String> {

    private var formattedString: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return formattedString
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (thisRef is Person3) {
            thisRef.updateCount++
        }
        formattedString = value.lowercase().replaceFirstChar { it.uppercase() }
    }
}

fun main() {
    val person3 = Person3()
    person3.name = "lei"
    person3.lastname = "li"
    println(person3.name)
    println(person3.lastname)
    println(person3.updateCount)
}