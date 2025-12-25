package com.yucheng.smarthealthpro.me.setting.dial;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.login.widget.ToolTipPopup;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityDialBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.bean.ToAppDataResponse;
import com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter;
import com.yucheng.smarthealthpro.me.setting.dial.bean.DialResultBean;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.DialsBean;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class DialActivity extends BaseVbActivity<ActivityDialBinding> implements Observer {
    private DialAdapter adapter;
    private GridView dial_gv;
    private List<String> names;
    private SmartRefreshLayout smartRefreshLayout;
    private List<DialResultBean.Data> datas = new ArrayList();
    private List<DialResultBean.Data> deviceDials = new ArrayList();
    private List<DialResultBean.Data> deviceCustomDials = new ArrayList();
    private int currInstallPosition = -1;
    private int currDialPosition = -1;
    private int oldDialPosition = -1;
    private boolean isDelete = false;
    private boolean isInitDelete = false;
    private boolean isInstalling = false;
    private boolean isFirst = true;
    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;
    private boolean isSending = false;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    DialActivity.this.isSending = false;
                    return false;
                case 1:
                    try {
                        if (DialActivity.this.wakeLock != null && DialActivity.this.wakeLock.isHeld()) {
                            DialActivity.this.wakeLock.release();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return false;
                case 2:
                default:
                    return false;
                case 3:
                    YCBTClient.resetQueue();
                    DialActivity.this.getDeviceDial();
                    return false;
                case 4:
                    DialActivity.this.smartRefreshLayout.finishRefresh();
                    return false;
                case 5:
                    DialActivity.this.isInstalling = false;
                    DialActivity dialActivity = DialActivity.this;
                    Tools.showAlert3(dialActivity, dialActivity.getString(R.string.dial_is_install_failed));
                    DialActivity.this.getDeviceDial();
                    return false;
                case 6:
                    DialActivity.this.isInstalling = false;
                    DialActivity dialActivity2 = DialActivity.this;
                    Tools.showAlert3(dialActivity2, dialActivity2.getString(R.string.dial_is_install_max));
                    DialActivity.this.getDeviceDial();
                    return false;
                case 7:
                    DialActivity.this.isInstalling = false;
                    DialActivity dialActivity3 = DialActivity.this;
                    Tools.showAlert3(dialActivity3, dialActivity3.getString(R.string.dial_is_install_done));
                    DialActivity.this.getDeviceDial();
                    DialActivity dialActivity4 = DialActivity.this;
                    dialActivity4.setDial(dialActivity4.currInstallPosition);
                    return false;
                case 8:
                    DialActivity.this.setDialProgress(msg.arg1);
                    return false;
                case 9:
                    DialActivity dialActivity5 = DialActivity.this;
                    Tools.showAlert3(dialActivity5, dialActivity5.getString(R.string.dial_delete_success));
                    DialActivity.this.getDeviceDial();
                    return false;
                case 10:
                    DialActivity dialActivity6 = DialActivity.this;
                    Tools.showAlert3(dialActivity6, dialActivity6.getString(R.string.dial_delete_failed));
                    DialActivity.this.getDeviceDial();
                    return false;
                case 11:
                    if (DialActivity.this.currDials == 0) {
                        DialActivity.this.setRightImage(R.mipmap.dial_delete_icon);
                        DialActivity.this.closeRightImage();
                        DialActivity.this.isDelete = false;
                        Iterator it2 = DialActivity.this.datas.iterator();
                        while (it2.hasNext()) {
                            ((DialResultBean.Data) it2.next()).isDelete = false;
                        }
                    } else {
                        DialActivity.this.showRightImage();
                    }
                    DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
                    return false;
            }
        }
    });
    private int maxDials = 0;
    private int currDials = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialProgress(int progress) {
        Logger.d("chong-------progress==" + progress);
        int i2 = this.currInstallPosition;
        if (i2 >= 0 && i2 < this.datas.size()) {
            this.datas.get(this.currInstallPosition).state = 5;
            this.datas.get(this.currInstallPosition).isCanDelete = true;
            this.datas.get(this.currInstallPosition).progress = ((progress * 95) / 100) + 5;
            this.adapter.setDataChanged(this.datas);
            this.isInstalling = true;
            try {
                PowerManager.WakeLock wakeLock = this.wakeLock;
                if (wakeLock != null && !wakeLock.isHeld()) {
                    this.wakeLock.acquire();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, 10000L);
        }
        closeRightImage();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (YCBTClient.isJieLi() && !YCBTClient.getAuthPass()) {
            Toast.makeText(getApplication(), getString(R.string.jl_authing), 0).show();
            finish();
            return;
        }
        EventBus.getDefault().register(this);
        init();
        setListener();
        initData();
        this.smartRefreshLayout.autoRefresh();
    }

    private void init() {
        changeTitle(getResources().getString(R.string.setting_dial_title));
        findViewById(R.id.is_has_custom_dial).setVisibility(YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCUSTOMDIAL) ? 0 : 8);
        this.dial_gv = (GridView) findViewById(R.id.activity_dial_gv);
        this.smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        DialAdapter dialAdapter = new DialAdapter(this, this.datas);
        this.adapter = dialAdapter;
        this.dial_gv.setAdapter((ListAdapter) dialAdapter);
    }

    private void setListener() {
        this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListenerImpl());
        SubObserver.getInstance().addObs(this);
        showBack(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.2
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                DialActivity.this.showDialog();
            }
        });
        showRightImage(R.mipmap.dial_delete_icon, new MyOnClickListenerImpl());
        findViewById(R.id.dial_tv_customize).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.dial_tv_download_record).setOnClickListener(new OnClickListenerImpl());
        this.adapter.setListener(new SetDialListenerImpl());
    }

    private void initData() {
        try {
            PowerManager powerManager = (PowerManager) getSystemService("power");
            this.powerManager = powerManager;
            this.wakeLock = powerManager.newWakeLock(26, "My Lock");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.names = getFileName(SystemUiUtil.isExistDir("health/dial"));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.smartRefreshLayout.setEnableLoadMore(false);
        closeRightImage();
    }

    public static List<String> getFileName(String fileAbsolutePath) {
        ArrayList arrayList = new ArrayList();
        try {
            File[] fileArrListFiles = new File(fileAbsolutePath).listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                for (File file : fileArrListFiles) {
                    if (!file.isDirectory() && file.getName().endsWith(".bin")) {
                        arrayList.add(file.getName());
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMyDialog(final int position) {
        new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black_light).setContentText(getString(R.string.dial_is_delete_downloaded)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.3
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                DialActivity.this.deleteDial(position);
                dialog.dismiss();
            }
        }).build().show();
    }

    private void deleteDialFile(int position) {
        try {
            DialResultBean.Data data = this.datas.get(position);
            for (String str : this.names) {
                if (str.split("_")[0].equals(data.fileName.substring(data.fileName.lastIndexOf("/") + 1).split("_")[0])) {
                    File file = new File(SystemUiUtil.isExistDir("health/dial") + "/" + str);
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downDialFile(final int position) {
        List<DialResultBean.Data> list;
        List<DialResultBean.Data> list2 = this.deviceDials;
        if (list2 == null || list2.size() == 0 || (list = this.datas) == null || list.size() == 0) {
            return;
        }
        int i2 = this.currInstallPosition;
        if (i2 < 0 || i2 >= this.datas.size() || !(this.datas.get(this.currInstallPosition).state == 5 || this.datas.get(this.currInstallPosition).state == 2)) {
            if (this.currInstallPosition != position && this.currDials == 1 && this.maxDials == 1) {
                showReplaceDialog(position);
            } else {
                if (this.isInstalling) {
                    return;
                }
                this.isInstalling = true;
                deleteDialFile(position);
                Logger.d("chong----------fileName==" + this.datas.get(position).fileName);
                DownloadUtil.getInstance().download(this.datas.get(position).fileName, "health/dial", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.4
                    @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                    public void onDownloadSuccess() {
                        DialActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.4.1
                            @Override // java.lang.Runnable
                            public void run() throws IOException, NumberFormatException {
                                ((DialResultBean.Data) DialActivity.this.datas.get(position)).state = 5;
                                DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
                                DialActivity.this.installDial(position);
                            }
                        });
                    }

                    @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                    public void onDownloading(final int progress) {
                        DialActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.4.2
                            @Override // java.lang.Runnable
                            public void run() {
                                ((DialResultBean.Data) DialActivity.this.datas.get(position)).state = 2;
                                ((DialResultBean.Data) DialActivity.this.datas.get(position)).progress = (progress * 5) / 100;
                                DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
                            }
                        });
                    }

                    @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                    public void onDownloadFailed() {
                        DialActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.4.3
                            @Override // java.lang.Runnable
                            public void run() {
                                ((DialResultBean.Data) DialActivity.this.datas.get(position)).state = 0;
                                ((DialResultBean.Data) DialActivity.this.datas.get(position)).progress = 0;
                                DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
                                DialActivity.this.isInstalling = false;
                                Tools.showAlert3(DialActivity.this, DialActivity.this.getString(R.string.down_failed));
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installDial(final int position) throws IOException, NumberFormatException {
        List<DialResultBean.Data> list;
        int i2;
        if (YCBTClient.connectState() != 10) {
            Tools.showAlert3(this, getString(R.string.please_connect_the_device));
            return;
        }
        List<DialResultBean.Data> list2 = this.deviceDials;
        if (list2 == null || list2.size() == 0 || (list = this.datas) == null || list.size() == 0) {
            return;
        }
        this.currInstallPosition = position;
        int i3 = 0;
        if (this.maxDials == 1 && this.currDials >= 1 && this.datas.get(position).state != 6) {
            int i4 = 0;
            while (true) {
                if (i4 >= this.deviceDials.size()) {
                    i2 = -1;
                    i4 = 0;
                    break;
                } else {
                    if (this.deviceDials.get(i4).isCanDelete) {
                        i2 = this.deviceDials.get(i4).dialplateId;
                        break;
                    }
                    i4++;
                }
            }
            for (int i5 = 0; i5 < this.datas.size(); i5++) {
                if (this.datas.get(i5).dialplateId == i2) {
                    this.isInitDelete = true;
                    deleteDial(i5);
                    this.deviceDials.get(i4).isCanDelete = false;
                    return;
                }
            }
        }
        int i6 = this.datas.get(position).dialplateId;
        for (DialResultBean.Data data : this.deviceDials) {
            Logger.d("vvvvvv---id==" + data.dialplateId);
            if (data.dialplateId == i6) {
                this.datas.get(position).blockNumber = data.blockNumber;
            }
        }
        try {
            String str = SystemUiUtil.isExistDir("health/dial") + "/" + this.datas.get(position).fileName.substring(this.datas.get(position).fileName.lastIndexOf("/") + 1);
            Logger.d("chong-----开始安装表盘  path==" + str);
            MLog.INSTANCE.d("currDials: " + this.currDials + "   maxDials: " + this.maxDials);
            if (YCBTClient.isJieLi()) {
                if (this.currDials >= this.maxDials) {
                    this.handler.sendEmptyMessage(6);
                    return;
                } else {
                    YCBTClient.jlWatchDialDownload(str, false, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.5
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i7, float v, HashMap hashMap) {
                            if (i7 == 0 && (hashMap == null || ((Integer) hashMap.get("dataType")).intValue() != 39168)) {
                                DialActivity.this.setDial(position);
                            }
                            DialActivity.this.parseInstallResult(i7, hashMap);
                        }
                    });
                    return;
                }
            }
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i7 = fileInputStream.read(bArr);
                if (i7 == -1) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, i7);
                }
            }
            byteArrayOutputStream.flush();
            try {
                i3 = Integer.parseInt(this.datas.get(position).dialVersion);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            YCBTClient.watchDialDownload(1, byteArrayOutputStream.toByteArray(), i6, this.datas.get(position).blockNumber, i3, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.6
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i8, float v, HashMap hashMap) {
                    DialActivity.this.parseInstallResult(i8, hashMap);
                }
            });
        } catch (Exception e3) {
            e3.printStackTrace();
            Logger.d("chong-----开始安装表盘报错");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseInstallResult(int code, HashMap hashMap) {
        if (code != 0) {
            if (code == 2) {
                this.handler.sendEmptyMessage(6);
                return;
            } else {
                this.handler.sendEmptyMessage(5);
                return;
            }
        }
        if (hashMap != null && ((Integer) hashMap.get("dataType")).intValue() == 39168) {
            Message message = new Message();
            message.what = 8;
            try {
                message.arg1 = (int) ((Float) hashMap.get("progress")).floatValue();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.handler.sendMessage(message);
            return;
        }
        this.handler.sendEmptyMessage(7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseInstallDial(int position) {
        if (YCBTClient.connectState() != 10) {
            Tools.showAlert3(this, getString(R.string.please_connect_the_device));
        } else {
            sendBroadcast(new Intent("com.health.communication.SENDMSG").putExtra("type", 16).putExtra("id", this.datas.get(position).dialplateId));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDial(int position) {
        if (YCBTClient.connectState() != 10) {
            Tools.showAlert3(this, getString(R.string.please_connect_the_device));
            return;
        }
        this.currDialPosition = position;
        if (YCBTClient.isJieLi()) {
            String str = this.datas.get(position).fileName;
            Logger.d("chong--------filePath==" + str.substring(str.lastIndexOf("/")));
            YCBTClient.jlWatchDialSetCurrent(str.substring(str.lastIndexOf("/")), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.7
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                    if (code == 0) {
                        if (DialActivity.this.oldDialPosition >= 0 && DialActivity.this.oldDialPosition < DialActivity.this.datas.size()) {
                            ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.oldDialPosition)).state = 3;
                            ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.oldDialPosition)).progress = 0;
                        }
                        DialActivity dialActivity = DialActivity.this;
                        dialActivity.oldDialPosition = dialActivity.currDialPosition;
                        if (DialActivity.this.currDialPosition >= 0 && DialActivity.this.currDialPosition < DialActivity.this.datas.size()) {
                            ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.currDialPosition)).state = 4;
                            ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.currDialPosition)).progress = 0;
                        }
                        DialActivity.this.isSending = false;
                        DialActivity.this.handler.sendEmptyMessage(11);
                    }
                }
            });
            return;
        }
        YCBTClient.watchDialSetCurrent(this.datas.get(position).dialplateId, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.8
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float v, HashMap hashMap) {
                if (code == 0) {
                    if (DialActivity.this.oldDialPosition >= 0 && DialActivity.this.oldDialPosition < DialActivity.this.datas.size()) {
                        ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.oldDialPosition)).state = 3;
                        ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.oldDialPosition)).progress = 0;
                    }
                    DialActivity dialActivity = DialActivity.this;
                    dialActivity.oldDialPosition = dialActivity.currDialPosition;
                    if (DialActivity.this.currDialPosition >= 0 && DialActivity.this.currDialPosition < DialActivity.this.datas.size()) {
                        ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.currDialPosition)).state = 4;
                        ((DialResultBean.Data) DialActivity.this.datas.get(DialActivity.this.currDialPosition)).progress = 0;
                    }
                    DialActivity.this.isSending = false;
                    DialActivity.this.handler.sendEmptyMessage(11);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteDial(int position) {
        if (YCBTClient.connectState() != 10) {
            Tools.showAlert3(this, getString(R.string.please_connect_the_device));
            return;
        }
        if (YCBTClient.isJieLi()) {
            String str = this.datas.get(position).fileName;
            Logger.d("chong--------filePath==" + str.substring(str.lastIndexOf("/")));
            YCBTClient.jlWatchDialDelete(str.substring(str.lastIndexOf("/")), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.9
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) throws IOException, NumberFormatException {
                    DialActivity.this.parserDeleteResult(i2);
                }
            });
        } else {
            DialResultBean.Data data = this.datas.get(position);
            data.state = 0;
            data.progress = 0;
            data.blockNumber = 0;
            YCBTClient.watchDialDelete(data.dialplateId, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.10
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) throws IOException, NumberFormatException {
                    DialActivity.this.parserDeleteResult(i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parserDeleteResult(int i2) throws IOException, NumberFormatException {
        MLog.INSTANCE.d("parserDeleteResult: " + i2);
        if (i2 == 0) {
            if (this.isInitDelete) {
                this.isInitDelete = false;
                this.isInstalling = true;
                this.currDials--;
                installDial(this.currInstallPosition);
                return;
            }
            this.handler.sendEmptyMessage(9);
            return;
        }
        this.handler.sendEmptyMessage(10);
    }

    public void getDeviceName() {
        YCBTClient.getDeviceName(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.11
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 != 0 || hashMap == null) {
                    return;
                }
                DialActivity.this.getData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllData() {
        if (YCBTClient.connectState() == 10) {
            YCBTClient.watchDialInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.12
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) throws IOException {
                    if (i2 != 0 || hashMap == null) {
                        return;
                    }
                    DialActivity.this.closeSportData();
                    DialActivity.this.handler.removeMessages(3);
                    DialActivity.this.maxDials = ((Integer) hashMap.get("maxDials")).intValue();
                    DialActivity.this.currDials = ((Integer) hashMap.get("currDials")).intValue();
                    List<DialsBean> list = (List) hashMap.get("dials");
                    List<DialsBean> list2 = (List) hashMap.get("customDials");
                    DialActivity.this.deviceDials.clear();
                    DialActivity.this.deviceCustomDials.clear();
                    Logger.d("chong--------dial==" + DialActivity.this.maxDials + "--" + DialActivity.this.currDials + "--" + list.size() + "--" + list2.size() + "--" + YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCUSTOMDIAL));
                    for (DialsBean dialsBean : list) {
                        DialResultBean.Data data = new DialResultBean().getData();
                        data.dialplateId = dialsBean.dialplateId;
                        data.blockNumber = dialsBean.blockNumber;
                        data.isCanDelete = dialsBean.isCanDelete;
                        data.dialVersion = dialsBean.dialVersion + "";
                        DialActivity.this.deviceDials.add(data);
                        MLog.INSTANCE.d("data: " + data);
                    }
                    for (DialsBean dialsBean2 : list2) {
                        DialResultBean.Data data2 = new DialResultBean().getData();
                        data2.dialplateId = dialsBean2.dialplateId;
                        data2.blockNumber = dialsBean2.blockNumber;
                        data2.isCanDelete = dialsBean2.isCanDelete;
                        data2.dialVersion = dialsBean2.dialVersion + "";
                        DialActivity.this.deviceCustomDials.add(data2);
                        DialActivity dialActivity = DialActivity.this;
                        dialActivity.currDials--;
                    }
                    Logger.d("已安装表盘：" + DialActivity.this.deviceDials);
                    if (DialActivity.this.deviceDials != null && DialActivity.this.deviceDials.size() > 0 && DialActivity.this.datas != null && DialActivity.this.datas.size() > 0) {
                        DialActivity.this.isInstalling = false;
                        DialActivity.this.changeState();
                    }
                    String deviceType = Tools.getDeviceType(DialActivity.this.context);
                    Logger.d("getAllData type=" + deviceType);
                    HashMap map = new HashMap();
                    map.put("code", DialActivity.this.getString(R.string.lan));
                    map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(DialActivity.this.context, Constant.SpConstKey.TOKEN, ""));
                    map.put(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, "240");
                    map.put("height", "240");
                    map.put(Constants.FunctionConstant.DEVICETYPE, deviceType);
                    HttpUtils.getInstance().getMsgAsynHttp(DialActivity.this, com.yucheng.smarthealthpro.framework.util.Constants.DIALURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.12.1
                        @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                        public void onSuccess(String result) throws IOException {
                            DialResultBean dialResultBean;
                            if (result != null) {
                                try {
                                    dialResultBean = (DialResultBean) new Gson().fromJson(result, DialResultBean.class);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    Logger.d("ltf e=" + e2.getMessage());
                                    dialResultBean = null;
                                }
                                if (dialResultBean != null) {
                                    DialActivity.this.datas = dialResultBean.data;
                                    if (DialActivity.this.names != null && DialActivity.this.names.size() > 0) {
                                        for (String str : DialActivity.this.names) {
                                            try {
                                                for (DialResultBean.Data data3 : DialActivity.this.datas) {
                                                    String strSubstring = data3.fileName.substring(data3.fileName.lastIndexOf("/") + 1);
                                                    data3.state = 1;
                                                    if (str.equals(strSubstring)) {
                                                        break;
                                                    }
                                                }
                                            } catch (Exception e3) {
                                                e3.printStackTrace();
                                            }
                                        }
                                    }
                                    if (DialActivity.this.deviceDials != null && DialActivity.this.deviceDials.size() > 0 && DialActivity.this.datas != null && DialActivity.this.datas.size() > 0) {
                                        DialActivity.this.changeState();
                                    }
                                }
                            }
                            DialActivity.this.handler.sendEmptyMessage(11);
                            DialActivity.this.handler.removeMessages(4);
                            DialActivity.this.smartRefreshLayout.finishRefresh();
                        }
                    });
                }
            });
            this.handler.removeMessages(3);
            this.handler.sendEmptyMessageDelayed(3, 3000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getData() {
        String deviceType = Tools.getDeviceType(this.context);
        if (deviceType == null || "".equals(deviceType)) {
            YCBTClient.getDeviceName(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.13
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    if (i2 != 0 || hashMap == null) {
                        return;
                    }
                    DialActivity.this.getData();
                }
            });
            return;
        }
        Logger.d("chong---------type==" + deviceType);
        getDeviceDial();
        HashMap map = new HashMap();
        map.put("code", getString(R.string.lan));
        map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, ""));
        map.put(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, "240");
        map.put("height", "240");
        map.put(Constants.FunctionConstant.DEVICETYPE, deviceType);
        HttpUtils.getInstance().getMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.DIALURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.14
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws IOException {
                if (result != null) {
                    DialResultBean dialResultBean = (DialResultBean) new Gson().fromJson(result, DialResultBean.class);
                    if (dialResultBean != null) {
                        DialActivity.this.datas = dialResultBean.data;
                        if (DialActivity.this.names != null && DialActivity.this.names.size() > 0) {
                            for (String str : DialActivity.this.names) {
                                try {
                                    for (DialResultBean.Data data : DialActivity.this.datas) {
                                        if (data.imgName.contains("http://")) {
                                            data.imgName = data.imgName.replace("http://", "https://");
                                        }
                                        if (data.fileName.contains("http://")) {
                                            data.fileName = data.fileName.replace("http://", "https://");
                                        }
                                        String strSubstring = data.fileName.substring(data.fileName.lastIndexOf("/") + 1);
                                        data.state = 1;
                                        if (str.equals(strSubstring)) {
                                            break;
                                        }
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        if (DialActivity.this.deviceDials != null && DialActivity.this.deviceDials.size() > 0 && DialActivity.this.datas != null && DialActivity.this.datas.size() > 0) {
                            DialActivity.this.changeState();
                        }
                    }
                    DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
                }
                DialActivity.this.handler.removeMessages(4);
                DialActivity.this.handler.sendEmptyMessage(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceDial() {
        if (YCBTClient.connectState() == 10) {
            YCBTClient.watchDialInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.15
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) throws IOException {
                    if (i2 != 0 || hashMap == null) {
                        return;
                    }
                    DialActivity.this.closeSportData();
                    DialActivity.this.handler.removeMessages(3);
                    DialActivity.this.maxDials = ((Integer) hashMap.get("maxDials")).intValue();
                    DialActivity.this.currDials = ((Integer) hashMap.get("currDials")).intValue();
                    List<DialsBean> list = (List) hashMap.get("dials");
                    List<DialsBean> list2 = (List) hashMap.get("customDials");
                    DialActivity.this.deviceDials.clear();
                    DialActivity.this.deviceCustomDials.clear();
                    Logger.d("chong--------dial==" + DialActivity.this.maxDials + "--" + DialActivity.this.currDials + "--" + list.size() + "--" + list2.size() + "--" + YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCUSTOMDIAL));
                    for (DialsBean dialsBean : list) {
                        DialResultBean.Data data = new DialResultBean().getData();
                        data.dialplateId = dialsBean.dialplateId;
                        data.blockNumber = dialsBean.blockNumber;
                        data.isCanDelete = dialsBean.isCanDelete;
                        data.dialVersion = dialsBean.dialVersion + "";
                        DialActivity.this.deviceDials.add(data);
                    }
                    for (DialsBean dialsBean2 : list2) {
                        DialResultBean.Data data2 = new DialResultBean().getData();
                        data2.dialplateId = dialsBean2.dialplateId;
                        data2.blockNumber = dialsBean2.blockNumber;
                        data2.isCanDelete = dialsBean2.isCanDelete;
                        data2.dialVersion = dialsBean2.dialVersion + "";
                        DialActivity.this.deviceCustomDials.add(data2);
                        DialActivity dialActivity = DialActivity.this;
                        dialActivity.currDials--;
                    }
                    if (DialActivity.this.deviceDials != null && DialActivity.this.deviceDials.size() > 0 && DialActivity.this.datas != null && DialActivity.this.datas.size() > 0) {
                        DialActivity.this.isInstalling = false;
                        DialActivity.this.changeState();
                    }
                    DialActivity.this.handler.sendEmptyMessage(11);
                }
            });
            this.handler.removeMessages(3);
            this.handler.sendEmptyMessageDelayed(3, 3000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeSportData() {
        if (this.isFirst) {
            this.isFirst = false;
            YCBTClient.appRealSportFromDevice(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.16
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                }
            });
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object o) throws NumberFormatException {
        Map map = (Map) o;
        if (map.get("key") == null) {
            return;
        }
        int i2 = Integer.parseInt(map.get("key").toString());
        Logger.d("vvvvvv---key==" + i2);
        if (i2 == 1) {
            this.isSending = false;
            byte[] bArr = (byte[]) map.get("smsg");
            if (bArr == null) {
                return;
            }
            byte b2 = bArr[0];
            if (((b2 & 255) != 9 || (bArr[1] & 255) != 3) && (b2 & 255) == 4 && (bArr[1] & 255) == 13) {
                int i3 = (bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24);
                for (int i4 = 0; i4 < this.datas.size(); i4++) {
                    DialResultBean.Data data = this.datas.get(i4);
                    if (data.state == 4) {
                        data.state = 3;
                    }
                    if (data.dialplateId == i3) {
                        data.state = 4;
                        this.oldDialPosition = i4;
                    }
                }
            }
            this.adapter.setDataChanged(this.datas);
            return;
        }
        if (i2 != 105 || YCBTClient.connectState() == 10) {
            return;
        }
        finish();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        SubObserver.getInstance().delObs(this);
        int i2 = this.currInstallPosition;
        if (i2 < 0 || i2 >= this.datas.size() || YCBTClient.getChipScheme() == 3) {
            return;
        }
        YCBTClient.watchDialDownload(0, new byte[]{0}, 0, 0, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.17
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i3, float v, HashMap hashMap) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeState() throws IOException {
        if (this.datas.size() > 0 && this.deviceDials.size() > 0) {
            this.handler.removeMessages(4);
            this.smartRefreshLayout.finishRefresh();
        }
        for (int i2 = 0; i2 < this.datas.size(); i2++) {
            DialResultBean.Data data = this.datas.get(i2);
            if (data.imgName.contains("http://")) {
                data.imgName = data.imgName.replace("http://", "https://");
            }
            if (data.fileName.contains("http://")) {
                data.fileName = data.fileName.replace("http://", "https://");
            }
            if (data.backgroundImgUrl.contains("http://")) {
                data.backgroundImgUrl = data.backgroundImgUrl.replace("http://", "https://");
            }
            data.state = 0;
            Iterator<DialResultBean.Data> it2 = this.deviceDials.iterator();
            while (true) {
                if (it2.hasNext()) {
                    DialResultBean.Data next = it2.next();
                    if (next.dialplateId == data.dialplateId) {
                        data.isCanDelete = next.isCanDelete;
                        try {
                            if (!TextUtils.isEmpty(data.dialVersion) && !TextUtils.isEmpty(next.dialVersion) && Integer.parseInt(next.dialVersion) > Integer.parseInt(data.dialVersion)) {
                                data.isUpdate = true;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (next.blockNumber == 0) {
                            data.state = 3;
                            data.progress = 0;
                        } else if (next.blockNumber == 65535) {
                            this.oldDialPosition = i2;
                            data.state = 4;
                            data.progress = 0;
                        } else {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(new File(SystemUiUtil.isExistDir("health/dial") + "/" + data.fileName.substring(data.fileName.lastIndexOf("/") + 1)));
                                byte[] bArr = new byte[1024];
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                while (true) {
                                    int i3 = fileInputStream.read(bArr);
                                    if (i3 == -1) {
                                        break;
                                    } else {
                                        byteArrayOutputStream.write(bArr, 0, i3);
                                    }
                                }
                                byteArrayOutputStream.flush();
                                data.progress = ((((next.blockNumber * 409600) / byteArrayOutputStream.toByteArray().length) * 95) / 100) + 5;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            data.state = 6;
                        }
                    }
                }
            }
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (YCBTClient.connectState() != 10) {
            finish();
        }
        getDeviceDial();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataResponse(ToAppDataResponse toAppDataResponse) {
        Log.e("result=", "onDataResponse deviceToApp");
        HashMap map = toAppDataResponse.hashMap;
        if (toAppDataResponse.f5706i != 0 || map == null || map.get("dataType") == null || ((Integer) map.get("dataType")).intValue() != 1037) {
            return;
        }
        int i2 = 0;
        if (YCBTClient.isJieLi()) {
            String string = map.get("datas").toString();
            while (i2 < this.datas.size()) {
                DialResultBean.Data data = this.datas.get(i2);
                String str = data.fileName;
                if (str.substring(str.lastIndexOf("/")).equalsIgnoreCase(string)) {
                    data.state = 4;
                    this.oldDialPosition = i2;
                } else if (data.state == 4) {
                    data.state = 3;
                }
                i2++;
            }
        } else {
            byte[] bArr = (byte[]) map.get("datas");
            int i3 = (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16) + ((bArr[3] & 255) << 24);
            Logger.d("chong---------id==" + i3);
            while (i2 < this.datas.size()) {
                DialResultBean.Data data2 = this.datas.get(i2);
                if (data2.state == 4) {
                    data2.state = 3;
                }
                if (data2.dialplateId == i3) {
                    data2.state = 4;
                    this.oldDialPosition = i2;
                }
                i2++;
            }
        }
        this.handler.sendEmptyMessage(11);
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
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            showDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog() {
        if (!this.isInstalling || this.currInstallPosition == -1) {
            finish();
        } else {
            new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black).setContentText(getString(R.string.dial_exit_content)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.18
                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickLeftButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                }

                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickRightButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                    DialActivity dialActivity = DialActivity.this;
                    dialActivity.pauseInstallDial(dialActivity.currInstallPosition);
                    DialActivity.this.finish();
                }
            }).build().show();
        }
    }

    private void showReplaceDialog(final int position) {
        new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black).setContentText(getString(R.string.dial_replace_content)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialActivity.19
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
                DialActivity.this.currInstallPosition = position;
                DialActivity.this.downDialFile(position);
            }
        }).build().show();
    }

    private class MyOnClickListenerImpl implements NavigationBar.MyOnClickListener {
        private MyOnClickListenerImpl() {
        }

        @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
        public void onClick(View btn) {
            if (DialActivity.this.isInstalling) {
                return;
            }
            if (!DialActivity.this.isDelete) {
                DialActivity.this.isDelete = true;
                DialActivity.this.setRightImage(R.mipmap.dial_ok_icon);
                Iterator it2 = DialActivity.this.datas.iterator();
                while (it2.hasNext()) {
                    ((DialResultBean.Data) it2.next()).isDelete = true;
                }
            } else {
                DialActivity.this.isDelete = false;
                DialActivity.this.setRightImage(R.mipmap.dial_delete_icon);
                Iterator it3 = DialActivity.this.datas.iterator();
                while (it3.hasNext()) {
                    ((DialResultBean.Data) it3.next()).isDelete = false;
                }
            }
            DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.dial_tv_download_record) {
                DialActivity.this.startActivity(new Intent(DialActivity.this, (Class<?>) DialDownRecordActivity.class));
            } else {
                if (v.getId() != R.id.dial_tv_customize || DialActivity.this.isInstalling) {
                    return;
                }
                DialActivity.this.startActivity(new Intent(DialActivity.this, (Class<?>) DialCustomizeActivity.class));
            }
        }
    }

    private class SetDialListenerImpl implements DialAdapter.SetDialListener {
        private SetDialListenerImpl() {
        }

        @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.SetDialListener
        public void callback(int position) throws IOException, NumberFormatException {
            if (DialActivity.this.isSending || DialActivity.this.datas.size() == 0) {
                return;
            }
            DialActivity.this.isSending = true;
            DialActivity.this.handler.sendEmptyMessageDelayed(0, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
            DialResultBean.Data data = (DialResultBean.Data) DialActivity.this.datas.get(position);
            Logger.d("chong----------position==" + position + "--state==" + data.state);
            if (data.state == 1) {
                DialActivity.this.downDialFile(position);
            } else if (data.state != 2) {
                if (data.state == 3) {
                    if (!DialActivity.this.isInstalling) {
                        DialActivity.this.setDial(position);
                    }
                } else if (data.state != 4 && data.state != 5) {
                    if (data.state == 6) {
                        DialActivity.this.isInstalling = true;
                        DialActivity.this.installDial(position);
                    } else if (data.state == 7) {
                        data.state = 0;
                    } else {
                        DialActivity.this.downDialFile(position);
                    }
                }
            }
            DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
        }

        @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.SetDialListener
        public void update(int position) {
            DialResultBean.Data data = (DialResultBean.Data) DialActivity.this.datas.get(position);
            if (data.isUpdate) {
                data.isUpdate = false;
                data.state = 0;
                DialActivity.this.downDialFile(position);
                DialActivity.this.adapter.setDataChanged(DialActivity.this.datas);
            }
        }

        @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.SetDialListener
        public void delete(int position) {
            DialResultBean.Data data = (DialResultBean.Data) DialActivity.this.datas.get(position);
            if (data.isCanDelete) {
                if (data.state == 3 || data.state == 4 || data.state == 5 || data.state == 6) {
                    DialActivity.this.showMyDialog(position);
                }
            }
        }
    }

    private class OnRefreshListenerImpl implements OnRefreshListener {
        private OnRefreshListenerImpl() {
        }

        @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
        public void onRefresh(RefreshLayout refreshlayout) {
            if (DialActivity.this.isInstalling) {
                DialActivity.this.smartRefreshLayout.finishRefresh();
                return;
            }
            DialActivity.this.datas.clear();
            DialActivity.this.deviceDials.clear();
            DialActivity.this.getAllData();
            DialActivity.this.handler.removeMessages(4);
            DialActivity.this.handler.sendEmptyMessageDelayed(4, 30000L);
        }
    }
}
