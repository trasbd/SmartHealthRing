package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.connect.common.Constants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityUsinghelpBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.ecg.activity.AiWebActivity;
import com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter;
import com.yucheng.smarthealthpro.me.adapter.MeHelpModuleAdapter;
import com.yucheng.smarthealthpro.me.bean.MeHelpIssueBean;
import com.yucheng.smarthealthpro.me.bean.MeHelpModuleBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class MeUsingHelpActivity extends BaseVbActivity<ActivityUsinghelpBinding> {
    EditText etSearch;
    ImageView ivFeedBack;
    ImageView ivSearch;
    RecyclerView mIssueRecyclerView;
    private MeHelpIssueAdapter mMeHelpIssueAdapter;
    private MeHelpModuleAdapter mMeHelpModuleAdapter;
    RecyclerView mModuleRecyclerView;
    private String mToken;
    private List<MeHelpModuleBean.DataBean> mMeHelpModuleDataBean = new ArrayList();
    private List<MeHelpIssueBean.DataBean> mMeHelpIssueDataBean = new ArrayList();

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mModuleRecyclerView = ((ActivityUsinghelpBinding) this.mBinding).recycleHelpModule;
        this.mIssueRecyclerView = ((ActivityUsinghelpBinding) this.mBinding).recycleHelpIssue;
        this.ivSearch = ((ActivityUsinghelpBinding) this.mBinding).ivSearch;
        this.ivFeedBack = ((ActivityUsinghelpBinding) this.mBinding).ivFeedBack;
        this.etSearch = ((ActivityUsinghelpBinding) this.mBinding).etSearch;
        this.ivSearch.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivFeedBack.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_using_help_title));
        showBack();
    }

    private void initData() {
        this.mToken = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, "");
        getModuleData();
        getHosIssueData();
        setIssueRecycleView();
    }

    private void getModuleData() {
        HashMap map = new HashMap();
        map.put("code", MultiLanguageUtils.getHelpLan());
        map.put("moduleType", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        HttpUtils.getInstance().getMsgAsynHttp(this.context, com.yucheng.smarthealthpro.framework.util.Constants.module, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity.1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.d("chong---------------result==" + result);
                if (result != null) {
                    try {
                        MeHelpModuleBean meHelpModuleBean = (MeHelpModuleBean) new Gson().fromJson(result, MeHelpModuleBean.class);
                        if (meHelpModuleBean == null || meHelpModuleBean.code != 0) {
                            return;
                        }
                        MeUsingHelpActivity.this.mMeHelpModuleDataBean = meHelpModuleBean.data;
                        MeUsingHelpActivity.this.setModuleRecycleView();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    private void getHosIssueData() {
        HashMap map = new HashMap();
        map.put("code", MultiLanguageUtils.getHelpLan());
        map.put("currentPage", "1");
        map.put("pageSize", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        HttpUtils.getInstance().postMsgAsynHttp(this.context, com.yucheng.smarthealthpro.framework.util.Constants.hotIssue, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity.2
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    MeHelpIssueBean meHelpIssueBean = (MeHelpIssueBean) new Gson().fromJson(result, MeHelpIssueBean.class);
                    MeUsingHelpActivity.this.mMeHelpIssueDataBean = meHelpIssueBean.getData();
                    if (MeUsingHelpActivity.this.mMeHelpIssueDataBean != null) {
                        MeUsingHelpActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MeUsingHelpActivity.this.mMeHelpIssueAdapter.replaceData(MeUsingHelpActivity.this.mMeHelpIssueDataBean);
                                MeUsingHelpActivity.this.mMeHelpIssueAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });
    }

    private void getSearchIssueData(final String search) {
        HashMap map = new HashMap();
        map.put("code", MultiLanguageUtils.getHelpLan());
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        map.put("name", search);
        HttpUtils.getInstance().getMsgAsynHttp(this.context, com.yucheng.smarthealthpro.framework.util.Constants.searchIssue, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity.3
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        MeHelpIssueBean meHelpIssueBean = (MeHelpIssueBean) new Gson().fromJson(result, MeHelpIssueBean.class);
                        if (meHelpIssueBean != null && meHelpIssueBean.code == 0 && meHelpIssueBean.data != null && meHelpIssueBean.data.size() > 0) {
                            if (meHelpIssueBean.data.size() == 1) {
                                Intent intent = new Intent(MeUsingHelpActivity.this.context, (Class<?>) AiWebActivity.class);
                                intent.putExtra("title", meHelpIssueBean.data.get(0).getName());
                                intent.putExtra("url", meHelpIssueBean.data.get(0).getUrl() + ("cn".equals(MeUsingHelpActivity.this.getString(R.string.lan)) ? "_cn.html" : "_en.html"));
                                MeUsingHelpActivity.this.startActivity(intent);
                            } else {
                                Intent intent2 = new Intent(MeUsingHelpActivity.this.context, (Class<?>) MeHelpIssueListActivity.class);
                                intent2.putExtra("name", search);
                                intent2.putExtra("bean", meHelpIssueBean);
                                MeUsingHelpActivity.this.startActivity(intent2);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setModuleRecycleView() {
        this.mModuleRecyclerView.setLayoutManager(new GridLayoutManager(BitmapDescriptorFactory.getContext(), 3));
        MeHelpModuleAdapter meHelpModuleAdapter = new MeHelpModuleAdapter(R.layout.item_me_help_module);
        this.mMeHelpModuleAdapter = meHelpModuleAdapter;
        meHelpModuleAdapter.addData((Collection) this.mMeHelpModuleDataBean);
        this.mModuleRecyclerView.setAdapter(this.mMeHelpModuleAdapter);
        this.mModuleRecyclerView.setNestedScrollingEnabled(false);
        this.mMeHelpModuleAdapter.setOnItemClickListener(new MeHelpModuleAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity.4
            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpModuleAdapter.OnItemClickListener
            public void onDelClick(MeHelpModuleBean.DataBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpModuleAdapter.OnItemClickListener
            public void onLongClick(MeHelpModuleBean.DataBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpModuleAdapter.OnItemClickListener
            public void onClick(MeHelpModuleBean.DataBean hisSearch, int position) {
                Intent intent = new Intent(MeUsingHelpActivity.this.context, (Class<?>) MeHelpIssueListActivity.class);
                intent.putExtra("name", hisSearch.name);
                intent.putExtra("moduleId", hisSearch.moduleId);
                MeUsingHelpActivity.this.startActivity(intent);
            }
        });
    }

    private void setIssueRecycleView() {
        this.mIssueRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MeHelpIssueAdapter meHelpIssueAdapter = new MeHelpIssueAdapter(R.layout.item_me_help_issue);
        this.mMeHelpIssueAdapter = meHelpIssueAdapter;
        meHelpIssueAdapter.addData((Collection) this.mMeHelpIssueDataBean);
        this.mIssueRecyclerView.setAdapter(this.mMeHelpIssueAdapter);
        this.mIssueRecyclerView.setNestedScrollingEnabled(false);
        this.mMeHelpIssueAdapter.setOnItemClickListener(new MeHelpIssueAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeUsingHelpActivity.5
            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter.OnItemClickListener
            public void onDelClick(MeHelpIssueBean.DataBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter.OnItemClickListener
            public void onLongClick(MeHelpIssueBean.DataBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter.OnItemClickListener
            public void onClick(MeHelpIssueBean.DataBean hisSearch, int position) {
                Intent intent = new Intent(MeUsingHelpActivity.this.context, (Class<?>) AiWebActivity.class);
                intent.putExtra("title", hisSearch.getName());
                intent.putExtra("url", hisSearch.getUrl() + ("cn".equals(MultiLanguageUtils.getHelpLan()) ? "_cn.html" : "_en.html"));
                MeUsingHelpActivity.this.startActivity(intent);
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_search) {
            if (this.etSearch.getText().toString().isEmpty()) {
                return;
            }
            getSearchIssueData(this.etSearch.getText().toString());
        } else if (view.getId() == R.id.iv_feed_back) {
            startActivity(new Intent(this.context, (Class<?>) MeHelpFeedBackActivity.class));
        }
    }
}
