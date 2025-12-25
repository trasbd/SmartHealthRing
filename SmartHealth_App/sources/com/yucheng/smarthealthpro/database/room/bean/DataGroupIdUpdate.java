package com.yucheng.smarthealthpro.database.room.bean;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataGroupIdUpdate.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/DataGroupIdUpdate;", "", "id", "", "dataGroupId", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getDataGroupId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class DataGroupIdUpdate {
    private final String dataGroupId;
    private final long id;

    public static /* synthetic */ DataGroupIdUpdate copy$default(DataGroupIdUpdate dataGroupIdUpdate, long j2, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = dataGroupIdUpdate.id;
        }
        if ((i2 & 2) != 0) {
            str = dataGroupIdUpdate.dataGroupId;
        }
        return dataGroupIdUpdate.copy(j2, str);
    }

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDataGroupId() {
        return this.dataGroupId;
    }

    public final DataGroupIdUpdate copy(long id, String dataGroupId) {
        Intrinsics.checkNotNullParameter(dataGroupId, "dataGroupId");
        return new DataGroupIdUpdate(id, dataGroupId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataGroupIdUpdate)) {
            return false;
        }
        DataGroupIdUpdate dataGroupIdUpdate = (DataGroupIdUpdate) other;
        return this.id == dataGroupIdUpdate.id && Intrinsics.areEqual(this.dataGroupId, dataGroupIdUpdate.dataGroupId);
    }

    public int hashCode() {
        return (Long.hashCode(this.id) * 31) + this.dataGroupId.hashCode();
    }

    public String toString() {
        return "DataGroupIdUpdate(id=" + this.id + ", dataGroupId=" + this.dataGroupId + ")";
    }

    public DataGroupIdUpdate(long j2, String dataGroupId) {
        Intrinsics.checkNotNullParameter(dataGroupId, "dataGroupId");
        this.id = j2;
        this.dataGroupId = dataGroupId;
    }

    public final long getId() {
        return this.id;
    }

    public final String getDataGroupId() {
        return this.dataGroupId;
    }
}
