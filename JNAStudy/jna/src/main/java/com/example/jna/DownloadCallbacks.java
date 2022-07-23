package com.example.jna;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2022/6/9
 */
public class DownloadCallbacks extends Structure {
    public DownloadStartCallback downloadStartCallback;
    public DownloadProgressCallback downloadProgressCallback;
    public DownloadFinishCallback downloadFinishCallback;


    public DownloadCallbacks(DownloadStartCallback downloadStartCallback, DownloadProgressCallback downloadProgressCallback, DownloadFinishCallback downloadFinishCallback) {
        this.downloadStartCallback = downloadStartCallback;
        this.downloadProgressCallback = downloadProgressCallback;
        this.downloadFinishCallback = downloadFinishCallback;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"downloadStartCallback", "downloadProgressCallback", "downloadFinishCallback"});
    }

    public static class ByReference extends DownloadCallbacks implements Structure.ByReference {

        public ByReference(DownloadStartCallback downloadStartCallback, DownloadProgressCallback downloadProgressCallback, DownloadFinishCallback downloadFinishCallback) {
            super(downloadStartCallback, downloadProgressCallback, downloadFinishCallback);
        }
    }
}
