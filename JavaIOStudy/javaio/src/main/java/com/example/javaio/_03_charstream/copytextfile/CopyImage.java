package com.example.javaio._03_charstream.copytextfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author wangzhichao
 * @since 2021/8/26
 */
public class CopyImage {
    public static void main(String[] args) throws IOException {
//        copyImageByCharStream();
//        copyImageByCharStream2("iso8859-1");
        copyImageByCharStream2("gbk");
//        copyImageByByteStream();
    }

    private static void copyImageByByteStream() throws IOException {
        // 1, 创建字节输入流，用于把文件读入内存
        FileInputStream fis = new FileInputStream("point.webp");
        // 2, 创建字节输出流，用于把内存的字节写入外部文件中
        FileOutputStream fos = new FileOutputStream("point_copy.webp");
        int b;
        // 3, 从字节输入流中读取一个字节，如果已经到达文件末尾，则返回 -1
        while((b = fis.read()) != -1) {
            // 4，将读到的字节写入到字节输出流中
            fos.write(b);
        }
        // 5, 关闭流资源
        fos.close();
        fis.close();

        File src = new File("point.webp");
        File dst = new File("point_copy.webp");
        System.out.println("point.webp 图片的大小：" + src.length());
        System.out.println("point_copy.webp 图片的大小：" + dst.length());
    }

    private static void copyImageByCharStream() throws IOException {
        // 1, 创建字符输入流，用于把文件读入内存
        FileReader fr = new FileReader("point.webp");
        System.out.println("fr.getEncoding() = " + fr.getEncoding());
        // 2，创建字符输出流，用于把内存中的字符写入外部文件中
        FileWriter fw = new FileWriter("point_copy.webp");
        System.out.println("fw.getEncoding() = " + fw.getEncoding());
        int ch;
        // 3，从字符输入流中读取一个字符，如果已经到达文件末尾，则返回-1
        while ((ch = fr.read()) != -1) {
            System.out.println(((char) ch));
            // 4，将读到的字符写入到字符输出流中
            fw.write(ch);
            // 5, 刷新该流的缓冲
            fw.flush();
        }
        // 6，关闭流资源
        fw.close();
        fr.close();
        File src = new File("point.webp");
        File dst = new File("point_copy.webp");
        System.out.println("point.webp 图片的大小：" + src.length());
        System.out.println("point_copy.webp 图片的大小：" + dst.length());
    }
    private static void copyImageByCharStream2(String charsetName) throws IOException {
        // 1, 创建字符输入流，用于把文件读入内存
        InputStreamReader isr = new InputStreamReader(new FileInputStream("point.webp"), charsetName);
        System.out.println("isr.getEncoding() = " + isr.getEncoding());
        // 2，创建字符输出流，用于把内存中的字符写入外部文件中
        OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream("point_copy.webp"), charsetName);
        System.out.println("osr.getEncoding() = " + osr.getEncoding());
        int ch;
        // 3，从字符输入流中读取一个字符，如果已经到达文件末尾，则返回-1
        while ((ch = isr.read()) != -1) {
            // 4，将读到的字符写入到字符输出流中
            System.out.println(((char) ch) + " " + Integer.toHexString(ch) + " " + Integer.toBinaryString(ch));
            osr.write(ch);
            // 5, 刷新该流的缓冲
            osr.flush();
        }
        // 6，关闭流资源
        isr.close();
        osr.close();
        File src = new File("point.webp");
        File dst = new File("point_copy.webp");
        System.out.println("point.webp 图片的大小：" + src.length());
        System.out.println("point_copy.webp 图片的大小：" + dst.length());
    }
}
