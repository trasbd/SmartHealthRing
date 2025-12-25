package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.orhanobut.logger.Logger;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.BuildConfig;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.activity.PermissionActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.CustomDialog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class PermissionUtil {
    public static boolean isIgnoringBatteryOptimizations(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager != null) {
            return powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
        }
        return false;
    }

    public static void requestIgnoreBatteryOptimizations(Context context) {
        try {
            Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void gotoPermission(Context context) {
        String str = Build.BRAND;
        if (str != null) {
            String lowerCase = str.toLowerCase();
            lowerCase.hashCode();
            switch (lowerCase) {
                case "huawei":
                case "honor":
                    gotoHuaweiPermission(context);
                    break;
                case "xiaomi":
                case "redmi":
                    gotoMiuiPermission(context);
                    break;
                case "meizu":
                    gotoMeizuPermission(context);
                    break;
                default:
                    startAppDetailSetting(context);
                    break;
            }
        }
        startAppDetailSetting(context);
    }

    public static boolean isHonor() {
        String str = Build.BRAND;
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        if (!"honor".equals(lowerCase) && !"huawei".equals(lowerCase)) {
            return false;
        }
        Logger.d("isHonor");
        return true;
    }

    private static void gotoMiuiPermission(Context context) {
        try {
            try {
                Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.putExtra("extra_pkgname", context.getPackageName());
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity"));
                context.startActivity(intent);
            } catch (Exception unused) {
                startAppDetailSetting(context);
            }
        } catch (Exception unused2) {
            Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent2.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent2.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(intent2);
        }
    }

    private static void gotoMeizuPermission(Context context) {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            startAppDetailSetting(context);
        }
    }

    private static void gotoHuaweiPermission(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            startAppDetailSetting(context);
        }
    }

    private static void startAppDetailSetting(Context context) {
        try {
            Intent intent = new Intent();
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isPermission(Context context, String[] permissions) {
        for (String str : permissions) {
            int iCheckSelfPermission = ContextCompat.checkSelfPermission(context, str);
            if (!str.isEmpty() && iCheckSelfPermission != 0) {
                return false;
            }
        }
        return true;
    }

    public static void openPermission(Activity context, String[] permissions) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : permissions) {
            if (!str.isEmpty() && ContextCompat.checkSelfPermission(context, str) != 0) {
                if ("android.permission.ACCESS_BACKGROUND_LOCATION".equals(str) || Permission.ACCESS_COARSE_LOCATION.equals(str) || Permission.ACCESS_FINE_LOCATION.equals(str)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(context, str) && !Tools.readBoolean(str, context, false)) || Tools.readBoolean("location_first", context, true)) {
                        Tools.saveBoolean(str, true, context);
                        Tools.saveBoolean("location_first", false, context);
                        arrayList.add(str);
                    }
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(context, str) || !Tools.readBoolean(str, context, false)) {
                    Tools.saveBoolean(str, true, context);
                    arrayList.add(str);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            ActivityCompat.requestPermissions(context, permissions, 1);
        } else {
            gotoPermission(context);
        }
    }

    public static boolean isNotificationEnable(Context context) {
        String packageName = context.getPackageName();
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(string)) {
            for (String str : string.split(":")) {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                if (componentNameUnflattenFromString != null && TextUtils.equals(packageName, componentNameUnflattenFromString.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void openNotificationSetting(Context context) {
        try {
            context.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                Intent intent = new Intent();
                intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationAccessSettingsActivity"));
                intent.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                context.startActivity(intent);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public static boolean checkOtaPermission(Activity context) {
        String[] strArr = {Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 33) {
            return true;
        }
        return openPermission(context, strArr, context.getString(R.string.premission_sd_card), true);
    }

    public static boolean openSDCardPermission(Activity context) {
        return openSDCardPermission(context, true);
    }

    public static boolean openSDCardPermission(Activity context, boolean showDialog) {
        String[] strArr = {Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 33) {
            strArr = new String[]{"android.permission.READ_MEDIA_IMAGES"};
        }
        return openPermission(context, strArr, context.getString(R.string.premission_sd_card), showDialog);
    }

    public static boolean openCameraPermission(Activity context) {
        return openCameraPermission(context, true);
    }

    public static boolean openCameraPermission(Activity context, boolean showDialog) {
        return openPermission(context, new String[]{Permission.CAMERA}, context.getString(R.string.permission_camera_card), showDialog);
    }

    public static boolean openPermission(Activity context, String[] permissions, String content, boolean showDialog) {
        if (isPermission(context, permissions)) {
            return true;
        }
        if (!showDialog) {
            return false;
        }
        initDialog(context, content);
        return false;
    }

    private static void initDialog(final Context context, String content) {
        final CommonDialog commonDialog = new CommonDialog(context);
        commonDialog.setMessage(content).setTitle(context.getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.utils.PermissionUtil.1
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                context.startActivity(new Intent(context, (Class<?>) PermissionActivity.class));
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void gotoServicePermission(android.content.Context r3) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.PermissionUtil.gotoServicePermission(android.content.Context):void");
    }

    private static void showActivity(String packageName, Context context) throws Exception {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
    }

    private static void showActivity(String packageName, String activityDir, Context context) throws Exception {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
        context.startActivity(intent);
    }

    public static void showPermissionTipDialog(Context context, String title, String content, DialogInterface.OnLeftAndRightClickListener<MDAlertDialog> onLeftAndRightClickListener) {
        ((CustomDialog.Builder) new CustomDialog.Builder(context).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(title).setTitleTextColor(R.color.black).setContentText(content).setContentTextColor(R.color.black_light).setLeftButtonText(context.getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(context.getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(18).setContentTextSize(16).setButtonTextSize(16).setOnclickListener(onLeftAndRightClickListener)).build().show();
    }
}
