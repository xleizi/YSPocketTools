package com.lei123.YSPocketTools.ui

import android.content.Context
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lei123.YSPocketTools.AndroidTools.ClipBoard
import com.lei123.YSPocketTools.AndroidTools.DensityUtil.px2dip
import com.lei123.YSPocketTools.AndroidTools.OpenApp
import com.lei123.YSPocketTools.AndroidTools.systemInformation
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.utils.*
import com.lei123.YSPocketTools.utils.Constant.Companion.commonHelp
import com.lei123.YSPocketTools.utils.Constant.Companion.updateMessage
import com.lei123.test.ui.widgetSettingLine
import com.tencent.bugly.beta.Beta


@Composable
fun settingPage2(context: Context, viewModel: MainViewModel) {
    var scState = rememberScrollState()
    viewModel.settingscheight = px2dip(scState.value.toFloat())
    Log.i("scState", scState.value.toString())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scState)
            .background(YSComposeTheme.colors.background),
    ) {
        Column {
            settingTitleBar(getString(R.string.function))

            settingContent(
                viewModel,
                getString(R.string.start_app_select),
                loadString("packagename", getString(R.string.genshin)),
                95,
                1
            ) {
                viewModel.settingToastType = 1
                viewModel.settingDialogKey = "packagename"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.genshin),
                    getString(R.string.Bgenshin),
                    getString(R.string.yun_genshin),
                    getString(R.string.miyoushe),
                    getString(R.string.OSgenshin)
                )
            }

            settingContent(
                viewModel,
                getString(R.string.start_notice_select),
                loadString("noticeStyle", getString(R.string.ResinAndExp)),
                145,
                2
            ) {
                viewModel.settingToastType = 2
                viewModel.settingDialogKey = "noticeStyle"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.ResinAndExp),
                    getString(R.string.ResinAndHomecoin),
                    getString(R.string.onlyResin),
                )
            }

            settingContent(
                viewModel,
                getString(R.string.ifAutoSign),
                loadString("ifAutoSign", getString(R.string.Yes)),
                199,
                3
            ) {
                getString(R.string.autoSign).toast()
                viewModel.settingToastType = 1
                viewModel.settingDialogKey = "ifAutoSign"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.Yes),
                    getString(R.string.No),
                )
            }

            settingContent(
                viewModel,
                getString(R.string.ifClickSign),
                loadString("ifClickSign", getString(R.string.Yes)),
                251,
                4
            ) {
                viewModel.settingToastType = 1
                viewModel.settingDialogKey = "ifClickSign"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.Yes),
                    getString(R.string.No),
                )
            }

            settingContent(
                viewModel,
                getString(R.string.refreshTime),
                loadString("refreshTime", getString(R.string.minute8)),
                304,
                5
            ) {
                getString(R.string.refreshTip).toast()
                viewModel.settingToastType = 2
                viewModel.settingDialogKey = "refreshTime"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.minute1),
                    getString(R.string.minute2),
                    getString(R.string.minute3),
                    getString(R.string.minute5),
                    getString(R.string.minute8),
                    getString(R.string.minute10),
                    getString(R.string.minute15),
                    getString(R.string.minute20),
                    getString(R.string.minute30),
                )
            }


            settingTitleBar(getString(R.string.cuiming))
            settingContent(
                viewModel,
                getString(R.string.resinCui),
                loadString("cuiResinBool", getString(R.string.close)),
                403,
                6
            ) {
                viewModel.settingToastType = 1
                viewModel.settingDialogKey = "cuiResinBool"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.open),
                    getString(R.string.close)
                )
            }

            settingContent(
                viewModel,
                getString(R.string.homecoinCui),
                loadString("cuiHomeCoinBool", getString(R.string.close)),
                456,
                7
            ) {
                viewModel.settingToastType = 1
                getString(R.string.SettingSuccess).toast()
                viewModel.settingDialogKey = "cuiHomeCoinBool"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.open),
                    getString(R.string.close)
                )
            }

            settingContent(
                viewModel,
                getString(R.string.expCui),
                loadString("cuiExploreBool", getString(R.string.close)),
                508,
                8
            ) {
                viewModel.settingToastType = 1
                viewModel.settingDialogKey = "cuiExploreBool"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.open),
                    getString(R.string.close)
                )
            }

            settingTitleBar(getString(R.string.showSetting))
            settingContent(
                viewModel,
                getString(R.string.ifbottomBar),
                loadString("ifbottomBar", getString(R.string.No)),
                608,
                9
            ) {
                viewModel.settingToastType = 2
                viewModel.settingDialogKey = "ifbottomBar"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.Yes),
                    getString(R.string.No),
                )
            }

            settingContent(
                viewModel,
                getString(R.string.ifToast),
                loadString("ifToast", getString(R.string.Yes)),
                660,
                10
            ) {
                viewModel.settingToastType = 2
                viewModel.settingDialogKey = "ifToast"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.Yes),
                    getString(R.string.No),
                )
            }
            settingContent(
                viewModel,
                getString(R.string.ifToast),
                loadString("ifToast", getString(R.string.Yes)),
                660,
                10
            ) {
                viewModel.settingToastType = 2
                viewModel.settingDialogKey = "ifToast"
                viewModel.settingDialogItems = arrayOf(
                    getString(R.string.Yes),
                    getString(R.string.No),
                )
            }

            settingTitleBar(getString(R.string.getHelp))
            settingContentWOutItems(
                getString(R.string.updateTitle2),
                ""
            ){
                viewModel.messageExpendState = true
                viewModel.messageContentTitle = getString(R.string.updateTitle)
                viewModel.messageContentText = updateMessage
            }

            settingContentWOutItems(
                getString(R.string.commonTitle2),
                ""
            ){
                viewModel.messageExpendState = true
                viewModel.messageContentTitle = getString(R.string.commonTitle)
                viewModel.messageContentText = commonHelp
            }

            settingContentWOutItems(
                getString(R.string.videohelp),
                ""
            ){
                OpenApp.Guanzhubzhan(context, viewModel.bzhan)
            }

            settingContentWOutItems(
                getString(R.string.qqhelp),
                ""
            ){
                OpenApp.joinQQGroup(context, viewModel.update)
            }

            settingContentWOutItems(
                getString(R.string.weixinhelp),
                ""
            ){
                OpenApp.OpenWeiXin(context)
                ClipBoard.sendToClipBoard(context)
                getString(R.string.weixinhelpclip).toast()
            }

            //getHelp(context, viewModel)
            /*Column {
                Row(Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            OpenApp.Guanzhubzhan(context, viewModel.bzhan)
                        },
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = YSComposeTheme.colors.topBottomText,
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
                            .padding(horizontal = 20.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = YSComposeTheme.colors.topBottomText,
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
                            ClipBoard.sendToClipBoard(context)
                            getString(R.string.weixinhelpclip).toast()
                        },
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = YSComposeTheme.colors.topBottomText,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = getString(R.string.weixinhelp))
                    }
                }
            }*/

            settingTitleBar(getString(R.string.version))
            Row(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = "当前版本：v" + systemInformation.getVersion(context),
                    color = YSComposeTheme.colors.textPrimary,
                    textAlign = TextAlign.Center,
                )
                systemInformation.getVersion(context)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    AnimatedVisibility(
        viewModel.messageExpendState,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        messageContent(viewModel)
    }

    AnimatedVisibility(
        viewModel.settingExpendState,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        settingSelectDialog(viewModel, viewModel.settingToastType) {
            viewModel.settingExpendState = false
        }
    }
}

