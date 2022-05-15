package com.bqt.test.x

import android.content.Context
import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import java.io.File

class QtFlutterEngine private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: FlutterEngine? = null
        private const val ENGINE_ID: String = "my_engine_id"

        fun getEngineId(context: Context, initialRoute: String? = null) = ENGINE_ID.also { init(context, initialRoute) }

        fun init(context: Context, initialRoute: String? = null): FlutterEngine {
            val isInvalid: Boolean = File(".soFileDir", ".soFileName").exists()
            if (isInvalid) {
                Log.i("bqt", "move .so file")
                INSTANCE?.destroy()  // Cleans up all components within this and destroys the associated Dart Isolate
                INSTANCE = null // This FlutterEngine instance should be discarded after invoking this method
            }
            return INSTANCE ?: synchronized(this) { // 第一次判空
                // 如果 INSTANCE 不为空，则返回 INSTANCE，否则返回 FlutterEngine(context)，并执行指定的逻辑
                INSTANCE ?: FlutterEngine(context).also { initFlutterEngine(it, initialRoute) }
            }
        }

        private fun initFlutterEngine(flutterEngine: FlutterEngine, initialRoute: String? = null) {
            INSTANCE = flutterEngine
            if (initialRoute != null) {
                flutterEngine.navigationChannel.setInitialRoute(initialRoute) // 为缓存的 FlutterEngine 配置自定义的初始路由
            }
            val dartEntrypoint = DartExecutor.DartEntrypoint.createDefault()
            flutterEngine.dartExecutor
                .executeDartEntrypoint(dartEntrypoint)  // Start executing Dart code to pre-warm the FlutterEngine
            // FlutterEngine 其实是保存在一个单例对象的 Map 中，可以根据自己的需求增删改查
            FlutterEngineCache.getInstance() // Static singleton cache that holds FlutterEngine instances identified by String
                .put(ENGINE_ID, flutterEngine) // 其他 API：get/remove/contains/clear
        }
    }
}