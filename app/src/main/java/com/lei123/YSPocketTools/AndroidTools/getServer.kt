package com.lei123.YSPocketTools.AndroidTools

object getServer {
    fun get_server(uid: String): String {
        return if (uid.startsWith("1") || uid.startsWith("2")) {
            // 天空岛
            "cn_gf01"
        } else if (uid.startsWith("5")) {
            // 世界树
            "cn_qd01"
        } else {
            // 空
            ""
        }
    }
}