package com.lei123.YSPocketTools.entity

class getSignAwardResult {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var month: Int? = null
        var awards: List<awardsInfo>? = null

        inner class awardsInfo {
            var icon: String? = null
            var name: String? = null
            var cnt = 0
        }
    }
}