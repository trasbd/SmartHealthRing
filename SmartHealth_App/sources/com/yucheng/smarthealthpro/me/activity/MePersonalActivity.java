package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPersonalBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.me.adapter.MeListAdapter;
import com.yucheng.smarthealthpro.me.bean.MeListBean;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors;
import com.yucheng.smarthealthpro.perfect.SkinColorActivity;
import com.yucheng.smarthealthpro.perfect.ui.ClipImageActivity;
import com.yucheng.smarthealthpro.perfect.utils.FileUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.PermissionHelper;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.view.CircleImageView;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class MePersonalActivity extends BaseVbActivity<ActivityPersonalBinding> {
    private static final int REQUEST_CAPTURE = 100;
    private static final int REQUEST_CROP_PHOTO = 102;
    private static final int REQUEST_PICK = 101;
    private String birthDate;
    EditText edUserName;
    ImageView ivEditUserName;
    CircleImageView ivHead;
    private int mAge;
    private CustomYearToDateSelectors mAgeCustomSelectors;
    private AppImageMgr mAppImageMgr;
    private int mHeight;
    private CustomSelectors mHeightCustomSelectors;
    private MeListAdapter mMeListAdapter;
    private List<MeListBean> mMeListBean;
    RecyclerView mRecyclerView;
    private int mSex;
    private CustomSelectors mSexCustomSelectors;
    private int mSkinColor;
    private int mUnit;
    private int mWeight;
    private CustomSelectors mWeightCustomSelectors;
    private File tempFile;
    TextView tvUserName;
    private ArrayList<String> firstSexList = new ArrayList<>();
    private ArrayList<String> firstHeightList = new ArrayList<>();
    private ArrayList<String> firstWeightList = new ArrayList<>();
    private int type = 1;
    private int mIsoMinHeight = 100;
    private int mIsoMaxHeight = 250;
    private int mIsoMinWeight = 30;
    private int mIsoMaxWeight = 220;
    private int mInchMinHeight = 40;
    private int mInchMaxHeight = 98;
    private int mInchMinWeight = 66;
    private int mInchMaxWeight = 484;
    private String mHeightUnit = "cm";
    private String mWeightUnit = "kg";
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            if (msg.what != 0) {
                return false;
            }
            MePersonalActivity.this.setPersonalHead();
            return false;
        }
    });

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivityPersonalBinding) this.mBinding).recycleView;
        this.ivHead = ((ActivityPersonalBinding) this.mBinding).ivPersonalHead;
        this.tvUserName = ((ActivityPersonalBinding) this.mBinding).tvUserName;
        this.edUserName = ((ActivityPersonalBinding) this.mBinding).edUserName;
        this.ivEditUserName = ((ActivityPersonalBinding) this.mBinding).ivEditUserName;
        this.ivHead.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivEditUserName.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_personal_details_title));
        showBack();
        showRightText(getString(R.string.save), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.2
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (YCBTClient.connectState() == 10) {
                    if (MePersonalActivity.this.tvUserName.getText().toString().isEmpty()) {
                        Toast.makeText(MePersonalActivity.this.context, MePersonalActivity.this.getString(R.string.me_personal_details_nick_not_null), 0).show();
                        return;
                    } else if (MePersonalActivity.this.tvUserName.getText().toString().length() > 12) {
                        Toast.makeText(MePersonalActivity.this.context, MePersonalActivity.this.getString(R.string.me_personal_details_nick_too_long), 0).show();
                        return;
                    } else {
                        MePersonalActivity.this.saveUserInfo();
                        return;
                    }
                }
                Toast.makeText(MePersonalActivity.this.context, MePersonalActivity.this.getString(R.string.please_connect_the_device), 0).show();
            }
        });
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if (str != null && str.equals(Constant.SpConstValue.ISO)) {
            this.mUnit = 0;
            this.mHeightUnit = getString(R.string.lenth_cm_unit);
            this.mWeightUnit = getString(R.string.weight_kg_unit);
        } else if (str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mUnit = 1;
            this.mHeightUnit = getString(R.string.lenth_in_unit);
            this.mWeightUnit = getString(R.string.weight_lb_unit);
        } else {
            this.mUnit = 0;
            this.mHeightUnit = getString(R.string.lenth_cm_unit);
            this.mWeightUnit = getString(R.string.weight_kg_unit);
        }
    }

    private void initData() {
        this.mAppImageMgr = new AppImageMgr(this.context);
        this.mMeListBean = new ArrayList();
        this.firstSexList.add(0, getString(R.string.man_text));
        this.firstSexList.add(1, getString(R.string.woman_text));
        if (this.mUnit == 0) {
            for (int i2 = this.mIsoMinHeight; i2 <= this.mIsoMaxHeight; i2++) {
                this.firstHeightList.add(i2 + "");
            }
        } else {
            for (int i3 = this.mInchMinHeight; i3 <= this.mInchMaxHeight; i3++) {
                this.firstHeightList.add(i3 + "");
            }
        }
        if (this.mUnit == 0) {
            for (int i4 = this.mIsoMinWeight; i4 <= this.mIsoMaxWeight; i4++) {
                this.firstWeightList.add(i4 + "");
            }
        } else {
            for (int i5 = this.mInchMinWeight; i5 <= this.mInchMaxWeight; i5++) {
                this.firstWeightList.add(i5 + "");
            }
        }
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20));
        this.birthDate = str;
        this.mAge = YearToDayListUtils.getAge(str);
        this.mSex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue();
        this.mHeight = ((Integer) SharedPreferencesUtils.get(this.context, "height", Integer.valueOf(Opcodes.TABLESWITCH))).intValue();
        this.mWeight = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.WEIGHT, 65)).intValue();
        if (!Constant.isTechFeel()) {
            this.mMeListBean.add(new MeListBean(getString(R.string.me_personal_details_qr_cord_title), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), "", 1));
        }
        this.mMeListBean.add(new MeListBean(getString(R.string.me_personal_details_birth_date), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), this.birthDate, 3));
        this.mMeListBean.add(new MeListBean(getString(R.string.me_personal_details_sex), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), getString(this.mSex == 1 ? R.string.woman_text : R.string.man_text), 4));
        this.mMeListBean.add(new MeListBean(getString(R.string.me_personal_details_height), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), this.mHeight + this.mHeightUnit, 6));
        this.mMeListBean.add(new MeListBean(getString(R.string.me_personal_details_weight), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), this.mWeight + this.mWeightUnit, 5));
        this.mSkinColor = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SKIN_COLOR, 2)).intValue();
        this.mMeListBean.add(new MeListBean(getString(R.string.me_personal_details_skin_color), null, this.mAppImageMgr.getBitmap(R.mipmap.icon_list_right_sel), this.mSkinColor + "", 7));
        setRecycleView();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.context == null) {
            return;
        }
        setPersonalHead();
        setPersonalNickName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPersonalHead() {
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IMAGE_PATH, "");
        Logger.d("ltf get imagePath=" + str);
        final String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEAD_IMG, "");
        if (!"".equals(str) && str != null && this.ivHead != null) {
            this.ivHead.setImageBitmap(BitmapFactory.decodeFile(str));
        }
        if (str2 == null || "".equals(str2) || !str2.contains("http")) {
            return;
        }
        if (str == null || "".equals(str) || str2.substring(str2.lastIndexOf("/")).equals(str.substring(str.lastIndexOf("/")))) {
            HttpUtils.getInstance().download(this.context, str2, Constants.avatarPath, new HttpUtils.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.3
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                public void onDownloadFailed() {
                }

                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                public void onDownloading(int progress) {
                }

                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.OnDownloadListener
                public void onDownloadSuccess() {
                    MePersonalActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(Constants.avatarPath + str2.substring(str2.lastIndexOf("/")));
                            if (MePersonalActivity.this.ivHead != null) {
                                MePersonalActivity.this.ivHead.setImageBitmap(bitmapDecodeFile);
                            }
                            Logger.d("ltf put cropImagePath=" + Constants.avatarPath + str2.substring(str2.lastIndexOf("/")));
                            SharedPreferencesUtils.put(MePersonalActivity.this.context, Constant.SpConstKey.IMAGE_PATH, Constants.avatarPath + str2.substring(str2.lastIndexOf("/")));
                        }
                    });
                }
            });
        }
    }

    private void setPersonalNickName() {
        if (Tools.readLogin(this.context)) {
            String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.NICK_NAME, "");
            if (str == null || "".equals(str) || str.contains("http")) {
                this.tvUserName.setText(Tools.readString(Constant.SpConstKey.USER_NAME, this.context, ""));
                return;
            } else {
                this.tvUserName.setText(str);
                return;
            }
        }
        this.tvUserName.setText(getString(R.string.me_not_log_in));
        if (Constant.isTechFeel()) {
            this.tvUserName.setText((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.NICK_NAME, getString(R.string.login_default_nick_name)));
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        MeListAdapter meListAdapter = new MeListAdapter(R.layout.item_me_list, 2, this.context);
        this.mMeListAdapter = meListAdapter;
        meListAdapter.addData((Collection) this.mMeListBean);
        this.mRecyclerView.setAdapter(this.mMeListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mMeListAdapter.setOnItemClickListener(new MeListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.4
            @Override // com.yucheng.smarthealthpro.me.adapter.MeListAdapter.OnItemClickListener
            public void onClick(MeListBean hisSearch, int position) {
                int dataType = hisSearch.getDataType();
                if (dataType == 1) {
                    MePersonalActivity.this.startActivity(new Intent(MePersonalActivity.this.context, (Class<?>) MeQrActivity.class));
                    return;
                }
                if (dataType == 3) {
                    MePersonalActivity.this.hideBottomUIMenu();
                    MePersonalActivity.this.initAgePicker(hisSearch, position);
                    return;
                }
                if (dataType == 4) {
                    MePersonalActivity.this.initSexPicker(hisSearch, position);
                    return;
                }
                if (dataType == 5) {
                    MePersonalActivity.this.initWeightPicker(hisSearch, position);
                    return;
                }
                if (dataType == 6) {
                    MePersonalActivity.this.initHeightPicker(hisSearch, position);
                } else {
                    if (dataType != 7) {
                        return;
                    }
                    MePersonalActivity mePersonalActivity = MePersonalActivity.this;
                    SkinColorActivity.load(mePersonalActivity, false, mePersonalActivity.mSkinColor);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAgePicker(final MeListBean hisSearch, final int position) {
        CustomYearToDateSelectors customYearToDateSelectors = new CustomYearToDateSelectors();
        this.mAgeCustomSelectors = customYearToDateSelectors;
        customYearToDateSelectors.BpLevelPicker(this.birthDate, YearToDayListUtils.subYear(120), YearToDayListUtils.subYear(6), this.context);
        this.mAgeCustomSelectors.setOnOneSelectorsDataListener(new CustomYearToDateSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.5
            @Override // com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(Date date) {
                try {
                    MePersonalActivity.this.birthDate = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).format(date);
                    MePersonalActivity mePersonalActivity = MePersonalActivity.this;
                    mePersonalActivity.mAge = YearToDayListUtils.getAge(mePersonalActivity.birthDate);
                    hisSearch.setRightText(MePersonalActivity.this.birthDate + "");
                    MePersonalActivity.this.mMeListAdapter.setData(position, hisSearch);
                    MePersonalActivity.this.mMeListAdapter.notifyItemChanged(position, false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSexPicker(final MeListBean hisSearch, final int position) {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mSexCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.firstSexList, null, null, this.mSex, 1, 1, "", "", "", false, CustomSelectors.IsShow.BOTTOM_CONFIRM, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mSexCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.6
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String str, int i2) {
                MePersonalActivity mePersonalActivity = MePersonalActivity.this;
                mePersonalActivity.mSex = !str.equals(mePersonalActivity.getString(R.string.man_text)) ? 1 : 0;
                hisSearch.setRightText(str + "");
                MePersonalActivity.this.mMeListAdapter.setData(position, hisSearch);
                MePersonalActivity.this.mMeListAdapter.notifyItemChanged(position, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHeightPicker(final MeListBean hisSearch, final int position) {
        int i2;
        int i3;
        CustomSelectors customSelectors = new CustomSelectors();
        this.mHeightCustomSelectors = customSelectors;
        ArrayList<String> arrayList = this.firstHeightList;
        if (this.mUnit == 0) {
            i2 = this.mHeight;
            i3 = this.mIsoMinHeight;
        } else {
            i2 = this.mHeight;
            i3 = this.mInchMinHeight;
        }
        customSelectors.BpLevelPicker(arrayList, null, null, i2 - i3, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mHeightCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.7
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                MePersonalActivity.this.mHeight = Integer.parseInt(oneValue);
                hisSearch.setRightText(oneValue + MePersonalActivity.this.mHeightUnit);
                MePersonalActivity.this.mMeListAdapter.setData(position, hisSearch);
                MePersonalActivity.this.mMeListAdapter.notifyItemChanged(position, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initWeightPicker(final MeListBean hisSearch, final int position) {
        int i2;
        int i3;
        CustomSelectors customSelectors = new CustomSelectors();
        this.mWeightCustomSelectors = customSelectors;
        ArrayList<String> arrayList = this.firstWeightList;
        if (this.mUnit == 0) {
            i2 = this.mWeight;
            i3 = this.mIsoMinWeight;
        } else {
            i2 = this.mWeight;
            i3 = this.mInchMinWeight;
        }
        customSelectors.BpLevelPicker(arrayList, null, null, i2 - i3, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mWeightCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.8
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                MePersonalActivity.this.mWeight = Integer.parseInt(oneValue);
                hisSearch.setRightText(oneValue + MePersonalActivity.this.mWeightUnit);
                MePersonalActivity.this.mMeListAdapter.setData(position, hisSearch);
                MePersonalActivity.this.mMeListAdapter.notifyItemChanged(position, false);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        File file;
        Uri data;
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 99:
                if (resultCode == -1) {
                    this.mSkinColor = intent.getIntExtra(SkinColorActivity.KEY_SKIN_COLOR, 2);
                    for (int size = this.mMeListAdapter.getData().size() - 1; size > 0; size--) {
                        if (this.mMeListAdapter.getData().get(size).getDataType() == 7) {
                            this.mMeListAdapter.getData().get(size).setRightText("" + this.mSkinColor);
                            this.mMeListAdapter.notifyItemChanged(size);
                            break;
                        }
                    }
                    break;
                }
                break;
            case 100:
                Logger.d("chong-------打开相机返回--tempFile==" + this.tempFile);
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
                    Logger.d("chong-------cropImagePath==" + realFilePathFromUri);
                    if (Constant.isTechFeel()) {
                        String str = (String) SharedPreferencesUtils.get(this, Constant.SpConstKey.IMAGE_PATH, "");
                        if (!"".equals(str)) {
                            File file2 = new File(str);
                            if (file2.exists() && file2.isFile()) {
                                file2.delete();
                            }
                        }
                        SharedPreferencesUtils.put(this, Constant.SpConstKey.IMAGE_PATH, realFilePathFromUri);
                        setPersonalHead();
                        break;
                    } else if (realFilePathFromUri != null && !"".equals(realFilePathFromUri)) {
                        HttpUtils.getInstance().upload(this, Constants.uploadAvatarUrl, "headImg", new File(realFilePathFromUri), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.9
                            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                            public void onSuccess(String result) throws Resources.NotFoundException {
                                if (result != null) {
                                    String str2 = (String) SharedPreferencesUtils.get(MePersonalActivity.this, Constant.SpConstKey.IMAGE_PATH, "");
                                    if (!"".equals(str2)) {
                                        File file3 = new File(str2);
                                        if (file3.exists() && file3.isFile()) {
                                            file3.delete();
                                        }
                                    }
                                    SharedPreferencesUtils.put(MePersonalActivity.this, Constant.SpConstKey.IMAGE_PATH, realFilePathFromUri);
                                    MePersonalActivity.this.handler.sendEmptyMessage(0);
                                    return;
                                }
                                ToastUtil.getInstance(MePersonalActivity.this.getApplicationContext()).toast(R.string.save_failed);
                                SharedPreferencesUtils.put(MePersonalActivity.this, Constant.SpConstKey.IMAGE_PATH, realFilePathFromUri);
                                MePersonalActivity.this.handler.sendEmptyMessage(0);
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
        int iMin = Math.min(AppScreenMgr.getScreenWidth(this), AppScreenMgr.getScreenHeight(this)) - 20;
        intent.putExtra(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, iMin);
        intent.putExtra("height", iMin);
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
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.10
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                attributes.alpha = 1.0f;
                MePersonalActivity.this.getWindow().setAttributes(attributes);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PermissionUtil.openCameraPermission(MePersonalActivity.this)) {
                    MePersonalActivity.this.gotoCamera();
                }
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MePersonalActivity.this.gotoPhoto();
                popupWindow.dismiss();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.13
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
        this.tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/DCIM/image/"), System.currentTimeMillis() + ".jpg");
        Logger.d("chong------------filePath===" + this.tempFile);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(2);
        intent.putExtra("output", FileProvider.getUriForFile(this, "com.zhuoting.healthyucheng.fileProvider", this.tempFile));
        startActivityForResult(intent, 100);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideKeyboard(currentFocus, ev)) {
                hideKeyboard(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v == null || !(v instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        v.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return event.getX() <= ((float) i2) || event.getX() >= ((float) (v.getWidth() + i2)) || event.getY() <= ((float) i3) || event.getY() >= ((float) (v.getHeight() + i3));
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity
    public void hideKeyboard(IBinder token) {
        if (token != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(token, 2);
            this.tvUserName.setVisibility(0);
            this.edUserName.setVisibility(8);
            this.tvUserName.setText(this.edUserName.getEditableText().toString().trim());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveUserInfo() {
        if (YCBTClient.connectState() == 10) {
            int i2 = this.mUnit;
            int i3 = this.mHeight;
            if (i2 != 0) {
                i3 = (int) (i3 * 2.54f);
            }
            YCBTClient.settingUserInfo(i3, i2 == 0 ? this.mWeight : (int) (this.mWeight * 0.45359f), this.mSex, this.mAge, null);
        }
        if (Constant.isTechFeel()) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.NICK_NAME, this.tvUserName.getText());
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.BIRTH_DATE, this.birthDate);
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.SEX, Integer.valueOf(this.mSex));
            SharedPreferencesUtils.put(this.context, "height", Integer.valueOf(this.mHeight));
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.WEIGHT, Integer.valueOf(this.mWeight));
            Toast.makeText(this.context, getString(R.string.save_successfully), 0).show();
            finish();
            return;
        }
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.TOKEN, "" + SharedPreferencesUtils.get(this, Constant.SpConstKey.TOKEN, ""));
        map.put(Constant.SpConstKey.AGE, this.mAge + "");
        map.put("birthday", this.birthDate);
        map.put(Constant.SpConstKey.DEV_ID, (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, "1"));
        map.put("deviceName", YCBTClient.getBindDeviceName());
        map.put("nickName", this.tvUserName.getText().toString());
        map.put("height", (this.mUnit == 0 ? this.mHeight : (int) (this.mHeight * 2.54f)) + "");
        map.put(Constant.SpConstKey.SEX, this.mSex + "");
        map.put(Constant.SpConstKey.WEIGHT, (this.mUnit == 0 ? this.mWeight : (int) (this.mWeight * 0.45359f)) + "");
        map.put("skin", this.mSkinColor + "");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.updateUserInfo, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.14
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result == null || MePersonalActivity.this.tvUserName == null) {
                    return;
                }
                SharedPreferencesUtils.put(MePersonalActivity.this.context, Constant.SpConstKey.NICK_NAME, MePersonalActivity.this.tvUserName.getText());
                SharedPreferencesUtils.put(MePersonalActivity.this.context, Constant.SpConstKey.AGE, Integer.valueOf(MePersonalActivity.this.mAge));
                SharedPreferencesUtils.put(MePersonalActivity.this.context, Constant.SpConstKey.BIRTH_DATE, MePersonalActivity.this.birthDate);
                SharedPreferencesUtils.put(MePersonalActivity.this.context, Constant.SpConstKey.SEX, Integer.valueOf(MePersonalActivity.this.mSex));
                SharedPreferencesUtils.put(MePersonalActivity.this.context, "height", Integer.valueOf(MePersonalActivity.this.mHeight));
                SharedPreferencesUtils.put(MePersonalActivity.this.context, Constant.SpConstKey.WEIGHT, Integer.valueOf(MePersonalActivity.this.mWeight));
                SharedPreferencesUtils.put(MePersonalActivity.this.context, Constant.SpConstKey.SKIN_COLOR, Integer.valueOf(MePersonalActivity.this.mSkinColor));
                if (YCBTClient.connectState() == 10) {
                    Toast.makeText(MePersonalActivity.this.context, MePersonalActivity.this.getString(R.string.save_successfully), 0).show();
                    MePersonalActivity mePersonalActivity = MePersonalActivity.this;
                    mePersonalActivity.setSkinColor(mePersonalActivity.mSkinColor);
                    MePersonalActivity.this.finish();
                }
            }
        });
        if (Constant.isMymon() && Tools.readLogin(this)) {
            postUserInfo();
        }
    }

    public void setSkinColor(final int skinColor) {
        YCBTClient.settingSkin(skinColor, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public final void onDataResponse(int i2, float f2, HashMap map) {
                this.f$0.lambda$setSkinColor$0(skinColor, i2, f2, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setSkinColor$0(int i2, int i3, float f2, HashMap map) {
        if (i3 == 0) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.SKIN_COLOR, Integer.valueOf(i2));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.INSTANCE.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_personal_head) {
            if (PermissionUtil.openSDCardPermission(this)) {
                uploadHeadImage();
            }
        } else if (view.getId() == R.id.iv_edit_user_name) {
            this.tvUserName.setVisibility(8);
            this.edUserName.setVisibility(0);
            this.edUserName.setText(this.tvUserName.getText().toString().trim());
            this.edUserName.setFocusable(true);
            this.edUserName.setFocusableInTouchMode(true);
            this.edUserName.requestFocus();
            ((InputMethodManager) this.context.getSystemService("input_method")).showSoftInput(this.edUserName, 0);
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity
    protected void hideBottomUIMenu() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.systemUiVisibility = 2050;
        window.setAttributes(attributes);
    }

    private void postUserInfo() {
        HashMap map = new HashMap();
        map.put("userId", SharedPreferencesUtils.get(this, Constant.SpConstKey.DEV_ID, "1"));
        map.put("email", Tools.readString(Constant.SpConstKey.USER_NAME, this, ""));
        map.put("phone", Tools.readString(Constant.SpConstKey.USER_NAME, this, ""));
        map.put(Constant.SpConstKey.AGE, Integer.valueOf(this.mAge));
        map.put("birthday", this.birthDate);
        map.put("nickName", this.tvUserName.getText().toString());
        map.put("height", Integer.valueOf(this.mUnit == 0 ? this.mHeight : (int) (this.mHeight * 2.54f)));
        map.put(Constant.SpConstKey.WEIGHT, Integer.valueOf(this.mUnit == 0 ? this.mWeight : (int) (this.mWeight * 0.45359f)));
        map.put("skinColor", Integer.valueOf(this.mSkinColor));
        map.put(Constant.SpConstKey.SEX, this.mSex == 1 ? "F" : "M");
        map.put(FirebaseAnalytics.Param.LOCATION, (String) SharedPreferencesUtils.get(this, "country", ""));
        ArrayList arrayList = new ArrayList();
        arrayList.add(map);
        HttpUtils.getInstance().postJsonMsgAsynHttp(this, Constants.UPUSERINFO, new Gson().toJson(arrayList), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MePersonalActivity.15
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.d("chong---------result==" + result);
            }
        });
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String string = savedInstanceState.getString("camera_file_path");
        if (string != null) {
            this.tempFile = new File(string);
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        File file = this.tempFile;
        if (file != null) {
            outState.putString("camera_file_path", file.getAbsolutePath());
        }
    }
}
