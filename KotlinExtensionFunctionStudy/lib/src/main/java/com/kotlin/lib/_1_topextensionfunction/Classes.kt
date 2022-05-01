package com.kotlin.lib._1_topextensionfunction

/**
 *
 * @author wangzhichao
 * @since 2022/4/30
 */
open class View {
    open fun showOff() {
        println("View member showOff" )
    }
}
class Button: View() {
    override fun showOff() {
        println("Button member showOff" )
    }
}