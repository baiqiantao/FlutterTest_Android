package com.bqt.test.flutter.channel

import android.os.Handler
import android.os.Looper


fun Handler.postDelayed(delay: Long, runnable: Runnable) = postDelayed(runnable, delay)
fun isMainThread() = Looper.getMainLooper().thread == Thread.currentThread()