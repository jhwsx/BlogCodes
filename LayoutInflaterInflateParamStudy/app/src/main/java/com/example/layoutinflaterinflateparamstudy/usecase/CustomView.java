package com.example.layoutinflaterinflateparamstudy.usecase;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.layoutinflaterinflateparamstudy.R;

/**
 * @author wangzhichao
 * @date 7/20/20
 */
public class CustomView extends LinearLayout {
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.custom_view_layout, this);
    }
}
