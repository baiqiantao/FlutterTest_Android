package com.bqt.test.flutter.channel.pigeon

import com.bqt.test.flutter.channel.isMainThread
import kotlin.random.Random

object TestAsyApiImpl : Pigeon.TestAsyApi {
    override fun calculate(key: Long, result: Pigeon.Result<String>?) {
        println("calculate $key isMainThread: ${isMainThread()}") // 注意，这里也是回调在主线程
        if (Random.nextBoolean()) {
            Thread {
                Thread.sleep(1000 * 3) // 可以在子线程中回调
                result?.success("白乾涛1") // 异步调用 native 时，native 不是直接返回，而是以回调的形式响应
                result?.success("白乾涛2") // 异步调用 native 时，native 不是直接返回，而是以回调的形式响应
                result?.success("白乾涛3") // 异步调用 native 时，native 不是直接返回，而是以回调的形式响应
                result?.success("白乾涛4") // 异步调用 native 时，native 不是直接返回，而是以回调的形式响应
            }.start()
        } else {
            result?.error(Throwable("异常了")) // Flutter 端需要捕获异常
        }
    }
}