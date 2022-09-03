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
        const val UpdateSummary_URL = Domain_name + "updateSummary"
        const val UpdateResinInfo_URL = Domain_name + "/updateResinInfo"
        const val MiHoYoGetAccount = "https://webapi.account.mihoyo.com/Api/login_by_cookie"
        const val MiHoYoGetUID_URL = "https://api-takumi.mihoyo.com/binding/api/getUserGameRolesByCookie"
        const val MiHoYoGetCookie_URL = "https://hk4e-api.mihoyo.com/event/ys_ledger/monthInfo"
        const val MiHoYoLogin_URL = "https://m.bbs.mihoyo.com/ys/#/login"
        const val miHoYoSign_URL = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/sign"
        const val miHoYoRole_URL =
            "https://api-takumi.mihoyo.com/binding/api/getUserGameRolesByCookie?game_biz=hk4e_cn"
        const val miHoYoSignInfo_URL =
            "https://api-takumi.mihoyo.com/event/bbs_sign_reward/info?act_id=" + act_id + "&uid="
        const val miHoYoSignAward_URL = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/home?act_id=" + act_id

        const val miHoYoSignCalendar_URL = "https://api-static.mihoyo.com/common/blackboard/ys_obc/v1/get_activity_calendar?app_sn=ys_obc"
        const val miHoYoSignCharacter_URL = "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/character"
        //{"role_id":"130267107","server":"cn_gf01"}
        const val miHoYoSignSummary_URL = "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/index?"
        //server=cn_gf01&role_id=130267107
        const val miHoYoAvatarCompute_URL = "https://api-takumi.mihoyo.com/event/e20200928calculate/v2/compute"
        //{"avatar_id":10000058,"avatar_level_current":80,"avatar_level_target":90,"element_attr_id":5,"skill_list":[{"id":5831,"level_current":9,"level_target":10},{"id":5832,"level_current":9,"level_target":10},{"id":5839,"level_current":9,"level_target":10},{"id":5821,"level_current":1,"level_target":1},{"id":5822,"level_current":1,"level_target":1},{"id":5823,"level_current":1,"level_target":1}],"weapon":{"id":14415,"level_current":90,"level_target":90},"reliquary_list":[{"id":9354,"level_current":20,"level_target":20},{"id":7952,"level_current":20,"level_target":20},{"id":7955,"level_current":20,"level_target":20},{"id":9351,"level_current":20,"level_target":20},{"id":7153,"level_current":20,"level_target":20}]}
        const val miHoYoAvatar_URL = "https://api-takumi.mihoyo.com/event/e20200928calculate/v1/sync/avatar/detail?"
        //avatar_id=10000058&uid=130267107&region=cn_gf01

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

        const val BUGLY_ID = "4965fc99e5"

        const val UMappkey = "62ad847e88ccdf4b7e9e6d92"
        const val Channelrelease = "Release"
        const val ChannelBeta = "Beta"
        const val ChannelBeta2 = "Release"
        const val ChannelQ = "Q群"
        const val ChannelB = "B站"
        const val ChannelDouyin = "抖音"


        const val beginMessage = "1、本软件会搜集用户的设备信息进行修复bug使用；" + "\n" +
                "2、本软件不会搜集用户的账号或cookie信息；" + "\n" +
                "3、软件未进行混淆和加密，可以随意拆包或抓包查看源代码和请求；" + "\n" +
                "4、使用前请选择忽略电池优化，给软件所有的启动权限，并按需设置通知选项" + "\n" +
                "5、点击桌面小部件的树脂栏图标或透明小部件的顶部可进入小部件设置界面" + "\n" +
                "6、早八晚八和中午十二点会自动尝试签到所有已登录账号"

        const val updateMessage =
                """
v2.6.1
1、修复了更新通知的获取
2、桌面胡桃嗷增加了声音
3、新增了更新公告和常见问题页面

v2.6
由于所使用的第三方更新服务在接口调整中，软件内更新了很不稳定（作者爱发电太穷了租不起足够用来更新的服务器和流量），如果无法收到更新通知请先移步公众号或qq群
0、新增了桌面胡桃动态小组件
0、重做了设置页面
1、新增了自定义数据刷新时间
2、修复了雷神瞳数字错误的问题
3、修复了底部导航栏的问题
4、实装了草神瞳数量和须弥探索度
5、简约版小部件重绘版图标分了两种主题色调
6、修复了简约型小部件预览时原生图标出现的问题
7、修复了集成小部件白色主题下每日任务逻辑未更改的问题
8、修复了集成小部件设置里部分进度条显示错误
9、尝试修复平板会出现的显示问题

v2.5
由于所使用的第三方更新服务在接口调整中，暂时无法使用软件内更新了（作者太穷租不起足够用来更新的服务器和流量），请大家最近几次更新先异步公众号或qq群
0、新增了数据页面
1、新增可关闭所有悬浮通知的选项
2、更改了桌面小部件每日任务的显示逻辑
3、尝试缩短主页启动时长
4、加回来了水平布局的桌面小部件（至此所有旧版小部件已全部恢复）
5、解决了草元素图标抠图没扣干净的问题
6、主页角色栏滚动逻辑修改
7、优化了主页角色栏的外观
8、更新了salt，签到弹验证码的概率降低
9、优化了过去不知道哪个版本导致的流量消耗异常

v2.4.1
1、修复了因为部分小号没有探索角色导致的闪退
2、回退了主页角色信息的加载逻辑，现在启动速度快了但是会出现某些角色天赋不显示的问题
3、加快了签到重试间隔，点击桌面小部件签到和自动签到增加了取消选项
4、签到最多重试10次

v2.4
0、新增了两款简约型桌面小部件（可自由组合成几十种样式）
1、修复了签到失败的问题
2、优化了软件更新逻辑
3、优化了加载动画的时间
4、优化了桌面小部件成椭圆形的问题
5、增加了通知栏会折叠的解决办法
6、更改主页角色信息加载的逻辑，防止加载过快被官方制裁（现在加载变慢了但是不会出错了）
7、解决了主页角色信息可能出现空白的bug
8、解决了主页角色信息部分手机右边会留白的bug
9、因网络问题没能获取到树脂信息后会自动重试
10、解决了圆形进度条到50%时不显示的bug

v2.3
1、添加了可养角色栏目
2、派遣探索增加进度条圆圈
3、新增自定义签到时间
4、新增一种只有树脂的通知样式
5、解决了部分通知栏显示不全的问题
6、修复了会连续签到很多次的bug
7、左上角添加了检查更新按钮
8、添加小部件时会自动跳出帮助

v2.2
1、修复了部分手机会闪退的bug
2、添加了带探索的通知栏样式
3、加回了三种旧版的桌面小部件样式
4、添加桌面小部件的时候带预览图了

v2.1，紧急更新
1、修复了签到只能签一个角色的bug
2、修复了后台刷新失败的bug
3、增加了 一种正方形不透明的样式

v2.0
1、全新框架全新UI
2、支持了多账号
3、桌面小部件可选择多账号
                """

        const val commonHelp = """
常见问题解决方法：
Q0:所有问题先看
A0:有问题之前请先下群里的最新版，如果还有问题再看下面

Q1、主页或桌面小部件显示Error
A1、一般是网络问题，点击彻底退出，再打开，或等8分钟试试，还不行的话请重新登录

Q2、桌面小部件大小颜色调整设置
A2、透明小部件是点头部进入设置，不透明小部件是点树脂栏进入设置，点击其他位置会进行刷新并签到


Q3、桌面时间不动了
A3、安卓后台被杀很正常，可以直接点击非设置处进行刷新，请在设置内给软件相应的权限，请尽量保持通知栏常驻存在和保持软件在后台（在同时存在常驻通知栏和桌面小组件的情况下大概率能随意杀后台）

Q4、登录之后显示cookie错误
A4、这种情况是米游社里没有原神账号，请核对米游社账号

Q5、桌面小部件显示一片空白/显示错误
A5、请重启手机即可解决

Q6、签到失败
A6、米游社调整了签到接口，目前签到基本都失败了，请等后续更新

Q7、桌面小部件的字体超出来了
A7、如果其他部位是正常的，说明是你手机自带的字体太大了

Q8、桌面的角色没有头怎么办
A8、安卓各种型号分辨率各异，请把小部件往上拉一格即可，可以点击树脂图标进设置界面自己调整大小

Q9、点击桌面小部件无法启动小部件的设置页面
A9、请检查是否打开了软件的后台启动权限，鸿蒙需要有一个后台启动页面的权限，如果都给了还不行，请尝试下面的步骤：1、下掉小部件重新放置；2、重启手机；3、卸载重新安装，并给足权限

Q10:软件闪退
A10:彻底的杀后台重启，如果多次还不行重启手机，如果还不行，@群主反馈，偶尔闪退暂时不管，后台会自动搜集会修复的，闪退的完全用不了了再反馈

Q11:软件无法安装
A11:重新安装 或 关闭手机的外部软件安全检测之后，重新安装

Q12:常驻通知栏显示不全
A12:请到设置里更改通知栏样式

Q13:想要自定义通知/想要锁屏显示通知/想要静默通知
A13:前往手机的系统设置里，更改本软件的通知策略即可

Q14:软件底部的按钮被挡住
A14:请到左右滑动到设置里--是否有虚拟导航键--选择是

Q15:一直弹出签到失败
A15:请到设置里--是否显示悬浮提醒-=选择否

Q16、打开软件一直显示在加载
A16、第一次打开会下载一些图片到本地，请耐心等待

        """
    }
}