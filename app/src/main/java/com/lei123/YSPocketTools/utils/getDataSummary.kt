package com.lei123.YSPocketTools.utils

import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R

fun getDataSummaryIcon(title: String): Int {
    val icon = when (title) {
        getString(R.string.activityday) -> R.drawable.lay_data_summary_icon_activitydays
        getString(R.string.achievement_number) -> R.drawable.lay_data_summary_icon_achievement
        getString(R.string.avatar_number) -> R.drawable.lay_data_summary_icon_avatarnumber
        getString(R.string.spiral_abyss) -> R.drawable.lay_data_summary_icon_spiralabyss

        getString(R.string.total_chest_number) -> R.drawable.lay_data_summary_icon_chest5
        getString(R.string.luxurious_chest_number) -> R.drawable.lay_data_summary_icon_chest4
        getString(R.string.precious_chest_number) -> R.drawable.lay_data_summary_icon_chest3
        getString(R.string.exquisite_chest_number) -> R.drawable.lay_data_summary_icon_chest2

        getString(R.string.common_chest_number) -> R.drawable.lay_data_summary_icon_chest1
        getString(R.string.magic_chest_number) -> R.drawable.lay_data_summary_icon_chest3
        getString(R.string.way_point_number) -> R.drawable.lay_data_summary_icon_waypoint
        getString(R.string.domain_number) -> R.drawable.lay_data_summary_icon_domainnumber

        getString(R.string.anemoculus_number) -> R.drawable.lay_data_summary_icon_feng
        getString(R.string.geoculus_number) -> R.drawable.lay_data_summary_icon_yan
        getString(R.string.electroculus_number) -> R.drawable.lay_data_summary_icon_lei
        getString(R.string.cao_number) -> R.drawable.lay_data_summary_icon_cao

        getString(R.string.exploration_percentage1) -> R.drawable.lay_data_summary_icon_mengde
        getString(R.string.exploration_percentage2) -> R.drawable.lay_data_summary_icon_liyue
        getString(R.string.exploration_percentage4) -> R.drawable.lay_data_summary_icon_daoqi
        getString(R.string.exploration_percentage8) -> R.drawable.lay_data_summary_icon_xumi

        getString(R.string.exploration_percentage3) -> R.drawable.lay_data_summary_icon_xueshan
        getString(R.string.exploration_percentage5) -> R.drawable.lay_data_summary_icon_yuanxiagong
        getString(R.string.exploration_percentage6) -> R.drawable.lay_data_summary_icon_cengyan
        getString(R.string.exploration_percentage7) -> R.drawable.lay_data_summary_icon_cengyan

        else -> R.drawable.lay_data_summary_icon_activitydays
    }
    return icon
}

fun getDataSummaryContent(viewModel: MainViewModel, title: String): String {
    val content = when (title) {
        getString(R.string.activityday) -> viewModel.active_day_number.toString()
        getString(R.string.achievement_number) -> viewModel.achievement_number.toString()
        getString(R.string.avatar_number) -> viewModel.avatar_number.toString()
        getString(R.string.spiral_abyss) -> viewModel.spiral_abyss

        getString(R.string.total_chest_number) -> viewModel.total_chest_number.toString()
        getString(R.string.luxurious_chest_number) -> viewModel.luxurious_chest_number.toString()
        getString(R.string.precious_chest_number) -> viewModel.precious_chest_number.toString()
        getString(R.string.exquisite_chest_number) -> viewModel.exquisite_chest_number.toString()

        getString(R.string.common_chest_number) -> viewModel.common_chest_number.toString()
        getString(R.string.magic_chest_number) -> viewModel.magic_chest_number.toString()
        getString(R.string.way_point_number) -> viewModel.way_point_number.toString()
        getString(R.string.domain_number) -> viewModel.domain_number.toString()

        getString(R.string.anemoculus_number) -> viewModel.anemoculus_number.toString()
        getString(R.string.geoculus_number) -> viewModel.geoculus_number.toString()
        getString(R.string.electroculus_number) -> viewModel.electroculus_number.toString()
        getString(R.string.cao_number) -> viewModel.dendroculus_number.toString()

        getString(R.string.exploration_percentage1) -> (viewModel.exploration_percentage1.toFloat() / 10).toString().plus("%")
        getString(R.string.exploration_percentage2) -> (viewModel.exploration_percentage2.toFloat() / 10).toString().plus("%")
        getString(R.string.exploration_percentage4) -> (viewModel.exploration_percentage4.toFloat() / 10).toString().plus("%")
        getString(R.string.exploration_percentage8) -> (viewModel.exploration_percentage8.toFloat() / 10).toString().plus("%")

        getString(R.string.exploration_percentage3) -> (viewModel.exploration_percentage3.toFloat() / 10).toString().plus("%")
        getString(R.string.exploration_percentage5) -> (viewModel.exploration_percentage5.toFloat() / 10).toString().plus("%")
        getString(R.string.exploration_percentage6) -> (viewModel.exploration_percentage6.toFloat() / 10).toString().plus("%")
        getString(R.string.exploration_percentage7) -> (viewModel.exploration_percentage7.toFloat() / 10).toString().plus("%")

        else -> viewModel.active_day_number.toString()
    }
    return content
}