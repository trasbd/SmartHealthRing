package com.yucheng.smarthealthpro.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringUtils.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"keepOnlyDigits", "", "input", "app_SmartHealthRelease"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class StringUtilsKt {
    public static final String keepOnlyDigits(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        StringBuilder sb = new StringBuilder();
        int length = input.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = input.charAt(i2);
            if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
