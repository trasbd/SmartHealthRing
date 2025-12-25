package com.yucheng.ycbtsdk.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class SystemUiUtil {
    public static Bitmap clip(View view, int i2, int i3, int i4, int i5) {
        Bitmap bitmapCreateBitmap = (view.getWidth() == 0 || view.getHeight() == 0) ? Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888) : Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap != null ? getRoundBitmapByShader(bitmapCreateBitmap, i2, i3, i4, 2, i5) : bitmapCreateBitmap;
    }

    public static Bitmap clip2(View view, int i2, int i3, int i4) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
        return bitmapCreateBitmap != null ? getRoundBitmapByShader(bitmapCreateBitmap, i2, i3, i2 / 2, 2, i4) : bitmapCreateBitmap;
    }

    public static Bitmap getRoundBitmapByShader(Bitmap bitmap, int i2, int i3, int i4, int i5, int i6) {
        if (bitmap == null) {
            return null;
        }
        float height = (i3 * 1.0f) / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale((i2 * 1.0f) / bitmap.getWidth(), height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(i6);
        Paint paint = new Paint(1);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f2 = i5;
        RectF rectF = new RectF(f2, f2, i2 - i5, i3 - i5);
        float f3 = i4;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        if (i5 > 0) {
            Paint paint2 = new Paint(1);
            paint2.setColor(Color.parseColor("#00AAFF"));
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(f2);
            canvas.drawRoundRect(rectF, f3, f3, paint2);
        }
        return bitmapCreateBitmap;
    }

    public static String isExistDir(Context context, String str) throws IOException {
        try {
            File file = new File(context.getExternalCacheDir().getAbsolutePath(), str);
            if (!file.mkdirs()) {
                file.createNewFile();
            }
            return file.getAbsolutePath();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0022 -> B:30:0x0025). Please report as a decompilation issue!!! */
    public static void saveBinFile(byte[] bArr, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            try {
                fileOutputStream = new FileOutputStream(str);
                try {
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream2 = fileOutputStream;
                    e.printStackTrace();
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = fileOutputStream2;
        }
    }

    public static boolean saveImageToGallery(Context context, Bitmap bitmap, String str, String str2) throws IOException {
        boolean z = false;
        if (Build.VERSION.SDK_INT <= 29) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        StringBuilder sbAppend = new StringBuilder().append(Environment.DIRECTORY_PICTURES);
        String str3 = File.separator;
        contentValues.put("relative_path", sbAppend.append(str3).append("health").append(str3).append(str2).toString());
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "image/png");
        long j2 = jCurrentTimeMillis / 1000;
        contentValues.put("date_added", Long.valueOf(j2));
        contentValues.put("date_modified", Long.valueOf(j2));
        contentValues.put("date_expires", Long.valueOf((jCurrentTimeMillis + 86400000) / 1000));
        contentValues.put("is_pending", (Integer) 1);
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        try {
            OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
            try {
                if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream)) {
                    throw new IOException("Failed to compress");
                }
                if (outputStreamOpenOutputStream != null) {
                    try {
                        outputStreamOpenOutputStream.close();
                    } catch (IOException unused) {
                        z = true;
                        contentResolver.delete(uriInsert, null);
                        return z;
                    }
                }
                contentValues.clear();
                contentValues.put("is_pending", (Integer) 0);
                contentValues.putNull("date_expires");
                contentResolver.update(uriInsert, contentValues, null, null);
                return true;
            } finally {
            }
        } catch (IOException unused2) {
            contentResolver.delete(uriInsert, null);
            return z;
        }
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i2 / width, i3 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public static Bitmap clip(Bitmap bitmap, int i2, int i3, int i4, int i5, int i6) {
        return bitmap != null ? getRoundBitmapByShader(bitmap, i2, i3, i4, i5, i6) : bitmap;
    }
}
