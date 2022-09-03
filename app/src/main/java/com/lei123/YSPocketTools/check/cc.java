package com.lei123.YSPocketTools.check;


import static com.lei123.YSPocketTools.utils.GlobleKt.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;

import com.lei123.AppApplication;
import com.lei123.YSPocketTools.utils.Constant;

import java.lang.reflect.Field;

public class cc {
    public static void ccc(){
        if(App(application) && Normal(application) && AppApplication.EarlySignResult() && PM(application) && NewAPI(application)){

        }else{
            System.exit(0);
        }
    }

    /**
     * 做普通的签名校验
     */
    @SuppressLint("PackageManagerGetSignatures")
    public static boolean Normal(Context context) {
        String nowSignMD5 = "";
        try {
            // 得到签名MD5
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            String signBase64 = Base64Util.encodeToString(signs[0].toByteArray());
            nowSignMD5 = MD5Utils.MD5(signBase64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return nowSignMD5.equals(Constant.mmm);
    }

    /**
     * 校验 application
     */
    public static boolean App(Application nowApplication){
        String nowApplicationName = nowApplication.getClass().getSimpleName();
        return nowApplicationName.equals(Constant.trueApplicationName);
    }

    /**
     * 检测 PM 代理
     */
    @SuppressLint("PrivateApi")
    public static boolean PM(Context context){
        String nowPMName = "";
        try {
            // 被代理的对象是 PackageManager.mPM
            PackageManager packageManager = context.getPackageManager();
            Field mPMField = packageManager.getClass().getDeclaredField("mPM");
            mPMField.setAccessible(true);
            Object mPM = mPMField.get(packageManager);
            // 取得类名
            assert mPM != null;
            nowPMName = mPM.getClass().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 类名改变说明被代理了
        return nowPMName.equals(Constant.truePMName);
    }

    /**
     * 使用较新的 API 检测
     */
    @SuppressLint("PackageManagerGetSignatures")
    public static boolean NewAPI(Context context){
        String nowSignMD5 = "";
        Signature[] signs = null;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_SIGNING_CERTIFICATES);
                signs = packageInfo.signingInfo.getApkContentsSigners();
            } else {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_SIGNATURES);
                signs = packageInfo.signatures;
            }
            // 得到签名MD5
            String signBase64 = Base64Util.encodeToString(signs[0].toByteArray());
            nowSignMD5 = MD5Utils.MD5(signBase64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return nowSignMD5.equals(Constant.mmm);
    }
}
