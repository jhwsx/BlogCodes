package com.example.javaio._03_charstream.copytextfile;

import java.io.IOException;
import java.io.Reader;

/**
 * @author wangzhichao
 * @since 2021/8/24
 */
public class MyBufferedReader2 extends Reader {
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
    private static int defaultExpectedLineLength = 80;

    public MyBufferedReader2(Reader in, int sz) {
        super(in);
        this.in = in;
        cb = new char[sz];
    }

    public MyBufferedReader2(Reader in) {
        this(in, defaultCharBufferSize);
    }

    @Override
    public int read() throws IOException {
        synchronized (lock) {
            ensureOpen();
            for (; ; ) {
                if (nextChar >= nChars) {
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
    public int read(char[] cbuf, int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            int n = read1(cbuf, off, len);
            if (n <= 0) return n;
            while ((n < len) && in.ready()) {
                int n1 = read1(cbuf, off + n, len - n);
                if (n1 <= 0) break;
                n += n1;
            }
            return n;
        }
    }

    public String readLine() throws IOException {
        StringBuilder s = null;
        int startChar;
        synchronized (lock) {
            ensureOpen();
            boolean omitLF = false;
            bufferLoop:
            for (; ; ) {
                if (nextChar >= nChars) {
                    fill();
                }
                if (nextChar >= nChars) {
                    if (s != null && s.length() > 0) {
                        return s.toString();
                    } else {
                        return null;
                    }
                }
                boolean eol = false;
                char c = 0;
                int i;
                // 查询字符数组缓冲区可读部分是否包含行结束符
                charLoop:
                for (i = nextChar; i < nChars; i++) {
                    c = cb[i];
                    if ((c == '\n') || (c == '\r')) {
                        eol = true;
                        break charLoop;
                    }
                }

                startChar = nextChar;
                nextChar = i;

                if (eol) {
                    String str;
                    if (s == null) {
                        str = new String(cb, startChar, i - startChar);
                    } else {
                        s.append(cb, startChar, i - startChar);
                        str = s.toString();
                    }
                    nextChar++;
                    return str;
                }

                if (s == null) {
                    s = new StringBuilder(defaultExpectedLineLength);
                }
                s.append(cb, startChar, i - startChar);
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

    private int read1(char[] cbuf, int off, int len) throws IOException {
        // 判断是不是缓冲字符数组里面没有可读的字符了
        if (nextChar >= nChars) {
            // 请求的长度至少是缓冲区的大小
            if (len >= cb.length) {
                return in.read(cbuf, off, len);
            }
            fill();
        }
        if (nextChar >= nChars) return -1;
        // 读取到的字符数
        int n = Math.min(len, nChars - nextChar);
        // 把读取到的字符从缓冲区字符数组拷贝到目标的 cbuf 字符数组里面
        System.arraycopy(cb, nextChar, cbuf, off, n);
        nextChar += n;
        return n;
    }

    @Override
    public boolean ready() throws IOException {
        return (nextChar < nChars) || in.ready();
    }
}
