package com.lei123.YSPocketTools.utils

import android.graphics.Bitmap
import com.lei123.YSPocketTools.AndroidTools.TimeCounter
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.entity.getResin
import com.lei123.YSPocketTools.http.HTTPs.getImage.getHttpBitmap
import com.lei123.selectcoser.BitmapUtils.saveBitmap

object NoteTidy {
    fun noteRegin(data: getResin.DataInfo?): Array<String> {
        var current_Resin = "120"
        var max_resin = "160"
        var resin1 = getString(R.string.UnknownData)
        var resin_recovery_time = "10000"
        var resinAim = getString(R.string.UnknownData)
        var resinRemain = getString(R.string.UnknownData)
        try {
            current_Resin = data?.current_resin ?: "0"
            max_resin = data?.max_resin ?: "0"
            resin_recovery_time = data?.resin_recovery_time ?: "0"
            resin1 = "$current_Resin/$max_resin"
            if (resin_recovery_time.toInt() != 0) {
                resinAim = TimeCounter.AimTime(resin_recovery_time.toInt())
                resinRemain = TimeCounter.NeededTime(resin_recovery_time.toInt())
            } else {
                resinAim = getString(R.string.resinAccomplish)
                resinRemain = getString(R.string.resinAccomplish)
            }
        } catch (e: Exception) {

        }

        return arrayOf(
            current_Resin, max_resin, resin1,
            resin_recovery_time, resinAim, resinRemain
        )
    }

    fun noteHomeCoin(data: getResin.DataInfo?): Array<String> {
        var current_home_coin = "120"
        var max_home_coin = "160"
        var homeCoin1 = getString(R.string.UnknownData)
        var home_coin_recovery_time = "10000"
        var homeCoinAim = getString(R.string.UnknownData)
        var homeCoinRemain = getString(R.string.UnknownData)
        try {
            current_home_coin = data?.current_home_coin ?: "0"
            max_home_coin = data?.max_home_coin ?: "0"
            home_coin_recovery_time = data?.home_coin_recovery_time ?: "0"
            homeCoin1 = "$current_home_coin/$max_home_coin"
            if (home_coin_recovery_time.toInt() != 0) {
                homeCoinAim = TimeCounter.AimTime(home_coin_recovery_time.toInt())
                homeCoinRemain = TimeCounter.NeededTime(home_coin_recovery_time.toInt())
            } else {
                homeCoinAim = getString(R.string.resinAccomplish)
                homeCoinRemain = getString(R.string.resinAccomplish)
            }
        } catch (e: Exception) {

        }
        return arrayOf(
            current_home_coin, max_home_coin, homeCoin1,
            home_coin_recovery_time, homeCoinAim, homeCoinRemain
        )
    }

    fun noteTask(uid: String, data: getResin.DataInfo?): Array<String> {
        var dailyTask = "0"
        var total_task_num = "4"
        var dailyTaskStr = "0/4"
        var dailyTaskSubmit = getString(R.string.dailyTaskUnSubmit)
        try {
            dailyTask = data?.finished_task_num ?: "0"
            total_task_num = data?.total_task_num ?: "0"
            dailyTaskStr = "$dailyTask/$total_task_num"
            dailyTaskSubmit =
                if (data?.is_extra_task_reward_received == true) getString(R.string.dailyTaskSubmit) else getString(
                    R.string.dailyTaskUnSubmit
                )
        } catch (e: Exception) {

        }
        var dailyTask1 = false
        var dailyTask2 = false
        var dailyTask3 = false
        var dailyTask4 = false
        if (dailyTask == "0") {
        } else if ((dailyTask == "1")) {
            dailyTask1 = true
        } else if ((dailyTask == "2")) {
            dailyTask1 = true
            dailyTask2 = true
        } else if ((dailyTask == "3")) {
            dailyTask1 = true
            dailyTask2 = true
            dailyTask3 = true
        } else if ((dailyTask == "4")) {
            dailyTask1 = true
            dailyTask2 = true
            dailyTask3 = true
            dailyTask4 = true
        }
        saveBoolean(uid + "dailyTask1", dailyTask1)
        saveBoolean(uid + "dailyTask2", dailyTask2)
        saveBoolean(uid + "dailyTask3", dailyTask3)
        saveBoolean(uid + "dailyTask4", dailyTask4)

        return arrayOf(
            dailyTask, total_task_num,
            dailyTaskStr, dailyTaskSubmit
        )
    }

