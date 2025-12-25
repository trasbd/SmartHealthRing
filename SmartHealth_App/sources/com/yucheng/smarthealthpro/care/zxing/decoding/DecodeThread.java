package com.yucheng.smarthealthpro.care.zxing.decoding;

import android.os.Handler;
import android.os.Looper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.yucheng.smarthealthpro.care.zxing.activity.CaptureActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes4.dex */
public final class DecodeThread extends Thread {
    private final CaptureActivity activity;
    private Handler handler;
    private final CountDownLatch handlerInitLatch = new CountDownLatch(1);

    DecodeThread(CaptureActivity activity) {
        this.activity = activity;
    }

    Handler getHandler() throws InterruptedException {
        try {
            this.handlerInitLatch.await();
        } catch (InterruptedException unused) {
        }
        return this.handler;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.handler = new DecodeHandler(this.activity, getHints());
        this.handlerInitLatch.countDown();
        Looper.loop();
    }

    public static Map<DecodeHintType, Object> getHints() {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet enumSetOf = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED, BarcodeFormat.UPC_EAN_EXTENSION);
        EnumSet enumSetOf2 = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
        EnumSet enumSetCopyOf = EnumSet.copyOf((Collection) enumSetOf);
        enumSetCopyOf.addAll(enumSetOf2);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(EnumSet.of(BarcodeFormat.AZTEC));
        arrayList.addAll(EnumSet.of(BarcodeFormat.PDF_417));
        arrayList.addAll(enumSetCopyOf);
        arrayList.addAll(EnumSet.of(BarcodeFormat.QR_CODE));
        arrayList.addAll(EnumSet.of(BarcodeFormat.DATA_MATRIX));
        arrayList.addAll(EnumSet.of(BarcodeFormat.MAXICODE));
        EnumSet.allOf(BarcodeFormat.class);
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) arrayList);
        enumMap.put((EnumMap) DecodeHintType.TRY_HARDER, (DecodeHintType) true);
        return enumMap;
    }
}
