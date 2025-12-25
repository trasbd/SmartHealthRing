package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.LocationActivity;
import com.yucheng.smarthealthpro.care.bean.CareSleepWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodFatBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodSugarBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareHeartBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareKetoneBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareRateBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareSpo2Bean;
import com.yucheng.smarthealthpro.care.bean.FriendCareTempBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareUricAcidBean;
import com.yucheng.smarthealthpro.care.bean.HistoryBloodResponse;
import com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse;
import com.yucheng.smarthealthpro.care.bean.HistoryHeartResponse;
import com.yucheng.smarthealthpro.care.bean.HistoryPressureResponse;
import com.yucheng.smarthealthpro.care.bean.HistorySleepResponse;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.home.bean.MyMonBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.json.HTTP;

/* loaded from: classes5.dex */
public class Tools {
    public static final String BasePath = Environment.getExternalStorageDirectory().getPath() + "/SmartAM/";

    public static void saveFirmwareVersion(Context context, String version) {
        SharedPreferencesUtils.put(context, "firmware_version", version);
    }

    public static String getFirmwareVersion(Context context) {
        return (String) SharedPreferencesUtils.get(context, "firmware_version", "");
    }

    public static String getDeviceType(Context context) {
        return (String) YCBTClient.readDeviceInfo(Constants.FunctionConstant.DEVICETYPE);
    }

    public static String getDeviceMac() {
        return YCBTClient.getBindDeviceMac();
    }

    public static int getTimeOffset() {
        return TimeZone.getDefault().getOffset(System.currentTimeMillis());
    }

