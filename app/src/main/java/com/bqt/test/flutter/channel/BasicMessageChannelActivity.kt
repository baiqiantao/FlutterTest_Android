package com.bqt.test.flutter.channel

import android.os.Handler
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StringCodec

class BasicMessageChannelActivity : FlutterActivity() {

    private var mBasicMessageChannel: BasicMessageChannel<String>? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val binaryMessenger: BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger
        val messageCodec: MessageCodec<String> = StringCodec.INSTANCE // 指定编解码器
        mBasicMessageChannel = BasicMessageChannel(binaryMessenger, "com.bqt.test/basic_channel", messageCodec)
        mBasicMessageChannel?.resizeChannelBuffer(5)
        mBasicMessageChannel?.setMessageHandler { message, reply ->
            println("onMessage, message: $message，isMainThread：${isMainThread()}") // true
            reply.reply("已收到【$message】") // 只能 reply 一次
        }

        Handler(mainLooper).apply {
            postDelayed(2000) { sendMessage() } // 可以多次调用 send
            postDelayed(5000) { sendMessage() }
        }
    }

    private fun sendMessage() = mBasicMessageChannel?.send("当前时间 ${System.currentTimeMillis()}") { reply ->
        println("reply: $reply, isMainThread：${isMainThread()}") // true
    }
}