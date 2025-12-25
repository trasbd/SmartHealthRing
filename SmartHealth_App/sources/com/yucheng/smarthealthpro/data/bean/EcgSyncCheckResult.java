package com.yucheng.smarthealthpro.data.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgSyncListResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: EcgSyncCheckResult.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0018\u00010\u0007R\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0018\u00010\u0007R\u00020\bHÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0018\u00010\u0007R\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0018\u00010\u0007R\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/yucheng/smarthealthpro/data/bean/EcgSyncCheckResult;", "", "startTime", "", MqttServiceConstants.DUPLICATE, "", "bean", "Lcom/yucheng/smarthealthpro/home/activity/ecg/bean/EcgSyncListResponse$DataBean;", "Lcom/yucheng/smarthealthpro/home/activity/ecg/bean/EcgSyncListResponse;", "<init>", "(JZLcom/yucheng/smarthealthpro/home/activity/ecg/bean/EcgSyncListResponse$DataBean;)V", "getStartTime", "()J", "getDuplicate", "()Z", "getBean", "()Lcom/yucheng/smarthealthpro/home/activity/ecg/bean/EcgSyncListResponse$DataBean;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class EcgSyncCheckResult {
    private final EcgSyncListResponse.DataBean bean;
    private final boolean duplicate;
    private final long startTime;

    public EcgSyncCheckResult() {
        this(0L, false, null, 7, null);
    }

    public static /* synthetic */ EcgSyncCheckResult copy$default(EcgSyncCheckResult ecgSyncCheckResult, long j2, boolean z, EcgSyncListResponse.DataBean dataBean, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = ecgSyncCheckResult.startTime;
        }
        if ((i2 & 2) != 0) {
            z = ecgSyncCheckResult.duplicate;
        }
        if ((i2 & 4) != 0) {
            dataBean = ecgSyncCheckResult.bean;
        }
        return ecgSyncCheckResult.copy(j2, z, dataBean);
    }

    /* renamed from: component1, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getDuplicate() {
        return this.duplicate;
    }

    /* renamed from: component3, reason: from getter */
    public final EcgSyncListResponse.DataBean getBean() {
        return this.bean;
    }

    public final EcgSyncCheckResult copy(long startTime, boolean duplicate, EcgSyncListResponse.DataBean bean) {
        return new EcgSyncCheckResult(startTime, duplicate, bean);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EcgSyncCheckResult)) {
            return false;
        }
        EcgSyncCheckResult ecgSyncCheckResult = (EcgSyncCheckResult) other;
        return this.startTime == ecgSyncCheckResult.startTime && this.duplicate == ecgSyncCheckResult.duplicate && Intrinsics.areEqual(this.bean, ecgSyncCheckResult.bean);
    }

    public int hashCode() {
        int iHashCode = ((Long.hashCode(this.startTime) * 31) + Boolean.hashCode(this.duplicate)) * 31;
        EcgSyncListResponse.DataBean dataBean = this.bean;
        return iHashCode + (dataBean == null ? 0 : dataBean.hashCode());
    }

    public String toString() {
        return "EcgSyncCheckResult(startTime=" + this.startTime + ", duplicate=" + this.duplicate + ", bean=" + this.bean + ")";
    }

    public EcgSyncCheckResult(long j2, boolean z, EcgSyncListResponse.DataBean dataBean) {
        this.startTime = j2;
        this.duplicate = z;
        this.bean = dataBean;
    }

    public /* synthetic */ EcgSyncCheckResult(long j2, boolean z, EcgSyncListResponse.DataBean dataBean, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j2, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? null : dataBean);
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final boolean getDuplicate() {
        return this.duplicate;
    }

    public final EcgSyncListResponse.DataBean getBean() {
        return this.bean;
    }
}
