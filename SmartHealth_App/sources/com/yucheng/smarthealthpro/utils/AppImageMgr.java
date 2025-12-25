package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.TypedValue;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
public class AppImageMgr {
    private static final long INITIALCRC = -1;
    public static int MIN_SIDE_LENGTH = 256;
    private static final long POLY64REV = -7661587058870466123L;
    private static float hRadius = 0.0f;
    public static boolean isComputeSampleSize = false;
    private static int iterations;
    private static long[] sCrcTable = new long[256];
    private static float vRadius;
    private Context mContext;
    private WeakHashMap<Integer, WeakReference<Bitmap>> mBitmaps = new WeakHashMap<>();
    private WeakHashMap<Integer, WeakReference<Drawable>> mDrawables = new WeakHashMap<>();

    public static int clamp(int x, int a2, int b2) {
        return x < a2 ? a2 : x > b2 ? b2 : x;
    }

    static {
        for (int i2 = 0; i2 < 256; i2++) {
            long j2 = i2;
            for (int i3 = 0; i3 < 8; i3++) {
                j2 = (j2 >> 1) ^ ((((int) j2) & 1) != 0 ? POLY64REV : 0L);
            }
            sCrcTable[i2] = j2;
        }
        hRadius = 2.0f;
        vRadius = 2.0f;
        iterations = 7;
    }

