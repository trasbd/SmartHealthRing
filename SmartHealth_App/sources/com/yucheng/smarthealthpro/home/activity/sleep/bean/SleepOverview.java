package com.yucheng.smarthealthpro.home.activity.sleep.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepOverview.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepOverview;", "", "sleepDetails", "", "Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepStageBean;", "total", "", "<init>", "(Ljava/util/List;I)V", "getSleepDetails", "()Ljava/util/List;", "getTotal", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class SleepOverview {
    private final List<SleepStageBean> sleepDetails;
    private final int total;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SleepOverview copy$default(SleepOverview sleepOverview, List list, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = sleepOverview.sleepDetails;
        }
        if ((i3 & 2) != 0) {
            i2 = sleepOverview.total;
        }
        return sleepOverview.copy(list, i2);
    }

    public final List<SleepStageBean> component1() {
        return this.sleepDetails;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTotal() {
        return this.total;
    }

    public final SleepOverview copy(List<SleepStageBean> sleepDetails, int total) {
        Intrinsics.checkNotNullParameter(sleepDetails, "sleepDetails");
        return new SleepOverview(sleepDetails, total);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepOverview)) {
            return false;
        }
        SleepOverview sleepOverview = (SleepOverview) other;
        return Intrinsics.areEqual(this.sleepDetails, sleepOverview.sleepDetails) && this.total == sleepOverview.total;
    }

    public int hashCode() {
        return (this.sleepDetails.hashCode() * 31) + Integer.hashCode(this.total);
    }

    public String toString() {
        return "SleepOverview(sleepDetails=" + this.sleepDetails + ", total=" + this.total + ")";
    }

    public SleepOverview(List<SleepStageBean> sleepDetails, int i2) {
        Intrinsics.checkNotNullParameter(sleepDetails, "sleepDetails");
        this.sleepDetails = sleepDetails;
        this.total = i2;
    }

    public final List<SleepStageBean> getSleepDetails() {
        return this.sleepDetails;
    }

    public final int getTotal() {
        return this.total;
    }
}
