package com.blog.flowlayout

import android.content.res.Resources
import android.util.TypedValue

fun Int.dp2px(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
}