package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EcgMeasure.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\bI\b\u0087\b\u0018\u00002\u00020\u0001BÓ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\b\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0011¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\bHÆ\u0003J\t\u0010D\u001a\u00020\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\bHÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0011HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010N\u001a\u00020\bHÆ\u0003J\t\u0010O\u001a\u00020\bHÆ\u0003J\t\u0010P\u001a\u00020\bHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010R\u001a\u00020\u0011HÆ\u0003J\t\u0010S\u001a\u00020\u0011HÆ\u0003JÜ\u0001\u0010T\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u0011HÆ\u0001¢\u0006\u0002\u0010UJ\u0013\u0010V\u001a\u00020\u00112\b\u0010W\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010X\u001a\u00020\u0005HÖ\u0001J\t\u0010Y\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001e\u0010\t\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010'R\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010 \"\u0004\b)\u0010'R\u001e\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010 \"\u0004\b+\u0010'R\u001e\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010 \"\u0004\b-\u0010'R\u001e\u0010\r\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010$\"\u0004\b/\u00100R\u001e\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010 \"\u0004\b2\u0010'R\u001e\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010 \"\u0004\b4\u0010'R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u00105\"\u0004\b6\u00107R\u001e\u0010\u0012\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010 \"\u0004\b9\u0010'R \u0010\u0013\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010$\"\u0004\b;\u00100R\u0016\u0010\u0014\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010$R\u0016\u0010\u0015\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010$R\u0016\u0010\u0016\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010$R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010$R\u0016\u0010\u0018\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u00105R\u0016\u0010\u0019\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u00105¨\u0006Z"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "", "id", "", "queryID", "", "startTimestamp", "timeYearToDay", "", "hrv", "heartRate", "maxBp", "minBp", "measureData", Constant.SpConstKey.AGE, Constant.SpConstKey.SEX, "isAfib", "", "diagnoseType", "healthNorm", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IJLjava/lang/String;IIIILjava/lang/String;IIZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTimestamp", "()J", "getTimeYearToDay", "()Ljava/lang/String;", "getHrv", "setHrv", "(I)V", "getHeartRate", "setHeartRate", "getMaxBp", "setMaxBp", "getMinBp", "setMinBp", "getMeasureData", "setMeasureData", "(Ljava/lang/String;)V", "getAge", "setAge", "getSex", "setSex", "()Z", "setAfib", "(Z)V", "getDiagnoseType", "setDiagnoseType", "getHealthNorm", "setHealthNorm", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "(Ljava/lang/Long;IJLjava/lang/String;IIIILjava/lang/String;IIZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class EcgMeasure {
    private int age;
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private int diagnoseType;
    private String healthNorm;
    private int heartRate;
    private int hrv;
    private final Long id;
    private boolean isAfib;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private int maxBp;
    private String measureData;
    private int minBp;
    private final int queryID;
    private int sex;
    private final long startTimestamp;
    private final String timeYearToDay;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getAge() {
        return this.age;
    }

    /* renamed from: component11, reason: from getter */
    public final int getSex() {
        return this.sex;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getIsAfib() {
        return this.isAfib;
    }

    /* renamed from: component13, reason: from getter */
    public final int getDiagnoseType() {
        return this.diagnoseType;
    }

    /* renamed from: component14, reason: from getter */
    public final String getHealthNorm() {
        return this.healthNorm;
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
    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    /* renamed from: component5, reason: from getter */
    public final int getHrv() {
        return this.hrv;
    }

    /* renamed from: component6, reason: from getter */
    public final int getHeartRate() {
        return this.heartRate;
    }

    /* renamed from: component7, reason: from getter */
    public final int getMaxBp() {
        return this.maxBp;
    }

    /* renamed from: component8, reason: from getter */
    public final int getMinBp() {
        return this.minBp;
    }

    /* renamed from: component9, reason: from getter */
    public final String getMeasureData() {
        return this.measureData;
    }

    public final EcgMeasure copy(Long id, int queryID, long startTimestamp, String timeYearToDay, int hrv, int heartRate, int maxBp, int minBp, String measureData, int age, int sex, boolean isAfib, int diagnoseType, String healthNorm, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(measureData, "measureData");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new EcgMeasure(id, queryID, startTimestamp, timeYearToDay, hrv, heartRate, maxBp, minBp, measureData, age, sex, isAfib, diagnoseType, healthNorm, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EcgMeasure)) {
            return false;
        }
        EcgMeasure ecgMeasure = (EcgMeasure) other;
        return Intrinsics.areEqual(this.id, ecgMeasure.id) && this.queryID == ecgMeasure.queryID && this.startTimestamp == ecgMeasure.startTimestamp && Intrinsics.areEqual(this.timeYearToDay, ecgMeasure.timeYearToDay) && this.hrv == ecgMeasure.hrv && this.heartRate == ecgMeasure.heartRate && this.maxBp == ecgMeasure.maxBp && this.minBp == ecgMeasure.minBp && Intrinsics.areEqual(this.measureData, ecgMeasure.measureData) && this.age == ecgMeasure.age && this.sex == ecgMeasure.sex && this.isAfib == ecgMeasure.isAfib && this.diagnoseType == ecgMeasure.diagnoseType && Intrinsics.areEqual(this.healthNorm, ecgMeasure.healthNorm) && Intrinsics.areEqual(this.userId, ecgMeasure.userId) && Intrinsics.areEqual(this.deviceType, ecgMeasure.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, ecgMeasure.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, ecgMeasure.dataGroupId) && this.isUploaded == ecgMeasure.isUploaded && this.isOtherUploaded == ecgMeasure.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.hrv)) * 31) + Integer.hashCode(this.heartRate)) * 31) + Integer.hashCode(this.maxBp)) * 31) + Integer.hashCode(this.minBp)) * 31) + this.measureData.hashCode()) * 31) + Integer.hashCode(this.age)) * 31) + Integer.hashCode(this.sex)) * 31) + Boolean.hashCode(this.isAfib)) * 31) + Integer.hashCode(this.diagnoseType)) * 31;
        String str = this.healthNorm;
        int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str2 = this.dataGroupId;
        return ((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "EcgMeasure(id=" + this.id + ", queryID=" + this.queryID + ", startTimestamp=" + this.startTimestamp + ", timeYearToDay=" + this.timeYearToDay + ", hrv=" + this.hrv + ", heartRate=" + this.heartRate + ", maxBp=" + this.maxBp + ", minBp=" + this.minBp + ", measureData=" + this.measureData + ", age=" + this.age + ", sex=" + this.sex + ", isAfib=" + this.isAfib + ", diagnoseType=" + this.diagnoseType + ", healthNorm=" + this.healthNorm + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public EcgMeasure(Long l, int i2, long j2, String timeYearToDay, int i3, int i4, int i5, int i6, String measureData, int i7, int i8, boolean z, int i9, String str, String userId, String deviceType, String deviceMacAddress, String str2, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(measureData, "measureData");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTimestamp = j2;
        this.timeYearToDay = timeYearToDay;
        this.hrv = i3;
        this.heartRate = i4;
        this.maxBp = i5;
        this.minBp = i6;
        this.measureData = measureData;
        this.age = i7;
        this.sex = i8;
        this.isAfib = z;
        this.diagnoseType = i9;
        this.healthNorm = str;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str2;
        this.isUploaded = z2;
        this.isOtherUploaded = z3;
    }

    public /* synthetic */ EcgMeasure(Long l, int i2, long j2, String str, int i3, int i4, int i5, int i6, String str2, int i7, int i8, boolean z, int i9, String str3, String str4, String str5, String str6, String str7, boolean z2, boolean z3, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : l, (i10 & 2) != 0 ? 0 : i2, j2, (i10 & 8) != 0 ? "" : str, (i10 & 16) != 0 ? 0 : i3, (i10 & 32) != 0 ? 0 : i4, (i10 & 64) != 0 ? 0 : i5, (i10 & 128) != 0 ? 0 : i6, (i10 & 256) != 0 ? "" : str2, (i10 & 512) != 0 ? 0 : i7, (i10 & 1024) != 0 ? 0 : i8, (i10 & 2048) != 0 ? false : z, (i10 & 4096) != 0 ? 0 : i9, (i10 & 8192) != 0 ? null : str3, (i10 & 16384) != 0 ? "" : str4, (32768 & i10) != 0 ? "" : str5, (65536 & i10) != 0 ? "" : str6, (131072 & i10) != 0 ? null : str7, (262144 & i10) != 0 ? false : z2, (i10 & 524288) != 0 ? false : z3);
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

    public final int getHrv() {
        return this.hrv;
    }

    public final void setHrv(int i2) {
        this.hrv = i2;
    }

    public final int getHeartRate() {
        return this.heartRate;
    }

    public final void setHeartRate(int i2) {
        this.heartRate = i2;
    }

    public final int getMaxBp() {
        return this.maxBp;
    }

    public final void setMaxBp(int i2) {
        this.maxBp = i2;
    }

    public final int getMinBp() {
        return this.minBp;
    }

    public final void setMinBp(int i2) {
        this.minBp = i2;
    }

    public final String getMeasureData() {
        return this.measureData;
    }

    public final void setMeasureData(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.measureData = str;
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int i2) {
        this.age = i2;
    }

    public final int getSex() {
        return this.sex;
    }

    public final void setSex(int i2) {
        this.sex = i2;
    }

    public final boolean isAfib() {
        return this.isAfib;
    }

    public final void setAfib(boolean z) {
        this.isAfib = z;
    }

    public final int getDiagnoseType() {
        return this.diagnoseType;
    }

    public final void setDiagnoseType(int i2) {
        this.diagnoseType = i2;
    }

    public final String getHealthNorm() {
        return this.healthNorm;
    }

    public final void setHealthNorm(String str) {
        this.healthNorm = str;
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
