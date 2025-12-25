package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.Paint;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CandleDataSet extends LineScatterCandleRadarDataSet<CandleEntry> implements ICandleDataSet {
    private float mBarSpace;
    protected int mDecreasingColor;
    protected Paint.Style mDecreasingPaintStyle;
    protected int mIncreasingColor;
    protected Paint.Style mIncreasingPaintStyle;
    protected int mNeutralColor;
    protected int mShadowColor;
    private boolean mShadowColorSameAsCandle;
    private float mShadowWidth;
    private boolean mShowCandleBar;

    public CandleDataSet(List<CandleEntry> yVals, String label) {
        super(yVals, label);
        this.mShadowWidth = 3.0f;
        this.mShowCandleBar = true;
        this.mBarSpace = 0.1f;
        this.mShadowColorSameAsCandle = false;
        this.mIncreasingPaintStyle = Paint.Style.STROKE;
        this.mDecreasingPaintStyle = Paint.Style.FILL;
        this.mNeutralColor = 1122868;
        this.mIncreasingColor = 1122868;
        this.mDecreasingColor = 1122868;
        this.mShadowColor = 1122868;
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public DataSet<CandleEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mEntries.size(); i2++) {
            arrayList.add(((CandleEntry) this.mEntries.get(i2)).copy());
        }
        CandleDataSet candleDataSet = new CandleDataSet(arrayList, getLabel());
        copy(candleDataSet);
        return candleDataSet;
    }

    protected void copy(CandleDataSet candleDataSet) {
        super.copy((LineScatterCandleRadarDataSet) candleDataSet);
        candleDataSet.mShadowWidth = this.mShadowWidth;
        candleDataSet.mShowCandleBar = this.mShowCandleBar;
        candleDataSet.mBarSpace = this.mBarSpace;
        candleDataSet.mShadowColorSameAsCandle = this.mShadowColorSameAsCandle;
        candleDataSet.mHighLightColor = this.mHighLightColor;
        candleDataSet.mIncreasingPaintStyle = this.mIncreasingPaintStyle;
        candleDataSet.mDecreasingPaintStyle = this.mDecreasingPaintStyle;
        candleDataSet.mNeutralColor = this.mNeutralColor;
        candleDataSet.mIncreasingColor = this.mIncreasingColor;
        candleDataSet.mDecreasingColor = this.mDecreasingColor;
        candleDataSet.mShadowColor = this.mShadowColor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public void calcMinMax(CandleEntry e2) {
        if (e2.getLow() < this.mYMin) {
            this.mYMin = e2.getLow();
        }
        if (e2.getHigh() > this.mYMax) {
            this.mYMax = e2.getHigh();
        }
        calcMinMaxX(e2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public void calcMinMaxY(CandleEntry e2) {
        if (e2.getHigh() < this.mYMin) {
            this.mYMin = e2.getHigh();
        }
        if (e2.getHigh() > this.mYMax) {
            this.mYMax = e2.getHigh();
        }
        if (e2.getLow() < this.mYMin) {
            this.mYMin = e2.getLow();
        }
        if (e2.getLow() > this.mYMax) {
            this.mYMax = e2.getLow();
        }
    }

    public void setBarSpace(float space) {
        if (space < 0.0f) {
            space = 0.0f;
        }
        if (space > 0.45f) {
            space = 0.45f;
        }
        this.mBarSpace = space;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public float getBarSpace() {
        return this.mBarSpace;
    }

    public void setShadowWidth(float width) {
        this.mShadowWidth = Utils.convertDpToPixel(width);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public float getShadowWidth() {
        return this.mShadowWidth;
    }

    public void setShowCandleBar(boolean showCandleBar) {
        this.mShowCandleBar = showCandleBar;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public boolean getShowCandleBar() {
        return this.mShowCandleBar;
    }

    public void setNeutralColor(int color) {
        this.mNeutralColor = color;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public int getNeutralColor() {
        return this.mNeutralColor;
    }

    public void setIncreasingColor(int color) {
        this.mIncreasingColor = color;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public int getIncreasingColor() {
        return this.mIncreasingColor;
    }

    public void setDecreasingColor(int color) {
        this.mDecreasingColor = color;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public int getDecreasingColor() {
        return this.mDecreasingColor;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public Paint.Style getIncreasingPaintStyle() {
        return this.mIncreasingPaintStyle;
    }

    public void setIncreasingPaintStyle(Paint.Style paintStyle) {
        this.mIncreasingPaintStyle = paintStyle;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public Paint.Style getDecreasingPaintStyle() {
        return this.mDecreasingPaintStyle;
    }

    public void setDecreasingPaintStyle(Paint.Style decreasingPaintStyle) {
        this.mDecreasingPaintStyle = decreasingPaintStyle;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public int getShadowColor() {
        return this.mShadowColor;
    }

    public void setShadowColor(int shadowColor) {
        this.mShadowColor = shadowColor;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet
    public boolean getShadowColorSameAsCandle() {
        return this.mShadowColorSameAsCandle;
    }

    public void setShadowColorSameAsCandle(boolean shadowColorSameAsCandle) {
        this.mShadowColorSameAsCandle = shadowColorSameAsCandle;
    }
}
