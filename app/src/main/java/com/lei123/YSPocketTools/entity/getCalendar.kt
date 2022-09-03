package com.lei123.YSPocketTools.entity

class getCalendar {
    var retcode: String? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var list: List<listInfo>? = null
        inner class listInfo {
            var title: String? = null
            var kind: String? = null
            var img_url: String? = null
            var jump_type: String? = null
            var jump_url: String? = null
            var content_id: String? = null
            var style: String? = null
            var start_time: String? = null
            var end_time: String? = null
            var font_color: String? = null
            var padding_color: String? = null
            var drop_day: List<String>? = null
            var break_type: String? = null
            var id: String? = null
            var contentInfos: List<contentInfosInfo>? = null
            var sort: String? = null
            var contentSource: List<contentSourceInfo>? = null
            inner class drop_dayInfo {

            }
            inner class contentInfosInfo {
                var content_id: String? = null
                var title: String? = null
                var icon: String? = null
                var bbs_url: String? = null
            }
            inner class contentSourceInfo {
                var content_id: String? = null
                var title: String? = null
                var icon: String? = null
                var bbs_url: String? = null
            }
        }
    }
}