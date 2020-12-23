package com.example.annotationstudy;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * @author wangzhichao
 * @since 2020/12/23
 */
public class Person {
    @DrawableRes
    private int avatarResId;
    @StringRes
    private int nameResId;

    // @DrawableRes 注解表示期望这个 int 值是一个图片资源类的 id
    // @StringRes 注解表示期望这个 int 值是一个 String 资源的 id
    public Person(@DrawableRes int avatarResId, @StringRes int nameResId) {
        this.avatarResId = avatarResId;
        this.nameResId = nameResId;
    }
}
