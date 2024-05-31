package com.hjq.demo

/**
 * author : 王星星
 * date : 2024/4/12 11:41
 * email : 1099420259@qq.com
 * description : 常量类
 */
object Const {

    object Config {
        const val DIR = "mzt_wallpaper"
        const val PAGE_SIZE = 20
        const val PIXABAY_API_KEY = "43740221-f004bfa831543598cb352bf86"
    }

    object ParamKey {
        // Intent传参KEY
        const val ID = "id"
        const val TYPE = "type"
        const val TITLE = "title"
        const val INDEX = "index"
        const val CATEGORY = "category"
        const val KEYWORD = "keyword"
    }

    object WallpaperType {
        const val TYPE_PHONE = 0
        const val TYPE_COMPUTER = 1
        const val TYPE_360 = 2
        const val TYPE_PIXABAY = 3
    }

    object ScreenType {
        const val MAIN = 1
        const val LOCK = 2
        const val ALL = 3
    }
}