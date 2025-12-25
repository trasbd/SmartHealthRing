package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import com.yucheng.smarthealthpro.customchart.components.Legend;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.DataSet;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import java.util.List;

/* loaded from: classes4.dex */
public interface IDataSet<T extends Entry> {
    boolean addEntry(T e2);

    void addEntryOrdered(T e2);

    void calcMinMax();

    void calcMinMaxY(float fromX, float toX);

    void clear();

    boolean contains(T entry);

    YAxis.AxisDependency getAxisDependency();

    int getColor();

    int getColor(int index);

    List<Integer> getColors();

    List<T> getEntriesForXValue(float xValue);

    int getEntryCount();

    T getEntryForIndex(int index);

    T getEntryForXValue(float xValue, float closestToY);

    T getEntryForXValue(float xValue, float closestToY, DataSet.Rounding rounding);

    int getEntryIndex(float xValue, float closestToY, DataSet.Rounding rounding);

    int getEntryIndex(T e2);

    Legend.LegendForm getForm();

    DashPathEffect getFormLineDashEffect();

    float getFormLineWidth();

    float getFormSize();

    MPPointF getIconsOffset();

    int getIndexInEntries(int xIndex);

    String getLabel();

    IValueFormatter getValueFormatter();

    int getValueTextColor();

    int getValueTextColor(int index);

    float getValueTextSize();

    Typeface getValueTypeface();

    float getXMax();

    float getXMin();

    float getYMax();

    float getYMin();

    boolean isDrawIconsEnabled();

    boolean isDrawValuesEnabled();

    boolean isHighlightEnabled();

    boolean isVisible();

    boolean needsFormatter();

    boolean removeEntry(int index);

    boolean removeEntry(T e2);

    boolean removeEntryByXValue(float xValue);

    boolean removeFirst();

    boolean removeLast();

    void setAxisDependency(YAxis.AxisDependency dependency);

    void setDrawIcons(boolean enabled);

    void setDrawValues(boolean enabled);

    void setHighlightEnabled(boolean enabled);

    void setIconsOffset(MPPointF offset);

    void setLabel(String label);

    void setValueFormatter(IValueFormatter f2);

    void setValueTextColor(int color);

    void setValueTextColors(List<Integer> colors);

    void setValueTextSize(float size);

    void setValueTypeface(Typeface tf);

    void setVisible(boolean visible);
}
