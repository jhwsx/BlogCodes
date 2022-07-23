package com.example.jna;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class User extends Structure {
    public String name;
    public int height;
    public double weight;

    public User(String name, int height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"name", "height", "weight"});
    }

    // 类实现 Structure.ByReference 接口，表示这个类代表结构体指针
    public static class ByReference extends User implements Structure.ByReference {
        public ByReference(String name, int height, double weight) {
            super(name, height, weight);
        }
    }

    // 类实现 Structure.ByValue 接口，表示这个类代表结构体本身
    public static class ByValue extends User implements Structure.ByValue {
        public ByValue(String name, int height, double weight) {
            super(name, height, weight);
        }
    }
}