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
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.lei123.YSPocketTools.AndroidTools.TimeCounter
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.UNIT.getLineDarkProgressBar
import com.lei123.YSPocketTools.UNIT.getLineLightProgressBar
import com.lei123.YSPocketTools.utils.*

class GlanceWidgetUI2_2_simple : GlanceAppWidget() {
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

        val usernickname = loadString(uid + "usernickname", getString(R.string.pleaseLogin2))

        val current_Resin = loadString(uid + "current_Resin", "120")
        val max_resin = loadString(uid + "max_resin", "160")
        val resinAim = loadString(uid + "resinAim", "20日 23:59")

        val current_home_coin = loadString(uid + "current_home_coin", "2000")
        val max_home_coin = loadString(uid + "max_home_coin", "2400")
        val homeCoinAim = loadString(uid + "homeCoinAim", "20日 23:59")

        val dailyTask = loadString(uid + "dailyTask", "2")
        val total_task_num = loadString(uid + "total_task_num", "4")
        val dailyTaskSubmit =
            loadString(uid + "dailyTaskSubmit", getString(R.string.dailyTaskUnSubmit))

        val weekly = loadString(uid + "weekly", "2")
        val weeklyall = loadString(uid + "weeklyall", "3")
        val weeklyStr = loadString(uid + "weeklyStr", "2/3")

        val transRemainSecond = loadString(uid + "transRemainSecond", "100000")
        val transRemain = loadString(uid + "transRemain", "三天")
        val transviable = loadBoolean(uid + "transviable", false)

        val current_expedition_num = loadString(uid + "current_expedition_num", "0")
        val max_expedition_num = loadString(uid + "max_expedition_num", "5")
        val ExpAim = loadString(uid + "ExpAim", "20日 23:59")

        var theme = loadString(id + "Theme", "Light")
        var background = R.drawable.w_simple_dark_bg
        var circle = R.drawable.w_simple_dark_circle
        var mainText = Color.White
        var subText = Color.Gray



        if (theme == "Light") {
            background = R.drawable.w_simple_light_bg
            circle = R.drawable.w_simple_light_circle
            mainText = Color.Black

        }
        //whichItem = "homecoin"

        var item = if (whichItem == "resin") {
            if (theme == "Light") R.drawable.w_simple_light_resin else R.drawable.w_simple_dark_resin
        } else if (whichItem == "homecoin") {
            if (theme == "Light") R.drawable.w_simple_light_homecoin else R.drawable.w_simple_dark_homecoin
        } else if (whichItem == "daily") {
            if (theme == "Light") R.drawable.w_simple_light_daily else R.drawable.w_simple_dark_daily
        } else if (whichItem == "weekly") {
            if (theme == "Light") R.drawable.w_simple_light_weekly else R.drawable.w_simple_dark_weekly
        } else if (whichItem == "transfor") {
            if (theme == "Light") R.drawable.w_simple_light_trans else R.drawable.w_simple_dark_trans
        } else if (whichItem == "explore") {
            if (theme == "Light") R.drawable.w_simple_light_explore else R.drawable.w_simple_dark_explore
        } else {
            if (theme == "Light") R.drawable.w_simple_light_resin else R.drawable.w_simple_dark_resin
        }

        if (iconStyle=="yuanshen"){
            item = if (whichItem == "resin") {
                R.drawable.icon_resin
            } else if (whichItem == "homecoin") {
                R.drawable.icon_homecoin
            } else if (whichItem == "daily") {
                R.drawable.w_icon_daily
            } else if (whichItem == "weekly") {
                R.drawable.w_icon_sword
            } else if (whichItem == "transfor") {
                R.drawable.icon_transfor
            } else if (whichItem == "explore") {
                R.drawable.w_icon_exp
            } else {
                R.drawable.icon_resin
            }
        }



        var itemText = if (whichItem == "resin") {
            current_Resin
        } else if (whichItem == "homecoin") {
            current_home_coin
        } else if (whichItem == "daily") {
            dailyTask
        } else if (whichItem == "weekly") {
            weekly
        } else if (whichItem == "transfor") {
            TimeCounter.AimTime(transRemainSecond.toInt())
        } else if (whichItem == "explore") {
            current_expedition_num
        } else {
            "Bug"
        }

        var itemText2 = if (whichItem == "resin") {
            "/$max_resin"
        } else if (whichItem == "homecoin") {
            "/$max_home_coin"
        } else if (whichItem == "daily") {
            "/$total_task_num"
        } else if (whichItem == "weekly") {
            "/$weeklyall"
        } else if (whichItem == "transfor") {
            ""
        } else if (whichItem == "explore") {
            "/$max_expedition_num"
        } else {
            "Bug"
        }

