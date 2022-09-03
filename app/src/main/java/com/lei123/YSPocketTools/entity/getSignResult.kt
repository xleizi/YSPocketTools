package com.lei123.YSPocketTools.entity

import com.lei123.YSPocketTools.R

class getSignResult {
    var data: DataInfo? = null
    var message: String? = null
    var retcode = 0

    inner class DataInfo {
        var code: String? = null
        var risk_code: String? = null
        var gt: String? = null
        var challenge: String? = null
        var success: String? = null
    }
}
