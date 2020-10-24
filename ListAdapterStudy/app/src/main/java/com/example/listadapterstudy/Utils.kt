package com.example.listadapterstudy

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

/**
 *
 *
 * @author wangzhichao
 * @date 20-10-24
 */
fun Int.dp2px() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(), Resources.getSystem().displayMetrics
).toInt()

fun getColorList(): List<Int> {
    return arrayListOf(
        R.color.red_200,
        R.color.pink_200,
        R.color.green_200,
        R.color.lime_200,
        R.color.orange_200,
        R.color.brown_200,
        R.color.gray_200,
        R.color.pink_400,
        R.color.green_400,
        R.color.lime_400,
        R.color.orange_400,
        R.color.brown_400,
        R.color.gray_400,
        R.color.red_600,
        R.color.pink_600,
        R.color.green_600,
        R.color.lime_600,
        R.color.orange_600,
        R.color.brown_600,
        R.color.cyan_600

    )
}
