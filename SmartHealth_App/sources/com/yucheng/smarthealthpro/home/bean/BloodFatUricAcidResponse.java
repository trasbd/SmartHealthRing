package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class BloodFatUricAcidResponse {
    public int code;
    public List<DataBean> data;
    public int dataType;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public static class DataBean {
        public int bloodFatModel;
        public int bloodKetoneFloat;
        public int bloodKetoneInteger;
        public int bloodKetoneModel;
        public int bloodSugarFloat;
        public int bloodSugarInteger;
        public int bloodSugarModel;
        public int cholesterolFloat;
        public int cholesterolInteger;
        public String deviceMac;
        public String deviceType;
        public int highLipoproteinCholesterolFloat;
        public int highLipoproteinCholesterolInteger;
        public boolean isBloodFatUpload;
        public boolean isUricAcidUpload;
        public int lowLipoproteinCholesterolFloat;
        public int lowLipoproteinCholesterolInteger;
        public long time;
        public int triglycerideCholesterolFloat;
        public int triglycerideCholesterolInteger;
        public int uricAcid;
        public int uricAcidModel;
        public String userId;

        public Float getCholesterol() throws NumberFormatException {
            float f2;
            String str;
            try {
                if (this.cholesterolFloat < 10) {
                    str = this.cholesterolInteger + ".0" + this.cholesterolFloat;
                } else {
                    str = this.cholesterolInteger + "." + this.cholesterolFloat;
                }
                f2 = Float.parseFloat(str);
            } catch (Exception e2) {
                e2.printStackTrace();
                f2 = 0.0f;
            }
            return Float.valueOf(f2);
        }
    }
}
