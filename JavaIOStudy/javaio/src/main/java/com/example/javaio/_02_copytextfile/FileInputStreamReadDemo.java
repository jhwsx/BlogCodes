package com.example.javaio._02_copytextfile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/16
 */
public class FileInputStreamReadDemo {
    public static void main(String[] args) throws IOException {
        // 1, 创建字节输入流，用于把文件读入内存
        FileInputStream fis = new FileInputStream("./file.txt");
        // 2, 从字节输入流中读取一个字节，如果已经到达文件末尾，则返回 -1
        System.out.println(fis.read());
        fis.close();
        System.out.println(fis.read());
        // 3, 关闭流资源
        fis.close();
    }
}
