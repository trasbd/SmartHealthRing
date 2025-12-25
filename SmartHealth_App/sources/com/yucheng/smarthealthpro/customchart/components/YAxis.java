package com.yucheng.smarthealthpro.customchart.components;

import android.graphics.Paint;
import com.yucheng.smarthealthpro.customchart.utils.Utils;

/* loaded from: classes4.dex */
public class YAxis extends AxisBase {
    private AxisDependency mAxisDependency;
    private boolean mDrawBottomYLabelEntry;
    private boolean mDrawTopYLabelEntry;
    protected boolean mDrawZeroLine;
    protected boolean mInverted;
    protected float mMaxWidth;
    protected float mMinWidth;
    private YAxisLabelPosition mPosition;
    protected float mSpacePercentBottom;
    protected float mSpacePercentTop;
    private boolean mUseAutoScaleRestrictionMax;
    private boolean mUseAutoScaleRestrictionMin;
    private float mXLabelOffset;
    protected int mZeroLineColor;
    protected float mZeroLineWidth;

    public enum AxisDependency {
        LEFT,
        RIGHT
    }

    public enum YAxisLabelPosition {
        OUTSIDE_CHART,
        INSIDE_CHART
    }

    public YAxis() {
        this.mDrawBottomYLabelEntry = true;
        this.mDrawTopYLabelEntry = true;
        this.mInverted = false;
        this.mDrawZeroLine = false;
        this.mUseAutoScaleRestrictionMin = false;
        this.mUseAutoScaleRestrictionMax = false;
        this.mZeroLineColor = -7829368;
        this.mZeroLineWidth = 1.0f;
        this.mSpacePercentTop = 10.0f;
        this.mSpacePercentBottom = 10.0f;
        this.mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        this.mXLabelOffset = 0.0f;
        this.mMinWidth = 0.0f;
        this.mMaxWidth = Float.POSITIVE_INFINITY;
        this.mAxisDependency = AxisDependency.LEFT;
        this.mYOffset = 0.0f;
    }

    public YAxis(AxisDependency position) {
        this.mDrawBottomYLabelEntry = true;
        this.mDrawTopYLabelEntry = true;
        this.mInverted = false;
        this.mDrawZeroLine = false;
        this.mUseAutoScaleRestrictionMin = false;
        this.mUseAutoScaleRestrictionMax = false;
        this.mZeroLineColor = -7829368;
        this.mZeroLineWidth = 1.0f;
        this.mSpacePercentTop = 10.0f;
        this.mSpacePercentBottom = 10.0f;
        this.mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        this.mXLabelOffset = 0.0f;
        this.mMinWidth = 0.0f;
        this.mMaxWidth = Float.POSITIVE_INFINITY;
        this.mAxisDependency = position;
        this.mYOffset = 0.0f;
    }

    public AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    public float getMinWidth() {
        return this.mMinWidth;
    }

    public void setMinWidth(float minWidth) {
        this.mMinWidth = minWidth;
    }

    public float getMaxWidth() {
        return this.mMaxWidth;
    }

    public void setMaxWidth(float maxWidth) {
        this.mMaxWidth = maxWidth;
    }

    public YAxisLabelPosition getLabelPosition() {
        return this.mPosition;
    }

    public void setPosition(YAxisLabelPosition pos) {
        this.mPosition = pos;
    }

    public float getLabelXOffset() {
        return this.mXLabelOffset;
    }

    public void setLabelXOffset(float xOffset) {
        this.mXLabelOffset = xOffset;
    }

    public boolean isDrawTopYLabelEntryEnabled() {
        return this.mDrawTopYLabelEntry;
    }

    public boolean isDrawBottomYLabelEntryEnabled() {
        return this.mDrawBottomYLabelEntry;
    }

    public void setDrawTopYLabelEntry(boolean enabled) {
        this.mDrawTopYLabelEntry = enabled;
    }

    public void setInverted(boolean enabled) {
        this.mInverted = enabled;
    }

    public boolean isInverted() {
        return this.mInverted;
    }

