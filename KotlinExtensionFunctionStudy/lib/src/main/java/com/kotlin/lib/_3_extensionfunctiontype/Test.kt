package com.kotlin.lib._3_extensionfunctiontype

import com.kotlin.lib._1_topextensionfunction.firstChar
import com.kotlin.lib._1_topextensionfunction.lastChar

/**
 *
 * @author wangzhichao
 * @since 2022/5/1
 */

fun printChar1(str: String, block: (String) -> Char) {
    println(block(str))
}

fun printChar2(str: String, block: String.() -> Char) {
    println(str.block())
}

fun main() {
    val lastChar: (String) -> Char = String::lastChar
    printChar1("Kotlin") {
        lastChar(it)
    }

    printChar2("Android") {
        this.lastChar()
    }
}