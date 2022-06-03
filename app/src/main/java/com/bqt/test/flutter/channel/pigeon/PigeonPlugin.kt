package com.bqt.test.flutter.channel.pigeon

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodChannel

class PigeonPlugin : FlutterPlugin, Pigeon.TestBookApi {
    // This local reference serves to register the plugin with the Flutter Engine and unregister it when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        Pigeon.TestBookApi.setup(flutterPluginBinding.binaryMessenger, this)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        Pigeon.TestBookApi.setup(binding.binaryMessenger, null)
    }

    override fun search(keyword: String?): Pigeon.Book? {
        TODO("Not yet implemented")
    }

    override fun searchList(keyword: String): MutableList<Pigeon.Book> {
        TODO("Not yet implemented")
    }

    override fun searchList2(keys: MutableList<String>): MutableList<Pigeon.Book> {
        TODO("Not yet implemented")
    }

    override fun testNoArguments() {
        TODO("Not yet implemented")
    }
}