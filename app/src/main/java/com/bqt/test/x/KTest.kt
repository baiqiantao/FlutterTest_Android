package com.bqt.test.x

import android.widget.Toast

fun MainActivity.showToast(text: Any) {
    Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
}

data class Person(val name: String, val age: Int)

fun main() {

}