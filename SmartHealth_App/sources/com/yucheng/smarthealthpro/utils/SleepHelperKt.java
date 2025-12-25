package com.yucheng.smarthealthpro.utils;

import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepStageBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepHelper.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001¨\u0006\u0005"}, d2 = {"mapToSleepStageList", "", "Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepStageBean;", "origin", "Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepDayInfo;", "app_SmartHealthRelease"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SleepHelperKt {
    public static final List<SleepStageBean> mapToSleepStageList(List<? extends SleepDayInfo> origin) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        List<? extends SleepDayInfo> list = origin;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (SleepDayInfo sleepDayInfo : list) {
            arrayList.add(new SleepStageBean(sleepDayInfo.time, sleepDayInfo.type, sleepDayInfo.duration));
        }
        ArrayList arrayList2 = arrayList;
        List<SleepStageBean> mutableList = CollectionsKt.toMutableList((Collection) arrayList2);
        int i2 = 0;
        int i3 = 0;
        for (Object obj : arrayList2) {
            int i4 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            SleepStageBean sleepStageBean = (SleepStageBean) obj;
            long j2 = 1000;
            long time = sleepStageBean.getTime() + (sleepStageBean.getDuration() * 1000) + j2;
            if (i2 != arrayList2.size() - 1 && time < ((SleepStageBean) arrayList2.get(i4)).getTime()) {
                if (sleepStageBean.getMode() == 4) {
                    sleepStageBean.setDuration(sleepStageBean.getDuration() + ((int) (((((SleepStageBean) arrayList2.get(i4)).getTime() - time) - j2) / j2)));
                } else {
                    i3++;
                    mutableList.add(i3, new SleepStageBean(time, 4, (int) (((((SleepStageBean) arrayList2.get(i4)).getTime() - time) - j2) / j2)));
                }
            }
            i3++;
            i2 = i4;
        }
        if (!origin.isEmpty()) {
            mutableList.add(i3, new SleepStageBean(((SleepStageBean) CollectionsKt.last((List) arrayList2)).getTime() + (r0.getDuration() * 1000) + 1000, 4, 1));
        }
        return mutableList;
    }
}
