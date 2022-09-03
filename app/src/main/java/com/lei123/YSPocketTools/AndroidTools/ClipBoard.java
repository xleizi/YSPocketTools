package com.lei123.YSPocketTools.AndroidTools;

import android.content.ClipboardManager;
import android.content.Context;

public class ClipBoard {
    public static void sendToClipBoard(Context context){
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText("派蒙口袋工具箱");
    }

    public static void sendCookiesToClipBoard(Context context, String Cookies){
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(Cookies);
    }

    public static void sendstrToClipBoard(Context context, String str){
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(str);
    }
}
