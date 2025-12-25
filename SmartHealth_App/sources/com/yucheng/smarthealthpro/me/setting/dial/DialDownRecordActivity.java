package com.yucheng.smarthealthpro.me.setting.dial;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import androidx.core.app.ActivityCompat;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityDialBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter;
import com.yucheng.smarthealthpro.me.setting.dial.bean.DialResultBean;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
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

/* loaded from: classes5.dex */
public class DialDownRecordActivity extends BaseVbActivity<ActivityDialBinding> implements Observer {
    private DialAdapter adapter;
    private DialBroadcastReceiver dialBroadcastReceiver;
    private AlertDialog dialog;
    private List<String> names;
    private ProgressBar progressBar;
    private List<DialResultBean.Data> datas = new ArrayList();
    private List<String> noLoadNames = new ArrayList();
    private int currInstallPosition = -1;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            if (msg.what != 0) {
                return false;
            }
            DialDownRecordActivity.this.installDial(msg.arg1);
            return false;
        }
    });
    private boolean isGettingDialInfo = false;
    private boolean isFirst = true;
    private int maxDials = 0;
    private int currDials = 0;
    private List<DialResultBean.Data> deviceDials = new ArrayList();

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws IOException {
        super.onCreate(savedInstanceState);
        init();
        initData();
    }

    private void init() {
        SubObserver.getInstance().addObs(this);
        showBack();
        GridView gridView = (GridView) findViewById(R.id.activity_dial_gv);
        DialAdapter dialAdapter = new DialAdapter(this, this.datas);
        this.adapter = dialAdapter;
        gridView.setAdapter((ListAdapter) dialAdapter);
        this.adapter.setListener(new DialAdapter.SetDialListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.2
            @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.SetDialListener
            public void delete(int position) {
            }

            @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.SetDialListener
            public void callback(int position) {
                DialResultBean.Data data = (DialResultBean.Data) DialDownRecordActivity.this.datas.get(position);
                Logger.d("chong----------position==" + position + "--state==" + data.state);
                if (data.state == 1) {
                    DialDownRecordActivity.this.installDial(position);
                } else if (data.state != 2) {
                    if (data.state == 3) {
                        DialDownRecordActivity.this.setDial(position);
                    } else if (data.state != 4) {
                        if (data.state == 5) {
                            DialDownRecordActivity.this.pauseInstallDial(position);
                            data.state = 6;
                        } else if (data.state == 6) {
                            DialDownRecordActivity.this.installDial(position);
                            data.state = 5;
                        } else {
                            DialDownRecordActivity.this.installDial(position);
                        }
                    }
                }
                DialDownRecordActivity.this.adapter.setDataChanged(DialDownRecordActivity.this.datas);
            }

            @Override // com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.SetDialListener
            public void update(int position) {
                DialResultBean.Data data = (DialResultBean.Data) DialDownRecordActivity.this.datas.get(position);
                if (data.isUpdate) {
                    data.isUpdate = false;
                    data.state = 0;
                    DialDownRecordActivity.this.deleteDialFile(position);
                    DialDownRecordActivity.this.downDialFile(position);
                    DialDownRecordActivity.this.adapter.setDataChanged(DialDownRecordActivity.this.datas);
                }
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.3
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialDownRecordActivity.this.showMyDialog(position);
                return false;
            }
        });
        try {
            this.names = getFileName(SystemUiUtil.isExistDir("health/dial"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        findViewById(R.id.dial_tv_download_record).setVisibility(8);
        this.dialBroadcastReceiver = new DialBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("dial_down_broadcast");
        intentFilter.addAction("action.device.type");
        ActivityCompat.registerReceiver(this, this.dialBroadcastReceiver, intentFilter, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteDialFile(int position) {
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
        DownloadUtil.getInstance().download(this.datas.get(position).fileName, "health/dial", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.4
            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                DialDownRecordActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((DialResultBean.Data) DialDownRecordActivity.this.datas.get(position)).state = 1;
                        DialDownRecordActivity.this.adapter.setDataChanged(DialDownRecordActivity.this.datas);
                        Tools.showAlert3(DialDownRecordActivity.this, DialDownRecordActivity.this.getString(R.string.down_success));
                        DialDownRecordActivity.this.installDial(position);
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloading(final int progress) {
                DialDownRecordActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ((DialResultBean.Data) DialDownRecordActivity.this.datas.get(position)).state = 2;
                        ((DialResultBean.Data) DialDownRecordActivity.this.datas.get(position)).progress = progress;
                        DialDownRecordActivity.this.adapter.setDataChanged(DialDownRecordActivity.this.datas);
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
                DialDownRecordActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.4.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Tools.showAlert3(DialDownRecordActivity.this, DialDownRecordActivity.this.getString(R.string.down_failed));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installDial(int position) {
        if (YCBTClient.connectState() != 10) {
            Tools.showAlert3(this, getString(R.string.please_connect_the_device));
            return;
        }
        int i2 = this.currInstallPosition;
        if (i2 >= 0 && i2 < this.datas.size() && this.datas.get(this.currInstallPosition).state == 5) {
            pauseInstallDial(this.currInstallPosition);
            Message message = new Message();
            message.what = 0;
            message.arg1 = position;
            this.handler.sendMessageDelayed(message, 1000L);
            return;
        }
        this.currInstallPosition = position;
        int i3 = this.datas.get(position).dialplateId;
        for (DialResultBean.Data data : this.deviceDials) {
            Logger.d("vvvvvv---id==" + data.dialplateId);
            if (data.dialplateId == i3) {
                this.datas.get(position).blockNumber = data.blockNumber;
            }
        }
        Logger.d("vvvvv-----" + this.datas.get(position).dialplateId + "--" + this.datas.get(position).blockNumber);
        sendBroadcast(new Intent("com.health.communication.SENDMSG").putExtra("type", 14).putExtra("name", this.datas.get(position).fileName.substring(this.datas.get(position).fileName.lastIndexOf("/") + 1)).putExtra("id", i3).putExtra("currIndex", this.datas.get(position).blockNumber));
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
        } else {
            sendBroadcast(new Intent("com.health.communication.SENDMSG").putExtra("type", 17).putExtra("id", this.datas.get(position).dialplateId));
        }
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
        new MDAlertDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(getString(R.string.prompt)).setTitleTextColor(R.color.black_light).setContentText(getString(R.string.dial_is_delete_downloaded)).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.5
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                DialDownRecordActivity.this.deleteDialFile(position);
                dialog.dismiss();
                DialDownRecordActivity.this.datas.remove(position);
                DialDownRecordActivity.this.adapter.setDataChanged(DialDownRecordActivity.this.datas);
            }
        }).build().show();
    }

    private void initData() throws IOException {
        this.datas.clear();
        getDeviceDial();
        for (String str : this.names) {
        }
        List<DialResultBean.Data> list = this.deviceDials;
        if (list != null && list.size() > 0 && this.datas.size() > 0) {
            changeState();
        }
        this.adapter.setDataChanged(this.datas);
        List<String> list2 = this.names;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        HashMap map = new HashMap();
        map.put("code", getString(R.string.lan));
        map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TOKEN, ""));
        map.put(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, "240");
        map.put("height", "240");
        map.put(Constants.FunctionConstant.DEVICETYPE, Tools.getDeviceType(this.context));
        HttpUtils.getInstance().getMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.DIALURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    DialResultBean dialResultBean = (DialResultBean) new Gson().fromJson(result, DialResultBean.class);
                    if (dialResultBean != null) {
                        try {
                            for (DialResultBean.Data data : dialResultBean.data) {
                                if (data.imgName.contains("http://")) {
                                    data.imgName = data.imgName.replace("http://", "https://");
                                }
                                if (data.fileName.contains("http://")) {
                                    data.fileName = data.fileName.replace("http://", "https://");
                                }
                                Logger.d("chong-------id==" + data.dialplateId);
                                String strSubstring = data.fileName.substring(data.fileName.lastIndexOf("/") + 1);
                                String[] strArrSplit = strSubstring.split("_");
                                Iterator it2 = DialDownRecordActivity.this.datas.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    DialResultBean.Data data2 = (DialResultBean.Data) it2.next();
                                    String[] strArrSplit2 = data2.fileName.substring(data2.fileName.lastIndexOf("/") + 1).split("_");
                                    if (strArrSplit2[0].equals(strArrSplit[0])) {
                                        if (!strArrSplit2[1].equals(strArrSplit[1])) {
                                            data2.isUpdate = true;
                                        } else {
                                            data2.name = data.name;
                                        }
                                    }
                                }
                                Iterator it3 = DialDownRecordActivity.this.noLoadNames.iterator();
                                while (true) {
                                    if (it3.hasNext()) {
                                        if (((String) it3.next()).equals(strSubstring)) {
                                            DialDownRecordActivity.this.datas.add(data);
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                            if (DialDownRecordActivity.this.deviceDials != null && DialDownRecordActivity.this.deviceDials.size() > 0 && DialDownRecordActivity.this.datas.size() > 0) {
                                DialDownRecordActivity.this.changeState();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    DialDownRecordActivity.this.adapter.setDataChanged(DialDownRecordActivity.this.datas);
                }
            }
        });
    }

    private class DialBroadcastReceiver extends BroadcastReceiver {
        private DialBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("dial_down_broadcast")) {
                int intExtra = intent.getIntExtra("type", 1);
                int intExtra2 = intent.getIntExtra("data", 0);
                if (intExtra == 1) {
                    if (intExtra2 == 0) {
                        ((DialResultBean.Data) DialDownRecordActivity.this.datas.get(DialDownRecordActivity.this.currInstallPosition)).state = 5;
                    } else if (intExtra2 == 2) {
                        DialDownRecordActivity dialDownRecordActivity = DialDownRecordActivity.this;
                        Tools.showAlert3(dialDownRecordActivity, dialDownRecordActivity.getString(R.string.dial_is_install_max));
                    } else {
                        DialDownRecordActivity dialDownRecordActivity2 = DialDownRecordActivity.this;
                        Tools.showAlert3(dialDownRecordActivity2, dialDownRecordActivity2.getString(R.string.dial_is_install_failed));
                    }
                } else if (intExtra == 0) {
                    if (intExtra2 == 0) {
                        DialDownRecordActivity dialDownRecordActivity3 = DialDownRecordActivity.this;
                        Tools.showAlert3(dialDownRecordActivity3, dialDownRecordActivity3.getString(R.string.dial_is_install_done));
                    } else {
                        DialDownRecordActivity dialDownRecordActivity4 = DialDownRecordActivity.this;
                        Tools.showAlert3(dialDownRecordActivity4, dialDownRecordActivity4.getString(R.string.dial_is_install_failed));
                    }
                } else if (intExtra != 2) {
                    if (intExtra == 3) {
                        Logger.d("chong-------progress==" + intent.getIntExtra("progress", 0) + "--curr==" + DialDownRecordActivity.this.currInstallPosition);
                        if (DialDownRecordActivity.this.currInstallPosition >= 0 && DialDownRecordActivity.this.currInstallPosition < DialDownRecordActivity.this.datas.size()) {
                            ((DialResultBean.Data) DialDownRecordActivity.this.datas.get(DialDownRecordActivity.this.currInstallPosition)).state = 5;
                            ((DialResultBean.Data) DialDownRecordActivity.this.datas.get(DialDownRecordActivity.this.currInstallPosition)).progress = intent.getIntExtra("progress", 0);
                        }
                    } else if (intExtra == 4) {
                        if (intExtra2 == 0) {
                            DialDownRecordActivity dialDownRecordActivity5 = DialDownRecordActivity.this;
                            Tools.showAlert3(dialDownRecordActivity5, dialDownRecordActivity5.getString(R.string.dial_delete_success));
                        } else {
                            DialDownRecordActivity dialDownRecordActivity6 = DialDownRecordActivity.this;
                            Tools.showAlert3(dialDownRecordActivity6, dialDownRecordActivity6.getString(R.string.dial_delete_failed));
                        }
                    }
                }
            }
            DialDownRecordActivity.this.adapter.setDataChanged(DialDownRecordActivity.this.datas);
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SubObserver.getInstance().delObs(this);
        DialBroadcastReceiver dialBroadcastReceiver = this.dialBroadcastReceiver;
        if (dialBroadcastReceiver != null) {
            unregisterReceiver(dialBroadcastReceiver);
        }
    }

    private void getDeviceDial() {
        if (YCBTClient.connectState() == 10 || this.isGettingDialInfo) {
            return;
        }
        this.isGettingDialInfo = true;
        YCBTClient.watchDialInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.7
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) throws IOException {
                if (i2 != 0 || hashMap == null) {
                    return;
                }
                DialDownRecordActivity.this.closeSportData();
                DialDownRecordActivity.this.handler.removeMessages(3);
                DialDownRecordActivity.this.maxDials = ((Integer) hashMap.get("maxDials")).intValue();
                DialDownRecordActivity.this.currDials = ((Integer) hashMap.get("currDials")).intValue();
                List<DialsBean> list = (List) hashMap.get("dials");
                DialDownRecordActivity.this.deviceDials.clear();
                for (DialsBean dialsBean : list) {
                    DialResultBean.Data data = new DialResultBean().getData();
                    data.dialplateId = dialsBean.dialplateId;
                    data.blockNumber = dialsBean.blockNumber;
                    data.isCanDelete = dialsBean.isCanDelete;
                    data.dialVersion = dialsBean.dialVersion + "";
                    DialDownRecordActivity.this.deviceDials.add(data);
                }
                if (DialDownRecordActivity.this.deviceDials != null && DialDownRecordActivity.this.deviceDials.size() > 0 && DialDownRecordActivity.this.datas.size() > 0) {
                    DialDownRecordActivity.this.changeState();
                }
                if (DialDownRecordActivity.this.currDials == 0) {
                    DialDownRecordActivity.this.setRightImage(R.mipmap.dial_delete_icon);
                    DialDownRecordActivity.this.closeRightImage();
                    Iterator it2 = DialDownRecordActivity.this.datas.iterator();
                    while (it2.hasNext()) {
                        ((DialResultBean.Data) it2.next()).isDelete = false;
                    }
                    return;
                }
                DialDownRecordActivity.this.showRightImage();
            }
        });
        this.handler.removeMessages(3);
        this.handler.sendEmptyMessageDelayed(3, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeSportData() {
        if (this.isFirst) {
            this.isFirst = false;
            YCBTClient.appRealSportFromDevice(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialDownRecordActivity.8
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                }
            });
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object o) throws NumberFormatException {
        byte[] bArr;
        Map map = (Map) o;
        int i2 = Integer.parseInt(map.get("key").toString());
        Logger.d("vvvvvv---key==" + i2);
        if (i2 != 1 || (bArr = (byte[]) map.get("smsg")) == null) {
            return;
        }
        byte b2 = bArr[0];
        if (((b2 & 255) != 9 || (bArr[1] & 255) != 3) && (b2 & 255) == 4 && (bArr[1] & 255) == 13) {
            int i3 = (bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24);
            for (DialResultBean.Data data : this.datas) {
                if (data.state == 4) {
                    data.state = 3;
                }
                if (data.dialplateId == i3) {
                    data.state = 4;
                }
            }
        }
        this.adapter.setDataChanged(this.datas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeState() throws IOException {
        for (DialResultBean.Data data : this.datas) {
            Iterator<DialResultBean.Data> it2 = this.deviceDials.iterator();
            while (true) {
                if (it2.hasNext()) {
                    DialResultBean.Data next = it2.next();
                    if (next.dialplateId == data.dialplateId) {
                        data.isCanDelete = next.isCanDelete;
                        if (next.blockNumber == 0) {
                            data.state = 3;
                        } else if (next.blockNumber == 65535) {
                            data.state = 4;
                        } else {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(new File(SystemUiUtil.isExistDir("health/dial") + "/" + data.fileName.substring(data.fileName.lastIndexOf("/") + 1)));
                                byte[] bArr = new byte[1024];
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                while (true) {
                                    int i2 = fileInputStream.read(bArr);
                                    if (i2 == -1) {
                                        break;
                                    } else {
                                        byteArrayOutputStream.write(bArr, 0, i2);
                                    }
                                }
                                byteArrayOutputStream.flush();
                                data.progress = (next.blockNumber * 409600) / byteArrayOutputStream.toByteArray().length;
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
}
