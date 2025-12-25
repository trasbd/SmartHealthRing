package com.yucheng.smarthealthpro.customchart.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.formatter.DefaultAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class AxisBase extends ComponentBase {
    protected IAxisValueFormatter mAxisValueFormatter;
    public int mDecimals;
    public int mEntryCount;
    protected List<LimitLine> mLimitLines;
    private int mGridColor = -7829368;
    private float mGridLineWidth = 1.0f;
    private int mAxisLineColor = -7829368;
    private float mAxisLineWidth = 1.0f;
    public float[] mEntries = new float[0];
    public float[] mCenteredEntries = new float[0];
    private int mLabelCount = 6;
    protected float mGranularity = 1.0f;
    protected boolean mGranularityEnabled = false;
    protected boolean mForceLabels = false;
    protected boolean mDrawGridLines = true;
    protected boolean mDrawAxisLine = true;
    protected boolean mDrawLabels = true;
    protected boolean mCenterAxisLabels = false;
    private DashPathEffect mAxisLineDashPathEffect = null;
    private DashPathEffect mGridDashPathEffect = null;
    protected boolean mDrawLimitLineBehindData = false;
    protected boolean mDrawGridLinesBehindData = true;
    protected float mSpaceMin = 0.0f;
    protected float mSpaceMax = 0.0f;
    protected boolean mCustomAxisMin = false;
    protected boolean mCustomAxisMax = false;
    public float mAxisMaximum = 0.0f;
    public float mAxisMinimum = 0.0f;
    public float mSleepTime = 0.0f;
    public float mUpTime = 0.0f;
    public float mAxisRange = 0.0f;
    private int mAxisMinLabels = 2;
    private int mAxisMaxLabels = 25;

    public int getAxisMinLabels() {
        return this.mAxisMinLabels;
    }

    public void setAxisMinLabels(int labels) {
        if (labels > 0) {
            this.mAxisMinLabels = labels;
        }
    }

    public int getAxisMaxLabels() {
        return this.mAxisMaxLabels;
    }

    public void setAxisMaxLabels(int labels) {
        if (labels > 0) {
            this.mAxisMaxLabels = labels;
        }
    }

    public AxisBase() {
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(5.0f);
        this.mLimitLines = new ArrayList();
    }

    public void setDrawGridLines(boolean enabled) {
        this.mDrawGridLines = enabled;
    }

    public boolean isDrawGridLinesEnabled() {
        return this.mDrawGridLines;
    }

    public void setDrawAxisLine(boolean enabled) {
        this.mDrawAxisLine = enabled;
    }

    public boolean isDrawAxisLineEnabled() {
        return this.mDrawAxisLine;
    }

    public void setCenterAxisLabels(boolean enabled) {
        this.mCenterAxisLabels = enabled;
    }

    public boolean isCenterAxisLabelsEnabled() {
        return this.mCenterAxisLabels && this.mEntryCount > 0;
    }

    public void setGridColor(int color) {
        this.mGridColor = color;
    }

    public int getGridColor() {
        return this.mGridColor;
    }

    public void setAxisLineWidth(float width) {
        this.mAxisLineWidth = Utils.convertDpToPixel(width);
    }

    public float getAxisLineWidth() {
        return this.mAxisLineWidth;
    }

    public void setGridLineWidth(float width) {
        this.mGridLineWidth = Utils.convertDpToPixel(width);
    }

    public float getGridLineWidth() {
        return this.mGridLineWidth;
    }

    public void setAxisLineColor(int color) {
        this.mAxisLineColor = color;
    }

    public int getAxisLineColor() {
        return this.mAxisLineColor;
    }

    public void setDrawLabels(boolean enabled) {
        this.mDrawLabels = enabled;
    }

    public boolean isDrawLabelsEnabled() {
        return this.mDrawLabels;
    }

    public void setLabelCount(int count) {
        if (count > getAxisMaxLabels()) {
            count = getAxisMaxLabels();
        }
        if (count < getAxisMinLabels()) {
            count = getAxisMinLabels();
        }
        this.mLabelCount = count;
        this.mForceLabels = false;
    }

    public void setLabelCount(int count, boolean force) {
        setLabelCount(count);
        this.mForceLabels = force;
    }

    public boolean isForceLabelsEnabled() {
        return this.mForceLabels;
    }

    public int getLabelCount() {
        return this.mLabelCount;
    }

    public boolean isGranularityEnabled() {
        return this.mGranularityEnabled;
    }

    public void setGranularityEnabled(boolean enabled) {
        this.mGranularityEnabled = enabled;
    }

    public float getGranularity() {
        return this.mGranularity;
    }

    public void setGranularity(float granularity) {
        this.mGranularity = granularity;
        this.mGranularityEnabled = true;
    }

    public void addLimitLine(LimitLine l) {
        this.mLimitLines.add(l);
        if (this.mLimitLines.size() > 6) {
            Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
        }
    }

    public void removeLimitLine(LimitLine l) {
        this.mLimitLines.remove(l);
    }

    public void removeAllLimitLines() {
        this.mLimitLines.clear();
    }

    public List<LimitLine> getLimitLines() {
        return this.mLimitLines;
    }

    public void setDrawLimitLinesBehindData(boolean enabled) {
        this.mDrawLimitLineBehindData = enabled;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return this.mDrawLimitLineBehindData;
    }

    public void setDrawGridLinesBehindData(boolean enabled) {
        this.mDrawGridLinesBehindData = enabled;
    }

    public boolean isDrawGridLinesBehindDataEnabled() {
        return this.mDrawGridLinesBehindData;
    }

    public String getLongestLabel() {
        String str = "";
        for (int i2 = 0; i2 < this.mEntries.length; i2++) {
            String formattedLabel = getFormattedLabel(i2);
            if (formattedLabel != null && str.length() < formattedLabel.length()) {
                str = formattedLabel;
            }
        }
        return str;
    }

    public String getFormattedLabel(int index) {
        if (index < 0 || index >= this.mEntries.length) {
            return "";
        }
        return getValueFormatter().getFormattedValue(this.mEntries[index], this);
    }

    public void setValueFormatter(IAxisValueFormatter f2) {
        if (f2 == null) {
            this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
        } else {
            this.mAxisValueFormatter = f2;
        }
    }

    public IAxisValueFormatter getValueFormatter() {
        IAxisValueFormatter iAxisValueFormatter = this.mAxisValueFormatter;
        if (iAxisValueFormatter == null || ((iAxisValueFormatter instanceof DefaultAxisValueFormatter) && ((DefaultAxisValueFormatter) iAxisValueFormatter).getDecimalDigits() != this.mDecimals)) {
            this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
        }
        return this.mAxisValueFormatter;
    }

    public void enableGridDashedLine(float lineLength, float spaceLength, float phase) {
        this.mGridDashPathEffect = new DashPathEffect(new float[]{lineLength, spaceLength}, phase);
    }

    public void setGridDashedLine(DashPathEffect effect) {
        this.mGridDashPathEffect = effect;
    }

    public void disableGridDashedLine() {
        this.mGridDashPathEffect = null;
    }

    public boolean isGridDashedLineEnabled() {
        return this.mGridDashPathEffect != null;
    }

    public DashPathEffect getGridDashPathEffect() {
        return this.mGridDashPathEffect;
    }

    public void enableAxisLineDashedLine(float lineLength, float spaceLength, float phase) {
        this.mAxisLineDashPathEffect = new DashPathEffect(new float[]{lineLength, spaceLength}, phase);
    }

    public void setAxisLineDashedLine(DashPathEffect effect) {
        this.mAxisLineDashPathEffect = effect;
    }

    public void disableAxisLineDashedLine() {
        this.mAxisLineDashPathEffect = null;
    }

    public boolean isAxisLineDashedLineEnabled() {
        return this.mAxisLineDashPathEffect != null;
    }

    public DashPathEffect getAxisLineDashPathEffect() {
        return this.mAxisLineDashPathEffect;
    }

    public float getAxisMaximum() {
        return this.mAxisMaximum;
    }

    public float getAxisMinimum() {
        return this.mAxisMinimum;
    }

    public void resetAxisMaximum() {
        this.mCustomAxisMax = false;
    }

    public boolean isAxisMaxCustom() {
        return this.mCustomAxisMax;
    }

    public void resetAxisMinimum() {
        this.mCustomAxisMin = false;
    }

    public boolean isAxisMinCustom() {
        return this.mCustomAxisMin;
    }

    public void setAxisMinimum(float min) {
        this.mCustomAxisMin = true;
        this.mAxisMinimum = min;
        this.mAxisRange = Math.abs(this.mAxisMaximum - min);
    }

    @Deprecated
    public void setAxisMinValue(float min) {
        setAxisMinimum(min);
    }

    public void setAxisMaximum(float max) {
        this.mCustomAxisMax = true;
        this.mAxisMaximum = max;
        this.mAxisRange = Math.abs(max - this.mAxisMinimum);
    }

    @Deprecated
    public void setAxisMaxValue(float max) {
        setAxisMaximum(max);
    }

    public void setSleep(float sleepTime) {
        this.mSleepTime = sleepTime;
    }

    public void setUp(float upTime) {
        this.mUpTime = upTime;
    }

    public void calculate(float dataMin, float dataMax) {
        float f2 = this.mCustomAxisMin ? this.mAxisMinimum : dataMin - this.mSpaceMin;
        float f3 = this.mCustomAxisMax ? this.mAxisMaximum : dataMax + this.mSpaceMax;
        if (Math.abs(f3 - f2) == 0.0f) {
            f3 += 1.0f;
            f2 -= 1.0f;
        }
        this.mAxisMinimum = f2;
        this.mAxisMaximum = f3;
        this.mAxisRange = Math.abs(f3 - f2);
    }

    public float getSpaceMin() {
        return this.mSpaceMin;
    }

    public void setSpaceMin(float mSpaceMin) {
        this.mSpaceMin = mSpaceMin;
    }

    public float getSpaceMax() {
        return this.mSpaceMax;
    }

    public void setSpaceMax(float mSpaceMax) {
        this.mSpaceMax = mSpaceMax;
    }
}
