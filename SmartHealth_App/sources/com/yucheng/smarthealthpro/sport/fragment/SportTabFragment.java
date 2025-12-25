package com.yucheng.smarthealthpro.sport.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.databinding.FragmentSportTabBinding;
import com.yucheng.smarthealthpro.sport.bean.SportTabItem;
import java.io.Serializable;
import java.text.ParseException;

/* loaded from: classes5.dex */
public class SportTabFragment extends BaseLazyLoadFragment<FragmentSportTabBinding> {
    private SportTabItem item;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) throws ParseException {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() {
    }

    public static SportTabFragment newInstance(SportTabItem item) {
        SportTabFragment sportTabFragment = new SportTabFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", item);
        sportTabFragment.setArguments(bundle);
        return sportTabFragment;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Serializable serializable;
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments == null || (serializable = arguments.getSerializable("data")) == null) {
            return;
        }
        this.item = (SportTabItem) serializable;
    }
}
