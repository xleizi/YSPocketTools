package com.lei123.YSPocketTools.utils

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.http.HTTPs.getHTTPs
import java.text.SimpleDateFormat
import java.util.*

fun updateSummary(viewModel: MainViewModel){
    Thread.sleep(5000)
    val avatarArray = JsonArray()
    for (avatar in viewModel.nameArray) {
        var JsonObject = JsonObject()
        JsonObject.addProperty("name", avatar)
        JsonObject.addProperty("id", viewModel.idmap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("element", viewModel.elementmap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("fetter", viewModel.fettermap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("level", viewModel.levelmap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("rarity", viewModel.raritymap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("actived_constellation_num", viewModel.actived_constellation_nummap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("skill_a", viewModel.Talentlevel_currentAmap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("skill_e", viewModel.Talentlevel_currentEmap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("skill_q", viewModel.Talentlevel_currentQmap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_id", viewModel.WeaponidMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_name", viewModel.WeaponnameMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_type", viewModel.WeapontypeMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_desc", viewModel.WeapondescMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_level_current", viewModel.Weaponlevel_currentMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_max_level", viewModel.Weaponmax_levelMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_affix_level", viewModel.Weaponaffix_levelMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_rarity", viewModel.WeaponrarityMap.getOrElse(avatar) { "Unknown" })
        JsonObject.addProperty("weapon_promote_level", viewModel.Weaponpromote_levelMap.getOrElse(avatar) { "Unknown" })
        avatarArray.add(JsonObject)
    }

    var activeObject = JsonObject()
    activeObject.addProperty("active_day_number", viewModel.active_day_number)
    activeObject.addProperty("achievement_number", viewModel.achievement_number)
    activeObject.addProperty("avatar_number", viewModel.avatar_number)
    activeObject.addProperty("spiral_abyss", viewModel.spiral_abyss)

    activeObject.addProperty("total_chest_number", viewModel.total_chest_number)
    activeObject.addProperty("luxurious_chest_number", viewModel.luxurious_chest_number)
    activeObject.addProperty("precious_chest_number", viewModel.precious_chest_number)
    activeObject.addProperty("exquisite_chest_number", viewModel.exquisite_chest_number)

    activeObject.addProperty("common_chest_number", viewModel.common_chest_number)
    activeObject.addProperty("magic_chest_number", viewModel.magic_chest_number)
    activeObject.addProperty("way_point_number", viewModel.way_point_number)
    activeObject.addProperty("domain_number", viewModel.domain_number)

    activeObject.addProperty("anemoculus_number", viewModel.anemoculus_number)
    activeObject.addProperty("geoculus_number", viewModel.geoculus_number)
    activeObject.addProperty("electroculus_number", viewModel.electroculus_number)
    activeObject.addProperty("dendroculus_number", viewModel.dendroculus_number)

    var exploreObject = JsonObject()

    exploreObject.addProperty("exploration_mengde", viewModel.exploration_percentage1)
    exploreObject.addProperty("exploration_liyue", viewModel.exploration_percentage2)
    exploreObject.addProperty("exploration_daoqi", viewModel.exploration_percentage4)
    exploreObject.addProperty("exploration_xumi", viewModel.exploration_percentage8)

    exploreObject.addProperty("exploration_xueshan", viewModel.exploration_percentage3)
    exploreObject.addProperty("exploration_yuanxiagong", viewModel.exploration_percentage5)
    exploreObject.addProperty("exploration_cengyan", viewModel.exploration_percentage6)
    exploreObject.addProperty("exploration_cengyanxia", viewModel.exploration_percentage7)

    val sdf = SimpleDateFormat("HH:mm:ss")
    val time = sdf.format(Date())

    val dataObject = JsonObject()
    dataObject.add("avatarArray", avatarArray)
    dataObject.add("active", activeObject)
    dataObject.add("explore", exploreObject)

    val jsonObject = JsonObject()
    jsonObject.addProperty("uid", loadMainUID())
    jsonObject.addProperty("updateTime", time)
    jsonObject.addProperty("avatarCount", viewModel.nameArray.size)
    dataObject.add("data", dataObject)


    getHTTPs.updateRequest(Constant.UpdateSummary_URL, jsonObject.toString(), loadMainUID(), "summary")
}