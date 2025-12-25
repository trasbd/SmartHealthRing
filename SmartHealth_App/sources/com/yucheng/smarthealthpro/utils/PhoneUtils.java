package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Toast;
import com.yanzhenjie.permission.Permission;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class PhoneUtils {
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static final String TAG = "Utils";
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault());
    private static long lastClickTime;

    public static boolean isMobileNO(String mobiles) {
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        }
        return mobiles.matches("[1][3578]\\d{9}");
    }

    public static long currentTimeInMillis() {
        Time time = new Time();
        time.setToNow();
        return time.toMillis(false);
    }

    public static boolean isHaveApp(String packageName, Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i2 = 0; i2 < installedPackages.size(); i2++) {
                if (installedPackages.get(i2).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getContactPhoneNumByName(Context context, String name) {
        Cursor cursorQuery = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("_id"));
                if (name.equals(cursorQuery.getString(cursorQuery.getColumnIndex("display_name")))) {
                    Cursor cursorQuery2 = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + string, null, null);
                    if (cursorQuery2.moveToNext()) {
                        String string2 = cursorQuery2.getString(cursorQuery2.getColumnIndex("data1"));
                        Toast.makeText(context, string2 + "", 0).show();
                        return string2;
                    }
                }
            } catch (Exception unused) {
            }
        }
        cursorQuery.close();
        return name;
    }

    private static String getContactNameByNumber(Context context, String phoneNum) {
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + phoneNum);
        String string = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"display_name"}, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                string = cursorQuery.getString(0);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return string;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0058 A[PHI: r2 r9
  0x0058: PHI (r2v4 java.lang.String) = (r2v8 java.lang.String), (r2v6 java.lang.String) binds: [B:21:0x0063, B:14:0x0056] A[DONT_GENERATE, DONT_INLINE]
  0x0058: PHI (r9v3 android.database.Cursor) = (r9v2 android.database.Cursor), (r9v11 android.database.Cursor) binds: [B:21:0x0063, B:14:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getDisplayNameByNumber(android.content.Context r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_display_name"
            java.lang.String r1 = "number:"
            r2 = 0
            if (r10 != 0) goto L8
            return r2
        L8:
            java.lang.String r10 = r10.trim()
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r9 = "Utils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.StringBuilder r1 = r4.append(r10)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.util.Log.e(r9, r1)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.net.Uri r9 = android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.net.Uri$Builder r9 = r9.buildUpon()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.net.Uri$Builder r9 = r9.appendPath(r10)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.net.Uri r4 = r9.build()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r9 = 2
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r9 = "_id"
            r10 = 0
            r5[r10] = r9     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r9 = 1
            r5[r9] = r0     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            if (r9 == 0) goto L56
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L67
            if (r10 == 0) goto L56
            int r10 = r9.getColumnIndex(r0)     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L67
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L67
            r2 = r10
            goto L56
        L54:
            r10 = move-exception
            goto L60
        L56:
            if (r9 == 0) goto L66
        L58:
            r9.close()
            goto L66
        L5c:
            r10 = move-exception
            goto L69
        L5e:
            r10 = move-exception
            r9 = r2
        L60:
            r10.printStackTrace()     // Catch: java.lang.Throwable -> L67
            if (r9 == 0) goto L66
            goto L58
        L66:
            return r2
        L67:
            r10 = move-exception
            r2 = r9
        L69:
            if (r2 == 0) goto L6e
            r2.close()
        L6e:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.PhoneUtils.getDisplayNameByNumber(android.content.Context, java.lang.String):java.lang.String");
    }

    private static String getName(Context context, String mNumber) {
        Cursor cursorQuery;
        if (context.checkSelfPermission(Permission.READ_CONTACTS) != 0 || (cursorQuery = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"display_name", "data1"}, "data1 = '" + mNumber + "'", null, null)) == null) {
            return null;
        }
        String string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndex("display_name")) : null;
        cursorQuery.close();
        return string;
    }

    public static String getContactNameFromPhoneNum(Context context, String phoneNum) {
        String displayNameByNumber = getDisplayNameByNumber(context, phoneNum);
        if (displayNameByNumber == null) {
            displayNameByNumber = getName(context, phoneNum);
        }
        return displayNameByNumber == null ? getContactNameByNumber(context, phoneNum) : displayNameByNumber;
    }

    public String getContactName(Context context, String number) {
        Cursor cursorQuery;
        String string = null;
        if (TextUtils.isEmpty(number)) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        String[] strArr = {"_id", "display_name"};
        try {
            cursorQuery = contentResolver.query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number)), strArr, null, null, null);
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                cursorQuery = contentResolver.query(Uri.withAppendedPath(Contacts.Phones.CONTENT_FILTER_URL, Uri.encode(number)), strArr, null, null, null);
            } catch (Exception e3) {
                e3.printStackTrace();
                cursorQuery = null;
            }
        }
        if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
            string = cursorQuery.getString(1);
        }
        cursorQuery.close();
        return string;
    }

    public static String getAppMetaData(Context ctx, String key) {
        ApplicationInfo applicationInfo;
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), 128)) == null || applicationInfo.metaData == null) {
                return null;
            }
            return applicationInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getChannel(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, 0).show();
    }

    public static void saveImageToGallery(Context context, Bitmap bmp) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory(), "qingcheng");
        if (!file.exists()) {
            file.mkdir();
        }
        String str = System.currentTimeMillis() + ".jpg";
        File file2 = new File(file, str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file2.getAbsolutePath(), str, (String) null);
        } catch (FileNotFoundException e4) {
            e4.printStackTrace();
        }
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file2.getAbsolutePath())));
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursorQuery = context.getContentResolver().query(contentUri, new String[]{"_data"}, null, null, null);
        String string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
        cursorQuery.close();
        return string;
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static String feetToCm(String values) {
        int iIntValue = Integer.valueOf(values.split("'")[0]).intValue();
        String str = values.split("'")[1];
        return ((int) Math.round((iIntValue * 30.48d) + (Integer.valueOf(str.substring(0, str.indexOf("\""))).intValue() * 2.54d))) + "";
    }

    public static String CmToFeet(String values) {
        int iIntValue = (int) ((Integer.valueOf(values).intValue() * 12) / 30.48d);
        return (iIntValue / 12) + "'" + (iIntValue % 12) + "\"";
    }

    public static String poundToKg(String values) {
        return ((int) Math.round(Integer.valueOf(values).intValue() * 0.4536d)) + "";
    }

    public static String KgToPound(String valueString) {
        return ((int) Math.round(Integer.valueOf(valueString).intValue() * 2.2d)) + "";
    }

    public static Bitmap myShot(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.buildDrawingCache();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int i2 = rect.top;
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        decorView.setDrawingCacheEnabled(true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(decorView.getDrawingCache(), 0, i2, width, height - i2);
        decorView.destroyDrawingCache();
        return bitmapCreateBitmap;
    }

    public static Bitmap myShot2(Activity activity) {
        activity.startActivityForResult(((MediaProjectionManager) activity.getSystemService("media_projection")).createScreenCaptureIntent(), 1);
        return null;
    }

    public static Bitmap getBitmap(Activity activity, int resultCode, Intent data) {
        MediaProjection mediaProjection = ((MediaProjectionManager) activity.getSystemService("media_projection")).getMediaProjection(resultCode, data);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ImageReader imageReaderNewInstance = ImageReader.newInstance(displayMetrics.widthPixels, displayMetrics.heightPixels, 1, 1);
        mediaProjection.createVirtualDisplay("ScreenShout", displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.densityDpi, 16, imageReaderNewInstance.getSurface(), null, null);
        SystemClock.sleep(1000L);
        Image imageAcquireNextImage = imageReaderNewInstance.acquireNextImage();
        if (imageAcquireNextImage == null) {
            return null;
        }
        int width = imageAcquireNextImage.getWidth();
        int height = imageAcquireNextImage.getHeight();
        Image.Plane[] planes = imageAcquireNextImage.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        int pixelStride = planes[0].getPixelStride();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width + ((planes[0].getRowStride() - (pixelStride * width)) / pixelStride), height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.copyPixelsFromBuffer(buffer);
        imageAcquireNextImage.close();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i2 = rect.top;
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap, 0, i2, displayMetrics.widthPixels, displayMetrics.heightPixels - i2);
        bitmapCreateBitmap.recycle();
        return bitmapCreateBitmap2;
    }

    public static Bitmap getBitmap(Activity activity, MediaProjection mediaProjection) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ImageReader imageReaderNewInstance = ImageReader.newInstance(displayMetrics.widthPixels, displayMetrics.heightPixels, 1, 1);
        mediaProjection.createVirtualDisplay("ScreenShout", displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.densityDpi, 16, imageReaderNewInstance.getSurface(), null, null);
        SystemClock.sleep(1000L);
        Image imageAcquireNextImage = imageReaderNewInstance.acquireNextImage();
        if (imageAcquireNextImage == null) {
            return null;
        }
        int width = imageAcquireNextImage.getWidth();
        int height = imageAcquireNextImage.getHeight();
        Image.Plane[] planes = imageAcquireNextImage.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        int pixelStride = planes[0].getPixelStride();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width + ((planes[0].getRowStride() - (pixelStride * width)) / pixelStride), height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.copyPixelsFromBuffer(buffer);
        imageAcquireNextImage.close();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i2 = rect.top;
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap, 0, i2, displayMetrics.widthPixels, displayMetrics.heightPixels - i2);
        bitmapCreateBitmap.recycle();
        return bitmapCreateBitmap2;
    }

    public static String getTime() {
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static boolean isFastClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = jCurrentTimeMillis - lastClickTime < 500;
        lastClickTime = jCurrentTimeMillis;
        return z;
    }
}
