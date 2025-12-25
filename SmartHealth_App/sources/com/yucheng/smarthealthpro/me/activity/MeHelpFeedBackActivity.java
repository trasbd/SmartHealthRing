package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeGelpfeedbackBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.me.adapter.MeHelpFeedBackImageAdapter;
import com.yucheng.smarthealthpro.me.bean.MeHelpFeedBackBean;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.perfect.ui.ClipImageActivity;
import com.yucheng.smarthealthpro.perfect.utils.FileUtil;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.PackageUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.PhoneUtils;
import com.yucheng.smarthealthpro.utils.PictureCompressUtilsKt;
import com.yucheng.ycbtsdk.YCBTClient;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class MeHelpFeedBackActivity extends BaseVbActivity<ActivityMeGelpfeedbackBinding> {
    private static final int REQUEST_CAPTURE = 100;
    private static final int REQUEST_CROP_PHOTO = 102;
    private static final int REQUEST_PICK = 101;
    private List<File> Files;
    EditText etAppVersion;
    EditText etBraceletType;
    EditText etPhone;
    EditText etProblemDescription;
    RecyclerView mAddImageRecyclerView;
    private AppImageMgr mAppImageMgr;
    private List<MeHelpFeedBackBean> mMeHelpFeedBackBean;
    private MeHelpFeedBackImageAdapter mMeHelpFeedBackImageAdapter;
    private File tempFile;
    TextView tvAddFeedBack;
    private int type = 2;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mAddImageRecyclerView = ((ActivityMeGelpfeedbackBinding) this.mBinding).recycleAddImage;
        this.tvAddFeedBack = ((ActivityMeGelpfeedbackBinding) this.mBinding).tvAddFeedBack;
        this.etBraceletType = ((ActivityMeGelpfeedbackBinding) this.mBinding).etBraceletType;
        this.etAppVersion = ((ActivityMeGelpfeedbackBinding) this.mBinding).etAppVersion;
        this.etProblemDescription = ((ActivityMeGelpfeedbackBinding) this.mBinding).etProblemDescription;
        this.etPhone = ((ActivityMeGelpfeedbackBinding) this.mBinding).etPhone;
        this.tvAddFeedBack.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_using_help_feed_back_title));
        showBack();
    }

    private void initData() {
        this.mAppImageMgr = new AppImageMgr(this.context);
        this.Files = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.mMeHelpFeedBackBean = arrayList;
        arrayList.add(new MeHelpFeedBackBean(this.mAppImageMgr.getBitmap(R.mipmap.help_feedback_img_add), 0));
        setModuleRecycleView();
        String bindDeviceName = YCBTClient.getBindDeviceName();
        if (YCBTClient.connectState() == 10 && !TextUtils.isEmpty(bindDeviceName)) {
            this.etBraceletType.setText(bindDeviceName);
        }
        this.etAppVersion.setText(PackageUtils.getVersionName(this.context) + "(" + PackageUtils.getVersionCode(this.context) + ")");
    }

    private void setModuleRecycleView() {
        this.mAddImageRecyclerView.setLayoutManager(new GridLayoutManager(BitmapDescriptorFactory.getContext(), 5));
        MeHelpFeedBackImageAdapter meHelpFeedBackImageAdapter = new MeHelpFeedBackImageAdapter(R.layout.item_me_help_feed_back);
        this.mMeHelpFeedBackImageAdapter = meHelpFeedBackImageAdapter;
        meHelpFeedBackImageAdapter.addData((Collection) this.mMeHelpFeedBackBean);
        this.mAddImageRecyclerView.setAdapter(this.mMeHelpFeedBackImageAdapter);
        this.mAddImageRecyclerView.setNestedScrollingEnabled(false);
        this.mMeHelpFeedBackImageAdapter.setOnItemClickListener(new MeHelpFeedBackImageAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity.1
            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpFeedBackImageAdapter.OnItemClickListener
            public void onLongClick(MeHelpFeedBackBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpFeedBackImageAdapter.OnItemClickListener
            public void onClick(MeHelpFeedBackBean hisSearch, int position) {
                if (position == MeHelpFeedBackActivity.this.mMeHelpFeedBackBean.size() - 1 && PermissionUtil.openSDCardPermission(MeHelpFeedBackActivity.this)) {
                    MeHelpFeedBackActivity.this.uploadHeadImage();
                }
            }

            @Override // com.yucheng.smarthealthpro.me.adapter.MeHelpFeedBackImageAdapter.OnItemClickListener
            public void onDelClick(MeHelpFeedBackBean hisSearch, int position) {
                MeHelpFeedBackActivity.this.mMeHelpFeedBackBean.remove(position);
                if (MeHelpFeedBackActivity.this.Files.size() == 3) {
                    MeHelpFeedBackActivity.this.mMeHelpFeedBackBean.add(MeHelpFeedBackActivity.this.mMeHelpFeedBackBean.size(), new MeHelpFeedBackBean(MeHelpFeedBackActivity.this.mAppImageMgr.getBitmap(R.mipmap.help_feedback_img_add), 0));
                }
                MeHelpFeedBackActivity.this.Files.remove(position);
                MeHelpFeedBackActivity.this.mMeHelpFeedBackImageAdapter.remove(position);
                MeHelpFeedBackActivity.this.mMeHelpFeedBackImageAdapter.replaceData(MeHelpFeedBackActivity.this.mMeHelpFeedBackBean);
                MeHelpFeedBackActivity.this.mMeHelpFeedBackImageAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        File file;
        Uri data;
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 100:
                if (resultCode == -1 && (file = this.tempFile) != null) {
                    addFile(Uri.fromFile(file));
                    break;
                }
                break;
            case 101:
                if (resultCode == -1) {
                    addFile(intent.getData());
                    break;
                }
                break;
            case 102:
                if (resultCode == -1 && (data = intent.getData()) != null) {
                    String realFilePathFromUri = FileUtil.getRealFilePathFromUri(this, data);
                    Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(realFilePathFromUri);
                    if (this.type == 2) {
                        this.Files.add(new File(realFilePathFromUri));
                        Collections.reverse(this.mMeHelpFeedBackBean);
                        this.mMeHelpFeedBackBean.add(new MeHelpFeedBackBean(bitmapDecodeFile, 1));
                        Collections.reverse(this.mMeHelpFeedBackBean);
                        if (this.mMeHelpFeedBackBean.size() > 3) {
                            List<MeHelpFeedBackBean> list = this.mMeHelpFeedBackBean;
                            list.remove(list.size() - 1);
                        }
                        this.mMeHelpFeedBackImageAdapter.replaceData(this.mMeHelpFeedBackBean);
                        this.mMeHelpFeedBackImageAdapter.notifyDataSetChanged();
                        break;
                    }
                }
                break;
        }
    }

    private void addFile(Uri uri) {
        if (uri == null) {
            return;
        }
        if (PictureCompressUtilsKt.isFileSizeExceedsLimit(this.context, uri, 2097152) && (uri = PictureCompressUtilsKt.compressImageToUri(this.context, FileUtil.getRealFilePathFromUri(this, uri), 2097152)) != null) {
            Logger.d("addFile: compressImageToUri size: " + new File(uri.getPath()).length());
        }
        Logger.d("addFile: compressImageToUri: " + uri);
        if (uri == null) {
            return;
        }
        String realFilePathFromUri = FileUtil.getRealFilePathFromUri(this, uri);
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(realFilePathFromUri);
        this.Files.add(new File(realFilePathFromUri));
        Collections.reverse(this.mMeHelpFeedBackBean);
        this.mMeHelpFeedBackBean.add(new MeHelpFeedBackBean(bitmapDecodeFile, 1));
        Collections.reverse(this.mMeHelpFeedBackBean);
        if (this.mMeHelpFeedBackBean.size() > 3) {
            List<MeHelpFeedBackBean> list = this.mMeHelpFeedBackBean;
            list.remove(list.size() - 1);
        }
        this.mMeHelpFeedBackImageAdapter.replaceData(this.mMeHelpFeedBackBean);
        this.mMeHelpFeedBackImageAdapter.notifyDataSetChanged();
    }

    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", this.type);
        intent.setData(uri);
        startActivityForResult(intent, 102);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadHeadImage() {
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
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                attributes.alpha = 1.0f;
                MeHelpFeedBackActivity.this.getWindow().setAttributes(attributes);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PermissionUtil.openCameraPermission(MeHelpFeedBackActivity.this)) {
                    MeHelpFeedBackActivity.this.gotoCamera();
                }
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MeHelpFeedBackActivity.this.gotoPhoto();
                popupWindow.dismiss();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity.5
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

    public void postFeedBack() {
        String string = new StringBuffer().append(Build.BRAND).append(";").append(Build.MODEL).append(";Android ").append(Build.VERSION.SDK_INT).append(";").append((CharSequence) this.etAppVersion.getText()).toString();
        HashMap map = new HashMap();
        map.put("appInfo", string);
        map.put("bandInfo", ((Object) this.etBraceletType.getText()) + "");
        map.put("contactWay", ((Object) this.etPhone.getText()) + "");
        map.put("functionMessage", ((Object) this.etProblemDescription.getText()) + "");
        map.put("functionType", "Android");
        map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, ""));
        HttpUtils.getInstance().uploadFileAndParam(this.context, Constants.feedback, map, "photos", this.Files, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.MeHelpFeedBackActivity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    Toast.makeText(MeHelpFeedBackActivity.this.context, MeHelpFeedBackActivity.this.getString(R.string.me_using_help_feed_back_success), 0).show();
                    MeHelpFeedBackActivity.this.finish();
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (PhoneUtils.isFastClick()) {
            return;
        }
        if ("".equals(this.etBraceletType.getEditableText().toString())) {
            Toast.makeText(this.context, getString(R.string.me_using_help_feed_back_device_type_null), 0).show();
            return;
        }
        if ("".equals(this.etAppVersion.getEditableText().toString())) {
            Toast.makeText(this.context, getString(R.string.me_using_help_feed_back_app_type_null), 0).show();
            return;
        }
        if ("".equals(this.etProblemDescription.getEditableText().toString())) {
            Toast.makeText(this.context, getString(R.string.me_using_help_feed_back_problem_description_null), 0).show();
            return;
        }
        if ("".equals(this.etPhone.getEditableText().toString())) {
            Toast.makeText(this.context, getString(R.string.me_using_help_feed_back_phone_null), 0).show();
        } else if ("".equals(SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, ""))) {
            Toast.makeText(this.context, getString(R.string.me_using_help_feed_back_token_null), 0).show();
        } else {
            postFeedBack();
        }
    }
}
