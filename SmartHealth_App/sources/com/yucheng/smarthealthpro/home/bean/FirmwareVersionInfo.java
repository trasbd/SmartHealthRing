package com.yucheng.smarthealthpro.home.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.internal.ServerProtocol;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceUpgradeInfo.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/yucheng/smarthealthpro/home/bean/FirmwareVersionInfo;", "", "majorVersion", "", "subVersion", "firmwareUrl", "", "autoUpdateVersions", "allowUpdateVersion", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMajorVersion", "()I", "getSubVersion", "getFirmwareUrl", "()Ljava/lang/String;", "getAutoUpdateVersions", "getAllowUpdateVersion", ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "getVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class FirmwareVersionInfo {
    private final String allowUpdateVersion;
    private final String autoUpdateVersions;
    private final String firmwareUrl;
    private final int majorVersion;
    private final int subVersion;

    public static /* synthetic */ FirmwareVersionInfo copy$default(FirmwareVersionInfo firmwareVersionInfo, int i2, int i3, String str, String str2, String str3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = firmwareVersionInfo.majorVersion;
        }
        if ((i4 & 2) != 0) {
            i3 = firmwareVersionInfo.subVersion;
        }
        int i5 = i3;
        if ((i4 & 4) != 0) {
            str = firmwareVersionInfo.firmwareUrl;
        }
        String str4 = str;
        if ((i4 & 8) != 0) {
            str2 = firmwareVersionInfo.autoUpdateVersions;
        }
        String str5 = str2;
        if ((i4 & 16) != 0) {
            str3 = firmwareVersionInfo.allowUpdateVersion;
        }
        return firmwareVersionInfo.copy(i2, i5, str4, str5, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMajorVersion() {
        return this.majorVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSubVersion() {
        return this.subVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFirmwareUrl() {
        return this.firmwareUrl;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAutoUpdateVersions() {
        return this.autoUpdateVersions;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAllowUpdateVersion() {
        return this.allowUpdateVersion;
    }

    public final FirmwareVersionInfo copy(int majorVersion, int subVersion, String firmwareUrl, String autoUpdateVersions, String allowUpdateVersion) {
        Intrinsics.checkNotNullParameter(firmwareUrl, "firmwareUrl");
        Intrinsics.checkNotNullParameter(autoUpdateVersions, "autoUpdateVersions");
        Intrinsics.checkNotNullParameter(allowUpdateVersion, "allowUpdateVersion");
        return new FirmwareVersionInfo(majorVersion, subVersion, firmwareUrl, autoUpdateVersions, allowUpdateVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirmwareVersionInfo)) {
            return false;
        }
        FirmwareVersionInfo firmwareVersionInfo = (FirmwareVersionInfo) other;
        return this.majorVersion == firmwareVersionInfo.majorVersion && this.subVersion == firmwareVersionInfo.subVersion && Intrinsics.areEqual(this.firmwareUrl, firmwareVersionInfo.firmwareUrl) && Intrinsics.areEqual(this.autoUpdateVersions, firmwareVersionInfo.autoUpdateVersions) && Intrinsics.areEqual(this.allowUpdateVersion, firmwareVersionInfo.allowUpdateVersion);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.majorVersion) * 31) + Integer.hashCode(this.subVersion)) * 31) + this.firmwareUrl.hashCode()) * 31) + this.autoUpdateVersions.hashCode()) * 31) + this.allowUpdateVersion.hashCode();
    }

    public String toString() {
        return "FirmwareVersionInfo(majorVersion=" + this.majorVersion + ", subVersion=" + this.subVersion + ", firmwareUrl=" + this.firmwareUrl + ", autoUpdateVersions=" + this.autoUpdateVersions + ", allowUpdateVersion=" + this.allowUpdateVersion + ")";
    }

    public FirmwareVersionInfo(int i2, int i3, String firmwareUrl, String autoUpdateVersions, String allowUpdateVersion) {
        Intrinsics.checkNotNullParameter(firmwareUrl, "firmwareUrl");
        Intrinsics.checkNotNullParameter(autoUpdateVersions, "autoUpdateVersions");
        Intrinsics.checkNotNullParameter(allowUpdateVersion, "allowUpdateVersion");
        this.majorVersion = i2;
        this.subVersion = i3;
        this.firmwareUrl = firmwareUrl;
        this.autoUpdateVersions = autoUpdateVersions;
        this.allowUpdateVersion = allowUpdateVersion;
    }

    public /* synthetic */ FirmwareVersionInfo(int i2, int i3, String str, String str2, String str3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, (i4 & 4) != 0 ? "" : str, (i4 & 8) != 0 ? "" : str2, (i4 & 16) != 0 ? "" : str3);
    }

    public final int getMajorVersion() {
        return this.majorVersion;
    }

    public final int getSubVersion() {
        return this.subVersion;
    }

    public final String getFirmwareUrl() {
        return this.firmwareUrl;
    }

    public final String getAutoUpdateVersions() {
        return this.autoUpdateVersions;
    }

    public final String getAllowUpdateVersion() {
        return this.allowUpdateVersion;
    }

    public final String getVersion() {
        String str = String.format("%d.%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.majorVersion), Integer.valueOf(this.subVersion)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
