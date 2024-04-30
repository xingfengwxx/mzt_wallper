package com.hjq.demo.bean

import com.google.gson.annotations.SerializedName

/**
 * author : 王星星
 * date : 2024/4/30 14:57
 * email : 1099420259@qq.com
 * description :
 */
data class AibiziPicBean(
    @SerializedName("atime")
    val atime: Double,
    @SerializedName("cid")
    val cid: List<String>,
    @SerializedName("cr")
    val cr: Boolean,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("favs")
    val favs: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("ivip")
    val ivip: Boolean,
    @SerializedName("ncos")
    val ncos: Int,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("rule")
    val rule: String,
    @SerializedName("store")
    val store: String,
    @SerializedName("tag")
    val tag: List<Any>,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("url")
    val url: List<Any>,
    @SerializedName("user")
    val user: User,
    @SerializedName("views")
    val views: Int,
    @SerializedName("wp")
    val wp: String,
    @SerializedName("xr")
    val xr: Boolean
) {
    data class User(
        @SerializedName("auth")
        val auth: String,
        @SerializedName("avatar")
        val avatar: String,
        @SerializedName("follower")
        val follower: Int,
        @SerializedName("id")
        val id: String,
        @SerializedName("isvip")
        val isvip: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("viptime")
        val viptime: Double
    )
}