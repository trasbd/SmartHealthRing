package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.UploadFileTypeBean;
import com.yucheng.smarthealthpro.databinding.ActivityDeveloperModelBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.ApplySigningUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.PackageUtils;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.utils.LogToFileUtils;
import java.io.IOException;

/* loaded from: classes5.dex */
public class DeveloperModeActivity extends BaseVbActivity<ActivityDeveloperModelBinding> {
    Button btn_open_log;
    Button btn_share;
    Button btn_share2;
    TextView btn_upload;
    String health = "77c12ed0f7ee5c325c058451ffe26fd7";
    String mecare = "344fb3f63e18c76c7aba4bcd6229a3be";
    String mymonx = "97b208faf580e128c064a28db55e23e5";
    TextView tv_release;
    TextView tv_sign;
    TextView tv_smart_health;
    TextView tv_version;
    TextView tv_versioncode;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        changeTitle(getString(R.string.develper_mode));
        showBack();
        String rawSignatureStr = ApplySigningUtils.getRawSignatureStr(this, getPackageName());
        if (Constant.isSmartHealth()) {
            this.tv_smart_health.setVisibility(0);
            this.tv_version.setVisibility(0);
        } else {
            this.tv_smart_health.setVisibility(8);
            this.tv_version.setVisibility(8);
        }
        String str = "未知签名 " + rawSignatureStr;
        if (this.health.equals(rawSignatureStr)) {
            if (Constant.isSmartHealth()) {
                this.tv_version.setText("当前签名版本：谷歌版");
            }
            str = "health.jks";
        }
        if (this.mecare.equals(rawSignatureStr)) {
            if (Constant.isSmartHealth()) {
                this.tv_version.setText("当前签名版本：应用宝版");
            }
            str = "mecare.jks";
        }
        if (this.mymonx.equals(rawSignatureStr)) {
            str = "mymonx";
        }
        this.tv_release.setText("当前版本：正式版本");
        this.tv_sign.setText("签名信息：" + str);
        this.btn_share.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.DeveloperModeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ShareUtils.sendLog(DeveloperModeActivity.this);
            }
        });
        HttpUtils.getInstance().getMsgAsynHttp(this, Constants.Props, null, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.DeveloperModeActivity.2
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.d("upload=" + result);
                try {
                    UploadFileTypeBean uploadFileTypeBean = (UploadFileTypeBean) new Gson().fromJson(result, UploadFileTypeBean.class);
                    if (uploadFileTypeBean.getData() == null || uploadFileTypeBean.getData().getLogDeviceModel() == null) {
                        return;
                    }
                    YCBTClient.OpenLogSwitch = uploadFileTypeBean.getData().getLogDeviceModel().contains((String) YCBTClient.readDeviceInfo(Constants.FunctionConstant.DEVICETYPE));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.btn_upload.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.DeveloperModeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) throws IOException {
                HttpUtils.getInstance().upload(DeveloperModeActivity.this.getApplicationContext(), com.yucheng.smarthealthpro.framework.util.Constants.UploadFile, "file", LogToFileUtils.getLogFile("yclogs.txt"), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.DeveloperModeActivity.3.1
                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                    public void onSuccess(String result) {
                        Logger.d("upload=" + result);
                    }
                });
            }
        });
        this.btn_upload.setVisibility(8);
        this.tv_versioncode.setText("版本号：" + PackageUtils.getVersionCode(this));
        this.btn_share2.setVisibility(8);
        this.btn_open_log = (Button) findViewById(R.id.btn_open_log);
        setLogText();
        this.btn_open_log.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.DeveloperModeActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                YCBTClient.OpenLogSwitch = !YCBTClient.OpenLogSwitch;
                SharedPreferencesUtils.put(DeveloperModeActivity.this.getApplicationContext(), Constant.SpConstKey.OpenLogSwitch, Boolean.valueOf(YCBTClient.OpenLogSwitch));
                DeveloperModeActivity.this.setLogText();
            }
        });
    }

    private void initView() {
        this.tv_sign = ((ActivityDeveloperModelBinding) this.mBinding).tvSign;
        this.btn_share = ((ActivityDeveloperModelBinding) this.mBinding).btnShare;
        this.tv_version = ((ActivityDeveloperModelBinding) this.mBinding).tvVersion;
        this.tv_smart_health = ((ActivityDeveloperModelBinding) this.mBinding).tvSmartHealth;
        this.tv_release = ((ActivityDeveloperModelBinding) this.mBinding).tvRelease;
        this.tv_versioncode = ((ActivityDeveloperModelBinding) this.mBinding).tvVersioncode;
        this.btn_upload = ((ActivityDeveloperModelBinding) this.mBinding).btnUpload;
        this.btn_share2 = ((ActivityDeveloperModelBinding) this.mBinding).btnShare2;
    }

    public void setLogText() {
        if (YCBTClient.OpenLogSwitch) {
            this.btn_open_log.setText("关闭日志");
        } else {
            this.btn_open_log.setText("开启日志");
        }
    }
}
