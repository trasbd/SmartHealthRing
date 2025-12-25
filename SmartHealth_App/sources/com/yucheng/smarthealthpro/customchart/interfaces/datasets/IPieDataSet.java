package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import com.yucheng.smarthealthpro.customchart.data.PieDataSet;
import com.yucheng.smarthealthpro.customchart.data.PieEntry;

/* loaded from: classes4.dex */
public interface IPieDataSet extends IDataSet<PieEntry> {
    Integer getHighlightColor();

    float getSelectionShift();

    float getSliceSpace();

    int getValueLineColor();

    float getValueLinePart1Length();

    float getValueLinePart1OffsetPercentage();

    float getValueLinePart2Length();

    float getValueLineWidth();

    PieDataSet.ValuePosition getXValuePosition();

    PieDataSet.ValuePosition getYValuePosition();

    boolean isAutomaticallyDisableSliceSpacingEnabled();

    boolean isUseValueColorForLineEnabled();

    boolean isValueLineVariableLength();
}
