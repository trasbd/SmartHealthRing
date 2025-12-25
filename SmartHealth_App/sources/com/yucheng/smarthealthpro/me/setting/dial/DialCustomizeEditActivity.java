package com.yucheng.smarthealthpro.me.setting.dial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityDialCustomizeEditBinding;
import com.yucheng.smarthealthpro.dialog.MyDialog;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.me.setting.dial.bean.DialCustomPositionBean;
import com.yucheng.smarthealthpro.me.setting.dial.bean.DialResultBean;
import com.yucheng.smarthealthpro.me.setting.dial.util.SaveBitmap888Util;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.perfect.utils.FileUtil;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.ImageBean;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/* loaded from: classes5.dex */
public class DialCustomizeEditActivity extends BaseVbActivity<ActivityDialCustomizeEditBinding> implements Observer {
    private static final int REQUEST_CAPTURE = 100;
    private static final int REQUEST_CROP_PHOTO = 102;
    private static final int REQUEST_PICK = 101;
    private byte[] bgBitmaps;
    private byte[] bins;
    private int cpSize;
    String cropImagePath;
    private DialResultBean.Data data;
    private List<DialCustomPositionBean> dialCustomPositionBeans;
    private ImageView dial_custom_bg_img;
    private TextClock dial_custom_date;
    private TextView dial_custom_default;
    private RadioGroup dial_custom_rg1;
    private RadioGroup dial_custom_rg2;
    private TextView dial_custom_select_picture;
    private TextClock dial_custom_time;
    private TextView dial_custom_tv_time_position;
    private TextClock dial_custom_week;
    String imgName;
    private MyDialog installDialog;
    String name;
    private RequestOptions requestOptions;
    private int size;
    private File tempFile;
    private byte[] thumbnails;
    private int timeIndexType;
    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;
    private boolean isInstalling = false;
    private boolean rg1 = false;
    private boolean rg2 = false;
    private int[] positions = new int[2];
    private int width = 240;
    private int height = 240;
    private int radius = -1;
    private int cpWidth = 140;
    private int cpHeight = 140;
    private int cpRadius = -1;
    String crop2 = SystemUiUtil.isExistDir("health/dial") + "/thumbnail.png";
    boolean isReset = false;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    try {
                        if (DialCustomizeEditActivity.this.wakeLock != null && DialCustomizeEditActivity.this.wakeLock.isHeld()) {
                            DialCustomizeEditActivity.this.wakeLock.release();
                            break;
                        }
                    } catch (Exception e2) {
                        CrashReport.postCatchedException(e2);
                        e2.printStackTrace();
                        return false;
                    }
                    break;
                case 2:
                    DialCustomizeEditActivity.this.dismissDialog();
                    break;
                case 3:
                    DialCustomizeEditActivity.this.dismissDialog();
                    break;
                case 5:
                    DialCustomizeEditActivity.this.dismissDialog();
                    DialCustomizeEditActivity dialCustomizeEditActivity = DialCustomizeEditActivity.this;
                    Tools.showAlert3(dialCustomizeEditActivity, dialCustomizeEditActivity.getString(R.string.dial_is_install_failed));
                    break;
                case 6:
                    DialCustomizeEditActivity.this.dismissDialog();
                    DialCustomizeEditActivity dialCustomizeEditActivity2 = DialCustomizeEditActivity.this;
                    Tools.showAlert3(dialCustomizeEditActivity2, dialCustomizeEditActivity2.getString(R.string.dial_is_install_max));
                    break;
                case 7:
                    DialCustomizeEditActivity.this.dismissDialog();
                    DialCustomizeEditActivity dialCustomizeEditActivity3 = DialCustomizeEditActivity.this;
                    Tools.showAlert3(dialCustomizeEditActivity3, dialCustomizeEditActivity3.getString(R.string.dial_is_install_done));
                    if (YCBTClient.isJieLi()) {
                        YCBTClient.jlWatchDialSetCurrent(DialCustomizeEditActivity.this.data.fileName.substring(DialCustomizeEditActivity.this.data.fileName.lastIndexOf("/")), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.1.1
                            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                            public void onDataResponse(int code, float v, HashMap hashMap) {
                                if (code == 0) {
                                    DialCustomizeEditActivity.this.data.state = 4;
                                }
                            }
                        });
                        break;
                    }
                    break;
                case 8:
                    DialCustomizeEditActivity.this.setDialProgress(msg.arg1);
                    break;
                case 10:
                    DialCustomizeEditActivity dialCustomizeEditActivity4 = DialCustomizeEditActivity.this;
                    Tools.showAlert3(dialCustomizeEditActivity4, dialCustomizeEditActivity4.getString(R.string.dial_delete_failed));
                    DialCustomizeEditActivity.this.dismissDialog();
                    break;
            }
            return false;
        }
    });
    private int color = Color.parseColor("#FFFFFF");

    public int convertRGB888toRGB565(int rgb888) {
        return (((rgb888 & 255) >> 3) & 31) | (((((rgb888 >> 16) & 255) >> 3) & 31) << 11) | (((((rgb888 >> 8) & 255) >> 2) & 63) << 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDialog() {
        MyDialog myDialog;
        this.isInstalling = false;
        try {
            if (isFinishing() || (myDialog = this.installDialog) == null || !myDialog.isShowing()) {
                return;
            }
            this.installDialog.dismiss();
            this.installDialog.setProgress(0);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setListener();
        initData();
    }

    private void init() {
        changeTitle(getResources().getString(R.string.home_compile_title));
        SubObserver.getInstance().addObs(this);
        this.dial_custom_bg_img = (ImageView) findViewById(R.id.dial_custom_bg_img);
        this.requestOptions = new RequestOptions().error(R.mipmap.dial_custom_bg_icon).placeholder(R.mipmap.dial_custom_bg_icon);
        this.dial_custom_select_picture = (TextView) findViewById(R.id.dial_custom_select_picture);
        this.dial_custom_default = (TextView) findViewById(R.id.dial_custom_default);
        this.dial_custom_rg1 = (RadioGroup) findViewById(R.id.dial_custom_rg1);
        this.dial_custom_rg2 = (RadioGroup) findViewById(R.id.dial_custom_rg2);
        this.dial_custom_tv_time_position = (TextView) findViewById(R.id.dial_custom_tv_time_position);
        this.dial_custom_time = (TextClock) findViewById(R.id.dial_custom_time);
        this.dial_custom_date = (TextClock) findViewById(R.id.dial_custom_date);
        this.dial_custom_week = (TextClock) findViewById(R.id.dial_custom_week);
    }

    private void setListener() {
        showBack(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.2
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                DialCustomizeEditActivity.this.finish();
            }
        });
        showRightImage(R.mipmap.dial_ok_icon, new MyOnClickListenerImpl());
        this.dial_custom_select_picture.setOnClickListener(new OnClickListenerImpl());
        this.dial_custom_default.setOnClickListener(new OnClickListenerImpl());
        this.dial_custom_rg1.setOnCheckedChangeListener(new OnCheckedChangeListener1());
        this.dial_custom_rg2.setOnCheckedChangeListener(new OnCheckedChangeListener2());
        findViewById(R.id.dial_custom_ly_time_position).setOnClickListener(new OnClickListenerImpl());
    }

    private void initData() {
        try {
            PowerManager powerManager = (PowerManager) getSystemService("power");
            this.powerManager = powerManager;
            this.wakeLock = powerManager.newWakeLock(26, "My Lock");
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        DialResultBean.Data data = (DialResultBean.Data) getIntent().getSerializableExtra("data");
        this.data = data;
        if (data == null) {
            finish();
            return;
        }
        this.imgName = data.imgName.substring(this.data.imgName.lastIndexOf("/") + 1, this.data.imgName.lastIndexOf("."));
        this.name = this.data.fileName.substring(this.data.fileName.lastIndexOf("/") + 1);
        if (!YCBTClient.isJieLi()) {
            if (!getTextPosition()) {
                finish();
                return;
            } else {
                downDialFile(this.data.fileName);
                return;
            }
        }
        if (getTextPosition()) {
            initJlScreen();
            downDialFile(this.data.backgroundImgUrl);
        }
    }

    private boolean getTextPosition() {
        try {
            if ("0".equals(this.data.dialVersion)) {
                if (YCBTClient.isJieLi()) {
                    this.dialCustomPositionBeans = new ArrayList();
                    for (int i2 = 1; i2 <= 9; i2++) {
                        this.dialCustomPositionBeans.add(new DialCustomPositionBean(i2));
                    }
                }
                return true;
            }
            this.dialCustomPositionBeans = (List) new Gson().fromJson(this.data.dialVersion, new TypeToken<List<DialCustomPositionBean>>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.3
            }.getType());
            return true;
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
            finish();
            return false;
        }
    }

    private void initJlScreen() {
        int[] jlScreenSize = YCBTClient.getJlScreenSize();
        int i2 = jlScreenSize[0];
        this.width = i2;
        int i3 = jlScreenSize[1];
        this.height = i3;
        if (i2 == 0) {
            this.width = 240;
        }
        if (i3 == 0) {
            this.height = 240;
        }
        YCBTClient.getScreenParameters(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.4
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i4, float v, HashMap hashMap) {
                if (hashMap != null) {
                    Logger.d("chong---------hashMap==" + hashMap.toString());
                    ((Integer) hashMap.get("screenType")).intValue();
                    DialCustomizeEditActivity.this.width = ((Integer) hashMap.get("screenWidth")).intValue();
                    DialCustomizeEditActivity.this.height = ((Integer) hashMap.get("screenHeight")).intValue();
                    DialCustomizeEditActivity.this.radius = ((Integer) hashMap.get("screenCorner")).intValue();
                    DialCustomizeEditActivity.this.cpWidth = ((Integer) hashMap.get("screenCpWidth")).intValue();
                    DialCustomizeEditActivity.this.cpHeight = ((Integer) hashMap.get("screenCpHeight")).intValue();
                    DialCustomizeEditActivity.this.cpRadius = ((Integer) hashMap.get("screenCpCorner")).intValue();
                    DialCustomizeEditActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.4.1
                        @Override // java.lang.Runnable
                        public void run() throws Throwable {
                            DialCustomizeEditActivity.this.initDialCpImage();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDefaultData() throws Throwable {
        String str;
        if ((this.cpRadius == -1 || this.radius == -1) && !YCBTClient.isJieLi()) {
            return;
        }
        String str2 = SystemUiUtil.isExistDir("health/dial") + "/" + this.imgName + ".bmp";
        File file = new File(str2);
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str2);
        if (file.exists()) {
            this.dial_custom_bg_img.setImageBitmap(SystemUiUtil.getRoundBitmapByShader(bitmapDecodeFile, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), (int) (((this.cpRadius * 1.0f) * bitmapDecodeFile.getWidth()) / this.cpWidth), 2, Color.parseColor("#00ffffff")));
            getBgBitmaps();
        } else {
            if (!TextUtils.isEmpty(this.data.backgroundImgUrl)) {
                str = this.data.backgroundImgUrl;
            } else {
                str = Constants.DIALCUSTOMIMAGEURL + this.data.imgName.substring(this.data.imgName.lastIndexOf("/") + 1);
            }
            Glide.with((FragmentActivity) this).applyDefaultRequestOptions(this.requestOptions).load(str).into(this.dial_custom_bg_img);
        }
        this.timeIndexType = Tools.readInt("custom_dial_time_index", this, 9);
        this.dial_custom_tv_time_position.setText(getTimePositionString());
        setTimePosition();
        this.color = Tools.readInt("custom_dial_color", this, ViewCompat.MEASURED_SIZE_MASK) & ViewCompat.MEASURED_SIZE_MASK;
        Logger.d("chong----------color==" + this.color);
        setCheckedRadioButton();
        if (YCBTClient.isJieLi()) {
            saveJieLiImage();
        }
    }

    private void setCheckedRadioButton() {
        switch (this.color) {
            case 0:
                ((RadioButton) findViewById(R.id.dial_custom_radio12)).setChecked(true);
                break;
            case 1295042:
                ((RadioButton) findViewById(R.id.dial_custom_radio6)).setChecked(true);
                break;
            case 1609983:
                ((RadioButton) findViewById(R.id.dial_custom_radio7)).setChecked(true);
                break;
            case 3101931:
                ((RadioButton) findViewById(R.id.dial_custom_radio8)).setChecked(true);
                break;
            case 7483089:
                ((RadioButton) findViewById(R.id.dial_custom_radio9)).setChecked(true);
                break;
            case 10541329:
                ((RadioButton) findViewById(R.id.dial_custom_radio5)).setChecked(true);
                break;
            case 15413142:
                ((RadioButton) findViewById(R.id.dial_custom_radio10)).setChecked(true);
                break;
            case 16065069:
                ((RadioButton) findViewById(R.id.dial_custom_radio1)).setChecked(true);
                break;
            case 16405532:
                ((RadioButton) findViewById(R.id.dial_custom_radio2)).setChecked(true);
                break;
            case 16419862:
                ((RadioButton) findViewById(R.id.dial_custom_radio3)).setChecked(true);
                break;
            case 16440084:
                ((RadioButton) findViewById(R.id.dial_custom_radio4)).setChecked(true);
                break;
            case ViewCompat.MEASURED_SIZE_MASK /* 16777215 */:
                ((RadioButton) findViewById(R.id.dial_custom_radio11)).setChecked(true);
                break;
        }
    }

    private String getTimePositionString() {
        String string = getString(R.string.dial_custom_mid);
        switch (this.timeIndexType) {
            case 1:
                return getString(R.string.dial_custom_top);
            case 2:
                return getString(R.string.dial_custom_bottom);
            case 3:
                return getString(R.string.dial_custom_left);
            case 4:
                return getString(R.string.dial_custom_right);
            case 5:
                return getString(R.string.dial_custom_left_top);
            case 6:
                return getString(R.string.dial_custom_right_top);
            case 7:
                return getString(R.string.dial_custom_left_bottom);
            case 8:
                return getString(R.string.dial_custom_right_bottom);
            case 9:
                return getString(R.string.dial_custom_mid);
            default:
                return string;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDefaultDialog() {
        new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black_light).setContentText(getResources().getString(R.string.dial_custom_is_restore_default)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.dial_custom_restore)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.5
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) throws Throwable {
                String str = DialCustomizeEditActivity.this.imgName;
                SystemUiUtil.deleteDialFile("new_" + str + ".bmp");
                SystemUiUtil.deleteDialFile(str + ".bmp");
                Tools.removeKey("custom_dial_time_index", DialCustomizeEditActivity.this);
                Tools.removeKey("custom_dial_color", DialCustomizeEditActivity.this);
                DialCustomizeEditActivity.this.bgBitmaps = null;
                DialCustomizeEditActivity.this.setDefaultData();
                if (YCBTClient.isJieLi()) {
                    SystemUiUtil.deleteDialFile(DialCustomizeEditActivity.this.getCustomBgName(str));
                    SystemUiUtil.deleteDialFile(DialCustomizeEditActivity.this.getCustomThumbnailBgName(str));
                    DialCustomizeEditActivity.this.isReset = true;
                    DialCustomizeEditActivity dialCustomizeEditActivity = DialCustomizeEditActivity.this;
                    dialCustomizeEditActivity.downDialFile(dialCustomizeEditActivity.data.backgroundImgUrl);
                }
                dialog.dismiss();
            }
        }).build().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downDialFile(String fileUrl) {
        final String strSubstring = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        DownloadUtil.getInstance().download(fileUrl, "health/dial", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.6
            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                DialCustomizeEditActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.6.1
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        if (YCBTClient.isJieLi()) {
                            try {
                                File file = new File(SystemUiUtil.isExistDir("health/dial") + "/" + DialCustomizeEditActivity.this.getCustomBgName(DialCustomizeEditActivity.this.name));
                                if (file.exists() && file.isFile() && !DialCustomizeEditActivity.this.isReset) {
                                    return;
                                }
                                AppImageMgr.saveImage(SystemUiUtil.getRoundBitmapByShader(BitmapFactory.decodeFile(SystemUiUtil.isExistDir("health/dial") + "/" + strSubstring), DialCustomizeEditActivity.this.cpWidth, DialCustomizeEditActivity.this.cpHeight, DialCustomizeEditActivity.this.cpRadius, 0, Color.parseColor("#00ffffff")), DialCustomizeEditActivity.this.crop2);
                                DialCustomizeEditActivity.this.saveJlBgImage(SystemUiUtil.isExistDir("health/dial") + "/" + DialCustomizeEditActivity.this.data.backgroundImgUrl.substring(DialCustomizeEditActivity.this.data.backgroundImgUrl.lastIndexOf("/") + 1));
                                DialCustomizeEditActivity.this.saveJieLiImage();
                                DialCustomizeEditActivity.this.isReset = false;
                                return;
                            } catch (Exception e2) {
                                CrashReport.postCatchedException(e2);
                                e2.printStackTrace();
                                return;
                            }
                        }
                        DialCustomizeEditActivity.this.initDialData();
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloading(int progress) {
                DialCustomizeEditActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.6.2
                    @Override // java.lang.Runnable
                    public void run() {
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
                DialCustomizeEditActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.6.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Tools.showAlert3(DialCustomizeEditActivity.this, DialCustomizeEditActivity.this.getString(R.string.down_failed));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00bb -> B:55:0x00c1). Please report as a decompilation issue!!! */
    public void initDialData() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        Exception e2;
        try {
        } catch (Exception e3) {
            CrashReport.postCatchedException(e3);
            e3.printStackTrace();
        }
        try {
            try {
                fileInputStream = new FileInputStream(new File(SystemUiUtil.isExistDir("health/dial") + "/" + this.name));
            } catch (Exception e4) {
                byteArrayOutputStream2 = null;
                e2 = e4;
                fileInputStream = null;
            } catch (Throwable th2) {
                byteArrayOutputStream = null;
                th = th2;
                fileInputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int i2 = fileInputStream.read(bArr);
                        if (i2 == -1) {
                            break;
                        } else {
                            byteArrayOutputStream2.write(bArr, 0, i2);
                        }
                    } catch (Exception e5) {
                        e2 = e5;
                        CrashReport.postCatchedException(e2);
                        e2.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e6) {
                                CrashReport.postCatchedException(e6);
                                e6.printStackTrace();
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        return;
                    }
                }
                byteArrayOutputStream2.flush();
                this.bins = byteArrayOutputStream2.toByteArray();
                ImageBean bmpSize = AITools.getInstance().getBmpSize(this.bins);
                ImageBean compressionBmpSize = AITools.getInstance().getCompressionBmpSize(this.bins);
                this.width = bmpSize.width;
                this.height = bmpSize.height;
                this.size = bmpSize.size;
                this.radius = bmpSize.radius;
                this.cpWidth = compressionBmpSize.width;
                this.cpHeight = compressionBmpSize.height;
                this.cpRadius = compressionBmpSize.radius;
                this.cpSize = compressionBmpSize.size;
                initDialCpImage();
                try {
                    fileInputStream.close();
                } catch (Exception e7) {
                    CrashReport.postCatchedException(e7);
                    e7.printStackTrace();
                }
                byteArrayOutputStream2.close();
            } catch (Exception e8) {
                byteArrayOutputStream2 = null;
                e2 = e8;
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e9) {
                        CrashReport.postCatchedException(e9);
                        e9.printStackTrace();
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                        throw th;
                    } catch (Exception e10) {
                        CrashReport.postCatchedException(e10);
                        e10.printStackTrace();
                        throw th;
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDialCpImage() throws Throwable {
        Logger.d("chong---------width==" + this.width + "--" + this.height + "--" + this.size + "--" + this.cpWidth + "--" + this.cpHeight + "--" + this.cpRadius + "--" + this.cpSize);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.dial_custom_rl);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
        layoutParams.width = (int) (((this.width * layoutParams.height) * 1.0f) / this.height);
        relativeLayout.setLayoutParams(layoutParams);
        setDefaultData();
    }

    private void installDial(boolean isNew) throws IOException {
        Logger.d("vvvvv-----" + this.data.dialplateId + "--" + this.data.blockNumber);
        this.isInstalling = true;
        try {
            String str = SystemUiUtil.isExistDir("health/dial") + "/" + this.name;
            if (isNew) {
                str = SystemUiUtil.isExistDir("health/dial") + "/new_" + this.name;
            }
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 != -1) {
                    byteArrayOutputStream.write(bArr, 0, i2);
                } else {
                    byteArrayOutputStream.flush();
                    Logger.d("chong-----开始安装表盘");
                    YCBTClient.watchDialDownload(1, byteArrayOutputStream.toByteArray(), this.data.dialplateId, 0, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.7
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i3, float v, HashMap hashMap) {
                            if (i3 != 0) {
                                if (i3 == 2) {
                                    DialCustomizeEditActivity.this.handler.sendEmptyMessage(6);
                                    return;
                                } else {
                                    DialCustomizeEditActivity.this.handler.sendEmptyMessage(5);
                                    return;
                                }
                            }
                            if (hashMap != null && ((Integer) hashMap.get("dataType")).intValue() == 39168) {
                                Message message = new Message();
                                message.what = 8;
                                message.arg1 = (int) ((Float) hashMap.get("progress")).floatValue();
                                DialCustomizeEditActivity.this.handler.sendMessage(message);
                                return;
                            }
                            DialCustomizeEditActivity.this.handler.sendEmptyMessage(7);
                        }
                    });
                    return;
                }
            }
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
            Logger.d("chong-----开始安装表盘报错");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jlInstallCustomizeDial() {
        YCBTClient.jlInstallCustomizeDial(SystemUiUtil.isExistDir("health/dial") + "/" + getCustomBgName(this.name), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.8
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) throws NumberFormatException {
                if (i2 != 0) {
                    if (i2 == 2) {
                        DialCustomizeEditActivity.this.handler.sendEmptyMessage(6);
                        return;
                    } else {
                        DialCustomizeEditActivity.this.handler.sendEmptyMessage(5);
                        return;
                    }
                }
                if (hashMap != null && ((Integer) hashMap.get("dataType")).intValue() == 39168) {
                    Message message = new Message();
                    message.what = 8;
                    message.arg1 = (int) ((((Float) hashMap.get("progress")).floatValue() / 100.0f) * 60.0f);
                    DialCustomizeEditActivity.this.handler.sendMessage(message);
                    return;
                }
                DialCustomizeEditActivity.this.jlInstallCustomizeThumbnailDial();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jlInstallCustomizeThumbnailDial() throws NumberFormatException {
        String customThumbnailBgName = getCustomThumbnailBgName(this.name);
        final String str = SystemUiUtil.isExistDir("health/dial") + "/" + customThumbnailBgName;
        YCBTClient.jlWatchDialDelete(customThumbnailBgName, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.9
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                YCBTClient.jlInstallCustomizeDial(str, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.9.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int i3, float v2, HashMap hashMap2) {
                        if (i3 != 0) {
                            if (i3 == 2) {
                                DialCustomizeEditActivity.this.handler.sendEmptyMessage(6);
                                return;
                            } else {
                                DialCustomizeEditActivity.this.handler.sendEmptyMessage(5);
                                return;
                            }
                        }
                        if (hashMap2 != null && ((Integer) hashMap2.get("dataType")).intValue() == 39168) {
                            Message message = new Message();
                            message.what = 8;
                            message.arg1 = (int) (((((Float) hashMap2.get("progress")).floatValue() / 100.0f) * 40.0f) + 60.0f);
                            DialCustomizeEditActivity.this.handler.sendMessage(message);
                            return;
                        }
                        DialCustomizeEditActivity.this.handler.sendEmptyMessage(7);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteDial() {
        if (YCBTClient.connectState() != 10) {
            Tools.showAlert3(this, getString(R.string.please_connect_the_device));
        } else {
            YCBTClient.watchDialDelete(this.data.dialplateId, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.10
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) throws Throwable {
                    if (i2 == 0) {
                        DialCustomizeEditActivity.this.checkedInstallDial();
                    } else {
                        DialCustomizeEditActivity.this.handler.sendEmptyMessage(10);
                    }
                }
            });
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SubObserver.getInstance().delObs(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkedInstallDial() throws Throwable {
        MyDialog myDialog;
        if (YCBTClient.isJieLi()) {
            YCBTClient.jieliSetDialText(this.timeIndexType, convertRGB888toRGB565(this.color), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.11
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    if (i2 == 0) {
                        DialCustomizeEditActivity.this.jlInstallCustomizeDial();
                    }
                }
            });
            return;
        }
        byte[] bArr = this.bgBitmaps;
        if ((bArr != null || this.timeIndexType != 9 || this.color != 16777215) && this.thumbnails != null) {
            byte[] bmp565 = bArr != null ? AITools.getInstance().toBmp565(this.bgBitmaps, this.size, YCBTClient.isSupportFunction(Constants.FunctionConstant.ISFLIPDIALIMAGE)) : null;
            Logger.d("chong----------info--width==" + this.width + "--height==" + this.height + "--radius==" + this.radius + "--cpWidth==" + this.cpWidth + "--cpheight==" + this.cpHeight + "--radius==" + this.cpRadius);
            byte[] bArr2 = this.bins;
            if (bArr2 == null) {
                this.installDialog.dismiss();
                return;
            }
            byte[] bArr3 = new byte[bArr2.length];
            byte[] bmp565Thumb = AITools.getInstance().toBmp565Thumb(this.thumbnails, this.cpSize, YCBTClient.isSupportFunction(Constants.FunctionConstant.ISFLIPDIALIMAGE));
            AITools aITools = AITools.getInstance();
            byte[] bArr4 = this.bins;
            int[] iArr = this.positions;
            int i2 = iArr[0];
            int i3 = iArr[1];
            int i4 = this.color;
            if (aITools.modifyBinFile(bArr3, bArr4, bmp565, bmp565Thumb, i2, i3, (byte) (i4 >> 16), (byte) (i4 >> 8), (byte) i4)) {
                SystemUiUtil.saveBinFile(bArr3, SystemUiUtil.isExistDir("health/dial") + "/new_" + this.name);
                installDial(true);
                return;
            } else {
                if (isFinishing() || (myDialog = this.installDialog) == null || !myDialog.isShowing()) {
                    return;
                }
                this.installDialog.dismiss();
                Tools.showAlert3(this, getString(R.string.dial_is_install_failed));
                return;
            }
        }
        installDial(false);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (YCBTClient.connectState() != 10) {
            finish();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        try {
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock == null || !wakeLock.isHeld()) {
                return;
            }
            this.wakeLock.release();
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    private void showDialog() {
        if (!this.isInstalling) {
            finish();
        } else {
            new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black).setContentText(getString(R.string.dial_exit_content)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.12
                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickLeftButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                }

                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickRightButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                    DialCustomizeEditActivity.this.deleteDial();
                    DialCustomizeEditActivity.this.finish();
                }
            }).build().show();
        }
    }

    private class MyOnClickListenerImpl implements NavigationBar.MyOnClickListener {
        private MyOnClickListenerImpl() {
        }

        @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
        public void onClick(View v) throws Throwable {
            try {
                if (DialCustomizeEditActivity.this.installDialog == null) {
                    DialCustomizeEditActivity dialCustomizeEditActivity = DialCustomizeEditActivity.this;
                    dialCustomizeEditActivity.installDialog = (MyDialog) DialogUtils.createProgressDialog(dialCustomizeEditActivity);
                }
                DialCustomizeEditActivity.this.installDialog.show();
                if (!SaveBitmap888Util.saveBitmap888(SystemUiUtil.clip(DialCustomizeEditActivity.this.findViewById(R.id.dial_custom_rl), DialCustomizeEditActivity.this.cpWidth, DialCustomizeEditActivity.this.cpHeight, DialCustomizeEditActivity.this.cpRadius, Color.parseColor("#000000")), SystemUiUtil.isExistDir("health/dial") + "/new_" + DialCustomizeEditActivity.this.imgName + ".bmp")) {
                    DialCustomizeEditActivity dialCustomizeEditActivity2 = DialCustomizeEditActivity.this;
                    Tools.showAlert3(dialCustomizeEditActivity2, dialCustomizeEditActivity2.getString(R.string.down_failed));
                    DialCustomizeEditActivity.this.installDialog.dismiss();
                    return;
                }
                Tools.saveInt("radius", DialCustomizeEditActivity.this.cpRadius, DialCustomizeEditActivity.this);
                Tools.saveInt("custom_dial_color", DialCustomizeEditActivity.this.color, DialCustomizeEditActivity.this);
                Tools.saveInt("custom_dial_time_index", DialCustomizeEditActivity.this.timeIndexType, DialCustomizeEditActivity.this);
                DialCustomizeEditActivity.this.getThumbnails();
                if (DialCustomizeEditActivity.this.data.isCanDelete && !YCBTClient.isJieLi()) {
                    DialCustomizeEditActivity.this.deleteDial();
                } else {
                    DialCustomizeEditActivity.this.checkedInstallDial();
                }
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
                if (DialCustomizeEditActivity.this.isFinishing() || DialCustomizeEditActivity.this.installDialog == null || !DialCustomizeEditActivity.this.installDialog.isShowing()) {
                    return;
                }
                DialCustomizeEditActivity.this.installDialog.dismiss();
                DialCustomizeEditActivity.this.installDialog.setProgress(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changedBgImage() {
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
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.13
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                attributes.alpha = 1.0f;
                DialCustomizeEditActivity.this.getWindow().setAttributes(attributes);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PermissionUtil.openCameraPermission(DialCustomizeEditActivity.this)) {
                    DialCustomizeEditActivity dialCustomizeEditActivity = DialCustomizeEditActivity.this;
                    dialCustomizeEditActivity.tempFile = SystemUiUtil.gotoCamera(dialCustomizeEditActivity, 100);
                }
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PermissionUtil.openSDCardPermission(DialCustomizeEditActivity.this)) {
                    SystemUiUtil.gotoPhoto(DialCustomizeEditActivity.this, 101);
                }
                popupWindow.dismiss();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent intent) throws Throwable {
        File file;
        Uri data;
        switch (requestCode) {
            case 100:
                if (resultCode == -1 && (file = this.tempFile) != null) {
                    int i2 = this.cpRadius * 2;
                    int i3 = this.cpWidth;
                    SystemUiUtil.gotoClipActivity(this, Uri.fromFile(file), 102, this.width, this.height, (i2 < i3 || i3 != this.cpHeight) ? 0 : 1);
                    break;
                }
                break;
            case 101:
                if (resultCode == -1) {
                    int i4 = this.cpRadius * 2;
                    int i5 = this.cpWidth;
                    SystemUiUtil.gotoClipActivity(this, intent.getData(), 102, this.width, this.height, (i4 < i5 || i5 != this.cpHeight) ? 0 : 1);
                    break;
                }
                break;
            case 102:
                if (resultCode == -1 && (data = intent.getData()) != null) {
                    String realFilePathFromUri = FileUtil.getRealFilePathFromUri(this, data);
                    this.cropImagePath = realFilePathFromUri;
                    Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(realFilePathFromUri);
                    if (bitmapDecodeFile != null) {
                        this.dial_custom_bg_img.setImageBitmap(SystemUiUtil.getRoundBitmapByShader(bitmapDecodeFile, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), (int) (((this.cpRadius * 1.0f) * bitmapDecodeFile.getWidth()) / this.cpWidth), 2, Color.parseColor("#00ffffff")));
                        SaveBitmap888Util.saveBitmap888(SystemUiUtil.clip(bitmapDecodeFile, this.width, this.height, this.radius, 0, Color.parseColor("#000000")), SystemUiUtil.isExistDir("health/dial") + "/" + this.imgName + ".bmp");
                        if (YCBTClient.isJieLi()) {
                            String str = SystemUiUtil.isExistDir("health/dial") + "/background.png";
                            AppImageMgr.saveImage(SystemUiUtil.getRoundBitmapByShader(bitmapDecodeFile, this.width, this.height, this.radius, 0, Color.parseColor("#00ffffff")), str);
                            SaveBitmap888Util.saveBitmap888(SystemUiUtil.clip(findViewById(R.id.dial_custom_rl), this.cpWidth, this.cpHeight, this.cpRadius, Color.parseColor("#000000")), this.crop2);
                            saveJlBgImage(str);
                            saveJlThumbnailImage(this.crop2);
                            break;
                        } else {
                            getBgBitmaps();
                            break;
                        }
                    }
                }
                break;
        }
    }

    public void saveJieLiImage() throws Throwable {
        SaveBitmap888Util.saveBitmap888(SystemUiUtil.clip(findViewById(R.id.dial_custom_rl), this.cpWidth, this.cpHeight, this.cpRadius, Color.parseColor("#000000")), this.crop2);
        saveJlThumbnailImage(this.crop2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveJlBgImage(String imagePath) {
        YCBTClient.jlSaveCustomizeDialBg(imagePath, SystemUiUtil.isExistDir("health/dial") + "/" + getCustomBgName(this.name), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.17
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 != 0 || hashMap == null || hashMap.get("path") == null) {
                    return;
                }
                Logger.d("chong----path==" + ((String) hashMap.get("path")));
            }
        });
    }

    private void saveJlThumbnailImage(String imagePath) {
        YCBTClient.jlSaveCustomizeDialBg(imagePath, SystemUiUtil.isExistDir("health/dial") + "/" + getCustomThumbnailBgName(this.name), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.18
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 != 0 || hashMap == null || hashMap.get("path") == null) {
                    return;
                }
                Logger.d("chong----path==" + ((String) hashMap.get("path")));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCustomBgName(String name) throws NumberFormatException {
        int i2 = 0;
        if (name == null) {
            return "BGP_W" + formatSeq(0) + "";
        }
        String upperCase = name.toUpperCase();
        if (!upperCase.contains("WATCH")) {
            return "BGP_W" + formatSeq(0) + "";
        }
        if (!upperCase.equals("WATCH")) {
            String strReplaceAll = upperCase.replaceAll("WATCH", "");
            if (strReplaceAll.contains("(")) {
                strReplaceAll = strReplaceAll.substring(0, strReplaceAll.indexOf("("));
            }
            try {
                i2 = Integer.parseInt(strReplaceAll);
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
            }
        }
        return "BGP_W" + formatSeq(i2) + "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCustomThumbnailBgName(String name) throws NumberFormatException {
        int i2 = 0;
        if (name == null) {
            return "VIE_W" + formatSeq(0) + "";
        }
        String upperCase = name.toUpperCase();
        if (!upperCase.contains("WATCH")) {
            return "VIE_W" + formatSeq(0) + "";
        }
        if (!upperCase.equals("WATCH")) {
            String strReplaceAll = upperCase.replaceAll("WATCH", "");
            if (strReplaceAll.contains("(")) {
                strReplaceAll = strReplaceAll.substring(0, strReplaceAll.indexOf("("));
            }
            try {
                i2 = Integer.parseInt(strReplaceAll);
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
            }
        }
        return "VIE_W" + formatSeq(i2) + "";
    }

    private String formatSeq(int seq) {
        if (seq < 10) {
            return "00" + seq;
        }
        if (seq < 100) {
            return "0" + seq;
        }
        return String.valueOf(seq);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getBgBitmaps() throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = "health/dial"
            java.lang.String r3 = com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil.isExistDir(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = "/"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = r5.imgName     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = ".bmp"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            int r0 = r1.available()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            byte[] r0 = new byte[r0]     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            r5.bgBitmaps = r0     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            r1.read(r0)     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            r1.close()     // Catch: java.lang.Exception -> L51
            goto L58
        L3a:
            r0 = move-exception
            goto L45
        L3c:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L5a
        L41:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L45:
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r0)     // Catch: java.lang.Throwable -> L59
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L59
            if (r1 == 0) goto L58
            r1.close()     // Catch: java.lang.Exception -> L51
            goto L58
        L51:
            r0 = move-exception
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r0)
            r0.printStackTrace()
        L58:
            return
        L59:
            r0 = move-exception
        L5a:
            if (r1 == 0) goto L67
            r1.close()     // Catch: java.lang.Exception -> L60
            goto L67
        L60:
            r1 = move-exception
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r1)
            r1.printStackTrace()
        L67:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.getBgBitmaps():void");
    }

    private void getBgBitmaps(String name) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(name);
                    try {
                        byte[] bArr = new byte[fileInputStream2.available()];
                        this.bgBitmaps = bArr;
                        fileInputStream2.read(bArr);
                        fileInputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        CrashReport.postCatchedException(e);
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e3) {
                                CrashReport.postCatchedException(e3);
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
            CrashReport.postCatchedException(e5);
            e5.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getThumbnails() throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = "health/dial"
            java.lang.String r3 = com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil.isExistDir(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = "/new_"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = r5.imgName     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r3 = ".bmp"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            int r0 = r1.available()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            byte[] r0 = new byte[r0]     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            r5.thumbnails = r0     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            r1.read(r0)     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L59
            r1.close()     // Catch: java.lang.Exception -> L51
            goto L58
        L3a:
            r0 = move-exception
            goto L45
        L3c:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L5a
        L41:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L45:
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r0)     // Catch: java.lang.Throwable -> L59
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L59
            if (r1 == 0) goto L58
            r1.close()     // Catch: java.lang.Exception -> L51
            goto L58
        L51:
            r0 = move-exception
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r0)
            r0.printStackTrace()
        L58:
            return
        L59:
            r0 = move-exception
        L5a:
            if (r1 == 0) goto L67
            r1.close()     // Catch: java.lang.Exception -> L60
            goto L67
        L60:
            r1 = move-exception
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r1)
            r1.printStackTrace()
        L67:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.getThumbnails():void");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPositionDialog() {
        if (this.data == null || this.dialCustomPositionBeans == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < this.dialCustomPositionBeans.size(); i3++) {
            if (this.timeIndexType == this.dialCustomPositionBeans.get(i3).type) {
                i2 = i3;
            }
            switch (this.dialCustomPositionBeans.get(i3).type) {
                case 1:
                    arrayList.add(getString(R.string.dial_custom_top));
                    break;
                case 2:
                    arrayList.add(getString(R.string.dial_custom_bottom));
                    break;
                case 3:
                    arrayList.add(getString(R.string.dial_custom_left));
                    break;
                case 4:
                    arrayList.add(getString(R.string.dial_custom_right));
                    break;
                case 5:
                    arrayList.add(getString(R.string.dial_custom_left_top));
                    break;
                case 6:
                    arrayList.add(getString(R.string.dial_custom_right_top));
                    break;
                case 7:
                    arrayList.add(getString(R.string.dial_custom_left_bottom));
                    break;
                case 8:
                    arrayList.add(getString(R.string.dial_custom_right_bottom));
                    break;
                case 9:
                    arrayList.add(getString(R.string.dial_custom_mid));
                    break;
            }
        }
        SinglePicker singlePicker = new SinglePicker(this, arrayList);
        singlePicker.setCanLoop(false);
        singlePicker.setTopBackgroundColor(-1118482);
        singlePicker.setTopHeight(50);
        singlePicker.setTopLineColor(-13388315);
        singlePicker.setTopLineHeight(1);
        singlePicker.setTitleText(getString(R.string.pls_select));
        singlePicker.setTitleTextColor(-6710887);
        singlePicker.setTitleTextSize(12);
        singlePicker.setCancelTextColor(-13388315);
        singlePicker.setCancelTextSize(13);
        singlePicker.setSubmitTextColor(-13388315);
        singlePicker.setSubmitTextSize(13);
        singlePicker.setSubmitText(getString(R.string.ok));
        singlePicker.setSelectedTextColor(-1179648);
        singlePicker.setUnSelectedTextColor(-6710887);
        LineConfig lineConfig = new LineConfig();
        lineConfig.setColor(-1179648);
        lineConfig.setAlpha(140);
        lineConfig.setRatio(0.125f);
        singlePicker.setLineConfig(lineConfig);
        singlePicker.setItemWidth(200);
        singlePicker.setBackgroundColor(-1973791);
        singlePicker.setSelectedIndex(i2);
        final List<DialCustomPositionBean> list = this.dialCustomPositionBeans;
        singlePicker.setOnItemPickListener(new OnItemPickListener<String>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeEditActivity.19
            @Override // cn.addapp.pickers.listeners.OnItemPickListener
            public void onItemPicked(int index, String item) throws Throwable {
                DialCustomizeEditActivity.this.timeIndexType = ((DialCustomPositionBean) list.get(index)).type;
                DialCustomizeEditActivity.this.dial_custom_tv_time_position.setText(item);
                DialCustomizeEditActivity.this.setTimePosition();
                if (YCBTClient.isJieLi()) {
                    DialCustomizeEditActivity.this.saveJieLiImage();
                }
            }
        });
        singlePicker.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimePosition() {
        List<DialCustomPositionBean> list = this.dialCustomPositionBeans;
        if (list == null) {
            return;
        }
        for (DialCustomPositionBean dialCustomPositionBean : list) {
            if (dialCustomPositionBean.type == this.timeIndexType) {
                this.positions[0] = dialCustomPositionBean.point_x;
                this.positions[1] = dialCustomPositionBean.point_y;
            }
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        float f2 = getResources().getDisplayMetrics().density;
        switch (this.timeIndexType) {
            case 1:
                layoutParams.addRule(14);
                layoutParams.setMargins(0, (int) (f2 * 10.0f), 0, 0);
                break;
            case 2:
                layoutParams.addRule(14);
                layoutParams.addRule(2, R.id.dial_custom_invisible_time);
                layoutParams.setMargins(0, 0, 0, 0);
                break;
            case 3:
                layoutParams.addRule(15);
                layoutParams.setMargins((int) (f2 * 10.0f), 0, 0, 0);
                break;
            case 4:
                layoutParams.addRule(15);
                layoutParams.addRule(21);
                layoutParams.setMargins(0, 0, (int) (f2 * 10.0f), 0);
                break;
            case 5:
                int i2 = (int) (f2 * 20.0f);
                layoutParams.setMargins(i2, i2, 0, 0);
                break;
            case 6:
                layoutParams.addRule(21);
                int i3 = (int) (f2 * 20.0f);
                layoutParams.setMargins(0, i3, i3, 0);
                break;
            case 7:
                layoutParams.addRule(2, R.id.dial_custom_invisible_time2);
                layoutParams.setMargins((int) (f2 * 20.0f), 0, 0, 0);
                break;
            case 8:
                layoutParams.addRule(21);
                layoutParams.addRule(2, R.id.dial_custom_invisible_time2);
                layoutParams.setMargins(0, 0, (int) (f2 * 20.0f), 0);
                break;
            case 9:
                layoutParams.addRule(13);
                layoutParams.setMargins(0, 0, 0, 0);
                break;
        }
        this.dial_custom_time.setLayoutParams(layoutParams);
    }

    private class OnCheckedChangeListener1 implements RadioGroup.OnCheckedChangeListener {
        private OnCheckedChangeListener1() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup group, int checkedId) throws Throwable {
            if (DialCustomizeEditActivity.this.rg2) {
                DialCustomizeEditActivity.this.dial_custom_rg2.setOnCheckedChangeListener(null);
                DialCustomizeEditActivity.this.dial_custom_rg2.clearCheck();
                DialCustomizeEditActivity.this.dial_custom_rg2.setOnCheckedChangeListener(new OnCheckedChangeListener2());
                DialCustomizeEditActivity.this.rg2 = false;
            }
            DialCustomizeEditActivity.this.rg1 = true;
            DialCustomizeEditActivity.this.setCheckedId(checkedId);
        }
    }

    private class OnCheckedChangeListener2 implements RadioGroup.OnCheckedChangeListener {
        private OnCheckedChangeListener2() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup group, int checkedId) throws Throwable {
            if (DialCustomizeEditActivity.this.rg1) {
                DialCustomizeEditActivity.this.dial_custom_rg1.setOnCheckedChangeListener(null);
                DialCustomizeEditActivity.this.dial_custom_rg1.clearCheck();
                DialCustomizeEditActivity.this.dial_custom_rg1.setOnCheckedChangeListener(new OnCheckedChangeListener1());
                DialCustomizeEditActivity.this.rg1 = false;
            }
            DialCustomizeEditActivity.this.rg2 = true;
            DialCustomizeEditActivity.this.setCheckedId(checkedId);
        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.dial_custom_select_picture) {
                DialCustomizeEditActivity.this.changedBgImage();
            } else if (v.getId() == R.id.dial_custom_default) {
                DialCustomizeEditActivity.this.showDefaultDialog();
            } else if (v.getId() == R.id.dial_custom_ly_time_position) {
                DialCustomizeEditActivity.this.showPositionDialog();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int checkedId) throws Throwable {
        if (checkedId == R.id.dial_custom_radio1) {
            this.color = Color.parseColor("#F5222D");
        } else if (checkedId == R.id.dial_custom_radio2) {
            this.color = Color.parseColor("#FA541C");
        } else if (checkedId == R.id.dial_custom_radio3) {
            this.color = Color.parseColor("#FA8C16");
        } else if (checkedId == R.id.dial_custom_radio4) {
            this.color = Color.parseColor("#FADB14");
        } else if (checkedId == R.id.dial_custom_radio5) {
            this.color = Color.parseColor("#A0D911");
        } else if (checkedId == R.id.dial_custom_radio6) {
            this.color = Color.parseColor("#13C2C2");
        } else if (checkedId == R.id.dial_custom_radio7) {
            this.color = Color.parseColor("#1890FF");
        } else if (checkedId == R.id.dial_custom_radio8) {
            this.color = Color.parseColor("#2F54EB");
        } else if (checkedId == R.id.dial_custom_radio9) {
            this.color = Color.parseColor("#722ED1");
        } else if (checkedId == R.id.dial_custom_radio10) {
            this.color = Color.parseColor("#EB2F96");
        } else if (checkedId == R.id.dial_custom_radio11) {
            this.color = Color.parseColor("#FFFFFF");
        } else if (checkedId == R.id.dial_custom_radio12) {
            this.color = Color.parseColor("#000000");
        }
        this.dial_custom_time.setTextColor(this.color);
        this.dial_custom_date.setTextColor(this.color);
        this.dial_custom_week.setTextColor(this.color);
        if (YCBTClient.isJieLi()) {
            saveJieLiImage();
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object o) {
        if (Integer.parseInt(((Map) o).get("key").toString()) != 105 || YCBTClient.connectState() == 10) {
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialProgress(int progress) {
        MyDialog myDialog;
        this.data.progress = progress;
        try {
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock != null && !wakeLock.isHeld()) {
                this.wakeLock.acquire();
            }
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        this.handler.removeMessages(1);
        this.handler.sendEmptyMessageDelayed(1, 10000L);
        if (isFinishing() || (myDialog = this.installDialog) == null || !myDialog.isShowing()) {
            return;
        }
        this.installDialog.setProgress(this.data.progress);
    }
}
