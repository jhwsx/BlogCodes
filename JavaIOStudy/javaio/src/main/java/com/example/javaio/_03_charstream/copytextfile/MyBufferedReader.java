package com.example.javaio._03_charstream.copytextfile;

import java.io.IOException;
import java.io.Reader;

/**
 * @author wangzhichao
 * @since 2021/8/24
 */
public class MyBufferedReader extends Reader {
    private Reader in;

    private char[] cb;
    /**
     * 缓冲区读取到字符的位置
     */
    private int nextChar;
    /**
     * 读入缓冲区中的字符数
     */
    private int nChars;
    private static int defaultCharBufferSize = 8192;

    public MyBufferedReader(Reader in, int sz) {
        super(in);
        this.in = in;
        cb = new char[sz];
    }

    public MyBufferedReader(Reader in) {
        this(in, defaultCharBufferSize);
    }

    @Override
    public int read() throws IOException {
        synchronized (lock) {
            ensureOpen();
            for (; ; ) {
                if (nextChar >= nChars) {
                    // 填充字符数组缓冲区
                    fill();
                    if (nextChar >= nChars) {
                        return -1;
                    }
                }
                return cb[nextChar++];
            }
        }
    }

    @Override
    public void close() throws IOException {
        synchronized (lock) {
            if (in == null)
                return;
            try {
                in.close();
            } finally {
                in = null;
                cb = null;
            }
        }
    }

    private void fill() throws IOException {
        int n;
        do {
            // 这里是调用被包装的字符流的读取方法
            n = in.read(cb, 0, cb.length);
        } while (n == 0);
        if (n > 0) {
            nextChar = 0;
            nChars = n;
        }
    }

    private void ensureOpen() throws IOException {
        if (in == null)
            throw new IOException("Stream closed");
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        // 注意：这里没有实现这个方法
        return -1;
    }
}
