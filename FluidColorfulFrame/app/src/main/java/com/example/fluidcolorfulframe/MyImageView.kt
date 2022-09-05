package com.example.fluidcolorfulframe

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.setPadding

/**
 *
 * @author wangzhichao
 * @since 2022/9/4
 */
class MyImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {
    private val drawable = FluidColorfulFrameDrawable()
    init {
        setPadding(5.dp.toInt())
        drawable.callback = this
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawable.setBounds(0, 0, w, h)
        drawable.startFluid()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        drawable.cancelFluid()
    }

    override fun invalidateDrawable(dr: Drawable) {
        super.invalidateDrawable(dr)
        if (dr === drawable) {
            invalidate()
        }
    }
}