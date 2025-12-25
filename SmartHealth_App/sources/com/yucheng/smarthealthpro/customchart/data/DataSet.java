package com.yucheng.smarthealthpro.customchart.data;

import com.yucheng.smarthealthpro.customchart.data.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public abstract class DataSet<T extends Entry> extends BaseDataSet<T> {
    protected List<T> mEntries;
    protected float mXMax;
    protected float mXMin;
    protected float mYMax;
    protected float mYMin;

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }

    public abstract DataSet<T> copy();

    public DataSet(List<T> entries, String label) {
        super(label);
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mEntries = entries;
        if (entries == null) {
            this.mEntries = new ArrayList();
        }
        calcMinMax();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void calcMinMax() {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        List<T> list = this.mEntries;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<T> it2 = this.mEntries.iterator();
        while (it2.hasNext()) {
            calcMinMax(it2.next());
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void calcMinMaxY(float fromX, float toX) {
        int entryIndex;
        int entryIndex2;
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        List<T> list = this.mEntries;
        if (list == null || list.isEmpty() || (entryIndex2 = getEntryIndex(toX, Float.NaN, Rounding.UP)) < (entryIndex = getEntryIndex(fromX, Float.NaN, Rounding.DOWN))) {
            return;
        }
        for (entryIndex = getEntryIndex(fromX, Float.NaN, Rounding.DOWN); entryIndex <= entryIndex2; entryIndex++) {
            calcMinMaxY(this.mEntries.get(entryIndex));
        }
    }

    protected void calcMinMax(T e2) {
        if (e2 == null) {
            return;
        }
        calcMinMaxX(e2);
        calcMinMaxY(e2);
    }

    protected void calcMinMaxX(T e2) {
        if (e2.getX() < this.mXMin) {
            this.mXMin = e2.getX();
        }
        if (e2.getX() > this.mXMax) {
            this.mXMax = e2.getX();
        }
    }

    protected void calcMinMaxY(T e2) {
        if (e2.getY() < this.mYMin) {
            this.mYMin = e2.getY();
        }
        if (e2.getY() > this.mYMax) {
            this.mYMax = e2.getY();
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getEntryCount() {
        return this.mEntries.size();
    }

    @Deprecated
    public List<T> getValues() {
        return this.mEntries;
    }

    public List<T> getEntries() {
        return this.mEntries;
    }

    @Deprecated
    public void setValues(List<T> values) {
        setEntries(values);
    }

    public void setEntries(List<T> entries) {
        this.mEntries = entries;
        notifyDataSetChanged();
    }

    protected void copy(DataSet dataSet) {
        super.copy((BaseDataSet) dataSet);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(toSimpleString());
        for (int i2 = 0; i2 < this.mEntries.size(); i2++) {
            stringBuffer.append(this.mEntries.get(i2).toString() + StringUtils.SPACE);
        }
        return stringBuffer.toString();
    }

    public String toSimpleString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DataSet, label: " + (getLabel() == null ? "" : getLabel()) + ", entries: " + this.mEntries.size() + StringUtils.LF);
        return stringBuffer.toString();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public float getYMin() {
        return this.mYMin;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public float getYMax() {
        return this.mYMax;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public float getXMin() {
        return this.mXMin;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public float getXMax() {
        return this.mXMax;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void addEntryOrdered(T e2) {
        if (e2 == null) {
            return;
        }
        if (this.mEntries == null) {
            this.mEntries = new ArrayList();
        }
        calcMinMax(e2);
        if (this.mEntries.size() > 0) {
            if (this.mEntries.get(r0.size() - 1).getX() > e2.getX()) {
                this.mEntries.add(getEntryIndex(e2.getX(), e2.getY(), Rounding.UP), e2);
                return;
            }
        }
        this.mEntries.add(e2);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void clear() {
        this.mEntries.clear();
        notifyDataSetChanged();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean addEntry(T e2) {
        if (e2 == null) {
            return false;
        }
        List<T> entries = getEntries();
        if (entries == null) {
            entries = new ArrayList<>();
        }
        calcMinMax(e2);
        return entries.add(e2);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean removeEntry(T e2) {
        List<T> list;
        if (e2 == null || (list = this.mEntries) == null) {
            return false;
        }
        boolean zRemove = list.remove(e2);
        if (zRemove) {
            calcMinMax();
        }
        return zRemove;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getEntryIndex(Entry e2) {
        return this.mEntries.indexOf(e2);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public T getEntryForXValue(float xValue, float closestToY, Rounding rounding) {
        int entryIndex = getEntryIndex(xValue, closestToY, rounding);
        if (entryIndex > -1) {
            return this.mEntries.get(entryIndex);
        }
        return null;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public T getEntryForXValue(float f2, float f3) {
        return (T) getEntryForXValue(f2, f3, Rounding.CLOSEST);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public T getEntryForIndex(int index) {
        return this.mEntries.get(index);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getEntryIndex(float xValue, float closestToY, Rounding rounding) {
        int i2;
        T t;
        List<T> list = this.mEntries;
        if (list == null || list.isEmpty()) {
            return -1;
        }
        int size = this.mEntries.size() - 1;
        int i3 = 0;
        while (i3 < size) {
            int i4 = (i3 + size) / 2;
            float x = this.mEntries.get(i4).getX() - xValue;
            int i5 = i4 + 1;
            float x2 = this.mEntries.get(i5).getX() - xValue;
            float fAbs = Math.abs(x);
            float fAbs2 = Math.abs(x2);
            if (fAbs2 >= fAbs) {
                if (fAbs >= fAbs2) {
                    double d2 = x;
                    if (d2 < 0.0d) {
                        if (d2 < 0.0d) {
                        }
                    }
                }
                size = i4;
            }
            i3 = i5;
        }
        if (size == -1) {
            return size;
        }
        float x3 = this.mEntries.get(size).getX();
        if (rounding == Rounding.UP) {
            if (x3 < xValue && size < this.mEntries.size() - 1) {
                size++;
            }
        } else if (rounding == Rounding.DOWN && x3 > xValue && size > 0) {
            size--;
        }
        if (Float.isNaN(closestToY)) {
            return size;
        }
        while (size > 0 && this.mEntries.get(size - 1).getX() == x3) {
            size--;
        }
        float y = this.mEntries.get(size).getY();
        loop2: while (true) {
            i2 = size;
            do {
                size++;
                if (size >= this.mEntries.size()) {
                    break loop2;
                }
                t = this.mEntries.get(size);
                if (t.getX() != x3) {
                    break loop2;
                }
            } while (Math.abs(t.getY() - closestToY) > Math.abs(y - closestToY));
            y = closestToY;
        }
        return i2;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public List<T> getEntriesForXValue(float xValue) {
        ArrayList arrayList = new ArrayList();
        int size = this.mEntries.size() - 1;
        int i2 = 0;
        while (true) {
            if (i2 > size) {
                break;
            }
            int i3 = (size + i2) / 2;
            T t = this.mEntries.get(i3);
            if (xValue == t.getX()) {
                while (i3 > 0 && this.mEntries.get(i3 - 1).getX() == xValue) {
                    i3--;
                }
                int size2 = this.mEntries.size();
                while (i3 < size2) {
                    T t2 = this.mEntries.get(i3);
                    if (t2.getX() != xValue) {
                        break;
                    }
                    arrayList.add(t2);
                    i3++;
                }
            } else if (xValue > t.getX()) {
                i2 = i3 + 1;
            } else {
                size = i3 - 1;
            }
        }
        return arrayList;
    }
}
