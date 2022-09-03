package com.lei123.YSPocketTools.ui

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lei123.YSPocketTools.*
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.ui.layoutItem.updateStates
import com.lei123.YSPocketTools.utils.*
import com.lei123.YSPocketTools.utils.getWeaponName.GetWeaponName
import com.lei123.test.ui.YSTopBar
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainPage(context: Context, viewModel: MainViewModel) {

    Column() {
        val pagerState = rememberPagerState()
        YSTopBar(viewModel, true, getString(R.string.app_name))
        updateStates(viewModel, context)
        HorizontalPager(
            count = 4, Modifier.weight(1f),
            pagerState
        ) { page ->
            when (page) {
                0 -> HomePage(context, viewModel)
                1 -> secondPage(viewModel)
                2 -> menuPage(viewModel)
                3 -> settingPage2(context,viewModel)
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


@Composable
private fun secondPage(viewModel: MainViewModel) {
    val roleArray = viewModel.nameArray
    var line = roleArray.size / 4
    if (roleArray.size % 4 != 0) {
        line += 1
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(YSComposeTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            dataTitlebar(viewModel)
            itemsTitle(R.drawable.lay_data_summary_icon, getString(R.string.datasummary))
            Box(
                modifier = Modifier
                    .height(368.dp)
                    .padding(
                        start = (8 * Zoom).dp,
                        top = (0 * Zoom).dp,
                        bottom = (0 * Zoom).dp,
                        end = (8 * Zoom).dp,
                    ),
            ) {
                Image(
                    painter = painterResource(R.drawable.lay_background_main_light_320),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Column(
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 5.dp,
                            end = 5.dp
                        )
                ) {
                    //dataSummaryLine()
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.activityday))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.achievement_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.avatar_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.spiral_abyss))
                        }
                    }
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.total_chest_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.luxurious_chest_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.precious_chest_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exquisite_chest_number))
                        }
                    }
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.common_chest_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.magic_chest_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.way_point_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.domain_number))
                        }
                    }
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.anemoculus_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.geoculus_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.electroculus_number))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.cao_number))
                        }
                    }
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage1))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage2))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage4))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage8))
                        }
                    }
                    //dataSummaryLine()
                    /*Row {
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage3))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage5))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage6))
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            dataSummaryItem(viewModel, getString(R.string.exploration_percentage7))
                        }
                    }*/
                }
            }
            itemsTitle(R.drawable.lay_data_roles_icon, getString(R.string.myroles))
            Box(
                modifier = Modifier
                    .height((line * 165).dp)
                    .padding(
                        start = (8 * Zoom).dp,
                        top = (0 * Zoom).dp,
                        bottom = (0 * Zoom).dp,
                        end = (8 * Zoom).dp,
                    ),
            ) {
                /*Image(
                    modifier = Modifier
                        .height((line * 165).dp),
                    painter = painterResource(R.drawable.lay_background_main_light_320),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )*/
                Column(
                    modifier = Modifier
                        .padding(
                            start = (4 * Zoom).dp,
                            top = (3 * Zoom).dp,
                            bottom = (0 * Zoom).dp,
                            end = (3 * Zoom).dp,
                        ),
                ) {
                    dataSummaryLine()
                    var i = 0
                    for (l in 1..line)
                        Row {
                            for (a in 0..3) {
                                val roleName = roleArray.getOrElse(i) { "Unknown" }
                                Box(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    dataSummaryRoleItem(viewModel, roleName)
                                    i += 1
                                }
                            }
                        }
                    dataSummaryLine()
                }
            }
        }
    }
}

