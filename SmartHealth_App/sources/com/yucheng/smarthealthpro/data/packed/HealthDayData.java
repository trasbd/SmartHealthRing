package com.yucheng.smarthealthpro.data.packed;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HealthDayData.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B+\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007HÆ\u0003J3\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/yucheng/smarthealthpro/data/packed/HealthDayData;", ExifInterface.GPS_DIRECTION_TRUE, "", "day", "", "yesterday", "data", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getDay", "()Ljava/lang/String;", "getYesterday", "getData", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class HealthDayData<T> {
    private final List<T> data;
    private final String day;
    private final String yesterday;

    public HealthDayData() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HealthDayData copy$default(HealthDayData healthDayData, String str, String str2, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = healthDayData.day;
        }
        if ((i2 & 2) != 0) {
            str2 = healthDayData.yesterday;
        }
        if ((i2 & 4) != 0) {
            list = healthDayData.data;
        }
        return healthDayData.copy(str, str2, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDay() {
        return this.day;
    }

    /* renamed from: component2, reason: from getter */
    public final String getYesterday() {
        return this.yesterday;
    }

    public final List<T> component3() {
        return this.data;
    }

    public final HealthDayData<T> copy(String day, String yesterday, List<? extends T> data) {
        Intrinsics.checkNotNullParameter(day, "day");
        Intrinsics.checkNotNullParameter(yesterday, "yesterday");
        Intrinsics.checkNotNullParameter(data, "data");
        return new HealthDayData<>(day, yesterday, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthDayData)) {
            return false;
        }
        HealthDayData healthDayData = (HealthDayData) other;
        return Intrinsics.areEqual(this.day, healthDayData.day) && Intrinsics.areEqual(this.yesterday, healthDayData.yesterday) && Intrinsics.areEqual(this.data, healthDayData.data);
    }

    public int hashCode() {
        return (((this.day.hashCode() * 31) + this.yesterday.hashCode()) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "HealthDayData(day=" + this.day + ", yesterday=" + this.yesterday + ", data=" + this.data + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HealthDayData(String day, String yesterday, List<? extends T> data) {
        Intrinsics.checkNotNullParameter(day, "day");
        Intrinsics.checkNotNullParameter(yesterday, "yesterday");
        Intrinsics.checkNotNullParameter(data, "data");
        this.day = day;
        this.yesterday = yesterday;
        this.data = data;
    }

    public /* synthetic */ HealthDayData(String str, String str2, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? new ArrayList() : arrayList);
    }

    public final String getDay() {
        return this.day;
    }

    public final String getYesterday() {
        return this.yesterday;
    }

    public final List<T> getData() {
        return this.data;
    }
}
