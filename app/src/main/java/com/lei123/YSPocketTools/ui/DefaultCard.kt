package com.lei123.YSPocketTools.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lei123.YSPocketTools.YSComposeTheme

@Composable
public fun ColumnScope.DefaultCard(
    modifier: Modifier = Modifier,
    text: String? = null,
    content: @Composable () -> Unit
) {
    if (text != null) {
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 10.dp, top = 8.dp, bottom = 10.dp),
            fontSize = 17.sp
        )
    }
    Card(
        modifier.fillMaxWidth(),
        backgroundColor = YSComposeTheme.colors.bottomBar,
        contentColor = contentColorFor(YSComposeTheme.colors.bottomBar)
    ) {
        Box(
            Modifier
                .padding(10.dp)
        ) {
            content()
        }
    }
}