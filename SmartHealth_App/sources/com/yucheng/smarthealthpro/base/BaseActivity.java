package com.yucheng.smarthealthpro.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.jieli.jl_bt_ota.constant.BluetoothConstant;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.utils.CommonAction;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class BaseActivity extends com.yucheng.smarthealthpro.framework.BaseActivity {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    ActivityResultLauncher<Intent> launcher;
    public ProgressDialog progressDialog;
    int requestCode;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.base.BaseActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            return false;
        }
    });
    long lastClickTime = 0;
    int maxCheckConnectTime = BluetoothConstant.PAIR_OR_UNPAIR_TIMEOUT;
    int currentTime = 0;
    Runnable reconnectCheck = new Runnable() { // from class: com.yucheng.smarthealthpro.base.BaseActivity.3
        @Override // java.lang.Runnable
        public void run() {
            boolean z = YCBTClient.connectState() == 10;
            if (BaseActivity.this.currentTime >= BaseActivity.this.maxCheckConnectTime) {
                BaseActivity.this.onConnectCheck(z);
            } else {
                if (z) {
                    BaseActivity.this.onConnectCheck(true);
                    return;
                }
                BaseActivity.this.currentTime += 1000;
                BaseActivity.this.handler.postDelayed(BaseActivity.this.reconnectCheck, 1000L);
            }
        }
    };

    public Context getActivity() {
        return this;
    }

    public void onActivityResult(ActivityResult result, int requestCode) {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonAction.getInstance().addActivity(this);
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.yucheng.smarthealthpro.base.BaseActivity.2
            @Override // androidx.activity.result.ActivityResultCallback
            public void onActivityResult(ActivityResult o) {
                if (o != null) {
                    BaseActivity baseActivity = BaseActivity.this;
                    baseActivity.onActivityResult(o, baseActivity.requestCode);
                }
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (YCBTClient.connectState() == 10) {
            YCBTClient.stopScanBle();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        CommonAction.getInstance().subActivity(this);
        super.onDestroy();
    }

    public static class ActivityCollector {
        public static List<Activity> activities = new ArrayList();

        public static void addActivity(Activity activity) {
            activities.add(activity);
        }

        public static void removeActivity(Activity activity) {
            activities.remove(activity);
        }

        public static void finishAll() {
            for (Activity activity : activities) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }

    public void showProgressDialog(String text) {
        dismissProgressDialog();
        ProgressDialog progressDialogShow = ProgressDialog.show(this.context, getString(R.string.prompt), text, true, false);
        this.progressDialog = progressDialogShow;
        try {
            Window window = progressDialogShow.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.95d);
            attributes.gravity = 17;
            window.setAttributes(attributes);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    public void showProgressDialog(int text) {
        ProgressDialog progressDialogShow = ProgressDialog.show(this.context, getString(R.string.prompt), getString(text), true, false);
        this.progressDialog = progressDialogShow;
        try {
            Window window = progressDialogShow.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.95d);
            attributes.gravity = 17;
            window.setAttributes(attributes);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    public void showProgressDialog(int text, boolean cancelable) {
        ProgressDialog progressDialogShow = ProgressDialog.show(this.context, getString(R.string.prompt), getString(text), true, cancelable);
        this.progressDialog = progressDialogShow;
        try {
            Window window = progressDialogShow.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.95d);
            attributes.gravity = 17;
            window.setAttributes(attributes);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        ProgressDialog progressDialog;
        if (isDestroyed() || (progressDialog = this.progressDialog) == null) {
            return;
        }
        progressDialog.dismiss();
        this.progressDialog = null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtils.resetLanguage(this);
    }

    protected void hideBottomUIMenu() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.systemUiVisibility = 2050;
        window.setAttributes(attributes);
    }

    public boolean checkCanClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastClickTime <= 1000) {
            return false;
        }
        this.lastClickTime = jCurrentTimeMillis;
        return true;
    }

    public void checkConnect() {
        this.currentTime = 5000;
        showProgressDialog(R.string.ecg_sync_data, true);
        this.handler.removeCallbacks(this.reconnectCheck);
        this.handler.postDelayed(this.reconnectCheck, 5000L);
    }

    public void checkConnect(int millis) {
        this.currentTime = millis;
        this.handler.removeCallbacks(this.reconnectCheck);
        this.handler.postDelayed(this.reconnectCheck, millis);
    }

    public void onConnectCheck(boolean isConnect) {
        dismissProgressDialog();
    }

    public void launchActivityForResult(Class<?> c2) {
        this.launcher.launch(new Intent(this, c2));
    }

    public void launchActivityForResult(int requestCode, Class<?> c2) {
        this.requestCode = requestCode;
        this.launcher.launch(new Intent(this, c2));
    }

    public void launchActivityForResult(Intent intent, int requestCode) {
        this.requestCode = requestCode;
        this.launcher.launch(intent);
    }

    public void showToast(String text) {
        ToastUtil.getInstance(this).toast(text);
    }

    public void showToast(int textId) throws Resources.NotFoundException {
        ToastUtil.getInstance(this).toast(textId);
    }
}
