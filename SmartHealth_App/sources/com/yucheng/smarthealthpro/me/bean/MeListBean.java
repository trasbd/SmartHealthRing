package com.yucheng.smarthealthpro.me.bean;

import android.graphics.Bitmap;

/* loaded from: classes5.dex */
public class MeListBean {
    private Bitmap RightImagePath;
    private int dataType;
    private String deviceId;
    private Bitmap leftImagePath;
    private String mac;
    private String rightText;
    private String title;

    public String getMac() {
        return this.mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setId(String id) {
        this.deviceId = id;
    }

    public MeListBean(String mac, String deviceId) {
        this.title = mac;
        this.rightText = mac;
        this.mac = mac;
        this.deviceId = deviceId;
    }

    public MeListBean(String title, Bitmap leftImagePath, Bitmap rightImagePath, String rightText, int dataType) {
        this.title = title;
        this.leftImagePath = leftImagePath;
        this.RightImagePath = rightImagePath;
        this.rightText = rightText;
        this.dataType = dataType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getLeftImagePath() {
        return this.leftImagePath;
    }

    public void setLeftImagePath(Bitmap leftImagePath) {
        this.leftImagePath = leftImagePath;
    }

    public Bitmap getRightImagePath() {
        return this.RightImagePath;
    }

    public void setRightImagePath(Bitmap rightImagePath) {
        this.RightImagePath = rightImagePath;
    }

    public String getRightText() {
        return this.rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
