package com.lei123.YSPocketTools.service


import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.lei123.YSPocketTools.AndroidTools.SignAll
import com.lei123.YSPocketTools.AndroidTools.TimeCounter.NeededTime
import com.lei123.YSPocketTools.AndroidTools.startService.startBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startwidgetsService
import com.lei123.YSPocketTools.AndroidTools.systemInformation.getVersion
import com.lei123.YSPocketTools.MainViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.check.cc
import com.lei123.YSPocketTools.entity.getNotice
import com.lei123.YSPocketTools.entity.getResin
import com.lei123.YSPocketTools.http.HTTPs.getDailyHTTPS.Daily
import com.lei123.YSPocketTools.http.HTTPs.getHTTPs
import com.lei123.YSPocketTools.http.HTTPs.getImage
import com.lei123.YSPocketTools.http.HTTPs.getImage.urlSaveToImage
import com.lei123.YSPocketTools.http.HTTPs.getInfo.getUserName
import com.lei123.YSPocketTools.http.HTTPs.getNoticeHTTP
import com.lei123.YSPocketTools.http.HTTPs.saveAccount.updateDeviceInfo
import com.lei123.YSPocketTools.ui.Notification.NotificationService
import com.lei123.YSPocketTools.ui.Notification.noticationServiceDome
import com.lei123.YSPocketTools.utils.*
import com.lei123.YSPocketTools.utils.NoteTidy.noteExplore
import com.lei123.YSPocketTools.utils.NoteTidy.noteHomeCoin
import com.lei123.YSPocketTools.utils.NoteTidy.noteRegin
import com.lei123.YSPocketTools.utils.NoteTidy.noteTask
import com.lei123.YSPocketTools.utils.NoteTidy.noteTransfor
import com.lei123.YSPocketTools.utils.NoteTidy.noteWeek
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import java.text.SimpleDateFormat
import java.util.*

class BaseService : Service() {
    override fun onDestroy() {
        super.onDestroy()
        try {
            task8m.cancel()
            timer8m.cancel()
        } catch (e: Exception) {

        }
        try {
            timer1s.cancel()
            task1s.cancel()
        } catch (e: Exception) {

        }
        try {
            startBasicService(application)
            startwidgetsService(application)
        } catch (e: Exception) {

        }
    }


