package com.yucheng.smarthealthpro.utils;

import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class CodeHints {
    private static Map<DecodeHintType, Object> DECODE_HINTS = new EnumMap(DecodeHintType.class);
    private static Map<EncodeHintType, Object> ENCODE_HINTS = new EnumMap(EncodeHintType.class);

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BarcodeFormat.QR_CODE);
        arrayList.add(BarcodeFormat.CODE_39);
        arrayList.add(BarcodeFormat.CODE_93);
        arrayList.add(BarcodeFormat.CODE_128);
        arrayList.add(BarcodeFormat.DATA_MATRIX);
        DECODE_HINTS.put(DecodeHintType.POSSIBLE_FORMATS, arrayList);
        ENCODE_HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    }

    public static Map<DecodeHintType, Object> getDefaultDecodeHints() {
        return DECODE_HINTS;
    }

    public static Map<DecodeHintType, Object> getCustomDecodeHints(String characterSet) {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        ArrayList arrayList = new ArrayList();
        arrayList.add(BarcodeFormat.QR_CODE);
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) arrayList);
        if (TextUtils.isEmpty(characterSet)) {
            characterSet = "UTF-8";
        }
        enumMap.put((EnumMap) DecodeHintType.CHARACTER_SET, (DecodeHintType) characterSet);
        return enumMap;
    }

    public static Map<EncodeHintType, Object> getDefaultEncodeHints() {
        return ENCODE_HINTS;
    }

    public static Map<EncodeHintType, Object> getCustomEncodeHints(ErrorCorrectionLevel level, Integer version, String characterSet) {
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        if (level != null) {
            enumMap.put((EnumMap) EncodeHintType.ERROR_CORRECTION, (EncodeHintType) level);
        }
        if (version.intValue() >= 1 && version.intValue() <= 40) {
            enumMap.put((EnumMap) EncodeHintType.QR_VERSION, (EncodeHintType) version);
        }
        if (!TextUtils.isEmpty(characterSet)) {
            enumMap.put((EnumMap) EncodeHintType.CHARACTER_SET, (EncodeHintType) characterSet);
        }
        return enumMap;
    }
}
