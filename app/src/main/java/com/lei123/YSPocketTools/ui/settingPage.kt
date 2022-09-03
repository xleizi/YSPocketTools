package com.lei123.YSPocketTools.ui

import ApiCst
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lei123.YSPocketTools.*
import com.lei123.YSPocketTools.AndroidTools.ClipBoard.sendToClipBoard
import com.lei123.YSPocketTools.AndroidTools.OpenApp
import com.lei123.YSPocketTools.AndroidTools.systemInformation.getVersion
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.ui.plugin.TimePicker
import com.lei123.YSPocketTools.utils.*


@Composable
fun settingPage(context: Context, viewModel: MainViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(YSComposeTheme.colors.background),
        verticalArrangement = Arrangement.Top
    ) {
        wakeUpApp(viewModel)
        cuiMing(viewModel)
        notice_select()
        ifToastselect()
        ifAutoSign_select()
        ifbottomBar()
        ifClickSign_select()
        timePickerItem()
        getHelp(context, viewModel)
        Row(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = "当前版本：" + getVersion(context),
                color = YSComposeTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
            )
            getVersion(context)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
private fun ColumnScope.ifToastselect() {
    var ifToast by remember { mutableStateOf(loadBoolean("ifToast", false)) }
    DefaultCard(
        text = getString(R.string.ifToast),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (ifToast) ifToast = false else ifToast = true
                            saveBoolean("ifToast", ifToast)
                            getString(R.string.SettingSuccess).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = ifToast,           //是否选中
                        onCheckedChange = {
                            ifToast = it
                            saveBoolean("ifToast", it)
                            getString(R.string.SettingSuccess).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.ifToast),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.ifbottomBar() {
    var ifbottomBar by remember { mutableStateOf(loadBoolean("ifbottomBar", false)) }
    DefaultCard(
        text = getString(R.string.ifbottomBar),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (ifbottomBar) ifbottomBar = false else ifbottomBar = true
                            saveBoolean("ifbottomBar", ifbottomBar)
                            getString(R.string.SettingSuccess).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = ifbottomBar,           //是否选中
                        onCheckedChange = {
                            ifbottomBar = it
                            saveBoolean("ifbottomBar", it)
                            getString(R.string.SettingSuccess).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.ifbottomBar),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.ifAutoSign_select() {
    var ifAutoSign by remember { mutableStateOf(loadBoolean("ifAutoSign", true)) }
    DefaultCard(
        text = getString(R.string.ifAutoSign),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (ifAutoSign) ifAutoSign = false else ifAutoSign = true
                            saveBoolean("ifAutoSign", ifAutoSign)
                            getString(R.string.SettingSuccess).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = ifAutoSign,           //是否选中
                        onCheckedChange = {
                            ifAutoSign = it
                            saveBoolean("ifAutoSign", it)
                            getString(R.string.SettingSuccess).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.ifAutoSign),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.ifClickSign_select() {
    var ifClickSign by remember { mutableStateOf(loadBoolean("ifClickSign", true)) }
    DefaultCard(
        text = getString(R.string.ifClickSign),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (ifClickSign) ifClickSign = false else ifClickSign = true
                            saveBoolean("ifClickSign", ifClickSign)
                            getString(R.string.SettingSuccess).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = ifClickSign,           //是否选中
                        onCheckedChange = {
                            ifClickSign = it
                            saveBoolean("ifClickSign", it)
                            getString(R.string.SettingSuccess).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.ifClickSign),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun timePickerItem() {
    var timePicker by remember { mutableStateOf(false) }
    Column {
        DefaultCard(
            //text = getString(R.string.timePicker),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clickable {
                    timePicker = timePicker != true
                },
        ) {
            Text(text = getString(R.string.timePicker))
            if (timePicker == true) {
                Column(Modifier.fillMaxWidth()) {
                    TimePicker { selected, hour, minute ->
                        //timePicker = !selected
                        /*val hourString = when(hour){
                                0,1,2,3,4,5,6,7,8,9 -> "0".plus(hour.toString())
                                else -> hour.toString()
                            }*/
                        val hourString = hour.toString()
                        val minuteString = when (minute) {
                            0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> "0".plus(minute.toString())
                            else -> minute.toString()
                        }
                        ("自定义签到时间为：$hourString 时 $minuteString 分").toast()
                        saveString("signTime", hourString + minuteString)
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.notice_select() {
    var noticeStyle by remember { mutableStateOf(loadString("noticeStyle",ApiCst.Notice_Style_HomeCoin)) }
    DefaultCard(
        text = getString(R.string.start_notice_select),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column {
            Row {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            noticeStyle = ApiCst.Notice_Style_HomeCoin
                            saveString("noticeStyle", noticeStyle)
                            getString(R.string.SettingPleaseRestart).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = noticeStyle == ApiCst.Notice_Style_HomeCoin,           //是否选中
                        onClick = {
                            noticeStyle = ApiCst.Notice_Style_HomeCoin
                            saveString("noticeStyle", noticeStyle)
                            getString(R.string.SettingPleaseRestart).toast()
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
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            noticeStyle = ApiCst.Notice_Style_Explore
                            saveString("noticeStyle", noticeStyle)
                            getString(R.string.SettingPleaseRestart).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = noticeStyle == ApiCst.Notice_Style_Explore,           //是否选中
                        onClick = {
                            noticeStyle = ApiCst.Notice_Style_Explore
                            saveString("noticeStyle", noticeStyle)
                            getString(R.string.SettingPleaseRestart).toast()
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
            Row {

                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            noticeStyle = ApiCst.Notice_Style_OnlyResin
                            saveString("noticeStyle", noticeStyle)
                            getString(R.string.SettingPleaseRestart).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = noticeStyle == ApiCst.Notice_Style_OnlyResin,           //是否选中
                        onClick = {
                            noticeStyle = ApiCst.Notice_Style_OnlyResin
                            saveString("noticeStyle", noticeStyle)
                            getString(R.string.SettingPleaseRestart).toast()
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
                        text = getString(R.string.onlyResin),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
    var noticeFix by remember { mutableStateOf(loadBoolean("noticeFix")) }
    DefaultCard(
        text = getString(R.string.noticeStyleTitle),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (noticeFix) noticeFix = false else noticeFix = true
                            saveBoolean("noticeFix", noticeFix)
                            getString(R.string.SettingPleaseRestart).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = noticeFix,           //是否选中
                        onCheckedChange = {
                            noticeFix = it
                            saveBoolean("noticeFix", it)
                            getString(R.string.SettingPleaseRestart).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.noticeStyleTitle),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.wakeUpApp(viewModel: MainViewModel) {
    var packageName by remember { mutableStateOf(loadString("packagename",ApiCst.APP_PACKAGE_NAME_GENSHIN)) }
    DefaultCard(
        text = getString(R.string.start_app_select),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column {
            Row {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
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
                        text = getString(R.string.genshin),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                }
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_CLOUD
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN_CLOUD,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_CLOUD
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
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
                        text = getString(R.string.yun_genshin),
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
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_Bilibili
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN_Bilibili,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_Bilibili
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
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
                        text = getString(R.string.Bgenshin),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                }
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_OS
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN_OS,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_OS
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
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
                        text = getString(R.string.OSgenshin),
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
                            packageName = ApiCst.APP_PACKAGE_NAME_BBS
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_BBS,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_BBS
                            saveString("packagename", packageName)
                            getString(R.string.SettingSuccess).toast()
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
                        text = getString(R.string.miyoushe),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.cuiMing(
    viewModel: MainViewModel
) {
    var cuiResinBool by remember { mutableStateOf(loadBoolean("cuiResinBool")) }
    var cuiHomeCoinBool by remember { mutableStateOf(loadBoolean("cuiHomeCoinBool")) }
    var cuiExploreBool by remember { mutableStateOf(loadBoolean("cuiExploreBool")) }

    DefaultCard(
        text = getString(R.string.cuiming),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (cuiResinBool) cuiResinBool = false else cuiResinBool = true
                            saveBoolean("cuiResinBool", cuiResinBool)
                            getString(R.string.SettingSuccess).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = cuiResinBool,           //是否选中
                        onCheckedChange = {
                            cuiResinBool = it
                            saveBoolean("cuiResinBool", it)
                            getString(R.string.SettingSuccess).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.resinCui),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (cuiHomeCoinBool) cuiHomeCoinBool = false else cuiHomeCoinBool =
                                true
                            saveBoolean("cuiHomeCoinBool", cuiHomeCoinBool)
                            getString(R.string.SettingSuccess).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = cuiHomeCoinBool,           //是否选中
                        onCheckedChange = {
                            cuiHomeCoinBool = it
                            saveBoolean("cuiHomeCoinBool", it)
                            getString(R.string.SettingSuccess).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.homecoinCui),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
                Row(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (cuiExploreBool) cuiExploreBool = false else cuiExploreBool =
                                true
                            saveBoolean("cuiExploreBool", cuiExploreBool)
                            getString(R.string.SettingSuccess).toast()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = cuiExploreBool,           //是否选中
                        onCheckedChange = {
                            cuiExploreBool = it
                            saveBoolean("cuiExploreBool", it)
                            getString(R.string.SettingSuccess).toast()
                        },                                          //点击事件
                        modifier = Modifier.padding(start = 2.dp),            //修饰符
                        enabled = true,                               //是否启用
                        colors = CheckboxDefaults.colors(
                            checkedColor = green1,                 //选中的颜色
                            uncheckedColor = black7,              //未选中的颜色
                            checkmarkColor = white                   //禁用的颜色
                        )
                    )
                    Text(
                        text = getString(R.string.expCui),
                        color = YSComposeTheme.colors.textPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ColumnScope.getHelp(
    context: Context,
    viewModel: MainViewModel
) {
    DefaultCard(
        text = getString(R.string.getHelp),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Column {
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        OpenApp.Guanzhubzhan(context, viewModel.bzhan)
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YSComposeTheme.colors.TextBold,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = getString(R.string.videohelp))
                }
                Button(
                    onClick = {
                        OpenApp.joinQQGroup(context, viewModel.update)
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YSComposeTheme.colors.TextBold,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = getString(R.string.qqhelp))
                }
            }
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        OpenApp.OpenWeiXin(context)
                        sendToClipBoard(context)
                        getString(R.string.weixinhelpclip).toast()
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YSComposeTheme.colors.TextBold,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = getString(R.string.weixinhelp))
                }
            }
        }
    }
}