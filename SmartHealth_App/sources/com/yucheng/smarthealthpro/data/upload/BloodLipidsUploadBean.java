package com.yucheng.smarthealthpro.data.upload;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BloodLipidsUploadBean.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\bHÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003Jw\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\bHÖ\u0001J\t\u00100\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u000b\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013¨\u00061"}, d2 = {"Lcom/yucheng/smarthealthpro/data/upload/BloodLipidsUploadBean;", "", "cholesterol", "", "triglyceride", "lowLipoproteinCholesterol", "highLipoproteinCholesterol", "measureMode", "", "startTime", "", "hour", "userId", "zone", "deviceMac", "deviceModel", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCholesterol", "()Ljava/lang/String;", "getTriglyceride", "getLowLipoproteinCholesterol", "getHighLipoproteinCholesterol", "getMeasureMode", "()I", "getStartTime", "()J", "getHour", "getUserId", "getZone", "getDeviceMac", "getDeviceModel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class BloodLipidsUploadBean {

    @SerializedName("tc")
    private final String cholesterol;

    @SerializedName("deviceMac")
    private final String deviceMac;

    @SerializedName("deviceModel")
    private final String deviceModel;

    @SerializedName("hdlc")
    private final String highLipoproteinCholesterol;

    @SerializedName("hour")
    private final int hour;

    @SerializedName("ldlc")
    private final String lowLipoproteinCholesterol;

    @SerializedName("cMode")
    private final int measureMode;

    @SerializedName("rtime")
    private final long startTime;

    @SerializedName("tg")
    private final String triglyceride;

    @SerializedName("userId")
    private final String userId;

    @SerializedName("zone")
    private final String zone;

    /* renamed from: component1, reason: from getter */
    public final String getCholesterol() {
        return this.cholesterol;
    }

    /* renamed from: component10, reason: from getter */
    public final String getDeviceMac() {
        return this.deviceMac;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTriglyceride() {
        return this.triglyceride;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLowLipoproteinCholesterol() {
        return this.lowLipoproteinCholesterol;
    }

    /* renamed from: component4, reason: from getter */
    public final String getHighLipoproteinCholesterol() {
        return this.highLipoproteinCholesterol;
    }

    /* renamed from: component5, reason: from getter */
    public final int getMeasureMode() {
        return this.measureMode;
    }

    /* renamed from: component6, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component7, reason: from getter */
    public final int getHour() {
        return this.hour;
    }

    /* renamed from: component8, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component9, reason: from getter */
    public final String getZone() {
        return this.zone;
    }

    public final BloodLipidsUploadBean copy(String cholesterol, String triglyceride, String lowLipoproteinCholesterol, String highLipoproteinCholesterol, int measureMode, long startTime, int hour, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(cholesterol, "cholesterol");
        Intrinsics.checkNotNullParameter(triglyceride, "triglyceride");
        Intrinsics.checkNotNullParameter(lowLipoproteinCholesterol, "lowLipoproteinCholesterol");
        Intrinsics.checkNotNullParameter(highLipoproteinCholesterol, "highLipoproteinCholesterol");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        return new BloodLipidsUploadBean(cholesterol, triglyceride, lowLipoproteinCholesterol, highLipoproteinCholesterol, measureMode, startTime, hour, userId, zone, deviceMac, deviceModel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodLipidsUploadBean)) {
            return false;
        }
        BloodLipidsUploadBean bloodLipidsUploadBean = (BloodLipidsUploadBean) other;
        return Intrinsics.areEqual(this.cholesterol, bloodLipidsUploadBean.cholesterol) && Intrinsics.areEqual(this.triglyceride, bloodLipidsUploadBean.triglyceride) && Intrinsics.areEqual(this.lowLipoproteinCholesterol, bloodLipidsUploadBean.lowLipoproteinCholesterol) && Intrinsics.areEqual(this.highLipoproteinCholesterol, bloodLipidsUploadBean.highLipoproteinCholesterol) && this.measureMode == bloodLipidsUploadBean.measureMode && this.startTime == bloodLipidsUploadBean.startTime && this.hour == bloodLipidsUploadBean.hour && Intrinsics.areEqual(this.userId, bloodLipidsUploadBean.userId) && Intrinsics.areEqual(this.zone, bloodLipidsUploadBean.zone) && Intrinsics.areEqual(this.deviceMac, bloodLipidsUploadBean.deviceMac) && Intrinsics.areEqual(this.deviceModel, bloodLipidsUploadBean.deviceModel);
    }

    public int hashCode() {
        return (((((((((((((((((((this.cholesterol.hashCode() * 31) + this.triglyceride.hashCode()) * 31) + this.lowLipoproteinCholesterol.hashCode()) * 31) + this.highLipoproteinCholesterol.hashCode()) * 31) + Integer.hashCode(this.measureMode)) * 31) + Long.hashCode(this.startTime)) * 31) + Integer.hashCode(this.hour)) * 31) + this.userId.hashCode()) * 31) + this.zone.hashCode()) * 31) + this.deviceMac.hashCode()) * 31) + this.deviceModel.hashCode();
    }

    public String toString() {
        return "BloodLipidsUploadBean(cholesterol=" + this.cholesterol + ", triglyceride=" + this.triglyceride + ", lowLipoproteinCholesterol=" + this.lowLipoproteinCholesterol + ", highLipoproteinCholesterol=" + this.highLipoproteinCholesterol + ", measureMode=" + this.measureMode + ", startTime=" + this.startTime + ", hour=" + this.hour + ", userId=" + this.userId + ", zone=" + this.zone + ", deviceMac=" + this.deviceMac + ", deviceModel=" + this.deviceModel + ")";
    }

    public BloodLipidsUploadBean(String cholesterol, String triglyceride, String lowLipoproteinCholesterol, String highLipoproteinCholesterol, int i2, long j2, int i3, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(cholesterol, "cholesterol");
        Intrinsics.checkNotNullParameter(triglyceride, "triglyceride");
        Intrinsics.checkNotNullParameter(lowLipoproteinCholesterol, "lowLipoproteinCholesterol");
        Intrinsics.checkNotNullParameter(highLipoproteinCholesterol, "highLipoproteinCholesterol");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        this.cholesterol = cholesterol;
        this.triglyceride = triglyceride;
        this.lowLipoproteinCholesterol = lowLipoproteinCholesterol;
        this.highLipoproteinCholesterol = highLipoproteinCholesterol;
        this.measureMode = i2;
        this.startTime = j2;
        this.hour = i3;
        this.userId = userId;
        this.zone = zone;
        this.deviceMac = deviceMac;
        this.deviceModel = deviceModel;
    }

    public final String getCholesterol() {
        return this.cholesterol;
    }

    public final String getTriglyceride() {
        return this.triglyceride;
    }

    public final String getLowLipoproteinCholesterol() {
        return this.lowLipoproteinCholesterol;
    }

    public final String getHighLipoproteinCholesterol() {
        return this.highLipoproteinCholesterol;
    }

    public final int getMeasureMode() {
        return this.measureMode;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final int getHour() {
        return this.hour;
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
