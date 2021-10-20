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

    var lineVerticalGravity: Int = LINE_VERTICAL_GRAVITY_CENTER_VERTICAL
        set(value) {
            field = value
            requestLayout()
        }
    var mode: Int = MODE_LIMIT_MAX_COUNT
    var maxLines: Int = Int.MAX_VALUE
        set(value) {
            mode = MODE_LIMIT_MAX_LINE
            field = value
            requestLayout()
        }
    var maxCount: Int = Int.MAX_VALUE
        set(value) {
            mode = MODE_LIMIT_MAX_COUNT
            field = value
            if (maxLines != Int.MAX_VALUE) {
                maxLines = Int.MAX_VALUE
            }
            requestLayout()
        }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout)
        lineVerticalGravity = ta.getInt(R.styleable.FlowLayout_flowlayout_line_vertical_gravity, LINE_VERTICAL_GRAVITY_CENTER_VERTICAL)
        Log.d(TAG, "init: lineVerticalGravity=$lineVerticalGravity")
        // 默认值为 Int.MAX_VALUE，表示不限制行数
        maxLines = ta.getInt(R.styleable.FlowLayout_android_maxLines, Int.MAX_VALUE)
        Log.d(TAG, "init: maxLines=$maxLines")
        maxCount = ta.getInt(R.styleable.FlowLayout_maxCount, Int.MAX_VALUE)
        ta.recycle()
    }
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
        var measuredChildCount = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                // 测量子 View
                val lp = child.layoutParams as MarginLayoutParams
                val childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    getPaddingLeft() + getPaddingRight(), lp.width)
                val childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    getPaddingTop() + getPaddingBottom(), lp.height)
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec)
                // 获取子 View 的测量宽/高
                val childMeasuredWidth = child.getMeasuredWidth()
                val childMeasuredHeight = child.getMeasuredHeight()
                val actualChildWidth = childMeasuredWidth + lp.leftMargin + lp.rightMargin
                val actualChildHeight = childMeasuredHeight + lp.topMargin + lp.bottomMargin
                val actualItemHorizontalSpacing = if (lineWidth == 0) 0 else itemHorizontalSpacing
                if (lineWidth + actualItemHorizontalSpacing + actualChildWidth <= maxWidth - getPaddingLeft() - getPaddingRight()) {
                    // 在本行还可以放置一个子 View
                    lineWidth += actualItemHorizontalSpacing + actualChildWidth
                    // 行高为一行中所有子 View 最高的那一个
                    lineHeight = max(lineHeight, actualChildHeight)
                    lineViews.add(child)
                } else {
                    // 在本行不可以放置一个子 View，需要换行
                    if (lineCount == maxLines && mode == MODE_LIMIT_MAX_LINE) {
                        break
                    }
                    maxLineWidth = max(lineWidth, maxLineWidth)
                    lineCount++
                    totalHeight += lineHeight + if (lineCount == 1) 0 else itemVerticalSpacing
                    lineHeights.add(lineHeight)
                    allLineViews.add(lineViews)
                    lineWidth = actualChildWidth
                    lineHeight = actualChildHeight
                    lineViews = ArrayList<View>()
                    lineViews.add(child)
                }
                measuredChildCount++
            }

            if (i == childCount - 1 || (measuredChildCount == maxCount && mode == MODE_LIMIT_MAX_COUNT)) {
                if (lineCount == maxLines && mode == MODE_LIMIT_MAX_LINE) {
                    break
                }
                maxLineWidth = max(lineWidth, maxLineWidth)
                lineCount++
                totalHeight += lineHeight + if (lineCount == 1) 0 else itemVerticalSpacing
                lineHeights.add(lineHeight)
                allLineViews.add(lineViews)
                if (measuredChildCount == maxCount) {
                    break
                }
            }
        }
        maxLineWidth += getPaddingLeft() + getPaddingRight()
        totalHeight += getPaddingTop() + getPaddingBottom()
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
        var childLeft = getPaddingLeft()
        // 子元素的左上角纵坐标
        var childTop = getPaddingTop()
        var nextChildIndex = 0
        // 遍历行
        val itemCount = allLineViews.sumOf { it.size }
        Log.d(TAG, "onLayout: itemCount=$itemCount,childCount=$childCount")
        outer@for (i in 0 until lineCount) {
            val lineViews = allLineViews[i]
            val lineHeight = lineHeights[i]
            // 遍历一行中的所有子元素
            for (j in 0 until lineViews.size) {
                val child = lineViews[j]
                val lp = child.layoutParams as MarginLayoutParams
                childLeft += lp.leftMargin
                val childMeasuredWidth = child.getMeasuredWidth()
                val childMeasuredHeight = child.getMeasuredHeight()
                val offsetTop = getOffsetTop(lineHeight, child)
                // 确定子元素的位置
                child.layout(childLeft, childTop + lp.topMargin + offsetTop, childLeft + childMeasuredWidth,
                    childTop + lp.topMargin + offsetTop+ childMeasuredHeight)
                nextChildIndex = indexOfChild(child)
                // 更新 childLeft，作为该行下一个子元素的左上角横坐标
                childLeft += childMeasuredWidth + lp.rightMargin + itemHorizontalSpacing
            }
            // 更新 childTop，作为下一行子元素的左上角纵坐标
            childTop += lineHeight + itemVerticalSpacing
            // 更新 childLeft，作为下一行子元素的左上角横坐标
            childLeft = getPaddingLeft()
        }

        val childCount = getChildCount()
        for (i in nextChildIndex + 1 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                continue
            }
            child.layout(0,0,0,0)
        }
    }

    private fun getOffsetTop(lineHeight: Int, child: View): Int {
        val lp = child.layoutParams as MarginLayoutParams
        val childMeasuredHeight = child.getMeasuredHeight()
        val childMeasuredHeightWithMargin = childMeasuredHeight + lp.topMargin + lp.bottomMargin
        return when (lineVerticalGravity) {
            LINE_VERTICAL_GRAVITY_TOP -> 0
            LINE_VERTICAL_GRAVITY_CENTER_VERTICAL -> (lineHeight - childMeasuredHeightWithMargin) / 2
            LINE_VERTICAL_GRAVITY_BOTTOM -> lineHeight - childMeasuredHeightWithMargin
            else -> {
                throw IllegalArgumentException("unknown lineVerticalGravity value: $lineVerticalGravity")
            }
        }
    }

    // 当通过 addView(View) 方法添加子元素，并且子元素没有设置布局参数时，会调用此方法来生成默认的布局参数
    // 这里重写返回 MarginLayoutParams 对象，是为了在获取子元素的 LayoutParams 对象时，可以正常强转为 MarginLayoutParams
    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    // 检查传入的布局参数是否符合某个条件
    override fun checkLayoutParams(p: LayoutParams?): Boolean {
        return p is MarginLayoutParams
    }

    // addViewInner 中调用，但是布局参数类型无法通过 checkLayoutParams() 判断时，会走这个方法。
    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    // 当通过 xml 添加时，会走这个方法获取子 View 的布局参数
    // 但是，默认的实现只会从 AttributeSet 里解析 layout_width 和 layout_height 这两个属性
    // 这里重写的目的是解析 margin 属性。
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(getContext(), attrs)
    }

    companion object {
        private const val TAG = "FlowLayout"
        const val LINE_VERTICAL_GRAVITY_TOP = 0
        const val LINE_VERTICAL_GRAVITY_CENTER_VERTICAL = 1
        const val LINE_VERTICAL_GRAVITY_BOTTOM = 2

        const val MODE_LIMIT_MAX_LINE = 0
        const val MODE_LIMIT_MAX_COUNT = 1
    }
}