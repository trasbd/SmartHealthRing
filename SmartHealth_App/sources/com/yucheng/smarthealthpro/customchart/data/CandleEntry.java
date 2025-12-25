package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.drawable.Drawable;

/* loaded from: classes4.dex */
public class CandleEntry extends Entry {
    private float mClose;
    private float mOpen;
    private float mShadowHigh;
    private float mShadowLow;
    private float mTime;
    private String timeStr;

    public String getTimeStr() {
        return this.timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public float getmTime() {
        return this.mTime;
    }

    public void setmTime(float mTime) {
        this.mTime = mTime;
    }

    public CandleEntry(float x, float shadowH, float shadowL, float open, float close, int time) {
        super(x, (shadowH + shadowL) / 2.0f);
        this.mShadowHigh = shadowH;
        this.mShadowLow = shadowL;
        this.mOpen = open;
        this.mClose = close;
    }

    public CandleEntry(float x, float shadowH, float shadowL, float open, float close, String timeStr) {
        super(x, (shadowH + shadowL) / 2.0f);
        this.mShadowHigh = shadowH;
        this.mShadowLow = shadowL;
        this.mOpen = open;
        this.mClose = close;
        this.mTime = x;
        this.timeStr = timeStr;
    }

    public CandleEntry(float x, float shadowH, float shadowL, float open, float close, Object data) {
        super(x, (shadowH + shadowL) / 2.0f, data);
        this.mShadowHigh = shadowH;
        this.mShadowLow = shadowL;
        this.mOpen = open;
        this.mClose = close;
    }

    public CandleEntry(float x, float shadowH, float shadowL, float open, float close, Drawable icon) {
        super(x, (shadowH + shadowL) / 2.0f, icon);
        this.mShadowHigh = shadowH;
        this.mShadowLow = shadowL;
        this.mOpen = open;
        this.mClose = close;
    }

    public CandleEntry(float x, float shadowH, float shadowL, float open, float close, Drawable icon, Object data) {
        super(x, (shadowH + shadowL) / 2.0f, icon, data);
        this.mShadowHigh = shadowH;
        this.mShadowLow = shadowL;
        this.mOpen = open;
        this.mClose = close;
    }

    public float getShadowRange() {
        return Math.abs(this.mShadowHigh - this.mShadowLow);
    }

    public float getBodyRange() {
        return Math.abs(this.mOpen - this.mClose);
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.BaseEntry
    public float getY() {
        return super.getY();
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.Entry
    public CandleEntry copy() {
        return new CandleEntry(getX(), this.mShadowHigh, this.mShadowLow, this.mOpen, this.mClose, getData());
    }

    public float getHigh() {
        return this.mShadowHigh;
    }

    public void setHigh(float mShadowHigh) {
        this.mShadowHigh = mShadowHigh;
    }

    public float getLow() {
        return this.mShadowLow;
    }

    public void setLow(float mShadowLow) {
        this.mShadowLow = mShadowLow;
    }

    public float getClose() {
        return this.mClose;
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.Entry
    public void setClose(float mClose) {
        this.mClose = mClose;
    }

    public float getOpen() {
        return this.mOpen;
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.Entry
    public void setOpen(float mOpen) {
        this.mOpen = mOpen;
    }
}
