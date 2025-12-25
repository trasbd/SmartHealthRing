package com.yucheng.smarthealthpro.database.room.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

/* compiled from: SleepItem.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\f\"\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/bean/SleepItem;", "", "time", "", "mode", "", TypedValues.TransitionType.S_DURATION, "<init>", "(JII)V", "getTime", "()J", "getMode", "()I", "getDuration", "setDuration", "(I)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class SleepItem {

    @SerializedName(alternate = {"sleepLong"}, value = "sleepLen")
    private int duration;

    @SerializedName("sleepType")
    private final int mode;

    @SerializedName(alternate = {"stime"}, value = "sleepStartTime")
    private final long time;

    public static /* synthetic */ SleepItem copy$default(SleepItem sleepItem, long j2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            j2 = sleepItem.time;
        }
        if ((i4 & 2) != 0) {
            i2 = sleepItem.mode;
        }
        if ((i4 & 4) != 0) {
            i3 = sleepItem.duration;
        }
        return sleepItem.copy(j2, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    /* renamed from: component2, reason: from getter */
    public final int getMode() {
        return this.mode;
    }

    /* renamed from: component3, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    public final SleepItem copy(long time, int mode, int duration) {
        return new SleepItem(time, mode, duration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepItem)) {
            return false;
        }
        SleepItem sleepItem = (SleepItem) other;
        return this.time == sleepItem.time && this.mode == sleepItem.mode && this.duration == sleepItem.duration;
    }

    public int hashCode() {
        return (((Long.hashCode(this.time) * 31) + Integer.hashCode(this.mode)) * 31) + Integer.hashCode(this.duration);
    }

    public String toString() {
        return "SleepItem(time=" + this.time + ", mode=" + this.mode + ", duration=" + this.duration + ")";
    }

    public SleepItem(long j2, int i2, int i3) {
        this.time = j2;
        this.mode = i2;
        this.duration = i3;
    }

    public final long getTime() {
        return this.time;
    }

    public final int getMode() {
        return this.mode;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i2) {
        this.duration = i2;
    }
}
