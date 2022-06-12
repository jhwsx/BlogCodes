package com.example.tencentclassloadingview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import java.lang.Float.max
import kotlin.math.min

/**
 *
 * @author wangzhichao
 * @since 2022/6/12
 */
class TencentClassLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var tencentClassBitmap: Bitmap
    private val defaultSize = 150.dp
    private lateinit var waveBitmap: Bitmap
    private lateinit var waveCanvas: Canvas
    private val wavePath = Path()

    /**
     * 波长
     */
    private var waveLength = 0f

    /**
     * 振幅
     */
    private var amplitude = 0f
    private var amplitudeLight = 0f

    private val WAVE_COLOR = Color.parseColor("#E600A2E8")
    private val WAVE_COLOR_LIGHT = Color.parseColor("#9900A2E8")
    var offsetX: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    var offsetXLight: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    var waveHeight: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val animatorSet = AnimatorSet()
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val measuredWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else defaultSize.toInt()
        val measuredHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else defaultSize.toInt()
        val measuredSize = min(measuredWidth, measuredHeight)
        setMeasuredDimension(measuredSize, measuredSize)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        tencentClassBitmap = getImage(R.drawable.tencent_class, w)
        waveBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        waveCanvas = Canvas(waveBitmap)
        waveLength = w / 3f
        amplitude = h / 20f
        amplitudeLight = h / 25f
        val animator = ObjectAnimator.ofFloat(this, "offsetX", 0f, waveLength).apply {
            duration = 500L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
        }
        val  animatorLight = ObjectAnimator.ofFloat(this, "offsetXLight", waveLength, 0f).apply {
            duration = 300L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
        }
        val animatorHeight = ObjectAnimator.ofFloat(this, "waveHeight",  h.toFloat() + max(amplitude, amplitudeLight), -max(amplitude, amplitudeLight)).apply {
            duration = 2000L
            startDelay = 200L
            interpolator = AccelerateInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
        }
        animatorSet.playTogether(animator, animatorLight, animatorHeight)
        animatorSet.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(tencentClassBitmap, 0f, 0f, paint)
        drawWaveOnWaveBitmap()
        val saveCount = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        canvas.drawBitmap(waveBitmap, 0f, 0f, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(tencentClassBitmap, 0f, 0f, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveCount)
    }

    private fun drawWaveOnWaveBitmap() {
        waveCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)
        paint.color = WAVE_COLOR_LIGHT
        updateWavePath(offsetXLight, amplitudeLight)
        waveCanvas.drawPath(wavePath, paint)
        paint.color = WAVE_COLOR
        updateWavePath(offsetX, amplitude)
        waveCanvas.drawPath(wavePath, paint)
    }

    private fun updateWavePath(offsetX: Float, amplitude: Float) {
        wavePath.apply {
            reset()
            val initialStart = offsetX - waveLength
            moveTo(initialStart, waveHeight)
            var waveStart = initialStart
            while (waveStart <= width) {
                rQuadTo(waveLength / 4f, amplitude, waveLength / 2f, 0f)
                rQuadTo(waveLength / 4f, -amplitude, waveLength / 2f, 0f)
                waveStart += waveLength
            }
            lineTo(width.toFloat(), height.toFloat())
            lineTo(0f, height.toFloat())
            close()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animatorSet.cancel()
    }

    private fun getImage(drawable: Int, requestSize: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, drawable, options)
        options.inTargetDensity = requestSize
        options.inDensity = options.outWidth
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resources, drawable, options)
    }
}