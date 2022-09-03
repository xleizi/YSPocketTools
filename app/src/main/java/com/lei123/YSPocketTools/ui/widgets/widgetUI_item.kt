package com.lei123.YSPocketTools.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.getString

object widgetUI_item {
    @Composable
    fun homeCoinWidget_4_2(
        Zoom: Float,
        main_thin: Int,
        titleColorSub: Color,
        current_home_coin: String,
        titleColorMajor: Color,
        line: Int,
        max_home_coin: String,
        imageHomeCoinsandClock: Int,
        homeCoinAim: String,
        homeCoinText: Color,
        homecoinBar: Int
    ) {
        Box(
            GlanceModifier
                .width((72 * Zoom).dp)
                .padding(start = (6 * Zoom).dp)
        ) {
            Image(
                provider = ImageProvider(main_thin),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                GlanceModifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = GlanceModifier
                        .padding(top = (3 * Zoom).dp),
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.icon_homecoin),
                        contentDescription = "",
                        modifier = GlanceModifier
                            .size((24 * Zoom).dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
                Text(
                    text = getString(R.string.homeCoinTitle),
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    style = TextStyle(
                        color = ColorProvider(titleColorSub),
                        fontSize = (8 * Zoom).sp
                    ),
                )
                Box(
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = current_home_coin,
                        style = TextStyle(
                            color = ColorProvider(titleColorMajor),
                            fontSize = (21 * Zoom).sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Box(
                        GlanceModifier
                            .size(width = (28 * Zoom).dp, height = (30 * Zoom).dp)
                            .padding(top = (25 * Zoom).dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Image(
                            provider = ImageProvider(line),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                    Text(
                        text = max_home_coin,
                        modifier = GlanceModifier
                            .padding(top = (32 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(titleColorMajor),
                            fontSize = (10 * Zoom).sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
                Row(
                    GlanceModifier
                        .fillMaxWidth()
                        .padding(top = (7 * Zoom).dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        provider = ImageProvider(imageHomeCoinsandClock),
                        contentDescription = "",
                        modifier = GlanceModifier.size((10 * Zoom).dp),
                        contentScale = ContentScale.FillBounds,
                    )
                    Text(
                        text = if (current_home_coin != max_home_coin && max_home_coin != "123456789") homeCoinAim else getString(
                            R.string.homeCoinTitle
                        ).plus(homeCoinAim),
                        //text = "原粹树脂已回满",
                        modifier = GlanceModifier.padding(start = (2 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(homeCoinText),
                            fontSize = (6 * Zoom).sp,
                        ),
                    )
                }
                Box(
                    GlanceModifier
                        .size(width = (48 * Zoom).dp, height = (7 * Zoom).dp)
                        .padding(top = (4 * Zoom).dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        provider = ImageProvider(homecoinBar),
                        modifier = GlanceModifier.width((40 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
        }
    }

    @Composable
    fun resinWidget_4_2(
        Zoom: Float,
        main_thin: Int,
        titleColorSub: Color,
        current_Resin: String,
        titleColorMajor: Color,
        line: Int,
        max_resin: String,
        imageResinsandClock: Int,
        resinAim: String,
        resinText: Color,
        resinBar: Int
    ) {
        Box(
            GlanceModifier
                .width((66 * Zoom).dp)
                .clickable(onClick = actionRunCallback<uidSetting>())
        ) {
            Image(
                provider = ImageProvider(main_thin),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                GlanceModifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = GlanceModifier
                        .padding(top = (3 * Zoom).dp),
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.icon_resin),
                        contentDescription = "",
                        modifier = GlanceModifier
                            .size((24 * Zoom).dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
                Text(
                    text = getString(R.string.resinTitle),
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    style = TextStyle(
                        color = ColorProvider(titleColorSub),
                        fontSize = (8 * Zoom).sp
                    ),
                )
                Box(
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = current_Resin,
                        style = TextStyle(
                            color = ColorProvider(titleColorMajor),
                            fontSize = (21 * Zoom).sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Box(
                        GlanceModifier
                            .size(width = (28 * Zoom).dp, height = (30 * Zoom).dp)
                            .padding(top = (25 * Zoom).dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Image(
                            provider = ImageProvider(line),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                    Text(
                        text = max_resin,
                        modifier = GlanceModifier
                            .padding(top = (32 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(titleColorMajor),
                            fontSize = (10 * Zoom).sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
                Row(
                    GlanceModifier
                        .fillMaxWidth()
                        .padding(top = (7 * Zoom).dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        provider = ImageProvider(imageResinsandClock),
                        contentDescription = "",
                        modifier = GlanceModifier.size((10 * Zoom).dp),
                        contentScale = ContentScale.FillBounds,
                    )
                    Text(
                        text = if (current_Resin != max_resin && max_resin != "123456789") resinAim else getString(
                            R.string.resinTitle
                        ).plus(resinAim),
                        //text = "原粹树脂已回满",
                        modifier = GlanceModifier.padding(start = (2 * Zoom).dp),
                        style = TextStyle(
                            color = ColorProvider(resinText),
                            fontSize = (6 * Zoom).sp,
                        ),
                    )
                }
                Box(
                    GlanceModifier
                        .size(width = (48 * Zoom).dp, height = (7 * Zoom).dp)
                        .padding(top = (4 * Zoom).dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        provider = ImageProvider(resinBar),
                        modifier = GlanceModifier.width((40 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                    )


                }
            }
        }
    }

    @Composable
    fun homeCoinWidget_4_2_horizontal(
        Zoom: Float,
        main_long: Int,
        titleColorSub: Color,
        current_Resin: String,
        titleColorMajor: Color,
        homeCoinPoint: String,
        max_home_coin: String,
        homeCoinAim: String,
        homeCoinText: Color,
        pointOn: Int,
        pointOff: Int,
    ) {
        var point1 = true
        var point2 = true
        var point3 = true
        var point4 = true
        var point5 = true
        if (homeCoinPoint == "0"){
            point1 = false
            point2 = false
            point3 = false
            point4 = false
            point5 = false
        }else if (homeCoinPoint == "1"){
            point1 = false
            point2 = false
            point3 = false
            point4 = false
        }else if (homeCoinPoint == "2"){
            point1 = false
            point2 = false
            point3 = false
        }else if (homeCoinPoint == "3"){
            point1 = false
            point2 = false
        }else if (homeCoinPoint == "4"){
            point1 = false
        }

        Box(
            GlanceModifier
                .width((138 * Zoom).dp)
                .clickable(onClick = actionRunCallback<uidSetting>())
        ) {
            Image(
                provider = ImageProvider(main_long),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Row(
                GlanceModifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = GlanceModifier
                        .padding(top = (3 * Zoom).dp, start = (5 * Zoom).dp),
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.icon_homecoin),
                        contentDescription = "",
                        modifier = GlanceModifier
                            .size((24 * Zoom).dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
                Column {
                    Row {
                        Text(
                            text = "${getString(R.string.resinTitle)}:",
                            modifier = GlanceModifier
                                .padding(top = (4 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorSub),
                                fontSize = (8 * Zoom).sp
                            ),
                        )
                        Text(
                            text = homeCoinAim,
                            //text = "原粹树脂已回满",
                            modifier = GlanceModifier.padding(start = (5 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(homeCoinText),
                                fontSize = (8 * Zoom).sp,
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                    }
                    Row(
                        modifier = GlanceModifier
                            .padding(start = (2 * Zoom).dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = current_Resin,
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (26 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )

                        Text(
                            text = "/$max_home_coin",
                            modifier = GlanceModifier
                                .padding(start = (2 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (10 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                }
                Spacer(modifier = GlanceModifier.defaultWeight())
                Column(
                    modifier = GlanceModifier.padding(end = (5 * Zoom).dp)
                ) {
                    Image(
                        provider = ImageProvider(if (point1) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point2) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point3) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point4) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point5) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }

    @Composable
    fun resinWidget_4_2_horizontal(
        Zoom: Float,
        main_long: Int,
        titleColorSub: Color,
        current_Resin: String,
        titleColorMajor: Color,
        resinPoint: String,
        max_resin: String,
        resinAim: String,
        resinText: Color,
        pointOn: Int,
        pointOff: Int,
    ) {
        var point1 = true
        var point2 = true
        var point3 = true
        var point4 = true
        var point5 = true
        if (resinPoint == "0"){
            point1 = false
            point2 = false
            point3 = false
            point4 = false
            point5 = false
        }else if (resinPoint == "1"){
            point1 = false
            point2 = false
            point3 = false
            point4 = false
        }else if (resinPoint == "2"){
            point1 = false
            point2 = false
            point3 = false
        }else if (resinPoint == "3"){
            point1 = false
            point2 = false
        }else if (resinPoint == "4"){
            point1 = false
        }

        Box(
            GlanceModifier
                .width((138 * Zoom).dp)
                .clickable(onClick = actionRunCallback<uidSetting>())
        ) {
            Image(
                provider = ImageProvider(main_long),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Row(
                GlanceModifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = GlanceModifier
                        .padding(top = (3 * Zoom).dp, start = (5 * Zoom).dp),
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.icon_resin),
                        contentDescription = "",
                        modifier = GlanceModifier
                            .size((24 * Zoom).dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
                Column {
                    Row {
                        Text(
                            text = "${getString(R.string.resinTitle)}:",
                            modifier = GlanceModifier
                                .padding(top = (4 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorSub),
                                fontSize = (8 * Zoom).sp
                            ),
                        )
                        Text(
                            text = resinAim,
                            //text = "原粹树脂已回满",
                            modifier = GlanceModifier.padding(start = (5 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(resinText),
                                fontSize = (8 * Zoom).sp,
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                    }
                    Row(
                        modifier = GlanceModifier
                            .padding(start = (2 * Zoom).dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = current_Resin,
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (26 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )

                        Text(
                            text = "/$max_resin",
                            modifier = GlanceModifier
                                .padding(start = (2 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (10 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                }
                Spacer(modifier = GlanceModifier.defaultWeight())
                Column(
                    modifier = GlanceModifier.padding(end = (5 * Zoom).dp)
                ) {
                    Image(
                        provider = ImageProvider(if (point1) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point2) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point3) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point4) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (point5) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(vertical = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }

    @Composable
    fun trans_4_2(
        Zoom: Float,
        main_short: Int,
        titleColorSub: Color,
        titleColorMajor: Color,
        transday: String,
        transviable: Boolean,
        pointOn: Int,
        pointOff: Int
    ) {
        Box(
            GlanceModifier
                .padding(start = (3 * Zoom).dp)
                .width((53 * Zoom).dp)
        ) {
            Image(
                provider = ImageProvider(main_short),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                GlanceModifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    provider = ImageProvider(R.drawable.icon_transfor),
                    modifier = GlanceModifier.size((18 * Zoom).dp),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                )
                Text(
                    text = getString(R.string.transTitle),
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    style = TextStyle(
                        color = ColorProvider(titleColorSub),
                        fontSize = (8 * Zoom).sp
                    ),
                )
                Row {
                    if (!transviable) {
                        Text(
                            text = getString(R.string.sheng),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (8 * Zoom).sp,
                            ),
                        )
                        Text(
                            text = transday,
                            modifier = GlanceModifier
                                .padding(horizontal = (1 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (10 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = getString(R.string.Days),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (8 * Zoom).sp,
                            ),
                        )
                    } else {
                        Text(
                            text = getString(R.string.TranAvailable),
                            modifier = GlanceModifier
                                .padding(horizontal = (1 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (9 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                }
                Row(
                    GlanceModifier
                        .padding(top = (1 * Zoom).dp)
                ) {
                    Image(
                        provider = ImageProvider(if (transviable) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }

    @Composable
    fun weekly_4_2(
        Zoom: Float,
        main_short: Int,
        titleColorSub: Color,
        weeklyStr: String,
        titleColorMajor: Color,
        weekly: String,
        weekly3: Boolean,
        pointOn: Int,
        pointOff: Int,
        weekly2: Boolean,
        weekly1: Boolean
    ) {
        Box(
            GlanceModifier
                .padding(start = (3 * Zoom).dp)
                .width((53 * Zoom).dp)
        ) {
            Image(
                provider = ImageProvider(main_short),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                GlanceModifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    provider = ImageProvider(R.drawable.w_icon_sword),
                    modifier = GlanceModifier.size((18 * Zoom).dp),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                )
                Text(
                    text = getString(R.string.weeklyTitle),
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    style = TextStyle(
                        color = ColorProvider(titleColorSub),
                        fontSize = (8 * Zoom).sp
                    ),
                )
                Row {
                    if (weeklyStr != getString(R.string.weeklyEmpty)) {
                        Text(
                            text = getString(R.string.sheng),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (8 * Zoom).sp,
                            ),
                        )
                        Text(
                            text = weekly,
                            modifier = GlanceModifier
                                .padding(horizontal = (1 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (10 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = getString(R.string.ge),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (8 * Zoom).sp,
                            ),
                        )
                    } else {
                        Text(
                            text = weeklyStr,
                            modifier = GlanceModifier
                                .padding(horizontal = (1 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (9 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                }
                Row(
                    GlanceModifier
                        .padding(top = (1 * Zoom).dp)
                ) {
                    Image(
                        provider = ImageProvider(if (!weekly3) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (!weekly2) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (!weekly1) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }

    @Composable
    fun dailyTask_4_2(
        Zoom: Float,
        main_short: Int,
        titleColorSub: Color,
        dailyTaskSubmit: String,
        titleColorMajor: Color,
        remainTask: String,
        dailyTask1: Boolean,
        pointOn: Int,
        pointOff: Int,
        dailyTask2: Boolean,
        dailyTask3: Boolean,
        dailyTask4: Boolean
    ) {
        Box(
            GlanceModifier
                .width((50 * Zoom).dp)
        ) {
            Image(
                provider = ImageProvider(main_short),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                GlanceModifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    provider = ImageProvider(R.drawable.w_icon_daily),
                    modifier = GlanceModifier.size((18 * Zoom).dp),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                )
                Text(
                    text = getString(R.string.dailyTaskTitle),
                    modifier = GlanceModifier
                        .padding(top = (2 * Zoom).dp),
                    style = TextStyle(
                        color = ColorProvider(titleColorSub),
                        fontSize = (8 * Zoom).sp
                    ),
                )
                Row {
                    if (dailyTaskSubmit == getString(R.string.dailyTaskUnSubmit)) {
                        Text(
                            text = getString(R.string.sheng),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (8 * Zoom).sp,
                            ),
                        )
                        Text(
                            text = remainTask,
                            modifier = GlanceModifier
                                .padding(horizontal = (1 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (10 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = getString(R.string.ge),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (8 * Zoom).sp,
                            ),
                        )
                    } else {
                        Text(
                            text = dailyTaskSubmit,
                            modifier = GlanceModifier
                                .padding(horizontal = (1 * Zoom).dp),
                            style = TextStyle(
                                color = ColorProvider(titleColorMajor),
                                fontSize = (9 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                }
                Row(
                    GlanceModifier
                        .padding(top = (1 * Zoom).dp)
                ) {
                    Image(
                        provider = ImageProvider(if (dailyTask1) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (dailyTask2) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (dailyTask3) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                    Image(
                        provider = ImageProvider(if (dailyTask4) pointOn else pointOff),
                        modifier = GlanceModifier
                            .size((8 * Zoom).dp)
                            .padding(horizontal = (1 * Zoom).dp),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }
}