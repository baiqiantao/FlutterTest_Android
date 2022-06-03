package com.bqt.test.flutter.basic

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

class BasicTestActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val array = arrayOf(
            "withNewEngine",
            "withCachedEngine",
            "FlutterFragmentActivity",
            "FlutterFragmentActivity 基础功能",
            "FlutterFragmentActivity + 缓存的 FlutterEngine",
        )
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
    }

    @Deprecated("Deprecated in Java")
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val intent1: Intent = FlutterActivity
            .withNewEngine() // 在内部创建一个属于自己的 FlutterEngine 实例，这会有一个明显的初始化时间
            .dartEntrypointArgs(null) // Pass arguments to Dart's entrypoint function
            .initialRoute("/my_route")  // 自定义初始路由
            .build(this)

        val intent2: Intent = FlutterActivity
            .withCachedEngine(QtFlutterEngine.getEngineId(this))
            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
            .build(this)
        when (position) {
            0 -> startActivity(intent1)
            1 -> startActivity(intent2)
            2 -> FlutterFragmentActivity.launche(this)
            3 -> FlutterFragmentActivity.launche(this, 1)
            4 -> FlutterFragmentActivity.launche(this, 2)
        }
    }
}
