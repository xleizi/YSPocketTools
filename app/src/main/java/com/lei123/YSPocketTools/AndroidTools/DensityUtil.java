package com.lei123.YSPocketTools.AndroidTools;

import static com.lei123.YSPocketTools.utils.GlobleKt.application;

import android.content.Context;

public class DensityUtil {


    /**
     * 根据手机的分辨率从 dp 的单位转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = application.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = application.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
