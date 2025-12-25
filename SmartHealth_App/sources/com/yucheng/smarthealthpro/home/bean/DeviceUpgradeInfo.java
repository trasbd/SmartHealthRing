package com.yucheng.smarthealthpro.home.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.yucheng.smarthealthpro.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceUpgradeInfo.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/yucheng/smarthealthpro/home/bean/DeviceUpgradeInfo;", "", "osVersion", "Lcom/yucheng/smarthealthpro/home/bean/FirmwareVersionInfo;", "bpVersion", Constant.SpConstKey.tpVersion, "onlyShowOtaPage", "", "<init>", "(Lcom/yucheng/smarthealthpro/home/bean/FirmwareVersionInfo;Lcom/yucheng/smarthealthpro/home/bean/FirmwareVersionInfo;Lcom/yucheng/smarthealthpro/home/bean/FirmwareVersionInfo;Z)V", "getOsVersion", "()Lcom/yucheng/smarthealthpro/home/bean/FirmwareVersionInfo;", "getBpVersion", "getTpVersion", "getOnlyShowOtaPage", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class DeviceUpgradeInfo {
    private final FirmwareVersionInfo bpVersion;
    private final boolean onlyShowOtaPage;
    private final FirmwareVersionInfo osVersion;
    private final FirmwareVersionInfo tpVersion;

    public static /* synthetic */ DeviceUpgradeInfo copy$default(DeviceUpgradeInfo deviceUpgradeInfo, FirmwareVersionInfo firmwareVersionInfo, FirmwareVersionInfo firmwareVersionInfo2, FirmwareVersionInfo firmwareVersionInfo3, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            firmwareVersionInfo = deviceUpgradeInfo.osVersion;
        }
        if ((i2 & 2) != 0) {
            firmwareVersionInfo2 = deviceUpgradeInfo.bpVersion;
        }
        if ((i2 & 4) != 0) {
            firmwareVersionInfo3 = deviceUpgradeInfo.tpVersion;
        }
        if ((i2 & 8) != 0) {
            z = deviceUpgradeInfo.onlyShowOtaPage;
        }
        return deviceUpgradeInfo.copy(firmwareVersionInfo, firmwareVersionInfo2, firmwareVersionInfo3, z);
    }

    /* renamed from: component1, reason: from getter */
    public final FirmwareVersionInfo getOsVersion() {
        return this.osVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final FirmwareVersionInfo getBpVersion() {
        return this.bpVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final FirmwareVersionInfo getTpVersion() {
        return this.tpVersion;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getOnlyShowOtaPage() {
        return this.onlyShowOtaPage;
    }

    public final DeviceUpgradeInfo copy(FirmwareVersionInfo osVersion, FirmwareVersionInfo bpVersion, FirmwareVersionInfo tpVersion, boolean onlyShowOtaPage) {
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(bpVersion, "bpVersion");
        Intrinsics.checkNotNullParameter(tpVersion, "tpVersion");
        return new DeviceUpgradeInfo(osVersion, bpVersion, tpVersion, onlyShowOtaPage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceUpgradeInfo)) {
            return false;
        }
        DeviceUpgradeInfo deviceUpgradeInfo = (DeviceUpgradeInfo) other;
        return Intrinsics.areEqual(this.osVersion, deviceUpgradeInfo.osVersion) && Intrinsics.areEqual(this.bpVersion, deviceUpgradeInfo.bpVersion) && Intrinsics.areEqual(this.tpVersion, deviceUpgradeInfo.tpVersion) && this.onlyShowOtaPage == deviceUpgradeInfo.onlyShowOtaPage;
    }

    public int hashCode() {
        return (((((this.osVersion.hashCode() * 31) + this.bpVersion.hashCode()) * 31) + this.tpVersion.hashCode()) * 31) + Boolean.hashCode(this.onlyShowOtaPage);
    }

    public String toString() {
        return "DeviceUpgradeInfo(osVersion=" + this.osVersion + ", bpVersion=" + this.bpVersion + ", tpVersion=" + this.tpVersion + ", onlyShowOtaPage=" + this.onlyShowOtaPage + ")";
    }

    public DeviceUpgradeInfo(FirmwareVersionInfo osVersion, FirmwareVersionInfo bpVersion, FirmwareVersionInfo tpVersion, boolean z) {
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(bpVersion, "bpVersion");
        Intrinsics.checkNotNullParameter(tpVersion, "tpVersion");
        this.osVersion = osVersion;
        this.bpVersion = bpVersion;
        this.tpVersion = tpVersion;
        this.onlyShowOtaPage = z;
    }

    public /* synthetic */ DeviceUpgradeInfo(FirmwareVersionInfo firmwareVersionInfo, FirmwareVersionInfo firmwareVersionInfo2, FirmwareVersionInfo firmwareVersionInfo3, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(firmwareVersionInfo, firmwareVersionInfo2, firmwareVersionInfo3, (i2 & 8) != 0 ? false : z);
    }

    public final FirmwareVersionInfo getOsVersion() {
        return this.osVersion;
    }

    public final FirmwareVersionInfo getBpVersion() {
        return this.bpVersion;
    }

    public final FirmwareVersionInfo getTpVersion() {
        return this.tpVersion;
    }

    public final boolean getOnlyShowOtaPage() {
        return this.onlyShowOtaPage;
    }
}
