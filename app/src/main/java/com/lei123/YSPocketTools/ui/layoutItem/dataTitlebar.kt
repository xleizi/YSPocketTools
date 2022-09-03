package com.lei123.YSPocketTools.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.lei123.YSPocketTools.AndroidTools.TimeCounter
import com.lei123.YSPocketTools.AndroidTools.getServer
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.imageUrlToBitmap

@Composable
fun dataTitlebar(viewModel: MainViewModel) {
    var backgroundimage = imageUrlToBitmap(viewModel.backgroundimage)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(186.dp)
    ) {
        if (backgroundimage != null) {
            Image(
                bitmap = backgroundimage.asImageBitmap(),
                modifier = Modifier
                    .fillMaxWidth(),
                /*painter = rememberImagePainter(
                    data = viewModel.backgroundimage,
                    builder = {
                        placeholder(R.drawable.lay_datapage_title_background)//占位图
                    }),*/
                contentDescription = "",
                alpha = 0.8f,
                contentScale = ContentScale.Crop,
            )
        }
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Image(

                    modifier = Modifier
                        .height(90.dp)
                        .width(98.dp)
                        .padding(top = 22.dp, start = 30.dp)
                        .clip(RoundedCornerShape(35.dp)),
                    painter = rememberImagePainter(
                        data = viewModel.miavatar_url,
                        builder = {
                            placeholder(R.drawable.role_z_kokomi)//占位图
                        }),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )

                Box(
                    modifier = Modifier
                        .height(42.dp)
                        .width(117.dp)
                        .padding(top = 25.dp, start = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.lay_uid_background_data),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                    )
                    Text(
                        text = "UID\t".plus(viewModel.mainUID),
                        color = YSComposeTheme.colors.background,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 19.dp, start = 30.dp, end = 34.dp)
            ) {
                Row {
                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Image(
                            modifier = Modifier
                                .height(26.dp),
                            painter = painterResource(R.drawable.lay_text_background_data),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                        Text(
                            text = viewModel.usernickname,
                            color = YSComposeTheme.colors.background,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 26.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Image(
                            modifier = Modifier
                                .height(19.dp),
                            painter = painterResource(R.drawable.lay_text_background_data),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                        Row {
                            Text(
                                text = getString(R.string.level3),
                                color = YSComposeTheme.colors.background,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = viewModel.level,
                                color = YSComposeTheme.colors.background,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                ) {
                    Row {
                        Text(
                            text = getString(R.string.server2),
                            color = YSComposeTheme.colors.background,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = getServer.get_server2(viewModel.mainUID),
                            color = YSComposeTheme.colors.background,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Image(
                            modifier = Modifier
                                .height(19.dp),
                            painter = painterResource(R.drawable.lay_text_background_data),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                        Row {
                            Text(
                                text = getString(R.string.travelTime),
                                color = YSComposeTheme.colors.background,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = TimeCounter.TravelTime(viewModel.active_day_number),
                                color = YSComposeTheme.colors.background,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}