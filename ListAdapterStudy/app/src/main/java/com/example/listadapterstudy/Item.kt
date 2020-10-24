package com.example.listadapterstudy

/**
 * 条目
 *
 * @author wangzhichao
 * @date 20-10-24
 */
class Item(val number: Int, val colorRes: Int, var selected: Boolean = false) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (number != other.number) return false
        if (colorRes != other.colorRes) return false
        if (selected != other.selected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number
        result = 31 * result + colorRes
        result = 31 * result + selected.hashCode()
        return result
    }
}