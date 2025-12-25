package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionHelper.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\u0002\u0010\u0010J1\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/PermissionHelper;", "", "<init>", "()V", "REQUEST_PERMISSION_STORAGE", "", "checkPermission", "", "activity", "Landroid/app/Activity;", "permissions", "", "", "requestCode", "callback", "Lkotlin/Function0;", "(Landroid/app/Activity;[Ljava/lang/String;ILkotlin/jvm/functions/Function0;)V", "onRequestPermissionsResult", "grantResults", "", "(Landroid/app/Activity;I[Ljava/lang/String;[I)V", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PermissionHelper {
    public static final PermissionHelper INSTANCE = new PermissionHelper();
    public static final int REQUEST_PERMISSION_STORAGE = 1001;

    private PermissionHelper() {
    }

    public final void checkPermission(Activity activity, String[] permissions, int requestCode, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(callback, "callback");
        for (String str : permissions) {
            if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode);
                return;
            }
        }
        callback.invoke();
    }

    public final void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (requestCode == 1001) {
            int length = grantResults.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (grantResults[i2] != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i2])) {
                    Activity activity2 = activity;
                    ToastUtil.getInstance(activity2).toast(activity.getString(R.string.premission_sd_card));
                    PermissionUtil.gotoPermission(activity2);
                    return;
                }
            }
        }
    }
}
