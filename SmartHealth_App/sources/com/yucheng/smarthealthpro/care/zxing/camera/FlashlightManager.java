package com.yucheng.smarthealthpro.care.zxing.camera;

import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
final class FlashlightManager {
    private static final String TAG = "FlashlightManager";
    private static final Object iHardwareService;
    private static final Method setFlashEnabledMethod;

    static {
        Object hardwareService = getHardwareService();
        iHardwareService = hardwareService;
        setFlashEnabledMethod = getSetFlashEnabledMethod(hardwareService);
        if (hardwareService == null) {
            Log.v("FlashlightManager", "This device does supports control of a flashlight");
        } else {
            Log.v("FlashlightManager", "This device does not support control of a flashlight");
        }
    }

    private FlashlightManager() {
    }

    static void enableFlashlight() {
        setFlashlight(false);
    }

    static void disableFlashlight() {
        setFlashlight(false);
    }

    private static Object getHardwareService() {
        Method methodMaybeGetMethod;
        Object objInvoke;
        Class<?> clsMaybeForName;
        Method methodMaybeGetMethod2;
        Class<?> clsMaybeForName2 = maybeForName("android.os.ServiceManager");
        if (clsMaybeForName2 == null || (methodMaybeGetMethod = maybeGetMethod(clsMaybeForName2, "getService", String.class)) == null || (objInvoke = invoke(methodMaybeGetMethod, null, "hardware")) == null || (clsMaybeForName = maybeForName("android.os.IHardwareService$Stub")) == null || (methodMaybeGetMethod2 = maybeGetMethod(clsMaybeForName, "asInterface", IBinder.class)) == null) {
            return null;
        }
        return invoke(methodMaybeGetMethod2, null, objInvoke);
    }

    private static Method getSetFlashEnabledMethod(Object iHardwareService2) {
        if (iHardwareService2 == null) {
            return null;
        }
        return maybeGetMethod(iHardwareService2.getClass(), "setFlashlightEnabled", Boolean.TYPE);
    }

    private static Class<?> maybeForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (RuntimeException e2) {
            Log.w(TAG, "Unexpected error while finding class " + name, e2);
            return null;
        }
    }

    private static Method maybeGetMethod(Class<?> clazz, String name, Class<?>... argClasses) {
        try {
            return clazz.getMethod(name, argClasses);
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (RuntimeException e2) {
            Log.w(TAG, "Unexpected error while finding method " + name, e2);
            return null;
        }
    }

    private static Object invoke(Method method, Object instance, Object... args) {
        try {
            return method.invoke(instance, args);
        } catch (IllegalAccessException e2) {
            Log.w(TAG, "Unexpected error while invoking " + method, e2);
            return null;
        } catch (RuntimeException e3) {
            Log.w(TAG, "Unexpected error while invoking " + method, e3);
            return null;
        } catch (InvocationTargetException e4) {
            Log.w(TAG, "Unexpected error while invoking " + method, e4.getCause());
            return null;
        }
    }

    private static void setFlashlight(boolean active) {
        Object obj = iHardwareService;
        if (obj != null) {
            invoke(setFlashEnabledMethod, obj, Boolean.valueOf(active));
        }
    }
}
