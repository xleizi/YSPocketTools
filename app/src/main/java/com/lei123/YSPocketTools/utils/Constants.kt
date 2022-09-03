package com.lei123.YSPocketTools.utils

interface Constant {
    companion object {
        const val delay = 1000
        const val ID_FOR_NORMAL = 1
        const val ID_FOR_BIG_TEXT = 2
        const val ID_FOR_CUSTOM_VIEW = 7
        const val ID_FOR_CUSTOM_VIEW2 = 11
        const val REPLY = "reply"
        const val KEY_TEXT_REPLY = "key_text_reply"
        const val REPLY_MESSAGING = "reply_messaging"
        const val MAKE_AS_READ = "make_as_read"
        const val DELETE = "delete"
        const val BACK = "back"
        const val NEXT = "next"
        const val PAUSE = "pause"
        const val ID_FOR_INBOX = 3
        const val ID_FOR_BIG_PICTURE = 4
        const val ID_FOR_MESSAGING = 5
        const val ID_FOR_MEDIA = 6
        const val ID_FOR_NORMAL_ACTION = 8
        const val ID_FOR_GROUP = 9
        const val UPDATE_CONTENT_DISPOSITION_FILENAME = "树脂.apk"
        const val UPDATE_URL =
            "https://xleiz.coding.net/p/genshinresin/d/genshinresin/git/raw/master/update/GenshinPockageTools-"
        const val UPDATE_URL2 = ".apk?download=true"
        const val ANGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36 Edg/102.0.1245.39"
        const val MiHoYo_ANGENT =
            "Mozilla/5.0 (iPad; CPU OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) miHoYoBBS/2.28.1"
        const val act_id = "e202009291139501"
        const val Domain_name = "http://api-yspockettools.leizi.work/"
        const val AddUID_URL = Domain_name + "/adduid"
        const val UpdateUID_URL = Domain_name + "/updateuid"
        const val GetUID_URL = Domain_name + "/getuid"
        const val GetNotice_URL = Domain_name + "/getnotice"
        const val UpdateDevice_URL = Domain_name + "/updatedevice"
        const val MiHoYoGetUID_URL = "https://api-takumi.mihoyo.com/binding/api/getUserGameRolesByCookie"
        const val MiHoYoGetCookie_URL = "https://hk4e-api.mihoyo.com/event/ys_ledger/monthInfo"
        const val MiHoYoLogin_URL = "https://m.bbs.mihoyo.com/ys/#/login"
        const val miHoYoSign_URL = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/sign"
        const val miHoYoRole_URL =
            "https://api-takumi.mihoyo.com/binding/api/getUserGameRolesByCookie?game_biz=hk4e_cn"
        const val miHoYoSignInfo_URL =
            "https://api-takumi.mihoyo.com/event/bbs_sign_reward/info?act_id=" + act_id + "&uid="
        const val miHoYoSignAward_URL =
            "https://api-takumi.mihoyo.com/event/bbs_sign_reward/home?act_id=" + act_id
        const val weibo =
            "https://weibo.com/p/aj/general/button?ajwvr=6&api=http://i.huati.weibo.com/aj/super/checkin&id=100808fc439dedbb06ca5fd858848e521b8716"
        const val GetIP1_URL = "https://2022.ip138.com/"
        const val GetIP2_URL = "https://ip.cn/api/index?ip=&type=0"
        const val GetMiHoYoIcon = "https://bbs-api.mihoyo.com/user/api/getUserFullInfo?uid="
        const val truePMName = "android.content.pm.IPackageManager\$Stub\$Proxy"

        const val trueApplicationName = "AppApplication"
        const val mmm = "41061961f7bd13228a80aa9d292a9c21"
        const val MD5salt = "lei"
        const val truePMName2 = ""
        const val expTimeTotal = 72000
        const val transForTimeTotal = 604800

        const val BUGLY_ID = ""

        const val UMappkey = ""
        const val Channelrelease = ""
        const val ChannelBeta = ""
        const val ChannelBeta2 = ""
        const val ChannelQ = ""
        const val ChannelB = ""
        const val ChannelDouyin = ""


        const val beginMessage = "1、本软件会搜集用户的设备信息进行修复bug使用；" + "\n" +
                "2、本软件不会搜集用户的账号或cookie信息；" + "\n" +
                "3、软件未进行混淆和加密，可以随意拆包或抓包查看源代码和请求；" + "\n" +
                "4、使用前请选择忽略电池优化，并按需设置通知选项" + "\n" +
                "5、点击桌面小部件的树脂栏图标或透明小部件的顶部可进入小部件设置界面" + "\n" +
                "6、早八晚八和中午十二点会自动尝试签到所有已登录账号"



    }
}