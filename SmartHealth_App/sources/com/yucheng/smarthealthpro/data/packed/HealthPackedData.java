package com.yucheng.smarthealthpro.data.packed;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HealthPackedData.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006HÆ\u0003J)\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/yucheng/smarthealthpro/data/packed/HealthPackedData;", ExifInterface.GPS_DIRECTION_TRUE, "", "dayCount", "", "data", "", "<init>", "(ILjava/util/List;)V", "getDayCount", "()I", "getData", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class HealthPackedData<T> {
    private final List<T> data;
    private final int dayCount;

    public HealthPackedData() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HealthPackedData copy$default(HealthPackedData healthPackedData, int i2, List list, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = healthPackedData.dayCount;
        }
        if ((i3 & 2) != 0) {
            list = healthPackedData.data;
        }
        return healthPackedData.copy(i2, list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getDayCount() {
        return this.dayCount;
    }

    public final List<T> component2() {
        return this.data;
    }

    public final HealthPackedData<T> copy(int dayCount, List<? extends T> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new HealthPackedData<>(dayCount, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthPackedData)) {
            return false;
        }
        HealthPackedData healthPackedData = (HealthPackedData) other;
        return this.dayCount == healthPackedData.dayCount && Intrinsics.areEqual(this.data, healthPackedData.data);
    }

    public int hashCode() {
        return (Integer.hashCode(this.dayCount) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "HealthPackedData(dayCount=" + this.dayCount + ", data=" + this.data + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HealthPackedData(int i2, List<? extends T> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.dayCount = i2;
        this.data = data;
    }

    public final int getDayCount() {
        return this.dayCount;
    }

    public /* synthetic */ HealthPackedData(int i2, ArrayList arrayList, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? new ArrayList() : arrayList);
    }

    public final List<T> getData() {
        return this.data;
    }
}
