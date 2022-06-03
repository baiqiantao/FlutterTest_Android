package com.bqt.test.flutter.multiple

import android.content.Context
import com.bqt.test.x.MyApplication
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

interface EngineBindingsDelegate {
    fun onNext()
}

class EngineBindings(context: Context, val delegate: EngineBindingsDelegate, entrypoint: String, val id: Int) {

    val channel: MethodChannel
    val engine: FlutterEngine

    init {
        // Represents a collection of FlutterEngines who share resources to allow them to be created faster and with less memory
        val engineGroup: FlutterEngineGroup = (context.applicationContext as MyApplication).engineGroup // 全局的 FEG
        // The path within the AssetManager where the app will look for assets
        val pathToBundle: String = FlutterInjector.instance().flutterLoader().findAppBundlePath() // 即 flutterApplicationInfo.flutterAssetsDir
        // This has to be lazy to avoid creation before the FlutterEngineGroup
        val dartEntrypoint = DartExecutor.DartEntrypoint(pathToBundle, entrypoint)
        // Creates a FlutterEngine in this group and run its DartExecutor with the specified DartEntrypoint
        engine = engineGroup.createAndRunEngine(context, dartEntrypoint) // 创建 FlutterEngine 并执行指定的 DartEntrypoint
        channel = MethodChannel(engine.dartExecutor.binaryMessenger, "multiple-flutters")
    }

    fun attach() {
        DataModel.addObserver(::observer)
        channel.invokeMethod("setData", DataModel.data) // 把当前的值传给 Dart 端
        channel.setMethodCallHandler { call, result ->
            when (call.method) {
                "incrementCount" -> result.success(true).also { DataModel.data = DataModel.data + 1 }
                "next" -> result.success(true).also { delegate.onNext() }
                else -> result.notImplemented()
            }
        }
    }

    fun detach() {
        engine.destroy() // Cleans up all components within this FlutterEngine and destroys the associated Dart Isolate
        DataModel.removeObserver(::observer)
        channel.setMethodCallHandler(null)
    }

    private fun observer(data: Int): Unit = channel.invokeMethod("setData", data)
}
