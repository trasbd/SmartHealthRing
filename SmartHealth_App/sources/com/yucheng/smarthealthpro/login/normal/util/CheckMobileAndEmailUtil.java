package com.yucheng.smarthealthpro.login.normal.util;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class CheckMobileAndEmailUtil {
    private static final String CHINA_CMCC_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[78]|5[0-27-9]|78|8[2-478]|98)\\d{8}$)|(?:^(?:\\+86)?1440\\d{7}$)|(?:^(?:\\+86)?170[356]\\d{7}$)";
    private static final String CHINA_MOBILE_PATTERN = "^1[3-9]\\d{9}$";
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|49|53|7[37]|8[019]|99)\\d{8}$)|(?:^(?:\\+86)?1349\\d{7}$)|(?:^(?:\\+86)?1410\\d{7}$)|(?:^(?:\\+86)?170[0-2]\\d{7}$)|(?:^(?:\\+86)?191\\d{8}$)";
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[56]|5[56]|66|7[156]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[47-9]\\d{7}$)";

    public static boolean checkPhone(String phone) {
        return !TextUtils.isEmpty(phone) && checkChinaMobile(phone);
    }

    public static boolean checkChinaMobile(String phone) {
        return !TextUtils.isEmpty(phone) && Pattern.compile(CHINA_MOBILE_PATTERN).matcher(phone).matches();
    }

    public static boolean checkCMCCMobile(String phone) {
        return !TextUtils.isEmpty(phone) && Pattern.compile(CHINA_CMCC_PATTERN).matcher(phone).matches();
    }

    public static boolean checkChinaUnicom(String phone) {
        return !TextUtils.isEmpty(phone) && Pattern.compile(CHINA_UNICOM_PATTERN).matcher(phone).matches();
    }

    public static boolean checkChinaTelecom(String phone) {
        return !TextUtils.isEmpty(phone) && Pattern.compile(CHINA_TELECOM_PATTERN).matcher(phone).matches();
    }

    public static String hideMiddleMobile(String phone) {
        return !TextUtils.isEmpty(phone) ? phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2") : phone;
    }

    public static boolean isEmail(String strEmail) {
        if (TextUtils.isEmpty("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$")) {
            return false;
        }
        return strEmail.matches("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
    }

    public static boolean checkALL(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        return checkChinaMobile(name) || isEmail(name);
    }
}
