package com.yucheng.smarthealthpro.care.utils;

import android.graphics.Bitmap;

/* loaded from: classes4.dex */
public class ImageViewUtil {
    public static Bitmap bitmap;
    private static ImageViewUtil imageViewUtil;

    public static ImageViewUtil getInstance(Bitmap bitmap2) {
        bitmap = bitmap2;
        if (imageViewUtil == null) {
            imageViewUtil = new ImageViewUtil();
        }
        return imageViewUtil;
    }

    public static void recyle() {
        Bitmap bitmap2 = bitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            bitmap = null;
        }
    }
}
