package com.lei123.YSPocketTools.http.HTTPs

import android.util.Log
import com.google.gson.Gson
import com.lei123.YSPocketTools.AndroidTools.Get_DS2
import com.lei123.YSPocketTools.AndroidTools.getServer.get_server
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.entity.getSignAwardResult
import com.lei123.YSPocketTools.entity.getSignInfoResult
import com.lei123.YSPocketTools.utils.Constant
import com.lei123.YSPocketTools.utils.getString
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

object SignHttp {
    val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()

    /**
     * 签到HTTP
     * @return
     */
    fun postSignHTTP(uid: String, cookie: String): String {
        val url: String = Constant.miHoYoSign_URL
        val server = get_server(uid)
        var result = "failed"
        //post请求
        val formBody: FormBody = FormBody.Builder()
            .add("act_id", Constant.act_id)
            .add("region", server)
            .add("uid", uid)
            .build()
        val json = "{ \"act_id\" : \"" + Constant.act_id.toString() + "\", \"region\" : \"" + server + "\", \"uid\" : \"" + uid + "\"}"
        val body = RequestBody.create(JSON, json)
        //println(formBody.value(1))
        //println(formBody)
        //println(server)
        //println(uid)
        //println(json)
        val request: Request = Request.Builder()
            .url(url)
            .addHeader("Accept", "application / json, text / plain, */*")
            .addHeader(
                "User",
                "Mozilla/5.0 (Linux; Android 6.0.1; MuMu Build/V417IR; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36 miHoYoBBS/2.35.2"
            )
            .addHeader("Content-Type", "text/plain")
            .addHeader("x-rpc-device_id", "fa498beb-eddf-345d-84e1-a3145b225309")
            .addHeader("x-rpc-client_type", "2")
            .addHeader("x-rpc-app_version", "2.35.2")
            .addHeader("Cookie", cookie)
            .addHeader("DS", Get_DS2("ZSHlXeQUBis52qD1kEgKt5lUYed4b7Bb"))
            .post(body)
            .build()
        val call = OkHttpClient().newCall(request)
        try {
            val response: Response  = call.execute() //必须子线程执行
            result = response.body!!.string()
            result = result.replace("\n", "")
            println(result)
            Log.i("response", "1"+result+"1")
        } catch (e: Exception) {
            println("网络错误")
            e.printStackTrace()
        }
        return result
    }


    fun doWithCookiesGet(url: String, cookie: String): String {
        var result = "获取失败!"
        val request: Request = Request.Builder()
            .url(url)
            .addHeader("Cookie", cookie)
            .get()
            .build()
        val call = OkHttpClient().newCall(request)
        try {
            val response = call.execute() //必须子线程执行
            result = response.body!!.string()
        } catch (e: java.lang.Exception) {
            println("网络错误")
            e.printStackTrace()
        }
        return result
    }

    /**
     * 获取总签到天数
     *
     * @param uid
     * @param cookie
     * @return
     */
    fun signInfo(uid: String, cookie: String): Array<String> {
        var url_info = Constant.miHoYoSignInfo_URL
        url_info = url_info + uid + "&region=" + get_server(uid)
        var result: String? = "获取失败"
        var Total = "0"
        var Today = "获取失败"
        try {
            result = doWithCookiesGet(url_info, cookie)
            val SignResult: getSignInfoResult = Gson().fromJson(result, getSignInfoResult::class.java)
            Total = SignResult.data?.total_sign_day.toString()
            Today = SignResult.data?.today.toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return arrayOf(Total, Today)
    }

    /**
     * 获取总签到天数
     *
     * @param uid
     * @param cookie
     * @return
     */
    fun issign(uid: String, cookie: String): String {
        var url_info = Constant.miHoYoSignInfo_URL
        url_info = url_info + uid + "&region=" + get_server(uid)
        var result: String? = "获取失败"
        var issign = "false"
        try {
            result = doWithCookiesGet(url_info, cookie)
            val SignResult: getSignInfoResult = Gson().fromJson(result, getSignInfoResult::class.java)
            issign = SignResult.data?.is_sign ?: "false"
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return issign
    }


    /**
     * 总签到收获
     *
     * @param cookie
     * @param total
     * @return
     * @throws String
     */
    fun signAward(cookie: String, total: Int): String {
        val url_award = Constant.miHoYoSignAward_URL
        var award = "Error"
        var cnt = 0
        try {
            val result = doWithCookiesGet(url_award, cookie!!)
            val SignResult: getSignAwardResult =Gson().fromJson(result, getSignAwardResult::class.java)
            award = SignResult.data!!.awards!![total - 1].name.toString()
            cnt = SignResult.data!!.awards!![total - 1].cnt
            award = getString(R.string.signTodayAward)+ "$award x $cnt"
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return award
    }
}