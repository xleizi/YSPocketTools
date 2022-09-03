package com.lei123.YSPocketTools.ui.plugin

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.YSComposeTheme
import com.lei123.YSPocketTools.utils.getString

/**
 * 日期选择器;
 */
@Composable
fun TimePicker(
    hour: Int = 12,
    minute: Int = 10,
    title: String = "选择时间",
    onDismiss: (selected: Boolean, hour: Int, minute: Int) -> Unit
) {
    var selectHour by remember { mutableStateOf(hour) }
    var selectMinute by remember { mutableStateOf(minute) }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
    ) {
        Button(
            onClick = {
                onDismiss(true, selectHour, selectMinute)
                //timePicker = false
            },
            modifier = Modifier
                .padding(top = 1.dp, end = 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = YSComposeTheme.colors.TextBold,
                contentColor = Color.White
            )
        ) {
            Text(text = getString(R.string.timePicker1))
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 0.dp),
    ) {
        TimeWheel(selectHour, selectMinute) { index, value ->
            when (index) {
                0 -> selectHour = value
                1 -> selectMinute = value
            }
        }

    }

}


/**
 * 时间选择器 - 睡眠 - (开始-结束时间)
 */
@Composable
private fun TimeWheel(
    hour: Int,
    minute: Int,
    onChange: (index: Int, value: Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        Alignment.Center
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            val modifier = Modifier.weight(1f)

            //  年
            ColumnPicker(
                modifier = modifier,
                value = hour,
                label = { "${it}时" },
                range = 0..23,
                onValueChange = {
                    onChange(0, it)
                }
            )
            //  月
            ColumnPicker(
                modifier = modifier,
                value = minute,
                label = { "${it}分" },
                range = 0..60,
                onValueChange = {
                    onChange(1, it)
                }
            )
        }

        // 中间两道横线
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .align(Alignment.Center)
        ) {
            Divider(Modifier.padding(horizontal = 15.dp))
            Divider(
                Modifier
                    .padding(horizontal = 15.dp)
                    .align(Alignment.BottomStart)
            )
        }

    }
}