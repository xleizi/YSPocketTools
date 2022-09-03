package com.lei123.YSPocketTools.ui

import ApiCst
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lei123.YSPocketTools.*
import com.lei123.YSPocketTools.AndroidTools.OpenApp
import com.lei123.YSPocketTools.AndroidTools.systemInformation
import com.lei123.YSPocketTools.AndroidTools.systemInformation.getVersion
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.*


@Composable
fun settingPage(context: Context, viewModel: MainViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .background(YSComposeTheme.colors.background),
        verticalArrangement = Arrangement.Top
    ) {
        wakeUpApp(viewModel)
        cuiMing(viewModel)
        notice_select()
        getHelp(context, viewModel)
        Row() {
            Text(
                text = "当前版本：" + getVersion(context),
                color = YSComposeTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
            )
            getVersion(context)
        }
    }
}

@Composable
private fun ColumnScope.notice_select() {
    var noticeStyle by remember { mutableStateOf(loadString("noticeStyle")) }
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
                            saveString("package_name", noticeStyle)
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
                            saveString("package_name", noticeStyle)
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
}

@Composable
private fun ColumnScope.wakeUpApp(viewModel: MainViewModel) {
    var packageName by remember { mutableStateOf(loadString("package_name")) }

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
                            saveString("package_name", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN
                            saveString("package_name", packageName)
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
                            saveString("package_name", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN_CLOUD,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_CLOUD
                            saveString("package_name", packageName)
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
                            saveString("package_name", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN_Bilibili,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_Bilibili
                            saveString("package_name", packageName)
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
                            saveString("package_name", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_GENSHIN_OS,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_GENSHIN_OS
                            saveString("package_name", packageName)
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
                            saveString("package_name", packageName)
                            getString(R.string.SettingSuccess).toast()
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = packageName == ApiCst.APP_PACKAGE_NAME_BBS,           //是否选中
                        onClick = {
                            packageName = ApiCst.APP_PACKAGE_NAME_BBS
                            saveString("package_name", packageName)
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
private fun ColumnScope.getHelp(
    context: Context,
    viewModel: MainViewModel
) {
    DefaultCard(
        text = getString(R.string.getHelp),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
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
    }
}