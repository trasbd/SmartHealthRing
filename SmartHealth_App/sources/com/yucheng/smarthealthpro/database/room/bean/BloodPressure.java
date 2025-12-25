package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BloodPressure.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b(\b\u0087\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\bHÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\bHÆ\u0003J\t\u0010/\u001a\u00020\bHÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u00101\u001a\u00020\u0011HÆ\u0003J\t\u00102\u001a\u00020\u0011HÆ\u0003J\u0094\u0001\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011HÆ\u0001¢\u0006\u0002\u00104J\u0013\u00105\u001a\u00020\u00112\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\u0005HÖ\u0001J\t\u00108\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0016\u0010\f\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0016\u0010\r\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0016\u0010\u000e\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010%R\u0016\u0010\u0012\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010%¨\u00069"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/BloodPressure;", "", "id", "", "queryID", "", "startTimestamp", "timeYearToDay", "", "diastolicBloodPressure", "systolicBloodPressure", "measureMode", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IJLjava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTimestamp", "()J", "getTimeYearToDay", "()Ljava/lang/String;", "getDiastolicBloodPressure", "getSystolicBloodPressure", "getMeasureMode", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/Long;IJLjava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/BloodPressure;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class BloodPressure {
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private final int diastolicBloodPressure;
    private final Long id;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private final int measureMode;
    private final int queryID;
    private final long startTimestamp;
    private final int systolicBloodPressure;
    private final String timeYearToDay;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getIsOtherUploaded() {
        return this.isOtherUploaded;
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
    public final int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    /* renamed from: component6, reason: from getter */
    public final int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    /* renamed from: component7, reason: from getter */
    public final int getMeasureMode() {
        return this.measureMode;
    }

    /* renamed from: component8, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component9, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    public final BloodPressure copy(Long id, int queryID, long startTimestamp, String timeYearToDay, int diastolicBloodPressure, int systolicBloodPressure, int measureMode, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new BloodPressure(id, queryID, startTimestamp, timeYearToDay, diastolicBloodPressure, systolicBloodPressure, measureMode, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodPressure)) {
            return false;
        }
        BloodPressure bloodPressure = (BloodPressure) other;
        return Intrinsics.areEqual(this.id, bloodPressure.id) && this.queryID == bloodPressure.queryID && this.startTimestamp == bloodPressure.startTimestamp && Intrinsics.areEqual(this.timeYearToDay, bloodPressure.timeYearToDay) && this.diastolicBloodPressure == bloodPressure.diastolicBloodPressure && this.systolicBloodPressure == bloodPressure.systolicBloodPressure && this.measureMode == bloodPressure.measureMode && Intrinsics.areEqual(this.userId, bloodPressure.userId) && Intrinsics.areEqual(this.deviceType, bloodPressure.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, bloodPressure.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, bloodPressure.dataGroupId) && this.isUploaded == bloodPressure.isUploaded && this.isOtherUploaded == bloodPressure.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.diastolicBloodPressure)) * 31) + Integer.hashCode(this.systolicBloodPressure)) * 31) + Integer.hashCode(this.measureMode)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "BloodPressure(id=" + this.id + ", queryID=" + this.queryID + ", startTimestamp=" + this.startTimestamp + ", timeYearToDay=" + this.timeYearToDay + ", diastolicBloodPressure=" + this.diastolicBloodPressure + ", systolicBloodPressure=" + this.systolicBloodPressure + ", measureMode=" + this.measureMode + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public BloodPressure(Long l, int i2, long j2, String timeYearToDay, int i3, int i4, int i5, String userId, String deviceType, String deviceMacAddress, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTimestamp = j2;
        this.timeYearToDay = timeYearToDay;
        this.diastolicBloodPressure = i3;
        this.systolicBloodPressure = i4;
        this.measureMode = i5;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isUploaded = z;
        this.isOtherUploaded = z2;
    }

    public /* synthetic */ BloodPressure(Long l, int i2, long j2, String str, int i3, int i4, int i5, String str2, String str3, String str4, String str5, boolean z, boolean z2, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? null : l, (i6 & 2) != 0 ? 0 : i2, j2, (i6 & 8) != 0 ? "" : str, i3, i4, i5, (i6 & 128) != 0 ? "" : str2, (i6 & 256) != 0 ? "" : str3, (i6 & 512) != 0 ? "" : str4, (i6 & 1024) != 0 ? null : str5, (i6 & 2048) != 0 ? false : z, (i6 & 4096) != 0 ? false : z2);
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

    public final int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    public final int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public final int getMeasureMode() {
        return this.measureMode;
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
