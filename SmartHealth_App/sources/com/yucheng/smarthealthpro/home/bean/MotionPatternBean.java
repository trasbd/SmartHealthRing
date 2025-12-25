package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class MotionPatternBean {
    public int code;
    public List<DataBean> data;
    public int dataType;

    public static class DataBean {
        public long endTime;
        public int maxHeartRate;
        public int minHeartRate;
        public int sportCalories;
        public int sportDistances;
        public int sportHeartRate;
        public int sportMode;
        public int sportSteps;
        public long sportTime;
        public int startMethod;
        public long startTime;

        public long getStartTime() {
            return this.startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return this.endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getSportSteps() {
            return this.sportSteps;
        }

        public void setSportSteps(int sportSteps) {
            this.sportSteps = sportSteps;
        }

        public int getSportDistances() {
            return this.sportDistances;
        }

        public void setSportDistances(int sportDistances) {
            this.sportDistances = sportDistances;
        }

        public int getSportCalories() {
            return this.sportCalories;
        }

        public void setSportCalories(int sportCalories) {
            this.sportCalories = sportCalories;
        }

        public int getSportMode() {
            return this.sportMode;
        }

        public void setSportMode(int sportMode) {
            this.sportMode = sportMode;
        }

        public int getStartMethod() {
            return this.startMethod;
        }

        public void setStartMethod(int startMethod) {
            this.startMethod = startMethod;
        }

        public int getSportHeartRate() {
            return this.sportHeartRate;
        }

        public void setSportHeartRate(int sportHeartRate) {
            this.sportHeartRate = sportHeartRate;
        }

        public long getSportTime() {
            return this.sportTime;
        }

        public void setSportTime(long sportTime) {
            this.sportTime = sportTime;
        }

        public int getMinHeartRate() {
            return this.minHeartRate;
        }

        public void setMinHeartRate(int minHeartRate) {
            this.minHeartRate = minHeartRate;
        }

        public int getMaxHeartRate() {
            return this.maxHeartRate;
        }

        public void setMaxHeartRate(int maxHeartRate) {
            this.maxHeartRate = maxHeartRate;
        }
    }

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
}