    fun noteWeek(uid: String, data: getResin.DataInfo?): Array<String> {
        var weekly = "3"
        var weeklyall = "3"
        var weeklyStr = "2/3"
        try {
            weekly = data?.remain_resin_discount_num ?: "0"
            weeklyall = data?.resin_discount_num_limit ?: "0"
            weeklyStr = "$weekly/$weeklyall"
            if (weeklyStr == "0/3") {
                weeklyStr = getString(R.string.weeklyEmpty)
            }
        } catch (e: Exception) {

        }
        var weekly1 = false
        var weekly2 = false
        var weekly3 = false
        if (weekly == "0") {
        } else if ((weekly == "1")) {
            weekly1 = true
        } else if ((weekly == "2")) {
            weekly1 = true
            weekly2 = true
        } else if ((weekly == "3")) {
            weekly1 = true
            weekly2 = true
            weekly3 = true
        }
        saveBoolean(uid + "weekly1", weekly1)
        saveBoolean(uid + "weekly2", weekly2)
        saveBoolean(uid + "weekly3", weekly3)

        return arrayOf(weekly, weeklyall, weeklyStr)
    }

    fun noteExplore(uid: String, data: getResin.DataInfo?): Array<String> {
        var current_expedition_num = ""
        var max_expedition_num = ""
        var explorePhotoUrl1 = "Empty"
        var explorePhotoUrl2 = "Empty"
        var explorePhotoUrl3 = "Empty"
        var explorePhotoUrl4 = "Empty"
        var explorePhotoUrl5 = "Empty"
        var explorePhotoUrlZheng1 = "Empty"
        var explorePhotoUrlZheng2 = "Empty"
        var explorePhotoUrlZheng3 = "Empty"
        var explorePhotoUrlZheng4 = "Empty"
        var explorePhotoUrlZheng5 = "Empty"
        var exploreName1 = "Empty"
        var exploreName2 = "Empty"
        var exploreName3 = "Empty"
        var exploreName4 = "Empty"
        var exploreName5 = "Empty"
        var exploreNamelocal1 = "Empty"
        var exploreNamelocal2 = "Empty"
        var exploreNamelocal3 = "Empty"
        var exploreNamelocal4 = "Empty"
        var exploreNamelocal5 = "Empty"

        var exploreState1 = "Empty"
        var exploreState2 = "Empty"
        var exploreState3 = "Empty"
        var exploreState4 = "Empty"
        var exploreState5 = "Empty"


        var explore1 = "0"
        var explore2 = "0"
        var explore3 = "0"
        var explore4 = "0"
        var explore5 = "0"
        var exploreBool1 = false
        var exploreBool2 = false
        var exploreBool3 = false
        var exploreBool4 = false
        var exploreBool5 = false
        val reurl =
            "https://upload-bbs.mihoyo.com/game_record/genshin/character_side_icon/UI_AvatarIcon_Side_"
        val vatarname = Avatar_Name.GetAvatarName()
        var ExpAim = getString(R.string.UnknownData)
        var ExpRemain = getString(R.string.UnknownData)

        try {
            current_expedition_num = data?.current_expedition_num ?: "0"
            max_expedition_num = data?.max_expedition_num ?: "0"
            var expeditionsList = data?.expeditions
            if (current_expedition_num.toInt() > 0) {
                explorePhotoUrl1 = expeditionsList?.get(0)?.avatar_side_icon ?: "Empty"
                explorePhotoUrlZheng1 =
                    explorePhotoUrl1.replace("UI_AvatarIcon_Side_", "UI_AvatarIcon_")
                        .replace("character_side_icon", "character_icon")
                exploreName1 = explorePhotoUrl1.replace(reurl, "").replace(".png", "")
                exploreNamelocal1 = vatarname.get(exploreName1) ?: exploreName1
                exploreState1 = expeditionsList?.get(0)?.status ?: "1"
                explore1 = expeditionsList?.get(0)?.remained_time ?: "0"
                exploreBool1 = true
            }
            if (current_expedition_num.toInt() > 1) {
                explorePhotoUrl2 = expeditionsList?.get(1)?.avatar_side_icon ?: "Empty"
                explorePhotoUrlZheng2 =
                    explorePhotoUrl2.replace("UI_AvatarIcon_Side_", "UI_AvatarIcon_")
                        .replace("character_side_icon", "character_icon")
                exploreName2 = explorePhotoUrl2.replace(reurl, "").replace(".png", "")
                exploreNamelocal2 = vatarname.get(exploreName2) ?: exploreName2
                exploreState2 = expeditionsList?.get(1)?.status ?: "1"
                explore2 = expeditionsList?.get(1)?.remained_time ?: "0"
                exploreBool1 = true
            }
            if (current_expedition_num.toInt() > 2) {
                explorePhotoUrl3 = expeditionsList?.get(2)?.avatar_side_icon ?: "Empty"
                explorePhotoUrlZheng3 =
                    explorePhotoUrl3.replace("UI_AvatarIcon_Side_", "UI_AvatarIcon_")
                        .replace("character_side_icon", "character_icon")
                exploreName3 = explorePhotoUrl3.replace(reurl, "").replace(".png", "")
                exploreNamelocal3 = vatarname.get(exploreName3) ?: exploreName3
                exploreState3 = expeditionsList?.get(2)?.status ?: "1"
                explore3 = expeditionsList?.get(2)?.remained_time ?: "0"
                exploreBool1 = true
            }
            if (current_expedition_num.toInt() > 3) {
                explorePhotoUrl4 = expeditionsList?.get(3)?.avatar_side_icon ?: "Empty"
                explorePhotoUrlZheng4 =
                    explorePhotoUrl4.replace("UI_AvatarIcon_Side_", "UI_AvatarIcon_")
                        .replace("character_side_icon", "character_icon")
                exploreName4 = explorePhotoUrl4.replace(reurl, "").replace(".png", "")
                exploreNamelocal4 = vatarname.get(exploreName4) ?: exploreName4
                exploreState4 = expeditionsList?.get(3)?.status ?: "1"
                explore4 = expeditionsList?.get(3)?.remained_time ?: "0"
                exploreBool1 = true
            }
            if (current_expedition_num.toInt() > 4) {
                explorePhotoUrl5 = expeditionsList?.get(4)?.avatar_side_icon ?: "Empty"
                explorePhotoUrlZheng5 =
                    explorePhotoUrl5.replace("UI_AvatarIcon_Side_", "UI_AvatarIcon_")
                        .replace("character_side_icon", "character_icon")
                exploreName5 = explorePhotoUrl5.replace(reurl, "").replace(".png", "")
                exploreNamelocal5 = vatarname.get(exploreName5) ?: exploreName5
                exploreState5 = expeditionsList?.get(4)?.status ?: "1"
                explore5 = expeditionsList?.get(4)?.remained_time ?: "0"
                exploreBool1 = true
            }
        } catch (e: Exception) {

        }

        var explore1Aim = ""
        var explore2Aim = ""
        var explore3Aim = ""
        var explore4Aim = ""
        var explore5Aim = ""
        var explore1Remain = ""
        var explore2Remain = ""
        var explore3Remain = ""
        var explore4Remain = ""
        var explore5Remain = ""

        var exploreInt1 = 0
        var exploreInt2 = 0
        var exploreInt3 = 0
        var exploreInt4 = 0
        var exploreInt5 = 0
        try {
            exploreInt1 = explore1.toInt()
            exploreInt2 = explore2.toInt()
            exploreInt3 = explore3.toInt()
            exploreInt4 = explore4.toInt()
            exploreInt5 = explore5.toInt()
        }catch (e:Exception){

        }

        if (exploreInt1 != 0) {
            explore1Aim = exploreNamelocal1 + getString(R.string.predict) + TimeCounter.AimTime(exploreInt1) + getString(R.string.accomplish)
            explore1Remain = getString(R.string.stillNeed) + TimeCounter.NeededTime(exploreInt1) + getString(R.string.accomplish)
        } else {
            explore1Aim = exploreNamelocal1 + getString(R.string.exploreAccomplish)
            explore1Remain = exploreNamelocal1 + getString(R.string.exploreAccomplish)
        }
        if (exploreInt2 != 0) {
            explore2Aim = exploreNamelocal2 + getString(R.string.predict) + TimeCounter.AimTime(exploreInt2) + getString(R.string.accomplish)
            explore2Remain = getString(R.string.stillNeed) + TimeCounter.NeededTime(exploreInt2) + getString(R.string.accomplish)
        } else {
            explore2Aim = exploreNamelocal2 + getString(R.string.exploreAccomplish)
            explore2Remain = exploreNamelocal2 + getString(R.string.exploreAccomplish)
        }
        if (exploreInt3 != 0) {
            explore3Aim = exploreNamelocal3 + getString(R.string.predict) + TimeCounter.AimTime(exploreInt3) + getString(R.string.accomplish)
            explore3Remain = getString(R.string.stillNeed) + TimeCounter.NeededTime(exploreInt3) + getString(R.string.accomplish)
        } else {
            explore3Aim = exploreNamelocal3 + getString(R.string.exploreAccomplish)
            explore3Remain = exploreNamelocal3 + getString(R.string.exploreAccomplish)
        }
        if (exploreInt4 != 0) {
            explore4Aim = exploreNamelocal4 + getString(R.string.predict) + TimeCounter.AimTime(exploreInt4) + getString(R.string.accomplish)
            explore4Remain = getString(R.string.stillNeed) + TimeCounter.NeededTime(exploreInt4) + getString(R.string.accomplish)
        } else {
            explore4Aim = exploreNamelocal4 + getString(R.string.exploreAccomplish)
            explore4Remain = exploreNamelocal4 + getString(R.string.exploreAccomplish)
        }
        if (exploreInt5 != 0) {
            explore5Aim = exploreNamelocal5 + getString(R.string.predict) + TimeCounter.AimTime(exploreInt5) + getString(R.string.accomplish)
            explore5Remain = getString(R.string.stillNeed) + TimeCounter.NeededTime(exploreInt5) + getString(R.string.accomplish)
        } else {
            explore5Aim = exploreNamelocal5 + getString(R.string.exploreAccomplish)
            explore5Remain = exploreNamelocal5 + getString(R.string.exploreAccomplish)
        }


        var maxExpTime = explore1.toInt()
        if (explore2.toInt() > maxExpTime) {
            maxExpTime = explore2.toInt()
        }
        if (explore3.toInt() > maxExpTime) {
            maxExpTime = explore3.toInt()
        }
        if (explore4.toInt() > maxExpTime) {
            maxExpTime = explore4.toInt()
        }
        if (explore5.toInt() > maxExpTime) {
            maxExpTime = explore5.toInt()
        }


        if (maxExpTime != 0) {
            ExpAim = TimeCounter.AimTime(maxExpTime)
            ExpRemain = getString(R.string.stillNeed) + TimeCounter.NeededTime(maxExpTime)
        } else {
            ExpAim = getString(R.string.exploreAccomplish)
            ExpRemain = getString(R.string.exploreAccomplish)
        }

        saveBoolean(uid + "exploreBool1", exploreBool1)
        saveBoolean(uid + "exploreBool2", exploreBool2)
        saveBoolean(uid + "exploreBool3", exploreBool3)
        saveBoolean(uid + "exploreBool4", exploreBool4)
        saveBoolean(uid + "exploreBool5", exploreBool5)
        var name = UrlToName(explorePhotoUrlZheng1)
        val TargetPath = getSavePath("images")

        var bitmap: Bitmap? = getHttpBitmap(explorePhotoUrlZheng1)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }

