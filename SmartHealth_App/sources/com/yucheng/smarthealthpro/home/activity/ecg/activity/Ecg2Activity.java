package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.ElectrocardiogramListBean;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.databinding.ActivityEcg2Binding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.AIDiagnosisResultBean;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgMeasureHisListBean;
import com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.view.CardiographView;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.viewmodel.EcgViewModel;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class Ecg2Activity extends BaseVbActivity<ActivityEcg2Binding> {
    private boolean afflag;
    private String devId;
    private int heart;
    private int hrv;
    ImageView ivFirstLeft;
    ImageView ivFirstRight;
    ImageView ivFourthlyLeft;
    ImageView ivFourthlyRight;
    ImageView ivPlay;
    ImageView ivSecondLeft;
    ImageView ivSecondRight;
    ImageView ivThirdlyLeft;
    ImageView ivThirdlyRight;
    RelativeLayout.LayoutParams layte;
    LinearLayout llStartButton;
    CardiographView mCardiographView;
    private EcgHisListAdapter mEcgHisListAdapter;
    private List<EcgMeasure> mEcgMeasureDb;
    private List<Integer> mEcgMeasureDbList;
    private List<EcgMeasureHisListBean> mEcgMeasureHisListBean;
    private List<Integer> mEcgMeasureList;
    private MediaPlayer mMediaPlay;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    private EcgViewModel mViewModel;
    private List<Integer> nativeList;
    private String phone;
    private int qrstype;
    RelativeLayout rlAnalyse;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    RelativeLayout rlThirdly;
    SeekBar sbProgress;
    TextView tvAnalyse;
    TextView tvAnalyseData;
    TextView tvBpm;
    TextView tvFirst;
    TextView tvFourthly;
    TextView tvHrv;
    TextView tvMmhg;
    TextView tvSecond;
    TextView tvStartButton;
    TextView tvThirdly;
    private Gson mGson = new Gson();
    private List<Integer> blist_change = new ArrayList();
    private int ARROW = 0;
    private Boolean isCare = false;
    private List<ElectrocardiogramListBean.DataBean.Data> electrocardiograms = new ArrayList();
    private int page = 1;
    private int pageSize = 10;
    private int index = 0;
    private int index2 = 0;
    private boolean isStart = true;
    private List<Integer> drawLists = new ArrayList();
    boolean isFirst = true;
    private Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws IllegalStateException {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (Ecg2Activity.this.index < Ecg2Activity.this.mEcgMeasureList.size()) {
                    if (Ecg2Activity.this.mCardiographView.plist.size() > Ecg2Activity.this.mCardiographView.WidthDots) {
                        Ecg2Activity.this.mCardiographView.plist.remove(0);
                    }
                    Ecg2Activity.this.mCardiographView.plist.add((Integer) Ecg2Activity.this.mEcgMeasureList.get(Ecg2Activity.this.index));
                    Ecg2Activity.this.mCardiographView.invalidate();
                    int i2 = (Ecg2Activity.this.index * 3) + 753;
                    if (i2 < Ecg2Activity.this.nativeList.size()) {
                        if (Ecg2Activity.this.index == 0) {
                            AITools.getInstance().ecgRealWaveFiltering(Ecg2Activity.this.nativeList.subList(0, 753));
                        } else {
                            AITools.getInstance().ecgRealWaveFiltering(Ecg2Activity.this.nativeList.subList(((Ecg2Activity.this.index - 1) * 3) + 753, i2));
                        }
                    }
                    int unused = Ecg2Activity.this.index;
                    Ecg2Activity.this.mEcgMeasureList.size();
                    Ecg2Activity.this.sbProgress.setMax(Ecg2Activity.this.mEcgMeasureList.size());
                    Ecg2Activity.this.sbProgress.setProgress(Ecg2Activity.this.index);
                    Ecg2Activity.this.index++;
                    return;
                }
                Ecg2Activity.this.isStart = false;
                Ecg2Activity.this.ivPlay.setImageResource(R.mipmap.ecg_play);
                return;
            }
            if (msg.what == 2) {
                if (Ecg2Activity.this.isStart) {
                    if (Ecg2Activity.this.index2 > 2) {
                        int i3 = (int) (Ecg2Activity.this.index2 * 230.76924f);
                        int i4 = (int) ((Ecg2Activity.this.index2 + 1) * 230.76924f);
                        Logger.d("ltf mHandler start=" + i3 + " end=" + i4);
                        if (Ecg2Activity.this.index2 == 3) {
                            AITools.getInstance().ecgRealWaveFiltering(Ecg2Activity.this.nativeList.subList(0, i4));
                        } else if (i3 < Ecg2Activity.this.nativeList.size() && i4 >= Ecg2Activity.this.nativeList.size()) {
                            AITools.getInstance().ecgRealWaveFiltering(Ecg2Activity.this.nativeList.subList(i3, Ecg2Activity.this.nativeList.size() - 1));
                        } else if (i4 < Ecg2Activity.this.nativeList.size()) {
                            AITools.getInstance().ecgRealWaveFiltering(Ecg2Activity.this.nativeList.subList(i3, i4));
                        }
                    }
                    Ecg2Activity.this.index2++;
                    return;
                }
                return;
            }
            if (msg.what == 22 && Ecg2Activity.this.isStart && Ecg2Activity.this.mMediaPlay != null) {
                Ecg2Activity.this.mMediaPlay.start();
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isStart = false;
        this.ivPlay.setImageResource(R.mipmap.ecg_play);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AITools.getInstance().init();
        initView();
        initViewModel();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        HashMap map = realDataResponse.hashMap;
        if (map != null) {
            Log.e("qob", "onRealDataResponse " + i2 + " dataType " + ((Integer) map.get("dataType")).intValue());
            if (i2 == 1541) {
                return;
            }
            if (i2 == 1776) {
                return;
            }
            if (i2 == 1777) {
                this.mHandler.sendEmptyMessage(22);
                Log.e("qob", "RR invo " + ((Float) map.get("data")).floatValue());
            } else if (i2 == 1539) {
                this.mHandler.sendEmptyMessage(23);
            }
        }
    }

    private void initView() {
        this.tvBpm = ((ActivityEcg2Binding) this.mBinding).tvBpm;
        this.tvMmhg = ((ActivityEcg2Binding) this.mBinding).tvMmhg;
        this.tvHrv = ((ActivityEcg2Binding) this.mBinding).tvHrv;
        this.tvStartButton = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.rlSecond;
        this.ivThirdlyLeft = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivThirdlyLeft;
        this.tvThirdly = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.tvThirdly;
        this.ivThirdlyRight = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivThirdlyRight;
        this.rlThirdly = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.rlThirdly;
        this.ivFourthlyLeft = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityEcg2Binding) this.mBinding).includeItemBottom.recycleView;
        this.mSmartRefreshLayout = ((ActivityEcg2Binding) this.mBinding).srlEcg;
        this.mCardiographView = ((ActivityEcg2Binding) this.mBinding).cardiographView;
        this.sbProgress = ((ActivityEcg2Binding) this.mBinding).sbProgress;
        this.ivPlay = ((ActivityEcg2Binding) this.mBinding).ivPlay;
        this.tvStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlThirdly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivPlay.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_ecg_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.2
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (Ecg2Activity.this.checkCanClick()) {
                    ShareUtils.share(Ecg2Activity.this);
                }
            }
        });
        if (Constant.isTechFeel()) {
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
        }
        String stringExtra = getIntent().getStringExtra("care");
        if (stringExtra != null && stringExtra.equals(getString(R.string.care_title))) {
            this.isCare = true;
            this.devId = getIntent().getStringExtra(Constant.SpConstKey.DEV_ID);
            this.phone = getIntent().getStringExtra("phone");
            this.llStartButton.setVisibility(8);
            this.rlFirst.setVisibility(8);
            this.rlThirdly.setVisibility(8);
            this.mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.3
                @Override // com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
                public void onLoadMore(RefreshLayout refreshLayout) {
                    Ecg2Activity.this.page++;
                    Ecg2Activity ecg2Activity = Ecg2Activity.this;
                    ecg2Activity.requestElectrocardiogramData(ecg2Activity.devId, Ecg2Activity.this.page, Ecg2Activity.this.pageSize);
                    refreshLayout.finishLoadMore();
                }
            });
        } else {
            this.tvStartButton.setText(getString(R.string.ecg_measure_title));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_second_button));
            this.ivFirstLeft.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_know, null));
            this.rlThirdly.setVisibility(0);
        }
        this.tvAnalyse.setText(getString(R.string.ecg_analyse_title));
        this.tvSecond.setText(getString(R.string.home_ecg_trend_following_tv));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivSecondLeft.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_trend, null));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        this.mSmartRefreshLayout.setEnableRefresh(false);
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    Ecg2Activity.this.index = progress;
                    int i2 = progress - Ecg2Activity.this.mCardiographView.WidthDots;
                    if (i2 < 0) {
                        i2 = 0;
                    }
                    List listSubList = Ecg2Activity.this.mEcgMeasureList.subList(i2, progress);
                    Ecg2Activity.this.mCardiographView.plist.clear();
                    Ecg2Activity.this.mCardiographView.plist.addAll(listSubList);
                    Ecg2Activity.this.mCardiographView.invalidate();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Ecg2Activity.this.isStart = false;
                Ecg2Activity.this.ivPlay.setImageResource(R.mipmap.ecg_play);
            }
        });
    }

    private void initViewModel() {
        this.mViewModel = (EcgViewModel) new ViewModelProvider(this).get(EcgViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHistoryEcgResultFlow(), new FlowUtils.FlowCollector<HealthResult<List<EcgMeasure>>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.5
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<List<EcgMeasure>> result) {
                Ecg2Activity.this.onEcgMeasureDbList(result.getValue());
            }
        });
    }

    private void initData() {
        this.mEcgMeasureHisListBean = new ArrayList();
        setRecycleView();
        if (this.isCare.booleanValue()) {
            requestElectrocardiogramData(this.devId, this.page, this.pageSize);
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.isCare.booleanValue()) {
            return;
        }
        this.mViewModel.getAllData();
    }

    private void setAnalyzeResult() {
        if (Constant.isTechFeel()) {
            if (this.afflag) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_af_sus) + StringUtils.LF + getResources().getString(R.string.ecg_atrial_detail));
                return;
            }
            int i2 = this.qrstype;
            if (i2 == 1) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_normal) + StringUtils.LF + getResources().getString(R.string.ecg_normal_detail));
                return;
            }
            if (i2 == 5) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_pvcs) + StringUtils.LF + getResources().getString(R.string.ecg_pvcs_detail));
                return;
            }
            if (i2 == 9) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_atrial_extrasystole) + StringUtils.LF + getResources().getString(R.string.ecg_atrial_extrasystole_detail));
                return;
            }
            if (i2 == 14) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_not_detectable));
                return;
            }
            int i3 = this.heart;
            if (i3 != 0 && i3 <= 50) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_abc) + StringUtils.LF + getResources().getString(R.string.ecg_abc_detail));
                return;
            }
            if (i3 >= 120) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_tach) + StringUtils.LF + getResources().getString(R.string.ecg_tach_detail));
                return;
            } else if (this.hrv >= 125) {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_sinus_arrhythmia) + StringUtils.LF + getResources().getString(R.string.ecg_sinus_arrhythmia_detail));
                return;
            } else {
                this.tvAnalyseData.setText(getResources().getString(R.string.ecg_not_detectable));
                return;
            }
        }
        if (this.afflag) {
            this.tvAnalyseData.setText(getResources().getString(R.string.ai_diagnosis_suspected_atrial_fibrillation) + StringUtils.LF + getResources().getString(R.string.ai_diagnosis_suspected_atrial_fibrillation_detail));
            return;
        }
        int i4 = this.qrstype;
        if (i4 == 5) {
            this.tvAnalyseData.setText(getResources().getString(R.string.ecg_ai_diagnosis_atrial_extrasystole) + StringUtils.LF + getResources().getString(R.string.ai_diagnosis_ventricular_precordial_electrocardiogram_detail));
            return;
        }
        if (i4 == 9) {
            this.tvAnalyseData.setText(getResources().getString(R.string.ecg_ai_diagnosis_atrial_extrasystole) + StringUtils.LF + getResources().getString(R.string.ai_diagnosis_atrial_premature_electrocardiogram_detail));
            return;
        }
        int i5 = this.heart;
        if (i5 != 0 && i5 <= 50) {
            this.tvAnalyseData.setText(getResources().getString(R.string.ai_diagnosis_suspected_atrial_bradycardia) + StringUtils.LF + getResources().getString(R.string.ai_diagnosis_suspected_atrial_bradycardia_detail));
            return;
        }
        if (i5 >= 120) {
            this.tvAnalyseData.setText(getResources().getString(R.string.ai_diagnosis_suspected_atrial_tachycardia) + StringUtils.LF + getResources().getString(R.string.ai_diagnosis_suspected_atrial_tachycardia_detail));
            return;
        }
        if (this.hrv >= 125) {
            this.tvAnalyseData.setText(getResources().getString(R.string.ai_diagnosis_suspected_atrial_arrhythmia) + StringUtils.LF + getResources().getString(R.string.ai_diagnosis_suspected_atrial_arrhythmia_detail));
        } else if (i4 == 1) {
            this.tvAnalyseData.setText(getResources().getString(R.string.ai_diagnosis_normal_ecg) + StringUtils.LF + getResources().getString(R.string.ai_diagnosis_normal_ecg_detail));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestElectrocardiogramData(String userId, int page, int pageSize) {
        HashMap map = new HashMap();
        map.put("userId", userId);
        map.put("currentPage", page + "");
        map.put("pageSize", pageSize + "");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.heartlinelist, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                String string;
                StringBuilder sbAppend;
                int i2;
                AIDiagnosisResultBean.AIResult aIResult;
                if (result != null) {
                    try {
                        Ecg2Activity ecg2Activity = Ecg2Activity.this;
                        ecg2Activity.electrocardiograms = ((ElectrocardiogramListBean) ecg2Activity.mGson.fromJson(result, ElectrocardiogramListBean.class)).data.items;
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                    }
                    Iterator it2 = Ecg2Activity.this.electrocardiograms.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        ElectrocardiogramListBean.DataBean.Data data = (ElectrocardiogramListBean.DataBean.Data) it2.next();
                        try {
                            aIResult = (AIDiagnosisResultBean.AIResult) Ecg2Activity.this.mGson.fromJson(data.medicalResult, AIDiagnosisResultBean.AIResult.class);
                        } catch (JsonSyntaxException e3) {
                            e3.printStackTrace();
                            aIResult = null;
                        }
                        if (aIResult != null) {
                            Ecg2Activity.this.afflag = aIResult.afflag != 0;
                            Ecg2Activity.this.qrstype = aIResult.qrstype;
                        } else {
                            Ecg2Activity.this.afflag = false;
                            Ecg2Activity.this.qrstype = 1;
                        }
                        Ecg2Activity.this.mEcgMeasureHisListBean.add(new EcgMeasureHisListBean(data.userId, data.time, data.hrHz, data.hhhh, data.maxb, data.minb, data.data, data.age, data.sex, data.medicalResult, Ecg2Activity.this.afflag, Ecg2Activity.this.qrstype));
                    }
                    if (Ecg2Activity.this.electrocardiograms.size() > 0) {
                        ElectrocardiogramListBean.DataBean.Data data2 = (ElectrocardiogramListBean.DataBean.Data) Ecg2Activity.this.electrocardiograms.get(0);
                        try {
                            Ecg2Activity ecg2Activity2 = Ecg2Activity.this;
                            ecg2Activity2.mEcgMeasureList = (List) ecg2Activity2.mGson.fromJson(data2.data, new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.6.1
                            }.getType());
                        } catch (JsonSyntaxException e4) {
                            e4.printStackTrace();
                        }
                        if (Ecg2Activity.this.mEcgMeasureList != null && Ecg2Activity.this.mEcgMeasureList.size() > 0) {
                            Ecg2Activity.this.tvBpm.setText(data2.hhhh > 0 ? data2.hhhh + "" : "--");
                            TextView textView = Ecg2Activity.this.tvMmhg;
                            if (data2.maxb == 0 || data2.minb == 0) {
                                string = "--/--";
                            } else {
                                if (data2.maxb > data2.minb) {
                                    sbAppend = new StringBuilder().append(data2.maxb).append("/");
                                    i2 = data2.minb;
                                } else {
                                    sbAppend = new StringBuilder().append(data2.minb).append("/");
                                    i2 = data2.maxb;
                                }
                                string = sbAppend.append(i2).toString();
                            }
                            textView.setText(string);
                            int i3 = data2.hrHz;
                            if (i3 > 150) {
                                i3 = 150;
                            }
                            Ecg2Activity.this.tvHrv.setText(i3 > 0 ? i3 + "" : "--");
                            Ecg2Activity.this.showCarView();
                        }
                    }
                    Ecg2Activity.this.setRecycleView();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEcgMeasureDbList(List<EcgMeasure> data) {
        this.mEcgMeasureHisListBean.clear();
        this.mEcgMeasureDb = data;
        if (data != null && data.size() > 0) {
            for (Iterator<EcgMeasure> it2 = this.mEcgMeasureDb.iterator(); it2.hasNext(); it2 = it2) {
                EcgMeasure next = it2.next();
                this.mEcgMeasureHisListBean.add(new EcgMeasureHisListBean(next.getUserId(), next.getStartTimestamp(), next.getHrv(), next.getHeartRate(), next.getMaxBp(), next.getMinBp(), next.getMeasureData(), next.getAge(), next.getSex(), "", next.isAfib(), next.getDiagnoseType(), next.getHealthNorm()));
            }
            try {
                this.mEcgMeasureList = (List) this.mGson.fromJson(this.mEcgMeasureDb.get(0).getMeasureData(), new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.7
                }.getType());
            } catch (JsonSyntaxException e2) {
                e2.printStackTrace();
            }
            EcgMeasure ecgMeasure = this.mEcgMeasureDb.get(0);
            this.heart = ecgMeasure.getHeartRate();
            this.hrv = ecgMeasure.getHrv();
            this.afflag = ecgMeasure.isAfib();
            this.qrstype = ecgMeasure.getDiagnoseType();
            this.tvBpm.setText(this.heart > 0 ? this.heart + "" : "--");
            this.tvMmhg.setText(ecgMeasure.getMaxBp() + "/" + ecgMeasure.getMinBp());
            this.tvHrv.setText(this.hrv > 0 ? this.hrv + "" : "--");
            showCarView();
        }
        setRecycleView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCarView() {
        this.blist_change.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRecycleView() {
        EcgHisListAdapter ecgHisListAdapter = this.mEcgHisListAdapter;
        if (ecgHisListAdapter == null) {
            EcgHisListAdapter ecgHisListAdapter2 = new EcgHisListAdapter(R.layout.item_ecg_his_list);
            this.mEcgHisListAdapter = ecgHisListAdapter2;
            ecgHisListAdapter2.addData((Collection) this.mEcgMeasureHisListBean);
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            this.mRecyclerView.setAdapter(this.mEcgHisListAdapter);
            this.mRecyclerView.setNestedScrollingEnabled(false);
            this.mEcgHisListAdapter.setOnItemClickListener(new ItemClickListenerIpml());
        } else {
            ecgHisListAdapter.setList(this.mEcgMeasureHisListBean);
            this.mEcgHisListAdapter.notifyDataSetChanged();
            setAnalyzeResult();
        }
        showEcgAnime();
    }

    public void showEcgAnime() {
        if (this.mEcgMeasureHisListBean.size() > 0) {
            EcgMeasureHisListBean ecgMeasureHisListBean = this.mEcgMeasureHisListBean.get(0);
            this.mMediaPlay = MediaPlayer.create(this, R.raw.vidio);
            if (this.isCare.booleanValue()) {
                String measureData = ecgMeasureHisListBean.getMeasureData();
                ecgMeasureHisListBean.getHeart();
                ecgMeasureHisListBean.getMaxBp();
                ecgMeasureHisListBean.getMinBp();
                ecgMeasureHisListBean.getHrv();
                Type type = new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.8
                }.getType();
                this.mEcgMeasureDbList = (List) new Gson().fromJson(measureData, type);
                this.nativeList = (List) new Gson().fromJson(measureData, type);
                return;
            }
            this.mGson = new Gson();
            this.mEcgMeasureDbList = new ArrayList();
            String measureData2 = ecgMeasureHisListBean.getMeasureData();
            Type type2 = new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.9
            }.getType();
            this.mEcgMeasureDbList = (List) this.mGson.fromJson(measureData2, type2);
            this.nativeList = (List) new Gson().fromJson(measureData2, type2);
            ecgMeasureHisListBean.getMinBp();
            ecgMeasureHisListBean.getMaxBp();
            ecgMeasureHisListBean.getHeart();
            ecgMeasureHisListBean.getHrv();
        }
    }

    private class ItemClickListenerIpml implements EcgHisListAdapter.OnItemClickListener {
        @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.OnItemClickListener
        public void onClick(EcgMeasureHisListBean bean, int position) {
        }

        private ItemClickListenerIpml() {
        }

        @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.OnItemClickListener
        public void onEcgClick(EcgMeasureHisListBean bean, int position) {
            Ecg2Activity.this.startEcgRecodeActivity(bean, position);
        }

        @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.OnItemClickListener
        public void onDiagnoseClick(EcgMeasureHisListBean bean, int position) {
            String string;
            StringBuilder sb;
            StringBuilder sbAppend;
            int maxBp = bean.getMaxBp();
            int minBp = bean.getMinBp();
            if (minBp == 0 || maxBp == 0) {
                string = "--";
            } else {
                if (maxBp > minBp) {
                    sb = new StringBuilder();
                    sbAppend = sb.append(maxBp).append("/").append(minBp);
                } else {
                    sb = new StringBuilder();
                    sbAppend = sb.append(minBp).append("/").append(maxBp);
                }
                string = sbAppend.toString();
            }
            Intent intent = new Intent(Ecg2Activity.this.context, (Class<?>) EcgAiDiagnoseActivity.class);
            if (Constant.isHealthWear() || Constant.isSmartHealth()) {
                intent = new Intent(Ecg2Activity.this.context, (Class<?>) EcgAiDiagnoseNewActivity.class);
            }
            if (Ecg2Activity.this.isCare.booleanValue()) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                String[] strArrSplit = bean.getMeasureData().replaceAll("\\[", "").replaceAll("]", "").split(",");
                for (int i2 = 0; i2 < strArrSplit.length - 1; i2++) {
                    if ("".equals(strArrSplit[i2].trim())) {
                        if (i2 == 0) {
                            strArrSplit[i2] = "0";
                        } else {
                            strArrSplit[i2] = strArrSplit[i2 - 1];
                        }
                    }
                    arrayList.add(Integer.valueOf(Integer.parseInt(strArrSplit[i2].trim())));
                }
                intent.putExtra("care", Ecg2Activity.this.getString(R.string.care_title));
                intent.putExtra("phone", Ecg2Activity.this.phone);
                try {
                    AIDiagnosisResultBean.AIResult aIResult = (AIDiagnosisResultBean.AIResult) new Gson().fromJson(bean.getMedicalResult(), AIDiagnosisResultBean.AIResult.class);
                    HealthNormBean healthNormBean = new HealthNormBean();
                    if (aIResult != null) {
                        healthNormBean.hrvNorm = Float.parseFloat(aIResult.hrvNorm);
                        healthNormBean.body = Float.parseFloat(aIResult.body);
                        healthNormBean.heavyLoad = Float.parseFloat(aIResult.heavyLoad);
                        healthNormBean.pressure = Float.parseFloat(aIResult.pressure);
                        healthNormBean.respiratoryRate = aIResult.respiratoryRate;
                        healthNormBean.sympatheticParasympathetic = Float.parseFloat(aIResult.sympatheticActivityIndex);
                    }
                    intent.putExtra("healthNorm", new Gson().toJson(healthNormBean));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    CrashReport.postCatchedException(e2);
                }
                intent.putIntegerArrayListExtra("lists", arrayList);
            } else {
                intent.putExtra("time", bean.getTime());
                intent.putExtra("healthNorm", bean.getHealthNorm());
            }
            intent.putExtra("mEcgMeasureDbSize", position);
            intent.putExtra("mBp", string);
            intent.putExtra("mHeart", bean.getHeart());
            intent.putExtra("mHRV", bean.getHrv());
            intent.putExtra("mAge", bean.getAge());
            intent.putExtra("mSex", bean.getSex());
            intent.putExtra("isAfib", bean.isAfib());
            intent.putExtra("mDiagnoseType", bean.getDiagnoseType());
            intent.putExtra("bean", bean);
            Ecg2Activity.this.startActivity(intent);
        }

        @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.OnItemClickListener
        public void onPlayBackClick(EcgMeasureHisListBean bean, int position) {
            Intent intent;
            if (Ecg2Activity.this.isCare.booleanValue()) {
                intent = new Intent(Ecg2Activity.this.context, (Class<?>) EcgPlayBackActivity.class);
                intent.putExtra("care", Ecg2Activity.this.getString(R.string.care_title));
                intent.putExtra("data", bean.getMeasureData());
                intent.putExtra("heart", bean.getHeart());
                intent.putExtra("maxBp", bean.getMaxBp());
                intent.putExtra("minBp", bean.getMinBp());
                intent.putExtra("hrv", bean.getHrv());
            } else {
                Intent intent2 = new Intent(Ecg2Activity.this.context, (Class<?>) EcgPlayBackActivity.class);
                intent2.putExtra("mEcgMeasureDbSize", position);
                intent2.putExtra("time", bean.getTime());
                intent = intent2;
            }
            intent.putExtra("isCare", Ecg2Activity.this.isCare);
            intent.putExtra("bean", bean);
            Ecg2Activity.this.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startEcgRecodeActivity(EcgMeasureHisListBean bean, int position) {
        ArrayList<Integer> arrayList;
        if (this.isCare.booleanValue()) {
            try {
                arrayList = (ArrayList) this.mGson.fromJson(bean.getMeasureData(), new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.10
                }.getType());
            } catch (Exception e2) {
                e2.printStackTrace();
                arrayList = null;
            }
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            Intent intent = new Intent(this.context, (Class<?>) EcgRecordActivity.class);
            intent.putExtra("mEcgMeasureDbSize", position);
            intent.putExtra("care", getString(R.string.care_title));
            intent.putExtra("minBP", bean.getMinBp());
            intent.putExtra("maxBP", bean.getMaxBp());
            intent.putExtra("heart", bean.getHeart());
            intent.putExtra("timeLong", bean.getTime());
            intent.putExtra("hrv", bean.getHrv());
            intent.putIntegerArrayListExtra("data", arrayList);
            startActivity(intent);
            return;
        }
        Intent intent2 = new Intent(this.context, (Class<?>) EcgRecordActivity.class);
        intent2.putExtra("mEcgDbIndex", position);
        startActivity(intent2);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_play) {
            if (this.isStart) {
                this.isStart = false;
                this.ivPlay.setImageResource(R.mipmap.ecg_play);
                return;
            } else {
                makeStart();
                return;
            }
        }
        if (view.getId() == R.id.iv_play) {
            startActivity(new Intent(this.context, (Class<?>) EcgMeasureActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_analyse) {
            return;
        }
        if (view.getId() == R.id.rl_first) {
            startActivity(new Intent(this.context, (Class<?>) HealthyActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_second) {
            if (this.isCare.booleanValue()) {
                Intent intent = new Intent(this.context, (Class<?>) AiWebActivity.class);
                intent.putExtra("is_start_trend", true);
                intent.putExtra("title", getString(R.string.home_ecg_trend_following_tv));
                intent.putExtra(Constant.SpConstKey.DEV_ID, this.devId);
                if (getString(R.string.lan).equals("cn")) {
                    intent.putExtra("url", "https://staticpage.ycaviation.com/app/ECG_Report_Trend/hrRecordcn.html");
                } else {
                    intent.putExtra("url", "https://staticpage.ycaviation.com/app/ECG_Report_Trend/hrRecorden.html");
                }
                startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(this.context, (Class<?>) AiWebActivity.class);
            intent2.putExtra("is_start_trend", true);
            intent2.putExtra("title", getString(R.string.home_ecg_trend_following_tv));
            if (getString(R.string.lan).equals("cn")) {
                intent2.putExtra("url", "https://staticpage.ycaviation.com/app/ECG_Report_Trend/hrRecordcn.html");
            } else {
                intent2.putExtra("url", "https://staticpage.ycaviation.com/app/ECG_Report_Trend/hrRecorden.html");
            }
            startActivity(intent2);
            return;
        }
        if (view.getId() == R.id.rl_thirdly) {
            startActivity(new Intent(this.context, (Class<?>) EcgSyncDataActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_fourthly) {
            if (this.ARROW == 0) {
                this.mRecyclerView.setVisibility(0);
                this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_s, null));
                this.ARROW = 1;
                if (this.isCare.booleanValue()) {
                    this.mSmartRefreshLayout.setEnableLoadMore(true);
                    return;
                }
                return;
            }
            this.mRecyclerView.setVisibility(8);
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
            this.ARROW = 0;
            if (this.isCare.booleanValue()) {
                this.mSmartRefreshLayout.setEnableLoadMore(false);
            }
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
    }

    public void makeStart() {
        this.isStart = true;
        this.ivPlay.setImageResource(R.mipmap.ecg_pause);
        if (this.index == this.mEcgMeasureList.size()) {
            this.index = 0;
            AITools.getInstance().init();
            this.mCardiographView.plist.clear();
        }
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.Ecg2Activity.11
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (Ecg2Activity.this.isStart) {
                    try {
                        Thread.sleep(13L);
                    } catch (InterruptedException e2) {
                        e2.getStackTrace();
                    }
                    Ecg2Activity.this.mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    public void checkDatas(List<Integer> native_list) {
        if (native_list != null) {
            int size = native_list.size();
            Logger.d("chong--------number==" + size);
            if (size > 6250) {
                this.mEcgMeasureList = NativeListToBList.nativeListToBList(native_list);
                return;
            }
            for (int i2 = 0; i2 < size && size > 250; i2++) {
                if (native_list.get(i2).intValue() > 1000) {
                    this.mEcgMeasureList = NativeListToBList.nativeListToBList(native_list);
                    return;
                }
            }
            this.mEcgMeasureList = NativeListToBList.oldListTobList(native_list);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.index = 0;
            this.mCardiographView.plist.clear();
            this.mCardiographView.initList();
            this.mCardiographView.invalidate();
            this.isFirst = false;
            checkDatas(this.mEcgMeasureList);
            this.sbProgress.setMax(this.mEcgMeasureList.size());
            makeStart();
        }
    }
}
