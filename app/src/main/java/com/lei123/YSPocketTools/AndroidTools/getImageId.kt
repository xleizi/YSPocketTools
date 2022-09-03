package com.lei123.YSPocketTools.AndroidTools

import android.content.res.Resources
import com.lei123.YSPocketTools.utils.application
import com.umeng.socialize.utils.ContextUtil.getPackageName

/**
 * 获取图片资源的id
 * @param name
 * @return
 */
fun getImageId(name: String?): Int {
    val res: Resources = application.resources
    return res.getIdentifier(name, "drawable", getPackageName())
}