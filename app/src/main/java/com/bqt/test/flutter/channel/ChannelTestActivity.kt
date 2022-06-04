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
            "MethodChannelActivity",
            "PigeonActivity",
            "EventChannelActivity",
            "BasicMessageChannelActivity",
            "TaskQueueActivity",
        )
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
    }

    @Deprecated("Deprecated in Java")
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        when (position) {
            0 -> startActivity(Intent(this, MethodChannelActivity::class.java))
            1 -> startActivity(Intent(this, PigeonActivity::class.java))
            2 -> startActivity(Intent(this, EventChannelActivity::class.java))
            3 -> startActivity(Intent(this, BasicMessageChannelActivity::class.java))
            4 -> startActivity(Intent(this, TaskQueueActivity::class.java))
        }
    }
}