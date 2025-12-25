package com.yucheng.smarthealthpro.perfect.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;

/* loaded from: classes5.dex */
public class FileUtil {
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        Cursor cursorQuery;
        int columnIndex;
        String string = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equalsIgnoreCase(scheme)) {
            return uri.getPath();
        }
        if (!FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(scheme) || (cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_data")) > -1) {
            string = cursorQuery.getString(columnIndex);
        }
        cursorQuery.close();
        return string;
    }

    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dirPath;
    }
}
