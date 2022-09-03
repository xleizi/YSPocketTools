package com.lei123.YSPocketTools.AndroidTools

import android.graphics.Bitmap
import android.widget.RemoteViews
import com.lei123.YSPocketTools.utils.application
import pl.droidsonroids.gif.GifDrawable

fun getBitmapArrayByGif(assertPath: String, index: Int): Bitmap? {
    var index = index
    return try {
        //val list: ArrayList<Bitmap> = ArrayList()
        val gifFromAssets = GifDrawable(application.getAssets(), assertPath) //代表android中assert的gif文件名
        val totalCount: Int = gifFromAssets.numberOfFrames
        //Log.i("totalCount", totalCount.toString())
        if (totalCount < index) {
            index = totalCount - 1
        }
        return gifFromAssets.seekToFrameAndGet(index)
    } catch (e: Exception) {
        null
    }
}

fun getFramesbyGif(assertPath: String): Int {
    return try {
        val gifFromAssets = GifDrawable(application.getAssets(), assertPath) //代表android中assert的gif文件名
        val totalCount: Int = gifFromAssets.numberOfFrames
        //Log.i("totalCount", totalCount.toString())
        return totalCount
    } catch (e: Exception) {
        50
    }
}


