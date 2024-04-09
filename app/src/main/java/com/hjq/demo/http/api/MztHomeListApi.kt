package com.hjq.demo.http.api

import com.hjq.http.config.IRequestApi

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2019/12/07
 *    desc   : https://api.iimzt.com/app/list/posts?type=post&order=best&page=1
 */
class MztHomeListApi : IRequestApi {

    override fun getApi(): String {
        return "list/posts"
    }

    private var type: String? = "post"
    private var order: String? = "order"
    private var page: Int = 1

    class Bean {

    }
}