package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.Locale;

/* loaded from: classes5.dex */
public class MultiLanguageUtils {
    public static Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.yucheng.smarthealthpro.utils.MultiLanguageUtils.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            String str = (String) SharedPreferencesUtils.get(activity, Constant.SP_LANGUAGE, "");
            String str2 = (String) SharedPreferencesUtils.get(activity, "COUNTRY", "");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            MultiLanguageUtils.setAppLanguage(activity, new Locale(str, str2));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            MultiLanguageUtils.mFinalCount++;
            if (MultiLanguageUtils.mFinalCount == 1) {
                HealthApplication.isBackground = false;
                SharedPreferencesUtils.put(activity, Constant.SpConstKey.isSendNotifyDevice, true);
            }
            MultiLanguageUtils.checkIfResetLanguage(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            MultiLanguageUtils.mFinalCount--;
            if (MultiLanguageUtils.mFinalCount != 0 || activity.isFinishing()) {
                return;
            }
            HealthApplication.isBackground = true;
        }
    };
    private static int mFinalCount;

    public static void changeLanguage(Context context, String language, String area) {
        if (area == null) {
            area = "";
        }
        Locale locale = new Locale(language, area);
        setAppLanguage(context, locale);
        saveLanguageSetting(context, locale);
    }

    public static void resetLanguage(Context context) {
        String str = (String) SharedPreferencesUtils.get(context, Constant.SP_LANGUAGE, "");
        String str2 = (String) SharedPreferencesUtils.get(context, "COUNTRY", "");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setAppLanguage(context, new Locale(str, str2));
    }

    public static void setAppLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        context.createConfigurationContext(configuration);
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static Context attachBaseContext(Context context) {
        String str = (String) SharedPreferencesUtils.get(context, Constant.SP_LANGUAGE, "");
        if (!TextUtils.isEmpty(str)) {
            setAppLanguage(context, new Locale(str));
        }
        return context;
    }

    public static boolean isSameWithSetting(Context context) {
        Locale appLocale = getAppLocale(context);
        String language = appLocale.getLanguage();
        appLocale.toLanguageTag();
        String str = (String) SharedPreferencesUtils.get(context, Constant.SP_LANGUAGE, "");
        return language.equals(str);
    }

    public static void saveLanguageSetting(Context context, Locale locale) {
        SharedPreferencesUtils.put(context, Constant.SP_LANGUAGE, locale.getLanguage());
        SharedPreferencesUtils.put(context, "COUNTRY", locale.getCountry());
    }

    public static Locale getAppLocale(Context context) {
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    public static LocaleListCompat getSystemLanguageList() {
        return ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
    }

    public static Locale getSystemLanguage() {
        return ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
    }

    public static void checkIfResetLanguage(Context activity) {
        String str = (String) SharedPreferencesUtils.get(activity, Constant.SP_LANGUAGE, "");
        String str2 = (String) SharedPreferencesUtils.get(activity, "COUNTRY", "");
        String str3 = (String) SharedPreferencesUtils.get(activity, Constant.SYSTEM_LANGUAGE, "");
        Locale appLocale = getAppLocale(activity);
        Locale systemLanguage = getSystemLanguage();
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.get(activity, "isSystem", true)).booleanValue();
        if (TextUtils.isEmpty(str3) || systemLanguage.toLanguageTag().equals(str3)) {
            if (zBooleanValue) {
                return;
            }
            changeLanguage(activity, str, str2);
            return;
        }
        if (zBooleanValue) {
            changeLanguage(activity, systemLanguage.getLanguage(), systemLanguage.getCountry());
            if (YCBTClient.connectState() == 10) {
                YCBTClient.settingLanguage(getLanguage(appLocale), null);
            }
        } else {
            Locale locale = new Locale(str, str2);
            changeLanguage(activity, str, str2);
            if (YCBTClient.connectState() == 10) {
                YCBTClient.settingLanguage(getLanguage(locale), null);
            }
        }
        SharedPreferencesUtils.put(activity, Constant.SYSTEM_LANGUAGE, systemLanguage.toLanguageTag());
    }

    public static void setConfiguration(Context context) {
        if (context == null) {
            return;
        }
        checkIfResetLanguage(context);
    }

    public static Locale getSysPreferredLocale() {
        return LocaleList.getDefault().get(0);
    }

    public static int getLanguage(Context context) {
        return getLanguage(getAppLocale(context));
    }

    public static String getLanguageStr(Context context) {
        Locale appLocale = getAppLocale(context);
        String language = appLocale.getLanguage();
        if (language.equals("zh")) {
            return language + "_" + appLocale.getCountry();
        }
        return language.equals("in") ? "id" : language;
    }

    public static String getHealthyLanguage(Context context) {
        Locale appLocale = getAppLocale(context);
        String language = appLocale.getLanguage();
        return language.equals("zh") ? language + "-r" + appLocale.getCountry() : language;
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x027d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getLanguage(java.util.Locale r24) {
        /*
            Method dump skipped, instructions count: 1154
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.MultiLanguageUtils.getLanguage(java.util.Locale):int");
    }

    public static boolean isZh(Context context) {
        String str = (String) SharedPreferencesUtils.get(context, Constant.SP_LANGUAGE, "");
        String upperCase = getAppLocale(context).getLanguage().toUpperCase();
        boolean zEquals = "ZH".equals(str.toUpperCase());
        if (!TextUtils.isEmpty(str)) {
            return zEquals;
        }
        if ("ZH".equals(upperCase) || Locale.getDefault().getCountry().toUpperCase().equals("HK") || Locale.getDefault().getCountry().toUpperCase().equals("TW") || "tw".equals(context.getString(R.string.lan))) {
            return true;
        }
        return zEquals;
    }

    public static boolean isEn(Context context) {
        return "EN".equals(getAppLocale(context).getLanguage().toUpperCase());
    }

    public static String getHelpLan() {
        Context applicationContext = MyApplication.getInstance().getApplicationContext();
        applicationContext.getString(R.string.lan);
        getAppLocale(applicationContext);
        if (!isZh(applicationContext)) {
            return "en";
        }
        return "cn";
    }
}
