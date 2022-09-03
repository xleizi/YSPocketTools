package com.lei123.YSPocketTools.ui.Notification

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.application
import com.lei123.YSPocketTools.utils.imageUrlToBitmap

fun startBigTaxtNotification(
    content: String,
    iconUrl: String,
    title: String,
    SubText: String,
    text: String,
    channel_id: String,
) {
    lateinit var largeIcon: Bitmap
    if (iconUrl=="resin"){
        largeIcon = BitmapFactory.decodeResource(application.resources, R.drawable.icon_resin)
    }else if (iconUrl=="homecoin"){
        largeIcon = BitmapFactory.decodeResource(application.resources, R.drawable.icon_homeicon)
    }else{
        try {
            largeIcon = imageUrlToBitmap(iconUrl)!!
        }catch (e:Exception){
            largeIcon = BitmapFactory.decodeResource(application.resources, R.drawable.icon_resin)
        }
    }
    try {
        noticationServiceDome.bigText(
            content, largeIcon,
            title, SubText, text, channel_id,
            application, false, true, true, true, false
        )
    } catch (e: Exception) {

    }
}