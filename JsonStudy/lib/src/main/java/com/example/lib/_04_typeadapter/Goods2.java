package com.example.lib._04_typeadapter;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Goods2 {
    public String name;
    public Integer weight;
    public long timestamp;

    public Goods2(String name, Integer weight, long timestamp) {
        this.name = name;
        this.weight = weight;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Goods2{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", timestamp=" + timestamp +
                '}';
    }
}
