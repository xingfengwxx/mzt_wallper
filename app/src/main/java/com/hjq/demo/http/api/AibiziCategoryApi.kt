package com.hjq.demo.http.api

import com.hjq.http.config.IRequestApi
import com.google.gson.annotations.SerializedName
import com.hjq.http.config.IRequestServer


/**
 * author : 王星星
 * date : 2024/4/10 10:01
 * email : 1099420259@qq.com
 * description : 手机壁纸分类
 */
class AibiziCategoryApi : IRequestServer, IRequestApi {

    override fun getHost(): String = "http://service.aibizhi.adesk.com/"

    override fun getApi(): String = "v1/wallpaper/category"

    data class Category(
        @SerializedName("category")
        val category: MutableList<Bean>,
    )

    data class Bean(
        @SerializedName("atime")
        val atime: Double,
        @SerializedName("count")
        val count: Int,
        @SerializedName("cover")
        val cover: String,
        @SerializedName("cover_temp")
        val coverTemp: String,
        @SerializedName("ename")
        val ename: String,
        @SerializedName("filter")
        val filter: List<Any>,
        @SerializedName("icover")
        val icover: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("picasso_cover")
        val picassoCover: String,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("rname")
        val rname: String,
        @SerializedName("sn")
        val sn: Int,
        @SerializedName("type")
        val type: Int
    )
}