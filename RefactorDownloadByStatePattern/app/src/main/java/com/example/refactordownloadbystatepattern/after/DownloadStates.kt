package com.example.refactordownloadbystatepattern.after

import android.view.View
import android.widget.Toast
import com.example.refactordownloadbystatepattern.App
import com.example.refactordownloadbystatepattern.DownloadViewModel
import com.example.refactordownloadbystatepattern.R
import com.example.refactordownloadbystatepattern.databinding.DownloadActivityBinding

/**
 *
 * @author wangzhichao
 * @since 2022/8/14
 */
/**
 * 下载上下文类
 *
 * @author wangzhichao
 * @since 2021/8/14
 */
class DownloadContext(val binding: DownloadActivityBinding, val viewModel: DownloadViewModel) {
    var state: BaseDownloadState = DownloadStartState(this)
}

/**
 * 下载状态基类
 *
 * @author wangzhichao
 * @since 2021/8/14
 */
abstract class BaseDownloadState(val downloadContext: DownloadContext) {
    abstract fun handleDownloadActionPositiveClick()
    abstract fun handleDownloadActionNegativeClick()
}

/**
 * 下载初始状态
 *
 * 职责：
 * 1，负责处理只显示正向按钮，文字为下载，负向按钮 GONE 掉
 * 2，负责 GONE 掉下载进度圈
 * 3，处理点击下载的事件
 *
 * @author wangzhichao
 * @since 2021/8/14
 */
class DownloadStartState(downloadContext: DownloadContext): BaseDownloadState(downloadContext) {

    init {
        downloadContext.binding.pb.visibility = View.GONE
        downloadContext.binding.btnNegative.visibility = View.GONE
        downloadContext.binding.btnPositive.setText(R.string.download)
    }

    override fun handleDownloadActionPositiveClick() {
        // 开始下载
        downloadContext.viewModel.startDownload()
    }

    override fun handleDownloadActionNegativeClick() {
        // do nothing
    }
}

/**
 * 下载中状态
 *
 * 职责：
 * 1，负责显示正向按钮为暂停，显示负向按钮为取消
 * 2，显示下载进度圈
 * 3，处理暂停，取消点击事件
 *
 * @author wangzhichao
 * @since 2021/8/14
 */
class DownloadingState(downloadContext: DownloadContext) : BaseDownloadState(downloadContext) {
    init {
        downloadContext.binding.pb.visibility = View.VISIBLE
        downloadContext.binding.btnNegative.visibility = View.VISIBLE
        downloadContext.binding.btnNegative.setText(R.string.download_cancel)
        downloadContext.binding.btnPositive.setText(R.string.download_pause)
    }

    override fun handleDownloadActionPositiveClick() {
        // 暂停下载
        downloadContext.viewModel.pauseDownload()
    }

    override fun handleDownloadActionNegativeClick() {
        // 取消下载
        downloadContext.viewModel.cancelDownload()
    }
}

/**
 * 下载暂停状态
 *
 * 职责：
 * 1，负责显示正向按钮为恢复，显示负向按钮为取消
 * 2，负责 GONE 掉下载进度圈
 * 3，处理恢复，取消点击事件
 *
 * @author wangzhichao
 * @since 2021/8/14
 */
class DownloadPauseState(downloadContext: DownloadContext): BaseDownloadState(downloadContext) {

    init {
        downloadContext.binding.pb.visibility = View.GONE
        downloadContext.binding.btnNegative.visibility = View.VISIBLE
        downloadContext.binding.btnNegative.setText(R.string.download_cancel)
        downloadContext.binding.btnPositive.setText(R.string.download_resume)
    }

    override fun handleDownloadActionPositiveClick() {
        // 恢复下载
        downloadContext.viewModel.resumeDownload()
    }

    override fun handleDownloadActionNegativeClick() {
        // 取消下载
        downloadContext.viewModel.cancelDownload()
    }
}

/**
 * 下载完成状态
 *
 * 职责：
 * 1，负责显示正向按钮为立即安装，不显示负向按钮
 * 2，处理立即安装点击事件
 *
 * @author wangzhichao
 * @since 2021/8/14
 */
class DownloadSuccessState(downloadContext: DownloadContext) : BaseDownloadState(downloadContext) {
    init {
        downloadContext.binding.pb.visibility = View.GONE
        downloadContext.binding.btnNegative.visibility = View.GONE
        downloadContext.binding.btnPositive.setText(R.string.download_success)
    }

    override fun handleDownloadActionPositiveClick() {
        // 立即安装
        Toast.makeText(App.instance, "去安装吧！", Toast.LENGTH_SHORT).show()
    }

    override fun handleDownloadActionNegativeClick() {
        // do nothing
    }
}