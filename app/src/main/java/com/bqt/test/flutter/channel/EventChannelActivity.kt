package com.bqt.test.flutter.channel

import android.os.CountDownTimer
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

class EventChannelActivity : FlutterActivity() {

    companion object {
        const val METHOD_CHANNEL_PATH = "com.bqt.test/method_channel"
        const val EVENT_CHANNEL_PATH = "com.bqt.test/enent_channel"
        const val mMillisInFuture: Long = 20000 // The number of millis in the future from the call to start() until the countdown is done and onFinish() is called
        const val countDownInterval: Long = 1000 // The interval along the way to receive onTick(long) callbacks
    }

    private var mEventSink: EventChannel.EventSink? = null
    private var mTimer: CountDownTimer = object : CountDownTimer(mMillisInFuture, countDownInterval) {
        override fun onTick(millisUntilFinished: Long) {
            println("onTick，millisUntilFinished: $millisUntilFinished, isMainThread：${isMainThread()}") // true
            mEventSink?.success("剩余时间 $millisUntilFinished")  // 发送消息通知
            //mEventSink?.endOfStream() // 通知结束，此时 EventSink 的 onCancel 会被调用
        }

        override fun onFinish() {
            println("onFinish，isMainThread：${isMainThread()}") // true
            mEventSink?.error("error_code", "error_message", "error_details") // 发送错误通知
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val binaryMessenger: BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger
        val mMethodChannel = MethodChannel(binaryMessenger, METHOD_CHANNEL_PATH)
        mMethodChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                "startTimer" -> mTimer.start().also { result.success("start success") }
                "stopTimer" -> mTimer.cancel().also { result.success("stop success") }
                else -> result.notImplemented()
            }
        }
        val mEventChannel = EventChannel(binaryMessenger, EVENT_CHANNEL_PATH)
        mEventChannel.setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                println("onListen(建立连接)，arguments: $arguments, isMainThread：${isMainThread()}") // true
                mEventSink = events
            }

            override fun onCancel(arguments: Any?) {
                println("onCancel(断开连接)，arguments: $arguments, isMainThread：${isMainThread()}") // true
                mEventSink = null
            }
        })
    }
}