    @Deprecated
    public void setStartAtZero(boolean startAtZero) {
        if (startAtZero) {
            setAxisMinimum(0.0f);
        } else {
            resetAxisMinimum();
        }
    }

    public void setSpaceTop(float percent) {
        this.mSpacePercentTop = percent;
    }

    public float getSpaceTop() {
        return this.mSpacePercentTop;
    }

    public void setSpaceBottom(float percent) {
        this.mSpacePercentBottom = percent;
    }

    public float getSpaceBottom() {
        return this.mSpacePercentBottom;
    }

    public boolean isDrawZeroLineEnabled() {
        return this.mDrawZeroLine;
    }

    public void setDrawZeroLine(boolean mDrawZeroLine) {
        this.mDrawZeroLine = mDrawZeroLine;
    }

    public int getZeroLineColor() {
        return this.mZeroLineColor;
    }

    public void setZeroLineColor(int color) {
        this.mZeroLineColor = color;
    }

    public float getZeroLineWidth() {
        return this.mZeroLineWidth;
    }

    public void setZeroLineWidth(float width) {
        this.mZeroLineWidth = Utils.convertDpToPixel(width);
    }

    public float getRequiredWidthSpace(Paint p) {
        p.setTextSize(this.mTextSize);
        float fCalcTextWidth = Utils.calcTextWidth(p, getLongestLabel()) + (getXOffset() * 2.0f);
        float minWidth = getMinWidth();
        float maxWidth = getMaxWidth();
        if (minWidth > 0.0f) {
            minWidth = Utils.convertDpToPixel(minWidth);
        }
        if (maxWidth > 0.0f && maxWidth != Float.POSITIVE_INFINITY) {
            maxWidth = Utils.convertDpToPixel(maxWidth);
        }
        if (maxWidth <= 0.0d) {
            maxWidth = fCalcTextWidth;
        }
        return Math.max(minWidth, Math.min(fCalcTextWidth, maxWidth));
    }

    public float getRequiredHeightSpace(Paint p) {
        p.setTextSize(this.mTextSize);
        return Utils.calcTextHeight(p, getLongestLabel()) + (getYOffset() * 2.0f);
    }

    public boolean needsOffset() {
        return isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxisLabelPosition.OUTSIDE_CHART;
    }

    @Deprecated
    public boolean isUseAutoScaleMinRestriction() {
        return this.mUseAutoScaleRestrictionMin;
    }

    @Deprecated
    public void setUseAutoScaleMinRestriction(boolean isEnabled) {
        this.mUseAutoScaleRestrictionMin = isEnabled;
    }

    @Deprecated
    public boolean isUseAutoScaleMaxRestriction() {
        return this.mUseAutoScaleRestrictionMax;
    }

    @Deprecated
    public void setUseAutoScaleMaxRestriction(boolean isEnabled) {
        this.mUseAutoScaleRestrictionMax = isEnabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.AxisBase
    public void calculate(float dataMin, float dataMax) {
        if (dataMin <= dataMax) {
            dataMax = dataMin;
            dataMin = dataMax;
        } else if (!this.mCustomAxisMax || !this.mCustomAxisMin) {
            if (this.mCustomAxisMax) {
                dataMin = dataMax < 0.0f ? 1.5f * dataMax : 0.5f * dataMax;
            } else if (this.mCustomAxisMin) {
                dataMax = dataMin;
                dataMin = dataMin < 0.0f ? 0.5f * dataMin : dataMin * 1.5f;
            }
            dataMax = dataMin;
            dataMin = dataMax;
        }
        if (Math.abs(dataMin - dataMax) == 0.0f) {
            dataMin += 1.0f;
            dataMax -= 1.0f;
        }
        float fAbs = Math.abs(dataMin - dataMax);
        this.mAxisMinimum = this.mCustomAxisMin ? this.mAxisMinimum : dataMax - ((fAbs / 100.0f) * getSpaceBottom());
        this.mAxisMaximum = this.mCustomAxisMax ? this.mAxisMaximum : dataMin + ((fAbs / 100.0f) * getSpaceTop());
        this.mAxisRange = Math.abs(this.mAxisMinimum - this.mAxisMaximum);
    }
}
