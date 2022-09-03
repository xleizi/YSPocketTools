package com.lei123.YSPocketTools.ui.widgets

import android.graphics.Bitmap
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.action.clickable
import androidx.glance.appwidget.*
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.UNIT.getCircleDarkProgressBar
import com.lei123.YSPocketTools.UNIT.getCircleLightProgressBar
import com.lei123.YSPocketTools.UNIT.getLineDarkProgressBar
import com.lei123.YSPocketTools.UNIT.getLineLightProgressBar
import com.lei123.YSPocketTools.ui.widgets.widgetUI_item.dailyTask_4_2
import com.lei123.YSPocketTools.ui.widgets.widgetUI_item.homeCoinWidget_4_2
import com.lei123.YSPocketTools.ui.widgets.widgetUI_item.homeCoinWidget_4_2_horizontal
import com.lei123.YSPocketTools.ui.widgets.widgetUI_item.resinWidget_4_2
import com.lei123.YSPocketTools.ui.widgets.widgetUI_item.resinWidget_4_2_horizontal
import com.lei123.YSPocketTools.ui.widgets.widgetUI_item.trans_4_2
import com.lei123.YSPocketTools.ui.widgets.widgetUI_item.weekly_4_2
import com.lei123.YSPocketTools.ui.widgets.widgetUI_title.widget_4_2_Title
import com.lei123.YSPocketTools.utils.*

class GlanceWidgetUI4_2_complex_horizontal : GlanceAppWidget() {
    @Composable
    override fun Content() {
        //GlanceAppWidgetManager(application)
        Widget4_2()
    }

    override val sizeMode: SizeMode
        get() = super.sizeMode


