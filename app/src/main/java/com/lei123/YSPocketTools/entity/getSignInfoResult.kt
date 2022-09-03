package com.lei123.YSPocketTools.entity

class getSignInfoResult {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var total_sign_day: String? = null
        var today: String? = null
        var is_sign: String? = null
        var first_bind: String? = null
        var is_sub: String? = null
        var month_first: String? = null
        var sign_cnt_missed: String? = null
    }
}