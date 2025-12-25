package com.yucheng.smarthealthpro.login.normal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.google.gson.Gson;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAiRegisterBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.login.normal.bean.UserBean;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.perfect.UserInfoActivity;
import com.yucheng.smarthealthpro.utils.CommonAction;
import com.yucheng.smarthealthpro.view.CleanableEditText;
import java.util.HashMap;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes5.dex */
public class RegisterActivity extends BaseVbActivity<ActivityAiRegisterBinding> {
    private CheckBox cb;
    private String code;
    private CleanableEditText ed_confirm_password;
    private CleanableEditText ed_password;
    private boolean is_show = false;
    private boolean is_show2 = false;
    private ProgressDialog progressDialog;
    private String s_account;
    private TextView tv_account;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
        setListener();
    }

    private void init() {
        this.tv_account = (TextView) findViewById(R.id.register_tv_account);
        this.ed_password = (CleanableEditText) findViewById(R.id.register_ed_password);
        this.ed_confirm_password = (CleanableEditText) findViewById(R.id.register_ed_confirm_password);
        this.cb = (CheckBox) findViewById(R.id.register_cb_protocal);
        this.ed_password.setCustomDeletedCallback(new CleanableEditText.CustomDeletedCallback() { // from class: com.yucheng.smarthealthpro.login.normal.RegisterActivity.1
            @Override // com.yucheng.smarthealthpro.view.CleanableEditText.CustomDeletedCallback
            public void onDeleted(CleanableEditText cleanableEditText) {
                Log.i("AAAAAA", "==密码==");
            }
        });
        this.ed_confirm_password.setCustomDeletedCallback(new CleanableEditText.CustomDeletedCallback() { // from class: com.yucheng.smarthealthpro.login.normal.RegisterActivity.2
            @Override // com.yucheng.smarthealthpro.view.CleanableEditText.CustomDeletedCallback
            public void onDeleted(CleanableEditText cleanableEditText) {
                Log.i("AAAAAA", "==确认密码==");
            }
        });
    }

    private void initData() {
        changeTitle(getString(R.string.register_title));
        showBack();
        if (getIntent() != null) {
            this.s_account = getIntent().getStringExtra("account");
            this.code = getIntent().getStringExtra("code");
        }
        String str = this.s_account;
        if (str == null || this.code == null) {
            finish();
        } else {
            this.tv_account.setText(str);
        }
    }

    private void setListener() {
        findViewById(R.id.is_show_password).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.is_show_password2).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.register_btn_register).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.tvUserAgreement).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.tvPrivacyPolicy).setOnClickListener(new OnClickListenerImpl());
        this.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.login.normal.RegisterActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            }
        });
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.register_btn_register) {
                RegisterActivity.this.checkContent();
                return;
            }
            if (view.getId() == R.id.tvUserAgreement) {
                RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", RegisterActivity.this.getString(R.string.webview_user_agreement)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + RegisterActivity.this.getString(R.string.app_name) + "/user_agreement_smart_" + (RegisterActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html"));
                return;
            }
            if (view.getId() == R.id.tvPrivacyPolicy) {
                RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", RegisterActivity.this.getString(R.string.webview_protocal)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + RegisterActivity.this.getString(R.string.app_name) + "/privacy_policy_smart_" + (RegisterActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html"));
                return;
            }
            if (view.getId() == R.id.is_show_password) {
                if (RegisterActivity.this.is_show) {
                    RegisterActivity.this.is_show = false;
                    ((ImageView) RegisterActivity.this.findViewById(R.id.is_show_password)).setImageResource(R.mipmap.login_icon_hind_pwd);
                    RegisterActivity.this.ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    return;
                } else {
                    RegisterActivity.this.is_show = true;
                    ((ImageView) RegisterActivity.this.findViewById(R.id.is_show_password)).setImageResource(R.mipmap.login_icon_show_pwd);
                    RegisterActivity.this.ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    return;
                }
            }
            if (view.getId() == R.id.is_show_password2) {
                if (RegisterActivity.this.is_show2) {
                    RegisterActivity.this.is_show2 = false;
                    ((ImageView) RegisterActivity.this.findViewById(R.id.is_show_password2)).setImageResource(R.mipmap.login_icon_hind_pwd);
                    RegisterActivity.this.ed_confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    RegisterActivity.this.is_show2 = true;
                    ((ImageView) RegisterActivity.this.findViewById(R.id.is_show_password2)).setImageResource(R.mipmap.login_icon_show_pwd);
                    RegisterActivity.this.ed_confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkContent() {
        if (this.cb.isChecked()) {
            String string = this.tv_account.getText().toString();
            if (string.trim().equals("")) {
                ToastUtil.getInstance(this).toast(getString(R.string.login_username_cannot_be_null));
                return;
            }
            String string2 = this.ed_password.getText().toString();
            String string3 = this.ed_confirm_password.getText().toString();
            if (string2.trim().equals("")) {
                ToastUtil.getInstance(this).toast(getString(R.string.login_password_cannot_be_null));
                return;
            }
            if (!string2.trim().equals(string3.trim())) {
                ToastUtil.getInstance(this).toast(getString(R.string.register_password_inconsistent));
                return;
            } else if (string2.length() < 8) {
                ToastUtil.getInstance(this).toast(getString(R.string.register_password_length_must_be_less_than_eight));
                return;
            } else {
                regAction(string, string2);
                return;
            }
        }
        ToastUtil.getInstance(this).toast(getString(R.string.login_please_read_protocol));
    }

    private boolean checkSimplePassword(String s_password) {
        boolean z = true;
        for (int i2 = 0; i2 < s_password.length(); i2++) {
            try {
                if (i2 > 0) {
                    if (Integer.parseInt(s_password.substring(i2 - 1, i2)) + 1 != Integer.parseInt(s_password.substring(i2, i2 + 1))) {
                        z = false;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                z = false;
            }
        }
        return z || s_password.matches(new StringBuilder().append(s_password.charAt(0)).append("{").append(s_password.length()).append(StringSubstitutor.DEFAULT_VAR_END).toString());
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    public void regAction(String s_account, String s_password) {
        this.progressDialog = ProgressDialog.show(this, getString(R.string.prompt), getString(R.string.is_registring), true, false);
        HashMap map = new HashMap();
        map.put("username", s_account);
        map.put("password", s_password);
        map.put("vcode", this.code);
        map.put(DeviceRequestsHelper.DEVICE_INFO_DEVICE, "2");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.register, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.normal.RegisterActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (RegisterActivity.this.progressDialog != null && RegisterActivity.this.progressDialog.isShowing()) {
                    RegisterActivity.this.progressDialog.dismiss();
                }
                if (result != null) {
                    try {
                        UserBean userBean = (UserBean) new Gson().fromJson(result, UserBean.class);
                        if (userBean == null || userBean.data == null) {
                            return;
                        }
                        userBean.data.nickname = RegisterActivity.this.getString(R.string.login_default_nick_name);
                        UserInfoUtil.setUserInfo(userBean, RegisterActivity.this);
                        RegisterActivity.this.goUserInfo(userBean.data.username);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    public void goUserInfo(String username) {
        Intent intent = new Intent(this, (Class<?>) UserInfoActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        CommonAction.getInstance().OutSign();
    }
}
