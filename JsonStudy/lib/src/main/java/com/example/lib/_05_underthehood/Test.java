package com.example.lib._05_underthehood;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MyReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;

/**
 * @author wangzhichao
 * @since 2021/6/1
 */
public class Test {
    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo2() {
        ConstructorConstructor constructorConstructor = new ConstructorConstructor(Collections.<Type, InstanceCreator<?>>emptyMap());
        Gson gson = new GsonBuilder().registerTypeAdapter(
                Person.class,
                new MyReflectiveTypeAdapterFactory(constructorConstructor).create(new Gson(), TypeToken.get(Person.class))
        ).create();
        // 序列化
        Person person = new Person("willwaywang6", 18);
        String personJson = gson.toJson(person);
        System.out.println(personJson);
    }

    private static void demo1() {
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
