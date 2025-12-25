package com.yucheng.smarthealthpro.login.normal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAiInputAccountBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.login.normal.util.CheckMobileAndEmailUtil;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class InputAccountActivity extends BaseVbActivity<ActivityAiInputAccountBinding> {
    private CheckBox cb;
    private ImageView clear_account;
    private EditText ed_account;
    private String title;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
    }

    private void init() {
        if (getIntent() != null) {
            this.title = getIntent().getStringExtra("title");
        }
        String str = this.title;
        if (str == null) {
            str = "";
        }
        changeTitle(str);
        showBack();
        this.ed_account = (EditText) findViewById(R.id.input_account_ed_account);
        this.clear_account = (ImageView) findViewById(R.id.input_account_iv_clear_account);
        this.cb = (CheckBox) findViewById(R.id.login_cb_protocal);
    }

    private void initData() {
        findViewById(R.id.input_account_btn_get_code).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.login.normal.InputAccountActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (InputAccountActivity.this.checkedAccount() && InputAccountActivity.this.isReadProtocol()) {
                    InputAccountActivity.this.getCode();
                }
            }
        });
        findViewById(R.id.login_tv_user_agreement).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.login.normal.InputAccountActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                InputAccountActivity.this.startActivity(new Intent(InputAccountActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", InputAccountActivity.this.getString(R.string.webview_user_agreement)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + InputAccountActivity.this.getString(R.string.app_name) + "/user_agreement_smart_" + (InputAccountActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html"));
            }
        });
        findViewById(R.id.login_tv_protocal).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.login.normal.InputAccountActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                InputAccountActivity.this.startActivity(new Intent(InputAccountActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", InputAccountActivity.this.getString(R.string.webview_protocal)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + InputAccountActivity.this.getString(R.string.app_name) + "/privacy_policy_smart_" + (InputAccountActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html"));
            }
        });
        this.ed_account.addTextChangedListener(new TextWatcher() { // from class: com.yucheng.smarthealthpro.login.normal.InputAccountActivity.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    InputAccountActivity.this.clear_account.setVisibility(0);
                } else {
                    InputAccountActivity.this.clear_account.setVisibility(8);
                }
            }
        });
        this.clear_account.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.login.normal.InputAccountActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                InputAccountActivity.this.ed_account.setText("");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkedAccount() {
        String strTrim = this.ed_account.getEditableText().toString().trim();
        if (strTrim.equals("")) {
            ToastUtil.getInstance(this).toast(getString(R.string.login_username_cannot_be_null));
            return false;
        }
        if (CheckMobileAndEmailUtil.checkALL(strTrim)) {
            return true;
        }
        ToastUtil.getInstance(this).toast(getString(R.string.login_please_input_right_data));
        return false;
    }

    public void getCode() {
        if (this.title == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("username", this.ed_account.getEditableText().toString().trim());
        map.put("title", getString(R.string.app_name));
        map.put("language", getString(R.string.lan).toLowerCase());
        map.put("smsType", this.title.equals(getString(R.string.reset_title)) ? "3" : "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.blvcode, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.normal.InputAccountActivity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result == null) {
                    return;
                }
                InputAccountActivity inputAccountActivity = InputAccountActivity.this;
                Toast.makeText(inputAccountActivity, inputAccountActivity.getString(R.string.input_account_send_code_success), 0).show();
                InputAccountActivity.this.startActivity(new Intent(InputAccountActivity.this, (Class<?>) InputCodeActivity.class).putExtra("title", InputAccountActivity.this.title).putExtra("account", InputAccountActivity.this.ed_account.getEditableText().toString().trim()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReadProtocol() {
        boolean zIsChecked = this.cb.isChecked();
        if (!zIsChecked) {
            Toast.makeText(this, getString(R.string.login_please_read_protocol), 0).show();
        }
        return zIsChecked;
    }
}
