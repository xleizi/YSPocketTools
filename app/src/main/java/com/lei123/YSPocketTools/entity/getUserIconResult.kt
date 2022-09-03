package com.lei123.YSPocketTools.entity

class getUserIconResult {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var data: DataInfo? = null
        var user_info: userInfo? = null

        inner class userInfo {
            var uid: String? = null
            var nickname: String? = null
            var avatar_url: String? = null
            var ip_region: String? = null
        }
    }
}