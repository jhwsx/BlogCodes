package com.kotlin.lib._1_topextensionfunction

/**
 *
 * @author wangzhichao
 * @since 2022/4/30
 */
fun greetings(message: String) {
    println("Hello, $message")
}

fun String.greetings2() {
    println("Hello, $this")
}

fun String?.greeting3() {
    println("Hello, $this")
}