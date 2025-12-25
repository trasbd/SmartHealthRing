package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class BloodResponse {
    private int code;
    private List<DataBean> data;
    private int dataType;

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
        private int bloodDBP;
        private int bloodSBP;
        private long bloodStartTime;
        private int isInflated;
        public boolean isUpload;

        public DataBean(long bloodStartTime, int bloodDBP, int bloodSBP, boolean isUpload, int isInflated) {
            this.bloodStartTime = bloodStartTime;
            this.bloodDBP = bloodDBP;
            this.bloodSBP = bloodSBP;
            this.isUpload = isUpload;
            this.isInflated = isInflated;
        }

        public int getIsInflated() {
            return this.isInflated;
        }

        public void setIsInflated(int isInflated) {
            this.isInflated = isInflated;
        }

        public long getBloodStartTime() {
            return this.bloodStartTime;
        }

        public void setBloodStartTime(long bloodStartTime) {
            this.bloodStartTime = bloodStartTime;
        }

        public int getBloodDBP() {
            return this.bloodDBP;
        }

        public void setBloodDBP(int bloodDBP) {
            this.bloodDBP = bloodDBP;
        }

        public int getBloodSBP() {
            return this.bloodSBP;
        }

        public void setBloodSBP(int bloodSBP) {
            this.bloodSBP = bloodSBP;
        }

        public boolean isUpload() {
            return this.isUpload;
        }

        public void setUpload(boolean upload) {
            this.isUpload = upload;
        }
    }
}
