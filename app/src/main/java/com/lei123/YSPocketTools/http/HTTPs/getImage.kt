package com.lei123.YSPocketTools.http.HTTPs

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.lei123.YSPocketTools.utils.FileUtils
import com.lei123.YSPocketTools.utils.UrlToName
import com.lei123.YSPocketTools.utils.getSavePath
import com.lei123.selectcoser.BitmapUtils
import java.io.File
import java.net.HttpURLConnection
import java.net.URL


object getImage {

    /*fun urlSaveToImage(url:String) {
        Thread {
            val name = UrlToName(url)
            val bitmap: Bitmap? = getHttpBitmap(url)
            val TargetPath = getSavePath("images")
            if (bitmap != null) {
                BitmapUtils.saveBitmap(name, bitmap, TargetPath)
            }
        }.start()
    }*/

    fun urlSaveToImage(url:String) {
        Thread {
            val name = UrlToName(url)
            val TargetPath = getSavePath("images")
            try {
                if (!FileUtils.fileIsExist(TargetPath)) {
                    Log.d("Save Bitmap", "TargetPath isn't exist")
                } else {
                    //如果指定文件夹创建成功，那么我们则需要进行图片存储操作
                    val saveFile = File(TargetPath, name)
                    if (!saveFile.exists()) {
                        val bitmap: Bitmap? = getHttpBitmap(url)
                        if (bitmap != null) {
                            BitmapUtils.saveBitmap(name, bitmap, TargetPath)
                        }
                    }
                }
            }catch (e:Exception){
                Log.i("SaveBitmapTargetPath", TargetPath)
                Log.i("SaveBitmapname", name.toString())
                Log.i("SaveBitmapnameurl", url)
                val bitmap: Bitmap? = getHttpBitmap(url)
                if (bitmap != null) {
                    BitmapUtils.saveBitmap(name, bitmap, TargetPath)
                }
            }
        }.start()
    }
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