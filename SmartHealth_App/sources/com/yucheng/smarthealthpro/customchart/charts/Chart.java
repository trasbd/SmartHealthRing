package com.yucheng.smarthealthpro.customchart.charts;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.animation.Easing;
import com.yucheng.smarthealthpro.customchart.components.Description;
import com.yucheng.smarthealthpro.customchart.components.IMarker;
import com.yucheng.smarthealthpro.customchart.components.Legend;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.data.ChartData;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.formatter.DefaultValueFormatter;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.highlight.ChartHighlighter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.highlight.IHighlighter;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.klinechart.BloodPressureBean;
import com.yucheng.smarthealthpro.customchart.listener.ChartTouchListener;
import com.yucheng.smarthealthpro.customchart.listener.OnChartGestureListener;
import com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener;
import com.yucheng.smarthealthpro.customchart.renderer.DataRenderer;
import com.yucheng.smarthealthpro.customchart.renderer.LegendRenderer;
import com.yucheng.smarthealthpro.customchart.temperature.NearestUtils;
import com.yucheng.smarthealthpro.customchart.temperature.TemperBean;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import com.yucheng.smarthealthpro.home.activity.phy.bean.PhyDayInfo;
import com.yucheng.smarthealthpro.home.activity.running.bean.StepBean;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>> extends ViewGroup implements ChartInterface {
    public static final String LOG_TAG = "MPAndroidChart";
    public static final int PAINT_CENTER_TEXT = 14;
    public static final int PAINT_DESCRIPTION = 11;
    public static final int PAINT_GRID_BACKGROUND = 4;
    public static final int PAINT_HOLE = 13;
    public static final int PAINT_INFO = 7;
    public static final int PAINT_LEGEND_LABEL = 18;
    protected ChartAnimator mAnimator;
    private List<BloodPressureBean> mBloodPressureBean;
    protected ChartTouchListener mChartTouchListener;
    protected T mData;
    protected DefaultValueFormatter mDefaultValueFormatter;
    protected Paint mDescPaint;
    protected Description mDescription;
    private boolean mDragDecelerationEnabled;
    private float mDragDecelerationFrictionCoef;
    protected boolean mDrawMarkers;
    private float mExtraBottomOffset;
    private float mExtraLeftOffset;
    private float mExtraRightOffset;
    private float mExtraTopOffset;
    private OnChartGestureListener mGestureListener;
    protected boolean mHighLightPerTapEnabled;
    protected IHighlighter mHighlighter;
    protected Highlight[] mIndicesToHighlight;
    protected Paint mInfoPaint;
    protected ArrayList<Runnable> mJobs;
    protected Legend mLegend;
    protected LegendRenderer mLegendRenderer;
    protected boolean mLogEnabled;
    protected IMarker mMarker;
    private MarkerLabel mMarkerLabel;
    protected float mMaxHighlightDistance;
    private String mNoDataText;
    private boolean mOffsetsCalculated;
    private List<PhyDayInfo> mPhyInfo;
    protected DataRenderer mRenderer;
    protected OnChartValueSelectedListener mSelectionListener;
    private List<SleepDayInfo> mSleepInfo;
    private List<StepBean> mStepBean;
    protected boolean mTouchEnabled;
    private boolean mUnbind;
    protected ViewPortHandler mViewPortHandler;
    protected XAxis mXAxis;
    private List<TemperBean> temperBeans;

    public enum MarkerLabel {
        TEMPER_CHART,
        TEMPER_WEEK_MONTH_CHART,
        BLOOD_OXYGEN_DAY_CHART,
        BLOOD_OXYGEN_WEEK_MONTH_CHART,
        BLOOD_SUGAR_DAY_CHART,
        BLOOD_SUGAR_WEEK_MONTH_CHART,
        URIC_ACID_DAY_CHART,
        URIC_ACID_WEEK_MONTH_CHART,
        HEART_RATE_DAY_CHART,
        HEART_RATE_WEEK_MONTH_CHART,
        HRV_DAY_CHART,
        HRV_WEEK_MONTH_CHART,
        RESPIRATORY_RATE_DAY_CHART,
        RESPIRATORY_WEEK_MONTH_CHART,
        BLOOD_PRESSURE_DAY_CHART,
        BLOOD_PRESSURE_WEEK_MONTH_CHART,
        STEP_CHART,
        SLEEP_DAY_CHART,
        SLEEP_WEEK_CHART,
        SLEEP_MONTH_CHART,
        BLOOD_KETONE_CHART,
        BLOOD_KETONE_WEEK_MONTH_CHART,
        PRESSURE_CHART,
        PRESSURE_WEEK_MONTH_CHART
    }

    protected abstract void calcMinMax();

    protected abstract void calculateOffsets();

    public abstract void notifyDataSetChanged();

    public Chart(Context context) {
        super(context);
        this.mLogEnabled = false;
        this.mData = null;
        this.mHighLightPerTapEnabled = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.mDefaultValueFormatter = new DefaultValueFormatter(0);
        this.mTouchEnabled = true;
        this.mNoDataText = "No chart data available.";
        this.mViewPortHandler = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0f;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList<>();
        this.mUnbind = false;
        init();
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLogEnabled = false;
        this.mData = null;
        this.mHighLightPerTapEnabled = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.mDefaultValueFormatter = new DefaultValueFormatter(0);
        this.mTouchEnabled = true;
        this.mNoDataText = "No chart data available.";
        this.mViewPortHandler = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0f;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList<>();
        this.mUnbind = false;
        init();
    }

    public Chart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mLogEnabled = false;
        this.mData = null;
        this.mHighLightPerTapEnabled = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.mDefaultValueFormatter = new DefaultValueFormatter(0);
        this.mTouchEnabled = true;
        this.mNoDataText = "No chart data available.";
        this.mViewPortHandler = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0f;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList<>();
        this.mUnbind = false;
        init();
    }

    protected void init() {
        setWillNotDraw(false);
        this.mAnimator = new ChartAnimator(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.customchart.charts.Chart.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                Chart.this.postInvalidate();
            }
        });
        Utils.init(getContext());
        this.mMaxHighlightDistance = Utils.convertDpToPixel(500.0f);
        this.mDescription = new Description();
        this.mLegend = new Legend();
        this.mLegendRenderer = new LegendRenderer(this.mViewPortHandler, this.mLegend);
        this.mXAxis = new XAxis();
        this.mDescPaint = new Paint(1);
        Paint paint = new Paint(1);
        this.mInfoPaint = paint;
        paint.setColor(Color.rgb(247, Opcodes.ANEWARRAY, 51));
        this.mInfoPaint.setTextAlign(Paint.Align.CENTER);
        this.mInfoPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        if (this.mLogEnabled) {
            Log.i("", "Chart.init()");
        }
    }

    public void setData(T data) {
        this.mData = data;
        this.mOffsetsCalculated = false;
        if (data == null) {
            return;
        }
        setupDefaultFormatter(data.getYMin(), data.getYMax());
        for (IDataSet iDataSet : this.mData.getDataSets()) {
            if (iDataSet.needsFormatter() || iDataSet.getValueFormatter() == this.mDefaultValueFormatter) {
                iDataSet.setValueFormatter(this.mDefaultValueFormatter);
            }
        }
        notifyDataSetChanged();
        if (this.mLogEnabled) {
            Log.i("MPAndroidChart", "Data is set.");
        }
    }

    public void clear() {
        this.mData = null;
        this.mOffsetsCalculated = false;
        this.mIndicesToHighlight = null;
        this.mChartTouchListener.setLastHighlighted(null);
        invalidate();
    }

    public void clearValues() {
        this.mData.clearValues();
        invalidate();
    }

    public boolean isEmpty() {
        T t = this.mData;
        return t == null || t.getEntryCount() <= 0;
    }

    protected void setupDefaultFormatter(float min, float max) {
        float fMax;
        T t = this.mData;
        if (t == null || t.getEntryCount() < 2) {
            fMax = Math.max(Math.abs(min), Math.abs(max));
        } else {
            fMax = Math.abs(max - min);
        }
        this.mDefaultValueFormatter.setup(Utils.getDecimals(fMax));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mData == null) {
            if (TextUtils.isEmpty(this.mNoDataText)) {
                return;
            }
            MPPointF center = getCenter();
            int i2 = AnonymousClass2.$SwitchMap$android$graphics$Paint$Align[this.mInfoPaint.getTextAlign().ordinal()];
            if (i2 == 1) {
                center.x = 0.0f;
                canvas.drawText(this.mNoDataText, center.x, center.y, this.mInfoPaint);
                return;
            } else if (i2 == 2) {
                center.x = (float) (center.x * 2.0d);
                canvas.drawText(this.mNoDataText, center.x, center.y, this.mInfoPaint);
                return;
            } else {
                canvas.drawText(this.mNoDataText, center.x, center.y, this.mInfoPaint);
                return;
            }
        }
        if (this.mOffsetsCalculated) {
            return;
        }
        calculateOffsets();
        this.mOffsetsCalculated = true;
    }

    protected void drawDescription(Canvas c2) {
        float height;
        float width;
        Description description = this.mDescription;
        if (description == null || !description.isEnabled()) {
            return;
        }
        MPPointF position = this.mDescription.getPosition();
        this.mDescPaint.setTypeface(this.mDescription.getTypeface());
        this.mDescPaint.setTextSize(this.mDescription.getTextSize());
        this.mDescPaint.setColor(this.mDescription.getTextColor());
        this.mDescPaint.setTextAlign(this.mDescription.getTextAlign());
        if (position == null) {
            width = (getWidth() - this.mViewPortHandler.offsetRight()) - this.mDescription.getXOffset();
            height = (getHeight() - this.mViewPortHandler.offsetBottom()) - this.mDescription.getYOffset();
        } else {
            float f2 = position.x;
            height = position.y;
            width = f2;
        }
        c2.drawText(this.mDescription.getText(), width, height, this.mDescPaint);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public float getMaxHighlightDistance() {
        return this.mMaxHighlightDistance;
    }

    public void setMaxHighlightDistance(float distDp) {
        this.mMaxHighlightDistance = Utils.convertDpToPixel(distDp);
    }

    public Highlight[] getHighlighted() {
        return this.mIndicesToHighlight;
    }

    public boolean isHighlightPerTapEnabled() {
        return this.mHighLightPerTapEnabled;
    }

    public void setHighlightPerTapEnabled(boolean enabled) {
        this.mHighLightPerTapEnabled = enabled;
    }

    public boolean valuesToHighlight() {
        Highlight[] highlightArr = this.mIndicesToHighlight;
        return (highlightArr == null || highlightArr.length <= 0 || highlightArr[0] == null) ? false : true;
    }

    protected void setLastHighlighted(Highlight[] highs) {
        Highlight highlight;
        if (highs == null || highs.length <= 0 || (highlight = highs[0]) == null) {
            this.mChartTouchListener.setLastHighlighted(null);
        } else {
            this.mChartTouchListener.setLastHighlighted(highlight);
        }
    }

    public void highlightValues(Highlight[] highs) {
        this.mIndicesToHighlight = highs;
        setLastHighlighted(highs);
        invalidate();
    }

    public void highlightValue(float x, int dataSetIndex, int dataIndex) {
        highlightValue(x, dataSetIndex, dataIndex, true);
    }

    public void highlightValue(float x, int dataSetIndex) {
        highlightValue(x, dataSetIndex, -1, true);
    }

    public void highlightValue(float x, float y, int dataSetIndex, int dataIndex) {
        highlightValue(x, y, dataSetIndex, dataIndex, true);
    }

    public void highlightValue(float x, float y, int dataSetIndex) {
        highlightValue(x, y, dataSetIndex, -1, true);
    }

    public void highlightValue(float x, int dataSetIndex, int dataIndex, boolean callListener) {
        highlightValue(x, Float.NaN, dataSetIndex, dataIndex, callListener);
    }

    public void highlightValue(float x, int dataSetIndex, boolean callListener) {
        highlightValue(x, Float.NaN, dataSetIndex, -1, callListener);
    }

    public void highlightValue(float x, float y, int dataSetIndex, int dataIndex, boolean callListener) {
        if (dataSetIndex < 0 || dataSetIndex >= this.mData.getDataSetCount()) {
            highlightValue((Highlight) null, callListener);
        } else {
            highlightValue(new Highlight(x, y, dataSetIndex, dataIndex), callListener);
        }
    }

    public void highlightValue(float x, float y, int dataSetIndex, boolean callListener) {
        highlightValue(x, y, dataSetIndex, -1, callListener);
    }

    public void highlightValue(Highlight highlight) {
        highlightValue(highlight, false);
    }

    public void highlightValue(Highlight high, boolean callListener) {
        Entry entry = null;
        if (high == null) {
            this.mIndicesToHighlight = null;
        } else {
            if (this.mLogEnabled) {
                Log.i("MPAndroidChart", "Highlighted: " + high.toString());
            }
            Entry entryForHighlight = this.mData.getEntryForHighlight(high);
            if (entryForHighlight == null) {
                this.mIndicesToHighlight = null;
                high = null;
            } else {
                this.mIndicesToHighlight = new Highlight[]{high};
            }
            entry = entryForHighlight;
        }
        setLastHighlighted(this.mIndicesToHighlight);
        if (callListener && this.mSelectionListener != null) {
            if (!valuesToHighlight()) {
                this.mSelectionListener.onNothingSelected();
            } else {
                this.mSelectionListener.onValueSelected(entry, high);
            }
        }
        invalidate();
    }

    public Highlight getHighlightByTouchPoint(float x, float y) {
        if (this.mData == null) {
            Log.e("MPAndroidChart", "Can't select by touch. No data set.");
            return null;
        }
        return getHighlighter().getHighlight(x, y);
    }

    public void setOnTouchListener(ChartTouchListener l) {
        this.mChartTouchListener = l;
    }

    public ChartTouchListener getOnTouchListener() {
        return this.mChartTouchListener;
    }

    protected void drawMarkers(Canvas canvas) {
        if (this.mMarker == null || !isDrawMarkersEnabled() || !valuesToHighlight()) {
            return;
        }
        int i2 = 0;
        while (true) {
            Highlight[] highlightArr = this.mIndicesToHighlight;
            if (i2 >= highlightArr.length) {
                return;
            }
            Highlight highlight = highlightArr[i2];
            IDataSet dataSetByIndex = this.mData.getDataSetByIndex(highlight.getDataSetIndex());
            Entry entryForHighlight = this.mData.getEntryForHighlight(this.mIndicesToHighlight[i2]);
            int entryIndex = dataSetByIndex.getEntryIndex(entryForHighlight);
            if (entryForHighlight != null && entryIndex <= dataSetByIndex.getEntryCount() * this.mAnimator.getPhaseX()) {
                float[] markerPosition = getMarkerPosition(highlight);
                if (this.mViewPortHandler.isInBounds(markerPosition[0], markerPosition[1])) {
                    if (this.mMarkerLabel == MarkerLabel.TEMPER_CHART) {
                        List<TemperBean> list = this.temperBeans;
                        if (list != null) {
                            int size = list.size();
                            int[] iArr = new int[size];
                            for (int i3 = 0; i3 < size; i3++) {
                                iArr[i3] = this.temperBeans.get(i3).getTime();
                            }
                            for (int i4 = 0; i4 < this.temperBeans.size(); i4++) {
                                if (entryForHighlight.getX() == new BigDecimal(this.temperBeans.get(i4).getTime()).setScale(1, 4).floatValue()) {
                                    this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                } else {
                                    int numberThree = NearestUtils.getNumberThree(iArr, Integer.valueOf((int) entryForHighlight.getX()));
                                    float f2 = numberThree;
                                    if (Math.abs((int) (f2 - entryForHighlight.getX())) < 60 && this.temperBeans.get(i4).getTime() == numberThree) {
                                        Entry entry = new Entry();
                                        entry.setX(f2);
                                        entry.setY(this.temperBeans.get(i4).getTemper());
                                        if (entryForHighlight.getY() != 0.0f) {
                                            this.mMarker.refreshContentTemp(entry, highlight, this.temperBeans);
                                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.TEMPER_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentTempWeekMohth(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.BLOOD_OXYGEN_DAY_CHART) {
                        List<TemperBean> list2 = this.temperBeans;
                        if (list2 != null) {
                            int size2 = list2.size();
                            int[] iArr2 = new int[size2];
                            for (int i5 = 0; i5 < size2; i5++) {
                                iArr2[i5] = this.temperBeans.get(i5).getTime();
                            }
                            for (int i6 = 0; i6 < this.temperBeans.size(); i6++) {
                                if (entryForHighlight.getX() == new BigDecimal(this.temperBeans.get(i6).getTime()).setScale(1, 4).floatValue()) {
                                    this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                } else {
                                    int numberThree2 = NearestUtils.getNumberThree(iArr2, Integer.valueOf((int) entryForHighlight.getX()));
                                    float f3 = numberThree2;
                                    if (Math.abs((int) (f3 - entryForHighlight.getX())) < 60 && this.temperBeans.get(i6).getTime() == numberThree2) {
                                        Entry entry2 = new Entry();
                                        entry2.setX(f3);
                                        entry2.setY(this.temperBeans.get(i6).getTemper());
                                        if (entryForHighlight.getY() != 0.0f) {
                                            this.mMarker.refreshContentDayBloodOxygen(entry2, highlight, this.temperBeans);
                                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentWeekMonthBloodOxygen(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.BLOOD_SUGAR_DAY_CHART) {
                        List<TemperBean> list3 = this.temperBeans;
                        if (list3 != null) {
                            int size3 = list3.size();
                            int[] iArr3 = new int[size3];
                            for (int i7 = 0; i7 < size3; i7++) {
                                iArr3[i7] = this.temperBeans.get(i7).getTime();
                            }
                            for (int i8 = 0; i8 < this.temperBeans.size(); i8++) {
                                if (entryForHighlight.getX() == new BigDecimal(this.temperBeans.get(i8).getTime()).setScale(1, 4).floatValue()) {
                                    this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                } else {
                                    int numberThree3 = NearestUtils.getNumberThree(iArr3, Integer.valueOf((int) entryForHighlight.getX()));
                                    float f4 = numberThree3;
                                    if (Math.abs((int) (f4 - entryForHighlight.getX())) < 60 && this.temperBeans.get(i8).getTime() == numberThree3) {
                                        Entry entry3 = new Entry();
                                        entry3.setX(f4);
                                        entry3.setY(this.temperBeans.get(i8).getTemper());
                                        if (entryForHighlight.getY() != 0.0f) {
                                            this.mMarker.refreshContentDayBloodSugar(entry3, highlight, this.temperBeans);
                                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentWeekMonthBloodSugar(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.URIC_ACID_DAY_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentDayUricAcid(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.URIC_ACID_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentWeekMonthUricAcid(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.HEART_RATE_DAY_CHART) {
                        List<TemperBean> list4 = this.temperBeans;
                        if (list4 != null) {
                            int size4 = list4.size();
                            int[] iArr4 = new int[size4];
                            for (int i9 = 0; i9 < size4; i9++) {
                                iArr4[i9] = this.temperBeans.get(i9).getTime();
                            }
                            for (int i10 = 0; i10 < this.temperBeans.size(); i10++) {
                                if (entryForHighlight.getX() == new BigDecimal(this.temperBeans.get(i10).getTime()).setScale(1, 4).floatValue()) {
                                    Entry entry4 = new Entry();
                                    entry4.setX(NearestUtils.getNumberThree(iArr4, Integer.valueOf((int) entryForHighlight.getX())));
                                    entry4.setY(this.temperBeans.get(i10).getTemper());
                                    this.mMarker.refreshContentDayHeartRate(entry4, highlight, this.temperBeans);
                                    this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                } else {
                                    int numberThree4 = NearestUtils.getNumberThree(iArr4, Integer.valueOf((int) entryForHighlight.getX()));
                                    float f5 = numberThree4;
                                    if (Math.abs((int) (f5 - entryForHighlight.getX())) < 60 && this.temperBeans.get(i10).getTime() == numberThree4) {
                                        Entry entry5 = new Entry();
                                        entry5.setX(f5);
                                        entry5.setY(this.temperBeans.get(i10).getTemper());
                                        if (entryForHighlight.getY() != 0.0f) {
                                            this.mMarker.refreshContentDayHeartRate(entry5, highlight, this.temperBeans);
                                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.HEART_RATE_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentWeekMonthHeartRate(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.HRV_DAY_CHART) {
                        List<TemperBean> list5 = this.temperBeans;
                        if (list5 != null) {
                            int size5 = list5.size();
                            int[] iArr5 = new int[size5];
                            for (int i11 = 0; i11 < size5; i11++) {
                                iArr5[i11] = this.temperBeans.get(i11).getTime();
                            }
                            for (int i12 = 0; i12 < this.temperBeans.size(); i12++) {
                                if (entryForHighlight.getX() == new BigDecimal(this.temperBeans.get(i12).getTime()).setScale(1, 4).floatValue()) {
                                    Entry entry6 = new Entry();
                                    entry6.setX(NearestUtils.getNumberThree(iArr5, Integer.valueOf((int) entryForHighlight.getX())));
                                    entry6.setY(this.temperBeans.get(i12).getTemper());
                                    this.mMarker.refreshContentDayHRV(entry6, highlight, this.temperBeans);
                                    this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                } else {
                                    int numberThree5 = NearestUtils.getNumberThree(iArr5, Integer.valueOf((int) entryForHighlight.getX()));
                                    float f6 = numberThree5;
                                    if (Math.abs((int) (f6 - entryForHighlight.getX())) < 60 && this.temperBeans.get(i12).getTime() == numberThree5) {
                                        Entry entry7 = new Entry();
                                        entry7.setX(f6);
                                        entry7.setY(this.temperBeans.get(i12).getTemper());
                                        if (entryForHighlight.getY() != 0.0f) {
                                            this.mMarker.refreshContentDayHRV(entry7, highlight, this.temperBeans);
                                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.HRV_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentWeekMonthHRV(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.RESPIRATORY_RATE_DAY_CHART) {
                        List<TemperBean> list6 = this.temperBeans;
                        if (list6 != null) {
                            int size6 = list6.size();
                            int[] iArr6 = new int[size6];
                            for (int i13 = 0; i13 < size6; i13++) {
                                iArr6[i13] = this.temperBeans.get(i13).getTime();
                            }
                            for (int i14 = 0; i14 < this.temperBeans.size(); i14++) {
                                if (entryForHighlight.getX() == new BigDecimal(this.temperBeans.get(i14).getTime()).setScale(1, 4).floatValue()) {
                                    this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                } else {
                                    int numberThree6 = NearestUtils.getNumberThree(iArr6, Integer.valueOf((int) entryForHighlight.getX()));
                                    float f7 = numberThree6;
                                    if (Math.abs((int) (f7 - entryForHighlight.getX())) < 60 && this.temperBeans.get(i14).getTime() == numberThree6) {
                                        Entry entry8 = new Entry();
                                        entry8.setX(f7);
                                        entry8.setY(this.temperBeans.get(i14).getTemper());
                                        if (entryForHighlight.getY() != 0.0f) {
                                            this.mMarker.refreshContentDayRespiratoryRate(entry8, highlight, this.temperBeans);
                                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentWeekMonthRespiratoryRate(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.BLOOD_PRESSURE_DAY_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentDayBloodPressure(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.BLOOD_PRESSURE_WEEK_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentWeekMonthBloodPressure(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.SLEEP_DAY_CHART) {
                        if (entryForHighlight.getY() != 25.0f) {
                            this.mMarker.refreshContentSleep(entryForHighlight, highlight, this.mSleepInfo);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.STEP_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentStep(entryForHighlight, highlight, this.mStepBean);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.SLEEP_WEEK_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentSleepWeekMonth(entryForHighlight, highlight, MarkerLabel.SLEEP_WEEK_CHART);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.SLEEP_MONTH_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentSleepWeekMonth(entryForHighlight, highlight, MarkerLabel.SLEEP_MONTH_CHART);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel == MarkerLabel.PRESSURE_CHART) {
                        if (entryForHighlight.getY() != 0.0f) {
                            this.mMarker.refreshContentDayPressure(entryForHighlight, highlight, this.temperBeans);
                            this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                        }
                    } else if (this.mMarkerLabel != MarkerLabel.PRESSURE_WEEK_MONTH_CHART) {
                        this.mMarker.refreshContent(entryForHighlight, highlight);
                        this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                    } else if (entryForHighlight.getY() != 0.0f) {
                        this.mMarker.refreshContentWeekMonthPressure(entryForHighlight, highlight, this.temperBeans);
                        this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                    }
                }
            }
            i2++;
        }
    }

    protected float[] getMarkerPosition(Highlight high) {
        return new float[]{high.getDrawX(), high.getDrawY()};
    }

    public ChartAnimator getAnimator() {
        return this.mAnimator;
    }

    public boolean isDragDecelerationEnabled() {
        return this.mDragDecelerationEnabled;
    }

    public void setDragDecelerationEnabled(boolean enabled) {
        this.mDragDecelerationEnabled = enabled;
    }

    public float getDragDecelerationFrictionCoef() {
        return this.mDragDecelerationFrictionCoef;
    }

    public void setDragDecelerationFrictionCoef(float newValue) {
        if (newValue < 0.0f) {
            newValue = 0.0f;
        }
        if (newValue >= 1.0f) {
            newValue = 0.999f;
        }
        this.mDragDecelerationFrictionCoef = newValue;
    }

    public void animateXY(int durationMillisX, int durationMillisY, Easing.EasingFunction easingX, Easing.EasingFunction easingY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easingX, easingY);
    }

    public void animateXY(int durationMillisX, int durationMillisY, Easing.EasingFunction easing) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easing);
    }

    public void animateX(int durationMillis, Easing.EasingFunction easing) {
        this.mAnimator.animateX(durationMillis, easing);
    }

    public void animateY(int durationMillis, Easing.EasingFunction easing) {
        this.mAnimator.animateY(durationMillis, easing);
    }

    public void animateX(int durationMillis) {
        this.mAnimator.animateX(durationMillis);
    }

    public void animateY(int durationMillis) {
        this.mAnimator.animateY(durationMillis);
    }

    public void animateXY(int durationMillisX, int durationMillisY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY);
    }

    public XAxis getXAxis() {
        return this.mXAxis;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public IValueFormatter getDefaultValueFormatter() {
        return this.mDefaultValueFormatter;
    }

    public void setOnChartValueSelectedListener(OnChartValueSelectedListener l) {
        this.mSelectionListener = l;
    }

    public void setOnChartGestureListener(OnChartGestureListener l) {
        this.mGestureListener = l;
    }

    public OnChartGestureListener getOnChartGestureListener() {
        return this.mGestureListener;
    }

    public float getYMax() {
        return this.mData.getYMax();
    }

    public float getYMin() {
        return this.mData.getYMin();
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public float getXChartMax() {
        return this.mXAxis.mAxisMaximum;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public float getXChartMin() {
        return this.mXAxis.mAxisMinimum;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public float getXRange() {
        return this.mXAxis.mAxisRange;
    }

    public MPPointF getCenter() {
        return MPPointF.getInstance(getWidth() / 2.0f, getHeight() / 2.0f);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public MPPointF getCenterOffsets() {
        return this.mViewPortHandler.getContentCenter();
    }

    public void setExtraOffsets(float left, float top, float right, float bottom) {
        setExtraLeftOffset(left);
        setExtraTopOffset(top);
        setExtraRightOffset(right);
        setExtraBottomOffset(bottom);
    }

    public void setExtraTopOffset(float offset) {
        this.mExtraTopOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraTopOffset() {
        return this.mExtraTopOffset;
    }

    public void setExtraRightOffset(float offset) {
        this.mExtraRightOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraRightOffset() {
        return this.mExtraRightOffset;
    }

    public void setExtraBottomOffset(float offset) {
        this.mExtraBottomOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraBottomOffset() {
        return this.mExtraBottomOffset;
    }

    public void setExtraLeftOffset(float offset) {
        this.mExtraLeftOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraLeftOffset() {
        return this.mExtraLeftOffset;
    }

    public void setLogEnabled(boolean enabled) {
        this.mLogEnabled = enabled;
    }

    public boolean isLogEnabled() {
        return this.mLogEnabled;
    }

    public void setNoDataText(String text) {
        this.mNoDataText = text;
    }

    public void setNoDataTextColor(int color) {
        this.mInfoPaint.setColor(color);
    }

    public void setNoDataTextTypeface(Typeface tf) {
        this.mInfoPaint.setTypeface(tf);
    }

    public void setNoDataTextAlignment(Paint.Align align) {
        this.mInfoPaint.setTextAlign(align);
    }

    public void setTouchEnabled(boolean enabled) {
        this.mTouchEnabled = enabled;
    }

    public void setMarker(IMarker marker, List<TemperBean> temperBean, List<BloodPressureBean> mBloodPressureBean, List<SleepDayInfo> mSleepInfo, List<StepBean> mStepBean, MarkerLabel mMarkerLabel) {
        this.temperBeans = temperBean;
        this.mBloodPressureBean = mBloodPressureBean;
        this.mSleepInfo = mSleepInfo;
        this.mStepBean = mStepBean;
        this.mMarkerLabel = mMarkerLabel;
        this.mMarker = marker;
    }

    public void setMarker(IMarker marker, List<TemperBean> temperBean, List<BloodPressureBean> mBloodPressureBean, List<SleepDayInfo> mSleepInfo, List<StepBean> mStepBean, List<PhyDayInfo> mPhyInfo, MarkerLabel mMarkerLabel) {
        this.temperBeans = temperBean;
        this.mBloodPressureBean = mBloodPressureBean;
        this.mSleepInfo = mSleepInfo;
        this.mStepBean = mStepBean;
        this.mMarkerLabel = mMarkerLabel;
        this.mPhyInfo = mPhyInfo;
        this.mMarker = marker;
    }

    public IMarker getMarker() {
        return this.mMarker;
    }

    @Deprecated
    public void setMarkerView(IMarker v) {
        setMarker(v, this.temperBeans, this.mBloodPressureBean, this.mSleepInfo, this.mStepBean, this.mMarkerLabel);
    }

    @Deprecated
    public IMarker getMarkerView() {
        return getMarker();
    }

    public void setDescription(Description desc) {
        this.mDescription = desc;
    }

    public Description getDescription() {
        return this.mDescription;
    }

    public Legend getLegend() {
        return this.mLegend;
    }

    public LegendRenderer getLegendRenderer() {
        return this.mLegendRenderer;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public RectF getContentRect() {
        return this.mViewPortHandler.getContentRect();
    }

    public void disableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    public void enableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    public void setPaint(Paint p, int which) {
        if (which == 7) {
            this.mInfoPaint = p;
        } else {
            if (which != 11) {
                return;
            }
            this.mDescPaint = p;
        }
    }

    public Paint getPaint(int which) {
        if (which == 7) {
            return this.mInfoPaint;
        }
        if (which != 11) {
            return null;
        }
        return this.mDescPaint;
    }

    @Deprecated
    public boolean isDrawMarkerViewsEnabled() {
        return isDrawMarkersEnabled();
    }

    @Deprecated
    public void setDrawMarkerViews(boolean enabled) {
        setDrawMarkers(enabled);
    }

    public boolean isDrawMarkersEnabled() {
        return this.mDrawMarkers;
    }

    public void setDrawMarkers(boolean enabled) {
        this.mDrawMarkers = enabled;
    }

    public T getData() {
        return this.mData;
    }

    public ViewPortHandler getViewPortHandler() {
        return this.mViewPortHandler;
    }

    public DataRenderer getRenderer() {
        return this.mRenderer;
    }

    public void setRenderer(DataRenderer renderer) {
        if (renderer != null) {
            this.mRenderer = renderer;
        }
    }

    public IHighlighter getHighlighter() {
        return this.mHighlighter;
    }

    public void setHighlighter(ChartHighlighter highlighter) {
        this.mHighlighter = highlighter;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface
    public MPPointF getCenterOfView() {
        return getCenter();
    }

    public Bitmap getChartBitmap() {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Drawable background = getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        draw(canvas);
        return bitmapCreateBitmap;
    }

    public boolean saveToPath(String title, String pathOnSD) throws IOException {
        Bitmap chartBitmap = getChartBitmap();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + pathOnSD + "/" + title + ".png");
            chartBitmap.compress(Bitmap.CompressFormat.PNG, 40, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean saveToGallery(String fileName, String subFolderPath, String fileDescription, Bitmap.CompressFormat format, int quality) throws IOException {
        String str;
        if (quality < 0 || quality > 100) {
            quality = 50;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/" + subFolderPath);
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        int i2 = AnonymousClass2.$SwitchMap$android$graphics$Bitmap$CompressFormat[format.ordinal()];
        if (i2 == 1) {
            str = "image/png";
            if (!fileName.endsWith(".png")) {
                fileName = fileName + ".png";
            }
        } else if (i2 == 2) {
            str = "image/webp";
            if (!fileName.endsWith(".webp")) {
                fileName = fileName + ".webp";
            }
        } else {
            str = "image/jpeg";
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
                fileName = fileName + ".jpg";
            }
        }
        String str2 = file.getAbsolutePath() + "/" + fileName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            getChartBitmap().compress(format, quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            long length = new File(str2).length();
            ContentValues contentValues = new ContentValues(8);
            contentValues.put("title", fileName);
            contentValues.put("_display_name", fileName);
            contentValues.put("date_added", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("mime_type", str);
            contentValues.put("description", fileDescription);
            contentValues.put("orientation", (Integer) 0);
            contentValues.put("_data", str2);
            contentValues.put("_size", Long.valueOf(length));
            return getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues) != null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.customchart.charts.Chart$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$CompressFormat;
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Paint$Align;

        static {
            int[] iArr = new int[Bitmap.CompressFormat.values().length];
            $SwitchMap$android$graphics$Bitmap$CompressFormat = iArr;
            try {
                iArr[Bitmap.CompressFormat.PNG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.WEBP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.JPEG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Paint.Align.values().length];
            $SwitchMap$android$graphics$Paint$Align = iArr2;
            try {
                iArr2[Paint.Align.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$graphics$Paint$Align[Paint.Align.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public boolean saveToGallery(String fileName, int quality) {
        return saveToGallery(fileName, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.PNG, quality);
    }

    public boolean saveToGallery(String fileName) {
        return saveToGallery(fileName, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.PNG, 40);
    }

    public void removeViewportJob(Runnable job) {
        this.mJobs.remove(job);
    }

    public void clearAllViewportJobs() {
        this.mJobs.clear();
    }

    public void addViewportJob(Runnable job) {
        if (this.mViewPortHandler.hasChartDimens()) {
            post(job);
        } else {
            this.mJobs.add(job);
        }
    }

    public ArrayList<Runnable> getJobs() {
        return this.mJobs;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            getChildAt(i2).layout(left, top, right, bottom);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iConvertDpToPixel = (int) Utils.convertDpToPixel(50.0f);
        setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), resolveSize(iConvertDpToPixel, widthMeasureSpec)), Math.max(getSuggestedMinimumHeight(), resolveSize(iConvertDpToPixel, heightMeasureSpec)));
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        if (this.mLogEnabled) {
            Log.i("MPAndroidChart", "OnSizeChanged()");
        }
        if (w > 0 && h2 > 0 && w < 10000 && h2 < 10000) {
            if (this.mLogEnabled) {
                Log.i("MPAndroidChart", "Setting chart dimens, width: " + w + ", height: " + h2);
            }
            this.mViewPortHandler.setChartDimens(w, h2);
        } else if (this.mLogEnabled) {
            Log.w("MPAndroidChart", "*Avoiding* setting chart dimens! width: " + w + ", height: " + h2);
        }
        notifyDataSetChanged();
        Iterator<Runnable> it2 = this.mJobs.iterator();
        while (it2.hasNext()) {
            post(it2.next());
        }
        this.mJobs.clear();
        super.onSizeChanged(w, h2, oldw, oldh);
    }

    public void setHardwareAccelerationEnabled(boolean enabled) {
        if (enabled) {
            setLayerType(2, null);
        } else {
            setLayerType(1, null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mUnbind) {
            unbindDrawables(this);
        }
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (!(view instanceof ViewGroup)) {
            return;
        }
        int i2 = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i2 < viewGroup.getChildCount()) {
                unbindDrawables(viewGroup.getChildAt(i2));
                i2++;
            } else {
                viewGroup.removeAllViews();
                return;
            }
        }
    }

    public void setUnbindEnabled(boolean enabled) {
        this.mUnbind = enabled;
    }
}
