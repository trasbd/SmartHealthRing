package com.yucheng.smarthealthpro.home.activity.pdnumber.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPdnumberBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.pdnumber.adapter.PDNumberHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.pdnumber.adapter.PDNumberTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.pdnumber.fragment.PDNumberTabFragment;
import com.yucheng.smarthealthpro.home.bean.MyMonBean;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeZoneUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class PDNumberActivity extends BaseVbActivity<ActivityPdnumberBinding> {
    private PDNumberTabFragmentAdapter mAdapter;
    NestedScrollView mNestedScrollView;
    private PDNumberHisListAdapter mPDNumberHisListAdapter;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    NoScrollViewPager mViewPager;
    private List<String> mTitles = new ArrayList();
    private List<MyMonBean.Data.Values> dayDatas = new ArrayList();
    private List<MyMonBean.Data.Values> weekDatas = new ArrayList();
    private List<MyMonBean.Data.Values> monthDatas = new ArrayList();
    private List<MyMonBean.Data.Values> halfYearDatas = new ArrayList();
    private List<MyMonBean.Data.Values> yearDatas = new ArrayList();
    private List<MyMonBean.Data.Values> myMondatas = new ArrayList();
    private String unit = "mm/kg";
    private Map<Integer, Boolean> isSuccess = new HashMap();

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivityPdnumberBinding) this.mBinding).recycleView;
        this.mViewPager = ((ActivityPdnumberBinding) this.mBinding).vpTab;
        this.mSlidingTabLayout = ((ActivityPdnumberBinding) this.mBinding).stlTab;
        this.mNestedScrollView = ((ActivityPdnumberBinding) this.mBinding).nsl;
        changeTitle(getIntent().getStringExtra("title"));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.pdnumber.activity.PDNumberActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (PDNumberActivity.this.checkCanClick()) {
                    ShareUtils.share(PDNumberActivity.this);
                }
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    private void initData() throws Resources.NotFoundException {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mTitles.clear();
        this.mTitles.add(getString(R.string.pd_day_text));
        this.mTitles.add(getString(R.string.pd_week_text));
        this.mTitles.add(getString(R.string.pd_month_text));
        this.mTitles.add(getString(R.string.pd_halfyear_text));
        this.mTitles.add(getString(R.string.pd_year_text));
        initViewPager();
        for (int i2 = 1; i2 <= 5; i2++) {
            this.isSuccess.put(Integer.valueOf(i2), false);
        }
        for (int i3 = 1; i3 <= 5; i3++) {
            getPDNumberData(i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        PDNumberTabFragmentAdapter pDNumberTabFragmentAdapter = new PDNumberTabFragmentAdapter(getSupportFragmentManager(), new PDNumberTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.pdnumber.activity.PDNumberActivity.2
            @Override // com.yucheng.smarthealthpro.home.activity.pdnumber.adapter.PDNumberTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return PDNumberTabFragment.newInstance(Html.fromHtml(data).toString(), position, PDNumberActivity.this.mNestedScrollView, PDNumberActivity.this.dayDatas, PDNumberActivity.this.weekDatas, PDNumberActivity.this.monthDatas, PDNumberActivity.this.halfYearDatas, PDNumberActivity.this.yearDatas, PDNumberActivity.this.unit);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.pdnumber.adapter.PDNumberTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = pDNumberTabFragmentAdapter;
        this.mViewPager.setAdapter(pDNumberTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mTitles.size());
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(0, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.pdnumber.activity.PDNumberActivity.3
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (position == 0) {
                    PDNumberActivity pDNumberActivity = PDNumberActivity.this;
                    pDNumberActivity.myMondatas = pDNumberActivity.dayDatas;
                } else if (position == 1) {
                    PDNumberActivity pDNumberActivity2 = PDNumberActivity.this;
                    pDNumberActivity2.myMondatas = pDNumberActivity2.weekDatas;
                } else if (position == 2) {
                    PDNumberActivity pDNumberActivity3 = PDNumberActivity.this;
                    pDNumberActivity3.myMondatas = pDNumberActivity3.monthDatas;
                } else if (position == 3) {
                    PDNumberActivity pDNumberActivity4 = PDNumberActivity.this;
                    pDNumberActivity4.myMondatas = pDNumberActivity4.halfYearDatas;
                } else if (position == 4) {
                    PDNumberActivity pDNumberActivity5 = PDNumberActivity.this;
                    pDNumberActivity5.myMondatas = pDNumberActivity5.yearDatas;
                }
                if (PDNumberActivity.this.myMondatas != null) {
                    PDNumberActivity.this.setRecycleView();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRecycleView() {
        PDNumberHisListAdapter pDNumberHisListAdapter = this.mPDNumberHisListAdapter;
        if (pDNumberHisListAdapter == null) {
            PDNumberHisListAdapter pDNumberHisListAdapter2 = new PDNumberHisListAdapter(R.layout.item_universal_his_list);
            this.mPDNumberHisListAdapter = pDNumberHisListAdapter2;
            pDNumberHisListAdapter2.setUnit(this.unit);
            this.mPDNumberHisListAdapter.addData((Collection) this.myMondatas);
            this.mRecyclerView.setAdapter(this.mPDNumberHisListAdapter);
            return;
        }
        pDNumberHisListAdapter.setUnit(this.unit);
        this.mPDNumberHisListAdapter.setList(this.myMondatas);
        this.mPDNumberHisListAdapter.notifyDataSetChanged();
    }

    private void getPDNumberData(final int type) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("zone", TimeZoneUtils.getTimeZoneOffset());
        map.put("qtype", type + "");
        HttpUtils.getInstance().getMsgAsynHttp(this, Constants.GETPDNUMBER, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.pdnumber.activity.PDNumberActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws Resources.NotFoundException {
                PDNumberActivity.this.isSuccess.put(Integer.valueOf(type), true);
                List arrayList = new ArrayList();
                try {
                    try {
                        MyMonBean myMonBean = (MyMonBean) new Gson().fromJson(result, MyMonBean.class);
                        if (myMonBean != null && myMonBean.data != null) {
                            arrayList = Tools.sortListPDNumber(myMonBean.data.values);
                            PDNumberActivity.this.unit = myMonBean.data.displayunits;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        int i2 = type;
                        if (i2 != 1) {
                            if (i2 != 2) {
                                if (i2 != 3) {
                                    if (i2 != 4) {
                                        if (i2 == 5) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Iterator it2 = PDNumberActivity.this.isSuccess.entrySet().iterator();
                    while (it2.hasNext()) {
                        if (!((Boolean) ((Map.Entry) it2.next()).getValue()).booleanValue()) {
                            return;
                        }
                    }
                    PDNumberActivity.this.initViewPager();
                    PDNumberActivity.this.setRecycleView();
                } finally {
                    int i3 = type;
                    if (i3 == 1) {
                        PDNumberActivity.this.dayDatas = arrayList;
                        PDNumberActivity pDNumberActivity = PDNumberActivity.this;
                        pDNumberActivity.myMondatas = pDNumberActivity.dayDatas;
                    } else if (i3 == 2) {
                        PDNumberActivity.this.weekDatas = arrayList;
                    } else if (i3 == 3) {
                        PDNumberActivity.this.monthDatas = arrayList;
                    } else if (i3 == 4) {
                        PDNumberActivity.this.halfYearDatas = arrayList;
                    } else if (i3 == 5) {
                        PDNumberActivity.this.yearDatas = arrayList;
                    }
                }
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
