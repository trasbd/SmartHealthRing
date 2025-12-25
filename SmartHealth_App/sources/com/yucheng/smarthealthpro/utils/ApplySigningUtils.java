package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class ApplySigningUtils {
    public static String getRawSignatureStr(Context context, String packageName) {
        try {
            return getSignValidString(getRawSignature(context, packageName)[0].toByteArray());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Signature[] getRawSignature(Context context, String packageName) throws PackageManager.NameNotFoundException {
        if (packageName != null && packageName.length() != 0) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 64);
                if (packageInfo != null) {
                    return packageInfo.signatures;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static String getSignValidString(byte[] paramArrayOfByte) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(paramArrayOfByte);
        return toHexString(messageDigest.digest());
    }

    private static String toHexString(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(paramArrayOfByte.length * 2);
        for (byte b2 : paramArrayOfByte) {
            String string = Integer.toString(b2 & 255, 16);
            if (string.length() == 1) {
                string = "0" + string;
            }
            sb.append(string);
        }
        return sb.toString();
    }
}
