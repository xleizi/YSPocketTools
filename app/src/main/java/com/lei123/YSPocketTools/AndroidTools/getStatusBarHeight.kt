package com.lei123.YSPocketTools.AndroidTools

import com.lei123.YSPocketTools.utils.application
import java.lang.reflect.Field

//获取状态栏的高度
fun getStatusBarHeight(): Int {
    val resourceId = application.resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        application.resources.getDimensionPixelSize(resourceId)
    } else 0
}

fun getStatusBarHeight2(): Int {
    var statusBarHeight = 0
    try {
        val c = Class.forName("com.android.internal.R\$dimen")
        val `object` = c.newInstance()
        val field: Field = c.getField("status_bar_height")
        val x = field.get(`object`) as Int
        statusBarHeight = application.resources.getDimensionPixelSize(x)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return statusBarHeight
}