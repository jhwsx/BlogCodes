package com.example.eventbusstudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eventbusstudy.databinding.ThirdFragmentBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 *
 * @author wangzhichao
 * @since 2022/4/6
 */
class ThirdFragment : Fragment() {
    private lateinit var binding: ThirdFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ThirdFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        EventBus2.getDefault().register(this)
//        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus2.getDefault().unregister(this)
//        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        binding.tv.text = "当前线程：${Thread.currentThread().name}," +
                " 消息来自线程：${event.fromThread}"
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Event) {
        Log.d(TAG, "onEvent: value=${event.value}")
    }

    companion object {
        private const val TAG = "ThirdFragment"
    }
}