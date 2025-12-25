package com.yucheng.smarthealthpro.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jieli.jl_rcsp.util.JL_Log;
import com.yucheng.ycbtsdk.utils.LogToFileUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.HTTP;

/* loaded from: classes5.dex */
public class ShareUtil {
    public static void sendLog(Context context) {
        sendLog(context, LogToFileUtils.getLogFile("yclogs.txt"));
    }

    public static void sendLog(Context context, Uri uri) {
        try {
            context.grantUriPermission(context.getPackageName(), uri, 1);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("body", "  ");
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.addFlags(1);
            intent.addFlags(2);
            intent.setType("text/*");
            context.startActivity(Intent.createChooser(intent, "share"));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, "发送失败", 0).show();
        }
    }

    public static void sendLog(Context context, File file) {
        try {
            if (file.exists()) {
                Uri uriForFile = FileProvider.getUriForFile(context, "com.example.yctesttool.fileprovider", file);
                String absolutePath = file.getAbsolutePath();
                String strSubstring = absolutePath.substring(absolutePath.lastIndexOf("/") + 1);
                Intent intent = new Intent("android.intent.action.SEND");
                context.grantUriPermission(context.getPackageName(), uriForFile, 1);
                intent.putExtra("subject", strSubstring);
                intent.putExtra("body", "  ");
                intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
                intent.putExtra("android.intent.extra.STREAM", uriForFile);
                intent.addFlags(1);
                intent.addFlags(2);
                intent.setType("text/*");
                context.startActivity(Intent.createChooser(intent, "share"));
            }
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, "发送失败！", 0).show();
        }
    }

    public static void sendLog(Context context, ArrayList<Uri> list) {
        try {
            String strSubstring = "test".substring("test".lastIndexOf("/") + 1);
            Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
            intent.putExtra("subject", strSubstring);
            intent.putExtra("body", "  ");
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", list);
            intent.addFlags(1);
            intent.addFlags(2);
            intent.setType("text/*");
            context.startActivity(Intent.createChooser(intent, "share"));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, "发送失败！", 0).show();
        }
    }

    public static String getRealPathFromUri(Context context, Uri uri) {
        String string;
        string = "";
        try {
            String scheme = uri.getScheme();
            if (scheme == null) {
                return uri.getPath();
            }
            if ("file".equals(scheme)) {
                return uri.getPath();
            }
            if (!FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
                return "";
            }
            Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            if (cursorQuery != null) {
                string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : "";
                cursorQuery.close();
            }
            return TextUtils.isEmpty(string) ? getFilePathForNonMediaUri(context, uri) : string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String getFilePathForNonMediaUri(Context context, Uri uri) {
        Cursor cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
        String string = "";
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
            }
            cursorQuery.close();
        }
        return string;
    }

    public static String getLogFilePath(Context mContext) {
        File file;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = new File(mContext.getExternalFilesDir("YCLog").getPath() + "/");
        } else {
            file = new File(mContext.getFilesDir().getPath() + "/YCLog/");
        }
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath() + "/";
    }

    public static String getJLLogFilePath(Context mContext) {
        File file;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = new File(mContext.getExternalFilesDir(JL_Log.f4171b).getPath() + "/");
        } else {
            file = new File(mContext.getFilesDir().getPath() + "/logcat/");
        }
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath() + "/";
    }

    public static String getPPGFilePath(Context mContext) {
        File file;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = new File(mContext.getExternalFilesDir("PPG").getPath() + "/");
        } else {
            file = new File(mContext.getFilesDir().getPath() + "/PPG/");
        }
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath() + "/";
    }

    public static void write(String path, String fileName, String content, boolean cover) throws IOException {
        Log.e("ShareUtil", "chong----------filePath==" + path);
        File file = new File(path + fileName);
        if (!file.exists()) {
            File file2 = new File(file.getParent());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (Exception e2) {
                Log.e("ShareUtil", "create file(yc_file) failure !!! " + e2.toString());
                return;
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, cover));
            bufferedWriter.write(content);
            bufferedWriter.write(HTTP.CRLF);
            bufferedWriter.flush();
        } catch (Exception e3) {
            Log.e("ShareUtil", "Write failure !!! " + e3.toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
    
        if (r7.name.equalsIgnoreCase(r2) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
    
        r3 = androidx.core.content.FileProvider.class.getDeclaredMethod("getPathStrategy", android.content.Context.class, java.lang.String.class);
        r3.setAccessible(true);
        r3 = r3.invoke(null, r10, r11.getAuthority());
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0065, code lost:
    
        if (r3 == null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0067, code lost:
    
        r4 = java.lang.Class.forName(androidx.core.content.FileProvider.class.getName() + "$PathStrategy").getDeclaredMethod("getFileForUri", android.net.Uri.class);
        r4.setAccessible(true);
        r3 = r4.invoke(r3, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x009d, code lost:
    
        if ((r3 instanceof java.io.File) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a9, code lost:
    
        return replace(((java.io.File) r3).getAbsolutePath());
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00aa, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ab, code lost:
    
        r3.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b0, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b1, code lost:
    
        r3.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b6, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b7, code lost:
    
        r3.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00bc, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bd, code lost:
    
        r3.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0017, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0017, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0017, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0017, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFPUriToPath(android.content.Context r10, android.net.Uri r11) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r0 = 0
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch: java.lang.Exception -> Lc6
            r2 = 8
            java.util.List r1 = r1.getInstalledPackages(r2)     // Catch: java.lang.Exception -> Lc6
            if (r1 == 0) goto Lca
            java.lang.Class<androidx.core.content.FileProvider> r2 = androidx.core.content.FileProvider.class
            java.lang.String r2 = r2.getName()     // Catch: java.lang.Exception -> Lc6
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> Lc6
        L17:
            boolean r3 = r1.hasNext()     // Catch: java.lang.Exception -> Lc6
            if (r3 == 0) goto Lca
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Exception -> Lc6
            android.content.pm.PackageInfo r3 = (android.content.pm.PackageInfo) r3     // Catch: java.lang.Exception -> Lc6
            android.content.pm.ProviderInfo[] r3 = r3.providers     // Catch: java.lang.Exception -> Lc6
            if (r3 == 0) goto L17
            int r4 = r3.length     // Catch: java.lang.Exception -> Lc6
            r5 = 0
            r6 = r5
        L2a:
            if (r6 >= r4) goto L17
            r7 = r3[r6]     // Catch: java.lang.Exception -> Lc6
            java.lang.String r8 = r11.getAuthority()     // Catch: java.lang.Exception -> Lc6
            java.lang.String r9 = r7.authority     // Catch: java.lang.Exception -> Lc6
            boolean r8 = r8.equals(r9)     // Catch: java.lang.Exception -> Lc6
            if (r8 == 0) goto Lc2
            java.lang.String r3 = r7.name     // Catch: java.lang.Exception -> Lc6
            boolean r3 = r3.equalsIgnoreCase(r2)     // Catch: java.lang.Exception -> Lc6
            if (r3 == 0) goto L17
            java.lang.Class<androidx.core.content.FileProvider> r3 = androidx.core.content.FileProvider.class
            java.lang.String r4 = "getPathStrategy"
            r6 = 2
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r5] = r7     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r8 = 1
            r6[r8] = r7     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r6)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            r3.setAccessible(r8)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.String r4 = r11.getAuthority()     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Object[] r4 = new java.lang.Object[]{r10, r4}     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Object r3 = r3.invoke(r0, r4)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            if (r3 == 0) goto L17
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            r4.<init>()     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Class<androidx.core.content.FileProvider> r6 = androidx.core.content.FileProvider.class
            java.lang.String r6 = r6.getName()     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.String r6 = "$PathStrategy"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.String r4 = r4.toString()     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.String r6 = "getFileForUri"
            java.lang.Class[] r7 = new java.lang.Class[r8]     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Class<android.net.Uri> r9 = android.net.Uri.class
            r7[r5] = r9     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.reflect.Method r4 = r4.getDeclaredMethod(r6, r7)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            r4.setAccessible(r8)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Object[] r5 = new java.lang.Object[]{r11}     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.Object r3 = r4.invoke(r3, r5)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            boolean r4 = r3 instanceof java.io.File     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            if (r4 == 0) goto L17
            java.io.File r3 = (java.io.File) r3     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            java.lang.String r10 = replace(r3)     // Catch: java.lang.ClassNotFoundException -> Laa java.lang.IllegalAccessException -> Lb0 java.lang.reflect.InvocationTargetException -> Lb6 java.lang.NoSuchMethodException -> Lbc java.lang.Exception -> Lc6
            return r10
        Laa:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> Lc6
            goto L17
        Lb0:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> Lc6
            goto L17
        Lb6:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> Lc6
            goto L17
        Lbc:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> Lc6
            goto L17
        Lc2:
            int r6 = r6 + 1
            goto L2a
        Lc6:
            r10 = move-exception
            r10.printStackTrace()
        Lca:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.ShareUtil.getFPUriToPath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static String replace(String filePath) {
        if (filePath.contains("%")) {
            filePath = filePath.replace("%", "%25");
        }
        if (filePath.contains(MqttTopic.MULTI_LEVEL_WILDCARD)) {
            filePath = filePath.replace("%", "%23");
        }
        if (filePath.contains("&")) {
            filePath = filePath.replace("%", "%26");
        }
        return filePath.contains("?") ? filePath.replace("%", "%3F") : filePath;
    }
}