@Composable
fun dataSummaryLine() {
    Box(Modifier.padding(start = (10 * Zoom).dp, end = (10 * Zoom).dp)) {
        Image(
            painter = painterResource(R.drawable.lay_main_line1),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
private fun dataSummaryRoleItem(viewModel: MainViewModel, roleName: String) {
    if (roleName != "Unknown") {
        Box(
            modifier = Modifier
                .height(161.dp)
                .width(90.dp)
                .padding(
                    horizontal = 5.dp,
                    vertical = 8.dp
                ),
        ) {
            var role = imageUrlToBitmap(viewModel.imagemap.getOrElse(roleName) { "珊瑚宫心海" })
            var rolerarity = viewModel.raritymap.getOrElse(roleName) { "4" }
            var roleelement = viewModel.elementmap.getOrElse(roleName) { "Hydro" }
            var rolelevel = viewModel.levelmap.getOrElse(roleName) { "1" }
            var rolefetter = viewModel.fettermap.getOrElse(roleName) { "1" }
            var roleactived_constellation_num =
                viewModel.actived_constellation_nummap.getOrElse(roleName) { "0" }
            var Weaponicon =
                imageUrlToBitmap(viewModel.WeaponiconMap.getOrElse(roleName) { "珊瑚宫心海" })
            var Weaponname = viewModel.WeaponnameMap.getOrElse(roleName) { "" }
            var Weaponrarity = viewModel.WeaponrarityMap.getOrElse(roleName) { "2" }
            var Weaponlevel = viewModel.WeaponlevelMap.getOrElse(roleName) { "1" }
            var Weaponaffix_level = viewModel.Weaponaffix_levelMap.getOrElse(roleName) { "1" }
            var roleAlevel = viewModel.Talentlevel_currentAmap.getOrElse(roleName) { "0" }
            var roleElevel = viewModel.Talentlevel_currentEmap.getOrElse(roleName) { "0" }
            var roleQlevel = viewModel.Talentlevel_currentQmap.getOrElse(roleName) { "0" }

            val WeaponBackGround = when (Weaponrarity) {
                "5" -> R.drawable.lay_data_summary_icon_bg_weapon5
                "4" -> R.drawable.lay_data_summary_icon_bg_weapon4
                "3" -> R.drawable.lay_data_summary_icon_bg_weapon3
                else -> R.drawable.lay_data_summary_icon_bg_weaponempty
            }

            val roleBackGround = when (rolerarity) {
                "5" -> R.drawable.lay_data_summary_icon_bg_role5
                "4" -> R.drawable.lay_data_summary_icon_bg_role5
                else -> R.drawable.lay_data_summary_icon_bg_role5
            }
            val elementBackGround = when (roleelement) {
                "Hydro" -> R.drawable.lay_data_summary_icon_bg_shui
                "Electro" -> R.drawable.lay_data_summary_icon_bg_lei
                "Pyro" -> R.drawable.lay_data_summary_icon_bg_huo
                "Cryo" -> R.drawable.lay_data_summary_icon_bg_bing
                "Geo" -> R.drawable.lay_data_summary_icon_bg_yan
                "Anemo" -> R.drawable.lay_data_summary_icon_bg_feng
                else -> R.drawable.lay_data_summary_icon_bg_cao
            }

            val roleactived_constellation_background = when (roleactived_constellation_num) {
                "0" -> R.drawable.lay_data_summary_icon_ming0
                "1" -> R.drawable.lay_data_summary_icon_ming1
                "2" -> R.drawable.lay_data_summary_icon_ming2
                "3" -> R.drawable.lay_data_summary_icon_ming3
                "4" -> R.drawable.lay_data_summary_icon_ming4
                "5" -> R.drawable.lay_data_summary_icon_ming5
                "6" -> R.drawable.lay_data_summary_icon_ming6
                else -> R.drawable.lay_data_summary_icon_ming0
            }

            Image(
                painter = painterResource(R.drawable.lay_calendar_bar_icon),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(
                                start = (3 * Zoom).dp,
                                top = (3 * Zoom).dp,
                                bottom = (0 * Zoom).dp,
                                end = (0 * Zoom).dp,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .height(12.dp)
                                .width(12.dp),
                            painter = painterResource(elementBackGround),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(
                                start = (3 * Zoom).dp,
                                top = (43 * Zoom).dp,
                                bottom = (0 * Zoom).dp,
                                end = (0 * Zoom).dp,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(12.dp),
                            painter = painterResource(R.drawable.lay_data_summary_icon_bg_haogan),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = (0 * Zoom).dp,
                                    top = (0 * Zoom).dp,
                                    bottom = (0 * Zoom).dp,
                                    end = (0 * Zoom).dp,
                                ),
                            text = rolefetter,
                            color = Color.White,
                            fontSize = 8.sp,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = (0 * Zoom).dp,
                                top = (0 * Zoom).dp,
                                bottom = (0 * Zoom).dp,
                                end = (0 * Zoom).dp,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(54.dp),
                            painter = painterResource(roleBackGround),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                        if (role != null) {
                            Image(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(35.dp)),
                                bitmap = role.asImageBitmap(),
                                //painter = painterResource(R.drawable.role_z_kokomi),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(
                                start = (55 * Zoom).dp,
                                top = (1 * Zoom).dp,
                                bottom = (0 * Zoom).dp,
                                end = (0 * Zoom).dp,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .height(55.dp)
                                .width(18.dp),
                            painter = painterResource(roleactived_constellation_background),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = (3 * Zoom).dp,
                                top = (58 * Zoom).dp,
                                bottom = (0 * Zoom).dp,
                                end = (3 * Zoom).dp,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.lay_calendar_levelbar_icon),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "lv:$rolelevel",
                            Modifier,
                            color = YSComposeTheme.colors.textPrimary,
                            fontSize = (16 * Zoom).sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(horizontal = 5.dp)
                        .padding(
                            start = (0 * Zoom).dp,
                            top = (2 * Zoom).dp,
                            bottom = (0 * Zoom).dp,
                            end = (0 * Zoom).dp,
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Box(Modifier.weight(1f)) {
                            skill_Item("a", roleAlevel)
                        }
                        Box(Modifier.weight(1f)) {
                            skill_Item("e", roleElevel)
                        }
                        Box(Modifier.weight(1f)) {
                            skill_Item("q", roleQlevel)
                        }
                    }
                }
                Image(
                    modifier = Modifier
                        .height((5 * Zoom).dp)
                        .padding(
                            start = (5 * Zoom).dp,
                            top = (0 * Zoom).dp,
                            bottom = (0 * Zoom).dp,
                            end = (5 * Zoom).dp,
                        ),
                    painter = painterResource(R.drawable.lay_data_summary_line),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Row(
                    modifier = Modifier
                        .padding(
                            start = (2 * Zoom).dp,
                            top = (0 * Zoom).dp,
                            bottom = (0 * Zoom).dp,
                            end = (5 * Zoom).dp,
                        ),
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(WeaponBackGround),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )
                        if (Weaponicon != null) {
                            Image(
                                modifier = Modifier
                                    .size(25.dp),
                                bitmap = Weaponicon.asImageBitmap(),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .height(30.dp)
                            .padding(
                                top = 0.dp,
                                start = 3.dp,
                                end = 1.dp
                            ),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            //text = Weaponname,
                            text = GetWeaponName(Weaponname),
                            color = YSComposeTheme.colors.textPrimary,
                            fontSize = (7 * Zoom).sp,
                        )
                        Row(
                            modifier = Modifier
                                .padding(
                                    top = 3.dp,
                                    start = 0.dp,
                                    end = 0.dp
                                ),
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            Text(
                                text = "lv:$Weaponlevel",
                                color = YSComposeTheme.colors.textPrimary,
                                fontSize = (7 * Zoom).sp,
                            )
                            Box(
                                modifier = Modifier
                                    .padding(
                                        top = 0.dp,
                                        start = 2.dp,
                                        end = 0.dp
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    modifier = Modifier.size(12.dp),
                                    painter = painterResource(WeaponBackGround),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds
                                )
                                Text(
                                    text = Weaponaffix_level,
                                    color = Color.White,
                                    fontSize = (8 * Zoom).sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun skill_Item(type: String, roleAlevel: String) {
    val skillBackground = when (type) {
        "a" -> R.drawable.lay_data_summary_icon_bg_skilla
        "e" -> R.drawable.lay_data_summary_icon_bg_skille
        "q" -> R.drawable.lay_data_summary_icon_bg_skillq
        else -> R.drawable.lay_data_summary_icon_bg_skilla
    }
    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(skillBackground),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .padding(
                    start = (0 * Zoom).dp,
                    top = (11 * Zoom).dp,
                    bottom = (0 * Zoom).dp,
                    end = (0 * Zoom).dp,
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(10.dp),
                painter = painterResource(R.drawable.lay_data_summary_icon_bg_skillcircle),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = roleAlevel,
                color = Color.White,
                fontSize = (7 * Zoom).sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
fun dataSummaryItem(viewModel: MainViewModel, title: String) {
    val icon = getDataSummaryIcon(title)
    val content = getDataSummaryContent(viewModel, title)

    Box(
        modifier = Modifier
            .width(90.dp)
            .height(70.dp)
            .padding(
                horizontal = 2.dp,
                vertical = 5.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.lay_data_summary_item_bg),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .padding(
                    top = 2.dp,
                    start = 2.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(icon),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(end = 5.dp),
                    text = title,
                    color = YSComposeTheme.colors.topBottomText,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Image(
                modifier = Modifier
                    .height((5 * Zoom).dp)
                    .padding(
                        start = (5 * Zoom).dp,
                        top = (0 * Zoom).dp,
                        bottom = (0 * Zoom).dp,
                        end = (5 * Zoom).dp,
                    ),
                painter = painterResource(R.drawable.lay_data_summary_line),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Box(
                modifier = Modifier
                    .padding(end = 2.dp)
                    .padding(
                        horizontal = 2.dp,
                        vertical = 2.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .height((23 * Zoom).dp)
                        .width(78.dp),
                    painter = painterResource(R.drawable.lay_data_summary_itembar),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = content,
                    color = YSComposeTheme.colors.topBottomText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview
@Composable
private fun view2() {
    //secondPage()
}


