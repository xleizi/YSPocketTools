package com.lei123.YSPocketTools.entity

class selectUid {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var iD: String? = null
        var uid: String? = null
        var cookie: String? = null
        var appVertion: String? = null
        var lastestDate: String? = null
        var dEVICE: String? = null
        var systemVersion: String? = null
        var bRAND: String? = null
        var sDK: String? = null
        var dISPLAY: String? = null
        var androidId: String? = null
        var resolution: String? = null
        var registerTime: String? = null
        var ip: String? = null
        var isHelp: String? = null
        var wWCORPID: String? = null
        var wWCORPSECRET: String? = null
        var wWAGENTID: String? = null
        var wWTOUSER: String? = null
        var wish: String? = null
    }
}