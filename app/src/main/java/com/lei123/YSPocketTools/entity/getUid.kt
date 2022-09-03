package com.lei123.YSPocketTools.entity

class getUid {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var list: List<listInfo>? = null

        inner class listInfo {
            var game_biz: String? = null
            var region: String? = null
            var game_uid: String? = null
            var nickname: String? = null
            var level: Int? = null
            var is_chosen: Boolean? = null
            var region_name: String? = null
            var is_official: Boolean? = null
        }
    }
}