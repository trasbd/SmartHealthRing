package com.yucheng.smarthealthpro.database.room.convert;

import com.yucheng.smarthealthpro.database.room.bean.SleepItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepItemListConvert.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¨\u0006\n"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/convert/SleepItemListConvert;", "", "<init>", "()V", "fromSleepItemList", "", "value", "", "Lcom/yucheng/smarthealthpro/database/room/bean/SleepItem;", "toSleepItemList", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SleepItemListConvert {
    public final String fromSleepItemList(List<SleepItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new BaseListConvert().toJson(value, SleepItem.class);
    }

    public final List<SleepItem> toSleepItemList(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new BaseListConvert().fromJson(value, SleepItem.class);
    }
}