    var serviceIntent: Intent? = null

    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 0) {
                saveBoolean("baseServiceState", false)
                //常驻通知使用
                //hc = new HTTPClient();
                serviceIntent = Intent(application, NotificationService::class.java)
                    .setPackage(packageName)
                startService(serviceIntent)
                if (loadString(mainUID + "resin_recovery_time") != null) {
                    pushNotication()
                }
                updateNotice()
            } else if (msg.what == 1) {
                //var uid = msg.obj.toString()
                Log.i("msg", msg.obj.toString())
                //saveNoteInfo(msg.obj.toString())
            } else if (msg.what == 2) {

            } else if (msg.what == 3) {

            } else if (msg.what == 4) {

            }
        }
    }

    fun updateNotice(){
        val lastAppVertion = loadString("lastAppVertion", getVersion(application))
        val notice1 = loadString("notice1", "notice1")
        if (lastAppVertion!=getVersion(application)){
            startBigTaxtNotification(
                "最新版本："+ lastAppVertion + "\t如未收到更新推送，请点击右上角检查更新，如果还不行请前往公众号“派蒙口袋工具箱”获取" + "\n" + notice1,
                "resin",
                getString(R.string.ResinupdateTitle),
                getString(R.string.ResinupdateTitle),
                "最新版本："+ lastAppVertion + "\t如未收到更新推送，请点击右上角检查更新，如果还不行请前往公众号“派蒙口袋工具箱”获取" + "\n" + notice1,
                getString(R.string.NoticeStr)
            )
        }
    }

    fun startBigTaxtNotification(
        content: String,
        iconUrl: String,
        title: String,
        SubText: String,
        text: String,
        channel_id: String,
    ) {
        lateinit var largeIcon: Bitmap
        if (iconUrl == "resin") {
            largeIcon = BitmapFactory.decodeResource(application.resources, R.drawable.icon_resin)
        } else if (iconUrl == "homecoin") {
            largeIcon =
                BitmapFactory.decodeResource(application.resources, R.drawable.icon_homecoin)
        } else {
            try {
                largeIcon = imageUrlToBitmap(iconUrl)!!
            } catch (e: Exception) {
                largeIcon =
                    BitmapFactory.decodeResource(application.resources, R.drawable.icon_resin)
            }
        }
        try {
            noticationServiceDome.bigText(
                content, largeIcon,
                title, SubText, text, channel_id,
                application, false, true, true, true, false
            )
        } catch (e: Exception) {

        }
    }

    var ResinNotice1 = true
    var ResinNotice2 = true
    var ResinNotice3 = true
    var ResinNotice4 = true
    var HomeCoinNootice1 = true
    var HomeCoinNootice2 = true
    var HomeCoinNootice3 = true
    var HomeCoinNootice4 = true
    var cuiResinBool = false
    var cuiHomeCoinBool = false
    var cuiExploreBool = false
    var ExploreNotice1 = true
    var ExploreNotice2 = true
    var ExploreNotice3 = true
    var ExploreNotice4 = true
    var ExploreNotice5 = true

    fun pushNotication() {
        loadNoticeBools()
        val resin_recovery_time = loadString(mainUID + "resin_recovery_time").toInt()
        val home_coin_recovery_time = loadString(mainUID + "home_coin_recovery_time").toInt()
        val explore1 = loadString(mainUID + "explore1").toInt()
        val explore2 = loadString(mainUID + "explore2").toInt()
        val explore3 = loadString(mainUID + "explore3").toInt()
        val explore4 = loadString(mainUID + "explore4").toInt()
        val explore5 = loadString(mainUID + "explore5").toInt()
        val exploreNamelocal1 = loadString(mainUID + "exploreNamelocal1")
        val exploreNamelocal2 = loadString(mainUID + "exploreNamelocal2")
        val exploreNamelocal3 = loadString(mainUID + "exploreNamelocal3")
        val exploreNamelocal4 = loadString(mainUID + "exploreNamelocal4")
        val exploreNamelocal5 = loadString(mainUID + "exploreNamelocal5")
        val exploreName1 = loadString(mainUID + "exploreName1")
        val exploreName2 = loadString(mainUID + "exploreName2")
        val exploreName3 = loadString(mainUID + "exploreName3")
        val exploreName4 = loadString(mainUID + "exploreName4")
        val exploreName5 = loadString(mainUID + "exploreName5")
        val explorePhotoUrl1 = loadString(mainUID + "explorePhotoUrl1")
        val explorePhotoUrl2 = loadString(mainUID + "explorePhotoUrl2")
        val explorePhotoUrl3 = loadString(mainUID + "explorePhotoUrl3")
        val explorePhotoUrl4 = loadString(mainUID + "explorePhotoUrl4")
        val explorePhotoUrl5 = loadString(mainUID + "explorePhotoUrl5")
        Log.i("resin_recovery_time2", resin_recovery_time.toString())

        //if (true) {
        if (resin_recovery_time > 3600 && resin_recovery_time < 7200) {
            //2小时提醒
            if (ResinNotice1) {
                val rt = NeededTime(resin_recovery_time)
                startBigTaxtNotification(
                    getString(R.string.Resinhave) + rt + getString(R.string.jiuManLe),
                    "resin",
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.Resinhave) + rt + getString(R.string.jiuManLe),
                    getString(R.string.NoticeStr)
                )
                ResinNotice1 = false
            }
        } else if (resin_recovery_time > 600 && resin_recovery_time < 3600) {
            //1小时提醒
            if (ResinNotice2 || cuiResinBool) {
                val rt = NeededTime(resin_recovery_time)
                startBigTaxtNotification(
                    getString(R.string.Resinhave) + rt + getString(R.string.jiuManLe),
                    "resin",
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.Resinhave) + rt + getString(R.string.jiuManLe),
                    getString(R.string.NoticeStr)
                )
                ResinNotice2 = false
            }
        } else if (resin_recovery_time > 0 && resin_recovery_time < 600) {
            //1小时提醒
            if (ResinNotice3 || cuiResinBool) {
                val rt = NeededTime(resin_recovery_time)
                startBigTaxtNotification(
                    getString(R.string.Resinhave) + rt + getString(R.string.jiuManLe),
                    "resin",
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.Resinhave) + rt + getString(R.string.jiuManLe),
                    getString(R.string.NoticeStr)
                )
                ResinNotice3 = false
            }
        } else if (resin_recovery_time == 0) {
            //满了提醒
            if (ResinNotice4 || cuiResinBool) {
                startBigTaxtNotification(
                    getString(R.string.ResinAlreadyFull),
                    "resin",
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.ResinNoticeTitle),
                    getString(R.string.ResinAlreadyFull),
                    getString(R.string.NoticeStr)
                )
                ResinNotice4 = false
            }
        } else {
            //重置
            ResinNotice1 = true
            ResinNotice2 = true
            ResinNotice3 = true
            ResinNotice4 = true
        }

        //if (true){
        if (home_coin_recovery_time > 3600 && home_coin_recovery_time < 7200) {
            //2小时提醒
            if (HomeCoinNootice1) {
                val rt = NeededTime(home_coin_recovery_time)
                startBigTaxtNotification(
                    getString(R.string.HomeCoinhave) + rt + getString(R.string.jiuManLe),
                    "resin",
                    getString(R.string.HomeCoinNoticeTitle),
                    getString(R.string.HomeCoinNoticeTitle),
                    getString(R.string.HomeCoinhave) + rt + getString(R.string.jiuManLe),
                    getString(R.string.NoticeStr)
                )
                HomeCoinNootice1 = false
            }
        } else if (home_coin_recovery_time > 600 && home_coin_recovery_time < 3600) {
            //1小时提醒
            if (HomeCoinNootice2 || cuiHomeCoinBool) {
                val rt = NeededTime(home_coin_recovery_time)
                startBigTaxtNotification(
                    getString(R.string.HomeCoinhave) + rt + getString(R.string.jiuManLe),
                    "resin",
                    getString(R.string.HomeCoinNoticeTitle),
                    getString(R.string.HomeCoinNoticeTitle),
                    getString(R.string.HomeCoinhave) + rt + getString(R.string.jiuManLe),
                    getString(R.string.NoticeStr)
                )
                HomeCoinNootice2 = false
            }
        } else if (home_coin_recovery_time > 0 && home_coin_recovery_time < 600) {
            //10分钟提醒
            if (HomeCoinNootice3 || cuiHomeCoinBool) {
                val rt = NeededTime(home_coin_recovery_time)
                startBigTaxtNotification(
                    getString(R.string.HomeCoinhave) + rt + getString(R.string.jiuManLe),
                    "resin",
                    getString(R.string.HomeCoinNoticeTitle),
                    getString(R.string.HomeCoinNoticeTitle),
                    getString(R.string.HomeCoinhave) + rt + getString(R.string.jiuManLe),
                    getString(R.string.NoticeStr)
                )
                HomeCoinNootice3 = false
            } else if (home_coin_recovery_time == 0) {
                //满了提醒
                if (HomeCoinNootice4 || cuiHomeCoinBool) {
                    startBigTaxtNotification(
                        getString(R.string.HomeCoinAlreadyFull),
                        "resin",
                        getString(R.string.HomeCoinNoticeTitle),
                        getString(R.string.HomeCoinNoticeTitle),
                        getString(R.string.HomeCoinAlreadyFull),
                        getString(R.string.NoticeStr)
                    )
                    HomeCoinNootice4 = false
                }
            } else {
                //重置
                HomeCoinNootice1 = true
                HomeCoinNootice2 = true
                HomeCoinNootice3 = true
                HomeCoinNootice4 = true
            }
        }

        if (explore1 == 0) {
            if (ExploreNotice1 || cuiExploreBool) {
                startBigTaxtNotification(
                    getString(R.string.ExploreFinish),
                    explorePhotoUrl1,
                    getString(R.string.ExploreNoticeStr),
                    getString(R.string.ExploreNoticeStr),
                    exploreNamelocal1 + getString(R.string.ExploreAlreadyFinish),
                    getString(R.string.NoticeStr)
                )
                ExploreNotice1 = false
            }
        } else {
            ExploreNotice1 = true
        }

        if (explore2 == 0) {
            if (ExploreNotice2 || cuiExploreBool) {
                startBigTaxtNotification(
                    getString(R.string.ExploreFinish),
                    explorePhotoUrl2,
                    getString(R.string.ExploreNoticeStr),
                    getString(R.string.ExploreNoticeStr),
                    exploreNamelocal2 + getString(R.string.ExploreAlreadyFinish),
                    getString(R.string.NoticeStr)
                )
                ExploreNotice2 = false
            }
        } else {
            ExploreNotice2 = true
        }

        if (explore3 == 0) {
            if (ExploreNotice3 || cuiExploreBool) {
                startBigTaxtNotification(
                    getString(R.string.ExploreFinish),
                    explorePhotoUrl3,
                    getString(R.string.ExploreNoticeStr),
                    getString(R.string.ExploreNoticeStr),
                    exploreNamelocal3 + getString(R.string.ExploreAlreadyFinish),
                    getString(R.string.NoticeStr)
                )
                ExploreNotice3 = false
            }
        } else {
            ExploreNotice3 = true
        }
        if (explore4 == 0) {
            if (ExploreNotice4 || cuiExploreBool) {
                startBigTaxtNotification(
                    getString(R.string.ExploreFinish),
                    explorePhotoUrl4,
                    getString(R.string.ExploreNoticeStr),
                    getString(R.string.ExploreNoticeStr),
                    exploreNamelocal4 + getString(R.string.ExploreAlreadyFinish),
                    getString(R.string.NoticeStr)
                )
                ExploreNotice4 = false
            }
        } else {
            ExploreNotice4 = true
        }

        if (explore5 == 0) {
            if (ExploreNotice5 || cuiExploreBool) {
                startBigTaxtNotification(
                    getString(R.string.ExploreFinish),
                    explorePhotoUrl5,
                    getString(R.string.ExploreNoticeStr),
                    getString(R.string.ExploreNoticeStr),
                    exploreNamelocal5 + getString(R.string.ExploreAlreadyFinish),
                    getString(R.string.NoticeStr)
                )
                ExploreNotice5 = false
            }
        } else {
            ExploreNotice5 = true
        }
        saveNoticeBools()
    }

    fun saveNoticeBools() {
        saveBoolean("ResinNotice1", ResinNotice1)
        saveBoolean("ResinNotice2", ResinNotice2)
        saveBoolean("ResinNotice3", ResinNotice3)
        saveBoolean("ResinNotice4", ResinNotice4)
        saveBoolean("HomeCoinNootice1", HomeCoinNootice1)
        saveBoolean("HomeCoinNootice2", HomeCoinNootice2)
        saveBoolean("HomeCoinNootice3", HomeCoinNootice3)
        saveBoolean("HomeCoinNootice4", HomeCoinNootice4)
        saveBoolean("ExploreNotice1", ExploreNotice1)
        saveBoolean("ExploreNotice2", ExploreNotice2)
        saveBoolean("ExploreNotice3", ExploreNotice3)
        saveBoolean("ExploreNotice4", ExploreNotice4)
        saveBoolean("ExploreNotice5", ExploreNotice5)
    }

    fun loadNoticeBools() {
        cuiResinBool = loadString("cuiResinBool",getString(R.string.close))==getString(R.string.open)
        cuiHomeCoinBool = loadString("cuiResinBool",getString(R.string.close))==getString(R.string.open)
        cuiExploreBool = loadString("cuiResinBool",getString(R.string.close))==getString(R.string.open)
        ResinNotice1 = loadBoolean("ResinNotice1")
        ResinNotice2 = loadBoolean("ResinNotice2")
        ResinNotice3 = loadBoolean("ResinNotice3")
        ResinNotice4 = loadBoolean("ResinNotice4")
        HomeCoinNootice1 = loadBoolean("HomeCoinNootice1")
        HomeCoinNootice2 = loadBoolean("HomeCoinNootice2")
        HomeCoinNootice3 = loadBoolean("HomeCoinNootice3")
        HomeCoinNootice4 = loadBoolean("HomeCoinNootice4")
        ExploreNotice1 = loadBoolean("ExploreNotice1")
        ExploreNotice2 = loadBoolean("ExploreNotice2")
        ExploreNotice3 = loadBoolean("ExploreNotice3")
        ExploreNotice4 = loadBoolean("ExploreNotice4")
        ExploreNotice5 = loadBoolean("ExploreNotice5")
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        saveBoolean("baseServiceState", true)
        //if(loadBoolean("ifToast", false)) "basicServiceRun".toast()
        loadUser()
        //Log.i("onCreate", "-------------------------------------")
        //if(loadBoolean("ifToast", false)) "basicService1".toast()
        //um()
        //BoolstoTrue()
        //val cookie =  "UM_distinctid=18248286d8530a-0008d47dc55d54-4a626c39-46e60-18248286d8635a; _ga=GA1.2.1320723920.1659067986; _gid=GA1.2.374300905.1659067986; CNZZDATA1275023096=1401200179-1659064591-%7C1659064591; _gat=1; _MHYUUID=51be7f34-b93f-4f7e-8959-5b473d8f3867; ltoken=hvD6a2ZOI0TMaCa0N7x870gf9AwWy8dTs8ytGsrE; ltuid=218675477; cookie_token=j9B8br4BYM6Y0UdoLkafri55BqIExXiykvPKK7fd; account_id=218675477"
        val reminute = when(loadString("refreshTime", getString(R.string.minute8))) {
            getString(R.string.minute1) -> 1
            getString(R.string.minute2) -> 2
            getString(R.string.minute3) -> 3
            getString(R.string.minute5) -> 5
            getString(R.string.minute8) -> 8
            getString(R.string.minute10) -> 10
            getString(R.string.minute15) -> 15
            getString(R.string.minute20) -> 20
            getString(R.string.minute30) -> 30
            else -> 8
        }

        timer8m.schedule(task8m, 0, (reminute * 60 * 1000).toLong())
        timer1s.schedule(task1s, 0, (1 * 1 * 1000).toLong())

    }

    /**
     * 开启友盟sdk
     */
    private fun um() {
        cc.ccc() //检测是否被解包
        //当用户使用自有账号登录时，可以这样统计：
        UMConfigure.init(
            this, Constant.UMappkey, Constant.Channelrelease,
            UMConfigure.DEVICE_TYPE_PHONE, null
        );
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }

    var clock8m = 470
    var clock1m = 50

    // 日期格式
    private val t = SimpleDateFormat("HHmmss")
    private val m = SimpleDateFormat("mm")
    private val s = SimpleDateFormat("ss")
    private val d = SimpleDateFormat("yyyyMMdd")
    private val w = SimpleDateFormat("EEEE")
    var minute: String = ""
    var second: String = ""
    var time: String = ""
    var day: String = ""
    var week: String = ""

    var BasicService = false
    private val timer1s = Timer(true)
    private val task1s: TimerTask = object : TimerTask() {
        override fun run() {

            if (BasicService){
                Thread.sleep(10000)
                if (mainUID != "123456789" && mainCookie != "123456789") {
                    getResinInfo(mainUID, mainCookie)
                    updateDeviceInfo(mainUID) //获取本地信息上传，并获取应用版本信息 之后获取米游社便签
                }
                getNoticeInfo()
                getResinInfos()
            }
            if (loadString("ifAutoSign",getString(R.string.Yes))==getString(R.string.Yes)){
                calendar()
            }
            startwidgetsService(application)

            val msg = Message()
            msg.what = 2
            handler.sendMessage(msg)
        }
    }

    fun calendar() {
        time = t.format(Date())
        week = w.format(Date())
        /*second = s.format(Date())
        minute = m.format(Date())
        day = d.format(Date())
        Log.i("task1s", "---------------------------------------------")
        Log.i("second", second)
        Log.i("minute", minute)
        Log.i("week", week)
        Log.i("time", time)
        Log.i("day", day)
        Log.i("task1s", "---------------------------------------------")*/
        //Log.i("calendartime", time)
        val personalTime = loadString("signTime", "80000")
        if ((time == "80000") or (time == "120000") or (time == "200000") or (time == personalTime)) {
            SignAll()
        }
    }

    var a = 5

    private val timer8m = Timer(true)
    private val task8m: TimerTask = object : TimerTask() {
        override fun run() {
            um()
            if (mainUID != "123456789" && mainCookie != "123456789") {
                getResinInfo(mainUID, mainCookie)
                updateDeviceInfo(mainUID) //获取本地信息上传，并获取应用版本信息 之后获取米游社便签
            }
            a += 1
            if(a > 6){
                getNoticeInfo()
                if (mainUID != "123456789" && mainCookie != "123456789") {
                    updateDeviceInfo(mainUID) //获取本地信息上传，并获取应用版本信息 之后获取米游社便签
                }
                a = 0
            }
            getResinInfos()
            val msg = Message()
            msg.what = 2
            handler.sendMessage(msg)
        }
    }
    /**
     * 读取通知信息
     */
    private fun getNoticeInfo() {
        Thread {
            val getNotice: getNotice = getNoticeHTTP()
            if (getNotice.notice1 == "Error") {
            } else {
                saveString("notice",getNotice.notice ?: "Error")
                saveString("notice1",getNotice.notice1 ?: "Error")
                saveString("notice2",getNotice.notice2 ?: "Error")
                saveString("notice3", getNotice.notice3 ?: "Error")
                val backgroundimage = getNotice.backgroundimage ?: "https://i.pixiv.re/img-master/img/2021/10/21/22/32/58/93593716_p0_master1200.jpg"
                saveString("backgroundimage", backgroundimage)
                urlSaveToImage(backgroundimage)
                saveString("noticeversion",getNotice.noticeversion ?: "1")
                saveString("lastAppVertion",getNotice.appversion ?: getVersion(application))
                saveString("update",getNotice.noticeversion ?: "RTgcJKrWQf2BYPVMy4biqw0KSgD8mxLq")
                saveString("douyin",getNotice.noticeversion ?: "7064504305553902863")
                saveString("bzhan",getNotice.noticeversion ?: "1wT4y1X7mh")
            }

            //向主线程发送数据
            val msg = Message.obtain()
            msg.what = 2
            handler.sendMessage(msg)
        }.start()
    }
    /**
     * 加载本地的用户信息
     */
    private fun loadUser() {
        mainUID = loadMainUID()
        mainCookie = loadMainCookie()
    }

    var mainUID = "123456789"
    var mainCookie = "123456789"

    fun loadUID() {
        mainUID = loadMainUID()
        mainCookie = loadMainCookie()
    }

    fun getResinInfos() {
        for (i in 0..9) {
            var uids = loadString("uid".plus(i.toString()))
            var cookies = loadString("cookie".plus(i.toString()))
            if (uids != "123456789" && cookies != "123456789" && uids != mainUID) {
                Log.i("loaduid", uids)
                getResinInfo(uids, cookies)
            }
        }
    }

    private fun getResinInfo(uid: String, cookie: String) {
        Thread {
            val result = Daily(uid, cookie)
            Log.i("uid", uid)
            Log.i("result", result)
            //result.toast()
            if (result != "failed") {
                val getResin: getResin = Gson().fromJson(result, getResin::class.java)
                val data = getResin.data
                if (data != null) {
                    val noteResinList = noteRegin(data)
                    saveString(uid + "current_Resin", noteResinList[0])
                    saveString(uid + "max_resin", noteResinList[1])
                    saveString(uid + "resin1", noteResinList[2])
                    saveString(uid + "resin_recovery_time", noteResinList[3])
                    saveString(uid + "resinAim", noteResinList[4])
                    saveString(uid + "resinRemain", noteResinList[5])

                    val noteTaskList = noteTask(uid, data)
                    saveString(uid + "dailyTask", noteTaskList[0])
                    saveString(uid + "total_task_num", noteTaskList[1])
                    saveString(uid + "dailyTaskStr", noteTaskList[2])
                    saveString(uid + "dailyTaskSubmit", noteTaskList[3])


                    val noteWeekList = noteWeek(uid, data)
                    saveString(uid + "weekly", noteWeekList[0])
                    saveString(uid + "weeklyall", noteWeekList[1])
                    saveString(uid + "weeklyStr", noteWeekList[2])


                    val noteHomeCoinList = noteHomeCoin(data)
                    saveString(uid + "current_home_coin", noteHomeCoinList[0])
                    saveString(uid + "max_home_coin", noteHomeCoinList[1])
                    saveString(uid + "homeCoin1", noteHomeCoinList[2])
                    saveString(uid + "home_coin_recovery_time", noteHomeCoinList[3])
                    saveString(uid + "homeCoinAim", noteHomeCoinList[4])
                    saveString(uid + "homeCoinRemain", noteHomeCoinList[5])

                    val noteExploreList = noteExplore(uid, data)
                    saveString(uid + "current_expedition_num", noteExploreList[0])
                    saveString(uid + "max_expedition_num", noteExploreList[1])
                    saveString(uid + "explorePhotoUrl1", noteExploreList[2])
                    saveString(uid + "explorePhotoUrl2", noteExploreList[3])
                    saveString(uid + "explorePhotoUrl3", noteExploreList[4])
                    saveString(uid + "explorePhotoUrl4", noteExploreList[5])
                    saveString(uid + "explorePhotoUrl5", noteExploreList[6])
                    saveString(uid + "exploreState1", noteExploreList[7])
                    saveString(uid + "exploreState2", noteExploreList[8])
                    saveString(uid + "exploreState3", noteExploreList[9])
                    saveString(uid + "exploreState4", noteExploreList[10])
                    saveString(uid + "exploreState5", noteExploreList[11])
                    saveString(uid + "explore1", noteExploreList[12])
                    saveString(uid + "explore2", noteExploreList[13])
                    saveString(uid + "explore3", noteExploreList[14])
                    saveString(uid + "explore4", noteExploreList[15])
                    saveString(uid + "explore5", noteExploreList[16])
                    saveString(uid + "explorePhotoUrlZheng1", noteExploreList[17])
                    saveString(uid + "explorePhotoUrlZheng2", noteExploreList[18])
                    saveString(uid + "explorePhotoUrlZheng3", noteExploreList[19])
                    saveString(uid + "explorePhotoUrlZheng4", noteExploreList[20])
                    saveString(uid + "explorePhotoUrlZheng5", noteExploreList[21])
                    saveString(uid + "exploreName1", noteExploreList[22])
                    saveString(uid + "exploreName2", noteExploreList[23])
                    saveString(uid + "exploreName3", noteExploreList[24])
                    saveString(uid + "exploreName4", noteExploreList[25])
                    saveString(uid + "exploreName5", noteExploreList[26])
                    saveString(uid + "exploreNamelocal1", noteExploreList[27])
                    saveString(uid + "exploreNamelocal2", noteExploreList[28])
                    saveString(uid + "exploreNamelocal3", noteExploreList[29])
                    saveString(uid + "exploreNamelocal4", noteExploreList[30])
                    saveString(uid + "exploreNamelocal5", noteExploreList[31])
                    saveString(uid + "ExpAim", noteExploreList[32])
                    saveString(uid + "ExpRemain", noteExploreList[33])
                    saveString(uid + "explore1Aim", noteExploreList[34])
                    saveString(uid + "explore2Aim", noteExploreList[35])
                    saveString(uid + "explore3Aim", noteExploreList[36])
                    saveString(uid + "explore4Aim", noteExploreList[37])
                    saveString(uid + "explore5Aim", noteExploreList[38])
                    saveString(uid + "explore1Remain", noteExploreList[39])
                    saveString(uid + "explore2Remain", noteExploreList[40])
                    saveString(uid + "explore3Remain", noteExploreList[41])
                    saveString(uid + "explore4Remain", noteExploreList[42])
                    saveString(uid + "explore5Remain", noteExploreList[43])

                    val noteTransforList = noteTransfor(uid, data)
                    saveString(uid + "transday", noteTransforList[0])
                    saveString(uid + "transRemain", noteTransforList[1])

                    saveString(uid + "transRemainSecond", noteTransforList[2])

                    val userNameList = getUserName(uid, cookie)
                    saveString(uid + "usernickname", userNameList.get(0))
                    saveString(uid + "level", userNameList.get(1))
                    saveString(uid + "region_name", userNameList.get(2))

                    BasicService = false


                    updateResinInfo(uid)

                } else {
                    if(loadString("ifToast", getString(R.string.Yes))==getString(R.string.Yes)) getString(R.string.loadError).toast()
                }
            } else {
                if(loadString("ifToast",
                        com.lei123.YSPocketTools.utils.getString(R.string.Yes)
                    )== com.lei123.YSPocketTools.utils.getString(R.string.Yes)
                ) "uid:$uid${getString(R.string.netError)}".toast()
                BasicService = true
                //saveNoteInfo(uid)
            }
            if (uid == mainUID) {
                val msg = Message()
                msg.what = 0
                handler.sendMessage(msg)
            }
        }.start()
    }

    fun updateResinInfo(uid: String){

        val dataObject = JsonObject()
        dataObject.addProperty("resin", loadString(uid + "current_Resin"))

        dataObject.addProperty("dailyTask", loadString(uid + "dailyTask"))
        dataObject.addProperty("dailyTaskSubmit", loadString(uid + "dailyTaskSubmit"))

        dataObject.addProperty("weekly", loadString(uid + "weekly"))
        dataObject.addProperty("current_home_coin", loadString(uid + "current_home_coin"))

        val expObject = JsonObject()
        expObject.addProperty("exploreNamelocal1", loadString(uid + "exploreNamelocal1"))
        expObject.addProperty("exploreNamelocal2", loadString(uid + "exploreNamelocal2"))
        expObject.addProperty("exploreNamelocal3", loadString(uid + "exploreNamelocal3"))
        expObject.addProperty("exploreNamelocal4", loadString(uid + "exploreNamelocal4"))
        expObject.addProperty("exploreNamelocal5", loadString(uid + "exploreNamelocal5"))
        expObject.addProperty("explore1", loadString(uid + "explore1"))
        expObject.addProperty("explore2", loadString(uid + "explore2"))
        expObject.addProperty("explore3", loadString(uid + "explore3"))
        expObject.addProperty("explore4", loadString(uid + "explore4"))
        expObject.addProperty("explore5", loadString(uid + "explore5"))

        dataObject.add("explore", expObject)

        val sdf = SimpleDateFormat("HH:mm:ss")
        val time = sdf.format(Date())

        val jsonObject = JsonObject()
        jsonObject.addProperty("uid", uid)
        jsonObject.addProperty("updateTime", time)
        jsonObject.add("data", dataObject)

        Log.i("jsonObject", jsonObject.toString())
        getHTTPs.updateRequest(Constant.UpdateResinInfo_URL, jsonObject.toString(), uid,"resininfo")
    }

    fun logNote(uid: String) {
        Log.i("uid", uid)
        Log.i("line", "========================================================")
        Log.i("transRemainSecond", transRemainSecond)
        Log.i("line", "========================================================")
        Log.i("explorePhotoUrl1", explorePhotoUrl1)
        Log.i("explorePhotoUrl2", explorePhotoUrl2)
        Log.i("explorePhotoUrl3", explorePhotoUrl3)
        Log.i("explorePhotoUrl4", explorePhotoUrl4)
        Log.i("explorePhotoUrl5", explorePhotoUrl5)
        Log.i("line", "========================================================")
        Log.i("exploreState1", exploreState1)
        Log.i("exploreState2", exploreState2)
        Log.i("exploreState3", exploreState3)
        Log.i("exploreState4", exploreState4)
        Log.i("exploreState5", exploreState5)
        Log.i("line", "========================================================")
        Log.i("explore1", explore1)
        Log.i("explore2", explore2)
        Log.i("explore3", explore3)
        Log.i("explore4", explore4)
        Log.i("explore5", explore5)
        Log.i("line", "========================================================")
        Log.i("explorePhotoUrlZheng1", explorePhotoUrlZheng1)
        Log.i("explorePhotoUrlZheng2", explorePhotoUrlZheng2)
        Log.i("explorePhotoUrlZheng3", explorePhotoUrlZheng3)
        Log.i("explorePhotoUrlZheng4", explorePhotoUrlZheng4)
        Log.i("explorePhotoUrlZheng5", explorePhotoUrlZheng5)
        Log.i("line", "========================================================")
        Log.i("exploreName1", exploreName1)
        Log.i("exploreName2", exploreName2)
        Log.i("exploreName3", exploreName3)
        Log.i("exploreName4", exploreName4)
        Log.i("exploreName5", exploreName5)
        Log.i("line", "========================================================")
        Log.i("exploreNamelocal1", exploreNamelocal1)
        Log.i("exploreNamelocal2", exploreNamelocal2)
        Log.i("exploreNamelocal3", exploreNamelocal3)
        Log.i("exploreNamelocal4", exploreNamelocal4)
        Log.i("exploreNamelocal5", exploreNamelocal5)
        Log.i("line", "========================================================")
        Log.i("current_Resin", current_Resin)
        Log.i("max_resin", max_resin)
        Log.i("resin1", resin1)
        Log.i("resin_recovery_time", resin_recovery_time)
        Log.i("resinAim", resinAim)
        Log.i("resinRemain", resinRemain)
        Log.i("line", "========================================================")
        Log.i("dailyTask", dailyTask)
        Log.i("total_task_num", total_task_num)
        Log.i("dailyTaskStr", dailyTaskStr)
        Log.i("dailyTaskSubmit", dailyTaskSubmit)
        Log.i("line", "========================================================")
        Log.i("weekly", weekly)
        Log.i("weeklyall", weeklyall)
        Log.i("weeklyStr", weeklyStr)
        Log.i("line", "========================================================")
        Log.i("current_home_coin", current_home_coin)
        Log.i("max_home_coin", max_home_coin)
        Log.i("homeCoin1", homeCoin1)
        Log.i("home_coin_recovery_time", home_coin_recovery_time)
        Log.i("homeCoinAim", homeCoinAim)
        Log.i("homeCoinRemain", homeCoinRemain)
        Log.i("line", "========================================================")
        if (loadBoolean(uid + "weekly1")) {
            Log.i("result", "完成周本")
        }
        if (loadBoolean(uid + "dailyTask1")) {
            Log.i("result", "完成每日委托")
        }
        Log.i("line", "========================================================")
    }

    fun saveNoteInfo(uid: String) {
        saveString(uid + "current_Resin", current_Resin)
        saveString(uid + "max_resin", max_resin)
        saveString(uid + "resin1", resin1)
        saveString(uid + "resin_recovery_time", resin_recovery_time)
        saveString(uid + "resinAim", resinAim)
        saveString(uid + "resinRemain", resinRemain)

        saveString(uid + "dailyTask", dailyTask)
        saveString(uid + "total_task_num", total_task_num)
        saveString(uid + "dailyTaskStr", dailyTaskStr)
        saveString(uid + "dailyTaskSubmit", dailyTaskSubmit)

        saveString(uid + "weekly", weekly)
        saveString(uid + "weeklyall", weeklyall)
        saveString(uid + "weeklyStr", weeklyStr)


        saveString(uid + "current_home_coin", current_home_coin)
        saveString(uid + "max_home_coin", max_home_coin)
        saveString(uid + "homeCoin1", homeCoin1)
        saveString(uid + "home_coin_recovery_time", home_coin_recovery_time)
        saveString(uid + "homeCoinAim", homeCoinAim)
        saveString(uid + "homeCoinRemain", homeCoinRemain)

        saveString(uid + "current_expedition_num", current_expedition_num)
        saveString(uid + "max_expedition_num", max_expedition_num)
        saveString(uid + "explorePhotoUrl1", explorePhotoUrl1)
        saveString(uid + "explorePhotoUrl2", explorePhotoUrl2)
        saveString(uid + "explorePhotoUrl3", explorePhotoUrl3)
        saveString(uid + "explorePhotoUrl4", explorePhotoUrl4)
        saveString(uid + "explorePhotoUrl5", explorePhotoUrl5)
        saveString(uid + "exploreState1", exploreState1)
        saveString(uid + "exploreState2", exploreState2)
        saveString(uid + "exploreState3", exploreState3)
        saveString(uid + "exploreState4", exploreState4)
        saveString(uid + "exploreState5", exploreState5)
        saveString(uid + "explore1", explore1)
        saveString(uid + "explore2", explore2)
        saveString(uid + "explore3", explore3)
        saveString(uid + "explore4", explore4)
        saveString(uid + "explore5", explore5)
        saveString(uid + "explorePhotoUrlZheng1", explorePhotoUrlZheng1)
        saveString(uid + "explorePhotoUrlZheng2", explorePhotoUrlZheng2)
        saveString(uid + "explorePhotoUrlZheng3", explorePhotoUrlZheng3)
        saveString(uid + "explorePhotoUrlZheng4", explorePhotoUrlZheng4)
        saveString(uid + "explorePhotoUrlZheng5", explorePhotoUrlZheng5)
        saveString(uid + "exploreName1", exploreName1)
        saveString(uid + "exploreName2", exploreName2)
        saveString(uid + "exploreName3", exploreName3)
        saveString(uid + "exploreName4", exploreName4)
        saveString(uid + "exploreName5", exploreName5)
        saveString(uid + "exploreNamelocal1", exploreNamelocal1)
        saveString(uid + "exploreNamelocal2", exploreNamelocal2)
        saveString(uid + "exploreNamelocal3", exploreNamelocal3)
        saveString(uid + "exploreNamelocal4", exploreNamelocal4)
        saveString(uid + "exploreNamelocal5", exploreNamelocal5)
        saveString(uid + "ExpAim", ExpAim)
        saveString(uid + "ExpRemain", ExpRemain)

        saveString(uid + "transRemainSecond", transRemainSecond)
    }

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
    var ExpAim = "Empty"
    var ExpRemain = "Empty"

    var transRemainSecond = "100000"

    var current_expedition_num = "5"
    var max_expedition_num = "5"
    var explorePhotoUrl1 = "Empty"
    var explorePhotoUrl2 = "Empty"
    var explorePhotoUrl3 = "Empty"
    var explorePhotoUrl4 = "Empty"
    var explorePhotoUrl5 = "Empty"
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

    var current_home_coin = "120"
    var max_home_coin = "160"
    var homeCoin1 = "Error"
    var home_coin_recovery_time = "10000"
    var homeCoinAim = "Error"
    var homeCoinRemain = "Error"

    var weekly = "3"
    var weeklyall = "3"
    var weeklyStr = "Error"

    var current_Resin = "160"
    var max_resin = "160"
    var resin1 = "Error"
    var resin_recovery_time = "10000"
    var resinAim = "Error"
    var resinRemain = "Error"
    var dailyTask = "0"
    var total_task_num = "4"
    var dailyTaskStr = "Error"
    var dailyTaskSubmit = "Error"
}