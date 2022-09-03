/*
package com.lei123.YSPocketTools.AndroidTools;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.hjq.permissions.XXPermissions;

import java.util.List;

public class YSPermission {
    private static Activity sContext;
    private static final String TAG = "NBSDKPermission";
    private static boolean isOpenTs = false;//我自己调试用的玩意

    */
/**
     * 获取权限
     * @param context
     * @param isAsk 是否开启获取权限功能
     * @param permissions
     *//*

    public static void getPermission(Context context, boolean isAsk, String... permissions){
        sContext = (Activity)context;
        if(!isAsk)return;
        try {
            if (XXPermissions.isGranted(context, permissions))
            {
                if (isOpenTs) {
                    Toast.makeText(sContext, "已经获得所需所有权限", Toast.LENGTH_SHORT).show();
                }
            }else {
                XXPermissions.with((Activity)context).permission(permissions).request(new OnPermission() {
                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            if (isOpenTs) {
                                Toast.makeText(sContext, "被永久拒绝授权", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if (isOpenTs) {
                                Toast.makeText(sContext, "获取权限失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            if (isOpenTs) {
                                Toast.makeText(sContext, "获取权限成功", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if (isOpenTs) {
                                Toast.makeText(sContext, "部分权限未正常授予", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        } catch (Exception e) {
            if (isOpenTs) {
                Toast.makeText(sContext, "获取权限时发生了错误，请检查清单是否配置权限，或者其他:"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    */
/**
     * 获取权限
     * @param context
     * @param isAsk 是否开启获取权限功能
     * @param isOpenGudie 获取权限失败时，是否引导用户去权限界面，手动打开权限
     * @param isOpenGudieForce 是否强制引导用户去权限界面授权
     * @param title 引导的dialog界面的title.例如：开启权限引导
     * @param message 引导的dialog界面中显示的提示内容，例如：被您永久禁用的权限为应用必要权限，是否需要引导您去手动开启权限呢？
     * @param okButMessage 确认按钮显示的内容
     * @param noButMessage 否定按钮显示的内容
     * @param permissions
     *//*

    public static void getPermission(Context context,boolean isAsk,final boolean isOpenGudie,final boolean isOpenGudieForce,final String title,final String message,final String okButMessage,final String noButMessage,String... permissions){
        sContext = (Activity)context;
        if(!isAsk)return;
        try {
            if (XXPermissions.isHasPermission(context, permissions))
            {
            }else {
                XXPermissions.with((Activity)context).permission(permissions).request(new OnPermission() {
                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            NBUtils.log(TAG, "被永久拒绝授权，请手动授予权限");
                            if (isOpenTs) {
                                Toast.makeText(sContext, "被永久拒绝授权，请手动授予权限", Toast.LENGTH_SHORT).show();
                            }
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            if(isOpenGudie) {
                                final AlertDialog.Builder normalDialog =
                                        new AlertDialog.Builder(sContext);
                                if(title != null && !title.equals(""))normalDialog.setTitle(title);
                                if(message != null && !message.equals(""))normalDialog.setMessage(message);
                                if(okButMessage != null){
                                    normalDialog.setPositiveButton(okButMessage.equals("")?"好的":okButMessage, new OnClickListener() {
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            XXPermissions.gotoPermissionSettings(sContext);
                                        }
                                    });
                                }

                                if(noButMessage != null){
                                    if (!isOpenGudieForce) {
                                        normalDialog.setNegativeButton(noButMessage.equals("")?"下一次":noButMessage, new OnClickListener() {
                                            public void onClick(DialogInterface arg0, int arg1) {
                                            }
                                        });
                                    }
                                }
                                normalDialog.show();
                            }
                        }else {
                            NBUtils.log(TAG, "获取权限失败");
                            if (isOpenTs) {
                                Toast.makeText(sContext, "获取权限失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            NBUtils.log(TAG, "获取权限成功");
                            if (isOpenTs) {
                                Toast.makeText(sContext, "获取权限成功", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            NBUtils.log(TAG, "部分权限未正常授予");
                            if (isOpenTs) {
                                Toast.makeText(sContext, "部分权限未正常授予", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        } catch (Exception e) {
            NBUtils.log(TAG, "获取权限时发生了错误，请检查清单是否配置权限，或者其他:"+e.toString());
            if (isOpenTs) {
                Toast.makeText(sContext, "获取权限时发生了错误，请检查清单是否配置权限，或者其他:"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}*/
