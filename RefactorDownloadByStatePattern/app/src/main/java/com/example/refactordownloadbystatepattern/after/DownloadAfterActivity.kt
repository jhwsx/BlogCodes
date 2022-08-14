package com.example.refactordownloadbystatepattern.after

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.refactordownloadbystatepattern.DownloadViewModel
import com.example.refactordownloadbystatepattern.DownloadViewModelFactory
import com.example.refactordownloadbystatepattern.R
import com.example.refactordownloadbystatepattern.data.DownloadStateEnum
import com.example.refactordownloadbystatepattern.databinding.DownloadActivityBinding

/**
 * 重构后的下载页面
 * @author wangzhichao
 * @since 2022/8/14
 */
class DownloadAfterActivity : AppCompatActivity() {
    private lateinit var binding: DownloadActivityBinding
    private val viewModel by viewModels<DownloadViewModel> { DownloadViewModelFactory() }
    private lateinit var downloadContext: DownloadContext
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DownloadActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        downloadContext = DownloadContext(binding, viewModel)
        viewModel.progress.observe(this) {
            binding.pb.progress = it
        }
        viewModel.downloadState.observe(this) {
            downloadContext.state = when(it!!) {
                DownloadStateEnum.DOWNLOAD_START -> DownloadStartState(downloadContext)
                DownloadStateEnum.DOWNLOADING -> DownloadingState(downloadContext)
                DownloadStateEnum.DOWNLOAD_PAUSE -> DownloadPauseState(downloadContext)
                DownloadStateEnum.DOWNLOAD_SUCCESS -> DownloadSuccessState(downloadContext)
            }
        }
        binding.btnNegative.setOnClickListener {
            downloadContext.state.handleDownloadActionNegativeClick()
            finish()
        }
        binding.btnPositive.setOnClickListener {
            downloadContext.state.handleDownloadActionPositiveClick()
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DownloadAfterActivity::class.java)
            context.startActivity(starter)
        }
    }
}