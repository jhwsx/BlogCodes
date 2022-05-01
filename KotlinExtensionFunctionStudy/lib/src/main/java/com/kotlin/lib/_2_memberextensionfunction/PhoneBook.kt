package com.kotlin.lib._2_memberextensionfunction

/**
 *
 * @author wangzhichao
 * @since 2022/5/1
 */
class PhoneNumber(val number: String) {
    fun isValid(): Boolean {
        return number.length == 11 && number.all { it.isDigit() }
    }

    fun printPhoneNumber(number: String) {
        println("PhoneNumber: $number")
    }
}

class PhoneBook {
    fun verify(phoneNumber: PhoneNumber): Boolean {
        return phoneNumber.check()
    }

    fun PhoneNumber.check(): Boolean {
        this@PhoneBook.printPhoneNumber(this.number)
        return isValid()
    }

    private fun printPhoneNumber(number: String) {
        println("PhoneBook: $number")
    }
}

fun main() {
    println(PhoneBook().verify(PhoneNumber("13912345678")))

//    PhoneBook().apply {
//        PhoneNumber("12312345678").check()
//    }
}