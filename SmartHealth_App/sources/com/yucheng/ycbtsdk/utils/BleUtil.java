package com.yucheng.ycbtsdk.utils;

import com.alibaba.fastjson2.JSONB;
import com.yucheng.ycbtsdk.bean.AdvUnit;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes5.dex */
public final class BleUtil {
    private static final String TAG = "BleUtil";

    public static BleAdvertisedData parseAdertisedData(byte[] bArr) {
        byte b2;
        ArrayList arrayList = new ArrayList();
        String str = null;
        if (bArr == null) {
            return new BleAdvertisedData(arrayList, null);
        }
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.remaining() > 2 && (b2 = byteBufferOrder.get()) != 0) {
            byte b3 = byteBufferOrder.get();
            if (b3 == 2 || b3 == 3) {
                while (b2 >= 2) {
                    arrayList.add(UUID.fromString(String.format("%08x-0000-1000-8000-00805f9b34fb", Short.valueOf(byteBufferOrder.getShort()))));
                    b2 = (byte) (b2 - 2);
                }
            } else if (b3 == 6 || b3 == 7) {
                while (b2 >= 16) {
                    arrayList.add(new UUID(byteBufferOrder.getLong(), byteBufferOrder.getLong()));
                    b2 = (byte) (b2 + JSONB.Constants.BC_INT32_NUM_MIN);
                }
            } else if (b3 != 9) {
                byteBufferOrder.position((byteBufferOrder.position() + b2) - 1);
            } else {
                byte[] bArr2 = new byte[b2 - 1];
                byteBufferOrder.get(bArr2);
                try {
                    str = new String(bArr2, "utf-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return new BleAdvertisedData(arrayList, str);
    }

    public static String parseAdvMac(String str, List<AdvUnit> list) {
        if (list == null || list.isEmpty() || str == null || str.length() < 15) {
            return "";
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            AdvUnit advUnit = list.get(i2);
            if ((advUnit.getType() == 255 || advUnit.getType() == 3) && advUnit.getValue().length >= 6) {
                byte[] value = advUnit.getValue();
                String strBytesToMacAddress = BluetoothUtils.bytesToMacAddress(ByteUtil.getSubArray(value, value.length - 6, 6));
                if (str.substring(0, 15).equals(strBytesToMacAddress.substring(0, 15))) {
                    return strBytesToMacAddress;
                }
            }
        }
        return "";
    }

    public static List<AdvUnit> parseScanRecord(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length != 0) {
            int i2 = 0;
            while (i2 < bArr.length) {
                int i3 = i2 + 1;
                int i4 = bArr[i2] & 255;
                if (i4 == 0) {
                    break;
                }
                int i5 = i2 + 2;
                int i6 = bArr[i3] & 255;
                int i7 = i4 - 1;
                byte[] bArr2 = new byte[i7];
                System.arraycopy(bArr, i5, bArr2, 0, i7);
                i2 = i5 + i7;
                arrayList.add(new AdvUnit(i4, i6, bArr2));
            }
        }
        return arrayList;
    }
}
