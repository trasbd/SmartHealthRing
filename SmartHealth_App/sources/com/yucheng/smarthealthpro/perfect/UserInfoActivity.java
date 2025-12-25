package com.yucheng.smarthealthpro.perfect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPerfectUserInfoBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors;
import com.yucheng.smarthealthpro.perfect.ui.ClipImageActivity;
import com.yucheng.smarthealthpro.perfect.utils.FileUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.view.CircleImageView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import me.jessyan.autosize.internal.CancelAdapt;

/* loaded from: classes5.dex */
public class UserInfoActivity extends BaseVbActivity<ActivityPerfectUserInfoBinding> implements CancelAdapt {
    private static final int REQUEST_CAPTURE = 100;
    private static final int REQUEST_CROP_PHOTO = 102;
    private static final int REQUEST_PICK = 101;
    private String birthDate;
    private CustomYearToDateSelectors mAgeCustomSelectors;
    EditText mEdUserNickname;
    CircleImageView mHeadImage;
    RadioGroup mRadioGroup;
    RadioButton mRbMan;
    RadioButton mRbWoman;
    TextView mTvNext;
    TextView mTvUserAge;
    TextView mUserNumber;
    NavigationBar navigationbar;
    public int sex;
    private File tempFile;
    private int type = 1;
    public int age = 20;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mHeadImage = ((ActivityPerfectUserInfoBinding) this.mBinding).headImage;
        this.mUserNumber = ((ActivityPerfectUserInfoBinding) this.mBinding).userNumber;
        this.mRadioGroup = ((ActivityPerfectUserInfoBinding) this.mBinding).radioGroup;
        this.mRbMan = ((ActivityPerfectUserInfoBinding) this.mBinding).rbMan;
        this.mRbWoman = ((ActivityPerfectUserInfoBinding) this.mBinding).rbWoman;
        this.mEdUserNickname = ((ActivityPerfectUserInfoBinding) this.mBinding).edUserNickname;
        this.mTvUserAge = ((ActivityPerfectUserInfoBinding) this.mBinding).tvUserAge;
        this.mTvNext = ((ActivityPerfectUserInfoBinding) this.mBinding).tvNext;
        this.navigationbar = ((ActivityPerfectUserInfoBinding) this.mBinding).navigationbar;
        this.mHeadImage.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.mEdUserNickname.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.mTvUserAge.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.mTvNext.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.perfect_userinfo_title));
        if (Constant.isBNRHealth()) {
            this.mEdUserNickname.setHint(getString(R.string.perfect_userinfo_your_username));
        }
        this.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_man) {
                    UserInfoActivity.this.sex = 0;
                } else if (checkedId == R.id.rb_woman) {
                    UserInfoActivity.this.sex = 1;
                }
            }
        });
    }

    private void initData() {
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(Constant.SpConstKey.USER_NAME);
            String stringExtra2 = getIntent().getStringExtra("nickName");
            TextView textView = this.mUserNumber;
            if (stringExtra == null) {
                stringExtra = "";
            }
            textView.setText(stringExtra);
            EditText editText = this.mEdUserNickname;
            if (stringExtra2 == null) {
                stringExtra2 = "";
            }
            editText.setText(stringExtra2);
        }
        this.birthDate = YearToDayListUtils.subYear(20);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.head_image) {
            uploadHeadImage();
            return;
        }
        if (view.getId() == R.id.ed_user_nickname) {
            this.mEdUserNickname.setBackground(getResources().getDrawable(R.drawable.button_age_bg));
            return;
        }
        if (view.getId() == R.id.tv_user_age) {
            this.mTvUserAge.setBackground(getResources().getDrawable(R.drawable.button_age_bg));
            initAgePicker();
            return;
        }
        if (view.getId() == R.id.tv_next) {
            if (this.mEdUserNickname.getText().toString().isEmpty()) {
                if (Constant.isBNRHealth()) {
                    Toast.makeText(this.context, getString(R.string.perfect_userinfo_your_username_not_null), 0).show();
                    return;
                } else {
                    Toast.makeText(this.context, getString(R.string.me_personal_details_nick_not_null), 0).show();
                    return;
                }
            }
            if (this.mTvUserAge.getText().toString().equals(getString(R.string.me_personal_details_birth_date))) {
                Toast.makeText(this.context, getString(R.string.me_personal_details_please_select_birth_date), 0).show();
                return;
            }
            if (this.mEdUserNickname.getText().toString().length() > 12 && !Constant.isBNRHealth()) {
                Toast.makeText(this.context, getString(R.string.me_personal_details_nick_too_long), 0).show();
                return;
            }
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.NICK_NAME, this.mEdUserNickname.getText());
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.SEX, Integer.valueOf(this.sex));
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.AGE, Integer.valueOf(this.age));
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.BIRTH_DATE, this.birthDate);
            startActivity(new Intent(this, (Class<?>) UnitActivity.class));
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        File file;
        Uri data;
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 100:
                if (resultCode == -1 && (file = this.tempFile) != null) {
                    gotoClipActivity(Uri.fromFile(file));
                    break;
                }
                break;
            case 101:
                if (resultCode == -1) {
                    gotoClipActivity(intent.getData());
                    break;
                }
                break;
            case 102:
                if (resultCode == -1 && (data = intent.getData()) != null) {
                    final String realFilePathFromUri = FileUtil.getRealFilePathFromUri(this, data);
                    Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(realFilePathFromUri);
                    if (this.type == 1) {
                        this.mHeadImage.setImageBitmap(bitmapDecodeFile);
                    }
                    if (realFilePathFromUri != null && !"".equals(realFilePathFromUri)) {
                        HttpUtils.getInstance().upload(this, Constants.uploadAvatarUrl, "headImg", new File(realFilePathFromUri), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity.2
                            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                            public void onSuccess(String result) {
                                if (result != null) {
                                    String str = (String) SharedPreferencesUtils.get(UserInfoActivity.this, Constant.SpConstKey.IMAGE_PATH, "");
                                    if (!"".equals(str)) {
                                        File file2 = new File(str);
                                        if (file2.exists() && file2.isFile()) {
                                            file2.delete();
                                        }
                                    }
                                    SharedPreferencesUtils.put(UserInfoActivity.this, Constant.SpConstKey.IMAGE_PATH, realFilePathFromUri);
                                }
                            }
                        });
                        break;
                    }
                }
                break;
        }
    }

    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", this.type);
        int screenWidth = AppScreenMgr.getScreenWidth(this) - 20;
        intent.putExtra(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, screenWidth);
        intent.putExtra("height", screenWidth);
        intent.setData(uri);
        startActivityForResult(intent, 102);
    }

    private void uploadHeadImage() {
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.btn_camera);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.btn_photo);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(viewInflate, -1, -2);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_main, (ViewGroup) null), 80, 0, 0);
        final WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.5f;
        getWindow().setAttributes(attributes);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity.3
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                attributes.alpha = 1.0f;
                UserInfoActivity.this.getWindow().setAttributes(attributes);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PermissionUtil.openCameraPermission(UserInfoActivity.this)) {
                    UserInfoActivity.this.gotoCamera();
                }
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PermissionUtil.openSDCardPermission(UserInfoActivity.this)) {
                    UserInfoActivity.this.gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoPhoto() {
        SystemUiUtil.gotoPhoto(this, 101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        this.tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/DCIM/image/"), System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(2);
        intent.putExtra("output", FileProvider.getUriForFile(this, "com.zhuoting.healthyucheng.fileProvider", this.tempFile));
        startActivityForResult(intent, 100);
    }

    private void initAgePicker() {
        CustomYearToDateSelectors customYearToDateSelectors = new CustomYearToDateSelectors();
        this.mAgeCustomSelectors = customYearToDateSelectors;
        customYearToDateSelectors.BpLevelPicker(this.birthDate, YearToDayListUtils.subYear(120), YearToDayListUtils.subYear(6), this.context);
        this.mAgeCustomSelectors.setOnOneSelectorsDataListener(new CustomYearToDateSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.perfect.UserInfoActivity.7
            @Override // com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(Date date) {
                try {
                    UserInfoActivity.this.birthDate = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).format(date);
                    UserInfoActivity userInfoActivity = UserInfoActivity.this;
                    userInfoActivity.age = YearToDayListUtils.getAge(userInfoActivity.birthDate);
                    UserInfoActivity.this.mTvUserAge.setText(UserInfoActivity.this.birthDate);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}
