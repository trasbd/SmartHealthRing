package com.yucheng.smarthealthpro.care.fragment;

import android.content.Context;
import android.view.View;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbFragment;
import com.yucheng.smarthealthpro.databinding.FragmentCare2Binding;
import com.yucheng.smarthealthpro.utils.Tools;
import java.text.ParseException;

/* loaded from: classes4.dex */
public class CareFragment2 extends BaseVbFragment<FragmentCare2Binding> {
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) throws ParseException {
        changeTitle(getString(R.string.care_title));
        view.findViewById(R.id.lay_login).setVisibility(Tools.readLogin(getActivity()) ? 8 : 0);
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }
}
