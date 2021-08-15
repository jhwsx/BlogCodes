package com.example.javaio._01_file;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取指定目录下符合要求的所有文件。
 * @author wangzhichao
 * @since 2021/8/12
 */
public class RecurseDirDemo2 {
    public static void main(String[] args) {
        File dir = new File(".");
        List<File> list = getFileListInDir(dir, new SuffixFilenameFilter(".bat"));
        for (File file : list) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static List<File> getFileListInDir(File dir, FileFilter fileFilter) {
        List<File> result = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles(fileFilter);
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        result.addAll(getFileListInDir(file, fileFilter));
                    } else {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 保留指定后缀名的文件
     */
    private static class SuffixFilenameFilter implements FileFilter {
        private final String suffix;

        private SuffixFilenameFilter(String suffix) {
            this.suffix = suffix;
        }

        @Override
        public boolean accept(File pathname) {
            // 是目录，接受
            if (pathname.isDirectory()) return true;
            // 是文件，以指定后缀名结尾，才接受
            return pathname.isFile() && pathname.getName().endsWith(suffix);
        }
    }
}
