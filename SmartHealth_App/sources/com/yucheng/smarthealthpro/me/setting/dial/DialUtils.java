package com.yucheng.smarthealthpro.me.setting.dial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter;
import com.yucheng.smarthealthpro.me.setting.dial.bean.DialResultBean;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import java.util.List;

/* loaded from: classes5.dex */
public class DialUtils {

    public interface HttpCallBack {
        void callBack(DialResultBean bean);
    }

    public static void getData(Context context, HttpCallBack httpCallBack) {
    }

    public static void sqlinster(DialResultBean.Data data, Context context) {
    }

    public static void downDialFile(final List<DialResultBean.Data> datas, final int position, final Activity context, final DialAdapter adapter) {
        DownloadUtil.getInstance().download(datas.get(position).fileName, "health/dial", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialUtils.1
            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                context.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialUtils.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((DialResultBean.Data) datas.get(position)).state = 1;
                        adapter.setDataChanged(datas);
                        Tools.showAlert3(context, context.getString(R.string.down_success));
                        DialUtils.sqlinster((DialResultBean.Data) datas.get(position), context);
                        DialUtils.sendDialToDevice((DialResultBean.Data) datas.get(position), context);
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloading(final int progress) {
                context.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialUtils.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ((DialResultBean.Data) datas.get(position)).progress = progress;
                        adapter.setDataChanged(datas);
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
                context.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.dial.DialUtils.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Tools.showAlert3(context, context.getString(R.string.down_failed));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendDialToDevice(DialResultBean.Data data, Activity context) {
        context.sendBroadcast(new Intent("com.health.communication.SENDMSG").putExtra("type", 14).putExtra("name", data.fileName.substring(data.fileName.lastIndexOf("/") + 1)).putExtra("id", data.dialplateId).putExtra("currIndex", data.blockNumber));
    }
}
