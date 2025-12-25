package com.yucheng.smarthealthpro.care.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class HealthFunctionResponse {
    public int code;
    public DataBean data = new DataBean();
    public String message;

    public static class DataBean {

        @SerializedName("haBloodFat")
        public Boolean isSupportBloodFat;

        @SerializedName("haBloodSugar")
        public Boolean isSupportBloodSugar;

        @SerializedName("haUricAcid")
        public Boolean isSupportUricAcid;

        public DataBean() {
        }

        public DataBean(Boolean isSupportUricAcid, Boolean isSupportBloodSugar, Boolean isSupportBloodFat) {
            this.isSupportUricAcid = isSupportUricAcid;
            this.isSupportBloodSugar = isSupportBloodSugar;
            this.isSupportBloodFat = isSupportBloodFat;
        }
    }
}
