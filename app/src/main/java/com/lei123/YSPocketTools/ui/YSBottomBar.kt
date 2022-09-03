package com.lei123.YSPocketTools

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YSBottomBar(selectedId: Int, onSelectedChanged: (Int) -> Unit) {
    Row(Modifier.background(YSComposeTheme.colors.bottomBar)) {
        TabItem(
            if (selectedId == 0) R.drawable.lay_home_home_on_ic else R.drawable.lay_home_home_off_ic,"首页",
            if (selectedId == 0) YSComposeTheme.colors.iconCurrent else YSComposeTheme.colors.icon,
            Modifier.weight(1f)
                .clickable { onSelectedChanged(0) }
        )
        TabItem(
            if (selectedId == 1) R.drawable.lay_home_data_on_ic else R.drawable.lay_home_data_off_ic,"数据",
            if (selectedId == 1) YSComposeTheme.colors.iconCurrent else YSComposeTheme.colors.icon,
            Modifier.weight(1f)
                .clickable { onSelectedChanged(1) }
        )
        TabItem(
            if (selectedId == 2) R.drawable.lay_home_menu_on_ic else R.drawable.lay_home_menu_off_ic,"菜单",
            if (selectedId == 2) YSComposeTheme.colors.iconCurrent else YSComposeTheme.colors.icon,
            Modifier.weight(1f)
                .clickable { onSelectedChanged(2) }
        )
        TabItem(
            if (selectedId == 3) R.drawable.lay_home_setting_on_ic else R.drawable.lay_home_setting_off_ic,"设置",
            if (selectedId == 3) YSComposeTheme.colors.iconCurrent else YSComposeTheme.colors.icon,
            Modifier.weight(1f)
                .clickable { onSelectedChanged(3) }
        )
    }
}

@Composable
fun TabItem(@DrawableRes iconId: Int, title: String, tint: Color, modifier: Modifier = Modifier) {
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(id = iconId), title, Modifier.size(24.dp), tint = tint)
        Text(text = title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun TabItemPreview() {
    TabItem(iconId = R.drawable.lay_home_home_on_ic, title = "首页", tint = YSComposeTheme.colors.icon)
}


@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    YSComposeTheme(YSComposeTheme.Theme.Light){
        var selectedTab by remember { mutableStateOf(0) }
        YSBottomBar(selectedTab){
            selectedTab = it
        }
    }
}