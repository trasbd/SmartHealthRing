package com.yucheng.smarthealthpro.care.zxing.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yucheng.smarthealthpro.R;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class QrImageUtil {
    public static Bitmap createQRImage(Context context, String content, Bitmap logoBm, int widthPix, int heightPix) {
        BitMatrix bitMatrixEncode;
        try {
            HashMap map = new HashMap();
            map.put(EncodeHintType.CHARACTER_SET, "utf-8");
            map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            map.put(EncodeHintType.MARGIN, 1);
            try {
                bitMatrixEncode = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, map);
            } catch (WriterException e2) {
                e2.printStackTrace();
                bitMatrixEncode = null;
            }
            int[] iArr = new int[widthPix * heightPix];
            for (int i2 = 0; i2 < heightPix; i2++) {
                for (int i3 = 0; i3 < widthPix; i3++) {
                    if (bitMatrixEncode.get(i3, i2)) {
                        iArr[(i2 * widthPix) + i3] = context.getColor(R.color.colorAccent);
                    } else {
                        iArr[(i2 * widthPix) + i3] = -1;
                    }
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, widthPix, 0, 0, widthPix, heightPix);
            return logoBm != null ? addLogo(bitmapCreateBitmap, logoBm) : bitmapCreateBitmap;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }
        if (logo == null) {
            return src;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        int width2 = logo.getWidth();
        int height2 = logo.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return src;
        }
        float f2 = ((width * 1.0f) / 5.0f) / width2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(src, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f2, f2, width / 2, height / 2);
            canvas.drawBitmap(logo, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            e2.getStackTrace();
            return null;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int width, int height, int roundPx) {
        if (bitmap == null) {
            return null;
        }
        int i2 = roundPx * 2;
        int i3 = width + i2;
        int i4 = i2 + height;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(new Rect(0, 0, i3, i4));
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        float f2 = roundPx;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawBitmap(getRoundBitmapByShader(bitmap, width, height, roundPx, 1), f2, f2, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap getRoundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius, int boarder) {
        if (bitmap == null) {
            return null;
        }
        float height = (outHeight * 1.0f) / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale((outWidth * 1.0f) / bitmap.getWidth(), height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
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
            paint2.setColor(Color.parseColor("#444444"));
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(f2);
            canvas.drawRoundRect(rectF, f3, f3, paint2);
        }
        return bitmapCreateBitmap;
    }
}
