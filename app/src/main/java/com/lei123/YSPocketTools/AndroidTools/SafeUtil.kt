package com.lei123.YSPocketTools.AndroidTools

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.MessageFormat
import java.util.*

val l = System.currentTimeMillis()

fun Get_DS(body: String?, q: String?): String {
    val n = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs"
    val i = GetTimeStamp()
    val r = GetRandomString()
    val c = GenerateMD5(
        MessageFormat.format(
            "salt={0}&t={1}&r={2}&b={3}&q={4}",
            n,
            i,
            r,
            body,
            q
        )
    )
    return MessageFormat.format("{0},{1},{2}", i, r, c)
}

fun Get_DS3(body: String?, q: String?): String {
    val n = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs"
    val i = GetTimeStamp()
    val r = GetRandomString()
    val c = GenerateMD5(
        MessageFormat.format(
            "salt={0}&t={1}&r={2}&b={3}&q={4}",
            n,
            i,
            r,
            body,
            q
        )
    )
    Log.i("Get_DS3", body.toString())
    Log.i("Get_DS3", q.toString())

    Log.i("Get_DS3",MessageFormat.format("{0},{1},{2}", i, r, c))
    return MessageFormat.format("{0},{1},{2}", i, r, c)
}

fun Get_DS2(salt: String): String {
    val n = salt
    //val n = "dWCcD2FsOUXEstC5f9xubswZxEeoBOTc"
    val i = GetTimeStamp()
    val r = getRandomCharacterAndNumber(6)
    val c = GenerateMD5(MessageFormat.format("salt={0}&t={1}&r={2}", n, i, r))
    return MessageFormat.format("{0},{1},{2}", i, r, c)
}

/**
 * 生成100000到199999的随机数
 * @return
 */
fun GetRandomString(): String {
    val rand = Random() //定义一个随机数生成
    val index = rand.nextInt(100000) + 100000 //生成一个随机数
    return index.toString()
}

fun getRandomCharacterAndNumber(length: Int): String {
    var `val` = ""
    val random = Random()
    for (i in 0 until length) {
        val charOrNum = if (random.nextInt(2) % 2 == 0) "char" else "num" // 输出字母还是数字
        if ("char".equals(charOrNum, ignoreCase = true)) // 字符串
        {
            val choice = if (random.nextInt(2) % 2 == 0) 65 else 97 // 取得大写字母还是小写字母
            `val` += (choice + random.nextInt(26)).toChar()
            // int choice = 97; // 指定字符串为小写字母
            //val += (char) (choice + random.nextInt(26));
        } else if ("num".equals(charOrNum, ignoreCase = true)) // 数字
        {
            `val` += random.nextInt(10).toString()
        }
    }
    return `val`
}

/**
 * 获取时间戳
 * @return
 */
fun GetTimeStamp(): String {
    val ts = System.currentTimeMillis() / 1000
    //TimeSpan ts = DateTime.UtcNow - new DateTime(1970, 1, 1, 0, 0, 0, 0);
    //return Convert.ToInt64(ts.TotalSeconds).ToString();
    return ts.toString()
}

/**
 * MD5加密算法
 * @param plainText
 * @return
 */
fun GenerateMD5(plainText: String): String {
    var secretBytes: ByteArray? = null
    secretBytes = try {
        MessageDigest.getInstance("md5").digest(
            plainText.toByteArray()
        )
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException("没有这个md5算法！")
    }
    var md5code = BigInteger(1, secretBytes).toString(16)
    for (i in 0 until 32 - md5code.length) {
        md5code = "0$md5code"
    }
    return md5code
}
