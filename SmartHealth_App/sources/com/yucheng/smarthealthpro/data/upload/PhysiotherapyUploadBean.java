package com.yucheng.smarthealthpro.data.upload;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PhysiotherapyUploadBean.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\u000bHÆ\u0003J\t\u0010%\u001a\u00020\u000bHÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003Jm\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000bHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\u000bHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0016\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001a¨\u0006."}, d2 = {"Lcom/yucheng/smarthealthpro/data/upload/PhysiotherapyUploadBean;", "", "durationLevel", "", "powerLevel", "startType", "type", TypedValues.TransitionType.S_DURATION, "startTime", "", "userId", "", "zone", "deviceMac", "deviceModel", "<init>", "(IIIIIJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDurationLevel", "()I", "getPowerLevel", "getStartType", "getType", "getDuration", "getStartTime", "()J", "getUserId", "()Ljava/lang/String;", "getZone", "getDeviceMac", "getDeviceModel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class PhysiotherapyUploadBean {

    @SerializedName("deviceMac")
    private final String deviceMac;

    @SerializedName("deviceModel")
    private final String deviceModel;

    @SerializedName(TypedValues.TransitionType.S_DURATION)
    private final int duration;

    @SerializedName("durationLevel")
    private final int durationLevel;

    @SerializedName("powerLevel")
    private final int powerLevel;

    @SerializedName("rtime")
    private final long startTime;

    @SerializedName("startupType")
    private final int startType;

    @SerializedName("operationType")
    private final int type;

    @SerializedName("userId")
    private final String userId;

    @SerializedName("zone")
    private final String zone;

    /* renamed from: component1, reason: from getter */
    public final int getDurationLevel() {
        return this.durationLevel;
    }

    /* renamed from: component10, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPowerLevel() {
        return this.powerLevel;
    }

    /* renamed from: component3, reason: from getter */
    public final int getStartType() {
        return this.startType;
    }

    /* renamed from: component4, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final int getDuration() {
        return this.duration;
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

    public final PhysiotherapyUploadBean copy(int durationLevel, int powerLevel, int startType, int type, int duration, long startTime, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        return new PhysiotherapyUploadBean(durationLevel, powerLevel, startType, type, duration, startTime, userId, zone, deviceMac, deviceModel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhysiotherapyUploadBean)) {
            return false;
        }
        PhysiotherapyUploadBean physiotherapyUploadBean = (PhysiotherapyUploadBean) other;
        return this.durationLevel == physiotherapyUploadBean.durationLevel && this.powerLevel == physiotherapyUploadBean.powerLevel && this.startType == physiotherapyUploadBean.startType && this.type == physiotherapyUploadBean.type && this.duration == physiotherapyUploadBean.duration && this.startTime == physiotherapyUploadBean.startTime && Intrinsics.areEqual(this.userId, physiotherapyUploadBean.userId) && Intrinsics.areEqual(this.zone, physiotherapyUploadBean.zone) && Intrinsics.areEqual(this.deviceMac, physiotherapyUploadBean.deviceMac) && Intrinsics.areEqual(this.deviceModel, physiotherapyUploadBean.deviceModel);
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.durationLevel) * 31) + Integer.hashCode(this.powerLevel)) * 31) + Integer.hashCode(this.startType)) * 31) + Integer.hashCode(this.type)) * 31) + Integer.hashCode(this.duration)) * 31) + Long.hashCode(this.startTime)) * 31) + this.userId.hashCode()) * 31) + this.zone.hashCode()) * 31) + this.deviceMac.hashCode()) * 31) + this.deviceModel.hashCode();
    }

    public String toString() {
        return "PhysiotherapyUploadBean(durationLevel=" + this.durationLevel + ", powerLevel=" + this.powerLevel + ", startType=" + this.startType + ", type=" + this.type + ", duration=" + this.duration + ", startTime=" + this.startTime + ", userId=" + this.userId + ", zone=" + this.zone + ", deviceMac=" + this.deviceMac + ", deviceModel=" + this.deviceModel + ")";
    }

    public PhysiotherapyUploadBean(int i2, int i3, int i4, int i5, int i6, long j2, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        this.durationLevel = i2;
        this.powerLevel = i3;
        this.startType = i4;
        this.type = i5;
        this.duration = i6;
        this.startTime = j2;
        this.userId = userId;
        this.zone = zone;
        this.deviceMac = deviceMac;
        this.deviceModel = deviceModel;
    }

    public final int getDurationLevel() {
        return this.durationLevel;
    }

    public final int getPowerLevel() {
        return this.powerLevel;
    }

    public final int getStartType() {
        return this.startType;
    }

    public final int getType() {
        return this.type;
    }

    public final int getDuration() {
        return this.duration;
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
