package com.bqt.test.flutter.channel

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import com.bqt.test.flutter.channel.pigeon.Pigeon
import com.bqt.test.flutter.channel.pigeon.TestAsyApiImpl
import com.bqt.test.flutter.channel.pigeon.TestBookApiImpl
import com.bqt.test.flutter.channel.pigeon.TestTaskQueueApiImpl
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger

class FlutterPigeonActivity : FlutterActivity() {
    var mFlutterApi: Pigeon.TestFlutterApi? = null

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val binaryMessenger: BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger

        Pigeon.TestBookApi.setup(binaryMessenger, TestBookApiImpl)
        Pigeon.TestAsyApi.setup(binaryMessenger, TestAsyApiImpl)
        Pigeon.TestTaskQueueApi.setup(binaryMessenger, TestTaskQueueApiImpl)
        mFlutterApi = Pigeon.TestFlutterApi(binaryMessenger)

        callFlutterMethod()
    }

    private fun callFlutterMethod() {
        val handler = Handler(Looper.getMainLooper())
        (0..20)
            .map { it.toLong() * 100 }
            .forEach {
                handler.postDelayed(it) {
                    mFlutterApi?.getYourName(it) { value -> // 必须在主线程中调用
                        println("从 Flutter 获取到的值是：$value ，isMainThread：${isMainThread()}") // true，回调在主线程
                    }
                }
            }
    }
}

fun Handler.postDelayed(delay: Long, runnable: Runnable) = postDelayed(runnable, delay)
fun isMainThread() = Looper.getMainLooper().thread == Thread.currentThread()