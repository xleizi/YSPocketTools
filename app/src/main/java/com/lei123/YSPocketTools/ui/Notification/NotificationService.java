package com.lei123.YSPocketTools.ui.Notification;

import static com.lei123.YSPocketTools.utils.FileUtilsKt.imageUrlToBitmap;
import static com.lei123.YSPocketTools.utils.GlobleKt.application;
import static com.lei123.YSPocketTools.utils.SaveAndLoadKt.loadMainUID;
import static com.lei123.YSPocketTools.utils.SaveAndLoadKt.loadString;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.lei123.YSPocketTools.R;
import com.lei123.YSPocketTools.activity.WakeupAppActivity;
import com.lei123.YSPocketTools.utils.Constant;
import com.lei123.YSPocketTools.utils.IntentAction;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {
    private Context context;
    private RemoteViews NormalView;
    private Notification notification;
    private ActionReceiver receiver = new ActionReceiver();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Timer timer;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        timer = new Timer();
        /**
         * 參数：1.事件2.延时事件3.运行间隔事件
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //ServiceRunning.startGuardService(getApplicationContext(), "core.GuardService");      //每5分钟开启守护线程
                //ServiceRunning.startBaseService(getApplicationContext(), "notification.baseService");
            }
        }, 0, 1000);
        //}, 0, 5*60*1000);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //start();
        initNotification();
        //register action broadcast
        IntentFilter filter = new IntentFilter();

        filter.addAction(IntentAction.NOTIFICATION_CLICK);
        filter.addAction(IntentAction.NOTIFICATION_CLOSE);

        registerReceiver(receiver, filter);
        return Service.START_STICKY;
    }

    /**
     * 获取图片资源的id
     *
     * @param name
     * @return
     */
    public int getImageId(String name) {
        Resources res = getResources();
        return res.getIdentifier(name, "drawable", getPackageName());
    }


    private void initNotification() {
        //String BRAND = systemInformation.getBrand();       //品牌
        //int SDK = systemInformation.getSDKVersion();        //SDK

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "常驻通知");
        /*//if(SDK > 30 && (BRAND.equals("Xiaomi") || BRAND.equals("Redmi") || BRAND.equals("samsung") || BRAND.equals("realme") || BRAND.equals("vivo"))){
        //SDK > 30 && (!BRAND.equals("HUAWEI"))
        if (false) {
            builder.setSmallIcon(R.drawable.icon_resin)
                    .setTicker(context.getString(R.string.app_name))
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(true);
            NormalView = new RemoteViews(context.getPackageName(), R.layout.notification_resin_view_homecoin);
            builder.setCustomContentView(NormalView);
        } else {*/
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.setClass(context, WakeupAppActivity.class);

        PendingIntent pendingIntent;
        //if (true) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_CANCEL_CURRENT);
        } else {
            pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        }

/*            PendingIntent pendingIntent = PendingIntent
                    .getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);*/

        builder.setSmallIcon(R.drawable.icon_resin)
                .setTicker(context.getString(R.string.app_name))
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setOngoing(true);
        String resinCount = "123456789";
        String resinInfo1 = null;
        String expInfo1 = null;
        String expInfo2 = null;
        String expInfo3 = null;
        String expInfo4 = null;
        String expInfo5 = null;

        String mainUID = loadMainUID();
        String resin1 = loadString(mainUID + "resin1");
        String resinAim = loadString(mainUID + "resinAim");
        String homeCoin1 = loadString(mainUID + "homeCoin1");
        String homeCoinAim = loadString(mainUID + "homeCoinAim");
        String usernickname = loadString(mainUID + "usernickname");
        String role1 = loadString(mainUID + "explorePhotoUrl1", "Empty");
        String role2 = loadString(mainUID + "explorePhotoUrl2", "Empty");
        String role3 = loadString(mainUID + "explorePhotoUrl3", "Empty");
        String role4 = loadString(mainUID + "explorePhotoUrl4", "Empty");
        String role5 = loadString(mainUID + "explorePhotoUrl5", "Empty");
        String explore1Aim = loadString(mainUID + "explore1Aim");
        String explore2Aim = loadString(mainUID + "explore2Aim");
        String explore3Aim = loadString(mainUID + "explore3Aim");
        String explore4Aim = loadString(mainUID + "explore4Aim");
        String explore5Aim = loadString(mainUID + "explore5Aim");

        if ("homecoin1".equals(loadString("noticeStyle","homecoin1"))){
            NormalView = new RemoteViews(context.getPackageName(), R.layout.notification_resin_view_homecoin);
            NormalView.setTextViewText(R.id.tv_resin1, getString(R.string.resinTitle) + ": " + resin1);
            NormalView.setTextViewText(R.id.tv_resin2, resinAim + getString(R.string.full));
            NormalView.setTextViewText(R.id.tv_homecoin1, getString(R.string.homeCoinTitle) + ": " + homeCoin1);
            NormalView.setTextViewText(R.id.tv_homecoin2, homeCoinAim + getString(R.string.full));
            NormalView.setTextViewText(R.id.tv_usernickname1, usernickname);
        }else if ("explore1".equals(loadString("noticeStyle"))){
            NormalView = new RemoteViews(context.getPackageName(), R.layout.notification_resin_view_explor);
            NormalView.setTextViewText(R.id.tv_resin1, resin1);
            NormalView.setTextViewText(R.id.tv_resin2, getString(R.string.yujiyu)  + resinAim + getString(R.string.full));
            NormalView.setTextViewText(R.id.tv_exText1, explore1Aim);
            NormalView.setTextViewText(R.id.tv_exText2, explore2Aim);
            NormalView.setTextViewText(R.id.tv_exText3, explore3Aim);
            NormalView.setTextViewText(R.id.tv_exText4, explore4Aim);
            NormalView.setTextViewText(R.id.tv_exText5, explore5Aim);
            NormalView.setImageViewBitmap(R.id.iv_exImage1, imageUrlToBitmap(role1));
            NormalView.setImageViewBitmap(R.id.iv_exImage2, imageUrlToBitmap(role2));
            NormalView.setImageViewBitmap(R.id.iv_exImage3, imageUrlToBitmap(role3));
            NormalView.setImageViewBitmap(R.id.iv_exImage4, imageUrlToBitmap(role4));
            NormalView.setImageViewBitmap(R.id.iv_exImage5, imageUrlToBitmap(role5));

            NormalView.setTextViewText(R.id.tv_usernickname1, usernickname);
        }else {
            NormalView = new RemoteViews(context.getPackageName(), R.layout.notification_resin_view_onlyresin);
            NormalView.setTextViewText(R.id.tv_resin1, resin1);
            NormalView.setTextViewText(R.id.tv_resin2, getString(R.string.yujiyu)  + resinAim + getString(R.string.full));
        }





        builder.setCustomContentView(NormalView);
        notification = builder.build();
        startForeground(Constant.ID_FOR_CUSTOM_VIEW, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    class ActionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent data) {
            if (data == null || data.getAction() == null) {
                return;
            }
            switch (data.getAction()) {
                case IntentAction.NOTIFICATION_CLICK:
                    Intent intent = new Intent(application, WakeupAppActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    application.startActivity(intent);
                default:
                    break;
            }
        }
    }
}
