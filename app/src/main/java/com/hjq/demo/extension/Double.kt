package com.hjq.demo.extension

/**
 * author : 王星星
 * date : 2023/7/21 11:56
 * email : 1099420259@qq.com
 * description :
 */

/**
 * 设置保留多位小数
 *
 * @param decimalPlaces 小数位数
 * @return eg: 12.35
 */
fun Double.formatToDecimalPlaces(decimalPlaces: Int): String {
    require(decimalPlaces >= 0) { "Decimal places should be non-negative" }
    val formatString = "%.${decimalPlaces}f"
    return String.format(formatString, this)
}

fun Double.formatToTwoDecimalPlaces(): String {
    return String.format("%.2f", this)
}

