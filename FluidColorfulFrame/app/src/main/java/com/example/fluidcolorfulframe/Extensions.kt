package com.example.fluidcolorfulframe

import android.content.res.Resources
import android.util.TypedValue

/**
 *
 * @author wangzhichao
 * @since 2022/9/4
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics)
val Int.dp
    get() = this.toFloat().dp