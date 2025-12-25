package com.yucheng.smarthealthpro.home.util;

import com.yucheng.smarthealthpro.database.room.bean.BloodKetones;
import com.yucheng.smarthealthpro.database.room.bean.BloodLipids;
import com.yucheng.smarthealthpro.database.room.bean.BloodPressure;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.database.room.bean.HeartRate;
import com.yucheng.smarthealthpro.database.room.bean.Physiotherapy;
import com.yucheng.smarthealthpro.database.room.bean.Sleep;
import com.yucheng.smarthealthpro.database.room.bean.SleepItem;
import com.yucheng.smarthealthpro.database.room.bean.UricAcid;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepSummary;
import com.yucheng.smarthealthpro.home.bean.HomeFunctionBean;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.TransUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HealthDataFilter.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u001a\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001\u001a\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\u001a\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\u001a\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\u001a\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\u001a\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\f0\u0001\u001a\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001\u001a\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\u001a\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001\u001a\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\u0006\u0010\u0013\u001a\u00020\u0014\u001a\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00012\u0006\u0010\u0013\u001a\u00020\u0014\u001a\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\f0\u00012\u0006\u0010\u0013\u001a\u00020\u0014\u001a\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00110\u00012\u0006\u0010\u0013\u001a\u00020\u0014\u001a\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00012\u0006\u0010\u0013\u001a\u00020\u0014\u001a\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00012\u0006\u0010\u0013\u001a\u00020\u0014\u001a\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00012\u0006\u0010\u0013\u001a\u00020\u0014\u001a\u0016\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0001\u001a(\u0010 \u001a\u00020!2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020&0%\u001a\u001c\u0010'\u001a\u00020!2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010(\u001a\u00020\u0014\u001a\u001e\u0010)\u001a\u0004\u0018\u00010#2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010(\u001a\u00020\u0014¨\u0006*"}, d2 = {"filterHeartRateData", "", "Lcom/yucheng/smarthealthpro/database/room/bean/HeartRate;", "data", "filterBloodPressureData", "Lcom/yucheng/smarthealthpro/database/room/bean/BloodPressure;", "filterBloodOxygenData", "Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "filterRespiratoryRateData", "filterTemperatureData", "filterBloodSugarData", "filterBloodLipidsData", "Lcom/yucheng/smarthealthpro/database/room/bean/BloodLipids;", "filterUricAcidData", "Lcom/yucheng/smarthealthpro/database/room/bean/UricAcid;", "filterHeartRateVariabilityData", "filterBloodKetonesData", "Lcom/yucheng/smarthealthpro/database/room/bean/BloodKetones;", "filterHealthMetricByDate", "dayStr", "", "filterUricAcidByDate", "filterBloodLipidsByDate", "filterBloodKetonesByDate", "filterBloodPressureByDate", "filterPhysiotherapyByDate", "Lcom/yucheng/smarthealthpro/database/room/bean/Physiotherapy;", "filterSleepByDate", "Lcom/yucheng/smarthealthpro/database/room/bean/Sleep;", "sleepTimeSummary", "Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepSummary;", "Lcom/yucheng/smarthealthpro/database/room/bean/SleepItem;", "resortFunction", "", "", "Lcom/yucheng/smarthealthpro/home/bean/HomeFunctionBean;", "orderBy", "", "", "removeExistsFunction", "key", "findFunction", "app_SmartHealthRelease"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class HealthDataFilterKt {
    public static final List<HeartRate> filterHeartRateData(List<HeartRate> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            HeartRate heartRate = (HeartRate) obj;
            if (heartRate.getHeartRate() >= TransUtils.HEART_RATE_VISIBLE_MIN && heartRate.getHeartRate() <= TransUtils.HEART_RATE_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<BloodPressure> filterBloodPressureData(List<BloodPressure> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            BloodPressure bloodPressure = (BloodPressure) obj;
            int systolicBloodPressure = bloodPressure.getSystolicBloodPressure() - bloodPressure.getDiastolicBloodPressure();
            if (bloodPressure.getSystolicBloodPressure() >= 60 && bloodPressure.getSystolicBloodPressure() <= 250 && bloodPressure.getDiastolicBloodPressure() >= 30 && bloodPressure.getDiastolicBloodPressure() <= 160 && systolicBloodPressure >= 10 && systolicBloodPressure <= 90) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<HealthMetric> filterBloodOxygenData(List<HealthMetric> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            HealthMetric healthMetric = (HealthMetric) obj;
            if (healthMetric.getBloodOxygenLevel() >= TransUtils.BLOOD_OXYGEN_VISIBLE_MIN && healthMetric.getBloodOxygenLevel() <= TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<HealthMetric> filterRespiratoryRateData(List<HealthMetric> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            HealthMetric healthMetric = (HealthMetric) obj;
            if (healthMetric.getRespiratoryRate() >= TransUtils.RESPIRATORY_RATE_VISIBLE_MIN && healthMetric.getRespiratoryRate() <= TransUtils.RESPIRATORY_RATE_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<HealthMetric> filterTemperatureData(List<HealthMetric> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            HealthMetric healthMetric = (HealthMetric) obj;
            if (healthMetric.getTemperatureInteger() >= TransUtils.TEMPERATURE_VISIBLE_MIN && healthMetric.getTemperatureInteger() <= TransUtils.TEMPERATURE_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<HealthMetric> filterBloodSugarData(List<HealthMetric> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            HealthMetric healthMetric = (HealthMetric) obj;
            if (healthMetric.getBloodSugarLevel() >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && healthMetric.getBloodSugarLevel() <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<BloodLipids> filterBloodLipidsData(List<BloodLipids> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            BloodLipids bloodLipids = (BloodLipids) obj;
            if (bloodLipids.getCholesterol() >= TransUtils.BLOOD_FAT_VISIBLE_MIN && bloodLipids.getCholesterol() < TransUtils.BLOOD_FAT_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<UricAcid> filterUricAcidData(List<UricAcid> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            UricAcid uricAcid = (UricAcid) obj;
            if (uricAcid.getUricAcid() >= TransUtils.URIC_ACID_VISIBLE_MIN && uricAcid.getUricAcid() <= TransUtils.URIC_ACID_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<HealthMetric> filterHeartRateVariabilityData(List<HealthMetric> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            HealthMetric healthMetric = (HealthMetric) obj;
            if (healthMetric.getHeartRateVariability() >= TransUtils.HRV_VISIBLE_MIN && healthMetric.getHeartRateVariability() <= TransUtils.HRV_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<BloodKetones> filterBloodKetonesData(List<BloodKetones> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            BloodKetones bloodKetones = (BloodKetones) obj;
            if (bloodKetones.getBloodKetones() >= TransUtils.KETONE_VISIBLE_MIN && bloodKetones.getBloodKetones() <= TransUtils.KETONE_VISIBLE_MAX) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<HealthMetric> filterHealthMetricByDate(List<HealthMetric> data, String dayStr) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (Intrinsics.areEqual(((HealthMetric) obj).getTimeYearToDay(), dayStr)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<UricAcid> filterUricAcidByDate(List<UricAcid> data, String dayStr) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (Intrinsics.areEqual(((UricAcid) obj).getTimeYearToDay(), dayStr)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<BloodLipids> filterBloodLipidsByDate(List<BloodLipids> data, String dayStr) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (Intrinsics.areEqual(((BloodLipids) obj).getTimeYearToDay(), dayStr)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<BloodKetones> filterBloodKetonesByDate(List<BloodKetones> data, String dayStr) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (Intrinsics.areEqual(((BloodKetones) obj).getTimeYearToDay(), dayStr)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<BloodPressure> filterBloodPressureByDate(List<BloodPressure> data, String dayStr) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (Intrinsics.areEqual(((BloodPressure) obj).getTimeYearToDay(), dayStr)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<Physiotherapy> filterPhysiotherapyByDate(List<Physiotherapy> data, String dayStr) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (Intrinsics.areEqual(((Physiotherapy) obj).getTimeYearToDay(), dayStr)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<Sleep> filterSleepByDate(List<Sleep> data, String dayStr) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        MLog.INSTANCE.d("filterSleepByDate: " + data);
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (Intrinsics.areEqual(((Sleep) obj).getTimeYearToDay(), dayStr)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final SleepSummary sleepTimeSummary(List<SleepItem> list) {
        if (list == null) {
            return new SleepSummary(0, 0, 0, 0, 0, 31, null);
        }
        int duration = 0;
        int duration2 = 0;
        int duration3 = 0;
        int duration4 = 0;
        int duration5 = 0;
        for (SleepItem sleepItem : list) {
            int mode = sleepItem.getMode() & 15;
            if (mode == 1) {
                duration += sleepItem.getDuration();
            } else if (mode == 2) {
                duration2 += sleepItem.getDuration();
            } else if (mode == 3) {
                duration3 += sleepItem.getDuration();
            } else if (mode == 4) {
                duration4 += sleepItem.getDuration();
            } else if (mode == 5) {
                duration5 += sleepItem.getDuration();
            }
        }
        return new SleepSummary(duration, duration2, duration3, duration4, duration5);
    }

    public static final void resortFunction(List<HomeFunctionBean> data, final Map<String, Integer> orderBy) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(orderBy, "orderBy");
        final Function2 function2 = new Function2() { // from class: com.yucheng.smarthealthpro.home.util.HealthDataFilterKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(HealthDataFilterKt.resortFunction$lambda$18(orderBy, (HomeFunctionBean) obj, (HomeFunctionBean) obj2));
            }
        };
        CollectionsKt.sortWith(data, new Comparator() { // from class: com.yucheng.smarthealthpro.home.util.HealthDataFilterKt$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HealthDataFilterKt.resortFunction$lambda$19(function2, obj, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int resortFunction$lambda$19(Function2 function2, Object obj, Object obj2) {
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int resortFunction$lambda$18(Map map, HomeFunctionBean homeFunctionBean, HomeFunctionBean homeFunctionBean2) {
        Integer num = (Integer) map.get(homeFunctionBean.getFunction());
        int iIntValue = num != null ? num.intValue() : -1;
        Integer num2 = (Integer) map.get(homeFunctionBean2.getFunction());
        int iIntValue2 = num2 != null ? num2.intValue() : -1;
        if (iIntValue < iIntValue2) {
            return -1;
        }
        return iIntValue > iIntValue2 ? 1 : 0;
    }

    public static final void removeExistsFunction(List<HomeFunctionBean> data, final String key) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(key, "key");
        final Function1 function1 = new Function1() { // from class: com.yucheng.smarthealthpro.home.util.HealthDataFilterKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HealthDataFilterKt.removeExistsFunction$lambda$20(key, (HomeFunctionBean) obj));
            }
        };
        data.removeIf(new Predicate() { // from class: com.yucheng.smarthealthpro.home.util.HealthDataFilterKt$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return HealthDataFilterKt.removeExistsFunction$lambda$21(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeExistsFunction$lambda$21(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeExistsFunction$lambda$20(String str, HomeFunctionBean it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        return it2.getFunction().equals(str);
    }

    public static final HomeFunctionBean findFunction(List<HomeFunctionBean> data, String key) {
        Object next;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(key, "key");
        Iterator<T> it2 = data.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            if (((HomeFunctionBean) next).getFunction().equals(key)) {
                break;
            }
        }
        return (HomeFunctionBean) next;
    }
}
