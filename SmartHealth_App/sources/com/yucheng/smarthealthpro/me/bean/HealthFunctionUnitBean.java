package com.yucheng.smarthealthpro.me.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HealthFunctionUnitBean.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/yucheng/smarthealthpro/me/bean/HealthFunctionUnitBean;", "", "title", "", "enable", "", "type", "Lcom/yucheng/smarthealthpro/me/bean/HealthFunctionType;", "<init>", "(Ljava/lang/String;ZLcom/yucheng/smarthealthpro/me/bean/HealthFunctionType;)V", "getTitle", "()Ljava/lang/String;", "getEnable", "()Z", "setEnable", "(Z)V", "getType", "()Lcom/yucheng/smarthealthpro/me/bean/HealthFunctionType;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class HealthFunctionUnitBean {
    private boolean enable;
    private final String title;
    private final HealthFunctionType type;

    public static /* synthetic */ HealthFunctionUnitBean copy$default(HealthFunctionUnitBean healthFunctionUnitBean, String str, boolean z, HealthFunctionType healthFunctionType, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = healthFunctionUnitBean.title;
        }
        if ((i2 & 2) != 0) {
            z = healthFunctionUnitBean.enable;
        }
        if ((i2 & 4) != 0) {
            healthFunctionType = healthFunctionUnitBean.type;
        }
        return healthFunctionUnitBean.copy(str, z, healthFunctionType);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getEnable() {
        return this.enable;
    }

    /* renamed from: component3, reason: from getter */
    public final HealthFunctionType getType() {
        return this.type;
    }

    public final HealthFunctionUnitBean copy(String title, boolean enable, HealthFunctionType type) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        return new HealthFunctionUnitBean(title, enable, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthFunctionUnitBean)) {
            return false;
        }
        HealthFunctionUnitBean healthFunctionUnitBean = (HealthFunctionUnitBean) other;
        return Intrinsics.areEqual(this.title, healthFunctionUnitBean.title) && this.enable == healthFunctionUnitBean.enable && this.type == healthFunctionUnitBean.type;
    }

    public int hashCode() {
        return (((this.title.hashCode() * 31) + Boolean.hashCode(this.enable)) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "HealthFunctionUnitBean(title=" + this.title + ", enable=" + this.enable + ", type=" + this.type + ")";
    }

    public HealthFunctionUnitBean(String title, boolean z, HealthFunctionType type) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        this.title = title;
        this.enable = z;
        this.type = type;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    public final HealthFunctionType getType() {
        return this.type;
    }
}
