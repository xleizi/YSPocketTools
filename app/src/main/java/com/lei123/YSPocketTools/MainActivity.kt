package com.lei123.YSPocketTools

import android.content.Intent
import android.os.*
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.glance.appwidget.updateAll
import com.google.gson.Gson
import com.lei123.YSPocketTools.AndroidTools.*
import com.lei123.YSPocketTools.AndroidTools.startService.reStartBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startwidgetsService
import com.lei123.YSPocketTools.UNIT.returnHome
import com.lei123.YSPocketTools.UNIT.saveUID
import com.lei123.YSPocketTools.check.cc
import com.lei123.YSPocketTools.entity.getNotice
import com.lei123.YSPocketTools.entity.getUid
import com.lei123.YSPocketTools.http.HTTPs.getHTTPs.getRequest
import com.lei123.YSPocketTools.http.HTTPs.getInfo.getUserIcon
import com.lei123.YSPocketTools.http.HTTPs.getInfo.getUserName
import com.lei123.YSPocketTools.http.HTTPs.getNoticeHTTP
import com.lei123.YSPocketTools.service.BaseService
import com.lei123.YSPocketTools.ui.CookieInputDialog
import com.lei123.YSPocketTools.ui.LoginDialog
import com.lei123.YSPocketTools.ui.MainPage
import com.lei123.YSPocketTools.ui.plugin.LoadingView
import com.lei123.YSPocketTools.ui.signDialog
import com.lei123.YSPocketTools.ui.widgets.GlanceWidgetUI4_2_complex
import com.lei123.YSPocketTools.utils.*
import com.lei123.YSPocketTools.webview.checkToken
import com.lei123.test.ui.settingDialog
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.PushAgent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    val context = this
    private var mainHandler: Handler? = null


    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 0) {
                //计时器使用
            } else if (msg.what == 1) {
                //计时器使用
            } else if (msg.what == 2) {
                //读取便签保存数据计时器使用
            } else if (msg.what == 3) {

            } else if (msg.what == 4) {

            }
        }
    }

    var uid: String? = ""

    /**
     * 加载本地的用户信息
     */
    private fun loadUser() {
        viewModel.mainUID = loadMainUID()
        viewModel.uid0 = loadString("uid0")
        viewModel.uid1 = loadString("uid1")
        viewModel.uid2 = loadString("uid2")
        viewModel.uid3 = loadString("uid3")
        viewModel.uid4 = loadString("uid4")
        viewModel.uid5 = loadString("uid5")
        viewModel.uid6 = loadString("uid6")
        viewModel.uid7 = loadString("uid7")
        viewModel.uid8 = loadString("uid8")
        viewModel.uid9 = loadString("uid9")
        viewModel.mainCookie = loadMainCookie()
        viewModel.cookie0 = loadString("cookie0")
        viewModel.cookie1 = loadString("cookie1")
        viewModel.cookie2 = loadString("cookie2")
        viewModel.cookie3 = loadString("cookie3")
        viewModel.cookie4 = loadString("cookie4")
        viewModel.cookie5 = loadString("cookie5")
        viewModel.cookie6 = loadString("cookie6")
        viewModel.cookie7 = loadString("cookie7")
        viewModel.cookie8 = loadString("cookie8")
        viewModel.cookie9 = loadString("cookie9")

        viewModel.ifLogin = loadBoolean("ifLogin")
    }

    fun int() {
        viewModel.mainUID = loadMainUID()
        viewModel.mainCookie = loadMainCookie()
        viewModel.ifLogin = loadBoolean("ifLogin")
        if (viewModel.ifLogin) {
            if (viewModel.mainUID != "123456789" && viewModel.mainCookie != "123456789") {

                Log.i("startBasicService", "====================================================")
                startBasicService(application)
                startwidgetsService(application)

                val datas = viewModel.mainCookie.split(";")
                for (data in datas) {
                    if (data.contains("account_id")) {
                        val miID = data.split("=")[1]
                        saveString("miID", miID)
                        viewModel.miID = miID
                    }
                }
                // 获取详细信息
                getUserInfo(viewModel.mainUID, viewModel.mainCookie)
                viewModel.uidAddIcon = R.drawable.lay_uid_change_light
            }
        } else {
            viewModel.usernickname = getString(R.string.pleaseLogin)
            viewModel.uidAddIcon = R.drawable.lay_uid_add_light
            viewModel.mainUID = getString(R.string.konguid)
        }
    }

    fun loadNoteInfo() {
        viewModel.current_Resin = loadString(viewModel.mainUID + "current_Resin")
        viewModel.max_resin = loadString(viewModel.mainUID + "max_resin")
        viewModel.resin1 = loadString(viewModel.mainUID + "resin1")
        viewModel.resin_recovery_time = loadString(viewModel.mainUID + "resin_recovery_time")
        viewModel.resinAim = loadString(viewModel.mainUID + "resinAim")
        viewModel.resinRemain = loadString(viewModel.mainUID + "resinRemain")

        viewModel.dailyTask = loadString(viewModel.mainUID + "dailyTask")
        viewModel.total_task_num = loadString(viewModel.mainUID + "total_task_num")
        viewModel.dailyTaskStr = loadString(viewModel.mainUID + "dailyTaskStr")
        viewModel.dailyTaskSubmit = loadString(viewModel.mainUID + "dailyTaskSubmit")

        viewModel.weekly = loadString(viewModel.mainUID + "weekly")
        viewModel.weeklyall = loadString(viewModel.mainUID + "weeklyall")
        viewModel.weeklyStr = loadString(viewModel.mainUID + "weeklyStr")

        viewModel.current_home_coin = loadString(viewModel.mainUID + "current_home_coin")
        viewModel.max_home_coin = loadString(viewModel.mainUID + "max_home_coin")
        viewModel.homeCoin1 = loadString(viewModel.mainUID + "homeCoin1")
        viewModel.home_coin_recovery_time =
            loadString(viewModel.mainUID + "home_coin_recovery_time")
        viewModel.homeCoinAim = loadString(viewModel.mainUID + "homeCoinAim")
        viewModel.homeCoinRemain = loadString("homeCoinRemain")

        viewModel.current_expedition_num = loadString(viewModel.mainUID + "current_expedition_num")
        viewModel.max_expedition_num = loadString(viewModel.mainUID + "max_expedition_num")
        viewModel.explorePhotoUrl1 = loadString(viewModel.mainUID + "explorePhotoUrl1")
        viewModel.explorePhotoUrl2 = loadString(viewModel.mainUID + "explorePhotoUrl2")
        viewModel.explorePhotoUrl3 = loadString(viewModel.mainUID + "explorePhotoUrl3")
        viewModel.explorePhotoUrl4 = loadString(viewModel.mainUID + "explorePhotoUrl4")
        viewModel.explorePhotoUrl5 = loadString(viewModel.mainUID + "explorePhotoUrl5")
        viewModel.exploreState1 = loadString(viewModel.mainUID + "exploreState1")
        viewModel.exploreState2 = loadString(viewModel.mainUID + "exploreState2")
        viewModel.exploreState3 = loadString(viewModel.mainUID + "exploreState3")
        viewModel.exploreState4 = loadString(viewModel.mainUID + "exploreState4")
        viewModel.exploreState5 = loadString(viewModel.mainUID + "exploreState5")
        viewModel.explore1 = loadString(viewModel.mainUID + "explore1")
        viewModel.explore2 = loadString(viewModel.mainUID + "explore2")
        viewModel.explore3 = loadString(viewModel.mainUID + "explore3")
        viewModel.explore4 = loadString(viewModel.mainUID + "explore4")
        viewModel.explore5 = loadString(viewModel.mainUID + "explore5")

        viewModel.explorePhotoUrlZheng1 = loadString(viewModel.mainUID + "explorePhotoUrlZheng1")
        viewModel.explorePhotoUrlZheng2 = loadString(viewModel.mainUID + "explorePhotoUrlZheng2")
        viewModel.explorePhotoUrlZheng3 = loadString(viewModel.mainUID + "explorePhotoUrlZheng3")
        viewModel.explorePhotoUrlZheng4 = loadString(viewModel.mainUID + "explorePhotoUrlZheng4")
        viewModel.explorePhotoUrlZheng5 = loadString(viewModel.mainUID + "explorePhotoUrlZheng5")
        viewModel.exploreName1 = loadString(viewModel.mainUID + "exploreName1")
        viewModel.exploreName2 = loadString(viewModel.mainUID + "exploreName2")
        viewModel.exploreName3 = loadString(viewModel.mainUID + "exploreName3")
        viewModel.exploreName4 = loadString(viewModel.mainUID + "exploreName4")
        viewModel.exploreName5 = loadString(viewModel.mainUID + "exploreName5")
        viewModel.exploreNamelocal1 = loadString(viewModel.mainUID + "exploreNamelocal1")
        viewModel.exploreNamelocal2 = loadString(viewModel.mainUID + "exploreNamelocal2")
        viewModel.exploreNamelocal3 = loadString(viewModel.mainUID + "exploreNamelocal3")
        viewModel.exploreNamelocal4 = loadString(viewModel.mainUID + "exploreNamelocal4")
        viewModel.exploreNamelocal5 = loadString(viewModel.mainUID + "exploreNamelocal5")
        viewModel.ExpAim = loadString(viewModel.mainUID + "ExpAim")
        viewModel.ExpRemain = loadString(viewModel.mainUID + "ExpRemain")

        viewModel.transRemainSecond = loadString(viewModel.mainUID + "transRemainSecond")
        viewModel.transday = loadString(viewModel.mainUID + "transday")
        viewModel.transRemain = loadString(viewModel.mainUID + "transRemain")

        // loadBool
        viewModel.transobtained = loadBoolean(viewModel.mainUID + "transobtained")
        viewModel.transviable = loadBoolean(viewModel.mainUID + "transviable")

        viewModel.exploreBool1 = loadBoolean(viewModel.mainUID + "exploreBool1")
        viewModel.exploreBool2 = loadBoolean(viewModel.mainUID + "exploreBool2")
        viewModel.exploreBool3 = loadBoolean(viewModel.mainUID + "exploreBool3")
        viewModel.exploreBool4 = loadBoolean(viewModel.mainUID + "exploreBool4")
        viewModel.exploreBool5 = loadBoolean(viewModel.mainUID + "exploreBool5")
        viewModel.weekly1 = loadBoolean(viewModel.mainUID + "weekly1")
        viewModel.weekly2 = loadBoolean(viewModel.mainUID + "weekly2")
        viewModel.weekly3 = loadBoolean(viewModel.mainUID + "weekly3")
        viewModel.dailyTask1 = loadBoolean(viewModel.mainUID + "dailyTask1")
        viewModel.dailyTask2 = loadBoolean(viewModel.mainUID + "dailyTask2")
        viewModel.dailyTask3 = loadBoolean(viewModel.mainUID + "dailyTask3")
        viewModel.dailyTask4 = loadBoolean(viewModel.mainUID + "dailyTask4")

        viewModel.pageLoadingState = loadBoolean("pageLoadingState")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainHandler = Handler(mainLooper)
        um()
        getNoticeInfo()
        if (loadBoolean("firstStart", true)) {
            setContent {
                YSComposeTheme {
                    Dialog(onDismissRequest = {}) {
                        Box {
                            Image(
                                painterResource(R.drawable.lay_home_login_light),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                            )
                        }
                        Column(
                            Modifier
                                .padding(15.dp)
                        ) {
                            Text(
                                text = getString(R.string.beginRead),
                                color = YSComposeTheme.colors.textPrimary,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = Constant.beginMessage,
                                color = YSComposeTheme.colors.TextBold,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Row(
                                Modifier.padding(vertical = 10.dp)
                            ) {
                                Button(
                                    onClick = {
                                        System.exit(0)
                                    },
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = YSComposeTheme.colors.TextBold,
                                        contentColor = Color.Red
                                    )
                                ) {
                                    Text(text = getString(R.string.disagree))
                                }
                                Button(
                                    onClick = {
                                        try {
                                            ignoreBatteryOptimization()
                                        }catch (e:Exception){
                                            getString(R.string.getError).toast()
                                        }
                                        //toSettingPage()
                                        saveBoolean("firstStart", false)
                                        this@MainActivity.recreate()
                                    },
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = YSComposeTheme.colors.TextBold,
                                        contentColor = Color.Green
                                    )
                                ) {
                                    Text(text = getString(R.string.agree))
                                }
                            }
                        }
                    }
                }
            }
        } else {

           //val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            /*val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", application.packageName, null)
            intent.data = uri
            startActivity(intent)*/

            //myCheckPermission()
            //ignoreBatteryOptimization()

            //saveBoolean("ifLogin", true)
            //saveString("mainUID", "123456789")
            //saveString("mainCookie", "123456789")
            //saveString("LoginCookie","UM_distinctid=1825525239f16e-0495a8417012cc-4a626c39-46e60-182552523a03db; _ga=GA1.2.1095786936.1659285874; _gid=GA1.2.1273350352.1659285874; CNZZDATA1275023096=1274334290-1659284255-%7C1659284255; _gat=1; _MHYUUID=91a61993-33d8-4eee-83e7-a57ac0844f7e; ltoken=hvD6a2ZOI0TMaCa0N7x870gf9AwWy8dTs8ytGsrE; ltuid=218675477; cookie_token=j9B8br4BYM6Y0UdoLkafri55BqIExXiykvPKK7fd; account_id=218675477")
            //saveString("LoginCookie","UM_distinctid=1825cf6bdc839-0129e0511a5088-4a626c39-59a60-1825cf6bdc960; _ga=GA1.2.438480966.1659417051; _gid=GA1.2.1051341999.1659417051; CNZZDATA1275023096=992545278-1659414707-%7C1659414707; _gat=1; _MHYUUID=8b379ef1-b134-4570-8486-f31ff0b42ef7; ltoken=DXqhRReTgALuhFoUqahrAeIX4vpqqaxC4wIzlfdR; ltuid=108071902; cookie_token=KpQmKmmjqtqTPZYo29bu3mxJEh4VtI8vK2Axzzci; account_id=108071902")
            //saveString("LoginCookie","cookie_token=qKdVAkFXhw5wzIPTUv34xsjpbFkSFgirJdTcvEw5;account_id=306434229;")
            //saveString("LoginCookie","cookie_token=xI9NnuYjLKPvRzlJZtXu7E5YUC6y1vWjlvrbqjT9; account_id=265997334")
            //Log.i("mainCookie", loadMainCookie())
            int()
            //CrashReport.testJavaCrash();
            //Beta.checkAppUpgrade()
/*        if (loadMainCookie() != "123456789") {
            saveString("LoginCookie", loadMainCookie())
        }*/

/*        if (!loadBoolean("ifLogin") || loadString("LoginCookie") != "123456789") {
            timerGetUid.schedule(taskGetUid, 0, (1 * 3 * 1000).toLong())
        }

        if (loadMainUID() == "123456789") {
            timerGetMainUID.schedule(taskGetMainUID, 0, (1 * 3 * 1000).toLong())
        }*/

            LoginCookie = loadString("LoginCookie")
            if (LoginCookie != "123456789") {
                getUid(LoginCookie)
                Log.i("taskGetUid", LoginCookie)
            }

            timerLoadNoteInfor.schedule(taskLoadNoteInfor, 0, (1 * 1 * 1000).toLong())

            //viewModel.LoginState = true
            setContent {
                YSComposeTheme {
                    MainPage(context, viewModel)
                    AnimatedVisibility (viewModel.LoginState) {
                        LoginDialog(
                            viewModel,
                            onDismissRequest = { viewModel.LoginState = false },
                            deleteMainUid = {
                                deleteMainUid()
                                viewModel.LoginState = false
                                viewModel.ifLogin = false
                                //saveBoolean("ifLogin", false)
                                reStartBasicService(application)
                                this@MainActivity.recreate()
                            },
                            changeMainUid = { rowIndex ->
                                viewModel.pageLoadingState = true
                                viewModel.LoginState = false
                                //saveString("LoginCookie",loadString("cookie".plus(rowIndex.toString())))
                                saveMainUID(loadString("uid".plus(rowIndex.toString())))
                                saveMainCookie(loadString("cookie".plus(rowIndex.toString())))
                                //reStartBasicService(context)
                                this@MainActivity.recreate()

                                var uidlist = viewModel.getuidlist()
                                uidlist
                            },
                            deleteUid = { rowIndex ->
                                viewModel.pageLoadingState = true
                                saveString(
                                    "uid".plus(rowIndex.toString()),
                                    "123456789"
                                )
                                Log.i("uid", "uid".plus(rowIndex.toString()))
                                saveString(
                                    "cookie".plus(rowIndex.toString()),
                                    "123456789"
                                )
                                var uidlist = viewModel.getuidlist()
                                uidlist
                            },
                            loginWeb = {
                                turnToLogWeb()
                            },
                            inputCookieDialog = {
                                // viewModel.LoginState = false
                                viewModel.cookieBoxState = true
                                //OpenApp.Guanzhubzhan(this, viewModel.bzhan)
                            }
                        )
                    }
                    AnimatedVisibility (viewModel.cookieBoxState) {
                        CookieInputDialog(
                            onDismissRequest = { viewModel.cookieBoxState = false },
                            onConfirm = { cookieRes ->
                                if (checkToken(cookieRes)) {
                                    R.string.get_cookie_success.toast()
                                    saveString("LoginCookie", cookieRes)
                                    val intent = Intent(application, BaseService::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    application.stopService(intent)
                                    application.startService(intent)
                                    val intent2 = Intent(application, MainActivity::class.java)
                                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    application.startActivity(intent2)
                                } else {
                                    R.string.get_cookie_failed.toast()
                                }
                            }
                        )
                    }
                    if (viewModel.settingBoxState) {
                        settingDialog(
                            viewModel,
                            onDismissRequest = {
                                viewModel.settingBoxState = false
                            },
                            onSignRequest = {
                                viewModel.settingBoxState = false
                                //getString(R.string.signWaiting).toast()
                                viewModel.pageLoadingState = true
                                saveBoolean("pageLoadingState", true)
                                viewModel.signMessageState = true
                                home_Sign(viewModel)
                            }
                        )
                    }

                    if (viewModel.signMessageState) {
                        signDialog(viewModel) {
                            viewModel.signMessageState = false
                        }
                    }

                    if (viewModel.pageLoadingState) {
                        LoadingView {
                            viewModel.pageLoadingState = false
                        }
                    }
                }
            }
            GlobalScope.launch() {
                GlanceWidgetUI4_2_complex().updateAll(application)
            }
            //returnHome()
            /*Log.i(loadString("uid0")+"resin1", loadString(loadString("uid0")+"resin1"))
            Log.i(loadString("uid1")+"resin1", loadString(loadString("uid1")+"resin1"))
            Log.i(loadString("uid2")+"resin1", loadString(loadString("uid2")+"resin1"))
            Log.i(loadString("uid3")+"resin1", loadString(loadString("uid3")+"resin1"))
            Log.i(loadString("uid4")+"resin1", loadString(loadString("uid4")+"resin1"))*/
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
                viewModel.notice = getNotice.notice ?: "Error"
                viewModel.notice1 = getNotice.notice1 ?: "Error"
                viewModel.notice2 = getNotice.notice2 ?: "Error"
                viewModel.noticeversion = getNotice.noticeversion ?: "1"
                viewModel.lastAppVertion = getNotice.appversion ?: "1.0"
                viewModel.update = getNotice.update ?: "RTgcJKrWQf2BYPVMy4biqw0KSgD8mxLq"
                viewModel.douyin = getNotice.douyin ?: "7064504305553902863"
                viewModel.bzhan = getNotice.bzhan ?: "1wT4y1X7mh"
            }

            //向主线程发送数据
            val msg = Message.obtain()
            msg.what = 2
            handler.sendMessage(msg)
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        taskLoadNoteInfor.cancel()
        startBasicService(application)
        startwidgetsService(application)
    }

    private val timerLoadNoteInfor = Timer(true)
    private val taskLoadNoteInfor: TimerTask = object : TimerTask() {
        override fun run() {
            loadUser()
            //Log.i("taskLoadNoteInfor", "11111111111111111111111111111111111")
            //Log.i("loadMainUID()", loadMainUID())
            //Log.i("current_Resin", loadString(viewModel.mainUID + "current_Resin"))
            if (loadMainUID() != "123456789" && loadString(viewModel.mainUID + "current_Resin") != "123456789") {
                loadNoteInfo()
            }
            val msg = Message()
            msg.what = 2
            handler.sendMessage(msg)
        }
    }
    //endregion
    /**
     * 开启友盟sdk
     */
    private fun um() {
        PushAgent.getInstance(application).onAppStart()
        val deviceToken = PushAgent.getInstance(application).registrationId
        Log.i("deviceToken", deviceToken)

        //当用户使用自有账号登录时，可以这样统计：
        UMConfigure.init(
            this,
            Constant.UMappkey,
            Constant.Channelrelease,
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        );
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }


    var cookie: String = ""
    var LoginCookie: String = ""

    fun getUserInfo(uid: String, cookie: String) {
        Thread {
            val userNameList = getUserName(uid, cookie)
            val miImageResult = getUserIcon(uid, cookie)

            viewModel.usernickname = userNameList?.get(0) ?: viewModel.usernickname
            viewModel.level = userNameList?.get(1) ?: viewModel.level
            viewModel.region_name = userNameList?.get(2) ?: viewModel.region_name

            viewModel.minickname = miImageResult?.get(0) ?: viewModel.minickname
            viewModel.miavatar_url = miImageResult?.get(1) ?: viewModel.miavatar_url
            viewModel.miip_region = miImageResult?.get(2) ?: viewModel.miip_region
            saveString(
                uid + "usernickname",
                userNameList?.get(0) ?: loadString(uid + "usernickname")
            )
            saveString(uid + "level", userNameList?.get(1) ?: loadString(uid + "level"))
            saveString(uid + "region_name", userNameList?.get(2) ?: loadString(uid + "region_name"))

            saveString(uid + "minickname", miImageResult?.get(0) ?: loadString(uid + "minickname"))
            saveString(
                uid + "miavatar_url",
                miImageResult?.get(1) ?: loadString(uid + "miavatar_url")
            )
            saveString(
                uid + "miip_region",
                miImageResult?.get(2) ?: loadString(uid + "miip_region")
            )

            val msg = Message.obtain()
            msg.what = 2
            handler.sendMessage(msg)
        }.start()
    }

    /**
     * 获取Uid
     */
    private fun getUid(LoginCookie: String) {
        val url: String = Constant.MiHoYoGetUID_URL
        //获取cookies
        if (checkToken(LoginCookie)) {
            Thread {
                val uids: MutableList<String> = ArrayList()
                val result = getRequest(url, LoginCookie);
                Log.i("Main", result)
                if (result != "failed") {
                    try {
                        val getUid: getUid = Gson().fromJson(result, getUid::class.java)
                        println(getUid.message)
                        val listInfos = getUid.data!!.list
                        println(listInfos)
                        for (listInfo in listInfos!!) {
                            if ("hk4e_cn" == listInfo.game_biz) {
                                uids.add(listInfo.game_uid!!)
                                //Log.i("Main", listInfo.game_uid!!)
                            }
                        }
                    } catch (e: Exception) {
                        "cookie错误".toast()
                        Log.i("Main", "1111111111111111111111111111111111111111111111111111")
                        e.printStackTrace()
                    }
                    if (uids.size > 0) {
                        for (i in uids.indices) {
                            saveUID(uids[i], LoginCookie)
                        }
                        val datas = LoginCookie.split(";")
                        for (data in datas) {
                            if (data.contains("account_id")) {
                                val miID = data.split("=")[1]
                                saveString("miID", miID)
                                viewModel.miID = miID
                            }
                        }
                        saveBoolean("ifLogin", true)
                        saveMainUID(uids[0])
                        saveMainCookie(LoginCookie)
                        Log.i("uids[0]", uids[0])
                        saveString("LoginCookie", "123456789")
                        reStartBasicService(application)
                        int()
                    } else {
                        "cookie错误".toast()
                    }
                } else {
                    "cookie错误".toast()
                }

                //回到主线程
/*                mainHandler?.post(Runnable {
                    int()
                })*/
            }.start()
        }
    }
}

