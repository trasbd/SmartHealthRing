package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Step.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b1\b\u0087\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\tHÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0005HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\tHÆ\u0003J\t\u00108\u001a\u00020\tHÆ\u0003J\t\u00109\u001a\u00020\tHÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010;\u001a\u00020\u0012HÆ\u0003J\t\u0010<\u001a\u00020\u0012HÆ\u0003J\u009e\u0001\u0010=\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0012HÆ\u0001¢\u0006\u0002\u0010>J\u0013\u0010?\u001a\u00020\u00122\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010A\u001a\u00020\u0005HÖ\u0001J\t\u0010B\u001a\u00020\tHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010%R\u001e\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001a\"\u0004\b'\u0010%R\u001e\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001a\"\u0004\b)\u0010%R\u0016\u0010\r\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0016\u0010\u000e\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\"R\u0016\u0010\u000f\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010.R\u0016\u0010\u0013\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010.¨\u0006C"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/Step;", "", "id", "", "queryID", "", "startTime", "endTime", "timeYearToDay", "", "sportStep", "sportDistance", "sportCalorie", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IJJLjava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTime", "()J", "setStartTime", "(J)V", "getEndTime", "setEndTime", "getTimeYearToDay", "()Ljava/lang/String;", "getSportStep", "setSportStep", "(I)V", "getSportDistance", "setSportDistance", "getSportCalorie", "setSportCalorie", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/Long;IJJLjava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/Step;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class Step {
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private long endTime;
    private final Long id;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private final int queryID;
    private int sportCalorie;
    private int sportDistance;
    private int sportStep;
    private long startTime;
    private final String timeYearToDay;
    private final String userId;

    public Step() {
        this(null, 0, 0L, 0L, null, 0, 0, 0, null, null, null, null, false, false, 16383, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component12, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }

    /* renamed from: component14, reason: from getter */
    public final boolean getIsOtherUploaded() {
        return this.isOtherUploaded;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQueryID() {
        return this.queryID;
    }

    /* renamed from: component3, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component4, reason: from getter */
    public final long getEndTime() {
        return this.endTime;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    /* renamed from: component6, reason: from getter */
    public final int getSportStep() {
        return this.sportStep;
    }

    /* renamed from: component7, reason: from getter */
    public final int getSportDistance() {
        return this.sportDistance;
    }

    /* renamed from: component8, reason: from getter */
    public final int getSportCalorie() {
        return this.sportCalorie;
    }

    /* renamed from: component9, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    public final Step copy(Long id, int queryID, long startTime, long endTime, String timeYearToDay, int sportStep, int sportDistance, int sportCalorie, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new Step(id, queryID, startTime, endTime, timeYearToDay, sportStep, sportDistance, sportCalorie, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Step)) {
            return false;
        }
        Step step = (Step) other;
        return Intrinsics.areEqual(this.id, step.id) && this.queryID == step.queryID && this.startTime == step.startTime && this.endTime == step.endTime && Intrinsics.areEqual(this.timeYearToDay, step.timeYearToDay) && this.sportStep == step.sportStep && this.sportDistance == step.sportDistance && this.sportCalorie == step.sportCalorie && Intrinsics.areEqual(this.userId, step.userId) && Intrinsics.areEqual(this.deviceType, step.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, step.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, step.dataGroupId) && this.isUploaded == step.isUploaded && this.isOtherUploaded == step.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTime)) * 31) + Long.hashCode(this.endTime)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.sportStep)) * 31) + Integer.hashCode(this.sportDistance)) * 31) + Integer.hashCode(this.sportCalorie)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "Step(id=" + this.id + ", queryID=" + this.queryID + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", timeYearToDay=" + this.timeYearToDay + ", sportStep=" + this.sportStep + ", sportDistance=" + this.sportDistance + ", sportCalorie=" + this.sportCalorie + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public Step(Long l, int i2, long j2, long j3, String timeYearToDay, int i3, int i4, int i5, String userId, String deviceType, String deviceMacAddress, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTime = j2;
        this.endTime = j3;
        this.timeYearToDay = timeYearToDay;
        this.sportStep = i3;
        this.sportDistance = i4;
        this.sportCalorie = i5;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isUploaded = z;
        this.isOtherUploaded = z2;
    }

    public /* synthetic */ Step(Long l, int i2, long j2, long j3, String str, int i3, int i4, int i5, String str2, String str3, String str4, String str5, boolean z, boolean z2, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? null : l, (i6 & 2) != 0 ? 0 : i2, (i6 & 4) != 0 ? 0L : j2, (i6 & 8) == 0 ? j3 : 0L, (i6 & 16) != 0 ? "" : str, (i6 & 32) != 0 ? 0 : i3, (i6 & 64) != 0 ? 0 : i4, (i6 & 128) != 0 ? 0 : i5, (i6 & 256) != 0 ? "" : str2, (i6 & 512) != 0 ? "" : str3, (i6 & 1024) == 0 ? str4 : "", (i6 & 2048) != 0 ? null : str5, (i6 & 4096) != 0 ? false : z, (i6 & 8192) != 0 ? false : z2);
    }

    public final Long getId() {
        return this.id;
    }

    public final int getQueryID() {
        return this.queryID;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j2) {
        this.startTime = j2;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(long j2) {
        this.endTime = j2;
    }

    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    public final int getSportStep() {
        return this.sportStep;
    }

    public final void setSportStep(int i2) {
        this.sportStep = i2;
    }

    public final int getSportDistance() {
        return this.sportDistance;
    }

    public final void setSportDistance(int i2) {
        this.sportDistance = i2;
    }

    public final int getSportCalorie() {
        return this.sportCalorie;
    }

    public final void setSportCalorie(int i2) {
        this.sportCalorie = i2;
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

    public final boolean isOtherUploaded() {
        return this.isOtherUploaded;
    }
}
