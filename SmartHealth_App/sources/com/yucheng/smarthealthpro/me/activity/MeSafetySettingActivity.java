package com.yucheng.smarthealthpro.me.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeSafetysettingBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.login.LoginActivity;
import com.yucheng.smarthealthpro.login.normal.InputCodeActivity;
import com.yucheng.smarthealthpro.perfect.LanguageActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.CommonAction;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MeSafetySettingActivity extends BaseVbActivity<ActivityMeSafetysettingBinding> {
    LinearLayout l_destroyAccount;
    LinearLayout llExitLogin;
    RelativeLayout rlPermissionSetting;
    RelativeLayout rlResetPasswords;
    private String userName;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.rlResetPasswords = ((ActivityMeSafetysettingBinding) this.mBinding).rlResetPasswords;
        this.rlPermissionSetting = ((ActivityMeSafetysettingBinding) this.mBinding).rlPermissionSetting;
        this.llExitLogin = ((ActivityMeSafetysettingBinding) this.mBinding).llExitLogin;
        this.l_destroyAccount = ((ActivityMeSafetysettingBinding) this.mBinding).destroyAccount;
        this.rlResetPasswords.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlPermissionSetting.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llExitLogin.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.l_destroyAccount.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_security_settings_title));
        showBack();
    }

    private void initData() {
        this.userName = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.USER_NAME, "");
        if (((Boolean) SharedPreferencesUtils.get(this, Constant.SpConstKey.IS_LOGIN, false)).booleanValue() && ((this.userName.startsWith("1") && this.userName.length() == 11) || (this.userName.contains("@") && this.userName.contains(".")))) {
            this.rlResetPasswords.setVisibility(0);
        }
        if (Tools.readLogin(this)) {
            return;
        }
        this.l_destroyAccount.setVisibility(8);
        this.llExitLogin.setVisibility(8);
    }

    private void initExitLoginDialog() {
        final CommonDialog commonDialog = new CommonDialog(this.context);
        commonDialog.setMessage(getString(R.string.me_security_settings_exit_the_login_dialog_message)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity.1
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
                MeSafetySettingActivity.this.loginOutDone();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void loginOutDone() {
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.IS_LOGIN, false);
        YCBTClient.disconnectBle();
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.IMAGE_PATH);
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.HEAD_IMG);
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.TOKEN);
        startActivity(new Intent(this.context, (Class<?>) LoginActivity.class).setFlags(335544320));
        CommonAction.getInstance().OutSign();
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.rl_reset_passwords) {
            getCode();
            return;
        }
        if (view.getId() == R.id.rl_permission_setting) {
            startActivity(new Intent(this.context, (Class<?>) PermissionActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_language_setting) {
            startActivity(new Intent(this.context, (Class<?>) LanguageActivity.class));
        } else if (view.getId() == R.id.ll_exit_login) {
            initExitLoginDialog();
        } else if (view.getId() == R.id.destroy_account) {
            showDestoryDialog();
        }
    }

    public void getCode() {
        startActivity(new Intent(this, (Class<?>) InputCodeActivity.class).putExtra("title", getString(R.string.reset_title)).putExtra("notGetCode", true).putExtra("account", this.userName));
    }

    private void showDestoryDialog() {
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_view, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.dialog_title)).setText(getString(R.string.destroy_account));
        ((TextView) viewInflate.findViewById(R.id.dialog_content)).setText(getString(R.string.destroy_account_content));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(this, R.style.loading_dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
        if (alertDialogCreate.getWindow() == null) {
            return;
        }
        alertDialogCreate.getWindow().setContentView(viewInflate);
        viewInflate.findViewById(R.id.dialog_done).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialogCreate.dismiss();
                MeSafetySettingActivity.this.destroyAccount();
            }
        });
        viewInflate.findViewById(R.id.dialog_cancle).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialogCreate.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyAccount() {
        final ProgressDialog progressDialogShow = ProgressDialog.show(this, getString(R.string.prompt), getString(R.string.destroying), true, false);
        HashMap map = new HashMap();
        map.put("key", "yucheng-delete-user-by-username");
        map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(this, Constant.SpConstKey.TOKEN, ""));
        map.put("username", (String) SharedPreferencesUtils.get(this, Constant.SpConstKey.USER_NAME, ""));
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.DESTROYACCOUNT, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeSafetySettingActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                ProgressDialog progressDialog = progressDialogShow;
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialogShow.dismiss();
                }
                try {
                    if (result != null) {
                        MeSafetySettingActivity meSafetySettingActivity = MeSafetySettingActivity.this;
                        Toast.makeText(meSafetySettingActivity, meSafetySettingActivity.getString(R.string.destroyed_success), 0).show();
                        MeSafetySettingActivity.this.loginOutDone();
                    } else {
                        MeSafetySettingActivity meSafetySettingActivity2 = MeSafetySettingActivity.this;
                        Toast.makeText(meSafetySettingActivity2, meSafetySettingActivity2.getString(R.string.destroyed_failed), 0).show();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}