    public AppImageMgr(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public Bitmap getBitmap(int resource) {
        if (!this.mBitmaps.containsKey(Integer.valueOf(resource)) && this.mContext != null) {
            this.mBitmaps.put(Integer.valueOf(resource), new WeakReference<>(readDrawableBitmap(this.mContext, resource)));
        }
        return this.mBitmaps.get(Integer.valueOf(resource)).get();
    }

    public Drawable getDrawable(int resource) {
        try {
            if (!this.mDrawables.containsKey(Integer.valueOf(resource)) && this.mContext != null) {
                try {
                    this.mDrawables.put(Integer.valueOf(resource), new WeakReference<>(this.mContext.getResources().getDrawable(resource)));
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                }
            }
            return this.mDrawables.get(Integer.valueOf(resource)).get();
        } catch (Exception e3) {
            e3.printStackTrace();
            return readBitmapResIdToDrawable(this.mContext, resource);
        }
    }

    public static Drawable bitmapToDrawble(Uri uri, Context mcontext) {
        return new BitmapDrawable(mcontext.getResources(), getBitmapFromUri(mcontext, uri));
    }

    public void recycleBitmaps() {
        Bitmap bitmap;
        for (Map.Entry<Integer, WeakReference<Bitmap>> entry : this.mBitmaps.entrySet()) {
            if (entry != null && (bitmap = entry.getValue().get()) != null) {
                bitmap.recycle();
            }
        }
        this.mBitmaps.clear();
    }

    public static Drawable readBitmapResIdToDrawable(Context context, int resId) throws Resources.NotFoundException, IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(resId);
        BitmapDrawable bitmapDrawable = null;
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenRawResource, null, options);
        if (bitmapDecodeStream != null) {
            bitmapDrawable = new BitmapDrawable(bitmapDecodeStream);
            if (inputStreamOpenRawResource != null) {
                try {
                    inputStreamOpenRawResource.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return bitmapDrawable;
    }

    public static Bitmap readDrawableBitmap(Context context, int resId) throws Resources.NotFoundException, IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(resId);
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenRawResource, null, options);
        if (inputStreamOpenRawResource != null) {
            try {
                inputStreamOpenRawResource.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return bitmapDecodeStream;
    }

    public static Bitmap readBitmap565FromFile(String filename) {
        Bitmap bitmapDecodeFile;
        File file = new File(filename);
        Bitmap bitmap = null;
        if (!file.exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            bitmapDecodeFile = BitmapFactory.decodeFile(filename, options);
            if (bitmapDecodeFile == null) {
                try {
                    file.delete();
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    e.printStackTrace();
                    if (bitmapDecodeFile == null || bitmapDecodeFile.isRecycled()) {
                        bitmap = bitmapDecodeFile;
                    } else {
                        bitmapDecodeFile.recycle();
                    }
                    System.gc();
                    return bitmap;
                }
            }
            return bitmapDecodeFile;
        } catch (OutOfMemoryError e3) {
            e = e3;
            bitmapDecodeFile = null;
        }
    }

    public static Bitmap readDrawableBigBitmap(Context context, int resId) throws Resources.NotFoundException, IOException {
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(resId);
        Bitmap bitmapFromStream = getBitmapFromStream(inputStreamOpenRawResource, 256, 256);
        if (inputStreamOpenRawResource != null) {
            try {
                inputStreamOpenRawResource.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return bitmapFromStream;
    }

    public static Bitmap getBitmapFromFile(String path, int width, int height) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        return getBitmapFromFile(new File(path), width, height);
    }

    public static Bitmap getBitmapFromFile(File dst, int width, int height) {
        BitmapFactory.Options options;
        if (dst != null && dst.exists()) {
            if (width <= 0 || height <= 0) {
                options = null;
            } else {
                options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), options);
                options.inSampleSize = computeSampleSize(options, Math.min(width, height), width * height);
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inJustDecodeBounds = false;
                options.inInputShareable = true;
                options.inPurgeable = true;
            }
            try {
                return BitmapFactory.decodeFile(dst.getPath(), options);
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
                System.gc();
            }
        }
        return null;
    }

    public static Bitmap getBitmapByteArray(byte[] data, int width, int height) {
        BitmapFactory.Options options;
        if (width <= 0 || height <= 0) {
            options = null;
        } else {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            options.inSampleSize = computeSampleSize(options, Math.min(width, height), width * height);
            options.inJustDecodeBounds = false;
            options.inInputShareable = true;
            options.inPurgeable = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        try {
            return BitmapFactory.decodeByteArray(data, 0, data.length, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            System.gc();
            return null;
        }
    }

    public static Bitmap getBitmapFromStream(InputStream is, int width, int height) {
        BitmapFactory.Options options;
        if (width <= 0 || height <= 0) {
            options = null;
        } else {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, options);
            options.inSampleSize = computeSampleSize(options, Math.min(width, height), width * height);
            options.inJustDecodeBounds = false;
            options.inInputShareable = true;
            options.inPurgeable = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        try {
            return BitmapFactory.decodeStream(is, null, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            System.gc();
            return null;
        }
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int iMin;
        double d2 = options.outWidth;
        double d3 = options.outHeight;
        int iCeil = maxNumOfPixels == -1 ? 1 : (int) Math.ceil(Math.sqrt((d2 * d3) / maxNumOfPixels));
        if (minSideLength == -1) {
            iMin = 128;
        } else {
            double d4 = minSideLength;
            iMin = (int) Math.min(Math.floor(d2 / d4), Math.floor(d3 / d4));
        }
        if (iMin < iCeil) {
            return iCeil;
        }
        if (maxNumOfPixels == -1 && minSideLength == -1) {
            return 1;
        }
        return minSideLength == -1 ? iCeil : iMin;
    }

    public static Bitmap setAlpha(Bitmap sourceImg, int number) {
        try {
            int width = sourceImg.getWidth() * sourceImg.getHeight();
            int[] iArr = new int[width];
            sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
            int i2 = (number * 255) / 100;
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = iArr[i3];
                if (((-16777216) & i4) != 0) {
                    iArr[i3] = (i4 & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
                }
            }
            return Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            System.gc();
            return sourceImg;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmapCreateBitmap;
        Bitmap bitmap = null;
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } catch (OutOfMemoryError e2) {
            e = e2;
            bitmapCreateBitmap = null;
        }
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (OutOfMemoryError e3) {
            e = e3;
            e.printStackTrace();
            if (bitmapCreateBitmap == null || bitmapCreateBitmap.isRecycled()) {
                bitmap = bitmapCreateBitmap;
            } else {
                bitmapCreateBitmap.recycle();
            }
            System.gc();
            return bitmap;
        }
    }

    public static Bitmap getImgCacheFromLocal2Bitmap(String sImagePath) throws IOException {
        Bitmap bitmapDecodeStream;
        if (!TextUtils.isEmpty(sImagePath)) {
            try {
                File file = new File(sImagePath);
                if (!file.exists()) {
                    return null;
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize = 1;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
                try {
                    fileInputStream.close();
                    return bitmapDecodeStream;
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (bitmapDecodeStream != null && !bitmapDecodeStream.isRecycled()) {
                        bitmapDecodeStream.recycle();
                    }
                    System.gc();
                    return null;
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    e.printStackTrace();
                    if (bitmapDecodeStream != null && !bitmapDecodeStream.isRecycled()) {
                        bitmapDecodeStream.recycle();
                    }
                    System.gc();
                    return null;
                }
            } catch (Exception e4) {
                e = e4;
                bitmapDecodeStream = null;
            } catch (OutOfMemoryError e5) {
                e = e5;
                bitmapDecodeStream = null;
            }
        }
        return null;
    }

    public static byte[] getImgCacheFromLocal2Byte(String sImagePath) throws IOException {
        if (!TextUtils.isEmpty(sImagePath)) {
            try {
                File file = new File(sImagePath);
                if (!file.exists()) {
                    return null;
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                fileInputStream.close();
                return bArr;
            } catch (Exception e2) {
                e2.printStackTrace();
                System.gc();
            }
        }
        return null;
    }

    public static byte[] getBitmap2Byte(Bitmap bitmap) throws IOException {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return byteArray;
    }

    public static Bitmap decodeBitmapToThumbnail(Bitmap bitmap) {
        return decodeBitmapToThumbnail(bitmap, true);
    }

    public static Bitmap decodeBitmapToThumbnail(Bitmap bitmap, boolean isThumbnail) throws IOException {
        if (!isThumbnail) {
            return bitmap;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        float f2 = options.outWidth;
        float f3 = options.outHeight;
        if (f3 > f2) {
            f2 = f3;
        }
        int i2 = (int) (f2 / 100.0f);
        options.inSampleSize = i2 > 0 ? i2 : 1;
        options.inJustDecodeBounds = false;
        byte[] bitmap2Byte = getBitmap2Byte(bitmap);
        return bitmap2Byte != null ? BitmapFactory.decodeByteArray(bitmap2Byte, 0, bitmap2Byte.length, options) : bitmap;
    }

    public static boolean saveImage(Bitmap oldbitmap, String sNewImagePath) throws IOException {
        try {
            File parentFile = new File(sNewImagePath).getParentFile();
            if (parentFile == null) {
                return false;
            }
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(sNewImagePath);
            oldbitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            System.gc();
            return false;
        }
    }

    public static boolean saveImage(byte[] oldbitmap, String sNewImagePath) throws IOException {
        try {
            File file = new File(sNewImagePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(sNewImagePath);
            fileOutputStream.write(oldbitmap);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            System.gc();
            return false;
        }
    }

    public static Bitmap bytes2Bimap(byte[] b2) {
        if (b2 == null || b2.length == 0) {
            return null;
        }
        try {
            return BitmapFactory.decodeByteArray(b2, 0, b2.length);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap grayMasking(Bitmap bmp, int x, int y, float r) {
        int[] iArr = {1, 2, 1, 2, 4, 2, 1, 2, 1};
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int[] iArr2 = new int[width * height];
        bmp.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i2 = height - 1;
        int i3 = 1;
        while (i3 < i2) {
            int i4 = width - 1;
            int i5 = 1;
            while (i5 < i4) {
                int i6 = i3;
                if (((int) (Math.pow(i5 - x, 2.0d) + Math.pow(i3 - y, 2.0d))) > r * r) {
                    int i7 = 0;
                    int i8 = 0;
                    int i9 = 0;
                    int i10 = 0;
                    for (int i11 = -1; i11 <= 1; i11++) {
                        for (int i12 = -1; i12 <= 1; i12++) {
                            int i13 = iArr2[((i6 + i11) * width) + i5 + i12];
                            int iRed = Color.red(i13);
                            int iGreen = Color.green(i13);
                            int iBlue = Color.blue(i13);
                            int i14 = iArr[i10];
                            i7 += iRed * i14;
                            i8 += iGreen * i14;
                            i9 += iBlue * i14;
                            i10++;
                        }
                    }
                    iArr2[(i6 * width) + i5] = Color.argb(255, Math.min(255, Math.max(0, i7 / 18)), Math.min(255, Math.max(0, i8 / 18)), Math.min(255, Math.max(0, i9 / 18)));
                }
                i5++;
                i3 = i6;
            }
            i3++;
        }
        bitmapCreateBitmap.setPixels(iArr2, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    public static int getBitmapSize(Bitmap bitmap) {
        return bitmap.getByteCount();
    }

    public static byte[] getBytes(String in) {
        byte[] bArr = new byte[in.length() * 2];
        int i2 = 0;
        for (char c2 : in.toCharArray()) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (c2 & 255);
            i2 += 2;
            bArr[i3] = (byte) (c2 >> '\b');
        }
        return bArr;
    }

    public static boolean isSameKey(byte[] key, byte[] buffer) {
        int length = key.length;
        if (buffer.length < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (key[i2] != buffer[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] copyOfRange(byte[] original, int from, int to) {
        int i2 = to - from;
        if (i2 < 0) {
            throw new IllegalArgumentException(from + " > " + to);
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(original, from, bArr, 0, Math.min(original.length - from, i2));
        return bArr;
    }

    public static byte[] makeKey(String httpUrl) {
        return getBytes(httpUrl);
    }

    public static final long crc64Long(String in) {
        if (in == null || in.length() == 0) {
            return 0L;
        }
        return crc64Long(getBytes(in));
    }

    public static final long crc64Long(byte[] buffer) {
        long j2 = -1;
        for (byte b2 : buffer) {
            j2 = (j2 >> 8) ^ sCrcTable[(((int) j2) ^ b2) & 255];
        }
        return j2;
    }

    public static Bitmap convertToBlackWhite(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] iArr = new int[width * height];
        bmp.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = (width * i2) + i3;
                int i5 = iArr[i4];
                int i6 = (int) ((((16711680 & i5) >> 16) * 0.3d) + (((65280 & i5) >> 8) * 0.59d) + ((i5 & 255) * 0.11d));
                iArr[i4] = i6 | (i6 << 16) | ViewCompat.MEASURED_STATE_MASK | (i6 << 8);
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    public static Bitmap convertToRoundedCorner(Bitmap bmp, float roundPx) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bmp, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap BoxBlurFilter(Bitmap bmp) {
        System.currentTimeMillis();
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int i2 = width * height;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmp.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i3 = 0; i3 < iterations; i3++) {
            blur(iArr, iArr2, width, height, hRadius);
            blur(iArr2, iArr, height, width, vRadius);
        }
        blurFractional(iArr, iArr2, width, height, hRadius);
        blurFractional(iArr2, iArr, height, width, vRadius);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        System.currentTimeMillis();
        return bitmapCreateBitmap;
    }

    public static void blur(int[] in, int[] out, int width, int height, float radius) {
        int i2 = width - 1;
        int i3 = (int) radius;
        int i4 = (i3 * 2) + 1;
        int i5 = i4 * 256;
        int[] iArr = new int[i5];
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            iArr[i7] = i7 / i4;
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < height) {
            int i10 = i6;
            int i11 = i10;
            int i12 = i11;
            int i13 = i12;
            for (int i14 = -i3; i14 <= i3; i14++) {
                int i15 = in[clamp(i14, i6, i2) + i9];
                i10 += (i15 >> 24) & 255;
                i11 += (i15 >> 16) & 255;
                i12 += (i15 >> 8) & 255;
                i13 += i15 & 255;
            }
            int i16 = i8;
            int i17 = i6;
            while (i17 < width) {
                out[i16] = (iArr[i10] << 24) | (iArr[i11] << 16) | (iArr[i12] << 8) | iArr[i13];
                int i18 = i17 + i3 + 1;
                if (i18 > i2) {
                    i18 = i2;
                }
                int i19 = i17 - i3;
                if (i19 < 0) {
                    i19 = i6;
                }
                int i20 = in[i18 + i9];
                int i21 = in[i19 + i9];
                i10 += ((i20 >> 24) & 255) - ((i21 >> 24) & 255);
                i11 += ((i20 & 16711680) - (16711680 & i21)) >> 16;
                i12 += ((i20 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) - (65280 & i21)) >> 8;
                i13 += (i20 & 255) - (i21 & 255);
                i16 += height;
                i17++;
                i2 = i2;
                i6 = 0;
            }
            i9 += width;
            i8++;
            i6 = 0;
        }
    }

    private static void blurFractional(int[] in, int[] out, int width, int height, float radius) {
        int i2;
        float f2 = radius - ((int) radius);
        float f3 = 1.0f / ((2.0f * f2) + 1.0f);
        char c2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < height) {
            out[i3] = in[c2];
            int i5 = i3 + height;
            int i6 = 1;
            int i7 = 1;
            while (true) {
                i2 = width - 1;
                if (i7 < i2) {
                    int i8 = i4 + i7;
                    int i9 = in[i8 - 1];
                    int i10 = in[i8];
                    int i11 = in[i8 + i6];
                    int i12 = (i10 >> 24) & 255;
                    int i13 = (i10 >> 8) & 255;
                    out[i5] = (((int) ((((i10 >> 16) & 255) + ((int) ((((i9 >> 16) & 255) + ((i11 >> 16) & 255)) * f2))) * f3)) << 16) | (((int) ((i12 + ((int) ((((i9 >> 24) & 255) + ((i11 >> 24) & 255)) * f2))) * f3)) << 24) | (((int) ((i13 + ((int) ((((i9 >> 8) & 255) + ((i11 >> 8) & 255)) * f2))) * f3)) << 8) | ((int) (((i10 & 255) + ((int) (((i9 & 255) + (i11 & 255)) * f2))) * f3));
                    i5 += height;
                    i7++;
                    i3 = i3;
                    i4 = i4;
                    i6 = 1;
                }
            }
            out[i5] = in[i2];
            i4 += width;
            i3++;
            c2 = 0;
        }
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        float f2;
        float f3;
        float f4;
        float f5;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f5 = width / 2;
            f4 = width;
            f2 = 0.0f;
            f3 = f4;
        } else {
            f2 = (width - height) / 2;
            f3 = height;
            f4 = width - f2;
            width = height;
            f5 = height / 2;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f2, (int) 0.0f, (int) f4, (int) f3);
        Rect rect2 = new Rect((int) 0.0f, (int) 0.0f, (int) f3, (int) f3);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f5, f5, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap bitmapCreateBitmap;
        if (bitmap == null || (bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888)) == null) {
            return null;
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f2 = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap getImageFromAssetsFile(String str, Context context) throws Throwable {
        Bitmap bitmap;
        InputStream inputStream = null;
        Bitmap bitmapDecodeStream = null;
        inputStream = null;
        try {
            try {
                InputStream inputStreamOpen = context.getResources().getAssets().open(str);
                try {
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
                    inputStreamOpen.close();
                    if (inputStreamOpen == null) {
                        return bitmapDecodeStream;
                    }
                    try {
                        inputStreamOpen.close();
                        return bitmapDecodeStream;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return bitmapDecodeStream;
                    }
                } catch (IOException e3) {
                    e = e3;
                    Bitmap bitmap2 = bitmapDecodeStream;
                    inputStream = inputStreamOpen;
                    bitmap = bitmap2;
                    e.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return bitmap;
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStreamOpen;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e = e6;
            bitmap = null;
        }
    }

    public static InputStream getImageFromAssetsFileInputStream(String filepath, Context context) {
        try {
            return context.getResources().getAssets().open(filepath);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap lessenBitmap(Context context, int resId, int destWidth, int destHeigth) throws Resources.NotFoundException, IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(resId);
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenRawResource, null, options);
        if (inputStreamOpenRawResource != null) {
            try {
                inputStreamOpenRawResource.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        int width = bitmapDecodeStream.getWidth();
        int height = bitmapDecodeStream.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(destWidth / width, destHeigth / height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeStream, 0, 0, width, height, matrix, true);
        if (!bitmapDecodeStream.isRecycled()) {
            bitmapDecodeStream.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static int readPictureDegree(String path) {
        int i2;
        try {
            int attributeInt = new ExifInterface(path).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                i2 = 180;
            } else if (attributeInt == 6) {
                i2 = 90;
            } else {
                if (attributeInt != 8) {
                    return 0;
                }
                i2 = 270;
            }
            return i2;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotaingImageView(String path, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        int pictureDegree = readPictureDegree(path);
        if (pictureDegree != 0) {
            matrix.postRotate(pictureDegree);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap rotateBitmap(Bitmap bm, float curDegrees) {
        return rotateBitmap(bm, curDegrees, true);
    }

    public static Bitmap rotateBitmap(Bitmap bm, float curDegrees, boolean isRecycled) {
        if (bm == null) {
            return null;
        }
        int width = bm.getWidth();
        int height = bm.getHeight();
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(curDegrees);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        if (isRecycled && !bm.isRecycled()) {
            bm.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (Exception e2) {
            e2.getMessage();
            return null;
        }
    }

    public static String getPicPathFromUri(Uri uri, Activity activity) throws IllegalArgumentException {
        Cursor cursorManagedQuery;
        String path = uri.getPath();
        try {
            if (!path.startsWith("/external") || (cursorManagedQuery = activity.managedQuery(uri, new String[]{"_data"}, null, null, null)) == null || cursorManagedQuery.getCount() <= 0) {
                return path;
            }
            int columnIndexOrThrow = cursorManagedQuery.getColumnIndexOrThrow("_data");
            cursorManagedQuery.moveToFirst();
            String string = cursorManagedQuery.getString(columnIndexOrThrow);
            if (Integer.parseInt(Build.VERSION.SDK) < 14 && cursorManagedQuery != null) {
                cursorManagedQuery.close();
            }
            return string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Bitmap getPathToBitmap(String filePath) {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(filePath);
        int pictureDegree = readPictureDegree(filePath);
        return pictureDegree != 0 ? rotateBitmap(bitmapDecodeFile, pictureDegree) : bitmapDecodeFile;
    }

    public static Bitmap getSmallBitmap(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, GlMapUtil.DEVICE_DISPLAY_DPI_XHIGH, 800);
        options.inJustDecodeBounds = false;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(filePath, options);
        int pictureDegree = readPictureDegree(filePath);
        return pictureDegree != 0 ? rotateBitmap(bitmapDecodeFile, pictureDegree) : bitmapDecodeFile;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i2 = options.outHeight;
        int i3 = options.outWidth;
        if (i2 <= reqHeight && i3 <= reqWidth) {
            return 1;
        }
        int iRound = Math.round(i2 / reqHeight);
        int iRound2 = Math.round(i3 / reqWidth);
        return iRound < iRound2 ? iRound : iRound2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.io.ByteArrayOutputStream] */
    public static byte[] decodeBitmap(String str) throws Throwable {
        ?? r9;
        ByteArrayOutputStream byteArrayOutputStream;
        Bitmap bitmapCreateScaledBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        ?? fileInputStream = 819200;
        options.inSampleSize = computeSampleSize(options, -1, 819200);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inDither = false;
        options.inPurgeable = true;
        options.inTempStorage = new byte[16384];
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    Bitmap bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
                    double scaling = getScaling(options.outWidth * options.outHeight, 614400);
                    bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeFileDescriptor, (int) (options.outWidth * scaling), (int) (options.outHeight * scaling), true);
                    bitmapDecodeFileDescriptor.recycle();
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (FileNotFoundException e2) {
                    e = e2;
                    byteArrayOutputStream = null;
                } catch (IOException e3) {
                    e = e3;
                    byteArrayOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    str = null;
                    fileInputStream2 = fileInputStream;
                    r9 = str;
                    try {
                        fileInputStream2.close();
                        r9.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    System.gc();
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                byteArrayOutputStream = null;
                fileInputStream = 0;
            } catch (IOException e6) {
                e = e6;
                byteArrayOutputStream = null;
                fileInputStream = 0;
            } catch (Throwable th2) {
                th = th2;
                r9 = 0;
                fileInputStream2.close();
                r9.close();
                System.gc();
                throw th;
            }
            try {
                bitmapCreateScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                bitmapCreateScaledBitmap.recycle();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                System.gc();
                return byteArray;
            } catch (FileNotFoundException e8) {
                e = e8;
                e.printStackTrace();
                try {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e9) {
                    e = e9;
                    e.printStackTrace();
                    System.gc();
                    return null;
                }
                System.gc();
                return null;
            } catch (IOException e10) {
                e = e10;
                e.printStackTrace();
                try {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e11) {
                    e = e11;
                    e.printStackTrace();
                    System.gc();
                    return null;
                }
                System.gc();
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static double getScaling(int src, int des) {
        return Math.sqrt(des / src);
    }

    private static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int iComputeInitialSampleSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        if (iComputeInitialSampleSize > 8) {
            return 8 * ((iComputeInitialSampleSize + 7) / 8);
        }
        int i2 = 1;
        while (i2 < iComputeInitialSampleSize) {
            i2 <<= 1;
        }
        return i2;
    }

    public static float applyDimension(Context context, int unit, float value) {
        return TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }

    public static String getImageFormat(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        String str = options.outMimeType;
        if (TextUtils.isEmpty(str)) {
            return "未能识别的图片";
        }
        return str.substring(6, str.length());
    }

    public static String getImageFormat(Resources res, int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);
        String str = options.outMimeType;
        if (TextUtils.isEmpty(str)) {
            return "未能识别的图片";
        }
        return str.substring(6, str.length());
    }

    public static String getImageFormat(byte[] data, int offset, int length) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, offset, length);
        String str = options.outMimeType;
        if (TextUtils.isEmpty(str)) {
            return "未能识别的图片";
        }
        return str.substring(6, str.length());
    }
}
