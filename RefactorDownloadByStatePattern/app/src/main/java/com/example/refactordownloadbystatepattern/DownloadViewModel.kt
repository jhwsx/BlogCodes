package com.example.refactordownloadbystatepattern

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.refactordownloadbystatepattern.data.DownloadListener
import com.example.refactordownloadbystatepattern.data.DownloadStateEnum
import com.example.refactordownloadbystatepattern.data.Downloader

/**
 *
 * @author wangzhichao
 * @since 2022/8/14
 */
class DownloadViewModel: ViewModel() {
    private val _progress = MutableLiveData<Int>().apply { value = 0 }
    val progress get() = _progress
    private val _downloadState = MutableLiveData<DownloadStateEnum>().apply { value = DownloadStateEnum.DOWNLOAD_START }
    val downloadState get() = _downloadState
    init {
        Downloader.downloadListener = object : DownloadListener {
            override fun onProgress(progress: Int) {
                _progress.postValue(progress)
            }

            override fun onPause() {
                _downloadState.postValue(DownloadStateEnum.DOWNLOAD_PAUSE)
            }

            override fun onFinished() {
                _downloadState.postValue(DownloadStateEnum.DOWNLOAD_SUCCESS)
            }
        }
    }


    fun startDownload() {
        _downloadState.value = DownloadStateEnum.DOWNLOADING
        Downloader.startDownload()
    }

    fun pauseDownload() {
        Downloader.pauseDownload()
    }

    fun resumeDownload() {
        _downloadState.value = DownloadStateEnum.DOWNLOADING
        Downloader.resumeDownload()
    }

    fun cancelDownload() {
        Downloader.cancelDownload()
    }
}
@Suppress("UNCHECKED_CAST")
class DownloadViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = DownloadViewModel() as T
}