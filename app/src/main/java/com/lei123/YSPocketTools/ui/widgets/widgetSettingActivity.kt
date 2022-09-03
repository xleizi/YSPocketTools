package com.lei123.YSPocketTools.ui.widgets

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.lei123.YSPocketTools.*
import com.lei123.YSPocketTools.AndroidTools.startService.startBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startwidgetsService
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.UNIT.returnHome
import com.lei123.YSPocketTools.ui.plugin.ColorPicker
import com.lei123.YSPocketTools.utils.*
import com.lei123.test.ui.YSTopBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * this activity is used to wake up the target app by package name
 * cuz android widgets are not supported to wake up other app?
 */
class widgetSettingActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    var urlll: String? = null
    var filllll: Bitmap? = null
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val activityResultCallback: ActivityResultCallback<ActivityResult> =
        ActivityResultCallback {
            val fileUri = it.data?.data
            if (fileUri != null) {
                filllll = fileUri.toImage()
                Log.i("urlll", fileUri.toString())
                return@ActivityResultCallback
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        startBasicService(application)
        startwidgetsService(application)
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            activityResultCallback
        )

        //返回桌面
        //moveTaskToBack(false);
        setContent {
            YSComposeTheme {
                YSTopBar(viewModel, false, getString(R.string.app_name))
                settingPage()
            }
        }
    }

    @Composable
    private fun settingPage() {
        //val id = "123"
        val id = glanceId2.toString()
        //var uid = loadString(id)
        var uid by remember { mutableStateOf(loadString(id)) }
        var Zoom by remember { mutableStateOf(loadFloat(id + "Zoom", 1f) * 100) }
        var Speed by remember { mutableStateOf(1000 / loadInt(id + "gifSpeed", 10).toFloat()) }
        var Alpha by remember { mutableStateOf(loadInt(id + "Alpha", 0)) }
        var theme by remember { mutableStateOf(loadString(id + "Theme", "Light")) }
        var uidlist by remember { mutableStateOf(viewModel.getuidlist()) }
        val scrollState = rememberScrollState()
        var drawColorR by remember { mutableStateOf(loadFloat(id + "drawColorR", 0f)) }
        var drawColorG by remember { mutableStateOf(loadFloat(id + "drawColorG", 0f)) }
        var drawColorB by remember { mutableStateOf(loadFloat(id + "drawColorB", 0f)) }
        var drawColorA by remember { mutableStateOf(loadFloat(id + "drawColorA", 0f)) }
        var ColorText by remember { mutableStateOf("nu") }
        var whichItem by remember { mutableStateOf(loadString(id + "whichItem", "resin")) }
        var timeShow by remember { mutableStateOf(loadBoolean(id + "timeShow", true)) }
        var widgetClass by remember { mutableStateOf(loadString(id + "widgetClass", "complex")) }

        if ((drawColorR * drawColorG * drawColorB) == 1f) {
            ColorText = "white"
        } else if (drawColorR == 0f && drawColorG == 0f && drawColorB == 0f) {
            ColorText = "black"
        }
        var size = 0
        for (i in 0..9) {
            if (uidlist[i] != "123456789" && uidlist[i] != viewModel.mainUID) {
                size += 1
            }
        }

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val screenWidthInPx = with(LocalDensity.current) { screenWidth.toPx() }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(YSComposeTheme.colors.background)
                .padding(top = 48.dp)
                .fillMaxHeight()
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                //.height(screenHeight+500.dp)
            ) {
                Text(
                    text = getString(R.string.WidgetSelectUid),
                    color = YSComposeTheme.colors.textPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 2.dp, start = 15.dp, end = 10.dp)
                        .height((size * 54).dp)
                        .fillMaxWidth()
                ) {
                    items(uidlist.size) { rowIndex ->
                        if (uidlist[rowIndex] != "123456789" && uidlist[rowIndex] != viewModel.mainUID) {
                            Box(
                                Modifier
                                    .clickable(onClick = {
                                        uid = uidlist[rowIndex]
                                    }
                                    )
                            ) {
                                Image(
                                    painterResource(R.drawable.lay_home_uidlist_bar_light),
                                    contentDescription = "",
                                    modifier = Modifier.padding(top = 5.dp),
                                    contentScale = ContentScale.FillBounds,
                                )
                                Text(
                                    text = "UID: " + uidlist[rowIndex],
                                    color = YSComposeTheme.colors.TextBold,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(top = 5.dp, start = 15.dp)
                                        .align(Alignment.CenterStart)
                                )
                                if (uidlist[rowIndex] == uid) {
                                    Image(
                                        painterResource(R.drawable.lay_uidlist_check),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(top = 5.dp, end = 8.dp)
                                            .align(Alignment.CenterEnd)
                                            .size(25.dp),
                                        contentScale = ContentScale.FillBounds,
                                    )
                                } else {

                                }
                            }
                        }
                    }
                }
                widgetSettingLine()
                Text(
                    text = getString(R.string.WidgetSize) + " = ${Zoom.toInt()}",
                    color = YSComposeTheme.colors.textPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
                Slider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    value = Zoom,
                    onValueChange = {
                        Zoom = it
                        Log.i("hsik", "onValueChange = $it")
                        //Zoom = it
                    },
                    valueRange = 20f..220f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Yellow,
                        activeTrackColor = Color.Green,
                    )
                )
                if (widgetClass=="gif"){
                    Text(
                        text = getString(R.string.gifSpeed) + " = ${Speed.toInt()}",
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    Text(
                        text = getString(R.string.gifSpeed2),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    Slider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        value = Speed,
                        onValueChange = {
                            Speed = it

                            Log.i("hsik", "onValueChange = $it")
                            Log.i("hsik", "onValueChange = ${(1000/Speed).toInt()}")
                            //Zoom = it
                        },
                        valueRange = 20f..200f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Yellow,
                            activeTrackColor = Color.Green,
                        )
                    )
                }

                //非透明部件主题颜色
                if (widgetClass == "complex42" || widgetClass == "complex22" ||  widgetClass == "complex42horizontal") {
                    widgetSettingLine()
                    Text(
                        text = getString(R.string.WidgetTheme),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                    ) {
                        Row(
                            Modifier
                                .weight(1f)
                                .clickable(onClick = {
                                    theme = "Light"
                                }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            RadioButton(
                                selected = theme == "Light",           //是否选中
                                onClick = {
                                    theme = "Light"
                                },                                          //点击事件
                                modifier = Modifier.padding(2.dp),            //修饰符
                                enabled = true,                               //是否启用
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = green1,                 //选中的颜色
                                    unselectedColor = black7,              //未选中的颜色
                                    disabledColor = grey2                   //禁用的颜色
                                )
                            )
                            Text(text = "Light", textAlign = TextAlign.Center)
                        }
                        Row(
                            Modifier
                                .weight(1f)
                                .clickable(onClick = {
                                    theme = "Dark"
                                }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            RadioButton(
                                selected = theme == "Dark",           //是否选中
                                onClick = {
                                    theme = "Dark"
                                },                                          //点击事件
                                modifier = Modifier.padding(2.dp),            //修饰符
                                enabled = true,                               //是否启用
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = green1,                 //选中的颜色
                                    unselectedColor = black7,              //未选中的颜色
                                    disabledColor = grey2                   //禁用的颜色
                                )
                            )
                            Text(
                                text = "Dark",
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                //透明部件背景颜色和字体颜色
                if (widgetClass == "transparency22" || widgetClass == "transparency41" || widgetClass == "transparency42" ) {
                    widgetSettingLine()
                    Text(
                        text = getString(R.string.WidgetAlpha2) + " = ${(Alpha * 1f / 2.55f).toInt()}",
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                        Text(
                            text = getString(R.string.WidgetAlpha3),
                            color = YSComposeTheme.colors.textPrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                        )
                    }
                    Slider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        value = Alpha * 1f / 2.55f,
                        onValueChange = {
                            if (true) {
                                //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                Log.i("hsik", "onValueChange = $it")
                                Alpha = (it * 2.55f).toInt()
                            } else {
                                //getString(R.string.CantChange).toast()
                            }
                        },
                        onValueChangeFinished = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            } else {
                                getString(R.string.CantChange2).toast()
                            }
                        },
                        valueRange = 0f..100f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Yellow,
                            activeTrackColor = Color.Green,
                        )
                    )


                    widgetSettingLine()
                    Text(
                        text = getString(R.string.WidgetTextColor),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        ColorPicker(
                            onColorSelected = { color ->
                                drawColorR = color.red
                                drawColorG = color.green
                                drawColorB = color.blue
                                drawColorA = color.alpha
                                ColorText = "nu"
                            })
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_resin),
                                contentDescription = null,
                                tint = Color(drawColorR, drawColorG, drawColorB)
                            )
                            Text(
                                text = "树脂：120/160",
                                color = Color(drawColorR, drawColorG, drawColorB)
                            )
                            Row(
                                Modifier.clickable(onClick = {
                                    ColorText = "black"
                                    drawColorR = 0f
                                    drawColorG = 0f
                                    drawColorB = 0f
                                }),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = ColorText == "black",           //是否选中
                                    onClick = {
                                        ColorText = "black"
                                        drawColorR = 0f
                                        drawColorG = 0f
                                        drawColorB = 0f
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color.Black,                 //选中的颜色
                                        unselectedColor = grey2,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = "black",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Row(
                                Modifier.clickable(onClick = {
                                    ColorText = "white"
                                    drawColorR = 1f
                                    drawColorG = 1f
                                    drawColorB = 1f
                                }),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = ColorText == "white",           //是否选中
                                    onClick = {
                                        ColorText = "white"
                                        drawColorR = 1f
                                        drawColorG = 1f
                                        drawColorB = 1f
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color.White,                 //选中的颜色
                                        unselectedColor = grey2,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = "white",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
                //单独部件显示内容
                if (widgetClass == "transparency22" || widgetClass == "complex22" || widgetClass == "transparency41") {
                    widgetSettingLine()
                    Text(
                        text = getString(R.string.WidgetItem),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    Column {
                        Row() {
                            Row(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        whichItem = "resin"
                                    }),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = whichItem == "resin",           //是否选中
                                    onClick = {
                                        whichItem = "resin"
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = green1,                 //选中的颜色
                                        unselectedColor = black7,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = getString(R.string.resinTitle),
                                    color = YSComposeTheme.colors.textPrimary,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Row(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        whichItem = "homecoin"
                                    }),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = whichItem == "homecoin",           //是否选中
                                    onClick = {
                                        whichItem = "homecoin"
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = green1,                 //选中的颜色
                                        unselectedColor = black7,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = getString(R.string.homeCoinTitle),
                                    color = YSComposeTheme.colors.textPrimary,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        Row {
                            Row(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        whichItem = "daily"
                                    }),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = whichItem == "daily",           //是否选中
                                    onClick = {
                                        whichItem = "daily"
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = green1,                 //选中的颜色
                                        unselectedColor = black7,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = getString(R.string.dailyTaskTitle),
                                    color = YSComposeTheme.colors.textPrimary,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Row(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        whichItem = "weekly"
                                    }),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = whichItem == "weekly",           //是否选中
                                    onClick = {
                                        whichItem = "weekly"
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = green1,                 //选中的颜色
                                        unselectedColor = black7,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = getString(R.string.weeklyTitle),
                                    color = YSComposeTheme.colors.textPrimary,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        Row {
                            Row(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        whichItem = "transfor"
                                    }),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = whichItem == "transfor",           //是否选中
                                    onClick = {
                                        whichItem = "transfor"
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = green1,                 //选中的颜色
                                        unselectedColor = black7,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = getString(R.string.transTitle),
                                    color = YSComposeTheme.colors.textPrimary,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Row(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        whichItem = "explore"
                                    }),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = whichItem == "explore",           //是否选中
                                    onClick = {
                                        whichItem = "explore"
                                    },                                          //点击事件
                                    modifier = Modifier.padding(start = 2.dp),            //修饰符
                                    enabled = true,                               //是否启用
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = green1,                 //选中的颜色
                                        unselectedColor = black7,              //未选中的颜色
                                        disabledColor = grey2                   //禁用的颜色
                                    )
                                )
                                Text(
                                    text = getString(R.string.exploreTitle),
                                    color = YSComposeTheme.colors.textPrimary,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
                //单独部件显示时间
                if (widgetClass == "transparency22") {
                    widgetSettingLine()
                    Text(
                        text = getString(R.string.WidgetItemTime),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    Row {
                        Row(
                            Modifier
                                .weight(1f)
                                .clickable(onClick = {
                                    timeShow = true
                                }),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = timeShow,           //是否选中
                                onClick = {
                                    timeShow = true
                                },                                          //点击事件
                                modifier = Modifier.padding(start = 2.dp),            //修饰符
                                enabled = true,                               //是否启用
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = green1,                 //选中的颜色
                                    unselectedColor = black7,              //未选中的颜色
                                    disabledColor = grey2                   //禁用的颜色
                                )
                            )
                            Text(
                                text = getString(R.string.yes),
                                color = YSComposeTheme.colors.textPrimary,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Row(
                            Modifier
                                .weight(1f)
                                .clickable(onClick = {
                                    timeShow = false
                                }),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = !timeShow,           //是否选中
                                onClick = {
                                    timeShow = false
                                },                                          //点击事件
                                modifier = Modifier.padding(start = 2.dp),            //修饰符
                                enabled = true,                               //是否启用
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = green1,                 //选中的颜色
                                    unselectedColor = black7,              //未选中的颜色
                                    disabledColor = grey2                   //禁用的颜色
                                )
                            )
                            Text(
                                text = getString(R.string.no),
                                color = YSComposeTheme.colors.textPrimary,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                widgetSettingLine()
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, top = 10.dp, bottom = 50.dp, end = 25.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YSComposeTheme.colors.topBottomText,
                        contentColor = Color.White
                    ),
                    onClick = {
                        Log.i("glanceIdGet", glanceId2.toString())
                        GlobalScope.launch() {
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI4_2_complex::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI4_2_complex().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI2_2_transparency::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI2_2_transparency().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI2_2_complex::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI2_2_complex().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI4_1_transparency::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI4_1_transparency().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI4_2_transparency::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI4_2_transparency().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI4_2_complex_horizontal::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI4_2_complex_horizontal().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI_gif_hutaoao::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI_gif_hutaoao().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                            GlanceAppWidgetManager(application).getGlanceIds(
                                GlanceWidgetUI_gif_nilue::class.java
                            ).let {
                                for (GlanceId in it) {
                                    Log.i("GlanceId", GlanceId.toString())
                                    if (GlanceId.toString() == glanceId2.toString()) {
                                        saveInfo(
                                            id,
                                            whichItem,
                                            Zoom,
                                            drawColorR,
                                            drawColorG,
                                            drawColorB,
                                            theme,
                                            Alpha,
                                            timeShow,
                                            uid,
                                            Speed
                                        )
                                        GlanceWidgetUI_gif_nilue().update(
                                            application,
                                            GlanceId
                                        )
                                        returnHome()
                                        this@widgetSettingActivity.finish()
                                    }
                                }
                            }
                        }
                    }) { Text(text = "Save") }
            }
        }
    }

    private fun saveInfo(
        id: String,
        whichItem: String,
        Zoom: Float,
        drawColorR: Float,
        drawColorG: Float,
        drawColorB: Float,
        theme: String,
        Alpha: Int,
        timeShow: Boolean,
        uid: String,
        Speed: Float
    ) {
        saveInt(id + "gifSpeed", (1000/Speed).toInt())

        saveString(id + "whichItem", whichItem)
        saveFloat(id + "Zoom", Zoom / 100)
        saveFloat(id + "drawColorR", drawColorR)
        saveFloat(id + "drawColorG", drawColorG)
        saveFloat(id + "drawColorB", drawColorB)
        saveString(id + "Theme", theme)
        saveInt(id + "Alpha", Alpha)
        saveBoolean(id + "timeShow", timeShow)
        saveString("widgetID", id)
        saveString(id, uid)
    }

    @Composable
    private fun widgetSettingLine() {
        Image(
            painterResource(R.drawable.lay_main_line1),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 10.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds,
        )
    }
}
