package com.lei123.YSPocketTools

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.lei123.YSPocketTools.utils.getString
import com.lei123.YSPocketTools.utils.loadString

class MainViewModel : ViewModel() {
    var selectedTab by mutableStateOf(0) // 选中了谁

    var theme by mutableStateOf(YSComposeTheme.Theme.Light)
    var chatting by mutableStateOf(false)

    var miID by mutableStateOf("123456789")
    var minickname by mutableStateOf("芭芭拉拉粑粑")
    var miavatar_url by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Kokomi.png")
    var miip_region by mutableStateOf("北京")

    var LoginState by mutableStateOf(false)
    var cookieBoxState by mutableStateOf(false)
    var settingBoxState by mutableStateOf(false)

    var pageLoadingState by mutableStateOf(false)

    var MainUidChange by mutableStateOf(false)


    var userPhoto by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Kokomi.png")
    var usernickname by mutableStateOf("芭芭拉拉粑粑")
    var level by mutableStateOf("等级")
    var region_name by mutableStateOf("天空岛")

    var notice by mutableStateOf("")
    var notice1 by mutableStateOf("")
    var notice2 by mutableStateOf("")
    var noticeversion by mutableStateOf("1")
    var lastAppVertion by mutableStateOf("1.0")
    var update by mutableStateOf("RTgcJKrWQf2BYPVMy4biqw0KSgD8mxLq")
    var douyin by mutableStateOf("7064504305553902863")
    var bzhan by mutableStateOf("1wT4y1X7mh")

    var uidAddIcon by mutableStateOf(R.drawable.lay_uid_change_light)
    var ifLogin by mutableStateOf(false)
    var uidlist by mutableStateOf(
        arrayOf(
            loadString("uid0"), loadString("uid1"),
            loadString("uid2"), loadString("uid3"),
            loadString("uid4"), loadString("uid5"),
            loadString("uid6"), loadString("uid7"),
            loadString("uid8"), loadString("uid9")
        )
    )

    fun getuidlist(): Array<String> {
        return arrayOf(
            loadString("uid0"), loadString("uid1"),
            loadString("uid2"), loadString("uid3"),
            loadString("uid4"), loadString("uid5"),
            loadString("uid6"), loadString("uid7"),
            loadString("uid8"), loadString("uid9")
        )
    }

    var mainUID by mutableStateOf("123456789")
    var uid0 by mutableStateOf("123456789")
    var uid1 by mutableStateOf("123456789")
    var uid2 by mutableStateOf("123456789")
    var uid3 by mutableStateOf("123456789")
    var uid4 by mutableStateOf("123456789")
    var uid5 by mutableStateOf("123456789")
    var uid6 by mutableStateOf("123456789")
    var uid7 by mutableStateOf("123456789")
    var uid8 by mutableStateOf("123456789")
    var uid9 by mutableStateOf("123456789")

    var mainCookie by mutableStateOf("123456789")
    var cookie0 by mutableStateOf("123456789")
    var cookie1 by mutableStateOf("123456789")
    var cookie2 by mutableStateOf("123456789")
    var cookie3 by mutableStateOf("123456789")
    var cookie4 by mutableStateOf("123456789")
    var cookie5 by mutableStateOf("123456789")
    var cookie6 by mutableStateOf("123456789")
    var cookie7 by mutableStateOf("123456789")
    var cookie8 by mutableStateOf("123456789")
    var cookie9 by mutableStateOf("123456789")

    var fixed_realTimeNote by mutableStateOf("实时便笺")
    var fixed_resinTitle by mutableStateOf("原粹树脂")
    var fixed_homeCoinTitle by mutableStateOf("洞天宝钱")
    var fixed_dailyTaskTitle by mutableStateOf("每日委托")
    var fixed_exploreTitle by mutableStateOf("探索派遣")
    var fixed_weeklyTitle by mutableStateOf("半价周本")
    var fixed_transTitle by mutableStateOf("参量质变仪")
    var fixed_NotAvailable by mutableStateOf("暂无数据")
    var fixed_day by mutableStateOf("天")

    var fixed_TranAvailable by mutableStateOf("可使用")
    var fixed_oneDay by mutableStateOf("一天")
    var fixed_twoDay by mutableStateOf("两天")
    var fixed_threeDay by mutableStateOf("三天")
    var fixed_fourDay by mutableStateOf("四天")
    var fixed_fiveDay by mutableStateOf("五天")
    var fixed_sixDay by mutableStateOf("六天")
    var fixed_sevenDay by mutableStateOf("七天")
    var fixed_UnknownData by mutableStateOf("未知数据")


    var current_Resin by mutableStateOf("120")
    var max_resin by mutableStateOf("160")
    var resin1 by mutableStateOf("160/160")
    var resin_recovery_time by mutableStateOf("10000")
    var resinAim by mutableStateOf("20日 23:59")
    var resinRemain by mutableStateOf("还需，12时30分")

