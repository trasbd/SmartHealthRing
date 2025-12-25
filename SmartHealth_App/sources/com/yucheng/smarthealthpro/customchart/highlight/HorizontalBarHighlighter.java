package com.yucheng.smarthealthpro.customchart.highlight;

import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.DataSet;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class HorizontalBarHighlighter extends BarHighlighter {
    public HorizontalBarHighlighter(BarDataProvider chart) {
        super(chart);
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.BarHighlighter, com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter, com.yucheng.smarthealthpro.customchart.highlight.IHighlighter
    public Highlight getHighlight(float x, float y) {
        BarData barData = ((BarDataProvider) this.mChart).getBarData();
        MPPointD valsForTouch = getValsForTouch(y, x);
        Highlight highlightForX = getHighlightForX((float) valsForTouch.y, y, x);
        if (highlightForX == null) {
            return null;
        }
        IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlightForX.getDataSetIndex());
        if (iBarDataSet.isStacked()) {
            return getStackedHighlight(highlightForX, iBarDataSet, (float) valsForTouch.y, (float) valsForTouch.x);
        }
        MPPointD.recycleInstance(valsForTouch);
        return highlightForX;
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
            MPPointD pixelForValues = ((BarDataProvider) this.mChart).getTransformer(set.getAxisDependency()).getPixelForValues(entry.getY(), entry.getX());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, dataSetIndex, set.getAxisDependency()));
        }
        return arrayList;
    }

    @Override // com.yucheng.smarthealthpro.customchart.highlight.BarHighlighter, com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter
    protected float getDistance(float x1, float y1, float x2, float y2) {
        return Math.abs(y1 - y2);
    }
}
