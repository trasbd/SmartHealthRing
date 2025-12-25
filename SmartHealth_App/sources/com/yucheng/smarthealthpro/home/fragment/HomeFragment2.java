package com.yucheng.smarthealthpro.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbFragment;
import com.yucheng.smarthealthpro.databinding.FragmentOneBakBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.DeviceListActivity;
import com.yucheng.smarthealthpro.login.LoginActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.CommonAction;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.YCBTClient;
import java.text.ParseException;

/* loaded from: classes5.dex */
public class HomeFragment2 extends BaseVbFragment<FragmentOneBakBinding> {
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) throws ParseException {
        changeTitle(getString(R.string.home_title));
        ImageView imageView = (ImageView) view.findViewById(R.id.mainfragment_bt_state);
        View viewFindViewById = view.findViewById(R.id.ll_exit_login);
        if (Constant.isHeGe()) {
            imageView.setImageResource(R.mipmap.ic_device_ring_0);
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                HomeFragment2.this.startActivity(new Intent(HomeFragment2.this.getContext(), (Class<?>) DeviceListActivity.class));
            }
        });
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment2.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                HomeFragment2.this.initExitLoginDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initExitLoginDialog() {
        final CommonDialog commonDialog = new CommonDialog(this.context);
        commonDialog.setMessage(getString(R.string.me_security_settings_exit_the_login_dialog_message)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment2.3
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
                HomeFragment2.this.loginOutDone();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void loginOutDone() {
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.IS_LOGIN, false);
        YCBTClient.disconnectBle();
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.IMAGE_PATH);
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.HEAD_IMG);
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.TOKEN);
        startActivity(new Intent(this.context, (Class<?>) LoginActivity.class).setFlags(335544320));
        CommonAction.getInstance().OutSign();
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }
}
