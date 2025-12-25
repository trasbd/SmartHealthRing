package com.yucheng.ycbtsdk;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson2.JSONB;
import com.facebook.internal.ServerProtocol;
import com.google.gson.Gson;
import com.jieli.bmp_convert.BmpConvert;
import com.jieli.bmp_convert.ConvertResult;
import com.jieli.bmp_convert.OnConvertListener;
import com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.response.ExternalFlashMsgResponse;
import com.jieli.jl_rcsp.task.SimpleTaskListener;
import com.jieli.jl_rcsp.task.contacts.DeviceContacts;
import com.jieli.jl_rcsp.task.contacts.ReadContactsTask;
import com.jieli.jl_rcsp.task.contacts.UpdateContactsTask;
import com.jieli.jl_rcsp.tool.DeviceStatusManager;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.bean.ContactsBean;
import com.yucheng.ycbtsdk.core.YCBTClientImpl;
import com.yucheng.ycbtsdk.core.YCSendBean;
import com.yucheng.ycbtsdk.gatt.BleHelper;
import com.yucheng.ycbtsdk.gatt.Reconnect;
import com.yucheng.ycbtsdk.jl.ALiIOTKit;
import com.yucheng.ycbtsdk.jl.WatchManager;
import com.yucheng.ycbtsdk.response.BleConnectResponse;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.response.BleDeviceToAppDataResponse;
import com.yucheng.ycbtsdk.response.BleRealDataResponse;
import com.yucheng.ycbtsdk.response.BleScanResponse;
import com.yucheng.ycbtsdk.upgrade.DfuCallBack;
import com.yucheng.ycbtsdk.upgrade.NordicDfuUpdateUtil;
import com.yucheng.ycbtsdk.utils.ByteUtil;
import com.yucheng.ycbtsdk.utils.DeviceSupportFunctionUtil;
import com.yucheng.ycbtsdk.utils.DialUtils;
import com.yucheng.ycbtsdk.utils.InnerUtils;
import com.yucheng.ycbtsdk.utils.SPUtil;
import com.yucheng.ycbtsdk.utils.TimeUtil;
import com.yucheng.ycbtsdk.utils.UpgradeFirmwareUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import com.zhihu.matisse.internal.loader.AlbumLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes5.dex */
public class YCBTClient {
    public static final int SecFrom30Year = 946684800;
    private static Context context;
    public static final int millisFromGMT = TimeZone.getDefault().getOffset(System.currentTimeMillis());
    public static boolean OpenLogSwitch = true;

