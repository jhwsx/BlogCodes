package com.example.refactordownloadbystatepattern.data

/**
 *
 * @author wangzhichao
 * @since 2022/8/14
 */
interface DownloadListener {
    fun onProgress(progress: Int)
    fun onPause()
    fun onFinished()
}