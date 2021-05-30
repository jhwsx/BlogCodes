package com.example.lib._03_builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Test04 {
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        SimpleBean bean = new SimpleBean();
        bean.title = "Mr Bush's House";
        bean.message = "a > b";
        Gson gson = new Gson();
        System.out.println(gson.toJson(bean));
    }

    private static void demo2() {
        SimpleBean bean = new SimpleBean();
        bean.title = "Mr Bush's House";
        bean.message = "a > b";
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        System.out.println(gson.toJson(bean));
    }
}
