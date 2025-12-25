package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeHelpissuelistBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.ecg.activity.AiWebActivity;
import com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter;
import com.yucheng.smarthealthpro.me.bean.MeHelpIssueBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class MeHelpIssueListActivity extends BaseVbActivity<ActivityMeHelpissuelistBinding> {
    RecyclerView mIssueRecyclerView;
    private MeHelpIssueAdapter mMeHelpIssueAdapter;
    private List<MeHelpIssueBean.DataBean> mMeHelpIssueDataBean;
    private String mToken;
    private String moduleId;
    private String name;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mIssueRecyclerView = ((ActivityMeHelpissuelistBinding) this.mBinding).recycleHelpIssue;
        this.name = getIntent().getStringExtra("name");
        this.moduleId = getIntent().getStringExtra("moduleId");
        changeTitle(this.name);
        showBack();
    }

    private void initData() {
        this.mToken = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, "");
        if (this.moduleId != null) {
            getHosIssueData();
            return;
        }
        MeHelpIssueBean meHelpIssueBean = (MeHelpIssueBean) getIntent().getSerializableExtra("bean");
        if (meHelpIssueBean != null) {
            this.mMeHelpIssueDataBean = meHelpIssueBean.data;
            setIssueRecycleView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIssueRecycleView() {
        this.mIssueRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MeHelpIssueAdapter meHelpIssueAdapter = new MeHelpIssueAdapter(R.layout.item_me_help_issue);
        this.mMeHelpIssueAdapter = meHelpIssueAdapter;
        meHelpIssueAdapter.addData((Collection) this.mMeHelpIssueDataBean);
        this.mIssueRecyclerView.setAdapter(this.mMeHelpIssueAdapter);
        this.mIssueRecyclerView.setNestedScrollingEnabled(false);
        this.mMeHelpIssueAdapter.setOnItemClickListener(new MeHelpIssueAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpIssueListActivity.1
            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter.OnItemClickListener
            public void onDelClick(MeHelpIssueBean.DataBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter.OnItemClickListener
            public void onLongClick(MeHelpIssueBean.DataBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter.OnItemClickListener
            public void onClick(MeHelpIssueBean.DataBean hisSearch, int position) {
                Intent intent = new Intent(MeHelpIssueListActivity.this.context, (Class<?>) AiWebActivity.class);
                intent.putExtra("title", hisSearch.getName());
                intent.putExtra("url", hisSearch.getUrl() + ("cn".equals(MultiLanguageUtils.getHelpLan()) ? "_cn.html" : "_en.html"));
                MeHelpIssueListActivity.this.startActivity(intent);
            }
        });
    }

    private void getHosIssueData() {
        HashMap map = new HashMap();
        map.put("code", MultiLanguageUtils.getHelpLan());
        map.put("moduleId", this.moduleId);
        map.put(Constant.SpConstKey.TOKEN, this.mToken);
        HttpUtils.getInstance().getMsgAsynHttp(this.context, Constants.help, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpIssueListActivity.2
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        MeHelpIssueBean meHelpIssueBean = (MeHelpIssueBean) new Gson().fromJson(result, MeHelpIssueBean.class);
                        if (meHelpIssueBean == null || meHelpIssueBean.code != 0) {
                            return;
                        }
                        MeHelpIssueListActivity.this.mMeHelpIssueDataBean = meHelpIssueBean.data;
                        MeHelpIssueListActivity.this.setIssueRecycleView();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }
}
