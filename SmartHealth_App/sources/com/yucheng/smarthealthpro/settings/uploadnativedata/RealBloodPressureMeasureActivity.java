package com.yucheng.smarthealthpro.settings.uploadnativedata;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityBpMeasureBinding;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.util.UUIDUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.bean.ToAppDataResponse;
import com.yucheng.smarthealthpro.me.setting.SettingsDataType;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import com.yucheng.smarthealthpro.utils.EventBusSyncData;
import com.yucheng.smarthealthpro.utils.SoundPoolDiUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.apache.commons.lang3.BooleanUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class RealBloodPressureMeasureActivity extends BaseVbActivity<ActivityBpMeasureBinding> implements Observer {
    private int dbp;
    private int heart;
    private LottieAnimationView mLottieAnimationView;
    private int sbp;
    private TextView tvLottieData;
    private TextView tvStartButton;
    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;
    private boolean isStarting = false;
    private List<Integer> lists = new ArrayList();
    final int StartCount = 10;
    private int startNumber = 10;
    private int number = 10;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i2 = msg.what;
            if (i2 == 1) {
                if (RealBloodPressureMeasureActivity.this.isStarting) {
                    if (RealBloodPressureMeasureActivity.this.startNumber > 0) {
                        RealBloodPressureMeasureActivity.this.tvLottieData.setText(RealBloodPressureMeasureActivity.this.startNumber + "");
                        RealBloodPressureMeasureActivity.this.startNumber--;
                        RealBloodPressureMeasureActivity.this.handler.sendEmptyMessageDelayed(1, 1000L);
                        return false;
                    }
                    RealBloodPressureMeasureActivity.this.tvLottieData.setText("--/--");
                    RealBloodPressureMeasureActivity.this.startNumber = 10;
                    RealBloodPressureMeasureActivity.this.mLottieAnimationView.playAnimation();
                    return false;
                }
                RealBloodPressureMeasureActivity.this.tvLottieData.setText("--/--");
                RealBloodPressureMeasureActivity.this.startNumber = 10;
                RealBloodPressureMeasureActivity.this.mLottieAnimationView.cancelAnimation();
                return false;
            }
            if (i2 != 2) {
                return false;
            }
            if (RealBloodPressureMeasureActivity.this.number > 0) {
                RealBloodPressureMeasureActivity.this.tvStartButton.setText(RealBloodPressureMeasureActivity.this.getString(R.string.include_tv_start_button_again) + "(" + RealBloodPressureMeasureActivity.this.number + ")");
                RealBloodPressureMeasureActivity.this.number--;
                RealBloodPressureMeasureActivity.this.handler.sendEmptyMessageDelayed(2, 1000L);
                return false;
            }
            RealBloodPressureMeasureActivity.this.tvStartButton.setText(RealBloodPressureMeasureActivity.this.getString(R.string.include_tv_start_button_again));
            RealBloodPressureMeasureActivity.this.tvStartButton.setEnabled(true);
            RealBloodPressureMeasureActivity.this.number = 10;
            try {
                if (RealBloodPressureMeasureActivity.this.wakeLock == null || !RealBloodPressureMeasureActivity.this.wakeLock.isHeld()) {
                    return false;
                }
                RealBloodPressureMeasureActivity.this.wakeLock.release();
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
    });
    private int times = 120;
    private boolean isTimes = true;
    Thread thread = new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.2
        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (RealBloodPressureMeasureActivity.this.isTimes) {
                if (RealBloodPressureMeasureActivity.this.times > 0) {
                    RealBloodPressureMeasureActivity realBloodPressureMeasureActivity = RealBloodPressureMeasureActivity.this;
                    realBloodPressureMeasureActivity.times--;
                } else {
                    RealBloodPressureMeasureActivity.this.times = 120;
                    RealBloodPressureMeasureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (RealBloodPressureMeasureActivity.this.tvStartButton != null) {
                                RealBloodPressureMeasureActivity.this.tvStartButton.setEnabled(true);
                            }
                            RealBloodPressureMeasureActivity.this.isStarting = false;
                            if (RealBloodPressureMeasureActivity.this.mLottieAnimationView != null) {
                                RealBloodPressureMeasureActivity.this.mLottieAnimationView.cancelAnimation();
                            }
                            RealBloodPressureMeasureActivity.this.upLoadDatas(200, 200);
                        }
                    });
                }
                try {
                    Thread.sleep(1000L);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    });

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        PowerManager powerManager = (PowerManager) getSystemService("power");
        this.powerManager = powerManager;
        this.wakeLock = powerManager.newWakeLock(26, "My Lock");
        initView();
        initData();
    }

    private void initView() {
        changeTitle(getString(R.string.home_blood_pressure_measure_title));
        showBack();
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).navigationBarDarkIcon(true, 0.2f).init();
        this.tvLottieData = (TextView) findViewById(R.id.tv_lottie_data);
        this.mLottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie);
        this.tvStartButton = (TextView) findViewById(R.id.tv_start_button);
        this.mLottieAnimationView.setAnimation(R.raw.bp);
        this.mLottieAnimationView.setRepeatCount(300);
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object o) throws Resources.NotFoundException {
        Map map = (Map) o;
        if (Integer.parseInt(map.get("key").toString()) == 1) {
            byte[] bArr = (byte[]) map.get("smsg");
            System.out.println("chong-------data==" + Arrays.toString(bArr));
            byte b2 = bArr[0];
            if (b2 == 4 && bArr[1] == 16) {
                this.times = 120;
                this.isStarting = false;
                this.mLottieAnimationView.cancelAnimation();
                upLoadDatas(bArr[5] & 255, bArr[6] & 255);
                byte b3 = bArr[4];
                if (b3 == 0) {
                    this.number = 10;
                    ToastUtil.getInstance(getApplicationContext()).toast(R.string.measure_success);
                    TextView textView = this.tvLottieData;
                    if (textView != null) {
                        textView.setText((bArr[5] & 255) + "/" + (bArr[6] & 255));
                    }
                    syncData();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                if (b3 == 1) {
                    this.tvStartButton.setEnabled(true);
                    ToastUtil.getInstance(getApplicationContext()).toast(R.string.measure_failed);
                    try {
                        PowerManager.WakeLock wakeLock = this.wakeLock;
                        if (wakeLock == null || !wakeLock.isHeld()) {
                            return;
                        }
                        this.wakeLock.release();
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                return;
            }
            if (b2 == 6 && bArr[1] == 14) {
                for (int i2 = 4; i2 < bArr.length - 2; i2 += 2) {
                    this.lists.add(Integer.valueOf((bArr[i2] & 255) + ((bArr[i2 + 1] & 255) << 8)));
                }
                this.times = 10;
                SoundPoolDiUtil.getInstance(this).play(1);
                return;
            }
            if (b2 == 3 && bArr[1] == 46) {
                this.tvStartButton.setEnabled(false);
                if (bArr[5] == 0) {
                    if (bArr[4] == 1) {
                        this.times = 120;
                        this.handler.removeMessages(1);
                        this.handler.sendEmptyMessage(1);
                        this.isStarting = true;
                        try {
                            if (this.wakeLock == null) {
                                this.wakeLock = this.powerManager.newWakeLock(26, "My Lock");
                            }
                            if (this.wakeLock.isHeld()) {
                                return;
                            }
                            this.wakeLock.acquire();
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                    this.isStarting = false;
                    this.mLottieAnimationView.cancelAnimation();
                    try {
                        PowerManager.WakeLock wakeLock2 = this.wakeLock;
                        if (wakeLock2 == null || !wakeLock2.isHeld()) {
                            return;
                        }
                        this.wakeLock.release();
                        return;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                this.tvStartButton.setEnabled(true);
                if (bArr[4] == 1) {
                    this.isStarting = false;
                    ToastUtil.getInstance(getApplicationContext()).toast(R.string.start_failed);
                }
            }
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        SubObserver.getInstance().addObs(this);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        SubObserver.getInstance().delObs(this);
    }

    private void initData() {
        this.bar.setRightText(getString(R.string.health_reminder_of_long_sitting_reminder_time_end_title), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.3
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (RealBloodPressureMeasureActivity.this.isStarting) {
                    RealBloodPressureMeasureActivity.this.showMyDialog(1);
                } else {
                    RealBloodPressureMeasureActivity.this.startActivity(new Intent(RealBloodPressureMeasureActivity.this, (Class<?>) MainActivity.class));
                }
            }
        });
        this.dbp = Tools.readInt("measure_dbp", HealthApplication.getInstance(), 90);
        this.sbp = Tools.readInt("measure_sbp", HealthApplication.getInstance(), SettingsDataType.MORE_SETTINGS);
        this.tvStartButton.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) throws Resources.NotFoundException {
                if (YCBTClient.connectState() == 10) {
                    RealBloodPressureMeasureActivity.this.tvStartButton.setEnabled(false);
                    if (RealBloodPressureMeasureActivity.this.isStarting) {
                        RealBloodPressureMeasureActivity.this.playStopMeasure(0);
                        return;
                    } else {
                        RealBloodPressureMeasureActivity.this.playStopMeasure(1);
                        return;
                    }
                }
                ToastUtil.getInstance(RealBloodPressureMeasureActivity.this.getApplicationContext()).toast(R.string.please_connect_the_device);
            }
        });
    }

    private void syncData() {
        DataSyncUtils.INSTANCE.getInstance(getApplicationContext()).getWatchesData(Constants.DATATYPE.Health_HistoryBlood);
        showProgressDialog(R.string.ecg_sync_data, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playStopMeasure(final int type) {
        byte[] bArr = {3, 46, (byte) type, (byte) this.sbp, (byte) this.dbp, (byte) this.heart, (byte) Tools.readCm(this), (byte) Tools.readKg(this), (byte) Tools.readAge(this), (byte) Tools.readSex(this)};
        this.lists.clear();
        for (int i2 = 0; i2 < 10; i2++) {
            this.lists.add(Integer.valueOf(bArr[i2] & 255));
        }
        YCBTClient.appStartBloodMeasurement(type, this.sbp, this.dbp, this.heart, ((Integer) SharedPreferencesUtils.get(this.context, "height", Integer.valueOf(Opcodes.TABLESWITCH))).intValue(), ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.WEIGHT, 65)).intValue(), YearToDayListUtils.getAge((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20))), ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue(), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.5
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i3, float v, HashMap hashMap) {
                if (i3 == 0) {
                    if (type == 1) {
                        RealBloodPressureMeasureActivity.this.tvStartButton.setEnabled(false);
                        RealBloodPressureMeasureActivity.this.handler.removeMessages(1);
                        RealBloodPressureMeasureActivity.this.handler.sendEmptyMessage(1);
                        RealBloodPressureMeasureActivity.this.isStarting = true;
                        try {
                            if (RealBloodPressureMeasureActivity.this.wakeLock == null) {
                                RealBloodPressureMeasureActivity realBloodPressureMeasureActivity = RealBloodPressureMeasureActivity.this;
                                realBloodPressureMeasureActivity.wakeLock = realBloodPressureMeasureActivity.powerManager.newWakeLock(26, "My Lock");
                            }
                            if (!RealBloodPressureMeasureActivity.this.wakeLock.isHeld()) {
                                RealBloodPressureMeasureActivity.this.wakeLock.acquire();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        RealBloodPressureMeasureActivity.this.tvStartButton.setText(RealBloodPressureMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_end_title));
                        return;
                    }
                    RealBloodPressureMeasureActivity.this.isStarting = false;
                    RealBloodPressureMeasureActivity.this.mLottieAnimationView.cancelAnimation();
                    try {
                        if (RealBloodPressureMeasureActivity.this.wakeLock == null || !RealBloodPressureMeasureActivity.this.wakeLock.isHeld()) {
                            return;
                        }
                        RealBloodPressureMeasureActivity.this.wakeLock.release();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadDatas(int sbp, int dbp) {
        List<Integer> list = this.lists;
        if (list == null || list.size() < 500 || !"true".equals(Tools.readString("BPUPLOADENABLE", this, BooleanUtils.FALSE))) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ECGRealDataBean eCGRealDataBean = new ECGRealDataBean();
        eCGRealDataBean.bangleMac = YCBTClient.getBindDeviceMac();
        eCGRealDataBean.bangleName = YCBTClient.getBindDeviceName() + "/" + sbp + "-" + dbp;
        eCGRealDataBean.time = System.currentTimeMillis();
        eCGRealDataBean.userId = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, UUIDUtils.generateUUID(this));
        eCGRealDataBean.ecgOriginal = new Gson().toJson(this.lists);
        arrayList.add(eCGRealDataBean);
        HttpUtils.getInstance().postJsonMsgAsynHttp(this, "https://web-api.ycaviation.com/smartam/upheartEcgOriginalData", new Gson().toJson(arrayList), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                RealBloodPressureMeasureActivity.this.lists.clear();
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (this.isStarting) {
                if (this.wakeLock == null) {
                    this.wakeLock = this.powerManager.newWakeLock(26, "My Lock");
                }
                if (this.wakeLock.isHeld()) {
                    return;
                }
                this.wakeLock.acquire();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        stop();
        try {
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock != null && wakeLock.isHeld()) {
                this.wakeLock.release();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.isTimes = false;
        EventBus.getDefault().unregister(this);
    }

    private void stop() {
        if (this.isStarting) {
            playStopMeasure(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMyDialog(final int type) {
        new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black_light).setContentText(getString(R.string.ecg_measure_dialog_message)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.register_confirm_password)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.7
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
                if (type == 0) {
                    RealBloodPressureMeasureActivity.this.finish();
                } else {
                    RealBloodPressureMeasureActivity.this.startActivity(new Intent(RealBloodPressureMeasureActivity.this, (Class<?>) MainActivity.class));
                }
            }
        }).build().show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && this.isStarting) {
            showMyDialog(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity
    public void backAction() {
        if (this.isStarting) {
            showMyDialog(0);
        } else {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataResponse(ToAppDataResponse toAppDataResponse) throws Resources.NotFoundException {
        HashMap map = toAppDataResponse.hashMap;
        int i2 = toAppDataResponse.f5706i;
        if (map == null || map.get("dataType") == null || ((Integer) map.get("dataType")).intValue() != 1040 || map.get("datas") == null) {
            return;
        }
        byte[] bArr = (byte[]) map.get("datas");
        Logger.d("chong-------data==" + Arrays.toString(bArr));
        this.isStarting = false;
        this.mLottieAnimationView.cancelAnimation();
        upLoadDatas(bArr[1] & 255, bArr[2] & 255);
        byte b2 = bArr[4];
        if (b2 == 0) {
            this.number = 10;
            ToastUtil.getInstance(getApplicationContext()).toast(R.string.measure_success);
            TextView textView = this.tvLottieData;
            if (textView != null) {
                textView.setText((bArr[1] & 255) + "/" + (bArr[2] & 255));
            }
            syncData();
            this.handler.sendEmptyMessage(2);
            return;
        }
        if (b2 == 1) {
            this.tvStartButton.setEnabled(true);
            ToastUtil.getInstance(getApplicationContext()).toast(R.string.measure_failed);
            try {
                PowerManager.WakeLock wakeLock = this.wakeLock;
                if (wakeLock == null || !wakeLock.isHeld()) {
                    return;
                }
                this.wakeLock.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRealDataResponse(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        HashMap map = realDataResponse.hashMap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(EventBusSyncData eventBusSyncData) {
        this.tvStartButton.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.RealBloodPressureMeasureActivity.8
            @Override // java.lang.Runnable
            public void run() {
                RealBloodPressureMeasureActivity.this.dismissProgressDialog();
            }
        }, 1000L);
    }
}
