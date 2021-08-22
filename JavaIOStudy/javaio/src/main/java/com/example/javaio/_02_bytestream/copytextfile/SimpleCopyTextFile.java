package com.example.javaio._02_bytestream.copytextfile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/16
 */
public class SimpleCopyTextFile {
    public static void main(String[] args) throws IOException {
        // 1, 创建字节输入流，用于把文件读入内存
        FileInputStream fis = new FileInputStream("./file.txt");
//        FileInputStream fis = new FileInputStream("./unexistfile.txt");
//        FileInputStream fis = new FileInputStream("./dir");
        // 2, 创建字节输出流，用于把内存的字节写入外部文件中
        FileOutputStream fos = new FileOutputStream("./filecopy.txt");
//        FileOutputStream fos = new FileOutputStream("./filecopy.txt", true);
        int b;
        // 3, 从字节输入流中读取一个字节，如果已经到达文件末尾，则返回 -1
        while((b = fis.read()) != -1) {
            // 4，将读到的字节写入到字节输出流中
            fos.write(b);
        }
        // 5, 关闭流资源
        fos.close();
        fis.close();
    }
}
