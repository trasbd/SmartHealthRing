package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> implements ILineRadarDataSet<T> {
    public int dpColor;
    public int lightColor;
    private boolean mDrawFilled;
    private int mFillAlpha;
    private int mFillColor;
    protected Drawable mFillDrawable;
    private float mLineWidth;
    public int napsColor;
    public int remColor;
    public int type;

    public LineRadarDataSet(List<T> yVals, String label) {
        super(yVals, label);
        this.mFillColor = Color.rgb(140, 234, 255);
        this.mFillAlpha = 85;
        this.mLineWidth = 2.5f;
        this.mDrawFilled = false;
        this.type = 0;
        this.dpColor = Color.parseColor("#8D57FA");
        this.lightColor = Color.parseColor("#80C6FC");
        this.remColor = Color.parseColor("#FC8F5F");
        this.napsColor = Color.parseColor("#1600DE");
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public int getType() {
        return this.type;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public int getDPColor() {
        return this.dpColor;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public int getLightColor() {
        return this.lightColor;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public int getRemColor() {
        return this.remColor;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public int getNapsColor() {
        return this.napsColor;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public int getFillColor() {
        return this.mFillColor;
    }

    public void setFillColor(int color) {
        this.mFillColor = color;
        this.mFillDrawable = null;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public Drawable getFillDrawable() {
        return this.mFillDrawable;
    }

    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public int getFillAlpha() {
        return this.mFillAlpha;
    }

    public void setFillAlpha(int alpha) {
        this.mFillAlpha = alpha;
    }

    public void setLineWidth(float width) {
        if (width < 0.0f) {
            width = 0.0f;
        }
        if (width > 10.0f) {
            width = 10.0f;
        }
        this.mLineWidth = Utils.convertDpToPixel(width);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public float getLineWidth() {
        return this.mLineWidth;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public void setDrawFilled(boolean filled) {
        this.mDrawFilled = filled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineRadarDataSet
    public boolean isDrawFilledEnabled() {
        return this.mDrawFilled;
    }

    protected void copy(LineRadarDataSet lineRadarDataSet) {
        super.copy((LineScatterCandleRadarDataSet) lineRadarDataSet);
        lineRadarDataSet.mDrawFilled = this.mDrawFilled;
        lineRadarDataSet.mFillAlpha = this.mFillAlpha;
        lineRadarDataSet.mFillColor = this.mFillColor;
        lineRadarDataSet.mFillDrawable = this.mFillDrawable;
        lineRadarDataSet.mLineWidth = this.mLineWidth;
    }
}
