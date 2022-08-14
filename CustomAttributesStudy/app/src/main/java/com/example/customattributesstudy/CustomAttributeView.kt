package com.example.customattributesstudy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.res.getColorOrThrow
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.core.graphics.toColorLong

/**
 * 演示自定义属性 format 的控件
 *
 * @author wangzhichao
 * @since 2022/7/23
 */
class CustomAttributeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomAttributeView)
        val id = typedArray.getResourceId(R.styleable.CustomAttributeView_cav_id, NO_ID)
        Log.d(TAG, "id = $id, hex: ${id.toString(16)}")
        val maxLines = typedArray.getInt(R.styleable.CustomAttributeView_cav_maxLines, -1)
        Log.d(TAG, "maxLines = $maxLines")
        val minLines = typedArray.getInteger(R.styleable.CustomAttributeView_cav_minLines, -1)
        Log.d(TAG, "minLines = $minLines")
        val rotationX = typedArray.getFloat(R.styleable.CustomAttributeView_cav_rotationX,0f)
        Log.d(TAG, "rotationX = $rotationX")
        val rotationY = typedArray.getFloat(R.styleable.CustomAttributeView_cav_rotationY,0f)
        Log.d(TAG, "rotationY = $rotationY")
        val clickable = typedArray.getBoolean(R.styleable.CustomAttributeView_cav_clickable, false)
        Log.d(TAG, "clickable = $clickable")
        val longClickable = typedArray.getBoolean(R.styleable.CustomAttributeView_cav_longClickable, false)
        Log.d(TAG, "longClickable = $longClickable")
        val pivotX = typedArray.getFraction(R.styleable.CustomAttributeView_cav_pivotX, 1, 1, -1f)
        Log.d(TAG, "pivotX = $pivotX")
        val pivotY = typedArray.getFraction(R.styleable.CustomAttributeView_cav_pivotY, 1, 1, -1f)
        Log.d(TAG, "pivotY = $pivotY")
        val paddingLeft: Int = typedArray.getDimensionPixelSize(R.styleable.CustomAttributeView_cav_paddingLeft, 0)
        Log.d(TAG, "paddingLeft = $paddingLeft")
        val paddingRight: Float = typedArray.getDimension(R.styleable.CustomAttributeView_cav_paddingRight, 0f)
        Log.d(TAG, "paddingRight = $paddingRight")
        val paddingRight2: Int = typedArray.getDimensionPixelOffset(R.styleable.CustomAttributeView_cav_paddingRight, 0)
        Log.d(TAG, "paddingRight2 = $paddingRight2")
        val tag1 = typedArray.getString(R.styleable.CustomAttributeView_cav_tag1)
        Log.d(TAG, "tag1 = $tag1")
        val tag2 = typedArray.getString(R.styleable.CustomAttributeView_cav_tag2)
        Log.d(TAG, "tag2 = $tag2")
        val tag3 = typedArray.getText(R.styleable.CustomAttributeView_cav_tag3)
        Log.d(TAG, "tag3 = $tag3")
        val backgroundTint = typedArray.getColor(R.styleable.CustomAttributeView_cav_backgroundTint, 0)
        Log.d(TAG, "backgroundTint = ${backgroundTint.toHexString()}")
        val foregroundTint = typedArray.getColor(R.styleable.CustomAttributeView_cav_foregroundTint, 0)
        Log.d(TAG, "foregroundTint = ${foregroundTint.toHexString()}")
        val backgroundTint2 = typedArray.getColorStateList(R.styleable.CustomAttributeView_cav_backgroundTint)
        Log.d(TAG, "backgroundTint2 = $backgroundTint2")
        val foregroundTint2 = typedArray.getColorStateList(R.styleable.CustomAttributeView_cav_foregroundTint)
        Log.d(TAG, "foregroundTint2 = $foregroundTint2")
        val visibility = typedArray.getInt(R.styleable.CustomAttributeView_cav_visibility, 0)
        Log.d(TAG, "visibility = $visibility")
        val textStyle = typedArray.getInt(R.styleable.CustomAttributeView_cav_textStyle, 0)
        Log.d(TAG, "textStyle = $textStyle")
        typedArray.recycle()
        postOnAnimation {  }
        post {  }
    }

    companion object {
        private const val TAG = "CustomAttributeView"
    }
}