package com.hjq.demo.http.api

import com.hjq.http.config.IRequestApi

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2019/12/07
 *    desc   : https://api.iimzt.com/app/list/posts?type=post&order=best&page=1
 */
class MztApi : IRequestApi {

    override fun getApi(): String {
        return "data/init.json"
    }

    private var t: String? = null

    fun setTime() {
        t = System.currentTimeMillis().toString()
    }

    class Bean {

    }
}