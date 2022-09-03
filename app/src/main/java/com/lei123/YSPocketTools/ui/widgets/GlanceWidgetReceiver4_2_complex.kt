package com.lei123.YSPocketTools.ui.widgets

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.toast

class GlanceWidgetReceiver4_2_complex : GlanceAppWidgetReceiver(){
    override val glanceAppWidget: GlanceAppWidget = GlanceWidgetUI4_2_complex()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

    }

    override fun onReceive(context: Context, intent: Intent) {
       // getString(R.string.widgethints2).toast()
        super.onReceive(context, intent)

/*        val intentOne = Intent(context, WidgetService::class.java)
        intentOne.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startService(intentOne)*/
    }

}