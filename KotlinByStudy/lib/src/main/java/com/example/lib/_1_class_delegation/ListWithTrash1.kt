package com.example.lib._1_class_delegation

/**
 * 直接继承于具体的 ArrayList，而不是实现 MutableCollection 接口，所以和 ArrayList 的具体实现高度耦合
 */
class ListWithTrash1<T> : ArrayList<T>() {

    private var deletedItem: T? = null

    override fun remove(element: T): Boolean {
        deletedItem = element
        return super.remove(element)
    }

    fun recover(): T? {
        return deletedItem
    }
}

fun main() {
    val listWithTrash1 = ListWithTrash1<Int>()
    listWithTrash1.add(1)
    listWithTrash1.add(2)
    listWithTrash1.add(3)
    listWithTrash1.remove(2)
    listWithTrash1.remove(3)
    println(listWithTrash1.recover())
}

