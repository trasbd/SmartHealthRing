package com.yucheng.smarthealthpro.home.bean;

import android.graphics.drawable.Drawable;

/* loaded from: classes5.dex */
public class HomeHealthyBean {
    private String body;
    private String classify;
    private Drawable imagePath;
    private int look;
    private String time;
    private String title;

    public HomeHealthyBean(String title, String body, String classify, int look, String time, Drawable imagePath) {
        this.title = title;
        this.body = body;
        this.classify = classify;
        this.look = look;
        this.time = time;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getClassify() {
        return this.classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getLook() {
        return this.look;
    }

    public void setLook(int look) {
        this.look = look;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Drawable getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(Drawable imagePath) {
        this.imagePath = imagePath;
    }
}
