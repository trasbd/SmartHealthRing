package com.yucheng.smarthealthpro.me.setting.thirdservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import com.facebook.internal.ServerProtocol;
import com.facebook.login.widget.ToolTipPopup;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeAliServiceBinding;
import com.yucheng.smarthealthpro.dialog.MyDialog;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.NetResult;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class AliPartyServiceActivity extends BaseVbActivity<ActivityMeAliServiceBinding> {
    private static final int DISMISS = 1004;
    private static final int FAILED = 1002;
    private static final int SUCCESS = 1001;
    private static final int UPDATE = 1003;
    private MyDialog mLoading;
    private int count = 0;
    Gson gson = new Gson();
    boolean isStartActivate = false;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (AliPartyServiceActivity.this.isFinishing()) {
            }
            switch (msg.what) {
                case 1001:
                    if (AliPartyServiceActivity.this.state == 1) {
                        ToastUtil.getInstance(AliPartyServiceActivity.this).toast(AliPartyServiceActivity.this.getString(R.string.ali_activate_success));
                        AliPartyServiceActivity.this.update();
                        break;
                    } else if (AliPartyServiceActivity.this.count < 20) {
                        AliPartyServiceActivity.this.getState();
                        AliPartyServiceActivity aliPartyServiceActivity = AliPartyServiceActivity.this;
                        aliPartyServiceActivity.setDialogTitle(aliPartyServiceActivity.getString(R.string.ali_activating));
                        AliPartyServiceActivity.this.handler.sendEmptyMessageDelayed(1001, 3000L);
                        AliPartyServiceActivity.this.count++;
                        break;
                    } else {
                        AliPartyServiceActivity.this.handler.removeMessages(1004);
                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1004);
                        ToastUtil.getInstance(AliPartyServiceActivity.this).toast(AliPartyServiceActivity.this.getString(R.string.ali_activate_failed));
                        break;
                    }
                case 1002:
                    ToastUtil.getInstance(AliPartyServiceActivity.this).toast(AliPartyServiceActivity.this.getString(R.string.ali_activate_failed));
                    break;
                case 1003:
                    AliPartyServiceActivity.this.update();
                    if (AliPartyServiceActivity.this.isStartActivate) {
                        YCBTClient.appShutDown(3, null);
                        final CommonDialog commonDialog = new CommonDialog(AliPartyServiceActivity.this.context);
                        commonDialog.setMessage(AliPartyServiceActivity.this.getString(R.string.alipay_activation_tip)).setTitle(AliPartyServiceActivity.this.getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity.1.1
                            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                            public void onConfirmClick() {
                                commonDialog.dismiss();
                                AliPartyServiceActivity.this.startActivity(new Intent(AliPartyServiceActivity.this, (Class<?>) MainActivity.class));
                                AliPartyServiceActivity.this.finish();
                            }

                            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                            public void onCancelClick() {
                                commonDialog.dismiss();
                                AliPartyServiceActivity.this.startActivity(new Intent(AliPartyServiceActivity.this, (Class<?>) MainActivity.class));
                                AliPartyServiceActivity.this.finish();
                            }

                            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                            public void onEditTextConfirmClick(String mEditText) {
                                commonDialog.dismiss();
                                AliPartyServiceActivity.this.startActivity(new Intent(AliPartyServiceActivity.this, (Class<?>) MainActivity.class));
                                AliPartyServiceActivity.this.finish();
                            }
                        }).show();
                        break;
                    }
                    break;
                case 1004:
                    if (AliPartyServiceActivity.this.mLoading.isShowing()) {
                        AliPartyServiceActivity.this.mLoading.dismiss();
                        break;
                    }
                    break;
            }
        }
    };
    private int state = 0;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        changeTitle(getString(R.string.ali_title));
        showBack();
        MyDialog myDialog = (MyDialog) DialogUtils.createLoadingDialog(this);
        this.mLoading = myDialog;
        myDialog.show();
        setDialogTitle(getString(R.string.ali_activate_get_state));
        getState();
        ((ActivityMeAliServiceBinding) this.mBinding).tvActivate.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onView(view);
            }
        }));
    }

    public void onView(View view) {
        if (view.getId() == R.id.tv_activate) {
            this.mLoading.show();
            setDialogTitle(getString(R.string.ali_activate_connect_aili_service));
            this.handler.sendEmptyMessageDelayed(1004, 60000L);
            YCBTClient.checkALiIOTKit(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity.2
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    if (code != 0) {
                        AliPartyServiceActivity.this.handler.removeMessages(1004);
                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1004);
                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1002);
                    } else if (ratio == 10.0f) {
                        AliPartyServiceActivity.this.state = 1;
                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1001);
                    } else {
                        AliPartyServiceActivity.this.count = 0;
                        AliPartyServiceActivity.this.handler.sendEmptyMessageDelayed(1001, 25000L);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialogTitle(String title) {
        MyDialog myDialog = this.mLoading;
        if (myDialog != null) {
            myDialog.setTitle(title);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getState() {
        this.handler.sendEmptyMessageDelayed(1004, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
        YCBTClient.getALiIOTActivationState(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                AliPartyServiceActivity.this.handler.removeMessages(1004);
                AliPartyServiceActivity.this.handler.sendEmptyMessage(1004);
                if (i2 != 0 || hashMap == null) {
                    return;
                }
                try {
                    AliPartyServiceActivity.this.state = ((Integer) hashMap.get(ServerProtocol.DIALOG_PARAM_STATE)).intValue();
                    if (AliPartyServiceActivity.this.state == 1) {
                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1003);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        ((ActivityMeAliServiceBinding) this.mBinding).tvActivate.setText(R.string.ali_activated);
        ((ActivityMeAliServiceBinding) this.mBinding).tvActivate.setClickable(false);
    }

    public void check() {
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, ""));
        HttpUtils.getInstance().getMsgAsynHttp(this, Constants.GetOneUnusedDevice, map, new AnonymousClass4());
    }

    /* renamed from: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity$4, reason: invalid class name */
    class AnonymousClass4 implements HttpUtils.HttpCallback {
        AnonymousClass4() {
        }

        @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
        public void onSuccess(String result) {
            Logger.d(result);
            String str = ((NetResult) AliPartyServiceActivity.this.gson.fromJson(result, NetResult.class)).data;
            YCBTClient.settingDeviceMac(str, new AnonymousClass1(str));
        }

        /* renamed from: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity$4$1, reason: invalid class name */
        class AnonymousClass1 implements BleDataResponse {
            final /* synthetic */ String val$mac;

            AnonymousClass1(final String val$mac) {
                this.val$mac = val$mac;
            }

            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 == 0) {
                    HashMap map = new HashMap();
                    map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(AliPartyServiceActivity.this.context, Constant.SpConstKey.TOKEN, ""));
                    map.put("mac", this.val$mac);
                    HttpUtils.getInstance().postMsgAsynHttp(AliPartyServiceActivity.this.context, Constants.ConfirmDevice, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity.4.1.1
                        @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                        public void onSuccess(String result) {
                            AliPartyServiceActivity.this.isStartActivate = true;
                            YCBTClient.checkALiIOTKit(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity.4.1.1.1
                                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                                    if (code != 0) {
                                        AliPartyServiceActivity.this.handler.removeMessages(1004);
                                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1004);
                                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1002);
                                    } else if (ratio == 10.0f) {
                                        AliPartyServiceActivity.this.state = 1;
                                        AliPartyServiceActivity.this.handler.sendEmptyMessage(1001);
                                    } else {
                                        AliPartyServiceActivity.this.count = 0;
                                        AliPartyServiceActivity.this.handler.sendEmptyMessageDelayed(1001, 25000L);
                                    }
                                }
                            });
                        }
                    });
                    return;
                }
                HashMap map2 = new HashMap();
                map2.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(AliPartyServiceActivity.this.context, Constant.SpConstKey.TOKEN, ""));
                map2.put("mac", this.val$mac);
                HttpUtils.getInstance().postMsgAsynHttp(AliPartyServiceActivity.this.context, Constants.CancelDevice, map2, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.AliPartyServiceActivity.4.1.2
                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                    public void onSuccess(String result) {
                        Logger.d(result);
                    }
                });
            }
        }
    }
}
