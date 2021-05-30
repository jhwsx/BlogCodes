package com.example.lib._02_annotation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Expose 注解的例子
 *
 * @author wangzhichao
 * @since 2021/5/28
 */
public class Test02 {
    public static void main(String[] args) {
        demo1();

        demo2();

    }

    private static void demo2() {
        // 序列化
        Person person = new Person("zhichao", "wang", 18, "123456", "13912345678");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(gson.toJson(person));
        // 反序列化
        String json = "{\"firstName\":\"zhichao\",\"lastName\":\"wang\",\"age\":18,\"password\":\"123456\",\"phoneNumber\":\"13912345678\"}";
        System.out.println(gson.fromJson(json, Person.class));
    }

    private static void demo1() {
        // 序列化
        Person person = new Person("zhichao", "wang", 18, "123456", "13912345678");
        Gson gson = new Gson();
        System.out.println(gson.toJson(person));
        // 反序列化
        String json = "{\"firstName\":\"zhichao\",\"lastName\":\"wang\",\"age\":18,\"password\":\"123456\",\"phoneNumber\":\"13912345678\"}";
        System.out.println(gson.fromJson(json, Person.class));
    }
}
