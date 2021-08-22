package com.example.javaio._02_bytestream.copytextfile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/16
 */
public class CustomBufferCopyTextFile {

    private static final int BUFFER_SIZE= 1024;

    public static void main(String[] args) throws IOException {
        // 1, 创建字节输入流，用于把文件读入内存
        FileInputStream fis = new FileInputStream("./file.txt");
        // 2, 创建字节输出流，用于把内存的字节写入外部文件中
        FileOutputStream fos = new FileOutputStream("./filecopy.txt");
        // 3, 定义一个字节数组，作为缓冲区
        byte[] buffer = new byte[BUFFER_SIZE];
        // 记录读入缓冲区的字节总数
        int len;
        // 4, 从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。
        // 如果已经到达文件末尾，则返回 -1.
        while((len = fis.read(buffer)) != -1) {
            // 5，将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流。
            fos.write(buffer, 0, len);
        }
        // 6, 关闭流资源
        fos.close();
        fis.close();
    }
}
