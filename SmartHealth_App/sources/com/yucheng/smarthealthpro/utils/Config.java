package com.yucheng.smarthealthpro.utils;

import kotlin.Metadata;

/* compiled from: Config.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/Config;", "", "<init>", "()V", "DBP_MIN", "", "DBP_MAX", "SBP_MIN", "SBP_MAX", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Config {
    public static final int DBP_MAX = 160;
    public static final int DBP_MIN = 30;
    public static final Config INSTANCE = new Config();
    public static final int SBP_MAX = 250;
    public static final int SBP_MIN = 60;

    private Config() {
    }
}
