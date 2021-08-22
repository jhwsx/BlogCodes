package com.example.javaio._01_file;

import java.io.File;
import java.io.IOException;

public class PathnameDemo {
    public static void main(String[] args) throws IOException {
        // 不代表任何文件或者目录
        printPathName("");
        // 当前工作目录
        printPathName(".");
        // 当前工作目录的上级目录
        printPathName("..");
        // 当前工作目录
        printPathName(".\\");
    }

    private static void printPathName(String pathname) throws IOException {
        System.out.println("pathname = " + pathname);
        File file = new File(pathname);
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());
    }
}
