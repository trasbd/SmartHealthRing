package com.yucheng.smarthealthpro.login.normal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAiInputCodeBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.login.normal.view.CodeView;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class InputCodeActivity extends BaseVbActivity<ActivityAiInputCodeBinding> {
    private Button btn_reget_code;
    private CodeView codeView;
    Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.login.normal.InputCodeActivity.6
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            if (msg.what == 100) {
                InputCodeActivity.this.btn_reget_code.setText(InputCodeActivity.this.getString(R.string.input_code_reget_code) + "(" + msg.obj.toString() + ")");
                InputCodeActivity.this.btn_reget_code.setEnabled(false);
            } else if (msg.what == 101) {
                InputCodeActivity.this.btn_reget_code.setEnabled(true);
                InputCodeActivity.this.btn_reget_code.setText(InputCodeActivity.this.getString(R.string.input_code_reget_code));
            }
            return false;
        }
    });
    private String s_account;
    private String title;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
    }

    private void initData() {
        this.btn_reget_code.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.login.normal.InputCodeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                InputCodeActivity.this.getCode();
            }
        });
        this.codeView.setOnInputListener(new CodeView.OnInputListener() { // from class: com.yucheng.smarthealthpro.login.normal.InputCodeActivity.2
            @Override // com.yucheng.smarthealthpro.login.normal.view.CodeView.OnInputListener
            public void onInput() {
            }

            @Override // com.yucheng.smarthealthpro.login.normal.view.CodeView.OnInputListener
            public void onSucess(String code) {
                if (InputCodeActivity.this.getString(R.string.reset_title).equals(InputCodeActivity.this.title)) {
                    InputCodeActivity.this.startActivity(new Intent(InputCodeActivity.this, (Class<?>) ForwodActivity.class).putExtra("account", InputCodeActivity.this.s_account).putExtra("code", code));
                } else {
                    InputCodeActivity.this.startActivity(new Intent(InputCodeActivity.this, (Class<?>) RegisterActivity.class).putExtra("account", InputCodeActivity.this.s_account).putExtra("code", code));
                }
            }
        });
        findViewById(R.id.input_code_cannt_get_code).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.login.normal.InputCodeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                InputCodeActivity.this.startActivity(new Intent(InputCodeActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", InputCodeActivity.this.getString(R.string.webview_about_code)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/about/verification_code_" + InputCodeActivity.this.getString(R.string.lan) + ".html"));
            }
        });
    }

    private void init() {
        this.codeView = (CodeView) findViewById(R.id.input_code_view);
        Button button = (Button) findViewById(R.id.input_code_btn_reget_code);
        this.btn_reget_code = button;
        button.setEnabled(false);
        if (getIntent() != null) {
            this.s_account = getIntent().getStringExtra("account");
            this.title = getIntent().getStringExtra("title");
        }
        if (this.title == null || this.s_account == null) {
            finish();
            return;
        }
        ((TextView) findViewById(R.id.input_code_note_account)).setText(getString(R.string.input_code_note_account_title) + this.s_account);
        changeTitle(this.title);
        showBack();
        waitCode();
    }

    public void getCode() {
        HashMap map = new HashMap();
        map.put("username", this.s_account);
        map.put("title", "SmartHealth");
        map.put("language", getString(R.string.lan).toLowerCase());
        map.put("smsType", getString(R.string.reset_title).equals(this.title) ? "3" : "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.blvcode, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.normal.InputCodeActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result == null) {
                    return;
                }
                InputCodeActivity.this.waitCode();
                InputCodeActivity inputCodeActivity = InputCodeActivity.this;
                Toast.makeText(inputCodeActivity, inputCodeActivity.getString(R.string.input_account_send_code_success), 0).show();
            }
        });
    }

    public void waitCode() {
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.login.normal.InputCodeActivity.5
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                int i2 = 60;
                while (i2 >= 0) {
                    Message message = new Message();
                    message.what = 100;
                    message.obj = i2 + "s";
                    InputCodeActivity.this.handler.sendMessage(message);
                    i2--;
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                InputCodeActivity.this.handler.sendEmptyMessage(101);
            }
        }).start();
    }
}
