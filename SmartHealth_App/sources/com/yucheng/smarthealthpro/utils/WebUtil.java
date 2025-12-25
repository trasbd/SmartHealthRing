package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* loaded from: classes5.dex */
public class WebUtil {
    public static void toWeb(Activity activity, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }

    public static void toWeb(Context activity, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }
}
