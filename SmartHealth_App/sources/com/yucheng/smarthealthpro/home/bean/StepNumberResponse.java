package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class StepNumberResponse {
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
        public boolean isUpload;
        private int sportCalorie;
        private int sportDistance;
        private long sportEndTime;
        private long sportStartTime;
        private int sportStep;

        public DataBean(long sportStartTime, long sportEndTime, int sportStep, int sportDistance, int sportCalorie, boolean isUpload) {
            this.sportStartTime = sportStartTime;
            this.sportEndTime = sportEndTime;
            this.sportStep = sportStep;
            this.sportDistance = sportDistance;
            this.sportCalorie = sportCalorie;
            this.isUpload = isUpload;
        }

        public long getSportStartTime() {
            return this.sportStartTime;
        }

        public void setSportStartTime(long sportStartTime) {
            this.sportStartTime = sportStartTime;
        }

        public long getSportEndTime() {
            return this.sportEndTime;
        }

        public void setSportEndTime(long sportEndTime) {
            this.sportEndTime = sportEndTime;
        }

        public int getSportStep() {
            return this.sportStep;
        }

        public void setSportStep(int sportStep) {
            this.sportStep = sportStep;
        }

        public int getSportDistance() {
            return this.sportDistance;
        }

        public void setSportDistance(int sportDistance) {
            this.sportDistance = sportDistance;
        }

        public int getSportCalorie() {
            return this.sportCalorie;
        }

        public void setSportCalorie(int sportCalorie) {
            this.sportCalorie = sportCalorie;
        }

        public boolean isUpload() {
            return this.isUpload;
        }

        public void setUpload(boolean upload) {
            this.isUpload = upload;
        }
    }
}
