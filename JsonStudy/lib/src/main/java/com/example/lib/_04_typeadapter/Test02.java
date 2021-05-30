package com.example.lib._04_typeadapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Test02 {
    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo1() {
        String goodsJson = "{\"name\":\"Huawei P100\",\"weight\":1,\"timestamp\":1622360677020}";
        Gson gson = new GsonBuilder().create();
        Goods goods = gson.fromJson(goodsJson, Goods.class);
        System.out.println(goods);
    }

    private static void demo2() {
        String goodsJson = "{\"name\":\"Huawei P100\",\"weight\":\"\",\"timestamp\":1622360677020}";
        Gson gson = new GsonBuilder().create();
        Goods goods = gson.fromJson(goodsJson, Goods.class);
        System.out.println(goods);
    }
}
