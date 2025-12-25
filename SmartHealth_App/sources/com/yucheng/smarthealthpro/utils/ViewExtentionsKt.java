package com.yucheng.smarthealthpro.utils;

import android.content.res.Resources;
import android.util.TypedValue;
import kotlin.Metadata;

/* compiled from: ViewExtentions.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"dp", "", "getDp", "(F)F", "sp", "getSp", "app_SmartHealthRelease"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ViewExtentionsKt {
    public static final float getDp(float f2) {
        return TypedValue.applyDimension(1, f2, Resources.getSystem().getDisplayMetrics());
    }

    public static final float getSp(float f2) {
        return TypedValue.applyDimension(2, f2, Resources.getSystem().getDisplayMetrics());
    }
}
