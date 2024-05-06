package com.hjq.demo.util

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ResourceUtils
import com.google.gson.reflect.TypeToken
import com.hjq.demo.bean.CategoryBean

/**
 * author : 王星星
 * date : 2024/5/6 17:25
 * email : 1099420259@qq.com
 * description :
 */
object LocalDataUtil {

    fun getPixabayCategoryList(): List<CategoryBean> {
        val jsonStr = ResourceUtils.readAssets2String("category/pixabay_category.json")
        return jsonToList(jsonStr)
    }

    // 将JSON字符串转换为对象列表
    private fun jsonToList(jsonString: String): List<CategoryBean> {
        val gson = GsonUtils.getGson()
        // 创建泛型类型对象，用于解析JSON数组
        val typeOfList = object : TypeToken<List<CategoryBean>>(){}.type
        // 将JSON字符串转换为对象列表
        return gson.fromJson(jsonString, typeOfList)
    }

}