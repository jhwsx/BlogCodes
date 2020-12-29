package com.example.annotationstudy;

/**
 * @author wangzhichao
 * @since 2020/12/23
 */
public class MyRunnable implements Runnable {

    // 这是正确的覆盖
    @Override
    @Test
    public void run() {
    }

    // 这是错误的覆盖，但仍然使用了 @Override 注解
//    @Override
//    public void run2() {
//    }
}
