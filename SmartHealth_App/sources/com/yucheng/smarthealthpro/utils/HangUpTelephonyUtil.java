package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import com.android.internal.telephony.ITelephony;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class HangUpTelephonyUtil {
    public static boolean endCall(Context context) {
        boolean zEndCall = false;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                if (context.checkSelfPermission("android.permission.ANSWER_PHONE_CALLS") != 0) {
                    Logger.d("chong------------没有权限");
                }
                TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
                if (telecomManager != null) {
                    zEndCall = telecomManager.endCall();
                }
            }
        } catch (SecurityException e2) {
            e2.printStackTrace();
        }
        return !zEndCall ? endCalls(context) : zEndCall;
    }

    private static boolean endCalls(Context context) throws NoSuchMethodException, SecurityException {
        ITelephony telephonyService = getTelephonyService(context);
        boolean zEndCall = false;
        if (telephonyService != null) {
            try {
                zEndCall = telephonyService.endCall();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        return !zEndCall ? killCall(context) : zEndCall;
    }

    private static ITelephony getTelephonyService(Context context) throws NoSuchMethodException, SecurityException {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            Method declaredMethod = Class.forName(telephonyManager.getClass().getName()).getDeclaredMethod("getITelephony", new Class[0]);
            declaredMethod.setAccessible(true);
            return (ITelephony) declaredMethod.invoke(telephonyManager, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static boolean killCall(Context context) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        boolean zBooleanValue = false;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Method declaredMethod = Class.forName(telephonyManager.getClass().getName()).getDeclaredMethod("getITelephony", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(telephonyManager, new Object[0]);
            zBooleanValue = ((Boolean) Class.forName(objInvoke.getClass().getName()).getDeclaredMethod("endCall", new Class[0]).invoke(objInvoke, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!zBooleanValue) {
            disconnectCall();
        }
        return zBooleanValue;
    }

    private static void disconnectCall() {
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.yucheng.smarthealthpro.utils.HangUpTelephonyUtil.1
            @Override // java.lang.Runnable
            public void run() throws IOException {
                try {
                    Runtime.getRuntime().exec("service call phone 5 \n");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static void answerCall(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                if (context.checkSelfPermission("android.permission.ANSWER_PHONE_CALLS") != 0) {
                    Logger.d("chong------------没有权限");
                }
                TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
                if (telecomManager != null) {
                    telecomManager.acceptRingingCall();
                    return;
                }
                return;
            }
            answerCall2(context);
        } catch (SecurityException e2) {
            e2.printStackTrace();
        }
    }

    private static void answerCall2(Context context) throws NoSuchMethodException, SecurityException {
        ITelephony telephonyService = getTelephonyService(context);
        if (telephonyService != null) {
            try {
                telephonyService.answerRingingCall();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void callPhone(Context context, String number) throws NoSuchMethodException, SecurityException {
        if (ContextCompat.checkSelfPermission(context, Permission.CALL_PHONE) == 0) {
            try {
                TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
                if (telecomManager != null) {
                    telecomManager.placeCall(Uri.parse("tel:" + number), null);
                    Logger.d("chong---------isCallPhone==打电话代码已执行");
                } else {
                    callPhone2(context, number);
                }
                return;
            } catch (SecurityException e2) {
                e2.printStackTrace();
                callPhone2(context, number);
                return;
            }
        }
        Logger.d("chong--------没有拨打电话的权限");
    }

    public static void callPhone2(Context context, String number) throws NoSuchMethodException, SecurityException {
        if (ContextCompat.checkSelfPermission(context, Permission.CALL_PHONE) == 0) {
            ITelephony telephonyService = getTelephonyService(context);
            try {
                if (telephonyService != null) {
                    telephonyService.call(number);
                    Logger.d("chong---------isCallPhone1==打电话代码已执行");
                } else {
                    callPhone3(context, number);
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
                callPhone3(context, number);
            }
        }
    }

    private static void callPhone3(Context context, String number) {
        try {
            Intent intent = new Intent("android.intent.action.CALL");
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            intent.setData(Uri.parse("tel:" + number));
            context.startActivity(intent);
            Logger.d("chong---------isCallPhone2==打电话代码已执行");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
