package com.example.lib._03_builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Test03 {
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        UserBean userBean = new UserBean();
        userBean.userid = "204895272048";
        userBean.username = "奥特曼";
        userBean.age = 1000;
        userBean.height = 300;
        userBean.password = "1234567890";
        userBean.salary = 30000.0;
        userBean.address = "outer space, unknown";

        Gson gson = new Gson();
        System.out.println(gson.toJson(userBean));
    }

    private static void demo2() {
        UserBean userBean = new UserBean();
        userBean.userid = "204895272048";
        userBean.username = "奥特曼";
        userBean.age = 1000;
        userBean.height = 300;
        userBean.password = "1234567890";
        userBean.salary = 30000.0;
        userBean.address = "outer space, unknown";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(userBean));
    }
}
