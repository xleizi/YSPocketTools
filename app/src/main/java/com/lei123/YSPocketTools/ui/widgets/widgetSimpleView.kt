package com.lei123.YSPocketTools.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lei123.YSPocketTools.AndroidTools.TimeCounter
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.UNIT
import com.lei123.YSPocketTools.UNIT.getItemImage
import com.lei123.YSPocketTools.utils.*

@Composable
fun view(viewModel: widgetSettingSimpleActivityViewModel) {
    val Zoom = viewModel.Zoom / 100
    val whichItem = viewModel.whichItem
    val timeShow = viewModel.timeShow
    val theme = viewModel.theme
    val nicknameShow = viewModel.nicknameShow
    val iconStyle = viewModel.iconStyle

    val usernickname = loadString(
        viewModel.uid + "usernickname",
        getString(R.string.pleaseLogin2)
    )

    val current_Resin = loadString(viewModel.uid + "current_Resin", "120")
    val max_resin = loadString(viewModel.uid + "max_resin", "160")
    val resinAim = loadString(viewModel.uid + "resinAim", "20日 23:59")

    val current_home_coin = loadString(viewModel.uid + "current_home_coin", "2000")
    val max_home_coin = loadString(viewModel.uid + "max_home_coin", "2400")
    val homeCoinAim = loadString(viewModel.uid + "homeCoinAim", "20日 23:59")

    val dailyTask = loadString(viewModel.uid + "dailyTask", "2")
    val total_task_num = loadString(viewModel.uid + "total_task_num", "4")
    val dailyTaskSubmit =
        loadString(
            viewModel.uid + "dailyTaskSubmit",
            getString(R.string.dailyTaskUnSubmit)
        )

    val weekly = loadString(viewModel.uid + "weekly", "2")
    val weeklyall = loadString(viewModel.uid + "weeklyall", "3")
    val weeklyStr = loadString(viewModel.uid + "weeklyStr", "2/3")

    val transRemainSecond = loadString(viewModel.uid + "transRemainSecond", "100000")
    val transRemain = loadString(viewModel.uid + "transRemain", "三天")
    val transviable = loadBoolean(viewModel.uid + "transviable", false)

    val current_expedition_num = loadString(viewModel.uid + "current_expedition_num", "0")
    val max_expedition_num = loadString(viewModel.uid + "max_expedition_num", "5")
    val ExpAim = loadString(viewModel.uid + "ExpAim", "20日 23:59")


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
        com.lei123.YSPocketTools.utils.getString(R.string.aimtime) + resinAim
    } else if (whichItem == "homecoin") {
        com.lei123.YSPocketTools.utils.getString(R.string.aimtime) + homeCoinAim
    } else if (whichItem == "daily") {
        dailyTaskSubmit
    } else if (whichItem == "weekly") {
        if (weeklyStr != "0/3") com.lei123.YSPocketTools.utils.getString(R.string.unuse) else weeklyStr
    } else if (whichItem == "transfor") {
        if (!transviable) transRemain else com.lei123.YSPocketTools.utils.getString(R.string.TranAvailable)
    } else if (whichItem == "explore") {
        com.lei123.YSPocketTools.utils.getString(R.string.aimtime) + ExpAim
    } else {
        "Bug"
    }

    Box(
        Modifier
            .size(width = (160 * Zoom).dp, height = (160 * Zoom).dp),
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = painterResource(background),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Box {
                if (nicknameShow) {
                    if (timeShow) {
                        Text(
                            text = loadString(
                                "time",
                                getString(R.string.timer2)
                            ),
                            modifier = Modifier
                                .width((90 * Zoom).dp)
                                .padding(
                                    top = (15 * Zoom).dp,
                                    start = (18 * Zoom).dp
                                ),
                            style = TextStyle(
                                color = subText,
                                fontSize = (12 * Zoom).sp
                            ),
                        )
                    }
                }

                Box {
                    if (nicknameShow) {
                        Box(
                            modifier = Modifier
                                .padding(
                                    top = (3 * Zoom).dp,
                                    start = (15 * Zoom).dp
                                )
                        ) {
                            Text(
                                text = usernickname,
                                modifier = Modifier
                                    .width((70 * Zoom).dp)
                                    .padding(
                                        top = if (timeShow) (26 * Zoom).dp else (15 * Zoom).dp,
                                        start = (3 * Zoom).dp
                                    ),
                                color = subText,
                                fontSize = if (timeShow) (12 * Zoom).sp else (16 * Zoom).sp
                            )

                            Box {
                                if (viewModel.iconStyle == "simple") {
                                    Image(
                                        painter = painterResource(item),
                                        modifier = Modifier
                                            .height((80 * Zoom).dp)
                                            .width((142 * Zoom).dp)
                                            .padding(top = (0 * Zoom).dp, start = (62 * Zoom).dp),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds,
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(item),
                                        modifier = Modifier
                                            .height((60 * Zoom).dp)
                                            .width((127 * Zoom).dp)
                                            .padding(top = (10 * Zoom).dp, start = (77 * Zoom).dp),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds,
                                    )
                                }
                            }
                        }
                    }
                    if (!nicknameShow) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    top = (5 * Zoom).dp,
                                    start = (0 * Zoom).dp
                                )
                        ) {
                            Box {
                                Image(
                                    painter = painterResource(item),
                                    modifier = Modifier
                                        .size((80 * Zoom).dp)
                                        .padding(top = (0 * Zoom).dp, start = (0 * Zoom).dp),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds,
                                )
                                if (viewModel.iconStyle == "simple") {
                                    Image(
                                        painter = painterResource(item),
                                        modifier = Modifier
                                            .size((80 * Zoom).dp)
                                            .padding(top = (0 * Zoom).dp, start = (0 * Zoom).dp),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds,
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(item),
                                        modifier = Modifier
                                            .size((60 * Zoom).dp)
                                            .padding(top = (10 * Zoom).dp, start = (10 * Zoom).dp),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds,
                                    )
                                }
                            }
                            if (timeShow) {
                                Text(
                                    text = loadString(
                                        "time",
                                        getString(R.string.timer2)
                                    ),
                                    modifier = Modifier
                                        .width((90 * Zoom).dp)
                                        .padding(
                                            top = (25 * Zoom).dp,
                                            start = (0 * Zoom).dp,
                                            end = (0 * Zoom).dp
                                        ),
                                    color = subText,
                                    fontSize = (15 * Zoom).sp
                                )
                            }
                        }
                    }



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = (75 * Zoom).dp,
                                start = (10 * Zoom).dp
                            ),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = itemText,
                            modifier = Modifier
                                .padding(
                                    top = if (whichItem != "transfor") (0 * Zoom).dp else (10 * Zoom).dp,
                                    start = (5 * Zoom).dp
                                ),
                            style = TextStyle(
                                color = mainText,
                                fontSize = if (whichItem != "transfor") (40 * Zoom).sp else (25 * Zoom).sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = itemText2,
                            modifier = Modifier
                                .padding(
                                    top = (0 * Zoom).dp,
                                    start = (0 * Zoom).dp
                                ),
                            style = TextStyle(
                                color = subText,
                                fontSize = (16 * Zoom).sp,
                            ),
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(
                        top = (0 * Zoom).dp,
                        start = (15 * Zoom).dp
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = itemText3,
                    modifier = Modifier
                        .padding(
                            top = (0 * Zoom).dp,
                            start = (5 * Zoom).dp
                        ),
                    style = TextStyle(
                        color = subText,
                        fontSize = (12 * Zoom).sp
                    ),
                )
            }
        }
    }
}

