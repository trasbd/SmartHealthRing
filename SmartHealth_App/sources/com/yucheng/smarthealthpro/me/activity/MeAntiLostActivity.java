package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeAntilostBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MeAntiLostActivity extends BaseVbActivity<ActivityMeAntilostBinding> {
    Switch mSwitchAntiLost;
    View rlSwitch;
    TextView tvAntiLost;

    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mSwitchAntiLost = ((ActivityMeAntilostBinding) this.mBinding).switchAntiLost;
        this.tvAntiLost = ((ActivityMeAntilostBinding) this.mBinding).tvAntiLost;
        this.rlSwitch = ((ActivityMeAntilostBinding) this.mBinding).rlSwitch;
        this.tvAntiLost.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAntiLostActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_my_device_anti_lost_title));
        showBack();
        if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASANTILOST)) {
            this.rlSwitch.setVisibility(8);
        }
        if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASFINDDEVICE)) {
            this.rlSwitch.setVisibility(8);
        }
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IS_ANTI_LOSE, "");
        if (str != null && !str.isEmpty() && str.equals(Constant.SpConstValue.OPEN)) {
            this.mSwitchAntiLost.setChecked(true);
        } else {
            this.mSwitchAntiLost.setChecked(false);
        }
        this.mSwitchAntiLost.setOnCheckedChangeListener(new AnonymousClass1());
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeAntiLostActivity$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass1() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                YCBTClient.settingAntiLose(2, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeAntiLostActivity.1.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(final int code, float ratio, HashMap resultMap) {
                        MeAntiLostActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAntiLostActivity.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (code == 0) {
                                    SharedPreferencesUtils.put(MeAntiLostActivity.this.context, Constant.SpConstKey.IS_ANTI_LOSE, Constant.SpConstValue.OPEN);
                                    Toast.makeText(MeAntiLostActivity.this.context, R.string.setup_successful, 0).show();
                                } else {
                                    Toast.makeText(MeAntiLostActivity.this.context, R.string.health_set_failed, 0).show();
                                }
                            }
                        });
                    }
                });
            } else {
                YCBTClient.settingAntiLose(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeAntiLostActivity.1.2
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(final int code, float ratio, HashMap resultMap) {
                        MeAntiLostActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAntiLostActivity.1.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (code == 0) {
                                    SharedPreferencesUtils.put(MeAntiLostActivity.this.context, Constant.SpConstKey.IS_ANTI_LOSE, Constant.SpConstValue.CLOSE);
                                    Toast.makeText(MeAntiLostActivity.this.context, R.string.setup_successful, 0).show();
                                } else {
                                    Toast.makeText(MeAntiLostActivity.this.context, R.string.health_set_failed, 0).show();
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    public void onViewClicked(View view) {
        YCBTClient.appFindDevice(1, 5, 2, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeAntiLostActivity.2
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
            }
        });
    }
}
