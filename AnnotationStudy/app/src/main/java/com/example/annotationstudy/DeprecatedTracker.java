package com.example.annotationstudy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangzhichao
 * @since 2020/12/26
 */
public class DeprecatedTracker {
    public static void trackDeprecated(Class<?> cl) {
        // 查找成员变量
        Field[] declaredFields = cl.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(Deprecated.class) != null) {
                System.out.println("deprecated field:" + field);
            }
        }
        // 查找成员方法
        Method[] declaredMethods = cl.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Deprecated.class)) {
                System.out.println("deprecated method:" +  method);
            }
        }
        // 查找构造方法
        Constructor<?>[] declaredConstructors = cl.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.isAnnotationPresent(Deprecated.class)) {
                System.out.println("deprecated constructor:" + constructor);
            }
        }
    }

    public static void main(String[] args) {
        DeprecatedTracker.trackDeprecated(VideoItem.class);
    }
}
