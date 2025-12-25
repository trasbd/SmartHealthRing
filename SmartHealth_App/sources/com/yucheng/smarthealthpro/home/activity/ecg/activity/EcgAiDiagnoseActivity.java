package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.utils.ImageViewUtil;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.databinding.ActivityAiDiagnosisBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgAiDiagnoseAdapter;
import com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgDataAdapter;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgAiDiagnoseItemBean;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgDataBean;
import com.yucheng.smarthealthpro.home.view.Cardiograph3View;
import com.yucheng.smarthealthpro.login.normal.WebViewActivity;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.HRVUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.viewmodel.EcgViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class EcgAiDiagnoseActivity extends BaseVbActivity<ActivityAiDiagnosisBinding> implements CancelAdapt {
    TextView aiDiagnosisInDoubt;
    public List<Integer> blist = new ArrayList();
    private Cardiograph3View cardiographView;
    RecyclerView dataRecyclerView;
    private EcgDataAdapter ecgDataAdapter;
    private HealthNormBean healthNorm;
    private boolean isAfib;
    ImageView ivAiDiagnosisEcg;
    private List<EcgAiDiagnoseItemBean> lists;
    private int mAge;
    private Bitmap mBitmap;
    private String mBp;
    private int mDiagnoseType;
    private int mEcgMeasureDbSize;
    private List<Integer> mEcgMeasureList;
    private Gson mGson;
    private int mHRV;
    private int mHeart;
    private String mMeasureTime;
    RecyclerView mRecyclerView;
    private int mSex;
    private EcgViewModel mViewModel;
    private String phone;
    TextView tvAge;
    TextView tvBool;
    TextView tvDetails;
    TextView tvDiagnosisResult;
    TextView tvHeart;
    TextView tvSex;
    TextView tv_title;
    private String ycble_bindedmac;
    private String ycble_bindedname;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.tvDiagnosisResult = ((ActivityAiDiagnosisBinding) this.mBinding).tvDiagnosisResult;
        this.tvHeart = ((ActivityAiDiagnosisBinding) this.mBinding).tvHeart;
        this.tvDetails = ((ActivityAiDiagnosisBinding) this.mBinding).tvDetails;
        this.tvAge = ((ActivityAiDiagnosisBinding) this.mBinding).tvAge;
        this.tvSex = ((ActivityAiDiagnosisBinding) this.mBinding).tvSex;
        this.tvBool = ((ActivityAiDiagnosisBinding) this.mBinding).tvBool;
        this.ivAiDiagnosisEcg = ((ActivityAiDiagnosisBinding) this.mBinding).ivAiDiagnosisEcg;
        this.mRecyclerView = ((ActivityAiDiagnosisBinding) this.mBinding).recycleView;
        this.aiDiagnosisInDoubt = ((ActivityAiDiagnosisBinding) this.mBinding).aiDiagnosisInDoubt;
        this.tv_title = ((ActivityAiDiagnosisBinding) this.mBinding).aiDiagnosisDetailTitleTv;
        this.dataRecyclerView = ((ActivityAiDiagnosisBinding) this.mBinding).dataRecyclerView;
        changeTitle(getString(R.string.ecg_ai_diagnose_title));
        showBack();
        this.mGson = new Gson();
        this.lists = new ArrayList();
        if (getString(R.string.lan).equals("cn")) {
            ((ImageView) findViewById(R.id.iv_ai_title)).setImageResource(R.mipmap.ai_title_cn);
        }
        if (Constant.isTechFeel()) {
            this.mRecyclerView.setVisibility(8);
            this.tv_title.setVisibility(8);
        }
    }

    private void initViewModel() {
        this.mViewModel = (EcgViewModel) new ViewModelProvider(this).get(EcgViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHistoryEcgResultFlow(), new FlowUtils.FlowCollector<HealthResult<List<EcgMeasure>>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.1
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<List<EcgMeasure>> result) {
                EcgAiDiagnoseActivity.this.onFindEcgHistory(result.getValue());
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
        } else {
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
        this.tvAge.setText(getString(R.string.me_personal_details_age) + ":" + this.mAge);
        this.tvBool.setText(getString(R.string.home_blood_pressure_title) + ":" + this.mBp);
        this.tvHeart.setText(this.mHeart > 0 ? this.mHeart + "" : "--");
        this.tvSex.setText(getString(R.string.me_personal_details_sex) + ":" + getString(this.mSex == 0 ? R.string.man_text : R.string.woman_text));
        if (Constant.isHealthWear() || Constant.isSmartHealth()) {
            String stringExtra2 = intent.getStringExtra("healthNorm");
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.healthNorm = (HealthNormBean) this.mGson.fromJson(stringExtra2, HealthNormBean.class);
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
                this.tvDiagnosisResult.setText(getString(R.string.ecg_atrial));
                this.tvDetails.setText(getString(R.string.ecg_atrial_detail));
            } else {
                int i4 = this.mDiagnoseType;
                if (i4 == 1) {
                    this.tvDiagnosisResult.setText(getString(R.string.ai_diagnosis_normal_ecg));
                    this.tvDetails.setText(getString(R.string.ecg_normal_detail));
                } else if (i4 == 5) {
                    this.tvDiagnosisResult.setText(getString(R.string.ecg_pvcs));
                    this.tvDetails.setText(getString(R.string.ecg_pvcs_detail));
                } else if (i4 == 9) {
                    this.tvDiagnosisResult.setText(getString(R.string.ecg_atrial_extrasystole));
                    this.tvDetails.setText(getString(R.string.ecg_atrial_extrasystole_detail));
                } else if (i4 == 14) {
                    this.tvDiagnosisResult.setText(getString(R.string.ecg_not_detectable));
                    this.tvDetails.setText(getString(R.string.ecg_not_detectable_detail));
                } else {
                    int i5 = this.mHeart;
                    if (i5 != 0 && i5 <= 50) {
                        this.tvDiagnosisResult.setText(getString(R.string.ecg_abc));
                        this.tvDetails.setText(getString(R.string.ecg_abc_detail));
                    } else if (i5 != 0 && i5 >= 120) {
                        this.tvDiagnosisResult.setText(getString(R.string.ecg_tach));
                        this.tvDetails.setText(getString(R.string.ecg_tach_detail));
                    } else if (!"--".equals(Integer.valueOf(this.mHRV)) && (i3 = this.mHRV) != 0 && i3 >= 125) {
                        this.tvDiagnosisResult.setText(getString(R.string.ecg_sinus_arrhythmia));
                        this.tvDetails.setText(getString(R.string.ecg_sinus_arrhythmia_detail));
                    } else {
                        this.tvDiagnosisResult.setText(getString(R.string.ecg_not_detectable));
                        this.tvDetails.setText(getString(R.string.ecg_not_detectable_detail));
                    }
                }
            }
        } else if (this.isAfib) {
            this.tvDiagnosisResult.setText(getString(R.string.ai_diagnosis_suspected_atrial_fibrillation));
            this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_fibrillation_detail));
        } else {
            int i6 = this.mDiagnoseType;
            if (i6 == 5) {
                this.tvDiagnosisResult.setText(getString(R.string.ecg_ai_diagnosis_ventricular_extrasystole));
                this.tvDetails.setText(getString(R.string.ai_diagnosis_ventricular_precordial_electrocardiogram_detail));
            } else if (i6 == 9) {
                this.tvDiagnosisResult.setText(getString(R.string.ecg_ai_diagnosis_atrial_extrasystole));
                this.tvDetails.setText(getString(R.string.ai_diagnosis_atrial_premature_electrocardiogram_detail));
            } else {
                int i7 = this.mHeart;
                if (i7 != 0 && i7 <= 50) {
                    this.tvDiagnosisResult.setText(getString(R.string.ai_diagnosis_suspected_atrial_bradycardia));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_bradycardia_detail));
                } else if (i7 != 0 && i7 >= 120) {
                    this.tvDiagnosisResult.setText(getString(R.string.ai_diagnosis_suspected_atrial_tachycardia));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_tachycardia_detail));
                } else if (!"--".equals(Integer.valueOf(this.mHRV)) && (i2 = this.mHRV) != 0 && i2 >= 125) {
                    this.tvDiagnosisResult.setText(getString(R.string.ai_diagnosis_suspected_atrial_arrhythmia));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_suspected_atrial_arrhythmia_detail));
                } else {
                    this.tvDiagnosisResult.setText(getString(R.string.ai_diagnosis_normal_ecg));
                    this.tvDetails.setText(getString(R.string.ai_diagnosis_normal_ecg_detail));
                }
            }
        }
        addBean();
        List<Integer> list = this.mEcgMeasureList;
        if (list != null) {
            addView(list);
        }
        if (Constant.isHealthWear() || Constant.isSmartHealth()) {
            setDataRecyclerView();
        }
        setRecycleView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFindEcgHistory(List<EcgMeasure> data) {
        if (data != null && data.size() > 0) {
            this.mEcgMeasureList = (List) this.mGson.fromJson(data.get(0).getMeasureData(), new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.2
            }.getType());
        }
        addView(this.mEcgMeasureList);
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
        this.ecgDataAdapter = new EcgDataAdapter(R.layout.item_ecg_diagnose);
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
        this.ecgDataAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity$$ExternalSyntheticLambda0
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
        relativeLayout.addView(this.cardiographView, new RelativeLayout.LayoutParams((int) (Cardiograph3View.getMultiple(this) * 2700.0f), (int) ((((this.cardiographView.getDatas().size() * 3) % 2500 == 0 ? (this.cardiographView.getDatas().size() * 3) / 2500 : ((this.cardiographView.getDatas().size() * 3) / 2500) + 1) + 1) * 200 * Cardiograph3View.getMultiple(this))));
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
            this.ivAiDiagnosisEcg.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ImageViewUtil.getInstance(EcgAiDiagnoseActivity.this.mBitmap);
                    EcgAiDiagnoseActivity.this.startActivity(new Intent(EcgAiDiagnoseActivity.this.context, (Class<?>) AIResultImageActivity.class));
                }
            });
        }
    }

    public void aiLogin() {
        new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(Constants.AiLogin).addHeader(HttpHeaders.CONTENT_TYPE, "application/json").post(new FormBody.Builder().add("merchant_name", "szyc0808").add("merchant_password", "c3p5YzA4MDg=").build()).build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.4
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e2) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                EcgAiDiagnoseActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() throws NumberFormatException {
                        try {
                            if (new JSONObject(strString).getInt("code") == 200) {
                                EcgAiDiagnoseActivity.this.testzd();
                            } else {
                                Tools.showAlert3(EcgAiDiagnoseActivity.this.context, "数据错误");
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
            if (!this.tvHeart.getText().toString().trim().equals("--")) {
                strTrim = this.tvHeart.getText().toString().trim();
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
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.5
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return HttpUtils.verifyHostName(hostname);
            }
        }).connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(com.yucheng.smarthealthpro.framework.util.Constants.ecgUrl).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject.toString())).addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset:utf-8").addHeader("merchantname", "c3p5YzA4MDg=").addHeader("publickey", "e6411Z54MmxxzmFEaRlSuTcO3bCmE78U75QFHUedZw").build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.6
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e3) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jSONObject2 = new JSONObject(response.body().string());
                    if (jSONObject2.getInt("code") == 200) {
                        EcgAiDiagnoseActivity.this.go2WebActivity(jSONObject2);
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EcgAiDiagnoseAdapter ecgAiDiagnoseAdapter = new EcgAiDiagnoseAdapter(R.layout.item_ecg_ai_diagnosis);
        ecgAiDiagnoseAdapter.addData((Collection) this.lists);
        this.mRecyclerView.setAdapter(ecgAiDiagnoseAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        ecgAiDiagnoseAdapter.setOnItemClickListener(new EcgAiDiagnoseAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgAiDiagnoseActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgAiDiagnoseAdapter.OnItemClickListener
            public void onClick(EcgAiDiagnoseItemBean hisSearch, int position) {
                Intent intent = new Intent(EcgAiDiagnoseActivity.this, (Class<?>) AiWebActivity.class);
                intent.putExtra("title", ((EcgAiDiagnoseItemBean) EcgAiDiagnoseActivity.this.lists.get(position)).getType().split(":")[0]);
                intent.putExtra("url", ((EcgAiDiagnoseItemBean) EcgAiDiagnoseActivity.this.lists.get(position)).getUrl());
                EcgAiDiagnoseActivity.this.startActivity(intent);
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
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
            }
            startActivity(intent);
            return;
        }
        Tools.showAlert3(this.context, getString(R.string.save_failed));
    }
}
