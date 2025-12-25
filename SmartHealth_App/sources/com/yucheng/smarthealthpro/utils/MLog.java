package com.yucheng.smarthealthpro.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MLog.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/MLog;", "", "<init>", "()V", "Companion", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MLog {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "MLog";

    /* compiled from: MLog.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/MLog$Companion;", "", "<init>", "()V", "TAG", "", "v", "", "msg", "d", "e", "i", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void d(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
        }

        public final void e(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
        }

        public final void i(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
        }

        public final void v(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
        }

        private Companion() {
        }
    }
}
