package com.lei123.YSPocketTools.http.HTTPs

import android.util.Log
import com.lei123.YSPocketTools.AndroidTools.Get_DS
import com.lei123.YSPocketTools.AndroidTools.Get_DS3
import com.lei123.YSPocketTools.utils.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.util.concurrent.TimeUnit


object getHTTPs {
    val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()

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

    fun getRequest2(url: String, cookie: String = ""): String {
        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .addHeader("User-Agent", Constant.ANGENT)
            .addHeader("Cookie", cookie)
            .build()
        val call = OkHttpClient()
            .newCall(request)
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

    /**
     * 获取Uid
     */
    fun getRequestCookieDS(url: String, cookie: String = "", q:String): String {
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .addHeader("Host", "api-takumi-record.mihoyo.com")
            .addHeader("x-rpc-client_type", "5")
            .addHeader("Accept-Language", "zh-CN,en-US;q=0.9")
            .addHeader("Accept", "application/json, text/plain, */*")
            .addHeader("Origin", "https://webstatic.mihoyo.com")
            .addHeader("User-Agent", Constant.MiHoYo_ANGENT)
            .addHeader("x-rpc-app_version", "2.28.1")
            .addHeader("Referer", "https://webstatic.mihoyo.com")
            .addHeader("Userx", "")
            .addHeader("User-Agent", Constant.ANGENT)
            .addHeader("Cookie", cookie)
            .addHeader("DS", Get_DS("",q))
            .build()

        //Log.i("getSummary", Get_DS("",q))
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

    /**
     * 获取Uid
     */
    fun postRequestCookieDS(url: String, cookie: String = "", uid:String, server:String): String {
        //post请求
        val json = "{\"role_id\":\"$uid\",\"server\":\"$server\"}"
        val body = RequestBody.create(JSON, json)

        val request: Request = Request.Builder()
            .url(url)
            .addHeader("x-rpc-client_type", "5")
            .addHeader("x-rpc-app_version", "2.35.2")
            .addHeader("User-Agent", Constant.ANGENT)
            .addHeader("Cookie", cookie)
            .addHeader("DS", Get_DS(json,""))
            .post(body)
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


    /**
     * 获取Uid
     */
    fun updateRequest(url: String, value: String, uid: String, type:String): String {
        //post请求
        //val body = RequestBody.create(JSON, value)

        val formBody: FormBody = FormBody.Builder()
            .add("uid", uid)
            .add(type, value)
            .build()

        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
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