@Composable
private fun messageContent(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .height(700.dp)
            .clickable {
                viewModel.messageExpendState = false
            }
            .fillMaxWidth()
            .background(YSComposeTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
        ) {
            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Text(
                    text = viewModel.messageContentTitle,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
                Text(
                    text = viewModel.messageContentText,
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
private fun settingContentWOutItems(
    settingtextTitle: String,
    itemSelectName: String,
    onSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 15.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(360.dp, 42.dp),
            painter = painterResource(R.drawable.lay_setting_item_background),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Box(modifier = Modifier.width(250.dp)) {
            Image(
                modifier = Modifier
                    .padding(start = 128.dp)
                    .width(200.dp)
                    .height(42.dp),
                painter = painterResource(R.drawable.lay_setting_item_middle),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
        }
        Image(
            modifier = Modifier
                .padding(start = 230.dp)
                .size(127.dp, 42.dp),
            painter = painterResource(R.drawable.lay_setting_item_select),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Box(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = settingtextTitle,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = YSComposeTheme.colors.TextBold,
            )
        }
        Box(
            modifier = Modifier
                .clickable {
                    onSelected()
                }
                .fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(modifier = Modifier.padding(end = 43.dp)) {
                Text(
                    text = itemSelectName,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = YSComposeTheme.colors.TextBold,
                )
            }
            Image(
                modifier = Modifier
                    .size(42.dp)
                    .rotate(270f)
                    .padding(
                        start = 0.dp,
                        top = 0.dp
                    ),
                painter = painterResource(R.drawable.lay_setting_item_selectarray_unselected),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

@Composable
private fun settingContent(
    viewModel: MainViewModel,
    settingtextTitle: String,
    itemSelectName: String,
    expendTopPlace: Int,
    itemplace: Int,
    onSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 15.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        var selectArray = 0f
        if (viewModel.settingExpendState) {
            if (viewModel.settingExpendSelectItem == itemplace) {
                selectArray = 270f
            }
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(360.dp, 42.dp),
            painter = painterResource(R.drawable.lay_setting_item_background),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Box(modifier = Modifier.width(250.dp)) {
            Image(
                modifier = Modifier
                    .padding(start = 128.dp)
                    .width(200.dp)
                    .height(42.dp),
                painter = painterResource(R.drawable.lay_setting_item_middle),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
        }
        Image(
            modifier = Modifier
                .padding(start = 230.dp)
                .size(127.dp, 42.dp),
            painter = painterResource(R.drawable.lay_setting_item_select),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Box(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = settingtextTitle,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = YSComposeTheme.colors.TextBold,
            )
        }
        Box(
            modifier = Modifier
                .clickable {
                    onSelected()
                    viewModel.settingExpendSelectItem = itemplace
                    viewModel.settingExpendState = true
                    viewModel.settingExpendHeight = expendTopPlace - viewModel.settingscheight
                }
                .fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(modifier = Modifier.padding(end = 43.dp)) {
                Text(
                    text = itemSelectName,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = YSComposeTheme.colors.TextBold,
                )
            }
            Image(
                modifier = Modifier
                    .size(42.dp)
                    .rotate(selectArray)
                    .padding(
                        start = 0.dp,
                        top = 0.dp
                    ),
                painter = painterResource(R.drawable.lay_setting_item_selectarray_unselected),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}


@Composable
fun settingSelectDialog(
    viewModel: MainViewModel,
    toastType: Int,
    onDismissRequest: () -> Unit,
) {
    val itemcount = viewModel.settingDialogItems.size
    var packageName by remember {
        mutableStateOf(
            loadString(
                viewModel.settingDialogKey,
                ApiCst.APP_PACKAGE_NAME_GENSHIN
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable(onClick = {
                onDismissRequest()
            }),
        contentAlignment = Alignment.TopEnd
    ) {
        Box(
            Modifier
                .padding(top = viewModel.settingExpendHeight.dp, end = 20.dp)
                .clickable(onClick = {

                })
        ) {
            Box(
                modifier = Modifier
                    .size(127.dp, (35 * itemcount).dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .size(127.dp, (35 * itemcount).dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(YSComposeTheme.colors.settingBackground)
                )
                Column {
                    for (item in viewModel.settingDialogItems) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(35.dp)
                                .clickable {
                                    if (toastType == 1) getString(R.string.SettingSuccess).toast() else getString(
                                        R.string.SettingPleaseRestart
                                    ).toast()
                                    onDismissRequest()
                                    packageName = item
                                    saveString(viewModel.settingDialogKey, item)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (packageName == item) {
                                Image(
                                    modifier = Modifier
                                        .size(width = 125.dp, height = 34.dp),
                                    painter = painterResource(R.drawable.lay_setting_item_select_selected),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds,
                                )
                            }
                            Text(
                                text = item,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun settingTitleBar(text: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .padding(top = 5.dp)
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = YSComposeTheme.colors.topBottomText,
        )
    }
}
