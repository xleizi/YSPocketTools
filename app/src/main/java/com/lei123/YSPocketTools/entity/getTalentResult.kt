package com.lei123.YSPocketTools.entity

class getTalentResult {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var skill_list: List<skillListInfo>? = null
        var weapon: weaponsInfo? = null
        var reliquary_list: List<reliquaryListInfo>? = null

        inner class skillListInfo {
            var id: String? = null
            var group_id: String? = null
            var name: String? = null
            var icon: String? = null
            var max_level: String? = null
            var level_current: String? = null
        }

        inner class weaponsInfo {
            var id: String? = null
            var name: String? = null
            var icon: String? = null
            var weapon_cat_id: String? = null
            var weapon_level: String? = null
            var max_level: String? = null
            var level_current: String? = null
        }

        inner class reliquaryListInfo {
            var id: String? = null
            var name: String? = null
            var icon: String? = null
            var reliquary_cat_id: String? = null
            var reliquary_level: String? = null
            var level_current: String? = null
            var max_level: String? = null
        }
    }
}