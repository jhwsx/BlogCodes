package com.example.javaio._02_bytestream.copytextfile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/18
 */
public class JavaBufferCopyTextFile2 {

    public static void main(String[] args) throws IOException {
        // 1, 创建字节输入流，用于把文件读入内存
        FileInputStream fis = new FileInputStream("./file.txt");
        System.out.println("fis.available() = " + fis.available()); // 101
        // 2, 创建字节输入流的缓冲区对象，关联需要被缓冲的字节输入流对象
//        BufferedInputStream bis = new BufferedInputStream(fis);
        MyBufferedInputStream2 bis = new MyBufferedInputStream2(fis, 50);
        // 3, 创建字节输出流，用于把内存的字节写入外部文件中
        FileOutputStream fos = new FileOutputStream("./filecopy.txt");
        // 4, 创建字节输出流的缓冲区对象，关联需要被缓冲的字节输出流对象
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] b = new byte[100];
        int len;
        // 5, 从此缓冲输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。
        // 如果已经到达文件末尾，则返回 -1.
        while((len = bis.read(b)) != -1) {
            // 6，将指定 byte 数组中从偏移量 off 开始的 len 个字节写入缓冲输出流
            bos.write(b, 0, len);
            bos.flush();
        }
        // 7, 关闭流资源
        bos.close();
        bis.close();
    }
}
