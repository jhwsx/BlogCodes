package com.example.javaio._01_file;

import java.io.File;

/**
 * @author wangzhichao
 * @since 2021/8/13
 */
public class ListFilesApiDemo {
    public static void main(String[] args) {
        list(new File("."));
        list(new File("./emptyDir"));
        list(new File("what"));
    }

    private static void list(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println(dir.getAbsolutePath() + "不是目录");
        } else if (files.length == 0) {
            System.out.println(dir.getAbsolutePath() + "是空目录");
        } else {
            System.out.println(dir.getAbsolutePath() + "里包含文件");
        }
    }
}
