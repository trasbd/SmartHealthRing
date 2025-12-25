package com.yucheng.smarthealthpro.sport.bean;

/* loaded from: classes5.dex */
public class SportRecord2 {
    private Object content;
    private boolean isOpen = true;
    private String title;
    private int viewType;

    public SportRecord2(int viewType, String title) {
        this.title = title;
        this.viewType = viewType;
    }

    public SportRecord2(int viewType, String title, Object content) {
        this.title = title;
        this.viewType = viewType;
        this.content = content;
    }

    public int getViewType() {
        return this.viewType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return this.content;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public boolean isOpen() {
        return this.isOpen;
    }
}
