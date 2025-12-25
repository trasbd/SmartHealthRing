package com.yucheng.smarthealthpro.me.bean;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: HealthFunctionUnitBean.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/yucheng/smarthealthpro/me/bean/HealthFunctionType;", "", "<init>", "(Ljava/lang/String;I)V", "BLOODSUGAR", "URICACID", "BLOODFAT", "HEARTRATE", "BLOODOXYGEN", "TEMPERATURE", "BPNORMAL", "BPACCURATE", "ECG", "HRV", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class HealthFunctionType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ HealthFunctionType[] $VALUES;
    public static final HealthFunctionType BLOODSUGAR = new HealthFunctionType("BLOODSUGAR", 0);
    public static final HealthFunctionType URICACID = new HealthFunctionType("URICACID", 1);
    public static final HealthFunctionType BLOODFAT = new HealthFunctionType("BLOODFAT", 2);
    public static final HealthFunctionType HEARTRATE = new HealthFunctionType("HEARTRATE", 3);
    public static final HealthFunctionType BLOODOXYGEN = new HealthFunctionType("BLOODOXYGEN", 4);
    public static final HealthFunctionType TEMPERATURE = new HealthFunctionType("TEMPERATURE", 5);
    public static final HealthFunctionType BPNORMAL = new HealthFunctionType("BPNORMAL", 6);
    public static final HealthFunctionType BPACCURATE = new HealthFunctionType("BPACCURATE", 7);
    public static final HealthFunctionType ECG = new HealthFunctionType("ECG", 8);
    public static final HealthFunctionType HRV = new HealthFunctionType("HRV", 9);

    private static final /* synthetic */ HealthFunctionType[] $values() {
        return new HealthFunctionType[]{BLOODSUGAR, URICACID, BLOODFAT, HEARTRATE, BLOODOXYGEN, TEMPERATURE, BPNORMAL, BPACCURATE, ECG, HRV};
    }

    public static EnumEntries<HealthFunctionType> getEntries() {
        return $ENTRIES;
    }

    private HealthFunctionType(String str, int i2) {
    }

    static {
        HealthFunctionType[] healthFunctionTypeArr$values = $values();
        $VALUES = healthFunctionTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(healthFunctionTypeArr$values);
    }

    public static HealthFunctionType valueOf(String str) {
        return (HealthFunctionType) Enum.valueOf(HealthFunctionType.class, str);
    }

    public static HealthFunctionType[] values() {
        return (HealthFunctionType[]) $VALUES.clone();
    }
}
