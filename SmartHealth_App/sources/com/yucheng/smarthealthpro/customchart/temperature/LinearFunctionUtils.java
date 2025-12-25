package com.yucheng.smarthealthpro.customchart.temperature;

import com.yucheng.smarthealthpro.customchart.data.Entry;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class LinearFunctionUtils {
    public static List<Entry> getCoordinates(float startX, float startY, float endX, float endY, float num) {
        float f2 = endX - startX;
        float f3 = (endY - startY) / f2;
        float f4 = startY - (f3 * startX);
        float f5 = f2 / num;
        ArrayList arrayList = new ArrayList();
        while (startX < endX) {
            arrayList.add(new Entry(startX, (f3 * startX) + f4));
            startX += f5;
        }
        return arrayList;
    }
}
