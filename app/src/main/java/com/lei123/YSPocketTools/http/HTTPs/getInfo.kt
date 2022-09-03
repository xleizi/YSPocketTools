package com.lei123.YSPocketTools.http.HTTPs

import com.google.gson.Gson
import com.lei123.YSPocketTools.entity.getUserNameResult
import com.lei123.YSPocketTools.entity.getUserIconResult
import com.lei123.YSPocketTools.utils.Constant
import com.lei123.YSPocketTools.utils.loadString

object getInfo {
    /**
     * 获取用户名
     *
     * @param uid
     * @param cookie
     * @return
     */
    fun getUserName(uid: String, cookie: String): Array<String> {
        val url_role = Constant.miHoYoRole_URL
        var result = "获取失败"
        var nickname = "角色名"
        var level = "等级"
        var region_name = "天空岛"
        try {
            result = getHTTPs.getRequest(url_role, cookie)
            val re: getUserNameResult = Gson().fromJson(result, getUserNameResult::class.java)
            for (list in re.data!!.list!!) {
                if (uid == list.game_uid) {
                    nickname = list.nickname!!
                    level = list.level!!
                    region_name = list.region_name!!
                }
            }
        } catch (e: Exception) {
            nickname = "角色名"
            e.printStackTrace()
        }
        return arrayOf(nickname, level, region_name)
    }

    /**
     * 获取用户头像
     *
     * @param uid
     * @param cookie
     * @return
     */
    fun getUserIcon(uid: String, cookie: String): Array<String>? {
        val url_role = Constant.GetMiHoYoIcon.plus(loadString("miID"))
        var minickname = ""
        var miavatar_url = "https://img-static.mihoyo.com/communityweb/upload/97734c89374997c7c87d5af5f7442171.png"
        var miip_region = ""

        try {
            val result = getHTTPs.getRequest(url_role, cookie)
            val re: getUserIconResult = Gson().fromJson(result, getUserIconResult::class.java)
            minickname = re.data!!.user_info!!.nickname!!
            miavatar_url = re.data!!.user_info!!.avatar_url!!
            miip_region = re.data!!.user_info!!.ip_region!!
        } catch (e: Exception) {
            minickname = "角色名"
            e.printStackTrace()
        }
        return arrayOf(minickname, miavatar_url, miip_region)
    }

    /**
     * 获取用户头像
     *
     * @param uid
     * @param cookie
     * @return
     */
    fun getResinInfo(uid: String, cookie: String): Array<String>? {
        val url_role = Constant.GetMiHoYoIcon
        var minickname = ""
        var miavatar_url = "https://img-static.mihoyo.com/communityweb/upload/97734c89374997c7c87d5af5f7442171.png"
        var miip_region = ""

        try {
            val result = getHTTPs.getRequest(url_role, cookie)
            val re: getUserIconResult = Gson().fromJson(result, getUserIconResult::class.java)
            minickname = re.data!!.user_info!!.nickname!!
            miavatar_url = re.data!!.user_info!!.avatar_url!!
            miip_region = re.data!!.user_info!!.ip_region!!
        } catch (e: Exception) {
            minickname = "角色名"
            e.printStackTrace()
        }
        return arrayOf(minickname, miavatar_url, miip_region)
    }
}