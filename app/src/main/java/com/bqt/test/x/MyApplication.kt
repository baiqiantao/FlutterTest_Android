package com.bqt.test.x

import android.app.Application
import com.bqt.test.flutter.basic.QtFlutterEngine
import io.flutter.embedding.engine.FlutterEngineGroup

class MyApplication : Application() {
    lateinit var engineGroup: FlutterEngineGroup // Create a FlutterEngineGroup whose child engines will share resources

    override fun onCreate() {
        super.onCreate()
        engineGroup = FlutterEngineGroup(this)
        QtFlutterEngine.init(this)
    }
}