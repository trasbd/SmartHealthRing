package com.yucheng.smarthealthpro.home.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityRankingBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.adapter.HomeRankingAdapter;
import com.yucheng.smarthealthpro.home.bean.HomeRankingBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.view.CircleImageView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
public class RankingActivity extends BaseVbActivity<ActivityRankingBinding> {
    private List<HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean> items;
    CircleImageView ivHeadOne;
    CircleImageView ivHeadPortrait;
    private HomeRankingAdapter mHomeRankingAdapter;
    private HomeRankingBean.DataBean.MyRankInfoBean mMyRankInfoBean;
    private HomeRankingBean.DataBean.OtherRankInfoBean mOtherRankInfoBean;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    private String mToken;
    RelativeLayout rlRankingMe;
    RelativeLayout rvDialog;
    TextView tvLikeNumber;
    TextView tvName;
    TextView tvRanking;
    TextView tvStepNumber;
    private int mPage = 1;
    private int pageSize = 30;
    Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.home.activity.RankingActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (RankingActivity.this.mMyRankInfoBean == null || RankingActivity.this.mMyRankInfoBean.getRanking() == null || RankingActivity.this.mMyRankInfoBean.getRanking().equals("")) {
                    RankingActivity.this.rlRankingMe.setVisibility(8);
                    return;
                }
                RankingActivity.this.rlRankingMe.setVisibility(0);
                String ranking = RankingActivity.this.mMyRankInfoBean.getRanking();
                if (!ranking.isEmpty() && !ranking.equals("")) {
                    RankingActivity.this.tvRanking.setText(((int) Float.parseFloat(ranking)) + "");
                } else {
                    RankingActivity.this.tvRanking.setText("");
                }
                RankingActivity.this.tvLikeNumber.setText("".equals(RankingActivity.this.mMyRankInfoBean.getLikedCount()) ? "0" : RankingActivity.this.mMyRankInfoBean.getLikedCount());
                RankingActivity.this.tvStepNumber.setText("".equals(RankingActivity.this.mMyRankInfoBean.getRankingList()) ? "0" : RankingActivity.this.mMyRankInfoBean.getRankingList());
                return;
            }
            if (msg.what == 2) {
                if (RankingActivity.this.items == null || RankingActivity.this.items.size() == 0) {
                    return;
                }
                RankingActivity.this.mHomeRankingAdapter.addData((Collection) RankingActivity.this.items);
                RankingActivity.this.mHomeRankingAdapter.notifyDataSetChanged();
                RankingActivity.this.mPage++;
                return;
            }
            if (msg.what == 3) {
                if (RankingActivity.this.items == null || RankingActivity.this.items.size() == 0) {
                    return;
                }
                RankingActivity.this.mHomeRankingAdapter.replaceData(RankingActivity.this.items);
                RankingActivity.this.mHomeRankingAdapter.notifyDataSetChanged();
                RankingActivity.this.mPage++;
                return;
            }
            if (msg.what != 4 || RankingActivity.this.items == null || RankingActivity.this.items.size() == 0) {
                return;
            }
            Glide.with(RankingActivity.this.context).load(((HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean) RankingActivity.this.items.get(0)).getHeadImg()).placeholder(R.mipmap.icon_head).error(R.mipmap.icon_head).into(RankingActivity.this.ivHeadOne);
        }
    };

    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mSmartRefreshLayout = ((ActivityRankingBinding) this.mBinding).srlRanking;
        this.ivHeadPortrait = ((ActivityRankingBinding) this.mBinding).ivHeadPortrait;
        this.tvName = ((ActivityRankingBinding) this.mBinding).tvName;
        this.tvRanking = ((ActivityRankingBinding) this.mBinding).tvRanking;
        this.tvStepNumber = ((ActivityRankingBinding) this.mBinding).tvStepNumber;
        this.tvLikeNumber = ((ActivityRankingBinding) this.mBinding).tvLikeNumber;
        this.ivHeadOne = ((ActivityRankingBinding) this.mBinding).ivHeadOne;
        this.mRecyclerView = ((ActivityRankingBinding) this.mBinding).recycleRanking;
        this.rlRankingMe = ((ActivityRankingBinding) this.mBinding).rlRankingMe;
        this.rvDialog = ((ActivityRankingBinding) this.mBinding).rvDialog.rvDialog;
        changeTitle(getString(R.string.home_ranking_title));
        showBack();
        this.mToken = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, "");
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.NICK_NAME, "");
        if (str != null && str.length() > 1) {
            this.tvName.setText(str);
        }
        String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IMAGE_PATH, "");
        if (str2 == null || str2.length() < 2) {
            str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEAD_IMG, "");
        }
        if (str2 != null && str2.length() > 1) {
            Glide.with(this.context).load(str2).into(this.ivHeadPortrait);
        }
        this.items = new ArrayList();
        setRecycleView();
        this.mSmartRefreshLayout.autoRefresh();
        this.mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() { // from class: com.yucheng.smarthealthpro.home.activity.RankingActivity.2
            @Override // com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
            public void onLoadMore(RefreshLayout refreshLayout) {
                RankingActivity rankingActivity = RankingActivity.this;
                rankingActivity.getRankingList(rankingActivity.mPage, RankingActivity.this.pageSize, false);
                refreshLayout.finishLoadMore();
            }

            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public void onRefresh(RefreshLayout refreshLayout) {
                RankingActivity.this.mPage = 1;
                RankingActivity rankingActivity = RankingActivity.this;
                rankingActivity.getRankingList(rankingActivity.mPage, RankingActivity.this.pageSize, true);
                refreshLayout.finishRefresh();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRankingList(int page, int pageSize, final boolean isLoad) {
        Log.i("AAAAAAA", "======" + page + "======" + pageSize);
        HashMap map = new HashMap();
        map.put("currentPage", page + "");
        map.put("pageSize", pageSize + "");
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        HttpUtils.getInstance().getMsgAsynHttp(this.context, Constants.rankingList, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.RankingActivity.3
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        HomeRankingBean.DataBean data = ((HomeRankingBean) new Gson().fromJson(result, HomeRankingBean.class)).getData();
                        if (data != null) {
                            if (isLoad) {
                                RankingActivity.this.mMyRankInfoBean = data.getMyRankInfo();
                                if (RankingActivity.this.mMyRankInfoBean != null) {
                                    RankingActivity.this.mHandler.sendEmptyMessage(1);
                                }
                                RankingActivity.this.mOtherRankInfoBean = data.getOtherRankInfo();
                                RankingActivity rankingActivity = RankingActivity.this;
                                rankingActivity.items = rankingActivity.mOtherRankInfoBean.getItems();
                                RankingActivity.this.mHandler.sendEmptyMessage(3);
                                RankingActivity.this.mHandler.sendEmptyMessage(4);
                                return;
                            }
                            RankingActivity.this.mMyRankInfoBean = data.getMyRankInfo();
                            if (RankingActivity.this.mMyRankInfoBean != null) {
                                RankingActivity.this.mHandler.sendEmptyMessage(1);
                            }
                            RankingActivity.this.mOtherRankInfoBean = data.getOtherRankInfo();
                            RankingActivity rankingActivity2 = RankingActivity.this;
                            rankingActivity2.items = rankingActivity2.mOtherRankInfoBean.getItems();
                            if (RankingActivity.this.items == null || RankingActivity.this.items.size() == 0 || RankingActivity.this.mPage > RankingActivity.this.mOtherRankInfoBean.getCurrentPage()) {
                                return;
                            }
                            RankingActivity.this.mHandler.sendEmptyMessage(2);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRankingLike(final HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch, final int type, String userId) {
        this.rvDialog.setVisibility(0);
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        map.put("type", type + "");
        map.put("userId", userId);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.rankingLikeList, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.RankingActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (RankingActivity.this.isDestroyed()) {
                    return;
                }
                if (result != null) {
                    if (type == 0) {
                        hisSearch.setLikedCount((Integer.parseInt(hisSearch.getLikedCount()) + 1) + "");
                    } else {
                        hisSearch.setLikedCount((Integer.parseInt(hisSearch.getLikedCount()) - 1) + "");
                    }
                    RankingActivity.this.mHomeRankingAdapter.notifyDataSetChanged();
                }
                if (RankingActivity.this.rvDialog != null) {
                    RankingActivity.this.rvDialog.setVisibility(8);
                }
            }
        });
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeRankingAdapter homeRankingAdapter = new HomeRankingAdapter(R.layout.item_home_ranking, this);
        this.mHomeRankingAdapter = homeRankingAdapter;
        homeRankingAdapter.addData((Collection) this.items);
        this.mRecyclerView.setAdapter(this.mHomeRankingAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mHomeRankingAdapter.setOnItemClickListener(new HomeRankingAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.RankingActivity.5
            @Override // com.yucheng.smarthealthpro.home.adapter.HomeRankingAdapter.OnItemClickListener
            public void onClick(HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch, int position) {
                if (Objects.equals(hisSearch.getUserId(), RankingActivity.this.getIntent().getStringExtra(Constant.SpConstKey.DEV_ID) == null ? (String) SharedPreferencesUtils.get(RankingActivity.this.context, Constant.SpConstKey.DEV_ID, "1") : RankingActivity.this.getIntent().getStringExtra(Constant.SpConstKey.DEV_ID)) || RankingActivity.this.rvDialog.getVisibility() == 0) {
                    return;
                }
                if (hisSearch.getIsLike() == 1) {
                    hisSearch.setIsLike(0);
                } else {
                    hisSearch.setIsLike(1);
                }
                RankingActivity.this.setRankingLike(hisSearch, hisSearch.getIsLike(), hisSearch.getUserId());
                RankingActivity.this.mHomeRankingAdapter.notifyDataSetChanged();
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.HomeRankingAdapter.OnItemClickListener
            public void liked(HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch, int position) {
                if (hisSearch != null) {
                    RankingActivity.this.setRankingLike(hisSearch, 0, hisSearch.getUserId());
                }
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.HomeRankingAdapter.OnItemClickListener
            public void unLiked(HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch, int position) {
                if (hisSearch != null) {
                    RankingActivity.this.setRankingLike(hisSearch, 1, hisSearch.getUserId());
                }
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mRecyclerView != null) {
            this.mRecyclerView = null;
        }
        if (this.mHomeRankingAdapter != null) {
            this.mHomeRankingAdapter = null;
        }
    }
}
