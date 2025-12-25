package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeMoresettingBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.me.adapter.MeListAdapter;
import com.yucheng.smarthealthpro.me.bean.MeListBean;
import com.yucheng.smarthealthpro.me.setting.camera.CameraActivity;
import com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity;
import com.yucheng.smarthealthpro.me.setting.thirdservice.ThirdPartyServiceActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
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
public class MeMoreSettingsActivity extends BaseVbActivity<ActivityMeMoresettingBinding> {
    CommonDialog dialog;
    private Handler handler = new Handler();
    LinearLayout llFactoryDataReset;
    private AppImageMgr mAppImageMgr;
    private MeListAdapter mMeListAdapter;
    private List<MeListBean> mMeListBean;
    RecyclerView mRecyclerView;
    CommonDialog shutDowndialog;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivityMeMoresettingBinding) this.mBinding).recycleView;
        LinearLayout linearLayout = ((ActivityMeMoresettingBinding) this.mBinding).llFactoryDataReset;
        this.llFactoryDataReset = linearLayout;
        linearLayout.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.setting_title));
        showBack();
    }

    private void initData() {
        this.mAppImageMgr = new AppImageMgr(this.context);
        this.mMeListBean = new ArrayList();
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this, Constant.SpConstKey.SEX, 0)).intValue();
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASNOTITOGGLE)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_dnd_mode_title), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 2));
        }
        if (YCBTClient.getAlarmCount() != 0) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_clock_title), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 3));
        }
        if (iIntValue == 1 && YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASFEMALEPHYSIOLOGICALCYCLE)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_physiological_cycle_title), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 4));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASMANUALTAKEPHOTO) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSHAKETAKEPHOTO)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_photograph), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 5));
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_units_setup_title), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 6));
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWATCHSCREENBRIGHTNESS) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASLIFTBRIGHT)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_display_setup_title), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 7));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCONTACTS)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.permission_tv_contacts_title), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 9));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBUSINESSCARD)) {
            this.mMeListBean.add(new MeListBean(getString(R.string.visiting_card), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 16));
        }
        if ((YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWECHATSPORT) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASALIIOT)) && !Constant.isTechFeel()) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_ott_services), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 10));
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.me_my_device_more_settings_shutdown), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 19));
        setRecycleView();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        MeListAdapter meListAdapter = new MeListAdapter(R.layout.item_me_list, 2, this.context);
        this.mMeListAdapter = meListAdapter;
        meListAdapter.addData((Collection) this.mMeListBean);
        this.mRecyclerView.setAdapter(this.mMeListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mMeListAdapter.setOnItemClickListener(new MeListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.1
            @Override // com.yucheng.smarthealthpro.me.adapter.MeListAdapter.OnItemClickListener
            public void onClick(MeListBean hisSearch, int position) {
                int dataType = hisSearch.getDataType();
                if (dataType == 16) {
                    MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MeVisitingCardActivity.class));
                    return;
                }
                if (dataType != 19) {
                    switch (dataType) {
                        case 2:
                            MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MeDndModeActivity.class));
                            break;
                        case 3:
                            MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MeAlarmClockActivity.class));
                            break;
                        case 4:
                            if (((Boolean) SharedPreferencesUtils.get(MeMoreSettingsActivity.this.context, "is_menstrual_setting", false)).booleanValue()) {
                                MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MePeriodActivity.class));
                                break;
                            } else {
                                MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MePeriodSettingActivity.class));
                                break;
                            }
                        case 5:
                            if (PermissionUtil.openCameraPermission(MeMoreSettingsActivity.this.context) && PermissionUtil.openSDCardPermission(MeMoreSettingsActivity.this.context)) {
                                MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) CameraActivity.class));
                                YCBTClient.appControlTakePhoto(1, null);
                                break;
                            }
                            break;
                        case 6:
                            MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MeUnitSettingActivity.class));
                            break;
                        case 7:
                            MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MeShowSettingActivity.class));
                            break;
                        case 8:
                            if (!PermissionUtil.isNotificationEnable(MeMoreSettingsActivity.this.context)) {
                                PermissionUtil.openNotificationSetting(MeMoreSettingsActivity.this.context);
                                break;
                            } else {
                                MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) MePushMessageActivity.class));
                                break;
                            }
                        case 9:
                            if (YCBTClient.getChipScheme() == 3 && !YCBTClient.getAuthPass()) {
                                Toast.makeText(MeMoreSettingsActivity.this.getApplication(), MeMoreSettingsActivity.this.getString(R.string.jl_authing), 0).show();
                                break;
                            } else {
                                MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) ContactsActivity.class));
                                break;
                            }
                            break;
                        case 10:
                            MeMoreSettingsActivity.this.startActivity(new Intent(MeMoreSettingsActivity.this.context, (Class<?>) ThirdPartyServiceActivity.class).addFlags(AMapEngineUtils.MAX_P20_WIDTH));
                            break;
                    }
                    return;
                }
                MeMoreSettingsActivity.this.shutDown();
            }
        });
    }

    private void initDialog() {
        CommonDialog commonDialog = new CommonDialog(this.context);
        this.dialog = commonDialog;
        commonDialog.setMessage(getString(R.string.me_my_device_more_settings_dialog_message)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new AnonymousClass2()).show();
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity$2, reason: invalid class name */
    class AnonymousClass2 implements CommonDialog.OnClickBottomListener {
        AnonymousClass2() {
        }

        @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
        public void onConfirmClick() {
            MeMoreSettingsActivity.this.dialog.dismiss();
            Toast.makeText(MeMoreSettingsActivity.this.context, MeMoreSettingsActivity.this.getString(R.string.setup_successful), 0).show();
            MeMoreSettingsActivity.this.handler.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    MeMoreSettingsActivity.this.finish();
                }
            }, 2000L);
            YCBTClient.settingRestoreFactory(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.2.2
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    if (code == 0) {
                        MeMoreSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.2.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(MeMoreSettingsActivity.this.context, MeMoreSettingsActivity.this.getString(R.string.setup_successful), 0).show();
                                MeMoreSettingsActivity.this.dialog.dismiss();
                            }
                        });
                    } else {
                        MeMoreSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.2.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(MeMoreSettingsActivity.this.context, MeMoreSettingsActivity.this.getString(R.string.health_set_failed), 0).show();
                                MeMoreSettingsActivity.this.dialog.dismiss();
                            }
                        });
                    }
                }
            });
        }

        @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
        public void onCancelClick() {
            MeMoreSettingsActivity.this.dialog.dismiss();
        }

        @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
        public void onEditTextConfirmClick(String mEditText) {
            MeMoreSettingsActivity.this.dialog.dismiss();
        }
    }

    public void onViewClicked(View view) {
        initDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutDown() {
        CommonDialog commonDialog = new CommonDialog(this.context);
        this.shutDowndialog = commonDialog;
        commonDialog.setMessage(getString(R.string.me_my_device_more_settings_shutdown_dialog_message)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new AnonymousClass3()).show();
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity$3, reason: invalid class name */
    class AnonymousClass3 implements CommonDialog.OnClickBottomListener {
        AnonymousClass3() {
        }

        @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
        public void onConfirmClick() {
            YCBTClient.appShutDown(1, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.3.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    if (code == 0) {
                        MeMoreSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.3.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(MeMoreSettingsActivity.this.context, MeMoreSettingsActivity.this.getString(R.string.setup_successful), 0).show();
                                MeMoreSettingsActivity.this.shutDowndialog.dismiss();
                            }
                        });
                    } else {
                        MeMoreSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeMoreSettingsActivity.3.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(MeMoreSettingsActivity.this.context, MeMoreSettingsActivity.this.getString(R.string.health_set_failed), 0).show();
                                MeMoreSettingsActivity.this.shutDowndialog.dismiss();
                            }
                        });
                    }
                }
            });
        }

        @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
        public void onCancelClick() {
            MeMoreSettingsActivity.this.shutDowndialog.dismiss();
        }

        @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
        public void onEditTextConfirmClick(String mEditText) {
            MeMoreSettingsActivity.this.shutDowndialog.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (messageEvent.belState != 0) {
            return;
        }
        finish();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (getProgressDialog() != null) {
            getProgressDialog().dismiss();
        }
        CommonDialog commonDialog = this.dialog;
        if (commonDialog != null) {
            commonDialog.dismiss();
        }
        CommonDialog commonDialog2 = this.shutDowndialog;
        if (commonDialog2 != null) {
            commonDialog2.dismiss();
        }
    }
}
