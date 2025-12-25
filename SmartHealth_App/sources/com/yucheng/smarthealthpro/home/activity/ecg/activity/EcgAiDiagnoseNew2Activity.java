package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.utils.ImageViewUtil;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.databinding.ActivityAiDiagnosisNew2Binding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgAiDiagnoseAdapter;
import com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgDataAdapter2;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgAiDiagnoseItemBean;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgDataBean;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgMeasureHisListBean;
import com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.view.Cardiograph2View;
import com.yucheng.smarthealthpro.home.view.Cardiograph3View;
import com.yucheng.smarthealthpro.home.view.CardiographView;
import com.yucheng.smarthealthpro.login.normal.WebViewActivity;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.HRVUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.viewmodel.EcgViewModel;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import me.jessyan.autosize.internal.CancelAdapt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class EcgAiDiagnoseNew2Activity extends BaseVbActivity<ActivityAiDiagnosisNew2Binding> implements CancelAdapt {
    TextView aiDiagnosisInDoubt;
    private Cardiograph3View cardiographView;
    RecyclerView dataRecyclerView;
    private EcgDataAdapter2 ecgDataAdapter;
    private HealthNormBean healthNorm;
    HorizontalScrollView hsvEcgView;
    private boolean isAfib;
    boolean isCare;
    ImageView ivAiDiagnosisEcg;
    ImageView ivPlay;
    RelativeLayout.LayoutParams layte;
    private List<EcgAiDiagnoseItemBean> lists;
    LinearLayoutCompat llAiCapture;
    private int mAge;
    private Bitmap mBitmap;
    private String mBp;
    private Cardiograph2View mCardiograph2View;
    CardiographView mCardiographView;
    private int mDiagnoseType;
    private int mEcgMeasureDbSize;
    private List<Integer> mEcgMeasureList;
    private int mHRV;
    private int mHeart;
    private String mMeasureTime;
    private MediaPlayer mMediaPlay;
    RecyclerView mRecyclerView;
    private int mSex;
    private EcgViewModel mViewModel;
    private List<Integer> nativeList;
    private String phone;
    RelativeLayout rlEcgMeasureView;
    SeekBar sbProgress;
    TextView tvAgeValue;
    TextView tvBPValue;
    TextView tvDetailTag;
    TextView tvDetails;
    TextView tvDiagnosis;
    TextView tvGenValue;
    TextView tvHRVValue;
    TextView tv_title;
    private Gson xmsUtil;
    private String ycble_bindedmac;
    private String ycble_bindedname;
    public List<Integer> blist = new ArrayList();
    private boolean isStart = false;
    private int index = 0;
    private List<EcgMeasureHisListBean> mEcgMeasureHisListBean = new ArrayList();
    private Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.11
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws IllegalStateException {
            super.handleMessage(msg);
            if (msg.what == 1 && EcgAiDiagnoseNew2Activity.this.isStart && EcgAiDiagnoseNew2Activity.this.mCardiographView.plist != null) {
                if (EcgAiDiagnoseNew2Activity.this.index < EcgAiDiagnoseNew2Activity.this.mEcgMeasureList.size()) {
                    if (EcgAiDiagnoseNew2Activity.this.mCardiographView.plist.size() > EcgAiDiagnoseNew2Activity.this.mCardiographView.WidthDots) {
                        EcgAiDiagnoseNew2Activity.this.mCardiographView.plist.remove(0);
                    }
                    EcgAiDiagnoseNew2Activity.this.mCardiographView.plist.add((Integer) EcgAiDiagnoseNew2Activity.this.mEcgMeasureList.get(EcgAiDiagnoseNew2Activity.this.index));
                    EcgAiDiagnoseNew2Activity.this.mCardiographView.invalidate();
                    int i2 = (EcgAiDiagnoseNew2Activity.this.index * 3) + 753;
                    if (i2 < EcgAiDiagnoseNew2Activity.this.nativeList.size()) {
                        if (EcgAiDiagnoseNew2Activity.this.index == 0) {
                            AITools.getInstance().ecgRealWaveFiltering(EcgAiDiagnoseNew2Activity.this.nativeList.subList(0, 753));
                        } else {
                            AITools.getInstance().ecgRealWaveFiltering(EcgAiDiagnoseNew2Activity.this.nativeList.subList(((EcgAiDiagnoseNew2Activity.this.index - 1) * 3) + 753, i2));
                        }
                    }
                    int unused = EcgAiDiagnoseNew2Activity.this.index;
                    EcgAiDiagnoseNew2Activity.this.mEcgMeasureList.size();
                    EcgAiDiagnoseNew2Activity.this.sbProgress.setMax(EcgAiDiagnoseNew2Activity.this.mEcgMeasureList.size());
                    EcgAiDiagnoseNew2Activity.this.sbProgress.setProgress(EcgAiDiagnoseNew2Activity.this.index);
                    EcgAiDiagnoseNew2Activity.this.index++;
                    return;
                }
                EcgAiDiagnoseNew2Activity.this.isStart = false;
                EcgAiDiagnoseNew2Activity.this.ivPlay.setImageResource(R.mipmap.ecg_play);
                return;
            }
            if (msg.what == 22 && EcgAiDiagnoseNew2Activity.this.isStart && EcgAiDiagnoseNew2Activity.this.mMediaPlay != null) {
                EcgAiDiagnoseNew2Activity.this.mMediaPlay.start();
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.tvDiagnosis = ((ActivityAiDiagnosisNew2Binding) this.mBinding).tvDiagnosis2;
        this.tvHRVValue = ((ActivityAiDiagnosisNew2Binding) this.mBinding).includeDiagnoseHeader.tvHRVValue;
        this.tvDetails = ((ActivityAiDiagnosisNew2Binding) this.mBinding).tvDetail2;
        this.tvAgeValue = ((ActivityAiDiagnosisNew2Binding) this.mBinding).includeDiagnoseHeader.tvAgeValue;
        this.tvGenValue = ((ActivityAiDiagnosisNew2Binding) this.mBinding).includeDiagnoseHeader.tvGenValue;
        this.tvBPValue = ((ActivityAiDiagnosisNew2Binding) this.mBinding).includeDiagnoseHeader.tvBPValue;
        this.tvDetailTag = ((ActivityAiDiagnosisNew2Binding) this.mBinding).tvDetailTag2;
        this.ivAiDiagnosisEcg = ((ActivityAiDiagnosisNew2Binding) this.mBinding).ivAiDiagnosisEcg;
        this.mRecyclerView = ((ActivityAiDiagnosisNew2Binding) this.mBinding).recycleView;
        this.aiDiagnosisInDoubt = ((ActivityAiDiagnosisNew2Binding) this.mBinding).aiDiagnosisInDoubt;
        this.tv_title = ((ActivityAiDiagnosisNew2Binding) this.mBinding).aiDiagnosisDetailTitleTv;
        this.dataRecyclerView = ((ActivityAiDiagnosisNew2Binding) this.mBinding).dataRecyclerView;
        this.mCardiographView = ((ActivityAiDiagnosisNew2Binding) this.mBinding).cardiographView;
        this.sbProgress = ((ActivityAiDiagnosisNew2Binding) this.mBinding).sbProgress;
        this.ivPlay = ((ActivityAiDiagnosisNew2Binding) this.mBinding).ivPlay;
        this.llAiCapture = ((ActivityAiDiagnosisNew2Binding) this.mBinding).llAiCapture;
        this.rlEcgMeasureView = ((ActivityAiDiagnosisNew2Binding) this.mBinding).rlEcgMeasureView;
        this.hsvEcgView = ((ActivityAiDiagnosisNew2Binding) this.mBinding).hsvEcg;
        changeTitle(getString(R.string.ecg_ai_diagnose_title));
        showBack();
        this.xmsUtil = new Gson();
        this.lists = new ArrayList();
        getString(R.string.lan).equals("cn");
        if (Constant.isTechFeel()) {
            this.mRecyclerView.setVisibility(8);
            this.tv_title.setVisibility(8);
        }
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    EcgAiDiagnoseNew2Activity.this.index = progress;
                    int i2 = progress - EcgAiDiagnoseNew2Activity.this.mCardiographView.WidthDots;
                    if (i2 < 0) {
                        i2 = 0;
                    }
                    List listSubList = EcgAiDiagnoseNew2Activity.this.mEcgMeasureList.subList(i2, progress);
                    EcgAiDiagnoseNew2Activity.this.mCardiographView.plist.clear();
                    EcgAiDiagnoseNew2Activity.this.mCardiographView.plist.addAll(listSubList);
                    EcgAiDiagnoseNew2Activity.this.mCardiographView.invalidate();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                EcgAiDiagnoseNew2Activity.this.isStart = false;
                EcgAiDiagnoseNew2Activity.this.ivPlay.setImageResource(R.mipmap.ecg_play);
            }
        });
        this.ivPlay.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgAiDiagnoseNew2Activity.this.isStart) {
                    EcgAiDiagnoseNew2Activity.this.isStart = false;
                    EcgAiDiagnoseNew2Activity.this.ivPlay.setImageResource(R.mipmap.ecg_play);
                } else {
                    EcgAiDiagnoseNew2Activity.this.hsvEcgView.setVisibility(8);
                    EcgAiDiagnoseNew2Activity.this.makeStart();
                }
            }
        });
    }

    private void initViewModel() {
        this.mViewModel = (EcgViewModel) new ViewModelProvider(this).get(EcgViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHistoryEcgResultFlow(), new FlowUtils.FlowCollector<HealthResult<List<EcgMeasure>>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<List<EcgMeasure>> result) {
                EcgAiDiagnoseNew2Activity.this.onFindEcgHistory(result.getValue());
            }
        });
    }

    private void initData() {
        int i2;
        int i3;
        this.ycble_bindedname = YCBTClient.getBindDeviceName();
        this.ycble_bindedmac = YCBTClient.getBindDeviceMac();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("care");
        if (stringExtra != null && stringExtra.equals(getString(R.string.care_title))) {
            this.mEcgMeasureDbSize = intent.getIntExtra("mEcgMeasureDbSize", 0);
            this.mBp = intent.getStringExtra("mBp");
            this.mHeart = intent.getIntExtra("mHeart", 0);
            this.mHRV = intent.getIntExtra("mHRV", 0);
            this.mAge = intent.getIntExtra("mAge", 0);
            this.mSex = intent.getIntExtra("mSex", 0);
            this.isAfib = intent.getBooleanExtra("isAfib", false);
            this.mDiagnoseType = intent.getIntExtra("mDiagnoseType", 0);
            this.phone = intent.getStringExtra("phone");
            this.mEcgMeasureList = intent.getIntegerArrayListExtra("lists");
            this.nativeList = intent.getIntegerArrayListExtra("lists");
            this.isCare = true;
        } else {
            this.isCare = false;
            this.phone = UserInfoUtil.getUserNickName();
            long longExtra = intent.getLongExtra("time", 0L);
            this.mMeasureTime = TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(longExtra));
            this.mEcgMeasureDbSize = intent.getIntExtra("mEcgMeasureDbSize", 0);
            this.mBp = intent.getStringExtra("mBp");
            this.mHeart = intent.getIntExtra("mHeart", 0);
            this.mHRV = intent.getIntExtra("mHRV", 0);
            this.mAge = intent.getIntExtra("mAge", 0);
            this.mSex = intent.getIntExtra("mSex", 0);
            this.isAfib = intent.getBooleanExtra("isAfib", false);
            this.mDiagnoseType = intent.getIntExtra("mDiagnoseType", 0);
            this.mSex = intent.getIntExtra("mSex", 0);
            this.mViewModel.getByStartTime(longExtra);
        }
        this.tvAgeValue.setText(this.mAge + "");
        this.tvBPValue.setText(this.mBp + "");
        this.tvHRVValue.setText(this.mHeart > 0 ? this.mHeart + "" : "--");
        this.tvGenValue.setText(getString(this.mSex == 0 ? R.string.man_text : R.string.woman_text));
        this.tvDetailTag.setText(getString(R.string.ecg_ai_diagnose_details_tv) + ":");
        if (Constant.isHealthWear() || Constant.isSmartHealth()) {
            String stringExtra2 = intent.getStringExtra("healthNorm");
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.healthNorm = (HealthNormBean) this.xmsUtil.fromJson(stringExtra2, HealthNormBean.class);
            }
            HealthNormBean healthNormBean = this.healthNorm;
            if (healthNormBean != null && (healthNormBean.hrvNorm > 0.0f || this.healthNorm.sympatheticParasympathetic > 0.0f || this.healthNorm.pressure > 0.0f || this.healthNorm.respiratoryRate > 0 || this.healthNorm.body > 0.0f || this.healthNorm.heavyLoad > 0.0f)) {
                this.dataRecyclerView.setVisibility(0);
            } else {
                this.dataRecyclerView.setVisibility(8);
            }
        }
        if (Constant.isTechFeel()) {
            if (this.isAfib) {
                this.tvDiagnosis.setText(getString(R.string.ecg_atrial));
                this.tvDetails.setText(getString(R.string.ecg_atrial_detail));
            } else {
                int i4 = this.mDiagnoseType;
                if (i4 == 1) {
                    this.tvDiagnosis.setText(getString(R.string.ai_diagnosis_normal_ecg));
                    this.tvDetails.setText(getString(R.string.ecg_normal_detail));
                } else if (i4 == 5) {
                    this.tvDiagnosis.setText(getString(R.string.ecg_pvcs));
                    this.tvDetails.setText(getString(R.string.ecg_pvcs_detail));
                } else if (i4 == 9) {
                    this.tvDiagnosis.setText(getString(R.string.ecg_atrial_extrasystole));
                    this.tvDetails.setText(getString(R.string.ecg_atrial_extrasystole_detail));
                } else if (i4 == 14) {
                    this.tvDiagnosis.setText(getString(R.string.ecg_not_detectable));
                    this.tvDetails.setText(getString(R.string.ecg_not_detectable_detail));
                } else {
                    int i5 = this.mHeart;
                    if (i5 != 0 && i5 <= 50) {
                        this.tvDiagnosis.setText(getString(R.string.ecg_abc));
                        this.tvDetails.setText(getString(R.string.ecg_abc_detail));
                    } else if (i5 != 0 && i5 >= 120) {
                        this.tvDiagnosis.setText(getString(R.string.ecg_tach));
                        this.tvDetails.setText(getString(R.string.ecg_tach_detail));
                    } else if (!"--".equals(Integer.valueOf(this.mHRV)) && (i3 = this.mHRV) != 0 && i3 >= 125) {
                        this.tvDiagnosis.setText(getString(R.string.ecg_sinus_arrhythmia));
                        this.tvDetails.setText(getString(R.string.ecg_sinus_arrhythmia_detail));
                    } else {
                        this.tvDiagnosis.setText(getString(R.string.ecg_not_detectable));
                        this.tvDetails.setText(getString(R.string.ecg_not_detectable_detail));
                    }
                }
            }
        } else if (this.isAfib) {
            this.tvDiagnosis.setText(getString(R.string.ai_diagnosis_suspected_atrial_fibrillation));
            this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_fibrillation_detail));
        } else {
            int i6 = this.mDiagnoseType;
            if (i6 == 5) {
                this.tvDiagnosis.setText(getString(R.string.ecg_ai_diagnosis_ventricular_extrasystole));
                this.tvDetails.setText(getString(R.string.ai_diagnosis_ventricular_precordial_electrocardiogram_detail));
            } else if (i6 == 9) {
                this.tvDiagnosis.setText(getString(R.string.ecg_ai_diagnosis_atrial_extrasystole));
                this.tvDetails.setText(getString(R.string.ai_diagnosis_atrial_premature_electrocardiogram_detail));
            } else {
                int i7 = this.mHeart;
                if (i7 != 0 && i7 <= 50) {
                    this.tvDiagnosis.setText(getString(R.string.ai_diagnosis_suspected_atrial_bradycardia));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_bradycardia_detail));
                } else if (i7 != 0 && i7 >= 120) {
                    this.tvDiagnosis.setText(getString(R.string.ai_diagnosis_suspected_atrial_tachycardia));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_tachycardia_detail));
                } else if (!"--".equals(Integer.valueOf(this.mHRV)) && (i2 = this.mHRV) != 0 && i2 >= 125) {
                    this.tvDiagnosis.setText(getString(R.string.ai_diagnosis_suspected_atrial_arrhythmia));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_arrhythmia_detail));
                } else {
                    this.tvDiagnosis.setText(getString(R.string.ai_diagnosis_normal_ecg));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_normal_ecg_detail));
                }
            }
        }
        addBean();
        addView(this.mEcgMeasureList);
        if (Constant.isHealthWear() || Constant.isSmartHealth()) {
            setDataRecyclerView();
        }
        setRecycleView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFindEcgHistory(List<EcgMeasure> data) {
        if (data != null && data.size() > 0) {
            String measureData = data.get(0).getMeasureData();
            Type type = new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.4
            }.getType();
            this.mEcgMeasureList = (List) this.xmsUtil.fromJson(measureData, type);
            this.nativeList = (List) this.xmsUtil.fromJson(measureData, type);
        }
        addView(this.mEcgMeasureList);
        showEcgAnime();
    }

    private void addBean() {
        String str;
        if (getString(R.string.lan).equals("cn")) {
            str = "https://staticpage.ycaviation.com/project/ECG_Diag_Web_CH/";
        } else {
            str = "https://staticpage.ycaviation.com/project/ECG_Diag_Web_EN/";
        }
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean = new EcgAiDiagnoseItemBean();
        if (this.isAfib) {
            ecgAiDiagnoseItemBean.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_ok));
        } else {
            ecgAiDiagnoseItemBean.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        }
        ecgAiDiagnoseItemBean.setType(getResources().getString(R.string.ecg_ai_diagnosis_atrial_fibrillation));
        ecgAiDiagnoseItemBean.setUrl(str.concat("fangchan.html"));
        this.lists.add(ecgAiDiagnoseItemBean);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean2 = new EcgAiDiagnoseItemBean();
        ecgAiDiagnoseItemBean2.setType(getResources().getString(R.string.ecg_ai_diagnosis_ventricular_flutter));
        ecgAiDiagnoseItemBean2.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        ecgAiDiagnoseItemBean2.setUrl(str.concat("xinshipudong.html"));
        this.lists.add(ecgAiDiagnoseItemBean2);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean3 = new EcgAiDiagnoseItemBean();
        ecgAiDiagnoseItemBean3.setType(getResources().getString(R.string.ecg_ai_diagnosis_fangxing_yibo));
        ecgAiDiagnoseItemBean3.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        ecgAiDiagnoseItemBean3.setUrl(str.concat("fangxingyibo.html"));
        this.lists.add(ecgAiDiagnoseItemBean3);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean4 = new EcgAiDiagnoseItemBean();
        if (this.mDiagnoseType == 9 && !this.isAfib) {
            ecgAiDiagnoseItemBean4.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_ok));
        } else {
            ecgAiDiagnoseItemBean4.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        }
        ecgAiDiagnoseItemBean4.setType(getResources().getString(R.string.ecg_ai_diagnosis_atrial_extrasystole));
        ecgAiDiagnoseItemBean4.setUrl(str.concat("fangxingzaobo.html"));
        this.lists.add(ecgAiDiagnoseItemBean4);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean5 = new EcgAiDiagnoseItemBean();
        if (this.mDiagnoseType == 5 && !this.isAfib) {
            ecgAiDiagnoseItemBean5.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_ok));
        } else {
            ecgAiDiagnoseItemBean5.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        }
        ecgAiDiagnoseItemBean5.setUrl(str.concat("shixingzaobo.html"));
        ecgAiDiagnoseItemBean5.setType(getResources().getString(R.string.ecg_ai_diagnosis_ventricular_extrasystole));
        this.lists.add(ecgAiDiagnoseItemBean5);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean6 = new EcgAiDiagnoseItemBean();
        ecgAiDiagnoseItemBean6.setType(getResources().getString(R.string.ecg_ai_diagnosis_ventricular_escape));
        ecgAiDiagnoseItemBean6.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        ecgAiDiagnoseItemBean6.setUrl(str.concat("shixingyibo.html"));
        this.lists.add(ecgAiDiagnoseItemBean6);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean7 = new EcgAiDiagnoseItemBean();
        ecgAiDiagnoseItemBean7.setType(getResources().getString(R.string.ecg_ai_diagnosis_borderline_premature_beat));
        ecgAiDiagnoseItemBean7.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        ecgAiDiagnoseItemBean7.setUrl(str.concat("jiaojiexingzaobo.html"));
        this.lists.add(ecgAiDiagnoseItemBean7);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean8 = new EcgAiDiagnoseItemBean();
        ecgAiDiagnoseItemBean8.setType(getResources().getString(R.string.ecg_ai_diagnosis_borderline_escape));
        ecgAiDiagnoseItemBean8.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        ecgAiDiagnoseItemBean8.setUrl(str.concat("jiaojiexingyibo.html"));
        this.lists.add(ecgAiDiagnoseItemBean8);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean9 = new EcgAiDiagnoseItemBean();
        ecgAiDiagnoseItemBean9.setType(getResources().getString(R.string.ecg_ai_diagnosis_left_bundle_branch_block));
        ecgAiDiagnoseItemBean9.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        ecgAiDiagnoseItemBean9.setUrl(str.concat("zuoshuzhichuandaozuzhi.html"));
        this.lists.add(ecgAiDiagnoseItemBean9);
        EcgAiDiagnoseItemBean ecgAiDiagnoseItemBean10 = new EcgAiDiagnoseItemBean();
        ecgAiDiagnoseItemBean10.setType(getResources().getString(R.string.ecg_ai_diagnosis_right_bundle_branch_block));
        ecgAiDiagnoseItemBean10.setResult(getResources().getString(R.string.ecg_ai_diagnosis_result_no));
        ecgAiDiagnoseItemBean10.setUrl(str.concat("youshuzhichuandaozuzhi.html"));
        this.lists.add(ecgAiDiagnoseItemBean10);
    }

    private void setDataRecyclerView() {
        String string = getString(R.string.heavy_load);
        String string2 = getString(R.string.hrv_norm);
        String string3 = getString(R.string.pressure);
        String string4 = getString(R.string.body_index);
        String string5 = getString(R.string.sympathetic_parasympathetic);
        String string6 = getString(R.string.respiratory_rate);
        this.ecgDataAdapter = new EcgDataAdapter2(R.layout.item_ecg_diagnose2);
        ArrayList arrayList = new ArrayList();
        if (this.healthNorm != null) {
            arrayList.add(new EcgDataBean(string, this.healthNorm.heavyLoad, HRVUtils.getLoadRode(this.healthNorm.heavyLoad), 1));
            arrayList.add(new EcgDataBean(string2, this.healthNorm.hrvNorm, HRVUtils.getHRV(this.healthNorm.hrvNorm), 2));
            arrayList.add(new EcgDataBean(string3, this.healthNorm.pressure, HRVUtils.getPressure(this.healthNorm.pressure), 3));
            arrayList.add(new EcgDataBean(string4, this.healthNorm.body, HRVUtils.getBody(this.healthNorm.body), 4));
            arrayList.add(new EcgDataBean(string5, this.healthNorm.sympatheticParasympathetic, HRVUtils.getSympatheticParasympathetic(this.healthNorm.sympatheticParasympathetic), 5));
            arrayList.add(new EcgDataBean(string6, this.healthNorm.respiratoryRate, HRVUtils.getRespiratoryRate(this.healthNorm.respiratoryRate), 6));
        } else {
            this.dataRecyclerView.setVisibility(8);
        }
        this.ecgDataAdapter.setList(arrayList);
        this.dataRecyclerView.setAdapter(this.ecgDataAdapter);
        this.ecgDataAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                this.f$0.lambda$setDataRecyclerView$0(baseQuickAdapter, view, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDataRecyclerView$0(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
        String string = getString(R.string.lan);
        EcgDataBean ecgDataBean = this.ecgDataAdapter.getData().get(i2);
        String str = "activeIndex";
        switch (ecgDataBean.getViewType()) {
            case 1:
                str = "loadIndex";
                break;
            case 2:
                str = "hrvIndex";
                break;
            case 3:
                str = "pressure";
                break;
            case 4:
                str = "bodyIndex";
                break;
            case 6:
                str = "respirationRate";
                break;
        }
        startActivity(new Intent(this, (Class<?>) WebViewActivity.class).putExtra("title", ecgDataBean.getName()).putExtra("url", "https://staticpage.ycaviation.com/app/ecg_body_index/" + str + "/" + str + ".html?language=" + string));
    }

    private void addView(List<Integer> list) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.aaaax);
        this.cardiographView = new Cardiograph3View(this);
        if (Constant.isTechFeel()) {
            this.cardiographView.setDatas(list, "", getString(this.mSex == 0 ? R.string.man_text : R.string.woman_text), this.mAge + "", this.mHeart + "", this.mBp);
        } else {
            this.cardiographView.setDatas(list, this.phone, getString(this.mSex == 0 ? R.string.man_text : R.string.woman_text), this.mAge + "", this.mHeart + "", this.mBp);
        }
        int multiple = (int) (Cardiograph3View.getMultiple(this) * 2700.0f);
        int size = (this.cardiographView.getDatas().size() * 3) % 2500 == 0 ? (this.cardiographView.getDatas().size() * 3) / 2500 : ((this.cardiographView.getDatas().size() * 3) / 2500) + 1;
        if (size < 6) {
            size = 6;
        }
        relativeLayout.addView(this.cardiographView, new RelativeLayout.LayoutParams(multiple, (int) ((size + 1) * 200 * Cardiograph3View.getMultiple(this))));
        this.cardiographView.invalidate();
    }

    private Bitmap loadBitmapFromView(View v) {
        int width = v.getWidth();
        int height = v.getHeight();
        if (height == 0 || width == 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        v.layout(0, 0, width, height);
        v.draw(canvas);
        return bitmapCreateBitmap;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Bitmap bitmapLoadBitmapFromView = loadBitmapFromView(this.cardiographView);
        this.mBitmap = bitmapLoadBitmapFromView;
        if (bitmapLoadBitmapFromView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.ivAiDiagnosisEcg.getLayoutParams();
        layoutParams.width = Tools.getwindowwidth(this) - ((int) (getResources().getDisplayMetrics().density * 30.0f));
        layoutParams.height = (int) (((this.mBitmap.getHeight() * 1.0d) * layoutParams.width) / this.mBitmap.getWidth());
        this.ivAiDiagnosisEcg.setLayoutParams(layoutParams);
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            this.ivAiDiagnosisEcg.setImageBitmap(bitmap);
            this.ivAiDiagnosisEcg.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ImageViewUtil.getInstance(EcgAiDiagnoseNew2Activity.this.mBitmap);
                    EcgAiDiagnoseNew2Activity.this.startActivity(new Intent(EcgAiDiagnoseNew2Activity.this.context, (Class<?>) AIResultImageActivity.class));
                }
            });
        }
    }

    public void makeStart() {
        this.isStart = true;
        this.ivPlay.setImageResource(R.mipmap.ecg_pause);
        if (this.index == this.mEcgMeasureList.size()) {
            this.index = 0;
            AITools.getInstance().init();
            this.mCardiographView.plist.clear();
        }
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.6
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (EcgAiDiagnoseNew2Activity.this.isStart) {
                    try {
                        Thread.sleep(13L);
                    } catch (InterruptedException e2) {
                        e2.getStackTrace();
                    }
                    EcgAiDiagnoseNew2Activity.this.mHandler.sendEmptyMessage(1);
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

    public void aiLogin() {
        new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(Constants.AiLogin).addHeader(HttpHeaders.CONTENT_TYPE, "application/json").post(new FormBody.Builder().add("merchant_name", "szyc0808").add("merchant_password", "c3p5YzA4MDg=").build()).build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.7
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e2) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                EcgAiDiagnoseNew2Activity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.7.1
                    @Override // java.lang.Runnable
                    public void run() throws NumberFormatException {
                        try {
                            if (new JSONObject(strString).getInt("code") == 200) {
                                EcgAiDiagnoseNew2Activity.this.testzd();
                            } else {
                                Tools.showAlert3(EcgAiDiagnoseNew2Activity.this.context, "数据错误");
                            }
                        } catch (JSONException e2) {
                            e2.getStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void testzd() throws NumberFormatException {
        int i2;
        int i3;
        String str;
        String str2 = this.mBp;
        String strTrim = "0";
        if (str2 == null || str2.equals("0/0") || this.mBp.equals("0") || this.mBp.split("/").length <= 1) {
            i2 = 0;
            i3 = 0;
        } else {
            i2 = Integer.parseInt(this.mBp.split("/")[0]);
            i3 = Integer.parseInt(this.mBp.split("/")[1]);
        }
        if (Locale.getDefault().getLanguage().toUpperCase().equals("ZH")) {
            str = "CH";
        } else {
            str = "EN";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (YCBTClient.connectState() == 10) {
                jSONObject.put("device_sn", this.ycble_bindedname + this.ycble_bindedmac);
            } else {
                jSONObject.put("device_sn", "P3 f2157003");
            }
            jSONObject.put(Constant.SpConstKey.AGE, this.mAge);
            jSONObject.put("gender", this.mSex);
            String string = this.phone;
            if (string == null) {
                string = Tools.readString(Constant.SpConstKey.USER_NAME, this.context, "");
            }
            jSONObject.put("cellphone", string);
            jSONObject.put("lead_name", "1");
            jSONObject.put("lead_data", new JSONArray((Collection<?>) this.mEcgMeasureList).toString());
            jSONObject.put("scale_value", 70);
            jSONObject.put("sample_base", 83);
            jSONObject.put("language", str);
            jSONObject.put("past_illness", "-");
            if (!this.tvHRVValue.getText().toString().trim().equals("--")) {
                strTrim = this.tvHRVValue.getText().toString().trim();
            }
            jSONObject.put("heart_beat", Integer.parseInt(strTrim));
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOOD)) {
                jSONObject.put("maxBP", i2);
                jSONObject.put("minBP", i3);
            }
            String str3 = this.ycble_bindedname;
            if (str3 != null && this.ycble_bindedmac != null && (str3.contains("P3") || this.ycble_bindedname.contains("V5") || this.ycble_bindedname.contains("P12") || this.ycble_bindedname.contains("P3A") || this.ycble_bindedname.contains("P3B") || this.ycble_bindedname.contains("XFITMATE") || this.ycble_bindedname.contains("P2A"))) {
                jSONObject.put("show_type", 0);
            } else {
                jSONObject.put("show_type", 1);
            }
            jSONObject.put("app_version", 77);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.8
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return HttpUtils.verifyHostName(hostname);
            }
        }).connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(com.yucheng.smarthealthpro.framework.util.Constants.ecgUrl).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject.toString())).addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset:utf-8").addHeader("merchantname", "c3p5YzA4MDg=").addHeader("publickey", "e6411Z54MmxxzmFEaRlSuTcO3bCmE78U75QFHUedZw").build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.9
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e3) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jSONObject2 = new JSONObject(response.body().string());
                    if (jSONObject2.getInt("code") == 200) {
                        EcgAiDiagnoseNew2Activity.this.go2WebActivity(jSONObject2);
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EcgAiDiagnoseAdapter ecgAiDiagnoseAdapter = new EcgAiDiagnoseAdapter(R.layout.item_ecg_ai_diagnosis_new);
        ecgAiDiagnoseAdapter.addData((Collection) this.lists);
        this.mRecyclerView.setAdapter(ecgAiDiagnoseAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        ecgAiDiagnoseAdapter.setOnItemClickListener(new EcgAiDiagnoseAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseNew2Activity.10
            @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgAiDiagnoseAdapter.OnItemClickListener
            public void onClick(EcgAiDiagnoseItemBean hisSearch, int position) {
                Intent intent = new Intent(EcgAiDiagnoseNew2Activity.this, (Class<?>) AiWebActivity.class);
                intent.putExtra("title", ((EcgAiDiagnoseItemBean) EcgAiDiagnoseNew2Activity.this.lists.get(position)).getType().split(":")[0]);
                intent.putExtra("url", ((EcgAiDiagnoseItemBean) EcgAiDiagnoseNew2Activity.this.lists.get(position)).getUrl());
                EcgAiDiagnoseNew2Activity.this.startActivity(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void go2WebActivity(JSONObject msg) {
        if (msg != null) {
            Intent intent = new Intent(this.context, (Class<?>) AiWebActivity.class);
            intent.putExtra("title", "心电图AI辅诊报告");
            try {
                intent.putExtra("url", msg.getJSONObject("data").getString("ViewUrl"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            startActivity(intent);
            return;
        }
        Tools.showAlert3(this.context, getString(R.string.save_failed));
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
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

    public void showEcgAnime() {
        this.mMediaPlay = MediaPlayer.create(this, R.raw.vidio);
        AITools.getInstance().init();
        checkDatas(this.mEcgMeasureList);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isStart = false;
        this.ivPlay.setImageResource(R.mipmap.ecg_play);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusMessageEvent messageEvent) {
        int i2 = messageEvent.belState;
    }
}
