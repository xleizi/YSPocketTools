package com.lei123.YSPocketTools.AndroidTools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.List;

public class OpenApp
{
    /**
     * 关注抖音
     */
    public static boolean Guanzhudouyin(Context context) {
        //判断是否安装了抖音并自动转到抖音主页
        boolean b = checkAppInstalled(context, "com.ss.android.ugc.aweme");
        if (b) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("snssdk1128://user/profile/102188393875"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } else {
            Toast.makeText(context, "未检测到抖音或安装的版本不支持,请从浏览器打开", Toast.LENGTH_SHORT).show();
            String url = "https://v.douyin.com/NjVgyQj/";
            //隐式调用Intent,指定Intent的action是Intent.ACTION_VIEW;
            Intent intent =new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(Intent.createChooser(intent, "打开抖音"));
            return false;
        }
    }

    /**
     * 抖音视频
     */
    public static boolean douyinshipin(Context context, String key) {
        //判断是否安装了抖音并自动转到抖音主页
        boolean b = OpenApp.checkAppInstalled(context, "com.ss.android.ugc.aweme");
        if (b) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("snssdk1128://aweme/detail/" + key));
            context.startActivity(intent);
            return true;
        } else {
            Toast.makeText(context, "未安装抖音或安装的版本不支持,请从浏览器打开", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * QQ一键加群
     */
    public static boolean joinQQGroup(Context context, String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26jump_from%3Dwebapi%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            Toast.makeText(context, "未安装手Q或安装的版本不支持,请手动打开", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 微信公众号
     */
    public static void OpenWeiXin(Context context) {
        ClipBoard.sendToClipBoard(context);
        Toast.makeText(context, "公众号已复制到剪贴板。", Toast.LENGTH_SHORT).show();

        boolean b = OpenApp.checkAppInstalled(context, "com.tencent.mm");
        if (b) {
            ComponentName apk2Component1 = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            Intent mIntent = new Intent();
            mIntent.setAction(Intent.ACTION_VIEW);
            //mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            mIntent.setComponent(apk2Component1);
            context.startActivity(mIntent);
        } else {
            Toast.makeText(context, "未安装此应用或安装的版本不支持,请手动打开", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关注B站
     */
    public static boolean Guanzhubzhan(Context context, String key){
        boolean b = OpenApp.checkAppInstalled(context, "com.ss.android.ugc.aweme");
        String url = "https://www.bilibili.com/video/bv" + key;
        Intent intent =new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "打开哔哩哔哩视频"));
        return true;
        //url = "https://b23.tv/gMuc373";
/*        if (b) {
            ComponentName cmp = new ComponentName("tv.danmaku.bili", "tv.danmaku.bili.ui.intent.IntentHandlerActivity");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setComponent(cmp);
            return true;
        } else {
            //Toast.makeText(context, "未安装B站或安装的版本不支持,请从浏览器打开", Toast.LENGTH_SHORT).show();
            //隐式调用Intent,指定Intent的action是Intent.ACTION_VIEW;
            Intent intent =new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(Intent.createChooser(intent, "打开哔哩哔哩视频"));
            return false;
        }*/
    }

    /**
     * 判断包名
     */
    public static boolean checkAppInstalled(Context context, String pkgName) {
        if (pkgName == null || pkgName.isEmpty()) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> info = packageManager.getInstalledPackages(0);
        if (info == null || info.isEmpty()){
            return false;
        }
        for (int i = 0; i < info.size(); i++) {
            if (pkgName.equals(info.get(i).packageName)) {
                return true;
            }
        }
        return true;
    }
}
