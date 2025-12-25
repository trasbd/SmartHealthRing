package com.yucheng.smarthealthpro.customchart.data;

import androidx.core.view.ViewCompat;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PieDataSet extends DataSet<PieEntry> implements IPieDataSet {
    private boolean mAutomaticallyDisableSliceSpacing;
    private Integer mHighlightColor;
    private float mShift;
    private float mSliceSpace;
    private boolean mUseValueColorForLine;
    private int mValueLineColor;
    private float mValueLinePart1Length;
    private float mValueLinePart1OffsetPercentage;
    private float mValueLinePart2Length;
    private boolean mValueLineVariableLength;
    private float mValueLineWidth;
    private ValuePosition mXValuePosition;
    private ValuePosition mYValuePosition;

    public enum ValuePosition {
        INSIDE_SLICE,
        OUTSIDE_SLICE
    }

    public PieDataSet(List<PieEntry> yVals, String label) {
        super(yVals, label);
        this.mSliceSpace = 0.0f;
        this.mShift = 18.0f;
        this.mXValuePosition = ValuePosition.INSIDE_SLICE;
        this.mYValuePosition = ValuePosition.INSIDE_SLICE;
        this.mValueLineColor = ViewCompat.MEASURED_STATE_MASK;
        this.mUseValueColorForLine = false;
        this.mValueLineWidth = 1.0f;
        this.mValueLinePart1OffsetPercentage = 75.0f;
        this.mValueLinePart1Length = 0.3f;
        this.mValueLinePart2Length = 0.4f;
        this.mValueLineVariableLength = true;
        this.mHighlightColor = null;
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public DataSet<PieEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mEntries.size(); i2++) {
            arrayList.add(((PieEntry) this.mEntries.get(i2)).copy());
        }
        PieDataSet pieDataSet = new PieDataSet(arrayList, getLabel());
        copy(pieDataSet);
        return pieDataSet;
    }

    protected void copy(PieDataSet pieDataSet) {
        super.copy((DataSet) pieDataSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public void calcMinMax(PieEntry e2) {
        if (e2 == null) {
            return;
        }
        calcMinMaxY(e2);
    }

    public void setSliceSpace(float spaceDp) {
        if (spaceDp > 20.0f) {
            spaceDp = 20.0f;
        }
        if (spaceDp < 0.0f) {
            spaceDp = 0.0f;
        }
        this.mSliceSpace = Utils.convertDpToPixel(spaceDp);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public float getSliceSpace() {
        return this.mSliceSpace;
    }

    public void setAutomaticallyDisableSliceSpacing(boolean autoDisable) {
        this.mAutomaticallyDisableSliceSpacing = autoDisable;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public boolean isAutomaticallyDisableSliceSpacingEnabled() {
        return this.mAutomaticallyDisableSliceSpacing;
    }

    public void setSelectionShift(float shift) {
        this.mShift = Utils.convertDpToPixel(shift);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public float getSelectionShift() {
        return this.mShift;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public ValuePosition getXValuePosition() {
        return this.mXValuePosition;
    }

    public void setXValuePosition(ValuePosition xValuePosition) {
        this.mXValuePosition = xValuePosition;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public ValuePosition getYValuePosition() {
        return this.mYValuePosition;
    }

    public void setYValuePosition(ValuePosition yValuePosition) {
        this.mYValuePosition = yValuePosition;
    }

    @Deprecated
    public boolean isUsingSliceColorAsValueLineColor() {
        return isUseValueColorForLineEnabled();
    }

    @Deprecated
    public void setUsingSliceColorAsValueLineColor(boolean enabled) {
        setUseValueColorForLine(enabled);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public int getValueLineColor() {
        return this.mValueLineColor;
    }

    public void setValueLineColor(int valueLineColor) {
        this.mValueLineColor = valueLineColor;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public boolean isUseValueColorForLineEnabled() {
        return this.mUseValueColorForLine;
    }

    public void setUseValueColorForLine(boolean enabled) {
        this.mUseValueColorForLine = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public float getValueLineWidth() {
        return this.mValueLineWidth;
    }

    public void setValueLineWidth(float valueLineWidth) {
        this.mValueLineWidth = valueLineWidth;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public float getValueLinePart1OffsetPercentage() {
        return this.mValueLinePart1OffsetPercentage;
    }

    public void setValueLinePart1OffsetPercentage(float valueLinePart1OffsetPercentage) {
        this.mValueLinePart1OffsetPercentage = valueLinePart1OffsetPercentage;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public float getValueLinePart1Length() {
        return this.mValueLinePart1Length;
    }

    public void setValueLinePart1Length(float valueLinePart1Length) {
        this.mValueLinePart1Length = valueLinePart1Length;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public float getValueLinePart2Length() {
        return this.mValueLinePart2Length;
    }

    public void setValueLinePart2Length(float valueLinePart2Length) {
        this.mValueLinePart2Length = valueLinePart2Length;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public boolean isValueLineVariableLength() {
        return this.mValueLineVariableLength;
    }

    public void setValueLineVariableLength(boolean valueLineVariableLength) {
        this.mValueLineVariableLength = valueLineVariableLength;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IPieDataSet
    public Integer getHighlightColor() {
        return this.mHighlightColor;
    }

    public void setHighlightColor(Integer color) {
        this.mHighlightColor = color;
    }
}
