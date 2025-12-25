package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.Typeface;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class ChartData<T extends IDataSet<? extends Entry>> {
    protected List<T> mDataSets;
    protected float mLeftAxisMax;
    protected float mLeftAxisMin;
    protected float mRightAxisMax;
    protected float mRightAxisMin;
    protected float mXMax;
    protected float mXMin;
    protected float mYMax;
    protected float mYMin;

    public ChartData() {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = new ArrayList();
    }

    public ChartData(T... dataSets) {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = arrayToList(dataSets);
        notifyDataChanged();
    }

    private List<T> arrayToList(T[] array) {
        ArrayList arrayList = new ArrayList();
        for (T t : array) {
            arrayList.add(t);
        }
        return arrayList;
    }

    public ChartData(List<T> sets) {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = sets;
        notifyDataChanged();
    }

    public void notifyDataChanged() {
        calcMinMax();
    }

    public void calcMinMaxY(float fromX, float toX) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().calcMinMaxY(fromX, toX);
        }
        calcMinMax();
    }

    protected void calcMinMax() {
        List<T> list = this.mDataSets;
        if (list == null) {
            return;
        }
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            calcMinMax(it2.next());
        }
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        IDataSet firstLeft = getFirstLeft(this.mDataSets);
        if (firstLeft != null) {
            this.mLeftAxisMax = firstLeft.getYMax();
            this.mLeftAxisMin = firstLeft.getYMin();
            for (T t : this.mDataSets) {
                if (t.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                    if (t.getYMin() < this.mLeftAxisMin) {
                        this.mLeftAxisMin = t.getYMin();
                    }
                    if (t.getYMax() > this.mLeftAxisMax) {
                        this.mLeftAxisMax = t.getYMax();
                    }
                }
            }
        }
        IDataSet firstRight = getFirstRight(this.mDataSets);
        if (firstRight != null) {
            this.mRightAxisMax = firstRight.getYMax();
            this.mRightAxisMin = firstRight.getYMin();
            for (T t2 : this.mDataSets) {
                if (t2.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
                    if (t2.getYMin() < this.mRightAxisMin) {
                        this.mRightAxisMin = t2.getYMin();
                    }
                    if (t2.getYMax() > this.mRightAxisMax) {
                        this.mRightAxisMax = t2.getYMax();
                    }
                }
            }
        }
    }

    public int getDataSetCount() {
        List<T> list = this.mDataSets;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMin(YAxis.AxisDependency axis) {
        if (axis == YAxis.AxisDependency.LEFT) {
            float f2 = this.mLeftAxisMin;
            return f2 == Float.MAX_VALUE ? this.mRightAxisMin : f2;
        }
        float f3 = this.mRightAxisMin;
        return f3 == Float.MAX_VALUE ? this.mLeftAxisMin : f3;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public float getYMax(YAxis.AxisDependency axis) {
        if (axis == YAxis.AxisDependency.LEFT) {
            float f2 = this.mLeftAxisMax;
            return f2 == -3.4028235E38f ? this.mRightAxisMax : f2;
        }
        float f3 = this.mRightAxisMax;
        return f3 == -3.4028235E38f ? this.mLeftAxisMax : f3;
    }

    public float getXMin() {
        return this.mXMin;
    }

    public float getXMax() {
        return this.mXMax;
    }

    public List<T> getDataSets() {
        return this.mDataSets;
    }

    protected int getDataSetIndexByLabel(List<T> dataSets, String label, boolean ignorecase) {
        int i2 = 0;
        if (ignorecase) {
            while (i2 < dataSets.size()) {
                if (label.equalsIgnoreCase(dataSets.get(i2).getLabel())) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        while (i2 < dataSets.size()) {
            if (label.equals(dataSets.get(i2).getLabel())) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public String[] getDataSetLabels() {
        String[] strArr = new String[this.mDataSets.size()];
        for (int i2 = 0; i2 < this.mDataSets.size(); i2++) {
            strArr[i2] = this.mDataSets.get(i2).getLabel();
        }
        return strArr;
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        if (highlight.getDataSetIndex() >= this.mDataSets.size()) {
            return null;
        }
        return this.mDataSets.get(highlight.getDataSetIndex()).getEntryForXValue(highlight.getX(), highlight.getY());
    }

    public T getDataSetByLabel(String label, boolean ignorecase) {
        int dataSetIndexByLabel = getDataSetIndexByLabel(this.mDataSets, label, ignorecase);
        if (dataSetIndexByLabel < 0 || dataSetIndexByLabel >= this.mDataSets.size()) {
            return null;
        }
        return this.mDataSets.get(dataSetIndexByLabel);
    }

    public T getDataSetByIndex(int index) {
        List<T> list = this.mDataSets;
        if (list == null || index < 0 || index >= list.size()) {
            return null;
        }
        return this.mDataSets.get(index);
    }

    public void addDataSet(T d2) {
        if (d2 == null) {
            return;
        }
        calcMinMax(d2);
        this.mDataSets.add(d2);
    }

    public boolean removeDataSet(T d2) {
        if (d2 == null) {
            return false;
        }
        boolean zRemove = this.mDataSets.remove(d2);
        if (zRemove) {
            notifyDataChanged();
        }
        return zRemove;
    }

    public boolean removeDataSet(int index) {
        if (index >= this.mDataSets.size() || index < 0) {
            return false;
        }
        return removeDataSet((ChartData<T>) this.mDataSets.get(index));
    }

    public void addEntry(Entry e2, int dataSetIndex) {
        if (this.mDataSets.size() > dataSetIndex && dataSetIndex >= 0) {
            T t = this.mDataSets.get(dataSetIndex);
            if (t.addEntry(e2)) {
                calcMinMax(e2, t.getAxisDependency());
                return;
            }
            return;
        }
        Log.e("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
    }

    protected void calcMinMax(Entry e2, YAxis.AxisDependency axis) {
        if (this.mYMax < e2.getY()) {
            this.mYMax = e2.getY();
        }
        if (this.mYMin > e2.getY()) {
            this.mYMin = e2.getY();
        }
        if (this.mXMax < e2.getX()) {
            this.mXMax = e2.getX();
        }
        if (this.mXMin > e2.getX()) {
            this.mXMin = e2.getX();
        }
        if (axis == YAxis.AxisDependency.LEFT) {
            if (this.mLeftAxisMax < e2.getY()) {
                this.mLeftAxisMax = e2.getY();
            }
            if (this.mLeftAxisMin > e2.getY()) {
                this.mLeftAxisMin = e2.getY();
                return;
            }
            return;
        }
        if (this.mRightAxisMax < e2.getY()) {
            this.mRightAxisMax = e2.getY();
        }
        if (this.mRightAxisMin > e2.getY()) {
            this.mRightAxisMin = e2.getY();
        }
    }

    protected void calcMinMax(T d2) {
        if (this.mYMax < d2.getYMax()) {
            this.mYMax = d2.getYMax();
        }
        if (this.mYMin > d2.getYMin()) {
            this.mYMin = d2.getYMin();
        }
        if (this.mXMax < d2.getXMax()) {
            this.mXMax = d2.getXMax();
        }
        if (this.mXMin > d2.getXMin()) {
            this.mXMin = d2.getXMin();
        }
        if (d2.getAxisDependency() == YAxis.AxisDependency.LEFT) {
            if (this.mLeftAxisMax < d2.getYMax()) {
                this.mLeftAxisMax = d2.getYMax();
            }
            if (this.mLeftAxisMin > d2.getYMin()) {
                this.mLeftAxisMin = d2.getYMin();
                return;
            }
            return;
        }
        if (this.mRightAxisMax < d2.getYMax()) {
            this.mRightAxisMax = d2.getYMax();
        }
        if (this.mRightAxisMin > d2.getYMin()) {
            this.mRightAxisMin = d2.getYMin();
        }
    }

    public boolean removeEntry(Entry e2, int dataSetIndex) {
        T t;
        if (e2 == null || dataSetIndex >= this.mDataSets.size() || (t = this.mDataSets.get(dataSetIndex)) == null) {
            return false;
        }
        boolean zRemoveEntry = t.removeEntry(e2);
        if (zRemoveEntry) {
            notifyDataChanged();
        }
        return zRemoveEntry;
    }

    public boolean removeEntry(float xValue, int dataSetIndex) {
        Entry entryForXValue;
        if (dataSetIndex < this.mDataSets.size() && (entryForXValue = this.mDataSets.get(dataSetIndex).getEntryForXValue(xValue, Float.NaN)) != null) {
            return removeEntry(entryForXValue, dataSetIndex);
        }
        return false;
    }

    public T getDataSetForEntry(Entry e2) {
        if (e2 == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.mDataSets.size(); i2++) {
            T t = this.mDataSets.get(i2);
            for (int i3 = 0; i3 < t.getEntryCount(); i3++) {
                if (e2.equalTo(t.getEntryForXValue(e2.getX(), e2.getY()))) {
                    return t;
                }
            }
        }
        return null;
    }

    public int[] getColors() {
        if (this.mDataSets == null) {
            return null;
        }
        int size = 0;
        for (int i2 = 0; i2 < this.mDataSets.size(); i2++) {
            size += this.mDataSets.get(i2).getColors().size();
        }
        int[] iArr = new int[size];
        int i3 = 0;
        for (int i4 = 0; i4 < this.mDataSets.size(); i4++) {
            Iterator<Integer> it2 = this.mDataSets.get(i4).getColors().iterator();
            while (it2.hasNext()) {
                iArr[i3] = it2.next().intValue();
                i3++;
            }
        }
        return iArr;
    }

    public int getIndexOfDataSet(T dataSet) {
        return this.mDataSets.indexOf(dataSet);
    }

    protected T getFirstLeft(List<T> sets) {
        for (T t : sets) {
            if (t.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                return t;
            }
        }
        return null;
    }

    public T getFirstRight(List<T> sets) {
        for (T t : sets) {
            if (t.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
                return t;
            }
        }
        return null;
    }

    public void setValueFormatter(IValueFormatter f2) {
        if (f2 == null) {
            return;
        }
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().setValueFormatter(f2);
        }
    }

    public void setValueTextColor(int color) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().setValueTextColor(color);
        }
    }

    public void setValueTextColors(List<Integer> colors) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().setValueTextColors(colors);
        }
    }

    public void setValueTypeface(Typeface tf) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().setValueTypeface(tf);
        }
    }

    public void setValueTextSize(float size) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().setValueTextSize(size);
        }
    }

    public void setDrawValues(boolean enabled) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().setDrawValues(enabled);
        }
    }

    public void setHighlightEnabled(boolean enabled) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            it2.next().setHighlightEnabled(enabled);
        }
    }

    public boolean isHighlightEnabled() {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            if (!it2.next().isHighlightEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void clearValues() {
        List<T> list = this.mDataSets;
        if (list != null) {
            list.clear();
        }
        notifyDataChanged();
    }

    public boolean contains(T dataSet) {
        Iterator<T> it2 = this.mDataSets.iterator();
        while (it2.hasNext()) {
            if (it2.next().equals(dataSet)) {
                return true;
            }
        }
        return false;
    }

    public int getEntryCount() {
        Iterator<T> it2 = this.mDataSets.iterator();
        int entryCount = 0;
        while (it2.hasNext()) {
            entryCount += it2.next().getEntryCount();
        }
        return entryCount;
    }

    public T getMaxEntryCountSet() {
        List<T> list = this.mDataSets;
        if (list == null || list.isEmpty()) {
            return null;
        }
        T t = this.mDataSets.get(0);
        for (T t2 : this.mDataSets) {
            if (t2.getEntryCount() > t.getEntryCount()) {
                t = t2;
            }
        }
        return t;
    }
}
