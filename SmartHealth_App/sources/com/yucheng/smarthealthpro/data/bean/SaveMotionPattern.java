package com.yucheng.smarthealthpro.data.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SaveMotionPattern.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001J\t\u0010 \u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006!"}, d2 = {"Lcom/yucheng/smarthealthpro/data/bean/SaveMotionPattern;", "", "startTimestamp", "", "sportHeartRate", "", "userId", "", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "<init>", "(JILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getStartTimestamp", "()J", "getSportHeartRate", "()I", "setSportHeartRate", "(I)V", "getUserId", "()Ljava/lang/String;", "getDeviceType", "getDeviceMacAddress", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class SaveMotionPattern {
    private final String deviceMacAddress;
    private final String deviceType;
    private int sportHeartRate;
    private final long startTimestamp;
    private final String userId;

    public static /* synthetic */ SaveMotionPattern copy$default(SaveMotionPattern saveMotionPattern, long j2, int i2, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = saveMotionPattern.startTimestamp;
        }
        long j3 = j2;
        if ((i3 & 2) != 0) {
            i2 = saveMotionPattern.sportHeartRate;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            str = saveMotionPattern.userId;
        }
        String str4 = str;
        if ((i3 & 8) != 0) {
            str2 = saveMotionPattern.deviceType;
        }
        String str5 = str2;
        if ((i3 & 16) != 0) {
            str3 = saveMotionPattern.deviceMacAddress;
        }
        return saveMotionPattern.copy(j3, i4, str4, str5, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSportHeartRate() {
        return this.sportHeartRate;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    public final SaveMotionPattern copy(long startTimestamp, int sportHeartRate, String userId, String deviceType, String deviceMacAddress) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new SaveMotionPattern(startTimestamp, sportHeartRate, userId, deviceType, deviceMacAddress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SaveMotionPattern)) {
            return false;
        }
        SaveMotionPattern saveMotionPattern = (SaveMotionPattern) other;
        return this.startTimestamp == saveMotionPattern.startTimestamp && this.sportHeartRate == saveMotionPattern.sportHeartRate && Intrinsics.areEqual(this.userId, saveMotionPattern.userId) && Intrinsics.areEqual(this.deviceType, saveMotionPattern.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, saveMotionPattern.deviceMacAddress);
    }

    public int hashCode() {
        return (((((((Long.hashCode(this.startTimestamp) * 31) + Integer.hashCode(this.sportHeartRate)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode();
    }

    public String toString() {
        return "SaveMotionPattern(startTimestamp=" + this.startTimestamp + ", sportHeartRate=" + this.sportHeartRate + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ")";
    }

    public SaveMotionPattern(long j2, int i2, String userId, String deviceType, String deviceMacAddress) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.startTimestamp = j2;
        this.sportHeartRate = i2;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
    }

    public /* synthetic */ SaveMotionPattern(long j2, int i2, String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? "" : str2, (i3 & 16) != 0 ? "" : str3);
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final int getSportHeartRate() {
        return this.sportHeartRate;
    }

    public final void setSportHeartRate(int i2) {
        this.sportHeartRate = i2;
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
}
