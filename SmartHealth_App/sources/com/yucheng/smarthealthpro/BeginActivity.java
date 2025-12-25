package com.yucheng.smarthealthpro;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAiBeginBinding;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.login.LoginActivity;
import com.yucheng.smarthealthpro.login.normal.WebViewActivity;
import com.yucheng.smarthealthpro.perfect.UserInfoActivity;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.CustomException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes3.dex */
public class BeginActivity extends BaseVbActivity<ActivityAiBeginBinding> {
    List<Integer> NotiList = new ArrayList();
    Handler handler = new Handler(Looper.getMainLooper());
    int maxFinishTime = 3000;
    int currentTime = 0;
    Runnable checkInitFinish = new Runnable() { // from class: com.yucheng.smarthealthpro.BeginActivity.1
        @Override // java.lang.Runnable
        public void run() {
            Log.d("ltf", "MyApplication.isInitFinish = " + MyApplication.isInitFinish + "  currentTime=" + BeginActivity.this.currentTime);
            if (MyApplication.isInitFinish || BeginActivity.this.currentTime >= BeginActivity.this.maxFinishTime) {
                BeginActivity.this.togo();
                if (MyApplication.isInitFinish) {
                    return;
                }
                CrashReport.postCatchedException(new CustomException("初始化超时：" + MyApplication.initStep));
                return;
            }
            BeginActivity.this.currentTime += 500;
            BeginActivity.this.handler.postDelayed(BeginActivity.this.checkInitFinish, 500L);
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;
        super.onCreate(savedInstanceState);
        if (Constant.isMymon()) {
            ((ImageView) findViewById(R.id.begin_iv)).setImageResource(R.mipmap.begin_mymom_icon);
        }
        if (Constant.isBNRHealth()) {
            ((TextView) findViewById(R.id.begin_tv)).setVisibility(0);
        } else {
            ((TextView) findViewById(R.id.begin_tv)).setVisibility(8);
        }
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
        if (!isTaskRoot() && (intent = getIntent()) != null) {
            String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(action)) {
                finish();
                return;
            }
        }
        next();
    }

    public void next() {
        if (!((Boolean) SharedPreferencesUtils.get(this, Constant.SpConstKey.IS_FIRST_DOWN, false)).booleanValue()) {
            showProtocolDialog();
        } else {
            this.currentTime = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.handler.postDelayed(this.checkInitFinish, ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSdk() {
        MyApplication.checkInitSdkAfterAgree(this);
    }

    private void showProtocolDialog() {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_view, (ViewGroup) null);
        setSpaning((TextView) viewInflate.findViewById(R.id.dialog_content));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(this.context, R.style.loading_dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
        if (alertDialogCreate.getWindow() == null) {
            return;
        }
        alertDialogCreate.getWindow().setContentView(viewInflate);
        viewInflate.findViewById(R.id.dialog_done).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.BeginActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialogCreate.dismiss();
                SharedPreferencesUtils.put(BeginActivity.this, Constant.SpConstKey.IS_FIRST_DOWN, true);
                BeginActivity.this.initSdk();
                BeginActivity.this.togo();
            }
        });
        viewInflate.findViewById(R.id.dialog_cancle).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.BeginActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialogCreate.dismiss();
                BeginActivity.this.finish();
            }
        });
    }

    private void setSpaning(TextView tv) {
        SpannableString spannableString = new SpannableString(getString(R.string.begin_show_protocol_content));
        String string = getString(R.string.webview_user_agreement);
        String string2 = getString(R.string.webview_protocal);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.yucheng.smarthealthpro.BeginActivity.4
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                BeginActivity.this.startActivity(new Intent(BeginActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", BeginActivity.this.getString(R.string.webview_user_agreement)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + BeginActivity.this.getString(R.string.app_name) + "/user_agreement_smart_" + (BeginActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html"));
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: com.yucheng.smarthealthpro.BeginActivity.5
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                BeginActivity.this.startActivity(new Intent(BeginActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", BeginActivity.this.getString(R.string.webview_protocal)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + BeginActivity.this.getString(R.string.app_name) + "/privacy_policy_smart_" + (BeginActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html"));
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };
        int iLastIndexOf = spannableString.toString().lastIndexOf(string);
        int iLastIndexOf2 = spannableString.toString().lastIndexOf(string2);
        if (iLastIndexOf != -1) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0099EE")), iLastIndexOf, string.length() + iLastIndexOf, 17);
            spannableString.setSpan(clickableSpan, iLastIndexOf, string.length() + iLastIndexOf, 17);
        }
        if (iLastIndexOf2 != -1) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0099EE")), iLastIndexOf2, string2.length() + iLastIndexOf2, 17);
            spannableString.setSpan(clickableSpan2, iLastIndexOf2, string2.length() + iLastIndexOf2, 17);
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(spannableString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togo() {
        String string = SharedPreferencesUtils.get(this, Constant.DEBUG_VERSION, "").toString();
        boolean z = (TextUtils.isEmpty(string) || BooleanUtils.FALSE.equals(string)) ? false : true;
        SharedPreferencesUtils.put(this, Constant.DEBUG_VERSION, BooleanUtils.FALSE);
        if (Constant.isTechFeel()) {
            Constants.isTechFeel = true;
            goMain();
        } else {
            if (((Boolean) SharedPreferencesUtils.get(this, Constant.SpConstKey.IS_LOGIN, false)).booleanValue() && !z) {
                if (((Integer) SharedPreferencesUtils.get(this, Constant.SpConstKey.INFO_FIRST_CHANGE, 0)).intValue() == 0) {
                    goMain();
                    return;
                } else {
                    goUserInfo((String) SharedPreferencesUtils.get(this, Constant.SpConstKey.USER_NAME, ""), (String) SharedPreferencesUtils.get(this, Constant.SpConstKey.NICK_NAME, ""));
                    return;
                }
            }
            goLogin();
        }
    }

    public void goLogin() {
        startActivity(new Intent(this, (Class<?>) LoginActivity.class));
        finish();
    }

    public void goMain() {
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
        finish();
    }

    public void goUserInfo(String userName, String nickName) {
        Intent intent = new Intent(this, (Class<?>) UserInfoActivity.class);
        intent.putExtra(Constant.SpConstKey.USER_NAME, userName);
        intent.putExtra("nickName", nickName);
        startActivity(intent);
        finish();
    }
}
