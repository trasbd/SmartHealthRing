package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.BatteryActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.ModelsInfoDataBean;
import com.yucheng.smarthealthpro.databinding.ActivityMeDeviceBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.me.adapter.MeListAdapter;
import com.yucheng.smarthealthpro.me.bean.MeListBean;
import com.yucheng.smarthealthpro.me.setting.SettingsDataType;
import com.yucheng.smarthealthpro.me.setting.dial.DialActivity;
import com.yucheng.smarthealthpro.settings.AboutDeviceActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.BatteryUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class MeDeviceActivity extends BaseVbActivity<ActivityMeDeviceBinding> {
    ImageView ivHead;
    ImageView ivTvDeviceState;
    LinearLayout llDisConnect;
    private AppImageMgr mAppImageMgr;
    private MeListAdapter mMeListAdapter;
    private List<MeListBean> mMeListBean;
    RecyclerView mRecyclerView;
    private CustomSelectors mThemeCustomSelectors;
    private int mThemeSelectorsIndex;
    TextView tvDeviceKwh;
    TextView tvDeviceName;
    private ArrayList<String> firstThemeList = new ArrayList<>();
    private int count = 0;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        this.ivHead = ((ActivityMeDeviceBinding) this.mBinding).ivHead;
        this.mRecyclerView = ((ActivityMeDeviceBinding) this.mBinding).recycleView;
        this.tvDeviceName = ((ActivityMeDeviceBinding) this.mBinding).tvDeviceName;
        this.ivTvDeviceState = ((ActivityMeDeviceBinding) this.mBinding).ivTvDeviceState;
        this.tvDeviceKwh = ((ActivityMeDeviceBinding) this.mBinding).tvDeviceKwh;
        this.llDisConnect = ((ActivityMeDeviceBinding) this.mBinding).llDisConnect;
        this.ivHead.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llDisConnect.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeDeviceBinding) this.mBinding).llDevice.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_my_device_title));
        showBack();
    }

    private void initData() {
        this.tvDeviceName.setText(YCBTClient.getBindDeviceName());
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.THEME_SELECTORS_INDEX, "");
        if (str != null && !str.isEmpty()) {
            this.mThemeSelectorsIndex = Integer.parseInt(str);
        } else {
            this.mThemeSelectorsIndex = 0;
        }
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this, "deviceBatteryValue", 100)).intValue();
        this.tvDeviceKwh.setText(iIntValue + "%");
        int batteryId = BatteryUtil.getBatteryId(iIntValue);
        if (batteryId != 0) {
            this.ivTvDeviceState.setImageResource(batteryId);
        }
        if (Constant.isRing()) {
            this.ivHead.setImageResource(R.mipmap.ic_device_ring);
        } else {
            this.ivHead.setImageResource(R.mipmap.icon_me_watch_default);
        }
        getDeviceImage();
        this.mAppImageMgr = new AppImageMgr(this.context);
        this.mMeListBean = new ArrayList();
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASDIAL)) {
            if (!Constant.isTechFeel()) {
                this.mMeListBean.add(new MeListBean(getString(R.string.setting_dial_title), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_watch), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 111));
            }
        } else if (YCBTClient.connectState() == 10 && YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTHEME)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_theme), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_theme), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 112));
            YCBTClient.getThemeInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    final int iIntValue2 = ((Integer) resultMap.get("themeTotal")).intValue();
                    MeDeviceActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int i2 = 0;
                            while (i2 < iIntValue2) {
                                int i3 = i2 + 1;
                                MeDeviceActivity.this.firstThemeList.add(i2, MeDeviceActivity.this.getString(R.string.me_my_device_theme) + i3);
                                i2 = i3;
                            }
                        }
                    });
                }
            });
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.include_bottom_tv_first_button), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_watch_monitor), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 113));
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASANTILOST) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASFINDDEVICE)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_anti_lost_title), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_watch_lose), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", SettingsDataType.ANTI_LOST));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASVIBRATIONINTENSITY) && Constant.isRing()) {
            this.mMeListBean.add(new MeListBean(getString(R.string.vibration_settings), this.mAppImageMgr.getBitmap(R.mipmap.ic_device_vibration), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 117));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINFORMATION)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_information_push_title), this.mAppImageMgr.getBitmap(R.mipmap.ic_push_message), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 8));
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.setting_title), this.mAppImageMgr.getBitmap(R.mipmap.icon_me_watch_set), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", SettingsDataType.MORE_SETTINGS));
        if (!Constant.isTechFeel() && !Constant.isHealthRing()) {
            int i2 = R.mipmap.icon_about_device;
            if (Constant.isRing()) {
                i2 = R.mipmap.ic_device_ring_2;
            }
            this.mMeListBean.add(new MeListBean(getString(R.string.about_device), this.mAppImageMgr.getBitmap(i2), this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 116));
        }
        setRecycleView();
    }

    private void getDeviceImage() {
        ImageView imageView;
        String deviceType = Tools.getDeviceType(this.context);
        String string = SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.DEVICE_IMAGE_PATH + deviceType, "").toString();
        if (!TextUtils.isEmpty(string)) {
            try {
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(string);
                if (bitmapDecodeFile != null && (imageView = this.ivHead) != null) {
                    imageView.setImageBitmap(bitmapDecodeFile);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                CrashReport.postCatchedException(e2);
            }
        }
        HashMap map = new HashMap();
        map.put("modelName", deviceType);
        String str = com.yucheng.smarthealthpro.framework.util.Constants.GetModelInfo;
        Logger.d("deviceImageUrl=" + str);
        HttpUtils.getInstance().getMsgAsynHttpV2(this, str, map, new AnonymousClass2(deviceType));
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity$2, reason: invalid class name */
    class AnonymousClass2 implements HttpUtils.HttpCallback {
        final /* synthetic */ String val$deviceType;

        AnonymousClass2(final String val$deviceType) {
            this.val$deviceType = val$deviceType;
        }

        @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
        public void onSuccess(String result) {
            if (TextUtils.isEmpty(result)) {
                return;
            }
            try {
                final String str = ((ModelsInfoDataBean) new Gson().fromJson(result, ModelsInfoDataBean.class)).data.imagesUrl;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                HttpUtils.getInstance().download(MeDeviceActivity.this, str, com.yucheng.smarthealthpro.framework.util.Constants.avatarPath, new HttpUtils.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.2.1
                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                    public void onDownloading(int progress) {
                    }

                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                    public void onDownloadSuccess() {
                        if (MeDeviceActivity.this == null) {
                            return;
                        }
                        MeDeviceActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                String strSubstring = str.substring(str.lastIndexOf("/"));
                                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(com.yucheng.smarthealthpro.framework.util.Constants.avatarPath + strSubstring);
                                if (bitmapDecodeFile != null && MeDeviceActivity.this.ivHead != null) {
                                    MeDeviceActivity.this.ivHead.setImageBitmap(bitmapDecodeFile);
                                }
                                SharedPreferencesUtils.put(MeDeviceActivity.this.getApplicationContext(), Constant.SpConstKey.DEVICE_IMAGE_PATH + AnonymousClass2.this.val$deviceType, com.yucheng.smarthealthpro.framework.util.Constants.avatarPath + strSubstring);
                            }
                        });
                    }

                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                    public void onDownloadFailed() {
                        Logger.d("onDownloadFailed");
                    }
                });
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
            }
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        MeListAdapter meListAdapter = new MeListAdapter(R.layout.item_me_list, 3, this.context);
        this.mMeListAdapter = meListAdapter;
        meListAdapter.addData((Collection) this.mMeListBean);
        this.mRecyclerView.setAdapter(this.mMeListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mMeListAdapter.setOnItemClickListener(new MeListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.3
            @Override // com.yucheng.smarthealthpro.me.adapter.MeListAdapter.OnItemClickListener
            public void onClick(MeListBean hisSearch, int position) {
                if (YCBTClient.connectState() != 10) {
                    return;
                }
                int dataType = hisSearch.getDataType();
                if (dataType != 8) {
                    switch (dataType) {
                        case 111:
                            if (Tools.readLogin(MeDeviceActivity.this)) {
                                MeDeviceActivity.this.startActivity(new Intent(MeDeviceActivity.this.context, (Class<?>) DialActivity.class));
                                break;
                            } else {
                                ToastUtil.getInstance(MeDeviceActivity.this).toast(MeDeviceActivity.this.getString(R.string.me_using_help_feed_back_token_null));
                                break;
                            }
                        case 112:
                            MeDeviceActivity.this.initThemePicker();
                            break;
                        case 113:
                            MeDeviceActivity.this.startActivity(new Intent(MeDeviceActivity.this.context, (Class<?>) MeHealthSettingActivity.class));
                            break;
                        case SettingsDataType.ANTI_LOST /* 114 */:
                            MeDeviceActivity.this.startActivity(new Intent(MeDeviceActivity.this.context, (Class<?>) MeAntiLostActivity.class));
                            break;
                        case SettingsDataType.MORE_SETTINGS /* 115 */:
                            MeDeviceActivity.this.startActivity(new Intent(MeDeviceActivity.this.context, (Class<?>) MeMoreSettingsActivity.class));
                            break;
                        case 116:
                            MeDeviceActivity.this.startActivity(new Intent(MeDeviceActivity.this.context, (Class<?>) AboutDeviceActivity.class));
                            break;
                        case 117:
                            MeDeviceActivity.this.openVibration();
                            break;
                    }
                    return;
                }
                if (!PermissionUtil.isNotificationEnable(MeDeviceActivity.this.context)) {
                    PermissionUtil.openNotificationSetting(MeDeviceActivity.this.context);
                } else {
                    MeDeviceActivity.this.startActivity(new Intent(MeDeviceActivity.this.context, (Class<?>) MePushMessageActivity.class));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openVibration() {
        CustomSelectors customSelectors = new CustomSelectors();
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string.close));
        arrayList.add(getString(R.string.index_weak));
        arrayList.add(getString(R.string.me_my_device_more_settings_display_setup_selectors_center));
        arrayList.add(getString(R.string.index_strong));
        customSelectors.BpLevelPicker(arrayList, null, null, ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.VIBRATION_SETTINGS, 0)).intValue(), 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        customSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.4
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(final String oneValue, final int optionsOne) {
                YCBTClient.settingVibrationIntensity(Integer.parseInt(Integer.toHexString(optionsOne)), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.4.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int i2, float v, HashMap hashMap) {
                        Logger.i(oneValue, new Object[0]);
                        SharedPreferencesUtils.put(MeDeviceActivity.this.context, Constant.SpConstKey.VIBRATION_SETTINGS, Integer.valueOf(optionsOne));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initThemePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mThemeCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.firstThemeList, null, null, this.mThemeSelectorsIndex, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mThemeCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.5
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, final int optionsOne) {
                YCBTClient.settingMainTheme(optionsOne, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.5.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float v, HashMap hashMap) {
                        if (code == 0) {
                            MeDeviceActivity.this.mThemeSelectorsIndex = optionsOne;
                            SharedPreferencesUtils.put(MeDeviceActivity.this.context, Constant.SpConstKey.THEME_SELECTORS_INDEX, optionsOne + "");
                        }
                    }
                });
            }
        });
    }

    private void initDialog(String content, boolean isSingle) {
        final CommonDialog commonDialog = new CommonDialog(this.context);
        commonDialog.setMessage(content).setTitle(getString(R.string.prompt)).setSingle(isSingle).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDeviceActivity.6
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                YCBTClient.disconnectBle();
                SharedPreferencesUtils.put(MeDeviceActivity.this.context, Constant.IS_BATTERY_LOW_SHOW, false);
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
        String string;
        if (view.getId() == R.id.iv_head) {
            return;
        }
        if (view.getId() == R.id.ll_dis_connect) {
            int i2 = this.count;
            boolean z = true;
            if (i2 < 3) {
                this.count = i2 + 1;
                string = getString(R.string.me_my_device_dis_connect_dialog_message);
                z = false;
            } else {
                this.count = 0;
                string = getString(R.string.me_my_device_dis_connect_dialog_message_cant_dis);
            }
            initDialog(string, z);
            return;
        }
        if (view.getId() == R.id.llDevice && YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_BATTERY_INFO_UPLOAD)) {
            startActivity(new Intent(this, (Class<?>) BatteryActivity.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        Logger.d("chong--------sttate==" + messageEvent.belState);
        if (messageEvent.belState != 0) {
            return;
        }
        finish();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
