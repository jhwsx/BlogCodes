package com.mqtt.mqttstudy.gate

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class GateMqttService : Service() {
    private var mqttConnectOptions: MqttConnectOptions? = null
    private val mqttCallback = object : MqttCallback {
        override fun connectionLost(cause: Throwable?) {
            Log.d(TAG, "connectionLost: 连接断开，$cause")
            doClientConnection()
        }

        override fun messageArrived(topic: String?, message: MqttMessage?) {
            Log.d(TAG, "messageArrived: topic=$topic, message=${String(message?.payload ?: byteArrayOf())}")
            Toast.makeText(
                applicationContext,
                "messageArrived: topic=$topic, message=${String(message?.payload ?: byteArrayOf())}",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun deliveryComplete(token: IMqttDeliveryToken?) {
            Log.d(TAG, "deliveryComplete: ")
        }

    }


    /**
     * 用于监听 MQTT 是否连接成功
     */
    private val iMqttActionListener = object : IMqttActionListener {
        override fun onSuccess(asyncActionToken: IMqttToken?) {
            Log.i(TAG, "onSuccess: 连接成功")
            try {
                // 订阅主题, 大屏幕更新后，会发出这个主题
                mqttAndroidClient?.subscribe(RESPONSE_TOPIC, 2)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
            Log.i(TAG, "onFailure: 连接失败 $exception")
            doClientConnection()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        init()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        try {
            mqttAndroidClient?.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

    private fun init() {
        mqttAndroidClient = MqttAndroidClient(this, HOST, CLIENTID).apply {
            // 设置监听订阅消息的回调
            setCallback(mqttCallback)
        }
        mqttConnectOptions = MqttConnectOptions().apply {
            // 设置是否清除缓存
            isCleanSession = true
            // 设置超时时间，单位：秒
            connectionTimeout = 10
            // 设置心跳包发送时间间隔，单位：秒
            keepAliveInterval = 20
            // 设置用户名
            userName = USERNAME
            // 设置密码
            password = PASSWORD.toCharArray()
        }

//        var doConnect = true
//        val message = "{\"terminal_uid\":\"$CLIENTID\"}"
//        val topic = PUBLISH_TOPIC
//        val qos = 2
//        val retained = false
//
//        if (message.isNotEmpty() || topic.isNotEmpty()) {
//            try {
//                mqttConnectOptions?.setWill(topic, message.toByteArray(), qos, retained)
//            } catch (e: Exception) {
//                Log.i(TAG, "init: $e")
//                doConnect = false
//                iMqttActionListener.onFailure(null, e)
//            }
//        }
//        if (doConnect) {
            doClientConnection()
//        }
    }

    /**
     * 连接 MQTT 服务器
     */
    private fun doClientConnection() {
        if (mqttAndroidClient != null && !mqttAndroidClient!!.isConnected && isConnectNormal()) {
            try {
                mqttAndroidClient!!.connect(mqttConnectOptions, null, iMqttActionListener)
            } catch (e: Exception) {
                Log.e(TAG, "doClientConnection: $e")
            }
        }
    }

    private fun isConnectNormal(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable) {
            val typeName = activeNetworkInfo.typeName
            Log.i(TAG, "当前网络名称：$typeName")
            return true
        } else {
            Log.i(TAG, "没有可用网络")
            // 没有可用的网络时，延迟 3 秒再尝试重连
            Handler().postDelayed({
                doClientConnection()
            }, 3000L)
            return false
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        private const val TAG = "MyMqttService"

        /**
         * 服务器地址（协议 + 地址 + 端口号）
         */
        private const val HOST = "tcp://172.16.50.97:61613"

        /**
         * 用户名
         */
        private const val USERNAME = "admin"

        /**
         * 密码
         */
        private const val PASSWORD = "password"

        /**
         * 发布主题
         */
        private const val PUBLISH_TOPIC = "tourist_enter"

        /**
         * 响应主题
         */
        private const val RESPONSE_TOPIC = "message_arrived"

        private val CLIENTID = "gate2"

        fun startService(context: Context) {
            context.startService(Intent(context, GateMqttService::class.java))
        }
        private var mqttAndroidClient: MqttAndroidClient? = null

        fun publish(msg: String) {
            val topic = PUBLISH_TOPIC
            val qos = 2
            val retained = false
            try {
                // 发布主题 PUBLISH_TOPIC = "tourist_enter"
                mqttAndroidClient?.publish(topic, msg.toByteArray(), qos, retained)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}