    @Composable
    fun Widget4_2() {
        val id = loadString("widgetID")
        val uid = loadString(id)

        var Alpha = loadInt(id + "Alpha", 0)
        var Zoom = loadFloat(id + "Zoom", 1f)
        var theme = loadString(id + "Theme", "Light")

        val usernickname = loadString(uid + "usernickname", getString(R.string.pleaseLogin2))
        val level = loadString(uid + "level", "60")

        val current_Resin = loadString(uid + "current_Resin", "120")
        val max_resin = loadString(uid + "max_resin", "160")
        val resinAim = loadString(uid + "resinAim", "20日 23:59")
        var rep = ((current_Resin.toFloat() / max_resin.toFloat()) * 100).toInt()
        var resinPoint = "1"
        if (rep in 0..19) {
            resinPoint = "0"
        } else if (rep in 20..39) {
            resinPoint = "1"
        } else if (rep in 40..59) {
            resinPoint = "2"
        } else if (rep in 60..79) {
            resinPoint = "3"
        } else if (rep in 80..99) {
            resinPoint = "4"
        } else {
            resinPoint = "5"
        }

        val current_home_coin = loadString(uid + "current_home_coin", "1200")
        val max_home_coin = loadString(uid + "max_home_coin", "2400")
        val homeCoinAim = loadString(uid + "homeCoinAim", "20日 23:59")
        var homep = ((current_home_coin.toFloat() / max_home_coin.toFloat()) * 100).toInt()
        var homeCoinPoint = "1"
        if (homep in 0..19) {
            homeCoinPoint = "0"
        } else if (rep in 20..39) {
            homeCoinPoint = "1"
        } else if (rep in 40..59) {
            homeCoinPoint = "2"
        } else if (rep in 60..79) {
            homeCoinPoint = "3"
        } else if (rep in 80..99) {
            homeCoinPoint = "4"
        } else {
            homeCoinPoint = "5"
        }

        val dailyTask = loadString(uid + "dailyTask", "0")
        val total_task_num = loadString(uid + "total_task_num", "4")
        val remainTask = (total_task_num.toInt() - dailyTask.toInt()).toString()
        val dailyTaskSubmit =
            loadString(uid + "dailyTaskSubmit", getString(R.string.dailyTaskUnSubmit))
        val dailyTask1 = loadBoolean(uid + "dailyTask1", false)
        val dailyTask2 = loadBoolean(uid + "dailyTask2", false)
        val dailyTask3 = loadBoolean(uid + "dailyTask3", false)
        val dailyTask4 = loadBoolean(uid + "dailyTask4", false)

        val weekly = loadString(uid + "weekly", "2")
        val weeklyStr = loadString(uid + "weeklyStr", "2/3")
        val weekly1 = loadBoolean(uid + "weekly1", false)
        val weekly2 = loadBoolean(uid + "weekly2", false)
        val weekly3 = loadBoolean(uid + "weekly3", false)

        val ExpRemain = loadString(uid + "ExpRemain", "还需，12时30分")
        val explorePhotoUrlZheng1 = loadString(uid + "explorePhotoUrlZheng1", "Empty")
        val explorePhotoUrlZheng2 = loadString(uid + "explorePhotoUrlZheng2", "Empty")
        val explorePhotoUrlZheng3 = loadString(uid + "explorePhotoUrlZheng3", "Empty")
        val explorePhotoUrlZheng4 = loadString(uid + "explorePhotoUrlZheng4", "Empty")
        val explorePhotoUrlZheng5 = loadString(uid + "explorePhotoUrlZheng5", "Empty")
        val explorePhotoUrl1 = loadString(uid + "explorePhotoUrl1", "Empty")
        val explorePhotoUrl2 = loadString(uid + "explorePhotoUrl2", "Empty")
        val explorePhotoUrl3 = loadString(uid + "explorePhotoUrl3", "Empty")
        val explorePhotoUrl4 = loadString(uid + "explorePhotoUrl4", "Empty")
        val explorePhotoUrl5 = loadString(uid + "explorePhotoUrl5", "Empty")
        val explore1 = loadString(uid + "explore1", "0")
        val explore2 = loadString(uid + "explore2", "0")
        val explore3 = loadString(uid + "explore3", "0")
        val explore4 = loadString(uid + "explore4", "0")
        val explore5 = loadString(uid + "explore5", "0")

        val role1 = imageUrlToBitmap(explorePhotoUrl1)
        val role2 = imageUrlToBitmap(explorePhotoUrl2)
        val role3 = imageUrlToBitmap(explorePhotoUrl3)
        val role4 = imageUrlToBitmap(explorePhotoUrl4)
        var role5 = imageUrlToBitmap(explorePhotoUrl5)
/*        if (role1 != null){
            Log.i("role1", "role1!=null")
        }else{
            Log.i("role1", "role1==null")
        }
        if (role5 != null){
            Log.i("role5", "role5!=null")
        }else{
            Log.i("role5", "role5==null")
        }*/

        val transRemainSecond = loadString(uid + "transRemainSecond", "0")
        val transday = loadString(uid + "transday", "0")
        //val transobtained = loadBoolean(uid + "transobtained", true)
        val transviable = loadBoolean(uid + "transviable", true)
        var remainDaily = transRemainSecond.toInt() / 86400


        //Log.i("transRemain", transRemain)
        //Zoom = 1f
        /*Log.i("widgetUid", uid)
Log.i("resin", resin)
Log.i("resin", loadString(uid + "ExpAim"))
Log.i("resin", loadString(uid + "explore3"))
Log.i("resin", loadString(uid + "exploreState1"))
Log.i("resin", loadString(uid + "exploreNamelocal2"))
Log.i("sizeMode", sizeMode.toString())*/
        //theme = "Dark"

        var background = R.drawable.w_dark_c_bg_4_2
        var title_short = R.drawable.w_dark_c_title_short
        var title_middle = R.drawable.w_dark_c_title_middle
        var title_long = R.drawable.w_dark_c_title_long
        var main_thin = R.drawable.w_dark_c_back_bar_thin
        var main_long = R.drawable.w_dark_c_back_bar_long
        var main_short = R.drawable.w_dark_c_back_bar_short
        var line = R.drawable.w_dark_c_line_slide
        var imagesandClockEmpty = R.drawable.w_icon_dark_sandclock_empty
        var imagesandClockFull = R.drawable.w_icon_dark_sandclock_full
        var titleColorMajor = Color(218, 205, 186, 255)
        var titleColorSub = Color(174, 160, 128, 255)
        var titleColorWarm = Color(49, 209, 84, 255)
        var resinBar = getLineDarkProgressBar(current_Resin, max_resin)
        var homecoinBar = getLineDarkProgressBar(current_home_coin, max_home_coin)

        var circlePb1 = getCircleDarkProgressBar(explore1, "72000")
        var circlePb2 = getCircleDarkProgressBar(explore2, "72000")
        var circlePb3 = getCircleDarkProgressBar(explore3, "72000")
        var circlePb4 = getCircleDarkProgressBar(explore4, "72000")
        var circlePb5 = getCircleDarkProgressBar(explore5, "72000")


        var pointOn = R.drawable.w_dark_point_warn
        var pointOff = R.drawable.w_dark_point_off

        if (theme == "Light") {
            background = R.drawable.w_light_c_bg_4_2
            title_short = R.drawable.w_light_c_title_short
            title_middle = R.drawable.w_light_c_title_middle
            title_long = R.drawable.w_light_c_title_long
            main_thin = R.drawable.w_light_c_back_bar_thin
            main_long = R.drawable.w_light_c_back_bar_long
            main_short = R.drawable.w_light_c_back_bar_short
            line = R.drawable.w_light_c_line_slide
            imagesandClockEmpty = R.drawable.w_icon_light_sandclock_empty
            imagesandClockFull = R.drawable.w_icon_light_sandclock_full
            pointOn = R.drawable.w_light_point_warn
            pointOff = R.drawable.w_light_point_off

            resinBar = getLineLightProgressBar(current_Resin, max_resin)
            homecoinBar = getLineLightProgressBar(current_home_coin, max_home_coin)
            circlePb1 = getCircleLightProgressBar(explore1, "72000")
            circlePb2 = getCircleLightProgressBar(explore2, "72000")
            circlePb3 = getCircleLightProgressBar(explore3, "72000")
            circlePb4 = getCircleLightProgressBar(explore4, "72000")
            circlePb5 = getCircleLightProgressBar(explore5, "72000")

            titleColorMajor = Color(75, 85, 102, 255)
            titleColorSub = Color(174, 160, 128, 255)
            titleColorWarm = Color(255, 121, 74, 255)
        } else {

        }
        var imageResinsandClock = imagesandClockEmpty
        var resinText = titleColorMajor
        if ((current_Resin.toFloat() / max_resin.toFloat()) * 100 > 95) {
            imageResinsandClock = imagesandClockFull
            resinText = titleColorWarm
        }

        var imageHomeCoinsandClock = imagesandClockEmpty
        var homeCoinText = titleColorMajor
        if ((current_home_coin.toFloat() / max_home_coin.toFloat()) * 100 > 95) {
            imageHomeCoinsandClock = imagesandClockFull
            homeCoinText = titleColorWarm
        }

        //Log.i("resin", theme)

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(255, 255, 255, Alpha)),

