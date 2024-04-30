package com.hjq.demo.http.api

import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestServer
import com.google.gson.annotations.SerializedName


/**
 * author : 王星星
 * date : 2024/4/10 16:05
 * email : 1099420259@qq.com
 * description : http://wallpaper.apc.360.cn/index.php?c=WallPaperAndroid&a=getAppsByCategory&cid=36&start=0&count=20
 */
class Pic360ListApi : IRequestServer, IRequestApi {

    override fun getHost(): String = "http://wallpaper.apc.360.cn/"

    override fun getApi(): String {
        return "index.php"
    }

    private val c = "WallPaperAndroid"
    private val a = "getAppsByCategory"
    private val count = 20

    private var start: Int = 0
    fun setStart(skip: Int) {
        this.start = skip
    }

    private var cid: String = ""
    fun setCid(cid: String) {
        this.cid = cid
    }

    data class Bean(
        @SerializedName("c_t")
        val cT: String,
        @SerializedName("cid")
        val cid: String,
        @SerializedName("dl_cnt")
        val dlCnt: String,
        @SerializedName("fav_total")
        val favTotal: String,
        @SerializedName("imgcut")
        val imgcut: String,
        @SerializedName("pid")
        val pid: String,
        @SerializedName("tempdata")
        val tempdata: String,
        @SerializedName("url")
        val url: String
    )

}