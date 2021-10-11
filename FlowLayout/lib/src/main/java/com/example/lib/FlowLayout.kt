package com.example.lib

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlin.math.max
import kotlin.math.min

class FlowLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    // 子元素水平间距
    private var itemHorizontalSpacing = 20
    // 子元素竖直间距
    private var itemVerticalSpacing = 20
    /**
     * 记录每一行所有的子 View 的集合
     */
    private val allLineViews = ArrayList<ArrayList<View>>()
    /**
     * 所有行的行高的集合
     */
    private val lineHeights = ArrayList<Int>()
    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        allLineViews.clear()
        lineHeights.clear()
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        // 获取流式布局允许的最大宽度
        val maxWidth = widthSize
        var lineWidth = 0 // 行宽
        var maxLineWidth = 0 // 最大行宽
        var lineHeight = 0 // 行高
        var totalHeight = 0 // 总高度
        val childCount = getChildCount()
        var lineViews = ArrayList<View>()
        var lineCount = 0
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
                    lineViews.add(child)
                } else {
                    // 在本行不可以放置一个子 View，需要换行
                    maxLineWidth = max(lineWidth, maxLineWidth)
                    lineCount++
                    totalHeight += lineHeight + if (lineCount == 1) 0 else itemVerticalSpacing
                    lineHeights.add(lineHeight)
                    allLineViews.add(lineViews)
                    lineWidth = childMeasuredWidth
                    lineHeight = childMeasuredHeight
                    lineViews = ArrayList<View>()
                    lineViews.add(child)
                }
            }

            if (i == childCount - 1) {
                maxLineWidth = max(lineWidth, maxLineWidth)
                lineCount++
                totalHeight += lineHeight + if (lineCount == 1) 0 else itemVerticalSpacing
                lineHeights.add(lineHeight)
                allLineViews.add(lineViews)
            }
        }
        Log.d(TAG, "onMeasure: lineCount=$lineCount")
        val measuredWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else maxLineWidth
        val measuredHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else totalHeight
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 获取行数
        val lineCount = allLineViews.size
        Log.d(TAG, "onLayout: lineCount=$lineCount")
        // 子元素的左上角横坐标
        var childLeft = 0
        // 子元素的左上角纵坐标
        var childTop = 0
        // 遍历行
        for (i in 0 until lineCount) {
            val lineViews = allLineViews[i]
            val lineHeight = lineHeights[i]
            // 遍历一行中的所有子元素
            for (j in 0 until lineViews.size) {
                val child = lineViews[j]
                val childMeasuredWidth = child.getMeasuredWidth()
                val childMeasuredHeight = child.getMeasuredHeight()
                // 确定子元素的位置
                child.layout(childLeft, childTop, childLeft + childMeasuredWidth, childTop + childMeasuredHeight)
                // 更新 childLeft，作为该行下一个子元素的左上角横坐标
                childLeft += childMeasuredWidth + itemHorizontalSpacing
            }
            // 更新 childTop，作为下一行子元素的左上角纵坐标
            childTop += lineHeight + itemVerticalSpacing
            // 更新 childLeft，作为下一行子元素的左上角横坐标
            childLeft = 0
        }
    }


    companion object {
        private const val TAG = "FlowLayout"
    }
}