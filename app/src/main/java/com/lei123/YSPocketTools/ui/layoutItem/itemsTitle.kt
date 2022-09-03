package com.lei123.YSPocketTools.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun itemsTitle(icon: Int, text: String) {
    Row(
        Modifier
            .padding(
                top = (20 * Zoom).dp,
                start = (12 * Zoom).dp,
                bottom = (12 * Zoom).dp),
        horizontalArrangement = Arrangement.Center,//设置水平居中对齐
        verticalAlignment = Alignment.CenterVertically, //设置垂直居中对齐
    ) {
        Image(
            painterResource(icon),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = text,
            Modifier.padding(start = (10 * Zoom).dp),
            fontSize = (16 * Zoom).sp,
            fontWeight = FontWeight.Bold,
        )
    }
}