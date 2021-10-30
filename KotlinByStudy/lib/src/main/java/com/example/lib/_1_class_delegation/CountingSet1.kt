package com.example.lib._1_class_delegation

class CountingSet1<T>: HashSet<T>() {

    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded++
        return super.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        // 因为 super.addAll 内部调用了 add 方法，所以这里不必统计添加个数了。
        return super.addAll(elements)
    }
}

fun main() {
    val cset = CountingSet1<Int>()
    cset.add(1)
    cset.addAll(listOf(2, 2, 3))
    println("${cset.objectAdded} objects were added, ${cset.size} remain")
}