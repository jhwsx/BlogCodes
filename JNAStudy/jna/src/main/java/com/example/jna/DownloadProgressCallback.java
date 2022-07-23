package com.example.jna;

import com.sun.jna.Callback;

/**
 * @author wangzhichao
 * @since 2022/3/11
 */
public interface DownloadProgressCallback extends Callback {
    void onDownloadProgress(int progress);
}
