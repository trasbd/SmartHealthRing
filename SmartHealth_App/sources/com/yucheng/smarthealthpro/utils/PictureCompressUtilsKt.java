package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.utils.Constant;
import java.io.ByteArrayOutputStream;
import java.io.File;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PictureCompressUtils.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001\u001a\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u001e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001\u001a \u0010\u0015\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"IMAGE_MAX_SIZE", "", "isFileSizeExceedsLimit", "", "context", "Landroid/content/Context;", "fileUri", "Landroid/net/Uri;", "maxSize", "retrieveFileLength", "", "calculateInSampleSize", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "compressImageToTargetSize", "", "bitmap", "Landroid/graphics/Bitmap;", "targetSizeKB", "compressImageToUri", Constant.SpConstKey.IMAGE_PATH, "", "app_SmartHealthRelease"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PictureCompressUtilsKt {
    public static final int IMAGE_MAX_SIZE = 2097152;

    public static final boolean isFileSizeExceedsLimit(Context context, Uri fileUri, int i2) {
        long jRetrieveFileLength;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileUri, "fileUri");
        try {
            jRetrieveFileLength = retrieveFileLength(context, fileUri);
            Logger.d("isFileSizeExceedsLimit: size: " + jRetrieveFileLength, new Object[0]);
        } catch (Exception unused) {
        }
        return jRetrieveFileLength > ((long) i2);
    }

    private static final long retrieveFileLength(Context context, Uri uri) {
        Cursor cursorQuery;
        String scheme = uri.getScheme();
        if (scheme == null) {
            return 0L;
        }
        int iHashCode = scheme.hashCode();
        if (iHashCode == 3143036) {
            if (scheme.equals("file")) {
                return new File(uri.getPath()).length();
            }
            return 0L;
        }
        if (iHashCode != 951530617 || !scheme.equals(FirebaseAnalytics.Param.CONTENT) || (cursorQuery = context.getContentResolver().query(uri, null, null, null, null)) == null) {
            return 0L;
        }
        int columnIndex = cursorQuery.getColumnIndex("_size");
        cursorQuery.moveToFirst();
        long j2 = cursorQuery.getLong(columnIndex);
        cursorQuery.close();
        return j2;
    }

    public static final int calculateInSampleSize(BitmapFactory.Options options, int i2, int i3) {
        Intrinsics.checkNotNullParameter(options, "options");
        Pair pair = TuplesKt.to(Integer.valueOf(options.outHeight), Integer.valueOf(options.outWidth));
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        int i4 = 1;
        if (iIntValue > i3 || iIntValue2 > i2) {
            int i5 = iIntValue / 2;
            int i6 = iIntValue2 / 2;
            while (i5 / i4 >= i3 && i6 / i4 >= i2) {
                i4 *= 2;
            }
        }
        return i4;
    }

    public static final byte[] compressImageToTargetSize(Bitmap bitmap, int i2) {
        byte[] byteArray;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 100;
        do {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            i3 -= 5;
            Intrinsics.checkNotNull(byteArray);
            if (byteArray.length / 1024 <= i2) {
                break;
            }
        } while (i3 > 0);
        if (byteArray.length / 1024 <= i2) {
            return byteArray;
        }
        return null;
    }

    public static final Uri compressImageToUri(Context context, String imagePath, int i2) {
        byte[] bArrCompressImageToTargetSize;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inSampleSize = calculateInSampleSize(options, 1080, 1080);
        options.inJustDecodeBounds = false;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(imagePath, options);
        if (bitmapDecodeFile == null || (bArrCompressImageToTargetSize = compressImageToTargetSize(bitmapDecodeFile, i2)) == null) {
            return null;
        }
        bitmapDecodeFile.recycle();
        File file = new File(context.getCacheDir(), "compress_" + System.currentTimeMillis() + ".jpg");
        FilesKt.writeBytes(file, bArrCompressImageToTargetSize);
        return Uri.fromFile(file);
    }
}
