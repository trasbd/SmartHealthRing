package com.yucheng.smarthealthpro.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPermissionBinding;
import com.yucheng.smarthealthpro.home.activity.ecg.activity.AiWebActivity;
import com.yucheng.smarthealthpro.sport.weathers.WeatherUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.NetworkManager;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.view.OnChangeListener;
import com.yucheng.smarthealthpro.view.SwitchButton;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class PermissionActivity extends BaseVbActivity<ActivityPermissionBinding> {
    private static final String BaseUrl = "https://staticpage.ycaviation.com/app/permission/";
    private String[] PERMISSION_CALL;
    private String[] PERMISSION_CALL_RECORD;
    private String[] PERMISSION_CAMARE;
    private String[] PERMISSION_CONTANTS;
    private String[] PERMISSION_INTERNET;
    private String[] PERMISSION_NEARBY_DEVICE;
    private String[] PERMISSION_POSITION;
    private String[] PERMISSION_SD;
    private String[] PERMISSION_SMS;
    private TextView internet_state;
    private LinearLayout ll_permission_call;
    private LinearLayout ll_permission_call_recode;
    private LinearLayout ll_permission_contacts;
    private LinearLayout ll_permission_notify;
    private LinearLayout ll_permission_sms;
    private LinearLayout permission_background_running;
    private SwitchButton permission_switchButton_call;
    private SwitchButton permission_switchButton_call_record;
    private SwitchButton permission_switchButton_camare;
    private SwitchButton permission_switchButton_contants;
    private SwitchButton permission_switchButton_internet;
    private SwitchButton permission_switchButton_nearby_device;
    private SwitchButton permission_switchButton_notify;
    private SwitchButton permission_switchButton_position;
    private SwitchButton permission_switchButton_sd;
    private SwitchButton permission_switchButton_service;
    private SwitchButton permission_switchButton_sms;
    private final String TAG_SERVICE = NotificationCompat.CATEGORY_SERVICE;
    private final String TAG_CALL = NotificationCompat.CATEGORY_CALL;
    private final String TAG_CALL_RECORD = "call_record";
    private final String TAG_SMS = "sms";
    private final String TAG_NOTIFY = "notify";
    private final String TAG_CONTANTS = "contants";
    private final String TAG_SD = "sd";
    private final String TAG_POSITION = "position";
    private final String TAG_NEARBY_DEVICE = "nearby_device";
    private final String TAG_CAMARE = "camare";
    private final String TAG_INTERNET = "internet";
    boolean isHasLocation = false;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
        initListence();
        this.isHasLocation = PermissionUtil.isPermission(this, this.PERMISSION_POSITION);
    }

    private void init() {
        changeTitle(getString(R.string.setting_permission));
        showBack();
        this.ll_permission_call = (LinearLayout) findViewById(R.id.ll_permission_call);
        this.ll_permission_call_recode = (LinearLayout) findViewById(R.id.ll_permission_call_recode);
        this.ll_permission_sms = (LinearLayout) findViewById(R.id.ll_permission_sms);
        this.ll_permission_notify = (LinearLayout) findViewById(R.id.ll_permission_notify);
        this.ll_permission_contacts = (LinearLayout) findViewById(R.id.ll_permission_contacts);
        this.permission_background_running = (LinearLayout) findViewById(R.id.permission_background_running);
        this.permission_switchButton_service = (SwitchButton) findViewById(R.id.permission_switchButton_service);
        this.permission_switchButton_call = (SwitchButton) findViewById(R.id.permission_switchButton_call);
        this.permission_switchButton_call_record = (SwitchButton) findViewById(R.id.permission_switchButton_call_record);
        this.permission_switchButton_sms = (SwitchButton) findViewById(R.id.permission_switchButton_sms);
        this.permission_switchButton_notify = (SwitchButton) findViewById(R.id.permission_switchButton_notify);
        this.permission_switchButton_contants = (SwitchButton) findViewById(R.id.permission_switchButton_contants);
        this.permission_switchButton_sd = (SwitchButton) findViewById(R.id.permission_switchButton_sd);
        this.permission_switchButton_position = (SwitchButton) findViewById(R.id.permission_switchButton_position);
        this.permission_switchButton_nearby_device = (SwitchButton) findViewById(R.id.permission_switchButton_nearby_device);
        this.permission_switchButton_camare = (SwitchButton) findViewById(R.id.permission_switchButton_camare);
        this.permission_switchButton_internet = (SwitchButton) findViewById(R.id.permission_switchButton_internet);
        this.internet_state = (TextView) findViewById(R.id.permission_internet_state);
        this.permission_switchButton_service.setTag(NotificationCompat.CATEGORY_SERVICE);
        this.permission_switchButton_call.setTag(NotificationCompat.CATEGORY_CALL);
        this.permission_switchButton_call_record.setTag("call_record");
        this.permission_switchButton_sms.setTag("sms");
        this.permission_switchButton_notify.setTag("notify");
        this.permission_switchButton_contants.setTag("contants");
        this.permission_switchButton_sd.setTag("sd");
        this.permission_switchButton_position.setTag("position");
        this.permission_switchButton_nearby_device.setTag("nearby_device");
        this.permission_switchButton_camare.setTag("camare");
        this.permission_switchButton_internet.setTag("internet");
        this.PERMISSION_CALL = new String[]{Permission.READ_PHONE_STATE, Permission.CALL_PHONE, "android.permission.ANSWER_PHONE_CALLS"};
        this.PERMISSION_CALL_RECORD = new String[]{Permission.READ_CALL_LOG};
        this.PERMISSION_SMS = new String[]{Permission.RECEIVE_SMS, Permission.READ_SMS};
        this.PERMISSION_CONTANTS = new String[]{Permission.READ_CONTACTS};
        if (Build.VERSION.SDK_INT >= 33) {
            this.PERMISSION_SD = new String[0];
        } else {
            this.PERMISSION_SD = new String[]{Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE};
        }
        String[] strArr = new String[2];
        strArr[0] = Permission.ACCESS_FINE_LOCATION;
        strArr[1] = Build.VERSION.SDK_INT >= 29 ? "android.permission.ACCESS_BACKGROUND_LOCATION" : Permission.ACCESS_COARSE_LOCATION;
        this.PERMISSION_POSITION = strArr;
        if (Build.VERSION.SDK_INT >= 31) {
            this.PERMISSION_NEARBY_DEVICE = new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"};
        }
        this.PERMISSION_CAMARE = new String[]{Permission.CAMERA};
        this.PERMISSION_INTERNET = new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"};
    }

    private void initData() {
        if (PermissionUtil.isPermission(this, this.PERMISSION_POSITION) && !this.isHasLocation) {
            WeatherUtils.weatherFunction(getApplicationContext());
        }
        this.permission_switchButton_service.setmSwitchOn(PermissionUtil.isIgnoringBatteryOptimizations(this));
        this.permission_switchButton_call.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_CALL));
        this.permission_switchButton_call_record.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_CALL_RECORD));
        this.permission_switchButton_sms.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_SMS));
        this.permission_switchButton_notify.setmSwitchOn(PermissionUtil.isNotificationEnable(this));
        this.permission_switchButton_contants.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_CONTANTS));
        this.permission_switchButton_sd.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_SD));
        this.permission_switchButton_position.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_POSITION));
        if (Build.VERSION.SDK_INT >= 31) {
            this.permission_switchButton_nearby_device.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_NEARBY_DEVICE));
        } else {
            findViewById(R.id.ly_nearby_device).setVisibility(8);
        }
        this.permission_switchButton_camare.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_CAMARE));
        this.permission_switchButton_internet.setmSwitchOn(PermissionUtil.isPermission(this, this.PERMISSION_INTERNET));
        this.internet_state.setText(NetworkManager.getInstance(this).getInternetState());
        if (Constant.isHealthRing() || Constant.isHeGe()) {
            this.ll_permission_call.setVisibility(8);
            this.ll_permission_call_recode.setVisibility(8);
            this.ll_permission_sms.setVisibility(8);
            this.ll_permission_notify.setVisibility(8);
            this.ll_permission_contacts.setVisibility(8);
        }
    }

    private void initListence() {
        this.permission_background_running.setOnClickListener(new OnClickListenerImpl());
        this.permission_switchButton_service.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_call.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_call_record.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_sms.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_notify.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_contants.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_sd.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_position.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_nearby_device.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_camare.setOnChangeListener(new OnChangeListenerImpl());
        this.permission_switchButton_internet.setOnChangeListener(new OnChangeListenerImpl());
    }

    private void openPermission(Activity context, String[] permissions) {
        ArrayList arrayList = new ArrayList();
        for (String str : permissions) {
            if (!str.isEmpty() && ContextCompat.checkSelfPermission(context, str) != 0) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, str) || !Tools.readBoolean(str, context, false)) {
                    Tools.saveBoolean(str, true, this);
                    arrayList.add(str);
                } else {
                    PermissionUtil.gotoPermission(this);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        ActivityCompat.requestPermissions(context, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
    }

    private class OnChangeListenerImpl implements OnChangeListener {
        private OnChangeListenerImpl() {
        }

        @Override // com.yucheng.smarthealthpro.view.OnChangeListener
        public void onChange(SwitchButton sb, boolean state) {
            if (!state && !"notify".equals(sb.getTag()) && !NotificationCompat.CATEGORY_SERVICE.equals(sb.getTag())) {
                PermissionUtil.gotoPermission(PermissionActivity.this);
            }
            String str = (String) sb.getTag();
            str.hashCode();
            switch (str) {
                case "camare":
                    PermissionActivity permissionActivity = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity, permissionActivity.PERMISSION_CAMARE);
                    break;
                case "notify":
                    PermissionUtil.openNotificationSetting(PermissionActivity.this);
                    break;
                case "contants":
                    PermissionActivity permissionActivity2 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity2, permissionActivity2.PERMISSION_CONTANTS);
                    break;
                case "nearby_device":
                    PermissionActivity permissionActivity3 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity3, permissionActivity3.PERMISSION_NEARBY_DEVICE);
                    break;
                case "call_record":
                    PermissionActivity permissionActivity4 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity4, permissionActivity4.PERMISSION_CALL_RECORD);
                    break;
                case "sd":
                    PermissionActivity permissionActivity5 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity5, permissionActivity5.PERMISSION_SD);
                    break;
                case "sms":
                    PermissionActivity permissionActivity6 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity6, permissionActivity6.PERMISSION_SMS);
                    break;
                case "call":
                    PermissionActivity permissionActivity7 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity7, permissionActivity7.PERMISSION_CALL);
                    break;
                case "internet":
                    PermissionActivity permissionActivity8 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity8, permissionActivity8.PERMISSION_INTERNET);
                    break;
                case "position":
                    PermissionActivity permissionActivity9 = PermissionActivity.this;
                    PermissionUtil.openPermission(permissionActivity9, permissionActivity9.PERMISSION_POSITION);
                    break;
                case "service":
                    if (state) {
                        PermissionUtil.requestIgnoreBatteryOptimizations(PermissionActivity.this);
                        break;
                    } else {
                        PermissionUtil.gotoServicePermission(PermissionActivity.this);
                        break;
                    }
            }
        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intent intent = new Intent(PermissionActivity.this, (Class<?>) AiWebActivity.class);
            intent.putExtra("title", PermissionActivity.this.getString(R.string.setting_permission));
            intent.putExtra("url", "https://staticpage.ycaviation.com/app/permission//runbackground_" + PermissionActivity.this.getString(R.string.lan) + ".html");
            PermissionActivity.this.startActivity(intent);
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        initData();
        WeatherUtils.checkLocation();
    }
}
