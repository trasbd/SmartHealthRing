package com.yucheng.smarthealthpro.care.zxing.decoding;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.journeyapps.barcodescanner.MixedDecoder;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.care.zxing.activity.CaptureActivity;
import com.yucheng.smarthealthpro.care.zxing.camera.CameraManager;
import com.yucheng.smarthealthpro.care.zxing.camera.PlanarYUVLuminanceSource;
import java.util.Map;

/* loaded from: classes4.dex */
final class DecodeHandler extends Handler {
    private static final String TAG = "DecodeHandler";
    private final CaptureActivity activity;
    private QRCodeReader mQRCodeReader;
    private MultiFormatReader multiFormatReader;

    DecodeHandler(CaptureActivity activity, Map<DecodeHintType, Object> hints) {
        this.multiFormatReader = null;
        this.activity = activity;
        if (hints != null) {
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            this.multiFormatReader = multiFormatReader;
            multiFormatReader.setHints(hints);
            return;
        }
        this.mQRCodeReader = new QRCodeReader();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == R.id.decode) {
            decode((byte[]) message.obj, message.arg1, message.arg2);
        } else if (i2 == R.id.quit) {
            Looper.myLooper().quit();
        }
    }

    private void decode(byte[] data, int width, int height) {
        Result resultDecode;
        Log.d("ltf", "decode width=" + width + " height=" + height);
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte[] bArr = new byte[data.length];
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                bArr[(((i3 * height) + height) - i2) - 1] = data[(i2 * width) + i3];
            }
        }
        PlanarYUVLuminanceSource planarYUVLuminanceSourceBuildLuminanceSource = CameraManager.get().buildLuminanceSource(bArr, height, width);
        Result resultDecodeWithState = null;
        if (planarYUVLuminanceSourceBuildLuminanceSource != null) {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSourceBuildLuminanceSource));
            try {
                QRCodeReader qRCodeReader = this.mQRCodeReader;
                if (qRCodeReader != null) {
                    resultDecodeWithState = qRCodeReader.decode(binaryBitmap);
                } else {
                    MultiFormatReader multiFormatReader = this.multiFormatReader;
                    if (multiFormatReader != null) {
                        resultDecodeWithState = multiFormatReader.decodeWithState(binaryBitmap);
                        Logger.d("ltf multiFormatReader " + resultDecodeWithState.getText());
                    } else {
                        Logger.d("chong------没有解析工具");
                    }
                }
            } catch (Exception e2) {
                Logger.d("ltf " + e2.getMessage());
                e2.printStackTrace();
            }
            if (resultDecodeWithState == null) {
                try {
                    if (this.mQRCodeReader != null) {
                        resultDecode = new MixedDecoder(this.mQRCodeReader).decode(planarYUVLuminanceSourceBuildLuminanceSource);
                    } else if (this.multiFormatReader != null) {
                        resultDecode = new MixedDecoder(this.multiFormatReader).decode(planarYUVLuminanceSourceBuildLuminanceSource);
                    }
                    resultDecodeWithState = resultDecode;
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        QRCodeReader qRCodeReader2 = this.mQRCodeReader;
        if (qRCodeReader2 != null) {
            qRCodeReader2.reset();
        }
        MultiFormatReader multiFormatReader2 = this.multiFormatReader;
        if (multiFormatReader2 != null) {
            multiFormatReader2.reset();
        }
        if (resultDecodeWithState != null) {
            Log.d(TAG, "Found barcode (" + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms):\n" + resultDecodeWithState.toString());
            Message messageObtain = Message.obtain(this.activity.getHandler(), R.id.decode_succeeded, resultDecodeWithState);
            if (messageObtain != null) {
                messageObtain.sendToTarget();
                return;
            }
            return;
        }
        Message messageObtain2 = Message.obtain(this.activity.getHandler(), R.id.decode_failed);
        if (this.activity.isFinishing() || this.activity.getHandler() == null || messageObtain2 == null) {
            return;
        }
        messageObtain2.sendToTarget();
    }
}
