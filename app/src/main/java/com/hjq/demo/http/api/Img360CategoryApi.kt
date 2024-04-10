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
class Img360CategoryApi : IRequestServer, IRequestApi {

    override fun getHost(): String =
        "http://wallpaper.apc.360.cn/index.php?c=WallPaperAndroid&a=getAllCategories"

    override fun getApi(): String = ""

    data class Bean(
        @SerializedName("create_time")
        val createTime: String,
        @SerializedName("displaytype")
        val displaytype: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("tempdata")
        val tempdata: String,
        @SerializedName("totalcnt")
        val totalcnt: String
    )
}