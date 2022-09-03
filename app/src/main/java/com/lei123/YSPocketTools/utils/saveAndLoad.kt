package com.lei123.YSPocketTools.utils

import android.app.Activity
import androidx.activity.ComponentActivity
import com.lei123.YSPocketTools.cst.SpCst.DEFAULT_SP_NAME




fun saveString(key: String, string: String) {
    application
        .getSharedPreferences(DEFAULT_SP_NAME, Activity.MODE_PRIVATE)
        .edit()
        .putString(key, string)
        .commit() //写入
}
fun saveString(constant: Constant,key: String, string: String) {
    application
        .getSharedPreferences(DEFAULT_SP_NAME, Activity.MODE_PRIVATE)
        .edit()
        .putString(key, string)
        .commit() //写入
}
fun loadString(key: String): String {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getString(key, "123456789") ?: "123456789"
}

fun loadString(key: String, def: String): String {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getString(key, def) ?: def
}

fun loadMainUID(): String {
    return loadString("mainUID")
}

fun loadMainCookie(): String {
    return loadString("mainCookie")
}

fun saveMainUID(string: String) {
    saveString("mainUID", string)
}

fun saveMainCookie(string: String) {
    saveString("mainCookie", string)
}

fun saveInt(key: String, int: Int) {
    application
        .getSharedPreferences(DEFAULT_SP_NAME, Activity.MODE_PRIVATE)
        .edit()
        .putInt(key, int)
        .commit() //写入
}

fun loadInt(key: String): Int {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getInt(key, 123456789)
}

fun loadInt(key: String, def: Int): Int {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getInt(key, def)
}


fun saveBoolean(key: String, boolean: Boolean) {
    application
        .getSharedPreferences(DEFAULT_SP_NAME, Activity.MODE_PRIVATE)
        .edit()
        .putBoolean(key, boolean)
        .commit() //写入
}

fun loadBoolean(key: String): Boolean {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getBoolean(key, false)
}

fun loadBoolean(key: String, def: Boolean): Boolean {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getBoolean(key, def)
}


fun saveLong(key: String, long: Long) {
    application
        .getSharedPreferences(DEFAULT_SP_NAME, Activity.MODE_PRIVATE)
        .edit()
        .putLong(key, long)
        .commit() //写入
}

fun loadLong(key: String): Long {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getLong(key, 123456789)
}

fun loadLong(key: String, def: Long): Long {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getLong(key, def)
}

fun saveFloat(key: String, float: Float) {
    application
        .getSharedPreferences(DEFAULT_SP_NAME, Activity.MODE_PRIVATE)
        .edit()
        .putFloat(key, float)
        .commit() //写入
}

fun loadFloat(key: String): Float {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getFloat(key, 1f)
}

fun loadFloat(key: String, def: Float): Float {
    return application.getSharedPreferences(
        DEFAULT_SP_NAME,
        ComponentActivity.MODE_PRIVATE
    ).getFloat(key, def)
}


