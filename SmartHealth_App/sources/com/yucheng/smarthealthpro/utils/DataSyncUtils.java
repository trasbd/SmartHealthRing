package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.viewmodel.DatabaseCallback;
import com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DataSyncUtils.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\tJ2\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0018\u0010\u0018\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\tH\u0002J(\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\u0006\u0010\"\u001a\u00020\u0012J \u0010#\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\t2\u000e\u0010$\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0019H\u0002J,\u0010%\u001a\u00020\u00122\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0015\u001a\u00020\t2\u0010\u0010$\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0019H\u0002J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\tH\u0002J\u000e\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u001dJ\u0006\u0010,\u001a\u00020\u0012R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/yucheng/smarthealthpro/utils/DataSyncUtils;", "", "appContext", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "isSyncDataSuccess", "", "endDataType", "", "finishDataType", "healthViewModel", "Lcom/yucheng/smarthealthpro/viewmodel/HealthDataViewModel;", "kotlin.jvm.PlatformType", "Lcom/yucheng/smarthealthpro/viewmodel/HealthDataViewModel;", "event", "Lcom/yucheng/smarthealthpro/utils/DataSyncEvent;", "startDataSync", "", "syncData", "getWatchesData", "dataType", "watchesResponse", "code", "result", "Ljava/util/HashMap;", "changedSyncState", "isSameDaySleep", "lastStartTime", "", "lastEndTime", "startTime", "endTime", "uploadHistoryData", "uploadEcgHistoryData", "setDBListener", "resultMap", "savaSyncData", "databaseCallback", "Lcom/yucheng/smarthealthpro/viewmodel/DatabaseCallback;", "deleteWatchesHistoryData", "type", "deleteSportRecord", "startTimestamp", "forceUploadLogFile", "Companion", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DataSyncUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static DataSyncUtils obj;
    private static volatile boolean syncing;
    private Context appContext;
    private int endDataType;
    private DataSyncEvent event;
    private int finishDataType;
    private boolean isSyncDataSuccess = true;
    private HealthDataViewModel healthViewModel = (HealthDataViewModel) MyApplication.sInstance.getAppViewModel(HealthDataViewModel.class);

    private final boolean isSameDaySleep(long lastStartTime, long lastEndTime, long startTime, long endTime) {
        return false;
    }

    /* compiled from: DataSyncUtils.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/DataSyncUtils$Companion;", "", "<init>", "()V", "syncing", "", "getSyncing", "()Z", "setSyncing", "(Z)V", "obj", "Lcom/yucheng/smarthealthpro/utils/DataSyncUtils;", "getInstance", "appContext", "Landroid/content/Context;", "release", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getSyncing() {
            return DataSyncUtils.syncing;
        }

        public final void setSyncing(boolean z) {
            DataSyncUtils.syncing = z;
        }

        public final synchronized DataSyncUtils getInstance(Context appContext) {
            DataSyncUtils dataSyncUtils;
            Intrinsics.checkNotNullParameter(appContext, "appContext");
            if (DataSyncUtils.obj == null) {
                DataSyncUtils.obj = new DataSyncUtils(appContext);
            }
            dataSyncUtils = DataSyncUtils.obj;
            Intrinsics.checkNotNull(dataSyncUtils);
            return dataSyncUtils;
        }

        public final void release() {
            setSyncing(false);
            DataSyncUtils dataSyncUtils = DataSyncUtils.obj;
            if (dataSyncUtils != null) {
                dataSyncUtils.appContext = null;
            }
            DataSyncUtils.obj = null;
        }
    }

    public DataSyncUtils(Context context) {
        this.appContext = context;
    }

    public final void startDataSync(DataSyncEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.event = event;
        event.callback(SyncState.START);
        if (YCBTClient.connectState() == 10 && !YCBTClient.isForceOta()) {
            syncData();
        } else {
            event.callback(SyncState.END);
        }
    }

    private final void syncData() {
        syncing = true;
        this.isSyncDataSuccess = true;
        this.endDataType = 0;
        ArrayList arrayList = new ArrayList();
        this.finishDataType = 0;
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSTEPCOUNT)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_HistorySport));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSLEEP)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_HistorySleep));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTRATE)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_HistoryHeart));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOOD)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_HistoryBlood));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASRESPIRATORYRATE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODOXYGEN) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODSUGAR)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_HistoryAll));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASURICACIDMEASUREMENT) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASIMPRECISEBLOODFAT) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_KETONE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_GLUCOSE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_LIPIDS) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_URIC_ACID)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_HistoryComprehensiveMeasureData));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_LOCAL_SPORT_DATA)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_HistorySportMode));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PHYSIOTHERAPY)) {
            arrayList.add(3445);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRESSURE)) {
            arrayList.add(Integer.valueOf(Constants.DATATYPE.Health_History_Body_Data));
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            int iIntValue = ((Number) it2.next()).intValue();
            getWatchesData(iIntValue);
            this.endDataType = iIntValue;
        }
        DataSyncEvent dataSyncEvent = this.event;
        if (dataSyncEvent != null) {
            dataSyncEvent.callback(SyncState.END);
        }
        this.finishDataType = this.endDataType;
    }

    public final void getWatchesData(final int dataType) {
        if (dataType == 3445) {
            AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", "开始同步理疗:" + dataType + " ;endDataType=" + this.endDataType);
            Logger.d("开始同步理疗:" + dataType + " ;endDataType=" + this.endDataType, new Object[0]);
            YCBTClient.startCustomizeDataSync(2, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.DataSyncUtils$$ExternalSyntheticLambda3
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public final void onDataResponse(int i2, float f2, HashMap map) {
                    this.f$0.watchesResponse(dataType, i2, map);
                }
            });
            return;
        }
        AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", "开始同步历史数据:" + dataType + " ;endDataType=" + this.endDataType);
        Logger.d("开始同步历史数据:" + dataType + " ;endDataType=" + this.endDataType, new Object[0]);
        YCBTClient.healthHistoryData(dataType, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.DataSyncUtils$$ExternalSyntheticLambda4
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public final void onDataResponse(int i2, float f2, HashMap map) {
                this.f$0.watchesResponse(dataType, i2, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void watchesResponse(int dataType, int code, HashMap<Object, Object> result) {
        if (code == 0 && result != null) {
            setDBListener(dataType, result);
            this.endDataType = dataType;
            String str = "同步成功:" + dataType + " ;" + new Gson().toJson(result) + " endDataType=" + this.endDataType;
            Logger.d(str, new Object[0]);
            AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", str);
            return;
        }
        if (code != 0 && code != -4) {
            this.isSyncDataSuccess = false;
        }
        Logger.d("同步失败:" + dataType + " ;code=" + code + " endDataType=" + this.endDataType, new Object[0]);
        AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", "同步失败:" + dataType + " ;code=" + code + " endDataType=" + this.endDataType);
        changedSyncState(dataType);
    }

    private final void changedSyncState(int dataType) {
        if (this.endDataType == dataType) {
            syncing = false;
            if (this.isSyncDataSuccess) {
                uploadHistoryData();
                Context context = this.appContext;
                if (context != null) {
                    Object obj2 = SharedPreferencesUtils.get(context, Constant.SpConstKey.anomalyLogEnable, false);
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                    if (((Boolean) obj2).booleanValue()) {
                        HealthDataViewModel healthDataViewModel = this.healthViewModel;
                        Context context2 = this.appContext;
                        Intrinsics.checkNotNull(context2);
                        healthDataViewModel.startMonitoring(context2);
                    }
                }
                Context context3 = this.appContext;
                if (context3 != null) {
                    SharedPreferencesUtils.put(context3, Constant.KEY_SYNC_DATA, Long.valueOf(System.currentTimeMillis()));
                }
                DataSyncEvent dataSyncEvent = this.event;
                if (dataSyncEvent != null) {
                    dataSyncEvent.callback(SyncState.SUCCESS);
                }
                Logger.i("同步成功", new Object[0]);
                AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", "同步成功");
                EventBus.getDefault().post(new EventBusSyncData(false, dataType));
                return;
            }
            DataSyncEvent dataSyncEvent2 = this.event;
            if (dataSyncEvent2 != null) {
                dataSyncEvent2.callback(SyncState.FAILED);
            }
            Logger.i("同步失败", new Object[0]);
            AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", "同步失败");
            EventBus.getDefault().post(new EventBusSyncData(false, dataType));
            YCBTClient.resetQueue();
        }
    }

    private final void uploadHistoryData() {
        if (Tools.readLogin(MyApplication.getInstance())) {
            HealthDataViewModel healthDataViewModel = this.healthViewModel;
            HealthApplication myApplication = MyApplication.getInstance();
            Intrinsics.checkNotNullExpressionValue(myApplication, "getInstance(...)");
            healthDataViewModel.uploadHistoryData(myApplication);
        }
    }

    public final void uploadEcgHistoryData() {
        HealthDataViewModel healthDataViewModel = this.healthViewModel;
        HealthApplication myApplication = MyApplication.getInstance();
        Intrinsics.checkNotNullExpressionValue(myApplication, "getInstance(...)");
        healthDataViewModel.uploadEcgHistoryData(myApplication);
    }

    private final void setDBListener(final int dataType, HashMap<?, ?> resultMap) {
        savaSyncData(new DatabaseCallback() { // from class: com.yucheng.smarthealthpro.utils.DataSyncUtils$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.viewmodel.DatabaseCallback
            public final void onExecuteCallback(boolean z) {
                DataSyncUtils.setDBListener$lambda$4(dataType, this, z);
            }
        }, dataType, resultMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDBListener$lambda$4(int i2, DataSyncUtils dataSyncUtils, boolean z) {
        if (!z) {
            AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", "插入数据失败---" + i2);
            Logger.w("插入数据失败---" + i2, new Object[0]);
        } else if (i2 == 1282) {
            dataSyncUtils.deleteWatchesHistoryData(1344);
        } else if (i2 == 1284) {
            dataSyncUtils.deleteWatchesHistoryData(Constants.DATATYPE.Health_DeleteSleep);
        } else if (i2 == 1286) {
            dataSyncUtils.deleteWatchesHistoryData(Constants.DATATYPE.Health_DeleteHeart);
        } else if (i2 == 1325) {
            dataSyncUtils.deleteWatchesHistoryData(Constants.DATATYPE.Health_DeleteSportMode);
            EventBus.getDefault().post(new EventBusSyncData(dataSyncUtils.isSyncDataSuccess, Constants.DATATYPE.Health_HistorySportMode));
        } else if (i2 == 1327) {
            dataSyncUtils.deleteWatchesHistoryData(Constants.DATATYPE.Health_DeleteComprehensiveMeasureData);
        } else if (i2 == 1331) {
            dataSyncUtils.deleteWatchesHistoryData(Constants.DATATYPE.Health_DeleteBodyData);
        } else if (i2 == 3445) {
            dataSyncUtils.deleteWatchesHistoryData(3445);
        } else if (i2 == 1288) {
            dataSyncUtils.deleteWatchesHistoryData(Constants.DATATYPE.Health_DeleteBlood);
        } else if (i2 == 1289) {
            dataSyncUtils.deleteWatchesHistoryData(Constants.DATATYPE.Health_DeleteAll);
        }
        dataSyncUtils.changedSyncState(i2);
    }

    private final void savaSyncData(DatabaseCallback databaseCallback, int dataType, HashMap<?, ?> resultMap) {
        Context context;
        if (resultMap == null || databaseCallback == null || (context = this.appContext) == null) {
            return;
        }
        if (dataType == 1282) {
            HealthDataViewModel healthDataViewModel = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel.saveStepData(context, resultMap, databaseCallback);
            return;
        }
        if (dataType == 1284) {
            HealthDataViewModel healthDataViewModel2 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel2.saveSleepData(context, resultMap, databaseCallback);
            return;
        }
        if (dataType == 1286) {
            HealthDataViewModel healthDataViewModel3 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel3.saveHeartRateData(context, resultMap, databaseCallback);
            return;
        }
        if (dataType == 1325) {
            HealthDataViewModel healthDataViewModel4 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel4.savaMotionPatternData(context, resultMap, databaseCallback);
            return;
        }
        if (dataType == 1327) {
            HealthDataViewModel healthDataViewModel5 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel5.savaBloodFatUricAcidData(context, resultMap, databaseCallback);
            return;
        }
        if (dataType == 1331) {
            HealthDataViewModel healthDataViewModel6 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel6.savaBodyData(context, resultMap, databaseCallback);
            return;
        }
        if (dataType == 3445) {
            HealthDataViewModel healthDataViewModel7 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel7.savaPhysiotherapyData(context, resultMap, databaseCallback);
        } else if (dataType == 1288) {
            HealthDataViewModel healthDataViewModel8 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel8.saveBloodPressureData(context, resultMap, databaseCallback);
        } else {
            if (dataType != 1289) {
                return;
            }
            HealthDataViewModel healthDataViewModel9 = this.healthViewModel;
            Intrinsics.checkNotNull(context);
            healthDataViewModel9.saveHealthMetricData(context, resultMap, databaseCallback);
        }
    }

    private final void deleteWatchesHistoryData(final int type) {
        if (type == 3445) {
            YCBTClient.deleteCustomizeData(2, 2, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.DataSyncUtils$$ExternalSyntheticLambda1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public final void onDataResponse(int i2, float f2, HashMap map) {
                    DataSyncUtils.deleteWatchesHistoryData$lambda$5(this.f$0, type, i2, f2, map);
                }
            });
        } else {
            YCBTClient.deleteHealthHistoryData(type, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.DataSyncUtils$$ExternalSyntheticLambda2
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public final void onDataResponse(int i2, float f2, HashMap map) {
                    DataSyncUtils.deleteWatchesHistoryData$lambda$6(type, this, i2, f2, map);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteWatchesHistoryData$lambda$5(DataSyncUtils dataSyncUtils, int i2, int i3, float f2, HashMap map) {
        AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", i3 + "---删除成功---");
        Logger.w(i3 + "---删除成功---", new Object[0]);
        EventBus.getDefault().post(new EventBusSyncData(dataSyncUtils.isSyncDataSuccess, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteWatchesHistoryData$lambda$6(int i2, DataSyncUtils dataSyncUtils, int i3, float f2, HashMap map) {
        AppLogManager.getInstance().log(MqttServiceConstants.TRACE_DEBUG, "DataSync", i3 + "---删除成功---" + i2 + "---" + dataSyncUtils.finishDataType);
        Logger.w(i3 + "---删除成功---" + i2 + "---" + dataSyncUtils.finishDataType, new Object[0]);
        EventBus.getDefault().post(new EventBusSyncData(dataSyncUtils.isSyncDataSuccess, i2));
        Logger.d("删除数据:" + i2, new Object[0]);
    }

    public final void deleteSportRecord(long startTimestamp) {
        this.healthViewModel.deleteSportRecord(startTimestamp);
    }

    public final void forceUploadLogFile() {
        this.healthViewModel.forceUploadLogFile();
    }
}
