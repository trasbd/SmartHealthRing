package com.yucheng.smarthealthpro.data.upload;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StepUploadBean.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\u000bHÆ\u0003J\t\u0010%\u001a\u00020\u000bHÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003Jm\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000bHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\u000bHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0016\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001a¨\u0006."}, d2 = {"Lcom/yucheng/smarthealthpro/data/upload/StepUploadBean;", "", "hour", "", "sportDistance", "sportStep", "sportCalorie", "endTime", "", "startTime", "userId", "", "zone", "deviceMac", "deviceModel", "<init>", "(IIIIJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHour", "()I", "getSportDistance", "getSportStep", "getSportCalorie", "getEndTime", "()J", "getStartTime", "getUserId", "()Ljava/lang/String;", "getZone", "getDeviceMac", "getDeviceModel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class StepUploadBean {

    @SerializedName("deviceMac")
    private final String deviceMac;

    @SerializedName("deviceModel")
    private final String deviceModel;

    @SerializedName("enddate")
    private final long endTime;

    @SerializedName("hour")
    private final int hour;

    @SerializedName("cakl")
    private final int sportCalorie;

    @SerializedName("des")
    private final int sportDistance;

    @SerializedName("step")
    private final int sportStep;

    @SerializedName("begindate")
    private final long startTime;

    @SerializedName("userId")
    private final String userId;

    @SerializedName("zone")
    private final String zone;

    /* renamed from: component1, reason: from getter */
    public final int getHour() {
        return this.hour;
    }

    /* renamed from: component10, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSportDistance() {
        return this.sportDistance;
    }

    /* renamed from: component3, reason: from getter */
    public final int getSportStep() {
        return this.sportStep;
    }

    /* renamed from: component4, reason: from getter */
    public final int getSportCalorie() {
        return this.sportCalorie;
    }

    /* renamed from: component5, reason: from getter */
    public final long getEndTime() {
        return this.endTime;
    }

    /* renamed from: component6, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component7, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component8, reason: from getter */
    public final String getZone() {
        return this.zone;
    }

    /* renamed from: component9, reason: from getter */
    public final String getDeviceMac() {
        return this.deviceMac;
    }

    public final StepUploadBean copy(int hour, int sportDistance, int sportStep, int sportCalorie, long endTime, long startTime, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        return new StepUploadBean(hour, sportDistance, sportStep, sportCalorie, endTime, startTime, userId, zone, deviceMac, deviceModel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StepUploadBean)) {
            return false;
        }
        StepUploadBean stepUploadBean = (StepUploadBean) other;
        return this.hour == stepUploadBean.hour && this.sportDistance == stepUploadBean.sportDistance && this.sportStep == stepUploadBean.sportStep && this.sportCalorie == stepUploadBean.sportCalorie && this.endTime == stepUploadBean.endTime && this.startTime == stepUploadBean.startTime && Intrinsics.areEqual(this.userId, stepUploadBean.userId) && Intrinsics.areEqual(this.zone, stepUploadBean.zone) && Intrinsics.areEqual(this.deviceMac, stepUploadBean.deviceMac) && Intrinsics.areEqual(this.deviceModel, stepUploadBean.deviceModel);
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.hour) * 31) + Integer.hashCode(this.sportDistance)) * 31) + Integer.hashCode(this.sportStep)) * 31) + Integer.hashCode(this.sportCalorie)) * 31) + Long.hashCode(this.endTime)) * 31) + Long.hashCode(this.startTime)) * 31) + this.userId.hashCode()) * 31) + this.zone.hashCode()) * 31) + this.deviceMac.hashCode()) * 31) + this.deviceModel.hashCode();
    }

    public String toString() {
        return "StepUploadBean(hour=" + this.hour + ", sportDistance=" + this.sportDistance + ", sportStep=" + this.sportStep + ", sportCalorie=" + this.sportCalorie + ", endTime=" + this.endTime + ", startTime=" + this.startTime + ", userId=" + this.userId + ", zone=" + this.zone + ", deviceMac=" + this.deviceMac + ", deviceModel=" + this.deviceModel + ")";
    }

    public StepUploadBean(int i2, int i3, int i4, int i5, long j2, long j3, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        this.hour = i2;
        this.sportDistance = i3;
        this.sportStep = i4;
        this.sportCalorie = i5;
        this.endTime = j2;
        this.startTime = j3;
        this.userId = userId;
        this.zone = zone;
        this.deviceMac = deviceMac;
        this.deviceModel = deviceModel;
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getSportDistance() {
        return this.sportDistance;
    }

    public final int getSportStep() {
        return this.sportStep;
    }

    public final int getSportCalorie() {
        return this.sportCalorie;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final long getStartTime() {
        return this.startTime;
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
