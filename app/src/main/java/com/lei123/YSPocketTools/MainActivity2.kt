package com.lei123.YSPocketTools

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lei123.YSPocketTools.AndroidTools.home_Sign
import com.lei123.YSPocketTools.service.BaseService
import com.lei123.YSPocketTools.ui.LoginDialog
import com.lei123.YSPocketTools.utils.*
import com.lei123.YSPocketTools.ui.CookieInputDialog
import com.lei123.YSPocketTools.webview.WebLoginActivity
import com.lei123.YSPocketTools.webview.checkToken
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
                if (viewModel.settingBoxState) {
                    settingDialog(
                        viewModel,
                        onDismissRequest = {
                            viewModel.settingBoxState = false
                        },
                        onSignRequest = {
                            viewModel.settingBoxState = false
                            //getString(R.string.signWaiting).toast()
                            viewModel.pageLoadingState = true
                            saveBoolean("pageLoadingState",true)
                            viewModel.signMessageState = true
                            home_Sign(viewModel)
                        }
                    )

                }
            }
        }
    }
}

fun turnToLogWeb() {
    val intent = Intent(application, WebLoginActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    application.startActivity(intent)
}

fun deleteMainUid() {
    for (i in 0..9) {
        if (loadString("uid".plus(i.toString())) == loadString("mainUID")) {
            saveString("uid".plus(i.toString()), "123456789")
            saveString("cookie".plus(i.toString()), "123456789")
            break
        }
    }
    saveMainUID("123456789")
    saveMainCookie("123456789")
    FirstuidToMainUid()
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

