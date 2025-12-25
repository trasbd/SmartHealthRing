package com.yucheng.smarthealthpro.view.chart;

import android.util.Log;
import com.yucheng.smarthealthpro.customchart.data.BarDataSet;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.DataSet;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class PhyDataSet extends BarDataSet {
    public PhyDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet, com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public List<BarEntry> getEntriesForXValue(float xValue) {
        ArrayList arrayList = new ArrayList();
        int size = this.mEntries.size() - 1;
        Log.e("Renderer dataSet", "xValue=====" + xValue + " 0 " + size);
        int i2 = 0;
        while (true) {
            if (i2 > size) {
                break;
            }
            int i3 = (size + i2) / 2;
            SleepItemEntry sleepItemEntry = (SleepItemEntry) this.mEntries.get(i3);
            if (xValue >= sleepItemEntry.beginTime && xValue < sleepItemEntry.endTime) {
                while (i3 > 0 && ((BarEntry) this.mEntries.get(i3 - 1)).getX() == xValue) {
                    i3--;
                }
                int size2 = this.mEntries.size();
                while (i3 < size2) {
                    SleepItemEntry sleepItemEntry2 = (SleepItemEntry) this.mEntries.get(i3);
                    if (sleepItemEntry2.getX() != xValue) {
                        break;
                    }
                    arrayList.add(sleepItemEntry2);
                    i3++;
                }
            } else if (xValue > sleepItemEntry.getX()) {
                i2 = i3 + 1;
            } else {
                size = i3 - 1;
            }
        }
        return arrayList;
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet, com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getEntryIndex(float xValue, float closestToY, DataSet.Rounding rounding) {
        int i2;
        Entry entry;
        if (this.mEntries == null || this.mEntries.isEmpty()) {
            return -1;
        }
        int size = this.mEntries.size() - 1;
        int i3 = 0;
        while (i3 < size) {
            int i4 = (i3 + size) / 2;
            if (xValue > ((SleepItemEntry) this.mEntries.get(i4)).getX() && xValue > r4.endTime) {
                i3 = i4 + 1;
            } else {
                size = i4;
            }
        }
        if (size == -1) {
            return size;
        }
        float x = ((BarEntry) this.mEntries.get(size)).getX();
        if (rounding == DataSet.Rounding.UP) {
            if (x < xValue && size < this.mEntries.size() - 1) {
                size++;
            }
        } else if (rounding == DataSet.Rounding.DOWN && x > xValue && size > 0) {
            size--;
        }
        if (Float.isNaN(closestToY)) {
            return size;
        }
        while (size > 0 && ((BarEntry) this.mEntries.get(size - 1)).getX() == x) {
            size--;
        }
        float y = ((BarEntry) this.mEntries.get(size)).getY();
        loop2: while (true) {
            i2 = size;
            do {
                size++;
                if (size >= this.mEntries.size()) {
                    break loop2;
                }
                entry = (Entry) this.mEntries.get(size);
                if (entry.getX() != x) {
                    break loop2;
                }
            } while (Math.abs(entry.getY() - closestToY) > Math.abs(y - closestToY));
            y = closestToY;
        }
        return i2;
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet, com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public BarEntry getEntryForXValue(float xValue, float closestToY) {
        return (BarEntry) super.getEntryForXValue(xValue, closestToY);
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet, com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public BarEntry getEntryForXValue(float xValue, float closestToY, DataSet.Rounding rounding) {
        return (BarEntry) super.getEntryForXValue(xValue, closestToY, rounding);
    }
}
