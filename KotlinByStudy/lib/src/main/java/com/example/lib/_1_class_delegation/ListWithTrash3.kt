package com.example.lib._1_class_delegation

/**
 * 使用类委托
 */
class ListWithTrash3<T>(private val innerList: MutableList<T> = ArrayList<T>()) :
    MutableCollection<T> by innerList {
    private var deletedItem: T? = null

    override fun remove(element: T): Boolean {
        deletedItem = element
        return innerList.remove(element)
    }

    fun recover(): T? {
        return deletedItem
    }
}

fun main() {
    val listWithTrash3 = ListWithTrash3<Int>()
    listWithTrash3.add(1)
    listWithTrash3.add(2)
    listWithTrash3.add(3)
    listWithTrash3.remove(1)
    listWithTrash3.remove(2)
    println(listWithTrash3.recover())
}