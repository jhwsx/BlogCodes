package com.example.lib._03_builder;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Test05 {
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz == Date.class || clazz == boolean.class;
            }
        }).create();
        // 序列化
        Student student = new Student();
        student.name = "willwaywang6";
        student.age = 18;
        student.gender = true;
        student.birthday = new Date(System.currentTimeMillis());
        student._school = "college";
        System.out.println(gson.toJson(student));
        // 反序列化
        String json = "{\"name\":\"willwaywang6\",\"age\":18,\"gender\":true,\"birthday\":\"May 30, 2021 1:58:25 PM\",\"_school\":\"college\"}";
        Student s = gson.fromJson(json, Student.class);
        System.out.println(s);
    }
    private static void demo2() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().startsWith("_");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz == Date.class || clazz == boolean.class;
            }
        }).create();
        // 序列化
        Student student = new Student();
        student.name = "willwaywang6";
        student.age = 18;
        student.gender = true;
        student.birthday = new Date(System.currentTimeMillis());
        student._school = "college";
        System.out.println(gson.toJson(student));
        // 反序列化
        String json = "{\"name\":\"willwaywang6\",\"age\":18,\"gender\":true,\"birthday\":\"May 30, 2021 1:58:25 PM\",\"_school\":\"college\"}";
        Student s = gson.fromJson(json, Student.class);
        System.out.println(s);
    }
}
