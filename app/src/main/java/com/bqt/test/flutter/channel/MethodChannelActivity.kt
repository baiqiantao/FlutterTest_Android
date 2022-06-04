package com.bqt.test.flutter.channel

import android.content.Context
import android.os.BatteryManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class MethodChannelActivity : FlutterActivity() {
    companion object {
        private const val CHANNEL_NAME = "com.bqt.test/base_channel"
        private const val METHOD_NAME = "getBatteryLevel"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val binaryMessenger: BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger
        MethodChannel(binaryMessenger, CHANNEL_NAME).setMethodCallHandler { call, result ->
            when (call.method) {
                METHOD_NAME -> onCallGetBatteryLevel(result)
                else -> result.notImplemented()
            }
        }
    }

    private fun onCallGetBatteryLevel(result: MethodChannel.Result) {
        println("onCallGetBatteryLevel, isMainThread：${isMainThread()}") // true
        Thread {
            val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            val batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            if (batteryLevel != -1) {
                result.success(batteryLevel) // 可在子线程响应请求
                println("result callback, isMainThread：${isMainThread()}") // false
            } else {
                result.error("UNAVAILABLE", "Battery level not available.", null)
            }
        }.start()
    }
}