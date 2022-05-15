package com.bqt.test.x

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        QtFlutterEngine.init(this)
    }
}