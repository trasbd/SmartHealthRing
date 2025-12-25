package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SPCache.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/SPCache;", "", "context", "Landroid/content/Context;", "name", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "rootName", "sharedPreference", "Landroid/content/SharedPreferences;", "saveFilePath", "", "filePath", "getFilePath", "save", "key", "value", "Companion", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SPCache {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static SPCache cache;
    private Context context;
    private String rootName;
    private SharedPreferences sharedPreference;

    public /* synthetic */ SPCache(Context context, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str);
    }

    private SPCache(Context context, String str) {
        this.context = context;
        this.rootName = str;
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.sharedPreference = sharedPreferences;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    /* compiled from: SPCache.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/SPCache$Companion;", "", "<init>", "()V", "cache", "Lcom/yucheng/smarthealthpro/utils/SPCache;", "getInstance", "name", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x001c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.yucheng.smarthealthpro.utils.SPCache getInstance(java.lang.String r4) {
            /*
                r3 = this;
                java.lang.String r0 = "name"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                com.yucheng.smarthealthpro.utils.SPCache r0 = com.yucheng.smarthealthpro.utils.SPCache.access$getCache$cp()
                if (r0 == 0) goto L1c
                com.yucheng.smarthealthpro.utils.SPCache r0 = com.yucheng.smarthealthpro.utils.SPCache.access$getCache$cp()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                java.lang.String r0 = com.yucheng.smarthealthpro.utils.SPCache.access$getRootName$p(r0)
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
                if (r0 != 0) goto L30
            L1c:
                com.yucheng.smarthealthpro.utils.SPCache r0 = new com.yucheng.smarthealthpro.utils.SPCache
                com.yucheng.smarthealthpro.framework.HealthApplication r1 = com.yucheng.smarthealthpro.MyApplication.getInstance()
                java.lang.String r2 = "getInstance(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                android.content.Context r1 = (android.content.Context) r1
                r2 = 0
                r0.<init>(r1, r4, r2)
                com.yucheng.smarthealthpro.utils.SPCache.access$setCache$cp(r0)
            L30:
                com.yucheng.smarthealthpro.utils.SPCache r4 = com.yucheng.smarthealthpro.utils.SPCache.access$getCache$cp()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.SPCache.Companion.getInstance(java.lang.String):com.yucheng.smarthealthpro.utils.SPCache");
        }
    }

    public final void saveFilePath(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        save("file_path", filePath);
    }

    public final String getFilePath() {
        return this.sharedPreference.getString("file_path", null);
    }

    public final void save(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences.Editor editorEdit = this.sharedPreference.edit();
        if (value != null) {
            if (value instanceof String) {
                editorEdit.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editorEdit.putInt(key, ((Number) value).intValue());
            } else if (value instanceof Boolean) {
                editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Float) {
                editorEdit.putFloat(key, ((Number) value).floatValue());
            } else if (value instanceof Long) {
                editorEdit.putLong(key, ((Number) value).longValue());
            } else {
                editorEdit.putString(key, value.toString());
            }
        }
        editorEdit.apply();
    }
}
