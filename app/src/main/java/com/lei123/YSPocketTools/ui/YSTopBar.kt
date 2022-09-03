package com.lei123.test.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson
import com.lei123.YSPocketTools.AndroidTools.home_Sign
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.entity.getSignResult
import com.lei123.YSPocketTools.http.HTTPs.SignHttp.postSignHTTP
import com.lei123.YSPocketTools.http.HTTPs.SignHttp.signAward
import com.lei123.YSPocketTools.http.HTTPs.SignHttp.signInfo
import com.lei123.YSPocketTools.ui.Notification.startBigTaxtNotification
import com.lei123.YSPocketTools.utils.*

@Composable
fun YSTopBar(
    viewModel: MainViewModel,
    rightIcon:Boolean,
    title: String,
    onBack: (() -> Unit)? = null
) {
    Box(
        Modifier
            .background(YSComposeTheme.colors.bottomBar)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .height(48.dp)
        ) {
            if (onBack != null) {
                Icon(
                    painterResource(R.drawable.lay_home_setting_on_ic),
                    null,
                    Modifier
                        .clickable(onClick = onBack)
                        .align(Alignment.CenterVertically)
                        .size(36.dp)
                        .padding(8.dp),
                    tint = YSComposeTheme.colors.icon
                )
            }
            Spacer(Modifier.weight(1f))
            val viewModel: MainViewModel = viewModel()
            if (rightIcon) {
                Icon(
                    painterResource(R.drawable.lay_topbarbutton_light),
                    "设置页面",
                    Modifier
                        .clickable {
                            viewModel.settingBoxState = true
                        }
                        .align(Alignment.CenterVertically)
                        .size(36.dp)
                        .padding(8.dp),
                    tint = YSComposeTheme.colors.topBottomText
                )
            }
        }
        Text(
            title,
            Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = YSComposeTheme.colors.topBottomText
        )
    }
}

@Composable
fun settingDialog(
    viewModel: MainViewModel,
    onDismissRequest: () -> Unit,
    onSignRequest: () -> Unit,
) {
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
                .padding(top = 40.dp, end = 20.dp)
                .size(100.dp, 160.dp)
                //.size(120.dp, 160.dp)
                .clickable(onClick = {

                })
        ) {
            Image(
                painterResource(R.drawable.lay_background_main_light),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                Modifier
                    .padding(top = 3.dp, bottom = 3.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp)
                        .weight(1f)
                        .clickable(onClick = {
                            onSignRequest()

                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getString(R.string.sign),
                        textAlign = TextAlign.Justify,
                    )
                }
                widgetSettingLine()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(34.dp)
                        .clickable(onClick = {

                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getString(R.string.comeingSoon),
                        textAlign = TextAlign.Justify,
                    )
                }
                widgetSettingLine()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(34.dp)
                        .clickable(onClick = {

                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getString(R.string.comeingSoon),
                        textAlign = TextAlign.Justify,
                    )
                }
                widgetSettingLine()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(34.dp)
                        .clickable(onClick = {

                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getString(R.string.comeingSoon),
                        textAlign = TextAlign.Justify,
                    )
                }
            }
        }
    }
}


@Composable
fun widgetSettingLine() {
    Image(
        painterResource(R.drawable.lay_main_line1),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds,
    )
}