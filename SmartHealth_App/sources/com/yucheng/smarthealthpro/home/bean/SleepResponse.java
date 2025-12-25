package com.yucheng.smarthealthpro.home.bean;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.gson.annotations.SerializedName;
import com.yucheng.smarthealthpro.database.room.bean.SleepItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class SleepResponse {
    private int code;
    private List<SleepDataBean> data;
    private int dataType;

    public static class SleepType {
        public static final int awake = 244;
        public static final int deepSleep = 241;
        public static final int lightSleep = 242;
        public static final int rem = 243;
        public static final int unknow = -1;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<SleepDataBean> getData() {
        return this.data;
    }

    public void setData(List<SleepDataBean> data) {
        this.data = data;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public static class SleepDataBean {
        private int deepSleepCount;
        private int deepSleepTotal;
        private long endTime;
        public boolean isUpload;
        private int lightSleepCount;
        private int lightSleepTotal;

        @SerializedName(alternate = {"remTimes"}, value = "rapidEyeMovementTotal")
        public int rapidEyeMovementTotal;
        private List<SleepItem> sleepData;
        private long startTime;
        public int wakeCount;
        public int wakeDuration;

        public SleepDataBean(int deepSleepCount, int lightSleepCount, long startTime, long endTime, int deepSleepTotal, int lightSleepTotal, int rapidEyeMovementTotal, int wakeCount, int wakeDuration, List<SleepItem> sleepData, boolean isUpload) {
            new ArrayList();
            this.deepSleepCount = deepSleepCount;
            this.lightSleepCount = lightSleepCount;
            this.startTime = startTime;
            this.endTime = endTime;
            this.deepSleepTotal = deepSleepTotal;
            this.lightSleepTotal = lightSleepTotal;
            this.sleepData = sleepData;
            this.isUpload = isUpload;
            this.rapidEyeMovementTotal = rapidEyeMovementTotal;
            this.wakeCount = wakeCount;
            this.wakeDuration = wakeDuration;
        }

        public int getDeepSleepCount() {
            return this.deepSleepCount;
        }

        public void setDeepSleepCount(int deepSleepCount) {
            this.deepSleepCount = deepSleepCount;
        }

        public int getLightSleepCount() {
            return this.lightSleepCount;
        }

        public void setLightSleepCount(int lightSleepCount) {
            this.lightSleepCount = lightSleepCount;
        }

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

        public int getDeepSleepTotal() {
            return this.deepSleepTotal;
        }

        public void setDeepSleepTotal(int deepSleepTotal) {
            this.deepSleepTotal = deepSleepTotal;
        }

        public int getLightSleepTotal() {
            return this.lightSleepTotal;
        }

        public void setLightSleepTotal(int lightSleepTotal) {
            this.lightSleepTotal = lightSleepTotal;
        }

        public List<SleepItem> getSleepData() {
            return this.sleepData;
        }

        public void setSleepData(List<SleepItem> sleepData) {
            this.sleepData = sleepData;
        }

        public boolean isUpload() {
            return this.isUpload;
        }

        public void setUpload(boolean upload) {
            this.isUpload = upload;
        }

        public String toString() {
            return "DataBean{deepSleepCount=" + this.deepSleepCount + ", lightSleepCount=" + this.lightSleepCount + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", deepSleepTotal=" + this.deepSleepTotal + ", lightSleepTotal=" + this.lightSleepTotal + ", sleepData=" + Arrays.toString(this.sleepData.toArray()) + ", isUpload=" + this.isUpload + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        }
    }
}
