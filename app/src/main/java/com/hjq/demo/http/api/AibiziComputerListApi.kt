package com.hjq.demo.http.api

import com.google.gson.annotations.SerializedName
import com.hjq.demo.bean.AibiziPicBean
import com.hjq.http.annotation.HttpIgnore
import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestServer

/**
 * author : 王星星
 * date : 2024/4/10 16:05
 * email : 1099420259@qq.com
 * description :
 */
class AibiziComputerListApi : IRequestServer, IRequestApi {

    override fun getHost(): String = "http://service.aibizhi.adesk.com/"

    override fun getApi(): String {
        return "v1/wallpaper/category/$id/wallpaper"
    }

    @HttpIgnore
    private var id: String? = null

    fun setId(id: String): AibiziComputerListApi {
        this.id = id
        return this
    }

    private var skip: Int = 0

    fun setSkip(skip: Int) {
        this.skip = skip
    }

    data class Wallpaper(
        @SerializedName("wallpaper")
        val wallpaper: MutableList<AibiziPicBean>,
    )

}