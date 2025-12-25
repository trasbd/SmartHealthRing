package com.yucheng.smarthealthpro.sport.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.yucheng.smarthealthpro.sport.bean.SportTabItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class TabFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private FragmentCreator mCreator;
    private List<SportTabItem> mTitles;
    private SparseArray<Fragment> registeredFragments;

    public interface FragmentCreator {
        Fragment createFragment(SportTabItem item, int position);

        String createTitle(SportTabItem item);
    }

    public TabFragmentStatePagerAdapter(FragmentManager fm, FragmentCreator creator) {
        super(fm);
        this.registeredFragments = new SparseArray<>();
        this.mTitles = new ArrayList();
        this.mCreator = creator;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i2) {
        return this.mCreator.createFragment(this.mTitles.get(i2), i2);
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.registeredFragments.put(position, fragment);
        return fragment;
    }

    public Fragment getFragment(int position) {
        return this.registeredFragments.get(position);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<SportTabItem> list = this.mTitles;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<SportTabItem> mTitles) {
        this.mTitles = mTitles;
        notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.mCreator.createTitle(this.mTitles.get(position));
    }
}
