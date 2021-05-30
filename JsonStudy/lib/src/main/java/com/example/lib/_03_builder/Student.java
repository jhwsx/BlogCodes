package com.example.lib._03_builder;

import java.util.Date;

/**
 * @author wangzhichao
 * @since 2021/5/30
 */
public class Student {
    public String name;
    public int age;
    public boolean gender;
    public Date birthday;
    public String _school;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", _school='" + _school + '\'' +
                '}';
    }
}
