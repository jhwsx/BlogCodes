package com.example.layoutinflaterinflateparamstudy.usecase;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.layoutinflaterinflateparamstudy.R;

/**
 * 根节点使用 merge 标签,减少了一个布局层级
 *
 * @author wangzhichao
 * @date 7/20/20
 */
public class CustomMergeView extends LinearLayout {
    public CustomMergeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.custom_merge_view_layout, this);
    }
}
