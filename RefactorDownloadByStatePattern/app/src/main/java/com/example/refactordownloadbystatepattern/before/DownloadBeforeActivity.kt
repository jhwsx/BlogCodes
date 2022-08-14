package com.example.refactordownloadbystatepattern.before

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
 * 重构前的下载页面
 * @author wangzhichao
 * @since 2022/8/14
 */
class DownloadBeforeActivity : AppCompatActivity() {
    private lateinit var binding: DownloadActivityBinding
    private val viewModel by viewModels<DownloadViewModel> { DownloadViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DownloadActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.progress.observe(this) {
            binding.pb.progress = it
        }
        viewModel.downloadState.observe(this) {
            when(it!!) {
                DownloadStateEnum.DOWNLOAD_START -> {
                    binding.pb.visibility = View.GONE
                    binding.btnNegative.visibility = View.GONE
                    binding.btnPositive.setText(R.string.download)
                    binding.btnPositive.tag = R.string.download
                }
                DownloadStateEnum.DOWNLOADING -> {
                    binding.pb.visibility = View.VISIBLE
                    binding.btnNegative.visibility = View.VISIBLE
                    binding.btnNegative.setText(R.string.download_cancel)
                    binding.btnPositive.setText(R.string.download_pause)
                    binding.btnNegative.tag = R.string.download_cancel
                    binding.btnPositive.tag = R.string.download_pause
                }
                DownloadStateEnum.DOWNLOAD_PAUSE -> {
                    binding.pb.visibility = View.GONE
                    binding.btnNegative.visibility = View.VISIBLE
                    binding.btnNegative.setText(R.string.download_cancel)
                    binding.btnPositive.setText(R.string.download_resume)
                    binding.btnNegative.tag = R.string.download_cancel
                    binding.btnPositive.tag = R.string.download_resume
                }
                DownloadStateEnum.DOWNLOAD_SUCCESS -> {
                    binding.pb.visibility = View.GONE
                    binding.btnNegative.visibility = View.GONE
                    binding.btnPositive.setText(R.string.download_success)
                    binding.btnPositive.tag = R.string.download_success
                }
            }
        }
        binding.btnNegative.setOnClickListener { view: View ->
            when (view.tag) {
                R.string.download_cancel -> {
                    viewModel.cancelDownload()
                    finish()
                }
            }
        }
        binding.btnPositive.setOnClickListener { view: View ->
            when (view.tag) {
                R.string.download -> {
                    viewModel.startDownload()
                }
                R.string.download_pause -> {
                    viewModel.pauseDownload()
                }
                R.string.download_resume -> {
                    viewModel.resumeDownload()
                }
                R.string.download_success -> {
                    Toast.makeText(this, "去安装吧！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DownloadBeforeActivity::class.java)
            context.startActivity(starter)
        }
    }
}