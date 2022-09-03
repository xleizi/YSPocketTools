package com.lei123.YSPocketTools.http.HTTPs

import android.util.Log
import com.google.gson.Gson
import com.lei123.YSPocketTools.entity.getNotice
import com.lei123.YSPocketTools.utils.Constant
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * HTTP获取通知信息
 */
fun getNoticeHTTP(): getNotice {
    val noticeurl: String = Constant.GetNotice_URL
    val request: Request = Request.Builder()
        .url(noticeurl)
        .get()
        .build()
    val call = OkHttpClient().newCall(request)
    var getNotice = getNotice()
    try {
        val response = call.execute() //必须子线程执行
        val result = response.body!!.string()
        Log.i("result",result)
        getNotice = Gson().fromJson(result, getNotice::class.java)
    } catch (e: Exception) {
        //System.out.println("网络问题获取失败");
        getNotice.notice = "Error"
        e.printStackTrace()
    }
    return getNotice
}