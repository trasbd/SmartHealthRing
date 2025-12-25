package com.yucheng.smarthealthpro.framework.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class SharedPreferencesUtils {
    private static final String FILE_NAME = "ycblespinfo";

    public SharedPreferencesUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void put(Context context, String str, Object obj) {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        if (obj != null) {
            if (obj instanceof String) {
                editorEdit.putString(str, (String) obj);
            } else if (obj instanceof Integer) {
                editorEdit.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Boolean) {
                editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Float) {
                editorEdit.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Long) {
                editorEdit.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                editorEdit.putString(str, obj.toString());
            } else {
                editorEdit.putString(str, obj.toString());
            }
        }
        SharedPreferencesCompat.apply(editorEdit);
    }

    public static Object get(Context context, String str, Object obj) {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return obj;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        if (obj instanceof String) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        return obj instanceof Double ? Double.valueOf(Double.parseDouble(sharedPreferences.getString(str, obj.toString()))) : obj;
    }

    public static void remove(Context context, String str) {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        editorEdit.remove(str);
        SharedPreferencesCompat.apply(editorEdit);
    }

    public static void clear(Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        editorEdit.clear();
        SharedPreferencesCompat.apply(editorEdit);
    }

    public static boolean contains(Context context, String str) {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(FILE_NAME, 0).contains(str);
    }

    public static Map<String, ?> getAll(Context context) {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return new HashMap();
        }
        return context.getSharedPreferences(FILE_NAME, 0).getAll();
    }

    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        private SharedPreferencesCompat() {
        }

        private static Method findApplyMethod() {
            try {
                return SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public static void apply(SharedPreferences.Editor editor) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            try {
                Method method = sApplyMethod;
                if (method != null) {
                    method.invoke(editor, new Object[0]);
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            editor.commit();
        }
    }

    private static void paraCheck(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences == null) {
            throw new IllegalArgumentException();
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean putBitmap(Context context, String str, Bitmap bitmap) {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        paraCheck(sharedPreferences, str);
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String str2 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(str, str2);
        return editorEdit.commit();
    }

    public static Bitmap getBitmap(Context context, String str, Bitmap bitmap) {
        Bitmap bitmapDecodeStream;
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return bitmap;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        paraCheck(sharedPreferences, str);
        String string = sharedPreferences.getString(str, "");
        return (TextUtils.isEmpty(string) || (bitmapDecodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(string.getBytes(), 0)))) == null) ? bitmap : bitmapDecodeStream;
    }

    public static boolean putDrawable(Context context, String str, Drawable drawable) {
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        paraCheck(sharedPreferences, str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ((BitmapDrawable) drawable).getBitmap().compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        String str2 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(str, str2);
        return editorEdit.commit();
    }

    public static Drawable getDrawable(Context context, String str, Drawable drawable) {
        Drawable drawableCreateFromStream;
        if (context == null && HealthApplication.getInstance() != null) {
            context = HealthApplication.getInstance().getApplicationContext();
        }
        if (context == null) {
            return drawable;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        paraCheck(sharedPreferences, str);
        String string = sharedPreferences.getString(str, "");
        return (TextUtils.isEmpty(string) || (drawableCreateFromStream = Drawable.createFromStream(new ByteArrayInputStream(Base64.decode(string.getBytes(), 0)), "")) == null) ? drawable : drawableCreateFromStream;
    }
}
