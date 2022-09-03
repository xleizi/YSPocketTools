package com.lei123.YSPocketTools.ui.widgets

import android.content.Intent
import android.graphics.Bitmap
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
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.unit.ColorProvider
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import com.lei123.YSPocketTools.*
import com.lei123.YSPocketTools.AndroidTools.TimeCounter
import com.lei123.YSPocketTools.AndroidTools.startService.startBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startwidgetsService
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.ui.plugin.drawColoredShadow
import com.lei123.YSPocketTools.utils.*
import com.lei123.test.ui.YSTopBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait

/**
 * this activity is used to wake up the target app by package name
 * cuz android widgets are not supported to wake up other app?
 */
class widgetSettingSimpleActivity : ComponentActivity() {
    val viewModel: widgetSettingSimpleActivityViewModel by viewModels()
    val viewModel2: MainViewModel by viewModels()


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
        // viewModel.id = "123"
        viewModel.id = glanceId2.toString()
        viewModel.uid = loadString(viewModel.id)
        //var uid by remember { mutableStateOf(loadString(viewModel.id)) }
        viewModel.uid = loadString(viewModel.id)
        viewModel.theme = loadString(viewModel.id + "Theme", "Light")
        viewModel.whichItem = loadString(viewModel.id + "whichItem", "resin")
        viewModel.Zoom = loadFloat(viewModel.id + "Zoom", 1f) * 100
        viewModel.timeShow = loadBoolean(viewModel.id + "timeShow", true)
        viewModel.nicknameShow = loadBoolean(viewModel.id + "nicknameShow", true)
        viewModel.iconStyle = loadString(viewModel.id + "iconStyle", "simple")
        viewModel.widgetClass = loadString(viewModel.id + "widgetClass", "transparencySimple22")
        viewModel.simpleitem1 = loadString(viewModel.id + "simpleitem1", "resin")
        viewModel.simpleitem2 = loadString(viewModel.id + "simpleitem2", "homecoin")
        viewModel.simpleitem3 = loadString(viewModel.id + "simpleitem3", "daily")
        viewModel.simpleitem4 = loadString(viewModel.id + "simpleitem4", "explore")

