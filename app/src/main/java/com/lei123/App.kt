package com.lei123

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.lei123.YSPocketTools.check.Base64Util
import com.lei123.YSPocketTools.check.ContextUtils
import com.lei123.YSPocketTools.check.MD5Utils
import com.lei123.YSPocketTools.utils.Constant
import com.lei123.YSPocketTools.utils.application

class App : Application() {

    val CHANNEL_ID = "常规提醒通知"
    val CHANNEL_ID2 = "探索提醒通知"
    val CHANNEL_ID3 = "常驻通知"
    val CHANNEL_ID4 = "每日提醒推送"

    override fun onCreate() {
        super.onCreate()
        application = this
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()

        creatNotification()

        //safeRun { Firebase.crashlytics.setCrashlyticsCollectionEnabled(Config.crashReport) }
        //handleHistoryData()
    }
    private fun creatNotification() {
        createNotificationChannel()
        createNotificationChannel2()
        createNotificationChannel3()
        createNotificationChannel4()
    }

    //树脂提醒
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "常规提醒通知",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    //探索提醒
    fun createNotificationChannel2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID2, "探索提醒通知",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    //常驻通知
    fun createNotificationChannel3() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID3, "常驻通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    //每日通知
    fun createNotificationChannel4() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID4, "每日提醒推送",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }
/*    private fun handleHistoryData() {
        if (!TextUtils.isEmpty(loginCookie)) {
            insertAccountByCookie(loginCookie)
            loginCookie = ""
        }
    }*/



    private var sEarlyCheckSignResult = false

    fun EarlySignResult(): Boolean {
        return sEarlyCheckSignResult
    }

    fun AppApplication() {
        sEarlyCheckSignResult = earlyCheckSign()
    }

    var nowSignMD5: String? = null

    fun earlyCheckSign(): Boolean {
        val trueSignMD5: String = Constant.mmm
        nowSignMD5 = ""
        try {
            // 获取新的 Context
            val context: Context = ContextUtils.getContext()
            //得到签名hashcode
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            )
            val signs = packageInfo.signatures
            val signBase64: String = Base64Util.encodeToString(signs[0].toByteArray())
            nowSignMD5 = MD5Utils.MD5(signBase64)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        println(trueSignMD5)
        println(nowSignMD5)
        return trueSignMD5 == nowSignMD5
    }
}