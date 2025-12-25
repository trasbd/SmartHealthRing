package com.yucheng.smarthealthpro.me.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbFragment;
import com.yucheng.smarthealthpro.care.bean.ModelsInfoDataBean;
import com.yucheng.smarthealthpro.databinding.FragmentMeBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.activity.DeviceListActivity;
import com.yucheng.smarthealthpro.login.LoginActivity;
import com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity;
import com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity;
import com.yucheng.smarthealthpro.me.activity.MeDeviceActivity;
import com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity;
import com.yucheng.smarthealthpro.me.activity.MeMessageCenterActivity;
import com.yucheng.smarthealthpro.me.activity.MePersonalActivity;
import com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity;
import com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity;
import com.yucheng.smarthealthpro.me.adapter.MeListAdapter;
import com.yucheng.smarthealthpro.me.bean.MeListBean;
import com.yucheng.smarthealthpro.perfect.LanguageActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.CacheUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.PackageUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.view.CircleImageView;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.utils.SPUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class MeFragment extends BaseVbFragment<FragmentMeBinding> {
    CircleImageView ivHead;
    private AppImageMgr mAppImageMgr;
    private MeListAdapter mMeListAdapter;
    private List<MeListBean> mMeListBean;
    RecyclerView mRecyclerView;
    AppCompatImageView mivImg;
    TextView tvUserName;
    private int deviceBatteryValue = 100;
    Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment$$ExternalSyntheticLambda0
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f$0.lambda$new$0(message);
        }
    });
    Runnable batteryRun = new Runnable() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.3
        @Override // java.lang.Runnable
        public void run() {
            if (MeFragment.this.getActivity() == null || YCBTClient.connectState() != 10) {
                return;
            }
            MeFragment meFragment = MeFragment.this;
            meFragment.deviceBatteryValue = ((Integer) SharedPreferencesUtils.get(meFragment.getActivity(), "deviceBatteryValue", 100)).intValue();
            MeFragment.this.mMeListAdapter.setData(0, new MeListBean(YCBTClient.getBindDeviceName(), MeFragment.this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), MeFragment.this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), MeFragment.this.deviceBatteryValue + "%", 1));
            MeFragment.this.mMeListAdapter.notifyItemChanged(0);
        }
    };

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(Message message) {
        getActivity();
        return false;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, com.yucheng.smarthealthpro.framework.BaseFragment
    protected int initLayout() {
        return R.layout.fragment_me;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.deviceBatteryValue = ((Integer) SharedPreferencesUtils.get(getActivity(), "deviceBatteryValue", 100)).intValue();
        EventBus.getDefault().register(this);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.ivHead = getMViewBind().ivHead;
        this.tvUserName = getMViewBind().tvUserName;
        this.mRecyclerView = getMViewBind().recycleView;
        this.mivImg = getMViewBind().ivImg;
        this.ivHead.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        changeTitle(getString(R.string.me_title));
        this.mAppImageMgr = new AppImageMgr(this.context);
        refreshList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    private void setDeviceState() {
        if (YCBTClient.connectState() == 10) {
            this.mMeListAdapter.setData(0, new MeListBean(TextUtils.isEmpty(YCBTClient.getBindDeviceName()) ? getString(R.string.me_my_device_title) : YCBTClient.getBindDeviceName(), this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), this.deviceBatteryValue + "%", 1));
            this.mMeListAdapter.notifyItemChanged(0);
        } else {
            this.mMeListAdapter.setData(0, new MeListBean(getString(R.string.me_my_device_title), this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "-", 1));
            this.mMeListAdapter.notifyItemChanged(0);
        }
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        Bitmap bitmapDecodeFile;
        super.onResume();
        if (getActivity() == null) {
            return;
        }
        String str = (String) SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.IMAGE_PATH, "");
        final String str2 = (String) SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.HEAD_IMG, "");
        if (!"".equals(str) && str != null && (bitmapDecodeFile = BitmapFactory.decodeFile(str)) != null) {
            this.ivHead.setImageBitmap(bitmapDecodeFile);
        }
        if (str2 != null && !"".equals(str2) && str2.contains("http") && (str == null || "".equals(str) || str2.substring(str2.lastIndexOf("/")).equals(str.substring(str.lastIndexOf("/"))))) {
            HttpUtils.getInstance().download(getActivity(), str2, Constants.avatarPath, new HttpUtils.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.1
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                public void onDownloadFailed() {
                }

                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                public void onDownloading(int progress) {
                }

                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                public void onDownloadSuccess() {
                    if (MeFragment.this.getActivity() == null) {
                        return;
                    }
                    MeFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(Constants.avatarPath + str2.substring(str2.lastIndexOf("/")));
                            if (bitmapDecodeFile2 != null && MeFragment.this.ivHead != null) {
                                MeFragment.this.ivHead.setImageBitmap(bitmapDecodeFile2);
                            }
                            SharedPreferencesUtils.put(MeFragment.this.getActivity(), Constant.SpConstKey.IMAGE_PATH, Constants.avatarPath + str2.substring(str2.lastIndexOf("/")));
                        }
                    });
                }
            });
        }
        if (Tools.readLogin(getActivity())) {
            String str3 = (String) SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.NICK_NAME, "");
            if (str3 == null || "".equals(str3) || str3.contains("http")) {
                this.tvUserName.setText((String) SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.USER_NAME, ""));
            } else {
                this.tvUserName.setText(str3);
            }
        } else {
            this.tvUserName.setText(getString(R.string.me_not_log_in));
            if (Constant.isTechFeel()) {
                this.tvUserName.setText((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.NICK_NAME, getString(R.string.login_default_nick_name)));
            }
        }
        setDeviceState();
        setCache();
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_LOGO)) {
            getImg();
        } else {
            this.mivImg.setVisibility(8);
        }
    }

    private void getBattery() {
        final FragmentActivity activity = getActivity();
        if (activity == null || YCBTClient.connectState() != 10) {
            return;
        }
        this.deviceBatteryValue = ((Integer) SharedPreferencesUtils.get(activity, "deviceBatteryValue", 100)).intValue();
        this.mMeListAdapter.setData(0, new MeListBean(YCBTClient.getBindDeviceName(), this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), this.deviceBatteryValue + "%", 1));
        this.mMeListAdapter.notifyItemChanged(0);
        this.handler.postDelayed(this.batteryRun, 3000L);
        YCBTClient.getDeviceInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.2
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                int iIntValue;
                if (code == 0) {
                    try {
                        if (resultMap == null) {
                            activity.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    MeFragment.this.deviceBatteryValue = ((Integer) SharedPreferencesUtils.get(activity, "deviceBatteryValue", 100)).intValue();
                                    MeFragment.this.mMeListAdapter.setData(0, new MeListBean(YCBTClient.getBindDeviceName(), MeFragment.this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), MeFragment.this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), MeFragment.this.deviceBatteryValue + "%", 1));
                                    MeFragment.this.mMeListAdapter.notifyItemChanged(0);
                                    MeFragment.this.handler.removeCallbacks(MeFragment.this.batteryRun);
                                }
                            });
                            return;
                        }
                        HashMap map = (HashMap) resultMap.get("data");
                        if (((Integer) resultMap.get("dataType")).intValue() == 512) {
                            MeFragment.this.deviceBatteryValue = ((Integer) map.get("deviceBatteryValue")).intValue();
                            SharedPreferencesUtils.put(activity, "deviceBatteryValue", Integer.valueOf(MeFragment.this.deviceBatteryValue));
                            try {
                                iIntValue = ((Integer) map.get("hardwareType")).intValue();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                iIntValue = 0;
                            }
                            SharedPreferencesUtils.put(activity, "hardwareType", Integer.valueOf(iIntValue));
                        }
                        activity.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MeFragment.this.mMeListAdapter.setData(0, new MeListBean(YCBTClient.getBindDeviceName(), MeFragment.this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), MeFragment.this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), MeFragment.this.deviceBatteryValue + "%", 1));
                                MeFragment.this.mMeListAdapter.notifyItemChanged(0);
                                MeFragment.this.handler.removeCallbacks(MeFragment.this.batteryRun);
                            }
                        });
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        CrashReport.postCatchedException(e3);
                    }
                }
            }
        });
    }

    private void setCache() {
        List<MeListBean> data = this.mMeListAdapter.getData();
        for (MeListBean meListBean : data) {
            if (getString(R.string.me_clear_cache_title).equals(meListBean.getTitle())) {
                meListBean.setRightText(CacheUtil.getTotalCacheSize(getContext()));
            }
        }
        this.mMeListAdapter.setList(data);
        this.mMeListAdapter.notifyDataSetChanged();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        MeListAdapter meListAdapter = new MeListAdapter(R.layout.item_me_list, 1, this.context);
        this.mMeListAdapter = meListAdapter;
        meListAdapter.addData((Collection) this.mMeListBean);
        this.mRecyclerView.setAdapter(this.mMeListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mMeListAdapter.setOnItemClickListener(new MeListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.4
            @Override // com.yucheng.smarthealthpro.me.adapter.MeListAdapter.OnItemClickListener
            public void onClick(MeListBean hisSearch, int position) {
                int dataType = hisSearch.getDataType();
                if (dataType == 1) {
                    if (YCBTClient.connectState() == 10) {
                        MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) MeDeviceActivity.class));
                        return;
                    }
                    if (MeFragment.this.getActivity() != null) {
                        ((MainActivity) MeFragment.this.getActivity()).isShowBluetoothDialog = false;
                    }
                    MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) DeviceListActivity.class));
                    return;
                }
                if (dataType == 8) {
                    MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) MeMessageCenterActivity.class));
                    return;
                }
                if (dataType == 17) {
                    MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) HealthFunctionActivity.class));
                    return;
                }
                if (dataType != 18) {
                    switch (dataType) {
                        case 11:
                            if ("0KB".equals(hisSearch.getRightText())) {
                                Toast.makeText(MeFragment.this.context, MeFragment.this.getString(R.string.no_data_to_clear), 0).show();
                                break;
                            } else {
                                MeFragment.this.initClearCacheDialog();
                                break;
                            }
                        case 12:
                            MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) MeSafetySettingActivity.class));
                            break;
                        case 13:
                            if (Constant.isTechFeel()) {
                                MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) MeAboutUsActivity.class));
                                break;
                            } else if (Tools.readLogin(MeFragment.this.requireActivity())) {
                                MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) MeUsingHelpActivity.class));
                                break;
                            } else {
                                ToastUtil.getInstance(MeFragment.this.getActivity()).toast(MeFragment.this.getString(R.string.me_using_help_feed_back_token_null));
                                break;
                            }
                        case 14:
                            MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) MeAboutUsActivity.class));
                            break;
                        case 15:
                            MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) LanguageActivity.class));
                            break;
                    }
                    return;
                }
                MeFragment.this.startActivity(new Intent(MeFragment.this.context, (Class<?>) MeHelpFeedBackActivity.class));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (YCBTClient.connectState() == 10) {
            YCBTClient.getMeasurementFunction(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.5
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    if (i2 == 0) {
                        MeFragment.this.handler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MeFragment.this.refreshList();
                            }
                        });
                        SPUtil.put(Constants.SharedKey.Function_Str, hashMap);
                    }
                }
            });
        }
        int i2 = messageEvent.belState;
        if (i2 != 0) {
            if (i2 != 1) {
                return;
            }
            getBattery();
        } else {
            this.handler.removeCallbacks(this.batteryRun);
            refreshList();
            this.mMeListAdapter.setData(0, new MeListBean(getString(R.string.me_my_device_title), this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "-", 1));
            this.mMeListAdapter.notifyItemChanged(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshList() {
        this.mMeListBean = new ArrayList();
        if (YCBTClient.connectState() == 10) {
            this.mMeListBean.add(new MeListBean((YCBTClient.getBindDeviceName() == null || TextUtils.isEmpty(YCBTClient.getBindDeviceName().trim())) ? getString(R.string.me_my_device_title) : YCBTClient.getBindDeviceName(), this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), this.deviceBatteryValue + "%", 1));
        } else {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_title), this.mAppImageMgr.getBitmap(Constant.getDeviceIcon()), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "-", 1));
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.me_clear_cache_title), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_clear), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), CacheUtil.getTotalCacheSize(getContext()), 11));
        if ((YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MeasurementFunction) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_HEART_RATE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_BLOOD_OXYGEN) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_TEMPERATURE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE_ACCURATE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_ECG) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_HRV)) && YCBTClient.connectState() == 10) {
            this.mMeListBean.add(new MeListBean(getString(R.string.health_functions), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_function), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 17));
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.me_security_settings_title), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_safe), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 12));
        if (!Constant.isTechFeel() && !Constant.isHeGe()) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_using_help_title), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_help), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 13));
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.select_language), this.mAppImageMgr.getBitmap(R.mipmap.ic_language), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 15));
        this.mMeListBean.add(new MeListBean(getString(R.string.me_using_help_feed_back_title), this.mAppImageMgr.getBitmap(R.mipmap.ic_push_message), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 18));
        this.mMeListBean.add(new MeListBean(getString(R.string.me_about_us_title), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_about), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), PackageUtils.getVersionName(this.context) + "(" + PackageUtils.getVersionCode(this.context) + ")", 14));
        setRecycleView();
    }

    private void getImg() {
        String deviceType = Tools.getDeviceType(this.context);
        HashMap map = new HashMap();
        map.put("modelName", deviceType);
        Logger.d("getImg type=" + deviceType);
        this.mivImg.setVisibility(8);
        HttpUtils.getInstance().getMsgAsynHttpV2(getActivity(), com.yucheng.smarthealthpro.framework.util.Constants.GetModelInfo, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.d("result=" + result);
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                try {
                    String str = ((ModelsInfoDataBean) new Gson().fromJson(result, ModelsInfoDataBean.class)).data.institution.logoUrl;
                    if (TextUtils.isEmpty(str) || MeFragment.this.getActivity() == null) {
                        return;
                    }
                    Glide.with(MeFragment.this.getActivity()).load(str).apply((BaseRequestOptions<?>) RequestOptions.fitCenterTransform()).into(MeFragment.this.mivImg);
                    MeFragment.this.mivImg.setVisibility(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    MeFragment.this.mivImg.setVisibility(8);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initClearCacheDialog() {
        final CommonDialog commonDialog = new CommonDialog(this.context);
        commonDialog.setMessage(getString(R.string.me_clear_cache_dialog_message)).setTitle(getString(R.string.prompt)).setConfirm(getString(R.string.me_clear_cache_dialog_confirm)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.fragment.MeFragment.7
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                CacheUtil.clearAllCache(MeFragment.this.context);
                List<MeListBean> data = MeFragment.this.mMeListAdapter.getData();
                for (MeListBean meListBean : data) {
                    if (MeFragment.this.getString(R.string.me_clear_cache_title).equals(meListBean.getTitle())) {
                        meListBean.setRightText("0KB");
                    }
                }
                MeFragment.this.mMeListAdapter.setList(data);
                MeFragment.this.mMeListAdapter.notifyDataSetChanged();
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    public void onViewClicked(View view) {
        if (Tools.readLogin(getActivity()) || Constant.isTechFeel()) {
            startActivity(new Intent(this.context, (Class<?>) MePersonalActivity.class));
        } else {
            startActivity(new Intent(this.context, (Class<?>) LoginActivity.class).setFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
            getActivity().finish();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, com.gyf.immersionbar.components.ImmersionOwner
    public void onVisible() {
        super.onVisible();
        if (YCBTClient.connectState() == 10) {
            getBattery();
        }
        setCache();
        refreshList();
    }
}
