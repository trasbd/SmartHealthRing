package com.yucheng.smarthealthpro.me.setting.dial;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityDialCustomizeBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.bean.ToAppDataResponse;
import com.yucheng.smarthealthpro.me.setting.dial.adapter.DialCustomizeAdapter;
import com.yucheng.smarthealthpro.me.setting.dial.bean.DialResultBean;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.DialsBean;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
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
public class DialCustomizeActivity extends BaseVbActivity<ActivityDialCustomizeBinding> implements Observer {
    private DialCustomizeAdapter adapter;
    private GridView dial_customize_gv;
    private BluetoothDevice myDevice;
    private List<String> names;
    private SmartRefreshLayout smartRefreshLayout;
    private List<DialResultBean.Data> datas = new ArrayList();
    private List<DialResultBean.Data> deviceDials = new ArrayList();
    private int currInstallPosition = -1;
    private int currDialPosition = -1;
    private int oldDialPosition = -1;
    private boolean isDelete = false;
    private boolean isInitDelete = false;
    private boolean isInstalling = false;
    private boolean isFirst = true;
    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;
    private boolean isSending = false;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    DialCustomizeActivity.this.isSending = false;
                    return false;
                case 1:
                    try {
                        if (DialCustomizeActivity.this.wakeLock != null && DialCustomizeActivity.this.wakeLock.isHeld()) {
                            DialCustomizeActivity.this.wakeLock.release();
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
                    DialCustomizeActivity.this.getDeviceDial();
                    return false;
                case 4:
                    DialCustomizeActivity.this.smartRefreshLayout.finishRefresh();
                    return false;
                case 5:
                    DialCustomizeActivity.this.isInstalling = false;
                    DialCustomizeActivity dialCustomizeActivity = DialCustomizeActivity.this;
                    Tools.showAlert3(dialCustomizeActivity, dialCustomizeActivity.getString(R.string.dial_is_install_failed));
                    DialCustomizeActivity.this.getDeviceDial();
                    return false;
                case 6:
                    DialCustomizeActivity.this.isInstalling = false;
                    DialCustomizeActivity dialCustomizeActivity2 = DialCustomizeActivity.this;
                    Tools.showAlert3(dialCustomizeActivity2, dialCustomizeActivity2.getString(R.string.dial_is_install_max));
                    DialCustomizeActivity.this.getDeviceDial();
                    return false;
                case 7:
                    DialCustomizeActivity.this.isInstalling = false;
                    DialCustomizeActivity dialCustomizeActivity3 = DialCustomizeActivity.this;
                    Tools.showAlert3(dialCustomizeActivity3, dialCustomizeActivity3.getString(R.string.dial_is_install_done));
                    DialCustomizeActivity.this.getDeviceDial();
                    return false;
                case 8:
                    DialCustomizeActivity.this.setDialProgress(msg.arg1);
                    return false;
                case 9:
                    DialCustomizeActivity dialCustomizeActivity4 = DialCustomizeActivity.this;
                    Tools.showAlert3(dialCustomizeActivity4, dialCustomizeActivity4.getString(R.string.dial_delete_success));
                    DialCustomizeActivity.this.getDeviceDial();
                    return false;
                case 10:
                    DialCustomizeActivity dialCustomizeActivity5 = DialCustomizeActivity.this;
                    Tools.showAlert3(dialCustomizeActivity5, dialCustomizeActivity5.getString(R.string.dial_delete_failed));
                    DialCustomizeActivity.this.getDeviceDial();
                    return false;
                case 11:
                    if (DialCustomizeActivity.this.currDials == 0) {
                        DialCustomizeActivity.this.setRightImage(R.mipmap.dial_delete_icon);
                        DialCustomizeActivity.this.closeRightImage();
                        DialCustomizeActivity.this.isDelete = false;
                        Iterator it2 = DialCustomizeActivity.this.datas.iterator();
                        while (it2.hasNext()) {
                            ((DialResultBean.Data) it2.next()).isDelete = false;
                        }
                    } else {
                        DialCustomizeActivity.this.showRightImage();
                    }
                    DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
                    return false;
            }
        }
    });
    private boolean isGettingDialInfo = false;
    private int maxDials = 0;
    private int currDials = 0;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        init();
        setListener();
        initData();
        getData();
    }

    private void init() {
        changeTitle(getResources().getString(R.string.dial_customize));
        this.dial_customize_gv = (GridView) findViewById(R.id.activity_dial_customize_gv);
        this.smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        DialCustomizeAdapter dialCustomizeAdapter = new DialCustomizeAdapter(this, this.datas);
        this.adapter = dialCustomizeAdapter;
        this.dial_customize_gv.setAdapter((ListAdapter) dialCustomizeAdapter);
        this.dial_customize_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (PermissionUtil.openSDCardPermission(DialCustomizeActivity.this)) {
                    DialCustomizeActivity.this.startActivity(new Intent(DialCustomizeActivity.this, (Class<?>) DialCustomizeEditActivity.class).putExtra("data", (Serializable) DialCustomizeActivity.this.datas.get(position)));
                }
            }
        });
    }

    private void setListener() {
        this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListenerImpl());
        SubObserver.getInstance().addObs(this);
        showBack(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.3
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                DialCustomizeActivity.this.showDialog();
            }
        });
        showRightImage(R.mipmap.dial_delete_icon, new MyOnClickListenerImpl());
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
        new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black_light).setContentText(getString(R.string.dial_is_delete_downloaded)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.4
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                DialCustomizeActivity.this.deleteDial(position);
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
        Logger.w(new Gson().toJson(this.datas), new Object[0]);
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
                Logger.w(this.datas.get(position).fileName, new Object[0]);
                DownloadUtil.getInstance().download(this.datas.get(position).fileName, "health/dial", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.5
                    @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                    public void onDownloadSuccess() {
                        DialCustomizeActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(position)).state = 5;
                                DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
                                DialCustomizeActivity.this.installDial(position);
                            }
                        });
                    }

                    @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                    public void onDownloading(final int progress) {
                        DialCustomizeActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.5.2
                            @Override // java.lang.Runnable
                            public void run() {
                                ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(position)).state = 2;
                                ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(position)).progress = (progress * 5) / 100;
                                DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
                            }
                        });
                    }

                    @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                    public void onDownloadFailed() {
                        DialCustomizeActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.5.3
                            @Override // java.lang.Runnable
                            public void run() {
                                ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(position)).state = 0;
                                ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(position)).progress = 0;
                                DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
                                Tools.showAlert3(DialCustomizeActivity.this, DialCustomizeActivity.this.getString(R.string.down_failed));
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installDial(int position) {
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
        if (this.maxDials == 1 && this.currDials == 1 && this.datas.get(position).state != 6) {
            int i3 = 0;
            while (true) {
                if (i3 >= this.deviceDials.size()) {
                    i2 = -1;
                    break;
                } else {
                    if (this.deviceDials.get(i3).isCanDelete) {
                        i2 = this.deviceDials.get(i3).dialplateId;
                        break;
                    }
                    i3++;
                }
            }
            for (int i4 = 0; i4 < this.datas.size(); i4++) {
                if (this.datas.get(i4).dialplateId == i2) {
                    deleteDial(i4);
                    this.isInitDelete = true;
                    return;
                }
            }
        }
        int i5 = this.datas.get(position).dialplateId;
        for (DialResultBean.Data data : this.deviceDials) {
            Logger.d("vvvvvv---id==" + data.dialplateId);
            if (data.dialplateId == i5) {
                this.datas.get(position).blockNumber = data.blockNumber;
            }
        }
        Logger.d("vvvvv-----" + this.datas.get(position).dialplateId + "--" + this.datas.get(position).blockNumber);
        sendBroadcast(new Intent("com.health.communication.SENDMSG").putExtra("type", 14).putExtra("name", this.datas.get(position).fileName.substring(this.datas.get(position).fileName.lastIndexOf("/") + 1)).putExtra("id", i5).putExtra("currIndex", this.datas.get(position).blockNumber));
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
            YCBTClient.jlWatchDialSetCurrent(str.substring(str.lastIndexOf("/")), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.6
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                    if (code == 0) {
                        if (DialCustomizeActivity.this.oldDialPosition >= 0 && DialCustomizeActivity.this.oldDialPosition < DialCustomizeActivity.this.datas.size()) {
                            ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.oldDialPosition)).state = 3;
                            ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.oldDialPosition)).progress = 0;
                        }
                        DialCustomizeActivity dialCustomizeActivity = DialCustomizeActivity.this;
                        dialCustomizeActivity.oldDialPosition = dialCustomizeActivity.currDialPosition;
                        if (DialCustomizeActivity.this.currDialPosition >= 0 && DialCustomizeActivity.this.currDialPosition < DialCustomizeActivity.this.datas.size()) {
                            ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.currDialPosition)).state = 4;
                            ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.currDialPosition)).progress = 0;
                        }
                        DialCustomizeActivity.this.isSending = false;
                        DialCustomizeActivity.this.handler.sendEmptyMessage(11);
                    }
                }
            });
            return;
        }
        YCBTClient.watchDialSetCurrent(this.datas.get(position).dialplateId, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.7
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float v, HashMap hashMap) {
                if (code == 0) {
                    if (DialCustomizeActivity.this.oldDialPosition >= 0 && DialCustomizeActivity.this.oldDialPosition < DialCustomizeActivity.this.datas.size()) {
                        ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.oldDialPosition)).state = 3;
                        ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.oldDialPosition)).progress = 0;
                    }
                    DialCustomizeActivity dialCustomizeActivity = DialCustomizeActivity.this;
                    dialCustomizeActivity.oldDialPosition = dialCustomizeActivity.currDialPosition;
                    if (DialCustomizeActivity.this.currDialPosition >= 0 && DialCustomizeActivity.this.currDialPosition < DialCustomizeActivity.this.datas.size()) {
                        ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.currDialPosition)).state = 4;
                        ((DialResultBean.Data) DialCustomizeActivity.this.datas.get(DialCustomizeActivity.this.currDialPosition)).progress = 0;
                    }
                    DialCustomizeActivity.this.isSending = false;
                    DialCustomizeActivity.this.handler.sendEmptyMessage(11);
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
        DialResultBean.Data data = this.datas.get(position);
        data.state = 0;
        data.progress = 0;
        data.blockNumber = 0;
        YCBTClient.watchDialDelete(data.dialplateId, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.8
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 == 0) {
                    if (DialCustomizeActivity.this.isInitDelete) {
                        DialCustomizeActivity.this.isInitDelete = false;
                        DialCustomizeActivity.this.isInstalling = true;
                        DialCustomizeActivity.this.currDials--;
                        DialCustomizeActivity dialCustomizeActivity = DialCustomizeActivity.this;
                        dialCustomizeActivity.installDial(dialCustomizeActivity.currInstallPosition);
                        return;
                    }
                    DialCustomizeActivity.this.handler.sendEmptyMessage(9);
                    return;
                }
                DialCustomizeActivity.this.handler.sendEmptyMessage(10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getData() {
        String deviceType = Tools.getDeviceType(this.context);
        if (deviceType == null || "".equals(deviceType)) {
            YCBTClient.getDeviceName(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.9
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    if (i2 != 0 || hashMap == null) {
                        return;
                    }
                    DialCustomizeActivity.this.getData();
                }
            });
            return;
        }
        getDeviceDial();
        HashMap map = new HashMap();
        map.put("code", getString(R.string.lan));
        map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, ""));
        map.put(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, "150");
        map.put("height", "150");
        map.put(Constants.FunctionConstant.DEVICETYPE, deviceType);
        HttpUtils.getInstance().getMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.DIALURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws IOException {
                Log.e("DialCustomizeActivity", "" + result);
                if (result != null) {
                    DialResultBean dialResultBean = (DialResultBean) new Gson().fromJson(result, DialResultBean.class);
                    if (dialResultBean != null) {
                        DialCustomizeActivity.this.datas = dialResultBean.data;
                        if (DialCustomizeActivity.this.names != null && DialCustomizeActivity.this.names.size() > 0) {
                            for (String str : DialCustomizeActivity.this.names) {
                                try {
                                    for (DialResultBean.Data data : DialCustomizeActivity.this.datas) {
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
                        if (DialCustomizeActivity.this.deviceDials != null && DialCustomizeActivity.this.deviceDials.size() > 0 && DialCustomizeActivity.this.datas.size() > 0) {
                            DialCustomizeActivity.this.changeState();
                        }
                    }
                    DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
                }
                DialCustomizeActivity.this.handler.removeMessages(4);
                DialCustomizeActivity.this.handler.sendEmptyMessage(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceDial() {
        if (YCBTClient.connectState() == 10) {
            YCBTClient.watchDialInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.11
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) throws IOException {
                    if (i2 != 0 || hashMap == null) {
                        return;
                    }
                    DialCustomizeActivity.this.closeSportData();
                    DialCustomizeActivity.this.handler.removeMessages(3);
                    DialCustomizeActivity.this.maxDials = ((Integer) hashMap.get("maxDials")).intValue();
                    List list = (List) hashMap.get("dials");
                    List<DialsBean> list2 = (List) hashMap.get("customDials");
                    DialCustomizeActivity.this.currDials = list2.size();
                    DialCustomizeActivity.this.deviceDials.clear();
                    Logger.d("chong--------dial==" + DialCustomizeActivity.this.maxDials + "--" + DialCustomizeActivity.this.currDials + "--" + list.size() + "--" + list2.size() + "--" + YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCUSTOMDIAL));
                    for (DialsBean dialsBean : list2) {
                        DialResultBean.Data data = new DialResultBean().getData();
                        data.dialplateId = dialsBean.dialplateId;
                        data.blockNumber = dialsBean.blockNumber;
                        data.isCanDelete = dialsBean.isCanDelete;
                        data.dialVersion = dialsBean.dialVersion + "";
                        DialCustomizeActivity.this.deviceDials.add(data);
                    }
                    if (DialCustomizeActivity.this.deviceDials != null && DialCustomizeActivity.this.deviceDials.size() > 0 && DialCustomizeActivity.this.datas.size() > 0) {
                        DialCustomizeActivity.this.isInstalling = false;
                        DialCustomizeActivity.this.changeState();
                    }
                    DialCustomizeActivity.this.handler.sendEmptyMessage(11);
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
            YCBTClient.appRealSportFromDevice(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.12
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                }
            });
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object o) throws NumberFormatException {
        int i2 = Integer.parseInt(((Map) o).get("key").toString());
        Logger.d("vvvvvv---key==" + i2);
        if (i2 != 105 || YCBTClient.connectState() == 10) {
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialProgress(int progress) {
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

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        SubObserver.getInstance().delObs(this);
        int i2 = this.currInstallPosition;
        if (i2 < 0 || i2 >= this.datas.size()) {
            return;
        }
        YCBTClient.watchDialDownload(0, new byte[]{0}, 0, 0, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.13
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
                            } catch (Exception e2) {
                                e2.printStackTrace();
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
        if (toAppDataResponse.f5706i == 0 && map.get("dataType") != null && ((Integer) map.get("dataType")).intValue() == 1037) {
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
                return;
            }
            byte[] bArr = (byte[]) map.get("data");
            if (bArr == null) {
                return;
            }
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
            this.handler.sendEmptyMessage(11);
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
        if (!this.isInstalling) {
            finish();
        } else {
            new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black).setContentText(getString(R.string.dial_exit_content)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.14
                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickLeftButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                }

                @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
                public void clickRightButton(MDAlertDialog dialog, View view) {
                    dialog.dismiss();
                    DialCustomizeActivity dialCustomizeActivity = DialCustomizeActivity.this;
                    dialCustomizeActivity.pauseInstallDial(dialCustomizeActivity.currInstallPosition);
                    DialCustomizeActivity.this.finish();
                }
            }).build().show();
        }
    }

    private void showReplaceDialog(final int position) {
        new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black).setContentText(getString(R.string.dial_replace_content)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialCustomizeActivity.15
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
                DialCustomizeActivity.this.currInstallPosition = position;
                DialCustomizeActivity.this.downDialFile(position);
            }
        }).build().show();
    }

    private class MyOnClickListenerImpl implements NavigationBar.MyOnClickListener {
        private MyOnClickListenerImpl() {
        }

        @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
        public void onClick(View btn) {
            if (DialCustomizeActivity.this.isInstalling) {
                return;
            }
            if (!DialCustomizeActivity.this.isDelete) {
                DialCustomizeActivity.this.isDelete = true;
                DialCustomizeActivity.this.setRightImage(R.mipmap.dial_ok_icon);
                Iterator it2 = DialCustomizeActivity.this.datas.iterator();
                while (it2.hasNext()) {
                    ((DialResultBean.Data) it2.next()).isDelete = true;
                }
            } else {
                DialCustomizeActivity.this.isDelete = false;
                DialCustomizeActivity.this.setRightImage(R.mipmap.dial_delete_icon);
                Iterator it3 = DialCustomizeActivity.this.datas.iterator();
                while (it3.hasNext()) {
                    ((DialResultBean.Data) it3.next()).isDelete = false;
                }
            }
            DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.dial_tv_download_record) {
                DialCustomizeActivity.this.startActivity(new Intent(DialCustomizeActivity.this, (Class<?>) DialDownRecordActivity.class));
            }
        }
    }

    private class SetDialListenerImpl implements DialCustomizeAdapter.SetDialListener {
        private SetDialListenerImpl() {
        }

        @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialCustomizeAdapter.SetDialListener
        public void callback(int position) {
            if (DialCustomizeActivity.this.isSending) {
                return;
            }
            DialCustomizeActivity.this.isSending = true;
            DialCustomizeActivity.this.handler.sendEmptyMessageDelayed(0, 2000L);
            DialResultBean.Data data = (DialResultBean.Data) DialCustomizeActivity.this.datas.get(position);
            Logger.d("chong----------position==" + position + "--state==" + data.state);
            if (data.state == 1) {
                DialCustomizeActivity.this.downDialFile(position);
            } else if (data.state != 2) {
                if (data.state == 3) {
                    if (!DialCustomizeActivity.this.isInstalling) {
                        DialCustomizeActivity.this.setDial(position);
                    }
                } else if (data.state != 4 && data.state != 5) {
                    if (data.state == 6) {
                        DialCustomizeActivity.this.isInstalling = true;
                        DialCustomizeActivity.this.installDial(position);
                    } else if (data.state == 7) {
                        data.state = 0;
                    } else {
                        DialCustomizeActivity.this.downDialFile(position);
                    }
                }
            }
            DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
        }

        @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialCustomizeAdapter.SetDialListener
        public void update(int position) {
            DialResultBean.Data data = (DialResultBean.Data) DialCustomizeActivity.this.datas.get(position);
            if (data.isUpdate) {
                data.isUpdate = false;
                data.state = 0;
                DialCustomizeActivity.this.downDialFile(position);
                DialCustomizeActivity.this.adapter.setDataChanged(DialCustomizeActivity.this.datas);
            }
        }

        @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialCustomizeAdapter.SetDialListener
        public void delete(int position) {
            DialResultBean.Data data = (DialResultBean.Data) DialCustomizeActivity.this.datas.get(position);
            if (data.isCanDelete) {
                if (data.state == 3 || data.state == 4 || data.state == 5 || data.state == 6) {
                    DialCustomizeActivity.this.showMyDialog(position);
                }
            }
        }
    }

    private class OnRefreshListenerImpl implements OnRefreshListener {
        private OnRefreshListenerImpl() {
        }

        @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
        public void onRefresh(RefreshLayout refreshlayout) {
            if (DialCustomizeActivity.this.isInstalling) {
                DialCustomizeActivity.this.smartRefreshLayout.finishRefresh();
                return;
            }
            DialCustomizeActivity.this.isGettingDialInfo = false;
            DialCustomizeActivity.this.datas.clear();
            DialCustomizeActivity.this.deviceDials.clear();
            DialCustomizeActivity.this.getData();
            DialCustomizeActivity.this.handler.removeMessages(4);
            DialCustomizeActivity.this.handler.sendEmptyMessageDelayed(4, 5000L);
        }
    }
}
