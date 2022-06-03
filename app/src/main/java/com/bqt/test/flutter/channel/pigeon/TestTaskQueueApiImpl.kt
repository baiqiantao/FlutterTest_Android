package com.bqt.test.flutter.channel.pigeon

import com.bqt.test.flutter.channel.isMainThread

object TestTaskQueueApiImpl : Pigeon.TestTaskQueueApi {
    override fun add(x: Long, y: Long): Long {
        println("add $x $y isMainThread: ${isMainThread()}") // false
        // 一定要注意了，使用 serialBackgroundThread 时是回调在【子线程】，而其他情况都是回调在【主线程】
        return x + y
    }
}