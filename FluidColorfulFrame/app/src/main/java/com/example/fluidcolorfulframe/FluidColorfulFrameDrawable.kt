package com.example.fluidcolorfulframe

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.core.graphics.toColorInt

/**
 *
 * @author wangzhichao
 * @since 2022/9/4
 */
private const val TAG = "FluidColorfulFrame"
class FluidColorfulFrameDrawable : Drawable() {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var bounds: RectF
    private val rectF = RectF()
    private val defaultRadius: Float = 10.dp
    private val defaultStrokeWidth: Float = 5.dp
    private val colors: IntArray
    private val positions: FloatArray
    private val mtx = Matrix()
    private var degree: Float = 0f
        set(value) {
            field = value
            Log.d(TAG, "degree setter called")
            invalidateSelf()
        }

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = defaultStrokeWidth
        colors = intArrayOf(
            "#FF0000FF".toColorInt(), // 蓝 0f
            "#FF000000".toColorInt(), // 黑 0.02f
            "#FF000000".toColorInt(), // 黑 0.25f
            "#FFFF0000".toColorInt(), // 红 0.27f
            "#FFFF0000".toColorInt(), // 红 0.37f
            "#FF00FF00".toColorInt(), // 绿 0.39f
            "#FF0000FF".toColorInt(), // 蓝 0.49f
            "#FFFFFF00".toColorInt(), // 黄 0.51f
            "#FF000000".toColorInt(), // 黑 0.53f
            "#FF000000".toColorInt(), // 黑 0.75f
            "#FFFF0000".toColorInt(), // 红 0.77f
            "#FFFF0000".toColorInt(), // 红 0.87f
            "#FFFFFF00".toColorInt(), // 黄 0.91f
            "#FF0000FF".toColorInt(), // 蓝 0.96f
        )

        positions = floatArrayOf(
            0f,
            0.02f,
            0.25f,
            0.27f,
            0.37f,
            0.39f,
            0.49f,
            0.51f,
            0.53f,
            0.75f,
            0.77f,
            0.87f,
            0.91f,
            0.96f,
        )
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        bounds = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        rectF.left = defaultStrokeWidth / 2
        rectF.top = defaultStrokeWidth / 2
        rectF.right = bounds.width() - defaultStrokeWidth / 2
        rectF.bottom = bounds.height() - defaultStrokeWidth / 2
        paint.shader = SweepGradient(bounds.centerX(), bounds.centerY(), colors, positions)
    }

    override fun draw(canvas: Canvas) {
        Log.d(TAG, "draw: ")
        mtx.reset()
        mtx.setRotate(degree, bounds.centerX(), bounds.centerY())
        (paint.shader as SweepGradient).setLocalMatrix(mtx)
        canvas.drawRoundRect(rectF, defaultRadius, defaultRadius, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
    private var fluidAnim: ObjectAnimator? = null

    fun startFluid() {
        fluidAnim = ObjectAnimator.ofFloat(this, "degree", 0f, 360f).apply {
            duration = 2000L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            start()
        }
    }

    fun cancelFluid() {
        fluidAnim?.cancel()
    }
}