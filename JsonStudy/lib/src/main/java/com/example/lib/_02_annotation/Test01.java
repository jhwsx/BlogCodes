package com.example.lib._02_annotation;

import com.google.gson.Gson;

/**
 *
 * @author wangzhichao
 * @since 2021/5/27
 */
public class Test01 {
    public static void main(String[] args) {
        Gson gson = new Gson();
//        // 序列化
//        Response response = new Response(0, "ok", "some data");
//        String responseJson = gson.toJson(response);
//        System.out.println(responseJson);
//        // 反序列化
//        String json = "{\"code\":0,\"msg\":\"ok\",\"content\":\"some data\"}";
//        Response r = gson.fromJson(json, Response.class);
//        System.out.println(r);
//
//        String json1 = "{\"code\":0,\"msg\":\"ok\",\"content\":\"some data\"}";
//        String json2 = "{\"code\":1,\"msg\":\"server bang\",\"result\":\"blablabla\"}";
//        String json3 = "{\"code\":2,\"msg\":\"server bang\",\"result_data\":\"blablabla\"}";
//        System.out.println(gson.fromJson(json1, Response.class));
//        System.out.println(gson.fromJson(json2, Response.class));
//        System.out.println(gson.fromJson(json3, Response.class));
        // 出现多个备选时的反序列化
        String json4 = "{\"code\":4,\"msg\":\"server bang\",\"content\":\"some data\",\"result\":\"blablabla\",\"result_data\":\"plaplapla\"}";
        String json5 = "{\"code\":5,\"msg\":\"server bang\",\"result\":\"blablabla\",\"result_data\":\"plaplapla\",\"content\":\"some data\"}";
        String json6 = "{\"code\":6,\"msg\":\"server bang\",\"result_data\":\"plaplapla\",\"content\":\"some data\",\"result\":\"blablabla\"}";
        System.out.println(gson.fromJson(json4, Response.class));
        System.out.println(gson.fromJson(json5, Response.class));
        System.out.println(gson.fromJson(json6, Response.class));
    }
}
