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
public class JavaBufferCopyTextFile {

    public static void main(String[] args) throws IOException {
        // 1, 创建字节输入流，用于把文件读入内存
        FileInputStream fis = new FileInputStream("./file.txt");
        // 2, 创建字节输入流的缓冲区对象，关联需要被缓冲的字节输入流对象
//        BufferedInputStream bis = new BufferedInputStream(fis);
        MyBufferedInputStream bis = new MyBufferedInputStream(fis);
        // 3, 创建字节输出流，用于把内存的字节写入外部文件中
        FileOutputStream fos = new FileOutputStream("./filecopy.txt");
        // 4, 创建字节输出流的缓冲区对象，关联需要被缓冲的字节输出流对象
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int b;
        // 5, 从字节输入流的缓冲区对象中读取一个字节，读取不到时返回-1
        while((b = bis.read()) != -1) {
            // 6，将指定字节写入到字节输出流的缓冲区对象。
            bos.write(b);
            bos.flush();
        }
        // 7, 关闭流资源
        bos.close();
        bis.close();
    }
}
