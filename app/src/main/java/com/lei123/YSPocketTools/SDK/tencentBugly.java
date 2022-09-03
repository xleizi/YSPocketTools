package com.lei123.YSPocketTools.SDK;

import android.app.Application;
import android.os.Environment;

import com.lei123.YSPocketTools.MainActivity;
import com.lei123.YSPocketTools.R;
import com.lei123.YSPocketTools.utils.Constant;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;

public class tencentBugly {
    public static void initBugly(Application application) {
        //BuildConfig.BuglyAppId替换成你的App ID
        // Bugly.init(getApplicationContext(),BuildConfig.BUGLY_ID,BuildConfig.DEBUG);
        Beta.autoInit = true;

        //自定义样式
       // Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
        //Beta.tipsDialogLayoutId = R.layout.tips_dialog_update;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.upgradeCheckPeriod = 5 * 1000;

        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.drawable.icon_resin;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.drawable.icon_resin;


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.drawable.icon_resin;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);

        /***** Bugly高级设置 *****/
        BuglyStrategy strategy = new BuglyStrategy();

        /**
         * 设置app渠道号
         */
        strategy.setAppChannel("Release");

        /***** 统一初始化Bugly产品，包含Beta *****/
        Bugly.init(application, Constant.BUGLY_ID, false, strategy);
    }
}
