package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.drawable.Drawable;
import com.yucheng.smarthealthpro.customchart.highlight.Range;

/* loaded from: classes4.dex */
public class BarEntry extends Entry {
    private float mNegativeSum;
    private float mPositiveSum;
    private Range[] mRanges;
    private float[] mYVals;

    public BarEntry(float x, float y) {
        super(x, y);
    }

    public BarEntry(float x, float y, Object data) {
        super(x, y, data);
    }

    public BarEntry(float x, float y, Drawable icon) {
        super(x, y, icon);
    }

    public BarEntry(float x, float y, Drawable icon, Object data) {
        super(x, y, icon, data);
    }

    public BarEntry(float x, float[] vals) {
        super(x, calcSum(vals));
        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float x, float[] vals, Object data) {
        super(x, calcSum(vals), data);
        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float x, float[] vals, Drawable icon) {
        super(x, calcSum(vals), icon);
        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float x, float[] vals, Drawable icon, Object data) {
        super(x, calcSum(vals), icon, data);
        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.Entry
    public BarEntry copy() {
        BarEntry barEntry = new BarEntry(getX(), getY(), getData());
        barEntry.setVals(this.mYVals);
        return barEntry;
    }

    public float[] getYVals() {
        return this.mYVals;
    }

    public void setVals(float[] vals) {
        setY(calcSum(vals));
        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.BaseEntry
    public float getY() {
        return super.getY();
    }

    public Range[] getRanges() {
        return this.mRanges;
    }

    public boolean isStacked() {
        return this.mYVals != null;
    }

    @Deprecated
    public float getBelowSum(int stackIndex) {
        return getSumBelow(stackIndex);
    }

    public float getSumBelow(int stackIndex) {
        float[] fArr = this.mYVals;
        float f2 = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (int length = fArr.length - 1; length > stackIndex && length >= 0; length--) {
            f2 += this.mYVals[length];
        }
        return f2;
    }

    public float getPositiveSum() {
        return this.mPositiveSum;
    }

    public float getNegativeSum() {
        return this.mNegativeSum;
    }

    private void calcPosNegSum() {
        float[] fArr = this.mYVals;
        if (fArr == null) {
            this.mNegativeSum = 0.0f;
            this.mPositiveSum = 0.0f;
            return;
        }
        float fAbs = 0.0f;
        float f2 = 0.0f;
        for (float f3 : fArr) {
            if (f3 <= 0.0f) {
                fAbs += Math.abs(f3);
            } else {
                f2 += f3;
            }
        }
        this.mNegativeSum = fAbs;
        this.mPositiveSum = f2;
    }

    private static float calcSum(float[] vals) {
        float f2 = 0.0f;
        if (vals == null) {
            return 0.0f;
        }
        for (float f3 : vals) {
            f2 += f3;
        }
        return f2;
    }

    protected void calcRanges() {
        float[] yVals = getYVals();
        if (yVals == null || yVals.length == 0) {
            return;
        }
        this.mRanges = new Range[yVals.length];
        float f2 = -getNegativeSum();
        int i2 = 0;
        float f3 = 0.0f;
        while (true) {
            Range[] rangeArr = this.mRanges;
            if (i2 >= rangeArr.length) {
                return;
            }
            float f4 = yVals[i2];
            if (f4 < 0.0f) {
                float f5 = f2 - f4;
                rangeArr[i2] = new Range(f2, f5);
                f2 = f5;
            } else {
                float f6 = f4 + f3;
                rangeArr[i2] = new Range(f3, f6);
                f3 = f6;
            }
            i2++;
        }
    }
}
