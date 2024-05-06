package com.hjq.demo.http.api

import com.hjq.demo.Const
import com.hjq.http.annotation.HttpRename
import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestServer
import com.google.gson.annotations.SerializedName
import com.hjq.demo.app.App
import com.hjq.language.MultiLanguages


/**
 * author : 王星星
 * date : 2024/5/6 15:04
 * email : 1099420259@qq.com
 * description : API地址：https://pixabay.com/api/docs/
 *                       https://www.dianbanjiu.com/post/pixabay_api/
 *              https://pixabay.com/api/?key=43740221-f004bfa831543598cb352bf86&q=yellow+flowers&image_type=photo
 */
class PixabayListApi : IRequestServer, IRequestApi {

    override fun getHost(): String = "https://pixabay.com/"


    override fun getApi(): String = "api/"

    private val key = Const.Config.PIXABAY_API_KEY

    @HttpRename("image_type")
    private val imageType = "photo"

    @HttpRename("per_page")
    private val PAGE_SIZE = Const.Config.PAGE_SIZE

    private val safesearch = true

    private val editors_choice = true

    private val lang = MultiLanguages.getAppLanguage(App.instance)

    private var category = "backgrounds"
    fun setCategory(category: String) {
        this.category = category
    }

    @HttpRename("page")
    private var pageNum = 0
    fun setPageNum(pageNum: Int) {
        this.pageNum = pageNum
    }

    data class Bean(
        @SerializedName("collections")
        val collections: Int,
        @SerializedName("comments")
        val comments: Int,
        @SerializedName("downloads")
        val downloads: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("imageHeight")
        val imageHeight: Int,
        @SerializedName("imageSize")
        val imageSize: Int,
        @SerializedName("imageWidth")
        val imageWidth: Int,
        @SerializedName("largeImageURL")
        val largeImageURL: String,
        @SerializedName("likes")
        val likes: Int,
        @SerializedName("pageURL")
        val pageURL: String,
        @SerializedName("previewHeight")
        val previewHeight: Int,
        @SerializedName("previewURL")
        val previewURL: String,
        @SerializedName("previewWidth")
        val previewWidth: Int,
        @SerializedName("tags")
        val tags: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("user")
        val user: String,
        @SerializedName("user_id")
        val userId: Int,
        @SerializedName("userImageURL")
        val userImageURL: String,
        @SerializedName("views")
        val views: Int,
        @SerializedName("webformatHeight")
        val webformatHeight: Int,
        @SerializedName("webformatURL")
        val webformatURL: String,
        @SerializedName("webformatWidth")
        val webformatWidth: Int
    )
}