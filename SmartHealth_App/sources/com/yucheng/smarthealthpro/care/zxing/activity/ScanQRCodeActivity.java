package com.yucheng.smarthealthpro.care.zxing.activity;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.yucheng.smarthealthpro.R;
import java.util.ArrayList;
import java.util.EnumMap;

/* loaded from: classes4.dex */
public class ScanQRCodeActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    private static final String TAG = "ScanQRCodeActivity";
    private ZXingView mZXingView;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        ZXingView zXingView = (ZXingView) findViewById(R.id.zxingview);
        this.mZXingView = zXingView;
        zXingView.setDelegate(this);
        BGAQRCodeUtil.setDebug(true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mZXingView.startCamera();
        startScan();
    }

    private void startScan() {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        ArrayList arrayList = new ArrayList();
        arrayList.add(BarcodeFormat.QR_CODE);
        arrayList.add(BarcodeFormat.UPC_A);
        arrayList.add(BarcodeFormat.EAN_13);
        arrayList.add(BarcodeFormat.CODE_39);
        arrayList.add(BarcodeFormat.MAXICODE);
        arrayList.add(BarcodeFormat.CODE_93);
        arrayList.add(BarcodeFormat.CODE_128);
        arrayList.add(BarcodeFormat.DATA_MATRIX);
        arrayList.add(BarcodeFormat.AZTEC);
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) arrayList);
        enumMap.put((EnumMap) DecodeHintType.TRY_HARDER, (DecodeHintType) Boolean.TRUE);
        enumMap.put((EnumMap) DecodeHintType.CHARACTER_SET, (DecodeHintType) "utf-8");
        this.mZXingView.setType(BarcodeType.CUSTOM, enumMap);
        this.mZXingView.startSpotAndShowRect();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        this.mZXingView.stopCamera();
        super.onStop();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mZXingView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        ((Vibrator) getSystemService("vibrator")).vibrate(200L);
    }

    @Override // cn.bingoogolapple.qrcode.core.QRCodeView.Delegate
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        setTitle("扫描结果为：" + result);
        vibrate();
        this.mZXingView.startSpot();
    }

    @Override // cn.bingoogolapple.qrcode.core.QRCodeView.Delegate
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        String tipText = this.mZXingView.getScanBoxView().getTipText();
        if (isDark) {
            if (tipText.contains("\n环境过暗，请打开闪光灯")) {
                return;
            }
            this.mZXingView.getScanBoxView().setTipText(tipText + "\n环境过暗，请打开闪光灯");
        } else if (tipText.contains("\n环境过暗，请打开闪光灯")) {
            this.mZXingView.getScanBoxView().setTipText(tipText.substring(0, tipText.indexOf("\n环境过暗，请打开闪光灯")));
        }
    }

    @Override // cn.bingoogolapple.qrcode.core.QRCodeView.Delegate
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
}
