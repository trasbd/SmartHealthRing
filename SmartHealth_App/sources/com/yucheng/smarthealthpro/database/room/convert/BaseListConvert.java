package com.yucheng.smarthealthpro.database.room.convert;

import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BaseListConvert.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\nJ(\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00060\b\"\u0004\b\u0000\u0010\u00062\u0006\u0010\f\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n¨\u0006\r"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/convert/BaseListConvert;", "", "<init>", "()V", "toJson", "", ExifInterface.GPS_DIRECTION_TRUE, "list", "", "elementType", "Ljava/lang/Class;", "fromJson", "json", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BaseListConvert {
    public final <T> String toJson(List<? extends T> list, Class<T> elementType) throws JsonIOException {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(elementType, "elementType");
        String json = new Gson().toJson(list, TypeToken.getParameterized(List.class, elementType).getType());
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        return json;
    }

    public final <T> List<T> fromJson(String json, Class<T> elementType) {
        List<T> list;
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(elementType, "elementType");
        return (StringsKt.isBlank(json) || (list = (List) new Gson().fromJson(json, TypeToken.getParameterized(List.class, elementType).getType())) == null) ? CollectionsKt.emptyList() : list;
    }
}
