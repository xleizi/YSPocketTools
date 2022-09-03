package com.lei123.YSPocketTools.ui.widgets

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.media.MediaPlayer
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.action.ActionCallback
import com.lei123.YSPocketTools.AndroidTools.ClickHelper.isFastDoubleClick_10s
import com.lei123.YSPocketTools.AndroidTools.SignAll
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

lateinit var glanceId2: GlanceId

class uidSetting : ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        //showImage
        if (loadString(
                "ifToast",
                getString(R.string.Yes)
            ) == getString(R.string.Yes)
        ) "进入设置界面".toast()
        val id = glanceId.toString()
        glanceId2 = glanceId
        //Log.i("uidChange", glanceId2.toString())
        var widgetClass = "GlanceWidgetUI4_2"
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI4_2_complex::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "complex42"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI2_2_transparency::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "transparency22"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI2_2_complex::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "complex22"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI4_1_transparency::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "transparency41"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI4_2_transparency::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "transparency42"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI4_2_complex_horizontal::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "complex42horizontal"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI_gif_hutaoao::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "gif"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI_gif_nilue::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "gif"
                }
            }
        }
        saveString(id + "widgetClass", widgetClass)
        val intent = Intent(application, widgetSettingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(intent)
    }
}


/**
 * 获取音频资源的id
 * @param name
 * @return
 */
private fun getVoiceId(name: String): Int {
    val res: Resources = application.resources
    return res.getIdentifier(name, "raw", application.packageName)
}

class gifEngine : ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        if (!loadBoolean("gifState", false)){

            Thread {
                GlobalScope.launch() {
                    val id = glanceId.toString()
                    GlanceAppWidgetManager(application).getGlanceIds(
                        GlanceWidgetUI_gif_hutaoao::class.java
                    ).let {
                        for (GlanceId in it) {
                            if (GlanceId.toString() == id) {
                                val name = "hutaoao"
                                val (ImageFrame, speed) = gifWidgetStart(id, name)
                                for (frame in 1..ImageFrame) {
                                    saveInt(id + "frame", frame)
                                    GlanceWidgetUI_gif_hutaoao().update(application, glanceId)
                                    Thread.sleep(speed.toLong())
                                }
                                saveBoolean("gifState", false)
                            }
                        }
                    }
                    GlanceAppWidgetManager(application).getGlanceIds(
                        GlanceWidgetUI_gif_nilue::class.java
                    ).let {
                        for (GlanceId in it) {
                            if (GlanceId.toString() == id) {
                                val name = "nilue"
                                val (ImageFrame, speed) = gifWidgetStart(id, name)
                                for (frame in 1..ImageFrame) {
                                    saveInt(id + "frame", frame)
                                    GlanceWidgetUI_gif_nilue().update(application, glanceId)
                                    Thread.sleep(speed.toLong())
                                }
                                saveBoolean("gifState", false)
                            }
                        }
                    }
                }
            }.start()
        }else{
            getString(R.string.gifRe).toast()
        }
    }

    private fun gifWidgetStart(
        id: String,
        name: String
    ): Pair<Int, Int> {
        try {
            val mMediaPlayer = MediaPlayer.create(application, getVoiceId(name))
            mMediaPlayer.isLooping = false
            mMediaPlayer.start()
        }catch (e:Exception){

        }
        saveBoolean("gifState", true)
        saveString("widgetID", id)
        //val gifname = loadString(id + "gif", name)
        //val ImageFrame = GetGifFrames(gifname)
        val ImageFrame = GetGifFrames(name)
        val speed = loadInt(id + "gifSpeed", 10)
        return Pair(ImageFrame, speed)
    }

}

class uidSettingsimple : ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        //showImage
        if (loadString(
                "ifToast",
                getString(R.string.Yes)
            ) == getString(R.string.Yes)
        ) "进入设置界面".toast()
        val id = glanceId.toString()
        glanceId2 = glanceId
        //Log.i("uidChange", glanceId2.toString())
        var widgetClass = "transparencySimple22"
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI2_2_simple::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "transparencySimple22"
                }
            }
        }
        GlanceAppWidgetManager(application).getGlanceIds(
            GlanceWidgetUI2_2_simple_inter::class.java
        ).let {
            for (GlanceId in it) {
                if (GlanceId.toString() == glanceId2.toString()) {
                    widgetClass = "transparencySimpleinter22"
                }
            }
        }
        saveString(id + "widgetClass", widgetClass)
        val intent = Intent(application, widgetSettingSimpleActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(intent)

        //GlanceWidgetUI4_2().updateAll(context)
    }
}

class ResetAction : ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        //showImage
        //GlanceWidgetUI4_2_complex().updateAll(application)
        //GlanceWidgetUI2_2_complex().updateAll(application)
        //GlanceWidgetUI4_1_transparency().updateAll(application)
        //GlanceWidgetUI4_2_transparency().updateAll(application)
        //GlanceWidgetUI2_2_transparency().updateAll(application)
        //GlanceWidgetUI2_2_simple().updateAll(application)
        //GlanceWidgetUI2_2_simple_inter().updateAll(application)
        if (!isFastDoubleClick_10s()) {
            if (loadString("ifClickSign", getString(R.string.Yes)) == getString(R.string.Yes)) {
                getString(R.string.refreshAndSign).toast()
                SignAll()
            } else {
                getString(R.string.refresh).toast()
            }

            val intent = Intent(application, WakeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            application.startActivity(intent)
        } else {
            getString(R.string.refreshWait10s).toast()
        }

        //GlanceWidgetUI4_2().updateAll(application)
    }
}
