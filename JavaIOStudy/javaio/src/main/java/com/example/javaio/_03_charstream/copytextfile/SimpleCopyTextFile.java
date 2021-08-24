package com.example.javaio._03_charstream.copytextfile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/24
 */
public class SimpleCopyTextFile {
    public static void main(String[] args) throws IOException {
        // 1, 创建字符输入流，用于把文件读入内存
        FileReader fr = new FileReader("file.txt");
        // 2，创建字符输出流，用于把内存中的字符写入外部文件中
        FileWriter fw = new FileWriter("filecopy.txt");
        int ch;
        // 3，从字符输入流中读取一个字符，如果已经到达文件末尾，则返回-1
        while ((ch = fr.read()) != -1) {
            // 4，将读到的字符写入到字符输出流中
            fw.write(ch);
            // 5, 刷新该流的缓冲
            fw.flush();
        }
        // 5，关闭流资源
        fw.close();
        fr.close();
    }
}
