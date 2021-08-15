package com.example.javaio._01_file;

import java.io.File;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/13
 */
public class CreateFileApiDemo {
    public static void main(String[] args) throws IOException {
        File file1 = new File("./a.txt");
        System.out.println("file1.createNewFile() = " + file1.createNewFile());
        try {
            File file2 = new File("./dirx/a.txt");
            System.out.println("file2.createNewFile() = " + file2.createNewFile());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        File dir = new File("./diry");
        if (!dir.exists()) {
            boolean mkdir = dir.mkdir();
            System.out.println(dir.getAbsolutePath() + "创建结果：" + mkdir);
            if (mkdir) {
                File file3 = new File("./diry/a.txt");
                System.out.println("file3.createNewFile() = " + file3.createNewFile());
            }
        }
        System.out.println(File.createTempFile("temp", ".tmp").getAbsolutePath());
    }
}
