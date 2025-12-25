package com.yucheng.smarthealthpro;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityLocationBinding;
import com.yucheng.smarthealthpro.utils.PermissionUtil;

/* loaded from: classes3.dex */
public class LocationActivity extends BaseVbActivity<ActivityLocationBinding> {
    private static final int REQUEST_BACKGROUND_LOCATION = 2;
    private static final int REQUEST_LOCATION = 1;
    private boolean isFirst = true;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        TextView textView = (TextView) findViewById(R.id.location_no_thanks);
        TextView textView2 = (TextView) findViewById(R.id.location_turn_on);
        TextView textView3 = (TextView) findViewById(R.id.location_content1);
        TextView textView4 = (TextView) findViewById(R.id.location_content2);
        textView3.setText(getString(R.string.location_content1).replace("SmartHealth", getString(R.string.app_name)));
        textView4.setText(getString(R.string.location_content2).replace("SmartHealth", getString(R.string.app_name)));
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.LocationActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LocationActivity.this.finish();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.LocationActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LocationActivity.this.requestLocalPermission();
            }
        });
    }

    public void requestLocalPermission() {
        if (Build.VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_BACKGROUND_LOCATION") != 0 || ContextCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) != 0) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_BACKGROUND_LOCATION") && ActivityCompat.shouldShowRequestPermissionRationale(this, Permission.ACCESS_FINE_LOCATION) && this.isFirst) {
                    this.isFirst = false;
                    PermissionUtil.gotoPermission(this);
                    return;
                } else {
                    PermissionUtil.gotoPermission(this);
                    return;
                }
            }
            finish();
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Permission.ACCESS_COARSE_LOCATION) != 0 || ContextCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this, Permission.ACCESS_FINE_LOCATION) && this.isFirst) {
                this.isFirst = false;
                PermissionUtil.gotoPermission(this);
                return;
            } else {
                PermissionUtil.gotoPermission(this);
                return;
            }
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Logger.i("chong---------requestCode==" + requestCode, new Object[0]);
        if (requestCode != 1) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_BACKGROUND_LOCATION") == 0 && ContextCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) == 0) {
                finish();
                return;
            }
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Permission.ACCESS_COARSE_LOCATION) == 0 && ContextCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) == 0) {
            finish();
        }
    }

    private boolean checkWriteExternalPermission(Context context) {
        return context.checkCallingOrSelfPermission(Permission.ACCESS_COARSE_LOCATION) == 0;
    }
}