        bitmap = getHttpBitmap(explorePhotoUrlZheng2)
        name = UrlToName(explorePhotoUrlZheng2)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrlZheng3)
        name = UrlToName(explorePhotoUrlZheng3)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrlZheng4)
        name = UrlToName(explorePhotoUrlZheng4)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrlZheng5)
        name = UrlToName(explorePhotoUrlZheng5)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrl1)
        name = UrlToName(explorePhotoUrl1)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrl2)
        name = UrlToName(explorePhotoUrl2)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrl3)
        name = UrlToName(explorePhotoUrl3)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrl4)
        name = UrlToName(explorePhotoUrl4)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }
        bitmap = getHttpBitmap(explorePhotoUrl5)
        name = UrlToName(explorePhotoUrl5)
        if (bitmap != null) {
            saveBitmap(name, bitmap, TargetPath)
        }

        return arrayOf(
            current_expedition_num, max_expedition_num,
            explorePhotoUrl1, explorePhotoUrl2, explorePhotoUrl3,
            explorePhotoUrl4, explorePhotoUrl5,
            exploreState1, exploreState2, exploreState3,
            exploreState4, exploreState5,
            explore1, explore2, explore3,
            explore4, explore5,
            explorePhotoUrlZheng1, explorePhotoUrlZheng2, explorePhotoUrlZheng3,
            explorePhotoUrlZheng4, explorePhotoUrlZheng5,
            exploreName1, exploreName2, exploreName3,
            exploreName4, exploreName5,
            exploreNamelocal1, exploreNamelocal2, exploreNamelocal3,
            exploreNamelocal4, exploreNamelocal5,
            ExpAim, ExpRemain,
            explore1Aim,explore2Aim,explore3Aim,
            explore4Aim,explore5Aim,
            explore1Remain,explore2Remain,explore3Remain,
            explore4Remain,explore5Remain
        )
    }

    fun noteTransfor(uid: String, data: getResin.DataInfo?): Array<String> {
        var transobtained = true
        var transRemainSecond = "123456789"
        var transformerInfo = data?.transformer
        val transday = data?.transformer?.recovery_time?.Day ?: "0"
        val h = data?.transformer?.recovery_time?.Hour ?: "0"
        val m = data?.transformer?.recovery_time?.Minute ?: "0"
        val s = data?.transformer?.recovery_time?.Second ?: "0"

        //transRemainSecond = (d!! * 86400 + h!! * 3600 + m!! * 60 + s!!).toString()

        transobtained = transformerInfo?.obtained == true

        if (transday == "0" && h == "0" && m == "0" && s == "0" ){
            saveBoolean(uid + "transviable", true)
        }else{
            saveBoolean(uid + "transviable", false)
        }


        saveBoolean(uid + "transobtained", transobtained)
        //var remainDaily = transRemainSecond.toInt() / 86400
        val transRemain = when (transday) {
            "0" -> getString(R.string.zeroDay)
            "1" -> getString(R.string.oneDay)
            "2" -> getString(R.string.twoDay)
            "3" -> getString(R.string.threeDay)
            "4" -> getString(R.string.fourDay)
            "5" -> getString(R.string.fiveDay)
            "6" -> getString(R.string.sixDay)
            "7" -> getString(R.string.sevenDay)
            else -> getString(R.string.UnknownData)
        }
        return arrayOf(transday, transRemain)
    }


}