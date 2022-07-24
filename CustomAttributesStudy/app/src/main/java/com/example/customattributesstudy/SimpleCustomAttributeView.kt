package com.example.customattributesstudy

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.res.use

/**
 * 简单自定义属性控件
 *
 * @author wangzhichao
 * @since 2022/7/23
 */
class SimpleCustomAttributeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        if (attrs != null) {
            for (index in 0 until attrs.attributeCount) {
                val attrName = attrs.getAttributeName(index)
                val attrValue = attrs.getAttributeValue(index)
                Log.d(TAG, "attr name: $attrName, attr value: $attrValue")
            }
            val authorId = attrs.getAttributeResourceValue(4, NO_ID)
            val name = resources.getString(authorId)
            Log.d(TAG, "name = $name")
        }
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.SimpleCustomAttributeView)
        val arrayLength = typedArray.length()
        Log.d(TAG, "arrayLength = $arrayLength")
        Log.d(TAG, "R.styleable.SimpleCustomAttributeView = ${R.styleable.SimpleCustomAttributeView.joinToString(transform = {it.toString(16)})}")
        Log.d(TAG, "R.styleable.SimpleCustomAttributeView = ${R.styleable.SimpleCustomAttributeView.joinToString()}")
        Log.d(TAG, "R.styleable.SimpleCustomAttributeView_age=${R.styleable.SimpleCustomAttributeView_age}")
        Log.d(TAG, "R.styleable.SimpleCustomAttributeView_gender=${R.styleable.SimpleCustomAttributeView_gender}")
        Log.d(TAG, "R.styleable.SimpleCustomAttributeView_name=${R.styleable.SimpleCustomAttributeView_name}")
        val name = typedArray.getString(R.styleable.SimpleCustomAttributeView_name)
        val age = typedArray.getInt(R.styleable.SimpleCustomAttributeView_age, 1)
        val gender = typedArray.getBoolean(R.styleable.SimpleCustomAttributeView_gender, true)
        Log.d(TAG, "name=$name,age=$age,gender=$gender")
        typedArray.recycle()

        val ta = context.obtainStyledAttributes(attrs, CUSTOM_ATTRS)
        val _age = ta.getInt(SimpleCustomAttributeView_age, 1)
        val _gender = ta.getBoolean(SimpleCustomAttributeView_gender, true)
        val _name = ta.getString(SimpleCustomAttributeView_name)
        Log.d(TAG, "_name=$_name,_age=$_age,_gender=$_gender")
        ta.recycle()

        context.obtainStyledAttributes(attrs, R.styleable.SimpleCustomAttributeView).use {
            // 在这里面进行属性解析
        }
    }

    class InnerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr)
    companion object {
        private const val TAG = "SimpleCustomAttribute"
        private val CUSTOM_ATTRS = intArrayOf(R.attr.age, R.attr.gender, R.attr.name)
        private const val SimpleCustomAttributeView_age = 0
        private const val SimpleCustomAttributeView_gender = 1
        private const val SimpleCustomAttributeView_name = 2
    }
}