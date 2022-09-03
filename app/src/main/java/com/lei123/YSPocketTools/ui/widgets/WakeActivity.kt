package com.lei123.YSPocketTools.ui.widgets

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lei123.YSPocketTools.AndroidTools.startService.startBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startwidgetsService

/**
 * this activity is used to wake up the target app by package name
 * cuz android widgets are not supported to wake up other app?
 */
class WakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBasicService(application)
        startwidgetsService(application)

        //返回桌面
        //moveTaskToBack(false);
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(homeIntent)
        this@WakeActivity.finish()
    }
}

