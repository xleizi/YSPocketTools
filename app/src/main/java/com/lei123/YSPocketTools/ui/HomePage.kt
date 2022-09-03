package com.lei123.YSPocketTools.ui

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lei123.YSPocketTools.AndroidTools.ClipBoard.sendstrToClipBoard
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.ui.layoutItem.homeCalendar
import com.lei123.YSPocketTools.ui.layoutItem.uidBar
import com.lei123.YSPocketTools.utils.*

var Zoom = 1

@Composable
fun HomePage(context: Context, viewModel: MainViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .background(YSComposeTheme.colors.background)
    ) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            uidBar(
                copyCookie = {
                    sendstrToClipBoard(application, loadMainCookie())
                    getString(R.string.copy_cookie_success).toast()
                },
                context = context,
                viewModel = viewModel
            )
            homeNote(viewModel)
            homeCalendar(viewModel)
        }
    }
}



enum class LovePageState {
    Closing, Closed, Opening, Open
}