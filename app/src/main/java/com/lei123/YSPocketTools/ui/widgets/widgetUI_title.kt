package com.lei123.YSPocketTools.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.loadString

object widgetUI_title {

    @Composable
    fun widget_4_2_Title(
        Zoom: Float,
        title_long: Int,
        titleColorMajor: Color,
        title_middle: Int,
        usernickname: String,
        level: String,
        title_short: Int
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .height(height = (22 * Zoom).dp)
                .padding(top = (8 * Zoom).dp, start = (12 * Zoom).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.Start,
        ) {
            Box(
                GlanceModifier
                    .width((89 * Zoom).dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(title_long),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = getString(R.string.timer).plus(
                        loadString(
                            "time",
                            getString(R.string.timer2)
                        )
                    ),
                    style = TextStyle(
                        color = ColorProvider(titleColorMajor),
                        fontWeight = FontWeight.Bold,
                        fontSize = (7 * Zoom).sp
                    ),
                )
            }
            Box(
                GlanceModifier
                    .padding(start = (2 * Zoom).dp, end = (2 * Zoom).dp)
                    .width(width = (150 * Zoom).dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(title_middle),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = usernickname,
                    style = TextStyle(
                        color = ColorProvider(titleColorMajor),
                        fontWeight = FontWeight.Bold,
                        fontSize = (8 * Zoom).sp
                    ),
                )
            }
            Box(
                GlanceModifier
                    .width(width = (54 * Zoom).dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(title_short),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = level.plus(getString(R.string.level)),
                    style = TextStyle(
                        color = ColorProvider(titleColorMajor),
                        fontWeight = FontWeight.Bold,
                        fontSize = (8 * Zoom).sp
                    ),
                )
            }
        }
    }
}