package com.kotlin.lib._1_topextensionfunction

/**
 *
 * @author wangzhichao
 * @since 2022/4/30
 */
fun List<Int>.sum(): Int {
    var sum: Int = 0
    for (element in this) {
        sum += element
    }
    return sum
}

fun List<Double>.sum(): Double {
    var sum: Double = 0.0
    for (element in this) {
        sum += element
    }
    return sum
}

fun <T> List<T>.joinToString(): String {
    val result = StringBuilder()
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(", ")
        result.append(element)
    }
    return result.toString()
}