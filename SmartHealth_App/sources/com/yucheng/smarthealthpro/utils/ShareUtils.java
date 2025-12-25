package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.yucheng.smarthealthpro.R;
import com.yucheng.ycbtsdk.utils.LogToFileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes5.dex */
public class ShareUtils {
    private static String sharePath = "";

    public static String share(Activity context) {
        return share(context, context.getString(R.string.app_name));
    }

    public static String share(Activity context, String name) {
        deleteShare(context, sharePath);
        Bitmap bitmapSnapShotWithStatusBar = AppScreenMgr.snapShotWithStatusBar(context);
        if (bitmapSnapShotWithStatusBar == null) {
            return null;
        }
        return share(context, bitmapSnapShotWithStatusBar, name);
    }

    public static String share(Activity context, Bitmap bitmap, String name) throws Throwable {
        String strSaveBitmap;
        try {
            strSaveBitmap = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, name, "");
        } catch (Exception e2) {
            e2.printStackTrace();
            strSaveBitmap = null;
        }
        if (strSaveBitmap == null) {
            try {
                strSaveBitmap = saveBitmap(context, bitmap, "share", name);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (strSaveBitmap != null) {
            Uri uri = Uri.parse(strSaveBitmap);
            sharePath = getRealPathFromURI(context, uri);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.setType("image/*");
            context.startActivity(Intent.createChooser(intent, context.getTitle()));
        }
        return sharePath;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.graphics.Bitmap] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String saveBitmap(android.app.Activity r3, android.graphics.Bitmap r4, java.lang.String r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            r2 = 0
            if (r0 <= r1) goto L35
            java.lang.String r0 = "share"
            boolean r3 = com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil.saveImageToGallery(r3, r4, r6, r0)
            if (r3 == 0) goto L87
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = android.os.Environment.DIRECTORY_PICTURES
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = java.io.File.separator
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = "health"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = java.io.File.separator
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r3 = r3.toString()
            return r3
        L35:
            java.io.File r5 = new java.io.File
            java.lang.String r0 = "/health/share"
            java.lang.String r0 = com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil.isExistDir(r0)
            r5.<init>(r0, r6)
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            r1 = 100
            boolean r4 = r4.compress(r0, r1, r6)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            if (r4 == 0) goto L70
            r6.flush()     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            android.content.Intent r4 = new android.content.Intent     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            java.lang.String r0 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
            r4.<init>(r0)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            android.net.Uri r0 = android.net.Uri.fromFile(r5)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            r4.setData(r0)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            r3.sendBroadcast(r4)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            java.lang.String r3 = r5.getAbsolutePath()     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> L88
            r6.close()     // Catch: java.lang.Exception -> L6b
            goto L6f
        L6b:
            r4 = move-exception
            r4.printStackTrace()
        L6f:
            return r3
        L70:
            r6.close()     // Catch: java.lang.Exception -> L83
            goto L87
        L74:
            r3 = move-exception
            goto L7a
        L76:
            r3 = move-exception
            goto L8a
        L78:
            r3 = move-exception
            r6 = r2
        L7a:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L88
            if (r6 == 0) goto L87
            r6.close()     // Catch: java.lang.Exception -> L83
            goto L87
        L83:
            r3 = move-exception
            r3.printStackTrace()
        L87:
            return r2
        L88:
            r3 = move-exception
            r2 = r6
        L8a:
            if (r2 == 0) goto L94
            r2.close()     // Catch: java.lang.Exception -> L90
            goto L94
        L90:
            r4 = move-exception
            r4.printStackTrace()
        L94:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.ShareUtils.saveBitmap(android.app.Activity, android.graphics.Bitmap, java.lang.String, java.lang.String):java.lang.String");
    }

    private static void deleteShare(Activity context, String sharePath2) {
        if (sharePath2 == null) {
            try {
                sharePath2 = sharePath;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (sharePath2 == null || sharePath2.equals("")) {
            return;
        }
        context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data='" + sharePath2 + "'", null);
        File file = new File(sharePath2);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursorQuery = context.getContentResolver().query(contentUri, new String[]{"_data"}, null, null, null);
        String string = "";
        if (cursorQuery != null && !cursorQuery.isClosed()) {
            if (cursorQuery.moveToFirst()) {
                string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
            }
            cursorQuery.close();
        }
        return string;
    }

    public static void sendLog(Context context) {
        sendLog(context, LogToFileUtils.getLogFile("yclogs.txt"));
    }

    public static void sendLog(Context context, File file) {
        try {
            if (file.exists()) {
                context.getApplicationContext().getPackageName();
                Uri uriForFile = FileProvider.getUriForFile(context, "com.zhuoting.healthyucheng.fileProvider", file);
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
        } catch (ActivityNotFoundException e2) {
            Toast.makeText(context, "发送失败！", 0).show();
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static void sendFile(Context context, String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                Uri uriForFile = FileProvider.getUriForFile(context, "com.zhuoting.healthyucheng.fileProvider", file);
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
                intent.setType("image/*");
                context.startActivity(Intent.createChooser(intent, "share"));
            }
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
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

    private void getContentResolverInfo(Context context, Uri uri, int width, int height, MediaType mediaType) {
        System.currentTimeMillis();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                    cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_size"));
                    FileDescriptor fileDescriptor = context.getContentResolver().openFileDescriptor(uri, "rw").getFileDescriptor();
                    readFileContent(fileDescriptor);
                    new FileInputStream(fileDescriptor);
                    context.getContentResolver().getType(uri);
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public static String readFileContent(FileDescriptor fileDescriptor) throws IOException {
        if (fileDescriptor == null) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer("");
            while (true) {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            stringBuffer.append(line);
                            stringBuffer.append(StringUtils.LF);
                        } else {
                            try {
                                break;
                            } catch (IOException e2) {
                            }
                        }
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        try {
                            bufferedReader.close();
                            inputStreamReader.close();
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        return null;
                    }
                } finally {
                    try {
                        bufferedReader.close();
                        inputStreamReader.close();
                        fileInputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            }
            return stringBuffer.toString();
        } catch (Exception e5) {
            e5.printStackTrace();
            return null;
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
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.ShareUtils.getFPUriToPath(android.content.Context, android.net.Uri):java.lang.String");
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
