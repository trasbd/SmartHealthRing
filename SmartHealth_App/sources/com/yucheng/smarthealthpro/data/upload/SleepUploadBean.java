package com.yucheng.smarthealthpro.data.upload;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepUploadBean.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u000e\u0012\u0006\u0010\u0012\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u000eHÆ\u0003J\t\u00100\u001a\u00020\u000eHÆ\u0003J\t\u00101\u001a\u00020\u000eHÆ\u0003J\t\u00102\u001a\u00020\u000eHÆ\u0003J\t\u00103\u001a\u00020\u000eHÆ\u0003J\u0095\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u000eHÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00108\u001a\u00020\u0006HÖ\u0001J\t\u00109\u001a\u00020\u000eHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0016\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0016\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0016\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0016\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u000f\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0016\u0010\u0010\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0016\u0010\u0011\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0016\u0010\u0012\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!¨\u0006:"}, d2 = {"Lcom/yucheng/smarthealthpro/data/upload/SleepUploadBean;", "", "startTime", "", "endTime", "deepSleepCount", "", "lightSleepCount", "deepSleepDuration", "lightSleepDuration", "remDuration", "wakeDuration", "wakeCount", "sleepData", "", "userId", "zone", "deviceMac", "deviceModel", "<init>", "(JJIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getStartTime", "()J", "getEndTime", "getDeepSleepCount", "()I", "getLightSleepCount", "getDeepSleepDuration", "getLightSleepDuration", "getRemDuration", "getWakeDuration", "getWakeCount", "getSleepData", "()Ljava/lang/String;", "getUserId", "getZone", "getDeviceMac", "getDeviceModel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class SleepUploadBean {

    @SerializedName("dsCount")
    private final int deepSleepCount;

    @SerializedName("dsTimes")
    private final int deepSleepDuration;

    @SerializedName("deviceMac")
    private final String deviceMac;

    @SerializedName("deviceModel")
    private final String deviceModel;

    @SerializedName("endTime")
    private final long endTime;

    @SerializedName("qsCount")
    private final int lightSleepCount;

    @SerializedName("qsTimes")
    private final int lightSleepDuration;

    @SerializedName("remTimes")
    private final int remDuration;

    @SerializedName("mlist")
    private final String sleepData;

    @SerializedName("beginTime")
    private final long startTime;

    @SerializedName("userId")
    private final String userId;

    @SerializedName("wakeCount")
    private final int wakeCount;

    @SerializedName("wakeDuration")
    private final int wakeDuration;

    @SerializedName("zone")
    private final String zone;

    /* renamed from: component1, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component10, reason: from getter */
    public final String getSleepData() {
        return this.sleepData;
    }

    /* renamed from: component11, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getZone() {
        return this.zone;
    }

    /* renamed from: component13, reason: from getter */
    public final String getDeviceMac() {
        return this.deviceMac;
    }

    /* renamed from: component14, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    /* renamed from: component2, reason: from getter */
    public final long getEndTime() {
        return this.endTime;
    }

    /* renamed from: component3, reason: from getter */
    public final int getDeepSleepCount() {
        return this.deepSleepCount;
    }

    /* renamed from: component4, reason: from getter */
    public final int getLightSleepCount() {
        return this.lightSleepCount;
    }

    /* renamed from: component5, reason: from getter */
    public final int getDeepSleepDuration() {
        return this.deepSleepDuration;
    }

    /* renamed from: component6, reason: from getter */
    public final int getLightSleepDuration() {
        return this.lightSleepDuration;
    }

    /* renamed from: component7, reason: from getter */
    public final int getRemDuration() {
        return this.remDuration;
    }

    /* renamed from: component8, reason: from getter */
    public final int getWakeDuration() {
        return this.wakeDuration;
    }

    /* renamed from: component9, reason: from getter */
    public final int getWakeCount() {
        return this.wakeCount;
    }

    public final SleepUploadBean copy(long startTime, long endTime, int deepSleepCount, int lightSleepCount, int deepSleepDuration, int lightSleepDuration, int remDuration, int wakeDuration, int wakeCount, String sleepData, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(sleepData, "sleepData");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        return new SleepUploadBean(startTime, endTime, deepSleepCount, lightSleepCount, deepSleepDuration, lightSleepDuration, remDuration, wakeDuration, wakeCount, sleepData, userId, zone, deviceMac, deviceModel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepUploadBean)) {
            return false;
        }
        SleepUploadBean sleepUploadBean = (SleepUploadBean) other;
        return this.startTime == sleepUploadBean.startTime && this.endTime == sleepUploadBean.endTime && this.deepSleepCount == sleepUploadBean.deepSleepCount && this.lightSleepCount == sleepUploadBean.lightSleepCount && this.deepSleepDuration == sleepUploadBean.deepSleepDuration && this.lightSleepDuration == sleepUploadBean.lightSleepDuration && this.remDuration == sleepUploadBean.remDuration && this.wakeDuration == sleepUploadBean.wakeDuration && this.wakeCount == sleepUploadBean.wakeCount && Intrinsics.areEqual(this.sleepData, sleepUploadBean.sleepData) && Intrinsics.areEqual(this.userId, sleepUploadBean.userId) && Intrinsics.areEqual(this.zone, sleepUploadBean.zone) && Intrinsics.areEqual(this.deviceMac, sleepUploadBean.deviceMac) && Intrinsics.areEqual(this.deviceModel, sleepUploadBean.deviceModel);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((Long.hashCode(this.startTime) * 31) + Long.hashCode(this.endTime)) * 31) + Integer.hashCode(this.deepSleepCount)) * 31) + Integer.hashCode(this.lightSleepCount)) * 31) + Integer.hashCode(this.deepSleepDuration)) * 31) + Integer.hashCode(this.lightSleepDuration)) * 31) + Integer.hashCode(this.remDuration)) * 31) + Integer.hashCode(this.wakeDuration)) * 31) + Integer.hashCode(this.wakeCount)) * 31) + this.sleepData.hashCode()) * 31) + this.userId.hashCode()) * 31) + this.zone.hashCode()) * 31) + this.deviceMac.hashCode()) * 31) + this.deviceModel.hashCode();
    }

    public String toString() {
        return "SleepUploadBean(startTime=" + this.startTime + ", endTime=" + this.endTime + ", deepSleepCount=" + this.deepSleepCount + ", lightSleepCount=" + this.lightSleepCount + ", deepSleepDuration=" + this.deepSleepDuration + ", lightSleepDuration=" + this.lightSleepDuration + ", remDuration=" + this.remDuration + ", wakeDuration=" + this.wakeDuration + ", wakeCount=" + this.wakeCount + ", sleepData=" + this.sleepData + ", userId=" + this.userId + ", zone=" + this.zone + ", deviceMac=" + this.deviceMac + ", deviceModel=" + this.deviceModel + ")";
    }

    public SleepUploadBean(long j2, long j3, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String sleepData, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(sleepData, "sleepData");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        this.startTime = j2;
        this.endTime = j3;
        this.deepSleepCount = i2;
        this.lightSleepCount = i3;
        this.deepSleepDuration = i4;
        this.lightSleepDuration = i5;
        this.remDuration = i6;
        this.wakeDuration = i7;
        this.wakeCount = i8;
        this.sleepData = sleepData;
        this.userId = userId;
        this.zone = zone;
        this.deviceMac = deviceMac;
        this.deviceModel = deviceModel;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final int getDeepSleepCount() {
        return this.deepSleepCount;
    }

    public final int getLightSleepCount() {
        return this.lightSleepCount;
    }

    public final int getDeepSleepDuration() {
        return this.deepSleepDuration;
    }

    public final int getLightSleepDuration() {
        return this.lightSleepDuration;
    }

    public final int getRemDuration() {
        return this.remDuration;
    }

    public final int getWakeDuration() {
        return this.wakeDuration;
    }

    public final int getWakeCount() {
        return this.wakeCount;
    }

    public final String getSleepData() {
        return this.sleepData;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getZone() {
        return this.zone;
    }

    public final String getDeviceMac() {
        return this.deviceMac;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }
}
