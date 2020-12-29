package com.example.annotationstudy;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author wangzhichao
 * @since 2020/12/28
 */
public class MusicItem {
    @IntDef({ITEM_TYPE_MUSIC, ITEM_TYPE_AD})
    @Retention(RetentionPolicy.SOURCE)
    @interface ItemType {
    }

    public static final int ITEM_TYPE_MUSIC = 0;
    public static final int ITEM_TYPE_AD = 1;

    private int type;

    public @ItemType int getType() {
        return type;
    }

    public void setType(@ItemType int type) {
        this.type = type;
    }
}
