package com.example.javaio._01_file;

import java.io.File;

/**
 * @author wangzhichao
 * @since 2021/8/13
 */
public class MkDirMkDirsApiDemo {
    public static void main(String[] args) {
        System.out.println("new File(\"./dir1\").mkdir() = " + new File("./dir1").mkdir());
        System.out.println("new File(\"./dira\").mkdirs() = " + new File("./dira").mkdirs());
        System.out.println("new File(\"./dir_1/dir_2\").mkdir() = " + new File("./dir_1/dir_2").mkdir());
        System.out.println("new File(\"./dir_a/dir_b\").mkdirs() = " + new File("./dir_a/dir_b").mkdirs());
    }
}
