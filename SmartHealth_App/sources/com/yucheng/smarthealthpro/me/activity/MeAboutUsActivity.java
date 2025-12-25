package com.yucheng.smarthealthpro.me.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeAboutusBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.login.normal.WebViewActivity;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.PackageUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.UpdateVersionUtil;
import com.yucheng.ycbtsdk.YCBTClient;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class MeAboutUsActivity extends BaseVbActivity<ActivityMeAboutusBinding> {
    private static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static final String GOOGLE_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=";
    private static final String TENCENT_QQ_STORE_PACKAGE = "com.tencent.android.qqdownloader";
    private static final String TENCENT_QQ_STORE_URL = "https://a.app.qq.com/o/simple.jsp?pkgname=";
    ImageView ivAppIcon;
    String lan;
    RelativeLayout rlAppVersions;
    RelativeLayout rlDeveloper;
    RelativeLayout rlFirmwareRecovery;
    RelativeLayout rlFirmwareUpgrade;
    RelativeLayout rlPrivacyPolicy;
    RelativeLayout rlToScoring;
    RelativeLayout rlUserAgreement;
    RelativeLayout rl_to_public_beta;
    TextView tvVersions;
    View vDeveloper;
    private Handler handler = new Handler(Looper.getMainLooper());
    int clickCount = 0;
    int iconClickCount = 0;
    int maxCount = 10;
    boolean isInDeveloperMode = false;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        UpdateVersionUtil.getInstance().checkUpdate("https://staticpage.ycaviation.com/app/app_version.xml", this, false);
        if (!Constant.isHealthWear() && !Constant.isSmartHealth()) {
            this.rl_to_public_beta.setVisibility(8);
        } else {
            this.rl_to_public_beta.setVisibility(0);
        }
    }

    private void initView() {
        this.rlAppVersions = ((ActivityMeAboutusBinding) this.mBinding).rlAppVersions;
        this.rlFirmwareUpgrade = ((ActivityMeAboutusBinding) this.mBinding).rlFirmwareUpgrade;
        this.rlFirmwareRecovery = ((ActivityMeAboutusBinding) this.mBinding).rlFirmwareRecovery;
        this.rlPrivacyPolicy = ((ActivityMeAboutusBinding) this.mBinding).rlPrivacyPolicy;
        this.rlUserAgreement = ((ActivityMeAboutusBinding) this.mBinding).rlUserAgreement;
        this.rlToScoring = ((ActivityMeAboutusBinding) this.mBinding).rlToScoring;
        this.tvVersions = ((ActivityMeAboutusBinding) this.mBinding).tvVersions;
        this.rlDeveloper = ((ActivityMeAboutusBinding) this.mBinding).rlDeveloper;
        this.vDeveloper = ((ActivityMeAboutusBinding) this.mBinding).vDeveloper;
        this.ivAppIcon = ((ActivityMeAboutusBinding) this.mBinding).ivHead;
        this.rl_to_public_beta = ((ActivityMeAboutusBinding) this.mBinding).rlToPublicBeta;
        this.rlAppVersions.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirmwareUpgrade.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirmwareRecovery.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlPrivacyPolicy.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlUserAgreement.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlToScoring.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeAboutusBinding) this.mBinding).rlToFaq.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlDeveloper.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivAppIcon.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rl_to_public_beta.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_about_us_title));
        getTitleTextView().setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MeAboutUsActivity.this.openDeveloperMode();
            }
        });
        showBack();
        this.tvVersions.setText(PackageUtils.getVersionName(this.context) + "(" + PackageUtils.getVersionCode(this.context) + ")");
        if (Constant.isTechFeel()) {
            findViewById(R.id.rl_to_faq).setVisibility(0);
            findViewById(R.id.v_faq).setVisibility(0);
        }
    }

    private void initData() {
        String string = getString(R.string.lan);
        this.lan = string;
        if (string.equals("cn")) {
            return;
        }
        this.lan = "en";
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.rl_to_public_beta) {
            startActivity(new Intent(this.context, (Class<?>) BetaUpdateActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_developer) {
            startActivity(new Intent(this.context, (Class<?>) DeveloperModeActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_app_versions) {
            openDeveloperMode();
            return;
        }
        if (view.getId() == R.id.iv_head) {
            triggerMonitoringReport();
            return;
        }
        if (view.getId() == R.id.rl_firmware_upgrade) {
            if (YCBTClient.connectState() == 10) {
                if (PermissionUtil.openSDCardPermission(this)) {
                    startActivity(new Intent(this.context, (Class<?>) SoftUpdateActivity.class));
                    return;
                }
                return;
            }
            ToastUtil.getInstance(this).toast(getString(R.string.please_connect_the_device));
            return;
        }
        if (view.getId() == R.id.rl_firmware_recovery) {
            startActivity(new Intent(this.context, (Class<?>) RecoveryActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_privacy_policy) {
            startActivity(new Intent(this, (Class<?>) WebViewActivity.class).putExtra("title", getString(R.string.webview_protocal)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + getString(R.string.app_name) + "/privacy_policy_smart_" + this.lan + ".html"));
            return;
        }
        if (view.getId() == R.id.rl_user_agreement) {
            startActivity(new Intent(this, (Class<?>) WebViewActivity.class).putExtra("title", getString(R.string.webview_user_agreement)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + getString(R.string.app_name) + "/user_agreement_smart_" + this.lan + ".html"));
            return;
        }
        if (view.getId() == R.id.rl_to_scoring) {
            if (getString(R.string.lan).equals("cn")) {
                if (isPlayStoreInstalled(this, TENCENT_QQ_STORE_PACKAGE)) {
                    openPlayStore(this, TENCENT_QQ_STORE_PACKAGE);
                    return;
                } else {
                    openWebViewStore(this, TENCENT_QQ_STORE_URL);
                    return;
                }
            }
            if (isPlayStoreInstalled(this, "com.android.vending")) {
                openPlayStore(this, "com.android.vending");
                return;
            } else {
                openWebViewStore(this, GOOGLE_PLAY_STORE_URL);
                return;
            }
        }
        if (view.getId() == R.id.rl_to_faq) {
            startActivity(new Intent(this, (Class<?>) WebViewActivity.class).putExtra("title", getString(R.string.faq)).putExtra("url", "https://staticpage.ycaviation.com/app/policy/" + URLEncoder.encode(getString(R.string.app_name)).replace("+", "%20") + "/faq/feel_faq_mod.html"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openDeveloperMode() {
        int i2 = this.clickCount + 1;
        this.clickCount = i2;
        if (i2 < this.maxCount) {
            this.handler.removeCallbacksAndMessages(null);
            this.handler.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    MeAboutUsActivity.this.clickCount = 0;
                }
            }, 3000L);
        } else {
            this.rlDeveloper.setVisibility(0);
            this.vDeveloper.setVisibility(0);
        }
    }

    private void triggerMonitoringReport() {
        int i2 = this.iconClickCount + 1;
        this.iconClickCount = i2;
        if (i2 < this.maxCount) {
            this.handler.removeCallbacksAndMessages(null);
            this.handler.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAboutUsActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    MeAboutUsActivity.this.iconClickCount = 0;
                }
            }, 3000L);
        } else {
            this.iconClickCount = 0;
            DataSyncUtils.INSTANCE.getInstance(getApplicationContext()).forceUploadLogFile();
            showToast(getString(R.string.app_name));
        }
    }

    private boolean isPlayStoreInstalled(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private void openPlayStore(Context context, String packageName) {
        try {
            if (context.getPackageName() != null) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
                intent.setPackage(packageName);
                intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
                if (TENCENT_QQ_STORE_PACKAGE.equals(packageName)) {
                    intent.setClassName(TENCENT_QQ_STORE_PACKAGE, "com.tencent.pangu.link.LinkProxyActivity");
                }
                context.startActivity(intent);
            }
        } catch (Exception unused) {
            if (TENCENT_QQ_STORE_PACKAGE.equals(packageName)) {
                openWebViewStore(context, TENCENT_QQ_STORE_URL);
            } else {
                openWebViewStore(context, GOOGLE_PLAY_STORE_URL);
            }
        }
    }

    private void openWebViewStore(Context context, String url) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url + context.getPackageName()));
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
    }
}
