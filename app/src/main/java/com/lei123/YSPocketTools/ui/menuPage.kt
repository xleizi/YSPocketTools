package com.lei123.YSPocketTools.ui

import ApiCst
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lei123.YSPocketTools.AndroidTools.home_Sign
import com.lei123.YSPocketTools.AndroidTools.ignoreBatteryOptimization
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.utils.application
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.launchApp
import com.lei123.YSPocketTools.utils.toast

@Composable
fun menuPage(viewModel: MainViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .background(YSComposeTheme.colors.background),
        verticalArrangement = Arrangement.Top
    ) {
        DefaultCard(
            text = getString(R.string.start_app),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            JumpAppView(
                onClickGenshin = { application.launchApp(ApiCst.APP_PACKAGE_NAME_GENSHIN) },
                onClickBGenshin = { application.launchApp(ApiCst.APP_PACKAGE_NAME_GENSHIN_Bilibili) },
                onClickCloud = { application.launchApp(ApiCst.APP_PACKAGE_NAME_GENSHIN_CLOUD) },
                onClickBBS = { application.launchApp(ApiCst.APP_PACKAGE_NAME_BBS) },
                onClickOS = { application.launchApp(ApiCst.APP_PACKAGE_NAME_GENSHIN_OS) },
            )
        }

        DefaultCard(
            text = getString(R.string.ignoreBatteryOptimization),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        try {
                            ignoreBatteryOptimization()
                        }catch (e:Exception){
                            getString(R.string.getError).toast()
                        }
                        //home_Sign(viewModel)
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YSComposeTheme.colors.TextBold,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = getString(R.string.ignoreBatteryOptimization))
                }
            }
        }
        DefaultCard(
            text = getString(R.string.Exit),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        System.exit(0)
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YSComposeTheme.colors.TextBold,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = getString(R.string.Exit))
                }
            }
        }
    }
}

@Composable
fun JumpAppView(
    onClickGenshin: () -> Unit,
    onClickBGenshin: () -> Unit,
    onClickCloud: () -> Unit,
    onClickBBS: () -> Unit,
    onClickOS: () -> Unit
) {
    Column {
        Row(Modifier.fillMaxWidth()) {
            Button(
                onClick = onClickGenshin,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = YSComposeTheme.colors.TextBold,
                    contentColor = Color.White
                )
            ) {
                Text(text = getString(R.string.genshin))
            }
            Button(
                onClick = onClickCloud,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = YSComposeTheme.colors.TextBold,
                    contentColor = Color.White
                )
            ) {
                Text(text = getString(R.string.yun_genshin))
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Button(
                onClick = onClickBGenshin,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = YSComposeTheme.colors.TextBold,
                    contentColor = Color.White
                )
            ) {
                Text(text = getString(R.string.Bgenshin))
            }
            Button(
                onClick = onClickOS,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = YSComposeTheme.colors.TextBold,
                    contentColor = Color.White
                )
            ) {
                Text(text = getString(R.string.OSgenshin))
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Button(
                onClick = onClickBBS,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = YSComposeTheme.colors.TextBold,
                    contentColor = Color.White
                )
            ) {
                Text(text = getString(R.string.miyoushe))
            }
        }
    }
}