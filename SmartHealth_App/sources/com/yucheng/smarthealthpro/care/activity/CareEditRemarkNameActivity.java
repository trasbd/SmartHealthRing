package com.yucheng.smarthealthpro.care.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityCareEditremarknameBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class CareEditRemarkNameActivity extends BaseVbActivity<ActivityCareEditremarknameBinding> {
    EditText edtRemark;
    private String nickname = "";
    TextView tvComplete;
    TextView tvPhone;
    private int userId;

    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.tvPhone = ((ActivityCareEditremarknameBinding) this.mBinding).tvPhone;
        this.edtRemark = ((ActivityCareEditremarknameBinding) this.mBinding).edtRemark;
        TextView textView = ((ActivityCareEditremarknameBinding) this.mBinding).tvComplete;
        this.tvComplete = textView;
        textView.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.care.activity.CareEditRemarkNameActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.care_edit_remark_name_title));
        showBack();
        this.userId = getIntent().getIntExtra("userId", 0);
        this.nickname = getIntent().getStringExtra("remark");
        this.tvPhone.setText(getIntent().getStringExtra("phone"));
        if (TextUtils.isEmpty(this.nickname)) {
            return;
        }
        this.edtRemark.setText(this.nickname);
        this.edtRemark.setSelection(this.nickname.length());
    }

    private void requestModifyRemark() {
        String strTrim = this.edtRemark.getText().toString().trim();
        if (strTrim == null || strTrim.equals("")) {
            Toast.makeText(this.context, getString(R.string.care_edit_remark_name_is_null), 0).show();
            return;
        }
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(this, Constant.SpConstKey.TOKEN, ""));
        map.put("friendid", this.userId + "");
        map.put("nickName", strTrim);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.MODIFY_REMARK, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareEditRemarkNameActivity.1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    Toast.makeText(CareEditRemarkNameActivity.this.context, CareEditRemarkNameActivity.this.getString(R.string.clock_modify_success), 0).show();
                    CareEditRemarkNameActivity.this.finish();
                }
            }
        });
    }

    public void onViewClicked(View view) {
        requestModifyRemark();
    }
}
