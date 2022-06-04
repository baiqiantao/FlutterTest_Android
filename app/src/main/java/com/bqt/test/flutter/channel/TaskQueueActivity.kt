package com.bqt.test.flutter.channel

import com.bqt.test.x.BuildConfig
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodCodec
import io.flutter.plugin.common.StandardMethodCodec

class TaskQueueActivity : FlutterActivity() {
    companion object {
        private const val CHANNEL_NAME = "com.bqt.test/base_channel"
        private const val METHOD_NAME = "getVersionCode"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val binaryMessenger: BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger
        val taskQueue: BinaryMessenger.TaskQueue = binaryMessenger.makeBackgroundTaskQueue()
        val methodCodec: MethodCodec = StandardMethodCodec.INSTANCE
        val methodChannel = MethodChannel(binaryMessenger, CHANNEL_NAME, methodCodec, taskQueue)
        methodChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                METHOD_NAME -> onCallGetVersionCode(result)
                else -> result.notImplemented()
            }
        }
    }

    private fun onCallGetVersionCode(result: MethodChannel.Result) {
        println("onCallGetVersionCode, isMainThread：${isMainThread()}") // false
        result.success(BuildConfig.VERSION_CODE)
    }
}