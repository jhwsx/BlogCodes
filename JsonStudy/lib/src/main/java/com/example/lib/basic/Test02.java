package com.example.lib.basic;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * 包含泛型的测试
 *
 * @author wangzhichao
 * @since 2021/5/27
 */
public class Test02 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        // 序列化
        Person person = new Person("willwaywang6", 18);
        Response<Person> response = new Response<>();
        response.code = 0;
        response.message = "success";
        response.data = person;
        String responseJson = gson.toJson(response);
        System.out.println(responseJson);
        // 反序列化
        String json = "{\"code\":0,\"message\":\"success\",\"data\":{\"name\":\"willwaywang6\",\"age\":18}}";
        Response<Person> r = gson.fromJson(json, Response.class);
        System.out.println(r);
        System.out.println(r.data);
        try {
            Person p2 = r.data;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