    public static void aLiIOTKitStartChecked(final Context context2, final BleDataResponse bleDataResponse) {
        if (getAuthPass()) {
            getALiIOTActivationState(new BleDataResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.13
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float f2, HashMap map) {
                    if (map == null || ((Integer) map.get(ServerProtocol.DIALOG_PARAM_STATE)).intValue() != 1) {
                        ALiIOTKit.getInstance(context2).startChecked(bleDataResponse);
                        return;
                    }
                    BleDataResponse bleDataResponse2 = bleDataResponse;
                    if (bleDataResponse2 != null) {
                        bleDataResponse2.onDataResponse(0, 10.0f, null);
                    }
                }
            });
        } else {
            setAuthPass(new BleDataResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.14
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float f2, HashMap map) {
                    if (i2 == 0) {
                        YCBTClient.aLiIOTKitStartChecked(context2, bleDataResponse);
                    } else {
                        bleDataResponse.onDataResponse(i2, f2, map);
                    }
                }
            });
        }
    }

    public static void appAmbientLightMeasurementControl(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppAmbientLightMeasurementControl, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appAmbientTempHumidityMeasurementControl(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(800, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appBloodCalibration(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppBloodCalibration, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void appBloodSugarCalibration(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppBloodSugarCalibration, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void appControlTakePhoto(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppControlTakePhoto, new byte[]{(byte) (i2 & 255)}, 2, bleDataResponse);
    }

    public static void appControlWave(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlWave, 14, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void appEarlyWarning(int i2, String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        int length;
        if (i2 == 1 && str == null) {
            return;
        }
        byte[] bytes = null;
        if (str != null) {
            try {
                bytes = str.getBytes("UTF-8");
                length = bytes.length;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            length = 0;
        }
        byte[] bArr = new byte[length + 1];
        bArr[0] = (byte) i2;
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 1, length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppEarlyWarning, bArr, 2, bleDataResponse);
    }

    public static void appEcgTestEnd(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppBloodSwitch, 11, new byte[]{0}, 3, bleDataResponse);
    }

    @Deprecated
    public static void appEcgTestStart(BleDataResponse bleDataResponse, BleRealDataResponse bleRealDataResponse) {
        AITools.getInstance().init();
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppBloodSwitch, 10, new byte[]{2}, 2, bleDataResponse);
        YCBTClientImpl.getInstance().registerEcgRealDataCallBack(bleRealDataResponse);
    }

    public static void appEffectiveHeart(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppEffectiveHeart, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appEffectiveStep(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppEffectiveStep, new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255), (byte) i3}, 2, bleDataResponse);
    }

    public static void appEmoticonIndex(int i2, int i3, int i4, String str, BleDataResponse bleDataResponse) {
        byte[] bytes;
        int length;
        if (str == null || "".equals(str)) {
            bytes = null;
            length = 0;
        } else {
            if (str.length() >= 8) {
                str = getData(str, 8) + "…";
            }
            bytes = str.getBytes();
            length = bytes.length;
        }
        byte[] bArr = new byte[length + 3];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        bArr[2] = (byte) i4;
        if (length != 0) {
            System.arraycopy(bytes, 0, bArr, 3, length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppEmoticonIndex, bArr, 2, bleDataResponse);
    }

    public static void appEmotionalMeasurementEnd(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppStartMeasurement, new byte[]{0, 12}, 2, bleDataResponse);
        appControlWave(0, 0, bleDataResponse);
    }

    public static void appEmotionalMeasurementStart(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppStartMeasurement, new byte[]{1, 12}, 2, bleDataResponse);
        appControlWave(1, 0, bleDataResponse);
    }

    public static void appFindDevice(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(768, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void appHealthArg(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[14];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        bArr[2] = (byte) i4;
        bArr[3] = (byte) i5;
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppHealthArg, bArr, 2, bleDataResponse);
    }

    public static void appHealthWriteBack(int i2, String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        byte[] bytes;
        if (str != null) {
            try {
                bytes = str.getBytes("UTF-8");
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            bytes = null;
        }
        int length = bytes == null ? 0 : bytes.length;
        byte[] bArr = new byte[length + 1];
        bArr[0] = (byte) i2;
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 1, length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppHealthWriteBack, bArr, 2, bleDataResponse);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void appInsuranceNews(int r3, int r4, int r5, int r6, int r7, java.lang.String r8, com.yucheng.ycbtsdk.response.BleDataResponse r9) throws java.io.UnsupportedEncodingException {
        /*
            if (r8 == 0) goto L16
            int r0 = r8.length()     // Catch: java.lang.Exception -> L14
            if (r0 <= 0) goto L16
            java.lang.String r0 = "UTF-8"
            byte[] r8 = r8.getBytes(r0)     // Catch: java.lang.Exception -> L14
            int r0 = r8.length     // Catch: java.lang.Exception -> L14
            r1 = 18
            if (r0 <= r1) goto L17
            return
        L14:
            r3 = move-exception
            goto L5f
        L16:
            r8 = 0
        L17:
            r0 = 8
            if (r8 != 0) goto L1d
            r1 = r0
            goto L1f
        L1d:
            int r1 = r8.length     // Catch: java.lang.Exception -> L14
            int r1 = r1 + r0
        L1f:
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L14
            byte r3 = (byte) r3     // Catch: java.lang.Exception -> L14
            r2 = 0
            r1[r2] = r3     // Catch: java.lang.Exception -> L14
            byte r3 = (byte) r4     // Catch: java.lang.Exception -> L14
            r4 = 1
            r1[r4] = r3     // Catch: java.lang.Exception -> L14
            byte r3 = (byte) r5     // Catch: java.lang.Exception -> L14
            r4 = 2
            r1[r4] = r3     // Catch: java.lang.Exception -> L14
            byte r3 = (byte) r6     // Catch: java.lang.Exception -> L14
            r5 = 3
            r1[r5] = r3     // Catch: java.lang.Exception -> L14
            r3 = r7 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3     // Catch: java.lang.Exception -> L14
            r5 = 4
            r1[r5] = r3     // Catch: java.lang.Exception -> L14
            int r3 = r7 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3     // Catch: java.lang.Exception -> L14
            r5 = 5
            r1[r5] = r3     // Catch: java.lang.Exception -> L14
            int r3 = r7 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3     // Catch: java.lang.Exception -> L14
            r5 = 6
            r1[r5] = r3     // Catch: java.lang.Exception -> L14
            int r3 = r7 >> 24
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3     // Catch: java.lang.Exception -> L14
            r5 = 7
            r1[r5] = r3     // Catch: java.lang.Exception -> L14
            if (r8 == 0) goto L55
            int r3 = r8.length     // Catch: java.lang.Exception -> L14
            java.lang.System.arraycopy(r8, r2, r1, r0, r3)     // Catch: java.lang.Exception -> L14
        L55:
            com.yucheng.ycbtsdk.core.YCBTClientImpl r3 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()     // Catch: java.lang.Exception -> L14
            r5 = 801(0x321, float:1.122E-42)
            r3.sendSingleData2Device(r5, r1, r4, r9)     // Catch: java.lang.Exception -> L14
            goto L62
        L5f:
            r3.printStackTrace()
        L62:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.YCBTClient.appInsuranceNews(int, int, int, int, int, java.lang.String, com.yucheng.ycbtsdk.response.BleDataResponse):void");
    }

    public static void appLipidCalibration(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[11];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        bArr[2] = (byte) i4;
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppLipidCalibration, bArr, 2, bleDataResponse);
    }

    public static void appMobileModel(String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        if (str == null || str.length() < 1) {
            return;
        }
        try {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppMobileModel, str.getBytes("UTF-8"), 2, bleDataResponse);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void appNewPushContacts(Context context2, List<ContactsBean> list, final BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (!InnerUtils.isJieLiChipScheme(SPUtil.getChipScheme())) {
            appPushContactsSwitch(2, null);
            for (ContactsBean contactsBean : list) {
                appPushContacts(contactsBean.number, contactsBean.name, null);
            }
            appPushContactsSwitch(0, bleDataResponse);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ContactsBean contactsBean2 : list) {
            DeviceContacts deviceContacts = new DeviceContacts(contactsBean2.id, contactsBean2.name, contactsBean2.number);
            String str = contactsBean2.name;
            if (str != null) {
                contactsBean2.name = ByteUtil.getData(str, 20);
            }
            arrayList.add(deviceContacts);
        }
        UpdateContactsTask updateContactsTask = new UpdateContactsTask(WatchManager.getInstance(), context2, arrayList);
        updateContactsTask.setListener(new SimpleTaskListener() { // from class: com.yucheng.ycbtsdk.YCBTClient.4
            @Override // com.jieli.jl_rcsp.task.SimpleTaskListener, com.jieli.jl_rcsp.task.TaskListener
            public void onBegin() {
            }

            @Override // com.jieli.jl_rcsp.task.SimpleTaskListener, com.jieli.jl_rcsp.task.TaskListener
            public void onError(int i2, String str2) {
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("msg", str2);
                    bleDataResponse.onDataResponse(-1, 0.0f, map);
                }
            }

            @Override // com.jieli.jl_rcsp.task.SimpleTaskListener, com.jieli.jl_rcsp.task.TaskListener
            public void onFinish() {
                BleDataResponse bleDataResponse2 = bleDataResponse;
                if (bleDataResponse2 != null) {
                    bleDataResponse2.onDataResponse(0, 0.0f, null);
                }
            }
        });
        updateContactsTask.start();
    }

    public static void appPrayerControl(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPrayerControl, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appPushCallState(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPushCallState, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appPushContacts(String str, String str2, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                String data = ByteUtil.getData(str, 20);
                String data2 = ByteUtil.getData(str2, 26);
                byte[] bytes = data.getBytes("UTF-8");
                byte[] bytes2 = data2.getBytes("UTF-8");
                if (bytes.length <= 20 && bytes2.length <= 26) {
                    byte[] bArr = new byte[bytes.length + 3 + bytes2.length];
                    bArr[0] = 1;
                    bArr[1] = (byte) bytes.length;
                    bArr[2] = (byte) bytes2.length;
                    System.arraycopy(bytes, 0, bArr, 3, bytes.length);
                    System.arraycopy(bytes2, 0, bArr, bytes.length + 3, bytes2.length);
                    YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPushContacts, bArr, 2, bleDataResponse);
                    return;
                }
                YCBTLog.e("phoneNumber or name's length is too long");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void appPushContactsSwitch(int i2, BleDataResponse bleDataResponse) {
        try {
            byte[] bArr = new byte[49];
            bArr[0] = (byte) i2;
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPushContacts, bArr, 2, bleDataResponse);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void appPushFemalePhysiological(long j2, int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPushFemalePhysiological, new byte[]{(byte) (j2 - 946684800), (byte) ((r5 >> 8) & 255), (byte) ((r5 >> 16) & 255), (byte) ((r5 >> 24) & 255), (byte) i2, (byte) i3, 0, 0, 0, 0, 0}, 2, bleDataResponse);
    }

    public static void appPushMessage(int i2, String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        int length;
        if (i2 == 6 && str == null) {
            return;
        }
        byte[] bytes = null;
        if (str != null) {
            try {
                bytes = str.getBytes("UTF-8");
                length = bytes.length;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            length = 0;
        }
        byte[] bArr = new byte[length + 1];
        bArr[0] = (byte) i2;
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 1, length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPushMessage, bArr, 2, bleDataResponse);
    }

    public static void appPushTempAndHumidCalibration(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPushTempAndHumidCalibration, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5}, 2, bleDataResponse);
    }

    public static void appRealAllDataFromDevice(int i2, int i3, BleDataResponse bleDataResponse) {
        if (i3 > 60 || i3 < 1) {
            return;
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(268, new byte[]{1, (byte) i3}, 2, bleDataResponse);
        byte b2 = (byte) i2;
        byte b3 = (byte) (i3 * 60);
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlReal, 12, new byte[]{b2, 1, b3}, 2, bleDataResponse);
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlReal, 12, new byte[]{b2, 2, b3}, 2, bleDataResponse);
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlReal, 12, new byte[]{b2, 3, b3}, 2, bleDataResponse);
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlReal, 12, new byte[]{b2, 4, b3}, 2, bleDataResponse);
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlReal, 12, new byte[]{b2, 5, b3}, 2, bleDataResponse);
    }

    public static void appRealDataFromDevice(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlReal, 12, new byte[]{(byte) i2, (byte) i3, 2}, 2, bleDataResponse);
    }

    public static void appRealDataReport(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppControlReal, 12, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void appRealSportFromDevice(int i2, BleDataResponse bleDataResponse) {
        appRealDataFromDevice(i2, 0, bleDataResponse);
    }

    public static void appRegisterRealDataCallBack(BleRealDataResponse bleRealDataResponse) {
        YCBTClientImpl.getInstance().registerRealDataCallBack(bleRealDataResponse);
    }

    public static void appRunMode(int i2, int i3, BleDataResponse bleDataResponse) {
        int i4;
        int i5;
        byte[] bArr = {(byte) i2, (byte) i3};
        if (i2 == 1) {
            i5 = 8;
        } else {
            if (i2 != 0) {
                i4 = 1;
                YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppRunMode, i4, bArr, 2, bleDataResponse);
            }
            i5 = 9;
        }
        i4 = i5;
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppRunMode, i4, bArr, 2, bleDataResponse);
    }

    public static void appRunModeEnd(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppRunMode, 9, new byte[]{0, (byte) i2}, 2, bleDataResponse);
    }

    public static void appRunModeStart(int i2, BleDataResponse bleDataResponse, BleRealDataResponse bleRealDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppRunMode, 8, new byte[]{1, (byte) i2}, 2, bleDataResponse);
        YCBTClientImpl.getInstance().registerRealDataCallBack(bleRealDataResponse);
    }

    public static void appSendCardNumber(int i2, String str, BleDataResponse bleDataResponse) {
        if (str == null || str.length() < 1) {
            if (bleDataResponse != null) {
                bleDataResponse.onDataResponse(-1, -1.0f, null);
                return;
            }
            return;
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        byte[] bArr = new byte[length + 2];
        bArr[0] = (byte) i2;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        bArr[length + 1] = 0;
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendCardNumber, bArr, 2, bleDataResponse);
    }

    public static void appSendLocationNumber(int i2, String str, BleDataResponse bleDataResponse) {
        if (str == null || str.length() < 1) {
            if (bleDataResponse != null) {
                bleDataResponse.onDataResponse(-1, -1.0f, null);
                return;
            }
            return;
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        byte[] bArr = new byte[length + 2];
        bArr[0] = (byte) i2;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        bArr[length + 1] = 0;
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendLocationNumber, bArr, 2, bleDataResponse);
    }

    public static void appSendMeasureNumber(int i2, long j2, int i3, int i4, BleDataResponse bleDataResponse) {
        appSendMeasureNumber(i2, j2, i3, i4, 0, 0, 0, 0, bleDataResponse);
    }

    public static void appSendPDNumber(String str, String str2, int i2, List<HashMap<String, Integer>> list, BleDataResponse bleDataResponse) {
        if (str != null && str.length() >= 1 && str2 != null && str2.length() >= 1 && list != null) {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppPDNumber, ByteUtil.pdNumberToByte(str, 32, str2, 10, list, i2), 2, bleDataResponse);
        } else if (bleDataResponse != null) {
            bleDataResponse.onDataResponse(-1, -1.0f, null);
        }
    }

    public static void appSendProductInfo(int i2, String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        if (str == null) {
            if (bleDataResponse != null) {
                bleDataResponse.onDataResponse(-1, 0.0f, null);
                return;
            }
            return;
        }
        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        byte[] bArr = new byte[bytes.length + 2];
        bArr[0] = (byte) i2;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendProductInfo, bArr, 2, bleDataResponse);
    }

    public static void appSendToken(String str, BleDataResponse bleDataResponse) {
        if (str == null || str.length() < 1) {
            if (bleDataResponse != null) {
                bleDataResponse.onDataResponse(-1, -1.0f, null);
            }
        } else {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            byte[] bArr = new byte[length + 1];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            bArr[length] = 0;
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendToken, bArr, 2, bleDataResponse);
        }
    }

    public static void appSendTokenStatus(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendTokenStatus, new byte[]{(byte) (i2 & 255)}, 2, bleDataResponse);
    }

    public static void appSendUUID(String str, BleDataResponse bleDataResponse) {
        if (str == null || !(str.length() == 32 || str.length() == 36)) {
            if (bleDataResponse != null) {
                bleDataResponse.onDataResponse(-1, 0.0f, null);
                return;
            }
            return;
        }
        if (str.length() == 36) {
            str = str.replaceAll("-", "");
        }
        byte[] bArr = new byte[16];
        for (int i2 = 0; i2 < 16; i2++) {
            try {
                bArr[i2] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                if (bleDataResponse != null) {
                    bleDataResponse.onDataResponse(-1, 0.0f, null);
                    return;
                }
                return;
            }
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendUUID, bArr, 2, bleDataResponse);
    }

    public static void appSengMessageToDevice(final int i2, final String str, final String str2, final BleDataResponse bleDataResponse) {
        if (str == null || str.length() < 1 || str2 == null || str2.length() < 1) {
            return;
        }
        getDeviceScreenInfo(new BleDataResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i3, float f2, HashMap map) {
                if (map != null) {
                    YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppMessageControl, ByteUtil.stringToByte(str, str2, ((Integer) map.get(AlbumLoader.COLUMN_COUNT)).intValue(), i2), 2, bleDataResponse);
                }
            }
        });
    }

    public static void appSensorSwitchControl(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSensorSwitchControl, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void appShutDown(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppShutDown, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appSleepWriteBack(int i2, int i3, int i4, int i5, int i6, int i7, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSleepWriteBack, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7}, 2, bleDataResponse);
    }

    public static void appStartBloodMeasurement(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppStartBloodMeasurement, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9}, 2, bleDataResponse);
    }

    public static void appStartMeasurement(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppStartMeasurement, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void appTemperatureCode(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppTemperatureCode, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appTemperatureCorrect(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppTemperatureCorrect, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void appTemperatureMeasure(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppTemperatureMeasure, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void appTodayWeather(String str, String str2, String str3, int i2, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            byte[] bytes3 = str3.getBytes("UTF-8");
            int length = bytes.length;
            int length2 = bytes2.length;
            int length3 = bytes3.length;
            byte[] bArr = new byte[length + 6 + length2 + 3 + length3 + 5];
            bArr[0] = 2;
            bArr[1] = (byte) (length3 & 255);
            bArr[2] = (byte) ((length3 >> 8) & 255);
            System.arraycopy(bytes3, 0, bArr, 3, length3);
            bArr[length3 + 3] = 0;
            int i3 = length3 + 5;
            bArr[length3 + 4] = (byte) (length & 255);
            int i4 = length3 + 6;
            bArr[i3] = (byte) ((length >> 8) & 255);
            System.arraycopy(bytes, 0, bArr, i4, length);
            int i5 = i4 + length;
            bArr[i5] = 1;
            int i6 = i5 + 2;
            bArr[i5 + 1] = (byte) (length2 & 255);
            int i7 = i5 + 3;
            bArr[i6] = (byte) ((length2 >> 8) & 255);
            System.arraycopy(bytes2, 0, bArr, i7, length2);
            int i8 = i7 + length2;
            bArr[i8] = 4;
            bArr[i8 + 1] = 2;
            bArr[i8 + 2] = 0;
            bArr[i8 + 3] = (byte) (i2 & 255);
            bArr[i8 + 4] = (byte) ((i2 >> 8) & 255);
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppTodayWeather, bArr, 2, bleDataResponse);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    public static void appTomorrowWeather(String str, String str2, String str3, int i2, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            byte[] bytes3 = str3.getBytes("UTF-8");
            int length = bytes.length;
            int length2 = bytes2.length;
            int length3 = bytes3.length;
            byte[] bArr = new byte[length + 6 + length2 + 3 + length3 + 5];
            bArr[0] = 2;
            bArr[1] = (byte) (length3 & 255);
            bArr[2] = (byte) ((length3 >> 8) & 255);
            System.arraycopy(bytes3, 0, bArr, 3, length3);
            bArr[length3 + 3] = 0;
            int i3 = length3 + 5;
            bArr[length3 + 4] = (byte) (length & 255);
            int i4 = length3 + 6;
            bArr[i3] = (byte) ((length >> 8) & 255);
            System.arraycopy(bytes, 0, bArr, i4, length);
            int i5 = i4 + length;
            bArr[i5] = 1;
            int i6 = i5 + 2;
            bArr[i5 + 1] = (byte) (length2 & 255);
            int i7 = i5 + 3;
            bArr[i6] = (byte) ((length2 >> 8) & 255);
            System.arraycopy(bytes2, 0, bArr, i7, length2);
            int i8 = i7 + length2;
            bArr[i8] = 4;
            bArr[i8 + 1] = 2;
            bArr[i8 + 2] = 0;
            bArr[i8 + 3] = (byte) (i2 & 255);
            bArr[i8 + 4] = (byte) ((i2 >> 8) & 255);
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppTomorrowWeather, bArr, 2, bleDataResponse);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    public static void appUpgradeReminder(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppUpgradeReminder, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void appUricAcidCalibration(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppUricAcidCalibration, new byte[]{(byte) i2, (byte) i3, (byte) ((i3 >> 8) & 255), 0, 0, 0, 0}, 2, bleDataResponse);
    }

    public static void appUserInfoWriteBack(int i2, String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        byte[] bytes;
        if (str != null) {
            try {
                bytes = str.getBytes("UTF-8");
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            bytes = null;
        }
        int length = bytes == null ? 0 : bytes.length;
        byte[] bArr = new byte[length + 3];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) (length & 255);
        bArr[2] = (byte) ((length >> 8) & 255);
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 1, length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppUserInfoWriteBack, bArr, 2, bleDataResponse);
    }

    public static void appWritebackData(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppWritebackData, new byte[]{(byte) i2, (byte) i3, (byte) i4, 0, 0, 0}, 2, bleDataResponse);
    }

    public static void checkALiIOTKit(BleDataResponse bleDataResponse) {
        if (getAuthPass()) {
            ALiIOTKit.getInstance(context).startChecked(bleDataResponse);
        } else {
            setAuthPass(null);
        }
    }

    public static void collectDeleteEcgPpg(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Collect_DeleteTimestamp, new byte[]{0, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)}, 2, bleDataResponse);
    }

    public static void collectEcgDataWithIndex(int i2, BleDataResponse bleDataResponse) {
        AITools.getInstance().init();
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_GetWithIndex, 5, new byte[]{0, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), 1}, 1, bleDataResponse);
    }

    public static void collectEcgDataWithTimestamp(long j2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_GetWithTimestamp, 5, new byte[]{0, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 1}, 1, bleDataResponse);
    }

    public static void collectEcgList(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(1792, 4, new byte[]{0}, 1, bleDataResponse);
    }

    public static void collectHistoryDataWithIndex(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_GetWithIndex, 5, new byte[]{(byte) i2, (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), 1}, 1, bleDataResponse);
    }

    public static void collectHistoryDataWithTimestamp(int i2, long j2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_GetWithTimestamp, 5, new byte[]{(byte) i2, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 1}, 1, bleDataResponse);
    }

    public static void collectHistoryListData(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(1792, 13, new byte[]{(byte) i2}, 1, bleDataResponse);
    }

    public static void collectPpgDataWithIndex(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_GetWithIndex, 7, new byte[]{1, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), 1}, 1, bleDataResponse);
    }

    public static void collectPpgDataWithTimestamp(long j2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_GetWithTimestamp, 7, new byte[]{1, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 1}, 1, bleDataResponse);
    }

    public static void collectPpgList(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(1792, 6, new byte[]{1}, 1, bleDataResponse);
    }

    public static boolean connectBleDevice(BluetoothDevice bluetoothDevice, BleConnectResponse bleConnectResponse) {
        if (bluetoothDevice == null) {
            return false;
        }
        return YCBTClientImpl.getInstance().connectBleDevice(bluetoothDevice, bleConnectResponse);
    }

    public static int connectState() {
        return YCBTClientImpl.getInstance().connectState();
    }

    public static void createBond() {
        BleHelper.getHelper().initBond();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void dataSetting(int r4, int r5, int r6, int r7, com.yucheng.ycbtsdk.response.BleDataResponse r8) {
        /*
            r0 = 6
            byte[] r0 = new byte[r0]
            byte r1 = (byte) r4
            r2 = 0
            r0[r2] = r1
            byte r1 = (byte) r5
            r2 = 1
            r0[r2] = r1
            r1 = 3
            r3 = 2
            if (r4 == r2) goto L4d
            if (r4 == r3) goto L26
            if (r4 == r1) goto L14
            return
        L14:
            if (r5 == r2) goto L1b
            if (r5 == r3) goto L1b
            if (r5 == r1) goto L1b
            goto L66
        L1b:
            byte r4 = (byte) r6
            r0[r3] = r4
            int r4 = r6 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r0[r1] = r4
            goto L66
        L26:
            r4 = 4
            if (r5 == r2) goto L3b
            if (r5 == r3) goto L30
            if (r5 == r1) goto L3b
            if (r5 == r4) goto L3b
            goto L66
        L30:
            byte r4 = (byte) r6
            r0[r3] = r4
            int r4 = r6 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r0[r1] = r4
            goto L66
        L3b:
            byte r5 = (byte) r6
            r0[r3] = r5
            int r5 = r6 >> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r0[r1] = r5
            int r5 = r6 >> 16
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r0[r4] = r5
            goto L66
        L4d:
            switch(r5) {
                case 0: goto L63;
                case 1: goto L5c;
                case 2: goto L63;
                case 3: goto L63;
                case 4: goto L5c;
                case 5: goto L63;
                case 6: goto L51;
                case 7: goto L5c;
                case 8: goto L63;
                case 9: goto L5c;
                case 10: goto L63;
                default: goto L50;
            }
        L50:
            return
        L51:
            byte r4 = (byte) r6
            r0[r3] = r4
            int r4 = r6 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r0[r1] = r4
            goto L66
        L5c:
            byte r4 = (byte) r6
            r0[r3] = r4
            byte r4 = (byte) r7
            r0[r1] = r4
            goto L66
        L63:
            byte r4 = (byte) r6
            r0[r3] = r4
        L66:
            com.yucheng.ycbtsdk.core.YCBTClientImpl r4 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()
            r5 = 3587(0xe03, float:5.026E-42)
            r4.sendSingleData2Device(r5, r0, r3, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.YCBTClient.dataSetting(int, int, int, int, com.yucheng.ycbtsdk.response.BleDataResponse):void");
    }

    public static void deleteCustomizeData(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(3445, new byte[]{3, (byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void deleteHealthHistoryData(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(i2, 3, new byte[]{2}, 2, bleDataResponse);
    }

    public static void deleteHistoryListData(int i2, long j2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_DeleteTimestamp, 13, new byte[]{(byte) i2, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255)}, 1, bleDataResponse);
    }

    public static void deviceToApp(BleDeviceToAppDataResponse bleDeviceToAppDataResponse) {
        YCBTClientImpl.getInstance().registerRealTypeCallBack(bleDeviceToAppDataResponse);
    }

    public static void disconnectBT() {
        BleHelper.getHelper().disconnectA2dp();
    }

    public static void disconnectBle() {
        YCBTClientImpl.getInstance().disconnectBle();
    }

    public static void electricGuantityMonitoring(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        if (i2 == 1) {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.ElectricGuantityMonitoring, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) ((i4 >> 8) & 255)}, 2, bleDataResponse);
            return;
        }
        if (i2 == 2) {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.ElectricGuantityMonitoring, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
        } else if (i2 == 3) {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.ElectricGuantityMonitoring, new byte[]{(byte) i2}, 2, bleDataResponse);
        } else if (i2 == 4) {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.ElectricGuantityMonitoring, new byte[]{(byte) i2}, 2, bleDataResponse);
        }
    }

    public static void exitScanDevice() {
        stopScanBle();
        setBleConnectResponse(null);
        Reconnect.getInstance().setReconnect(true);
    }

    public static void factoryTest(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.FactoryTest, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void getALiIOTActivationState(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetALiIOTActivationState, new byte[0], 2, bleDataResponse);
    }

    public static int getAlarmCount() {
        return DeviceSupportFunctionUtil.alarmCount();
    }

    public static void getAlgorithmicLicense(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetAlgorithmicLicense, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 70}, 2, bleDataResponse);
    }

    public static void getAllRealDataFromDevice(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetAllRealDataFromDevice, new byte[0], 2, bleDataResponse);
    }

    public static boolean getAuthPass() {
        boolean zIsAuthPass = WatchManager.getInstance().isAuthPass() && (WatchManager.getInstance().isInit() || WatchManager.getInstance().isRcspInit());
        if (YCBTClientImpl.getInstance().isOta || YCBTClientImpl.getInstance().isForceOta) {
            zIsAuthPass = WatchManager.getInstance().isAuthPass();
        }
        YCBTLog.d("getAuthPass " + zIsAuthPass);
        return zIsAuthPass;
    }

    public static String getBindDeviceMac() {
        return SPUtil.getBindedDeviceMac();
    }

    public static String getBindDeviceName() {
        return SPUtil.getBindedDeviceName();
    }

    public static String getBindDeviceVersion() {
        return SPUtil.getBindDeviceVersion();
    }

    public static String getBloodSugarVersion() {
        return SPUtil.getBloodSugarVersion();
    }

    public static void getCardInfo(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetCardInfo, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void getChipScheme(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(539, new byte[0], 2, bleDataResponse);
    }

    public static BluetoothDevice getConnectedDevice() {
        return YCBTClientImpl.getInstance().getConnectedDevice();
    }

    public static void getCurrentAmbientLightIntensity(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(530, new byte[]{JSONB.Constants.BC_STR_ASCII_FIX_1, 84}, 2, bleDataResponse);
    }

    public static void getCurrentAmbientTempAndHumidity(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetCurrentAmbientTempAndHumidity, new byte[]{75, 85}, 2, bleDataResponse);
    }

    public static void getCurrentSystemWorkingMode(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(534, new byte[0], 2, bleDataResponse);
    }

    public static void getCustomizeCGM(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Customize_CGM, new byte[]{1}, 2, bleDataResponse);
    }

    private static String getData(String str, int i2) throws UnsupportedEncodingException {
        int i3;
        int i4;
        try {
            byte[] bytes = str.substring(0, i2).getBytes("UTF-8");
            boolean z = true;
            i3 = 0;
            while (z) {
                i3++;
                try {
                    if (bytes.length >= (i2 - 1) * 3 || (i4 = i2 + i3) >= str.length()) {
                        z = false;
                    } else {
                        bytes = str.substring(0, i4).getBytes("UTF-8");
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return str.substring(0, (i2 + i3) - 2);
                }
            }
        } catch (Exception e3) {
            e = e3;
            i3 = 0;
        }
        return str.substring(0, (i2 + i3) - 2);
    }

    public static int getDeviceBatteryState() {
        return SPUtil.getDeviceBatteryState();
    }

    public static int getDeviceBatteryValue() {
        return SPUtil.getDeviceBatteryValue();
    }

    public static void getDeviceContacts(Context context2, final BleDataResponse bleDataResponse) {
        final ReadContactsTask readContactsTask = new ReadContactsTask(WatchManager.getInstance(), context2);
        readContactsTask.setListener(new SimpleTaskListener() { // from class: com.yucheng.ycbtsdk.YCBTClient.5
            @Override // com.jieli.jl_rcsp.task.SimpleTaskListener, com.jieli.jl_rcsp.task.TaskListener
            public void onBegin() {
            }

            @Override // com.jieli.jl_rcsp.task.SimpleTaskListener, com.jieli.jl_rcsp.task.TaskListener
            public void onError(int i2, String str) {
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("msg", str);
                    bleDataResponse.onDataResponse(-1, 0.0f, map);
                }
            }

            @Override // com.jieli.jl_rcsp.task.SimpleTaskListener, com.jieli.jl_rcsp.task.TaskListener
            public void onFinish() {
                if (bleDataResponse != null) {
                    List<DeviceContacts> contacts = readContactsTask.getContacts();
                    ArrayList arrayList = new ArrayList();
                    for (DeviceContacts deviceContacts : contacts) {
                        arrayList.add(new ContactsBean(deviceContacts.getFileId(), deviceContacts.getName(), deviceContacts.getMobile()));
                    }
                    HashMap map = new HashMap();
                    map.put("data", arrayList);
                    bleDataResponse.onDataResponse(0, 0.0f, map);
                }
            }
        });
        readContactsTask.start();
        resetQueue();
    }

    public static void getDeviceInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(512, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 67}, 2, bleDataResponse);
    }

    public static void getDeviceLog(int i2, BleDataResponse bleDataResponse, BleRealDataResponse bleRealDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(520, new byte[]{(byte) i2}, 2, bleDataResponse);
        if (bleRealDataResponse != null) {
            YCBTClientImpl.getInstance().registerRealDataCallBack(bleRealDataResponse);
        }
    }

    public static void getDeviceName(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(515, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 80}, 2, bleDataResponse);
    }

    public static void getDeviceRemindInfo(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetDeviceRemindInfo, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void getDeviceScreenInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(523, new byte[0], 2, bleDataResponse);
    }

    public static void getDeviceSupportFunction(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(513, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 70}, 2, bleDataResponse);
    }

    public static String getDeviceType() {
        return readDeviceInfo(Constants.FunctionConstant.DEVICETYPE).toString();
    }

    public static void getDeviceUserConfig(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(519, new byte[]{67, 70}, 2, bleDataResponse);
    }

    public static void getEcgMode(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetEcgMode, new byte[0], 2, bleDataResponse);
    }

    public static void getElectrodeLocationInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(522, new byte[0], 2, bleDataResponse);
    }

    public static void getEventReminderInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(538, new byte[]{1}, 2, bleDataResponse);
    }

    public static void getFileCount(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Collect_File_Count, new byte[1], 2, bleDataResponse);
    }

    public static void getFileData(String str, int i2, BleDataResponse bleDataResponse) {
        try {
            byte[] bArr = new byte[20];
            System.arraycopy(str.getBytes("UTF-8"), 0, bArr, 0, 16);
            System.arraycopy(ByteUtil.fromInt(i2), 0, bArr, 16, 4);
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Collect_File_Content, bArr, 2, bleDataResponse);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void getFileList(int i2, int i3, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[4];
        System.arraycopy(ByteUtil.fromInt(i2), 0, bArr, 0, 2);
        System.arraycopy(ByteUtil.fromInt(i3), 0, bArr, 2, 2);
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Collect_File_List, bArr, 2, bleDataResponse);
    }

    public static BluetoothGatt getGatt() {
        return YCBTClientImpl.getInstance().getGatt();
    }

    public static void getHeavenEarthAndFiveElement(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetHeavenEarthAndFiveElement, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void getHistoryOutline(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(525, new byte[0], 2, bleDataResponse);
    }

    public static void getInsuranceRelatedInfo(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(535, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void getJLLog(BleDataResponse bleDataResponse) {
        WatchManager.getInstance().getLog(bleDataResponse);
    }

    public static int[] getJlScreenSize() {
        int[] iArr = new int[2];
        ExternalFlashMsgResponse extFlashMsg = DeviceStatusManager.getInstance().getExtFlashMsg(WatchManager.getInstance().getConnectedDevice());
        if (extFlashMsg != null) {
            iArr[0] = extFlashMsg.getScreenWidth();
            iArr[1] = extFlashMsg.getScreenHeight();
        }
        return iArr;
    }

    public static void getLaserTreatmentParams(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetLaserTreatmentParams, new byte[]{1}, 2, bleDataResponse);
    }

    public static String getLastBindDeviceMac() {
        return SPUtil.getLastBindedDeviceMac();
    }

    public static void getMeasurementFunction(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetMeasurementFunction, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 70}, 2, bleDataResponse);
    }

    public static int getMtu() {
        return BleHelper.MTU;
    }

    public static void getNowStep(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(524, new byte[0], 2, bleDataResponse);
    }

    public static void getPowerStatistics(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetPowerStatistics, new byte[0], 2, bleDataResponse);
    }

    public static int getQueueSize() {
        return YCBTClientImpl.getInstance().getQueueSize();
    }

    public static void getRealBloodOxygen(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetRealBloodOxygen, new byte[]{73, 83}, 2, bleDataResponse);
    }

    public static void getRealTemp(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(526, new byte[0], 2, bleDataResponse);
    }

    public static void getRingProductionTestHostConfig(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetRingProductionTestHostConfig, new byte[0], 2, bleDataResponse);
    }

    public static void getScheduleInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(532, new byte[]{1}, 2, bleDataResponse);
    }

    public static void getScreenInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(527, new byte[0], 2, bleDataResponse);
    }

    public static void getScreenParameters(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetScreenParameters, new byte[0], 2, bleDataResponse);
    }

    public static void getSensorSamplingInfo(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(533, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void getSleepStatus(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetSleepStatus, new byte[0], 2, bleDataResponse);
    }

    public static void getStatusOfManualMode(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(537, new byte[0], 2, bleDataResponse);
    }

    public static void getTerminalConf(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetTerminalConf, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 70}, 2, bleDataResponse);
    }

    public static void getThemeInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(521, new byte[0], 2, bleDataResponse);
    }

    public static void getUploadConfigurationInfoOfReminder(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.GetUploadConfigurationInfoOfReminder, new byte[0], 2, bleDataResponse);
    }

    public static void getWitState(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Customize_Intelligent_Functions, new byte[]{2}, 2, bleDataResponse);
    }

    public static void gsensor(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Gsensor, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void healthHistoryData(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(i2, 3, new byte[0], 2, bleDataResponse);
    }

    public static void initClient(Context context2, boolean z) {
        initClient(context2, false, z);
    }

    public static boolean isBond() {
        return BleHelper.getHelper().isBond();
    }

    public static boolean isForceOta() {
        return YCBTClientImpl.getInstance().isForceOta;
    }

    public static boolean isJieLi() {
        return InnerUtils.isJieLiChipScheme(getChipScheme());
    }

    public static boolean isOta() {
        return YCBTClientImpl.getInstance().isOta;
    }

    public static boolean isScaning() {
        return BleHelper.getHelper().isBleScanning();
    }

    public static boolean isSupportFunction(String str) {
        return DeviceSupportFunctionUtil.isHasSupportFunction(str);
    }

    public static boolean isWatchManagerInit() {
        return WatchManager.getInstance().isInit();
    }

    public static void jieliSetDialText(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingSettingCustomDial, new byte[]{(byte) i2, (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), 0, 0, 0, 0}, 2, bleDataResponse);
    }

    public static void jlInstallCustomizeDial(String str, final BleDataResponse bleDataResponse) {
        WatchManager.getInstance().createWatchFile(str, true, new OnFatFileProgressListener() { // from class: com.yucheng.ycbtsdk.YCBTClient.11
            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onProgress(float f2) {
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("progress", Float.valueOf(f2));
                    map.put("dataType", Integer.valueOf(Constants.DATATYPE.WatchDialProgress));
                    bleDataResponse.onDataResponse(0, 0.0f, map);
                }
            }

            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onStart(String str2) {
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("progress", Float.valueOf(0.0f));
                    map.put("dataType", Integer.valueOf(Constants.DATATYPE.WatchDialProgress));
                    bleDataResponse.onDataResponse(0, 0.0f, map);
                }
            }

            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onStop(int i2) {
                YCBTLog.e("jlInstallCustomizeDial result=" + i2);
                if (i2 == 0) {
                    BleDataResponse bleDataResponse2 = bleDataResponse;
                    if (bleDataResponse2 != null) {
                        bleDataResponse2.onDataResponse(0, 0.0f, null);
                        return;
                    }
                    return;
                }
                BleDataResponse bleDataResponse3 = bleDataResponse;
                if (bleDataResponse3 != null) {
                    bleDataResponse3.onDataResponse(-1, 0.0f, null);
                }
            }
        });
    }

    public static void jlSaveCustomizeDialBg(String str, String str2, final BleDataResponse bleDataResponse) {
        YCBTLog.e("jl_watch_dial--inPath==" + str + "--" + str2);
        final BmpConvert bmpConvert = new BmpConvert();
        bmpConvert.bitmapConvert(WatchManager.getInstance().getDeviceInfo(WatchManager.getInstance().getConnectedDevice()).getSdkType() == 9 ? 1 : 0, str, str2, new OnConvertListener() { // from class: com.yucheng.ycbtsdk.YCBTClient.10
            @Override // com.jieli.bmp_convert.OnConvertListener
            public void onStart(String str3) {
                YCBTLog.e("jl_watch_dial_start--path==" + str3);
            }

            @Override // com.jieli.bmp_convert.OnConvertListener
            public void onStop(ConvertResult convertResult, String str3) {
            }

            @Override // com.jieli.bmp_convert.OnConvertListener
            public void onStop(boolean z, String str3) {
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("path", str3);
                    bleDataResponse.onDataResponse(z ? 0 : -1, 0.0f, map);
                }
                BmpConvert bmpConvert2 = bmpConvert;
                if (bmpConvert2 != null) {
                    bmpConvert2.release();
                }
            }
        });
    }

    public static void jlWatchDialDelete(String str, final BleDataResponse bleDataResponse) {
        YCBTLog.e("jlWatchDialDelete = " + str);
        WatchManager.getInstance().deleteWatchFile(str, new OnFatFileProgressListener() { // from class: com.yucheng.ycbtsdk.YCBTClient.8
            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onProgress(float f2) {
            }

            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onStart(String str2) {
            }

            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onStop(int i2) {
                YCBTLog.e("jlWatchDialDelete result=" + i2);
                BleDataResponse bleDataResponse2 = bleDataResponse;
                if (bleDataResponse2 != null) {
                    bleDataResponse2.onDataResponse(i2, 0.0f, null);
                }
            }
        });
    }

    public static void jlWatchDialDownload(String str, boolean z, final BleDataResponse bleDataResponse) {
        WatchManager.getInstance().createWatchFile(str, z, new OnFatFileProgressListener() { // from class: com.yucheng.ycbtsdk.YCBTClient.6
            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onProgress(float f2) {
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("progress", Float.valueOf(f2));
                    map.put("dataType", Integer.valueOf(Constants.DATATYPE.WatchDialProgress));
                    bleDataResponse.onDataResponse(0, 0.0f, map);
                }
            }

            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onStart(String str2) {
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("progress", Float.valueOf(0.0f));
                    map.put("dataType", Integer.valueOf(Constants.DATATYPE.WatchDialProgress));
                    bleDataResponse.onDataResponse(0, 0.0f, map);
                }
            }

            @Override // com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener
            public void onStop(int i2) {
                BleDataResponse bleDataResponse2 = bleDataResponse;
                if (bleDataResponse2 != null) {
                    if (i2 == 0) {
                        bleDataResponse2.onDataResponse(0, 0.0f, null);
                    } else {
                        bleDataResponse2.onDataResponse(i2, 0.0f, null);
                    }
                }
            }
        });
    }

    public static void jlWatchDialSetCurrent(String str, final BleDataResponse bleDataResponse) {
        WatchManager.getInstance().setCurrentWatchInfo(str, new OnWatchOpCallback<FatFile>() { // from class: com.yucheng.ycbtsdk.YCBTClient.9
            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback
            public void onFailed(BaseError baseError) {
                YCBTLog.e("jlWatchDialSetCurrent error.getMessage()" + baseError.getMessage() + "  error.getCode()" + baseError.getCode());
                bleDataResponse.onDataResponse(-1, 0.0f, null);
            }

            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback
            public void onSuccess(FatFile fatFile) {
                YCBTLog.e("jlWatchDialSetCurrent result" + new Gson().toJson(fatFile));
                if (bleDataResponse != null) {
                    HashMap map = new HashMap();
                    if (fatFile != null) {
                        map.put("data", fatFile.toString());
                        bleDataResponse.onDataResponse(0, 0.0f, map);
                    }
                }
            }
        });
    }

    public static void newSettingTemperatureAlarm(boolean z, int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingTemperatureAlarm, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2, (byte) i3, (byte) i4, (byte) i5}, 2, bleDataResponse);
    }

    public static void notifyDevice(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendNotifyToDevice, new byte[]{0}, 2, bleDataResponse);
    }

    public static void oneKeyBackground(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.OneKeyBackground, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void openFactory(boolean z, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.OpenFactory, new byte[]{1, 1, z ? (byte) 1 : (byte) 0}, 2, bleDataResponse);
    }

    public static void otaDownload(int i2, int i3, byte[] bArr, BleDataResponse bleDataResponse) {
        int length = bArr.length;
        int iCrc16_compute = ByteUtil.crc16_compute(bArr, bArr.length);
        YCBTClientImpl.setOtaDownloadData(bArr);
        YCBTClientImpl.getInstance().sendSingleData2Device(2560, new byte[]{(byte) i2, (byte) length, (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255), (byte) ((length >> 24) & 255), (byte) i3, (byte) iCrc16_compute, (byte) ((iCrc16_compute >> 8) & 255)}, 2, bleDataResponse);
    }

    public static void otaUIBlock(byte[] bArr, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(32258, bArr, 2, bleDataResponse);
    }

    public static void otaUIBlockCheck(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[8];
        System.arraycopy(ByteUtil.fromInt(i2), 0, bArr, 0, 4);
        System.arraycopy(ByteUtil.fromInt(i3), 0, bArr, 4, 2);
        System.arraycopy(ByteUtil.fromInt(i4), 0, bArr, 6, 2);
        YCBTClientImpl.getInstance().sendSingleData2Device(32259, bArr, 2, bleDataResponse);
    }

    public static void otaUIFileInfo(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[18];
        System.arraycopy(ByteUtil.fromInt(i2), 0, bArr, 0, 4);
        System.arraycopy(ByteUtil.fromInt(i3), 0, bArr, 4, 4);
        System.arraycopy(ByteUtil.fromInt(i4), 0, bArr, 8, 4);
        System.arraycopy(ByteUtil.fromInt(i5), 0, bArr, 12, 4);
        System.arraycopy(ByteUtil.fromInt(i6), 0, bArr, 16, 2);
        YCBTClientImpl.getInstance().sendSingleData2Device(32257, bArr, 2, bleDataResponse);
    }

    public static void otaUIGetBreakInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(32256, new byte[]{0}, 2, bleDataResponse);
    }

    public static void powerMonitoringDataUploadFormat(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(3445, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static Object readDeviceInfo(String str) {
        return DeviceSupportFunctionUtil.readDeviceInfo(str);
    }

    public static void reconnectDevice(String str, BleConnectResponse bleConnectResponse) {
        if (TextUtils.isEmpty(str)) {
            bleConnectResponse.onConnectResponse(1);
        } else {
            YCBTClientImpl.getInstance().reconnectDevice(str, bleConnectResponse);
        }
    }

    public static void registerBleStateChange(BleConnectResponse bleConnectResponse) {
        YCBTClientImpl.getInstance().registerBleStateChangeCallBack(bleConnectResponse);
    }

    public static void resetBond() {
        BleHelper.getHelper().disconnectA2dp();
    }

    public static void resetQueue() {
        YCBTClientImpl.getInstance().resetQueue();
    }

    public static void selfInspection(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(3072, new byte[]{(byte) i2, (byte) i3, (byte) ((i3 >> 8) & 255)}, 2, bleDataResponse);
    }

    public static void sendALIDataToDevice(byte[] bArr, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.ALIDATA, bArr, 2, bleDataResponse);
    }

    public static void sendAlgorithmicLicenseKey(int i2, byte[] bArr, BleDataResponse bleDataResponse) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 2];
        bArr2[0] = (byte) i2;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        bArr2[length + 1] = 0;
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendAlgorithmicLicenseKey, bArr2, 2, bleDataResponse);
    }

    public static void sendControlCmd(byte[] bArr) {
        YCBTClientImpl.getInstance().sendControlCommand(bArr);
    }

    public static void sendDomain(String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        try {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendDomain, str.getBytes("UTF-8"), 2, bleDataResponse);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    public static void sendJLDataToDevice(byte[] bArr, boolean z) {
        YCBTClientImpl.getInstance().sendSingleData2Device(z ? Constants.DATATYPE.JLOTADATA : Constants.DATATYPE.JLDATA, bArr, 2, null);
    }

    public static void sendWarningBackgroundLine(int i2, byte[] bArr, BleDataResponse bleDataResponse) {
        byte[] bArr2 = new byte[bArr.length + 1];
        bArr2[0] = (byte) i2;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendBackgroundLine, bArr2, 2, bleDataResponse);
    }

    public static void setAuthPass(BleDataResponse bleDataResponse) {
        WatchManager.getInstance().setReAuthPass(bleDataResponse);
    }

    public static void setBleConnectResponse(BleConnectResponse bleConnectResponse) {
        YCBTClientImpl.getInstance().setBleConnectResponse(bleConnectResponse);
    }

    public static void setBonding(boolean z) {
        YCBTClientImpl.getInstance().isBonding = z;
    }

    public static void setDialCustomize(Context context2, String str, String str2, int i2, String str3, int i3, int i4, int i5, int i6, boolean z, DialUtils.DialProgressListener dialProgressListener) {
        DialUtils.getInstance().setDialCustomize(context2, str, str2, i2, str3, i3, i4, i5, i6, z, dialProgressListener);
    }

    public static void setMtu(int i2) {
        BleHelper.getHelper().setMTU(i2);
    }

    public static void setOta(boolean z) {
        YCBTClientImpl.getInstance().setOta(z);
    }

    public static void setReconnect(boolean z) {
        Reconnect.getInstance().setReconnect(z);
    }

    public static void setSingleMeasurementFunction(byte[] bArr, BleDataResponse bleDataResponse) {
        byte[] bArr2 = new byte[9];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingMeasurementFunction, bArr2, 2, bleDataResponse);
    }

    public static void setTheBenchmarkDeviceValue(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[15];
        if (i2 == 0) {
            bArr[1] = (byte) (i3 & 255);
            bArr[2] = (byte) ((i3 >> 8) & 255);
        }
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        bArr[2] = (byte) i4;
        YCSendBean yCSendBean = new YCSendBean(bArr, 2, bleDataResponse);
        yCSendBean.dataType = Constants.DATATYPE.SetTheBenchmarkDeviceValue;
        yCSendBean.groupType = 1;
        yCSendBean.dataSendFinish = true;
        YCBTClientImpl.getInstance().pushQueue(yCSendBean);
    }

    public static void setTheBenchmarkDeviceValueStep(int i2, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[15];
        bArr[0] = 0;
        bArr[1] = (byte) (i2 & 255);
        bArr[2] = (byte) ((i2 >> 8) & 255);
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SetTheBenchmarkDeviceValue, bArr, 2, bleDataResponse);
    }

    public static void setWitOnOff(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.Customize_Intelligent_Functions, new byte[]{1, (byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void settingAccidentMode(boolean z, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingAccidentMode, new byte[]{z ? (byte) 1 : (byte) 0}, 2, bleDataResponse);
    }

    public static void settingAddAlarm(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(257, new byte[]{1, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6}, 2, bleDataResponse);
    }

    public static void settingAirPumpFrequency(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(309, new byte[]{(byte) i2, (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)}, 2, bleDataResponse);
    }

    public static void settingAmbientLight(boolean z, int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingAmbientLight, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2}, 2, bleDataResponse);
    }

    public static void settingAmbientTemperatureAndHumidity(boolean z, int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingAmbientTemperatureAndHumidity, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2}, 2, bleDataResponse);
    }

    public static void settingAntiLose(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(262, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingAppSystem(int i2, String str, BleDataResponse bleDataResponse) {
        byte[] bytes;
        int length;
        if (TextUtils.isEmpty(str)) {
            bytes = null;
            length = 0;
        } else {
            bytes = str.getBytes();
            length = bytes.length;
        }
        byte[] bArr = new byte[length + 1];
        bArr[0] = (byte) i2;
        if (length != 0) {
            System.arraycopy(bytes, 0, bArr, 1, length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(265, bArr, 2, bleDataResponse);
    }

    public static void settingAutomaticMeasurementTime(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(320, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void settingBloodAlarm(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(312, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6}, 2, bleDataResponse);
    }

    public static void settingBloodOxygenAlarm(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(313, new byte[]{(byte) i2, (byte) i3, 0}, 2, bleDataResponse);
    }

    public static void settingBloodOxygenModeMonitor(boolean z, int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingBloodOxygenModeMonitor, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2}, 2, bleDataResponse);
    }

    public static void settingBloodPressureMonitor(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingBloodPressureMonitor, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void settingBloodRange(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingBloodRange, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingBloodSugarAlarm(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingBloodSugarAlarm, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, 0, 0, 0, 0}, 2, bleDataResponse);
    }

    public static void settingBluetoothBroadcastInterval(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(300, new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255)}, 2, bleDataResponse);
    }

    public static void settingBluetoothTransmittingPower(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(301, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingBraceletStatusAlert(boolean z, int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingBraceletStatusAlert, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2}, 2, bleDataResponse);
    }

    public static void settingConfigInDifWorkingModes(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(305, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) ((i4 >> 8) & 255), (byte) i5, (byte) ((i5 >> 8) & 255)}, 2, bleDataResponse);
    }

    public static void settingDataCollect(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(283, new byte[]{(byte) (i2 & 255), (byte) (i3 & 255), (byte) (i4 & 255), (byte) ((i4 >> 8) & 255), (byte) (i5 & 255), (byte) ((i5 >> 8) & 255)}, 2, bleDataResponse);
    }

    public static void settingDeleteAlarm(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(257, new byte[]{2, (byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void settingDeviceMac(String str, BleDataResponse bleDataResponse) {
        if (str == null) {
            return;
        }
        if (str.contains(":")) {
            str = str.replace(":", "");
        }
        if (str.length() != 12) {
            return;
        }
        try {
            YCBTClientImpl.getInstance().sendSingleData2Device(308, new byte[]{(byte) Integer.parseInt(str.substring(10, 12), 16), (byte) Integer.parseInt(str.substring(8, 10), 16), (byte) Integer.parseInt(str.substring(6, 8), 16), (byte) Integer.parseInt(str.substring(4, 6), 16), (byte) Integer.parseInt(str.substring(2, 4), 16), (byte) Integer.parseInt(str.substring(0, 2), 16)}, 2, bleDataResponse);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void settingDeviceName(String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        try {
            YCBTClientImpl.getInstance().sendSingleData2Device(279, str.getBytes("UTF-8"), 2, bleDataResponse);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    public static void settingDisplayBrightness(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(276, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingEmergencyContacts(List<String> list, BleDataResponse bleDataResponse) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        int length = 1;
        for (int i2 = 0; i2 < list.size(); i2++) {
            byte[] bytes = list.get(i2).getBytes(StandardCharsets.UTF_8);
            length += bytes.length;
            arrayList.add(bytes);
        }
        byte[] bArr = {0};
        byte[] bytes2 = ",".getBytes(StandardCharsets.UTF_8);
        byte[] bArr2 = new byte[length + (bytes2.length * (size - 1)) + 1];
        bArr2[0] = (byte) size;
        int length2 = 1;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            byte[] bArr3 = (byte[]) arrayList.get(i3);
            System.arraycopy(bArr3, 0, bArr2, length2, bArr3.length);
            length2 += bArr3.length;
            if (i3 != arrayList.size() - 1) {
                System.arraycopy(bytes2, 0, bArr2, length2, bytes2.length);
                length2 += bytes2.length;
            }
        }
        System.arraycopy(bArr, 0, bArr2, length2, 1);
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingEmergencyContacts, bArr2, 2, bleDataResponse);
    }

    public static void settingEventReminder(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        int length;
        byte[] bytes = null;
        if (i5 != 1 || str == null) {
            length = 0;
        } else {
            try {
                bytes = str.getBytes("UTF-8");
                length = bytes.length;
            } catch (Exception e2) {
                e2.printStackTrace();
                length = 0;
            }
        }
        if (length > 12) {
            return;
        }
        byte[] bArr = new byte[length + 8];
        byte b2 = (byte) i2;
        bArr[0] = b2;
        bArr[1] = (byte) i3;
        bArr[2] = (byte) i4;
        bArr[3] = (byte) i5;
        bArr[4] = (byte) i6;
        bArr[5] = (byte) i7;
        bArr[6] = (byte) i8;
        bArr[7] = (byte) i9;
        bArr[0] = b2;
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 8, length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(303, bArr, 2, bleDataResponse);
    }

    public static void settingEventReminderSwitch(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(304, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingExerciseHeartRateZone(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(302, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void settingFindPhone(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(269, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingGetAllAlarm(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(257, new byte[]{0}, 2, bleDataResponse);
    }

    public static void settingGoal(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(258, new byte[]{(byte) i2, (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 24) & 255), (byte) i4, (byte) i5}, 2, bleDataResponse);
    }

    public static void settingHRVMonitor(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingHRVMonitor, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6}, 2, bleDataResponse);
    }

    public static void settingHandWear(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(264, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingHeartAlarm(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(267, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void settingHeartAlarmDuration(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(267, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6}, 2, bleDataResponse);
    }

    public static void settingHeartMonitor(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(268, new byte[]{(byte) i2, (byte) i3}, 2, bleDataResponse);
    }

    public static void settingInsuranceSwitch(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(306, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingLanguage(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(274, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingLaserTreatmentParams(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(315, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9, (byte) ((i9 >> 8) & 255), (byte) i10, (byte) i11}, 2, bleDataResponse);
    }

    public static void settingLatitudeAndLongitude(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(311, new byte[]{(byte) i2, (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255), (byte) i3, (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 24) & 255), 0, 0, 0, 0}, 2, bleDataResponse);
    }

    public static void settingLongsite(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(261, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9, (byte) i10, (byte) i11}, 2, bleDataResponse);
    }

    public static void settingLunchDoNotDisturbMode(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(307, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6}, 2, bleDataResponse);
    }

    public static void settingMainTheme(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(281, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingModfiyAlarm(int i2, int i3, int i4, int i5, int i6, int i7, int i8, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(257, new byte[]{3, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8}, 2, bleDataResponse);
    }

    public static void settingModifyDeviceName(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingModifyDeviceName, new byte[4], 2, bleDataResponse);
    }

    public static void settingNotDisturb(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(271, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6}, 2, bleDataResponse);
    }

    public static void settingNotify(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        byte[] bArr = new byte[11];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        bArr[2] = (byte) i4;
        bArr[3] = (byte) i5;
        YCBTClientImpl.getInstance().sendSingleData2Device(266, bArr, 2, bleDataResponse);
    }

    public static void settingPpgCollect(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        settingDataCollect(i2, 0, i3, i4, bleDataResponse);
    }

    public static void settingRaiseScreen(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(275, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingRegularReminder(int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        int length;
        byte[] bytes = null;
        if (str != null) {
            try {
                bytes = str.getBytes("UTF-8");
                length = bytes.length + 7;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            length = 7;
        }
        byte[] bArr = new byte[length];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        bArr[2] = (byte) i4;
        bArr[3] = (byte) i5;
        bArr[4] = (byte) i6;
        bArr[5] = (byte) i7;
        bArr[6] = (byte) i8;
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 7, bytes.length);
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(317, bArr, 2, bleDataResponse);
    }

    public static void settingRespiratoryRateAlarm(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(318, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void settingRestoreFactory(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(270, new byte[]{82, 83, 89, 83}, 2, bleDataResponse);
    }

    public static void settingRingProductionTestHost(int i2, int i3, String str, String str2, int i4, int i5, int i6, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        byte[] bArr = new byte[29];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        bArr[26] = (byte) (i4 & 255);
        bArr[27] = (byte) (i5 & 255);
        bArr[28] = (byte) (i6 & 255);
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            System.arraycopy(bytes, 0, bArr, 2, Math.min(bytes.length, 12));
            System.arraycopy(bytes2, 0, bArr, 14, Math.min(bytes2.length, 12));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingRingProductionTestHost, bArr, 2, bleDataResponse);
    }

    public static void settingRingSizeAndColor(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingRingSizeAndColor, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void settingScheduleModification(int r6, int r7, int r8, int r9, int r10, java.lang.String r11, int r12, java.lang.String r13, com.yucheng.ycbtsdk.response.BleDataResponse r14) {
        /*
            if (r13 == 0) goto Lf
            int r0 = r13.length()     // Catch: java.lang.Exception -> L8f
            if (r0 <= 0) goto Lf
            java.lang.String r0 = "UTF-8"
            byte[] r13 = r13.getBytes(r0)     // Catch: java.lang.Exception -> L8f
            goto L10
        Lf:
            r13 = 0
        L10:
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> L8f
            java.lang.String r1 = "yyyy-MM-dd HH:mm:ss"
            r0.<init>(r1)     // Catch: java.lang.Exception -> L8f
            java.util.Date r11 = r0.parse(r11)     // Catch: java.lang.Exception -> L8f
            long r0 = r11.getTime()     // Catch: java.lang.Exception -> L8f
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            r2 = 946684800(0x386d4380, double:4.67724437E-315)
            long r0 = r0 - r2
            java.util.TimeZone r11 = java.util.TimeZone.getDefault()     // Catch: java.lang.Exception -> L8f
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L8f
            int r11 = r11.getOffset(r2)     // Catch: java.lang.Exception -> L8f
            int r11 = r11 / 1000
            long r2 = (long) r11     // Catch: java.lang.Exception -> L8f
            long r0 = r0 + r2
            r11 = 10
            if (r13 != 0) goto L3d
            r2 = r11
            goto L3f
        L3d:
            int r2 = r13.length     // Catch: java.lang.Exception -> L8f
            int r2 = r2 + r11
        L3f:
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L8f
            byte r6 = (byte) r6     // Catch: java.lang.Exception -> L8f
            r3 = 0
            r2[r3] = r6     // Catch: java.lang.Exception -> L8f
            byte r6 = (byte) r7     // Catch: java.lang.Exception -> L8f
            r7 = 1
            r2[r7] = r6     // Catch: java.lang.Exception -> L8f
            byte r6 = (byte) r8     // Catch: java.lang.Exception -> L8f
            r7 = 2
            r2[r7] = r6     // Catch: java.lang.Exception -> L8f
            byte r6 = (byte) r9     // Catch: java.lang.Exception -> L8f
            r8 = 3
            r2[r8] = r6     // Catch: java.lang.Exception -> L8f
            byte r6 = (byte) r10     // Catch: java.lang.Exception -> L8f
            r8 = 4
            r2[r8] = r6     // Catch: java.lang.Exception -> L8f
            r8 = 255(0xff, double:1.26E-321)
            long r4 = r0 & r8
            int r6 = (int) r4     // Catch: java.lang.Exception -> L8f
            byte r6 = (byte) r6     // Catch: java.lang.Exception -> L8f
            r10 = 5
            r2[r10] = r6     // Catch: java.lang.Exception -> L8f
            r6 = 8
            long r4 = r0 >> r6
            long r4 = r4 & r8
            int r10 = (int) r4     // Catch: java.lang.Exception -> L8f
            byte r10 = (byte) r10     // Catch: java.lang.Exception -> L8f
            r4 = 6
            r2[r4] = r10     // Catch: java.lang.Exception -> L8f
            r10 = 16
            long r4 = r0 >> r10
            long r4 = r4 & r8
            int r10 = (int) r4     // Catch: java.lang.Exception -> L8f
            byte r10 = (byte) r10     // Catch: java.lang.Exception -> L8f
            r4 = 7
            r2[r4] = r10     // Catch: java.lang.Exception -> L8f
            r10 = 24
            long r0 = r0 >> r10
            long r8 = r8 & r0
            int r8 = (int) r8     // Catch: java.lang.Exception -> L8f
            byte r8 = (byte) r8     // Catch: java.lang.Exception -> L8f
            r2[r6] = r8     // Catch: java.lang.Exception -> L8f
            byte r6 = (byte) r12     // Catch: java.lang.Exception -> L8f
            r8 = 9
            r2[r8] = r6     // Catch: java.lang.Exception -> L8f
            if (r13 == 0) goto L85
            int r6 = r13.length     // Catch: java.lang.Exception -> L8f
            java.lang.System.arraycopy(r13, r3, r2, r11, r6)     // Catch: java.lang.Exception -> L8f
        L85:
            com.yucheng.ycbtsdk.core.YCBTClientImpl r6 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()     // Catch: java.lang.Exception -> L8f
            r8 = 295(0x127, float:4.13E-43)
            r6.sendSingleData2Device(r8, r2, r7, r14)     // Catch: java.lang.Exception -> L8f
            goto L93
        L8f:
            r6 = move-exception
            r6.printStackTrace()
        L93:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.YCBTClient.settingScheduleModification(int, int, int, int, int, java.lang.String, int, java.lang.String, com.yucheng.ycbtsdk.response.BleDataResponse):void");
    }

    public static void settingScheduleSwitch(boolean z, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingScheduleSwitch, new byte[]{z ? (byte) 1 : (byte) 0}, 2, bleDataResponse);
    }

    public static void settingScreenTime(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingScreenTime, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingSelfCheck(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingSelfCheck, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingSkin(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(277, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingSleepRemind(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(282, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void settingSosSwitch(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(310, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingSportHeartAlarm(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingSportHeartAlarm, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6}, 2, bleDataResponse);
    }

    public static void settingStepCountingStateTime(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingStepCountingStateTime, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingStudentData(String str, String str2, String str3, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        if (str == null || str2 == null || str3 == null) {
            return;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            byte[] bytes3 = str3.getBytes("UTF-8");
            byte[] bArr = new byte[bytes3.length + bytes2.length + bytes.length + 3];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            System.arraycopy(bytes2, 0, bArr, bytes.length + 1, bytes2.length);
            System.arraycopy(bytes3, 0, bArr, bytes.length + bytes2.length + 2, bytes3.length);
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingStudentData, bArr, 2, bleDataResponse);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void settingTemperatureAlarm(boolean z, int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingTemperatureAlarm, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2, 0}, 2, bleDataResponse);
    }

    public static void settingTemperatureAlarmWithDuration(boolean z, int i2, int i3, int i4, int i5, int i6, int i7, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingTemperatureAlarm, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7}, 2, bleDataResponse);
    }

    public static void settingTemperatureMonitor(boolean z, int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingTemperatureMonitor, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2}, 2, bleDataResponse);
    }

    public static void settingTime(BleDataResponse bleDataResponse) {
        if (bleDataResponse == null) {
            bleDataResponse = new BleDataResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float f2, HashMap map) {
                }
            };
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(256, TimeUtil.makeBleTime(), 2, bleDataResponse);
    }

    public static void settingTimeZone(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingTimeZone, TimeUtil.makeBleTimeZone(), 2, bleDataResponse);
    }

    public static void settingUnit(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        settingUnit(i2, i3, i4, i5, 0, bleDataResponse);
    }

    public static void settingUploadReminder(boolean z, int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingUploadReminder, new byte[]{z ? (byte) 1 : (byte) 0, (byte) i2}, 2, bleDataResponse);
    }

    public static void settingUserInfo(int i2, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(259, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5}, 2, bleDataResponse);
    }

    public static void settingVibrationIntensity(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(316, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void settingVibrationTime(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(314, new byte[]{(byte) i2, (byte) i3, (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 24) & 255), 0, 0}, 2, bleDataResponse);
    }

    public static void settingWorkingMode(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SettingWorkingMode, new byte[]{(byte) i2}, 2, bleDataResponse);
    }

    public static void specificInformationPush(int i2, String str, String str2, BleDataResponse bleDataResponse) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            bleDataResponse.onDataResponse(-1, -1.0f, null);
            return;
        }
        try {
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.SpecificInformationPush, ByteUtil.byteMerger(new byte[]{(byte) i2}, ByteUtil.byteMerger(ByteUtil.getData(str, 32).getBytes("utf-8"), ByteUtil.getData(str2, 512).getBytes("utf-8"))), 2, bleDataResponse);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            bleDataResponse.onDataResponse(-1, -1.0f, null);
        }
    }

    public static void startCollection(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTLog.e("onOff=" + i2 + " type=" + i3 + " dataResponse" + bleDataResponse);
        byte[] bArr = new byte[19];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.StartCollection, bArr, 2, bleDataResponse);
    }

    public static void startCustomizeDataSync(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(3445, new byte[]{1, (byte) i2, 1}, 2, bleDataResponse);
    }

    public static void startScanBle(BleScanResponse bleScanResponse, int i2) {
        if (isScaning()) {
            YCBTLog.e("YCBTClient isScaning 搜索设备中，不能重复调用搜索");
        } else {
            YCBTLog.e("YCBTClient startScanBle");
            YCBTClientImpl.getInstance().startScanBle(bleScanResponse, i2);
        }
    }

    public static void stopScanBle() {
        if (isScaning()) {
            YCBTLog.e("YCBTClient stopScanBle");
        }
        YCBTClientImpl.getInstance().stopScanBle();
    }

    public static void unRegisterBleStateChange(BleConnectResponse bleConnectResponse) {
        YCBTClientImpl.getInstance().unregisterBleStateChangeCallBack(bleConnectResponse);
    }

    public static void updateCallAlerts(boolean z) {
        SPUtil.put(Constants.SharedKey.Call_Alerts_Str, Boolean.valueOf(z));
        if (z) {
            YCBTClientImpl.getInstance().registerReceiver();
        } else {
            YCBTClientImpl.getInstance().unregisterReceiver();
        }
    }

    public static void upgradeFirmware(final Context context2, String str, String str2, final String str3, final DfuCallBack dfuCallBack) {
        if (str2 != null && str2.toLowerCase(Locale.ROOT).contains(DfuBaseService.NOTIFICATION_CHANNEL_DFU)) {
            NordicDfuUpdateUtil.getInstance(context2).dfuInit(str, str2, str3, dfuCallBack);
            return;
        }
        if (connectState() == 10) {
            UpgradeFirmwareUtil.startUpgrade(context2, str3, dfuCallBack);
        } else if (!TextUtils.isEmpty(str)) {
            reconnectDevice(str, new BleConnectResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.12
                @Override // com.yucheng.ycbtsdk.response.BleConnectResponse
                public void onConnectResponse(int i2) {
                    if (i2 == 0) {
                        if (YCBTClient.isOta() || YCBTClient.isForceOta()) {
                            UpgradeFirmwareUtil.startUpgrade(context2, str3, dfuCallBack);
                        }
                    }
                }
            });
        } else if (dfuCallBack != null) {
            dfuCallBack.error("mac is null");
        }
    }

    public static void vibrationMotor(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.VibrationMotorControl, new byte[]{1, (byte) i2}, 2, bleDataResponse);
    }

    public static void vibrationMotorControl(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.VibrationMotorControl, new byte[]{2, (byte) i2, (byte) ((i2 >> 8) & 255), (byte) i3, (byte) ((i3 >> 8) & 255), (byte) i4, (byte) ((i4 >> 8) & 255)}, 2, bleDataResponse);
    }

    public static void watchDialDelete(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(2308, new byte[]{(byte) i2, (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)}, 2, bleDataResponse);
    }

    public static void watchDialDownload(int i2, byte[] bArr, int i3, int i4, int i5, BleDataResponse bleDataResponse) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length;
        int iCrc16_compute = ByteUtil.crc16_compute(bArr, bArr.length);
        YCBTClientImpl.setWatchDialDownloadData(bArr);
        YCBTClientImpl.getInstance().sendSingleData2Device(2304, new byte[]{(byte) i2, (byte) length, (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255), (byte) ((length >> 24) & 255), (byte) i3, (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 24) & 255), (byte) i4, (byte) ((i4 >> 8) & 255), (byte) i5, (byte) ((i5 >> 8) & 255), (byte) iCrc16_compute, (byte) ((iCrc16_compute >> 8) & 255)}, 2, bleDataResponse);
    }

    public static void watchDialInfo(BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(2307, new byte[]{0}, 3, bleDataResponse);
    }

    public static void watchDialSetCurrent(int i2, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(2309, new byte[]{(byte) i2, (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)}, 2, bleDataResponse);
    }

    public static void watchUiUpgrade(String str, final BleDataResponse bleDataResponse) throws IOException {
        final int iCrc16_compute;
        final int i2 = 0;
        if (str == null || "".equals(str)) {
            iCrc16_compute = 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(str));
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
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                int length = byteArray.length;
                iCrc16_compute = ByteUtil.crc16_compute(byteArray, byteArray.length);
                if (length < 1024) {
                    System.out.println("chong-----传入的文件不是UI升级文件");
                    bleDataResponse.onDataResponse(1, 1.0f, null);
                    return;
                } else {
                    YCBTClientImpl.setWatchDialDownloadData(byteArray);
                    i2 = length;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("chong-----开始安装UI报错");
                bleDataResponse.onDataResponse(1, 1.0f, null);
                return;
            }
        }
        otaUIGetBreakInfo(new BleDataResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.7
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i4, float f2, HashMap map) {
                if (i4 != 0 || map == null) {
                    bleDataResponse.onDataResponse(1, 1.0f, null);
                    return;
                }
                int iIntValue = ((Integer) map.get("uiFileTotalLen")).intValue();
                final int iIntValue2 = ((Integer) map.get("uiFileOffset")).intValue();
                int iIntValue3 = ((Integer) map.get("uiFileCheckSum")).intValue();
                if (iIntValue == 0 || iIntValue != i2 || iIntValue3 != iCrc16_compute) {
                    iIntValue2 = 0;
                }
                YCBTClient.getChipScheme(new BleDataResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.7.1
                    /* JADX WARN: Removed duplicated region for block: B:13:0x002a A[PHI: r7
  0x002a: PHI (r7v8 int) = (r7v5 int), (r7v12 int) binds: [B:11:0x0027, B:8:0x001c] A[DONT_GENERATE, DONT_INLINE]] */
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void onDataResponse(int r7, float r8, java.util.HashMap r9) {
                        /*
                            r6 = this;
                            if (r7 != 0) goto L11
                            if (r9 == 0) goto L11
                            java.lang.String r7 = "chipScheme"
                            java.lang.Object r7 = r9.get(r7)
                            java.lang.Integer r7 = (java.lang.Integer) r7
                            int r7 = r7.intValue()
                            goto L12
                        L11:
                            r7 = 0
                        L12:
                            if (r7 != 0) goto L1f
                            com.yucheng.ycbtsdk.YCBTClient$7 r7 = com.yucheng.ycbtsdk.YCBTClient.AnonymousClass7.this
                            int r7 = r1
                            int r8 = r7 % 1024
                            int r7 = r7 / 1024
                            if (r8 != 0) goto L2a
                            goto L2c
                        L1f:
                            com.yucheng.ycbtsdk.YCBTClient$7 r7 = com.yucheng.ycbtsdk.YCBTClient.AnonymousClass7.this
                            int r7 = r1
                            int r8 = r7 % 4096
                            int r7 = r7 / 4096
                            if (r8 != 0) goto L2a
                            goto L2c
                        L2a:
                            int r7 = r7 + 1
                        L2c:
                            r2 = r7
                            com.yucheng.ycbtsdk.YCBTClient$7 r7 = com.yucheng.ycbtsdk.YCBTClient.AnonymousClass7.this
                            int r0 = r1
                            int r3 = r2
                            int r1 = r0 - r3
                            int r4 = r2
                            com.yucheng.ycbtsdk.response.BleDataResponse r5 = r3
                            com.yucheng.ycbtsdk.YCBTClient.otaUIFileInfo(r0, r1, r2, r3, r4, r5)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.YCBTClient.AnonymousClass7.AnonymousClass1.onDataResponse(int, float, java.util.HashMap):void");
                    }
                });
            }
        });
    }

    public static void appSendMeasureNumber(int i2, long j2, int i3, int i4, int i5, int i6, int i7, int i8, BleDataResponse bleDataResponse) {
        long offset = (j2 - 946684800) + (TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000);
        YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppSendMeasureNumber, new byte[]{(byte) i2, (byte) (offset & 255), (byte) ((offset >> 8) & 255), (byte) ((offset >> 16) & 255), (byte) ((offset >> 24) & 255), (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8}, 2, bleDataResponse);
    }

    public static boolean connectBleDevice(BluetoothDevice bluetoothDevice, int i2, BleConnectResponse bleConnectResponse) {
        if (bluetoothDevice == null) {
            return false;
        }
        return YCBTClientImpl.getInstance().connectBleDevice(bluetoothDevice, i2, bleConnectResponse);
    }

    public static void initClient(Context context2, boolean z, boolean z2) {
        initClient(context2, z, 3, z2);
    }

    public static void settingUnit(int i2, int i3, int i4, int i5, int i6, BleDataResponse bleDataResponse) {
        settingUnit(i2, i3, i4, i5, i6, 0, bleDataResponse);
    }

    public static void deleteHistoryListData(int i2, int i3, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.Collect_DeleteIndex, 13, new byte[]{(byte) i2, (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), 0, 0}, 1, bleDataResponse);
    }

    public static int getChipScheme() {
        return SPUtil.getChipScheme();
    }

    public static void initClient(Context context2, boolean z, int i2, boolean z2) {
        context = context2;
        YCBTClientImpl.getInstance().init(context2, z, i2, z2);
        YCBTLog.e("YCBTClient initClient");
    }

    public static void settingUnit(int i2, int i3, int i4, int i5, int i6, int i7, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(260, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7}, 2, bleDataResponse);
    }

    public static void checkALiIOTKit(Context context2, BleDataResponse bleDataResponse) {
        if (getAuthPass()) {
            ALiIOTKit.getInstance(context2).startChecked(bleDataResponse);
        } else {
            setAuthPass(null);
        }
    }

    public static void settingBloodOxygenAlarm(int i2, int i3, int i4, BleDataResponse bleDataResponse) {
        YCBTClientImpl.getInstance().sendSingleData2Device(313, new byte[]{(byte) i2, (byte) i3, (byte) i4}, 2, bleDataResponse);
    }

    public static void startScanBle(BleScanResponse bleScanResponse, int i2, int i3) {
        BleHelper.getHelper().setProductId(i3);
        startScanBle(bleScanResponse, i2);
    }

    public static void appEcgTestStart(BleDataResponse bleDataResponse) {
        AITools.getInstance().init();
        YCBTClientImpl.getInstance().sendDataType2Device(Constants.DATATYPE.AppBloodSwitch, 10, new byte[]{2}, 2, bleDataResponse);
    }

    public static void settingTime(long j2, BleDataResponse bleDataResponse) {
        if (bleDataResponse == null) {
            bleDataResponse = new BleDataResponse() { // from class: com.yucheng.ycbtsdk.YCBTClient.2
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float f2, HashMap map) {
                }
            };
        }
        YCBTClientImpl.getInstance().sendSingleData2Device(256, TimeUtil.makeBleTime(j2), 2, bleDataResponse);
    }

    public static void startScanBle(BleScanResponse bleScanResponse, int i2, List<String> list) {
        BleHelper.getHelper().setProductIds(list);
        startScanBle(bleScanResponse, i2);
    }

    public static void appTodayWeather(String str, String str2, String str3, int i2, String str4, String str5, String str6, int i3, BleDataResponse bleDataResponse) throws UnsupportedEncodingException {
        int length;
        byte[] bytes;
        byte[] bArr;
        byte[] bytes2;
        int length2;
        byte[] bytes3;
        int length3;
        try {
            byte[] bytes4 = str.getBytes("UTF-8");
            byte[] bytes5 = str2.getBytes("UTF-8");
            byte[] bytes6 = str3.getBytes("UTF-8");
            byte[] bArr2 = {(byte) i3};
            int length4 = bytes4.length;
            int length5 = bytes5.length;
            int length6 = bytes6.length;
            int i4 = length4 + 6 + length5 + 3 + length6 + 5;
            if (str4 != null) {
                bytes = str4.getBytes("UTF-8");
                length = bytes.length;
                i4 += length + 3;
            } else {
                length = 0;
                bytes = null;
            }
            if (str5 != null) {
                bytes2 = str5.getBytes("UTF-8");
                bArr = bArr2;
                length2 = bytes2.length;
                i4 += length2 + 3;
            } else {
                bArr = bArr2;
                bytes2 = null;
                length2 = 0;
            }
            if (str6 != null) {
                bytes3 = str6.getBytes("UTF-8");
                length3 = bytes3.length;
                i4 += length3 + 3;
            } else {
                bytes3 = null;
                length3 = 0;
            }
            byte[] bArr3 = new byte[i4 + 4];
            int i5 = length3;
            bArr3[0] = 2;
            bArr3[1] = (byte) (length6 & 255);
            bArr3[2] = (byte) ((length6 >> 8) & 255);
            byte[] bArr4 = bytes3;
            System.arraycopy(bytes6, 0, bArr3, 3, length6);
            bArr3[length6 + 3] = 0;
            int i6 = length6 + 5;
            bArr3[length6 + 4] = (byte) (length4 & 255);
            int i7 = length6 + 6;
            bArr3[i6] = (byte) ((length4 >> 8) & 255);
            System.arraycopy(bytes4, 0, bArr3, i7, length4);
            int i8 = i7 + length4;
            bArr3[i8] = 1;
            int i9 = i8 + 2;
            bArr3[i8 + 1] = (byte) (length5 & 255);
            int i10 = i8 + 3;
            bArr3[i9] = (byte) ((length5 >> 8) & 255);
            System.arraycopy(bytes5, 0, bArr3, i10, length5);
            int i11 = i10 + length5;
            bArr3[i11] = 4;
            bArr3[i11 + 1] = 2;
            bArr3[i11 + 2] = 0;
            bArr3[i11 + 3] = (byte) (i2 & 255);
            int i12 = i11 + 5;
            bArr3[i11 + 4] = (byte) ((i2 >> 8) & 255);
            if (str4 != null) {
                bArr3[i12] = 6;
                int i13 = i11 + 7;
                bArr3[i11 + 6] = (byte) (length & 255);
                int i14 = i11 + 8;
                bArr3[i13] = (byte) ((length >> 8) & 255);
                System.arraycopy(bytes, 0, bArr3, i14, length);
                i12 = i14 + length;
            }
            if (str5 != null) {
                bArr3[i12] = 7;
                int i15 = i12 + 2;
                bArr3[i12 + 1] = (byte) (length2 & 255);
                int i16 = i12 + 3;
                bArr3[i15] = (byte) ((length2 >> 8) & 255);
                System.arraycopy(bytes2, 0, bArr3, i16, length2);
                i12 = i16 + length2;
            }
            if (bArr4 != null) {
                bArr3[i12] = 8;
                int i17 = i12 + 2;
                bArr3[i12 + 1] = (byte) (i5 & 255);
                int i18 = i12 + 3;
                bArr3[i17] = (byte) ((i5 >> 8) & 255);
                System.arraycopy(bArr4, 0, bArr3, i18, i5);
                i12 = i18 + i5;
            }
            bArr3[i12] = 9;
            bArr3[i12 + 1] = (byte) 1;
            bArr3[i12 + 2] = (byte) 0;
            System.arraycopy(bArr, 0, bArr3, i12 + 3, 1);
            YCBTClientImpl.getInstance().sendSingleData2Device(Constants.DATATYPE.AppTodayWeather, bArr3, 2, bleDataResponse);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }
}
