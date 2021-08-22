package com.example.javaio._01_file;

import java.io.File;

/**
 * 删除多级空目录
 */
public class DeleteEmptyDirsDemo {
    public static void main(String[] args) {
        deleteEmptyDir(new File("C:\\Users\\39233\\Downloads\\a"));
    }

    private static void deleteEmptyDir(File dir) {
        if (dir == null) {
            throw new IllegalArgumentException("dir is null");
        }
        File[] files = dir.listFiles();
        if (files == null) {
            // dir 不是一个目录，或者发生了I/O错误
            throw new RuntimeException("dir is not a directory, or i/o error happens");
        }
        if (files.length == 0) {
            // 是空目录
            dir.delete();
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                deleteEmptyDir(file);
            }
        }
        // 外层目录变成空目录
        File[] listFiles = dir.listFiles();
        if (listFiles != null && listFiles.length == 0) {
            dir.delete();
        }
    }
}
