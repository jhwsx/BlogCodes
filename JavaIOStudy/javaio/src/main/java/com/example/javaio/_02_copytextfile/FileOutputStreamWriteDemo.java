package com.example.javaio._02_copytextfile;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/16
 */
public class FileOutputStreamWriteDemo {
    public static void main(String[] args) throws IOException {
        // 1, 创建字节输出流，用于把内存的字节写入外部文件中
        FileOutputStream fos = new FileOutputStream("./filecopy.txt");
        // 2, 将指定的字节写入到字节输出流中
        // 0110 0001 -> 97
        fos.write(97);
        fos.close();
        // 1 0110 0001 -> 353
        fos.write(353);
        // 3, 关闭流资源
        fos.close();
    }
}
