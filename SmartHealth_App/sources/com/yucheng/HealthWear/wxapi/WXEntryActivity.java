package com.yucheng.HealthWear.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.BaseActivity;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.login.wechat.WeChatLogin;

/* loaded from: classes4.dex */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    private IWXAPI api;

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
    }

    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null) {
            startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
        }
    }

    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        if (msg == null || msg.mediaObject == null || !(msg.mediaObject instanceof WXAppExtendObject)) {
            return;
        }
        Toast.makeText(this, ((WXAppExtendObject) msg.mediaObject).extInfo, 0).show();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(this, "wx944dbd119d02c1ff", false);
        this.api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.handleIntent(getIntent(), this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
        finish();
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        String str;
        int i2 = baseResp.errCode;
        if (i2 == -4) {
            str = "发送被拒绝";
        } else if (i2 == -2) {
            str = "发送取消";
        } else if (i2 == 0) {
            int type = baseResp.getType();
            if (type == 1) {
                getAccess_token(((SendAuth.Resp) baseResp).code);
                Toast.makeText(this, getString(R.string.authorization_succeeded), 1).show();
                return;
            } else if (type != 2) {
                str = "";
            } else {
                sendBroadcast(new Intent().setAction("authlogin"));
                str = "发送成功";
            }
        } else {
            str = "发送返回";
        }
        Toast.makeText(this, str, 1).show();
        finish();
    }

    private void getAccess_token(final String code) {
        HttpUtils.getInstance().getMsgAsynHttp(this, "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeChatLogin.WEIXIN_APP_ID + "&secret=" + WeChatLogin.APP_SECRET + "&code=" + code + "&grant_type=authorization_code", null, new HttpUtils.HttpCallback() { // from class: com.yucheng.HealthWear.wxapi.WXEntryActivity.1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    UserResult userResult = (UserResult) new Gson().fromJson(result, UserResult.class);
                    WXEntryActivity.this.sendBroadcast(new Intent("com.login.wechat").putExtra("accessToken", userResult.access_token).putExtra("openID", userResult.openid));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                WXEntryActivity.this.finish();
            }
        });
    }

    private void getUserMesg(final String access_token, final String openid) {
        String str = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;
    }

    public class UserResult {
        public String access_token;
        public String openid;

        public UserResult() {
        }
    }
}
