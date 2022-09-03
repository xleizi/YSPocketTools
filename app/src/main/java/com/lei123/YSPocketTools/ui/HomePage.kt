package com.lei123.YSPocketTools.ui

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.lei123.YSPocketTools.AndroidTools.ClipBoard.sendstrToClipBoard
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
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
                viewModel = viewModel)
            homeNote(viewModel)
        }
    }
}

@Composable
fun homeNote(viewModel: MainViewModel) {
    Column {
        Row(
            Modifier
                .padding(top = (20 * Zoom).dp, start = (12 * Zoom).dp),
            horizontalArrangement = Arrangement.Center,//设置水平居中对齐
            verticalAlignment = Alignment.CenterVertically, //设置垂直居中对齐
        ) {
            Image(
                painterResource(R.drawable.lay_note_icon),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Text(
                text = getString(R.string.realTimeNote),
                Modifier.padding(start = (10 * Zoom).dp),
                fontSize = (16* Zoom).sp,
                fontWeight = FontWeight.Bold,
                //text = viewModel.fixed_realTimeNote,
            )
        }
        Box(
            modifier = Modifier
                .padding(start = (12 * Zoom).dp, top = (10 * Zoom).dp, end = (12 * Zoom).dp)
                .height((290 * Zoom).dp)
        ) {
            Image(
                painterResource(R.drawable.lay_background_main_light),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(Modifier.padding(start = (13 * Zoom).dp, end = (13 * Zoom).dp)) {
                main2Col1((13 * Zoom).dp, viewModel)
                main2Col2((12 * Zoom).dp, viewModel)
                main2Col3((12 * Zoom).dp, viewModel)
            }
        }
    }
}

@Composable
fun resinBox(viewModel: MainViewModel) {
    Box(
        Modifier
            .padding((10 * Zoom).dp)
            .fillMaxSize(),
    ) {
        BoxTitle(getString(R.string.resinTitle), viewModel.resinAim, viewModel)
        boxDecorateLine()
        Box(
            Modifier
                .padding(top = (32 * Zoom).dp)
                .fillMaxSize(),
        )
        {
            Image(
                painterResource(R.drawable.icon_resin),
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
            Box(modifier = Modifier.padding(start = (36 * Zoom).dp)) {
                Image(
                    painterResource(R.drawable.lay_bar_small_light),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = viewModel.resin1,
                    color = YSComposeTheme.colors.textPrimary,
                    fontSize = (16* Zoom).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun boxDecorateLine() {
    Box(Modifier.padding(top = (19 * Zoom).dp, bottom = (37 * Zoom).dp)) {
        Image(
            painterResource(R.drawable.lay_main_line1),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
fun homeCoinBox(viewModel: MainViewModel) {
    Box(
        Modifier
            .padding((10 * Zoom).dp)
            .fillMaxSize()
    ) {
        BoxTitle(getString(R.string.homeCoinTitle), viewModel.homeCoinAim, viewModel)
        boxDecorateLine()
        Box(
            Modifier
                .padding(top = (32 * Zoom).dp)
                .fillMaxSize()
        )
        {
            Image(
                painterResource(R.drawable.icon_homeicon),
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
            Box(modifier = Modifier.padding(start = (36 * Zoom).dp)) {
                Image(
                    painterResource(R.drawable.lay_bar_small_light),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = viewModel.homeCoin1,
                    color = YSComposeTheme.colors.textPrimary,
                    fontSize = (16* Zoom).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun dailyTaskBox(viewModel: MainViewModel) {
    Box(
        Modifier
            .padding((10 * Zoom).dp)
            .fillMaxSize()
    ) {
        BoxTitle(
            getString(R.string.dailyTaskTitle),
            if (viewModel.dailyTaskSubmit == getString(R.string.dailyTaskSubmit)) viewModel.dailyTaskSubmit else viewModel.dailyTaskStr,
            viewModel
        )
        boxDecorateLine()
        Row(Modifier
            .padding(start = (4 * Zoom).dp, top = (32 * Zoom).dp, end = (4 * Zoom).dp)
            .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(if (viewModel.dailyTask1) R.drawable.lay_task_full else R.drawable.lay_task_empty),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(
                Modifier
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(if (viewModel.dailyTask2) R.drawable.lay_task_full else R.drawable.lay_task_empty),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(
                Modifier
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(if (viewModel.dailyTask3) R.drawable.lay_task_full else R.drawable.lay_task_empty),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(
                Modifier
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(if (viewModel.dailyTask4) R.drawable.lay_task_full else R.drawable.lay_task_empty),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun exploreBox(viewModel: MainViewModel) {
    Box {
        Box(
            Modifier
                .padding((10 * Zoom).dp)
                .fillMaxSize()
        ) {
            BoxTitle(getString(R.string.exploreTitle), viewModel.ExpAim, viewModel)
            boxDecorateLine()
        }
        Box(
            modifier = Modifier
                .padding(
                    top = (43 * Zoom).dp,
                    start = (9 * Zoom).dp,
                    bottom = (11 * Zoom).dp,
                    end = (6 * Zoom).dp
                )
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box() {
                    roleItem(
                        viewModel,
                        viewModel.exploreAllSecond - viewModel.explore1.toInt(),
                        viewModel.explorePhotoUrlZheng1
                    )
                }
                Box() {
                    roleItem(
                        viewModel,
                        viewModel.exploreAllSecond - viewModel.explore2.toInt(),
                        viewModel.explorePhotoUrlZheng2
                    )
                }
                Box() {
                    roleItem(
                        viewModel,
                        viewModel.exploreAllSecond - viewModel.explore3.toInt(),
                        viewModel.explorePhotoUrlZheng3
                    )
                }
                Box() {
                    roleItem(
                        viewModel,
                        viewModel.exploreAllSecond - viewModel.explore4.toInt(),
                        viewModel.explorePhotoUrlZheng4
                    )
                }
                Box() {
                    roleItem(
                        viewModel,
                        viewModel.exploreAllSecond - viewModel.explore5.toInt(),
                        viewModel.explorePhotoUrlZheng5
                    )
                }
            }
        }
    }
}

@Composable
private fun roleItem(
    viewModel: MainViewModel,
    sweepInt: Int,
    role: String
) {
    val color1 = YSComposeTheme.colors.ProgressBarFull
    val color2 = YSComposeTheme.colors.textPrimary
    val strokWidth = 6F
    val angle = (sweepInt.toFloat() / viewModel.exploreAllSecond.toFloat()) * 360
    Canvas(
        modifier = Modifier
            .size((22 * Zoom).dp)
    ) {
        if (role != "Empty") {
            drawArc(
                Color.Gray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokWidth, cap = StrokeCap.Round),
            )
            drawArc(
                //color = Color.Green,
                //color = Color(0,0,0,0),
                //color = YSComposeTheme.colors.textPrimary,
                color = if (angle > 300f) color1 else Color.Green,
                startAngle = 270f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(strokWidth, cap = StrokeCap.Round),
            )
        }
    }
    Box(
        Modifier
            //.padding(1*_root_ide_package_.com.lei123.YSPocketTools(.ui.Zoom).dp)
            .clip(RoundedCornerShape((22 * Zoom).dp))
    ) {
        Image(
            painter = rememberImagePainter(role),
            modifier = Modifier.size((22 * Zoom).dp),
            //painter = if (role == "Empty") rememberImagePainter(role) else painterResource(R.drawable.role_z_kongbai),
            contentDescription = "",
        )
    }
}


@Composable
fun weeklyBox(viewModel: MainViewModel) {
    Box(
        Modifier
            .padding((10 * Zoom).dp)
            .fillMaxSize()
    ) {
        BoxTitle(getString(R.string.weeklyTitle), viewModel.weeklyStr, viewModel)
        boxDecorateLine()
        Row(Modifier
            .padding(start = (14 * Zoom).dp, top = (32 * Zoom).dp, end = (14 * Zoom).dp)
            .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .fillMaxHeight(),
            ) {
                Image(
                    painterResource(if (viewModel.weekly1) R.drawable.lay_week_full else R.drawable.lay_week_empty),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                )
            }
            Box(
                Modifier
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(if (viewModel.weekly2) R.drawable.lay_week_full else R.drawable.lay_week_empty),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(
                Modifier
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(if (viewModel.weekly3) R.drawable.lay_week_full else R.drawable.lay_week_empty),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}

@Composable
fun transBox(viewModel: MainViewModel) {

    Box(
        Modifier
            .padding((10 * Zoom).dp)
            .fillMaxSize()
    ) {
        BoxTitle(getString(R.string.transTitle), "", viewModel)
        boxDecorateLine()
        Box(
            Modifier
                .padding(top = (32 * Zoom).dp)
                .fillMaxSize()
        )
        {
            if (viewModel.transobtained) {
                Image(
                    painterResource(R.drawable.icon_transfor),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                )
                Box(modifier = Modifier.padding(start = (36 * Zoom).dp)) {
                    Image(
                        painterResource(R.drawable.lay_bar_small_light),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                    )
                    Text(
                        text = if (!viewModel.transviable) viewModel.transRemain else getString(R.string.TranAvailable),
                        color = YSComposeTheme.colors.textPrimary,
                        fontSize = (16* Zoom).sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun main2Col1(top: Dp, viewModel: MainViewModel) {
    Row(
        Modifier
            .padding(top = top)
            .height((80 * Zoom).dp)
            .fillMaxSize()
    ) {
        Box(Modifier.weight(1f)) {
            main2Background()
            resinBox(viewModel)
        }
        Box(
            Modifier
                .weight(1f)
                .padding(start = (8 * Zoom).dp)
        ) {
            main2Background()
            homeCoinBox(viewModel)
        }
    }
}

@Composable
private fun main2Col2(top: Dp, viewModel: MainViewModel) {
    Row(
        Modifier
            .padding(top = top)
            .height((80 * Zoom).dp)
            .fillMaxSize()
    ) {
        Box(Modifier.weight(1f)) {
            main2Background()
            dailyTaskBox(viewModel)
        }
        Box(
            Modifier
                .weight(1f)
                .padding(start = (8 * Zoom).dp)
        ) {
            main2Background()
            exploreBox(viewModel)
        }
    }
}

@Composable
private fun main2Col3(top: Dp, viewModel: MainViewModel) {
    Row(
        Modifier
            .padding(top = top)
            .height((80 * Zoom).dp)
            .fillMaxSize()
    ) {
        Box(Modifier.weight(1f)) {
            main2Background()
            weeklyBox(viewModel)
        }
        Box(
            Modifier
                .weight(1f)
                .padding(start = (8 * Zoom).dp)
        ) {
            main2Background()
            transBox(viewModel)
        }
    }
}

@Composable
private fun BoxTitle(
    title1: String,
    title2: String,
    viewModel: MainViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = (43 * Zoom).dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title1,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = YSComposeTheme.colors.textPrimary,
            fontSize = (14* Zoom).sp,
            fontWeight = FontWeight.Bold,

        )
        Row(
            //modifier = Modifier.background(Color.Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = title2,
                modifier = Modifier.align(Alignment.CenterVertically),
                color = if (title2 != getString(R.string.NotAvailable)) YSComposeTheme.colors.textPrimary else YSComposeTheme.colors.textEmpty,
                fontSize = (10 * Zoom).sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun main2Background() {
    Image(
        painterResource(R.drawable.lay_main2_light),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
    )
}


@Preview
@Composable
fun topBarPre() {
    var viewModel: MainViewModel = viewModel()
    viewModel.uidAddIcon = R.drawable.lay_uid_add_light
    viewModel.usernickname = "点击加号登陆→→→→"
    viewModel.mainUID = "000000000"

    //uidBar(viewModel)
}

@Preview
@Composable
fun homeNotePre() {
    var viewModel: MainViewModel = viewModel()
    homeNote(viewModel)
}

enum class LovePageState {
    Closing, Closed, Opening, Open
}