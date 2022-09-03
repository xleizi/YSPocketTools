package com.lei123.YSPocketTools.AndroidTools

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.application
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.toast
import java.util.ArrayList

fun toSettingPage() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", application.packageName, null)
    intent.data = uri
    application.startActivity(intent)
}

fun ignoreBattery() {
    val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
    val uri = Uri.fromParts("package", application.packageName, null)
    intent.data = uri
    application.startActivity(intent)
}

//主要方法
fun myCheckPermission(activity: Activity) {
    //需要申请的权限列表
    val permissions = arrayOf<String>(
        Manifest.permission.ACCESS_NOTIFICATION_POLICY
    )
    //需要授权权限列表
    val tempPermissions: MutableList<String> = ArrayList()
    for (permission in permissions) {
        //判断权限是否已经获取
        if (ContextCompat.checkSelfPermission(
                application,
                permission
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            tempPermissions.add(permission)
        }
    }
    //如果需要授权列表不为空，那么就授权
    if (tempPermissions.size != 0) {
        //这里1是用来在回调函数中判断是哪次请求的【请求码】
        ActivityCompat.requestPermissions(activity, tempPermissions.toTypedArray(), 1)
    }

}


/**
 * 忽略电池优化
 */
fun ignoreBatteryOptimization() {
    val powerManager: PowerManager = application.getSystemService(ComponentActivity.POWER_SERVICE) as PowerManager
    var hasIgnored = false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        hasIgnored = powerManager.isIgnoringBatteryOptimizations(application.packageName)
        //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
        if (!hasIgnored) {
            val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.data = Uri.parse("package:" + application.packageName)
            application.startActivity(intent)
        }else{
            getString(R.string.alreadyIgnoreBatteryOptimization).toast()
        }
    }
}