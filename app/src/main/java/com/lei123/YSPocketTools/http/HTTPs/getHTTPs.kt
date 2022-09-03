package com.lei123.YSPocketTools.http.HTTPs

import com.lei123.YSPocketTools.utils.*
import okhttp3.*
import java.io.File

object getHTTPs {
    /**
     * 获取Uid
     */
    fun getRequest(url: String, cookie: String = ""): String {
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .addHeader("User-Agent", Constant.ANGENT)
            .addHeader("Cookie", cookie)
            .build()
        val call = OkHttpClient().newCall(request)
        try {
            val response: Response = call.execute() //必须子线程执行
            var result: String? = response.body!!.string()
            if (result.isNullOrEmpty()) {
                return "failed"
            }
            return result
        } catch (e: Exception) {
            return "failed"
        }
    }


    fun download(fileUrl: String, saveFile: File): Boolean {
        try {
            val request = Request.Builder().url(fileUrl).get().build()
            OkHttpClient().newCall(request).to(saveFile)
            return true
        } catch (e: Exception) {
            e.printStackTrace().toString().toast()
            return false
        }
    }
}

