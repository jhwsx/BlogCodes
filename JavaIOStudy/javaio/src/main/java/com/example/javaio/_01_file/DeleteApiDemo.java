package com.example.javaio._01_file;

import java.io.File;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/13
 */
public class DeleteApiDemo {
    public static void main(String[] args) throws IOException {
        new File("./existfile.txt").createNewFile();
        deletePrint(new File("./existfile.txt"));
        deletePrint(new File("./notexistfile.txt"));
        // mkdir:创建单级目录
        boolean mkdir = new File("./existdir").mkdir();
        System.out.println("mkdir = "+mkdir);
        deletePrint(new File("./existdir"));
        deletePrint(new File("./notexistdir"));
        // mkdirs:创建多级目录
        boolean mkdirs = new File("./existdir1/existdir2").mkdirs();
        System.out.println("mkdirs = " + mkdirs);
        deletePrint(new File("./existdir1"));
    }

    private static void deletePrint(File file1) {
        System.out.println("删除" + file1.getName() + ",结果：" + file1.delete());
    }
}
