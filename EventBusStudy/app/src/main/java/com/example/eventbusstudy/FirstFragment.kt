package com.example.eventbusstudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eventbusstudy.databinding.FirstFragmentBinding
import org.greenrobot.eventbus.EventBus
import kotlin.concurrent.thread

/**
 *
 * @author wangzhichao
 * @since 2022/4/6
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FirstFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var value = 0
        binding.btnPost.setOnClickListener {
            Log.d(TAG, "post: value=$value")
            if (value == 2) {
                EventBus1.getDefault().post(Event(value))
                value = 0
            } else {
                EventBus2.getDefault().post(Event(value))
                value = 2
            }
        }
        binding.btnPostFromMainThread.setOnClickListener {
            EventBus.getDefault().post(MessageEvent(Thread.currentThread().name))
        }
        binding.btnPostFromWorkThread.setOnClickListener {
            thread(name = "WorkThread") {
                EventBus.getDefault().post(MessageEvent(Thread.currentThread().name))
            }
        }
    }

    companion object {
        private const val TAG = "FirstFragment"
    }
}