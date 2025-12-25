package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yucheng.smarthealthpro.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QrCodeUtils.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J$\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J4\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bJ\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u0002¨\u0006\u001b"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/QrCodeUtils;", "", "<init>", "()V", "decodeUri", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", ShareConstants.MEDIA_URI, "Landroid/net/Uri;", "maxWidth", "", "maxHeight", "resolveUri", "", "options", "Landroid/graphics/BitmapFactory$Options;", "resolveUriForBitmap", "createQRImage", FirebaseAnalytics.Param.CONTENT, "", "logoBm", "widthPix", "heightPix", "addLogo", "src", "logo", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class QrCodeUtils {
    public static final QrCodeUtils INSTANCE = new QrCodeUtils();

    private QrCodeUtils() {
    }

    public final Bitmap decodeUri(Context context, Uri uri, int maxWidth, int maxHeight) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i2 = 1;
        options.inJustDecodeBounds = true;
        resolveUri(context, uri, options);
        for (int i3 = 0; i3 < Integer.MAX_VALUE && ((options.outWidth / i2 > maxWidth && options.outWidth / i2 > maxWidth * 1.4d) || (options.outHeight / i2 > maxHeight && options.outHeight / i2 > maxHeight * 1.4d)); i3++) {
            i2++;
        }
        options.inSampleSize = i2;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        try {
            return resolveUriForBitmap(context, uri, options);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private final void resolveUri(Context context, Uri uri, BitmapFactory.Options options) throws Throwable {
        StringBuilder sb;
        if (uri == null) {
            return;
        }
        String scheme = uri.getScheme();
        if (Intrinsics.areEqual(FirebaseAnalytics.Param.CONTENT, scheme) || Intrinsics.areEqual("file", scheme)) {
            InputStream inputStream = null;
            try {
                try {
                    InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                    try {
                        BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                        if (inputStreamOpenInputStream != null) {
                            try {
                                inputStreamOpenInputStream.close();
                            } catch (IOException e2) {
                                e = e2;
                                sb = new StringBuilder("Unable to close content: ");
                                Log.w("resolveUri", sb.append(uri).toString(), e);
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        inputStream = inputStreamOpenInputStream;
                        Integer.valueOf(Log.w("resolveUri", "Unable to open content: " + uri, e));
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                e = e4;
                                sb = new StringBuilder("Unable to close content: ");
                                Log.w("resolveUri", sb.append(uri).toString(), e);
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamOpenInputStream;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e5) {
                                Log.w("resolveUri", "Unable to close content: " + uri, e5);
                            }
                        }
                        throw th;
                    }
                } catch (Exception e6) {
                    e = e6;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else if (Intrinsics.areEqual("android.resource", scheme)) {
            Log.w("resolveUri", "Unable to close content: " + uri);
        } else {
            Log.w("resolveUri", "Unable to close content: " + uri);
        }
    }

    private final Bitmap resolveUriForBitmap(Context context, Uri uri, BitmapFactory.Options options) throws Throwable {
        Bitmap bitmap;
        InputStream inputStream = null;
        Bitmap bitmapDecodeStream = null;
        inputStream = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (Intrinsics.areEqual(FirebaseAnalytics.Param.CONTENT, scheme) || Intrinsics.areEqual("file", scheme)) {
            try {
                try {
                    InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                    try {
                        bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                        Unit unit = Unit.INSTANCE;
                        if (inputStreamOpenInputStream == null) {
                            return bitmapDecodeStream;
                        }
                        try {
                            inputStreamOpenInputStream.close();
                            return bitmapDecodeStream;
                        } catch (IOException e2) {
                            Log.w("resolveUriForBitmap", "Unable to close content: " + uri, e2);
                            return bitmapDecodeStream;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        Bitmap bitmap2 = bitmapDecodeStream;
                        inputStream = inputStreamOpenInputStream;
                        bitmap = bitmap2;
                        Integer.valueOf(Log.w("resolveUriForBitmap", "Unable to open content: " + uri, e));
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                Log.w("resolveUriForBitmap", "Unable to close content: " + uri, e4);
                            }
                        }
                        return bitmap;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamOpenInputStream;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e5) {
                                Log.w("resolveUriForBitmap", "Unable to close content: " + uri, e5);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e6) {
                e = e6;
                bitmap = null;
            }
        } else {
            if (Intrinsics.areEqual("android.resource", scheme)) {
                Log.w("resolveUriForBitmap", "Unable to close content: " + uri);
                return null;
            }
            Log.w("resolveUriForBitmap", "Unable to close content: " + uri);
            return null;
        }
    }

    public final Bitmap createQRImage(Context context, String content, Bitmap logoBm, int widthPix, int heightPix) {
        BitMatrix bitMatrixEncode;
        Intrinsics.checkNotNullParameter(context, "context");
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
                    Intrinsics.checkNotNull(bitMatrixEncode);
                    if (bitMatrixEncode.get(i3, i2)) {
                        iArr[(i2 * widthPix) + i3] = context.getColor(R.color.black_light);
                    } else {
                        iArr[(i2 * widthPix) + i3] = -1;
                    }
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
            bitmapCreateBitmap.setPixels(iArr, 0, widthPix, 0, 0, widthPix, heightPix);
            if (logoBm == null) {
                return bitmapCreateBitmap;
            }
            Bitmap bitmapAddLogo = addLogo(bitmapCreateBitmap, logoBm);
            Intrinsics.checkNotNull(bitmapAddLogo);
            return bitmapAddLogo;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private final Bitmap addLogo(Bitmap src, Bitmap logo) {
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
        float f2 = ((width * 1.0f) / 5) / width2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Intrinsics.checkNotNull(bitmapCreateBitmap);
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
}
