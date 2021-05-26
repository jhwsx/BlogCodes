package com.example.lib.basic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * 包含泛型的测试
 *
 * 解决反序列化时出现的类型转换异常
 *
 * @author wangzhichao
 * @since 2021/5/27
 */
public class Test03 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        // 反序列化
        String json = "{\"code\":0,\"message\":\"success\",\"data\":{\"name\":\"willwaywang6\",\"age\":18}}";
        Type responseType = new TypeToken<Response<Person>>() {
        }.getType();
        Response<Person> r = gson.fromJson(json, responseType);
        System.out.println(r);
        System.out.println(r.data);
        try {
            Person p2 = r.data;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
