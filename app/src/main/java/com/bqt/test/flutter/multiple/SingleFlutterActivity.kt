package com.bqt.test.flutter.multiple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class SingleFlutterActivity : FlutterActivity(), EngineBindingsDelegate {
    // EngineBindings is used to bridge communication between the Flutter instance and the DataModel
    private val engineBindings: EngineBindings by lazy { EngineBindings(this, this, "main", 1) }

    override fun onCreate(bundle: Bundle?) = super.onCreate(bundle).also { engineBindings.attach() }
    override fun onDestroy() = super.onDestroy().also { engineBindings.detach() }
    override fun provideFlutterEngine(context: Context): FlutterEngine = engineBindings.engine // 使用指定的 FlutterEngine
    override fun onNext() = startActivity(Intent(this, MultFlutterTestActivity::class.java))
}