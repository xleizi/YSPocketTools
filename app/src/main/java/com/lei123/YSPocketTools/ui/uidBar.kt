package com.lei123.YSPocketTools.ui

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme

@Composable
fun uidBar(
    context: Context,
    viewModel: MainViewModel,
    copyCookie: () -> Unit,
) {
    Box(
        Modifier
            .padding(start = 12.dp, top = 15.dp, end = 12.dp)
            .height(70.dp)

    ) {
        Image(
            painterResource(R.drawable.lay_background_uid_light),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Row(
            Modifier
                .padding()
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .padding(start = 11.dp, top = 9.dp, bottom = 10.dp)
            ) {
                Image(
                    painterResource(R.drawable.icon_role_block_light),
                    contentDescription = "",
                )
                Box(
                    Modifier
                        .padding(start = 12.dp, top = 7.dp, bottom = 6.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = if (viewModel.ifLogin) rememberImagePainter(viewModel.miavatar_url) else painterResource(
                            R.drawable.role_z_kongbai
                        ),
                        modifier = Modifier.clickable {
                            copyCookie()
                        },
                        // painterResource(viewModel.userPhoto),
                        contentDescription = "",
                    )
                }
            }
            Column(
                Modifier.padding(start = 10.dp, top = 12.dp)
            ) {
                Text(
                    text = "uid:\t".plus(viewModel.mainUID),
                    Modifier.padding(
                        start = 12.dp
                    ),
                    color = YSComposeTheme.colors.textSecondary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    Modifier
                        .padding(top = 3.dp, bottom = 12.dp)
                        .width(166.dp)
                ) {
                    Image(
                        painterResource(R.drawable.lay_uid_background_light),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                    )
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,//设置水平居中对齐
                        verticalAlignment = Alignment.CenterVertically//设置垂直居中对齐
                    ) {
                        Text(
                            text = viewModel.usernickname,
                            //text = "viewModel.usernickname",
/*                                    modifier = Modifier.padding(
                                        start = 13.dp,
                                        top = 7.dp,
                                        bottom = 7.dp,
                                        end = 13.dp
                                    ).align(alignment = Alignment.CenterVertically),*/
                            color = YSComposeTheme.colors.textPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(viewModel.uidAddIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(
                            top = 18.dp,
                            bottom = 18.dp,
                            end = 19.dp
                        )
                        .clickable {
                            viewModel.LoginState = true
                            /*                           val intent = Intent(context, WebLoginActivity::class.java)
                                                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                       context.startActivity(intent)*/
                        }
                    //.aspectRatio(1f)
                )
            }
        }

    }
    var animReady by remember { mutableStateOf(false) }
    val background by animateColorAsState(
        if (UIDlistPageState > UIDlistState.Closed) Color(0xfff8f8f8) else Color.White,
        finishedListener = {
            if (UIDlistPageState == UIDlistState.Closing) {
                UIDlistPageState = UIDlistState.Closed
                animReady = false
            }
        })
    val cornerSize by animateDpAsState(if (UIDlistPageState > UIDlistState.Closed) 0.dp else 16.dp)
    val paddingSize by animateDpAsState(if (UIDlistPageState > UIDlistState.Closed) 10.dp else 6.dp)


    val offsetAnimatable = remember { Animatable(IntOffset(0, 0), IntOffset.VectorConverter) }
    val fullOffset = remember { IntOffset(0, 0) }

    LaunchedEffect(UIDlistPageState) {
        when (UIDlistPageState) {
            UIDlistState.Opening -> {
                animReady = true
                offsetAnimatable.snapTo(cardOffset)
                offsetAnimatable.animateTo(fullOffset)
            }
            UIDlistState.Closing -> {
                offsetAnimatable.snapTo(fullOffset)
                offsetAnimatable.animateTo(cardOffset)
            }
            else -> {}
        }
    }


}

var cardOffset by mutableStateOf(IntOffset(0, 0))

var UIDlistPageState by mutableStateOf(UIDlistState.Closed)

enum class UIDlistState {
    Closing, Closed, Opening, Open
}
