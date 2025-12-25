package com.yucheng.smarthealthpro.perfect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPerfectSkincolorBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.perfect.view.RelativeRadioGroup;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class SkinColorActivity extends BaseVbActivity<ActivityPerfectSkincolorBinding> {
    public static final String KEY_SKIN_COLOR = "skin_color";
    public static final int REQUEST_SKIN_COLOR = 99;
    private String birthDate;
    private boolean isFirst;
    private int mAge;
    private int mHeight;
    RadioButton mRbBlack;
    RadioButton mRbBrown;
    RadioButton mRbBrownness;
    RadioButton mRbWhite;
    RadioButton mRbWhiteBetweenYellow;
    RadioButton mRbYellow;
    RelativeRadioGroup mRelativeRadioGroup;
    private int mSex;
    TextView mTvNext;
    private int mUnit;
    private int mWeight;
    private int skinColor = 2;

    public static void load(Activity activity, boolean isFirst, int skinColor) {
        if (isFirst) {
            activity.startActivity(new Intent(activity, (Class<?>) SkinColorActivity.class).putExtra("first", true).putExtra(KEY_SKIN_COLOR, skinColor));
        } else {
            activity.startActivityForResult(new Intent(activity, (Class<?>) SkinColorActivity.class).putExtra("first", false).putExtra(KEY_SKIN_COLOR, skinColor), 99);
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isFirst = getIntent().getBooleanExtra("first", false);
        this.skinColor = getIntent().getIntExtra(KEY_SKIN_COLOR, 2);
        initView();
    }

    public void setSkinColor(final int skinColor) {
        YCBTClient.settingSkin(skinColor, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.perfect.SkinColorActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public final void onDataResponse(int i2, float f2, HashMap map) {
                this.f$0.lambda$setSkinColor$1(skinColor, i2, f2, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setSkinColor$1(final int i2, int i3, float f2, HashMap map) {
        if (i3 == 0) {
            runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.perfect.SkinColorActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setSkinColor$0(i2);
                }
            });
        } else {
            Toast.makeText(this.context, getString(R.string.save_failed), 0).show();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setSkinColor$0(int i2) {
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.SKIN_COLOR, Integer.valueOf(i2));
        setResult(-1);
        finish();
    }

    private void initView() {
        this.mRbBlack = ((ActivityPerfectSkincolorBinding) this.mBinding).rbBlack;
        this.mRbBrownness = ((ActivityPerfectSkincolorBinding) this.mBinding).rbBrownness;
        this.mRbWhite = ((ActivityPerfectSkincolorBinding) this.mBinding).rbWhite;
        this.mRbBrown = ((ActivityPerfectSkincolorBinding) this.mBinding).rbBrown;
        this.mRbWhiteBetweenYellow = ((ActivityPerfectSkincolorBinding) this.mBinding).rbWhiteBetweenYellow;
        this.mRbYellow = ((ActivityPerfectSkincolorBinding) this.mBinding).rbYellow;
        this.mRelativeRadioGroup = ((ActivityPerfectSkincolorBinding) this.mBinding).radioGroup;
        TextView textView = ((ActivityPerfectSkincolorBinding) this.mBinding).tvNext;
        this.mTvNext = textView;
        textView.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.perfect.SkinColorActivity$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_personal_details_skin_color));
        if (this.isFirst) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.SKIN_COLOR, Integer.valueOf(this.skinColor));
        } else {
            showBack();
            showRightText(getString(R.string.save), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.perfect.SkinColorActivity$$ExternalSyntheticLambda3
                @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initView$2(view);
                }
            });
            this.mTvNext.setVisibility(8);
        }
        int i2 = this.skinColor;
        if (i2 == 0) {
            this.mRelativeRadioGroup.check(R.id.rb_white);
        } else if (i2 == 1) {
            this.mRelativeRadioGroup.check(R.id.rb_white_between_yellow);
        } else if (i2 == 2) {
            this.mRelativeRadioGroup.check(R.id.rb_yellow);
        } else if (i2 == 3) {
            this.mRelativeRadioGroup.check(R.id.rb_brown);
        } else if (i2 == 4) {
            this.mRelativeRadioGroup.check(R.id.rb_brownness);
        } else if (i2 == 5) {
            this.mRelativeRadioGroup.check(R.id.rb_black);
        }
        this.mRelativeRadioGroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.perfect.SkinColorActivity$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f$0.lambda$initView$3(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$2(View view) {
        if (YCBTClient.connectState() == 10) {
            Intent intent = new Intent();
            intent.putExtra(KEY_SKIN_COLOR, this.skinColor);
            setResult(-1, intent);
            finish();
            return;
        }
        Toast.makeText(this.context, getString(R.string.please_connect_the_device), 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$3(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.rb_white) {
            this.skinColor = 0;
            return;
        }
        if (compoundButton.getId() == R.id.rb_black) {
            this.skinColor = 5;
            return;
        }
        if (compoundButton.getId() == R.id.rb_brownness) {
            this.skinColor = 4;
            return;
        }
        if (compoundButton.getId() == R.id.rb_brown) {
            this.skinColor = 3;
        } else if (compoundButton.getId() == R.id.rb_white_between_yellow) {
            this.skinColor = 1;
        } else if (compoundButton.getId() == R.id.rb_yellow) {
            this.skinColor = 2;
        }
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_next) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.SKIN_COLOR, Integer.valueOf(this.skinColor));
            saveUserInfo();
            Intent intent = new Intent(this.context, (Class<?>) MainActivity.class);
            intent.setFlags(335544320);
            startActivity(intent);
            finish();
        }
    }

    private void saveUserInfo() {
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20));
        this.birthDate = str;
        this.mAge = YearToDayListUtils.getAge(str);
        this.mSex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue();
        this.mHeight = ((Integer) SharedPreferencesUtils.get(this.context, "height", Integer.valueOf(Opcodes.TABLESWITCH))).intValue();
        this.mWeight = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.WEIGHT, 65)).intValue();
        String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if (str2 != null && str2.equals(Constant.SpConstValue.INCH)) {
            this.mUnit = 1;
        } else {
            this.mUnit = 0;
        }
        if (YCBTClient.connectState() == 10) {
            int i2 = this.mUnit;
            int i3 = this.mHeight;
            if (i2 != 0) {
                i3 = (int) (i3 * 2.54f);
            }
            YCBTClient.settingUserInfo(i3, i2 == 0 ? this.mWeight : (int) (this.mWeight * 0.45359f), this.mSex, this.mAge, null);
        }
        if (Constant.isTechFeel()) {
            return;
        }
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(this, Constant.SpConstKey.TOKEN, ""));
        map.put(Constant.SpConstKey.AGE, this.mAge + "");
        map.put("birthday", this.birthDate);
        map.put(Constant.SpConstKey.DEV_ID, SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, ""));
        map.put("deviceName", YCBTClient.getBindDeviceName());
        map.put("height", (this.mUnit == 0 ? this.mHeight : (int) (this.mHeight * 2.54f)) + "");
        map.put("nickName", SharedPreferencesUtils.get(this.context, Constant.SpConstKey.NICK_NAME, ""));
        map.put(Constant.SpConstKey.SEX, this.mSex + "");
        map.put(Constant.SpConstKey.WEIGHT, (this.mUnit == 0 ? this.mWeight : (int) (this.mWeight * 0.45359f)) + "");
        map.put("skin", this.skinColor + "");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.updateUserInfo, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.perfect.SkinColorActivity.1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                SharedPreferencesUtils.put(SkinColorActivity.this, Constant.SpConstKey.INFO_FIRST_CHANGE, 0);
            }
        });
        if (Constant.isMymon() && Tools.readLogin(this)) {
            postUserInfo();
        }
    }

    private void postUserInfo() {
        HashMap map = new HashMap();
        map.put("userId", SharedPreferencesUtils.get(this, Constant.SpConstKey.DEV_ID, "1"));
        map.put("email", Tools.readString(Constant.SpConstKey.USER_NAME, this, ""));
        map.put("phone", Tools.readString(Constant.SpConstKey.USER_NAME, this, ""));
        map.put(Constant.SpConstKey.AGE, Integer.valueOf(this.mAge));
        map.put("birthday", this.birthDate);
        map.put(Constant.SpConstKey.SEX, this.mSex == 1 ? "F" : "M");
        map.put(FirebaseAnalytics.Param.LOCATION, (String) SharedPreferencesUtils.get(this, "country", ""));
        map.put("nickName", SharedPreferencesUtils.get(this.context, Constant.SpConstKey.NICK_NAME, ""));
        map.put("height", Integer.valueOf(this.mUnit == 0 ? this.mHeight : (int) (this.mHeight * 2.54f)));
        map.put(Constant.SpConstKey.WEIGHT, Integer.valueOf(this.mUnit == 0 ? this.mWeight : (int) (this.mWeight * 0.45359f)));
        map.put("skinColor", Integer.valueOf(this.skinColor));
        ArrayList arrayList = new ArrayList();
        arrayList.add(map);
        HttpUtils.getInstance().postJsonMsgAsynHttp(this, Constants.UPUSERINFO, new Gson().toJson(arrayList), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.perfect.SkinColorActivity.2
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.d("chong---------result==" + result);
            }
        });
    }
}
