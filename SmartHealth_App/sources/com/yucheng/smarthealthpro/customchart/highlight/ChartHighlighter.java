package com.yucheng.smarthealthpro.customchart.highlight;

import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarLineScatterCandleBubbleData;
import com.yucheng.smarthealthpro.customchart.data.DataSet;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider> implements IHighlighter {
    protected T mChart;
    protected List<Highlight> mHighlightBuffer = new ArrayList();

    public ChartHighlighter(T chart) {
        this.mChart = chart;
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.IHighlighter
    public Highlight getHighlight(float x, float y) {
        MPPointD valsForTouch = getValsForTouch(x, y);
        float f2 = (float) valsForTouch.x;
        MPPointD.recycleInstance(valsForTouch);
        return getHighlightForX(f2, x, y);
    }

    protected MPPointD getValsForTouch(float x, float y) {
        return this.mChart.getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(x, y);
    }

    protected Highlight getHighlightForX(float xVal, float x, float y) {
        List<Highlight> highlightsAtXValue = getHighlightsAtXValue(xVal, x, y);
        if (highlightsAtXValue.isEmpty()) {
            return null;
        }
        return getClosestHighlightByPixel(highlightsAtXValue, x, y, getMinimumDistance(highlightsAtXValue, y, YAxis.AxisDependency.LEFT) < getMinimumDistance(highlightsAtXValue, y, YAxis.AxisDependency.RIGHT) ? YAxis.AxisDependency.LEFT : YAxis.AxisDependency.RIGHT, this.mChart.getMaxHighlightDistance());
    }

    protected float getMinimumDistance(List<Highlight> closestValues, float pos, YAxis.AxisDependency axis) {
        float f2 = Float.MAX_VALUE;
        for (int i2 = 0; i2 < closestValues.size(); i2++) {
            Highlight highlight = closestValues.get(i2);
            if (highlight.getAxis() == axis) {
                float fAbs = Math.abs(getHighlightPos(highlight) - pos);
                if (fAbs < f2) {
                    f2 = fAbs;
                }
            }
        }
        return f2;
    }

    protected float getHighlightPos(Highlight h2) {
        return h2.getYPx();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet] */
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
            MPPointD pixelForValues = this.mChart.getTransformer(set.getAxisDependency()).getPixelForValues(entry.getX(), entry.getY());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, dataSetIndex, set.getAxisDependency()));
        }
        return arrayList;
    }

    public Highlight getClosestHighlightByPixel(List<Highlight> closestValues, float x, float y, YAxis.AxisDependency axis, float minSelectionDistance) {
        Highlight highlight = null;
        for (int i2 = 0; i2 < closestValues.size(); i2++) {
            Highlight highlight2 = closestValues.get(i2);
            if (axis == null || highlight2.getAxis() == axis) {
                float distance = getDistance(x, y, highlight2.getXPx(), highlight2.getYPx());
                if (distance < minSelectionDistance) {
                    highlight = highlight2;
                    minSelectionDistance = distance;
                }
            }
        }
        return highlight;
    }

    protected float getDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.hypot(x1 - x2, y1 - y2);
    }

    protected BarLineScatterCandleBubbleData getData() {
        return this.mChart.getData();
    }
}
