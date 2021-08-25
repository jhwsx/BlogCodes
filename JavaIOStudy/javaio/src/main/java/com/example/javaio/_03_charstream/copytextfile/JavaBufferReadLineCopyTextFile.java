package com.example.javaio._03_charstream.copytextfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/8/24
 */
public class JavaBufferReadLineCopyTextFile {
    public static void main(String[] args) throws IOException {
        // 1, 创建字符输入流，用于把文件读入内存
        FileReader fr = new FileReader("file.txt");
        // 2，创建字符输入流的缓冲区对象，关联需要被缓冲的字符输入流
        BufferedReader br = new BufferedReader(fr);
        // 3，创建字符输出流，用于把内存中的字符写入外部文件中
        FileWriter fw = new FileWriter("filecopy.txt");
        // 4，创建字符输出流的缓冲区对象，关联需要被缓冲的字符输出流
        BufferedWriter bw = new BufferedWriter(fw);
        String line;
        // 5，从字符输入流中读取一行，如果已经到达文件末尾，则返回null
        while ((line = br.readLine()) != null) {
            // 6，将读到的字符写入到字符输出流中
            bw.write(line);
            bw.newLine();
            // 7, 刷新该流的缓冲
            bw.flush();
        }
        // 8，关闭流资源
        bw.close();
        br.close();
    }
}
