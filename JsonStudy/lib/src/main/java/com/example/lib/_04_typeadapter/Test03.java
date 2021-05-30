package com.example.lib._04_typeadapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * @author wangzhichao
 * @since 2021/5/31
 */
public class Test03 {
    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
        demo4();
    }

    private static void demo1() {
        String json = "{\"userid\":\"id_12345\",\"username\":\"willwaywang6\"}";
        Gson gson = new GsonBuilder().create();
        ShopperContext shopperContext = gson.fromJson(json, ShopperContext.class);
        System.out.println(shopperContext);
    }

    private static void demo2() {
        String json = "{\"userid\":\"id_12345\",\"username\":\"willwaywang6\"}";
        // 模拟一个 Context 对象
        final Context context = new Context();
        InstanceCreator<ShopperContext> instanceCreator = new InstanceCreator<ShopperContext>() {
            @Override
            public ShopperContext createInstance(Type type) {
                return new ShopperContext(context);
            }
        };
        Gson gson = new GsonBuilder().registerTypeAdapter(ShopperContext.class, instanceCreator).create();
        ShopperContext shopperContext = gson.fromJson(json, ShopperContext.class);
        System.out.println(shopperContext);
    }

    private static void demo3() {
        String json = "{\"userid\":\"id_12345\",\"username\":\"willwaywang6\"}";
        Gson gson = new GsonBuilder().create();
        System.out.println(ShopperSingleton.getInstance());
        ShopperSingleton shopperContext = gson.fromJson(json, ShopperSingleton.class);
        System.out.println(shopperContext);
    }

    private static void demo4() {
        String json = "{\"userid\":\"id_12345\",\"username\":\"willwaywang6\"}";
        System.out.println(ShopperSingleton.getInstance());
        InstanceCreator<ShopperSingleton> instanceCreator = new InstanceCreator<ShopperSingleton>() {
            @Override
            public ShopperSingleton createInstance(Type type) {
                return ShopperSingleton.getInstance();
            }
        };
        Gson gson = new GsonBuilder().registerTypeAdapter(ShopperSingleton.class, instanceCreator).create();
        ShopperSingleton shopperContext = gson.fromJson(json, ShopperSingleton.class);
        System.out.println(shopperContext);
    }
}
