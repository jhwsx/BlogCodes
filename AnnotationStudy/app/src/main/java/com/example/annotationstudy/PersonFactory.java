package com.example.annotationstudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/12/23
 */
public class PersonFactory {
    public List<Person> createPersonList() {
        List<Person> result = new ArrayList<>();
        // 编译正确
        result.add(new Person(R.mipmap.ic_launcher, R.string.name_peter));
        // 在 IDE 中可以传入的实参都变成红色，语法检查出错，
        // 参数 1 提示：Expected resource of type drawable
        // 参数 2 提示：Expected resource of type string
        result.add(new Person(1, 1));
        return result;
    }
}
