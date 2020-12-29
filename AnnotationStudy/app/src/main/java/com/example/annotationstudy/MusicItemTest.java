package com.example.annotationstudy;

import androidx.annotation.IntRange;

/**
 * @author wangzhichao
 * @since 2020/12/28
 */
public class MusicItemTest {
    public static void main(String[] args) {
        MusicItem musicItem = new MusicItem();
        musicItem.setType(2);

        @IntRange(from = 1) int pageNumber = 0;
    }
}
