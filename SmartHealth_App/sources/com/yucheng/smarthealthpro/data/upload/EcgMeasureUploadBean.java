package com.yucheng.smarthealthpro.data.upload;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import com.yucheng.smarthealthpro.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EcgMeasureUploadBean.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n\u0012\u0006\u0010\u0011\u001a\u00020\n\u0012\u0006\u0010\u0012\u001a\u00020\n\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\u0014\u001a\u00020\n¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\nHÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u000eHÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\t\u00105\u001a\u00020\nHÆ\u0003J\t\u00106\u001a\u00020\nHÆ\u0003J\t\u00107\u001a\u00020\nHÆ\u0003J\t\u00108\u001a\u00020\nHÆ\u0003J\u009f\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\nHÆ\u0001J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0005HÖ\u0001J\t\u0010>\u001a\u00020\nHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0016\u0010\u000f\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0016\u0010\u0010\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0016\u0010\u0011\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0016\u0010\u0012\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0016\u0010\u0013\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0016\u0010\u0014\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001f¨\u0006?"}, d2 = {"Lcom/yucheng/smarthealthpro/data/upload/EcgMeasureUploadBean;", "", "time", "", "hrv", "", "heartRate", "maxBloodPressure", "minBloodPressure", "data", "", Constant.SpConstKey.AGE, Constant.SpConstKey.SEX, "medicalResult", "Lcom/yucheng/smarthealthpro/data/upload/ECGDiagnosisResult;", "aIMedicalResult", "aIDiagnosesUrl", "userId", "zone", "deviceMac", "deviceModel", "<init>", "(JIIIILjava/lang/String;IILcom/yucheng/smarthealthpro/data/upload/ECGDiagnosisResult;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTime", "()J", "getHrv", "()I", "getHeartRate", "getMaxBloodPressure", "getMinBloodPressure", "getData", "()Ljava/lang/String;", "getAge", "getSex", "getMedicalResult", "()Lcom/yucheng/smarthealthpro/data/upload/ECGDiagnosisResult;", "getAIMedicalResult", "getAIDiagnosesUrl", "getUserId", "getZone", "getDeviceMac", "getDeviceModel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class EcgMeasureUploadBean {

    @SerializedName("aidiagnosesUrl")
    private final String aIDiagnosesUrl;

    @SerializedName("aimedicalResult")
    private final String aIMedicalResult;

    @SerializedName(Constant.SpConstKey.AGE)
    private final int age;

    @SerializedName("data")
    private final String data;

    @SerializedName("deviceMac")
    private final String deviceMac;

    @SerializedName("deviceModel")
    private final String deviceModel;

    @SerializedName("hhhh")
    private final int heartRate;

    @SerializedName("hrHz")
    private final int hrv;

    @SerializedName("maxb")
    private final int maxBloodPressure;

    @SerializedName("medicalResult")
    private final ECGDiagnosisResult medicalResult;

    @SerializedName("minb")
    private final int minBloodPressure;

    @SerializedName(Constant.SpConstKey.SEX)
    private final int sex;

    @SerializedName("time")
    private final long time;

    @SerializedName("userId")
    private final String userId;

    @SerializedName("zone")
    private final String zone;

    /* renamed from: component1, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    /* renamed from: component10, reason: from getter */
    public final String getAIMedicalResult() {
        return this.aIMedicalResult;
    }

    /* renamed from: component11, reason: from getter */
    public final String getAIDiagnosesUrl() {
        return this.aIDiagnosesUrl;
    }

    /* renamed from: component12, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component13, reason: from getter */
    public final String getZone() {
        return this.zone;
    }

    /* renamed from: component14, reason: from getter */
    public final String getDeviceMac() {
        return this.deviceMac;
    }

    /* renamed from: component15, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    /* renamed from: component2, reason: from getter */
    public final int getHrv() {
        return this.hrv;
    }

    /* renamed from: component3, reason: from getter */
    public final int getHeartRate() {
        return this.heartRate;
    }

    /* renamed from: component4, reason: from getter */
    public final int getMaxBloodPressure() {
        return this.maxBloodPressure;
    }

    /* renamed from: component5, reason: from getter */
    public final int getMinBloodPressure() {
        return this.minBloodPressure;
    }

    /* renamed from: component6, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* renamed from: component7, reason: from getter */
    public final int getAge() {
        return this.age;
    }

    /* renamed from: component8, reason: from getter */
    public final int getSex() {
        return this.sex;
    }

    /* renamed from: component9, reason: from getter */
    public final ECGDiagnosisResult getMedicalResult() {
        return this.medicalResult;
    }

    public final EcgMeasureUploadBean copy(long time, int hrv, int heartRate, int maxBloodPressure, int minBloodPressure, String data, int age, int sex, ECGDiagnosisResult medicalResult, String aIMedicalResult, String aIDiagnosesUrl, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(medicalResult, "medicalResult");
        Intrinsics.checkNotNullParameter(aIMedicalResult, "aIMedicalResult");
        Intrinsics.checkNotNullParameter(aIDiagnosesUrl, "aIDiagnosesUrl");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        return new EcgMeasureUploadBean(time, hrv, heartRate, maxBloodPressure, minBloodPressure, data, age, sex, medicalResult, aIMedicalResult, aIDiagnosesUrl, userId, zone, deviceMac, deviceModel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EcgMeasureUploadBean)) {
            return false;
        }
        EcgMeasureUploadBean ecgMeasureUploadBean = (EcgMeasureUploadBean) other;
        return this.time == ecgMeasureUploadBean.time && this.hrv == ecgMeasureUploadBean.hrv && this.heartRate == ecgMeasureUploadBean.heartRate && this.maxBloodPressure == ecgMeasureUploadBean.maxBloodPressure && this.minBloodPressure == ecgMeasureUploadBean.minBloodPressure && Intrinsics.areEqual(this.data, ecgMeasureUploadBean.data) && this.age == ecgMeasureUploadBean.age && this.sex == ecgMeasureUploadBean.sex && Intrinsics.areEqual(this.medicalResult, ecgMeasureUploadBean.medicalResult) && Intrinsics.areEqual(this.aIMedicalResult, ecgMeasureUploadBean.aIMedicalResult) && Intrinsics.areEqual(this.aIDiagnosesUrl, ecgMeasureUploadBean.aIDiagnosesUrl) && Intrinsics.areEqual(this.userId, ecgMeasureUploadBean.userId) && Intrinsics.areEqual(this.zone, ecgMeasureUploadBean.zone) && Intrinsics.areEqual(this.deviceMac, ecgMeasureUploadBean.deviceMac) && Intrinsics.areEqual(this.deviceModel, ecgMeasureUploadBean.deviceModel);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((Long.hashCode(this.time) * 31) + Integer.hashCode(this.hrv)) * 31) + Integer.hashCode(this.heartRate)) * 31) + Integer.hashCode(this.maxBloodPressure)) * 31) + Integer.hashCode(this.minBloodPressure)) * 31) + this.data.hashCode()) * 31) + Integer.hashCode(this.age)) * 31) + Integer.hashCode(this.sex)) * 31) + this.medicalResult.hashCode()) * 31) + this.aIMedicalResult.hashCode()) * 31) + this.aIDiagnosesUrl.hashCode()) * 31) + this.userId.hashCode()) * 31) + this.zone.hashCode()) * 31) + this.deviceMac.hashCode()) * 31) + this.deviceModel.hashCode();
    }

    public String toString() {
        return "EcgMeasureUploadBean(time=" + this.time + ", hrv=" + this.hrv + ", heartRate=" + this.heartRate + ", maxBloodPressure=" + this.maxBloodPressure + ", minBloodPressure=" + this.minBloodPressure + ", data=" + this.data + ", age=" + this.age + ", sex=" + this.sex + ", medicalResult=" + this.medicalResult + ", aIMedicalResult=" + this.aIMedicalResult + ", aIDiagnosesUrl=" + this.aIDiagnosesUrl + ", userId=" + this.userId + ", zone=" + this.zone + ", deviceMac=" + this.deviceMac + ", deviceModel=" + this.deviceModel + ")";
    }

    public EcgMeasureUploadBean(long j2, int i2, int i3, int i4, int i5, String data, int i6, int i7, ECGDiagnosisResult medicalResult, String aIMedicalResult, String aIDiagnosesUrl, String userId, String zone, String deviceMac, String deviceModel) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(medicalResult, "medicalResult");
        Intrinsics.checkNotNullParameter(aIMedicalResult, "aIMedicalResult");
        Intrinsics.checkNotNullParameter(aIDiagnosesUrl, "aIDiagnosesUrl");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(zone, "zone");
        Intrinsics.checkNotNullParameter(deviceMac, "deviceMac");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        this.time = j2;
        this.hrv = i2;
        this.heartRate = i3;
        this.maxBloodPressure = i4;
        this.minBloodPressure = i5;
        this.data = data;
        this.age = i6;
        this.sex = i7;
        this.medicalResult = medicalResult;
        this.aIMedicalResult = aIMedicalResult;
        this.aIDiagnosesUrl = aIDiagnosesUrl;
        this.userId = userId;
        this.zone = zone;
        this.deviceMac = deviceMac;
        this.deviceModel = deviceModel;
    }

    public /* synthetic */ EcgMeasureUploadBean(long j2, int i2, int i3, int i4, int i5, String str, int i6, int i7, ECGDiagnosisResult eCGDiagnosisResult, String str2, String str3, String str4, String str5, String str6, String str7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, (i8 & 2) != 0 ? 0 : i2, (i8 & 4) != 0 ? 0 : i3, (i8 & 8) != 0 ? 0 : i4, (i8 & 16) != 0 ? 0 : i5, str, (i8 & 64) != 0 ? 0 : i6, (i8 & 128) != 0 ? 0 : i7, eCGDiagnosisResult, (i8 & 512) != 0 ? "" : str2, (i8 & 1024) != 0 ? "" : str3, str4, str5, str6, str7);
    }

    public final long getTime() {
        return this.time;
    }

    public final int getHrv() {
        return this.hrv;
    }

    public final int getHeartRate() {
        return this.heartRate;
    }

    public final int getMaxBloodPressure() {
        return this.maxBloodPressure;
    }

    public final int getMinBloodPressure() {
        return this.minBloodPressure;
    }

    public final String getData() {
        return this.data;
    }

    public final int getAge() {
        return this.age;
    }

    public final int getSex() {
        return this.sex;
    }

    public final ECGDiagnosisResult getMedicalResult() {
        return this.medicalResult;
    }

    public final String getAIMedicalResult() {
        return this.aIMedicalResult;
    }

    public final String getAIDiagnosesUrl() {
        return this.aIDiagnosesUrl;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getZone() {
        return this.zone;
    }

    public final String getDeviceMac() {
        return this.deviceMac;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }
}
