package com.yucheng.smarthealthpro.home.util;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class Md5Util {
    public static String get(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        try {
            return toHexString(MessageDigest.getInstance("MD5").digest(text.getBytes()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String get(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        try {
            byte[] bArr = new byte[8192];
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            while (true) {
                int i2 = inputStream.read(bArr);
                if (i2 != -1) {
                    messageDigest.update(bArr, 0, i2);
                } else {
                    inputStream.close();
                    return toHexString(messageDigest.digest());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return null;
        }
    }

    public static String get(File file) {
        try {
            return get(new FileInputStream(file));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String toHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : digest) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
