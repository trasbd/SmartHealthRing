package com.yucheng.smarthealthpro.utils;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class AppScreenMgr {
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getStatusHeight(Activity activity) {
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(activity.getWindow().getDecorView());
        if (rootWindowInsets != null) {
            return rootWindowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
        }
        return getStatusBarHeightFromResources(activity);
    }

    public static int getStatusBarHeightFromResources(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i2 = rect.top;
        return i2 == 0 ? activity.getResources().getDimensionPixelSize(activity.getResources().getIdentifier("status_bar_height", "dimen", "android")) : i2;
    }

    public static int getRealScreenHeight(Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int getNavigationAreaHeight(Context context) {
        int realScreenHeight = getRealScreenHeight(context) - getScreenHeight(context);
        if (Build.VERSION.SDK_INT >= 35) {
            realScreenHeight = ViewCompat.getRootWindowInsets(((Activity) context).getWindow().getDecorView()).getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
        }
        MLog.INSTANCE.d("navigationHeight: " + realScreenHeight);
        return realScreenHeight;
    }

    public static int getNavigationBarrH(Context c2) {
        Resources resources = c2.getResources();
        return resources.getDimensionPixelOffset(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    private AppScreenMgr() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        getScreenWidth(activity);
        getScreenHeight(activity);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawingCache, 0, 0, decorView.getWidth(), decorView.getHeight());
        decorView.destroyDrawingCache();
        return bitmapCreateBitmap;
    }

    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawingCache, 0, rect.top, getScreenWidth(activity), getScreenHeight(activity));
        decorView.destroyDrawingCache();
        return bitmapCreateBitmap;
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

    public static int getTitleBarHeight(Activity context) {
        return context.getWindow().findViewById(R.id.content).getTop() - getStatusBarHeight(context);
    }

    public static int getStatusBarHeight(Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NumberFormatException {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private static Bitmap takeScreenShot(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i2 = rect.top;
        Log.i("TAG", "" + i2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawingCache, 0, i2, activity.getWindowManager().getDefaultDisplay().getWidth(), activity.getWindowManager().getDefaultDisplay().getHeight() - i2);
        decorView.destroyDrawingCache();
        return bitmapCreateBitmap;
    }

    private static boolean savePic(Bitmap b2, String strFileName) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(strFileName);
            b2.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return false;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    private static Bitmap captureWebView(WebView webView) {
        Picture pictureCapturePicture = webView.capturePicture();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(pictureCapturePicture.getWidth(), pictureCapturePicture.getHeight(), Bitmap.Config.ARGB_8888);
        pictureCapturePicture.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    private static String getDate(long time, String format) {
        return new SimpleDateFormat(format).format(new Date(time));
    }

    private static Boolean isExistsSD() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    private static String getFileName(Context context) {
        String str = getDate(System.currentTimeMillis(), "yyyyMMddHHmmss") + ".png";
        if (isExistsSD().booleanValue()) {
            return context.getExternalCacheDir() + File.separator + str;
        }
        return context.getFilesDir() + str;
    }

    public static String shoot(Activity a2) {
        String fileName = getFileName(a2);
        return savePic(takeScreenShot(a2), fileName) ? fileName : "";
    }

    public static String shootWebView(Context context, WebView webView) {
        String fileName = getFileName(context);
        return savePic(captureWebView(webView), fileName) ? fileName : "";
    }
}
