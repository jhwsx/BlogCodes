package com.example.lib._04_typeadapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Test02 {
    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
        demo5();
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

    private static void demo3() {
        String goodsJson = "{\"name\":\"Huawei P100\",\"weight\":\"\",\"timestamp\":1622360677020}";
        JsonDeserializer<Integer> jsonDeserializer = new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json.getAsInt();
                } catch (Exception exception) {
                    return 0;
                }
            }
        };
        Gson gson = new GsonBuilder().registerTypeAdapter(int.class, jsonDeserializer).create();
        Goods goods = gson.fromJson(goodsJson, Goods.class);
        System.out.println(goods);
    }
    private static void demo4() {
        String goodsJson = "{\"name\":\"Huawei P100\",\"weight\":\"null\",\"timestamp\":1622360677020}";
        JsonDeserializer<Integer> jsonDeserializer = new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json.getAsInt();
                } catch (Exception exception) {
                    return 0;
                }
            }
        };
        Gson gson = new GsonBuilder().registerTypeAdapter(int.class, jsonDeserializer).create();
        Goods goods = gson.fromJson(goodsJson, Goods.class);
        System.out.println(goods);
    }

    private static void demo5() {
        String goodsJson = "{\"name\":\"Huawei P100\",\"weight\":\"\",\"timestamp\":1622360677020}";
        JsonDeserializer<Integer> jsonDeserializer = new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json.getAsInt();
                } catch (Exception exception) {
                    return 0;
                }
            }
        };
        Gson gson = new GsonBuilder().registerTypeAdapter(Integer.class, jsonDeserializer).create();
        Goods2 goods = gson.fromJson(goodsJson, Goods2.class);
        System.out.println(goods);
    }
}
