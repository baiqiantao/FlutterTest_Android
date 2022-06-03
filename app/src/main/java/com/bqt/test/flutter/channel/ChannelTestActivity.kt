package com.bqt.test.flutter.channel

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class ChannelTestActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val array = arrayOf(
            "FlutterPigeonActivity",
            "FlutterChannelActivity",
            "FlutterBasicMessageChannelActivity",
        )
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
    }

    @Deprecated("Deprecated in Java")
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        when (position) {
            0 -> startActivity(Intent(this, FlutterPigeonActivity::class.java))
            1 -> startActivity(Intent(this, FlutterChannelActivity::class.java))
            2 -> startActivity(Intent(this, FlutterBasicMessageChannelActivity::class.java))
        }
    }
}