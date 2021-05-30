package com.example.lib._03_builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Test01 {
    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo1() {
        Gson gson = new Gson();
        RequestBean requestBean = new RequestBean();
        requestBean.id = 1;
        requestBean.title = "国士无双";
        System.out.println(gson.toJson(requestBean));
    }

    private static void demo2() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        RequestBean requestBean = new RequestBean();
        requestBean.id = 1;
        requestBean.title = "国士无双";
        System.out.println(gson.toJson(requestBean));
    }
}
