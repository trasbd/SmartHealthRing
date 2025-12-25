package com.yucheng.smarthealthpro.me.setting.dial.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.alibaba.fastjson2.JSONB;
import com.google.common.base.Ascii;
import com.orhanobut.logger.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public class SaveBitmap888Util {
    public static boolean saveBitmap888(Bitmap bitmap, String path) throws Throwable {
        if (bitmap == null) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArrAddBMP_RGB_888 = addBMP_RGB_888(iArr, width, height);
        byte[] bArrAddBMPImageHeader = addBMPImageHeader(bArrAddBMP_RGB_888.length);
        byte[] bArrAddBMPImageInfosHeader = addBMPImageInfosHeader(width, height);
        byte[] bArr = new byte[bArrAddBMP_RGB_888.length + 54];
        System.arraycopy(bArrAddBMPImageHeader, 0, bArr, 0, bArrAddBMPImageHeader.length);
        System.arraycopy(bArrAddBMPImageInfosHeader, 0, bArr, 14, bArrAddBMPImageInfosHeader.length);
        System.arraycopy(bArrAddBMP_RGB_888, 0, bArr, 54, bArrAddBMP_RGB_888.length);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(path);
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream2.flush();
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return true;
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream == null) {
                        return false;
                    }
                    try {
                        fileOutputStream.close();
                        return false;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return false;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static byte[] addBMPImageHeader(int size) {
        return new byte[]{66, JSONB.Constants.BC_STR_ASCII_FIX_4, (byte) size, (byte) (size >> 8), (byte) (size >> 16), (byte) (size >> 24), 0, 0, 0, 0, 54, 0, 0, 0};
    }

    private static byte[] addBMPImageInfosHeader(int w, int h2) {
        return new byte[]{40, 0, 0, 0, (byte) w, (byte) (w >> 8), (byte) (w >> 16), (byte) (w >> 24), (byte) h2, (byte) (h2 >> 8), (byte) (h2 >> 16), (byte) (h2 >> 24), 1, 0, Ascii.CAN, 0, 0, 0, 0, 0, 0, 0, 0, 0, -32, 1, 0, 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private static byte[] addBMP_RGB_888(int[] b2, int w, int h2) {
        int length = b2.length;
        Logger.d(Integer.valueOf(b2.length));
        int i2 = (4 - (w % 4)) % 4;
        byte[] bArr = new byte[((w * 3) + i2) * h2];
        int i3 = length - 1;
        int i4 = 0;
        while (i3 >= w) {
            int i5 = i3 - w;
            for (int i6 = i5 + 1; i6 <= i3; i6++) {
                int i7 = b2[i6];
                bArr[i4] = (byte) i7;
                bArr[i4 + 1] = (byte) (i7 >> 8);
                bArr[i4 + 2] = (byte) (i7 >> 16);
                i4 += 3;
            }
            i4 += i2;
            i3 = i5;
        }
        return bArr;
    }

    public static boolean saveBitmap8882(Bitmap bitmap, String path) throws IOException {
        if (bitmap == null) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width * 3;
        int i3 = ((width % 4) + i2) * height;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            writeWord(fileOutputStream, 19778);
            writeDword(fileOutputStream, i3 + 54);
            writeWord(fileOutputStream, 0);
            writeWord(fileOutputStream, 0);
            writeDword(fileOutputStream, 54L);
            writeDword(fileOutputStream, 40L);
            writeLong(fileOutputStream, width);
            writeLong(fileOutputStream, height);
            writeWord(fileOutputStream, 1);
            writeWord(fileOutputStream, 24);
            writeDword(fileOutputStream, 0L);
            writeDword(fileOutputStream, 0L);
            writeLong(fileOutputStream, 0L);
            writeLong(fileOutputStream, 0L);
            writeDword(fileOutputStream, 0L);
            writeDword(fileOutputStream, 0L);
            byte[] bArr = new byte[i3];
            int i4 = i2 + (width % 4);
            int i5 = height - 1;
            int i6 = 0;
            while (i6 < height) {
                int i7 = 0;
                int i8 = 0;
                while (i7 < width) {
                    int pixel = bitmap.getPixel(i7, i6);
                    int i9 = (i5 * i4) + i8;
                    bArr[i9] = (byte) Color.blue(pixel);
                    bArr[i9 + 1] = (byte) Color.green(pixel);
                    bArr[i9 + 2] = (byte) Color.red(pixel);
                    i7++;
                    i8 += 3;
                }
                i6++;
                i5--;
            }
            fileOutputStream.write(bArr);
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

    private static void writeWord(FileOutputStream stream, int value) throws IOException {
        stream.write(new byte[]{(byte) (value & 255), (byte) ((value >> 8) & 255)});
    }

    private static void writeDword(FileOutputStream stream, long value) throws IOException {
        stream.write(new byte[]{(byte) (value & 255), (byte) ((value >> 8) & 255), (byte) ((value >> 16) & 255), (byte) ((value >> 24) & 255)});
    }

    private static void writeLong(FileOutputStream stream, long value) throws IOException {
        stream.write(new byte[]{(byte) (value & 255), (byte) ((value >> 8) & 255), (byte) ((value >> 16) & 255), (byte) ((value >> 24) & 255)});
    }
}
