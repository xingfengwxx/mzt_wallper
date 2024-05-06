package com.hjq.demo.http.model

import com.google.gson.annotations.SerializedName

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2019/12/07
 *    desc   : 统一接口数据结构
 */
open class HttpData<T> {

    /** 返回码 */
    @SerializedName(value = "code", alternate = ["errno"])
    private val code: Int = 0

    /** 提示语 */
    @SerializedName(value = "msg", alternate = ["errmsg"])
    private val msg: String? = null

    /** 数据 */
    @SerializedName(value = "data", alternate = ["res", "hits"])
    private val data: T? = null

    /** 数据总数 */
    @SerializedName(value = "total")
    private val total: Int = 0

    @SerializedName(value = "totalHits")
    private val totalHits = 0

    fun getCode(): Int {
        return code
    }

    fun getMessage(): String? {
        return msg
    }

    fun getData(): T? {
        return data
    }

    /**
     * 是否请求成功
     */
    fun isRequestSucceed(): Boolean {
        return code == 0
    }

    /**
     * 是否 Token 失效
     */
    fun isTokenFailure(): Boolean {
        return code == 1001
    }
}