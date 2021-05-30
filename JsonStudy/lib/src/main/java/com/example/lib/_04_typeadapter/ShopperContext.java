package com.example.lib._04_typeadapter;

/**
 * 买家
 *
 * @author wangzhichao
 * @since 2021/5/30
 */
public class ShopperContext {
    public String userid;
    public String username;
    public Context context;

    public ShopperContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "ShopperContext{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", context=" + context +
                '}';
    }
}
