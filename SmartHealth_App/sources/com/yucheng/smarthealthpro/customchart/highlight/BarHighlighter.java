package com.yucheng.smarthealthpro.customchart.highlight;

import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.BarLineScatterCandleBubbleData;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;

/* loaded from: classes4.dex */
public class BarHighlighter extends ChartHighlighter<BarDataProvider> {
    public BarHighlighter(BarDataProvider chart) {
        super(chart);
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter, com.yucheng.smarthealthpro.customchart.highlight.IHighlighter
    public Highlight getHighlight(float x, float y) {
        Highlight highlight = super.getHighlight(x, y);
        if (highlight == null) {
            return null;
        }
        MPPointD valsForTouch = getValsForTouch(x, y);
        IBarDataSet iBarDataSet = (IBarDataSet) ((BarDataProvider) this.mChart).getBarData().getDataSetByIndex(highlight.getDataSetIndex());
        if (iBarDataSet.isStacked()) {
            return getStackedHighlight(highlight, iBarDataSet, (float) valsForTouch.x, (float) valsForTouch.y);
        }
        MPPointD.recycleInstance(valsForTouch);
        return highlight;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Highlight getStackedHighlight(Highlight high, IBarDataSet set, float xVal, float yVal) {
        BarEntry barEntry = (BarEntry) set.getEntryForXValue(xVal, yVal);
        if (barEntry == null) {
            return null;
        }
        if (barEntry.getYVals() == null) {
            return high;
        }
        Range[] ranges = barEntry.getRanges();
        if (ranges.length <= 0) {
            return null;
        }
        int closestStackIndex = getClosestStackIndex(ranges, yVal);
        MPPointD pixelForValues = ((BarDataProvider) this.mChart).getTransformer(set.getAxisDependency()).getPixelForValues(high.getX(), ranges[closestStackIndex].to);
        Highlight highlight = new Highlight(barEntry.getX(), barEntry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, high.getDataSetIndex(), closestStackIndex, high.getAxis());
        MPPointD.recycleInstance(pixelForValues);
        return highlight;
    }

    protected int getClosestStackIndex(Range[] ranges, float value) {
        if (ranges == null || ranges.length == 0) {
            return 0;
        }
        int i2 = 0;
        for (Range range : ranges) {
            if (range.contains(value)) {
                return i2;
            }
            i2++;
        }
        int iMax = Math.max(ranges.length - 1, 0);
        if (value > ranges[iMax].to) {
            return iMax;
        }
        return 0;
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter
    protected float getDistance(float x1, float y1, float x2, float y2) {
        return Math.abs(x1 - x2);
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter
    protected BarLineScatterCandleBubbleData getData() {
        return ((BarDataProvider) this.mChart).getBarData();
    }
}
