package com.example.lib._1_class_delegation

class CountingSet4<T>(
) : MutableSet<T> by HashSet<T>() {

}