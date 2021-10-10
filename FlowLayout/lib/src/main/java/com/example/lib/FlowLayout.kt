package com.example.lib

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max
import kotlin.math.min

class FlowLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    var itemHorizontalSpacing = 20
    var itemVerticalSpacing = 20
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val maxWidth = widthSize

        var lineWidth = 0
        var maxLineWidth = 0
        var lineHeight = 0
        var totalHeight = 0
        val childCount = getChildCount()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                // 测量子 View
                val lp = child.layoutParams
                val childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    getPaddingLeft() + getPaddingRight(), lp.width)
                val childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    getPaddingTop() + getPaddingBottom(), lp.height)
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec)
                // 获取子 View 的测量宽/高
                val childMeasuredWidth = child.getMeasuredWidth()
                val childMeasuredHeight = child.getMeasuredHeight()
                val actualItemHorizontalSpacing = if (lineWidth == 0) 0 else itemHorizontalSpacing
                if (lineWidth + actualItemHorizontalSpacing + childMeasuredWidth <= maxWidth) {
                    // 在本行还可以放置一个子 View
                    lineWidth += actualItemHorizontalSpacing + childMeasuredWidth
                    // 行高为一行中所有子 View 最高的那一个
                    lineHeight = max(lineHeight, childMeasuredHeight)
                } else {
                    // 在本行不可以放置一个子 View，需要换行
                    maxLineWidth = max(lineWidth, maxLineWidth)
                    totalHeight += lineHeight
                    lineWidth = childMeasuredWidth
                    lineHeight = childMeasuredHeight
                }
            }
        }
        val measuredWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else maxLineWidth
        val measuredHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else totalHeight
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childCount = getChildCount()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childMeasuredWidth = child.getMeasuredWidth()
            val childMeasuredHeight = child.getMeasuredHeight()
            if (child.visibility != View.GONE) {

            }
        }
    }
}