package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.care.bean.PropertiesBean;
import com.yucheng.smarthealthpro.care.bean.UploadFileTypeBean;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.tasks.TimeUploadTask;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.jl.WatchManager;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.utils.LogToFileUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class UploadUtil {
    public static void upLoadBattery(Activity activity) {
    }

    public static void checkUploadLog(final Activity activity, final boolean isUploadFile) {
        if (TimeUploadTask.INSTANCE.isSportRunning()) {
            return;
        }
        HttpUtils.getInstance().getMsgAsynHttp(activity, Constants.Props, null, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    Logger.d("UploadFile upload=" + result);
                    UploadFileTypeBean uploadFileTypeBean = (UploadFileTypeBean) new Gson().fromJson(result, UploadFileTypeBean.class);
                    if (uploadFileTypeBean != null) {
                        PropertiesBean propertiesBean = (PropertiesBean) new Gson().fromJson(uploadFileTypeBean.getData().getProperties(), PropertiesBean.class);
                        if (propertiesBean == null || !propertiesBean.getLogUploadEnabled() || !propertiesBean.getLogMacAddress().contains(YCBTClient.getBindDeviceMac()) || TextUtils.isEmpty(YCBTClient.getBindDeviceMac())) {
                            MLog.INSTANCE.d("异常数据监测开关：false");
                            SharedPreferencesUtils.put(activity, Constant.SpConstKey.anomalyLogEnable, false);
                        } else {
                            MLog.INSTANCE.d("异常数据监测开关：true");
                            SharedPreferencesUtils.put(activity, Constant.SpConstKey.anomalyLogEnable, true);
                        }
                    }
                    if (uploadFileTypeBean == null || uploadFileTypeBean.getData() == null || uploadFileTypeBean.getData().getLogDeviceModel() == null) {
                        return;
                    }
                    List<String> logDeviceModel = uploadFileTypeBean.getData().getLogDeviceModel();
                    String str = (String) YCBTClient.readDeviceInfo(Constants.FunctionConstant.DEVICETYPE);
                    boolean zContains = logDeviceModel.contains(str);
                    String lastBindDeviceMac = YCBTClient.getLastBindDeviceMac();
                    if (isUploadFile && zContains && !TextUtils.isEmpty(lastBindDeviceMac)) {
                        String bindDeviceVersion = YCBTClient.getBindDeviceVersion();
                        String strReplace = lastBindDeviceMac.replace(":", "");
                        String[] strArrSplit = bindDeviceVersion.split("\\.");
                        final String str2 = "Android_" + str + "_" + strReplace + "_" + strArrSplit[0] + "@" + strArrSplit[1];
                        final String str3 = str2 + ".txt";
                        File logFile = LogToFileUtils.getLogFile("yclogs.txt");
                        File logFile2 = LogToFileUtils.getLogFile(str2 + "_yclogs.txt");
                        Logger.d("UploadFile logFileSize=" + LogToFileUtils.getFileSize(logFile));
                        YCBTClient.OpenLogSwitch = false;
                        Logger.d("UploadFile upload=" + logFile2.getAbsolutePath());
                        JxdUtils.copy(logFile, logFile2);
                        HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), com.yucheng.smarthealthpro.framework.util.Constants.UploadFile, "file", logFile2, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.1.1
                            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                            public void onSuccess(String result2) throws IOException {
                                if (!TextUtils.isEmpty(result2)) {
                                    Logger.d("upload=" + result2);
                                    LogToFileUtils.clearLog();
                                }
                                YCBTClient.OpenLogSwitch = true;
                            }
                        });
                        if (YCBTClient.connectState() == 10) {
                            if (YCBTClient.isJieLi()) {
                                WatchManager.getInstance().getLog(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.1.2
                                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                                    public void onDataResponse(int code, float ratio, HashMap resultMap) throws Throwable {
                                        if (code == 0 && resultMap != null && resultMap.containsKey("size")) {
                                            ((Integer) resultMap.get("size")).intValue();
                                            String strWriteJLLog = LogToFileUtils.writeJLLog(str2, (byte[]) resultMap.get("data"));
                                            File file = new File(strWriteJLLog);
                                            Logger.d("UploadFile upload=" + strWriteJLLog);
                                            if (file.length() > 0) {
                                                HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), com.yucheng.smarthealthpro.framework.util.Constants.UploadFile, "file", file, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.1.2.1
                                                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                                                    public void onSuccess(String result2) {
                                                    }
                                                });
                                            }
                                        }
                                    }
                                });
                            }
                            final String logFilePath = ShareUtil.getLogFilePath(activity);
                            final File file = new File(logFilePath + str3);
                            YCBTClient.getDeviceLog(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.1.3
                                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                                public void onDataResponse(int code, float ratio, HashMap resultMap) throws IOException {
                                    if (code == 0) {
                                        Object obj = resultMap.get("one_data");
                                        if (obj != null) {
                                            ShareUtil.write(logFilePath, file.getName(), obj.toString(), true);
                                        }
                                        if (resultMap.containsKey("dataType") && 520 == ((Integer) resultMap.get("dataType")).intValue()) {
                                            Logger.d("UploadFile upload=" + logFilePath + str3);
                                            if (file.length() > 0) {
                                                HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), com.yucheng.smarthealthpro.framework.util.Constants.UploadFile, "file", file, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.1.3.1
                                                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                                                    public void onSuccess(String result2) {
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }, null);
                            UploadUtil.upLoadBattery(activity);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static void bindDevice(final Activity activity, boolean needAuth) {
        if (Tools.readLogin(activity)) {
            String str = (String) YCBTClient.readDeviceInfo(Constants.FunctionConstant.DEVICETYPE);
            String bindDeviceMac = YCBTClient.getBindDeviceMac();
            HashMap map = new HashMap();
            map.put("deviceModel", str);
            map.put("deviceMac", bindDeviceMac);
            map.put("needAuth", Boolean.valueOf(needAuth));
            HttpUtils.getInstance().postJsonMsgAsynHttp(activity, com.yucheng.smarthealthpro.framework.util.Constants.deviceBind, new Gson().toJson(map), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.3
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public void onSuccess(String result) {
                    try {
                        YCBTClient.appSendToken(new JSONObject(result).optJSONObject("data").optString("accessToken"), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.3.1
                            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                            public void onDataResponse(final int i2, float v, HashMap hashMap) {
                                activity.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.utils.UploadUtil.3.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                    }
                                });
                            }
                        });
                    } catch (Exception e2) {
                        Log.d("bindDevice", e2.getMessage());
                        e2.printStackTrace();
                    }
                }
            });
        }
    }
}
