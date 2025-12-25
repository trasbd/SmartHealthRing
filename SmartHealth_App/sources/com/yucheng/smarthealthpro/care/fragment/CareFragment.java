package com.yucheng.smarthealthpro.care.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbFragment;
import com.yucheng.smarthealthpro.care.activity.CareAddLoveActivity;
import com.yucheng.smarthealthpro.care.activity.CareEditRemarkNameActivity;
import com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity;
import com.yucheng.smarthealthpro.care.activity.CareNewFriendActivity;
import com.yucheng.smarthealthpro.care.adapter.CareFriendListAdapter;
import com.yucheng.smarthealthpro.care.bean.FriendListBean;
import com.yucheng.smarthealthpro.care.utils.ImageViewUtil;
import com.yucheng.smarthealthpro.databinding.FragmentCareBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.ecg.activity.AIResultImageActivity;
import com.yucheng.smarthealthpro.login.LoginActivity;
import com.yucheng.smarthealthpro.sport.view.NoScrollSwipeRecyclerView;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.Tools;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class CareFragment extends BaseVbFragment<FragmentCareBinding> {
    Button btNewFriendNum;
    LinearLayout llNewFriend;
    LinearLayout llNoFriend;
    LinearLayout llToLogin;
    private CareFriendListAdapter mCareFriendListAdapter;
    NoScrollSwipeRecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    TextView tvLogin;
    public List<FriendListBean.DataBean> dataList = new ArrayList();
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.5
        @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
            swipeRightMenu.addMenuItem(new SwipeMenuItem(CareFragment.this.context).setBackground(R.drawable.selector_red).setText(CareFragment.this.getString(R.string.delete)).setTextColor(-1).setWidth(CareFragment.this.getResources().getDimensionPixelSize(R.dimen.care_item_width)).setHeight(-1));
        }
    };
    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.6
        @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection();
            menuBridge.getPosition();
            if (direction == -1) {
                CareFragment.this.deleteFriend(position);
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, com.yucheng.smarthealthpro.framework.BaseFragment
    protected int initLayout() {
        return R.layout.fragment_care;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.llToLogin = getMViewBind().llToLogin;
        this.mSmartRefreshLayout = getMViewBind().srlHome;
        this.btNewFriendNum = getMViewBind().btNewFriendNum;
        this.llNewFriend = getMViewBind().llNewFriend;
        this.llNoFriend = getMViewBind().llNoFriend;
        this.tvLogin = getMViewBind().tvLogin;
        this.mRecyclerView = getMViewBind().recycleView;
        this.tvLogin.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        this.llNewFriend.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        changeTitle(getString(R.string.care_title));
        showRightImage(R.mipmap.icon_care_add, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (Tools.readLogin(CareFragment.this.getActivity())) {
                    CareFragment.this.startActivity(new Intent(CareFragment.this.context, (Class<?>) CareAddLoveActivity.class));
                } else {
                    ToastUtil.getInstance(CareFragment.this.getActivity()).toast(CareFragment.this.getString(R.string.me_using_help_feed_back_token_null));
                }
            }
        });
        final String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, "");
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.2
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public void onRefresh(RefreshLayout refreshLayout) {
                if (str != null && Tools.readLogin(CareFragment.this.getActivity())) {
                    CareFragment.this.getFriendList();
                    CareFragment.this.requestFriendList();
                } else {
                    CareFragment.this.llToLogin.setVisibility(0);
                    CareFragment.this.mSmartRefreshLayout.setVisibility(8);
                    CareFragment.this.llNoFriend.setVisibility(8);
                }
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
        setRecycleView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, "") != null) {
            getFriendList();
            requestFriendList();
        }
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    public void requestFriendList() {
        if (Tools.readLogin(getActivity())) {
            HashMap map = new HashMap();
            map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.TOKEN, ""));
            HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.friendApplylistUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.3
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public void onSuccess(String result) {
                    List<FriendListBean.DataBean> list;
                    if (result == null || (list = ((FriendListBean) new Gson().fromJson(result, FriendListBean.class)).data) == null) {
                        return;
                    }
                    if (list.size() != 0) {
                        String strValueOf = String.valueOf(list.size());
                        CareFragment.this.btNewFriendNum.setVisibility(0);
                        if (list.size() > 99) {
                            strValueOf = "99+";
                        }
                        CareFragment.this.btNewFriendNum.setText(strValueOf);
                        return;
                    }
                    CareFragment.this.btNewFriendNum.setVisibility(8);
                }
            });
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.mRecyclerView.setSwipeMenuCreator(this.swipeMenuCreator);
        this.mRecyclerView.setOnItemMenuClickListener(this.mMenuItemClickListener);
        CareFriendListAdapter careFriendListAdapter = new CareFriendListAdapter(R.layout.item_care_friend_list);
        this.mCareFriendListAdapter = careFriendListAdapter;
        careFriendListAdapter.addData((Collection) this.dataList);
        this.mRecyclerView.setAdapter(this.mCareFriendListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mCareFriendListAdapter.setOnItemClickListener(new CareFriendListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.4
            @Override // com.yucheng.smarthealthpro.care.adapter.CareFriendListAdapter.OnItemClickListener
            public void onClick(FriendListBean.DataBean hisSearch, int position) {
                Intent intent = new Intent(CareFragment.this.context, (Class<?>) CareFriendMainActivity.class);
                intent.putExtra("userId", CareFragment.this.dataList.get(position).userId);
                intent.putExtra("phone", CareFragment.this.dataList.get(position).friendName);
                intent.putExtra("nickName", CareFragment.this.dataList.get(position).nickName);
                intent.putExtra(Constant.SpConstKey.DEV_ID, CareFragment.this.dataList.get(position).devId);
                intent.putExtra(Constant.SpConstKey.SEX, CareFragment.this.dataList.get(position).sex);
                CareFragment.this.startActivity(intent);
            }

            @Override // com.yucheng.smarthealthpro.care.adapter.CareFriendListAdapter.OnItemClickListener
            public void onHeadClick(final FriendListBean.DataBean hisSearch, int position) {
                if (hisSearch.headImg == null || "".equals(hisSearch.headImg) || !hisSearch.headImg.contains("http")) {
                    return;
                }
                HttpUtils.getInstance().download(CareFragment.this.getActivity(), hisSearch.headImg, Constants.avatarPath, new HttpUtils.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.4.1
                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                    public void onDownloadFailed() {
                    }

                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                    public void onDownloading(int progress) {
                    }

                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                    public void onDownloadSuccess() {
                        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(Constants.avatarPath + hisSearch.headImg.substring(hisSearch.headImg.lastIndexOf("/")));
                        if (bitmapDecodeFile != null) {
                            ImageViewUtil.getInstance(bitmapDecodeFile);
                            CareFragment.this.startActivity(new Intent(CareFragment.this.getActivity(), (Class<?>) AIResultImageActivity.class));
                        }
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.care.adapter.CareFriendListAdapter.OnItemClickListener
            public void onEditNameClick(FriendListBean.DataBean hisSearch, int position) {
                Intent intent = new Intent(CareFragment.this.getActivity(), (Class<?>) CareEditRemarkNameActivity.class);
                intent.putExtra("userId", hisSearch.userId);
                intent.putExtra("phone", hisSearch.friendName);
                intent.putExtra("remark", hisSearch.nickName);
                CareFragment.this.startActivityForResult(intent, 1);
            }

            @Override // com.yucheng.smarthealthpro.care.adapter.CareFriendListAdapter.OnItemClickListener
            public void onDeleteClick(FriendListBean.DataBean hisSearch, int position) {
                CareFragment.this.deleteFriend(position);
            }
        });
    }

    public void getFriendList() {
        if (getActivity() == null) {
            return;
        }
        if (Tools.readLogin(getActivity())) {
            this.mSmartRefreshLayout.setVisibility(0);
            this.llToLogin.setVisibility(8);
            this.llNoFriend.setVisibility(8);
            this.dataList.clear();
            this.mCareFriendListAdapter.setList(this.dataList);
            this.mCareFriendListAdapter.notifyDataSetChanged();
            HashMap map = new HashMap();
            map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.TOKEN, ""));
            HttpUtils.getInstance().postMsgAsynHttp(getActivity(), Constants.friendListUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.7
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public void onSuccess(String result) {
                    CareFragment.this.mSmartRefreshLayout.finishRefresh();
                    if (result != null) {
                        List<FriendListBean.DataBean> list = ((FriendListBean) new Gson().fromJson(result, FriendListBean.class)).data;
                        CareFragment.this.dataList.clear();
                        for (FriendListBean.DataBean dataBean : list) {
                            if (!TextUtils.isEmpty(dataBean.friendName) && dataBean.userId != 154146) {
                                Log.i("LLLLLLLLL", "======" + dataBean.userId);
                                CareFragment.this.dataList.add(dataBean);
                            }
                        }
                        if (CareFragment.this.dataList.size() != 0) {
                            CareFragment.this.mCareFriendListAdapter.replaceData(CareFragment.this.dataList);
                            CareFragment.this.mCareFriendListAdapter.notifyDataSetChanged();
                            CareFragment.this.mSmartRefreshLayout.setVisibility(0);
                            CareFragment.this.llNoFriend.setVisibility(8);
                            return;
                        }
                        CareFragment.this.mSmartRefreshLayout.setVisibility(0);
                        CareFragment.this.llNoFriend.setVisibility(0);
                        return;
                    }
                    CareFragment.this.mSmartRefreshLayout.setVisibility(0);
                    CareFragment.this.llNoFriend.setVisibility(0);
                }
            });
            return;
        }
        this.mSmartRefreshLayout.setVisibility(8);
        this.llNoFriend.setVisibility(0);
    }

    public void deleteFriend(final int position) {
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.TOKEN, ""));
        map.put("friendid", this.dataList.get(position).userId + "");
        HttpUtils.getInstance().postMsgAsynHttp(getActivity(), Constants.friendDeleteUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.fragment.CareFragment.8
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                CareFragment.this.mSmartRefreshLayout.finishRefresh();
                if (result != null) {
                    CareFragment.this.dataList.remove(position);
                    CareFragment.this.mCareFriendListAdapter.remove(position);
                    CareFragment.this.mCareFriendListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_login) {
            startActivity(new Intent(getActivity(), (Class<?>) LoginActivity.class).setFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
            getActivity().finish();
        } else if (view.getId() == R.id.ll_new_friend) {
            startActivity(new Intent(this.context, (Class<?>) CareNewFriendActivity.class));
        }
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }
}
