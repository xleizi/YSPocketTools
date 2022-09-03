package com.lei123.YSPocketTools.AndroidTools

import android.util.Log
import com.google.gson.Gson
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.entity.getSignResult
import com.lei123.YSPocketTools.http.HTTPs.SignHttp
import com.lei123.YSPocketTools.ui.Notification.startBigTaxtNotification
import com.lei123.YSPocketTools.utils.*

fun home_Sign(viewModel: MainViewModel) {
    Thread {
        val mainUID = loadMainUID()
        val mainCookie = loadMainCookie()
        viewModel.signMainMessage = home_Sign_complex(mainUID, mainCookie)
        viewModel.signMessage = ""
        for (i in 0..9) {
            var uids = loadString("uid".plus(i.toString()))
            var cookies = loadString("cookie".plus(i.toString()))
            if (uids != "123456789" && cookies != "123456789" && uids != mainUID) {
                Log.i("loaduid", uids)
                viewModel.signMessage = viewModel.signMessage + loadString(uids+"usernickname") + "：" +
                        home_Sign_simply(uids, cookies) + "\n"
                Thread.sleep(500)
                //Log.i("signMessage", viewModel.signMessage)
            }
        }
        viewModel.signMainMessage = viewModel.signMainMessage + "\n" + getString(R.string.otherUID) + "\n" + viewModel.signMessage
        Log.i("signMainMessage", viewModel.signMainMessage)
        //sign_notice(viewModel.signMainMessage)
        viewModel.pageLoadingState = false
        saveBoolean("pageLoadingState",false)
    }.start()
}

fun SignAll() {
    Thread {
        val mainUID = loadMainUID()
        val mainCookie = loadMainCookie()
        var signMainMessage = home_Sign_complex(mainUID, mainCookie)
        var signMessage = ""
        var notice = false
        for (i in 0..9) {
            var uids = loadString("uid".plus(i.toString()))
            var cookies = loadString("cookie".plus(i.toString()))
            if (uids != "123456789" && cookies != "123456789" && uids != mainUID) {
                Log.i("loaduid", uids)
                signMessage = signMessage + loadString(uids+"usernickname") + "：" +
                        home_Sign_simply(uids, cookies) + "\n"
                Thread.sleep(500)
                //Log.i("signMessage", viewModel.signMessage)
            }
        }
        signMainMessage = signMainMessage + "\n" + getString(R.string.otherUID) + "\n" + signMessage
        if (signMainMessage.contains(getString(R.string.signSuccess))){
            sign_notice(signMainMessage)
        }
        Log.i("signMainMessage", signMainMessage)
    }.start()
}

fun home_Sign_complex(uid: String, cookie: String): String {
    var signMessage = home_Sign_simply(uid, cookie)
    val usernickname = loadString(uid + "usernickname")
    val level = loadString(uid + "level")
    val region_name = loadString(uid + "region_name")
    var b = "签到天数"
    var c = "签到奖励"
    val signInfo: Array<String> = SignHttp.signInfo(uid, cookie)
    var total = 0
    try {
        total = signInfo[0].toInt()
    }catch (e:Exception){
    }
    var date = signInfo[1]
    //Log.i("total", total.toString())
    //Log.i("date", date)
    b = getString(R.string.signThisMonth) + total + getString(R.string.Days)
    c = SignHttp.signAward(cookie, total)
    var message = """$date
$usernickname		${getString(R.string.level2)}：$level
${getString(R.string.signReward)}
    $signMessage
    $b
    $c"""
    /*var message = """$date
UID:$uid
${getString(R.string.server)}$region_name
$usernickname		${getString(R.string.level2)}：$level
${getString(R.string.signReward)}
    $signMessage
    $b
    $c"""*/
    //Log.i("message", message)
    return message
}

fun home_Sign_simply(uid: String, cookie: String): String {
    var result = SignHttp.postSignHTTP(uid, cookie)
    Log.i("result", result)
    var signMessage = "Error"
    Log.i("signMessage",signMessage)
    if (result == "Too Many Requests"){
        return getString(R.string.signTooMany)
    }
    if (result != "failed") {
        val SignResult: getSignResult = Gson().fromJson(result, getSignResult::class.java)
        signMessage = SignResult.message.toString()
    }
    if (signMessage=="OK"){
        signMessage = getString(R.string.signSuccess)
    }
    //Log.i("message", signMessage)
    return signMessage
}

fun sign_notice(message: String){
    startBigTaxtNotification(
        message,
        "resin",
        getString(R.string.SignNoticeTitle),
        getString(R.string.SignNoticeTitle),
        message,
        getString(R.string.NoticeStr)
    )
}