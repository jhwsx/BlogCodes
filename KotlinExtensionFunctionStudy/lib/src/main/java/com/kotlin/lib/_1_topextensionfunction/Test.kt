package com.kotlin.lib._1_topextensionfunction

/**
 *
 * @author wangzhichao
 * @since 2022/4/30
 */
fun main() {
    println("Kotlin".lastChar())
    "I".isNullOrEmpty()
    println("Kotlin".firstChar())
    println(null.firstChar())

    println(listOf(1, 2, 3).sum())
    println(listOf(1.1, 2.2, 3.3).sum())

    println(listOf(1, 2, 3).joinToString())
    println(listOf("a", "b", "c").joinToString())

    val buttonView: View = Button()
    buttonView.showOff()

    val greeting: (String) -> Unit = ::greetings

    val greeting2: (String) -> Unit = String::greetings2
    greeting2.invoke("Kotlin")
    greeting2("Android")
    "Jetpack".greetings2()
    val greeting22: String.() -> Unit = String::greetings2
    "Kotlin".greeting22()
    val greeting3: (String?) -> Unit = String?::greeting3
//    greeting3("Kotlin")
    greeting3(null)

}

