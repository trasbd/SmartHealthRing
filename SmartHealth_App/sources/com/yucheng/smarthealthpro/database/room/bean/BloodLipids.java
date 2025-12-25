package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BloodLipids.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\u0007\n\u0002\b\"\b\u0087\b\u0018\u00002\u00020\u0001Bµ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\bHÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\bHÆ\u0003J\t\u0010J\u001a\u00020\bHÆ\u0003J\t\u0010K\u001a\u00020\bHÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010M\u001a\u00020\u0017HÆ\u0003J\t\u0010N\u001a\u00020\u0017HÆ\u0003JÐ\u0001\u0010O\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0017HÆ\u0001¢\u0006\u0002\u0010PJ\u0013\u0010Q\u001a\u00020\u00172\b\u0010R\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010S\u001a\u00020\u0005HÖ\u0001J\t\u0010T\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0016\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0016\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0016\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0016\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0016\u0010\u0012\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0016\u0010\u0013\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0016\u0010\u0014\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010#R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0016\u0010\u0016\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u00101R\u0016\u0010\u0018\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u00101R\u0011\u00102\u001a\u0002038F¢\u0006\u0006\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u0002038F¢\u0006\u0006\u001a\u0004\b7\u00105R\u0011\u00108\u001a\u0002038F¢\u0006\u0006\u001a\u0004\b9\u00105R\u0011\u0010:\u001a\u0002038F¢\u0006\u0006\u001a\u0004\b;\u00105¨\u0006U"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/BloodLipids;", "", "id", "", "queryID", "", "startTimestamp", "timeYearToDay", "", "cholesterolInteger", "cholesterolFraction", "triglycerideInteger", "triglycerideFraction", "highLipoproteinCholesterolInteger", "highLipoproteinCholesterolFraction", "lowLipoproteinCholesterolInteger", "lowLipoproteinCholesterolFraction", "measureMode", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IJLjava/lang/String;IIIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTimestamp", "()J", "getTimeYearToDay", "()Ljava/lang/String;", "getCholesterolInteger", "getCholesterolFraction", "getTriglycerideInteger", "getTriglycerideFraction", "getHighLipoproteinCholesterolInteger", "getHighLipoproteinCholesterolFraction", "getLowLipoproteinCholesterolInteger", "getLowLipoproteinCholesterolFraction", "getMeasureMode", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "cholesterol", "", "getCholesterol", "()F", "triglyceride", "getTriglyceride", "highLipoproteinCholesterol", "getHighLipoproteinCholesterol", "lowLipoproteinCholesterol", "getLowLipoproteinCholesterol", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(Ljava/lang/Long;IJLjava/lang/String;IIIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/BloodLipids;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class BloodLipids {
    private final int cholesterolFraction;
    private final int cholesterolInteger;
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private final int highLipoproteinCholesterolFraction;
    private final int highLipoproteinCholesterolInteger;
    private final Long id;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private final int lowLipoproteinCholesterolFraction;
    private final int lowLipoproteinCholesterolInteger;
    private final int measureMode;
    private final int queryID;
    private final long startTimestamp;
    private final String timeYearToDay;
    private final int triglycerideFraction;
    private final int triglycerideInteger;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getHighLipoproteinCholesterolFraction() {
        return this.highLipoproteinCholesterolFraction;
    }

    /* renamed from: component11, reason: from getter */
    public final int getLowLipoproteinCholesterolInteger() {
        return this.lowLipoproteinCholesterolInteger;
    }

    /* renamed from: component12, reason: from getter */
    public final int getLowLipoproteinCholesterolFraction() {
        return this.lowLipoproteinCholesterolFraction;
    }

    /* renamed from: component13, reason: from getter */
    public final int getMeasureMode() {
        return this.measureMode;
    }

    /* renamed from: component14, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component15, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component16, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component17, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component18, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }

    /* renamed from: component19, reason: from getter */
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
    public final int getCholesterolInteger() {
        return this.cholesterolInteger;
    }

    /* renamed from: component6, reason: from getter */
    public final int getCholesterolFraction() {
        return this.cholesterolFraction;
    }

    /* renamed from: component7, reason: from getter */
    public final int getTriglycerideInteger() {
        return this.triglycerideInteger;
    }

    /* renamed from: component8, reason: from getter */
    public final int getTriglycerideFraction() {
        return this.triglycerideFraction;
    }

    /* renamed from: component9, reason: from getter */
    public final int getHighLipoproteinCholesterolInteger() {
        return this.highLipoproteinCholesterolInteger;
    }

    public final BloodLipids copy(Long id, int queryID, long startTimestamp, String timeYearToDay, int cholesterolInteger, int cholesterolFraction, int triglycerideInteger, int triglycerideFraction, int highLipoproteinCholesterolInteger, int highLipoproteinCholesterolFraction, int lowLipoproteinCholesterolInteger, int lowLipoproteinCholesterolFraction, int measureMode, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new BloodLipids(id, queryID, startTimestamp, timeYearToDay, cholesterolInteger, cholesterolFraction, triglycerideInteger, triglycerideFraction, highLipoproteinCholesterolInteger, highLipoproteinCholesterolFraction, lowLipoproteinCholesterolInteger, lowLipoproteinCholesterolFraction, measureMode, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodLipids)) {
            return false;
        }
        BloodLipids bloodLipids = (BloodLipids) other;
        return Intrinsics.areEqual(this.id, bloodLipids.id) && this.queryID == bloodLipids.queryID && this.startTimestamp == bloodLipids.startTimestamp && Intrinsics.areEqual(this.timeYearToDay, bloodLipids.timeYearToDay) && this.cholesterolInteger == bloodLipids.cholesterolInteger && this.cholesterolFraction == bloodLipids.cholesterolFraction && this.triglycerideInteger == bloodLipids.triglycerideInteger && this.triglycerideFraction == bloodLipids.triglycerideFraction && this.highLipoproteinCholesterolInteger == bloodLipids.highLipoproteinCholesterolInteger && this.highLipoproteinCholesterolFraction == bloodLipids.highLipoproteinCholesterolFraction && this.lowLipoproteinCholesterolInteger == bloodLipids.lowLipoproteinCholesterolInteger && this.lowLipoproteinCholesterolFraction == bloodLipids.lowLipoproteinCholesterolFraction && this.measureMode == bloodLipids.measureMode && Intrinsics.areEqual(this.userId, bloodLipids.userId) && Intrinsics.areEqual(this.deviceType, bloodLipids.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, bloodLipids.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, bloodLipids.dataGroupId) && this.isUploaded == bloodLipids.isUploaded && this.isOtherUploaded == bloodLipids.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.cholesterolInteger)) * 31) + Integer.hashCode(this.cholesterolFraction)) * 31) + Integer.hashCode(this.triglycerideInteger)) * 31) + Integer.hashCode(this.triglycerideFraction)) * 31) + Integer.hashCode(this.highLipoproteinCholesterolInteger)) * 31) + Integer.hashCode(this.highLipoproteinCholesterolFraction)) * 31) + Integer.hashCode(this.lowLipoproteinCholesterolInteger)) * 31) + Integer.hashCode(this.lowLipoproteinCholesterolFraction)) * 31) + Integer.hashCode(this.measureMode)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "BloodLipids(id=" + this.id + ", queryID=" + this.queryID + ", startTimestamp=" + this.startTimestamp + ", timeYearToDay=" + this.timeYearToDay + ", cholesterolInteger=" + this.cholesterolInteger + ", cholesterolFraction=" + this.cholesterolFraction + ", triglycerideInteger=" + this.triglycerideInteger + ", triglycerideFraction=" + this.triglycerideFraction + ", highLipoproteinCholesterolInteger=" + this.highLipoproteinCholesterolInteger + ", highLipoproteinCholesterolFraction=" + this.highLipoproteinCholesterolFraction + ", lowLipoproteinCholesterolInteger=" + this.lowLipoproteinCholesterolInteger + ", lowLipoproteinCholesterolFraction=" + this.lowLipoproteinCholesterolFraction + ", measureMode=" + this.measureMode + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public BloodLipids(Long l, int i2, long j2, String timeYearToDay, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, String userId, String deviceType, String deviceMacAddress, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTimestamp = j2;
        this.timeYearToDay = timeYearToDay;
        this.cholesterolInteger = i3;
        this.cholesterolFraction = i4;
        this.triglycerideInteger = i5;
        this.triglycerideFraction = i6;
        this.highLipoproteinCholesterolInteger = i7;
        this.highLipoproteinCholesterolFraction = i8;
        this.lowLipoproteinCholesterolInteger = i9;
        this.lowLipoproteinCholesterolFraction = i10;
        this.measureMode = i11;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isUploaded = z;
        this.isOtherUploaded = z2;
    }

    public /* synthetic */ BloodLipids(Long l, int i2, long j2, String str, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, String str2, String str3, String str4, String str5, boolean z, boolean z2, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this((i12 & 1) != 0 ? null : l, (i12 & 2) != 0 ? 0 : i2, j2, (i12 & 8) != 0 ? "" : str, i3, i4, i5, i6, i7, i8, i9, i10, i11, (i12 & 8192) != 0 ? "" : str2, (i12 & 16384) != 0 ? "" : str3, (32768 & i12) != 0 ? "" : str4, (65536 & i12) != 0 ? null : str5, (131072 & i12) != 0 ? false : z, (i12 & 262144) != 0 ? false : z2);
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

    public final int getCholesterolInteger() {
        return this.cholesterolInteger;
    }

    public final int getCholesterolFraction() {
        return this.cholesterolFraction;
    }

    public final int getTriglycerideInteger() {
        return this.triglycerideInteger;
    }

    public final int getTriglycerideFraction() {
        return this.triglycerideFraction;
    }

    public final int getHighLipoproteinCholesterolInteger() {
        return this.highLipoproteinCholesterolInteger;
    }

    public final int getHighLipoproteinCholesterolFraction() {
        return this.highLipoproteinCholesterolFraction;
    }

    public final int getLowLipoproteinCholesterolInteger() {
        return this.lowLipoproteinCholesterolInteger;
    }

    public final int getLowLipoproteinCholesterolFraction() {
        return this.lowLipoproteinCholesterolFraction;
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

    public final float getCholesterol() {
        return Float.parseFloat(this.cholesterolInteger + "." + StringsKt.padStart(String.valueOf(this.cholesterolFraction), 2, '0'));
    }

    public final float getTriglyceride() {
        return Float.parseFloat(this.triglycerideInteger + "." + this.triglycerideFraction);
    }

    public final float getHighLipoproteinCholesterol() {
        return Float.parseFloat(this.highLipoproteinCholesterolInteger + "." + this.highLipoproteinCholesterolFraction);
    }

    public final float getLowLipoproteinCholesterol() {
        return Float.parseFloat(this.lowLipoproteinCholesterolInteger + "." + this.lowLipoproteinCholesterolFraction);
    }
}
