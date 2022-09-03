package com.lei123.YSPocketTools.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lei123.YSPocketTools.AndroidTools.home_Sign
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.utils.*
import com.lei123.YSPocketTools.webview.WebLoginActivity
import com.lei123.test.ui.settingDialog

/**
 * this activity is used to wake up the target app by package name
 * cuz android widgets are not supported to wake up other app?
 */
class MainActivity2 : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.LoginState = true
        viewModel.cookieBoxState = false
        viewModel.settingBoxState = true
        setContent {
            YSComposeTheme {

            }
        }
    }
}

fun FirstuidToMainUid() {
    var a = 0
    for (i in 0..9) {
        a += 1
        if (loadString("uid".plus(i.toString())) != "123456789") {
            saveMainUID(loadString("uid".plus(i.toString())))
            saveMainCookie(loadString("cookie".plus(i.toString())))
            break
        }
    }
    if (a == 10){
        saveBoolean("ifLogin",false)
    }
}

