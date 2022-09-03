package com.lei123.YSPocketTools.http.HTTPs

import com.lei123.YSPocketTools.AndroidTools.Get_DS
import com.lei123.YSPocketTools.AndroidTools.getServer
import com.lei123.YSPocketTools.utils.Constant
import okhttp3.*

object getDailyHTTPS {
    fun Daily(uid: String, cookie: String): String {
        val server: String = getServer.get_server(uid)
        val body = ""
        val q = "role_id=$uid&server=$server"
        val url =
            "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/dailyNote?$q"
        return doDailyinfoGet(url, cookie, body, q)
    }

    fun doDailyinfoGet(httpurl: String, cookie: String, body: String?, q: String?): String {
        val request: Request = Request.Builder()
            .url(httpurl)
            .addHeader("Host", "api-takumi-record.mihoyo.com")
            .addHeader("x-rpc-client_type", "5")
            .addHeader("Accept-Language", "zh-CN,en-US;q=0.9")
            .addHeader("Accept", "application/json, text/plain, */*")
            .addHeader("Origin", "https://webstatic.mihoyo.com")
            .addHeader("User-Agent", Constant.MiHoYo_ANGENT)
            .addHeader("x-rpc-app_version", "2.28.1")
            .addHeader("Referer", "https://webstatic.mihoyo.com")
            .addHeader("Cookie", cookie)
            .addHeader("DS", Get_DS(body, q))
            .addHeader("Userx", "")
            .addHeader("Userx", "")
            .get()
            .build()
        val call = OkHttpClient().newCall(request)
        try {
            val response: Response = call.execute() //必须子线程执行
            var result: String? = response.body!!.string()
            if (result.isNullOrEmpty()){
                return "failed"
            }
            return result
        }catch (e:Exception){
            return "failed"
        }
    }
}