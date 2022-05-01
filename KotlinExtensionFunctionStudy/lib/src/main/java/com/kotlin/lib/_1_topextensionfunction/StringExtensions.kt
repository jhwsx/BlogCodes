package com.kotlin.lib._1_topextensionfunction

/**
 *
 * @author wangzhichao
 * @since 2022/4/30
 */
fun String.lastChar(): Char {
    return this.get(this.length - 1)
}

fun String?.firstChar(): Char? {
    return this?.get(0)
}