package com.example.customattributesstudy

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import java.util.*

/**
 *
 * @author wangzhichao
 * @since 2022/7/24
 */
fun Int.toHexString(): String {
    return StringBuilder().apply {
        append("#")
        append(Integer.toHexString(Color.alpha(this@toHexString)).uppercase(Locale.getDefault()))
        append(Integer.toHexString(Color.red(this@toHexString)).uppercase(Locale.getDefault()))
        fun String.doubleZero(): String {
            return if (this == "0") "00" else this
        }
        append(Integer.toHexString(Color.green(this@toHexString)).uppercase(Locale.getDefault()).doubleZero())
        append(Integer.toHexString(Color.blue(this@toHexString)).uppercase(Locale.getDefault()).doubleZero())
    }.toString()
}

