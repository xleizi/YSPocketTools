package com.lei123.YSPocketTools.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lei123.YSPocketTools.*
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.UNIT.toHexEncoding
import com.lei123.YSPocketTools.ui.plugin.ColorPicker
import com.lei123.YSPocketTools.utils.*
import com.lei123.test.ui.YSTopBar
import com.lei123.test.ui.widgetSettingLine
import kotlinx.coroutines.launch
import android.graphics.Color as AndroidColor


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainPage(context: Context, viewModel: MainViewModel) {
    Column() {
        val pagerState = rememberPagerState()
        YSTopBar(viewModel,true, getString(R.string.app_name))
        HorizontalPager(
            count = 4, Modifier.weight(1f),
            pagerState
        ) { page ->
            when (page) {
                0 -> HomePage(context, viewModel)
                1 -> Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = getString(R.string.comeingSoon))
                }
                2 -> menuPage(viewModel)
                3 -> settingPage(context, viewModel)
            }
        }
        val scope = rememberCoroutineScope() // 创建 CoroutineScope
        YSBottomBar(pagerState.currentPage) { page ->
            scope.launch {
                pagerState.animateScrollToPage(page)
            }
        }
    }
}


