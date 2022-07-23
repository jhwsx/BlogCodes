package com.example.customattributesstudy

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 *
 * @author wangzhichao
 * @since 2022/7/23
 */
class SimpleCustomAttributeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.SimpleCustomAttributeView)
        val name = typedArray.getString(R.styleable.SimpleCustomAttributeView_name)
        val age = typedArray.getInt(R.styleable.SimpleCustomAttributeView_age, 1)
        val gender = typedArray.getBoolean(R.styleable.SimpleCustomAttributeView_gender, true)
        Log.d(TAG, "name=$name,age=$age,gender=$gender")
        typedArray.recycle()
    }

    class InnerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr)
    companion object {
        private const val TAG = "SimpleCustomAttribute"
    }
}