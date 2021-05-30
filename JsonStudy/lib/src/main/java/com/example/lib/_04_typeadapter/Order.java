package com.example.lib._04_typeadapter;

import java.util.List;

/**
 * 订单
 *
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Order {
    public String orderid;
    public String userid;
    public String username;
    public List<Commodity> commodities;

    public Order(String orderid, String userid, String username, List<Commodity> commodities) {
        this.orderid = orderid;
        this.userid = userid;
        this.username = username;
        this.commodities = commodities;
    }
}
