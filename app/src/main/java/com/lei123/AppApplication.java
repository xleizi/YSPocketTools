package com.lei123;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.StrictMode;

import com.lei123.YSPocketTools.SDK.PushHelper;
import com.lei123.YSPocketTools.SDK.tencentBugly;
import com.lei123.YSPocketTools.check.Base64Util;
import com.lei123.YSPocketTools.check.ContextUtils;
import com.lei123.YSPocketTools.check.MD5Utils;
import com.lei123.YSPocketTools.utils.Constant;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.utils.UMUtils;

import static com.lei123.YSPocketTools.utils.GlobleKt.application;

/*使用SDK名称：友盟+SDK
        服务类型：请根据SDK提供功能填写
        收集个人信息类型：设备信息（IMEI/Mac/android ID/IDFA/OPENUDID/GUID/SIM卡IMSI/地理位置信息）
        隐私权政策链接：https://www.umeng.com/page/policy*/

public final class AppApplication extends Application {

    public final String CHANNEL_ID = "常规提醒通知";
    public final String CHANNEL_ID2 = "探索提醒通知";
    public final String CHANNEL_ID3 = "常驻通知";
    public final String CHANNEL_ID4 = "每日提醒推送";

    @Override
    public void onCreate() {
        super.onCreate();
        application = AppApplication.this;
        initSdk(application);
        //UMConfigure.preInit(application,Constant.UMappkey, Constant.Channelrelease);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        creatNotification();
    }



    private void creatNotification(){
        createNotificationChannel();
        //createNotificationChannel2();
        createNotificationChannel3();
        createNotificationChannel4();
    }

    //常规提醒通知
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID, "常规提醒通知",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    //探索提醒
    public void createNotificationChannel2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID2, "探索提醒通知",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    //常驻通知
    public void createNotificationChannel3() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID3, "常驻通知",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    //每日通知
    public void createNotificationChannel4() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID4, "每日提醒推送",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    public void initSdk(Application application) {
        tencentBugly.initBugly(application);
        initUmengSDK();
    }
    /**
     * 初始化友盟SDK
     */
    private void initUmengSDK() {
        //日志开关
        UMConfigure.setLogEnabled(true);
        boolean isMainProcess = UMUtils.isMainProgress(application);
        //预初始化
        PushHelper.preInit(application);
        //是否同意隐私政策
        boolean agreed = true; //MyPreferences.getInstance(this).hasAgreePrivacyAgreement();
        if (!agreed) {
            return;
        }
        if (isMainProcess) {
            //启动优化：建议在子线程中执行初始化
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PushHelper.init(getApplicationContext());
                }
            }).start();
        }
    }
    /**
     * 初始化一些第三方框架
     */
    /*public static void initSdk(Application application) {
        // 设置标题栏初始化器
        //TitleBar.setDefaultStyle(new TitleBarStyle());

        //刷新球和刷新机制
        // 设置全局的 Header 构建器
*//*        SmartRefreshLayout.setDefaultRefreshHeaderCreator((cx, layout) ->
                new MaterialHeader(application).setColorSchemeColors(ContextCompat.getColor(application, R.color.common_accent_color)));
        // 设置全局的 Footer 构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((cx, layout) -> new SmartBallPulseFooter(application));
        // 设置全局初始化器
        SmartRefreshLayout.setDefaultRefreshInitializer((cx, layout) -> {
            // 刷新头部是否跟随内容偏移
            layout.setEnableHeaderTranslationContent(true)
                    // 刷新尾部是否跟随内容偏移
                    .setEnableFooterTranslationContent(true)
                    // 加载更多是否跟随内容偏移
                    .setEnableFooterFollowWhenNoMoreData(true)
                    // 内容不满一页时是否可以上拉加载更多
                    .setEnableLoadMoreWhenContentNotFull(false)
                    // 仿苹果越界效果开关
                    .setEnableOverScrollDrag(false);
        });*//*

        // 初始化吐司
        ToastUtils.init(application, new ToastStyle());
        // 设置调试模式
        ToastUtils.setDebugMode(AppConfig.isDebug());
        // 设置 Toast 拦截器
        ToastUtils.setInterceptor(new ToastLogInterceptor());

        // 本地异常捕捉
        CrashHandler.register(application);

        // 友盟统计、登录、分享 SDK
        UmengClient.init(application, AppConfig.isLogEnable());

        // Bugly 异常捕捉
        //CrashReport.initCrashReport(application, AppConfig.getBuglyId(), AppConfig.isDebug());
        //Bugly.init(application, AppConfig.getBuglyId(), AppConfig.isDebug());
        Bugly.initBugly(application);

        //System.out.println("test!!!!!!!!!!");
        // Activity 栈管理初始化
        ActivityManager.getInstance().init(application);

        // MMKV 初始化
        MMKV.initialize(application);

        // 网络请求框架初始化
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        EasyConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(AppConfig.isLogEnable())
                // 设置服务器配置
                .setServer(new RequestServer())
                // 设置请求处理策略
                .setHandler(new RequestHandler(application))
                // 设置请求重试次数
                .setRetryCount(1)
                .setInterceptor((api, params, headers) -> {
                    // 添加全局请求头
                    headers.put("token", "66666666666");
                    headers.put("deviceOaid", UmengClient.getDeviceOaid());
                    headers.put("versionName", AppConfig.getVersionName());
                    headers.put("versionCode", String.valueOf(AppConfig.getVersionCode()));
                    // 添加全局请求参数
                    // params.put("6666666", "6666666");
                })
                .into();

        // 设置 Json 解析容错监听
        GsonFactory.setJsonCallback((typeToken, fieldName, jsonToken) -> {
            // 上报到 Bugly 错误列表
            CrashReport.postCatchedException(new IllegalArgumentException(
                    "类型解析异常：" + typeToken + "#" + fieldName + "，后台返回的类型为：" + jsonToken));
        });

        // 初始化日志打印
        if (AppConfig.isLogEnable()) {
            Timber.plant(new DebugLoggerTree());
        }

        // 注册网络状态变化监听
        ConnectivityManager connectivityManager = ContextCompat.getSystemService(application, ConnectivityManager.class);
        if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onLost(@NonNull Network network) {
                    Activity topActivity = ActivityManager.getInstance().getTopActivity();
                    if (!(topActivity instanceof LifecycleOwner)) {
                        return;
                    }

                    LifecycleOwner lifecycleOwner = ((LifecycleOwner) topActivity);
                    if (lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.RESUMED) {
                        return;
                    }

                    ToastUtils.show(R.string.common_network_error);
                }
            });
        }
    }*/




    private static boolean sEarlyCheckSignResult = false;

    public static boolean EarlySignResult() {
        return sEarlyCheckSignResult;
    }

    public AppApplication() {
        sEarlyCheckSignResult = earlyCheckSign();
    }

    public static String nowSignMD5;

    boolean earlyCheckSign() {
        String trueSignMD5 = Constant.mmm;
        nowSignMD5 = "";
        try {
            // 获取新的 Context
            Context context = ContextUtils.getContext();
            //得到签名hashcode
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            String signBase64 = Base64Util.encodeToString(signs[0].toByteArray());
            nowSignMD5 = MD5Utils.MD5(signBase64);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(trueSignMD5);
        System.out.println(nowSignMD5);
        return trueSignMD5.equals(nowSignMD5);
    }
}
