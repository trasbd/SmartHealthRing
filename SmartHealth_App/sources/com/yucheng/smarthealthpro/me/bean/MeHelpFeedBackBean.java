package com.yucheng.smarthealthpro.me.bean;

import android.graphics.Bitmap;

/* loaded from: classes5.dex */
public class MeHelpFeedBackBean {
    private Bitmap imagePath;
    private int index;

    public MeHelpFeedBackBean(Bitmap imagePath, int index) {
        this.imagePath = imagePath;
        this.index = index;
    }

    public Bitmap getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(Bitmap imagePath) {
        this.imagePath = imagePath;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
