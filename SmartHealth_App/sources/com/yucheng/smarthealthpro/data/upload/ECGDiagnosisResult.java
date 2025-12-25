package com.yucheng.smarthealthpro.data.upload;

import androidx.core.app.FrameMetricsAggregator;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EcgMeasureUploadBean.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\b¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003Jc\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0016\u0010\n\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0016\u0010\u000b\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0016\u0010\f\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0016\u0010\r\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016¨\u0006+"}, d2 = {"Lcom/yucheng/smarthealthpro/data/upload/ECGDiagnosisResult;", "", "atrialFibrillationFlag", "", "qrsType", "healthNorm", "", "heavyLoad", "", "body", "hrvNorm", "pressure", "sympatheticActivityIndex", "respiratoryRate", "<init>", "(IILjava/lang/String;FFFFFF)V", "getAtrialFibrillationFlag", "()I", "getQrsType", "getHealthNorm", "()Ljava/lang/String;", "getHeavyLoad", "()F", "getBody", "getHrvNorm", "getPressure", "getSympatheticActivityIndex", "getRespiratoryRate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class ECGDiagnosisResult {

    @SerializedName("afflag")
    private final int atrialFibrillationFlag;

    @SerializedName("body")
    private final float body;

    @SerializedName("healthNorm")
    private final String healthNorm;

    @SerializedName("heavyLoad")
    private final float heavyLoad;

    @SerializedName("hrvNorm")
    private final float hrvNorm;

    @SerializedName("pressure")
    private final float pressure;

    @SerializedName("qrstype")
    private final int qrsType;

    @SerializedName("respiratoryRate")
    private final float respiratoryRate;

    @SerializedName("sympatheticActivityIndex")
    private final float sympatheticActivityIndex;

    public ECGDiagnosisResult() {
        this(0, 0, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    /* renamed from: component1, reason: from getter */
    public final int getAtrialFibrillationFlag() {
        return this.atrialFibrillationFlag;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQrsType() {
        return this.qrsType;
    }

    /* renamed from: component3, reason: from getter */
    public final String getHealthNorm() {
        return this.healthNorm;
    }

    /* renamed from: component4, reason: from getter */
    public final float getHeavyLoad() {
        return this.heavyLoad;
    }

    /* renamed from: component5, reason: from getter */
    public final float getBody() {
        return this.body;
    }

    /* renamed from: component6, reason: from getter */
    public final float getHrvNorm() {
        return this.hrvNorm;
    }

    /* renamed from: component7, reason: from getter */
    public final float getPressure() {
        return this.pressure;
    }

    /* renamed from: component8, reason: from getter */
    public final float getSympatheticActivityIndex() {
        return this.sympatheticActivityIndex;
    }

    /* renamed from: component9, reason: from getter */
    public final float getRespiratoryRate() {
        return this.respiratoryRate;
    }

    public final ECGDiagnosisResult copy(int atrialFibrillationFlag, int qrsType, String healthNorm, float heavyLoad, float body, float hrvNorm, float pressure, float sympatheticActivityIndex, float respiratoryRate) {
        Intrinsics.checkNotNullParameter(healthNorm, "healthNorm");
        return new ECGDiagnosisResult(atrialFibrillationFlag, qrsType, healthNorm, heavyLoad, body, hrvNorm, pressure, sympatheticActivityIndex, respiratoryRate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ECGDiagnosisResult)) {
            return false;
        }
        ECGDiagnosisResult eCGDiagnosisResult = (ECGDiagnosisResult) other;
        return this.atrialFibrillationFlag == eCGDiagnosisResult.atrialFibrillationFlag && this.qrsType == eCGDiagnosisResult.qrsType && Intrinsics.areEqual(this.healthNorm, eCGDiagnosisResult.healthNorm) && Float.compare(this.heavyLoad, eCGDiagnosisResult.heavyLoad) == 0 && Float.compare(this.body, eCGDiagnosisResult.body) == 0 && Float.compare(this.hrvNorm, eCGDiagnosisResult.hrvNorm) == 0 && Float.compare(this.pressure, eCGDiagnosisResult.pressure) == 0 && Float.compare(this.sympatheticActivityIndex, eCGDiagnosisResult.sympatheticActivityIndex) == 0 && Float.compare(this.respiratoryRate, eCGDiagnosisResult.respiratoryRate) == 0;
    }

    public int hashCode() {
        return (((((((((((((((Integer.hashCode(this.atrialFibrillationFlag) * 31) + Integer.hashCode(this.qrsType)) * 31) + this.healthNorm.hashCode()) * 31) + Float.hashCode(this.heavyLoad)) * 31) + Float.hashCode(this.body)) * 31) + Float.hashCode(this.hrvNorm)) * 31) + Float.hashCode(this.pressure)) * 31) + Float.hashCode(this.sympatheticActivityIndex)) * 31) + Float.hashCode(this.respiratoryRate);
    }

    public String toString() {
        return "ECGDiagnosisResult(atrialFibrillationFlag=" + this.atrialFibrillationFlag + ", qrsType=" + this.qrsType + ", healthNorm=" + this.healthNorm + ", heavyLoad=" + this.heavyLoad + ", body=" + this.body + ", hrvNorm=" + this.hrvNorm + ", pressure=" + this.pressure + ", sympatheticActivityIndex=" + this.sympatheticActivityIndex + ", respiratoryRate=" + this.respiratoryRate + ")";
    }

    public ECGDiagnosisResult(int i2, int i3, String healthNorm, float f2, float f3, float f4, float f5, float f6, float f7) {
        Intrinsics.checkNotNullParameter(healthNorm, "healthNorm");
        this.atrialFibrillationFlag = i2;
        this.qrsType = i3;
        this.healthNorm = healthNorm;
        this.heavyLoad = f2;
        this.body = f3;
        this.hrvNorm = f4;
        this.pressure = f5;
        this.sympatheticActivityIndex = f6;
        this.respiratoryRate = f7;
    }

    public final int getAtrialFibrillationFlag() {
        return this.atrialFibrillationFlag;
    }

    public final int getQrsType() {
        return this.qrsType;
    }

    public final String getHealthNorm() {
        return this.healthNorm;
    }

    public /* synthetic */ ECGDiagnosisResult(int i2, int i3, String str, float f2, float f3, float f4, float f5, float f6, float f7, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i2, (i4 & 2) != 0 ? 1 : i3, (i4 & 4) != 0 ? "" : str, (i4 & 8) != 0 ? 0.0f : f2, (i4 & 16) != 0 ? 0.0f : f3, (i4 & 32) != 0 ? 0.0f : f4, (i4 & 64) != 0 ? 0.0f : f5, (i4 & 128) != 0 ? 0.0f : f6, (i4 & 256) == 0 ? f7 : 0.0f);
    }

    public final float getHeavyLoad() {
        return this.heavyLoad;
    }

    public final float getBody() {
        return this.body;
    }

    public final float getHrvNorm() {
        return this.hrvNorm;
    }

    public final float getPressure() {
        return this.pressure;
    }

    public final float getSympatheticActivityIndex() {
        return this.sympatheticActivityIndex;
    }

    public final float getRespiratoryRate() {
        return this.respiratoryRate;
    }
}
