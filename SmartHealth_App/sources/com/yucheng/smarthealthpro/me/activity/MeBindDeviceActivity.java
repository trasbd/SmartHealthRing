package com.yucheng.smarthealthpro.me.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeBindDeviceBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.me.adapter.MeListAdapter;
import com.yucheng.smarthealthpro.me.bean.MeListBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class MeBindDeviceActivity extends BaseActivity {
    ActivityMeBindDeviceBinding binding;
    private MeListAdapter mMeListAdapter;
    private List<MeListBean> mMeListBean = new ArrayList();
    RecyclerView mRecyclerView;

    @Override // com.yucheng.smarthealthpro.base.BaseActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMeBindDeviceBinding activityMeBindDeviceBindingInflate = ActivityMeBindDeviceBinding.inflate(getLayoutInflater());
        this.binding = activityMeBindDeviceBindingInflate;
        setContentView(activityMeBindDeviceBindingInflate.getRoot());
        this.mRecyclerView = this.binding.recycleView;
        changeTitle(getString(R.string.device_already_bound));
        showBack();
        getDeviceList();
        setRecycleView();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        MeListAdapter meListAdapter = new MeListAdapter(R.layout.item_me_list, 4, this.context);
        this.mMeListAdapter = meListAdapter;
        meListAdapter.addData((Collection) this.mMeListBean);
        this.mRecyclerView.setAdapter(this.mMeListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mMeListAdapter.setOnItemClickListener(new MeListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeBindDeviceActivity.1
            @Override // com.yucheng.smarthealthpro.me.adapter.MeListAdapter.OnItemClickListener
            public void onClick(MeListBean hisSearch, int position) {
                MeBindDeviceActivity meBindDeviceActivity = MeBindDeviceActivity.this;
                meBindDeviceActivity.unbindDevice(meBindDeviceActivity.getActivity(), hisSearch.getDeviceId());
            }
        });
    }

    public void unbindDevice(Context context, String deviceId) {
        HashMap map = new HashMap();
        map.put("deviceId", deviceId);
        HttpUtils.getInstance().postJsonMsgAsynHttp(context, Constants.deviceUnBind, new Gson().toJson(map), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeBindDeviceActivity.2
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    if (new JSONObject(result).optInt("code") == 200) {
                        MeBindDeviceActivity.this.getDeviceList();
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void getDeviceList() {
        HttpUtils.getInstance().getMsgAsynHttp(getActivity(), Constants.deviceList, new HashMap(), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeBindDeviceActivity.3
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    final JSONArray jSONArrayOptJSONArray = new JSONObject(result).optJSONArray("data");
                    MeBindDeviceActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeBindDeviceActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (jSONArrayOptJSONArray != null) {
                                MeBindDeviceActivity.this.mMeListBean.clear();
                                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                                    MeBindDeviceActivity.this.mMeListBean.add(new MeListBean(jSONObjectOptJSONObject.optString("deviceMac"), jSONObjectOptJSONObject.optString("deviceId")));
                                }
                                MeBindDeviceActivity.this.mMeListAdapter.setList(MeBindDeviceActivity.this.mMeListBean);
                                MeBindDeviceActivity.this.mMeListAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}
