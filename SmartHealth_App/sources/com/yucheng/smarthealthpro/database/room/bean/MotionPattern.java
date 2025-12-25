package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MotionPattern.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\bB\b\u0087\b\u0018\u00002\u00020\u0001BÑ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\tHÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\t\u0010N\u001a\u00020\tHÆ\u0003J\t\u0010O\u001a\u00020\tHÆ\u0003J\t\u0010P\u001a\u00020\tHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010R\u001a\u00020\u0018HÆ\u0003J\t\u0010S\u001a\u00020\u0018HÆ\u0003JÚ\u0001\u0010T\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018HÆ\u0001¢\u0006\u0002\u0010UJ\u0013\u0010V\u001a\u00020\u00182\b\u0010W\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010X\u001a\u00020\u0005HÖ\u0001J\t\u0010Y\u001a\u00020\tHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\"\"\u0004\b$\u0010%R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010 \"\u0004\b)\u0010*R\u001e\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010 \"\u0004\b,\u0010*R\u001e\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010 \"\u0004\b.\u0010*R\u001e\u0010\r\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010 \"\u0004\b0\u0010*R\u001e\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010 \"\u0004\b2\u0010*R\u001e\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010 \"\u0004\b4\u0010*R\u001e\u0010\u0010\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\"\"\u0004\b6\u0010%R\u001e\u0010\u0011\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010 \"\u0004\b8\u0010*R\u001e\u0010\u0012\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010 \"\u0004\b:\u0010*R\u0016\u0010\u0013\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010'R\u0016\u0010\u0014\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010'R\u0016\u0010\u0015\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010'R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010'R\u0016\u0010\u0017\u001a\u00020\u00188\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010?R\u0016\u0010\u0019\u001a\u00020\u00188\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010?¨\u0006Z"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/MotionPattern;", "", "id", "", "queryID", "", "startTimestamp", "endTimestamp", "timeYearToDay", "", "sportSteps", "sportDistances", "sportCalories", "sportMode", "startMethod", "sportHeartRate", "sportDuration", "minHeartRate", "maxHeartRate", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IJJLjava/lang/String;IIIIIIJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTimestamp", "()J", "getEndTimestamp", "setEndTimestamp", "(J)V", "getTimeYearToDay", "()Ljava/lang/String;", "getSportSteps", "setSportSteps", "(I)V", "getSportDistances", "setSportDistances", "getSportCalories", "setSportCalories", "getSportMode", "setSportMode", "getStartMethod", "setStartMethod", "getSportHeartRate", "setSportHeartRate", "getSportDuration", "setSportDuration", "getMinHeartRate", "setMinHeartRate", "getMaxHeartRate", "setMaxHeartRate", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "(Ljava/lang/Long;IJJLjava/lang/String;IIIIIIJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/MotionPattern;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class MotionPattern {
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private long endTimestamp;
    private final Long id;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private int maxHeartRate;
    private int minHeartRate;
    private final int queryID;
    private int sportCalories;
    private int sportDistances;
    private long sportDuration;
    private int sportHeartRate;
    private int sportMode;
    private int sportSteps;
    private int startMethod;
    private final long startTimestamp;
    private final String timeYearToDay;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getStartMethod() {
        return this.startMethod;
    }

    /* renamed from: component11, reason: from getter */
    public final int getSportHeartRate() {
        return this.sportHeartRate;
    }

    /* renamed from: component12, reason: from getter */
    public final long getSportDuration() {
        return this.sportDuration;
    }

    /* renamed from: component13, reason: from getter */
    public final int getMinHeartRate() {
        return this.minHeartRate;
    }

    /* renamed from: component14, reason: from getter */
    public final int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    /* renamed from: component15, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component16, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component17, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component18, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component19, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQueryID() {
        return this.queryID;
    }

    /* renamed from: component20, reason: from getter */
    public final boolean getIsOtherUploaded() {
        return this.isOtherUploaded;
    }

    /* renamed from: component3, reason: from getter */
    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    /* renamed from: component4, reason: from getter */
    public final long getEndTimestamp() {
        return this.endTimestamp;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    /* renamed from: component6, reason: from getter */
    public final int getSportSteps() {
        return this.sportSteps;
    }

    /* renamed from: component7, reason: from getter */
    public final int getSportDistances() {
        return this.sportDistances;
    }

    /* renamed from: component8, reason: from getter */
    public final int getSportCalories() {
        return this.sportCalories;
    }

    /* renamed from: component9, reason: from getter */
    public final int getSportMode() {
        return this.sportMode;
    }

    public final MotionPattern copy(Long id, int queryID, long startTimestamp, long endTimestamp, String timeYearToDay, int sportSteps, int sportDistances, int sportCalories, int sportMode, int startMethod, int sportHeartRate, long sportDuration, int minHeartRate, int maxHeartRate, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new MotionPattern(id, queryID, startTimestamp, endTimestamp, timeYearToDay, sportSteps, sportDistances, sportCalories, sportMode, startMethod, sportHeartRate, sportDuration, minHeartRate, maxHeartRate, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MotionPattern)) {
            return false;
        }
        MotionPattern motionPattern = (MotionPattern) other;
        return Intrinsics.areEqual(this.id, motionPattern.id) && this.queryID == motionPattern.queryID && this.startTimestamp == motionPattern.startTimestamp && this.endTimestamp == motionPattern.endTimestamp && Intrinsics.areEqual(this.timeYearToDay, motionPattern.timeYearToDay) && this.sportSteps == motionPattern.sportSteps && this.sportDistances == motionPattern.sportDistances && this.sportCalories == motionPattern.sportCalories && this.sportMode == motionPattern.sportMode && this.startMethod == motionPattern.startMethod && this.sportHeartRate == motionPattern.sportHeartRate && this.sportDuration == motionPattern.sportDuration && this.minHeartRate == motionPattern.minHeartRate && this.maxHeartRate == motionPattern.maxHeartRate && Intrinsics.areEqual(this.userId, motionPattern.userId) && Intrinsics.areEqual(this.deviceType, motionPattern.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, motionPattern.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, motionPattern.dataGroupId) && this.isUploaded == motionPattern.isUploaded && this.isOtherUploaded == motionPattern.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + Long.hashCode(this.endTimestamp)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.sportSteps)) * 31) + Integer.hashCode(this.sportDistances)) * 31) + Integer.hashCode(this.sportCalories)) * 31) + Integer.hashCode(this.sportMode)) * 31) + Integer.hashCode(this.startMethod)) * 31) + Integer.hashCode(this.sportHeartRate)) * 31) + Long.hashCode(this.sportDuration)) * 31) + Integer.hashCode(this.minHeartRate)) * 31) + Integer.hashCode(this.maxHeartRate)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "MotionPattern(id=" + this.id + ", queryID=" + this.queryID + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ", timeYearToDay=" + this.timeYearToDay + ", sportSteps=" + this.sportSteps + ", sportDistances=" + this.sportDistances + ", sportCalories=" + this.sportCalories + ", sportMode=" + this.sportMode + ", startMethod=" + this.startMethod + ", sportHeartRate=" + this.sportHeartRate + ", sportDuration=" + this.sportDuration + ", minHeartRate=" + this.minHeartRate + ", maxHeartRate=" + this.maxHeartRate + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public MotionPattern(Long l, int i2, long j2, long j3, String timeYearToDay, int i3, int i4, int i5, int i6, int i7, int i8, long j4, int i9, int i10, String userId, String deviceType, String deviceMacAddress, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTimestamp = j2;
        this.endTimestamp = j3;
        this.timeYearToDay = timeYearToDay;
        this.sportSteps = i3;
        this.sportDistances = i4;
        this.sportCalories = i5;
        this.sportMode = i6;
        this.startMethod = i7;
        this.sportHeartRate = i8;
        this.sportDuration = j4;
        this.minHeartRate = i9;
        this.maxHeartRate = i10;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isUploaded = z;
        this.isOtherUploaded = z2;
    }

    public /* synthetic */ MotionPattern(Long l, int i2, long j2, long j3, String str, int i3, int i4, int i5, int i6, int i7, int i8, long j4, int i9, int i10, String str2, String str3, String str4, String str5, boolean z, boolean z2, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this((i11 & 1) != 0 ? null : l, (i11 & 2) != 0 ? 0 : i2, j2, (i11 & 8) != 0 ? 0L : j3, (i11 & 16) != 0 ? "" : str, (i11 & 32) != 0 ? 0 : i3, (i11 & 64) != 0 ? 0 : i4, (i11 & 128) != 0 ? 0 : i5, (i11 & 256) != 0 ? 0 : i6, (i11 & 512) != 0 ? 0 : i7, (i11 & 1024) != 0 ? 0 : i8, (i11 & 2048) != 0 ? 0L : j4, (i11 & 4096) != 0 ? 0 : i9, (i11 & 8192) != 0 ? 0 : i10, (i11 & 16384) != 0 ? "" : str2, (32768 & i11) != 0 ? "" : str3, (65536 & i11) != 0 ? "" : str4, (131072 & i11) != 0 ? null : str5, (262144 & i11) != 0 ? false : z, (i11 & 524288) != 0 ? false : z2);
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

    public final long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final void setEndTimestamp(long j2) {
        this.endTimestamp = j2;
    }

    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    public final int getSportSteps() {
        return this.sportSteps;
    }

    public final void setSportSteps(int i2) {
        this.sportSteps = i2;
    }

    public final int getSportDistances() {
        return this.sportDistances;
    }

    public final void setSportDistances(int i2) {
        this.sportDistances = i2;
    }

    public final int getSportCalories() {
        return this.sportCalories;
    }

    public final void setSportCalories(int i2) {
        this.sportCalories = i2;
    }

    public final int getSportMode() {
        return this.sportMode;
    }

    public final void setSportMode(int i2) {
        this.sportMode = i2;
    }

    public final int getStartMethod() {
        return this.startMethod;
    }

    public final void setStartMethod(int i2) {
        this.startMethod = i2;
    }

    public final int getSportHeartRate() {
        return this.sportHeartRate;
    }

    public final void setSportHeartRate(int i2) {
        this.sportHeartRate = i2;
    }

    public final long getSportDuration() {
        return this.sportDuration;
    }

    public final void setSportDuration(long j2) {
        this.sportDuration = j2;
    }

    public final int getMinHeartRate() {
        return this.minHeartRate;
    }

    public final void setMinHeartRate(int i2) {
        this.minHeartRate = i2;
    }

    public final int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public final void setMaxHeartRate(int i2) {
        this.maxHeartRate = i2;
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
