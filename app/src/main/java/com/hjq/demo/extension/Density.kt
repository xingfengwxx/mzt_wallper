package com.hjq.demo.extension

import com.hjq.demo.app.App


/**
 * 根据手机的分辨率将dp转成为px。
 */
fun dp2px(dp: Float): Int {
    val scale = App.instance.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * 根据手机的分辨率将px转成dp。
 */
fun px2dp(px: Float): Int {
    val scale = App.instance.resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * 获取屏幕宽值。
 */
val screenWidth
    get() = App.instance.resources.displayMetrics.widthPixels

/**
 * 获取屏幕高值。
 */
val screenHeight
    get() = App.instance.resources.displayMetrics.heightPixels

/**
 * 获取屏幕像素：对获取的宽高进行拼接。例：1080X2340。
 */
fun screenPixel(): String {
    App.instance.resources.displayMetrics.run {
        return "${widthPixels}X${heightPixels}"
    }
}
