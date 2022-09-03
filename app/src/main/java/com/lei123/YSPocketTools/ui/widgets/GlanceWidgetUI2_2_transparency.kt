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

class GlanceWidgetUI2_2_transparency : GlanceAppWidget() {
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
            ExpAim
        } else {
            "Bug"
        }

        var itemText2 = if (whichItem == "resin") {
            resinAim
        } else if (whichItem == "homecoin") {
            homeCoinAim
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

        //Zoom = 1f
        Box(
            modifier = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                GlanceModifier
                    .fillMaxSize()
                    .clickable(onClick = actionRunCallback<ResetAction>())
                    .cornerRadius((15 * Zoom).dp)
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
            Column(
                GlanceModifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (timeShow) {
                    nickNameItem(usernickname, drawColorR, drawColorG, drawColorB,0.75f,8, timeShow, Zoom)
                }
                transparencyTitle(
                    item,
                    Zoom,
                    timeShow,
                    drawColor,
                    usernickname,
                    drawColorR,
                    drawColorG,
                    drawColorB
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = itemText,
                        modifier = GlanceModifier
                            .padding(top = (2 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(drawColor),
                            fontSize = (25 * Zoom).sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
                if (itemText2 != "") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = itemText2,
                            modifier = GlanceModifier
                                .padding(top = (2 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(drawColor),
                                fontSize = (12 * Zoom).sp,
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun transparencyTitle(
        item: Int,
        Zoom: Float,
        timeShow: Boolean,
        drawColor: Color,
        usernickname: String,
        drawColorR: Float,
        drawColorG: Float,
        drawColorB: Float
    ) {
        Row(
            GlanceModifier.clickable(onClick = actionRunCallback<uidSetting>()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                provider = ImageProvider(item),
                contentDescription = "",
                modifier = GlanceModifier.size((30 * Zoom).dp),
                contentScale = ContentScale.Fit,
            )
            if (timeShow) {
                Text(
                    text = loadString("time", getString(R.string.timer2)),
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    style = TextStyle(
                        color = ColorProvider(drawColor),
                        fontSize = (18 * Zoom).sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            } else {
                nickNameItem(usernickname, drawColorR, drawColorG, drawColorB, 1f, 10, timeShow, Zoom)
            }
        }
    }

    @Composable
    private fun nickNameItem(
        usernickname: String,
        drawColorR: Float,
        drawColorG: Float,
        drawColorB: Float,
        Alpha: Float,
        size: Int,
        timeShow: Boolean,
        Zoom: Float
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (timeShow) getString(R.string.yeming) + usernickname else usernickname,
                style = TextStyle(
                    color = ColorProvider(
                        Color(
                            drawColorR,
                            drawColorG,
                            drawColorB,
                            Alpha
                        )
                    ),
                    fontSize = (size * Zoom).sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}
