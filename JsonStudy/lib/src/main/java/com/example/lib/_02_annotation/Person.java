package com.example.lib._02_annotation;

import com.google.gson.annotations.Expose;

/**
 * @author wangzhichao
 * @since 2021/5/28
 */
public class Person {
    @Expose
    String firstName;
    @Expose(deserialize = false)
    String lastName;
    @Expose(serialize = false)
    int age;
    @Expose(serialize = false, deserialize = false)
    String password;

    String phoneNumber;

    public Person(String firstName, String lastName, int age, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age  +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
