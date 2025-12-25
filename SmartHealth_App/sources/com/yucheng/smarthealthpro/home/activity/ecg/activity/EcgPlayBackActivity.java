package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityEcgpalybackBinding;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgMeasureHisListBean;
import com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.view.CardiographView;
import com.yucheng.ycbtsdk.AITools;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class EcgPlayBackActivity extends BaseVbActivity<ActivityEcgpalybackBinding> {
    CardiographView mCardiographView;
    private List<Integer> mEcgMeasureDbList;
    private Gson mGson;
    private MediaPlayer mMediaPlay;
    private List<Integer> nativeList;
    ProgressBar progressBar;
    TextView tvBpm;
    TextView tvHrv;
    TextView tvMmHg;
    TextView tvSchedule;
    private int index = 0;
    private boolean isStart = true;
    private Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgPlayBackActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws IllegalStateException {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (EcgPlayBackActivity.this.index < EcgPlayBackActivity.this.mEcgMeasureDbList.size()) {
                    if (EcgPlayBackActivity.this.mCardiographView.plist.size() > EcgPlayBackActivity.this.mCardiographView.WidthDots) {
                        EcgPlayBackActivity.this.mCardiographView.plist.remove(0);
                    }
                    EcgPlayBackActivity.this.mCardiographView.plist.add((Integer) EcgPlayBackActivity.this.mEcgMeasureDbList.get(EcgPlayBackActivity.this.index));
                    EcgPlayBackActivity.this.mCardiographView.invalidate();
                    int i2 = (EcgPlayBackActivity.this.index * 3) + 753;
                    if (i2 < EcgPlayBackActivity.this.nativeList.size()) {
                        if (EcgPlayBackActivity.this.index == 0) {
                            AITools.getInstance().ecgRealWaveFiltering(EcgPlayBackActivity.this.nativeList.subList(0, 753));
                        } else {
                            AITools.getInstance().ecgRealWaveFiltering(EcgPlayBackActivity.this.nativeList.subList(((EcgPlayBackActivity.this.index - 1) * 3) + 753, i2));
                        }
                    }
                    EcgPlayBackActivity.this.index++;
                    int size = (int) ((EcgPlayBackActivity.this.index * 100.0f) / EcgPlayBackActivity.this.mEcgMeasureDbList.size());
                    EcgPlayBackActivity.this.progressBar.setProgress(size);
                    EcgPlayBackActivity.this.tvSchedule.setText(size + "%");
                    return;
                }
                EcgPlayBackActivity.this.isStart = false;
                return;
            }
            if (msg.what == 22 && EcgPlayBackActivity.this.mMediaPlay != null && EcgPlayBackActivity.this.isStart) {
                EcgPlayBackActivity.this.mMediaPlay.start();
            }
        }
    };
    boolean isFirst = true;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AITools.getInstance().init();
        initView();
        initData();
    }

    private void initView() {
        this.tvBpm = ((ActivityEcgpalybackBinding) this.mBinding).tvBpm;
        this.tvMmHg = ((ActivityEcgpalybackBinding) this.mBinding).tvMmHg;
        this.tvHrv = ((ActivityEcgpalybackBinding) this.mBinding).tvHrv;
        this.mCardiographView = ((ActivityEcgpalybackBinding) this.mBinding).cardiographView;
        this.tvSchedule = ((ActivityEcgpalybackBinding) this.mBinding).tvSchedule;
        this.progressBar = ((ActivityEcgpalybackBinding) this.mBinding).progressBar;
        changeTitle(getString(R.string.ecg_play_back_title));
        showBack();
    }

    private void initData() {
        String string;
        StringBuilder sb;
        StringBuilder sbAppend;
        String string2;
        StringBuilder sb2;
        StringBuilder sbAppend2;
        Intent intent = getIntent();
        intent.getStringExtra("care");
        boolean booleanExtra = intent.getBooleanExtra("isCare", false);
        EcgMeasureHisListBean ecgMeasureHisListBean = (EcgMeasureHisListBean) intent.getSerializableExtra("bean");
        if (ecgMeasureHisListBean == null) {
            finish();
            return;
        }
        if (booleanExtra) {
            String measureData = ecgMeasureHisListBean.getMeasureData();
            int heart = ecgMeasureHisListBean.getHeart();
            int maxBp = ecgMeasureHisListBean.getMaxBp();
            int minBp = ecgMeasureHisListBean.getMinBp();
            int hrv = ecgMeasureHisListBean.getHrv();
            if (hrv > 150) {
                hrv = 150;
            }
            this.tvBpm.setText(heart > 0 ? heart + "" : "--");
            this.tvMmHg.setText(maxBp + "/" + minBp);
            TextView textView = this.tvMmHg;
            if (minBp == 0 || maxBp == 0) {
                string2 = "--";
            } else {
                if (maxBp > minBp) {
                    sb2 = new StringBuilder();
                    sbAppend2 = sb2.append(maxBp).append("/").append(minBp);
                } else {
                    sb2 = new StringBuilder();
                    sbAppend2 = sb2.append(minBp).append("/").append(maxBp);
                }
                string2 = sbAppend2.toString();
            }
            textView.setText(string2);
            this.tvHrv.setText(hrv > 0 ? hrv + "" : "--");
            Type type = new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgPlayBackActivity.2
            }.getType();
            this.mEcgMeasureDbList = (List) new Gson().fromJson(measureData, type);
            this.nativeList = (List) new Gson().fromJson(measureData, type);
            return;
        }
        this.mGson = new Gson();
        this.mEcgMeasureDbList = new ArrayList();
        String measureData2 = ecgMeasureHisListBean.getMeasureData();
        Type type2 = new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgPlayBackActivity.3
        }.getType();
        this.mEcgMeasureDbList = (List) this.mGson.fromJson(measureData2, type2);
        this.nativeList = (List) new Gson().fromJson(measureData2, type2);
        int minBp2 = ecgMeasureHisListBean.getMinBp();
        int maxBp2 = ecgMeasureHisListBean.getMaxBp();
        int heart2 = ecgMeasureHisListBean.getHeart();
        int hrv2 = ecgMeasureHisListBean.getHrv();
        this.tvBpm.setText(heart2 > 0 ? heart2 + "" : "--");
        this.tvMmHg.setText(maxBp2 + "/" + minBp2);
        TextView textView2 = this.tvMmHg;
        if (minBp2 == 0 || maxBp2 == 0) {
            string = "--";
        } else {
            if (maxBp2 > minBp2) {
                sb = new StringBuilder();
                sbAppend = sb.append(maxBp2).append("/").append(minBp2);
            } else {
                sb = new StringBuilder();
                sbAppend = sb.append(minBp2).append("/").append(maxBp2);
            }
            string = sbAppend.toString();
        }
        textView2.setText(string);
        this.tvHrv.setText(hrv2 > 0 ? hrv2 + "" : "--");
        this.mMediaPlay = MediaPlayer.create(this, R.raw.vidio);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        HashMap map = realDataResponse.hashMap;
        if (i2 == 1777) {
            this.mHandler.sendEmptyMessage(22);
            Log.e("qob", "RR invo " + ((Float) map.get("data")).floatValue());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.index = 0;
        this.isStart = true;
        this.mCardiographView.plist.clear();
        this.mCardiographView.initList();
        this.mCardiographView.invalidate();
        if (this.isFirst) {
            this.isFirst = false;
            makeStart();
        }
    }

    public void makeStart() {
        checkDatas(this.mEcgMeasureDbList);
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgPlayBackActivity.4
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (EcgPlayBackActivity.this.isStart) {
                    try {
                        Thread.sleep(13L);
                    } catch (InterruptedException e2) {
                        e2.getStackTrace();
                    }
                    EcgPlayBackActivity.this.mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    public void checkDatas(List<Integer> native_list) {
        if (native_list != null) {
            int size = native_list.size();
            Logger.d("ltf checkDatas number=" + size);
            if (size > 6250) {
                this.mEcgMeasureDbList = NativeListToBList.nativeListToBList(native_list);
                Logger.d("ltf checkDatas nativeListToBList1=" + this.mEcgMeasureDbList.size());
                return;
            }
            for (int i2 = 0; i2 < size && size > 250; i2++) {
                if (native_list.get(i2).intValue() > 1000) {
                    this.mEcgMeasureDbList = NativeListToBList.nativeListToBList(native_list);
                    Logger.d("ltf checkDatas nativeListToBList2=" + this.mEcgMeasureDbList.size());
                    return;
                }
            }
            this.mEcgMeasureDbList = NativeListToBList.oldListTobList(native_list);
            Logger.d("ltf checkDatas oldListTobList=" + this.mEcgMeasureDbList.size());
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
        List<Integer> list = this.mEcgMeasureDbList;
        if (list != null) {
            list.clear();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isStart = false;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isStart = true;
    }
}
