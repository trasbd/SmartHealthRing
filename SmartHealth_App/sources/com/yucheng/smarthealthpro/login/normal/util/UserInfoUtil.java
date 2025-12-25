package com.yucheng.smarthealthpro.login.normal.util;

import android.content.Context;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.login.normal.bean.UserBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class UserInfoUtil {
    public static void setUserInfo(UserBean bean, Context context) {
        SharedPreferencesUtils.put(context, Constant.SpConstKey.DEV_ID, bean.data.devId);
        SharedPreferencesUtils.put(context, Constant.SpConstKey.USER_NAME, bean.data.username == null ? "" : bean.data.username);
        try {
            Logger.d("setUserInfo", "encode=" + URLEncoder.encode(bean.data.token, "UTF-8"));
            SharedPreferencesUtils.put(context, Constant.SpConstKey.TOKEN, bean.data.token);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        SharedPreferencesUtils.put(context, Constant.SpConstKey.NICK_NAME, bean.data.nickname);
        SharedPreferencesUtils.put(context, Constant.SpConstKey.HEAD_IMG, bean.data.headimg);
        SharedPreferencesUtils.put(context, Constant.SpConstKey.AGE, Integer.valueOf(bean.data.age));
        String strSubYear = bean.data.birthday == null ? YearToDayListUtils.subYear(bean.data.age) : bean.data.birthday;
        if (strSubYear.contains(StringUtils.SPACE)) {
            strSubYear = strSubYear.split(StringUtils.SPACE)[0];
        }
        SharedPreferencesUtils.put(context, Constant.SpConstKey.BIRTH_DATE, strSubYear);
        SharedPreferencesUtils.put(context, Constant.SpConstKey.SEX, Integer.valueOf(bean.data.sex));
        SharedPreferencesUtils.put(context, Constant.SpConstKey.WEIGHT, Integer.valueOf(bean.data.weight));
        SharedPreferencesUtils.put(context, "height", Integer.valueOf(bean.data.height));
        SharedPreferencesUtils.put(context, Constant.SpConstKey.IS_LOGIN, true);
        SharedPreferencesUtils.put(context, Constant.SpConstKey.INFO_FIRST_CHANGE, Integer.valueOf(bean.data.infoFirstChange));
        SharedPreferencesUtils.put(context, Constant.SpConstKey.SKIN_COLOR, Integer.valueOf(bean.data.skin));
    }

    public static int getAge(Date birthDate) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.before(birthDate)) {
            throw new IllegalArgumentException("The birth date is before current time, it's unbelievable");
        }
        int i2 = calendar.get(1);
        int i3 = calendar.get(2);
        int i4 = calendar.get(5);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(birthDate);
        int i5 = calendar2.get(1);
        int i6 = calendar2.get(2);
        int i7 = i2 - i5;
        return (i3 >= i6 && (i3 != i6 || i4 >= calendar2.get(5))) ? i7 : i7 - 1;
    }

    public static String getUserName() {
        return SharedPreferencesUtils.get(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.USER_NAME, "").toString();
    }

    public static String getUserNickName() {
        return SharedPreferencesUtils.get(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.NICK_NAME, "").toString();
    }
}
