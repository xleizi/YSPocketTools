package com.lei123.YSPocketTools.AndroidTools;

/**
 * 判断两次点击事件的间隔
 */
public class ClickHelper {
    private static long lastClickTime = 0;
    private static long lastClickTime2 = 0;

    /**
     * 判断事件出发时间间隔是否超过预定值 5s
     * 如果小于间隔（目前是1000毫秒）则返回true，否则返回false
     */
    public static boolean isFastDoubleClick_5s() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 5000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 判断事件出发时间间隔是否超过预定值 3s
     * 如果小于间隔（目前是1000毫秒）则返回true，否则返回false
     */
    public static boolean isFastDoubleClick_3s() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 3000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 判断事件出发时间间隔是否超过预定值 5s
     * 如果小于间隔（目前是1000毫秒）则返回true，否则返回false
     */
    public static boolean isFastDoubleClick_10s() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 10000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 判断事件出发时间间隔是否超过预定值 5m
     * 如果小于间隔（目前是1000毫秒）则返回true，否则返回false
     */
    public static boolean isFastDoubleClick2() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime2;
        if (0 < timeD && timeD < 300000) {
            return true;
        }
        lastClickTime2 = time;
        return false;
    }
}
