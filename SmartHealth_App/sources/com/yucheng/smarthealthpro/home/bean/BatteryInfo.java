package com.yucheng.smarthealthpro.home.bean;

/* loaded from: classes5.dex */
public class BatteryInfo {
    public BatteryUsageStatistics batteryUsageStatistics;
    public String deviceModel;
    public Firmware firmware;
    public String mac;

    public static class BatteryUsageStatistics {
        public long callDuration;
        public long currentBatteryLevel;
        public long healthMeasurementDuration;
        public long lastChargingEndBatteryLevel;
        public long lastChargingEndTime;
        public long musicDuration;
        public long pushMessagesCount;
        public long screenOnDuration;
        public long usageDuration;
    }

    public static class Firmware {
        public long updateTime;
        public String version;
    }
}
