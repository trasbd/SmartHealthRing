package com.yucheng.smarthealthpro.home.activity.sleep.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: SleepSummary.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepSummary;", "", "deepSleepTime", "", "lightSleepTime", "remTime", "awakeTime", "napsTime", "<init>", "(IIIII)V", "getDeepSleepTime", "()I", "getLightSleepTime", "getRemTime", "getAwakeTime", "getNapsTime", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class SleepSummary {
    private final int awakeTime;
    private final int deepSleepTime;
    private final int lightSleepTime;
    private final int napsTime;
    private final int remTime;

    public SleepSummary() {
        this(0, 0, 0, 0, 0, 31, null);
    }

    public static /* synthetic */ SleepSummary copy$default(SleepSummary sleepSummary, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = sleepSummary.deepSleepTime;
        }
        if ((i7 & 2) != 0) {
            i3 = sleepSummary.lightSleepTime;
        }
        int i8 = i3;
        if ((i7 & 4) != 0) {
            i4 = sleepSummary.remTime;
        }
        int i9 = i4;
        if ((i7 & 8) != 0) {
            i5 = sleepSummary.awakeTime;
        }
        int i10 = i5;
        if ((i7 & 16) != 0) {
            i6 = sleepSummary.napsTime;
        }
        return sleepSummary.copy(i2, i8, i9, i10, i6);
    }

    /* renamed from: component1, reason: from getter */
    public final int getDeepSleepTime() {
        return this.deepSleepTime;
    }

    /* renamed from: component2, reason: from getter */
    public final int getLightSleepTime() {
        return this.lightSleepTime;
    }

    /* renamed from: component3, reason: from getter */
    public final int getRemTime() {
        return this.remTime;
    }

    /* renamed from: component4, reason: from getter */
    public final int getAwakeTime() {
        return this.awakeTime;
    }

    /* renamed from: component5, reason: from getter */
    public final int getNapsTime() {
        return this.napsTime;
    }

    public final SleepSummary copy(int deepSleepTime, int lightSleepTime, int remTime, int awakeTime, int napsTime) {
        return new SleepSummary(deepSleepTime, lightSleepTime, remTime, awakeTime, napsTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepSummary)) {
            return false;
        }
        SleepSummary sleepSummary = (SleepSummary) other;
        return this.deepSleepTime == sleepSummary.deepSleepTime && this.lightSleepTime == sleepSummary.lightSleepTime && this.remTime == sleepSummary.remTime && this.awakeTime == sleepSummary.awakeTime && this.napsTime == sleepSummary.napsTime;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.deepSleepTime) * 31) + Integer.hashCode(this.lightSleepTime)) * 31) + Integer.hashCode(this.remTime)) * 31) + Integer.hashCode(this.awakeTime)) * 31) + Integer.hashCode(this.napsTime);
    }

    public String toString() {
        return "SleepSummary(deepSleepTime=" + this.deepSleepTime + ", lightSleepTime=" + this.lightSleepTime + ", remTime=" + this.remTime + ", awakeTime=" + this.awakeTime + ", napsTime=" + this.napsTime + ")";
    }

    public SleepSummary(int i2, int i3, int i4, int i5, int i6) {
        this.deepSleepTime = i2;
        this.lightSleepTime = i3;
        this.remTime = i4;
        this.awakeTime = i5;
        this.napsTime = i6;
    }

    public /* synthetic */ SleepSummary(int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i2, (i7 & 2) != 0 ? 0 : i3, (i7 & 4) != 0 ? 0 : i4, (i7 & 8) != 0 ? 0 : i5, (i7 & 16) != 0 ? 0 : i6);
    }

    public final int getDeepSleepTime() {
        return this.deepSleepTime;
    }

    public final int getLightSleepTime() {
        return this.lightSleepTime;
    }

    public final int getRemTime() {
        return this.remTime;
    }

    public final int getAwakeTime() {
        return this.awakeTime;
    }

    public final int getNapsTime() {
        return this.napsTime;
    }
}
