package com.lei123.YSPocketTools.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lei123.YSPocketTools.MainActivity
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.service.BaseService
import com.lei123.YSPocketTools.utils.application
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.saveString
import com.lei123.YSPocketTools.utils.toast
import com.lei123.YSPocketTools.webview.checkToken

@Composable
fun CookieInputDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (cookieRes:String) -> Unit,
) {
    val cookie = remember { mutableStateOf("") }
    val title = remember { mutableStateOf(getString(R.string.input_cookie)) }
    val showLoading = remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthInPx = with(LocalDensity.current) { screenWidth.toPx() }


    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Box(
                Modifier
                    .size(width = 290.dp, height = 250.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(R.drawable.lay_home_login_light),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Column(Modifier.padding(10.dp)) {
                    Text(
                        title.value,
                        modifier = Modifier
                            .padding(bottom = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    TextField(
                        value = cookie.value,
                        onValueChange = {
                            cookie.value = it
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                    )
                    Row(Modifier.padding(start = 10.dp, end = 10.dp)) {
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = onDismissRequest,
                            modifier = Modifier.padding(end = 10.dp)
                        ) {
                            Text(getString(R.string.cancel))
                        }
                        Button(onClick = {
                            val cookieRes = cookie.value.replace("\n", "")
                            onConfirm(cookieRes)
                        }) {
                            Text(getString(R.string.confirm))
                        }
                    }
                }
                if (showLoading.value) {
                    Box(Modifier.size(50.dp)) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
private fun testt(cookieRes: String) {
    if (checkToken(cookieRes)) {
        R.string.get_cookie_success.toast()
        saveString("LoginCookie", cookieRes)
        val intent = Intent(application, BaseService::class.java)
            .run {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        application.startService(intent)
        val intent2 = Intent(application, MainActivity::class.java)
            .run {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        application.startActivity(intent2)
    } else {
        R.string.get_cookie_failed.toast()
    }
}
