package com.yucheng.smarthealthpro.me.setting.dial.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.FileProvider;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.perfect.ui.ClipImageActivity;
import com.yucheng.smarthealthpro.perfect.utils.FileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class SystemUiUtil {
    public static File gotoCamera(Activity context, int requestCode) {
        File file = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/DCIM/image/"), System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(2);
        intent.putExtra("output", FileProvider.getUriForFile(context, "com.zhuoting.healthyucheng.fileProvider", file));
        context.startActivityForResult(intent, requestCode);
        return file;
    }

    public static void gotoPhoto(Activity context, int requestCode) {
        if (TextUtils.equals(Build.BRAND.toLowerCase(), "vivo")) {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setType("image/*");
            context.startActivityForResult(intent, requestCode);
            return;
        }
        context.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), context.getString(R.string.app_name)), requestCode);
    }

    public static void gotoClipActivity(Activity context, Uri uri, int requestCode, int width, int height, int value) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(context, ClipImageActivity.class);
        intent.putExtra("type", value);
        intent.putExtra(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, width);
        intent.putExtra("height", height);
        intent.setData(uri);
        Logger.d("chong--------width==" + width + "--" + height);
        context.startActivityForResult(intent, requestCode);
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(w / width, h2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public static Bitmap getRoundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius, int boarder, int background) {
        if (bitmap == null) {
            return null;
        }
        float height = (outHeight * 1.0f) / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale((outWidth * 1.0f) / bitmap.getWidth(), height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(background);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f2 = boarder;
        RectF rectF = new RectF(f2, f2, outWidth - boarder, outHeight - boarder);
        float f3 = radius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        if (boarder > 0) {
            Paint paint2 = new Paint(1);
            paint2.setColor(Color.parseColor("#00AAFF"));
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(f2);
            canvas.drawRoundRect(rectF, f3, f3, paint2);
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap clip2(View view, int width, int height, int background) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
        return bitmapCreateBitmap != null ? getRoundBitmapByShader(bitmapCreateBitmap, width, height, width / 2, 2, background) : bitmapCreateBitmap;
    }

    public static Bitmap clip(View view, int width, int height, int radius, int background) {
        Bitmap bitmapCreateBitmap;
        Logger.d("chong----------info--width==view==" + view.getWidth() + "--height==" + view.getHeight());
        if (view.getWidth() != 0 && view.getHeight() != 0) {
            bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        Bitmap bitmap = bitmapCreateBitmap;
        view.draw(new Canvas(bitmap));
        return bitmap != null ? getRoundBitmapByShader(bitmap, width, height, radius, 2, background) : bitmap;
    }

    public static Bitmap clip(Bitmap bitmap, int width, int height, int radius, int boarder, int background) {
        return bitmap != null ? getRoundBitmapByShader(bitmap, width, height, radius, boarder, background) : bitmap;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0023 -> B:29:0x0026). Please report as a decompilation issue!!! */
    public static void saveBinFile(byte[] bins, String path) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(path);
                try {
                    fileOutputStream2.write(bins);
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
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
        }
    }

    public static String isExistDir(String saveDir) throws IOException {
        String absolutePath;
        try {
            if (HealthApplication.getInstance().getExternalCacheDir() != null) {
                absolutePath = HealthApplication.getInstance().getExternalCacheDir().getAbsolutePath();
            } else {
                absolutePath = HealthApplication.getInstance().getCacheDir().getAbsolutePath();
            }
            File file = new File(absolutePath, saveDir);
            if (!file.mkdirs()) {
                file.createNewFile();
            }
            return file.getAbsolutePath();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void deleteDialFile(String name) {
        try {
            File file = new File(isExistDir("health/dial") + "/" + name);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean saveImageToGallery(Context context, Bitmap image, String imageName, String type) throws IOException {
        boolean z = false;
        if (Build.VERSION.SDK_INT <= 29) {
            return false;
        }
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        ContentValues contentValues = new ContentValues();
        contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + File.separator + "health" + File.separator + type);
        contentValues.put("_display_name", imageName);
        contentValues.put("mime_type", "image/png");
        contentValues.put("date_added", Long.valueOf(lValueOf.longValue() / 1000));
        contentValues.put("date_modified", Long.valueOf(lValueOf.longValue() / 1000));
        contentValues.put("date_expires", Long.valueOf((lValueOf.longValue() + 86400000) / 1000));
        contentValues.put("is_pending", (Integer) 1);
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        try {
            OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
            try {
                if (!image.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream)) {
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
        }
    }
}
