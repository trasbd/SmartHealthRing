package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.common.net.HttpHeaders;
import com.orhanobut.logger.Logger;
import com.tencent.connect.common.Constants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityWebBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes5.dex */
public class AiWebActivity extends BaseVbActivity<ActivityWebBinding> {
    private static final String BaseUrl = "https://staticpage.ycaviation.com/app/permission/";
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.AiWebActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            if (msg.what != 0) {
                return false;
            }
            AiWebActivity.this.webView.reload();
            AiWebActivity.this.webView.loadUrl(AiWebActivity.this.url);
            return false;
        }
    });
    private String url;
    private WebView webView;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
    }

    private void init() {
        changeTitle(getIntent().getStringExtra("title"));
        showBack();
        this.webView = (WebView) findViewById(R.id.webView);
        String stringExtra = getIntent().getStringExtra("url");
        this.url = stringExtra;
        if (stringExtra != null) {
            this.webView.loadUrl(stringExtra);
        }
    }

    private void initData() {
        this.webView.setWebViewClient(new MWebViewClient());
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        String str = this.url;
        if (str != null && !str.contains(Constants.JumpUrlConstants.SRC_TYPE_APP)) {
            settings.setUseWideViewPort(true);
        }
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        this.webView.addJavascriptInterface(new AndroidJs(this), "AndroidJs");
    }

    private class MWebViewClient extends WebViewClient {
        String referer;

        private MWebViewClient() {
            this.referer = "http://www.kangyuanai.com/";
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logger.d("chong----------url==" + url);
            try {
                if (url.startsWith("weixin://") || url.startsWith("alipays://")) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(url));
                    AiWebActivity.this.startActivity(intent);
                    return true;
                }
                if (url.contains("https://wx.tenpay.com")) {
                    HashMap map = new HashMap();
                    map.put(HttpHeaders.REFERER, this.referer);
                    view.loadUrl(url, map);
                    this.referer = url;
                    return true;
                }
                view.loadUrl(url);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return true;
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (AiWebActivity.this.getIntent().getBooleanExtra("is_start_trend", false)) {
                AiWebActivity aiWebActivity = AiWebActivity.this;
                aiWebActivity.loadJS(aiWebActivity.webView);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadJS(WebView webView) {
        webView.evaluateJavascript("javascript:ocCallJSFunction('" + (Locale.getDefault().getLanguage().toLowerCase().equals("zh") ? "zh-cn" : "en-us") + "','" + (getIntent().getStringExtra(Constant.SpConstKey.DEV_ID) == null ? (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, "1") : getIntent().getStringExtra(Constant.SpConstKey.DEV_ID)) + "')", new ValueCallback<String>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.AiWebActivity.2
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String value) {
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity
    public void backAction() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            finish();
        }
    }

    public class AndroidJs {
        private Context mContext;

        public AndroidJs(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void callAndroids(String mobileType) {
            Logger.d("chong----------callAndroids==" + mobileType);
            if (mobileType == null || mobileType.isEmpty()) {
                return;
            }
            AiWebActivity.this.url = AiWebActivity.BaseUrl + mobileType + "_" + AiWebActivity.this.getString(R.string.lan) + ".html";
            AiWebActivity.this.handler.sendEmptyMessage(0);
        }

        @JavascriptInterface
        public void back() {
            AiWebActivity.this.finish();
        }
    }
}
