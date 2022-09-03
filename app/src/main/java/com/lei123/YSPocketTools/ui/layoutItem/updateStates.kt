package com.lei123.YSPocketTools.ui.layoutItem

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lei123.YSPocketTools.AndroidTools.ClipBoard
import com.lei123.YSPocketTools.AndroidTools.OpenApp
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.ui.Zoom
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.toast

@Composable
fun updateStates(
    viewModel: MainViewModel,
    context: Context
) {
    if (viewModel.updateState) {
        Column {
            Row(
                Modifier
                    .padding(top = (20 * Zoom).dp, start = (12 * Zoom).dp),
                horizontalArrangement = Arrangement.Center,//设置水平居中对齐
                verticalAlignment = Alignment.CenterVertically, //设置垂直居中对齐
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    tint = YSComposeTheme.colors.textSecondary,
                    contentDescription = null,
                )
                Text(
                    text = getString(R.string.updateNotice) + "最新版本：" + viewModel.lastAppVertion,
                    Modifier.padding(start = (10 * Zoom).dp),
                    fontSize = (16 * Zoom).sp,
                    fontWeight = FontWeight.Bold,
                    //text = viewModel.fixed_realTimeNote,
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = (12 * Zoom).dp, top = (10 * Zoom).dp, end = (12 * Zoom).dp)
                    .height((190 * Zoom).dp)
                    .clickable(
                        onClick = {
                            OpenApp.OpenWeiXin(context)
                            ClipBoard.sendToClipBoard(context)
                            getString(R.string.weixinhelpclip).toast()
                        }
                    )
            ) {
                Image(
                    painterResource(R.drawable.lay_background_main_small),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(
                            top = (0 * Zoom).dp,
                            start = (13 * Zoom).dp,
                            bottom = (23 * Zoom).dp,
                            end = (13 * Zoom).dp
                        )
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "如未收到更新推送，请点击右上角检查更新，如果还不行请前往公众号“派蒙口袋工具箱”获取,点击此文本框可复制并跳转。" + "\n" + viewModel.notice1,
                        Modifier
                            .padding(
                                start = (10 * Zoom).dp,
                                top = (10 * Zoom).dp
                            ),
                        fontSize = (16 * Zoom).sp,
                        fontWeight = FontWeight.Bold,
                        //text = viewModel.fixed_realTimeNote,
                    )
                }
            }
        }
    }
}