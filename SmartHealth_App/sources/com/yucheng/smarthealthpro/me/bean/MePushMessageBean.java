package com.yucheng.smarthealthpro.me.bean;

import android.graphics.Bitmap;

/* loaded from: classes5.dex */
public class MePushMessageBean {
    private String appName;
    private Bitmap bitmap;
    public int index;

    public MePushMessageBean(String appName, Bitmap bitmap, int index) {
        this.appName = appName;
        this.bitmap = bitmap;
        this.index = index;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
