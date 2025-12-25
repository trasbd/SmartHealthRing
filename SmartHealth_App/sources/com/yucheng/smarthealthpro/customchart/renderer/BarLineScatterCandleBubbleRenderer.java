package com.yucheng.smarthealthpro.customchart.renderer;

import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.data.DataSet;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public abstract class BarLineScatterCandleBubbleRenderer extends DataRenderer {
    protected XBounds mXBounds;

    public BarLineScatterCandleBubbleRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mXBounds = new XBounds();
    }

    protected boolean shouldDrawValues(IDataSet set) {
        return set.isVisible() && (set.isDrawValuesEnabled() || set.isDrawIconsEnabled());
    }

    protected boolean isInBoundsX(Entry e2, IBarLineScatterCandleBubbleDataSet set) {
        if (e2 == null) {
            return false;
        }
        return e2 != null && ((float) set.getEntryIndex(e2)) < ((float) set.getEntryCount()) * this.mAnimator.getPhaseX();
    }

    protected class XBounds {
        public int max;
        public int min;
        public int range;

        protected XBounds() {
        }

        public void set(BarLineScatterCandleBubbleDataProvider chart, IBarLineScatterCandleBubbleDataSet dataSet) {
            float fMax = Math.max(0.0f, Math.min(1.0f, BarLineScatterCandleBubbleRenderer.this.mAnimator.getPhaseX()));
            float lowestVisibleX = chart.getLowestVisibleX();
            float highestVisibleX = chart.getHighestVisibleX();
            T entryForXValue = dataSet.getEntryForXValue(lowestVisibleX, Float.NaN, DataSet.Rounding.DOWN);
            T entryForXValue2 = dataSet.getEntryForXValue(highestVisibleX, Float.NaN, DataSet.Rounding.UP);
            this.min = entryForXValue == 0 ? 0 : dataSet.getEntryIndex(entryForXValue);
            this.max = entryForXValue2 != 0 ? dataSet.getEntryIndex(entryForXValue2) : 0;
            this.range = (int) ((r2 - this.min) * fMax);
        }
    }
}
