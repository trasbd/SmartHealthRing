package com.yucheng.smarthealthpro.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMePushmessageBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.me.adapter.MePushMessageListAdapter;
import com.yucheng.smarthealthpro.me.bean.MePushMessageBean;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class MePushMessageActivity extends BaseVbActivity<ActivityMePushmessageBinding> {
    Handler handler = new Handler() { // from class: com.yucheng.smarthealthpro.me.activity.MePushMessageActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what != 0) {
                return;
            }
            MePushMessageActivity.this.mMePushMessageListAdapter.notifyDataSetChanged();
        }
    };
    private List<MePushMessageBean> mMePushMessageBean;
    private MePushMessageListAdapter mMePushMessageListAdapter;
    RecyclerView mRecyclerView;
    Switch mSwitchMessage;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mSwitchMessage = ((ActivityMePushmessageBinding) this.mBinding).switchMessage;
        this.mRecyclerView = ((ActivityMePushmessageBinding) this.mBinding).recycleView;
        changeTitle(getString(R.string.me_my_device_more_settings_information_push_title));
        showBack();
        showRightText(getString(R.string.push_permission), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePushMessageActivity.2
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                MePushMessageActivity.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        });
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getTitleTextView().getLayoutParams();
        int iDp2px = (int) DpUtil.dp2px(this, 120.0f);
        layoutParams.setMargins(iDp2px, 0, iDp2px, 0);
        getTitleTextView().setLayoutParams(layoutParams);
    }

    private void initData() {
        setBean();
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.PUSH_MESSAGE, Constant.SpConstValue.OPEN);
        if (str != null && !str.isEmpty() && str.equals(Constant.SpConstValue.CLOSE)) {
            this.mSwitchMessage.setChecked(false);
        } else {
            this.mSwitchMessage.setChecked(true);
        }
        this.mSwitchMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePushMessageActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MePushMessageActivity.this.setOpenClose("1");
                } else {
                    MePushMessageActivity.this.setOpenClose("0");
                }
            }
        });
        setRecycleView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOpenClose(String isSwitch) {
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.PUSHMESSAGE, "");
        if (isSwitch.equals("1")) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.PUSH_MESSAGE, Constant.SpConstValue.OPEN);
            setSwatchOpenClose(1, str.substring(0, 8), str.substring(8, 16), str.substring(16, 18));
        } else {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.PUSH_MESSAGE, Constant.SpConstValue.CLOSE);
            setSwatchOpenClose(0, str.substring(0, 8), str.substring(8, 16), str.substring(16, 18));
        }
        setRecycleView();
    }

    private void setBean() {
        List<MePushMessageBean> list = this.mMePushMessageBean;
        if (list == null) {
            this.mMePushMessageBean = new ArrayList();
        } else {
            list.clear();
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCALLPHONE)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_phone), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_phone), 0));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASMESSAGE)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_sms), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_mass), 1));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWECHAT)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_wechat), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_wechat), 3));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASQQ)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_qq), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_qq), 4));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSINA)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_sina), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_weibo), 5));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASFACEBOOK)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_facebook), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_facebook), 6));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTWITTER)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_twitter), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_twitter), 7));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWHATSAPP)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_whatsApp), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_whatsapp), 9));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASMESSENGER)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_messenger), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_messenger), 8));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINSTAGRAM)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_instagram), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_ins), 11));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASLINKEDIN)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_linked_in), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_linked), 10));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSKYPE)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_skype), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_skype), 12));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASLINE)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_line), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_line), 13));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSNAPCHAT)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_snapchat), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_snapchat), 14));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASOTHERMESSENGER)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_telegram), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_telegram), 15));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASEMAIL)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_email), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_email), 2));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASVIBERNOTIFY)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_viber), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_viber), 19));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASZOOMNOTIFY)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_zoom), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_zoom), 16));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTIKTOKNOTIFY)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_tiktok), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_tiktok), 17));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASKAKAOTALKNOTIFY)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_kakaotalk), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_kakaotalk), 18));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASOTHRENOTIFY)) {
            this.mMePushMessageBean.add(new MePushMessageBean(getString(R.string.me_my_device_more_settings_push_message_other), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_push_other), 20));
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MePushMessageListAdapter mePushMessageListAdapter = new MePushMessageListAdapter(R.layout.item_me_push_message, this.context);
        this.mMePushMessageListAdapter = mePushMessageListAdapter;
        mePushMessageListAdapter.addData((Collection) this.mMePushMessageBean);
        this.mRecyclerView.setAdapter(this.mMePushMessageListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mMePushMessageListAdapter.setOnItemClickListener(new MePushMessageListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePushMessageActivity.4
            @Override // com.yucheng.smarthealthpro.me.adapter.MePushMessageListAdapter.OnItemClickListener
            public void onClick(MePushMessageBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MePushMessageListAdapter.OnItemClickListener
            public void onCheckedClick(String mPushMessage) {
                SharedPreferencesUtils.put(MePushMessageActivity.this.context, Constant.SpConstKey.PUSHMESSAGE, mPushMessage);
                MePushMessageActivity.this.setSwatchOpenClose(1, mPushMessage.substring(0, 8), mPushMessage.substring(8, 16), mPushMessage.substring(16, 21));
                MePushMessageActivity.this.handler.sendEmptyMessage(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwatchOpenClose(int on, String mPushMessageOne, String mPushMessageTwo, String mPushMessageThree) {
        try {
            YCBTClient.settingNotify(on, Integer.parseInt(mPushMessageOne, 2), Integer.parseInt(mPushMessageTwo, 2), Integer.parseInt(mPushMessageThree, 2), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MePushMessageActivity.5
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    if (code != 0) {
                        ToastUtil.getInstance(MePushMessageActivity.this.context).toast(MePushMessageActivity.this.getString(R.string.health_set_failed));
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastUtil.getInstance(this.context).toast(getString(R.string.health_set_failed));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permission(new String[]{Permission.READ_PHONE_STATE, Permission.CALL_PHONE, "android.permission.ANSWER_PHONE_CALLS", Permission.READ_CALL_LOG})) {
            return;
        }
        final CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setMessage(getString(R.string.home_premisstion_title)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePushMessageActivity.6
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
                PermissionUtil.gotoPermission(MePushMessageActivity.this.getApplicationContext());
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    private boolean permission(String[] permissions) {
        if (this.context == null || !(this.context instanceof Activity)) {
            return false;
        }
        return openPermission(this.context, permissions);
    }

    private boolean openPermission(Activity context, String[] permissions) {
        ArrayList arrayList = new ArrayList();
        for (String str : permissions) {
            if (!str.isEmpty() && ContextCompat.checkSelfPermission(context, str) != 0) {
                Tools.saveBoolean(str, true, context);
                arrayList.add(str);
            }
        }
        return arrayList.isEmpty();
    }
}
