package com.yucheng.smarthealthpro.data.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EcgSaveBean.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/yucheng/smarthealthpro/data/bean/EcgSaveBean;", "", "sendTime", "", "ecgBean", "Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "<init>", "(JLcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;)V", "getSendTime", "()J", "getEcgBean", "()Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class EcgSaveBean {
    private final EcgMeasure ecgBean;
    private final long sendTime;

    public static /* synthetic */ EcgSaveBean copy$default(EcgSaveBean ecgSaveBean, long j2, EcgMeasure ecgMeasure, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = ecgSaveBean.sendTime;
        }
        if ((i2 & 2) != 0) {
            ecgMeasure = ecgSaveBean.ecgBean;
        }
        return ecgSaveBean.copy(j2, ecgMeasure);
    }

    /* renamed from: component1, reason: from getter */
    public final long getSendTime() {
        return this.sendTime;
    }

    /* renamed from: component2, reason: from getter */
    public final EcgMeasure getEcgBean() {
        return this.ecgBean;
    }

    public final EcgSaveBean copy(long sendTime, EcgMeasure ecgBean) {
        Intrinsics.checkNotNullParameter(ecgBean, "ecgBean");
        return new EcgSaveBean(sendTime, ecgBean);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EcgSaveBean)) {
            return false;
        }
        EcgSaveBean ecgSaveBean = (EcgSaveBean) other;
        return this.sendTime == ecgSaveBean.sendTime && Intrinsics.areEqual(this.ecgBean, ecgSaveBean.ecgBean);
    }

    public int hashCode() {
        return (Long.hashCode(this.sendTime) * 31) + this.ecgBean.hashCode();
    }

    public String toString() {
        return "EcgSaveBean(sendTime=" + this.sendTime + ", ecgBean=" + this.ecgBean + ")";
    }

    public EcgSaveBean(long j2, EcgMeasure ecgBean) {
        Intrinsics.checkNotNullParameter(ecgBean, "ecgBean");
        this.sendTime = j2;
        this.ecgBean = ecgBean;
    }

    public final long getSendTime() {
        return this.sendTime;
    }

    public final EcgMeasure getEcgBean() {
        return this.ecgBean;
    }
}
