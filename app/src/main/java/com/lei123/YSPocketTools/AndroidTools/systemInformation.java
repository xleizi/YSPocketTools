package com.lei123.YSPocketTools.AndroidTools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class systemInformation {
    /**
     * 设备名称
     *
     * @return 设备名称
     */
    public static String getDeviceName() {
        return Build.DEVICE;
    }

    /**
     * 设备型号
     *
     * @return 设备型号
     */
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取品牌
     *
     * @return 品牌
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取设备制造商
     *
     * @return 制造商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * SDK 版本
     * @return SDK
     */
    public static int getSDKVersion() {
        int SDKVersion = Build.VERSION.SDK_INT;
        return  SDKVersion;
    }

    /**
     * 获取设备系统版本名称
     * @return 系统版本名称
     */
    public static String getDISPLAY() {
        String DISPLAY = Build.DISPLAY;
        return  DISPLAY;
    }

    /**
     * 获取设备系统序列号
     * @return 系统序列号
     */
    public static String getSerialNum() {
        String serialNum = Build.SERIAL;
        return  serialNum;
    }

    /**
     * 获取android的唯一标识
     *
     * @param context 上下文
     * @return 返回androidId
     */
    public static String getAndroidId(Context context) {
        final String uuid = UUID.randomUUID().toString();
        String androidId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (TextUtils.isEmpty(androidId)) {
            return uuid;
        } else {
            return androidId;
        }
    }

    /**
     * 获取屏幕尺寸
     *
     * @param context 当前activity
     * @return 返回尺寸
     */
    public static double getScreenInch(Activity context) {
        double mInch = 0;
        try {
            int realWidth = 0, realHeight = 0;
            Display display = context.getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            if (Build.VERSION.SDK_INT >= 17) {
                Point size = new Point();
                display.getRealSize(size);
                realWidth = size.x;
                realHeight = size.y;
            } else if (Build.VERSION.SDK_INT < 17 && Build.VERSION.SDK_INT >= 14) {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } else {
                realWidth = metrics.widthPixels;
                realHeight = metrics.heightPixels;
            }
            mInch = formatDouble(Math.sqrt((realWidth / metrics.xdpi) * (realWidth / metrics.xdpi) + (realHeight / metrics.ydpi) * (realHeight / metrics.ydpi)), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mInch;
    }
    /**
     * Double类型保留指定位数的小数，返回double类型（四舍五入）
     * newScale 为指定的位数
     */
    private static double formatDouble(double d, int newScale) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @param activity 当前activity
     * @return 1：竖屏 2 横屏
     */
    public static int screenOrientation(Activity activity) {
        //获取设置的配置信息
        return activity.getResources().getConfiguration().orientation;
    }


    /**
     * 获取手机IMSI
     */
    public static String getIMSI(Activity context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMSI号
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    context.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1008);
                }
            }
            String imsi = telephonyManager.getSubscriberId();
            if (null == imsi) {
                imsi = null;
            }
            return imsi;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前应用的版本号
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getNowTime() {
        Date nowTime = new Date();
        long ts = System.currentTimeMillis();
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date(ts);
        res = simpleDateFormat.format(date);
        return res;
    }



    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }
}
