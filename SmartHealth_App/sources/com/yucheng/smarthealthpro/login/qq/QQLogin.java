package com.yucheng.smarthealthpro.login.qq;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import com.orhanobut.logger.Logger;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.utils.Constant;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class QQLogin {
    public static String APP_ID = "1112175030";
    public static QQLogin qqLogin;
    private BaseUiListener baseUiListener;
    private Activity context;
    private Tencent tencent;

    public static synchronized QQLogin getInstance(Activity context) {
        if (qqLogin == null) {
            qqLogin = new QQLogin(context);
        }
        return qqLogin;
    }

    private QQLogin(Activity context) {
        this.context = context;
        if (this.tencent == null) {
            if (Constant.isHealthWear()) {
                APP_ID = "102025629";
            } else if (Constant.isSmartHealth()) {
                APP_ID = "1108133486";
            }
            Tencent.setIsPermissionGranted(true);
            this.tencent = Tencent.createInstance(APP_ID, context.getApplicationContext());
        }
    }

    public void qqLogin() {
        if (this.baseUiListener == null) {
            this.baseUiListener = new BaseUiListener();
        }
        this.tencent.login(this.context, "all", this.baseUiListener);
    }

    private class BaseUiListener implements IUiListener {
        private BaseUiListener() {
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object response) {
            Toast.makeText(QQLogin.this.context, QQLogin.this.context.getString(R.string.authorization_succeeded), 0).show();
            JSONObject jSONObject = (JSONObject) response;
            try {
                QQLogin.this.context.sendBroadcast(new Intent("com.login.qq").putExtra("accessToken", jSONObject.getString("access_token")).putExtra("openID", jSONObject.getString("openid")));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            Logger.d("chong---------qq_error==" + uiError.errorCode + "--" + uiError.errorMessage);
            Toast.makeText(QQLogin.this.context, QQLogin.this.context.getString(R.string.authorization_failed), 0).show();
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            Toast.makeText(QQLogin.this.context, QQLogin.this.context.getString(R.string.authorization_cancle), 0).show();
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i2) {
            Toast.makeText(QQLogin.this.context, QQLogin.this.context.getString(R.string.authorization_cancle), 0).show();
        }
    }

    public void setCallBack(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, this.baseUiListener);
    }

    public void clear() {
        this.baseUiListener = null;
        this.tencent = null;
        qqLogin = null;
    }
}
