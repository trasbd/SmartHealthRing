package com.yucheng.smarthealthpro.utils;

import android.text.TextUtils;
import com.yucheng.smarthealthpro.home.bean.DeviceUpgradeInfo;
import com.yucheng.smarthealthpro.home.bean.FirmwareVersionInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: VersionUtils.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u00062\u0006\u0010\u0007\u001a\u00020\u0003\u001a \u0010\b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003\u001a\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e\u001a\u0016\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e¨\u0006\u0011"}, d2 = {"parsePlist", "Lcom/yucheng/smarthealthpro/home/bean/DeviceUpgradeInfo;", "path", "", "parseUpdateVersionToList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "versionStr", "parseDeviceVersionInfo", "bloodVersionStr", "tpVersionString", "checkCanUpgrade", "", "localVersion", "Lcom/yucheng/smarthealthpro/home/bean/FirmwareVersionInfo;", "serverVersion", "checkMandatoryUpgrade", "app_SmartHealthRelease"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class VersionUtilsKt {
    /* JADX WARN: Can't wrap try/catch for region: R(38:84|5|6|90|7|(3:78|8|9)|15|(1:17)(1:18)|19|20|(1:22)(1:23)|24|(1:26)(1:27)|28|(2:30|31)(1:32)|86|33|88|34|35|41|(1:43)(1:44)|45|(1:47)(1:48)|49|(2:51|52)(1:53)|80|54|82|55|62|(1:64)|65|(1:67)(1:68)|69|(1:71)(1:72)|73|74) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0121, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0123, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0124, code lost:
    
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0125, code lost:
    
        r0.printStackTrace();
        r22 = r7;
        r23 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01ab, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01ad, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01ae, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01af, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0080 A[Catch: Exception -> 0x0225, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a0 A[Catch: Exception -> 0x0225, TRY_ENTER, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00bc A[Catch: Exception -> 0x0225, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d8 A[Catch: Exception -> 0x0225, TRY_LEAVE, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0132 A[Catch: Exception -> 0x0225, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x014e A[Catch: Exception -> 0x0225, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0168 A[Catch: Exception -> 0x0225, TRY_LEAVE, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01ba A[Catch: Exception -> 0x0225, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01d5 A[Catch: Exception -> 0x0225, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f3 A[Catch: Exception -> 0x0225, TryCatch #3 {Exception -> 0x0225, blocks: (B:5:0x0026, B:15:0x0078, B:17:0x0080, B:19:0x0098, B:22:0x00a0, B:24:0x00b6, B:26:0x00bc, B:28:0x00d2, B:30:0x00d8, B:41:0x012c, B:43:0x0132, B:45:0x0148, B:47:0x014e, B:49:0x0162, B:51:0x0168, B:62:0x01b2, B:64:0x01ba, B:65:0x01cb, B:67:0x01d5, B:69:0x01eb, B:71:0x01f3, B:73:0x0209, B:61:0x01af, B:40:0x0125, B:14:0x0073), top: B:84:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0207  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.yucheng.smarthealthpro.home.bean.DeviceUpgradeInfo parsePlist(java.lang.String r31) {
        /*
            Method dump skipped, instructions count: 554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.VersionUtilsKt.parsePlist(java.lang.String):com.yucheng.smarthealthpro.home.bean.DeviceUpgradeInfo");
    }

    public static final ArrayList<String> parseUpdateVersionToList(String versionStr) {
        Intrinsics.checkNotNullParameter(versionStr, "versionStr");
        ArrayList<String> arrayList = new ArrayList<>();
        String str = versionStr;
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listSplit$default) {
            if (((String) obj).length() > 0) {
                arrayList2.add(obj);
            }
        }
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    public static final DeviceUpgradeInfo parseDeviceVersionInfo(String versionStr, String bloodVersionStr, String tpVersionString) {
        FirmwareVersionInfo firmwareVersionInfo;
        FirmwareVersionInfo firmwareVersionInfo2;
        FirmwareVersionInfo firmwareVersionInfo3;
        Intrinsics.checkNotNullParameter(versionStr, "versionStr");
        Intrinsics.checkNotNullParameter(bloodVersionStr, "bloodVersionStr");
        Intrinsics.checkNotNullParameter(tpVersionString, "tpVersionString");
        try {
            if (!TextUtils.isEmpty(versionStr)) {
                List listSplit$default = StringsKt.split$default((CharSequence) versionStr, new String[]{"."}, false, 0, 6, (Object) null);
                firmwareVersionInfo = new FirmwareVersionInfo(Integer.parseInt((String) listSplit$default.get(0)), Integer.parseInt((String) listSplit$default.get(1)), null, null, null, 28, null);
            } else {
                firmwareVersionInfo = new FirmwareVersionInfo(0, 0, null, null, null, 28, null);
            }
            FirmwareVersionInfo firmwareVersionInfo4 = firmwareVersionInfo;
            if (!TextUtils.isEmpty(bloodVersionStr)) {
                List listSplit$default2 = StringsKt.split$default((CharSequence) bloodVersionStr, new String[]{"."}, false, 0, 6, (Object) null);
                firmwareVersionInfo2 = new FirmwareVersionInfo(Integer.parseInt((String) listSplit$default2.get(0)), Integer.parseInt((String) listSplit$default2.get(1)), null, null, null, 28, null);
            } else {
                firmwareVersionInfo2 = new FirmwareVersionInfo(0, 0, null, null, null, 28, null);
            }
            if (!TextUtils.isEmpty(tpVersionString)) {
                List listSplit$default3 = StringsKt.split$default((CharSequence) tpVersionString, new String[]{"."}, false, 0, 6, (Object) null);
                firmwareVersionInfo3 = new FirmwareVersionInfo(Integer.parseInt((String) listSplit$default3.get(0)), Integer.parseInt((String) listSplit$default3.get(1)), null, null, null, 28, null);
            } else {
                firmwareVersionInfo3 = new FirmwareVersionInfo(0, 0, null, null, null, 28, null);
            }
            return new DeviceUpgradeInfo(firmwareVersionInfo4, firmwareVersionInfo2, firmwareVersionInfo3, false, 8, null);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final boolean checkCanUpgrade(FirmwareVersionInfo localVersion, FirmwareVersionInfo serverVersion) {
        Intrinsics.checkNotNullParameter(localVersion, "localVersion");
        Intrinsics.checkNotNullParameter(serverVersion, "serverVersion");
        boolean z = serverVersion.getMajorVersion() > localVersion.getMajorVersion() || (serverVersion.getMajorVersion() == localVersion.getMajorVersion() && serverVersion.getSubVersion() > localVersion.getSubVersion());
        if (serverVersion.getAllowUpdateVersion().length() > 0) {
            StringsKt.contains$default((CharSequence) serverVersion.getAllowUpdateVersion(), (CharSequence) localVersion.getVersion(), false, 2, (Object) null);
        }
        return z;
    }

    public static final boolean checkMandatoryUpgrade(FirmwareVersionInfo localVersion, FirmwareVersionInfo serverVersion) {
        Intrinsics.checkNotNullParameter(localVersion, "localVersion");
        Intrinsics.checkNotNullParameter(serverVersion, "serverVersion");
        return StringsKt.contains$default((CharSequence) serverVersion.getAutoUpdateVersions(), (CharSequence) localVersion.getVersion(), false, 2, (Object) null);
    }
}
