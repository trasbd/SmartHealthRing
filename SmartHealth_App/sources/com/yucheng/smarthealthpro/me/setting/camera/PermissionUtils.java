package com.yucheng.smarthealthpro.me.setting.camera;

import android.content.Context;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

/* loaded from: classes5.dex */
public class PermissionUtils {

    public interface PermissionListener {
        void onFailed(Context context);

        void onSuccess(Context context);
    }

    public static void applicationPermissions(final Context context, final PermissionListener listener, String[]... permissions) {
        if (!AndPermission.hasPermissions(context, permissions)) {
            AndPermission.with(context).runtime().permission(permissions).rationale(new Rationale() { // from class: com.yucheng.smarthealthpro.me.setting.camera.PermissionUtils$$ExternalSyntheticLambda0
                @Override // com.yanzhenjie.permission.Rationale
                public final void showRationale(Context context2, Object obj, RequestExecutor requestExecutor) {
                    requestExecutor.execute();
                }
            }).onGranted(new Action() { // from class: com.yucheng.smarthealthpro.me.setting.camera.PermissionUtils$$ExternalSyntheticLambda1
                @Override // com.yanzhenjie.permission.Action
                public final void onAction(Object obj) {
                    listener.onSuccess(context);
                }
            }).onDenied(new Action() { // from class: com.yucheng.smarthealthpro.me.setting.camera.PermissionUtils$$ExternalSyntheticLambda2
                @Override // com.yanzhenjie.permission.Action
                public final void onAction(Object obj) {
                    listener.onFailed(context);
                }
            }).start();
        } else {
            listener.onSuccess(context);
        }
    }

    public static void applicationPermissions(final Context context, final PermissionListener listener, String... permissions) {
        if (!AndPermission.hasPermissions(context, permissions)) {
            AndPermission.with(context).runtime().permission(permissions).rationale(new Rationale() { // from class: com.yucheng.smarthealthpro.me.setting.camera.PermissionUtils$$ExternalSyntheticLambda3
                @Override // com.yanzhenjie.permission.Rationale
                public final void showRationale(Context context2, Object obj, RequestExecutor requestExecutor) {
                    requestExecutor.execute();
                }
            }).onGranted(new Action() { // from class: com.yucheng.smarthealthpro.me.setting.camera.PermissionUtils$$ExternalSyntheticLambda4
                @Override // com.yanzhenjie.permission.Action
                public final void onAction(Object obj) {
                    listener.onSuccess(context);
                }
            }).onDenied(new Action() { // from class: com.yucheng.smarthealthpro.me.setting.camera.PermissionUtils$$ExternalSyntheticLambda5
                @Override // com.yanzhenjie.permission.Action
                public final void onAction(Object obj) {
                    listener.onFailed(context);
                }
            }).start();
        } else {
            listener.onSuccess(context);
        }
    }
}
