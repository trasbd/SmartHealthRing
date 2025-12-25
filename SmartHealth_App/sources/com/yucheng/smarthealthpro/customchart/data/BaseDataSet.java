package com.yucheng.smarthealthpro.customchart.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.yucheng.smarthealthpro.customchart.components.Legend;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.utils.ColorTemplate;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class BaseDataSet<T extends Entry> implements IDataSet<T> {
    protected YAxis.AxisDependency mAxisDependency;
    protected List<Integer> mColors;
    protected boolean mDrawIcons;
    protected boolean mDrawValues;
    private Legend.LegendForm mForm;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    protected boolean mHighlightEnabled;
    protected MPPointF mIconsOffset;
    private String mLabel;
    protected List<Integer> mValueColors;
    protected transient IValueFormatter mValueFormatter;
    protected float mValueTextSize;
    protected Typeface mValueTypeface;
    protected boolean mVisible;

    public BaseDataSet() {
        this.mColors = null;
        this.mValueColors = null;
        this.mLabel = "DataSet";
        this.mAxisDependency = YAxis.AxisDependency.LEFT;
        this.mHighlightEnabled = true;
        this.mForm = Legend.LegendForm.DEFAULT;
        this.mFormSize = Float.NaN;
        this.mFormLineWidth = Float.NaN;
        this.mFormLineDashEffect = null;
        this.mDrawValues = true;
        this.mDrawIcons = true;
        this.mIconsOffset = new MPPointF();
        this.mValueTextSize = 17.0f;
        this.mVisible = true;
        this.mColors = new ArrayList();
        this.mValueColors = new ArrayList();
        this.mColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
        this.mValueColors.add(Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
    }

    public BaseDataSet(String label) {
        this();
        this.mLabel = label;
    }

    public void notifyDataSetChanged() {
        calcMinMax();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public List<Integer> getColors() {
        return this.mColors;
    }

    public List<Integer> getValueColors() {
        return this.mValueColors;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getColor() {
        return this.mColors.get(0).intValue();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getColor(int index) {
        List<Integer> list = this.mColors;
        return list.get(index % list.size()).intValue();
    }

    public void setColors(List<Integer> colors) {
        this.mColors = colors;
    }

    public void setColors(int... colors) {
        this.mColors = ColorTemplate.createColors(colors);
    }

    public void setColors(int[] colors, Context c2) {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.clear();
        for (int i2 : colors) {
            this.mColors.add(Integer.valueOf(c2.getResources().getColor(i2)));
        }
    }

    public void addColor(int color) {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.add(Integer.valueOf(color));
    }

    public void setColor(int color) {
        resetColors();
        this.mColors.add(Integer.valueOf(color));
    }

    public void setColor(int color, int alpha) {
        setColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
    }

    public void setColors(int[] colors, int alpha) {
        resetColors();
        for (int i2 : colors) {
            addColor(Color.argb(alpha, Color.red(i2), Color.green(i2), Color.blue(i2)));
        }
    }

    public void resetColors() {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.clear();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setLabel(String label) {
        this.mLabel = label;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public String getLabel() {
        return this.mLabel;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setHighlightEnabled(boolean enabled) {
        this.mHighlightEnabled = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean isHighlightEnabled() {
        return this.mHighlightEnabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setValueFormatter(IValueFormatter f2) {
        if (f2 == null) {
            return;
        }
        this.mValueFormatter = f2;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public IValueFormatter getValueFormatter() {
        if (needsFormatter()) {
            return Utils.getDefaultValueFormatter();
        }
        return this.mValueFormatter;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean needsFormatter() {
        return this.mValueFormatter == null;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setValueTextColor(int color) {
        this.mValueColors.clear();
        this.mValueColors.add(Integer.valueOf(color));
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setValueTextColors(List<Integer> colors) {
        this.mValueColors = colors;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setValueTypeface(Typeface tf) {
        this.mValueTypeface = tf;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setValueTextSize(float size) {
        this.mValueTextSize = Utils.convertDpToPixel(size);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getValueTextColor() {
        return this.mValueColors.get(0).intValue();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getValueTextColor(int index) {
        List<Integer> list = this.mValueColors;
        return list.get(index % list.size()).intValue();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public Typeface getValueTypeface() {
        return this.mValueTypeface;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public float getValueTextSize() {
        return this.mValueTextSize;
    }

    public void setForm(Legend.LegendForm form) {
        this.mForm = form;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public Legend.LegendForm getForm() {
        return this.mForm;
    }

    public void setFormSize(float formSize) {
        this.mFormSize = formSize;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float formLineWidth) {
        this.mFormLineWidth = formLineWidth;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setDrawValues(boolean enabled) {
        this.mDrawValues = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean isDrawValuesEnabled() {
        return this.mDrawValues;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setDrawIcons(boolean enabled) {
        this.mDrawIcons = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean isDrawIconsEnabled() {
        return this.mDrawIcons;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setIconsOffset(MPPointF offsetDp) {
        this.mIconsOffset.x = offsetDp.x;
        this.mIconsOffset.y = offsetDp.y;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public MPPointF getIconsOffset() {
        return this.mIconsOffset;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setVisible(boolean visible) {
        this.mVisible = visible;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean isVisible() {
        return this.mVisible;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public YAxis.AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public void setAxisDependency(YAxis.AxisDependency dependency) {
        this.mAxisDependency = dependency;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public int getIndexInEntries(int xIndex) {
        for (int i2 = 0; i2 < getEntryCount(); i2++) {
            if (xIndex == getEntryForIndex(i2).getX()) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean removeFirst() {
        if (getEntryCount() > 0) {
            return removeEntry((BaseDataSet<T>) getEntryForIndex(0));
        }
        return false;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean removeLast() {
        if (getEntryCount() > 0) {
            return removeEntry((BaseDataSet<T>) getEntryForIndex(getEntryCount() - 1));
        }
        return false;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean removeEntryByXValue(float xValue) {
        return removeEntry((BaseDataSet<T>) getEntryForXValue(xValue, Float.NaN));
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean removeEntry(int index) {
        return removeEntry((BaseDataSet<T>) getEntryForIndex(index));
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet
    public boolean contains(T e2) {
        for (int i2 = 0; i2 < getEntryCount(); i2++) {
            if (getEntryForIndex(i2).equals(e2)) {
                return true;
            }
        }
        return false;
    }

    protected void copy(BaseDataSet baseDataSet) {
        baseDataSet.mAxisDependency = this.mAxisDependency;
        baseDataSet.mColors = this.mColors;
        baseDataSet.mDrawIcons = this.mDrawIcons;
        baseDataSet.mDrawValues = this.mDrawValues;
        baseDataSet.mForm = this.mForm;
        baseDataSet.mFormLineDashEffect = this.mFormLineDashEffect;
        baseDataSet.mFormLineWidth = this.mFormLineWidth;
        baseDataSet.mFormSize = this.mFormSize;
        baseDataSet.mHighlightEnabled = this.mHighlightEnabled;
        baseDataSet.mIconsOffset = this.mIconsOffset;
        baseDataSet.mValueColors = this.mValueColors;
        baseDataSet.mValueFormatter = this.mValueFormatter;
        baseDataSet.mValueColors = this.mValueColors;
        baseDataSet.mValueTextSize = this.mValueTextSize;
        baseDataSet.mVisible = this.mVisible;
    }
}
