package com.yucheng.smarthealthpro.customchart.buffer;

import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;

/* loaded from: classes4.dex */
public class BarBuffer extends AbstractBuffer<IBarDataSet> {
    protected float mBarWidth;
    protected boolean mContainsStacks;
    protected int mDataSetCount;
    protected int mDataSetIndex;
    protected boolean mInverted;

    public BarBuffer(int size, int dataSetCount, boolean containsStacks) {
        super(size);
        this.mDataSetIndex = 0;
        this.mInverted = false;
        this.mBarWidth = 1.0f;
        this.mDataSetCount = dataSetCount;
        this.mContainsStacks = containsStacks;
    }

    public void setBarWidth(float barWidth) {
        this.mBarWidth = barWidth;
    }

    public void setDataSet(int index) {
        this.mDataSetIndex = index;
    }

    public void setInverted(boolean inverted) {
        this.mInverted = inverted;
    }

    protected void addBar(float left, float top, float right, float bottom) {
        float[] fArr = this.buffer;
        int i2 = this.index;
        this.index = i2 + 1;
        fArr[i2] = left;
        float[] fArr2 = this.buffer;
        int i3 = this.index;
        this.index = i3 + 1;
        fArr2[i3] = top;
        float[] fArr3 = this.buffer;
        int i4 = this.index;
        this.index = i4 + 1;
        fArr3[i4] = right;
        float[] fArr4 = this.buffer;
        int i5 = this.index;
        this.index = i5 + 1;
        fArr4[i5] = bottom;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.buffer.AbstractBuffer
    public void feed(IBarDataSet data) {
        float f2;
        float fAbs;
        float fAbs2;
        float f3;
        float entryCount = data.getEntryCount() * this.phaseX;
        float f4 = this.mBarWidth / 2.0f;
        for (int i2 = 0; i2 < entryCount; i2++) {
            BarEntry barEntry = (BarEntry) data.getEntryForIndex(i2);
            if (barEntry != null) {
                float x = barEntry.getX();
                float y = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                if (!this.mContainsStacks || yVals == null) {
                    float f5 = x - f4;
                    float f6 = x + f4;
                    if (this.mInverted) {
                        f2 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                    } else {
                        float f7 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                        float f8 = y;
                        y = f7;
                        f2 = f8;
                    }
                    if (y > 0.0f) {
                        y *= this.phaseY;
                    } else {
                        f2 *= this.phaseY;
                    }
                    addBar(f5, y, f6, f2);
                } else {
                    float f9 = -barEntry.getNegativeSum();
                    float f10 = 0.0f;
                    int i3 = 0;
                    while (i3 < yVals.length) {
                        float f11 = yVals[i3];
                        if (f11 == 0.0f && (f10 == 0.0f || f9 == 0.0f)) {
                            fAbs = f11;
                            fAbs2 = f9;
                            f9 = fAbs;
                        } else if (f11 >= 0.0f) {
                            fAbs = f11 + f10;
                            fAbs2 = f9;
                            f9 = f10;
                            f10 = fAbs;
                        } else {
                            fAbs = Math.abs(f11) + f9;
                            fAbs2 = Math.abs(f11) + f9;
                        }
                        float f12 = x - f4;
                        float f13 = x + f4;
                        if (this.mInverted) {
                            f3 = f9 >= fAbs ? f9 : fAbs;
                            if (f9 > fAbs) {
                                f9 = fAbs;
                            }
                        } else {
                            float f14 = f9 >= fAbs ? f9 : fAbs;
                            if (f9 > fAbs) {
                                f9 = fAbs;
                            }
                            float f15 = f9;
                            f9 = f14;
                            f3 = f15;
                        }
                        addBar(f12, f9 * this.phaseY, f13, f3 * this.phaseY);
                        i3++;
                        f9 = fAbs2;
                    }
                }
            }
        }
        reset();
    }
}