@Composable
fun view2(viewModel: widgetSettingSimpleActivityViewModel) {
    var Zoom = viewModel.Zoom / 100
    var theme = viewModel.theme
    var iconStyle = viewModel.iconStyle
    var simpleitem1 = viewModel.simpleitem1
    var simpleitem2 = viewModel.simpleitem2
    var simpleitem3 = viewModel.simpleitem3
    var simpleitem4 = viewModel.simpleitem4

    val current_Resin = loadString(viewModel.uid + "current_Resin", "120")
    val max_resin = loadString(viewModel.uid + "max_resin", "160")
    val current_home_coin = loadString(viewModel.uid + "current_home_coin", "2000")
    val max_home_coin = loadString(viewModel.uid + "max_home_coin", "2400")
    val dailyTask = loadString(viewModel.uid + "dailyTask", "2")
    val total_task_num = loadString(viewModel.uid + "total_task_num", "4")
    val dailyTaskSubmit =
        loadString(viewModel.uid + "dailyTaskSubmit", getString(R.string.dailyTaskUnSubmit))

    val dailyTaskpussed = (total_task_num.toInt() - dailyTask.toInt()).toString()

    val weekly = loadString(viewModel.uid + "weekly", "2")
    val weeklyall = loadString(viewModel.uid + "weeklyall", "3")
    val transRemainSecond = loadString(viewModel.uid + "transRemainSecond", "100000")
    val transTotalecond = "604800"
    val transpussed = (604800 - transRemainSecond.toInt()).toString()


    val explore1 = loadString(viewModel.uid + "explore1", "0")
    val explore2 = loadString(viewModel.uid + "explore2", "0")
    val explore3 = loadString(viewModel.uid + "explore3", "0")
    val explore4 = loadString(viewModel.uid + "explore4", "0")
    val explore5 = loadString(viewModel.uid + "explore5", "0")
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
    var pb1 = UNIT.getCircleSimpleDarkProgressBar(current_Resin, max_resin)
    var pb2 = UNIT.getCircleSimpleDarkProgressBar(current_home_coin, max_home_coin)
    if (dailyTaskSubmit == getString(R.string.dailyTaskUnSubmit)) pb3 =
        UNIT.getCircleSimpleDarkProgressBar(dailyTaskpussed, total_task_num)
    var pb4 = UNIT.getCircleSimpleDarkProgressBar(weekly, weeklyall)
    var pb5 = UNIT.getCircleSimpleDarkProgressBar(transpussed, "604800")
    var pb6 = UNIT.getCircleSimpleDarkProgressBar(Exppussed, "72000")

    val image1 = getItemImage(simpleitem1,iconStyle,theme)
    val image2 = getItemImage(simpleitem2,iconStyle,theme)
    val image3 = getItemImage(simpleitem3,iconStyle,theme)
    val image4 = getItemImage(simpleitem4,iconStyle,theme)

    var background = R.drawable.w_simple_dark_bg

    //theme = "Dark"
    if (theme == "Light") {
        background = R.drawable.w_simple_light_bg
        pb3 = R.drawable.circle_miui_light_pd0
        pb1 = UNIT.getCircleSimpleLightProgressBar(current_Resin, max_resin)
        pb2 = UNIT.getCircleSimpleLightProgressBar(current_home_coin, max_home_coin)
        if (dailyTaskSubmit == getString(R.string.dailyTaskUnSubmit)) pb3 =
            UNIT.getCircleSimpleLightProgressBar(dailyTaskpussed, total_task_num)
        pb4 = UNIT.getCircleSimpleLightProgressBar(weekly, weeklyall)
        pb5 = UNIT.getCircleSimpleLightProgressBar(transpussed, "604800")
        pb6 = UNIT.getCircleSimpleLightProgressBar(Exppussed, "72000")
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

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Box(
            Modifier
                .size(width = (160 * Zoom).dp, height = (160 * Zoom).dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(background),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
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

@Composable
private fun itemBar(progressBar: Int, item: Int, Zoom: Float) {
    Box(
        modifier = Modifier.padding(vertical = (3 * Zoom).dp, horizontal = (3 * Zoom).dp)
    ) {
        Image(
            painter = painterResource(progressBar),
            modifier = Modifier
                .height((65 * Zoom).dp)
                .width((65 * Zoom).dp),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
        Image(
            painter = painterResource(item),
            modifier = Modifier
                .height((65 * Zoom).dp)
                .width((65 * Zoom).dp)
                .padding(horizontal = (20 * Zoom).dp, vertical = (20 * Zoom).dp),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
    }
}