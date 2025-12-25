package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HealthMetric.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b*\n\u0002\u0010\u0007\n\u0002\b+\b\u0087\b\u0018\u00002\u00020\u0001BÝ\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\b\u0012\b\b\u0002\u0010\u0018\u001a\u00020\b\u0012\b\b\u0002\u0010\u0019\u001a\u00020\b\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u001c\u0012\b\b\u0002\u0010 \u001a\u00020\u001c\u0012\b\b\u0002\u0010!\u001a\u00020\u001c\u0012\b\b\u0002\u0010\"\u001a\u00020\u001c\u0012\b\b\u0002\u0010#\u001a\u00020\u001c\u0012\b\b\u0002\u0010$\u001a\u00020\u001c\u0012\b\b\u0002\u0010%\u001a\u00020\u001c\u0012\b\b\u0002\u0010&\u001a\u00020\u001c\u0012\b\b\u0002\u0010'\u001a\u00020\u001c¢\u0006\u0004\b(\u0010)J\u0010\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010+J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\bHÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\t\u0010Q\u001a\u00020\u0005HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0005HÆ\u0003J\t\u0010T\u001a\u00020\u0005HÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\t\u0010X\u001a\u00020\u0005HÆ\u0003J\t\u0010Y\u001a\u00020\u0005HÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\u0005HÆ\u0003J\t\u0010\\\u001a\u00020\bHÆ\u0003J\t\u0010]\u001a\u00020\bHÆ\u0003J\t\u0010^\u001a\u00020\bHÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010`\u001a\u00020\u001cHÆ\u0003J\t\u0010a\u001a\u00020\u001cHÆ\u0003J\t\u0010b\u001a\u00020\u001cHÆ\u0003J\t\u0010c\u001a\u00020\u001cHÆ\u0003J\t\u0010d\u001a\u00020\u001cHÆ\u0003J\t\u0010e\u001a\u00020\u001cHÆ\u0003J\t\u0010f\u001a\u00020\u001cHÆ\u0003J\t\u0010g\u001a\u00020\u001cHÆ\u0003J\t\u0010h\u001a\u00020\u001cHÆ\u0003J\t\u0010i\u001a\u00020\u001cHÆ\u0003J\t\u0010j\u001a\u00020\u001cHÆ\u0003J\t\u0010k\u001a\u00020\u001cHÆ\u0003Jæ\u0002\u0010l\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\b2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010 \u001a\u00020\u001c2\b\b\u0002\u0010!\u001a\u00020\u001c2\b\b\u0002\u0010\"\u001a\u00020\u001c2\b\b\u0002\u0010#\u001a\u00020\u001c2\b\b\u0002\u0010$\u001a\u00020\u001c2\b\b\u0002\u0010%\u001a\u00020\u001c2\b\b\u0002\u0010&\u001a\u00020\u001c2\b\b\u0002\u0010'\u001a\u00020\u001cHÆ\u0001¢\u0006\u0002\u0010mJ\u0013\u0010n\u001a\u00020\u001c2\b\u0010o\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010p\u001a\u00020\u0005HÖ\u0001J\t\u0010q\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010.R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010.R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010.R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010.R\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010.R\u0016\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010.R\u0016\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010.R\u0016\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010.R\u0016\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010.R\u0016\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010.R\u0016\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010.R\u0016\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010.R\u0016\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010.R\u0016\u0010\u0016\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010.R\u0016\u0010\u0017\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u00102R\u0016\u0010\u0018\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u00102R\u0016\u0010\u0019\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u00102R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u00102R\u0016\u0010\u001b\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010ER\u0016\u0010\u001d\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010ER\u0016\u0010\u001e\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010ER\u0016\u0010\u001f\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010ER\u0016\u0010 \u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010ER\u0016\u0010!\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010ER\u0016\u0010\"\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010ER\u0016\u0010#\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010ER\u0016\u0010$\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010ER\u0016\u0010%\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010ER\u0016\u0010&\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010ER\u0016\u0010'\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010ER\u0011\u0010F\u001a\u00020G8F¢\u0006\u0006\u001a\u0004\bH\u0010I¨\u0006r"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "", "id", "", "queryID", "", "startTimestamp", "timeYearToDay", "", "heartRate", "heartRateVariability", "cvrr", "bloodOxygenLevel", "stepCount", "diastolicBloodPressure", "systolicBloodPressure", "respiratoryRate", "temperatureInteger", "temperatureFraction", "bodyFatInteger", "bodyFatFraction", "bloodSugarLevel", "bloodSugarMode", "userId", Constants.FunctionConstant.DEVICETYPE, "deviceMacAddress", "dataGroupId", "isHrvUploaded", "", "isBloodOxygenUploaded", "isRespiratoryRateUploaded", "isTemperatureUploaded", "isBodyFatUploaded", "isBloodSugarUploaded", "isOtherHrvUploaded", "isOtherBloodOxygenUploaded", "isOtherRespiratoryRateUploaded", "isOtherTemperatureUploaded", "isOtherBodyFatUploaded", "isOtherBloodSugarUploaded", "<init>", "(Ljava/lang/Long;IJLjava/lang/String;IIIIIIIIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZZZZZZZZZ)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQueryID", "()I", "getStartTimestamp", "()J", "getTimeYearToDay", "()Ljava/lang/String;", "getHeartRate", "getHeartRateVariability", "getCvrr", "getBloodOxygenLevel", "getStepCount", "getDiastolicBloodPressure", "getSystolicBloodPressure", "getRespiratoryRate", "getTemperatureInteger", "getTemperatureFraction", "getBodyFatInteger", "getBodyFatFraction", "getBloodSugarLevel", "getBloodSugarMode", "getUserId", "getDeviceType", "getDeviceMacAddress", "getDataGroupId", "()Z", "temperature", "", "getTemperature", "()F", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "copy", "(Ljava/lang/Long;IJLjava/lang/String;IIIIIIIIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZZZZZZZZZ)Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class HealthMetric {
    private final int bloodOxygenLevel;
    private final int bloodSugarLevel;
    private final int bloodSugarMode;
    private final int bodyFatFraction;
    private final int bodyFatInteger;
    private final int cvrr;
    private final String dataGroupId;
    private final String deviceMacAddress;
    private final String deviceType;
    private final int diastolicBloodPressure;
    private final int heartRate;
    private final int heartRateVariability;
    private final Long id;
    private final boolean isBloodOxygenUploaded;
    private final boolean isBloodSugarUploaded;
    private final boolean isBodyFatUploaded;
    private final boolean isHrvUploaded;
    private final boolean isOtherBloodOxygenUploaded;
    private final boolean isOtherBloodSugarUploaded;
    private final boolean isOtherBodyFatUploaded;
    private final boolean isOtherHrvUploaded;
    private final boolean isOtherRespiratoryRateUploaded;
    private final boolean isOtherTemperatureUploaded;
    private final boolean isRespiratoryRateUploaded;
    private final boolean isTemperatureUploaded;
    private final int queryID;
    private final int respiratoryRate;
    private final long startTimestamp;
    private final int stepCount;
    private final int systolicBloodPressure;
    private final int temperatureFraction;
    private final int temperatureInteger;
    private final String timeYearToDay;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    /* renamed from: component11, reason: from getter */
    public final int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    /* renamed from: component12, reason: from getter */
    public final int getRespiratoryRate() {
        return this.respiratoryRate;
    }

    /* renamed from: component13, reason: from getter */
    public final int getTemperatureInteger() {
        return this.temperatureInteger;
    }

    /* renamed from: component14, reason: from getter */
    public final int getTemperatureFraction() {
        return this.temperatureFraction;
    }

    /* renamed from: component15, reason: from getter */
    public final int getBodyFatInteger() {
        return this.bodyFatInteger;
    }

    /* renamed from: component16, reason: from getter */
    public final int getBodyFatFraction() {
        return this.bodyFatFraction;
    }

    /* renamed from: component17, reason: from getter */
    public final int getBloodSugarLevel() {
        return this.bloodSugarLevel;
    }

    /* renamed from: component18, reason: from getter */
    public final int getBloodSugarMode() {
        return this.bloodSugarMode;
    }

    /* renamed from: component19, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQueryID() {
        return this.queryID;
    }

    /* renamed from: component20, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component21, reason: from getter */
    public final String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    /* renamed from: component22, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    /* renamed from: component23, reason: from getter */
    public final boolean getIsHrvUploaded() {
        return this.isHrvUploaded;
    }

    /* renamed from: component24, reason: from getter */
    public final boolean getIsBloodOxygenUploaded() {
        return this.isBloodOxygenUploaded;
    }

    /* renamed from: component25, reason: from getter */
    public final boolean getIsRespiratoryRateUploaded() {
        return this.isRespiratoryRateUploaded;
    }

    /* renamed from: component26, reason: from getter */
    public final boolean getIsTemperatureUploaded() {
        return this.isTemperatureUploaded;
    }

    /* renamed from: component27, reason: from getter */
    public final boolean getIsBodyFatUploaded() {
        return this.isBodyFatUploaded;
    }

    /* renamed from: component28, reason: from getter */
    public final boolean getIsBloodSugarUploaded() {
        return this.isBloodSugarUploaded;
    }

    /* renamed from: component29, reason: from getter */
    public final boolean getIsOtherHrvUploaded() {
        return this.isOtherHrvUploaded;
    }

    /* renamed from: component3, reason: from getter */
    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    /* renamed from: component30, reason: from getter */
    public final boolean getIsOtherBloodOxygenUploaded() {
        return this.isOtherBloodOxygenUploaded;
    }

    /* renamed from: component31, reason: from getter */
    public final boolean getIsOtherRespiratoryRateUploaded() {
        return this.isOtherRespiratoryRateUploaded;
    }

    /* renamed from: component32, reason: from getter */
    public final boolean getIsOtherTemperatureUploaded() {
        return this.isOtherTemperatureUploaded;
    }

    /* renamed from: component33, reason: from getter */
    public final boolean getIsOtherBodyFatUploaded() {
        return this.isOtherBodyFatUploaded;
    }

    /* renamed from: component34, reason: from getter */
    public final boolean getIsOtherBloodSugarUploaded() {
        return this.isOtherBloodSugarUploaded;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTimeYearToDay() {
        return this.timeYearToDay;
    }

    /* renamed from: component5, reason: from getter */
    public final int getHeartRate() {
        return this.heartRate;
    }

    /* renamed from: component6, reason: from getter */
    public final int getHeartRateVariability() {
        return this.heartRateVariability;
    }

    /* renamed from: component7, reason: from getter */
    public final int getCvrr() {
        return this.cvrr;
    }

    /* renamed from: component8, reason: from getter */
    public final int getBloodOxygenLevel() {
        return this.bloodOxygenLevel;
    }

    /* renamed from: component9, reason: from getter */
    public final int getStepCount() {
        return this.stepCount;
    }

    public final HealthMetric copy(Long id, int queryID, long startTimestamp, String timeYearToDay, int heartRate, int heartRateVariability, int cvrr, int bloodOxygenLevel, int stepCount, int diastolicBloodPressure, int systolicBloodPressure, int respiratoryRate, int temperatureInteger, int temperatureFraction, int bodyFatInteger, int bodyFatFraction, int bloodSugarLevel, int bloodSugarMode, String userId, String deviceType, String deviceMacAddress, String dataGroupId, boolean isHrvUploaded, boolean isBloodOxygenUploaded, boolean isRespiratoryRateUploaded, boolean isTemperatureUploaded, boolean isBodyFatUploaded, boolean isBloodSugarUploaded, boolean isOtherHrvUploaded, boolean isOtherBloodOxygenUploaded, boolean isOtherRespiratoryRateUploaded, boolean isOtherTemperatureUploaded, boolean isOtherBodyFatUploaded, boolean isOtherBloodSugarUploaded) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        return new HealthMetric(id, queryID, startTimestamp, timeYearToDay, heartRate, heartRateVariability, cvrr, bloodOxygenLevel, stepCount, diastolicBloodPressure, systolicBloodPressure, respiratoryRate, temperatureInteger, temperatureFraction, bodyFatInteger, bodyFatFraction, bloodSugarLevel, bloodSugarMode, userId, deviceType, deviceMacAddress, dataGroupId, isHrvUploaded, isBloodOxygenUploaded, isRespiratoryRateUploaded, isTemperatureUploaded, isBodyFatUploaded, isBloodSugarUploaded, isOtherHrvUploaded, isOtherBloodOxygenUploaded, isOtherRespiratoryRateUploaded, isOtherTemperatureUploaded, isOtherBodyFatUploaded, isOtherBloodSugarUploaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthMetric)) {
            return false;
        }
        HealthMetric healthMetric = (HealthMetric) other;
        return Intrinsics.areEqual(this.id, healthMetric.id) && this.queryID == healthMetric.queryID && this.startTimestamp == healthMetric.startTimestamp && Intrinsics.areEqual(this.timeYearToDay, healthMetric.timeYearToDay) && this.heartRate == healthMetric.heartRate && this.heartRateVariability == healthMetric.heartRateVariability && this.cvrr == healthMetric.cvrr && this.bloodOxygenLevel == healthMetric.bloodOxygenLevel && this.stepCount == healthMetric.stepCount && this.diastolicBloodPressure == healthMetric.diastolicBloodPressure && this.systolicBloodPressure == healthMetric.systolicBloodPressure && this.respiratoryRate == healthMetric.respiratoryRate && this.temperatureInteger == healthMetric.temperatureInteger && this.temperatureFraction == healthMetric.temperatureFraction && this.bodyFatInteger == healthMetric.bodyFatInteger && this.bodyFatFraction == healthMetric.bodyFatFraction && this.bloodSugarLevel == healthMetric.bloodSugarLevel && this.bloodSugarMode == healthMetric.bloodSugarMode && Intrinsics.areEqual(this.userId, healthMetric.userId) && Intrinsics.areEqual(this.deviceType, healthMetric.deviceType) && Intrinsics.areEqual(this.deviceMacAddress, healthMetric.deviceMacAddress) && Intrinsics.areEqual(this.dataGroupId, healthMetric.dataGroupId) && this.isHrvUploaded == healthMetric.isHrvUploaded && this.isBloodOxygenUploaded == healthMetric.isBloodOxygenUploaded && this.isRespiratoryRateUploaded == healthMetric.isRespiratoryRateUploaded && this.isTemperatureUploaded == healthMetric.isTemperatureUploaded && this.isBodyFatUploaded == healthMetric.isBodyFatUploaded && this.isBloodSugarUploaded == healthMetric.isBloodSugarUploaded && this.isOtherHrvUploaded == healthMetric.isOtherHrvUploaded && this.isOtherBloodOxygenUploaded == healthMetric.isOtherBloodOxygenUploaded && this.isOtherRespiratoryRateUploaded == healthMetric.isOtherRespiratoryRateUploaded && this.isOtherTemperatureUploaded == healthMetric.isOtherTemperatureUploaded && this.isOtherBodyFatUploaded == healthMetric.isOtherBodyFatUploaded && this.isOtherBloodSugarUploaded == healthMetric.isOtherBloodSugarUploaded;
    }

    public int hashCode() {
        Long l = this.id;
        int iHashCode = (((((((((((((((((((((((((((((((((((((((((l == null ? 0 : l.hashCode()) * 31) + Integer.hashCode(this.queryID)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + this.timeYearToDay.hashCode()) * 31) + Integer.hashCode(this.heartRate)) * 31) + Integer.hashCode(this.heartRateVariability)) * 31) + Integer.hashCode(this.cvrr)) * 31) + Integer.hashCode(this.bloodOxygenLevel)) * 31) + Integer.hashCode(this.stepCount)) * 31) + Integer.hashCode(this.diastolicBloodPressure)) * 31) + Integer.hashCode(this.systolicBloodPressure)) * 31) + Integer.hashCode(this.respiratoryRate)) * 31) + Integer.hashCode(this.temperatureInteger)) * 31) + Integer.hashCode(this.temperatureFraction)) * 31) + Integer.hashCode(this.bodyFatInteger)) * 31) + Integer.hashCode(this.bodyFatFraction)) * 31) + Integer.hashCode(this.bloodSugarLevel)) * 31) + Integer.hashCode(this.bloodSugarMode)) * 31) + this.userId.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.deviceMacAddress.hashCode()) * 31;
        String str = this.dataGroupId;
        return ((((((((((((((((((((((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isHrvUploaded)) * 31) + Boolean.hashCode(this.isBloodOxygenUploaded)) * 31) + Boolean.hashCode(this.isRespiratoryRateUploaded)) * 31) + Boolean.hashCode(this.isTemperatureUploaded)) * 31) + Boolean.hashCode(this.isBodyFatUploaded)) * 31) + Boolean.hashCode(this.isBloodSugarUploaded)) * 31) + Boolean.hashCode(this.isOtherHrvUploaded)) * 31) + Boolean.hashCode(this.isOtherBloodOxygenUploaded)) * 31) + Boolean.hashCode(this.isOtherRespiratoryRateUploaded)) * 31) + Boolean.hashCode(this.isOtherTemperatureUploaded)) * 31) + Boolean.hashCode(this.isOtherBodyFatUploaded)) * 31) + Boolean.hashCode(this.isOtherBloodSugarUploaded);
    }

    public String toString() {
        return "HealthMetric(id=" + this.id + ", queryID=" + this.queryID + ", startTimestamp=" + this.startTimestamp + ", timeYearToDay=" + this.timeYearToDay + ", heartRate=" + this.heartRate + ", heartRateVariability=" + this.heartRateVariability + ", cvrr=" + this.cvrr + ", bloodOxygenLevel=" + this.bloodOxygenLevel + ", stepCount=" + this.stepCount + ", diastolicBloodPressure=" + this.diastolicBloodPressure + ", systolicBloodPressure=" + this.systolicBloodPressure + ", respiratoryRate=" + this.respiratoryRate + ", temperatureInteger=" + this.temperatureInteger + ", temperatureFraction=" + this.temperatureFraction + ", bodyFatInteger=" + this.bodyFatInteger + ", bodyFatFraction=" + this.bodyFatFraction + ", bloodSugarLevel=" + this.bloodSugarLevel + ", bloodSugarMode=" + this.bloodSugarMode + ", userId=" + this.userId + ", deviceType=" + this.deviceType + ", deviceMacAddress=" + this.deviceMacAddress + ", dataGroupId=" + this.dataGroupId + ", isHrvUploaded=" + this.isHrvUploaded + ", isBloodOxygenUploaded=" + this.isBloodOxygenUploaded + ", isRespiratoryRateUploaded=" + this.isRespiratoryRateUploaded + ", isTemperatureUploaded=" + this.isTemperatureUploaded + ", isBodyFatUploaded=" + this.isBodyFatUploaded + ", isBloodSugarUploaded=" + this.isBloodSugarUploaded + ", isOtherHrvUploaded=" + this.isOtherHrvUploaded + ", isOtherBloodOxygenUploaded=" + this.isOtherBloodOxygenUploaded + ", isOtherRespiratoryRateUploaded=" + this.isOtherRespiratoryRateUploaded + ", isOtherTemperatureUploaded=" + this.isOtherTemperatureUploaded + ", isOtherBodyFatUploaded=" + this.isOtherBodyFatUploaded + ", isOtherBloodSugarUploaded=" + this.isOtherBloodSugarUploaded + ")";
    }

    public HealthMetric(Long l, int i2, long j2, String timeYearToDay, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, String userId, String deviceType, String deviceMacAddress, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12) {
        Intrinsics.checkNotNullParameter(timeYearToDay, "timeYearToDay");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceMacAddress, "deviceMacAddress");
        this.id = l;
        this.queryID = i2;
        this.startTimestamp = j2;
        this.timeYearToDay = timeYearToDay;
        this.heartRate = i3;
        this.heartRateVariability = i4;
        this.cvrr = i5;
        this.bloodOxygenLevel = i6;
        this.stepCount = i7;
        this.diastolicBloodPressure = i8;
        this.systolicBloodPressure = i9;
        this.respiratoryRate = i10;
        this.temperatureInteger = i11;
        this.temperatureFraction = i12;
        this.bodyFatInteger = i13;
        this.bodyFatFraction = i14;
        this.bloodSugarLevel = i15;
        this.bloodSugarMode = i16;
        this.userId = userId;
        this.deviceType = deviceType;
        this.deviceMacAddress = deviceMacAddress;
        this.dataGroupId = str;
        this.isHrvUploaded = z;
        this.isBloodOxygenUploaded = z2;
        this.isRespiratoryRateUploaded = z3;
        this.isTemperatureUploaded = z4;
        this.isBodyFatUploaded = z5;
        this.isBloodSugarUploaded = z6;
        this.isOtherHrvUploaded = z7;
        this.isOtherBloodOxygenUploaded = z8;
        this.isOtherRespiratoryRateUploaded = z9;
        this.isOtherTemperatureUploaded = z10;
        this.isOtherBodyFatUploaded = z11;
        this.isOtherBloodSugarUploaded = z12;
    }

    public /* synthetic */ HealthMetric(Long l, int i2, long j2, String str, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, int i17, int i18, DefaultConstructorMarker defaultConstructorMarker) {
        this((i17 & 1) != 0 ? null : l, (i17 & 2) != 0 ? 0 : i2, j2, (i17 & 8) != 0 ? "" : str, (i17 & 16) != 0 ? 0 : i3, (i17 & 32) != 0 ? 0 : i4, (i17 & 64) != 0 ? 0 : i5, (i17 & 128) != 0 ? 0 : i6, (i17 & 256) != 0 ? 0 : i7, (i17 & 512) != 0 ? 0 : i8, (i17 & 1024) != 0 ? 0 : i9, (i17 & 2048) != 0 ? 0 : i10, (i17 & 4096) != 0 ? 0 : i11, (i17 & 8192) != 0 ? 0 : i12, (i17 & 16384) != 0 ? 0 : i13, (32768 & i17) != 0 ? 0 : i14, (65536 & i17) != 0 ? 0 : i15, (131072 & i17) != 0 ? 0 : i16, (262144 & i17) != 0 ? "" : str2, (524288 & i17) != 0 ? "" : str3, (1048576 & i17) != 0 ? "" : str4, (2097152 & i17) != 0 ? null : str5, (4194304 & i17) != 0 ? false : z, (8388608 & i17) != 0 ? false : z2, (16777216 & i17) != 0 ? false : z3, (33554432 & i17) != 0 ? false : z4, (67108864 & i17) != 0 ? false : z5, (134217728 & i17) != 0 ? false : z6, (268435456 & i17) != 0 ? false : z7, (536870912 & i17) != 0 ? false : z8, (1073741824 & i17) != 0 ? false : z9, (i17 & Integer.MIN_VALUE) != 0 ? false : z10, (i18 & 1) != 0 ? false : z11, (i18 & 2) != 0 ? false : z12);
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

    public final int getHeartRate() {
        return this.heartRate;
    }

    public final int getHeartRateVariability() {
        return this.heartRateVariability;
    }

    public final int getCvrr() {
        return this.cvrr;
    }

    public final int getBloodOxygenLevel() {
        return this.bloodOxygenLevel;
    }

    public final int getStepCount() {
        return this.stepCount;
    }

    public final int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    public final int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public final int getRespiratoryRate() {
        return this.respiratoryRate;
    }

    public final int getTemperatureInteger() {
        return this.temperatureInteger;
    }

    public final int getTemperatureFraction() {
        return this.temperatureFraction;
    }

    public final int getBodyFatInteger() {
        return this.bodyFatInteger;
    }

    public final int getBodyFatFraction() {
        return this.bodyFatFraction;
    }

    public final int getBloodSugarLevel() {
        return this.bloodSugarLevel;
    }

    public final int getBloodSugarMode() {
        return this.bloodSugarMode;
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

    public final boolean isHrvUploaded() {
        return this.isHrvUploaded;
    }

    public final boolean isBloodOxygenUploaded() {
        return this.isBloodOxygenUploaded;
    }

    public final boolean isRespiratoryRateUploaded() {
        return this.isRespiratoryRateUploaded;
    }

    public final boolean isTemperatureUploaded() {
        return this.isTemperatureUploaded;
    }

    public final boolean isBodyFatUploaded() {
        return this.isBodyFatUploaded;
    }

    public final boolean isBloodSugarUploaded() {
        return this.isBloodSugarUploaded;
    }

    public final boolean isOtherHrvUploaded() {
        return this.isOtherHrvUploaded;
    }

    public final boolean isOtherBloodOxygenUploaded() {
        return this.isOtherBloodOxygenUploaded;
    }

    public final boolean isOtherRespiratoryRateUploaded() {
        return this.isOtherRespiratoryRateUploaded;
    }

    public final boolean isOtherTemperatureUploaded() {
        return this.isOtherTemperatureUploaded;
    }

    public final boolean isOtherBodyFatUploaded() {
        return this.isOtherBodyFatUploaded;
    }

    public final boolean isOtherBloodSugarUploaded() {
        return this.isOtherBloodSugarUploaded;
    }

    public final float getTemperature() {
        return Float.parseFloat(this.temperatureInteger + "." + this.temperatureFraction);
    }
}
