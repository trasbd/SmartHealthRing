package com.yucheng.smarthealthpro.view.chart;

import android.util.Log;
import com.yucheng.smarthealthpro.customchart.buffer.BarBuffer;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;

/* loaded from: classes5.dex */
public class PhyBuffer extends BarBuffer {
    public PhyBuffer(int size, int dataSetCount, boolean containsStacks) {
        super(size, dataSetCount, containsStacks);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getColor(IBarDataSet dataSet, int pos) {
        return dataSet.getColor(((SleepItemEntry) dataSet.getEntryForIndex(pos)).type);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.buffer.BarBuffer, com.yucheng.smarthealthpro.customchart.buffer.AbstractBuffer
    public void feed(IBarDataSet data) {
        float fAbs;
        float fAbs2;
        float f2;
        float entryCount = data.getEntryCount() * this.phaseX;
        float f3 = this.mBarWidth / 2.0f;
        for (int i2 = 0; i2 < entryCount; i2++) {
            BarEntry barEntry = (BarEntry) data.getEntryForIndex(i2);
            if (barEntry != null) {
                SleepItemEntry sleepItemEntry = (SleepItemEntry) barEntry;
                float x = sleepItemEntry.getX();
                float y = sleepItemEntry.getY();
                float[] yVals = sleepItemEntry.getYVals();
                if (!this.mContainsStacks || yVals == null) {
                    float f4 = sleepItemEntry.endTime;
                    float f5 = (sleepItemEntry.type * 5) - 5;
                    Log.e("Renderer_sleep", "left:" + x + " top:" + y + " right:" + f4 + " bottom:" + f5 + "\ntype:" + sleepItemEntry.type + " beginTime:" + sleepItemEntry.beginTime + " endTime:" + sleepItemEntry.endTime);
                    addBar(x, y, f4, f5);
                } else {
                    float f6 = -barEntry.getNegativeSum();
                    float f7 = 0.0f;
                    int i3 = 0;
                    while (i3 < yVals.length) {
                        float f8 = yVals[i3];
                        if (f8 == 0.0f && (f7 == 0.0f || f6 == 0.0f)) {
                            fAbs = f8;
                            fAbs2 = f6;
                            f6 = fAbs;
                        } else if (f8 >= 0.0f) {
                            fAbs = f8 + f7;
                            fAbs2 = f6;
                            f6 = f7;
                            f7 = fAbs;
                        } else {
                            fAbs = Math.abs(f8) + f6;
                            fAbs2 = Math.abs(f8) + f6;
                        }
                        float f9 = x - f3;
                        float f10 = x + f3;
                        if (this.mInverted) {
                            f2 = f6 >= fAbs ? f6 : fAbs;
                            if (f6 > fAbs) {
                                f6 = fAbs;
                            }
                        } else {
                            float f11 = f6 >= fAbs ? f6 : fAbs;
                            if (f6 > fAbs) {
                                f6 = fAbs;
                            }
                            float f12 = f6;
                            f6 = f11;
                            f2 = f12;
                        }
                        addBar(f9, f6 * this.phaseY, f10, f2 * this.phaseY);
                        i3++;
                        f6 = fAbs2;
                    }
                }
            }
        }
        reset();
    }
}
