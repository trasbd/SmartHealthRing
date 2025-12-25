package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Sleep.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\bB\b\u0087\b\u0018\u00002\u00020\u0001BÑ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\b\b\u0002\u0010\u0016\u001a\u00020\t\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\tHÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\u0011\u0010N\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012HÆ\u0003J\t\u0010O\u001a\u00020\tHÆ\u0003J\t\u0010P\u001a\u00020\tHÆ\u0003J\t\u0010Q\u001a\u00020\tHÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010S\u001a\u00020\u0019HÆ\u0003J\t\u0010T\u001a\u00020\u0019HÆ\u0003JØ\u0001\u0010U\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\b\b\u0002\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\t2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0019HÆ\u0001¢\u0006\u0002\u0010VJ\u0013\u0010W\u001a\u00020\u00192\b\u0010X\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010Y\u001a\u00020\u0005HÖ\u0001J\t\u0010Z\u001a\u00020\tHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010#\"\u0004\b'\u0010%R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010!\"\u0004\b+\u0010,R\u001e\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010!\"\u0004\b.\u0010,R\u001e\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010!\"\u0004\b0\u0010,R\u001e\u0010\r\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010!\"\u0004\b2\u0010,R\u001e\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010!\"\u0004\b4\u0010,R\u001e\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010!\"\u0004\b6\u0010,R\u001e\u0010\u0010\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010!\"\u0004\b8\u0010,R&\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0016\u0010\u0014\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010)R\u0016\u0010\u0015\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010)R\u0016\u0010\u0016\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010)R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010)R\u0016\u0010\u0018\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010AR\u0016\u0010\u001a\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010A¨\u0006["}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/Sleep;", "", "id", "", "queryID", "", "startTime", "endTime", "timeYearToDay", "", "deepSleepCount", "lightSleepCount", "deepSleepTotal", "lightSleepTotal", "remTotal", "wakeCount", "wakeDuration", "sleepItems", "", "Lcom/yucheng/smarthealthpro/database/room/bean/SleepItem;", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isUploaded", "", "isOtherUploaded", "<init>", "(Ljava/lang/Long;IJJLjava/lang/String;IIIIIIILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTime", "()J", "setStartTime", "(J)V", "getEndTime", "setEndTime", "getTimeYearToDay", "()Ljava/lang/String;", "getDeepSleepCount", "setDeepSleepCount", "(I)V", "getLightSleepCount", "setLightSleepCount", "getDeepSleepTotal", "setDeepSleepTotal", "getLightSleepTotal", "setLightSleepTotal", "getRemTotal", "setRemTotal", "getWakeCount", "setWakeCount", "getWakeDuration", "setWakeDuration", "getSleepItems", "()Ljava/util/List;", "setSleepItems", "(Ljava/util/List;)V", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(Ljava/lang/Long;IJJLjava/lang/String;IIIIIIILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/yucheng/smarthealthpro/database/room/bean/Sleep;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class Sleep {
    private final String dataGroupId;
    private int deepSleepCount;
    private int deepSleepTotal;
    private final String deviceMacAddress;
    private final String deviceType;
    private long endTime;
    private final Long id;
    private final boolean isOtherUploaded;
    private final boolean isUploaded;
    private int lightSleepCount;
    private int lightSleepTotal;
    private final int queryID;
    private int remTotal;
    private List<SleepItem> sleepItems;
    private long startTime;
    private final String timeYearToDay;
    private final String userId;
    private int wakeCount;
    private int wakeDuration;

    public Sleep() {
        this(null, 0, 0L, 0L, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, false, false, 524287, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getRemTotal() {
        return this.remTotal;
    }

    /* renamed from: component11, reason: from getter */
    public final int getWakeCount() {
        return this.wakeCount;
    }

    /* renamed from: component12, reason: from getter */
    public final int getWakeDuration() {
        return this.wakeDuration;
    }

    public final List<SleepItem> component13() {
        return this.sleepItems;
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
    public final int getDeepSleepCount() {
        return this.deepSleepCount;
    }

    /* renamed from: component7, reason: from getter */
    public final int getLightSleepCount() {
        return this.lightSleepCount;
    }

    /* renamed from: component8, reason: from getter */
    public final int getDeepSleepTotal() {
        return this.deepSleepTotal;
    }

    /* renamed from: component9, reason: from getter */
    public final int getLightSleepTotal() {
        return this.lightSleepTotal;
    }

    public final Sleep copy(Long id, int queryID, long startTime, long endTime, String timeYearToDay, int deepSleepCount, int lightSleepCount, int deepSleepTotal, int lightSleepTotal, int remTotal, int wakeCount, int wakeDuration, List<SleepItem> sleepItems, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isUploaded, boolean isOtherUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new Sleep(id, queryID, startTime, endTime, timeYearToDay, deepSleepCount, lightSleepCount, deepSleepTotal, lightSleepTotal, remTotal, wakeCount, wakeDuration, sleepItems, userId, deviceType, deviceMacAddress, dataGroupId, isUploaded, isOtherUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Sleep)) {
            return false;
        }
        Sleep sleep = (Sleep) other;
        return Intrinsics.areEqual(this.id, sleep.id) && this.queryID == sleep.queryID && this.startTime == sleep.startTime && this.endTime == sleep.endTime && Intrinsics.areEqual(this.timeYearToDay, sleep.timeYearToDay) && this.deepSleepCount == sleep.deepSleepCount && this.lightSleepCount == sleep.lightSleepCount && this.deepSleepTotal == sleep.deepSleepTotal && this.lightSleepTotal == sleep.lightSleepTotal && this.remTotal == sleep.remTotal && this.wakeCount == sleep.wakeCount && this.wakeDuration == sleep.wakeDuration && Intrinsics.areEqual(this.sleepItems, sleep.sleepItems) && Intrinsics.areEqual(this.userId, sleep.userId) && Intrinsics.areEqual(this.deviceType, sleep.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, sleep.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, sleep.dataGroupId) && this.isUploaded == sleep.isUploaded && this.isOtherUploaded == sleep.isOtherUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTime)) * 31) + Long.hashCode(this.endTime)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.deepSleepCount)) * 31) + Integer.hashCode(this.lightSleepCount)) * 31) + Integer.hashCode(this.deepSleepTotal)) * 31) + Integer.hashCode(this.lightSleepTotal)) * 31) + Integer.hashCode(this.remTotal)) * 31) + Integer.hashCode(this.wakeCount)) * 31) + Integer.hashCode(this.wakeDuration)) * 31;
        List<SleepItem> list = this.sleepItems;
        int iHashCode2 = (((((((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isUploaded)) * 31) + Boolean.hashCode(this.isOtherUploaded);
    }

    public String toString() {
        return "Sleep(id=" + this.id + ", queryID=" + this.queryID + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", timeYearToDay=" + this.timeYearToDay + ", deepSleepCount=" + this.deepSleepCount + ", lightSleepCount=" + this.lightSleepCount + ", deepSleepTotal=" + this.deepSleepTotal + ", lightSleepTotal=" + this.lightSleepTotal + ", remTotal=" + this.remTotal + ", wakeCount=" + this.wakeCount + ", wakeDuration=" + this.wakeDuration + ", sleepItems=" + this.sleepItems + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isUploaded=" + this.isUploaded + ", isOtherUploaded=" + this.isOtherUploaded + ")";
    }

    public Sleep(Long l, int i2, long j2, long j3, String timeYearToDay, int i3, int i4, int i5, int i6, int i7, int i8, int i9, List<SleepItem> list, String userId, String deviceType, String deviceMacAddress, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTime = j2;
        this.endTime = j3;
        this.timeYearToDay = timeYearToDay;
        this.deepSleepCount = i3;
        this.lightSleepCount = i4;
        this.deepSleepTotal = i5;
        this.lightSleepTotal = i6;
        this.remTotal = i7;
        this.wakeCount = i8;
        this.wakeDuration = i9;
        this.sleepItems = list;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isUploaded = z;
        this.isOtherUploaded = z2;
    }

    public /* synthetic */ Sleep(Long l, int i2, long j2, long j3, String str, int i3, int i4, int i5, int i6, int i7, int i8, int i9, List list, String str2, String str3, String str4, String str5, boolean z, boolean z2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : l, (i10 & 2) != 0 ? 0 : i2, (i10 & 4) != 0 ? 0L : j2, (i10 & 8) == 0 ? j3 : 0L, (i10 & 16) != 0 ? "" : str, (i10 & 32) != 0 ? 0 : i3, (i10 & 64) != 0 ? 0 : i4, (i10 & 128) != 0 ? 0 : i5, (i10 & 256) != 0 ? 0 : i6, (i10 & 512) != 0 ? 0 : i7, (i10 & 1024) != 0 ? 0 : i8, (i10 & 2048) != 0 ? 0 : i9, (i10 & 4096) != 0 ? null : list, (i10 & 8192) != 0 ? "" : str2, (i10 & 16384) != 0 ? "" : str3, (i10 & 32768) != 0 ? "" : str4, (i10 & 65536) != 0 ? null : str5, (i10 & 131072) != 0 ? false : z, (i10 & 262144) != 0 ? false : z2);
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

    public final int getDeepSleepCount() {
        return this.deepSleepCount;
    }

    public final void setDeepSleepCount(int i2) {
        this.deepSleepCount = i2;
    }

    public final int getLightSleepCount() {
        return this.lightSleepCount;
    }

    public final void setLightSleepCount(int i2) {
        this.lightSleepCount = i2;
    }

    public final int getDeepSleepTotal() {
        return this.deepSleepTotal;
    }

    public final void setDeepSleepTotal(int i2) {
        this.deepSleepTotal = i2;
    }

    public final int getLightSleepTotal() {
        return this.lightSleepTotal;
    }

    public final void setLightSleepTotal(int i2) {
        this.lightSleepTotal = i2;
    }

    public final int getRemTotal() {
        return this.remTotal;
    }

    public final void setRemTotal(int i2) {
        this.remTotal = i2;
    }

    public final int getWakeCount() {
        return this.wakeCount;
    }

    public final void setWakeCount(int i2) {
        this.wakeCount = i2;
    }

    public final int getWakeDuration() {
        return this.wakeDuration;
    }

    public final void setWakeDuration(int i2) {
        this.wakeDuration = i2;
    }

    public final List<SleepItem> getSleepItems() {
        return this.sleepItems;
    }

    public final void setSleepItems(List<SleepItem> list) {
        this.sleepItems = list;
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
