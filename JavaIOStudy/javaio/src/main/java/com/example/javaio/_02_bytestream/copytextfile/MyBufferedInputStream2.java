package com.example.javaio._02_bytestream.copytextfile;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyBufferedInputStream2 extends FilterInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private byte[] buf;
    // 当前已读位置
    private int pos;
    // 存入缓冲区中的字节数
    private int count;

    public MyBufferedInputStream2(InputStream in) {
        this(in, DEFAULT_BUFFER_SIZE);
    }

    public MyBufferedInputStream2(InputStream in, int size) {
        super(in);
        buf = new byte[size];
    }

    public int read() throws IOException {
        if (pos >= count) {
            fill();
            if (pos >= count) {
                return -1;
            }
        }
        return getBufIfOpen()[pos++] & 0xff;
    }
    // 从此字节输入流中给定偏移量处开始将各字节读取到指定的 byte 数组中。
    // b - 目标缓冲区。
    // off - 开始存储字节处的偏移量。
    // len - 要读取的最大字节数。
    public int read(byte[] b, int off, int len) throws IOException {
        getBufIfOpen();
        // 已经读取的总字节数
        int n = 0;
        for (; ; ) {
            int nread = read1(b, off + n, len - n);
            if (nread <= 0) {
                return (n == 0) ? nread : n;
            }
            n += nread;
            if (n >= len) {
                return n;
            }
        }
    }

    private int read1(byte[] b, int off, int len) throws IOException {
        // 缓冲区中还可以读取的字节数
        int avail = count - pos;
        if (avail <= 0) {
            // 如果读取的长度大于缓冲区的长度，直接从原始输入流中读取
            if (len >= getBufIfOpen().length) {
                return getInIfOpen().read(b, off, len);
            }
            fill();
            avail = count - pos;
            if (avail <= 0) return -1;
        }
        int cnt = Math.min(avail, len);
        // 把 buf 中从 pos 位置开始，赋值 cnt 长度的数据到 b 中的 off 位置。
        System.arraycopy(getBufIfOpen(), pos, b, off, cnt);
        // 更新 pos 的值
        pos += cnt;
        return cnt;
    }

    private void fill() throws IOException {
        byte[] buffer = getBufIfOpen();
        pos = 0;
        count = 0;
        int n = getInIfOpen().read(buffer, pos, buffer.length);
        if (n > 0) {
            count = n + pos;
        }
    }

    private byte[] getBufIfOpen() throws IOException {
        byte[] buffer = buf;
        if (buffer == null)
            throw new IOException("Stream closed");
        return buffer;
    }

    private InputStream getInIfOpen() throws IOException {
        InputStream input = in;
        if (input == null)
            throw new IOException("Stream closed");
        return input;
    }
}
