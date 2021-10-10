package com.example.lib

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class FlowLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val maxWidth = widthSize

        var lineWidth = 0
        var maxLineWidth = 0

        val childCount = getChildCount()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                // 测量子 View
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
                // 获取子 View 的测量宽/高
                val measuredWidth = child.getMeasuredWidth()
                val measuredHeight = child.getMeasuredHeight()
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
}