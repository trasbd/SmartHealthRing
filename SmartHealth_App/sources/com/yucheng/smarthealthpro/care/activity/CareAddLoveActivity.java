package com.yucheng.smarthealthpro.care.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.zxing.activity.CaptureActivity;
import com.yucheng.smarthealthpro.databinding.ActivityCareAddloveBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class CareAddLoveActivity extends BaseVbActivity<ActivityCareAddloveBinding> {
    private static final int REQUEST_PERMISSION_CAMERA = 114;
    EditText etCarePhone;
    LinearLayout llAddQr;
    TextView tvAddEdit;

    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.etCarePhone = ((ActivityCareAddloveBinding) this.mBinding).etCarePhone;
        this.tvAddEdit = ((ActivityCareAddloveBinding) this.mBinding).tvAddEdit;
        this.llAddQr = ((ActivityCareAddloveBinding) this.mBinding).llAddQr;
        this.tvAddEdit.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.care.activity.CareAddLoveActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llAddQr.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.care.activity.CareAddLoveActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.care_add_love_title));
        showBack();
    }

    private void searchFriend() {
        String strTrim = this.etCarePhone.getText().toString().trim();
        if (strTrim == null || strTrim.equals("")) {
            Toast.makeText(this.context, getString(R.string.care_add_love_null_account), 0).show();
            return;
        }
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, ""));
        map.put("friendPhone", strTrim);
        HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.friendApplyUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareAddLoveActivity.1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    Toast.makeText(CareAddLoveActivity.this.context, CareAddLoveActivity.this.getString(R.string.care_add_love_send_success), 0).show();
                    CareAddLoveActivity.this.finish();
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_add_edit) {
            searchFriend();
        } else if (view.getId() == R.id.ll_add_qr) {
            skipCapture();
        }
    }

    private void skipCapture() {
        if (checkSelfPermission(Permission.CAMERA) != 0) {
            PermissionUtil.showPermissionTipDialog(this, getString(R.string.prompt), getString(R.string.camera_permission_prompt_content), new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.care.activity.CareAddLoveActivity.2
                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickLeftButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                }

                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickRightButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                    ActivityCompat.requestPermissions(CareAddLoveActivity.this, new String[]{Permission.CAMERA}, 114);
                }
            });
        } else {
            startActivityForResult(new Intent(this.context, (Class<?>) CaptureActivity.class), 100);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Logger.i("onRequestPermissionsResult " + new Gson().toJson(permissions), new Object[0]);
        if (grantResults.length > 0 && grantResults[0] == 0) {
            skipCapture();
        } else {
            DialogUtils.showPermissionDialog(this, getString(R.string.push_permission), getString(R.string.permission_prompt_content));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String stringExtra;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 100 || resultCode != 100123 || data == null || (stringExtra = data.getStringExtra("result")) == null) {
            return;
        }
        this.etCarePhone.setText(stringExtra);
        searchFriend();
    }
}
