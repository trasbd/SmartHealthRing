package com.yucheng.smarthealthpro.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityHealthyBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.activity.ecg.activity.AiWebActivity;
import com.yucheng.smarthealthpro.home.adapter.HomeHealthyAdapter;
import com.yucheng.smarthealthpro.home.bean.HomeHealthyBean;
import com.yucheng.smarthealthpro.home.bean.HomeHealthyBeans;
import com.yucheng.smarthealthpro.me.bean.MeHelpModuleBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class HealthyActivity extends BaseVbActivity<ActivityHealthyBinding> {
    EditText etSearch;
    private List<HomeHealthyBeans.DataBean.ItemsBean> itemsBeans;
    ImageView ivSearch;
    private HomeHealthyAdapter mHomeHealthyAdapter;
    private List<HomeHealthyBean> mHomeHealthyBean;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    private String mToken;
    TabLayout tabLayout;
    private int mPage = 1;
    private int pageSize = 10;
    private String moduleId = "";

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mSmartRefreshLayout = ((ActivityHealthyBinding) this.mBinding).srlHealthy;
        this.mRecyclerView = ((ActivityHealthyBinding) this.mBinding).recycleHealthy;
        this.etSearch = ((ActivityHealthyBinding) this.mBinding).etSearch;
        this.ivSearch = ((ActivityHealthyBinding) this.mBinding).ivSearch;
        this.tabLayout = ((ActivityHealthyBinding) this.mBinding).tabLayout;
        this.etSearch.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.HealthyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivSearch.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.HealthyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_healthy_text));
        showBack();
        this.mToken = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, "");
        if (!Tools.readLogin(this)) {
            ToastUtil.getInstance(this).toast(getString(R.string.me_using_help_feed_back_token_null));
            finish();
            return;
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.tabLayout = tabLayout;
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new OnTabSelectedListenerImpl());
        getModuleData();
        this.mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() { // from class: com.yucheng.smarthealthpro.home.activity.HealthyActivity.1
            @Override // com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
            public void onLoadMore(RefreshLayout refreshLayout) {
                HealthyActivity healthyActivity = HealthyActivity.this;
                healthyActivity.getHealthList(healthyActivity.mPage, HealthyActivity.this.pageSize, HealthyActivity.this.moduleId);
                refreshLayout.finishLoadMore();
            }

            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public void onRefresh(RefreshLayout refreshLayout) {
                HealthyActivity.this.mPage = 1;
                HealthyActivity healthyActivity = HealthyActivity.this;
                healthyActivity.getHealthList(healthyActivity.mPage, HealthyActivity.this.pageSize, HealthyActivity.this.moduleId);
                refreshLayout.finishRefresh();
            }
        });
    }

    private void initData() {
        this.itemsBeans = new ArrayList();
        setRecycleView();
        this.mHomeHealthyAdapter.setOnItemClickListener(new HomeHealthyAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.HealthyActivity.2
            @Override // com.yucheng.smarthealthpro.home.adapter.HomeHealthyAdapter.OnItemClickListener
            public void onDelClick(HomeHealthyBeans.DataBean.ItemsBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.HomeHealthyAdapter.OnItemClickListener
            public void onLongClick(HomeHealthyBeans.DataBean.ItemsBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.HomeHealthyAdapter.OnItemClickListener
            public void onClick(HomeHealthyBeans.DataBean.ItemsBean hisSearch, int position) {
                Intent intent = new Intent(HealthyActivity.this, (Class<?>) AiWebActivity.class);
                Logger.d("chong---------url===" + hisSearch.fileUrl);
                intent.putExtra("title", hisSearch.title);
                intent.putExtra("url", hisSearch.fileUrl + ("cn".equals(HealthyActivity.this.getString(R.string.lan)) ? "_cn.html" : "_en.html"));
                HealthyActivity.this.startActivity(intent);
            }
        });
    }

    private void getModuleData() {
        HashMap map = new HashMap();
        map.put("code", MultiLanguageUtils.getHelpLan());
        map.put("moduleType", "20");
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        HttpUtils.getInstance().getMsgAsynHttp(this.context, Constants.module, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.HealthyActivity.3
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.d("chong---------------result==" + result);
                if (result != null) {
                    try {
                        MeHelpModuleBean meHelpModuleBean = (MeHelpModuleBean) new Gson().fromJson(result, MeHelpModuleBean.class);
                        if (meHelpModuleBean == null || meHelpModuleBean.code != 0) {
                            return;
                        }
                        HealthyActivity.this.tabLayout.removeAllTabs();
                        for (MeHelpModuleBean.DataBean dataBean : meHelpModuleBean.data) {
                            HealthyActivity.this.tabLayout.addTab(HealthyActivity.this.tabLayout.newTab().setText(dataBean.name).setTag(dataBean.moduleId));
                        }
                        if (HealthyActivity.this.tabLayout.getTabCount() > 4) {
                            HealthyActivity.this.tabLayout.setTabMode(0);
                        } else {
                            HealthyActivity.this.tabLayout.setTabMode(1);
                        }
                        HealthyActivity.this.mSmartRefreshLayout.autoRefresh();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHealthList(final int page, int pageSize, final String id) {
        HashMap map = new HashMap();
        map.put("code", MultiLanguageUtils.getHelpLan());
        map.put("currentPage", page + "");
        map.put("pageSize", pageSize + "");
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        map.put("moduleId", this.moduleId);
        HttpUtils.getInstance().getMsgAsynHttp(this.context, Constants.healthList, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.HealthyActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                List<List<HomeHealthyBeans.DataBean.ItemsBean>> list;
                if (result != null) {
                    try {
                        HomeHealthyBeans.DataBean dataBean = ((HomeHealthyBeans) new Gson().fromJson(result, HomeHealthyBeans.class)).data;
                        if (dataBean == null || (list = dataBean.items) == null || list.size() == 0 || !id.equals(HealthyActivity.this.moduleId)) {
                            return;
                        }
                        HealthyActivity.this.itemsBeans = list.get(0);
                        if (page == 1) {
                            HealthyActivity.this.mHomeHealthyAdapter.setList(HealthyActivity.this.itemsBeans);
                        } else {
                            HealthyActivity.this.mHomeHealthyAdapter.addData((Collection) HealthyActivity.this.itemsBeans);
                        }
                        HealthyActivity.this.mHomeHealthyAdapter.notifyDataSetChanged();
                        HealthyActivity.this.mPage++;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        HealthyActivity.this.mHomeHealthyAdapter.setList(new ArrayList());
                        HealthyActivity.this.mHomeHealthyAdapter.notifyDataSetChanged();
                        HealthyActivity.this.mPage++;
                    }
                }
            }
        });
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeHealthyAdapter homeHealthyAdapter = new HomeHealthyAdapter(R.layout.item_home_healthy);
        this.mHomeHealthyAdapter = homeHealthyAdapter;
        homeHealthyAdapter.addData((Collection) this.itemsBeans);
        this.mRecyclerView.setAdapter(this.mHomeHealthyAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    public void onViewClicked(View view) {
        if (view.getId() != R.id.et_search && view.getId() == R.id.iv_search) {
            Toast.makeText(this, "搜索", 0).show();
        }
    }

    private class OnTabSelectedListenerImpl implements TabLayout.OnTabSelectedListener {
        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        private OnTabSelectedListenerImpl() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getText() == null) {
                return;
            }
            HealthyActivity.this.moduleId = (String) tab.getTag();
            HealthyActivity.this.mPage = 1;
            HealthyActivity healthyActivity = HealthyActivity.this;
            healthyActivity.getHealthList(healthyActivity.mPage, HealthyActivity.this.pageSize, HealthyActivity.this.moduleId);
        }
    }
}
