package com.lei123.YSPocketTools.ui.widgets

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.action.clickable
import androidx.glance.appwidget.*
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.lei123.YSPocketTools.AndroidTools.TimeCounter
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.UNIT.getCircleSimpleDarkProgressBar
import com.lei123.YSPocketTools.UNIT.getCircleSimpleLightProgressBar
import com.lei123.YSPocketTools.UNIT.getItemImage
import com.lei123.YSPocketTools.utils.*

class GlanceWidgetUI2_2_simple_inter : GlanceAppWidget() {
    @Composable
    override fun Content() {
        //GlanceAppWidgetManager(application)
        Widget2_2_transparency()
    }

    override val sizeMode: SizeMode
        get() = super.sizeMode


    @Composable
    fun Widget2_2_transparency() {
        val id = loadString("widgetID")
        val uid = loadString(id)

        var Alpha = loadInt(id + "Alpha", 0)
        var Zoom = loadFloat(id + "Zoom", 1f)
        var whichItem = loadString(id + "whichItem", "resin")
        var timeShow = loadBoolean(id + "timeShow", true)
        var nicknameShow = loadBoolean(id + "nicknameShow", true)
        var iconStyle = loadString(id + "iconStyle", "simple")
        var simpleitem1 = loadString(id + "simpleitem1", "resin")
        var simpleitem2 = loadString(id + "simpleitem2", "homecoin")
        var simpleitem3 = loadString(id + "simpleitem3", "daily")
        var simpleitem4 = loadString(id + "simpleitem4", "explore")

        val current_Resin = loadString(uid + "current_Resin", "120")
        val max_resin = loadString(uid + "max_resin", "160")
        val current_home_coin = loadString(uid + "current_home_coin", "2000")
        val max_home_coin = loadString(uid + "max_home_coin", "2400")
        val dailyTask = loadString(uid + "dailyTask", "2")
        val total_task_num = loadString(uid + "total_task_num", "4")
        val dailyTaskSubmit =
            loadString(uid + "dailyTaskSubmit", getString(R.string.dailyTaskUnSubmit))

        val dailyTaskpussed = (total_task_num.toInt() - dailyTask.toInt()).toString()

        val weekly = loadString(uid + "weekly", "2")
        val weeklyall = loadString(uid + "weeklyall", "3")
        val transRemainSecond = loadString(uid + "transRemainSecond", "100000")
        val transTotalecond = "604800"
        val transpussed = (604800 - transRemainSecond.toInt()).toString()

        val explore1 = loadString(uid + "explore1", "0")
        val explore2 = loadString(uid + "explore2", "0")
        val explore3 = loadString(uid + "explore3", "0")
        val explore4 = loadString(uid + "explore4", "0")
        val explore5 = loadString(uid + "explore5", "0")
        var maxExpTime = explore1.toInt()
        if (explore2.toInt() > maxExpTime) {
            maxExpTime = explore2.toInt()
        }
        if (explore3.toInt() > maxExpTime) {
            maxExpTime = explore3.toInt()
        }
        if (explore4.toInt() > maxExpTime) {
            maxExpTime = explore4.toInt()
        }
        if (explore5.toInt() > maxExpTime) {
            maxExpTime = explore5.toInt()
        }
        val Exppussed = (72000 - maxExpTime).toString()

        var pb3 = R.drawable.circle_miui_dark_pd0
        var pb1 = getCircleSimpleDarkProgressBar(current_Resin, max_resin)
        var pb2 = getCircleSimpleDarkProgressBar(current_home_coin, max_home_coin)
        if (dailyTaskSubmit == getString(R.string.dailyTaskUnSubmit)) pb3 = getCircleSimpleDarkProgressBar(dailyTaskpussed, total_task_num)
        var pb4 = getCircleSimpleDarkProgressBar(weekly, weeklyall)
        var pb5 = getCircleSimpleDarkProgressBar(transpussed, "604800")
        var pb6 = getCircleSimpleDarkProgressBar(Exppussed, "72000")
        var theme = loadString(id + "Theme", "Light")

        val image1 = getItemImage(simpleitem1,iconStyle,theme)
        val image2 = getItemImage(simpleitem2,iconStyle,theme)
        val image3 = getItemImage(simpleitem3,iconStyle,theme)
        val image4 = getItemImage(simpleitem4,iconStyle,theme)

        var background = R.drawable.w_simple_dark_bg

        //theme = "Dark"
        if (theme == "Light") {
            background = R.drawable.w_simple_light_bg
            pb3 = R.drawable.circle_miui_light_pd0
            pb1 = getCircleSimpleLightProgressBar(current_Resin, max_resin)
            pb2 = getCircleSimpleLightProgressBar(current_home_coin, max_home_coin)
            if (dailyTaskSubmit == getString(R.string.dailyTaskUnSubmit)) pb3 = getCircleSimpleLightProgressBar(dailyTaskpussed, total_task_num)
            pb4 = getCircleSimpleLightProgressBar(weekly, weeklyall)
            pb5 = getCircleSimpleLightProgressBar(transpussed, "604800")
            pb6 = getCircleSimpleLightProgressBar(Exppussed, "72000")
        }
        val itempb1 = when (simpleitem1) {
            "resin" -> pb1
            "homecoin" -> pb2
            "daily" -> pb3
            "weekly" -> pb4
            "transfor" -> pb5
            "explore" -> pb6
            else -> pb1
        }
        val itempb2 = when (simpleitem2) {
            "resin" -> pb1
            "homecoin" -> pb2
            "daily" -> pb3
            "weekly" -> pb4
            "transfor" -> pb5
            "explore" -> pb6
            else -> pb1
        }
        val itempb3 = when (simpleitem3) {
            "resin" -> pb1
            "homecoin" -> pb2
            "daily" -> pb3
            "weekly" -> pb4
            "transfor" -> pb5
            "explore" -> pb6
            else -> pb1
        }
        val itempb4 = when (simpleitem4) {
            "resin" -> pb1
            "homecoin" -> pb2
            "daily" -> pb3
            "weekly" -> pb4
            "transfor" -> pb5
            "explore" -> pb6
            else -> pb1
        }
        //whichItem = "homecoin"

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(255, 255, 255, Alpha)),
            contentAlignment = Alignment.Center
        )
        {
            Box(
                GlanceModifier
                    .size(width = (160 * Zoom).dp, height = (160 * Zoom).dp),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    provider = ImageProvider(background),
                    modifier = GlanceModifier.clickable(onClick = actionRunCallback<ResetAction>()),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Column(
                    GlanceModifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        itemBar(itempb1, image1, Zoom)
                        itemBar(itempb2, image2, Zoom)
                    }
                    Row() {
                        itemBar(itempb3, image3, Zoom)
                        itemBar(itempb4, image4, Zoom)
                    }
                }
            }
        }
    }
}

@Composable
private fun itemBar(progressBar: Int, item: Int, Zoom: Float) {
    Box(
        modifier = GlanceModifier.padding(vertical = (3 * Zoom).dp, horizontal = (3 * Zoom).dp)
    ) {
        Image(
            provider = ImageProvider(progressBar),
            modifier = GlanceModifier
                .height((65 * Zoom).dp)
                .width((65 * Zoom).dp),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
        Image(
            provider = ImageProvider(item),
            modifier = GlanceModifier
                .height((65 * Zoom).dp)
                .width((65 * Zoom).dp)
                .clickable(onClick = actionRunCallback<uidSettingsimple>())
                .padding(horizontal = (20 * Zoom).dp, vertical = (20 * Zoom).dp),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
    }
}
