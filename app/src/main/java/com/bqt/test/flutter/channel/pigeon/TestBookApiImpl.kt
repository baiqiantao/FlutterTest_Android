package com.bqt.test.flutter.channel.pigeon

import com.bqt.test.flutter.channel.isMainThread

object TestBookApiImpl : Pigeon.TestBookApi {
    override fun search(keyword: String?): Pigeon.Book = Pigeon.Book().apply {
        println("search $keyword isMainThread: ${isMainThread()}") // true，默认回调在主线程
        id = null // 参数、返回值、属性等，都有明确的 @Nullable 或 @NonNull 注解，但是 属性 都是 @Nullable 的，默认值都是 null
        title = "《我的$keyword》"// 支持调用 set/get 方法，也支持使用 Builder 模式
        author = Pigeon.Author.Builder()
            .setName("白乾涛")
            .setMale(true)
            .setState(Pigeon.StateEnum.success)
            .build()
    }

    override fun searchList(keyword: String): MutableList<Pigeon.Book> = mutableListOf(Pigeon.Book()).also {
        println("searchList $keyword isMainThread: ${isMainThread()}")
    }

    override fun searchList2(keys: MutableList<String>): MutableList<Pigeon.Book> = mutableListOf(Pigeon.Book()).also {
        println("searchList2 $keys isMainThread: ${isMainThread()}")
    }

    override fun testNoArguments() = println("testNoArguments isMainThread: ${isMainThread()}")
}
