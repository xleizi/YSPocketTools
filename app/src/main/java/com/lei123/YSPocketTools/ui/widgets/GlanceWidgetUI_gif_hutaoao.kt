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
import com.lei123.YSPocketTools.AndroidTools.getImageId
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.*

class GlanceWidgetUI_gif_hutaoao : GlanceAppWidget() {
    @Composable
    override fun Content() {
        Widget2_2_transparency()
    }

    override val sizeMode: SizeMode
        get() = super.sizeMode


    @Composable
    fun Widget2_2_transparency() {
        val id = loadString("widgetID")
        val uid = loadString(id)
        val frame = loadInt(id + "frame", 1)

        val imageName = "w_gif_hutaoao_${frame}"
        val image = getImageId(imageName)

        var Zoom = loadFloat(id + "Zoom", 1f)
        //Zoom = 1f
        val resinIcon = R.drawable.icon_resin
        val resinText = loadString(uid + "resin1", "Empty")
        val resinText2 = loadString(uid + "resinAim", "20日 23:59")

        val ExpIcon = R.drawable.w_icon_exp
        val ExpRemain = loadString(uid + "ExpRemain", "还需，12时30分")
        val ExpAim = loadString(uid + "ExpAim", "Empty")

        val titleColorMajor = Color(75, 85, 102, 255)

        //val Alpha = 200
        //Zoom = 1f
        Box(
            modifier = GlanceModifier
                //.background(Color.Green)
                //.background(Color(255, 255, 255, Alpha))
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = GlanceModifier
                    .size(width = (320 * Zoom).dp, height = (158 * Zoom).dp),
            ) {
                Image(
                    provider = ImageProvider(R.drawable.w_gif_hutaoao_background),
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .clickable(onClick = actionRunCallback<ResetAction>()),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
            }
            Row(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .padding(start = (20 * Zoom).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = GlanceModifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom,
                    ) {
                    Row {
                        Image(
                            provider = ImageProvider(resinIcon),
                            contentDescription = "",
                            modifier = GlanceModifier
                                .size((45 * Zoom).dp)
                                .clickable(onClick = actionRunCallback<uidSetting>()),
                            contentScale = ContentScale.FillBounds,
                        )
                        Column(
                            modifier = GlanceModifier
                                .padding(top = (2 * Zoom).dp),
                        ) {
                            Text(
                                text = resinText,
                                style = TextStyle(
                                    color = ColorProvider(titleColorMajor),
                                    fontSize = (18 * Zoom).sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                            Text(
                                text = resinText2,
                                style = TextStyle(
                                    color = ColorProvider(titleColorMajor),
                                    fontSize = (14 * Zoom).sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                        }
                    }
                    Row(
                        modifier = GlanceModifier
                            .padding(bottom = (40 * Zoom).dp)
                    ) {
                        Image(
                            provider = ImageProvider(ExpIcon),
                            contentDescription = "",
                            modifier = GlanceModifier
                                .size((45 * Zoom).dp)
                                .clickable(onClick = actionRunCallback<uidSetting>()),
                            contentScale = ContentScale.FillBounds,
                        )
                        Column(
                            modifier = GlanceModifier
                                .padding(top = (4 * Zoom).dp),
                        ) {
                            Text(
                                text = ExpRemain,
                                style = TextStyle(
                                    color = ColorProvider(titleColorMajor),
                                    fontSize = (14 * Zoom).sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                            Text(
                                text = ExpAim,
                                style = TextStyle(
                                    color = ColorProvider(titleColorMajor),
                                    fontSize = (14 * Zoom).sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                        }
                    }
                }

                Column(
                    GlanceModifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                        Image(
                            provider = ImageProvider(image),
                            modifier = GlanceModifier
                                .size((205 * Zoom).dp)
                                .clickable(onClick = actionRunCallback<gifEngine>())
                                .padding(horizontal = (4 * Zoom).dp, vertical = (4 * Zoom).dp),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Fit,
                        )
                }
            }
        }
    }
}
