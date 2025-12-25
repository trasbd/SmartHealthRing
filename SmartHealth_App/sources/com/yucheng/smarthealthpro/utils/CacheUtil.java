package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class CacheUtil {
    public static String getTotalCacheSize(Context context) {
        long folderSize;
        long j2 = 0;
        try {
            long folderSize2 = getFolderSize(context.getFilesDir());
            if (Environment.getExternalStorageState().equals("mounted")) {
                folderSize = getFolderSize(context.getExternalCacheDir());
            } else {
                folderSize = getFolderSize(context.getCacheDir());
            }
            j2 = folderSize2 + folderSize;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return getFormatSize(j2);
    }

    public static void clearAllCache(Context context) {
        deleteDir(context.getFilesDir());
        if (Environment.getExternalStorageState().equals("mounted")) {
            deleteDir(context.getExternalCacheDir());
        } else {
            deleteDir(context.getCacheDir());
        }
        clearFirmWare();
        SharedPreferencesUtils.put(context, Constant.SpConstKey.TMP_CACHE_TIME, 0L);
    }

    private static void clearFirmWare() {
        String str = (String) SharedPreferencesUtils.get(MyApplication.getInstance(), Constant.SpConstKey.FIRM_WARE_FILE, "");
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.addAll(Arrays.asList(str.split(",")));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            File file = new File((String) arrayList.get(i2));
            if (file.exists()) {
                file.delete();
            }
        }
        SharedPreferencesUtils.put(MyApplication.getInstance(), Constant.SpConstKey.FIRM_WARE_FILE, "");
    }

    private static boolean deleteDir(File dir) {
        String[] list;
        if (dir != null && dir.isDirectory() && (list = dir.list()) != null) {
            for (String str : list) {
                if (!deleteDir(new File(dir, str))) {
                    return false;
                }
            }
        }
        if (dir != null) {
            return dir.delete();
        }
        return false;
    }

    public static long getFolderSize(File file) throws Exception {
        long length;
        long j2 = 0;
        try {
            File[] fileArrListFiles = file.listFiles();
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                if (fileArrListFiles[i2].isDirectory()) {
                    length = getFolderSize(fileArrListFiles[i2]);
                } else {
                    length = fileArrListFiles[i2].length();
                }
                j2 += length;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j2;
    }

    public static String getFormatSize(double size) {
        double d2 = size / 1024.0d;
        if (d2 < 1.0d) {
            return "0KB";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(1, 4).toPlainString() + "KB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(1, 4).toPlainString() + "MB";
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            return new BigDecimal(Double.toString(d4)).setScale(1, 4).toPlainString() + "GB";
        }
        return new BigDecimal(d5).setScale(1, 4).toPlainString() + "TB";
    }
}
