package com.yucheng.smarthealthpro.login.normal;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAiWebviewBinding;

/* loaded from: classes5.dex */
public class WebViewActivity extends BaseVbActivity<ActivityAiWebviewBinding> {
    private String url;
    private WebView webView;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        String stringExtra = getIntent().getStringExtra("title");
        this.url = getIntent().getStringExtra("url");
        showBack();
        if (stringExtra == null) {
            stringExtra = "";
        }
        changeTitle(stringExtra);
        WebView webView = (WebView) findViewById(R.id.webView);
        this.webView = webView;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(2);
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.yucheng.smarthealthpro.login.normal.WebViewActivity.1
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Logger.e("onReceivedError:" + ((Object) error.getDescription()) + " url:" + request.getUrl(), new Object[0]);
            }
        });
        this.webView.addJavascriptInterface(new AndroidJs(this), "AndroidJs");
        this.webView.loadUrl(this.url);
    }

    public class AndroidJs {
        private Context mContext;

        @JavascriptInterface
        public void fastRegister() {
        }

        public AndroidJs(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void back() {
            WebViewActivity.this.finish();
        }
    }
}
