package com.example.handlerstudy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.util.TimeUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.handlerstudy.databinding.SyncBarrierTestActivityBinding

/**
 * 测试同步屏障
 *
 * @author wangzhichao
 * @date 20-8-27
 */
class SyncBarrierTestActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: SyncBarrierTestActivityBinding
    private lateinit var handler: Handler
    private lateinit var handlerThread: HandlerThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SyncBarrierTestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createHandler()
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.btnSendSyncMessage.setOnClickListener(this)
        binding.btnSendAsyncMessage.setOnClickListener(this)
        binding.btnSendSyncBarrierMessage.setOnClickListener(this)
        binding.btnRemoveSyncBarrierMessage.setOnClickListener(this)
    }

    private inner class MyHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                val content = msg.obj as String
                Log.d(TAG, "handleMessage: 收到 $content")
                printMessageQueue()
            }
        }
    }


    private fun createHandler() {
        handlerThread = HandlerThread(TAG)
        handlerThread.start()
        handler = MyHandler(handlerThread.looper)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        handlerThread.quit()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_send_sync_message -> {
                sendSyncMessage()
            }
            R.id.btn_send_async_message -> {
                sendAsyncMessage()
            }
            R.id.btn_send_sync_barrier_message -> {
                sendSyncBarrierMessage()
            }
            R.id.btn_remove_sync_barrier_message -> {
                removeSyncBarrierMessage()
            }
        }
    }

    private var syncMessageCounter = 0
    private var asyncMessageCounter = 0
    private val tokenList = arrayListOf<Int>()
    private fun sendSyncMessage() {
        syncMessageCounter++
        val message = Message.obtain()
        message.what = MESSAGE_CODE
        val content = "同步消息 $syncMessageCounter"
        message.obj = content
        handler.sendMessageDelayed(message, 1000L * (syncMessageCounter + asyncMessageCounter))
        Log.d(TAG, "sendSyncMessage: 发送 $content")
        printMessageQueue()
    }

    private fun sendAsyncMessage() {
        asyncMessageCounter++
        val message = Message.obtain()
        message.what = MESSAGE_CODE
        val content = "异步消息 $asyncMessageCounter"
        message.obj = content
        message.isAsynchronous = true
        handler.sendMessageDelayed(message, 1000L * (syncMessageCounter + asyncMessageCounter))
        Log.d(TAG, "sendAsyncMessage: 发送 $content")
        printMessageQueue()
    }
    // 在 Xiaomi Redmi Note 8 Pro Android 9, API 28 上，报错：Accessing hidden method Landroid/os/MessageQueue;->postSyncBarrier(J)I (dark greylist, reflection)
    // 在 HUAWEI HRY-AL00a Android 10, API 29 上， 报错：Accessing hidden method Landroid/os/MessageQueue;->postSyncBarrier(J)I (greylist-max-o, reflection, denied)
    @SuppressLint("SoonBlockedPrivateApi")
    private fun sendSyncBarrierMessage() {
        try {
            val messageQueue = handler.looper.queue
            val postSyncBarrierMethod =
                MessageQueue::class.java.getDeclaredMethod("postSyncBarrier", Long::class.java)
            postSyncBarrierMethod.isAccessible = true
            val uptimeMillis = SystemClock.uptimeMillis()
            Log.d(TAG, "sendSyncBarrierMessage: uptimeMillis=$uptimeMillis")
            val tokenObj = postSyncBarrierMethod.invoke(messageQueue, uptimeMillis)
            if (tokenObj is Int) {
                val token = tokenObj as Int
                tokenList.add(token)
                Log.d(TAG, "sendSyncBarrierMessage: token=$token")
            }
            printMessageQueue()
        } catch (e: Exception) {
            Log.e(TAG, "sendSyncBarrierMessage: ", e)
        }
    }
    // 在 Xiaomi Redmi Note 8 Pro Android 9, API 28 上，提示：Accessing hidden method Landroid/os/MessageQueue;->removeSyncBarrier(I)V (light greylist, reflection)
    // 在 HUAWEI HRY-AL00a Android 10, API 29 上，提示：Accessing hidden method Landroid/os/MessageQueue;->removeSyncBarrier(I)V (greylist, reflection, allowed)
    private fun removeSyncBarrierMessage() {
        try {
            val messageQueue = handler.looper.queue
            val removeSyncBarrierMethod =
                MessageQueue::class.java.getDeclaredMethod("removeSyncBarrier", Int::class.java)
            removeSyncBarrierMethod.isAccessible = true
            val token = tokenList.removeLastOrNull()
            Log.d(TAG, "removeSyncBarrierMessage: token=$token")
            if (token != null) {
                removeSyncBarrierMethod.invoke(messageQueue, token)
                printMessageQueue()
            }
        } catch (e: Exception) {
            Log.e(TAG, "removeSyncBarrierMessage: ", e)
        }
    }

    private fun printMessageQueue() {
        try {
            Log.d(TAG, "printMessageQueue: ---------------------start")
            val messageQueue = handler.looper.queue
            val mMessagesField = MessageQueue::class.java.getDeclaredField("mMessages")
            mMessagesField.isAccessible = true
            val mMessages = mMessagesField.get(messageQueue) as? Message
            var message: Message? = mMessages
            if (mMessages == null) {
                Log.d(TAG, "printMessageQueue: no message")
                binding.tv.post { binding.tv.text = "no message" }
                clearCounter()
                return
            }
            val sb = StringBuilder()
            while (message != null) {
                val printMessage = printMessage(message)
                sb.append(printMessage).append("\n")
                Log.d(TAG, "printMessageQueue: $printMessage")
                val nextField = Message::class.java.getDeclaredField("next")
                nextField.isAccessible = true
                message = nextField.get(message) as? Message
            }
            binding.tv.post { binding.tv.text = sb.toString() }

            Log.d(TAG, "printMessageQueue: ---------------------end")
        } catch (e: Exception) {
            Log.e(TAG, "printMessageQueue: ", e)
        }
    }

    private fun printMessage(message: Message): String {
        val b = StringBuilder()
        b.append("{ ")

        if (message.target != null) {
            if (message.obj != null) {
                b.append(" obj=")
                b.append(message.obj)
            }
            b.append(" target=")
            b.append(message.target.javaClass.simpleName)
        } else {
            b.append(" 同步屏障消息 ${message.arg1}")
        }

        b.append(" }")
        return b.toString()
    }

    private fun clearCounter() {
        syncMessageCounter = 0
        asyncMessageCounter = 0
    }

    companion object {
        private const val TAG = "SyncBarrierTestActivity"
        private const val MESSAGE_CODE = 1

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SyncBarrierTestActivity::class.java)
            context.startActivity(starter)
        }
    }
}
/**
 * 总结：
 * 1, 没有发送同步屏障消息之前， 同步消息和异步消息都可以被处理，一样地对待；
 * 2, 在发送同步屏障消息之后， 同步消息不被处理， 异步消息仍然可以被处理；
 * 3, 在移除同步屏障消息之后， 同步消息和异步消息可以被处理，一样地对待。
 */