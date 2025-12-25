package com.yucheng.smarthealthpro.data.bean;

import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SaveSportRecord.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b,\u0018\u00002\u00020\u0001BÍ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\n\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001d\"\u0004\b'\u0010\u001fR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001d\"\u0004\b/\u0010\u001fR\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001d\"\u0004\b1\u0010\u001fR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010%\"\u0004\b3\u00104R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001d\"\u0004\b6\u0010\u001fR\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001d\"\u0004\b8\u0010\u001fR\u001a\u0010\u0011\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010)\"\u0004\b:\u0010+R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010%\"\u0004\b<\u00104R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010%\"\u0004\b>\u00104R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010%\"\u0004\b@\u00104R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bA\u0010%R\u0011\u0010\u0016\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bB\u0010%R\u0011\u0010\u0017\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bC\u0010%R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010D¨\u0006E"}, d2 = {"Lcom/yucheng/smarthealthpro/data/bean/SaveSportRecord;", "", "type", "", "beginDate", "", "timeYearToDay", "", "totalSteps", "totalDistance", "", "lastDistance", "totalCalories", "lastCalories", "minkm", "heartRate", "runDuration", "kmh", "startPoint", "endPoint", "pathLinePoints", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "isUploaded", "", "<init>", "(IJLjava/lang/String;IFFIILjava/lang/String;IIFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getType", "()I", "setType", "(I)V", "getBeginDate", "()J", "setBeginDate", "(J)V", "getTimeYearToDay", "()Ljava/lang/String;", "getTotalSteps", "setTotalSteps", "getTotalDistance", "()F", "setTotalDistance", "(F)V", "getLastDistance", "setLastDistance", "getTotalCalories", "setTotalCalories", "getLastCalories", "setLastCalories", "getMinkm", "setMinkm", "(Ljava/lang/String;)V", "getHeartRate", "setHeartRate", "getRunDuration", "setRunDuration", "getKmh", "setKmh", "getStartPoint", "setStartPoint", "getEndPoint", "setEndPoint", "getPathLinePoints", "setPathLinePoints", "getUserId", "getDeviceType", "getDeviceMacAddress", "()Z", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SaveSportRecord {
    private long beginDate;
    private final String deviceMacAddress;
    private final String deviceType;
    private String endPoint;
    private int heartRate;
    private final boolean isUploaded;
    private float kmh;
    private int lastCalories;
    private float lastDistance;
    private String minkm;
    private String pathLinePoints;
    private int runDuration;
    private String startPoint;
    private final String timeYearToDay;
    private int totalCalories;
    private float totalDistance;
    private int totalSteps;
    private int type;
    private final String userId;

    public SaveSportRecord() {
        this(0, 0L, null, 0, 0.0f, 0.0f, 0, 0, null, 0, 0, 0.0f, null, null, null, null, null, null, false, 524287, null);
    }

    public SaveSportRecord(int i2, long j2, String timeYearToDay, int i3, float f2, float f3, int i4, int i5, String str, int i6, int i7, float f4, String str2, String str3, String str4, String userId, String deviceType, String deviceMacAddress, boolean z) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.type = i2;
        this.beginDate = j2;
        this.timeYearToDay = timeYearToDay;
        this.totalSteps = i3;
        this.totalDistance = f2;
        this.lastDistance = f3;
        this.totalCalories = i4;
        this.lastCalories = i5;
        this.minkm = str;
        this.heartRate = i6;
        this.runDuration = i7;
        this.kmh = f4;
        this.startPoint = str2;
        this.endPoint = str3;
        this.pathLinePoints = str4;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.isUploaded = z;
    }

    public /* synthetic */ SaveSportRecord(int i2, long j2, String str, int i3, float f2, float f3, int i4, int i5, String str2, int i6, int i7, float f4, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? -1 : i2, (i8 & 2) != 0 ? 0L : j2, (i8 & 4) != 0 ? "" : str, (i8 & 8) != 0 ? 0 : i3, (i8 & 16) != 0 ? 0.0f : f2, (i8 & 32) != 0 ? 0.0f : f3, (i8 & 64) != 0 ? 0 : i4, (i8 & 128) != 0 ? 0 : i5, (i8 & 256) != 0 ? null : str2, (i8 & 512) != 0 ? 0 : i6, (i8 & 1024) != 0 ? 0 : i7, (i8 & 2048) != 0 ? 0.0f : f4, (i8 & 4096) != 0 ? null : str3, (i8 & 8192) != 0 ? null : str4, (i8 & 16384) != 0 ? null : str5, (i8 & 32768) != 0 ? "" : str6, (i8 & 65536) != 0 ? "" : str7, (i8 & 131072) != 0 ? "" : str8, (i8 & 262144) != 0 ? false : z);
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

    /* renamed from: isUploaded, reason: from getter */
    public final boolean getIsUploaded() {
        return this.isUploaded;
    }
}
