package com.lei123.YSPocketTools.ui.plugin

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * 日期选择器;
 */
@Composable
fun DatePicker(
    year: Int = 2000,
    month: Int = 1,
    day: Int = 1,
    title: String = "选择日期",
    onDismiss: (selected: Boolean, year: Int, month: Int, day: Int) -> Unit
) {
    var selectYear by remember { mutableStateOf(year) }
    var selectMonth by remember { mutableStateOf(month) }
    val selectDay = remember { mutableStateOf(day) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
    ) {
        /*TitleBar(
            title = title,
            endText = "确定",
            endClick = {
                onDismiss(true, selectYear, selectMonth, selectDay.value)
            },
        ) { onDismiss(false, 0, 0, 0) }*/
        DateWheel(selectYear, selectMonth, selectDay) { index, value ->
            when (index) {
                0 -> selectYear = value
                1 -> selectMonth = value
                2 -> selectDay.value = value
            }
        }
    }
}

/**
 * 时间选择器 - 睡眠 - (开始-结束时间)
 */
@Composable
private fun DateWheel(
    year: Int,
    month: Int,
    day: MutableState<Int>,
    onChange: (index: Int, value: Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        Alignment.Center
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)) {
            val modifier = Modifier.weight(1f)

            //  年
            ColumnPicker(
                modifier = modifier,
                value = year,
                label = { "${it}年" },
                range = 1920..2060,
                onValueChange = {
                    onChange(0, it)
                }
            )
            //  月
            ColumnPicker(
                modifier = modifier,
                value = month,
                label = { "${it}月" },
                range = 1..12,
                onValueChange = {
                    onChange(1, it)
                }
            )

            //  日
            val lastDay = getLastDay(year, month)
            if (day.value > lastDay) day.value = lastDay
            ColumnPicker(
                modifier = modifier,
                value = day.value,
                label = { "${it}日" },
                range = 1..lastDay,
                onValueChange = {
                    onChange(2, it)
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

/**
 * 根据年月, 获取天数
 */
private fun getLastDay(year: Int, month: Int): Int {
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> {
            // 百年: 四百年一闰年;  否则: 四年一闰年;
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    29
                } else {
                    28
                }
            } else {
                if (year % 4 == 0) {
                    29
                } else {
                    28
                }
            }
        }
    }
}

@Preview
@Composable
private fun TimePreview() {
    DatePicker { selected, year, month, day ->
    }
}
