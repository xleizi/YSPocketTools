package com.lei123.YSPocketTools.webview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.lei123.YSPocketTools.MainActivity
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.service.BaseService
import com.lei123.YSPocketTools.utils.runOnMainThread
import com.lei123.YSPocketTools.utils.saveString
import com.lei123.YSPocketTools.utils.toast

class WebLoginActivity : WebViewActivity() {

    override fun onLoadUrl(intent: Intent?): String = ApiCst.MIHOYO_BBS_LOGIN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RemoveCookiesTask {
            runOnMainThread { initView() }
        }.start()
    }

    override fun onWebViewPageFinished(view: WebView?, url: String?) {
        val cookie = CookieManager.getInstance().getCookie(ApiCst.MIHOYO_BBS_LOGIN) ?: ""
        Log.i("kyle", "cookie = $cookie")
        if (checkToken(cookie)) {
            R.string.get_cookie_success.toast()
            saveString("LoginCookie", cookie)
            val intent = Intent(application, BaseService::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            application.stopService(intent)
            application.startService(intent)
            val intent2 = Intent(this, MainActivity::class.java)
            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent2)
            finish()
        }
    }
}