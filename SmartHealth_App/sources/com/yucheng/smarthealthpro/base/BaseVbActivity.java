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
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.viewbinding.ViewBinding;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.jieli.jl_bt_ota.constant.BluetoothConstant;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.ext.ViewBindUtilKt;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.utils.CommonAction;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseVbActivity.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108H\u0014J\b\u00109\u001a\u000206H\u0014J\b\u0010:\u001a\u000206H\u0014J\u0010\u0010;\u001a\u0002062\b\u0010<\u001a\u0004\u0018\u00010=J\u000e\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020'J\u0016\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020'2\u0006\u0010>\u001a\u00020?J\u0006\u0010@\u001a\u000206J\u0010\u0010A\u001a\u0002062\u0006\u0010B\u001a\u00020CH\u0016J\b\u0010D\u001a\u000206H\u0014J\u0006\u0010E\u001a\u00020?J\u0006\u0010F\u001a\u000206J\u000e\u0010F\u001a\u0002062\u0006\u0010M\u001a\u00020'J\u000e\u0010T\u001a\u0002062\u0006\u0010U\u001a\u00020?J\u0006\u0010V\u001a\u00020\u0007J\u001c\u0010W\u001a\u0002062\u0006\u0010*\u001a\u00020'2\f\u0010X\u001a\b\u0012\u0002\b\u0003\u0018\u00010YJ\u001a\u0010Z\u001a\u0002062\b\u0010[\u001a\u0004\u0018\u00010\\2\u0006\u0010*\u001a\u00020'H\u0016J\u0010\u0010]\u001a\u0002062\b\u0010<\u001a\u0004\u0018\u00010=J\u000e\u0010]\u001a\u0002062\u0006\u0010^\u001a\u00020'R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020'X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010)\"\u0004\b,\u0010-R\"\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u0010G\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010)\"\u0004\bI\u0010-R\u001a\u0010J\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010)\"\u0004\bL\u0010-R\u001a\u0010N\u001a\u00020OX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010S¨\u0006_"}, d2 = {"Lcom/yucheng/smarthealthpro/base/BaseVbActivity;", "VB", "Landroidx/viewbinding/ViewBinding;", "Lcom/yucheng/smarthealthpro/framework/BaseActivity;", "<init>", "()V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mActivity", "Landroid/app/Activity;", "getMActivity", "()Landroid/app/Activity;", "setMActivity", "(Landroid/app/Activity;)V", "mBinding", "getMBinding", "()Landroidx/viewbinding/ViewBinding;", "setMBinding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "handler", "Landroid/os/Handler;", "lastClickTime", "", "getLastClickTime", "()J", "setLastClickTime", "(J)V", "MIN_CLICK_DELAY_TIME", "", "getMIN_CLICK_DELAY_TIME", "()I", "requestCode", "getRequestCode", "setRequestCode", "(I)V", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onDestroy", "showProgressDialog", ViewHierarchyConstants.TEXT_KEY, "", "cancelable", "", "dismissProgressDialog", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "hideBottomUIMenu", "checkCanClick", "checkConnect", "maxCheckConnectTime", "getMaxCheckConnectTime", "setMaxCheckConnectTime", "currentTime", "getCurrentTime", "setCurrentTime", "millis", "reconnectCheck", "Ljava/lang/Runnable;", "getReconnectCheck", "()Ljava/lang/Runnable;", "setReconnectCheck", "(Ljava/lang/Runnable;)V", "onConnectCheck", Constant.SpConstKey.IS_CONNECT, "getActivity", "launchActivityForResult", "c", "Ljava/lang/Class;", "onActivityResult", "result", "Landroidx/activity/result/ActivityResult;", "showToast", "textId", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseVbActivity<VB extends ViewBinding> extends com.yucheng.smarthealthpro.framework.BaseActivity {
    private int currentTime;
    private long lastClickTime;
    private ActivityResultLauncher<Intent> launcher;
    private Activity mActivity;
    public VB mBinding;
    private Context mContext;
    private ProgressDialog progressDialog;
    private int requestCode;
    private final Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.base.BaseVbActivity$$ExternalSyntheticLambda0
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return BaseVbActivity.handler$lambda$0(message);
        }
    });
    private final int MIN_CLICK_DELAY_TIME = 1000;
    private int maxCheckConnectTime = BluetoothConstant.PAIR_OR_UNPAIR_TIMEOUT;
    private Runnable reconnectCheck = new Runnable(this) { // from class: com.yucheng.smarthealthpro.base.BaseVbActivity$reconnectCheck$1
        final /* synthetic */ BaseVbActivity<VB> this$0;

        {
            this.this$0 = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = YCBTClient.connectState() == 10;
            if (this.this$0.getCurrentTime() >= this.this$0.getMaxCheckConnectTime()) {
                this.this$0.onConnectCheck(z);
            } else {
                if (z) {
                    this.this$0.onConnectCheck(true);
                    return;
                }
                BaseVbActivity<VB> baseVbActivity = this.this$0;
                baseVbActivity.setCurrentTime(baseVbActivity.getCurrentTime() + 1000);
                ((BaseVbActivity) this.this$0).handler.postDelayed(this, 1000L);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handler$lambda$0(Message it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        return false;
    }

    public void onActivityResult(ActivityResult result, int requestCode) {
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    public final Activity getMActivity() {
        return this.mActivity;
    }

    public final void setMActivity(Activity activity) {
        this.mActivity = activity;
    }

    public final VB getMBinding() {
        VB vb = this.mBinding;
        if (vb != null) {
            return vb;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        return null;
    }

    public final void setMBinding(VB vb) {
        Intrinsics.checkNotNullParameter(vb, "<set-?>");
        this.mBinding = vb;
    }

    public final ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public final void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public final long getLastClickTime() {
        return this.lastClickTime;
    }

    public final void setLastClickTime(long j2) {
        this.lastClickTime = j2;
    }

    public final int getMIN_CLICK_DELAY_TIME() {
        return this.MIN_CLICK_DELAY_TIME;
    }

    public final int getRequestCode() {
        return this.requestCode;
    }

    public final void setRequestCode(int i2) {
        this.requestCode = i2;
    }

    public final ActivityResultLauncher<Intent> getLauncher() {
        return this.launcher;
    }

    public final void setLauncher(ActivityResultLauncher<Intent> activityResultLauncher) {
        this.launcher = activityResultLauncher;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        BaseVbActivity<VB> baseVbActivity = this;
        this.mActivity = baseVbActivity;
        CommonAction.getInstance().addActivity(baseVbActivity);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
        setMBinding(ViewBindUtilKt.inflateWithGeneric(this, layoutInflater));
        setContentView(getMBinding().getRoot());
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResultCallback) new ActivityResultCallback<ActivityResult>(this) { // from class: com.yucheng.smarthealthpro.base.BaseVbActivity.onCreate.1
            final /* synthetic */ BaseVbActivity<VB> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.activity.result.ActivityResultCallback
            public void onActivityResult(ActivityResult result) {
                if (result != null) {
                    BaseVbActivity<VB> baseVbActivity2 = this.this$0;
                    baseVbActivity2.onActivityResult(result, baseVbActivity2.getRequestCode());
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

    public final void showProgressDialog(String text) {
        Window window;
        dismissProgressDialog();
        ProgressDialog progressDialogShow = ProgressDialog.show(this.context, getString(R.string.prompt), text, true, false);
        this.progressDialog = progressDialogShow;
        if (progressDialogShow != null) {
            try {
                window = progressDialogShow.getWindow();
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
                return;
            }
        } else {
            window = null;
        }
        Intrinsics.checkNotNull(window);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.95d);
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public final void showProgressDialog(int text) {
        Window window;
        ProgressDialog progressDialogShow = ProgressDialog.show(this.context, getString(R.string.prompt), getString(text), true, false);
        this.progressDialog = progressDialogShow;
        if (progressDialogShow != null) {
            try {
                window = progressDialogShow.getWindow();
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
                return;
            }
        } else {
            window = null;
        }
        Intrinsics.checkNotNull(window);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.95d);
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public final void showProgressDialog(int text, boolean cancelable) {
        Window window;
        ProgressDialog progressDialogShow = ProgressDialog.show(this.context, getString(R.string.prompt), getString(text), true, cancelable);
        this.progressDialog = progressDialogShow;
        if (progressDialogShow != null) {
            try {
                window = progressDialogShow.getWindow();
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
                return;
            }
        } else {
            window = null;
        }
        Intrinsics.checkNotNull(window);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.95d);
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public final void dismissProgressDialog() {
        ProgressDialog progressDialog;
        if (isDestroyed() || (progressDialog = this.progressDialog) == null) {
            return;
        }
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.dismiss();
        this.progressDialog = null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtils.resetLanguage(this);
    }

    protected void hideBottomUIMenu() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.systemUiVisibility = 2050;
        window.setAttributes(attributes);
    }

    public final boolean checkCanClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastClickTime <= this.MIN_CLICK_DELAY_TIME) {
            return false;
        }
        this.lastClickTime = jCurrentTimeMillis;
        return true;
    }

    public final void checkConnect() {
        this.currentTime = 5000;
        showProgressDialog(R.string.ecg_sync_data, true);
        this.handler.removeCallbacks(this.reconnectCheck);
        this.handler.postDelayed(this.reconnectCheck, 5000L);
    }

    public final int getMaxCheckConnectTime() {
        return this.maxCheckConnectTime;
    }

    public final void setMaxCheckConnectTime(int i2) {
        this.maxCheckConnectTime = i2;
    }

    public final int getCurrentTime() {
        return this.currentTime;
    }

    public final void setCurrentTime(int i2) {
        this.currentTime = i2;
    }

    public final void checkConnect(int millis) {
        this.currentTime = millis;
        this.handler.removeCallbacks(this.reconnectCheck);
        this.handler.postDelayed(this.reconnectCheck, millis);
    }

    public final Runnable getReconnectCheck() {
        return this.reconnectCheck;
    }

    public final void setReconnectCheck(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "<set-?>");
        this.reconnectCheck = runnable;
    }

    public final void onConnectCheck(boolean isConnect) {
        dismissProgressDialog();
    }

    public final Context getActivity() {
        return this;
    }

    public final void launchActivityForResult(int requestCode, Class<?> c2) {
        this.requestCode = requestCode;
        Intent intent = new Intent(this, c2);
        ActivityResultLauncher<Intent> activityResultLauncher = this.launcher;
        Intrinsics.checkNotNull(activityResultLauncher);
        activityResultLauncher.launch(intent);
    }

    public final void showToast(String text) {
        ToastUtil.getInstance(this).toast(text);
    }

    public final void showToast(int textId) throws Resources.NotFoundException {
        ToastUtil.getInstance(this).toast(textId);
    }
}
