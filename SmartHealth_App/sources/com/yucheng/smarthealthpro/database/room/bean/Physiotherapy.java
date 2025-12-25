package com.yucheng.smarthealthpro.database.room.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Physiotherapy.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b*\b\u0087\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\t\u00103\u001a\u00020\bHÆ\u0003J\t\u00104\u001a\u00020\bHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u00106\u001a\u00020\u0013HÆ\u0003J\u009e\u0001\u00107\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013HÆ\u0001¢\u0006\u0002\u00108J\u0013\u00109\u001a\u00020\u00132\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\u0005HÖ\u0001J\t\u0010<\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0016\u0010\u000e\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0016\u0010\u000f\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0016\u0010\u0010\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u0016\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010(¨\u0006="}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/Physiotherapy;", "", "id", "", "queryID", "", "startTimestamp", "timeYearToDay", "", TypedValues.TransitionType.S_DURATION, "type", "startType", "powerLevel", "durationLevel", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "<init>", "(Ljava/lang/Long;IJLjava/lang/String;IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTimestamp", "()J", "getTimeYearToDay", "()Ljava/lang/String;", "getDuration", "getType", "getStartType", "getPowerLevel", "getDurationLevel", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/Long;IJLjava/lang/String;IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Lcom/yucheng/smarthealthpro/database/room/bean/Physiotherapy;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class Physiotherapy {
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private final int duration;
    private final int durationLevel;
    private final Long id;
    private final boolean isUploaded;
    private final int powerLevel;
    private final int queryID;
    private final long startTimestamp;
    private final int startType;
    private final String timeYearToDay;
    private final int type;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component12, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component13, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component14, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQueryID() {
        return this.queryID;
    }

    /* renamed from: component3, reason: from getter */
    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    /* renamed from: component5, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    /* renamed from: component6, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component7, reason: from getter */
    public final int getStartType() {
        return this.startType;
    }

    /* renamed from: component8, reason: from getter */
    public final int getPowerLevel() {
        return this.powerLevel;
    }

    /* renamed from: component9, reason: from getter */
    public final int getDurationLevel() {
        return this.durationLevel;
    }

    public final Physiotherapy copy(Long id, int queryID, long startTimestamp, String timeYearToDay, int duration, int type, int startType, int powerLevel, int durationLevel, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new Physiotherapy(id, queryID, startTimestamp, timeYearToDay, duration, type, startType, powerLevel, durationLevel, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Physiotherapy)) {
            return false;
        }
        Physiotherapy physiotherapy = (Physiotherapy) other;
        return Intrinsics.areEqual(this.id, physiotherapy.id) && this.queryID == physiotherapy.queryID && this.startTimestamp == physiotherapy.startTimestamp && Intrinsics.areEqual(this.timeYearToDay, physiotherapy.timeYearToDay) && this.duration == physiotherapy.duration && this.type == physiotherapy.type && this.startType == physiotherapy.startType && this.powerLevel == physiotherapy.powerLevel && this.durationLevel == physiotherapy.durationLevel && Intrinsics.areEqual(this.userId, physiotherapy.userId) && Intrinsics.areEqual(this.deviceType, physiotherapy.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, physiotherapy.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, physiotherapy.dataGroupId) && this.isUploaded == physiotherapy.isUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.duration)) * 31) + Integer.hashCode(this.type)) * 31) + Integer.hashCode(this.startType)) * 31) + Integer.hashCode(this.powerLevel)) * 31) + Integer.hashCode(this.durationLevel)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded);
    }

    public String toString() {
        return "Physiotherapy(id=" + this.id + ", queryID=" + this.queryID + ", startTimestamp=" + this.startTimestamp + ", timeYearToDay=" + this.timeYearToDay + ", duration=" + this.duration + ", type=" + this.type + ", startType=" + this.startType + ", powerLevel=" + this.powerLevel + ", durationLevel=" + this.durationLevel + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ")";
    }

    public Physiotherapy(Long l, int i2, long j2, String timeYearToDay, int i3, int i4, int i5, int i6, int i7, String userId, String deviceType, String deviceMacAddress, String str, boolean z) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTimestamp = j2;
        this.timeYearToDay = timeYearToDay;
        this.duration = i3;
        this.type = i4;
        this.startType = i5;
        this.powerLevel = i6;
        this.durationLevel = i7;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isUploaded = z;
    }

    public /* synthetic */ Physiotherapy(Long l, int i2, long j2, String str, int i3, int i4, int i5, int i6, int i7, String str2, String str3, String str4, String str5, boolean z, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? 0 : i2, j2, (i8 & 8) != 0 ? "" : str, i3, i4, i5, i6, i7, (i8 & 512) != 0 ? "" : str2, (i8 & 1024) != 0 ? "" : str3, (i8 & 2048) != 0 ? "" : str4, (i8 & 4096) != 0 ? null : str5, (i8 & 8192) != 0 ? false : z);
    }

    public final Long getId() {
        return this.id;
    }

    public final int getQueryID() {
        return this.queryID;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final int getType() {
        return this.type;
    }

    public final int getStartType() {
        return this.startType;
    }

    public final int getPowerLevel() {
        return this.powerLevel;
    }

    public final int getDurationLevel() {
        return this.durationLevel;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    public final boolean isUploaded() {
        return this.isUploaded;
    }
}
