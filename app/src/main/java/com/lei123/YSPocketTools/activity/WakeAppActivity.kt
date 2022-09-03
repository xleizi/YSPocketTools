package com.lei123.YSPocketTools.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lei123.YSPocketTools.MainActivity
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.launchApp
import com.lei123.YSPocketTools.utils.loadString
import com.lei123.YSPocketTools.utils.saveString

/**
 * this activity is used to wake up the target app by package name
 * cuz android widgets are not supported to wake up other app?
 */
class WakeupAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //saveString("packagename", ApiCst.APP_PACKAGE_NAME_BBS)
        //setContentView(R.layout.activity_awake_app)
        val packageNameCN = loadString("packagename",getString(R.string.genshin))

        val packageName = when(packageNameCN){
            getString(R.string.genshin) -> ApiCst.APP_PACKAGE_NAME_GENSHIN
            getString(R.string.Bgenshin) -> ApiCst.APP_PACKAGE_NAME_GENSHIN_Bilibili
            getString(R.string.yun_genshin) -> ApiCst.APP_PACKAGE_NAME_GENSHIN_CLOUD
            getString(R.string.miyoushe) -> ApiCst.APP_PACKAGE_NAME_BBS
            getString(R.string.OSgenshin) -> ApiCst.APP_PACKAGE_NAME_GENSHIN_OS
            else -> ApiCst.APP_PACKAGE_NAME_GENSHIN
        }
        //Log.i("kyle", "packageName: $packageName")
        if (packageName == "123456789" || TextUtils.isEmpty(packageName)) {
            val intent = Intent(application, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            application.startActivity(intent)
            finish()
        } else {
            launchApp(packageName)
            finish()
        }
    }
}