package com.yucheng.smarthealthpro.utils;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.alibaba.fastjson2.JSONB;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.common.base.Ascii;
import com.jieli.jl_rcsp.util.CHexConver;

/* loaded from: classes5.dex */
public class ConvertUtils {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', ASCIIPropertyListParser.DATA_GSBOOL_BEGIN_TOKEN, 'C', ASCIIPropertyListParser.DATA_GSDATE_BEGIN_TOKEN, 'E', 'F'};

    private ConvertUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        String upperCase = hexString.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) (charToByte(charArray[i3 + 1]) | (charToByte(charArray[i3]) << 4));
        }
        return bArr;
    }

    public static byte charToByte(char c2) {
        return (byte) CHexConver.f4164b.indexOf(c2);
    }

    private static int toDigit(final char ch, final int index) throws Exception {
        int iDigit = Character.digit(ch, 16);
        if (iDigit != -1) {
            return iDigit;
        }
        throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
    }

    private static String bytes2Hex(final byte[] data, final char[] toDigits) {
        char[] cArr = new char[data.length << 1];
        int i2 = 0;
        for (byte b2 : data) {
            int i3 = i2 + 1;
            cArr[i2] = toDigits[(b2 & JSONB.Constants.BC_INT32_NUM_MIN) >>> 4];
            i2 += 2;
            cArr[i3] = toDigits[b2 & 15];
        }
        return new String(cArr);
    }

    public static String bytesToHexString(byte[] b2) {
        if (b2.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b3 : b2) {
            String hexString = Integer.toHexString(b3 & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] intToByte(int res) {
        return new byte[]{(byte) (res & 255), (byte) ((res >> 8) & 255), (byte) ((res >> 16) & 255), (byte) (res >>> 24)};
    }

    public static int byteToInt(byte[] res) {
        return ((res[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | (res[3] & 255) | ((res[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((res[1] << 16) & 16711680);
    }

    public static String saveDecimals(int cnt, double value) {
        if (cnt == 2) {
            return String.format("%.02f", Double.valueOf(value));
        }
        if (cnt == 1) {
            return String.format("%.01f", Double.valueOf(value));
        }
        return String.format("%.0f", Double.valueOf(value));
    }

    public static String nullOfString(String str) {
        return str == null ? "" : str;
    }

    public static byte stringToByte(String str) {
        if (str != null) {
            try {
                return Byte.parseByte(str);
            } catch (Exception unused) {
            }
        }
        return (byte) 0;
    }

    public static boolean stringToBoolean(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("1")) {
            return true;
        }
        if (str.equals("0")) {
            return false;
        }
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public static int stringToInt(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception unused) {
            return 0;
        }
    }

    public static short stringToShort(String str) {
        if (str == null) {
            return (short) 0;
        }
        try {
            return Short.parseShort(str.trim());
        } catch (Exception unused) {
            return (short) 0;
        }
    }

    public static double stringToDouble(String str) {
        if (str == null) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static String intToString(int i2) {
        try {
            return String.valueOf(i2);
        } catch (Exception unused) {
            return "";
        }
    }

    public static long doubleToLong(double d2) {
        try {
            return Long.parseLong(String.valueOf(d2).substring(0, String.valueOf(d2).lastIndexOf(".")));
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static int doubleToInt(double d2) {
        try {
            return Integer.parseInt(String.valueOf(d2).substring(0, String.valueOf(d2).lastIndexOf(".")));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static double longToDouble(long d2) {
        try {
            return Double.parseDouble(String.valueOf(d2));
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static int longToInt(long d2) {
        try {
            return Integer.parseInt(String.valueOf(d2));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static long stringToLong(String str) throws NumberFormatException {
        Long l = new Long(0L);
        try {
            l = Long.valueOf(str);
        } catch (Exception unused) {
        }
        return l.longValue();
    }

    public static String longToString(long li) {
        try {
            return String.valueOf(li);
        } catch (Exception unused) {
            return "";
        }
    }
}
