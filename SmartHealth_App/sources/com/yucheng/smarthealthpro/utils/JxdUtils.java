package com.yucheng.smarthealthpro.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/* loaded from: classes5.dex */
public class JxdUtils {
    public static String getPath(final Context context, final Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                }
                if (isMediaDocument(uri)) {
                    String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = strArrSplit2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit2[1]});
                }
            }
        } else {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) throws Throwable {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, selection, selectionArgs, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return string;
                        }
                    } catch (IllegalArgumentException unused) {
                        cursor = cursorQuery;
                        String filePathFromURI = getFilePathFromURI(context, uri);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return filePathFromURI;
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            } catch (IllegalArgumentException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String getFilePathFromURI(Context context, Uri contentUri) throws IOException {
        String fileName = getFileName(contentUri);
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        File file = new File(context.getFilesDir() + File.separator + fileName);
        if (file.exists()) {
            file.delete();
        }
        copy(context, contentUri, file);
        return file.getAbsolutePath();
    }

    public static String copyFile(Context context, String filePath) {
        String strSubstring;
        int iLastIndexOf = filePath.lastIndexOf(47);
        if (iLastIndexOf == -1) {
            strSubstring = "FileName";
        } else {
            strSubstring = filePath.substring(iLastIndexOf + 1);
        }
        File file = new File(context.getFilesDir() + File.separator + strSubstring);
        File file2 = new File(filePath);
        if (file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            return file.getAbsolutePath();
        }
        if (file.exists()) {
            file.delete();
        }
        if (copy(context, Uri.fromFile(file2), file)) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static String getFileName(Uri uri) {
        String path;
        int iLastIndexOf;
        if (uri == null || (path = uri.getPath()) == null || (iLastIndexOf = path.lastIndexOf(47)) == -1) {
            return null;
        }
        return path.substring(iLastIndexOf + 1);
    }

    public static boolean copy(Context context, Uri srcUri, File dstFile) throws IOException {
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStreamOpenInputStream == null) {
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            copyFile(inputStreamOpenInputStream, fileOutputStream);
            inputStreamOpenInputStream.close();
            fileOutputStream.close();
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void copy(File srcUri, File dstFile) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(srcUri);
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            copyFile(fileInputStream, fileOutputStream);
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[2048];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 2048);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 2048);
        while (true) {
            try {
                int i2 = bufferedInputStream.read(bArr, 0, 2048);
                if (i2 == -1) {
                    break;
                } else {
                    bufferedOutputStream.write(bArr, 0, i2);
                }
            } finally {
            }
        }
        bufferedOutputStream.flush();
        try {
            bufferedOutputStream.close();
        } catch (IOException unused) {
        }
        try {
            bufferedInputStream.close();
        } catch (IOException unused2) {
        }
    }

    public static void copy(Path toPath, OutputStream os) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(toPath.toFile());
            copyFile(fileInputStream, os);
            fileInputStream.close();
            os.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
