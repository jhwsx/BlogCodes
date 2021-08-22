package com.example.javaio._02_bytestream.copytextfile;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyBufferedInputStream extends FilterInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private byte[] buf;
    // 当前已读位置
    private int pos;
    // 存入缓冲区中的字节数
    private int count;

    public MyBufferedInputStream(InputStream in) {
        this(in, DEFAULT_BUFFER_SIZE);
    }

    public MyBufferedInputStream(InputStream in, int size) {
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
