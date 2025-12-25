package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.realsil.sdk.dfu.DfuException;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.ElectrocardiogramListBean;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.databinding.ActivityEcgBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.AIDiagnosisResultBean;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgMeasureHisListBean;
import com.yucheng.smarthealthpro.home.view.Cardiograph2View;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.viewmodel.EcgViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class EcgActivity extends BaseVbActivity<ActivityEcgBinding> {
    private boolean afflag;
    Cardiograph2View cardiograph2View;
    private String devId;
    private int heart;
    private int hrv;
    ImageView ivFirstLeft;
    ImageView ivFirstRight;
    ImageView ivFourthlyLeft;
    ImageView ivFourthlyRight;
    ImageView ivSecondLeft;
    ImageView ivSecondRight;
    ImageView ivThirdlyLeft;
    ImageView ivThirdlyRight;
    LinearLayout llStartButton;
    private EcgHisListAdapter mEcgHisListAdapter;
    private List<EcgMeasure> mEcgMeasureDb;
    private List<EcgMeasureHisListBean> mEcgMeasureHisListBean;
    private List<Integer> mEcgMeasureList;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    private EcgViewModel mViewModel;
    private String phone;
    private int qrstype;
    RelativeLayout rlAnalyse;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    RelativeLayout rlThirdly;
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

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.tvBpm = ((ActivityEcgBinding) this.mBinding).tvBpm;
        this.tvMmhg = ((ActivityEcgBinding) this.mBinding).tvMmhg;
        this.tvHrv = ((ActivityEcgBinding) this.mBinding).tvHrv;
        this.tvStartButton = ((ActivityEcgBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityEcgBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityEcgBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityEcgBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityEcgBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityEcgBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityEcgBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityEcgBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityEcgBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivThirdlyLeft = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivThirdlyLeft;
        this.tvThirdly = ((ActivityEcgBinding) this.mBinding).includeItemBottom.tvThirdly;
        this.ivThirdlyRight = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivThirdlyRight;
        this.rlThirdly = ((ActivityEcgBinding) this.mBinding).includeItemBottom.rlThirdly;
        this.ivFourthlyLeft = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityEcgBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityEcgBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityEcgBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityEcgBinding) this.mBinding).includeItemBottom.recycleView;
        this.mSmartRefreshLayout = ((ActivityEcgBinding) this.mBinding).srlEcg;
        this.mNestedScrollView = ((ActivityEcgBinding) this.mBinding).nsv;
        this.cardiograph2View = ((ActivityEcgBinding) this.mBinding).cardiograph2View;
        this.tvStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlThirdly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_ecg_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                ShareUtils.share(EcgActivity.this);
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
            this.mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.2
                @Override // com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
                public void onLoadMore(RefreshLayout refreshLayout) {
                    EcgActivity.this.page++;
                    EcgActivity ecgActivity = EcgActivity.this;
                    ecgActivity.requestElectrocardiogramData(ecgActivity.devId, EcgActivity.this.page, EcgActivity.this.pageSize);
                    refreshLayout.finishLoadMore();
                }
            });
        } else {
            this.phone = UserInfoUtil.getUserNickName();
            this.tvStartButton.setText(getString(R.string.ecg_measure_title));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_second_button));
            this.ivFirstLeft.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_know, null));
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASECGHISTORYUPLOAD)) {
                this.rlThirdly.setVisibility(0);
            } else {
                this.rlThirdly.setVisibility(8);
            }
        }
        this.tvAnalyse.setText(getString(R.string.ecg_analyse_title));
        this.tvSecond.setText(getString(R.string.home_ecg_trend_following_tv));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivSecondLeft.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_trend, null));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        this.mSmartRefreshLayout.setEnableRefresh(false);
        this.mSmartRefreshLayout.setEnableLoadMore(false);
    }

    private void initViewModel() {
        this.mViewModel = (EcgViewModel) new ViewModelProvider(this).get(EcgViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHistoryEcgResultFlow(), new FlowUtils.FlowCollector<HealthResult<List<EcgMeasure>>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<List<EcgMeasure>> result) {
                EcgActivity.this.onEcgMeasureDbList(result.getValue());
            }
        });
    }

    private void initData() {
        this.cardiograph2View.setDatas(this.blist_change, true);
        this.cardiograph2View.invalidate();
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
        MLog.INSTANCE.d("setAnalyzeResult: " + this.afflag + StringUtils.SPACE + this.qrstype);
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.heartlinelist, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                String string;
                StringBuilder sbAppend;
                int i2;
                AIDiagnosisResultBean.AIResult aIResult;
                if (result == null || EcgActivity.this.isDestroyed()) {
                    return;
                }
                try {
                    EcgActivity ecgActivity = EcgActivity.this;
                    ecgActivity.electrocardiograms = ((ElectrocardiogramListBean) ecgActivity.mGson.fromJson(result, ElectrocardiogramListBean.class)).data.items;
                } catch (JsonSyntaxException e2) {
                    e2.printStackTrace();
                }
                Iterator it2 = EcgActivity.this.electrocardiograms.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    ElectrocardiogramListBean.DataBean.Data data = (ElectrocardiogramListBean.DataBean.Data) it2.next();
                    try {
                        aIResult = (AIDiagnosisResultBean.AIResult) EcgActivity.this.mGson.fromJson(data.medicalResult, AIDiagnosisResultBean.AIResult.class);
                    } catch (JsonSyntaxException e3) {
                        e3.printStackTrace();
                        aIResult = null;
                    }
                    if (aIResult != null) {
                        EcgActivity.this.afflag = aIResult.afflag != 0;
                        EcgActivity.this.qrstype = aIResult.qrstype;
                    } else {
                        EcgActivity.this.afflag = false;
                        EcgActivity.this.qrstype = 1;
                    }
                    EcgActivity.this.mEcgMeasureHisListBean.add(new EcgMeasureHisListBean(data.userId, data.time, data.hrHz, data.hhhh, data.maxb, data.minb, data.data, data.age, data.sex, data.medicalResult, EcgActivity.this.afflag, EcgActivity.this.qrstype));
                }
                if (EcgActivity.this.electrocardiograms.size() > 0) {
                    ElectrocardiogramListBean.DataBean.Data data2 = (ElectrocardiogramListBean.DataBean.Data) EcgActivity.this.electrocardiograms.get(0);
                    try {
                        EcgActivity ecgActivity2 = EcgActivity.this;
                        ecgActivity2.mEcgMeasureList = (List) ecgActivity2.mGson.fromJson(data2.data, new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.4.1
                        }.getType());
                    } catch (JsonSyntaxException e4) {
                        e4.printStackTrace();
                    }
                    if (EcgActivity.this.mEcgMeasureList != null && EcgActivity.this.mEcgMeasureList.size() > 0) {
                        EcgActivity.this.tvBpm.setText(data2.hhhh > 0 ? data2.hhhh + "" : "--");
                        TextView textView = EcgActivity.this.tvMmhg;
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
                        EcgActivity.this.tvHrv.setText(i3 > 0 ? i3 + "" : "--");
                        EcgActivity.this.showCarView();
                    }
                }
                EcgActivity.this.setRecycleView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEcgMeasureDbList(List<EcgMeasure> data) {
        String string;
        StringBuilder sbAppend;
        int maxBp;
        this.mEcgMeasureHisListBean.clear();
        this.mEcgMeasureDb = data;
        if (data != null && data.size() > 0) {
            for (Iterator<EcgMeasure> it2 = this.mEcgMeasureDb.iterator(); it2.hasNext(); it2 = it2) {
                EcgMeasure next = it2.next();
                this.mEcgMeasureHisListBean.add(new EcgMeasureHisListBean(next.getUserId(), next.getStartTimestamp(), next.getHrv(), next.getHeartRate(), next.getMaxBp(), next.getMinBp(), next.getMeasureData(), next.getAge(), next.getSex(), "", next.isAfib(), next.getDiagnoseType(), next.getHealthNorm()));
            }
            try {
                this.mEcgMeasureList = (List) this.mGson.fromJson(this.mEcgMeasureDb.get(0).getMeasureData(), new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.5
                }.getType());
            } catch (JsonSyntaxException e2) {
                e2.printStackTrace();
            }
            EcgMeasure ecgMeasure = this.mEcgMeasureDb.get(0);
            this.heart = ecgMeasure.getHeartRate();
            this.hrv = ecgMeasure.getHrv();
            this.afflag = ecgMeasure.isAfib();
            this.qrstype = ecgMeasure.getDiagnoseType();
            MLog.INSTANCE.d("afflag: " + this.afflag + " qrstype: " + this.qrstype);
            this.tvBpm.setText(this.heart > 0 ? this.heart + "" : "--");
            TextView textView = this.tvMmhg;
            if (ecgMeasure.getMaxBp() == 0 || ecgMeasure.getMinBp() == 0) {
                string = "--/--";
            } else {
                if (ecgMeasure.getMaxBp() > ecgMeasure.getMinBp()) {
                    sbAppend = new StringBuilder().append(ecgMeasure.getMaxBp()).append("/");
                    maxBp = ecgMeasure.getMinBp();
                } else {
                    sbAppend = new StringBuilder().append(ecgMeasure.getMinBp()).append("/");
                    maxBp = ecgMeasure.getMaxBp();
                }
                string = sbAppend.append(maxBp).toString();
            }
            textView.setText(string);
            this.tvHrv.setText(this.hrv > 0 ? this.hrv + "" : "--");
            showCarView();
        }
        setRecycleView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCarView() {
        this.blist_change.clear();
        int size = this.mEcgMeasureList.size();
        if (size > 280) {
            for (int i2 = DfuException.ERROR_ENTER_OTA_MODE_FAILED; i2 < this.mEcgMeasureList.size(); i2++) {
                this.blist_change.add(this.mEcgMeasureList.get(i2));
            }
        } else {
            this.blist_change.addAll(this.mEcgMeasureList);
        }
        this.cardiograph2View.setDatas(this.blist_change, true);
        this.cardiograph2View.invalidate();
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
            return;
        }
        ecgHisListAdapter.setList(this.mEcgMeasureHisListBean);
        this.mEcgHisListAdapter.notifyDataSetChanged();
        setAnalyzeResult();
    }

    private class ItemClickListenerIpml implements EcgHisListAdapter.OnItemClickListener {
        @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.OnItemClickListener
        public void onClick(EcgMeasureHisListBean bean, int position) {
        }

        private ItemClickListenerIpml() {
        }

        @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.OnItemClickListener
        public void onEcgClick(EcgMeasureHisListBean bean, int position) {
            EcgActivity.this.startEcgRecodeActivity(bean, position);
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
            Intent intent = new Intent(EcgActivity.this.context, (Class<?>) EcgAiDiagnoseActivity.class);
            if (Constant.isHealthWear() || Constant.isSmartHealth()) {
                intent = new Intent(EcgActivity.this.context, (Class<?>) EcgAiDiagnoseNew2Activity.class);
            }
            if (EcgActivity.this.isCare.booleanValue()) {
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
                intent.putExtra("care", "关爱");
                intent.putExtra("phone", EcgActivity.this.phone);
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
            EcgActivity.this.startActivity(intent);
        }

        @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.OnItemClickListener
        public void onPlayBackClick(EcgMeasureHisListBean bean, int position) {
            Intent intent;
            if (EcgActivity.this.isCare.booleanValue()) {
                intent = new Intent(EcgActivity.this.context, (Class<?>) EcgPlayBackActivity.class);
                intent.putExtra("care", EcgActivity.this.getString(R.string.care_title));
                intent.putExtra("data", bean.getMeasureData());
                intent.putExtra("heart", bean.getHeart());
                intent.putExtra("maxBp", bean.getMaxBp());
                intent.putExtra("minBp", bean.getMinBp());
                intent.putExtra("hrv", bean.getHrv());
            } else {
                Intent intent2 = new Intent(EcgActivity.this.context, (Class<?>) EcgPlayBackActivity.class);
                intent2.putExtra("mEcgMeasureDbSize", position);
                intent2.putExtra("time", bean.getTime());
                intent = intent2;
            }
            intent.putExtra("isCare", EcgActivity.this.isCare);
            intent.putExtra("bean", bean);
            EcgActivity.this.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startEcgRecodeActivity(EcgMeasureHisListBean bean, int position) {
        ArrayList<Integer> arrayList;
        if (this.isCare.booleanValue()) {
            try {
                arrayList = (ArrayList) this.mGson.fromJson(bean.getMeasureData(), new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.6
                }.getType());
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
                arrayList = null;
            }
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            Intent intent = new Intent(this.context, (Class<?>) EcgRecordActivity.class);
            intent.putExtra("mEcgMeasureDbSize", position);
            intent.putExtra("care", "关爱");
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
        if (view.getId() == R.id.tv_start_button) {
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
                }
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity.7
                    @Override // java.lang.Runnable
                    public void run() {
                        EcgActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (EcgActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(EcgActivity.this.context, 56.0f) * 1.5f)));
                    }
                }, 100L);
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
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusMessageEvent messageEvent) {
        if (messageEvent.belState != 0) {
            return;
        }
        finish();
    }
}
