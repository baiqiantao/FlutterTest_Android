package com.bqt.test.flutter.multiple

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngineCache

class DoubleFlutterActivity : FragmentActivity(), EngineBindingsDelegate {
    private val topBindings: EngineBindings by lazy { EngineBindings(this, this, "topMain", 2) }
    private val bottomBindings: EngineBindings by lazy { EngineBindings(this, this, "bottomMain", 3) }
    private lateinit var root: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        setContentView(root)
        addFlutterFragment(topBindings)
        addFlutterFragment(bottomBindings)

        topBindings.attach()
        bottomBindings.attach()
    }

    private fun addFlutterFragment(engineBindings: EngineBindings) {
        val containerViewId: Int = engineBindings.id
        val engineId: String = engineBindings.id.toString()

        FrameLayout(this).apply {
            id = containerViewId
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f)
            root.addView(this)
        }

        FlutterEngineCache.getInstance().put(engineId, engineBindings.engine) // 缓存 FlutterEngine
        val flutterFragment = FlutterFragment.withCachedEngine(engineId).build<FlutterFragment>() // 构建 Fragment
        supportFragmentManager.beginTransaction().add(containerViewId, flutterFragment).commit() // 添加 Fragment
    }

    override fun onDestroy() {
        FlutterEngineCache.getInstance().remove(topBindings.id.toString())
        FlutterEngineCache.getInstance().remove(bottomBindings.id.toString())
        super.onDestroy()

        bottomBindings.detach()
        topBindings.detach()
    }

    override fun onNext() = startActivity(Intent(this, MultFlutterTestActivity::class.java))
}