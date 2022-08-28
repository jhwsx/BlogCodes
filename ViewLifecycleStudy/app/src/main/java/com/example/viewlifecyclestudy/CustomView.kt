package com.example.viewlifecyclestudy

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Parcelable
import kotlin.jvm.JvmOverloads
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View

/**
 * @author wangzhichao
 * @since 2022/8/28
 */
private const val TAG = "CustomView"
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    init {
        Log.d(TAG, "Constructors")
    }

    // 当 View 及其子 View 已经从 XML 里面加载完成时会被调用
    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "onFinishInflate: ")
    }

    // 当需要决定 View 及其所有子 View 的尺寸要求时会被调用
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure: widthMeasureSpec=${MeasureSpec.toString(widthMeasureSpec)}, heightMeasureSpec=${MeasureSpec.toString(heightMeasureSpec)}")
    }

    // 当 View 应当分配尺寸并且定位它的所有子 View 时会被调用
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout: changed=$changed, left=$left, top=$top, right=$right, bottom=$bottom")
    }

    // 当 View 的尺寸已经发生变化时会被调用
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d(TAG, "onSizeChanged: w=$w, h=$h, oldw=$oldw, oldh=$oldh")
    }

    // 当 View 应当渲染内容时会被调用
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: ")
    }

    // 当一个新的物理按键事件发生时会被调用
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        Log.d(TAG, "onKeyDown: ")
        return super.onKeyDown(keyCode, event)
    }

    // 当物理按键抬起事件发生时会被调用
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        Log.d(TAG, "onKeyUp: ")
        return super.onKeyUp(keyCode, event)
    }

    // 当轨迹球运动事件发生时会被调用
    override fun onTrackballEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "onTrackballEvent: ")
        return super.onTrackballEvent(event)
    }

    // 当触摸屏运动事件发生时会被调用
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "onTouchEvent: ")
        return super.onTouchEvent(event)
    }

    // 当 View 或者它的父 View 的可见性发生改变时会被调用
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        Log.d(TAG, "onVisibilityChanged: changedView=${changedView.javaClass.simpleName}, visibility=${visibility.readable()}")
    }

    // 当 View 获得或者失去焦点时会被调用
    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
        Log.d(TAG, "onFocusChanged: gainFocus=$gainFocus, direction=$direction, previouslyFocusedRect=$previouslyFocusedRect")
    }

    // 当包含 View 的 Window 获得或者失去焦点时会被调用
    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        Log.d(TAG, "onWindowFocusChanged: hasWindowFocus=$hasWindowFocus")
    }

    // 当 View 和 Window 相关联时会被调用
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow: ")
    }

    // 当 View 从 Window 分离时会被调用
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow: ")
    }

    // 当包含 View 的 Window 的可见性发生改变时会被调用
    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        Log.d(TAG, "onWindowVisibilityChanged: visibility=${visibility.readable()}")
    }

    override fun onSaveInstanceState(): Parcelable? {
        Log.d(TAG, "onSaveInstanceState: ")
        return super.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        Log.d(TAG, "onRestoreInstanceState: ")
    }
}

private fun Int.readable(): String {
   return when(this) {
        View.VISIBLE -> "VISIBLE"
        View.INVISIBLE -> "INVISIBLE"
        View.GONE -> "GONE"
        else -> throw IllegalArgumentException("illegal value: $this")
    }
}