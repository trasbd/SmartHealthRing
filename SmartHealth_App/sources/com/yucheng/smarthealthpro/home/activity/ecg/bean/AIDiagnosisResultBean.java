package com.yucheng.smarthealthpro.home.activity.ecg.bean;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class AIDiagnosisResultBean implements Serializable {
    public String aIDiagnosesUrl;
    public String aIMedicalResult;
    public int age;
    public float body;
    public String data;
    public String deviceMac;
    public String deviceModel;
    public String healthNorm;
    public float heavyLoad;
    public int hhhh;
    public int hrHz;
    public float hrvNorm;
    public int maxb;
    public AIResult medicalResult = new AIResult();
    public int minb;
    public float pressure;
    public float respiratoryRate;
    public int sex;
    public float sympatheticActivityIndex;
    public long time;
    public String userId;

    public static class AIResult {
        public int afflag;
        public String body;
        public String heavyLoad;
        public String hrvNorm;
        public String pressure;
        public int qrstype = 1;
        public int respiratoryRate;
        public String sympatheticActivityIndex;
    }
}
