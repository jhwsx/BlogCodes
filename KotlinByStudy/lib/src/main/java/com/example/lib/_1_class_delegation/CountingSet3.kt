package com.example.lib._1_class_delegation

class CountingSet3<T>(
    val innerSet: HashSet<T> = HashSet<T>()
) : MutableSet<T> by innerSet {
    var objectAdded = 0
    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val cset = CountingSet3<Int>()
    cset.add(1)
    cset.addAll(listOf(2, 2, 3))
    println("${cset.objectAdded} objects were added, ${cset.size} remain")
}