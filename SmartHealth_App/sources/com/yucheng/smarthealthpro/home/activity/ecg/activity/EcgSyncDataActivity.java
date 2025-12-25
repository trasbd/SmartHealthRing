package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.LinearLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.data.bean.EcgSaveBean;
import com.yucheng.smarthealthpro.data.bean.EcgSyncCheckResult;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.databinding.ActivityEcgsyncdataBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgSyncListAdapter;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.AIDiagnosisResultBean;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgSyncListResponse;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.AIDataBean;
import com.yucheng.ycbtsdk.response.BleAIDiagnosisResponse;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class EcgSyncDataActivity extends BaseVbActivity<ActivityEcgsyncdataBinding> {
    private boolean isAfib;
    LinearLayout llNoData;
    private int mDiagnoseType;
    private EcgSyncListAdapter mEcgSyncListAdapter;
    private Gson mGson;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    private EcgSyncViewModel mViewModel;
    Dialog progressDialog;
    private List<EcgSyncListResponse.DataBean> data = new ArrayList();
    private List<Integer> mEcgMeasureList = new ArrayList();
    Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i2 = msg.what;
            if (i2 == 1) {
                EcgSyncDataActivity.this.dismisDialog(1);
                return false;
            }
            if (i2 == 2) {
                EcgSyncDataActivity.this.dismisDialog(2);
                return false;
            }
            if (i2 == 3) {
                EcgSyncDataActivity.this.upDateSyncData();
                EcgSyncDataActivity.this.mEcgSyncListAdapter.setList(EcgSyncDataActivity.this.data);
                return false;
            }
            if (i2 != 4) {
                return false;
            }
            EcgSyncDataActivity.this.syncEcgList();
            return false;
        }
    });

    private void initData() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismisDialog(int type) {
        Dialog dialog;
        if (isFinishing() || (dialog = this.progressDialog) == null || !dialog.isShowing()) {
            return;
        }
        this.progressDialog.dismiss();
        if (type == 1) {
            ToastUtil.getInstance(this).toast(getString(R.string.ecg_sync_data_failed));
            this.handler.sendEmptyMessage(4);
        } else if (type == 2) {
            ToastUtil.getInstance(this).toast(getString(R.string.ecg_sync_data_success));
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivityEcgsyncdataBinding) this.mBinding).recycleView;
        this.mSmartRefreshLayout = ((ActivityEcgsyncdataBinding) this.mBinding).srlEcg;
        this.llNoData = ((ActivityEcgsyncdataBinding) this.mBinding).llNoData;
        changeTitle(getString(R.string.include_bottom_tv_thirdly_button));
        showBack();
        this.mGson = new Gson();
        this.mSmartRefreshLayout.autoRefresh();
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.2
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public void onRefresh(RefreshLayout refreshLayout) {
                EcgSyncDataActivity.this.syncEcgList();
                refreshLayout.finishRefresh();
            }
        });
        setRecycleView();
    }

    private void initViewModel() {
        this.mViewModel = (EcgSyncViewModel) new ViewModelProvider(this).get(EcgSyncViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSaveResultFlow(), new FlowUtils.FlowCollector<HealthResult<EcgSaveBean>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<EcgSaveBean> result) {
                MLog.INSTANCE.d("ecg 同步保存数据： " + result.getValue());
                if (result.getValue() != null) {
                    EcgMeasure ecgBean = result.getValue().getEcgBean();
                    if (ecgBean.getId().longValue() != -1) {
                        EcgSyncDataActivity.this.deleteEcgInfo(result.getValue().getSendTime());
                    } else {
                        EcgSyncDataActivity.this.handler.sendEmptyMessage(1);
                    }
                    EcgSyncDataActivity.this.onEcgSaved(ecgBean);
                }
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getCheckResultFlow(), new FlowUtils.FlowCollector<EcgSyncCheckResult>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.4
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(EcgSyncCheckResult result) {
                MLog.INSTANCE.d("getCheckResultFlow: " + result);
                if (result.getBean() != null) {
                    if (result.getDuplicate()) {
                        EcgSyncDataActivity.this.deleteEcgInfo(result.getBean().collectSendTime);
                    } else {
                        EcgSyncDataActivity.this.data.add(result.getBean());
                    }
                    EcgSyncDataActivity.this.handler.sendEmptyMessage(3);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncEcgList() {
        if (this.data == null) {
            this.data = new ArrayList();
        }
        this.data.clear();
        YCBTClient.collectEcgList(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.5
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (code == 0) {
                    if (resultMap == null) {
                        EcgSyncDataActivity.this.handler.sendEmptyMessage(3);
                        return;
                    }
                    EcgSyncListResponse ecgSyncListResponse = (EcgSyncListResponse) EcgSyncDataActivity.this.mGson.fromJson(String.valueOf(resultMap), EcgSyncListResponse.class);
                    if (ecgSyncListResponse == null || ecgSyncListResponse.data == null) {
                        EcgSyncDataActivity.this.handler.sendEmptyMessage(3);
                        return;
                    }
                    for (EcgSyncListResponse.DataBean dataBean : ecgSyncListResponse.data) {
                        EcgSyncDataActivity.this.mViewModel.checkIfDuplicate(dataBean.collectStartTime, dataBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncEcgListData(int index, final long startTime, final long sendTime) {
        Dialog dialog = this.progressDialog;
        if (dialog == null || dialog.isShowing()) {
            this.progressDialog = DialogUtils.createLoadingDialog(this, R.string.ecg_sync_data);
        }
        if (!this.progressDialog.isShowing()) {
            this.progressDialog.show();
        }
        List<Integer> list = this.mEcgMeasureList;
        if (list != null) {
            list.clear();
        }
        AITools.getInstance().init();
        this.handler.sendEmptyMessageDelayed(1, 90000L);
        YCBTClient.collectEcgDataWithIndex(index, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.6
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                EcgSyncDataActivity.this.handler.removeMessages(1);
                if (code == 0 && resultMap.get("data") != null) {
                    byte[] bArr = (byte[]) resultMap.get("data");
                    EcgSyncDataActivity.this.mEcgMeasureList = AITools.getInstance().ecgRealWaveFiltering(bArr);
                    EcgSyncDataActivity.this.getAIDiagnosisResult(startTime, sendTime);
                    return;
                }
                EcgSyncDataActivity.this.handler.sendEmptyMessage(1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAIDiagnosisResult(final long startTime, final long sendTime) {
        AITools.getInstance().getAIDiagnosisResult(new BleAIDiagnosisResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.7
            @Override // com.yucheng.ycbtsdk.response.BleAIDiagnosisResponse
            public void onAIDiagnosisResponse(AIDataBean aiDataBean) {
                if (aiDataBean != null) {
                    short s = aiDataBean.heart;
                    EcgSyncDataActivity.this.mDiagnoseType = aiDataBean.qrstype;
                    EcgSyncDataActivity.this.isAfib = aiDataBean.is_atrial_fibrillation;
                    Logger.d("chong------heart==" + ((int) s) + "--qrstype==" + EcgSyncDataActivity.this.mDiagnoseType + "--is_atrial_fibrillation==" + EcgSyncDataActivity.this.isAfib);
                    EcgSyncDataActivity.this.saveEcgInfo(startTime, sendTime);
                    return;
                }
                EcgSyncDataActivity.this.handler.sendEmptyMessage(1);
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        try {
            YCBTClient.resetQueue();
            this.handler.removeCallbacksAndMessages(null);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveEcgInfo(long startTime, long sendTime) {
        int heart = AITools.getInstance().getHeart();
        this.mViewModel.saveEcgMeasureData(new EcgMeasure(null, 0, startTime, TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(startTime)), AITools.getInstance().getHRV(), heart, 0, 0, new Gson().toJson(this.mEcgMeasureList), YearToDayListUtils.getAge((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20))), ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue(), this.isAfib, this.mDiagnoseType, "", UserInfoUtil.getUserName(), Tools.getDeviceType(this.context), YCBTClient.getBindDeviceMac(), "", false, false), sendTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEcgSaved(EcgMeasure ecgMeasure) {
        AIDiagnosisResultBean aIDiagnosisResultBean = new AIDiagnosisResultBean();
        aIDiagnosisResultBean.userId = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, "1");
        aIDiagnosisResultBean.time = ecgMeasure.getStartTimestamp();
        aIDiagnosisResultBean.hrHz = ecgMeasure.getHrv();
        aIDiagnosisResultBean.hhhh = ecgMeasure.getHeartRate();
        aIDiagnosisResultBean.maxb = 0;
        aIDiagnosisResultBean.minb = 0;
        aIDiagnosisResultBean.data = new Gson().toJson(this.mEcgMeasureList);
        aIDiagnosisResultBean.age = ecgMeasure.getAge();
        aIDiagnosisResultBean.sex = ecgMeasure.getSex();
        aIDiagnosisResultBean.medicalResult.afflag = this.isAfib ? 1 : 0;
        aIDiagnosisResultBean.medicalResult.qrstype = this.mDiagnoseType;
        aIDiagnosisResultBean.deviceModel = Tools.getDeviceType(this.context);
        aIDiagnosisResultBean.deviceMac = YCBTClient.getBindDeviceMac();
        ArrayList arrayList = new ArrayList();
        arrayList.add(aIDiagnosisResultBean);
        uploadLocalService(new Gson().toJson(arrayList).toString(), ecgMeasure.getId());
    }

    private void uploadLocalService(String data, final Long id) {
        HttpUtils.getInstance().postJsonMsgAsynHttp(this, Constants.upheartline, data, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.8
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    Logger.d("chong---------response1==" + result);
                    if (result != null) {
                        JSONObject jSONObject = new JSONObject(result);
                        int iOptInt = jSONObject.optInt("code", -1);
                        String strOptString = jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                        if (iOptInt != 0) {
                            if (!TextUtils.isEmpty(strOptString)) {
                                ToastUtil.getInstance(EcgSyncDataActivity.this.getApplicationContext()).toast(strOptString);
                            }
                        } else if (id.longValue() != -1) {
                            EcgSyncDataActivity.this.mViewModel.updateEcgUploaded(id.longValue());
                        }
                    }
                } catch (Exception e2) {
                    CrashReport.postCatchedException(e2);
                    e2.printStackTrace();
                }
            }
        });
        if (Constant.isMymon()) {
            HttpUtils.getInstance().postJsonMsgAsynHttp(this, Constants.upheartline.replace("https://web-api.ycaviation.com/smartam", Constants.BASEMYMOMURL), data, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.9
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public void onSuccess(String result) {
                    try {
                        Logger.d("chong---------response1==" + result);
                    } catch (Exception e2) {
                        CrashReport.postCatchedException(e2);
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteEcgInfo(final long sendTime) {
        YCBTClient.deleteHistoryListData(0, sendTime, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.10
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (code == 0) {
                    EcgSyncDataActivity.this.handler.sendEmptyMessage(2);
                    Iterator it2 = EcgSyncDataActivity.this.data.iterator();
                    while (it2.hasNext()) {
                        if (sendTime == ((EcgSyncListResponse.DataBean) it2.next()).collectSendTime) {
                            EcgSyncDataActivity.this.handler.sendEmptyMessage(4);
                            return;
                        }
                    }
                    return;
                }
                EcgSyncDataActivity.this.handler.sendEmptyMessage(1);
            }
        });
    }

    private void setRecycleView() {
        EcgSyncListAdapter ecgSyncListAdapter = this.mEcgSyncListAdapter;
        if (ecgSyncListAdapter == null) {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
            EcgSyncListAdapter ecgSyncListAdapter2 = new EcgSyncListAdapter(R.layout.item_ecg_sync_list);
            this.mEcgSyncListAdapter = ecgSyncListAdapter2;
            ecgSyncListAdapter2.addData((Collection) this.data);
            this.mRecyclerView.setAdapter(this.mEcgSyncListAdapter);
            this.mRecyclerView.setNestedScrollingEnabled(false);
            this.mEcgSyncListAdapter.setOnItemClickListener(new EcgSyncListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgSyncDataActivity.11
                @Override // com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgSyncListAdapter.OnItemClickListener
                public void onClick(EcgSyncListResponse.DataBean hisSearch, int position) {
                    EcgSyncDataActivity.this.syncEcgListData(hisSearch.collectSN, hisSearch.collectStartTime, hisSearch.collectSendTime);
                }
            });
            return;
        }
        ecgSyncListAdapter.setList(this.data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upDateSyncData() {
        if (!this.data.isEmpty()) {
            this.mRecyclerView.setVisibility(0);
            this.llNoData.setVisibility(8);
            Collections.reverse(this.data);
        } else {
            this.mRecyclerView.setVisibility(8);
            this.llNoData.setVisibility(0);
        }
    }
}
