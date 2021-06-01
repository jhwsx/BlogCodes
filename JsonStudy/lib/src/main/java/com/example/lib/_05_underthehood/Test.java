package com.example.lib._05_underthehood;

import com.google.gson.Gson;

/**
 * @author wangzhichao
 * @since 2021/6/1
 */
public class Test {
    public static void main(String[] args) {
        Gson gson = new Gson();
        // 序列化
        Person person = new Person("willwaywang6", 18);
        String personJson = gson.toJson(person);
        System.out.println(personJson);

        // 反序列化
//        String json = "{\"name\":\"willwaywang6\",\"age\":18}";
//        Person p = gson.fromJson(json, Person.class);
//        System.out.println(p);
    }
}
