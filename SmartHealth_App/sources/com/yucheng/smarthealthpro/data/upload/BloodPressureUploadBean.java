package com.yucheng.smarthealthpro.data.upload;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BloodPressureUploadBean.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\tHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0016\u0010\u000b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0016\u0010\f\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016¨\u0006("}, d2 = {"Lcom/yucheng/smarthealthpro/data/upload/BloodPressureUploadBean;", "", "diastolicBloodPressure", "", "systolicBloodPressure", "measureMode", "startTime", "", "userId", "", "zone", "deviceMac", "deviceModel", "<init>", "(IIIJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDiastolicBloodPressure", "()I", "getSystolicBloodPressure", "getMeasureMode", "getStartTime", "()J", "getUserId", "()Ljava/lang/String;", "getZone", "getDeviceMac", "getDeviceModel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class BloodPressureUploadBean {

    @SerializedName("deviceMac")
    private final String deviceMac;

    @SerializedName("deviceModel")
    private final String deviceModel;

    @SerializedName("dbp")
    private final int diastolicBloodPressure;

    @SerializedName("isInflated")
    private final int measureMode;

    @SerializedName("rtime")
    private final long startTime;

    @SerializedName("sbp")
    private final int systolicBloodPressure;

    @SerializedName("userId")
    private final String userId;

    @SerializedName("zone")
    private final String zone;

    /* renamed from: component1, reason: from getter */
    public final int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    /* renamed from: component3, reason: from getter */
    public final int getMeasureMode() {
        return this.measureMode;
    }

    /* renamed from: component4, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component5, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getZone() {
        return this.zone;
    }

    /* renamed from: component7, reason: from getter */
    public final String getDeviceMac() {
        return this.deviceMac;
    }

    /* renamed from: component8, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    public final BloodPressureUploadBean copy(int diastolicBloodPressure, int systolicBloodPressure, int measureMode, long startTime, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        return new BloodPressureUploadBean(diastolicBloodPressure, systolicBloodPressure, measureMode, startTime, userId, zone, deviceMac, deviceModel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodPressureUploadBean)) {
            return false;
        }
        BloodPressureUploadBean bloodPressureUploadBean = (BloodPressureUploadBean) other;
        return this.diastolicBloodPressure == bloodPressureUploadBean.diastolicBloodPressure && this.systolicBloodPressure == bloodPressureUploadBean.systolicBloodPressure && this.measureMode == bloodPressureUploadBean.measureMode && this.startTime == bloodPressureUploadBean.startTime && Intrinsics.areEqual(this.userId, bloodPressureUploadBean.userId) && Intrinsics.areEqual(this.zone, bloodPressureUploadBean.zone) && Intrinsics.areEqual(this.deviceMac, bloodPressureUploadBean.deviceMac) && Intrinsics.areEqual(this.deviceModel, bloodPressureUploadBean.deviceModel);
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.diastolicBloodPressure) * 31) + Integer.hashCode(this.systolicBloodPressure)) * 31) + Integer.hashCode(this.measureMode)) * 31) + Long.hashCode(this.startTime)) * 31) + this.userId.hashCode()) * 31) + this.zone.hashCode()) * 31) + this.deviceMac.hashCode()) * 31) + this.deviceModel.hashCode();
    }

    public String toString() {
        return "BloodPressureUploadBean(diastolicBloodPressure=" + this.diastolicBloodPressure + ", systolicBloodPressure=" + this.systolicBloodPressure + ", measureMode=" + this.measureMode + ", startTime=" + this.startTime + ", userId=" + this.userId + ", zone=" + this.zone + ", deviceMac=" + this.deviceMac + ", deviceModel=" + this.deviceModel + ")";
    }

    public BloodPressureUploadBean(int i2, int i3, int i4, long j2, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        this.diastolicBloodPressure = i2;
        this.systolicBloodPressure = i3;
        this.measureMode = i4;
        this.startTime = j2;
        this.userId = userId;
        this.zone = zone;
        this.deviceMac = deviceMac;
        this.deviceModel = deviceModel;
    }

    public final int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    public final int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public final int getMeasureMode() {
        return this.measureMode;
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
