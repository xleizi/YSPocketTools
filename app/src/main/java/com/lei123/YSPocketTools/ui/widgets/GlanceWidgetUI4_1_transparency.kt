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

class GlanceWidgetUI4_1_transparency : GlanceAppWidget() {
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
        val transviable = loadBoolean(uid + "transviable", true)

        val drawColorR = loadFloat(id + "drawColorR", 0f)
        val drawColorG = loadFloat(id + "drawColorG", 0f)
        val drawColorB = loadFloat(id + "drawColorB", 0f)
        val drawColorA = loadFloat(id + "drawColorA", 0f)
        val drawColor = Color(drawColorR, drawColorG, drawColorB)

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
            ExpRemain
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
            getString(R.string.aimtime) + ExpAim
        } else {
            "Bug"
        }

        //Zoom = 1f
        Box(
            modifier = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                GlanceModifier
                    .fillMaxSize()
                    .clickable(onClick = actionRunCallback<ResetAction>())
                    .cornerRadius((10 * Zoom).dp)
                    .background(Color(255, 255, 255, Alpha))
            } else {
                GlanceModifier
                    .fillMaxSize()
                    .clickable(onClick = actionRunCallback<ResetAction>())
                    .background(Color(255, 255, 255, Alpha))
            },
            contentAlignment = Alignment.Center
        )
        {
            Row(
                GlanceModifier
                    .fillMaxWidth()
                    .padding(start = (20 * Zoom).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    provider = ImageProvider(item),
                    contentDescription = "",
                    modifier = GlanceModifier
                        .size((45 * Zoom).dp)
                        .clickable(onClick = actionRunCallback<uidSetting>()),
                    contentScale = ContentScale.FillBounds,
                )
                Column {
                    Text(
                        text = itemText,
                        modifier = GlanceModifier
                            .padding(top = (2 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(drawColor),
                            fontSize = (18 * Zoom).sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                    Text(
                        text = itemText2,
                        modifier = GlanceModifier
                            .padding(top = (0 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(drawColor),
                            fontSize = (14 * Zoom).sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
                Column(
                    GlanceModifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(end = (20 * Zoom).dp),
                    horizontalAlignment = Alignment.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = loadString("time", getString(R.string.timer2)),
                        modifier = GlanceModifier
                            .padding(bottom = (9 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(drawColor),
                            fontSize = (13 * Zoom).sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
            }
        }
    }
}
