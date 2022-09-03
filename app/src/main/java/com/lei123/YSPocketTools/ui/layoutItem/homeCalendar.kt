package com.lei123.YSPocketTools.ui.layoutItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.ui.Zoom
import com.lei123.YSPocketTools.ui.dataSummaryLine
import com.lei123.YSPocketTools.ui.itemsTitle
import com.lei123.YSPocketTools.ui.skill_Item
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.imageUrlToBitmap

@Composable
fun homeCalendar(viewModel: MainViewModel) {
    val roleArray = viewModel.getweekArray()
    itemsTitle(R.drawable.lay_calendar_icon, getString(R.string.calendarNote))
    var line = roleArray.size / 4
    if (roleArray.size % 4 != 0) {
        line += 1
    }

    Box(
        modifier = Modifier
            .height((line * 140).dp)
            .padding(start = (12 * Zoom).dp, end = (12 * Zoom).dp)
        //.height((290 * Zoom).dp)
    ) {
        /*Image(
            modifier = Modifier
                .height((line * 135).dp),
            painter = painterResource(R.drawable.lay_background_main_light_320),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )*/
        //Log.i("roleArraySize", roleArray.size.toString())

        Column(
            Modifier
                .padding(
                    start = (0 * Zoom).dp,
                    top = (8 * Zoom).dp,
                    bottom = (10 * Zoom).dp,
                    end = (8 * Zoom).dp,
                )
            //.verticalScroll(rememberScrollState())
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
                            homeRoleItem(viewModel, roleName)
                            i += 1
                        }
                    }
                }
            dataSummaryLine()
        }
    }
}

@Composable
private fun homeRoleItem(viewModel: MainViewModel, roleName: String) {
    if (roleName != "Unknown") {
        Box(
            modifier = Modifier
                .height(125.dp)
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
            var roleAlevel = viewModel.Talentlevel_currentAmap.getOrElse(roleName) { "0" }
            var roleElevel = viewModel.Talentlevel_currentEmap.getOrElse(roleName) { "0" }
            var roleQlevel = viewModel.Talentlevel_currentQmap.getOrElse(roleName) { "0" }

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
            }
        }
    }
}