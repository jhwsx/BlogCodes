package com.example.handlerstudy

import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handlerstudy.databinding.ListenMainLooperMessageQueueActivityBinding

/**
 * 监听 Main Looper 的消息队列的内容变化。
 *
 * @author wangzhichao
 * @date 20-8-27
 */
class ListenMainLooperMessageQueueActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ListenMainLooperMessageQueueActivityBinding
    private lateinit var handlerThread: HandlerThread
    // 用于产生消息循环的 Handler
    private lateinit var handler: Handler
    // 用于获取主 Looper 的消息队列的 Handler
    private lateinit var mainHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListenMainLooperMessageQueueActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createHandler()
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.btn.setOnClickListener {
            Log.d(TAG, "onCreate: click the button")
        }
    }

    private inner class MyHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == MESSAGE_CODE) {
                printMessageQueue()
                handler.sendEmptyMessageDelayed(1, 10L)
            }
        }
    }

    private fun createHandler() {
        handlerThread = HandlerThread(TAG)
        handlerThread.start()
        handler = MyHandler(handlerThread.looper)
        mainHandler = Handler(Looper.getMainLooper())
        handler.sendEmptyMessage(MESSAGE_CODE)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        handlerThread.quit()
    }

    override fun onClick(v: View) {
    }
    // 在 Xiaomi Redmi Note 8 Pro Android 9, API 28 上，提示：Accessing hidden field Landroid/os/MessageQueue;->mMessages:Landroid/os/Message; (light greylist, reflection)
    // 在 HUAWEI HRY-AL00a Android 10, API 29 上，提示：Accessing hidden field Landroid/os/Message;->next:Landroid/os/Message; (greylist, reflection, allowed)
    private fun printMessageQueue() {
        try {
            val messageQueue = mainHandler.looper.queue
            val mMessagesField = MessageQueue::class.java.getDeclaredField("mMessages")
            mMessagesField.isAccessible = true
            val mMessages = mMessagesField.get(messageQueue) as? Message
            var message: Message? = mMessages
            if (mMessages == null) {
    //            Log.d(TAG, "printMessageQueue: no message")
                return
            }
            Log.d(TAG, "printMessageQueue: ---------------------start")
            while (message != null) {
                Log.d(TAG, "printMessageQueue: $message")
                val nextField = Message::class.java.getDeclaredField("next")
                nextField.isAccessible = true
                message = nextField.get(message) as? Message
            }
            Log.d(TAG, "printMessageQueue: ---------------------end")
        } catch (e: Exception) {
            Log.e(TAG, "printMessageQueue: ", e)
        }
    }

    companion object {
        private const val TAG = "ListenMessageQueue"
        private const val MESSAGE_CODE = 1

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ListenMainLooperMessageQueueActivity::class.java)
            context.startActivity(starter)
        }
    }
}