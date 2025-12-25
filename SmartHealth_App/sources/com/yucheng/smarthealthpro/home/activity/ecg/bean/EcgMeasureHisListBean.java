package com.yucheng.smarthealthpro.home.activity.ecg.bean;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class EcgMeasureHisListBean implements Serializable {
    private int age;
    private int diagnoseType;
    private String healthNorm;
    private int heart;
    private int hrv;
    private boolean isAfib;
    private int maxBp;
    private String measureData;
    private String medicalResult;
    private int minBp;
    private int sex;
    private long time;
    private String userId;

    public EcgMeasureHisListBean(String userId, long time, int hrv, int heart, int maxBp, int minBp, String measureData, int age, int sex, String medicalResult, boolean isAfib, int diagnoseType) {
        this.userId = userId;
        this.time = time;
        this.hrv = hrv;
        this.heart = heart;
        this.maxBp = maxBp;
        this.minBp = minBp;
        this.measureData = measureData;
        this.age = age;
        this.sex = sex;
        this.medicalResult = medicalResult;
        this.isAfib = isAfib;
        this.diagnoseType = diagnoseType;
    }

    public EcgMeasureHisListBean(String userId, long time, int hrv, int heart, int maxBp, int minBp, String measureData, int age, int sex, String medicalResult, boolean isAfib, int diagnoseType, String healthNorm) {
        this.userId = userId;
        this.time = time;
        this.hrv = hrv;
        this.heart = heart;
        this.maxBp = maxBp;
        this.minBp = minBp;
        this.measureData = measureData;
        this.age = age;
        this.sex = sex;
        this.medicalResult = medicalResult;
        this.isAfib = isAfib;
        this.diagnoseType = diagnoseType;
        this.healthNorm = healthNorm;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getHrv() {
        return this.hrv;
    }

    public void setHrv(int hrv) {
        this.hrv = hrv;
    }

    public int getHeart() {
        return this.heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getMaxBp() {
        return this.maxBp;
    }

    public void setMaxBp(int maxBp) {
        this.maxBp = maxBp;
    }

    public int getMinBp() {
        return this.minBp;
    }

    public void setMinBp(int minBp) {
        this.minBp = minBp;
    }

    public String getMeasureData() {
        return this.measureData;
    }

    public void setMeasureData(String measureData) {
        this.measureData = measureData;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMedicalResult() {
        return this.medicalResult;
    }

    public void setMedicalResult(String medicalResult) {
        this.medicalResult = medicalResult;
    }

    public boolean isAfib() {
        return this.isAfib;
    }

    public void setAfib(boolean afib) {
        this.isAfib = afib;
    }

    public int getDiagnoseType() {
        return this.diagnoseType;
    }

    public void setDiagnoseType(int diagnoseType) {
        this.diagnoseType = diagnoseType;
    }

    public String getHealthNorm() {
        return this.healthNorm;
    }

    public void setHealthNorm(String healthNorm) {
        this.healthNorm = healthNorm;
    }
}
