package com.example.lib._04_typeadapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Test01 {
    public static void main(String[] args) {
//        demo1();
//        demo2();
        demo3();
    }

    private static void demo1() {
        Shopper shopper = new Shopper("userid_1234", "willwaywang6");
        List<Commodity> commodities = Arrays.asList(
                new Commodity("id_12345", "Huawei P50"),
                new Commodity("id_23456", "Huawei P60")
        );
        Order order = new Order("order_1111", shopper.userid, shopper.username, commodities);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(order));
    }

    private static void demo2() {
        Shopper shopper = new Shopper("userid_1234", "willwaywang6");
        List<Commodity> commodities = Arrays.asList(
                new Commodity("id_12345", "Huawei P50"),
                new Commodity("id_23456", "Huawei P60")
        );
        Order order = new Order("order_1111", shopper.userid, shopper.username, commodities);
        JsonSerializer<Commodity> jsonSerializer = new JsonSerializer<Commodity>() {
            @Override
            public JsonElement serialize(Commodity src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject result = new JsonObject();
                result.addProperty("id", src.id);
                return result;
            }
        };
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Commodity.class, jsonSerializer).create();
        System.out.println(gson.toJson(order));
    }

    private static void demo3() {
        Shopper shopper = new Shopper("userid_1234", "willwaywang6");
        final List<Commodity> commodities = Arrays.asList(
                new Commodity("id_12345", "Huawei P50"),
                new Commodity("id_23456", "Huawei P60")
        );
        Order order = new Order("order_1111", shopper.userid, shopper.username, commodities);
        JsonSerializer<List<Commodity>> jsonSerializer = new JsonSerializer<List<Commodity>>() {
            @Override
            public JsonElement serialize(List<Commodity> src, Type typeOfSrc, JsonSerializationContext context) {
                JsonArray jsonArray = new JsonArray();
                for (Commodity commodity : src) {
                    jsonArray.add(commodity.id);
                }
                return jsonArray;
            }
        };
        // 注意这里有泛型，要这样获取 type。
        Type type = new TypeToken<List<Commodity>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(type, jsonSerializer).create();
        System.out.println(gson.toJson(order));
    }
}
