package com.yucheng.smarthealthpro.view.chart;

import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarLineScatterCandleBubbleData;
import com.yucheng.smarthealthpro.customchart.data.DataSet;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.BarHighlighter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class SleepHighlighter extends BarHighlighter {
    public SleepHighlighter(BarDataProvider chart) {
        super(chart);
    }

    protected Highlight getHighlight(float xVal, float yVal, float x, float y) {
        List<Highlight> highlightsAtXValue = getHighlightsAtXValue(xVal, x, y);
        if (highlightsAtXValue.isEmpty()) {
            return null;
        }
        return getClosestHighlightByPixel(highlightsAtXValue, x, y, getMinimumDistance(highlightsAtXValue, y, YAxis.AxisDependency.LEFT) < getMinimumDistance(highlightsAtXValue, y, YAxis.AxisDependency.RIGHT) ? YAxis.AxisDependency.LEFT : YAxis.AxisDependency.RIGHT, ((BarDataProvider) this.mChart).getMaxHighlightDistance());
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter
    protected Highlight getHighlightForX(float xVal, float x, float y) {
        List<Highlight> highlightsAtXValue = getHighlightsAtXValue(xVal, x, y);
        if (highlightsAtXValue.isEmpty()) {
            return null;
        }
        return getClosestHighlightByPixel(highlightsAtXValue, x, y, getMinimumDistance(highlightsAtXValue, y, YAxis.AxisDependency.LEFT) < getMinimumDistance(highlightsAtXValue, y, YAxis.AxisDependency.RIGHT) ? YAxis.AxisDependency.LEFT : YAxis.AxisDependency.RIGHT, ((BarDataProvider) this.mChart).getMaxHighlightDistance());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet] */
    protected List<Highlight> getHighlights(float xVal, float yVal, float x, float y) {
        this.mHighlightBuffer.clear();
        BarLineScatterCandleBubbleData data = getData();
        if (data == null) {
            return this.mHighlightBuffer;
        }
        int dataSetCount = data.getDataSetCount();
        for (int i2 = 0; i2 < dataSetCount; i2++) {
            ?? dataSetByIndex = data.getDataSetByIndex(i2);
            if (dataSetByIndex.isHighlightEnabled()) {
                this.mHighlightBuffer.addAll(buildHighlights(dataSetByIndex, i2, xVal, yVal, DataSet.Rounding.CLOSEST));
            }
        }
        return this.mHighlightBuffer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet] */
    @Override // com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter
    protected List<Highlight> getHighlightsAtXValue(float xVal, float x, float y) {
        this.mHighlightBuffer.clear();
        BarLineScatterCandleBubbleData data = getData();
        if (data == null) {
            return this.mHighlightBuffer;
        }
        int dataSetCount = data.getDataSetCount();
        for (int i2 = 0; i2 < dataSetCount; i2++) {
            ?? dataSetByIndex = data.getDataSetByIndex(i2);
            if (dataSetByIndex.isHighlightEnabled()) {
                this.mHighlightBuffer.addAll(buildHighlights(dataSetByIndex, i2, xVal, DataSet.Rounding.CLOSEST));
            }
        }
        return this.mHighlightBuffer;
    }

    protected List<Highlight> buildHighlights(IDataSet set, int dataSetIndex, float xVal, float yVal, DataSet.Rounding rounding) {
        Entry entryForXValue;
        ArrayList arrayList = new ArrayList();
        List<Entry> entriesForXValue = set.getEntriesForXValue(xVal);
        if (entriesForXValue.size() == 0 && (entryForXValue = set.getEntryForXValue(xVal, Float.NaN, rounding)) != null) {
            entriesForXValue = set.getEntriesForXValue(entryForXValue.getX());
        }
        if (entriesForXValue.size() == 0) {
            return arrayList;
        }
        for (Entry entry : entriesForXValue) {
            MPPointD pixelForValues = ((BarDataProvider) this.mChart).getTransformer(set.getAxisDependency()).getPixelForValues(entry.getX(), entry.getY());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, dataSetIndex, set.getAxisDependency()));
        }
        return arrayList;
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter
    protected List<Highlight> buildHighlights(IDataSet set, int dataSetIndex, float xVal, DataSet.Rounding rounding) {
        Entry entryForXValue;
        ArrayList arrayList = new ArrayList();
        List<Entry> entriesForXValue = set.getEntriesForXValue(xVal);
        if (entriesForXValue.size() == 0 && (entryForXValue = set.getEntryForXValue(xVal, Float.NaN, rounding)) != null) {
            entriesForXValue = set.getEntriesForXValue(entryForXValue.getX());
        }
        if (entriesForXValue.size() == 0) {
            return arrayList;
        }
        for (Entry entry : entriesForXValue) {
            MPPointD pixelForValues = ((BarDataProvider) this.mChart).getTransformer(set.getAxisDependency()).getPixelForValues(entry.getX(), entry.getY());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, dataSetIndex, set.getAxisDependency()));
        }
        return arrayList;
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.BarHighlighter, com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter, com.yucheng.smarthealthpro.customchart.highlight.IHighlighter
    public Highlight getHighlight(float x, float y) {
        MPPointD valsForTouch = getValsForTouch(x, y);
        float f2 = (float) valsForTouch.x;
        float f3 = (float) valsForTouch.y;
        MPPointD.recycleInstance(valsForTouch);
        Highlight highlight = getHighlight(f2, f3, x, y);
        if (highlight == null) {
            return null;
        }
        IBarDataSet iBarDataSet = (IBarDataSet) ((BarDataProvider) this.mChart).getBarData().getDataSetByIndex(highlight.getDataSetIndex());
        if (iBarDataSet.isStacked()) {
            return getStackedHighlight(highlight, iBarDataSet, (float) valsForTouch.x, (float) valsForTouch.y);
        }
        MPPointD.recycleInstance(valsForTouch);
        return highlight;
    }
}
