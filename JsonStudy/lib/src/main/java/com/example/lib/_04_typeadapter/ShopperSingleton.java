package com.example.lib._04_typeadapter;

/**
 * @author wangzhichao
 * @since 2021/5/31
 */
public class ShopperSingleton {

    public String userid;
    public String username;

    private static ShopperSingleton singleton = new ShopperSingleton();

    private ShopperSingleton() {

    }

    public static ShopperSingleton getInstance() {
        return singleton;
    }

    @Override
    public String toString() {
        return "ShopperSingleton@" + hashCode() + "{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
