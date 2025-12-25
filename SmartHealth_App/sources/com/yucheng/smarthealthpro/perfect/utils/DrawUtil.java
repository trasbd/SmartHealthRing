package com.yucheng.smarthealthpro.perfect.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class DrawUtil {
    public static final int NAVBAR_LOCATION_BOTTOM = 2;
    public static final int NAVBAR_LOCATION_RIGHT = 1;
    private static Class sClass = null;
    public static float sDensity = 1.0f;
    public static int sDensityDpi = 0;
    public static float sFontDensity = 0.0f;
    public static int sHeightPixels = 0;
    private static Method sMethodForHeight = null;
    private static Method sMethodForWidth = null;
    private static int sNavBarHeight = 0;
    public static int sNavBarLocation = 0;
    private static int sNavBarWidth = 0;
    private static int sRealHeightPixels = 0;
    private static int sRealWidthPixels = 0;
    public static int sStatusBar = 0;
    public static int sStatusHeight = 0;
    public static int sTopStatusHeight = 0;
    public static int sTouchSlop = 15;
    public static float sVirtualDensity = -1.0f;
    public static float sVirtualDensityDpi = -1.0f;
    public static int sWidthPixels;

    public static int dip2px(float dipVlue) {
        return (int) ((dipVlue * sDensity) + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) ((pxValue / sDensity) + 0.5f);
    }

    public static int sp2px(float spValue) {
        return (int) (sDensity * spValue);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / sDensity);
    }

    public static void resetDensity(Context context) throws IllegalAccessException, Resources.NotFoundException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (context != null && context.getResources() != null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            sDensity = displayMetrics.density;
            sFontDensity = displayMetrics.scaledDensity;
            sWidthPixels = displayMetrics.widthPixels;
            sHeightPixels = displayMetrics.heightPixels;
            sDensityDpi = displayMetrics.densityDpi;
            if (Machine.isTablet(context)) {
                sStatusHeight = getTabletScreenHeight(context) - sHeightPixels;
            }
            try {
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                if (viewConfiguration != null) {
                    sTouchSlop = viewConfiguration.getScaledTouchSlop();
                }
                getStatusBarHeight(context);
            } catch (Error e2) {
                Log.i("DrawUtil", "resetDensity has error" + e2.getMessage());
            }
            sStatusBar = getStatusBarHeight(context);
        }
        resetNavBarHeight(context);
    }

    private static void resetNavBarHeight(Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (context != null) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            try {
                if (sClass == null) {
                    sClass = Class.forName("android.view.Display");
                }
                Point point = new Point();
                sClass.getMethod("getRealSize", Point.class).invoke(defaultDisplay, point);
                sRealWidthPixels = point.x;
                sRealHeightPixels = point.y;
                sNavBarWidth = point.x - sWidthPixels;
                sNavBarHeight = point.y - sHeightPixels;
            } catch (Exception unused) {
                sRealWidthPixels = sWidthPixels;
                sRealHeightPixels = sHeightPixels;
                sNavBarHeight = 0;
            }
        }
        sNavBarLocation = getNavBarLocation();
    }

    public static int getTabletScreenWidth(Context context) {
        int iIntValue = 0;
        if (context != null) {
            try {
                Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                if (sClass == null) {
                    sClass = Class.forName("android.view.Display");
                }
                if (sMethodForWidth == null) {
                    sMethodForWidth = sClass.getMethod("getRealWidth", new Class[0]);
                }
                iIntValue = ((Integer) sMethodForWidth.invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception unused) {
            }
        }
        return iIntValue == 0 ? sWidthPixels : iIntValue;
    }

    public static int getTabletScreenHeight(Context context) {
        int iIntValue = 0;
        if (context != null) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            try {
                if (sClass == null) {
                    sClass = Class.forName("android.view.Display");
                }
                if (sMethodForHeight == null) {
                    sMethodForHeight = sClass.getMethod("getRealHeight", new Class[0]);
                }
                iIntValue = ((Integer) sMethodForHeight.invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception unused) {
            }
        }
        return iIntValue == 0 ? sHeightPixels : iIntValue;
    }

    public static boolean isPad() {
        float f2 = sDensity;
        if (f2 < 1.5d && f2 > 0.0f) {
            int i2 = sWidthPixels;
            int i3 = sHeightPixels;
            if (i2 < i3) {
                if (i2 > 480 && i3 > 800) {
                    return true;
                }
            } else if (i2 > 800 && i3 > 480) {
                return true;
            }
        }
        return false;
    }

    public static int getStatusBarHeight(Context context) throws IllegalAccessException, Resources.NotFoundException, InstantiationException, ClassNotFoundException, NumberFormatException {
        int dimensionPixelSize = 0;
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            dimensionPixelSize = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
            sTopStatusHeight = dimensionPixelSize;
            return dimensionPixelSize;
        } catch (Exception e2) {
            e2.printStackTrace();
            return dimensionPixelSize;
        }
    }

    public static int getRealWidth() {
        if (Machine.s_IS_SDK_ABOVE_KITKAT) {
            return sRealWidthPixels;
        }
        return sWidthPixels;
    }

    public static int getRealHeight() {
        if (Machine.s_IS_SDK_ABOVE_KITKAT) {
            return sRealHeightPixels;
        }
        return sHeightPixels;
    }

    public static int getNavBarHeight() {
        if (Machine.s_IS_SDK_ABOVE_KITKAT) {
            return sNavBarHeight;
        }
        return 0;
    }

    public static int getNavBarWidth() {
        if (Machine.s_IS_SDK_ABOVE_KITKAT) {
            return sNavBarWidth;
        }
        return 0;
    }

    public static int getNavBarLocation() {
        return sRealWidthPixels > sWidthPixels ? 1 : 2;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float width = bitmap.getWidth() / 2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, width, width, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap resizeImage(Bitmap bitmap, int w, int h2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(w / width, h2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static void setTranslucentStatusBar(Window window) {
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
    }

    public static void setNoTranslucentStatusBar(Window window) {
        Log.i("zou", "DrawUtil setNoTranslucentStatusBar");
        Log.i("zou", "DrawUtil setNoTranslucentStatusBar1111");
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        view.setDrawingCacheEnabled(false);
        return drawingCache;
    }
}