    var current_home_coin by mutableStateOf("2000")
    var max_home_coin by mutableStateOf("2400")
    var homeCoin1 by mutableStateOf("2400/2400")
    var home_coin_recovery_time by mutableStateOf("12000")
    var homeCoinAim by mutableStateOf("20日 23:59")
    var homeCoinRemain by mutableStateOf("还需，12时30分")

    var dailyTask by mutableStateOf("2")
    var dailyTask1 by mutableStateOf(true)
    var dailyTask2 by mutableStateOf(true)
    var dailyTask3 by mutableStateOf(false)
    var dailyTask4 by mutableStateOf(false)
    var total_task_num by mutableStateOf("4")
    var dailyTaskStr by mutableStateOf("2/4")
    var dailyTaskSubmit by mutableStateOf("未提交")


    var current_expedition_num by mutableStateOf("0")
    var max_expedition_num by mutableStateOf("5")
    var explorePhoto1 by mutableStateOf(R.drawable.role_z_kongbai)
    var explorePhoto2 by mutableStateOf(R.drawable.role_z_kongbai)
    var explorePhoto3 by mutableStateOf(R.drawable.role_z_kongbai)
    var explorePhoto4 by mutableStateOf(R.drawable.role_z_kongbai)
    var explorePhoto5 by mutableStateOf(R.drawable.role_z_kongbai)
    var explorePhotoUrl1 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Side_Kokomi.png")
    var explorePhotoUrl2 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Side_Yoimiya.png")
    var explorePhotoUrl3 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Side_Keqing.png")
    var explorePhotoUrl4 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Side_Sayu.png")
    var explorePhotoUrl5 by mutableStateOf("Empty")

    var explorePhotoUrlZheng1 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Kokomi.png")
    var explorePhotoUrlZheng2 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Yoimiya.png")
    var explorePhotoUrlZheng3 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Keqing.png")
    var explorePhotoUrlZheng4 by mutableStateOf("https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Sayu.png")
    var explorePhotoUrlZheng5 by mutableStateOf("Empty")
    var exploreName1 by mutableStateOf("Kokomi")
    var exploreName2 by mutableStateOf("Yoimiya")
    var exploreName3 by mutableStateOf("Keqing")
    var exploreName4 by mutableStateOf("Sayu")
    var exploreName5 by mutableStateOf("Empty")
    var exploreNamelocal1 by mutableStateOf("心海")
    var exploreNamelocal2 by mutableStateOf("宵宫")
    var exploreNamelocal3 by mutableStateOf("刻晴")
    var exploreNamelocal4 by mutableStateOf("早柚")
    var exploreNamelocal5 by mutableStateOf("Empty")

    var exploreBool1 by mutableStateOf(false)
    var exploreBool2 by mutableStateOf(false)
    var exploreBool3 by mutableStateOf(false)
    var exploreBool4 by mutableStateOf(false)
    var exploreBool5 by mutableStateOf(false)
    var exploreState1 by mutableStateOf("Ongoing")
    var exploreState2 by mutableStateOf("Ongoing")
    var exploreState3 by mutableStateOf("Ongoing")
    var exploreState4 by mutableStateOf("Ongoing")
    var exploreState5 by mutableStateOf("Ongoing")
    var explore1 by mutableStateOf("10000")
    var explore2 by mutableStateOf("28000")
    var explore3 by mutableStateOf("58000")
    var explore4 by mutableStateOf("68000")
    var explore5 by mutableStateOf("0")
    var exploreAllSecond by mutableStateOf(72000)
    var ExpAim by mutableStateOf("20日 23:59")
    var ExpRemain by mutableStateOf("还需，12时30分")

    var weekly by mutableStateOf("2")
    var weekly1 by mutableStateOf(true)
    var weekly2 by mutableStateOf(true)
    var weekly3 by mutableStateOf(false)
    var weeklyall by mutableStateOf("3")
    var weeklyStr by mutableStateOf("2/3")


    var transRemainSecond by mutableStateOf("100000")
    var transday by mutableStateOf("0")
    var transRemain by mutableStateOf("三天")
    var transAllSecond by mutableStateOf(604800)
    var transobtained by mutableStateOf(false)
    var transviable by mutableStateOf(false)

    var signMainMessage by mutableStateOf(getString(R.string.signing))
    var signMessage by mutableStateOf("Error")
    var signMessageState by mutableStateOf(false)

    var beginMessage by mutableStateOf(
        "1、本软件会搜集用户的设备信息进行修复bug使用；" + "\n" +
                "2、本软件不会搜集用户的账号或cookie信息；" + "\n" +
                "3、软件未进行混淆和加密，可以随意拆包或抓包查看源代码和请求；" + "\n" +
                "4、使用前请选择忽略电池优化，并按需设置通知选项" + "\n" +
                "5、点击桌面小部件的树脂栏图标或透明小部件的顶部可进入小部件设置界面" + "\n" +
                "6、早八晚八和中午十二点会自动尝试签到所有已登录账号"
    )

    fun endChat(): Boolean {
        if (chatting) {
            chatting = false
            return true
        }
        return false
    }
}