package com.yucheng.smarthealthpro.framework.catchexception;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
final class SettingsCollector {
    private static final String LOG_TAG = "SetingsCollector";
    private final Context mContext;

    public SettingsCollector(Context context) {
        this.mContext = context;
    }

    public String collectSystemSettings() throws SecurityException {
        StringBuilder sb = new StringBuilder();
        for (Field field : Settings.System.class.getFields()) {
            if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class) {
                try {
                    String string = Settings.System.getString(this.mContext.getContentResolver(), (String) field.get(null));
                    if (string != null) {
                        sb.append(field.getName()).append("=").append((Object) string).append("\\n");
                    }
                } catch (Exception e2) {
                    Log.w(LOG_TAG, "Error:", e2);
                }
            }
        }
        return sb.toString();
    }

    public String CoolectSecureSettings() throws SecurityException {
        StringBuilder sb = new StringBuilder();
        for (Field field : Settings.Secure.class.getFields()) {
            if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class && isAuthorized(field)) {
                try {
                    String string = Settings.Secure.getString(this.mContext.getContentResolver(), (String) field.get(null));
                    if (string != null) {
                        sb.append(field.getName()).append("=").append((Object) string).append("\\n");
                    }
                } catch (Exception e2) {
                    Log.w(LOG_TAG, "Error", e2);
                }
            }
        }
        return sb.toString();
    }

    public String collectGlobalSettins() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        Object objInvoke;
        StringBuilder sb = new StringBuilder();
        try {
            Class<?> cls = Class.forName("android.provider.Settings$Global");
            Field[] fields = cls.getFields();
            Method method = cls.getMethod("getString", ContentResolver.class, String.class);
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class && isAuthorized(field) && (objInvoke = method.invoke(null, this.mContext.getContentResolver(), field.get(null))) != null) {
                    sb.append(field.getName()).append("=").append(objInvoke).append("\\n");
                }
            }
        } catch (Exception e2) {
            Log.w(LOG_TAG, "Error", e2);
        }
        return sb.toString();
    }

    private boolean isAuthorized(Field field) {
        return field != null && field.getName().startsWith("WIFI_AP");
    }
}
