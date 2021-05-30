package com.example.lib._04_typeadapter;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Goods {
    public String name;
    public int weight;
    public long timestamp;

    public Goods(String name, int weight, long timestamp) {
        this.name = name;
        this.weight = weight;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", timestamp=" + timestamp +
                '}';
    }
}
