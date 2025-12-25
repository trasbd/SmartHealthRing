package com.yucheng.smarthealthpro.care.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Toast;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.adapter.CareNewFriendListAdapter;
import com.yucheng.smarthealthpro.care.bean.FriendListBean;
import com.yucheng.smarthealthpro.care.view.GamItemTouchCallback;
import com.yucheng.smarthealthpro.databinding.ActivityCareNewfriendBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class CareNewFriendActivity extends BaseVbActivity<ActivityCareNewfriendBinding> {
    private List<FriendListBean.DataBean> dataList = new ArrayList();
    private CareNewFriendListAdapter mCareNewFriendListAdapter;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    private String mToken;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivityCareNewfriendBinding) this.mBinding).recycleView;
        this.mSmartRefreshLayout = ((ActivityCareNewfriendBinding) this.mBinding).srlHome;
        changeTitle(getString(R.string.care_new_friend_title));
        showBack();
        this.mSmartRefreshLayout.autoRefresh();
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.yucheng.smarthealthpro.care.activity.CareNewFriendActivity.1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public void onRefresh(RefreshLayout refreshLayout) {
                CareNewFriendActivity.this.dataList.clear();
                CareNewFriendActivity.this.requestFriendList();
                refreshLayout.finishRefresh();
            }
        });
    }

    private void initData() {
        this.mToken = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, "");
        setRecycleView();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        CareNewFriendListAdapter careNewFriendListAdapter = new CareNewFriendListAdapter(R.layout.item_care_new_friend_list);
        this.mCareNewFriendListAdapter = careNewFriendListAdapter;
        careNewFriendListAdapter.addData((Collection) this.dataList);
        this.mRecyclerView.setAdapter(this.mCareNewFriendListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        new ItemTouchHelper(new GamItemTouchCallback(this.mCareNewFriendListAdapter, dpToPx(this.context, 80.0f))).attachToRecyclerView(this.mRecyclerView);
        this.mCareNewFriendListAdapter.setOnItemClickListener(new CareNewFriendListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.care.activity.CareNewFriendActivity.2
            @Override // com.yucheng.smarthealthpro.care.adapter.CareNewFriendListAdapter.OnItemClickListener
            public void onClick(FriendListBean.DataBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.care.adapter.CareNewFriendListAdapter.OnItemClickListener
            public void onPassClick(FriendListBean.DataBean hisSearch, int position) {
                CareNewFriendActivity.this.requestAgreeFriend(hisSearch.userId);
            }

            @Override // com.yucheng.smarthealthpro.care.adapter.CareNewFriendListAdapter.OnItemClickListener
            public void onDeleteClick(FriendListBean.DataBean hisSearch, int position) {
                CareNewFriendActivity.this.requestRefuseFriend(hisSearch.userId);
            }
        });
    }

    public static int dpToPx(Context context, float value) {
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }

    public void requestFriendList() {
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.friendApplylistUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareNewFriendActivity.3
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<FriendListBean.DataBean> list = ((FriendListBean) new Gson().fromJson(result, FriendListBean.class)).data;
                    if (list != null) {
                        CareNewFriendActivity.this.dataList.addAll(list);
                    }
                    CareNewFriendActivity.this.mCareNewFriendListAdapter.replaceData(CareNewFriendActivity.this.dataList);
                    CareNewFriendActivity.this.mCareNewFriendListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void requestAgreeFriend(int friendid) {
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        map.put("friendid", friendid + "");
        HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.friendOkUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareNewFriendActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    Toast.makeText(CareNewFriendActivity.this.context, CareNewFriendActivity.this.getString(R.string.clock_add_success), 0).show();
                    CareNewFriendActivity.this.dataList.clear();
                    CareNewFriendActivity.this.requestFriendList();
                }
            }
        });
    }

    public void requestRefuseFriend(int friendid) {
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        map.put("friendid", friendid + "");
        HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.friendCancelUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareNewFriendActivity.5
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    Toast.makeText(CareNewFriendActivity.this.context, CareNewFriendActivity.this.getString(R.string.refuse_success), 0).show();
                    CareNewFriendActivity.this.dataList.clear();
                    CareNewFriendActivity.this.requestFriendList();
                }
            }
        });
    }
}
