package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.databinding.ActivityEcgrecordBinding;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CarBgView;
import com.yucheng.smarthealthpro.home.view.Cardiograph2View;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.viewmodel.EcgViewModel;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class EcgRecordActivity extends BaseVbActivity<ActivityEcgrecordBinding> {
    private static final int END = 1003;
    private static final int LOADING = 1001;
    private static final int SAVE = 1002;
    CarBgView carBigView;
    private HeartMsgHandler heartMsgHandler = new HeartMsgHandler(this);
    private Cardiograph2View mCardiograph2View;
    private int mEcgDbIndex;
    private List<EcgMeasure> mEcgMeasureDb;
    private List<Integer> mEcgMeasureList;
    private Gson mGson;
    SpinKitView mSpinKitView;
    private EcgViewModel mViewModel;
    RelativeLayout rlEcgMeasureView;
    RelativeLayout rvDialog;
    private long timeStr;
    TextView tvBpm;
    TextView tvHrv;
    TextView tvMmHg;

    class HeartMsgHandler extends Handler {
        WeakReference<EcgRecordActivity> mActivity;

        HeartMsgHandler(EcgRecordActivity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (this.mActivity.get() != null) {
                int i2 = msg.what;
                if (i2 == 1001) {
                    EcgRecordActivity.this.heartMsgHandler.sendEmptyMessage(1002);
                } else if (i2 == 1002) {
                    new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgRecordActivity.HeartMsgHandler.1
                        @Override // java.lang.Runnable
                        public void run() throws Throwable {
                            EcgRecordActivity.this.loadBitmapFromView(EcgRecordActivity.this.mCardiograph2View);
                            EcgRecordActivity.this.heartMsgHandler.sendEmptyMessage(1003);
                        }
                    }).start();
                } else {
                    EcgRecordActivity.this.dismissLoading();
                }
            }
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initData();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.rvDialog.getVisibility() == 0) {
            return;
        }
        super.onBackPressed();
    }

    private void initView() {
        this.carBigView = ((ActivityEcgrecordBinding) this.mBinding).carBigView;
        this.tvBpm = ((ActivityEcgrecordBinding) this.mBinding).tvBpm;
        this.tvMmHg = ((ActivityEcgrecordBinding) this.mBinding).tvMmHg;
        this.tvHrv = ((ActivityEcgrecordBinding) this.mBinding).tvHrv;
        this.rlEcgMeasureView = ((ActivityEcgrecordBinding) this.mBinding).rlEcgMeasureView;
        this.mSpinKitView = ((ActivityEcgrecordBinding) this.mBinding).rvDialog.spinKit;
        this.rvDialog = ((ActivityEcgrecordBinding) this.mBinding).rvDialog.rvDialog;
        changeTitle(getString(R.string.home_ecg_his_list_item_ecg_tv));
        showBack();
        showRightImage(R.mipmap.topbar_ic_download_n, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgRecordActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (Build.VERSION.SDK_INT >= 33) {
                    if (ActivityCompat.checkSelfPermission(EcgRecordActivity.this, "android.permission.READ_MEDIA_IMAGES") != 0) {
                        ActivityCompat.requestPermissions(EcgRecordActivity.this, new String[]{"android.permission.READ_MEDIA_IMAGES"}, 1);
                        return;
                    } else {
                        EcgRecordActivity.this.showLoading();
                        EcgRecordActivity.this.heartMsgHandler.sendEmptyMessage(1001);
                        return;
                    }
                }
                String[] strArr = {Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE};
                if (ActivityCompat.checkSelfPermission(EcgRecordActivity.this, Permission.WRITE_EXTERNAL_STORAGE) != 0) {
                    ActivityCompat.requestPermissions(EcgRecordActivity.this, strArr, 1);
                } else {
                    EcgRecordActivity.this.showLoading();
                    EcgRecordActivity.this.heartMsgHandler.sendEmptyMessage(1001);
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (String str : permissions) {
            if (ActivityCompat.checkSelfPermission(this, str) != 0) {
                DialogUtils.showPermissionDialog(this, getString(R.string.push_permission), getString(R.string.permission_prompt_content));
                return;
            }
        }
    }

    private void initViewModel() {
        this.mViewModel = (EcgViewModel) new ViewModelProvider(this).get(EcgViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHistoryEcgResultFlow(), new FlowUtils.FlowCollector<HealthResult<List<EcgMeasure>>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgRecordActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<List<EcgMeasure>> result) {
                EcgRecordActivity.this.onEcgMeasureDbList(result.getValue());
            }
        });
    }

    private void initData() {
        String string;
        StringBuilder sb;
        StringBuilder sbAppend;
        Intent intent = getIntent();
        String stringExtra = getIntent().getStringExtra("care");
        this.mEcgMeasureList = new ArrayList();
        if (stringExtra != null && stringExtra.equals(getString(R.string.care_title))) {
            if (getIntent() == null) {
                return;
            }
            int intExtra = getIntent().getIntExtra("minBP", 0);
            int intExtra2 = getIntent().getIntExtra("maxBP", 0);
            int intExtra3 = getIntent().getIntExtra("heart", 0);
            int intExtra4 = getIntent().getIntExtra("hrv", 0);
            if (intExtra4 > 150) {
                intExtra4 = 150;
            }
            this.mEcgMeasureList.addAll(getIntent().getIntegerArrayListExtra("data"));
            this.tvBpm.setText(intExtra3 > 0 ? intExtra3 + "" : "--");
            TextView textView = this.tvMmHg;
            if (intExtra == 0 || intExtra2 == 0) {
                string = "--";
            } else {
                if (intExtra2 > intExtra) {
                    sb = new StringBuilder();
                    sbAppend = sb.append(intExtra2).append("/").append(intExtra);
                } else {
                    sb = new StringBuilder();
                    sbAppend = sb.append(intExtra).append("/").append(intExtra2);
                }
                string = sbAppend.toString();
            }
            textView.setText(string);
            this.tvHrv.setText(intExtra4 > 0 ? intExtra4 + "" : "--");
            this.timeStr = getIntent().getLongExtra("timeLong", System.currentTimeMillis() / 1000);
            addView();
            return;
        }
        this.mEcgDbIndex = intent.getIntExtra("mEcgDbIndex", 0);
        this.mViewModel.getAllData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEcgMeasureDbList(List<EcgMeasure> data) {
        String string;
        StringBuilder sb;
        StringBuilder sbAppend;
        this.mGson = new Gson();
        this.mEcgMeasureDb = data;
        try {
            this.mEcgMeasureList = (List) this.mGson.fromJson(data.get(this.mEcgDbIndex).getMeasureData(), new TypeToken<List<Integer>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgRecordActivity.3
            }.getType());
        } catch (JsonSyntaxException e2) {
            e2.printStackTrace();
        }
        int minBp = this.mEcgMeasureDb.get(this.mEcgDbIndex).getMinBp();
        int maxBp = this.mEcgMeasureDb.get(this.mEcgDbIndex).getMaxBp();
        int heartRate = this.mEcgMeasureDb.get(this.mEcgDbIndex).getHeartRate();
        int hrv = this.mEcgMeasureDb.get(this.mEcgDbIndex).getHrv();
        if (hrv > 150) {
            hrv = 150;
        }
        this.tvBpm.setText(heartRate > 0 ? heartRate + "" : "--");
        TextView textView = this.tvMmHg;
        if (minBp == 0 || maxBp == 0) {
            string = "--";
        } else {
            if (maxBp > minBp) {
                sb = new StringBuilder();
                sbAppend = sb.append(maxBp).append("/").append(minBp);
            } else {
                sb = new StringBuilder();
                sbAppend = sb.append(minBp).append("/").append(maxBp);
            }
            string = sbAppend.toString();
        }
        textView.setText(string);
        this.tvHrv.setText(hrv > 0 ? hrv + "" : "--");
        this.timeStr = this.mEcgMeasureDb.get(this.mEcgDbIndex).getStartTimestamp();
        addView();
    }

    public void addView() {
        Cardiograph2View cardiograph2View = new Cardiograph2View(this);
        this.mCardiograph2View = cardiograph2View;
        cardiograph2View.setDatas(this.mEcgMeasureList, false);
        int size = (int) (this.mCardiograph2View.getDatas().size() * getResources().getDisplayMetrics().density);
        this.rlEcgMeasureView.addView(this.mCardiograph2View, new RelativeLayout.LayoutParams(size, -1));
        this.mCardiograph2View.make(size);
        this.mCardiograph2View.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBitmapFromView(View v) throws Throwable {
        int width = v.getWidth();
        int height = v.getHeight();
        Logger.d("chong----------w==" + width + "--" + height);
        if (height == 0 || width == 0) {
            Tools.showAlert3(this, getString(R.string.save_failed));
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        v.layout(0, 0, width, height);
        v.draw(canvas);
        Logger.d("chong----------timestr==" + Tools.transformDate(this.timeStr));
        saveBitmap(bitmapCreateBitmap, Tools.transformDate(this.timeStr) + ".png");
        if (bitmapCreateBitmap.isRecycled()) {
            return;
        }
        bitmapCreateBitmap.recycle();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x006d -> B:31:0x008a). Please report as a decompilation issue!!! */
    private void saveBitmap(Bitmap bitmap, String bitName) throws Throwable {
        if (Build.VERSION.SDK_INT > 29) {
            final boolean zSaveImageToGallery = SystemUiUtil.saveImageToGallery(this, bitmap, bitName, "ecg");
            runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgRecordActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$saveBitmap$0(zSaveImageToGallery);
                }
            });
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_PICTURES, bitName);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2)) {
                            fileOutputStream2.flush();
                            runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgRecordActivity$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.lambda$saveBitmap$1();
                                }
                            });
                            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                            intent.setData(Uri.fromFile(file));
                            sendBroadcast(intent);
                        }
                        fileOutputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgRecordActivity$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$saveBitmap$2();
                            }
                        });
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveBitmap$0(boolean z) {
        if (z) {
            Tools.showAlert3(this, getString(R.string.save_successfully));
        } else {
            Tools.showAlert3(this, getString(R.string.save_failed));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveBitmap$1() {
        Tools.showAlert3(this, getString(R.string.save_successfully));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveBitmap$2() {
        Tools.showAlert3(this, getString(R.string.save_failed));
    }

    private String getAlbumDirectory() {
        String string;
        Cursor cursorQuery = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data"}, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                Log.d("Album Directory", string);
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
            return string;
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoading() {
        this.rvDialog.setVisibility(0);
        this.mSpinKitView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissLoading() {
        RelativeLayout relativeLayout = this.rvDialog;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
            return;
        }
        SpinKitView spinKitView = this.mSpinKitView;
        if (spinKitView != null) {
            spinKitView.setVisibility(8);
        }
    }
}
