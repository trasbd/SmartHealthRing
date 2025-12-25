package com.yucheng.ycbtsdk.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.alibaba.fastjson2.JSONB;
import com.google.common.base.Ascii;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public class SaveBitmap888Util {
    private static byte[] addBMPImageHeader(int i2) {
        return new byte[]{66, JSONB.Constants.BC_STR_ASCII_FIX_4, (byte) i2, (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24), 0, 0, 0, 0, 54, 0, 0, 0};
    }

    private static byte[] addBMPImageInfosHeader(int i2, int i3) {
        return new byte[]{40, 0, 0, 0, (byte) i2, (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24), (byte) i3, (byte) (i3 >> 8), (byte) (i3 >> 16), (byte) (i3 >> 24), 1, 0, Ascii.CAN, 0, 0, 0, 0, 0, 0, 0, 0, 0, -32, 1, 0, 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private static byte[] addBMP_RGB_888(int[] iArr, int i2, int i3) {
        int i4 = (4 - (i2 % 4)) % 4;
        byte[] bArr = new byte[((i2 * 3) + i4) * i3];
        int length = iArr.length - 1;
        int i5 = 0;
        while (length >= i2) {
            int i6 = length - i2;
            for (int i7 = i6 + 1; i7 <= length; i7++) {
                int i8 = iArr[i7];
                bArr[i5] = (byte) i8;
                bArr[i5 + 1] = (byte) (i8 >> 8);
                bArr[i5 + 2] = (byte) (i8 >> 16);
                i5 += 3;
            }
            i5 += i4;
            length = i6;
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean saveBitmap888(android.graphics.Bitmap r12, java.lang.String r13) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r12 == 0) goto L80
            int r9 = r12.getWidth()
            int r10 = r12.getHeight()
            int r1 = r9 * r10
            int[] r11 = new int[r1]
            r5 = 0
            r6 = 0
            r3 = 0
            r1 = r12
            r2 = r11
            r4 = r9
            r7 = r9
            r8 = r10
            r1.getPixels(r2, r3, r4, r5, r6, r7, r8)
            byte[] r12 = addBMP_RGB_888(r11, r9, r10)
            int r1 = r12.length
            byte[] r1 = addBMPImageHeader(r1)
            byte[] r2 = addBMPImageInfosHeader(r9, r10)
            int r3 = r12.length
            r4 = 54
            int r3 = r3 + r4
            byte[] r3 = new byte[r3]
            int r5 = r1.length
            java.lang.System.arraycopy(r1, r0, r3, r0, r5)
            int r1 = r2.length
            r5 = 14
            java.lang.System.arraycopy(r2, r0, r3, r5, r1)
            int r1 = r12.length
            java.lang.System.arraycopy(r12, r0, r3, r4, r1)
            r12 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            r1.<init>(r13)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            if (r2 == 0) goto L4a
            r1.delete()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
        L4a:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            r1.<init>(r13)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            r1.write(r3)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r1.flush()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r0 = 1
            r1.close()     // Catch: java.lang.Exception -> L6c
            goto L80
        L5a:
            r12 = move-exception
            goto L75
        L5c:
            r12 = move-exception
            goto L63
        L5e:
            r13 = move-exception
            goto L73
        L60:
            r13 = move-exception
            r1 = r12
            r12 = r13
        L63:
            r12.printStackTrace()     // Catch: java.lang.Throwable -> L71
            if (r1 == 0) goto L80
            r1.close()     // Catch: java.lang.Exception -> L6c
            goto L80
        L6c:
            r12 = move-exception
            r12.printStackTrace()
            goto L80
        L71:
            r13 = move-exception
            r12 = r1
        L73:
            r1 = r12
            r12 = r13
        L75:
            if (r1 == 0) goto L7f
            r1.close()     // Catch: java.lang.Exception -> L7b
            goto L7f
        L7b:
            r13 = move-exception
            r13.printStackTrace()
        L7f:
            throw r12
        L80:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.utils.SaveBitmap888Util.saveBitmap888(android.graphics.Bitmap, java.lang.String):boolean");
    }

    public static boolean saveBitmap8882(Bitmap bitmap, String str) throws IOException {
        if (bitmap == null) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width * 3;
        int i3 = ((width % 4) + i2) * height;
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str);
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

    private static void writeDword(FileOutputStream fileOutputStream, long j2) throws IOException {
        fileOutputStream.write(new byte[]{(byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255)});
    }

    private static void writeLong(FileOutputStream fileOutputStream, long j2) throws IOException {
        fileOutputStream.write(new byte[]{(byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255)});
    }

    private static void writeWord(FileOutputStream fileOutputStream, int i2) throws IOException {
        fileOutputStream.write(new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255)});
    }
}
