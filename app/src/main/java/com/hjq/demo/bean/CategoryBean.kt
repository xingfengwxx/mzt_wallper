package com.hjq.demo.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * author : 王星星
 * date : 2024/4/30 17:07
 * email : 1099420259@qq.com
 * description : 分类
 */
@Parcelize
data class CategoryBean(
    val id: String,
    val name: String
) : Parcelable