        //返回桌面
        //moveTaskToBack(false);
        setContent {
            YSComposeTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    YSTopBar(
                        viewModel2,
                        false,
                        getString(R.string.app_name) + getString(R.string.Widgetsetting)
                    )
                    settingPage()
                }
            }
        }
    }

    @Composable
    private fun settingPage() {
        //viewModel.id = glanceId2.toString()
        var size = 0
        var uidlist by remember { mutableStateOf(viewModel2.getuidlist()) }
        for (i in 0..9) {
            if (uidlist[i] != "123456789" && uidlist[i] != viewModel2.mainUID) {
                size += 1
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = getString(R.string.Widgetsetting),
                    color = YSComposeTheme.colors.simpleBlack,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    Modifier
                        .size(width = 164.dp, height = 164.dp)
                        .drawColoredShadow(
                            color = colorResource(id = R.color.public_color_D3DBF9),
                            alpha = 0.5f,
                            shadowRadius = 15.dp,
                            borderRadius = 15.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.widgetClass == "transparencySimple22") {
                        view(viewModel)
                    } else {
                        view2(viewModel)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .padding(horizontal = 15.dp),
            ) {
                Text(
                    text = if (viewModel.widgetClass == "transparencySimple22") getString(R.string.Widgetsetting2_2) else getString(
                        R.string.Widgetsetting2_2_inter
                    ),
                    color = YSComposeTheme.colors.simpleBlack,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 0.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .clickable(
                            onClick = {
                                GlobalScope.launch() {
                                    GlanceAppWidgetManager(application)
                                        .getGlanceIds(
                                            GlanceWidgetUI2_2_simple::class.java
                                        )
                                        .let {
                                            for (GlanceId in it) {
                                                Log.i("GlanceId", GlanceId.toString())
                                                if (GlanceId.toString() == glanceId2.toString()) {
                                                    saveInfo()
                                                    GlanceWidgetUI2_2_simple().update(
                                                        application,
                                                        GlanceId
                                                    )
                                                    UNIT.returnHome()
                                                    this@widgetSettingSimpleActivity.finish()
                                                }
                                            }
                                        }
                                    GlanceAppWidgetManager(application)
                                        .getGlanceIds(
                                            GlanceWidgetUI2_2_simple_inter::class.java
                                        )
                                        .let {
                                            for (GlanceId in it) {
                                                if (GlanceId.toString() == glanceId2.toString()) {
                                                    saveInfo()
                                                    GlanceWidgetUI2_2_simple_inter().update(
                                                        application,
                                                        GlanceId
                                                    )
                                                    UNIT.returnHome()
                                                    this@widgetSettingSimpleActivity.finish()
                                                }
                                            }
                                        }
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.w_simple_botton),
                        modifier = Modifier.size(width = 70.dp, height = 30.dp),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                    )
                    Text(
                        text = getString(R.string.Widgetsetting2_2save),
                        color = Color.White,
                        fontSize = 15.sp,
                    )
                }
            }
            Image(
                painter = painterResource(R.drawable.w_simple_line),
                modifier = Modifier
                    .height(7.dp)
                    .padding(vertical = 3.dp, horizontal = 15.dp)
                    .fillMaxWidth(),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 2.dp),
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 5.dp)
                    ) {
                        Text(
                            text = getString(R.string.Widgetsetting2_2showuid),
                            color = YSComposeTheme.colors.simplegray,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 5.dp, end = 0.dp)
                        )

                        LazyColumn(
                            modifier = Modifier
                                .padding(top = 2.dp, start = 0.dp, end = 0.dp)
                                .height((size * 54).dp)
                                .fillMaxWidth()
                        ) {
                            items(uidlist.size) { rowIndex ->
                                if (uidlist[rowIndex] != "123456789" && uidlist[rowIndex] != viewModel2.mainUID) {
                                    Box(
                                        Modifier
                                            .clickable(onClick = {
                                                viewModel.uid = uidlist[rowIndex]
                                            })
                                    ) {
                                        Image(
                                            painterResource(R.drawable.w_simple_uidbar),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .height(50.dp)
                                                .padding(top = 5.dp),
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
                                        if (uidlist[rowIndex] == viewModel.uid) {
                                            Image(
                                                painterResource(R.drawable.w_simple_check),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .align(Alignment.CenterEnd)
                                                    .height(25.dp)
                                                    .width(30.dp)
                                                    .padding(top = 5.dp, end = 10.dp),
                                                contentScale = ContentScale.FillBounds,
                                            )
                                        } else {

                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(top = 10.dp)
                        ) {
                            Text(
                                text = getString(R.string.Widgetsetting2_2showsize),
                                color = YSComposeTheme.colors.simplegray,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 0.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "${viewModel.Zoom.toInt()}%",
                                color = YSComposeTheme.colors.simplegray,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 0.dp)
                            )
                        }
                        Slider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp, vertical = 10.dp),
                            value = viewModel.Zoom,
                            onValueChange = {
                                viewModel.Zoom = it
                                Log.i("hsik", "onValueChange = $it")
                                //Zoom = it
                            },
                            valueRange = 20f..220f,
                            colors = SliderDefaults.colors(
                                thumbColor = YSComposeTheme.colors.simpleblue,
                                inactiveTrackColor = Color.LightGray,
                                activeTrackColor = YSComposeTheme.colors.simpleblue,
                            )
                        )
                    }

                    Column {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(top = 20.dp)
                        ) {
                            Text(
                                text = getString(R.string.Widgetsetting2_2iconStyle),
                                color = YSComposeTheme.colors.simplegray,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 0.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        ) {
                            Box(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        viewModel.iconStyle = "simple"
                                    })
                            ) {
                                Image(
                                    painterResource(R.drawable.w_simple_shortbar),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(top = 5.dp),
                                    contentScale = ContentScale.FillBounds,
                                )
                                Text(
                                    text = "简洁版图标",
                                    color = YSComposeTheme.colors.TextBold,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(top = 5.dp, start = 15.dp)
                                        .align(Alignment.CenterStart)
                                )
                                if (viewModel.iconStyle == "simple") {
                                    Image(
                                        painterResource(R.drawable.w_simple_check),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .height(25.dp)
                                            .width(30.dp)
                                            .padding(top = 5.dp, end = 10.dp),
                                        contentScale = ContentScale.FillBounds,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                            Box(
                                Modifier
                                    .weight(1f)
                                    .clickable(onClick = {
                                        viewModel.iconStyle = "yuanshen"
                                    })
                            ) {
                                Image(
                                    painterResource(R.drawable.w_simple_shortbar),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(top = 5.dp),
                                    contentScale = ContentScale.FillBounds,
                                )
                                Text(
                                    text = "原生图标",
                                    color = YSComposeTheme.colors.TextBold,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(top = 5.dp, start = 15.dp)
                                        .align(Alignment.CenterStart)
                                )
                                if (viewModel.iconStyle == "yuanshen") {
                                    Image(
                                        painterResource(R.drawable.w_simple_check),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .height(25.dp)
                                            .width(30.dp)
                                            .padding(top = 5.dp, end = 10.dp),
                                        contentScale = ContentScale.FillBounds,
                                    )
                                }
                            }
                        }
                    }

                    if (viewModel.widgetClass == "transparencySimple22") {
                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .padding(top = 20.dp)
                            ) {
                                Text(
                                    text = getString(R.string.Widgetsetting2_2top),
                                    color = YSComposeTheme.colors.simplegray,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .padding(start = 5.dp, end = 0.dp)
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                            ) {
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .clickable(onClick = {
                                            viewModel.nicknameShow = viewModel.nicknameShow != true
                                        })
                                ) {
                                    Image(
                                        painterResource(R.drawable.w_simple_shortbar),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(50.dp)
                                            .padding(top = 5.dp),
                                        contentScale = ContentScale.FillBounds,
                                    )
                                    Text(
                                        text = "角色昵称",
                                        color = YSComposeTheme.colors.TextBold,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(top = 5.dp, start = 15.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    if (viewModel.nicknameShow) {
                                        Image(
                                            painterResource(R.drawable.w_simple_check),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .align(Alignment.CenterEnd)
                                                .height(25.dp)
                                                .width(30.dp)
                                                .padding(top = 5.dp, end = 10.dp),
                                            contentScale = ContentScale.FillBounds,
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .clickable(onClick = {
                                            viewModel.timeShow = viewModel.timeShow != true
                                        })
                                ) {
                                    Image(
                                        painterResource(R.drawable.w_simple_shortbar),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(50.dp)
                                            .padding(top = 5.dp),
                                        contentScale = ContentScale.FillBounds,
                                    )
                                    Text(
                                        text = "当前时间",
                                        color = YSComposeTheme.colors.TextBold,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(top = 5.dp, start = 15.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    if (viewModel.timeShow) {
                                        Image(
                                            painterResource(R.drawable.w_simple_check),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .align(Alignment.CenterEnd)
                                                .height(25.dp)
                                                .width(30.dp)
                                                .padding(top = 5.dp, end = 10.dp),
                                            contentScale = ContentScale.FillBounds,
                                        )
                                    }
                                }
                            }
                        }
                    }


                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 15.dp)
                    ) {
                        Text(
                            text = getString(R.string.Widgetsetting2_2color),
                            color = YSComposeTheme.colors.simplegray,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 5.dp, end = 0.dp)
                        )
                        Row(
                            modifier = Modifier.padding(top = 5.dp)
                        ) {
                            Box {
                                if (viewModel.theme == "Light") {
                                    Image(
                                        painter = painterResource(R.drawable.w_simple_settingbluecheck),
                                        modifier = Modifier
                                            .height(50.dp),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                    )
                                }
                                Image(
                                    painter = painterResource(R.drawable.w_simple_settinglightcircle),
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(vertical = 3.dp, horizontal = 3.dp)
                                        .clickable {
                                            viewModel.theme = "Light"
                                        },
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit,
                                )
                            }
                            Box(
                                modifier = Modifier.padding(start = 5.dp)
                            ) {
                                if (viewModel.theme == "Dark") {
                                    Image(
                                        painter = painterResource(R.drawable.w_simple_settingbluecheck),
                                        modifier = Modifier
                                            .height(50.dp),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                    )
                                }
                                Image(
                                    painter = painterResource(R.drawable.w_simple_settingdarkcircle),
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(vertical = 3.dp, horizontal = 3.dp)
                                        .clickable {
                                            viewModel.theme = "Dark"
                                        },
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit,
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 15.dp, bottom = 50.dp)
                    ) {
                        Text(
                            text = getString(R.string.Widgetsetting2_2content),
                            color = YSComposeTheme.colors.simplegray,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 5.dp, end = 0.dp)
                        )
                        if (viewModel.widgetClass == "transparencySimple22") {
                            Row(
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                itemSelect("resin", R.drawable.w_simple_setting_resin)
                                itemSelect("homecoin", R.drawable.w_simple_setting_homecoin)
                                itemSelect("daily", R.drawable.w_simple_setting_daily)
                                itemSelect("weekly", R.drawable.w_simple_setting_weekly)
                                itemSelect("transfor", R.drawable.w_simple_setting_trans)
                                itemSelect("explore", R.drawable.w_simple_setting_explore)
                            }
                        } else {
                            if(viewModel.simpleitem1=="resin"){
                                viewModel.ifitemselect1 = true
                                viewModel.ifitemselecticon1 = R.drawable.w_simple_select1
                            }else if(viewModel.simpleitem2=="resin"){
                                viewModel.ifitemselect1 = true
                                viewModel.ifitemselecticon1 = R.drawable.w_simple_select2
                            }else if(viewModel.simpleitem3=="resin") {
                                viewModel.ifitemselect1 = true
                                viewModel.ifitemselecticon1 = R.drawable.w_simple_select3
                            }else if(viewModel.simpleitem4=="resin") {
                                viewModel.ifitemselect1 = true
                                viewModel.ifitemselecticon1 = R.drawable.w_simple_select4
                            }else{
                                viewModel.ifitemselect1 = false
                                viewModel.ifitemselecticon1 = R.drawable.w_simple_select5
                            }

                            if(viewModel.simpleitem1=="homecoin"){
                                viewModel.ifitemselect2 = true
                                viewModel.ifitemselecticon2 = R.drawable.w_simple_select1
                            }else if(viewModel.simpleitem2=="homecoin"){
                                viewModel.ifitemselect2 = true
                                viewModel.ifitemselecticon2 = R.drawable.w_simple_select2
                            }else if(viewModel.simpleitem3=="homecoin") {
                                viewModel.ifitemselect2 = true
                                viewModel.ifitemselecticon2 = R.drawable.w_simple_select3
                            }else if(viewModel.simpleitem4=="homecoin") {
                                viewModel.ifitemselect2 = true
                                viewModel.ifitemselecticon2 = R.drawable.w_simple_select4
                            }else{
                                viewModel.ifitemselect2 = false
                                viewModel.ifitemselecticon2 = R.drawable.w_simple_select5
                            }

                            if(viewModel.simpleitem1=="daily"){
                                viewModel.ifitemselect3 = true
                                viewModel.ifitemselecticon3 = R.drawable.w_simple_select1
                            }else if(viewModel.simpleitem2=="daily"){
                                viewModel.ifitemselect3 = true
                                viewModel.ifitemselecticon3 = R.drawable.w_simple_select2
                            }else if(viewModel.simpleitem3=="daily") {
                                viewModel.ifitemselect3 = true
                                viewModel.ifitemselecticon3 = R.drawable.w_simple_select3
                            }else if(viewModel.simpleitem4=="daily") {
                                viewModel.ifitemselect3 = true
                                viewModel.ifitemselecticon3 = R.drawable.w_simple_select4
                            }else{
                                viewModel.ifitemselect3 = false
                                viewModel.ifitemselecticon3 = R.drawable.w_simple_select5
                            }

                            if(viewModel.simpleitem1=="weekly"){
                                viewModel.ifitemselect4 = true
                                viewModel.ifitemselecticon4 = R.drawable.w_simple_select1
                            }else if(viewModel.simpleitem2=="weekly"){
                                viewModel.ifitemselect4 = true
                                viewModel.ifitemselecticon4 = R.drawable.w_simple_select2
                            }else if(viewModel.simpleitem3=="weekly") {
                                viewModel.ifitemselect4 = true
                                viewModel.ifitemselecticon4 = R.drawable.w_simple_select3
                            }else if(viewModel.simpleitem4=="weekly") {
                                viewModel.ifitemselect4 = true
                                viewModel.ifitemselecticon4 = R.drawable.w_simple_select4
                            }else{
                                viewModel.ifitemselect4 = false
                                viewModel.ifitemselecticon4 = R.drawable.w_simple_select5
                            }

                            if(viewModel.simpleitem1=="transfor"){
                                viewModel.ifitemselect5 = true
                                viewModel.ifitemselecticon5 = R.drawable.w_simple_select1
                            }else if(viewModel.simpleitem2=="transfor"){
                                viewModel.ifitemselect5 = true
                                viewModel.ifitemselecticon5 = R.drawable.w_simple_select2
                            }else if(viewModel.simpleitem3=="transfor") {
                                viewModel.ifitemselect5 = true
                                viewModel.ifitemselecticon5 = R.drawable.w_simple_select3
                            }else if(viewModel.simpleitem4=="transfor") {
                                viewModel.ifitemselect5 = true
                                viewModel.ifitemselecticon5 = R.drawable.w_simple_select4
                            }else{
                                viewModel.ifitemselect5 = false
                                viewModel.ifitemselecticon5 = R.drawable.w_simple_select5
                            }

                            if(viewModel.simpleitem1=="explore"){
                                viewModel.ifitemselect6 = true
                                viewModel.ifitemselecticon6 = R.drawable.w_simple_select1
                            }else if(viewModel.simpleitem2=="explore"){
                                viewModel.ifitemselect6 = true
                                viewModel.ifitemselecticon6 = R.drawable.w_simple_select2
                            }else if(viewModel.simpleitem3=="explore") {
                                viewModel.ifitemselect6 = true
                                viewModel.ifitemselecticon6 = R.drawable.w_simple_select3
                            }else if(viewModel.simpleitem4=="explore") {
                                viewModel.ifitemselect6 = true
                                viewModel.ifitemselecticon6 = R.drawable.w_simple_select4
                            }else{
                                viewModel.ifitemselect6 = false
                                viewModel.ifitemselecticon6 = R.drawable.w_simple_select5
                            }
                            Row {
                                Box(modifier = Modifier.weight(1f)) {
                                    itemSelect2(
                                        "resin",
                                        viewModel.ifitemselect1,
                                        R.drawable.w_simple_setting_small_resin,
                                        viewModel.ifitemselecticon1
                                    ) {
                                        selectClick("resin")
                                    }
                                }
                                Box(modifier = Modifier.weight(1f)) {
                                    itemSelect2(
                                        "homecoin",
                                        viewModel.ifitemselect2,
                                        R.drawable.w_simple_setting_small_homecoin,
                                        viewModel.ifitemselecticon2
                                    ) {
                                        selectClick("homecoin")
                                    }
                                }
                                Box(modifier = Modifier.weight(1f)) {
                                    itemSelect2(
                                        "daily",
                                        viewModel.ifitemselect3,
                                        R.drawable.w_simple_setting_small_daily,
                                        viewModel.ifitemselecticon3
                                    ) {
                                        selectClick("daily")
                                    }
                                }
                            }
                            Row {
                                Box(modifier = Modifier.weight(1f)) {
                                    itemSelect2(
                                        "weekly",
                                        viewModel.ifitemselect4,
                                        R.drawable.w_simple_setting_small_weekly,
                                        viewModel.ifitemselecticon4
                                    ) {
                                        selectClick("weekly")
                                    }
                                }
                                Box(modifier = Modifier.weight(1f)) {
                                    itemSelect2(
                                        "transfor",
                                        viewModel.ifitemselect5,
                                        R.drawable.w_simple_setting_small_trans,
                                        viewModel.ifitemselecticon5
                                    ) {
                                        selectClick("transfor")
                                    }
                                }
                                Box(modifier = Modifier.weight(1f)) {
                                    itemSelect2(
                                        "explore",
                                        viewModel.ifitemselect6,
                                        R.drawable.w_simple_setting_small_explore,
                                        viewModel.ifitemselecticon6
                                    ) {
                                        selectClick("explore")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveInfo() {
        saveString(
            viewModel.id + "whichItem",
            viewModel.whichItem
        )
        saveFloat(
            viewModel.id + "Zoom",
            viewModel.Zoom / 100
        )
        saveString(
            viewModel.id + "Theme",
            viewModel.theme
        )
        saveString(
            viewModel.id + "iconStyle",
            viewModel.iconStyle
        )
        saveBoolean(
            viewModel.id + "nicknameShow",
            viewModel.nicknameShow
        )
        saveBoolean(
            viewModel.id + "timeShow",
            viewModel.timeShow
        )

        saveString("widgetID", viewModel.id)
        saveString(viewModel.id, viewModel.uid)
    }

    private fun selectClick(item: String) {
        if (viewModel.simpleitem1 == item) {
            viewModel.simpleitem1 = ""
        } else if (viewModel.simpleitem2 == item) {
            viewModel.simpleitem2 = ""
        } else if (viewModel.simpleitem3 == item) {
            viewModel.simpleitem3 = ""
        } else if (viewModel.simpleitem4 == item) {
            viewModel.simpleitem4 = ""
        } else if (viewModel.simpleitem1 == "") {
            viewModel.simpleitem1 = item
        } else if (viewModel.simpleitem2 == "") {
            viewModel.simpleitem2 = item
        } else if (viewModel.simpleitem3 == "") {
            viewModel.simpleitem3 = item
        } else if (viewModel.simpleitem4 == "") {
            viewModel.simpleitem4 = item
        } else {

        }
    }

    @Composable
    private fun itemSelect(item: String, image: Int) {
        Box(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
        ) {
            if (viewModel.whichItem == item) {
                Image(
                    painter = painterResource(R.drawable.w_simple_settingbluecheck_fang),
                    modifier = Modifier
                        .height(130.dp),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                )
            }
            Image(
                painter = painterResource(image),
                modifier = Modifier
                    .height(130.dp)
                    .padding(vertical = 5.dp, horizontal = 5.dp)
                    .clickable {
                        viewModel.whichItem = item
                    },
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
        }
    }

    @Composable
    private fun itemSelect2(
        item: String,
        select: Boolean,
        image: Int,
        check: Int,
        click: () -> Unit
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
        ) {
            Image(
                painter = painterResource(image),
                modifier = Modifier
                    .height(100.dp)
                    .padding(vertical = 5.dp, horizontal = 5.dp)
                    .clickable {

                        click()
                    },
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
            if (select) {
                Image(
                    painter = painterResource(check),
                    modifier = Modifier
                        .height(30.dp)
                        .padding(top = 10.dp, start = 70.dp),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}
