package com.yucheng.smarthealthpro.login.normal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAiForwodBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.login.LoginActivity;
import com.yucheng.smarthealthpro.utils.CommonAction;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.view.CleanableEditText;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.HashMap;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes5.dex */
public class ForwodActivity extends BaseVbActivity<ActivityAiForwodBinding> {
    private Button btn_reset;
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
        this.tv_account = (TextView) findViewById(R.id.reset_tv_account);
        this.ed_password = (CleanableEditText) findViewById(R.id.reset_ed_password);
        this.ed_confirm_password = (CleanableEditText) findViewById(R.id.reset_ed_confirm_password);
        this.btn_reset = (Button) findViewById(R.id.reset_btn_reset);
        this.ed_password.setCustomDeletedCallback(new CleanableEditText.CustomDeletedCallback() { // from class: com.yucheng.smarthealthpro.login.normal.ForwodActivity.1
            @Override // com.yucheng.smarthealthpro.view.CleanableEditText.CustomDeletedCallback
            public void onDeleted(CleanableEditText cleanableEditText) {
                Log.i("AAAAAA", "==密码==");
            }
        });
        this.ed_confirm_password.setCustomDeletedCallback(new CleanableEditText.CustomDeletedCallback() { // from class: com.yucheng.smarthealthpro.login.normal.ForwodActivity.2
            @Override // com.yucheng.smarthealthpro.view.CleanableEditText.CustomDeletedCallback
            public void onDeleted(CleanableEditText cleanableEditText) {
                Log.i("AAAAAA", "==确认密码==");
            }
        });
    }

    private void initData() {
        changeTitle(getString(R.string.reset_title));
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
        this.btn_reset.setOnClickListener(new OnClickListenerImpl());
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.reset_btn_reset) {
                String string = ForwodActivity.this.tv_account.getText().toString();
                if (string.trim().equals("")) {
                    ToastUtil.getInstance(ForwodActivity.this).toast(ForwodActivity.this.getString(R.string.login_username_cannot_be_null));
                    return;
                }
                String string2 = ForwodActivity.this.ed_password.getText().toString();
                String string3 = ForwodActivity.this.ed_confirm_password.getText().toString();
                if (string2.trim().equals("")) {
                    ToastUtil.getInstance(ForwodActivity.this).toast(ForwodActivity.this.getString(R.string.login_password_cannot_be_null));
                    return;
                }
                if (!string2.trim().equals(string3.trim())) {
                    ToastUtil.getInstance(ForwodActivity.this).toast(ForwodActivity.this.getString(R.string.register_password_inconsistent));
                    return;
                } else if (string2.length() < 8) {
                    ToastUtil.getInstance(ForwodActivity.this).toast(ForwodActivity.this.getString(R.string.register_password_length_must_be_less_than_eight));
                    return;
                } else {
                    ForwodActivity.this.resetPassword(string, string2);
                    return;
                }
            }
            if (view.getId() == R.id.is_show_password) {
                if (ForwodActivity.this.is_show) {
                    ForwodActivity.this.is_show = false;
                    ((ImageView) ForwodActivity.this.findViewById(R.id.is_show_password)).setImageResource(R.mipmap.login_icon_hind_pwd);
                    ForwodActivity.this.ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    return;
                } else {
                    ForwodActivity.this.is_show = true;
                    ((ImageView) ForwodActivity.this.findViewById(R.id.is_show_password)).setImageResource(R.mipmap.login_icon_show_pwd);
                    ForwodActivity.this.ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    return;
                }
            }
            if (view.getId() == R.id.is_show_password2) {
                if (ForwodActivity.this.is_show2) {
                    ForwodActivity.this.is_show2 = false;
                    ((ImageView) ForwodActivity.this.findViewById(R.id.is_show_password2)).setImageResource(R.mipmap.login_icon_hind_pwd);
                    ForwodActivity.this.ed_confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ForwodActivity.this.is_show2 = true;
                    ((ImageView) ForwodActivity.this.findViewById(R.id.is_show_password2)).setImageResource(R.mipmap.login_icon_show_pwd);
                    ForwodActivity.this.ed_confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        }
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

    public void resetPassword(String s_account, String s_password) {
        this.progressDialog = ProgressDialog.show(this, getString(R.string.prompt), getString(R.string.is_reseting), true, false);
        HashMap map = new HashMap();
        map.put("username", s_account);
        map.put("password", s_password);
        map.put("code", this.code);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.changePwd, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.normal.ForwodActivity.3
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (ForwodActivity.this.progressDialog != null && ForwodActivity.this.progressDialog.isShowing()) {
                    ForwodActivity.this.progressDialog.dismiss();
                }
                if (result == null) {
                    return;
                }
                ToastUtil.getInstance(ForwodActivity.this).toast(ForwodActivity.this.getString(R.string.clock_modify_success));
                SharedPreferencesUtils.put(ForwodActivity.this.context, Constant.SpConstKey.IS_LOGIN, false);
                YCBTClient.disconnectBle();
                SharedPreferencesUtils.remove(ForwodActivity.this.context, Constant.SpConstKey.IMAGE_PATH);
                SharedPreferencesUtils.remove(ForwodActivity.this.context, Constant.SpConstKey.HEAD_IMG);
                SharedPreferencesUtils.remove(ForwodActivity.this.context, Constant.SpConstKey.TOKEN);
                ForwodActivity.this.startActivity(new Intent(ForwodActivity.this.context, (Class<?>) LoginActivity.class).setFlags(335544320));
                CommonAction.getInstance().OutSign();
            }
        });
    }
}
