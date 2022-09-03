package com.lei123.YSPocketTools.ui.widgets

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.lei123.YSPocketTools.AndroidTools.getFramesbyGif
import com.lei123.YSPocketTools.AndroidTools.startService.startBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startwidgetsService
import com.lei123.YSPocketTools.AndroidTools.systemInformation
import com.lei123.YSPocketTools.utils.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class widgetsService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        /*val intentOne = Intent(application, widgetsService::class.java)
        intentOne.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startService(intentOne)*/
        super.onDestroy()
        try {
            task1s.cancel()
            timer1s.cancel()
        } catch (e: Exception) {

        }
        try {
            startBasicService(application)
            startwidgetsService(application)
        } catch (e: Exception) {

        }
    }

    private var timer: Timer? = null
    private val sdf = SimpleDateFormat("HH:mm:ss")
    lateinit var context: Context
    var ImageFrame = 50
    override fun onCreate() {
        super.onCreate()
        //"widgetServiceRun".toast()
        context = applicationContext

        ImageFrame = getFramesbyGif("hutaoao.gif")
        timer1s.schedule(task1s, 0, (1 * 1 * 1000).toLong())
        //timer2s.schedule(task2s, 0, (1 * 1 * 23).toLong())
    }

    var count = 0
    var frame = 0

    private val timer2s = Timer(true)
    private val task2s: TimerTask = object : TimerTask() {
        override fun run() {
            val lastAppVertion = loadString(
                "lastAppVertion",
                systemInformation.getVersion(application)
            )
            val ifupdate = lastAppVertion != systemInformation.getVersion(application)
            if (count == 0) {
                if (ifupdate) {
                    "原神口袋工具可更新，最新版本$lastAppVertion".toast()
                }
            }
            count += 1
            if (count > 3600) {
                count = 0
            }
            if (frame > ImageFrame) {
                frame = 0
            }
            frame = frame + 1
            saveInt("frame", frame)
            //widgetservice3".toast()

            startBasicService(application)
            val time = sdf.format(Date())
            saveString("time", time)
            //Log.i("123456789", time)
            //"widgetservice4".toast()
            GlobalScope.launch() {
                //FirstGlanceWidget().updateAll(application)
                GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI_gif_hutaoao::class.java)
                    .let {
                        for (GlanceId in it) {
                            var id = GlanceId.toString()
                            var uid = loadString(id)
                            //Log.i("123456789", uid)

                            if (uid != "123456789") {
                                saveString("widgetUid", uid)
                                saveString(id, uid)
                                saveString("widgetID", id)
                                //Log.i("123456789", "1111111111111111111111111")

                                GlanceWidgetUI_gif_hutaoao().update(application, GlanceId)
                            } else {
                                uid = loadMainUID()
                                //Log.i("loadMainUID", uid)
                                saveString("widgetUid", uid)
                                saveString(id, uid)
                                saveString("widgetID", id)

                                GlanceWidgetUI_gif_hutaoao().update(application, GlanceId)
                            }
                        }
                    }
            }


            /*val msg = Message()
            msg.what = 2
            handler.sendMessage(msg)*/
        }
    }

    private val timer1s = Timer(true)
    private val task1s: TimerTask = object : TimerTask() {
        override fun run() {
            widgetUpdateToast()
            if (!loadBoolean("gifState", false)) {
                //widgetservice3".toast()
                startBasicService(application)
                val time = sdf.format(Date())
                saveString("time", time)

                GlobalScope.launch() {
                    //FirstGlanceWidget().updateAll(application)
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI4_2_complex::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI4_2_complex().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(
                        GlanceWidgetUI4_2_complex_horizontal::class.java
                    )
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI4_2_complex_horizontal().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI2_2_transparency::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI2_2_transparency().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI2_2_complex::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI2_2_complex().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI4_1_transparency::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI4_1_transparency().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI4_2_transparency::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI4_2_transparency().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI2_2_simple::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI2_2_simple().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI2_2_simple_inter::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI2_2_simple_inter().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI_gif_hutaoao::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI_gif_hutaoao().update(application, GlanceId)
                            }
                        }
                    GlanceAppWidgetManager(application).getGlanceIds(GlanceWidgetUI_gif_nilue::class.java)
                        .let {
                            for (GlanceId in it) {
                                saveInfo(GlanceId)
                                GlanceWidgetUI_gif_nilue().update(application, GlanceId)
                            }
                        }
                }
            }
        }

        private fun saveInfo(GlanceId: GlanceId) {
            var id = GlanceId.toString()
            var uid = loadString(id, loadMainUID())
            saveString("widgetUid", uid)
            saveString(id, uid)
            saveString("widgetID", id)
        }

        private fun widgetUpdateToast() {
            val lastAppVertion = loadString(
                "lastAppVertion",
                systemInformation.getVersion(application)
            )
            val ifupdate = lastAppVertion != systemInformation.getVersion(application)
            if (count == 0) {
                if (ifupdate) {
                    "原神口袋工具可更新，最新版本$lastAppVertion".toast()
                }
            }
            count += 1
            if (count > 3600) {
                count = 0
            }
        }
    }
}
