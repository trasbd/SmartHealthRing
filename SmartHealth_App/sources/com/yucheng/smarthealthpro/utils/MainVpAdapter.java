package com.yucheng.smarthealthpro.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/* loaded from: classes5.dex */
public class MainVpAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments;

    public MainVpAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i2) {
        return this.mFragments.get(i2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mFragments.size();
    }
}
