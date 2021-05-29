package com.example.lib.basic;

import com.google.gson.Gson;

/**
 * 基本测试
 * @author wangzhichao
 * @since 2021/5/27
 */
public class Test01 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        // 序列化
        Person person = new Person("willwaywang6", 18);
        String personJson = gson.toJson(person);
        System.out.println(personJson);

        // 反序列化
        String json = "{\"name\":\"willwaywang6\",\"age\":18}";
        Person p = gson.fromJson(json, Person.class);
        System.out.println(p);
    }
}
