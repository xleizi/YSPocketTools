package com.lei123.YSPocketTools.webview

import android.net.Uri
import android.text.TextUtils
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import java.util.*


// (useful cookie token): ltoken=jNTz0ZMhkKj2aqFIQDtfLXdBmk9X4nMXWbiubYtz; ltuid=270915355; cookie_token=QggeYVhgEArWcrwUghMqhhe43b6i448bA6rJi4nU; account_id=270915355;
fun checkToken(cookie: String): Boolean {
    return cookie.contains("cookie_token") && cookie.contains("account_id")
}

fun isSameCookieToken(cookieA: Map<String, String>, cookieB: Map<String, String>): Boolean {
    return cookieA["cookie_token"] == cookieB["cookie_token"] && cookieA["account_id"] == cookieB["account_id"]
}

fun parseCookie(cookie: String): Map<String, String> {
    if (cookie.isEmpty()) {
        return mutableMapOf()
    }
    return cookie.split(";").map {
        val split = it.trim().split("=")
        if (split.size == 2) {
            return@map split[0] to split[1]
        }
        return@map "" to ""
    }.toMap()

}

fun clearCookieByUrl(
    url: String,
    pCookieManager: CookieManager,
    pCookieSyncManager: CookieSyncManager
) {
    val uri: Uri = Uri.parse(url)
    val host: String? = uri.getHost()
    clearCookieByUrlInternal(url, pCookieManager, pCookieSyncManager)
    clearCookieByUrlInternal("http://.$host", pCookieManager, pCookieSyncManager)
    clearCookieByUrlInternal("https://.$host", pCookieManager, pCookieSyncManager)
}

private fun clearCookieByUrlInternal(
    url: String,
    pCookieManager: CookieManager,
    pCookieSyncManager: CookieSyncManager
) {
    if (TextUtils.isEmpty(url)) {
        return
    }
    val cookieString: String = pCookieManager.getCookie(url)
    val cookie: Vector<String>? = getCookieNamesByUrl(cookieString)
    if (cookie == null || cookie.isEmpty()) {
        return
    }
    val len: Int = cookie.size
    for (i in 0 until len) {
        pCookieManager.setCookie(url, cookie.get(i) + "=-1")
    }
    pCookieSyncManager.sync()
}

private fun getCookieNamesByUrl(cookie: String): Vector<String>? {
    if (TextUtils.isEmpty(cookie)) {
        return null
    }
    val cookieField = cookie.split(";").toTypedArray()
    val len = cookieField.size
    for (i in 0 until len) {
        cookieField[i] = cookieField[i].trim { it <= ' ' }
    }
    val allCookieField: Vector<String> = Vector<String>()
    for (i in 0 until len) {
        if (TextUtils.isEmpty(cookieField[i])) {
            continue
        }
        if (!cookieField[i].contains("=")) {
            continue
        }
        val singleCookieField = cookieField[i].split("=").toTypedArray()
        allCookieField.add(singleCookieField[0])
    }
    return if (allCookieField.isEmpty()) {
        null
    } else allCookieField
}

/*fun insertAccountByCookie(cookie: String) {
    val id = parseCookie(cookie)["account_id"] ?: return
    if (!TextUtils.isEmpty(id)) {
        val acc = DatabaseStore.queries.selectAccount(id).executeAsOneOrNull()
        if (acc == null) {
            DatabaseStore.queries.insertAccount(
                account_id = id,
                cookie = cookie,
                cookie_updated_at = Date().time,
                nick_name = "",
                type = "miyoushe"
            )
            return
        }
        if (acc.cookie != cookie) {
            //DatabaseStore.queries.updateCookie(cookie, Date().time, id)
        }
    }
}*/

