package com.yucheng.smarthealthpro.me.setting.thirdservice;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeWechatOldServiceBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.me.bean.AccreDitInfo;
import com.yucheng.smarthealthpro.me.bean.DeviceInfo;
import com.yucheng.smarthealthpro.me.bean.MacInfo;
import com.yucheng.smarthealthpro.me.bean.TokenInfo;
import com.yucheng.smarthealthpro.utils.AppNetworkMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.YCBTClient;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class OldWechatPartyServiceActivity extends BaseVbActivity<ActivityMeWechatOldServiceBinding> {
    private static final int ACCREDIT = 1002;
    private static final int DEVICE = 1001;
    private static final int GETMAC = 1004;
    private static final int GOACCREDIT = 1003;
    TextView btnBanding;
    private String deviceid;
    private String mDeviceMac;
    private String mDeviceName;
    private Dialog mLoading;
    private String token;
    TextView tvAdd;
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.OldWechatPartyServiceActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws JSONException {
            if (msg.obj == null) {
            }
            switch (msg.what) {
                case 1001:
                    OldWechatPartyServiceActivity.this.dealDeviceInfo((DeviceInfo) msg.obj);
                    break;
                case 1002:
                    OldWechatPartyServiceActivity oldWechatPartyServiceActivity = OldWechatPartyServiceActivity.this;
                    oldWechatPartyServiceActivity.requestAccredit(oldWechatPartyServiceActivity.token);
                    break;
                case 1003:
                    OldWechatPartyServiceActivity.this.go2AccreditActivity((AccreDitInfo) msg.obj);
                    break;
                case 1004:
                    OldWechatPartyServiceActivity.this.bindingMacAddress((MacInfo) msg.obj);
                    break;
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.tvAdd = ((ActivityMeWechatOldServiceBinding) this.mBinding).tvAdd;
        this.btnBanding = ((ActivityMeWechatOldServiceBinding) this.mBinding).btnBanding;
        changeTitle(getString(R.string.me_my_device_more_settings_ott_services_title));
        showBack();
        this.mLoading = DialogUtils.createLoadingDialog(this);
        DialogUtils.setTitle(getString(R.string.me_my_device_more_settings_ott_services_the_binding_of));
        this.mDeviceName = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.YCBLE_BINDED_NAME, "");
        this.mDeviceMac = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.YCBLE_BINDED_MAC, "");
    }

    private void initData() {
        String string = getString(R.string.me_my_device_more_settings_ott_services_five_text);
        SpannableString spannableString = new SpannableString(string + getString(R.string.me_my_device_more_settings_ott_services_rebinding));
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.OldWechatPartyServiceActivity.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                if (YCBTClient.connectState() == 10) {
                    if (AppNetworkMgr.isNetworkConnected(OldWechatPartyServiceActivity.this.context)) {
                        OldWechatPartyServiceActivity.this.mLoading.show();
                        OldWechatPartyServiceActivity.this.requestToken();
                        return;
                    } else {
                        Tools.showAlert3(OldWechatPartyServiceActivity.this.context, OldWechatPartyServiceActivity.this.getString(R.string.me_my_device_more_settings_ott_services_the_network_is_currently_unavailable));
                        return;
                    }
                }
                Tools.showAlert3(OldWechatPartyServiceActivity.this.context, OldWechatPartyServiceActivity.this.getString(R.string.please_connect_the_device));
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
                requestMac();
                return;
            } else {
                Tools.showAlert3(this.context, getString(R.string.me_my_device_more_settings_ott_services_the_network_is_currently_unavailable));
                return;
            }
        }
        Tools.showAlert3(this.context, getString(R.string.please_connect_the_device));
    }

    public void requestMac() {
        if (this.mDeviceName == null || this.mDeviceMac == null) {
            return;
        }
        this.mLoading.show();
        this.client.newCall(new Request.Builder().url("https://web-api.ycaviation.com/smartam/wxtoken").post(new FormBody.Builder().add("mac", format(this.mDeviceMac)).build()).build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.OldWechatPartyServiceActivity.3
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e2) {
                OldWechatPartyServiceActivity.this.mLoading.dismiss();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String strString = response.body().string();
                Log.e("WeChat", strString);
                OldWechatPartyServiceActivity.this.handler.sendMessage(OldWechatPartyServiceActivity.this.handler.obtainMessage(1004, OldWechatPartyServiceActivity.this.gson.fromJson(strString, MacInfo.class)));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindingMacAddress(MacInfo macInfo) {
        if (macInfo == null) {
            this.mLoading.dismiss();
        } else if (macInfo.getCode() == 100000) {
            requestToken();
        } else {
            this.tvAdd.setText(getString(R.string.me_my_device_more_settings_ott_services_is_binding));
            this.mLoading.dismiss();
        }
    }

    public void requestToken() {
        this.client.newCall(new Request.Builder().url("https://web-api.ycaviation.com/smartam/wxtoken").get().build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.OldWechatPartyServiceActivity.4
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e2) {
                OldWechatPartyServiceActivity.this.mLoading.dismiss();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String strString = response.body().string();
                Log.e("WeChat--requestToken", strString);
                try {
                    TokenInfo tokenInfo = (TokenInfo) new Gson().fromJson(strString, TokenInfo.class);
                    if (tokenInfo.getCode() == 0) {
                        JSONObject jSONObject = new JSONObject(tokenInfo.getData());
                        OldWechatPartyServiceActivity.this.token = jSONObject.getString("access_token");
                        OldWechatPartyServiceActivity oldWechatPartyServiceActivity = OldWechatPartyServiceActivity.this;
                        oldWechatPartyServiceActivity.requestDevice(oldWechatPartyServiceActivity.token, "50762");
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    OldWechatPartyServiceActivity.this.mLoading.dismiss();
                }
            }
        });
    }

    public void requestDevice(String token, String product_id) {
        String str = " https://api.weixin.qq.com/device/getqrcode?access_token=" + token + "&product_id=" + product_id;
        Logger.d("chong------url==" + str);
        this.client.newCall(new Request.Builder().url(str).get().build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.OldWechatPartyServiceActivity.5
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e2) {
                OldWechatPartyServiceActivity.this.mLoading.dismiss();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String strString = response.body().string();
                Logger.d("chong-----------deivceid==" + strString);
                OldWechatPartyServiceActivity.this.handler.sendMessage(OldWechatPartyServiceActivity.this.handler.obtainMessage(1001, OldWechatPartyServiceActivity.this.gson.fromJson(strString, DeviceInfo.class)));
            }
        });
    }

    public static String format(String s) {
        return s.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealDeviceInfo(DeviceInfo deviceInfo) throws JSONException {
        DeviceInfo.BaseRespBean base_resp = deviceInfo.getBase_resp();
        if (base_resp != null && base_resp.getErrcode() == 0) {
            this.deviceid = deviceInfo.getDeviceid();
            deviceInfo.getQrticket();
            requestAccredit(this.token);
            return;
        }
        this.mLoading.dismiss();
    }

    public void requestAccredit(String token) throws JSONException {
        if (this.mDeviceName == null || this.mDeviceMac == null) {
            this.mLoading.dismiss();
            return;
        }
        String str = "https://api.weixin.qq.com/device/authorize_device?access_token=" + token;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject.put("id", this.deviceid);
            jSONObject.put("mac", format(this.mDeviceMac));
            jSONObject.put("connect_protocol", "3");
            jSONObject.put("auth_key", "");
            jSONObject.put("close_strategy", "2");
            jSONObject.put("conn_strategy", "1");
            jSONObject.put("crypt_method", "0");
            jSONObject.put("auth_ver", "0");
            jSONObject.put("manu_mac_pos", "-1");
            jSONObject.put("ser_mac_pos", "-2");
            jSONObject.put("ble_simple_protocol", "1");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        jSONArray.put(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("device_num", "1");
            jSONObject2.put("op_type", "1");
            jSONObject2.put("device_list", jSONArray);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject2.toString());
        Log.i("wangshu", "network---" + jSONObject2.toString());
        this.client.newCall(new Request.Builder().url(str).post(requestBodyCreate).build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.OldWechatPartyServiceActivity.6
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e4) {
                OldWechatPartyServiceActivity.this.mLoading.dismiss();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("uuuuuu", "授权成功");
                OldWechatPartyServiceActivity.this.handler.sendMessage(OldWechatPartyServiceActivity.this.handler.obtainMessage(1003, OldWechatPartyServiceActivity.this.gson.fromJson(response.body().string(), AccreDitInfo.class)));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void go2AccreditActivity(AccreDitInfo accreditInfo) {
        if (accreditInfo.getResp().get(0).getErrcode() == 0) {
            this.tvAdd.setText(getString(R.string.me_my_device_more_settings_ott_services_is_binding));
            this.tvAdd.setClickable(false);
            Tools.showAlert3(this, getString(R.string.me_my_device_more_settings_ott_services_binding_success));
            this.mLoading.dismiss();
            return;
        }
        this.tvAdd.setText(getString(R.string.me_my_device_more_settings_ott_services_the_binding_of));
        this.tvAdd.setClickable(true);
        Tools.showAlert3(this, getString(R.string.me_my_device_more_settings_ott_services_binding_failure));
        this.mLoading.dismiss();
    }
}
