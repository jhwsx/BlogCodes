package com.example.javaio._03_charstream.copytextfile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/24
 */
public class CustomBufferCopyTextFile {
    public static void main(String[] args) throws IOException {
        // 1, 创建字符输入流，用于把文件读入内存
        FileReader fr = new FileReader("file.txt");
        // 2，创建字符输出流，用于把内存中的字符写入外部文件中
        FileWriter fw = new FileWriter("filecopy.txt");
        // 3，自定义一个字符数组，作为缓冲区
        char[] buffer = new char[1024];
        // 记录读入缓冲区的字符数
        int len;
        // 4, 从此输入流中将最多 b.length 个字符的数据读入一个字符数组中。
        // 如果已经到达文件末尾，则返回 -1.
        while((len = fr.read(buffer)) != -1) {
            // 5，将缓冲区中读到的字符写入到字符输出流中
            fw.write(buffer, 0, len);
            // 6, 刷新该流的缓冲
            fw.flush();
        }
        // 7，关闭流资源
        fw.close();
        fr.close();
    }
}
