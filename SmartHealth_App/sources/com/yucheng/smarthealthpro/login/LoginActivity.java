package com.yucheng.smarthealthpro.login;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.UploadFileTypeBean;
import com.yucheng.smarthealthpro.databinding.ActivityAiLoginBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.util.UUIDUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.login.google.GoogleLogin;
import com.yucheng.smarthealthpro.login.normal.InputAccountActivity;
import com.yucheng.smarthealthpro.login.normal.WebViewActivity;
import com.yucheng.smarthealthpro.login.normal.bean.UserBean;
import com.yucheng.smarthealthpro.login.normal.util.CheckMobileAndEmailUtil;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.login.qq.QQLogin;
import com.yucheng.smarthealthpro.login.wechat.WeChatLogin;
import com.yucheng.smarthealthpro.perfect.UserInfoActivity;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.PackageUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.TimeZoneUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.view.CleanableEditText;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.utils.SPUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class LoginActivity extends BaseVbActivity<ActivityAiLoginBinding> {
    private CheckBox cb;
    private ImageView clear_account;
    private CleanableEditText ed_account;
    private CleanableEditText ed_password;
    private boolean is_show = false;
    private MBroadcastReceiver mBroadcastReceiver;
    private Dialog progressDialog;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
        getProp();
    }

    private void getProp() {
        try {
            if (Constant.isSmartHealth() || Constant.isHealthRing() || Constant.isHealthWear()) {
                HttpUtils.getInstance().getMsgAsynHttp(this, Constants.Props, null, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.1
                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                    public void onSuccess(String result) {
                        try {
                            Log.d("HttpUtils", "upload=" + result);
                            SharedPreferencesUtils.put(LoginActivity.this.getApplicationContext(), Constant.SpConstKey.Props, result);
                            UploadFileTypeBean uploadFileTypeBean = (UploadFileTypeBean) new Gson().fromJson(result, UploadFileTypeBean.class);
                            String packageName = LoginActivity.this.context.getPackageName();
                            boolean z = false;
                            for (int i2 = 0; i2 < uploadFileTypeBean.getData().getAppBeta().Android.size(); i2++) {
                                UploadFileTypeBean.AppInfo appInfo = uploadFileTypeBean.getData().getAppBeta().Android.get(i2);
                                if (packageName.equals(appInfo.bundleID)) {
                                    z = appInfo.vestBag;
                                }
                            }
                            SharedPreferencesUtils.put(LoginActivity.this.getApplicationContext(), Constant.SpConstKey.vestBag, Boolean.valueOf(z));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            CrashReport.postCatchedException(e2);
        }
    }

    private void init() {
        changeTitle(getString(R.string.login_title));
        showRightText(getString(R.string.login_title_right_skip), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.2
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (Constant.isHealthWear()) {
                    if (LoginActivity.this.isReadProtocol()) {
                        LoginActivity.this.login();
                        return;
                    }
                    return;
                }
                LoginActivity.this.goMain();
            }
        });
        if (getString(R.string.lan).equals("cn")) {
            findViewById(R.id.login_in_china).setVisibility(0);
            findViewById(R.id.login_out_china).setVisibility(8);
        } else {
            findViewById(R.id.login_in_china).setVisibility(8);
            findViewById(R.id.login_out_china).setVisibility(0);
        }
        if (Constant.isMymon() || Constant.isBNRHealth() || Constant.isHealthRing() || Constant.isHealthband()) {
            findViewById(R.id.login_in_china).setVisibility(8);
            findViewById(R.id.login_out_china).setVisibility(8);
        }
        this.cb = (CheckBox) findViewById(R.id.login_cb_protocal);
        this.ed_account = (CleanableEditText) findViewById(R.id.login_ed_account);
        this.ed_password = (CleanableEditText) findViewById(R.id.login_ed_password);
        this.ed_account.setCustomDeletedCallback(new CleanableEditText.CustomDeletedCallback() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.3
            @Override // com.yucheng.smarthealthpro.view.CleanableEditText.CustomDeletedCallback
            public void onDeleted(CleanableEditText cleanableEditText) {
                Log.i("AAAAAA", "==用户名==");
            }
        });
        this.ed_password.setCustomDeletedCallback(new CleanableEditText.CustomDeletedCallback() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.4
            @Override // com.yucheng.smarthealthpro.view.CleanableEditText.CustomDeletedCallback
            public void onDeleted(CleanableEditText cleanableEditText) {
                Log.i("AAAAAA", "==密码==");
            }
        });
        this.mBroadcastReceiver = new MBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.login.qq");
        intentFilter.addAction("com.login.wechat");
        intentFilter.addAction("com.login.weibo");
        intentFilter.addAction("com.login.facebook");
        intentFilter.addAction("com.login.google");
        intentFilter.addAction("com.login.twitter");
        ActivityCompat.registerReceiver(this, this.mBroadcastReceiver, intentFilter, 2);
        HttpUtils.getInstance().putHeaderInfo(getString(R.string.app_name), PackageUtils.getVersionName(this.context), PackageUtils.getVersionCode(this.context) + "", YCBTClient.getBindDeviceVersion(), SPUtil.getHardwareType(), Tools.getDeviceType(this.context), getString(R.string.lan).toLowerCase(), TimeZoneUtils.getTimeZoneOffset());
    }

    private void initData() {
        findViewById(R.id.is_show_password).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_tv_no_account).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_btn_login).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_tv_register).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_tv_forgetPassword).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_tv_user_agreement).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_tv_protocal).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_qq).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_wechat).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_weibo).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_facebook).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_twitter).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.login_google).setOnClickListener(new OnClickListenerImpl());
        this.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b2) {
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    public void loginNet() {
        String strTrim = this.ed_account.getText().toString().trim();
        String strTrim2 = this.ed_password.getText().toString().trim();
        if (strTrim.equals("")) {
            ToastUtil.getInstance(this).toast(getString(R.string.login_username_cannot_be_null));
            return;
        }
        if (strTrim2.equals("")) {
            ToastUtil.getInstance(this).toast(getString(R.string.login_password_cannot_be_null));
            return;
        }
        if (!CheckMobileAndEmailUtil.checkALL(strTrim)) {
            ToastUtil.getInstance(this).toast(getString(R.string.login_please_input_right_data));
            return;
        }
        Dialog dialogCreateLoadingDialog = DialogUtils.createLoadingDialog(this, getString(R.string.is_logining));
        this.progressDialog = dialogCreateLoadingDialog;
        dialogCreateLoadingDialog.show();
        HashMap map = new HashMap();
        map.put("username", strTrim);
        map.put("password", strTrim2);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.login, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                LoginActivity.this.personData(result);
            }
        });
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

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intentPutExtra;
            if (view.getId() == R.id.login_tv_forgetPassword) {
                intentPutExtra = new Intent(LoginActivity.this, (Class<?>) InputAccountActivity.class).putExtra("title", LoginActivity.this.getString(R.string.reset_title));
            } else {
                if (view.getId() == R.id.login_tv_user_agreement) {
                    intentPutExtra = new Intent(LoginActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", LoginActivity.this.getString(R.string.webview_user_agreement)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + LoginActivity.this.getString(R.string.app_name) + "/user_agreement_smart_" + (LoginActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html");
                } else if (view.getId() == R.id.login_tv_protocal) {
                    intentPutExtra = new Intent(LoginActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", LoginActivity.this.getString(R.string.webview_protocal)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + LoginActivity.this.getString(R.string.app_name) + "/privacy_policy_smart_" + (LoginActivity.this.getString(R.string.lan).equals("cn") ? "cn" : "en") + ".html");
                } else if (view.getId() == R.id.login_tv_register) {
                    intentPutExtra = new Intent(LoginActivity.this, (Class<?>) InputAccountActivity.class).putExtra("title", LoginActivity.this.getString(R.string.register_title));
                } else {
                    if (view.getId() == R.id.is_show_password) {
                        if (LoginActivity.this.is_show) {
                            LoginActivity.this.is_show = false;
                            ((ImageView) LoginActivity.this.findViewById(R.id.is_show_password)).setImageResource(R.mipmap.login_icon_hind_pwd);
                            LoginActivity.this.ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            LoginActivity.this.is_show = true;
                            ((ImageView) LoginActivity.this.findViewById(R.id.is_show_password)).setImageResource(R.mipmap.login_icon_show_pwd);
                            LoginActivity.this.ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    } else if (view.getId() == R.id.login_btn_login) {
                        if (LoginActivity.this.isReadProtocol()) {
                            LoginActivity.this.loginNet();
                        }
                    } else if (view.getId() == R.id.login_tv_no_account) {
                        if (LoginActivity.this.isReadProtocol()) {
                            LoginActivity.this.login();
                        }
                    } else if (view.getId() == R.id.login_qq) {
                        if (LoginActivity.this.isReadProtocol()) {
                            QQLogin.getInstance(LoginActivity.this).qqLogin();
                        }
                    } else if (view.getId() == R.id.login_wechat) {
                        if (LoginActivity.this.isReadProtocol()) {
                            WeChatLogin.getInstance(LoginActivity.this).loginWeChat();
                        }
                    } else if (view.getId() != R.id.login_weibo) {
                        if (view.getId() == R.id.login_facebook) {
                            LoginActivity.this.isReadProtocol();
                        } else if (view.getId() != R.id.login_twitter && view.getId() == R.id.login_google && LoginActivity.this.isReadProtocol()) {
                            GoogleLogin googleLogin = GoogleLogin.getInstance(LoginActivity.this);
                            LoginActivity loginActivity = LoginActivity.this;
                            googleLogin.loginFirebase(loginActivity, loginActivity.getString(R.string.client_id), GoogleLogin.REQUEST_CODE_GG);
                        }
                    }
                    intentPutExtra = null;
                }
            }
            if (intentPutExtra != null) {
                LoginActivity.this.startActivity(intentPutExtra);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReadProtocol() {
        boolean zIsChecked = this.cb.isChecked();
        if (!zIsChecked) {
            Toast.makeText(this, getString(R.string.login_please_read_protocol), 0).show();
        }
        return zIsChecked;
    }

    public void login() {
        String strGenerateUUID = generateUUID();
        if (strGenerateUUID == null) {
            return;
        }
        Dialog dialogCreateLoadingDialog = DialogUtils.createLoadingDialog(this, getString(R.string.is_logining));
        this.progressDialog = dialogCreateLoadingDialog;
        dialogCreateLoadingDialog.show();
        HashMap map = new HashMap();
        map.put("username", strGenerateUUID);
        map.put("password", "12345678");
        HttpUtils.getInstance().postMsgAsynHttp(100, this, Constants.login, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.7
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null && result.equals("1")) {
                    LoginActivity.this.registerAccount();
                } else {
                    LoginActivity.this.personData(result);
                }
            }
        });
    }

    public String generateUUID() {
        long jCurrentTimeMillis = (System.currentTimeMillis() / 1000) / 3600;
        if ((ContextCompat.checkSelfPermission(this.context, Permission.READ_PHONE_STATE) != 0 || ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_WIFI_STATE") != 0) && jCurrentTimeMillis - ((Long) SharedPreferencesUtils.get(this.context, "uuid_time", 0L)).longValue() > 48) {
            PermissionUtil.showPermissionTipDialog(this, getString(R.string.prompt), getString(R.string.phone_permission_prompt_content), new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.8
                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickLeftButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                }

                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickRightButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                    UUIDUtils.generateUUID(LoginActivity.this);
                }
            });
            return null;
        }
        return UUIDUtils.generateUUID(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerAccount() {
        HashMap map = new HashMap();
        map.put("username", generateUUID());
        map.put("password", "12345678");
        map.put("vcode", "223355");
        map.put(DeviceRequestsHelper.DEVICE_INFO_DEVICE, com.tencent.connect.common.Constants.VIA_TO_TYPE_QZONE);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.register, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (LoginActivity.this.progressDialog != null && LoginActivity.this.progressDialog.isShowing()) {
                    LoginActivity.this.progressDialog.dismiss();
                }
                if (result != null) {
                    try {
                        UserBean userBean = (UserBean) new Gson().fromJson(result, UserBean.class);
                        if (userBean != null && userBean.data != null) {
                            userBean.data.nickname = LoginActivity.this.getString(R.string.login_default_nick_name);
                            UserInfoUtil.setUserInfo(userBean, LoginActivity.this);
                            LoginActivity.this.goUserInfo(userBean.data.username, userBean.data.nickname);
                            return;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                ToastUtil.getInstance(LoginActivity.this).toast(LoginActivity.this.getString(R.string.login_failed));
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11101) {
            QQLogin.getInstance(this).setCallBack(requestCode, resultCode, data);
        } else if (requestCode == 1435) {
            GoogleLogin.getInstance(this).callback(this, requestCode, data, GoogleLogin.REQUEST_CODE_GG);
        }
        Logger.d("chong------------requestCode==" + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void otherLogin(Map map) {
        if (map == null) {
            return;
        }
        Logger.w(new Gson().toJson(map), new Object[0]);
        Dialog dialogCreateLoadingDialog = DialogUtils.createLoadingDialog(this, getString(R.string.is_logining));
        this.progressDialog = dialogCreateLoadingDialog;
        dialogCreateLoadingDialog.show();
        HttpUtils.getInstance().postMsgAsynHttp(this, "https://web-api.ycaviation.com/smartam/oauth/login", map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.login.LoginActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                LoginActivity.this.personData(result);
            }
        });
    }

    private class MBroadcastReceiver extends BroadcastReceiver {
        private MBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HashMap map;
            if (intent == null || intent.getAction() == null) {
                return;
            }
            map = new HashMap();
            String action = intent.getAction();
            action.hashCode();
            switch (action) {
                case "com.login.qq":
                    map.put("loginType", com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
                    map.put("appId", QQLogin.APP_ID);
                    break;
                case "com.login.weibo":
                    map.put("loginType", "70");
                    break;
                case "com.login.facebook":
                    map.put("loginType", "30");
                    break;
                case "com.login.google":
                    map.put("loginType", "60");
                    break;
                case "com.login.twitter":
                    map.put("loginType", "40");
                    break;
                case "com.login.wechat":
                    map.put("loginType", "20");
                    break;
                default:
                    return;
            }
            if (intent.getStringExtra("accessToken") != null && intent.getStringExtra("openID") != null) {
                map.put("accessToken", intent.getStringExtra("accessToken"));
                map.put("openId", intent.getStringExtra("openID"));
                map.put("appName", "SmartHealth");
                map.put(DeviceRequestsHelper.DEVICE_INFO_DEVICE, "2");
                LoginActivity.this.otherLogin(map);
                return;
            }
            Toast.makeText(context, context.getString(R.string.login_failed), 0).show();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        unregisterReceiver(this.mBroadcastReceiver);
        clear();
        WeChatLogin.release();
        super.onDestroy();
    }

    private void clear() {
        if (QQLogin.qqLogin != null) {
            QQLogin.qqLogin.clear();
        }
        if (WeChatLogin.weChatLogin != null) {
            WeChatLogin.weChatLogin.clear();
        }
        if (GoogleLogin.googleLogin != null) {
            GoogleLogin.googleLogin.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void personData(String result) {
        Log.d("ltf", "result=" + result);
        Dialog dialog = this.progressDialog;
        if (dialog != null && dialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        if (result != null) {
            try {
                UserBean userBean = (UserBean) new Gson().fromJson(result, UserBean.class);
                if (userBean == null || userBean.data == null) {
                    return;
                }
                if (userBean.data.infoFirstChange == 1) {
                    userBean.data.nickname = getString(R.string.login_default_nick_name);
                    UserInfoUtil.setUserInfo(userBean, this);
                    goUserInfo(userBean.data.username, userBean.data.nickname);
                    return;
                }
                UserInfoUtil.setUserInfo(userBean, this);
                goMain();
            } catch (Exception e2) {
                e2.printStackTrace();
                ToastUtil.getInstance(this).toast(getString(R.string.login_failed));
            }
        }
    }
}
