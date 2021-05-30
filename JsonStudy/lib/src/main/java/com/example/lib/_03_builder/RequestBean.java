package com.example.lib._03_builder;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class RequestBean {
    /**
     * 文章 id
     */
    public int id;
    /**
     * 文章标题
     */
    public String title;
    /**
     * 用户喜欢，则为 true；用户不喜欢，则为 false；用户没有选择，则为 null
     */
    public Boolean action;
}
