
package com.lei123.YSPocketTools.ui.Notification;

import static com.lei123.YSPocketTools.utils.FileUtilsKt.imageUrlToBitmap;
import static com.lei123.YSPocketTools.utils.SaveAndLoadKt.loadMainUID;
import static com.lei123.YSPocketTools.utils.SaveAndLoadKt.loadString;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.lei123.YSPocketTools.MainActivity;
import com.lei123.YSPocketTools.R;
import com.lei123.YSPocketTools.activity.WakeupAppActivity;
import com.lei123.YSPocketTools.utils.Constant;
import com.lei123.YSPocketTools.utils.IntentAction;

public class noticationServiceDome {
    /**
     * show normal notification
     *
     * @param context      context
     * @param isSound      Set the sound to play.  if no, it will play on the default stream.
     * @param isShowLock   show when mobile locks screen
     * @param isHeads      heads up dialog
     * @param isAutoCancel cancel notification while click
     * @param isOnly       only show one notification
     */
    public static void normal(String title, String SubText, String text, int picId, String channel_id,
                              Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), picId);


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        //PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), new Intent(IntentAction.NOTIFICATION_CLICK), PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(picId)
                .setTicker(context.getString(R.string.app_name))       //这个没啥用
                .setWhen(System.currentTimeMillis())        //
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(isAutoCancel)
                .setContentIntent(pendingIntent)
                .setSubText(SubText);          //通知文字程序名旁边的副内容

        //设置声音为文件中的还是默认
        //if (isSound) {
        //    builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        //} else {
        builder.setDefaults(Notification.DEFAULT_ALL);
        //}

        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }

        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? Constant.ID_FOR_NORMAL : (int) System.currentTimeMillis(), builder.build());
    }

    /**
     * Show big picture notification
     *
     * @param context      context
     * @param isSound      Set the sound to play.  if no, it will play on the default stream.
     * @param isShowLock   show when mobile locks screen
     * @param isHeads      heads up dialog
     * @param isAutoCancel cancel notification while click
     * @param isOnly       only show one notification
     */
    public static void bigPicture(String content, int imageId,
                                  String title, String SubText, String text, int picId, String channel_id,
                                  Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), picId);

        //String BRAND = systemInformation.getBrand();       //品牌
        //int SDK = systemInformation.getSDKVersion();        //SDK
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(context.getResources(), imageId));
        style.setBigContentTitle(title);
        style.setSummaryText(text);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.setClass(context, WakeupAppActivity.class);

        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setLargeIcon(largeIcon)
                .setSmallIcon(picId)
                .setTicker(context.getString(R.string.app_name))
                .setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(style)
                .setAutoCancel(isAutoCancel)
                //.setOngoing(true)
                .setSubText(SubText);          //通知文字程序名旁边的副内容

        //if(SDK > 30 && (BRAND.equals("Xiaomi") || BRAND.equals("Redmi"))){
        //SDK > 30 && (!"HUAWEI".equals(BRAND))
        if (false) {

        } else {
            builder.setContentIntent(pendingIntent);
        }


        //if (isSound) {
        //    builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        //} else {
        builder.setDefaults(Notification.DEFAULT_ALL);
        //}
        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? Constant.ID_FOR_BIG_PICTURE : (int) System.currentTimeMillis(), builder.build());
    }


    /**
     * Show big text notification
     * You can show more detail in the notification.
     *
     * @param context      context
     * @param isSound      Set the sound to play.  if no, it will play on the default stream.
     * @param isShowLock   show when mobile locks screen
     * @param isHeads      heads up dialog
     * @param isAutoCancel cancel notification while click
     * @param isOnly       only show one notification
     */
    public static void bigText(String content, Bitmap role,
                               String title, String SubText, String text, String channel_id,
                               Context context, boolean isSound, boolean isShowLock,
                               boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.setClass(context, WakeupAppActivity.class);

        PendingIntent pendingIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            //if (true) {
            pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_CANCEL_CURRENT);
        } else {
            pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        }
/*        PendingIntent pendingIntent = PendingIntent
                .getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);*/

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setBigContentTitle(title);
        style.bigText(content);
        style.setSummaryText(context.getString(R.string.app_name));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.setLargeIcon(role)
                    .setSmallIcon(IconCompat.createWithBitmap(role))
                    .setTicker(context.getString(R.string.app_name))
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(title)
                    .setContentText(text)
                    .setStyle(style)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setAutoCancel(isAutoCancel)
                    .setContentIntent(pendingIntent)
                    //.setOngoing(true)
                    .setSubText(SubText);          //通知文字程序名旁边的副内容
        }else{
            return;
        }

        if (false) {

        } else {
            builder.setContentIntent(pendingIntent);
        }
        //String BRAND = systemInformation.getBrand();       //品牌
        //int SDK = systemInformation.getSDKVersion();        //SDK
        //if(SDK > 30 && (BRAND.equals("Xiaomi") || BRAND.equals("Redmi") || BRAND.equals("samsung") || BRAND.equals("realme") || BRAND.equals("vivo"))){
        //SDK > 30 && (!BRAND.equals("HUAWEI"))


        //if (isSound) {
        //builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        //} else {
        builder.setDefaults(Notification.DEFAULT_ALL);
        //}

        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }

        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? Constant.ID_FOR_BIG_TEXT : (int) System.currentTimeMillis(), builder.build());
    }



}

