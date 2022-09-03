package com.lei123.YSPocketTools.http.HTTPs

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.lei123.YSPocketTools.AndroidTools.systemInformation.*
import com.lei123.YSPocketTools.entity.selectUid
import com.lei123.YSPocketTools.utils.Constant
import com.lei123.YSPocketTools.utils.application
import com.lei123.YSPocketTools.utils.loadMainUID
import com.lei123.YSPocketTools.utils.loadString
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.*

object saveAccount {
    /**
     * 保存uid，cookie到数据库操作
     */
    fun saveAcountinfo(uid: String) {
        Thread {
            val retcode: Int = selectUid(uid)
            if (retcode == -1) {
                val adedurl: String = Constant.AddUID_URL
                addUid(uid, adedurl)
            } else {
                val adedurl: String = Constant.UpdateUID_URL
                addUid(uid, adedurl)
            }
        }.start()
    }

    /**
     * 查找UID
     */
    private fun selectUid(uid:String): Int {
        val url: String = Constant.GetUID_URL
        var retcode = -1
        //post请求
        val formBody: FormBody = FormBody.Builder()
            .add("uid", uid)
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()
        val call = OkHttpClient().newCall(request)
        //第四步:同步get请求
        try {
            val response = call.execute() //必须子线程执行
            val result = response.body!!.string()
            val selecuid: selectUid = Gson().fromJson(result, selectUid::class.java) //反序列化
            retcode = selecuid.retcode!!
            //System.out.println(result);
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retcode
    }
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    /**
     * 添加或更新uid
     *
     * @param url
     */
    private fun addUid(uid:String, url: String) {
        //获取登录时间
        val LoginTime: String = sdf.format(Date())
        //post请求
        val formBody: FormBody = FormBody.Builder()
            .add("uid", uid)
            .add("registerTime", LoginTime)
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()
        val call = OkHttpClient().newCall(request)
        //第四步:同步get请求
        try {
            val response = call.execute() //必须子线程执行.
            val result = response.body!!.string()
            //System.out.println("response!!!!!!"+result);
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 上传设备信息
     */
    fun updateDeviceInfo(uid:String) {
        Thread {
            val result: String = updateDeviceHTTP(uid)
            Log.i("updateDeviceInfo", "run: $result")
        }.start()
    }

    /**
     * 上传Device信息
     * @return
     */
    fun updateDeviceHTTP(uid: String): String {
        val url = Constant.UpdateDevice_URL
        val a: Array<String> = getSystemInfo(uid,application)
        var result = "failed"
        //post请求
        val formBody: FormBody = FormBody.Builder()
            .add("uid", uid)
            .add("AppVertion", a[0])
            .add("LastestDate", a[1])
            .add("ip", a[2])
            .add("DEVICE", a[3])
            .add("MODEL", a[4])
            .add("systemVersion", a[5])
            .add("BRAND", a[6])
            .add("SDK", a[7])
            .add("DISPLAY", a[8])
            .add("androidId", a[9])
            .add("resolution", a[10])
            .add("Language", a[11])
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()
        val call = OkHttpClient().newCall(request)
        try {
            val response = call.execute() //必须子线程执行
            result = response.body!!.string()
            println(result)
            Log.i("response", result)
        } catch (e: java.lang.Exception) {
            println("网络错误")
            e.printStackTrace()
        }
        return result
    }

    /**
     * 获取设备信息
     *
     * @return
     */
    fun getSystemInfo(uid:String, context: Context): Array<String> {
        var appVertion = getVersion(context) //程序版本
        val NowTime: String = getNowTime() //现在时间
        val DEVICE: String = getDeviceName() //设备名
        val MODEL: String = getModelName() //设备型号
        val systemVersion: String = getSystemVersion() //系统版本号
        val BRAND: String = getBrand() //品牌
        val SDK: String = java.lang.String.valueOf(getSDKVersion()) //SDK
        val DISPLAY: String = getDISPLAY() //系统版本名称
        val androidId: String = getAndroidId(context) //androidId
        val Language: String = getSystemLanguage() //系统语言
        val ip: String = loadString(uid + "miip_region")
        //计算分辨率
        val dm = context.resources.displayMetrics
        val screenWidth = dm.widthPixels
        val screenHeight = dm.heightPixels
        val fenbianlv = "$screenWidth*$screenHeight"
        return arrayOf(
            appVertion,
            NowTime,
            ip,
            DEVICE,
            MODEL,
            systemVersion,
            BRAND,
            SDK,
            DISPLAY,
            androidId,
            fenbianlv,
            Language
        )
    }
}


