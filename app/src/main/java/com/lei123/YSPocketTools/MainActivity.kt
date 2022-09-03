package com.lei123.YSPocketTools

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
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
import androidx.core.view.WindowCompat
import androidx.glance.appwidget.updateAll
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.lei123.YSPocketTools.AndroidTools.home_Sign
import com.lei123.YSPocketTools.AndroidTools.ignoreBatteryOptimization
import com.lei123.YSPocketTools.AndroidTools.startService.reStartBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startBasicService
import com.lei123.YSPocketTools.AndroidTools.startService.startwidgetsService
import com.lei123.YSPocketTools.AndroidTools.systemInformation
import com.lei123.YSPocketTools.AndroidTools.systemInformation.getVersion
import com.lei123.YSPocketTools.UNIT.saveUID
import com.lei123.YSPocketTools.check.cc
import com.lei123.YSPocketTools.entity.*
import com.lei123.YSPocketTools.http.HTTPs.getHTTPs
import com.lei123.YSPocketTools.http.HTTPs.getHTTPs.getRequest
import com.lei123.YSPocketTools.http.HTTPs.getImage.urlSaveToImage
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
    var LoginCookie: String = ""

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

    fun updateuse(){
        try {
            loadString("cuiResinBool")
            loadString("cuiHomeCoinBool")
            loadString("cuiExploreBool")
            loadString("ifAutoSign")
            loadString("ifbottomBar")
            loadString("ifClickSign")
            loadString("ifToast")
        } catch (e: Exception) {
            saveString("cuiResinBool", getString(R.string.open))
            saveString("cuiHomeCoinBool", getString(R.string.open))
            saveString("cuiExploreBool", getString(R.string.open))
            saveString("ifAutoSign", getString(R.string.Yes))
            saveString("ifbottomBar", getString(R.string.No))
            saveString("ifClickSign", getString(R.string.Yes))
            saveString("ifToast", getString(R.string.Yes))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mainHandler = Handler(mainLooper)
        updateuse()

        saveBoolean("baseServiceState", false)
        saveBoolean("SignState", false)
        saveBoolean("SummaryState", false)
        cc.ccc()
        um()
        getNoticeInfo()

        //自定义样式
        //Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
        //Beta.tipsDialogLayoutId = R.layout.tips_dialog_update;
        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        /*Log.i("checkupdate","")
        Log.i("checkupdate",Beta.getAppUpgradeInfo().title)
        Log.i("checkupdate",Beta.getAppUpgradeInfo().versionName)
        Log.i("checkupdate",Beta.getAppUpgradeInfo().apkUrl)
        Log.i("checkupdate",Beta.getAppUpgradeInfo().newFeature)
        Log.i("checkupdate", Beta.getAppUpgradeInfo().fileSize.toString())
        Log.i("checkupdate", Beta.getAppUpgradeInfo().versionCode.toString())

        Log.i("checkupdate",Beta.getUpgradeInfo().title)
        Log.i("checkupdate",Beta.getUpgradeInfo().versionName)
        Log.i("checkupdate",Beta.getUpgradeInfo().apkUrl)
        Log.i("checkupdate",Beta.getUpgradeInfo().newFeature)
        Log.i("checkupdate", Beta.getUpgradeInfo().fileSize.toString())
        Log.i("checkupdate", Beta.getUpgradeInfo().versionCode.toString())*/


        /*val screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels//屏幕高度
        Log.i("screenHeight", screenHeight.toString())
        val screenWidth = LocalConfiguration.current.screenHeightDp.dp
        Log.i("screenWidth", screenWidth.toString())*/

        getSummary(viewModel)
        getCharacter(viewModel)
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
                                        } catch (e: Exception) {
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
            int()
            LoginCookie = loadString("LoginCookie")
            if (LoginCookie != "123456789") {
                getUid(LoginCookie)
                Log.i("taskGetUid", LoginCookie)
            }

            timerLoadNoteInfor.schedule(taskLoadNoteInfor, 0, (1 * 1 * 1000).toLong())
            setContent {
                YSComposeTheme {
                    MainPage(context, viewModel)
                    AnimatedVisibility(viewModel.LoginState) {
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
                    AnimatedVisibility(viewModel.cookieBoxState) {
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
                                saveBoolean("SignState", true)
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
                            //viewModel.pageLoadingState = false
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

        viewModel.pageLoadingState =
            !(!loadBoolean("baseServiceState") && !loadBoolean("SignState") && !loadBoolean("SummaryState"))
// viewModel.pageLoadingState = loadBoolean("pageLoadingState") && loadBoolean("pageLoadingState")
    }

/*    fun getAccountInfo() {
viewModel.active_day_number = loadInt("active_day_number", 0)
viewModel.achievement_number = loadInt("achievement_number", 0)
viewModel.anemoculus_number = loadInt("anemoculus_number", 0)
viewModel.geoculus_number = loadInt("geoculus_number", 0)
viewModel.avatar_number = loadInt("avatar_number", 0)
viewModel.way_point_number = loadInt("way_point_number", 0)
viewModel.domain_number = loadInt("domain_number", 0)
viewModel.spiral_abyss = loadString("spiral_abyss", "1-1")
viewModel.precious_chest_number = loadInt("precious_chest_number", 0)
viewModel.luxurious_chest_number = loadInt("luxurious_chest_number", 0)
viewModel.exquisite_chest_number = loadInt("exquisite_chest_number", 0)
viewModel.common_chest_number = loadInt("common_chest_number", 0)
viewModel.electroculus_number = loadInt("electroculus_number", 0)
viewModel.magic_chest_number = loadInt("magic_chest_number", 0)
}*/

    /**
     * 读取通知信息
     */
    private fun getNoticeInfo() {
        Thread {
            val getNotice: getNotice = getNoticeHTTP()
            if (getNotice.notice1 == "Error") {
            } else {
                saveString("notice", getNotice.notice ?: "Error")
                saveString("notice1", getNotice.notice1 ?: "Error")
                saveString("notice2", getNotice.notice2 ?: "Error")
                saveString("notice3", getNotice.notice3 ?: "Error")
                val backgroundimage = getNotice.backgroundimage
                    ?: "https://i.pixiv.re/img-master/img/2021/10/21/22/32/58/93593716_p0_master1200.jpg"
                saveString("backgroundimage", backgroundimage)
                urlSaveToImage(backgroundimage)
                saveString("noticeversion", getNotice.noticeversion ?: "1")
                saveString(
                    "lastAppVertion",
                    getNotice.appversion ?: systemInformation.getVersion(application)
                )
                saveString("update", getNotice.noticeversion ?: "RTgcJKrWQf2BYPVMy4biqw0KSgD8mxLq")
                saveString("douyin", getNotice.noticeversion ?: "7064504305553902863")
                saveString("bzhan", getNotice.noticeversion ?: "1wT4y1X7mh")
                viewModel.notice = loadString("notice", "Error")
                viewModel.notice1 = loadString("notice1", "Error")
                viewModel.notice2 = loadString("notice2", "Error")
                viewModel.notice3 = loadString("notice3", "Error")
                viewModel.backgroundimage = loadString(
                    "backgroundimage",
                    "https://i.pixiv.re/img-master/img/2021/10/21/22/32/58/93593716_p0_master1200.jpg"
                )
                viewModel.noticeversion = loadString("noticeversion", "Error")
                viewModel.lastAppVertion =
                    loadString("lastAppVertion", systemInformation.getVersion(application))
                viewModel.update = loadString("update", "RTgcJKrWQf2BYPVMy4biqw0KSgD8mxLq")
                viewModel.douyin = loadString("douyin", "7064504305553902863")
                viewModel.bzhan = loadString("bzhan", "1wT4y1X7mh")
                viewModel.updateState = viewModel.lastAppVertion != getVersion(application)


            }

            //向主线程发送数据
            val msg = Message.obtain()
            msg.what = 2
            handler.sendMessage(msg)
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            taskLoadNoteInfor.cancel()
        } catch (e: Exception) {

        }
        try {
            startBasicService(application)
            startwidgetsService(application)
        } catch (e: Exception) {

        }
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
                //getAccountInfo()
            }
            val msg = Message()
            msg.what = 2
            handler.sendMessage(msg)
        }
    }

    /**
     * 开启友盟sdk
     */
    private fun um() {
        cc.ccc() //检测是否被解包
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
                Log.i("MaingetUid", result)
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

