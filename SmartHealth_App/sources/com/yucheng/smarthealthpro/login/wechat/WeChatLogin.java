package com.yucheng.smarthealthpro.login.wechat;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class WeChatLogin {
    public static String APP_SECRET = "6262c00c315f3244be3ec043f00b8498";
    private static final String SCOPE = "snsapi_userinfo";
    private static final String STATE = "wechat_sdk_demo_test_neng";
    public static String WEIXIN_APP_ID = "wx3481c2726bcf901f";
    public static WeChatLogin weChatLogin;
    private IWXAPI api;
    private Context context;

    private void init() {
        if (Constant.isHealthWear()) {
            WEIXIN_APP_ID = "wx5c847dc6c1a8186b";
            APP_SECRET = "d797aeb079157a5943c3e8d72a5ba801";
        } else if (Constant.isSmartHealth()) {
            WEIXIN_APP_ID = "wx842d2a07707de29c";
            APP_SECRET = "e6b5f3f17dab5d59104cbd1bc89f14c1";
        }
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(this.context, WEIXIN_APP_ID, true);
        this.api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(WEIXIN_APP_ID);
        WXAPIFactory.createWXAPI(this.context, null).registerApp(WEIXIN_APP_ID);
    }

    private WeChatLogin(Activity context) {
        this.context = context;
        init();
    }

    public static WeChatLogin getInstance(Activity context) {
        if (weChatLogin == null) {
            weChatLogin = new WeChatLogin(context);
        }
        return weChatLogin;
    }

    public static void release() {
        weChatLogin = null;
    }

    public void loginWeChat() {
        if (this.context == null) {
            return;
        }
        if (this.api == null) {
            init();
        }
        if (!this.api.isWXAppInstalled()) {
            Context context = this.context;
            Toast.makeText(context, context.getString(R.string.you_have_not_installed_the_wechat_client), 0).show();
        } else {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = SCOPE;
            req.state = STATE;
            this.api.sendReq(req);
        }
    }

    public void clear() {
        this.api = null;
        weChatLogin = null;
    }
}
