package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BodyData.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\u0007\n\u0002\b+\b\u0087\b\u0018\u00002\u00020\u0001BÍ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\b\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H\u0002J\u0006\u0010G\u001a\u00020\u0005J\u0010\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\bHÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\t\u0010Q\u001a\u00020\u0005HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0005HÆ\u0003J\t\u0010T\u001a\u00020\u0005HÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\t\u0010X\u001a\u00020\bHÆ\u0003J\t\u0010Y\u001a\u00020\bHÆ\u0003J\t\u0010Z\u001a\u00020\bHÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010\\\u001a\u00020\u001aHÆ\u0003J\t\u0010]\u001a\u00020\u001aHÆ\u0003Jî\u0001\u0010^\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001aHÆ\u0001¢\u0006\u0002\u0010_J\u0013\u0010`\u001a\u00020\u001a2\b\u0010a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010b\u001a\u00020\u0005HÖ\u0001J\t\u0010c\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\"R\u0016\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0016\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0016\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0016\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\"R\u0016\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\"R\u0016\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\"R\u0016\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\"R\u0016\u0010\u0015\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010&R\u0016\u0010\u0016\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010&R\u0016\u0010\u0017\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010&R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010&R\u0016\u0010\u0019\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u00107R\u0016\u0010\u001b\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u00107R\u0011\u00108\u001a\u0002098F¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u0002098F¢\u0006\u0006\u001a\u0004\b=\u0010;R\u0011\u0010>\u001a\u0002098F¢\u0006\u0006\u001a\u0004\b?\u0010;R\u0011\u0010@\u001a\u0002098F¢\u0006\u0006\u001a\u0004\bA\u0010;R\u0011\u0010B\u001a\u0002098F¢\u0006\u0006\u001a\u0004\bC\u0010;¨\u0006d"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/BodyData;", "", "id", "", "queryID", "", "startTimestamp", "timeYearToDay", "", "loadIndexInteger", "loadIndexFraction", "hrvInteger", "hrvFraction", "pressureInteger", "pressureFraction", "bodyStateInteger", "bodyStateFraction", "sympatheticInteger", "sympatheticFraction", "sdn", "maximalOxygenIntake", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IJLjava/lang/String;IIIIIIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTimestamp", "()J", "getTimeYearToDay", "()Ljava/lang/String;", "getLoadIndexInteger", "getLoadIndexFraction", "getHrvInteger", "getHrvFraction", "getPressureInteger", "getPressureFraction", "getBodyStateInteger", "getBodyStateFraction", "getSympatheticInteger", "getSympatheticFraction", "getSdn", "getMaximalOxygenIntake", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "loadIndex", "", "getLoadIndex", "()F", "hrvValue", "getHrvValue", "pressureValue", "getPressureValue", "bodyStateValue", "getBodyStateValue", "sympatheticValue", "getSympatheticValue", "calculateCompositeValue", "integerPart", "fractionalPart", "getCompositePressure", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "copy", "(Ljava/lang/Long;IJLjava/lang/String;IIIIIIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/BodyData;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class BodyData {
    private final int bodyStateFraction;
    private final int bodyStateInteger;
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private final int hrvFraction;
    private final int hrvInteger;
    private final Long id;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private final int loadIndexFraction;
    private final int loadIndexInteger;
    private final int maximalOxygenIntake;
    private final int pressureFraction;
    private final int pressureInteger;
    private final int queryID;
    private final int sdn;
    private final long startTimestamp;
    private final int sympatheticFraction;
    private final int sympatheticInteger;
    private final String timeYearToDay;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getPressureFraction() {
        return this.pressureFraction;
    }

    /* renamed from: component11, reason: from getter */
    public final int getBodyStateInteger() {
        return this.bodyStateInteger;
    }

    /* renamed from: component12, reason: from getter */
    public final int getBodyStateFraction() {
        return this.bodyStateFraction;
    }

    /* renamed from: component13, reason: from getter */
    public final int getSympatheticInteger() {
        return this.sympatheticInteger;
    }

    /* renamed from: component14, reason: from getter */
    public final int getSympatheticFraction() {
        return this.sympatheticFraction;
    }

    /* renamed from: component15, reason: from getter */
    public final int getSdn() {
        return this.sdn;
    }

    /* renamed from: component16, reason: from getter */
    public final int getMaximalOxygenIntake() {
        return this.maximalOxygenIntake;
    }

    /* renamed from: component17, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component18, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component19, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQueryID() {
        return this.queryID;
    }

    /* renamed from: component20, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component21, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }

    /* renamed from: component22, reason: from getter */
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
    public final int getLoadIndexInteger() {
        return this.loadIndexInteger;
    }

    /* renamed from: component6, reason: from getter */
    public final int getLoadIndexFraction() {
        return this.loadIndexFraction;
    }

    /* renamed from: component7, reason: from getter */
    public final int getHrvInteger() {
        return this.hrvInteger;
    }

    /* renamed from: component8, reason: from getter */
    public final int getHrvFraction() {
        return this.hrvFraction;
    }

    /* renamed from: component9, reason: from getter */
    public final int getPressureInteger() {
        return this.pressureInteger;
    }

    public final BodyData copy(Long id, int queryID, long startTimestamp, String timeYearToDay, int loadIndexInteger, int loadIndexFraction, int hrvInteger, int hrvFraction, int pressureInteger, int pressureFraction, int bodyStateInteger, int bodyStateFraction, int sympatheticInteger, int sympatheticFraction, int sdn, int maximalOxygenIntake, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new BodyData(id, queryID, startTimestamp, timeYearToDay, loadIndexInteger, loadIndexFraction, hrvInteger, hrvFraction, pressureInteger, pressureFraction, bodyStateInteger, bodyStateFraction, sympatheticInteger, sympatheticFraction, sdn, maximalOxygenIntake, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BodyData)) {
            return false;
        }
        BodyData bodyData = (BodyData) other;
        return Intrinsics.areEqual(this.id, bodyData.id) && this.queryID == bodyData.queryID && this.startTimestamp == bodyData.startTimestamp && Intrinsics.areEqual(this.timeYearToDay, bodyData.timeYearToDay) && this.loadIndexInteger == bodyData.loadIndexInteger && this.loadIndexFraction == bodyData.loadIndexFraction && this.hrvInteger == bodyData.hrvInteger && this.hrvFraction == bodyData.hrvFraction && this.pressureInteger == bodyData.pressureInteger && this.pressureFraction == bodyData.pressureFraction && this.bodyStateInteger == bodyData.bodyStateInteger && this.bodyStateFraction == bodyData.bodyStateFraction && this.sympatheticInteger == bodyData.sympatheticInteger && this.sympatheticFraction == bodyData.sympatheticFraction && this.sdn == bodyData.sdn && this.maximalOxygenIntake == bodyData.maximalOxygenIntake && Intrinsics.areEqual(this.userId, bodyData.userId) && Intrinsics.areEqual(this.deviceType, bodyData.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, bodyData.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, bodyData.dataGroupId) && this.isUploaded == bodyData.isUploaded && this.isOtherUploaded == bodyData.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.loadIndexInteger)) * 31) + Integer.hashCode(this.loadIndexFraction)) * 31) + Integer.hashCode(this.hrvInteger)) * 31) + Integer.hashCode(this.hrvFraction)) * 31) + Integer.hashCode(this.pressureInteger)) * 31) + Integer.hashCode(this.pressureFraction)) * 31) + Integer.hashCode(this.bodyStateInteger)) * 31) + Integer.hashCode(this.bodyStateFraction)) * 31) + Integer.hashCode(this.sympatheticInteger)) * 31) + Integer.hashCode(this.sympatheticFraction)) * 31) + Integer.hashCode(this.sdn)) * 31) + Integer.hashCode(this.maximalOxygenIntake)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "BodyData(id=" + this.id + ", queryID=" + this.queryID + ", startTimestamp=" + this.startTimestamp + ", timeYearToDay=" + this.timeYearToDay + ", loadIndexInteger=" + this.loadIndexInteger + ", loadIndexFraction=" + this.loadIndexFraction + ", hrvInteger=" + this.hrvInteger + ", hrvFraction=" + this.hrvFraction + ", pressureInteger=" + this.pressureInteger + ", pressureFraction=" + this.pressureFraction + ", bodyStateInteger=" + this.bodyStateInteger + ", bodyStateFraction=" + this.bodyStateFraction + ", sympatheticInteger=" + this.sympatheticInteger + ", sympatheticFraction=" + this.sympatheticFraction + ", sdn=" + this.sdn + ", maximalOxygenIntake=" + this.maximalOxygenIntake + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public BodyData(Long l, int i2, long j2, String timeYearToDay, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, String userId, String deviceType, String deviceMacAddress, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTimestamp = j2;
        this.timeYearToDay = timeYearToDay;
        this.loadIndexInteger = i3;
        this.loadIndexFraction = i4;
        this.hrvInteger = i5;
        this.hrvFraction = i6;
        this.pressureInteger = i7;
        this.pressureFraction = i8;
        this.bodyStateInteger = i9;
        this.bodyStateFraction = i10;
        this.sympatheticInteger = i11;
        this.sympatheticFraction = i12;
        this.sdn = i13;
        this.maximalOxygenIntake = i14;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isUploaded = z;
        this.isOtherUploaded = z2;
    }

    public /* synthetic */ BodyData(Long l, int i2, long j2, String str, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, String str2, String str3, String str4, String str5, boolean z, boolean z2, int i15, DefaultConstructorMarker defaultConstructorMarker) {
        this((i15 & 1) != 0 ? null : l, (i15 & 2) != 0 ? 0 : i2, j2, (i15 & 8) != 0 ? "" : str, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, (i15 & 65536) != 0 ? "" : str2, (i15 & 131072) != 0 ? "" : str3, (i15 & 262144) != 0 ? "" : str4, (i15 & 524288) != 0 ? null : str5, (i15 & 1048576) != 0 ? false : z, (i15 & 2097152) != 0 ? false : z2);
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

    public final int getLoadIndexInteger() {
        return this.loadIndexInteger;
    }

    public final int getLoadIndexFraction() {
        return this.loadIndexFraction;
    }

    public final int getHrvInteger() {
        return this.hrvInteger;
    }

    public final int getHrvFraction() {
        return this.hrvFraction;
    }

    public final int getPressureInteger() {
        return this.pressureInteger;
    }

    public final int getPressureFraction() {
        return this.pressureFraction;
    }

    public final int getBodyStateInteger() {
        return this.bodyStateInteger;
    }

    public final int getBodyStateFraction() {
        return this.bodyStateFraction;
    }

    public final int getSympatheticInteger() {
        return this.sympatheticInteger;
    }

    public final int getSympatheticFraction() {
        return this.sympatheticFraction;
    }

    public final int getSdn() {
        return this.sdn;
    }

    public final int getMaximalOxygenIntake() {
        return this.maximalOxygenIntake;
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

    public final float getLoadIndex() {
        return calculateCompositeValue(this.loadIndexInteger, this.loadIndexFraction);
    }

    public final float getHrvValue() {
        return calculateCompositeValue(this.hrvInteger, this.hrvFraction);
    }

    public final float getPressureValue() {
        return calculateCompositeValue(this.pressureInteger, this.pressureFraction);
    }

    public final float getBodyStateValue() {
        return calculateCompositeValue(this.bodyStateInteger, this.bodyStateFraction);
    }

    public final float getSympatheticValue() {
        return calculateCompositeValue(this.sympatheticInteger, this.sympatheticFraction);
    }

    private final float calculateCompositeValue(int integerPart, int fractionalPart) {
        return Float.parseFloat(integerPart + "." + fractionalPart);
    }

    public final int getCompositePressure() {
        try {
            int i2 = this.pressureInteger;
            return Integer.parseInt(new StringBuilder().append(i2).append(this.pressureFraction).toString());
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
