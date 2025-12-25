package com.yucheng.smarthealthpro.me.setting.dial.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.yucheng.smarthealthpro.utils.JxdUtils;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class SaveUtils {
    private static final String TAG = "SaveUtils";

    public static boolean saveImgFileToAlbum(Context context, String imageFilePath) {
        Log.d(TAG, "saveImgToAlbum() imageFile = [" + imageFilePath + "]");
        try {
            return saveBitmapToAlbum(context, BitmapFactory.decodeFile(imageFilePath));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean saveBitmapToAlbum(Context context, Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 29) {
            return saveBitmapToAlbumBeforeQ(context, bitmap);
        }
        return saveBitmapToAlbumAfterQ(context, bitmap);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean saveBitmapToAlbumAfterQ(android.content.Context r7, android.graphics.Bitmap r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = android.os.Environment.getExternalStorageState()
            java.lang.String r1 = "mounted"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Lf
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L11
        Lf:
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
        L11:
            android.content.ContentValues r1 = getImageContentValues(r7)
            android.content.ContentResolver r2 = r7.getContentResolver()
            android.net.Uri r0 = r2.insert(r0, r1)
            r2 = 0
            if (r0 != 0) goto L21
            return r2
        L21:
            r3 = 0
            android.content.ContentResolver r4 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.io.OutputStream r4 = r4.openOutputStream(r0)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L6b
            r6 = 50
            r8.compress(r5, r6, r4)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L6b
            r1.clear()     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L6b
            java.lang.String r8 = "is_pending"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L6b
            r1.put(r8, r5)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L6b
            android.content.ContentResolver r8 = r7.getContentResolver()     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L6b
            r8.update(r0, r1, r3, r3)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L6b
            if (r4 == 0) goto L4e
            r4.close()     // Catch: java.io.IOException -> L4a
            goto L4e
        L4a:
            r7 = move-exception
            r7.printStackTrace()
        L4e:
            r7 = 1
            return r7
        L50:
            r8 = move-exception
            goto L56
        L52:
            r7 = move-exception
            goto L6d
        L54:
            r8 = move-exception
            r4 = r3
        L56:
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L6b
            r7.delete(r0, r3, r3)     // Catch: java.lang.Throwable -> L6b
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L6b
            if (r4 == 0) goto L6a
            r4.close()     // Catch: java.io.IOException -> L66
            goto L6a
        L66:
            r7 = move-exception
            r7.printStackTrace()
        L6a:
            return r2
        L6b:
            r7 = move-exception
            r3 = r4
        L6d:
            if (r3 == 0) goto L77
            r3.close()     // Catch: java.io.IOException -> L73
            goto L77
        L73:
            r8 = move-exception
            r8.printStackTrace()
        L77:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.setting.dial.util.SaveUtils.saveBitmapToAlbumAfterQ(android.content.Context, android.graphics.Bitmap):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean saveBitmapToAlbumBeforeQ(android.content.Context r6, android.graphics.Bitmap r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = android.os.Environment.DIRECTORY_DCIM
            java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r0)
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r6.getPackageName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = java.io.File.separator
            java.lang.StringBuilder r2 = r2.append(r3)
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ".jpg"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r0, r2)
            r0 = 0
            r2 = 0
            boolean r3 = r1.exists()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            if (r3 != 0) goto L42
            java.io.File r3 = r1.getParentFile()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r3.mkdirs()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r1.createNewFile()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
        L42:
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6e
            r4 = 50
            boolean r0 = r7.compress(r0, r4, r3)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6e
            boolean r4 = r7.isRecycled()     // Catch: java.io.IOException -> L66 java.lang.Throwable -> L6b
            if (r4 != 0) goto L5d
            r7.recycle()     // Catch: java.io.IOException -> L66 java.lang.Throwable -> L6b
        L5d:
            r3.close()     // Catch: java.io.IOException -> L61
            goto L83
        L61:
            r7 = move-exception
            r7.printStackTrace()
            goto L83
        L66:
            r7 = move-exception
            r5 = r3
            r3 = r0
            r0 = r5
            goto L75
        L6b:
            r6 = move-exception
            r0 = r3
            goto L9b
        L6e:
            r7 = move-exception
            r0 = r3
            goto L74
        L71:
            r6 = move-exception
            goto L9b
        L73:
            r7 = move-exception
        L74:
            r3 = r2
        L75:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L82
            r0.close()     // Catch: java.io.IOException -> L7e
            goto L82
        L7e:
            r7 = move-exception
            r7.printStackTrace()
        L82:
            r0 = r3
        L83:
            r7 = 1
            java.lang.String[] r3 = new java.lang.String[r7]
            java.lang.String r1 = r1.getAbsolutePath()
            r3[r2] = r1
            java.lang.String[] r7 = new java.lang.String[r7]
            java.lang.String r1 = "image/*"
            r7[r2] = r1
            com.yucheng.smarthealthpro.me.setting.dial.util.SaveUtils$$ExternalSyntheticLambda1 r1 = new com.yucheng.smarthealthpro.me.setting.dial.util.SaveUtils$$ExternalSyntheticLambda1
            r1.<init>()
            android.media.MediaScannerConnection.scanFile(r6, r3, r7, r1)
            return r0
        L9b:
            if (r0 == 0) goto La5
            r0.close()     // Catch: java.io.IOException -> La1
            goto La5
        La1:
            r7 = move-exception
            r7.printStackTrace()
        La5:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.setting.dial.util.SaveUtils.saveBitmapToAlbumBeforeQ(android.content.Context, android.graphics.Bitmap):boolean");
    }

    public static ContentValues getImageContentValues(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", System.currentTimeMillis() + ".jpg");
        contentValues.put("mime_type", "image/*");
        contentValues.put("relative_path", Environment.DIRECTORY_DCIM + File.separator + context.getPackageName());
        contentValues.put("is_pending", (Integer) 1);
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static boolean saveVideoToAlbum(Context context, String videoFile) {
        Log.d(TAG, "saveVideoToAlbum() videoFile = [" + videoFile + "]");
        if (Build.VERSION.SDK_INT < 29) {
            return saveVideoToAlbumBeforeQ(context, videoFile);
        }
        return saveVideoToAlbumAfterQ(context, videoFile);
    }

    private static boolean saveVideoToAlbumAfterQ(Context context, String videoFile) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            File file = new File(videoFile);
            ContentValues videoContentValues = getVideoContentValues(context, file, System.currentTimeMillis());
            Uri uriInsert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, videoContentValues);
            copyFileAfterQ(context, contentResolver, file, uriInsert);
            videoContentValues.clear();
            videoContentValues.put("is_pending", (Integer) 0);
            context.getContentResolver().update(uriInsert, videoContentValues, null, null);
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uriInsert));
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00a3 A[Catch: IOException -> 0x009f, TRY_LEAVE, TryCatch #4 {IOException -> 0x009f, blocks: (B:42:0x009b, B:46:0x00a3), top: B:52:0x009b }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean saveVideoToAlbumBeforeQ(android.content.Context r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = android.os.Environment.DIRECTORY_DCIM
            java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r0)
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            java.io.File r7 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r6.getPackageName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = java.io.File.separator
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = r1.getName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r7.<init>(r0, r2)
            r0 = 0
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7a
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7a
            r4.<init>(r7)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7a
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7a
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
        L44:
            int r4 = r3.read(r2)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r4 <= 0) goto L4e
            r1.write(r2, r0, r4)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            goto L44
        L4e:
            r2 = 1
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r7 = r7.getAbsolutePath()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r4[r0] = r7     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = "video/*"
            r7[r0] = r5     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            com.yucheng.smarthealthpro.me.setting.dial.util.SaveUtils$$ExternalSyntheticLambda0 r5 = new com.yucheng.smarthealthpro.me.setting.dial.util.SaveUtils$$ExternalSyntheticLambda0     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r5.<init>()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            android.media.MediaScannerConnection.scanFile(r6, r4, r7, r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r3.close()     // Catch: java.io.IOException -> L6d
            r1.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r6 = move-exception
            r6.printStackTrace()
        L71:
            return r2
        L72:
            r6 = move-exception
            goto L78
        L74:
            r6 = move-exception
            goto L7c
        L76:
            r6 = move-exception
            r1 = r2
        L78:
            r2 = r3
            goto L99
        L7a:
            r6 = move-exception
            r1 = r2
        L7c:
            r2 = r3
            goto L83
        L7e:
            r6 = move-exception
            r1 = r2
            goto L99
        L81:
            r6 = move-exception
            r1 = r2
        L83:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L98
            if (r2 == 0) goto L8e
            r2.close()     // Catch: java.io.IOException -> L8c
            goto L8e
        L8c:
            r6 = move-exception
            goto L94
        L8e:
            if (r1 == 0) goto L97
            r1.close()     // Catch: java.io.IOException -> L8c
            goto L97
        L94:
            r6.printStackTrace()
        L97:
            return r0
        L98:
            r6 = move-exception
        L99:
            if (r2 == 0) goto La1
            r2.close()     // Catch: java.io.IOException -> L9f
            goto La1
        L9f:
            r7 = move-exception
            goto La7
        La1:
            if (r1 == 0) goto Laa
            r1.close()     // Catch: java.io.IOException -> L9f
            goto Laa
        La7:
            r7.printStackTrace()
        Laa:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.setting.dial.util.SaveUtils.saveVideoToAlbumBeforeQ(android.content.Context, java.lang.String):boolean");
    }

    private static void copyFileAfterQ(Context context, ContentResolver localContentResolver, File tempFile, Uri localUri) throws IOException {
        if (Build.VERSION.SDK_INT < 29 || context.getApplicationInfo().targetSdkVersion < 29) {
            return;
        }
        OutputStream outputStreamOpenOutputStream = localContentResolver.openOutputStream(localUri);
        JxdUtils.copy(tempFile.toPath(), outputStreamOpenOutputStream);
        outputStreamOpenOutputStream.close();
        tempFile.delete();
    }

    public static ContentValues getVideoContentValues(Context context, File paramFile, long timestamp) {
        ContentValues contentValues = new ContentValues();
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM + File.separator + context.getPackageName());
        }
        contentValues.put("title", paramFile.getName());
        contentValues.put("_display_name", paramFile.getName());
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("datetaken", Long.valueOf(timestamp));
        contentValues.put("date_modified", Long.valueOf(timestamp));
        contentValues.put("date_added", Long.valueOf(timestamp));
        contentValues.put("_size", Long.valueOf(paramFile.length()));
        return contentValues;
    }
}
