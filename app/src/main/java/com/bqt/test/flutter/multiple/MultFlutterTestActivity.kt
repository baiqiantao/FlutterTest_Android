package com.bqt.test.flutter.multiple

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MultFlutterTestActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val array = arrayOf(
            "addObserver",
            "removeObserver",
            "SingleFlutterActivity",
            "DoubleFlutterActivity",
            "get",
            "set",
        )
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
    }

    @Deprecated("Deprecated in Java")
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        when (position) {
            0 -> DataModel.addObserver(::observer)
            1 -> DataModel.removeObserver(this::observer)
            2 -> startActivity(Intent(this, SingleFlutterActivity::class.java))
            3 -> startActivity(Intent(this, DoubleFlutterActivity::class.java))
            4 -> Toast.makeText(this, "data: ${DataModel.data}", Toast.LENGTH_SHORT).show()
            5 -> DataModel.data += 1
        }
    }

    private fun observer(data: Int): Unit = Toast.makeText(this, "observer: $data", Toast.LENGTH_SHORT).show()
}