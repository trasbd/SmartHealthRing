package com.yucheng.smarthealthpro.me.setting.thirdservice;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.zxing.util.QrImageUtil;
import com.yucheng.smarthealthpro.databinding.ActivityMeWechatServiceBinding;
import com.yucheng.smarthealthpro.dialog.MyDialog;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.utils.AppNetworkMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.NetResult;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class WechatPartyServiceActivity extends BaseVbActivity<ActivityMeWechatServiceBinding> {
    private static final int ACCREDIT = 1002;
    private static final int ACCREDITSTATE = 1003;
    private static final int REACCREDIT = 1001;
    private Bitmap bitmap;
    TextView btnBanding;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.WechatPartyServiceActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.obj != null) {
                try {
                    WechatPartyServiceActivity.this.createQr(((NetResult) msg.obj).data);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            switch (msg.what) {
                case 1001:
                case 1002:
                    WechatPartyServiceActivity.this.updateUI((NetResult) msg.obj, true);
                    break;
                case 1003:
                    WechatPartyServiceActivity.this.updateUI((NetResult) msg.obj, false);
                    break;
            }
        }
    };
    ImageView imageView;
    LinearLayout ly_first;
    LinearLayout ly_next;
    private MyDialog mLoading;
    TextView tvAdd;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.tvAdd = ((ActivityMeWechatServiceBinding) this.mBinding).tvAdd;
        this.btnBanding = ((ActivityMeWechatServiceBinding) this.mBinding).btnBanding;
        this.imageView = ((ActivityMeWechatServiceBinding) this.mBinding).wechatQrImg;
        this.ly_first = ((ActivityMeWechatServiceBinding) this.mBinding).thirdPartyWechatFirstNoteLy;
        this.ly_next = ((ActivityMeWechatServiceBinding) this.mBinding).thirdPartyWechatNextNoteLy;
        changeTitle(getString(R.string.me_my_device_more_settings_ott_services_title));
        showBack();
        this.mLoading = (MyDialog) DialogUtils.createLoadingDialog(this);
        setDialogTitle(getString(R.string.me_my_device_more_settings_ott_services_the_binding_of));
        this.tvAdd.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.WechatPartyServiceActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0(view);
            }
        });
        this.imageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.WechatPartyServiceActivity.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                WechatPartyServiceActivity.this.saveImage();
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        accredit(Constants.ACCREDITURL, 1002);
    }

    private void setDialogTitle(String title) {
        MyDialog myDialog = this.mLoading;
        if (myDialog != null) {
            myDialog.setTitle(title);
        }
    }

    private void initData() {
        String string = getString(R.string.wechat_qr_content);
        SpannableString spannableString = new SpannableString(string + getString(R.string.me_my_device_more_settings_ott_services_rebinding));
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.WechatPartyServiceActivity.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                if (YCBTClient.connectState() == 10) {
                    if (AppNetworkMgr.isNetworkConnected(WechatPartyServiceActivity.this.context)) {
                        WechatPartyServiceActivity.this.mLoading.show();
                        WechatPartyServiceActivity.this.accredit(Constants.REACCREDITURL, 1001);
                        return;
                    } else {
                        Tools.showAlert3(WechatPartyServiceActivity.this.context, WechatPartyServiceActivity.this.getString(R.string.me_my_device_more_settings_ott_services_the_network_is_currently_unavailable));
                        return;
                    }
                }
                Tools.showAlert3(WechatPartyServiceActivity.this.context, WechatPartyServiceActivity.this.getString(R.string.please_connect_the_device));
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };
        spannableString.setSpan(foregroundColorSpan, string.length(), spannableString.length(), 17);
        spannableString.setSpan(clickableSpan, string.length(), spannableString.length(), 17);
        this.btnBanding.setMovementMethod(LinkMovementMethod.getInstance());
        this.btnBanding.setText(spannableString);
        if (YCBTClient.connectState() == 10) {
            if (AppNetworkMgr.isNetworkConnected(this.context)) {
                accredit(Constants.GETSTATICURL, 1003);
                return;
            } else {
                Tools.showAlert3(this.context, getString(R.string.me_my_device_more_settings_ott_services_the_network_is_currently_unavailable));
                return;
            }
        }
        Tools.showAlert3(this.context, getString(R.string.please_connect_the_device));
    }

    public void accredit(String url, final int type) {
        if (YCBTClient.connectState() != 10 || YCBTClient.getBindDeviceMac() == null || YCBTClient.getBindDeviceMac().isEmpty()) {
            return;
        }
        this.mLoading.show();
        HashMap map = new HashMap();
        map.put("mac", YCBTClient.getBindDeviceMac());
        map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(this, Constant.SpConstKey.TOKEN, ""));
        map.put("wxProductId", "50762");
        HttpUtils.getInstance().postMsgAsynHttp(this, url, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.WechatPartyServiceActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    WechatPartyServiceActivity.this.handler.sendMessage(WechatPartyServiceActivity.this.handler.obtainMessage(type, new Gson().fromJson(result, NetResult.class)));
                } catch (JsonSyntaxException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUI(NetResult result, boolean isShowToast) {
        if (result != null && result.code == 0) {
            this.tvAdd.setText(getString(R.string.me_my_device_more_settings_ott_services_is_binding));
            this.tvAdd.setClickable(false);
            this.mLoading.dismiss();
            if (isShowToast) {
                Tools.showAlert3(this, getString(R.string.me_my_device_more_settings_ott_services_binding_success));
                return;
            }
            return;
        }
        this.tvAdd.setText(getString(R.string.me_my_device_more_settings_ott_services_tv_add_text));
        this.tvAdd.setClickable(true);
        this.mLoading.dismiss();
        if (isShowToast) {
            Tools.showAlert3(this, getString(R.string.me_my_device_more_settings_ott_services_binding_failure));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createQr(String content) {
        if (content == null || content.isEmpty()) {
            return;
        }
        Bitmap bitmapCreateQRImage = QrImageUtil.createQRImage(this, content, null, (int) (getResources().getDisplayMetrics().density * 250.0f), (int) (getResources().getDisplayMetrics().density * 250.0f));
        this.bitmap = bitmapCreateQRImage;
        if (bitmapCreateQRImage != null) {
            this.imageView.setImageBitmap(bitmapCreateQRImage);
            this.ly_next.setVisibility(0);
            this.ly_first.setVisibility(8);
            showRightImage(com.yucheng.smarthealthpro.framework.R.mipmap.fenxiang, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.WechatPartyServiceActivity.5
                @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
                public void onClick(View btn) {
                    WechatPartyServiceActivity wechatPartyServiceActivity = WechatPartyServiceActivity.this;
                    ShareUtils.share(wechatPartyServiceActivity, wechatPartyServiceActivity.getString(R.string.me_my_device_more_settings_ott_services_title));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveImage() {
        String realPathFromURI = ShareUtils.getRealPathFromURI(this, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), this.bitmap, getString(R.string.me_my_device_more_settings_ott_services_title), getString(R.string.me_my_device_more_settings_ott_services_title))));
        if (realPathFromURI != null) {
            ToastUtil.getInstance(this).toast(getString(R.string.wechat_save_qr_path) + realPathFromURI);
        }
    }
}
