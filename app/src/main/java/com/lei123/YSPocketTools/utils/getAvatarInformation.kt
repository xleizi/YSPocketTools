package com.lei123.YSPocketTools.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.lei123.YSPocketTools.AndroidTools.getServer
import com.lei123.YSPocketTools.AndroidTools.random
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.entity.getCalendar
import com.lei123.YSPocketTools.entity.getCharacter
import com.lei123.YSPocketTools.entity.getTalentResult
import com.lei123.YSPocketTools.entity.getUserSummary
import com.lei123.YSPocketTools.http.HTTPs.getHTTPs
import com.lei123.YSPocketTools.http.HTTPs.getHTTPs.updateRequest
import com.lei123.YSPocketTools.http.HTTPs.getImage
import com.lei123.YSPocketTools.http.HTTPs.getImage.urlSaveToImage
import com.lei123.YSPocketTools.utils.Constant.Companion.UpdateSummary_URL
import java.text.SimpleDateFormat
import java.util.*



fun getSummary(viewModel:MainViewModel) {
    Thread {
        saveBoolean("SummaryState", true)
        var url = Constant.miHoYoSignSummary_URL
        if (loadMainUID() != "123456789") {
            val uid = loadMainUID()
            val cookie = loadMainCookie()
            val server = getServer.get_server(loadMainUID())
            val q = "role_id=$uid&server=$server"
            url = url + "server=" + server + "&role_id=" + uid
            var result = getHTTPs.getRequestCookieDS(url, cookie, q);
            Log.i("getSummary", result)
            if (result == "failed") {
                Thread.sleep(200)
                getSummary(viewModel)
            } else {
                val getSummary: getUserSummary = Gson().fromJson(result, getUserSummary::class.java)
                val message = getSummary.message
                if (message == "OK") {
                    val avatars = getSummary.data?.avatars
                    if (avatars != null) {
                        var i = 0
                        for (avatar in avatars) {
                            val name = avatar.name.toString()
                            val id = avatar.id.toString()
                            val image = avatar.image.toString()
                            val element = avatar.element.toString()
                            val fetter = avatar.fetter.toString()
                            val level = avatar.level.toString()
                            val rarity = avatar.rarity.toString()
                            val actived_constellation_num =
                                avatar.actived_constellation_num.toString()
                            val card_image = avatar.card_image.toString()
                            val is_chosen = avatar.is_chosen.toString()
                            //viewModel.nameArray[i] = name
                            viewModel.nameArray = viewModel.nameArray.plus(name)
                            viewModel.idmap += name to id
                            viewModel.imagemap += name to image
                            viewModel.elementmap += name to element
                            viewModel.fettermap += name to fetter
                            viewModel.levelmap += name to level
                            viewModel.raritymap += name to rarity
                            viewModel.actived_constellation_nummap += name to actived_constellation_num
                            viewModel.card_imagemap += name to card_image
                            viewModel.is_chosenmap += name to is_chosen
                            urlSaveToImage(image)
                            urlSaveToImage(card_image)

                            getTalent(viewModel, id, name)
                            Thread.sleep((20..50).random().toLong())

                            /*Log.i("avatars", viewModel.nameArray[i])
                            Log.i(
                                "id",
                                viewModel.idmap.getOrElse(viewModel.nameArray[i]) { "Unknown" })
                            Log.i(
                                "元素",
                                viewModel.elementmap.getOrElse(viewModel.nameArray[i]) { "Unknown" })
                            Log.i(
                                "好感度",
                                viewModel.fettermap.getOrElse(viewModel.nameArray[i]) { "Unknown" })
                            Log.i(
                                "等级",
                                viewModel.levelmap.getOrElse(viewModel.nameArray[i]) { "Unknown" })
                            Log.i(
                                "星级",
                                viewModel.raritymap.getOrElse(viewModel.nameArray[i]) { "Unknown" })
                            Log.i(
                                "命座",
                                viewModel.actived_constellation_nummap.getOrElse(viewModel.nameArray[i]) { "Unknown" })*/
                            i += 1
                        }
                    }
                    val stats = getSummary.data?.stats
                    if (stats != null) {
                        viewModel.active_day_number = stats.active_day_number ?: 0
                        viewModel.achievement_number = stats.achievement_number ?: 0
                        viewModel.anemoculus_number = stats.anemoculus_number ?: 0
                        viewModel.geoculus_number = stats.geoculus_number ?: 0
                        viewModel.avatar_number = stats.avatar_number ?: 0
                        viewModel.way_point_number = stats.way_point_number ?: 0
                        viewModel.domain_number = stats.domain_number ?: 0
                        viewModel.spiral_abyss = stats.spiral_abyss ?: "1-1"
                        viewModel.precious_chest_number = stats.precious_chest_number ?: 0
                        viewModel.luxurious_chest_number = stats.luxurious_chest_number ?: 0
                        viewModel.exquisite_chest_number = stats.exquisite_chest_number ?: 0
                        viewModel.common_chest_number = stats.common_chest_number ?: 0
                        viewModel.electroculus_number = stats.electroculus_number ?: 0
                        viewModel.magic_chest_number = stats.magic_chest_number ?: 0
                        viewModel.dendroculus_number = stats.dendroculus_number ?: 0
                        viewModel.total_chest_number =
                            viewModel.precious_chest_number + viewModel.luxurious_chest_number + viewModel.exquisite_chest_number + viewModel.common_chest_number + viewModel.magic_chest_number
                    }
                    val world_explorations = getSummary.data?.world_explorations
                    if (world_explorations != null) {
                        for(world_exploration in world_explorations){
                            val id = world_exploration.id
                            if (id == 1){
                                viewModel.exploration_percentage1 = world_exploration.exploration_percentage ?: 0
                            }else if (id == 2){
                                viewModel.exploration_percentage2 = world_exploration.exploration_percentage ?: 0
                            }else if (id == 3){
                                viewModel.exploration_percentage3 = world_exploration.exploration_percentage ?: 0
                            }else if (id == 4){
                                viewModel.exploration_percentage4 = world_exploration.exploration_percentage ?: 0
                            }else if (id == 5){
                                viewModel.exploration_percentage5 = world_exploration.exploration_percentage ?: 0
                            }else if (id == 6){
                                viewModel.exploration_percentage6 = world_exploration.exploration_percentage ?: 0
                            }else if (id == 7){
                                viewModel.exploration_percentage7 = world_exploration.exploration_percentage ?: 0
                            }else if (id == 8){
                                viewModel.exploration_percentage8 = world_exploration.exploration_percentage ?: 0
                            }
                        }
                    }
                }
            }
            val calendarUrl = Constant.miHoYoSignCalendar_URL
            val calendarResult = getHTTPs.getRequest(calendarUrl, cookie);
            Log.i("calendarResult", calendarResult)
            val getcalendar: getCalendar =
                Gson().fromJson(calendarResult, getCalendar::class.java)
            Log.i("calendarResult", getcalendar.message.toString())
            val lists = getcalendar.data?.list
            if (lists != null) {
                for (item in lists) {
                    val kind = item.kind
                    if (kind == "2") {
                        val break_type = item.break_type
                        if (break_type == "2") {
                            val dropday = item.drop_day
                            if (dropday != null) {
                                Log.i("dropday", dropday.toString())
                                if (dropday.contains("1")) {
                                    Log.i("dropday", "周一")
                                    viewModel.MondayArray =
                                        viewModel.MondayArray.plus(item.title.toString())
                                }
                                if (dropday.contains("2")) {
                                    Log.i("dropday", "周二")
                                    viewModel.TuesdayArray =
                                        viewModel.TuesdayArray.plus(item.title.toString())
                                }
                                if (dropday.contains("3")) {
                                    Log.i("dropday", "周三")
                                    viewModel.WednesdayArray =
                                        viewModel.WednesdayArray.plus(item.title.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
        updateSummary(viewModel)
        saveBoolean("SummaryState", false)
    }.start()
}

fun getTalent(viewModel:MainViewModel, id: String, Avatarname: String) {
    Thread {
        var url = Constant.miHoYoAvatar_URL
        val uid = loadMainUID()
        val cookie = loadMainCookie()
        val server = getServer.get_server(loadMainUID())
        url = url + "avatar_id=" + id + "&uid=" + uid + "&region=" + server
        val result = getHTTPs.getRequest2(url, cookie);
        Log.i("getTalent", result)
        if (result != "failed") {
            val getTalent: getTalentResult =
                Gson().fromJson(result, getTalentResult::class.java)
            val message = getTalent.message
            if (message == "OK") {
                val skill_list = getTalent.data?.skill_list
                if (skill_list != null) {
                    var i = 0
                    for (skill in skill_list) {
                        if ((skill.max_level ?: "0").toInt() > 1) {
                            getImage.urlSaveToImage(skill.icon.toString())
                            if (i == 0) {
                                val id = skill.id.toString()
                                val group_id = skill.group_id.toString()
                                val name = skill.name.toString()
                                val icon = skill.icon.toString()
                                val max_level = skill.max_level.toString()
                                val level_current = skill.level_current.toString()
                                viewModel.TalentidAmap += Avatarname to id
                                viewModel.Talentgroup_idAmap += Avatarname to group_id
                                viewModel.TalentnameAmap += Avatarname to name
                                viewModel.TalenticonAmap += Avatarname to icon
                                viewModel.Talentmax_levelAmap += Avatarname to max_level
                                viewModel.Talentlevel_currentAmap += Avatarname to level_current
                                i += 1
                            } else if (i == 1) {
                                val id = skill.id.toString()
                                val group_id = skill.group_id.toString()
                                val name = skill.name.toString()
                                val icon = skill.icon.toString()
                                val max_level = skill.max_level.toString()
                                val level_current = skill.level_current.toString()
                                viewModel.TalentidEmap += Avatarname to id
                                viewModel.Talentgroup_idEmap += Avatarname to group_id
                                viewModel.TalentnameEmap += Avatarname to name
                                viewModel.TalenticonEmap += Avatarname to icon
                                viewModel.Talentmax_levelEmap += Avatarname to max_level
                                viewModel.Talentlevel_currentEmap += Avatarname to level_current
                                i += 1
                            } else if (i == 2) {
                                val id = skill.id.toString()
                                val group_id = skill.group_id.toString()
                                val name = skill.name.toString()
                                val icon = skill.icon.toString()
                                val max_level = skill.max_level.toString()
                                val level_current = skill.level_current.toString()
                                viewModel.TalentidQmap += Avatarname to id
                                viewModel.Talentgroup_idQmap += Avatarname to group_id
                                viewModel.TalentnameQmap += Avatarname to name
                                viewModel.TalenticonQmap += Avatarname to icon
                                viewModel.Talentmax_levelQmap += Avatarname to max_level
                                viewModel.Talentlevel_currentQmap += Avatarname to level_current
                                i += 1
                            }
                        }
                    }
                }
                val weapon = getTalent.data?.weapon
                if (weapon != null) {
                    val weapon_cat_id = (weapon.weapon_cat_id ?: 0).toString()
                    val weapon_level = (weapon.weapon_level ?: 0).toString()
                    val max_level = (weapon.max_level ?: 0).toString()
                    val level_current = (weapon.level_current ?: 0).toString()

                    viewModel.Weaponweapon_cat_idMap += Avatarname to weapon_cat_id
                    viewModel.Weaponweapon_levelMap += Avatarname to weapon_level
                    viewModel.Weaponmax_levelMap += Avatarname to max_level
                    viewModel.Weaponlevel_currentMap += Avatarname to level_current
                    urlSaveToImage(weapon.icon.toString())
                }
                val reliquary_list = getTalent.data?.reliquary_list
                if (reliquary_list != null) {
                    for (reliquary in reliquary_list) {
                        urlSaveToImage(reliquary.icon.toString())
                    }
                }
            }
            /*Log.i("平A", viewModel.TalentnameAmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("平A", viewModel.Talentlevel_currentAmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("平A", viewModel.Talentmax_levelAmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("E技能", viewModel.TalentnameEmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("E技能", viewModel.Talentlevel_currentEmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("E技能", viewModel.Talentmax_levelEmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("大招", viewModel.TalentnameQmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("大招", viewModel.Talentlevel_currentQmap.getOrElse(Avatarname) { "Unknown" })
            Log.i("大招", viewModel.Talentmax_levelQmap.getOrElse(Avatarname) { "Unknown" })*/
        }
    }.start()
}

fun getCharacter(viewModel: MainViewModel){
    Thread {
        var url = Constant.miHoYoSignCharacter_URL
        if (loadMainUID() != "123456789") {
            val uid = loadMainUID()
            val cookie = loadMainCookie()
            val server = getServer.get_server(loadMainUID())
            var result = getHTTPs.postRequestCookieDS(url, cookie, uid, server)
            Log.i("getCharacter", result)
            if (result == "failed") {
                Thread.sleep(200)
                getCharacter()
            } else {
                val getcharacter: getCharacter = Gson().fromJson(result, getCharacter::class.java)
                val avatars = getcharacter.data?.avatars
                if (avatars != null) {
                    for (avatar in avatars){
                        val avatarname = avatar.name ?: "珊瑚宫心海"
                        val avataricon = avatar.icon ?: "https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Kokomi.png"
                        viewModel.avatariconMap += avatarname to avataricon
                        urlSaveToImage(avataricon)

                        val id = (avatar.weapon?.id ?: 0).toString()
                        val name = avatar.weapon?.name ?: "天目影打刀"
                        val icon = avatar.weapon?.icon ?: "https://upload-bbs.mihoyo.com/game_record/genshin/equip/UI_EquipIcon_Sword_Bakufu.png"
                        val type = (avatar.weapon?.type ?: 0).toString()
                        val rarity = (avatar.weapon?.rarity ?: 0).toString()
                        val level = (avatar.weapon?.level ?: 0).toString()
                        val promote_level = (avatar.weapon?.promote_level ?: 0).toString()
                        val type_name = avatar.weapon?.type_name ?: "单手剑"
                        val desc = avatar.weapon?.desc ?: "传说中连以神速见长的天狗都能斩落的名士订制的刀"
                        val affix_level = (avatar.weapon?.affix_level ?: 0).toString()
                        urlSaveToImage(icon)

                        viewModel.WeaponidMap += avatarname to id
                        viewModel.WeaponnameMap += avatarname to name
                        viewModel.WeaponiconMap += avatarname to icon
                        viewModel.WeapontypeMap += avatarname to type
                        viewModel.WeaponrarityMap += avatarname to rarity
                        viewModel.WeaponlevelMap += avatarname to level
                        viewModel.Weaponpromote_levelMap += avatarname to promote_level
                        viewModel.WeapondescMap += avatarname to type_name
                        viewModel.Weapontype_nameMap += avatarname to desc
                        viewModel.Weaponaffix_levelMap += avatarname to affix_level

                        /*Log.i("id", viewModel.WeaponidMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("WeaponnameMap", viewModel.WeaponnameMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("WeaponiconMap", viewModel.WeaponiconMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("WeapontypeMap", viewModel.WeapontypeMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("WeaponrarityMap", viewModel.WeaponrarityMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("WeaponlevelMap", viewModel.WeaponlevelMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("Weaponpromote_levelMap", viewModel.Weaponpromote_levelMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("WeapondescMap", viewModel.WeapondescMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("Weapontype_nameMap", viewModel.Weapontype_nameMap.getOrElse(avatarname) { "Unknown" })
                        Log.i("Weaponaffix_levelMap", viewModel.Weaponaffix_levelMap.getOrElse(avatarname) { "Unknown" })*/
                    }
                }
            }
        }
    }.start()
}