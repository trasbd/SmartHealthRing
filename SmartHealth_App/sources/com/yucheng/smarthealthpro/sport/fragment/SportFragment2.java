package com.yucheng.smarthealthpro.sport.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbFragment;
import com.yucheng.smarthealthpro.databinding.FragmentTwoBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import java.text.ParseException;

/* loaded from: classes5.dex */
public class SportFragment2 extends BaseVbFragment<FragmentTwoBinding> {
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) throws ParseException {
        changeTitle(getString(R.string.sport_title));
        view.findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) throws Resources.NotFoundException {
                ToastUtil.getInstance().toast(R.string.please_connect_device);
            }
        });
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }
}