        var itemText3 = if (whichItem == "resin") {
            getString(R.string.aimtime) + resinAim
        } else if (whichItem == "homecoin") {
            getString(R.string.aimtime) + homeCoinAim
        } else if (whichItem == "daily") {
            dailyTaskSubmit
        } else if (whichItem == "weekly") {
            "半价周本次数" + if (weeklyStr != "0/3") getString(R.string.unuse) else weeklyStr
        } else if (whichItem == "transfor") {
            if (!transviable) transRemain else getString(R.string.TranAvailable)
        } else if (whichItem == "explore") {
            getString(R.string.aimtime) + ExpAim
        } else {
            "Bug"
        }

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
                    GlanceModifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.Start
                ) {
                    Box {
                        if (nicknameShow) {
                            if (timeShow) {
                                Text(
                                    text = loadString("time", getString(R.string.timer2)),
                                    modifier = GlanceModifier
                                        .width((90 * Zoom).dp)
                                        .padding(
                                            top = (15 * Zoom).dp,
                                            start = (18 * Zoom).dp
                                        ),
                                    style = TextStyle(
                                        color = ColorProvider(subText),
                                        fontSize = (12 * Zoom).sp
                                    ),
                                )
                            }
                        }
                        Box {
                            if (nicknameShow) {
                                Box(
                                    modifier = GlanceModifier
                                        .padding(
                                            top = (3 * Zoom).dp,
                                            start = (15 * Zoom).dp
                                        )
                                ) {
                                    Text(
                                        text = usernickname,
                                        modifier = GlanceModifier
                                            .width((70 * Zoom).dp)
                                            .padding(
                                                top = if(timeShow) (26 * Zoom).dp else (15 * Zoom).dp,
                                                start = (3 * Zoom).dp
                                            ),
                                        style = TextStyle(
                                            color = ColorProvider(subText),
                                            fontSize = if(timeShow) (12 * Zoom).sp else (16 * Zoom).sp
                                        ),
                                    )

                                    Box {
                                        if (iconStyle=="simple"){
                                            Image(
                                                provider = ImageProvider(item),
                                                modifier = GlanceModifier
                                                    .height((80 * Zoom).dp)
                                                    .width((142 * Zoom).dp)
                                                    .padding(top = (0 * Zoom).dp, start = (62 * Zoom).dp)
                                                    .clickable(onClick = actionRunCallback<uidSettingsimple>()),
                                                contentDescription = "",
                                                contentScale = ContentScale.FillBounds,
                                            )
                                        }else{
                                            Image(
                                                provider = ImageProvider(item),
                                                modifier = GlanceModifier
                                                    .height((60 * Zoom).dp)
                                                    .width((127 * Zoom).dp)
                                                    .padding(top = (10 * Zoom).dp, start = (77 * Zoom).dp)
                                                    .clickable(onClick = actionRunCallback<uidSettingsimple>()),
                                                contentDescription = "",
                                                contentScale = ContentScale.FillBounds,
                                            )
                                        }
                                    }
                                }
                            }
                            if (!nicknameShow) {
                                Row(
                                    modifier = GlanceModifier
                                        .padding(
                                            top = (5 * Zoom).dp,
                                            start = (0 * Zoom).dp
                                        )
                                ) {
                                    Box {
                                        if (iconStyle=="simple"){
                                            Image(
                                                provider = ImageProvider(item),
                                                modifier = GlanceModifier
                                                    .size((80 * Zoom).dp)
                                                    .padding(top = (0 * Zoom).dp, start = (0 * Zoom).dp)
                                                    .clickable(onClick = actionRunCallback<uidSettingsimple>()),
                                                contentDescription = "",
                                                contentScale = ContentScale.FillBounds,
                                            )
                                        }else{
                                            Image(
                                                provider = ImageProvider(item),
                                                modifier = GlanceModifier
                                                    .size((60 * Zoom).dp)
                                                    .padding(top = (10 * Zoom).dp, start = (10 * Zoom).dp)
                                                    .clickable(onClick = actionRunCallback<uidSettingsimple>()),
                                                contentDescription = "",
                                                contentScale = ContentScale.FillBounds,
                                            )
                                        }
                                    }
                                    if (timeShow) {
                                        Text(
                                            text = loadString("time", getString(R.string.timer2)),
                                            modifier = GlanceModifier
                                                .width((90 * Zoom).dp)
                                                .padding(
                                                    top = (25 * Zoom).dp,
                                                    start = (0 * Zoom).dp,
                                                    end = (0 * Zoom).dp
                                                ),
                                            style = TextStyle(
                                                color = ColorProvider(subText),
                                                fontSize = (15 * Zoom).sp
                                            ),
                                        )
                                    }
                                }

                            }
                            Row(
                                modifier = GlanceModifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = (81 * Zoom).dp,
                                        start = (8 * Zoom).dp
                                    ),
                                horizontalAlignment = Alignment.Start,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = itemText,
                                    modifier = GlanceModifier
                                        .padding(
                                            top = if (whichItem != "transfor") (0 * Zoom).dp else (10 * Zoom).dp,
                                            start = (5 * Zoom).dp
                                        ),
                                    style = TextStyle(
                                        color = ColorProvider(mainText),
                                        fontSize = if (whichItem != "transfor") (40 * Zoom).sp else (25 * Zoom).sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                                Text(
                                    text = itemText2,
                                    modifier = GlanceModifier
                                        .padding(
                                            top = (0 * Zoom).dp,
                                            start = (0 * Zoom).dp
                                        ),
                                    style = TextStyle(
                                        color = ColorProvider(subText),
                                        fontSize = (16 * Zoom).sp,
                                    ),
                                )
                            }
                            Row(
                                modifier = GlanceModifier
                                    .padding(
                                        top = (128 * Zoom).dp,
                                        start = (13 * Zoom).dp
                                    )
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = itemText3,
                                    modifier = GlanceModifier
                                        .padding(
                                            top = (0 * Zoom).dp,
                                            start = (3 * Zoom).dp
                                        ),
                                    style = TextStyle(
                                        color = ColorProvider(subText),
                                        fontSize = (12 * Zoom).sp
                                    ),
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}