            //.background(day = Color.White, night = Color.Blue)
            //.cornerRadius((10*Zoom).dp)
            //.padding((2*Zoom).dp)
            //.clickable(onClick = actionRunCallback<ResetAction>()),
            contentAlignment = Alignment.Center
        ) {
            Box(
                GlanceModifier
                    .size(width = (320 * Zoom).dp, height = (158 * Zoom).dp),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    provider = ImageProvider(background),
                    modifier = GlanceModifier.clickable(onClick = actionRunCallback<ResetAction>()),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Column {
                    widget_4_2_Title(
                        Zoom,
                        title_long,
                        titleColorMajor,
                        title_middle,
                        usernickname,
                        level,
                        title_short
                    )
                    Row(
                        GlanceModifier
                            .padding(top = (5 * Zoom).dp, start = (10 * Zoom).dp)
                            .height((124 * Zoom).dp)
                            .background(Color.Unspecified)
                        //.clickable(onClick = actionRunCallback<ResetAction>())
                        //.size(height = 119.dp, width = 80.dp)
                    ) {
                        Column {
                            Row(
                                GlanceModifier
                                    .height((57 * Zoom).dp)
                            ) {
                                resinWidget_4_2_horizontal(
                                    Zoom,
                                    main_long,
                                    titleColorSub,
                                    current_Resin,
                                    titleColorMajor,
                                    resinPoint,
                                    max_resin,
                                    resinAim,
                                    resinText,
                                    pointOn,
                                    pointOff
                                )
                            }
                            Spacer(modifier = GlanceModifier.height((6 * Zoom).dp))
                            homeCoinWidget_4_2_horizontal(
                                Zoom,
                                main_long,
                                titleColorSub,
                                current_home_coin,
                                titleColorMajor,
                                homeCoinPoint,
                                max_home_coin,
                                homeCoinAim,
                                homeCoinText,
                                pointOn,
                                pointOff
                            )
                        }

                        Column(
                            GlanceModifier
                                .width((162 * Zoom).dp)
                                .padding(start = (6 * Zoom).dp),
                        ) {
                            Row(
                                GlanceModifier
                                    .height((57 * Zoom).dp)
                            ) {
                                dailyTask_4_2(
                                    Zoom,
                                    main_short,
                                    titleColorSub,
                                    dailyTaskSubmit,
                                    titleColorMajor,
                                    remainTask,
                                    dailyTask1,
                                    pointOn,
                                    pointOff,
                                    dailyTask2,
                                    dailyTask3,
                                    dailyTask4
                                )
                                weekly_4_2(
                                    Zoom,
                                    main_short,
                                    titleColorSub,
                                    weeklyStr,
                                    titleColorMajor,
                                    weekly,
                                    weekly3,
                                    pointOn,
                                    pointOff,
                                    weekly2,
                                    weekly1
                                )
                                trans_4_2(
                                    Zoom,
                                    main_short,
                                    titleColorSub,
                                    titleColorMajor,
                                    transday,
                                    transviable,
                                    pointOn,
                                    pointOff
                                )
                            }
                            Box(
                                GlanceModifier
                                    .height((62 * Zoom).dp)
                                    .padding(top = (6 * Zoom).dp)
                            ) {
                                Image(
                                    provider = ImageProvider(main_long),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds,
                                )
                                Row(
                                    modifier = GlanceModifier
                                        .fillMaxWidth()
                                        .padding(top = (2 * Zoom).dp, start = (5 * Zoom).dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Image(
                                        provider = ImageProvider(R.drawable.w_icon_exp),
                                        modifier = GlanceModifier.size((22 * Zoom).dp),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds,
                                    )
                                    Text(
                                        text = getString(R.string.exploreTitle),
                                        style = TextStyle(
                                            color = ColorProvider(titleColorMajor),
                                            fontSize = (8 * Zoom).sp,
                                        ),
                                    )
                                    Row(
                                        modifier = GlanceModifier
                                            .fillMaxWidth()
                                            .padding(end = (5 * Zoom).dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Image(
                                            provider = ImageProvider(if (ExpRemain == getString(R.string.exploreAccomplish)) imagesandClockFull else imagesandClockEmpty),
                                            modifier = GlanceModifier.size((10 * Zoom).dp),
                                            contentDescription = "",
                                            contentScale = ContentScale.FillBounds,
                                        )
                                        Text(
                                            text = ExpRemain,
                                            style = TextStyle(
                                                color = ColorProvider(resinText),
                                                fontSize = (6 * Zoom).sp,
                                            ),
                                        )
                                    }
                                }
                                Row(
                                    GlanceModifier
                                        .padding(
                                            top = (20 * Zoom).dp,
                                            start = (2 * Zoom).dp,
                                            end = (2 * Zoom).dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    if (role1 != null) {
                                        Box(
                                            GlanceModifier.defaultWeight()
                                        ) {
                                            roleItem(Zoom, role1, circlePb1)
                                        }
                                    }
                                    if (role2 != null) {
                                        Box(
                                            GlanceModifier.defaultWeight()
                                        ) {
                                            roleItem(Zoom, role2, circlePb2)
                                        }
                                    }
                                    if (role3 != null) {
                                        Box(
                                            GlanceModifier.defaultWeight()
                                        ) {
                                            roleItem(Zoom, role3, circlePb3)
                                        }
                                    }
                                    if (role4 != null) {
                                        Box(
                                            GlanceModifier.defaultWeight()
                                        ) {
                                            roleItem(Zoom, role4, circlePb4)
                                        }
                                    }
                                    if (role5 != null) {
                                        Box(
                                            GlanceModifier.defaultWeight()
                                        ) {
                                            roleItem(Zoom, role5, circlePb5)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun roleItem(
        Zoom: Float,
        role: Bitmap,
        circlePb: Int
    ) {
        Box(
            modifier = GlanceModifier.size((35 * Zoom).dp)
        ) {
            Image(
                provider = ImageProvider(circlePb),
                modifier = GlanceModifier
                    //.size((35 * Zoom).dp)
                    .padding(top = (7 * Zoom).dp, start = (3 * Zoom).dp, end = (4 * Zoom).dp),
                contentDescription = "avatar",
                contentScale = ContentScale.Fit,
            )
            Image(
                provider = ImageProvider(role),
                modifier = GlanceModifier
                    //.size((35 * Zoom).dp)
                    .padding(horizontal = (4 * Zoom).dp, vertical = (4 * Zoom).dp),
                contentDescription = "avatar",
                contentScale = ContentScale.Fit,
            )
        }
    }
}

