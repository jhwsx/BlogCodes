package com.example.lib._1_class_delegation

/**
 * 手动实现委托
 *
 * 委托模式，ListWithTrash2 不是任何类层级的一部分。
 * 委托类和内部对象实现了同样的接口。
 *
 * 在不能继承某个类时，委托是特别有用的。
 */
class ListWithTrash2<T> : MutableCollection<T> {

    private val innerList = ArrayList<T>()
    private var deletedItem: T? = null
    override val size: Int
        get() = innerList.size

    override fun contains(element: T): Boolean {
        return innerList.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return innerList.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return innerList.isEmpty()
    }

    override fun add(element: T): Boolean {
        return innerList.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return innerList.addAll(elements)
    }

    override fun clear() {
        innerList.clear()
    }

    override fun iterator(): MutableIterator<T> {
        return innerList.iterator()
    }

    override fun remove(element: T): Boolean {
        deletedItem = element
        return innerList.remove(element)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return innerList.removeAll(elements)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return innerList.retainAll(elements)
    }

    fun recover(): T? {
        return deletedItem
    }
}

fun main() {
    val listWithTrash2 = ListWithTrash2<String>()
    listWithTrash2.add("a")
    listWithTrash2.add("b")
    listWithTrash2.add("c")
    listWithTrash2.remove("a")
    listWithTrash2.remove("b")
    println(listWithTrash2.recover())
}