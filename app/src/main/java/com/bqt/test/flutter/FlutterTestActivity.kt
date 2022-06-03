package com.bqt.test.flutter

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.bqt.test.flutter.basic.BasicTestActivity
import com.bqt.test.flutter.channel.ChannelTestActivity
import com.bqt.test.flutter.multiple.MultFlutterTestActivity

class FlutterTestActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val array = arrayOf(
            "BasicTestActivity",
            "ChannelTestActivity",
            "MultFlutterTestActivity",
        )
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
    }

    @Deprecated("Deprecated in Java")
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        when (position) {
            0 -> startActivity(Intent(this, BasicTestActivity::class.java))
            1 -> startActivity(Intent(this, ChannelTestActivity::class.java))
            2 -> startActivity(Intent(this, MultFlutterTestActivity::class.java))
        }
    }
}