package com.yucheng.smarthealthpro.life.adapter;

/* loaded from: classes5.dex */
public class LifeData {
    public int icon;
    public boolean opened;
    public int protocolIndex;
    public boolean showIndicate;
    public int text1;
    public int text2;
    public int text3;
    public int viewType;

    public LifeData(int viewType) {
        this.viewType = viewType;
    }

    public LifeData(int viewType, int protocolIndex) {
        this.viewType = viewType;
        this.protocolIndex = protocolIndex;
    }

    public LifeData(int icon, int text1, int text2, int text3, boolean opened, boolean showIndicate, int viewType, int protocolIndex) {
        this.viewType = viewType;
        this.icon = icon;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.opened = opened;
        this.showIndicate = showIndicate;
        this.protocolIndex = protocolIndex;
    }
}
