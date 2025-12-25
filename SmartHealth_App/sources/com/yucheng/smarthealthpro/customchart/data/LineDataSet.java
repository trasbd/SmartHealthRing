package com.yucheng.smarthealthpro.customchart.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.formatter.DefaultFillFormatter;
import com.yucheng.smarthealthpro.customchart.formatter.IFillFormatter;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;
import com.yucheng.smarthealthpro.customchart.utils.ColorTemplate;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class LineDataSet extends LineRadarDataSet<Entry> implements ILineDataSet {
    private List<Integer> mCircleColors;
    private int mCircleHoleColor;
    private float mCircleHoleRadius;
    private float mCircleRadius;
    private float mCubicIntensity;
    private DashPathEffect mDashPathEffect;
    private boolean mDrawCircleHole;
    private boolean mDrawCircles;
    private IFillFormatter mFillFormatter;
    private Mode mMode;

    public enum Mode {
        LINEAR,
        STEPPED,
        CUBIC_BEZIER,
        HORIZONTAL_BEZIER,
        SLEEP_MODE
    }

    public LineDataSet(List<Entry> yVals, String label) {
        super(yVals, label);
        this.mMode = Mode.LINEAR;
        this.mCircleColors = null;
        this.mCircleHoleColor = -1;
        this.mCircleRadius = 8.0f;
        this.mCircleHoleRadius = 4.0f;
        this.mCubicIntensity = 0.2f;
        this.mDashPathEffect = null;
        this.mFillFormatter = new DefaultFillFormatter();
        this.mDrawCircles = false;
        this.mDrawCircleHole = true;
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
        this.mCircleColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
    }

    @Override // com.yucheng.smarthealthpro.customchart.data.DataSet
    public DataSet<Entry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mEntries.size(); i2++) {
            arrayList.add(((Entry) this.mEntries.get(i2)).copy());
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, getLabel());
        copy(lineDataSet);
        return lineDataSet;
    }

    protected void copy(LineDataSet lineDataSet) {
        super.copy((LineRadarDataSet) lineDataSet);
        lineDataSet.mCircleColors = this.mCircleColors;
        lineDataSet.mCircleHoleColor = this.mCircleHoleColor;
        lineDataSet.mCircleHoleRadius = this.mCircleHoleRadius;
        lineDataSet.mCircleRadius = this.mCircleRadius;
        lineDataSet.mCubicIntensity = this.mCubicIntensity;
        lineDataSet.mDashPathEffect = this.mDashPathEffect;
        lineDataSet.mDrawCircleHole = this.mDrawCircleHole;
        lineDataSet.mDrawCircles = this.mDrawCircleHole;
        lineDataSet.mFillFormatter = this.mFillFormatter;
        lineDataSet.mMode = this.mMode;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public Mode getMode() {
        return this.mMode;
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
    }

    public void setCubicIntensity(float intensity) {
        if (intensity > 1.0f) {
            intensity = 1.0f;
        }
        if (intensity < 0.05f) {
            intensity = 0.05f;
        }
        this.mCubicIntensity = intensity;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public float getCubicIntensity() {
        return this.mCubicIntensity;
    }

    public void setCircleRadius(float radius) {
        if (radius >= 1.0f) {
            this.mCircleRadius = Utils.convertDpToPixel(radius);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 1");
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public float getCircleRadius() {
        return this.mCircleRadius;
    }

    public void setCircleHoleRadius(float holeRadius) {
        if (holeRadius >= 0.5f) {
            this.mCircleHoleRadius = Utils.convertDpToPixel(holeRadius);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 0.5");
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public float getCircleHoleRadius() {
        return this.mCircleHoleRadius;
    }

    @Deprecated
    public void setCircleSize(float size) {
        setCircleRadius(size);
    }

    @Deprecated
    public float getCircleSize() {
        return getCircleRadius();
    }

    public void enableDashedLine(float lineLength, float spaceLength, float phase) {
        this.mDashPathEffect = new DashPathEffect(new float[]{lineLength, spaceLength}, phase);
    }

    public void disableDashedLine() {
        this.mDashPathEffect = null;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public void setDrawCircles(boolean enabled) {
        this.mDrawCircles = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public boolean isDrawCirclesEnabled() {
        return this.mDrawCircles;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    @Deprecated
    public boolean isDrawCubicEnabled() {
        return this.mMode == Mode.CUBIC_BEZIER;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    @Deprecated
    public boolean isDrawSteppedEnabled() {
        return this.mMode == Mode.STEPPED;
    }

    public List<Integer> getCircleColors() {
        return this.mCircleColors;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public int getCircleColor(int index) {
        return this.mCircleColors.get(index).intValue();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public int getCircleColorCount() {
        return this.mCircleColors.size();
    }

    public void setCircleColors(List<Integer> colors) {
        this.mCircleColors = colors;
    }

    public void setCircleColors(int... colors) {
        this.mCircleColors = ColorTemplate.createColors(colors);
    }

    public void setCircleColors(int[] colors, Context c2) {
        List<Integer> arrayList = this.mCircleColors;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.clear();
        for (int i2 : colors) {
            arrayList.add(Integer.valueOf(c2.getResources().getColor(i2)));
        }
        this.mCircleColors = arrayList;
    }

    public void setCircleColor(int color) {
        resetCircleColors();
        this.mCircleColors.add(Integer.valueOf(color));
    }

    public void resetCircleColors() {
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
    }

    public void setCircleHoleColor(int color) {
        this.mCircleHoleColor = color;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public int getCircleHoleColor() {
        return this.mCircleHoleColor;
    }

    public void setDrawCircleHole(boolean enabled) {
        this.mDrawCircleHole = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public boolean isDrawCircleHoleEnabled() {
        return this.mDrawCircleHole;
    }

    public void setFillFormatter(IFillFormatter formatter) {
        if (formatter == null) {
            this.mFillFormatter = new DefaultFillFormatter();
        } else {
            this.mFillFormatter = formatter;
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet
    public IFillFormatter getFillFormatter() {
        return this.mFillFormatter;
    }
}
