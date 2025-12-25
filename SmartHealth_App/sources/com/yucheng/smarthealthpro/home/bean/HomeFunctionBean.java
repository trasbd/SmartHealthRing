package com.yucheng.smarthealthpro.home.bean;

import android.graphics.Bitmap;

/* loaded from: classes5.dex */
public class HomeFunctionBean {
    private String function;
    private Bitmap imagePath;
    private Boolean isVisible;
    public String name;
    private float sleepScore;
    private String unit;
    private String value;

    public HomeFunctionBean(String value, String unit, String name, String function, Bitmap imagePath, Boolean isVisible) {
        this.value = value;
        this.name = name;
        this.unit = unit;
        this.function = function;
        this.imagePath = imagePath;
        this.isVisible = isVisible;
    }

    public HomeFunctionBean(String value, String unit, String name, String function, Bitmap imagePath, Boolean isVisible, float sleepScore) {
        this.value = value;
        this.name = name;
        this.unit = unit;
        this.function = function;
        this.imagePath = imagePath;
        this.isVisible = isVisible;
        this.sleepScore = sleepScore;
    }

    public float getSleepScore() {
        return this.sleepScore;
    }

    public void setSleepScore(float sleepScore) {
        this.sleepScore = sleepScore;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Bitmap getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(Bitmap imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getVisible() {
        return this.isVisible;
    }

    public void setVisible(Boolean visible) {
        this.isVisible = visible;
    }
}
