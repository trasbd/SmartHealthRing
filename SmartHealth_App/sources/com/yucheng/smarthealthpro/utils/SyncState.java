package com.yucheng.smarthealthpro.utils;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DataSyncUtils.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/SyncState;", "", "<init>", "(Ljava/lang/String;I)V", "START", "END", "SUCCESS", "FAILED", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SyncState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ SyncState[] $VALUES;
    public static final SyncState START = new SyncState("START", 0);
    public static final SyncState END = new SyncState("END", 1);
    public static final SyncState SUCCESS = new SyncState("SUCCESS", 2);
    public static final SyncState FAILED = new SyncState("FAILED", 3);

    private static final /* synthetic */ SyncState[] $values() {
        return new SyncState[]{START, END, SUCCESS, FAILED};
    }

    public static EnumEntries<SyncState> getEntries() {
        return $ENTRIES;
    }

    private SyncState(String str, int i2) {
    }

    static {
        SyncState[] syncStateArr$values = $values();
        $VALUES = syncStateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(syncStateArr$values);
    }

    public static SyncState valueOf(String str) {
        return (SyncState) Enum.valueOf(SyncState.class, str);
    }

    public static SyncState[] values() {
        return (SyncState[]) $VALUES.clone();
    }
}
