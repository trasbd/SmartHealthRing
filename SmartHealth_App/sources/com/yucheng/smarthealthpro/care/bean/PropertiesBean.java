package com.yucheng.smarthealthpro.care.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PropertiesBean.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/yucheng/smarthealthpro/care/bean/PropertiesBean;", "", "logUploadEnabled", "", "logMacAddress", "", "", "<init>", "(ZLjava/util/List;)V", "getLogUploadEnabled", "()Z", "getLogMacAddress", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class PropertiesBean {
    private final List<String> logMacAddress;
    private final boolean logUploadEnabled;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PropertiesBean copy$default(PropertiesBean propertiesBean, boolean z, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = propertiesBean.logUploadEnabled;
        }
        if ((i2 & 2) != 0) {
            list = propertiesBean.logMacAddress;
        }
        return propertiesBean.copy(z, list);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getLogUploadEnabled() {
        return this.logUploadEnabled;
    }

    public final List<String> component2() {
        return this.logMacAddress;
    }

    public final PropertiesBean copy(boolean logUploadEnabled, List<String> logMacAddress) {
        Intrinsics.checkNotNullParameter(logMacAddress, "logMacAddress");
        return new PropertiesBean(logUploadEnabled, logMacAddress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PropertiesBean)) {
            return false;
        }
        PropertiesBean propertiesBean = (PropertiesBean) other;
        return this.logUploadEnabled == propertiesBean.logUploadEnabled && Intrinsics.areEqual(this.logMacAddress, propertiesBean.logMacAddress);
    }

    public int hashCode() {
        return (Boolean.hashCode(this.logUploadEnabled) * 31) + this.logMacAddress.hashCode();
    }

    public String toString() {
        return "PropertiesBean(logUploadEnabled=" + this.logUploadEnabled + ", logMacAddress=" + this.logMacAddress + ")";
    }

    public PropertiesBean(boolean z, List<String> logMacAddress) {
        Intrinsics.checkNotNullParameter(logMacAddress, "logMacAddress");
        this.logUploadEnabled = z;
        this.logMacAddress = logMacAddress;
    }

    public final boolean getLogUploadEnabled() {
        return this.logUploadEnabled;
    }

    public final List<String> getLogMacAddress() {
        return this.logMacAddress;
    }
}
