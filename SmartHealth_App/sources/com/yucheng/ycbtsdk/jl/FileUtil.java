package com.yucheng.ycbtsdk.jl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import java.io.File;
import java.util.Locale;

/* loaded from: classes5.dex */
public class FileUtil {
    public static int convertOtaConnectStatus(int i2) {
        if (i2 != 1) {
            return i2 != 2 ? 0 : 1;
        }
        return 3;
    }

    public static int convertWatchConnectStatus(int i2) {
        if (i2 != 1) {
            return i2 != 2 ? 0 : 1;
        }
        return 3;
    }

    public static String createFilePath(Context context, String... strArr) {
        File externalFilesDir;
        if (context == null || strArr == null || strArr.length == 0 || (externalFilesDir = context.getExternalFilesDir(null)) == null || !externalFilesDir.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(externalFilesDir.getPath());
        int i2 = 0;
        if (sb.toString().endsWith("/")) {
            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf("/")));
        }
        int length = strArr.length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            sb.append("/").append(strArr[i2]);
            File file = new File(sb.toString());
            if ((!file.exists() || file.isFile()) && !file.mkdir()) {
                Log.w("jieli", "create dir failed. filePath = " + ((Object) sb));
                break;
            }
            i2++;
        }
        return sb.toString();
    }

    public static Bitmap createScaleBitmap(String str, int i2, int i3) {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        if (bitmapDecodeFile == null) {
            return null;
        }
        int width = bitmapDecodeFile.getWidth();
        int height = bitmapDecodeFile.getHeight();
        if (width == i2 && height == i3) {
            return bitmapDecodeFile;
        }
        float fMin = Math.min((i2 * 1.0f) / width, (i3 * 1.0f) / height);
        Matrix matrix = new Matrix();
        matrix.postScale(fMin, fMin, 0.0f, 0.0f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setFlags(1);
        canvas.drawBitmap(bitmapDecodeFile, matrix, paint);
        return bitmapCreateBitmap;
    }

    public static String getDeviceName(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        String name = bluetoothDevice.getName();
        return name == null ? bluetoothDevice.getAddress() : name;
    }

    public static String getFileNameByPath(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf("/");
        return iLastIndexOf > -1 ? str.substring(iLastIndexOf + 1) : str;
    }

    public static int getHttpErrorCode(String str) {
        String[] strArrSplit = str.split(",");
        if (strArrSplit.length < 2) {
            return 0;
        }
        return Integer.parseInt(strArrSplit[0]);
    }

    public static String getPriceFormat(int i2) {
        return String.format(Locale.getDefault(), "%.2f", Float.valueOf(i2 / 100.0f));
    }

    public static String obtainUpdateFilePath(String str, String str2) {
        File[] fileArrListFiles;
        String strObtainUpdateFilePath = null;
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        if (file.isFile()) {
            if (str.endsWith(str2)) {
                return str;
            }
            return null;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                strObtainUpdateFilePath = obtainUpdateFilePath(file2.getPath(), str2);
                if (strObtainUpdateFilePath != null) {
                    break;
                }
            }
        }
        return strObtainUpdateFilePath;
    }

    public static String saveScaleBitmap(String str, int i2, int i3, int i4) {
        Bitmap bitmapCreateScaleBitmap = createScaleBitmap(str, i2, i3);
        if (bitmapCreateScaleBitmap != null && com.jieli.jl_bt_ota.util.FileUtil.bitmapToFile(bitmapCreateScaleBitmap, str, i4)) {
            return str;
        }
        return null;
    }
}
