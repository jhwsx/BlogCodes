package com.example.javaio._01_file;

import java.io.File;

public class DeleteDirDemo {
    public static void main(String[] args) {
        deleteDir(new File("C:\\Users\\39233\\Downloads\\代码问题示例"));
    }

    /**
     * 删除目录
     * @param dir 要删除的目录
     */
    private static void deleteDir(File dir) {
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
            if (file.isFile()) {
                file.delete();
            } else {
                deleteDir(file);
            }
        }
        dir.delete();
    }
}