    public static void writeFile(String str, String pathname) throws IOException {
        String path = Environment.getExternalStorageDirectory().getPath();
        Log.i("glttest", "path:" + path);
        File file = new File(path, pathname);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            String str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date(System.currentTimeMillis()));
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeUTF(str2 + "-----" + str + StringUtils.LF);
            randomAccessFile.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static String readFile(String path) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return str;
                }
                str = str + line;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int getwindowwidth(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void showAlert3(Context context, String msg) {
        Toast.makeText(context, msg, 0).show();
    }

    public static String readString(String key, Context context, String defaultValue) {
        return context.getSharedPreferences("ycblespinfo", 0).getString(key, defaultValue);
    }

    public static void showtext(String str, int lengNum) {
        int length = str.length() / lengNum;
        if (length > 0) {
            if (str.length() % lengNum > 0) {
                length++;
            }
            String str2 = "";
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (i3 == length - 1) {
                    str2 = str2 + str.substring(i2, str.length() - 1);
                } else {
                    int i4 = i2 + lengNum;
                    str2 = str2 + str.substring(i2, i4) + HTTP.CRLF;
                    i2 = i4;
                }
            }
            Log.e("eeeeee", "aaaaaaaaaaa" + str2);
            return;
        }
        Log.e("aaaaaaaaaaa", str);
    }

    public static int readUnit(int key, Context context) {
        int i2 = context.getSharedPreferences("ycblespinfo", 0).getInt(Constant.SpConstKey.UNIT + key, -1);
        if (i2 == -1 && (key == 1 || key == 2 || key == 3 || key == 4 || key == 5)) {
            return 0;
        }
        return i2;
    }

    public static boolean readLogin(Context context) {
        return ((Boolean) SharedPreferencesUtils.get(context, Constant.SpConstKey.IS_LOGIN, false)).booleanValue();
    }

    public static void saveFilePath(String filepath, Context context) {
        context.getSharedPreferences("ycblespinfo", 0).edit().putString("file_path", filepath).commit();
    }

    public static String getFilePath(Context context) {
        return context.getSharedPreferences("ycblespinfo", 0).getString("file_path", null);
    }

    public static String transformNowTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(time));
    }

    public static List<TemperatureHisListBean> removeTempRepeatData(List<TemperatureHisListBean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).getTime().equals(list.get(i2).getTime())) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortTempRepeatData$0(TemperatureHisListBean temperatureHisListBean, TemperatureHisListBean temperatureHisListBean2) {
        return (int) (Long.parseLong(temperatureHisListBean2.getTime()) - Long.parseLong(temperatureHisListBean.getTime()));
    }

    public static List<TemperatureHisListBean> sortTempRepeatData(List<TemperatureHisListBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda5
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortTempRepeatData$0((TemperatureHisListBean) obj, (TemperatureHisListBean) obj2);
            }
        });
        return list;
    }

    public static List<FriendCareTempBean> removeCareTempRepeatData(List<FriendCareTempBean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortCareTempRepeatData$1(FriendCareTempBean friendCareTempBean, FriendCareTempBean friendCareTempBean2) {
        return (int) (friendCareTempBean2.rtime - friendCareTempBean.rtime);
    }

    public static List<FriendCareTempBean> sortCareTempRepeatData(List<FriendCareTempBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareTempRepeatData$1((FriendCareTempBean) obj, (FriendCareTempBean) obj2);
            }
        });
        return list;
    }

    public static List<FriendCareRateBean> removeCareRateData(List<FriendCareRateBean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortCareRateData$2(FriendCareRateBean friendCareRateBean, FriendCareRateBean friendCareRateBean2) {
        return (int) (friendCareRateBean2.rtime - friendCareRateBean.rtime);
    }

    public static List<FriendCareRateBean> sortCareRateData(List<FriendCareRateBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda9
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareRateData$2((FriendCareRateBean) obj, (FriendCareRateBean) obj2);
            }
        });
        return list;
    }

    public static List<HistoryHeartResponse.Mlist> removeHeartData(List<HistoryHeartResponse.Mlist> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortHeartData$3(HistoryHeartResponse.Mlist mlist, HistoryHeartResponse.Mlist mlist2) {
        return (int) (mlist2.rtime - mlist.rtime);
    }

    public static List<HistoryHeartResponse.Mlist> sortHeartData(List<HistoryHeartResponse.Mlist> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortHeartData$3((HistoryHeartResponse.Mlist) obj, (HistoryHeartResponse.Mlist) obj2);
            }
        });
        return list;
    }

    public static List<FriendCareRateBean> removeRespiratoryRateData(List<FriendCareRateBean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortRespiratoryRateData$4(FriendCareRateBean friendCareRateBean, FriendCareRateBean friendCareRateBean2) {
        return (int) (friendCareRateBean2.rtime - friendCareRateBean.rtime);
    }

    public static List<FriendCareRateBean> sortRespiratoryRateData(List<FriendCareRateBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda10
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortRespiratoryRateData$4((FriendCareRateBean) obj, (FriendCareRateBean) obj2);
            }
        });
        return list;
    }

    public static List<HistoryBloodResponse.Mlist> removeBloodData(List<HistoryBloodResponse.Mlist> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortBloodData$5(HistoryBloodResponse.Mlist mlist, HistoryBloodResponse.Mlist mlist2) {
        return (int) (mlist2.rtime - mlist.rtime);
    }

    public static List<HistoryBloodResponse.Mlist> sortBloodData(List<HistoryBloodResponse.Mlist> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda15
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortBloodData$5((HistoryBloodResponse.Mlist) obj, (HistoryBloodResponse.Mlist) obj2);
            }
        });
        return list;
    }

    public static List<FriendCareSpo2Bean> removeSpo2Data(List<FriendCareSpo2Bean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortSpo2Data$6(FriendCareSpo2Bean friendCareSpo2Bean, FriendCareSpo2Bean friendCareSpo2Bean2) {
        return (int) (friendCareSpo2Bean2.rtime - friendCareSpo2Bean.rtime);
    }

    public static List<FriendCareSpo2Bean> sortSpo2Data(List<FriendCareSpo2Bean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda13
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortSpo2Data$6((FriendCareSpo2Bean) obj, (FriendCareSpo2Bean) obj2);
            }
        });
        return list;
    }

    public static List<HistorySleepResponse.SleepBean> removeSleepDuplicate(List<HistorySleepResponse.SleepBean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).beginTime == list.get(i2).beginTime || list.get(size).endTime == list.get(i2).endTime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortWeekAndMonthListSleep$7(CareSleepWeekMonthBean.DataBean dataBean, CareSleepWeekMonthBean.DataBean dataBean2) {
        return (int) (getTime(dataBean.dateformat) - getTime(dataBean2.dateformat));
    }

    public static List<CareSleepWeekMonthBean.DataBean> sortWeekAndMonthListSleep(List<CareSleepWeekMonthBean.DataBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda7
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortWeekAndMonthListSleep$7((CareSleepWeekMonthBean.DataBean) obj, (CareSleepWeekMonthBean.DataBean) obj2);
            }
        });
        return list;
    }

    static /* synthetic */ int lambda$sortListSleep$8(HistorySleepResponse.SleepBean sleepBean, HistorySleepResponse.SleepBean sleepBean2) {
        return (int) (getTime(sleepBean.beginTime) - getTime(sleepBean2.beginTime));
    }

    public static List<HistorySleepResponse.SleepBean> sortListSleep(List<HistorySleepResponse.SleepBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda6
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortListSleep$8((HistorySleepResponse.SleepBean) obj, (HistorySleepResponse.SleepBean) obj2);
            }
        });
        return list;
    }

    public static long getTime(String time1) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(time1).getTime();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static String fameDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppDateMgr.DF_HH_MM, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(time));
    }

    public static String BinaryToHex(String s) {
        if (s.equals("")) {
            return "0";
        }
        return Long.toHexString(Long.parseLong(s, 2));
    }

    public static Bitmap small(Bitmap bitmap, float value) {
        Matrix matrix = new Matrix();
        matrix.postScale(value, value);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void saveBoolean(String key, boolean flag, Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("ycblespinfo", 0).edit();
        editorEdit.putBoolean(key, flag);
        editorEdit.commit();
    }

    public static boolean readBoolean(String key, Context context, boolean defaultValue) {
        return context.getSharedPreferences("ycblespinfo", 0).getBoolean(key, defaultValue);
    }

    public static void saveInt(String key, int value, Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("ycblespinfo", 0).edit();
        editorEdit.putInt(key, value);
        editorEdit.commit();
    }

    public static int readInt(String key, Context context, int defaultValue) {
        return context.getSharedPreferences("ycblespinfo", 0).getInt(key, defaultValue);
    }

    public static void removeKey(String key, Context context) {
        context.getSharedPreferences("ycblespinfo", 0).edit().remove(key).apply();
    }

    public static void saveString(String key, String value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ycblespinfo", 0);
        sharedPreferences.edit().putString(key, value);
        sharedPreferences.edit().commit();
    }

    public static boolean checkPermiss(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_BACKGROUND_LOCATION") == 0 && ContextCompat.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION) == 0) {
                return true;
            }
            context.startActivity(new Intent(context, (Class<?>) LocationActivity.class));
            return false;
        }
        if (ContextCompat.checkSelfPermission(context, Permission.ACCESS_COARSE_LOCATION) == 0 && ContextCompat.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION) == 0) {
            return true;
        }
        context.startActivity(new Intent(context, (Class<?>) LocationActivity.class));
        return false;
    }

    static /* synthetic */ int lambda$sortListPDNumber$9(MyMonBean.Data.Values values, MyMonBean.Data.Values values2) {
        return (int) (getPDTime(values2.time) - getPDTime(values.time));
    }

    public static List<MyMonBean.Data.Values> sortListPDNumber(List<MyMonBean.Data.Values> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortListPDNumber$9((MyMonBean.Data.Values) obj, (MyMonBean.Data.Values) obj2);
            }
        });
        return list;
    }

    public static long getPDTime(String time) {
        SimpleDateFormat simpleDateFormat;
        if (time == null) {
            return 0L;
        }
        try {
            if (time.contains(ExifInterface.GPS_DIRECTION_TRUE)) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
            } else if (!time.contains("-")) {
                time = YearToDayListUtils.getStringDateFromMonth(Integer.parseInt(time));
                simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            } else {
                simpleDateFormat = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH);
            }
            return simpleDateFormat.parse(time).getTime() / 1000;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static synchronized List<FriendCareBloodSugarBean> removeCareRealTimeBloodSugar(List<FriendCareBloodSugarBean> list) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).bloodSugar >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && list.get(i2).bloodSugar <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                arrayList.add(list.get(i2));
            }
            float f2 = list.get(i2).bloodSugar * 10.0f;
            if (f2 >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && f2 <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                arrayList.add(list.get(i2));
            }
        }
        return arrayList;
    }

    static /* synthetic */ int lambda$sortCareListBloodSugar$10(FriendCareBloodSugarBean friendCareBloodSugarBean, FriendCareBloodSugarBean friendCareBloodSugarBean2) {
        return (int) (friendCareBloodSugarBean2.rtime - friendCareBloodSugarBean.rtime);
    }

    public static List<FriendCareBloodSugarBean> sortCareListBloodSugar(List<FriendCareBloodSugarBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda14
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareListBloodSugar$10((FriendCareBloodSugarBean) obj, (FriendCareBloodSugarBean) obj2);
            }
        });
        return list;
    }

    public static List<FriendCareBloodFatBean.BloodFat> removeCareListBloodFat(List<FriendCareBloodFatBean.BloodFat> list) {
        int i2 = 0;
        while (i2 < list.size()) {
            FriendCareBloodFatBean.BloodFat bloodFat = list.get(i2);
            if (bloodFat.tc < TransUtils.BLOOD_FAT_VISIBLE_MIN || bloodFat.tc > TransUtils.BLOOD_FAT_VISIBLE_MAX) {
                list.remove(i2);
                i2--;
            }
            i2++;
        }
        return list;
    }

    public static List<FriendCareUricAcidBean.UricAcid> removeCareListUricAcid(List<FriendCareUricAcidBean.UricAcid> list) {
        int i2 = 0;
        while (i2 < list.size()) {
            FriendCareUricAcidBean.UricAcid uricAcid = list.get(i2);
            if (uricAcid.uricAcid < TransUtils.URIC_ACID_VISIBLE_MIN || uricAcid.uricAcid > TransUtils.URIC_ACID_VISIBLE_MAX) {
                list.remove(i2);
                i2--;
            }
            i2++;
        }
        return list;
    }

    static /* synthetic */ int lambda$sortCareListBloodFat$11(FriendCareBloodFatBean.BloodFat bloodFat, FriendCareBloodFatBean.BloodFat bloodFat2) {
        return (int) (bloodFat2.rtime - bloodFat.rtime);
    }

    public static List<FriendCareBloodFatBean.BloodFat> sortCareListBloodFat(List<FriendCareBloodFatBean.BloodFat> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda12
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareListBloodFat$11((FriendCareBloodFatBean.BloodFat) obj, (FriendCareBloodFatBean.BloodFat) obj2);
            }
        });
        return list;
    }

    static /* synthetic */ int lambda$sortCareListUricAcid$12(FriendCareUricAcidBean.UricAcid uricAcid, FriendCareUricAcidBean.UricAcid uricAcid2) {
        return (int) (uricAcid2.rtime - uricAcid.rtime);
    }

    public static List<FriendCareUricAcidBean.UricAcid> sortCareListUricAcid(List<FriendCareUricAcidBean.UricAcid> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda17
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareListUricAcid$12((FriendCareUricAcidBean.UricAcid) obj, (FriendCareUricAcidBean.UricAcid) obj2);
            }
        });
        return list;
    }

    static /* synthetic */ int lambda$sortCareListBloodKetone$13(FriendCareKetoneBean.BloodKetone bloodKetone, FriendCareKetoneBean.BloodKetone bloodKetone2) {
        return (int) (bloodKetone2.rtime - bloodKetone.rtime);
    }

    public static List<FriendCareKetoneBean.BloodKetone> sortCareListBloodKetone(List<FriendCareKetoneBean.BloodKetone> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda11
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareListBloodKetone$13((FriendCareKetoneBean.BloodKetone) obj, (FriendCareKetoneBean.BloodKetone) obj2);
            }
        });
        return list;
    }

    public static synchronized List<FriendCareKetoneBean.BloodKetone> removeCareListBloodKetone(List<FriendCareKetoneBean.BloodKetone> list) {
        int i2 = 0;
        while (i2 < list.size() - 1) {
            if (list.get(i2).bloodKetone >= TransUtils.KETONE_VISIBLE_MIN && list.get(i2).bloodKetone <= TransUtils.KETONE_VISIBLE_MAX) {
                for (int size = list.size() - 1; size > i2; size--) {
                    if (list.get(size) != null && list.get(i2) != null && list.get(size).rtime == list.get(i2).rtime) {
                        list.remove(size);
                    }
                }
            } else {
                list.remove(i2);
                i2--;
            }
            i2++;
        }
        return list;
    }

    static /* synthetic */ int lambda$sortCareListHRV$14(HistoryHRVResponse.Mlist mlist, HistoryHRVResponse.Mlist mlist2) {
        return (int) (mlist2.rtime - mlist.rtime);
    }

    public static List<HistoryHRVResponse.Mlist> sortCareListHRV(List<HistoryHRVResponse.Mlist> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareListHRV$14((HistoryHRVResponse.Mlist) obj, (HistoryHRVResponse.Mlist) obj2);
            }
        });
        return list;
    }

    static /* synthetic */ int lambda$sortCareListPressure$15(HistoryPressureResponse.Mlist mlist, HistoryPressureResponse.Mlist mlist2) {
        return (int) (mlist2.rtime - mlist.rtime);
    }

    public static List<HistoryPressureResponse.Mlist> sortCareListPressure(List<HistoryPressureResponse.Mlist> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareListPressure$15((HistoryPressureResponse.Mlist) obj, (HistoryPressureResponse.Mlist) obj2);
            }
        });
        return list;
    }

    public static List<FriendCareBloodBean> removeCareBloodData(List<FriendCareBloodBean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortCareBloodData$16(FriendCareBloodBean friendCareBloodBean, FriendCareBloodBean friendCareBloodBean2) {
        return (int) (friendCareBloodBean2.rtime - friendCareBloodBean.rtime);
    }

    public static List<FriendCareBloodBean> sortCareBloodData(List<FriendCareBloodBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda16
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareBloodData$16((FriendCareBloodBean) obj, (FriendCareBloodBean) obj2);
            }
        });
        return list;
    }

    public static List<FriendCareHeartBean> removeCareHeartData(List<FriendCareHeartBean> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            for (int size = list.size() - 1; size > i2; size--) {
                if (list.get(size).rtime == list.get(i2).rtime) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    static /* synthetic */ int lambda$sortCareHeartData$17(FriendCareHeartBean friendCareHeartBean, FriendCareHeartBean friendCareHeartBean2) {
        return (int) (friendCareHeartBean2.rtime - friendCareHeartBean.rtime);
    }

    public static List<FriendCareHeartBean> sortCareHeartData(List<FriendCareHeartBean> list) {
        Collections.sort(list, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.Tools$$ExternalSyntheticLambda8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tools.lambda$sortCareHeartData$17((FriendCareHeartBean) obj, (FriendCareHeartBean) obj2);
            }
        });
        return list;
    }

    public static String transformDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(time));
    }

    public static int readCm(Context context) {
        return context.getSharedPreferences("smartam", 0).getInt("saveCm", Opcodes.TABLESWITCH);
    }

    public static int readKg(Context context) {
        return context.getSharedPreferences("smartam", 0).getInt("saveKg", 60);
    }

    public static int readAge(Context context) {
        return context.getSharedPreferences("smartam", 0).getInt("saveAge", 18);
    }

    public static int readSex(Context context) {
        return context.getSharedPreferences("smartam", 0).getInt("saveSex", 0);
    }

    public static void expendTouchArea(final View view, final int expendSize) {
        if (view != null) {
            final View view2 = (View) view.getParent();
            view2.post(new Runnable() { // from class: com.yucheng.smarthealthpro.utils.Tools.1
                @Override // java.lang.Runnable
                public void run() {
                    Rect rect = new Rect();
                    view.getHitRect(rect);
                    rect.left -= expendSize;
                    rect.top -= expendSize;
                    rect.right += expendSize;
                    rect.bottom += expendSize;
                    view2.setTouchDelegate(new TouchDelegate(rect, view));
                }
            });
        }
    }
}
