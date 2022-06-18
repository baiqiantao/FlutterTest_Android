package com.bqt.test.flutter.basic

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterShellArgs
import java.io.File

/**
 * FlutterActivity
 * @tag <p>
 * @author 白乾涛 <p>
 * @date 2022/6/18 16:17 <p>
 */
class TestFlutterActivity : FlutterActivity() {

    private val rootDir: File by lazy { externalCacheDir ?: cacheDir }
    private val entrypoint: String by lazy { intent.getStringExtra("entrypoint") ?: "main" } // 指定使用哪个入口函数

    // 入口函数默认是 main，也可以通过【@pragma('vm:entry-point')】指定为任意的方法
    override fun getDartEntrypointFunctionName(): String = entrypoint.also { println("entrypoint=$it") } // 指定入口函数

    override fun getFlutterShellArgs(): FlutterShellArgs = super.getFlutterShellArgs().apply {
        add("--aot-shared-library-name=" + File(rootDir, "libapp1.so").absolutePath) // 入口可以在任一加载的 so 中
        add("--aot-shared-library-name=" + File(rootDir, "libapp2.so").absolutePath) // 加载顺序决定了查找优先级
        println(toArray().toList())
    }
}