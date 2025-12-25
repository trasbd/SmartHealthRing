package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class HeartRateResponse {
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
        private long heartStartTime;
        private int heartValue;
        public boolean isUpload;

        public DataBean(int heartValue, long heartStartTime, boolean isUpload) {
            this.heartValue = heartValue;
            this.heartStartTime = heartStartTime;
            this.isUpload = isUpload;
        }

        public int getHeartValue() {
            return this.heartValue;
        }

        public void setHeartValue(int heartValue) {
            this.heartValue = heartValue;
        }

        public long getHeartStartTime() {
            return this.heartStartTime;
        }

        public void setHeartStartTime(long heartStartTime) {
            this.heartStartTime = heartStartTime;
        }

        public boolean isUpload() {
            return this.isUpload;
        }

        public void setUpload(boolean upload) {
            this.isUpload = upload;
        }
    }
}
