package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class AllDataResponse {
    public int code;
    public List<DataBean> data;
    public int dataType;

    public static class DataBean {
        public int DBPValue;
        public int OOValue;
        public int SBPValue;
        public int bloodSugarValue;
        public int bodyFatFloatValue;
        public int bodyFatIntValue;
        public int cvrrValue;
        public String deviceMac;
        public String deviceType;
        public int heartValue;
        public int hrvValue;
        public boolean isAwRRUpload;
        public boolean isBloodSugarUpload;
        public boolean isBodyFatUpload;
        public boolean isHrvUpload;
        public boolean isOtherAwRRUpload;
        public boolean isOtherBloodSugarUpload;
        public boolean isOtherBodyFatUpload;
        public boolean isOtherHrvUpload;
        public boolean isOtherSpo2Upload;
        public boolean isOtherTempUpload;
        public boolean isSpo2Upload;
        public boolean isTempUpload;
        public int respiratoryRateValue;
        public long startTime;
        public int stepValue;
        public int tempFloatValue;
        public int tempIntValue;
        public String userId;

        public DataBean(int heartValue, int hrvValue, int cvrrValue, int OOValue, int stepValue, int DBPValue, int tempIntValue, int tempFloatValue, long startTime, int SBPValue, int respiratoryRateValue, int bodyFatIntValue, int bodyFatFloatValue, int bloodSugarValue, boolean isHrvUpload, boolean isSpo2Upload, boolean isAwRRUpload, boolean isTempUpload, boolean isBodyFatUpload, boolean isBloodSugarUpload, boolean isOtherHrvUpload, boolean isOtherBloodUpload, boolean isOtherAwRRUpload, boolean isOtherTempUpload, boolean isOtherBodyFatUpload, boolean isOtherBloodSugarUpload, String userId, String deviceType, String deviceMac) {
            this.heartValue = heartValue;
            this.hrvValue = hrvValue;
            this.cvrrValue = cvrrValue;
            this.OOValue = OOValue;
            this.stepValue = stepValue;
            this.DBPValue = DBPValue;
            this.tempIntValue = tempIntValue;
            this.tempFloatValue = tempFloatValue;
            this.startTime = startTime;
            this.SBPValue = SBPValue;
            this.respiratoryRateValue = respiratoryRateValue;
            this.bodyFatIntValue = bodyFatIntValue;
            this.bodyFatFloatValue = bodyFatFloatValue;
            this.bloodSugarValue = bloodSugarValue;
            this.isSpo2Upload = isSpo2Upload;
            this.isAwRRUpload = isAwRRUpload;
            this.isTempUpload = isTempUpload;
            this.isHrvUpload = isHrvUpload;
            this.isBodyFatUpload = isBodyFatUpload;
            this.isBloodSugarUpload = isBloodSugarUpload;
            this.isOtherHrvUpload = isOtherHrvUpload;
            this.isOtherSpo2Upload = isOtherBloodUpload;
            this.isOtherAwRRUpload = isOtherAwRRUpload;
            this.isOtherTempUpload = isOtherTempUpload;
            this.isOtherBodyFatUpload = isOtherBodyFatUpload;
            this.isOtherBloodSugarUpload = isOtherBloodSugarUpload;
            this.userId = userId;
            this.deviceType = deviceType;
            this.deviceMac = deviceMac;
        }
    }
}
