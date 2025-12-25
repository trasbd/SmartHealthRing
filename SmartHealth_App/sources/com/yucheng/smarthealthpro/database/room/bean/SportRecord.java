package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SportRecord.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\bO\b\u0087\b\u0018\u00002\u00020\u0001Bù\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\f\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0017\u001a\u00020\t\u0012\b\b\u0002\u0010\u0018\u001a\u00020\t\u0012\b\b\u0002\u0010\u0019\u001a\u00020\t\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\tHÆ\u0003J\t\u0010S\u001a\u00020\u0005HÆ\u0003J\t\u0010T\u001a\u00020\fHÆ\u0003J\t\u0010U\u001a\u00020\fHÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010Y\u001a\u00020\u0005HÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\fHÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010_\u001a\u00020\tHÆ\u0003J\t\u0010`\u001a\u00020\tHÆ\u0003J\t\u0010a\u001a\u00020\tHÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010c\u001a\u00020\u001cHÆ\u0003J\t\u0010d\u001a\u00020\u001cHÆ\u0003J\u0080\u0002\u0010e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0017\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001cHÆ\u0001¢\u0006\u0002\u0010fJ\u0013\u0010g\u001a\u00020\u001c2\b\u0010h\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010i\u001a\u00020\u0005HÖ\u0001J\t\u0010j\u001a\u00020\tHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010$\"\u0004\b&\u0010'R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010$\"\u0004\b/\u0010'R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001e\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00101\"\u0004\b5\u00103R\u001e\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010$\"\u0004\b7\u0010'R\u001e\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010$\"\u0004\b9\u0010'R \u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010-\"\u0004\b;\u0010<R\u001e\u0010\u0011\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010$\"\u0004\b>\u0010'R\u001e\u0010\u0012\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010$\"\u0004\b@\u0010'R\u001e\u0010\u0013\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u00101\"\u0004\bB\u00103R \u0010\u0014\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010-\"\u0004\bD\u0010<R \u0010\u0015\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010-\"\u0004\bF\u0010<R \u0010\u0016\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010-\"\u0004\bH\u0010<R\u0016\u0010\u0017\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010-R\u0016\u0010\u0018\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010-R\u0016\u0010\u0019\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010-R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010-R\u0016\u0010\u001b\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010MR\u0016\u0010\u001d\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010M¨\u0006k"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/SportRecord;", "", "id", "", "queryID", "", "type", "beginDate", "timeYearToDay", "", "totalSteps", "totalDistance", "", "lastDistance", "totalCalories", "lastCalories", "minkm", "heartRate", "runDuration", "kmh", "startPoint", "endPoint", "pathLinePoints", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IIJLjava/lang/String;IFFIILjava/lang/String;IIFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getType", "setType", "(I)V", "getBeginDate", "()J", "setBeginDate", "(J)V", "getTimeYearToDay", "()Ljava/lang/String;", "getTotalSteps", "setTotalSteps", "getTotalDistance", "()F", "setTotalDistance", "(F)V", "getLastDistance", "setLastDistance", "getTotalCalories", "setTotalCalories", "getLastCalories", "setLastCalories", "getMinkm", "setMinkm", "(Ljava/lang/String;)V", "getHeartRate", "setHeartRate", "getRunDuration", "setRunDuration", "getKmh", "setKmh", "getStartPoint", "setStartPoint", "getEndPoint", "setEndPoint", "getPathLinePoints", "setPathLinePoints", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "(Ljava/lang/Long;IIJLjava/lang/String;IFFIILjava/lang/String;IIFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/SportRecord;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class SportRecord {
    private long beginDate;
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private String endPoint;
    private int heartRate;
    private final Long id;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private float kmh;
    private int lastCalories;
    private float lastDistance;
    private String minkm;
    private String pathLinePoints;
    private final int queryID;
    private int runDuration;
    private String startPoint;
    private final String timeYearToDay;
    private int totalCalories;
    private float totalDistance;
    private int totalSteps;
    private int type;
    private final String userId;

    public SportRecord() {
        this(null, 0, 0, 0L, null, 0, 0.0f, 0.0f, 0, 0, null, 0, 0, 0.0f, null, null, null, null, null, null, null, false, false, 8388607, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getLastCalories() {
        return this.lastCalories;
    }

    /* renamed from: component11, reason: from getter */
    public final String getMinkm() {
        return this.minkm;
    }

    /* renamed from: component12, reason: from getter */
    public final int getHeartRate() {
        return this.heartRate;
    }

    /* renamed from: component13, reason: from getter */
    public final int getRunDuration() {
        return this.runDuration;
    }

    /* renamed from: component14, reason: from getter */
    public final float getKmh() {
        return this.kmh;
    }

    /* renamed from: component15, reason: from getter */
    public final String getStartPoint() {
        return this.startPoint;
    }

    /* renamed from: component16, reason: from getter */
    public final String getEndPoint() {
        return this.endPoint;
    }

    /* renamed from: component17, reason: from getter */
    public final String getPathLinePoints() {
        return this.pathLinePoints;
    }

    /* renamed from: component18, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component19, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQueryID() {
        return this.queryID;
    }

    /* renamed from: component20, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component21, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component22, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }

    /* renamed from: component23, reason: from getter */
    public final boolean getIsOtherUploaded() {
        return this.isOtherUploaded;
    }

    /* renamed from: component3, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final long getBeginDate() {
        return this.beginDate;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    /* renamed from: component6, reason: from getter */
    public final int getTotalSteps() {
        return this.totalSteps;
    }

    /* renamed from: component7, reason: from getter */
    public final float getTotalDistance() {
        return this.totalDistance;
    }

    /* renamed from: component8, reason: from getter */
    public final float getLastDistance() {
        return this.lastDistance;
    }

    /* renamed from: component9, reason: from getter */
    public final int getTotalCalories() {
        return this.totalCalories;
    }

    public final SportRecord copy(Long id, int queryID, int type, long beginDate, String timeYearToDay, int totalSteps, float totalDistance, float lastDistance, int totalCalories, int lastCalories, String minkm, int heartRate, int runDuration, float kmh, String startPoint, String endPoint, String pathLinePoints, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new SportRecord(id, queryID, type, beginDate, timeYearToDay, totalSteps, totalDistance, lastDistance, totalCalories, lastCalories, minkm, heartRate, runDuration, kmh, startPoint, endPoint, pathLinePoints, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SportRecord)) {
            return false;
        }
        SportRecord sportRecord = (SportRecord) other;
        return Intrinsics.areEqual(this.id, sportRecord.id) && this.queryID == sportRecord.queryID && this.type == sportRecord.type && this.beginDate == sportRecord.beginDate && Intrinsics.areEqual(this.timeYearToDay, sportRecord.timeYearToDay) && this.totalSteps == sportRecord.totalSteps && Float.compare(this.totalDistance, sportRecord.totalDistance) == 0 && Float.compare(this.lastDistance, sportRecord.lastDistance) == 0 && this.totalCalories == sportRecord.totalCalories && this.lastCalories == sportRecord.lastCalories && Intrinsics.areEqual(this.minkm, sportRecord.minkm) && this.heartRate == sportRecord.heartRate && this.runDuration == sportRecord.runDuration && Float.compare(this.kmh, sportRecord.kmh) == 0 && Intrinsics.areEqual(this.startPoint, sportRecord.startPoint) && Intrinsics.areEqual(this.endPoint, sportRecord.endPoint) && Intrinsics.areEqual(this.pathLinePoints, sportRecord.pathLinePoints) && Intrinsics.areEqual(this.userId, sportRecord.userId) && Intrinsics.areEqual(this.deviceType, sportRecord.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, sportRecord.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, sportRecord.dataGroupId) && this.isUploaded == sportRecord.isUploaded && this.isOtherUploaded == sportRecord.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Integer.hashCode(this.type)) * 31) + Long.hashCode(this.beginDate)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.totalSteps)) * 31) + Float.hashCode(this.totalDistance)) * 31) + Float.hashCode(this.lastDistance)) * 31) + Integer.hashCode(this.totalCalories)) * 31) + Integer.hashCode(this.lastCalories)) * 31;
        String str = this.minkm;
        int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.heartRate)) * 31) + Integer.hashCode(this.runDuration)) * 31) + Float.hashCode(this.kmh)) * 31;
        String str2 = this.startPoint;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.endPoint;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.pathLinePoints;
        int iHashCode5 = (((((((iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str5 = this.dataGroupId;
        return ((((iHashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "SportRecord(id=" + this.id + ", queryID=" + this.queryID + ", type=" + this.type + ", beginDate=" + this.beginDate + ", timeYearToDay=" + this.timeYearToDay + ", totalSteps=" + this.totalSteps + ", totalDistance=" + this.totalDistance + ", lastDistance=" + this.lastDistance + ", totalCalories=" + this.totalCalories + ", lastCalories=" + this.lastCalories + ", minkm=" + this.minkm + ", heartRate=" + this.heartRate + ", runDuration=" + this.runDuration + ", kmh=" + this.kmh + ", startPoint=" + this.startPoint + ", endPoint=" + this.endPoint + ", pathLinePoints=" + this.pathLinePoints + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public SportRecord(Long l, int i2, int i3, long j2, String timeYearToDay, int i4, float f2, float f3, int i5, int i6, String str, int i7, int i8, float f4, String str2, String str3, String str4, String userId, String deviceType, String deviceMacAddress, String str5, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.type = i3;
        this.beginDate = j2;
        this.timeYearToDay = timeYearToDay;
        this.totalSteps = i4;
        this.totalDistance = f2;
        this.lastDistance = f3;
        this.totalCalories = i5;
        this.lastCalories = i6;
        this.minkm = str;
        this.heartRate = i7;
        this.runDuration = i8;
        this.kmh = f4;
        this.startPoint = str2;
        this.endPoint = str3;
        this.pathLinePoints = str4;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str5;
        this.isUploaded = z;
        this.isOtherUploaded = z2;
    }

    public /* synthetic */ SportRecord(Long l, int i2, int i3, long j2, String str, int i4, float f2, float f3, int i5, int i6, String str2, int i7, int i8, float f4, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, boolean z2, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this((i9 & 1) != 0 ? null : l, (i9 & 2) != 0 ? 0 : i2, (i9 & 4) != 0 ? -1 : i3, (i9 & 8) != 0 ? 0L : j2, (i9 & 16) != 0 ? "" : str, (i9 & 32) != 0 ? 0 : i4, (i9 & 64) != 0 ? 0.0f : f2, (i9 & 128) != 0 ? 0.0f : f3, (i9 & 256) != 0 ? 0 : i5, (i9 & 512) != 0 ? 0 : i6, (i9 & 1024) != 0 ? null : str2, (i9 & 2048) != 0 ? 0 : i7, (i9 & 4096) != 0 ? 0 : i8, (i9 & 8192) != 0 ? 0.0f : f4, (i9 & 16384) != 0 ? null : str3, (i9 & 32768) != 0 ? null : str4, (i9 & 65536) != 0 ? null : str5, (i9 & 131072) != 0 ? "" : str6, (i9 & 262144) != 0 ? "" : str7, (i9 & 524288) != 0 ? "" : str8, (i9 & 1048576) != 0 ? null : str9, (i9 & 2097152) != 0 ? false : z, (i9 & 4194304) != 0 ? false : z2);
    }

    public final Long getId() {
        return this.id;
    }

    public final int getQueryID() {
        return this.queryID;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i2) {
        this.type = i2;
    }

    public final long getBeginDate() {
        return this.beginDate;
    }

    public final void setBeginDate(long j2) {
        this.beginDate = j2;
    }

    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    public final int getTotalSteps() {
        return this.totalSteps;
    }

    public final void setTotalSteps(int i2) {
        this.totalSteps = i2;
    }

    public final float getTotalDistance() {
        return this.totalDistance;
    }

    public final void setTotalDistance(float f2) {
        this.totalDistance = f2;
    }

    public final float getLastDistance() {
        return this.lastDistance;
    }

    public final void setLastDistance(float f2) {
        this.lastDistance = f2;
    }

    public final int getTotalCalories() {
        return this.totalCalories;
    }

    public final void setTotalCalories(int i2) {
        this.totalCalories = i2;
    }

    public final int getLastCalories() {
        return this.lastCalories;
    }

    public final void setLastCalories(int i2) {
        this.lastCalories = i2;
    }

    public final String getMinkm() {
        return this.minkm;
    }

    public final void setMinkm(String str) {
        this.minkm = str;
    }

    public final int getHeartRate() {
        return this.heartRate;
    }

    public final void setHeartRate(int i2) {
        this.heartRate = i2;
    }

    public final int getRunDuration() {
        return this.runDuration;
    }

    public final void setRunDuration(int i2) {
        this.runDuration = i2;
    }

    public final float getKmh() {
        return this.kmh;
    }

    public final void setKmh(float f2) {
        this.kmh = f2;
    }

    public final String getStartPoint() {
        return this.startPoint;
    }

    public final void setStartPoint(String str) {
        this.startPoint = str;
    }

    public final String getEndPoint() {
        return this.endPoint;
    }

    public final void setEndPoint(String str) {
        this.endPoint = str;
    }

    public final String getPathLinePoints() {
        return this.pathLinePoints;
    }

    public final void setPathLinePoints(String str) {
        this.pathLinePoints = str;
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
