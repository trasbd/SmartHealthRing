package com.yucheng.smarthealthpro.sport.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbFragment;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.database.room.bean.SportRecord;
import com.yucheng.smarthealthpro.databinding.FragmentSportBinding;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.DeviceListActivity;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.sport.SportType;
import com.yucheng.smarthealthpro.sport.activity.SportCountDownActivity;
import com.yucheng.smarthealthpro.sport.activity.SportRecord2Activity;
import com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity;
import com.yucheng.smarthealthpro.sport.adapter.SportHisListAdapter;
import com.yucheng.smarthealthpro.sport.adapter.TabFragmentStatePagerAdapter;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.sport.bean.SportTabItem;
import com.yucheng.smarthealthpro.sport.utils.GetGPSUtil;
import com.yucheng.smarthealthpro.sport.utils.GoogleUtil;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.sport.view.NoScrollSwipeRecyclerView;
import com.yucheng.smarthealthpro.sport.weathers.WeatherUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.viewmodel.SportRecordViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class SportFragment extends BaseVbFragment<FragmentSportBinding> {
    ImageView ivNoHis;
    ImageView ivStartSport;
    ImageView ivState;
    ImageView ivWeather;
    private TabFragmentStatePagerAdapter mAdapter;
    NoScrollSwipeRecyclerView mRecyclerView;
    private SportHisListAdapter mSportHisListAdapter;
    private List<SportHisListBean> mSportHisListBean;
    private SportRecordViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private int mWatchesState;
    private int selectedPosition;
    TabLayout tabLayout;
    TextView tvAddress;
    TextView tvTemp;
    TextView tvTempBetween;
    private List<SportTabItem> mTitles = new ArrayList();
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.7
        @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
            swipeRightMenu.addMenuItem(new SwipeMenuItem(SportFragment.this.context).setBackground(R.drawable.selector_red).setText(SportFragment.this.getString(R.string.delete)).setTextColor(-1).setWidth(SportFragment.this.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
        }
    };
    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.8
        @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection();
            menuBridge.getPosition();
            if (direction == -1) {
                SportFragment.this.mViewModel.deleteSportRecord(SportFragment.this.mSportHisListAdapter.getItem(position).getBeginDate());
                SportFragment.this.mSportHisListAdapter.remove(position);
                SportFragment.this.mSportHisListAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.tabLayout = getMViewBind().tabLayout;
        this.ivStartSport = getMViewBind().ivStartSport;
        this.ivState = getMViewBind().ivState;
        this.ivWeather = getMViewBind().ivWeather;
        this.tvTemp = getMViewBind().tvTemp;
        this.tvAddress = getMViewBind().tvAddress;
        this.tvTempBetween = getMViewBind().tvTempBetween;
        this.mViewPager = getMViewBind().vpCommon;
        this.mRecyclerView = getMViewBind().recycleView;
        this.ivNoHis = getMViewBind().ivNoHis;
        this.ivStartSport.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        changeTitle(getString(R.string.sport_title));
        showLeftImage(R.mipmap.icon_sp_hist, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                SportFragment.this.startActivity(new Intent(SportFragment.this.context, (Class<?>) SportRecord2Activity.class));
            }
        });
        initViewModel();
    }

    private void initViewModel() {
        this.mViewModel = (SportRecordViewModel) new ViewModelProvider(this).get(SportRecordViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSportRecordDataFlow(), new FlowUtils.FlowCollector<HealthDayData<SportRecord>>() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<SportRecord> data) {
                SportFragment sportFragment = SportFragment.this;
                sportFragment.onSportData(((SportTabItem) sportFragment.mTitles.get(SportFragment.this.tabLayout.getSelectedTabPosition())).sportType, data.getData());
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
        this.mSportHisListBean = new ArrayList();
        this.mViewModel.getAllData();
        setRecycleView();
        initTable();
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, com.gyf.immersionbar.components.ImmersionOwner
    public void onVisible() {
        super.onVisible();
        getWeather();
        setAdapter();
        freshConnectIcon();
    }

    private void getWeather() {
        if (getActivity() == null) {
            return;
        }
        if ("-".equals(this.tvAddress.getText().toString()) || "-".equals(this.tvTemp.getText().toString()) || "-/-".equals(this.tvTempBetween.getText().toString())) {
            WeatherUtils.weatherFunction(getActivity(), this.tvTemp, this.tvAddress, this.tvTempBetween, this.ivWeather);
        } else {
            WeatherUtils.weatherFunction(getActivity());
        }
    }

    private void addTitle() {
        List<SportTabItem> list = this.mTitles;
        if (list == null) {
            this.mTitles = new ArrayList();
        } else {
            list.clear();
        }
        if (YCBTClient.connectState() == 10) {
            this.mTitles.addAll(SportType.getAllSportItem(requireContext()));
        } else {
            this.mTitles.add(new SportTabItem(getString(R.string.sport_running), 1));
            this.mTitles.add(new SportTabItem(getString(R.string.sport_riding), 3));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    private void initViewPager() {
        TabFragmentStatePagerAdapter tabFragmentStatePagerAdapter = new TabFragmentStatePagerAdapter(getChildFragmentManager(), new TabFragmentStatePagerAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.3
            @Override // com.yucheng.smarthealthpro.sport.adapter.TabFragmentStatePagerAdapter.FragmentCreator
            public Fragment createFragment(SportTabItem item, int position) {
                return SportTabFragment.newInstance(item);
            }

            @Override // com.yucheng.smarthealthpro.sport.adapter.TabFragmentStatePagerAdapter.FragmentCreator
            public String createTitle(SportTabItem item) {
                return item.title;
            }
        });
        this.mAdapter = tabFragmentStatePagerAdapter;
        this.mViewPager.setAdapter(tabFragmentStatePagerAdapter);
        this.mViewPager.setOffscreenPageLimit(this.mTitles.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.tabLayout.setupWithViewPager(this.mViewPager);
        this.tabLayout.setSelectedTabIndicatorGravity(0);
        if (this.mTitles.size() > 4) {
            this.tabLayout.setTabMode(0);
        } else {
            this.tabLayout.setTabMode(1);
        }
        this.tabLayout.setSelectedTabIndicator(AppCompatResources.getDrawable(requireContext(), R.drawable.tab_indicator));
        this.tabLayout.setTabIndicatorFullWidth(false);
        this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.4
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == null || SportFragment.this.mTitles == null) {
                    return;
                }
                tab.getPosition();
                SportFragment.this.mViewModel.getAllData();
            }
        });
        setupTabView();
    }

    public void setupTabView() {
        for (int i2 = 0; i2 < this.tabLayout.getTabCount(); i2++) {
            TabLayout.Tab tabAt = this.tabLayout.getTabAt(i2);
            if (tabAt != null) {
                SportTabItem sportTabItem = this.mTitles.get(i2);
                tabAt.setCustomView(R.layout.item_tab);
                ((TextView) tabAt.getCustomView().findViewById(R.id.tab_item_textview)).setText("" + sportTabItem.title);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSportData(int type, List<SportRecord> data) {
        List<SportHisListBean> list = this.mSportHisListBean;
        if (list != null) {
            list.clear();
        }
        if (data != null && data.size() != 0) {
            for (int i2 = 0; i2 < data.size(); i2++) {
                if (data.get(i2).getType() == type) {
                    this.mSportHisListBean.add(new SportHisListBean(data.get(i2).getType(), data.get(i2).getBeginDate(), data.get(i2).getTimeYearToDay(), data.get(i2).getTotalDistance(), data.get(i2).getTotalCalories(), data.get(i2).getMinkm(), data.get(i2).getHeartRate(), data.get(i2).getRunDuration(), data.get(i2).getKmh(), data.get(i2).getStartPoint(), data.get(i2).getEndPoint(), data.get(i2).getPathLinePoints(), Boolean.valueOf(data.get(i2).isUploaded()), data.get(i2).getTotalSteps()));
                }
            }
        }
        Collections.reverse(this.mSportHisListBean);
        if (this.mSportHisListBean.size() != 0) {
            this.mRecyclerView.setVisibility(0);
            this.ivNoHis.setVisibility(8);
        } else {
            this.ivNoHis.setVisibility(0);
            this.mRecyclerView.setVisibility(8);
        }
        this.mSportHisListAdapter.setList(this.mSportHisListBean);
        this.mSportHisListAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        int i2 = messageEvent.belState;
        if (i2 == 0) {
            this.mWatchesState = 3;
            initTable();
        } else if (i2 == 1) {
            this.mWatchesState = 1;
            initTable();
        } else if (i2 == 2) {
            this.mWatchesState = 2;
        }
        freshConnectIcon();
    }

    private void freshConnectIcon() {
        int iConnectState = YCBTClient.connectState();
        if (iConnectState == 1 || iConnectState == 2 || iConnectState == 3 || iConnectState == 4) {
            if (Constant.isRing()) {
                this.ivState.setImageResource(R.mipmap.ic_device_ring_0);
                return;
            } else {
                this.ivState.setImageResource(R.mipmap.home_icon_btoff);
                return;
            }
        }
        if (iConnectState == 10) {
            if (Constant.isRing()) {
                this.ivState.setImageResource(R.mipmap.ic_device_ring_1);
                return;
            } else {
                this.ivState.setImageResource(R.mipmap.home_icon_bt);
                return;
            }
        }
        if (Constant.isRing()) {
            this.ivState.setImageResource(R.mipmap.ic_device_ring_0);
        } else {
            this.ivState.setImageResource(R.mipmap.home_icon_btoff_gr);
        }
    }

    private void initDialog() {
        final CommonDialog commonDialog = new CommonDialog(this.context);
        commonDialog.setMessage(getString(R.string.sport_running_connect_dialog_message)).setTitle(getString(R.string.prompt)).setConfirm(getString(R.string.sport_running_connect_dialog_confirm)).setCancel(getString(R.string.include_bottom_tv_start_button)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.5
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                if (SportFragment.this.getActivity() != null) {
                    ((MainActivity) SportFragment.this.getActivity()).isShowBluetoothDialog = false;
                }
                SportFragment.this.startActivity(new Intent(SportFragment.this.getContext(), (Class<?>) DeviceListActivity.class));
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA)) {
                    ((MainActivity) SportFragment.this.getActivity()).isNeedStopStep = false;
                }
                SportFragment.this.startActivity(new Intent(SportFragment.this.context, (Class<?>) SportCountDownActivity.class).putExtra("data", (Serializable) SportFragment.this.mTitles.get(SportFragment.this.tabLayout.getSelectedTabPosition())));
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.mRecyclerView.setSwipeMenuCreator(this.swipeMenuCreator);
        this.mRecyclerView.setOnItemMenuClickListener(this.mMenuItemClickListener);
        SportHisListAdapter sportHisListAdapter = new SportHisListAdapter(R.layout.item_sport_his_list);
        this.mSportHisListAdapter = sportHisListAdapter;
        sportHisListAdapter.addData((Collection) this.mSportHisListBean);
        this.mRecyclerView.setAdapter(this.mSportHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(this.mRecyclerView, false);
        this.mSportHisListAdapter.setOnItemClickListener(new SportHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.sport.fragment.SportFragment.6
            @Override // com.yucheng.smarthealthpro.sport.adapter.SportHisListAdapter.OnItemClickListener
            public void onClick(SportHisListBean hisSearch, int position) {
                if (GoogleUtil.checkGoogleAvailable()) {
                    Intent intent = new Intent(SportFragment.this.context, (Class<?>) SportRunningHisMapActivity.class);
                    intent.putExtra("hislist", hisSearch);
                    intent.putExtra("map", "googleMap");
                    SportFragment.this.context.startActivity(intent);
                    return;
                }
                Intent intent2 = new Intent(SportFragment.this.context, (Class<?>) SportRunningHisMapActivity.class);
                intent2.putExtra("hislist", hisSearch);
                intent2.putExtra("map", "aMap");
                SportFragment.this.context.startActivity(intent2);
            }

            @Override // com.yucheng.smarthealthpro.sport.adapter.SportHisListAdapter.OnItemClickListener
            public void onDeleteClick(SportHisListBean hisSearch, int position) {
                SportFragment.this.mSportHisListAdapter.remove(position);
                SportFragment.this.mSportHisListAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onViewClicked(View view) {
        if (getActivity() == null || !Tools.checkPermiss(getActivity())) {
            return;
        }
        if (!GetGPSUtil.isOpenGpsService(getActivity())) {
            GetGPSUtil.showLocation(getActivity());
            return;
        }
        if (YCBTClient.connectState() != 10) {
            initDialog();
            return;
        }
        if (this.mTitles.size() <= 0 || this.mTitles.size() <= this.tabLayout.getSelectedTabPosition() || this.tabLayout.getSelectedTabPosition() < 0) {
            return;
        }
        if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA)) {
            ((MainActivity) getActivity()).isNeedStopStep = false;
        }
        Intent intent = new Intent(this.context, (Class<?>) SportCountDownActivity.class);
        intent.putExtra("data", this.mTitles.get(this.tabLayout.getSelectedTabPosition()));
        startActivity(intent);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    private synchronized void initTable() {
        addTitle();
        initViewPager();
        setAdapter();
    }

    private void setAdapter() {
        List<SportTabItem> list = this.mTitles;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mViewModel.getAllData();
    }
}
