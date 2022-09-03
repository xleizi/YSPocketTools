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
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.*

class GlanceWidgetUI2_2_complex : GlanceAppWidget() {
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

        val usernickname = loadString(uid + "usernickname", getString(R.string.pleaseLogin2))

        val resinAim = loadString(uid + "resinAim", "20日 23:59")

        val homeCoinAim = loadString(uid + "homeCoinAim", "20日 23:59")

        val dailyTaskSubmit =
            loadString(uid + "dailyTaskSubmit", getString(R.string.dailyTaskUnSubmit))
        val dailyTaskStr = loadString(uid + "dailyTaskStr", "Empty")
        val weeklyStr = loadString(uid + "weeklyStr", "2/3")
        val transRemain = loadString(uid + "transRemain", "0")
        val ExpRemain = loadString(uid + "ExpRemain", "还需，12时30分")
        val ExpAim = loadString(uid + "ExpAim", "Empty")
        val resin1 = loadString(uid + "resin1", "Empty")
        val homeCoin1 = loadString(uid + "homeCoin1", "Empty")

        val drawColorR = loadFloat(id + "drawColorR", 0f)
        val drawColorG = loadFloat(id + "drawColorG", 0f)
        val drawColorB = loadFloat(id + "drawColorB", 0f)
        val drawColorA = loadFloat(id + "drawColorA", 0f)
        val drawColor = Color(drawColorR, drawColorG, drawColorB)

        var theme = loadString(id + "Theme", "Light")
        var background = R.drawable.w_dark_bg_2_2
        var title_left = R.drawable.w_dark_c_title_short
        var title_right = R.drawable.w_dark_small_right
        var contextBar = R.drawable.w_dark_s_back_bar_short
        var arrows = R.drawable.w_dark_arrows


        var titleColorMajor = Color(218, 205, 186, 255)
        var titleColorSub = Color(174, 160, 128, 255)
        var titleColorWarm = Color(49, 209, 84, 255)
        val transviable = loadBoolean(uid + "transviable", true)

        if (theme == "Light") {
            background = R.drawable.w_light_bg_2_2
            title_left = R.drawable.w_light_c_title_short
            title_right = R.drawable.w_light_small_right
            contextBar = R.drawable.w_preview_light_s_back_bar_short
            arrows = R.drawable.w_light_arrows


            titleColorMajor = Color(75, 85, 102, 255)
            titleColorSub = Color(174, 160, 128, 255)
            titleColorWarm = Color(255, 121, 74, 255)
        }

        var item = if (whichItem == "resin") {
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

        var itemText = if (whichItem == "resin") {
            resin1
        } else if (whichItem == "homecoin") {
            homeCoin1
        } else if (whichItem == "daily") {
            dailyTaskSubmit
        } else if (whichItem == "weekly") {
            weeklyStr
        } else if (whichItem == "transfor") {
            if (transviable) getString(R.string.TranAvailable) else transRemain
        } else if (whichItem == "explore") {
            ExpAim
        } else {
            "Bug"
        }

        var itemText2 = if (whichItem == "resin") {
            getString(R.string.aimtime) + resinAim
        } else if (whichItem == "homecoin") {
            getString(R.string.aimtime) + homeCoinAim
        } else if (whichItem == "daily") {
            dailyTaskStr
        } else if (whichItem == "weekly") {
            ""
        } else if (whichItem == "transfor") {
            ""
        } else if (whichItem == "explore") {
            ExpRemain
        } else {
            "Bug"
        }

        var itemText3 = if (whichItem == "resin") {
            getString(R.string.resinTitle)
        } else if (whichItem == "homecoin") {
            getString(R.string.homeCoinTitle)
        } else if (whichItem == "daily") {
            getString(R.string.dailyTaskTitle)
        } else if (whichItem == "weekly") {
            getString(R.string.weeklyTitle)
        } else if (whichItem == "transfor") {
            getString(R.string.transTitle)
        } else if (whichItem == "explore") {
            getString(R.string.exploreTitle)
        } else {
            "Bug"
        }

        //Zoom = 1f
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(255, 255, 255, Alpha)),
            contentAlignment = Alignment.Center
        )
        {
            Box(
                GlanceModifier
                    .size(width = (168 * Zoom).dp, height = (168 * Zoom).dp),
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
                    widgetSmallTopbar(Zoom, title_left, usernickname, titleColorMajor, title_right)
                    Row(
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(top = (8 * Zoom).dp, start = (20 * Zoom).dp)
                            .clickable(onClick = actionRunCallback<uidSetting>()),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Image(
                            provider = ImageProvider(item),
                            contentDescription = "",
                            modifier = GlanceModifier
                                .size((36 * Zoom).dp),
                            contentScale = ContentScale.FillBounds,
                        )
                        Text(
                            text = itemText3,
                            modifier = GlanceModifier
                                .padding(start = (5 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorSub),
                                fontSize = (18 * Zoom).sp
                            ),
                        )
                    }
                    Row(
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(
                                top = (10 * Zoom).dp,
                                start = (20 * Zoom).dp,
                                end = (20 * Zoom).dp
                            )
                            .height((53 * Zoom).dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = GlanceModifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                provider = ImageProvider(contextBar),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                            )
                            Text(
                                text = itemText,
                                style = TextStyle(
                                    color = ColorProvider(titleColorMajor),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = (23 * Zoom).sp
                                ),
                            )
                        }
                    }
                    Row(
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(
                                top = (8 * Zoom).dp,
                                start = (15 * Zoom).dp,
                                end = (15 * Zoom).dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = itemText2,
                            modifier = GlanceModifier
                                .padding(start = (5 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorSub),
                                fontSize = (10 * Zoom).sp
                            ),
                        )
                        Spacer(GlanceModifier.defaultWeight())
                        Image(
                            provider = ImageProvider(arrows),
                            modifier = GlanceModifier.size((20 * Zoom).dp),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun widgetSmallTopbar(
        Zoom: Float,
        title_left: Int,
        usernickname: String,
        titleColorMajor: Color,
        title_right: Int
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(top = (14 * Zoom).dp, start = (16 * Zoom).dp)
                .height(height = (29 * Zoom).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                GlanceModifier
                    .width((69 * Zoom).dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(title_left),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = usernickname,
                    style = TextStyle(
                        color = ColorProvider(titleColorMajor),
                        fontWeight = FontWeight.Bold,
                        fontSize = (7 * Zoom).sp,
                    ),
                    //modifier = GlanceModifier.padding(start = (0 * Zoom).dp)
                )
            }
            Box(
                GlanceModifier
                    .width((65 * Zoom).dp)
                    .padding(start = (3 * Zoom).dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(title_right),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = loadString("time", getString(R.string.timer2)),
                    style = TextStyle(
                        color = ColorProvider(titleColorMajor),
                        fontWeight = FontWeight.Bold,
                        fontSize = (7 * Zoom).sp,
                    ),
                    modifier = GlanceModifier.padding(start = (15 * Zoom).dp)
                )
            }
        }
    }

}
