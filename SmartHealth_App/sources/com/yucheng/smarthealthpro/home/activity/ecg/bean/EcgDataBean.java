package com.yucheng.smarthealthpro.home.activity.ecg.bean;

/* loaded from: classes5.dex */
public class EcgDataBean {
    private float data;
    private String description;
    private String name;
    private int state;
    private int viewType;

    public EcgDataBean(String name, float data, int state, int viewType) {
        this.name = name;
        this.data = data;
        this.state = state;
        this.viewType = viewType;
    }

    public EcgDataBean(String name, float data, int state, String description, int viewType) {
        this.name = name;
        this.data = data;
        this.state = state;
        this.description = description;
        this.viewType = viewType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getData() {
        return this.data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public int getViewType() {
        return this.viewType;
    }
}
