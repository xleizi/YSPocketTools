package com.lei123.YSPocketTools.AndroidTools

import android.content.Context
import android.content.Intent
import com.lei123.YSPocketTools.service.BaseService
import com.lei123.YSPocketTools.ui.widgets.widgetsService

object startService {
    fun startBasicService(context: Context){
        val intent = Intent(context, BaseService::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startService(intent)
        }catch (e:Exception){
        }
    }

    fun reStartBasicService(context: Context){
        val intent = Intent(context, BaseService::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.stopService(intent)
            context.startService(intent)
        }catch (e:Exception){
        }
    }

    fun startwidgetsService(context: Context){
        val intent = Intent(context, widgetsService::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startService(intent)
        }catch (e:Exception){
        }
    }

    fun reStartwidgetsService(context: Context){
        val intent = Intent(context, widgetsService::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.stopService(intent)
            context.startService(intent)
        }catch (e:Exception){
        }
    }
}