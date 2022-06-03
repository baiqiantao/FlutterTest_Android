package com.bqt.test.flutter.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.bqt.test.x.R
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.android.TransparencyMode

class FlutterFragmentActivity : FragmentActivity() {

    private var flutterFragment: FlutterFragment? = null

    companion object {
        private const val FRAGMENT_TAG = "flutter_fragment"
        private const val TYPE = "TYPE"

        fun launche(context: Context, type: Int = 0) {
            val intent = Intent(context, FlutterFragmentActivity::class.java)
            intent.putExtra(TYPE, type)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)

        val fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as FlutterFragment?
        flutterFragment = fragment ?: getFlutterFragment().also {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, it, FRAGMENT_TAG)
                .commit()
        }
    }

    private fun getFlutterFragment(): FlutterFragment = when (intent.getIntExtra(TYPE, 0)) {
        1 -> FlutterFragment
            .withNewEngine() // Create a FlutterFragment with a new FlutterEngine and a desired engine configuration
            .initialRoute("myInitialRoute/") // 允许指定一个初始路由
            .dartEntrypoint("bottomMain") // The name of the initial Dart method to invoke, defaults to "com.bqt.test.x.main"
            .transparencyMode(TransparencyMode.transparent) // 启动一个透明的 FlutterFragment
            .renderMode(RenderMode.surface) // 支持三种渲染模式：surface/texture/image
            .shouldAttachEngineToActivity(true) // 是否应该控制宿主 Activity
            .build()
        2 -> FlutterFragment
            .withCachedEngine(QtFlutterEngine.getEngineId(this)) // 使用预热的 FlutterEngine
            .build() // 当使用已预热的 FlutterEngine 构建 FlutterFragment 时，指定 initialRoute、dartEntrypoint 是无效的
        else -> FlutterFragment.createDefault() // 以 com.bqt.test.x.main() 为 Dart 入口函数，以 / 为初始路由，并使用新的 FlutterEngine
    }

    override fun onPostResume() = super.onPostResume().also { flutterFragment?.onPostResume() }
    override fun onNewIntent(intent: Intent) = super.onNewIntent(intent).also { flutterFragment?.onNewIntent(intent) }
    override fun onBackPressed() = super.onBackPressed().also { flutterFragment?.onBackPressed() }
    override fun onUserLeaveHint() = super.onUserLeaveHint().also { flutterFragment?.onUserLeaveHint() }
    override fun onTrimMemory(level: Int) = super.onTrimMemory(level).also { flutterFragment?.onTrimMemory(level) }
    override fun onRequestPermissionsResult(r: Int, p: Array<String?>, g: IntArray) =
        super.onRequestPermissionsResult(r, p, g).also { flutterFragment?.onRequestPermissionsResult(r, p, g) }
}