package com.example.customattributesstudy

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * 使用 style 的自定义属性控件
 *
 * @author wangzhichao
 * @since 2022/7/24
 */
class StyleCustomAttributeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.styleCustomAttributeViewStyle
) : View(context, attrs, defStyleAttr) {
    init {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.StyleCustomAttributeView, defStyleAttr, 0)
        val name = typedArray.getString(R.styleable.StyleCustomAttributeView_name)
        val age = typedArray.getInt(R.styleable.StyleCustomAttributeView_age, 1)
        val gender = typedArray.getBoolean(R.styleable.StyleCustomAttributeView_gender, true)
        Log.d(TAG, "name=$name,age=$age,gender=$gender")
        typedArray.recycle()
    }

    companion object {
        private const val TAG = "StyleCustomAttribute"
    }
}