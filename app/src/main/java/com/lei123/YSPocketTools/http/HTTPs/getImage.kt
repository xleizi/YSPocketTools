package com.lei123.YSPocketTools.http.HTTPs

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL


object getImage {
    /**
     * 获取网落图片资源
     * @param url
     * @return
     */
    fun getHttpBitmap(url: String?): Bitmap? {
        val myFileURL: URL
        var bitmap: Bitmap? = null
        try {
            myFileURL = URL(url)
            //获得连接
            val conn = myFileURL.openConnection() as HttpURLConnection
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.connectTimeout = 6000
            //连接设置获得数据流
            conn.doInput = true
            //不使用缓存
            conn.useCaches = false
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            val `is` = conn.inputStream
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(`is`)
            //关闭数据流
            `is`.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }
}