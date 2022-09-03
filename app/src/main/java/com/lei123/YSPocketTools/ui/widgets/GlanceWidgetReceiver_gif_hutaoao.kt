package com.lei123.YSPocketTools.ui.widgets

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class GlanceWidgetReceiver_gif_hutaoao : GlanceAppWidgetReceiver(){
    override val glanceAppWidget: GlanceAppWidget = GlanceWidgetUI_gif_hutaoao()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        //getString(R.string.widgethints1).toast()
        super.onReceive(context, intent)


        /*val intentOne = Intent(context, WidgetService::class.java)
        intentOne.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startService(intentOne)*/
    }

}