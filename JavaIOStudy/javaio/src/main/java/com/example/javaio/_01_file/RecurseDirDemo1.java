package com.example.javaio._01_file;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取指定目录下的所有文件。
 * @author wangzhichao
 * @since 2021/8/12
 */
public class RecurseDirDemo1 {
    public static void main(String[] args) {
        File dir = new File(".");
        List<File> list = getFileListInDir(dir);
        for (File file : list) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static List<File> getFileListInDir(File dir) {
        List<File> result = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        result.addAll(getFileListInDir(file));
                    } else {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }
}
