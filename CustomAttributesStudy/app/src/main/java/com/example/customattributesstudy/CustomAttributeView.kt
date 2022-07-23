package com.example.customattributesstudy

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
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
        typedArray.recycle()
    }

    companion object {
        private const val TAG = "CustomAttributeView"
    }
}