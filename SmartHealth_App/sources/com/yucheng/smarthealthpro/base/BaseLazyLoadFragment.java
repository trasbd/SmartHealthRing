package com.yucheng.smarthealthpro.base;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class BaseLazyLoadFragment<VB extends ViewBinding> extends BaseVbFragment<VB> {
    private boolean isDataLoaded;
    private boolean isViewCreated;
    private boolean isVisibleToUser;

    protected abstract void lazyLoadData();

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.isViewCreated = true;
        tryLoadData();
    }

    private boolean isParentVisible() {
        Fragment parentFragment = getParentFragment();
        return parentFragment == null || ((parentFragment instanceof BaseLazyLoadFragment) && ((BaseLazyLoadFragment) parentFragment).isVisibleToUser);
    }

    private void dispatchParentVisibleState() {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment instanceof BaseLazyLoadFragment) {
                BaseLazyLoadFragment baseLazyLoadFragment = (BaseLazyLoadFragment) fragment;
                if (baseLazyLoadFragment.isVisibleToUser) {
                    baseLazyLoadFragment.tryLoadData();
                }
            }
        }
    }

    public void tryLoadData() {
        if (this.isViewCreated && this.isVisibleToUser && isParentVisible() && !this.isDataLoaded) {
            lazyLoadData();
            this.isDataLoaded = true;
            dispatchParentVisibleState();
        }
    }
}
