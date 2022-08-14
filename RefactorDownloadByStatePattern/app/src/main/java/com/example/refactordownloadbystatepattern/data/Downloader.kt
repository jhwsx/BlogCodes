package com.example.refactordownloadbystatepattern.data

import kotlin.concurrent.thread

/**
 * 下载器
 * @author wangzhichao
 * @since 2022/8/14
 */
object Downloader {

    var downloadListener: DownloadListener? = null
    @Volatile
    private var pause: Boolean = false
    @Volatile
    private var cancel: Boolean = false
    private var pauseProgress: Int = 0
    fun startDownload(startProgress: Int = 0) {
        thread {
            for (i in startProgress .. 100) {
                if (pause) {
                    pauseProgress = i
                    return@thread
                }
                if (cancel) {
                    cancel = false
                    return@thread
                }
                Thread.sleep(200)
                downloadListener?.onProgress(i)
            }
            downloadListener?.onFinished()
        }
    }

    fun pauseDownload() {
        pause = true
        downloadListener?.onPause()
    }

    fun resumeDownload() {
        pause = false
        startDownload(pauseProgress)
    }

    fun cancelDownload() {
        cancel = true
    }
}