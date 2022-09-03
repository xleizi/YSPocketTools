package com.lei123.YSPocketTools.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.loadMainUID

@Composable
fun LoginDialog(
    viewModel: MainViewModel,
    onDismissRequest: () -> Unit,
    deleteMainUid: () -> Unit,
    changeMainUid: (rowIndex: Int) -> Array<String>,
    deleteUid: (rowIndex: Int) -> Array<String>,
    loginWeb: () -> Unit,
    inputCookieDialog: () -> Unit
) {
    var uidlist by remember { mutableStateOf(viewModel.getuidlist()) }
    Dialog(onDismissRequest = onDismissRequest) {
            Box(
                Modifier
                    .size(width = 290.dp, height = 272.dp),
            ) {
                Image(
                    painterResource(R.drawable.lay_home_login_light),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Column() {
                    Text(
                        text = getString(R.string.uidManager),
                        color = YSComposeTheme.colors.TextBold,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = getString(R.string.MainUID),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 5.dp, start = 15.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 6.dp, start = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (loadMainUID() != "123456789") "UID: ".plus(loadMainUID()) else getString(
                                R.string.pleaseLogin3
                            ),
                            color = YSComposeTheme.colors.TextBold,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.weight(1f))
                        Image(
                            painterResource(R.drawable.lay_uidlist_delete),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(top = 5.dp, end = 18.dp)
                                .align(Alignment.CenterVertically)
                                .size(25.dp)
                                .clickable(onClick = deleteMainUid),
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                    Text(
                        text = getString(R.string.uidLog),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 15.dp)
                    )
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = 2.dp, start = 10.dp, end = 10.dp)
                            .height(100.dp)
                    ) {
                        items(uidlist.size) { rowIndex ->
                            if (uidlist[rowIndex] != "123456789" && uidlist[rowIndex] != viewModel.mainUID) {
                                Box(
                                    Modifier
                                        .height(39.dp)
                                        .clickable(onClick = { uidlist = changeMainUid(rowIndex) })
                                ) {
                                    Image(
                                        painterResource(R.drawable.lay_home_uidlist_bar_light),
                                        contentDescription = "",
                                        modifier = Modifier.padding(top = 5.dp),
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
                                    Image(
                                        painterResource(R.drawable.lay_uidlist_delete),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(top = 5.dp, end = 8.dp)
                                            .align(Alignment.CenterEnd)
                                            .size(25.dp)
                                            .clickable {uidlist = deleteUid(rowIndex)},
                                        contentScale = ContentScale.FillBounds,
                                    )
                                }
                            }
                        }
                    }
                    Row {
                        Image(
                            painterResource(R.drawable.lay_home_loginbyweb),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = 10.dp, top = 5.dp, end = 8.dp)
                                .size(width = 130.dp, height = 48.dp)
                                .clickable(onClick = loginWeb),
                            contentScale = ContentScale.Fit,
                        )
                        Image(
                            painterResource(R.drawable.lay_home_loginbycookie),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(top = 5.dp, end = 8.dp)
                                .size(width = 130.dp, height = 48.dp)
                                .clickable(onClick = inputCookieDialog),
                            contentScale = ContentScale.Fit,
                        )
                    }
                }
            }
    }
}