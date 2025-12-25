package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.utils.Fill;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class BarDataSet extends BarLineScatterCandleBubbleDataSet<BarEntry> implements IBarDataSet {
    private int mBarBorderColor;
    private float mBarBorderWidth;
    private int mBarShadowColor;
    private int mEntryCountStacks;
    protected List<Fill> mFills;
    private int mHighLightAlpha;
    private String[] mStackLabels;
    private int mStackSize;

    public BarDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
        this.mStackSize = 1;
        this.mBarShadowColor = Color.rgb(215, 215, 215);
        this.mBarBorderWidth = 0.0f;
        this.mBarBorderColor = ViewCompat.MEASURED_STATE_MASK;
        this.mHighLightAlpha = 120;
        this.mEntryCountStacks = 0;
        this.mStackLabels = new String[0];
        this.mFills = null;
        this.mHighLightColor = Color.rgb(0, 0, 0);
        calcStackSize(yVals);
        calcEntryCountIncludingStacks(yVals);
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public DataSet<BarEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mEntries.size(); i2++) {
            arrayList.add(((BarEntry) this.mEntries.get(i2)).copy());
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, getLabel());
        copy(barDataSet);
        return barDataSet;
    }

    protected void copy(BarDataSet barDataSet) {
        super.copy((BarLineScatterCandleBubbleDataSet) barDataSet);
        barDataSet.mStackSize = this.mStackSize;
        barDataSet.mBarShadowColor = this.mBarShadowColor;
        barDataSet.mBarBorderWidth = this.mBarBorderWidth;
        barDataSet.mStackLabels = this.mStackLabels;
        barDataSet.mHighLightAlpha = this.mHighLightAlpha;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public List<Fill> getFills() {
        return this.mFills;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public Fill getFill(int index) {
        List<Fill> list = this.mFills;
        return list.get(index % list.size());
    }

    @Deprecated
    public List<Fill> getGradients() {
        return this.mFills;
    }

    @Deprecated
    public Fill getGradient(int index) {
        return getFill(index);
    }

    public void setGradientColor(int startColor, int endColor) {
        this.mFills.clear();
        this.mFills.add(new Fill(startColor, endColor));
    }

    @Deprecated
    public void setGradientColors(List<Fill> gradientColors) {
        this.mFills = gradientColors;
    }

    public void setFills(List<Fill> fills) {
        this.mFills = fills;
    }

    private void calcEntryCountIncludingStacks(List<BarEntry> yVals) {
        this.mEntryCountStacks = 0;
        for (int i2 = 0; i2 < yVals.size(); i2++) {
            float[] yVals2 = yVals.get(i2).getYVals();
            if (yVals2 == null) {
                this.mEntryCountStacks++;
            } else {
                this.mEntryCountStacks += yVals2.length;
            }
        }
    }

    private void calcStackSize(List<BarEntry> yVals) {
        for (int i2 = 0; i2 < yVals.size(); i2++) {
            float[] yVals2 = yVals.get(i2).getYVals();
            if (yVals2 != null && yVals2.length > this.mStackSize) {
                this.mStackSize = yVals2.length;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public void calcMinMax(BarEntry e2) {
        if (e2 == null || Float.isNaN(e2.getY())) {
            return;
        }
        if (e2.getYVals() == null) {
            if (e2.getY() < this.mYMin) {
                this.mYMin = e2.getY();
            }
            if (e2.getY() > this.mYMax) {
                this.mYMax = e2.getY();
            }
        } else {
            if ((-e2.getNegativeSum()) < this.mYMin) {
                this.mYMin = -e2.getNegativeSum();
            }
            if (e2.getPositiveSum() > this.mYMax) {
                this.mYMax = e2.getPositiveSum();
            }
        }
        calcMinMaxX(e2);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public int getStackSize() {
        return this.mStackSize;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public boolean isStacked() {
        return this.mStackSize > 1;
    }

    public int getEntryCountStacks() {
        return this.mEntryCountStacks;
    }

    public void setBarShadowColor(int color) {
        this.mBarShadowColor = color;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public int getBarShadowColor() {
        return this.mBarShadowColor;
    }

    public void setBarBorderWidth(float width) {
        this.mBarBorderWidth = width;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public float getBarBorderWidth() {
        return this.mBarBorderWidth;
    }

    public void setBarBorderColor(int color) {
        this.mBarBorderColor = color;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public int getBarBorderColor() {
        return this.mBarBorderColor;
    }

    public void setHighLightAlpha(int alpha) {
        this.mHighLightAlpha = alpha;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public int getHighLightAlpha() {
        return this.mHighLightAlpha;
    }

    public void setStackLabels(String[] labels) {
        this.mStackLabels = labels;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet
    public String[] getStackLabels() {
        return this.mStackLabels;
    }
}
