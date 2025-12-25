package com.yucheng.smarthealthpro.framework.util;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;
import com.yucheng.smarthealthpro.framework.HealthApplication;

/* loaded from: classes4.dex */
public class ToastUtil {
    private static ToastUtil toastUtil;
    private Context context;

    public static ToastUtil getInstance(Context context) {
        if (toastUtil == null && HealthApplication.getInstance() != null) {
            toastUtil = new ToastUtil(HealthApplication.getInstance().getApplicationContext());
        }
        return toastUtil;
    }

    public static ToastUtil getInstance() {
        if (toastUtil == null && HealthApplication.getInstance() != null) {
            toastUtil = new ToastUtil(HealthApplication.getInstance().getApplicationContext());
        }
        return toastUtil;
    }

    public static void release() {
        toastUtil = null;
    }

    private ToastUtil(Context context) {
        if (context != null) {
            this.context = context;
        }
    }

    public void toast(String str) {
        Toast toastMakeText = Toast.makeText(this.context, str, 1);
        toastMakeText.setGravity(17, toastMakeText.getXOffset() / 2, toastMakeText.getYOffset() / 2);
        toastMakeText.show();
    }

    public void toast(int i2) throws Resources.NotFoundException {
        Toast toastMakeText = Toast.makeText(this.context, i2, 1);
        toastMakeText.setGravity(17, toastMakeText.getXOffset() / 2, toastMakeText.getYOffset() / 2);
        toastMakeText.show();
    }
}
