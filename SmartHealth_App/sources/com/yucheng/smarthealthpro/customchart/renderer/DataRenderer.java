package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import com.yucheng.smarthealthpro.me.setting.SettingsDataType;

/* loaded from: classes4.dex */
public abstract class DataRenderer extends Renderer {
    protected ChartAnimator mAnimator;
    protected Paint mDrawPaint;
    protected Paint mHighlightPaint;
    protected Paint mRenderPaint;
    protected Paint mValuePaint;

    public abstract void drawData(Canvas c2);

    public abstract void drawExtras(Canvas c2);

    public abstract void drawHighlighted(Canvas c2, Highlight[] indices);

    public abstract void drawValues(Canvas c2);

    public abstract void initBuffers();

    public abstract void setRoundBar(boolean isRound);

    public DataRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(viewPortHandler);
        this.mAnimator = animator;
        Paint paint = new Paint(1);
        this.mRenderPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mDrawPaint = new Paint(4);
        Paint paint2 = new Paint(1);
        this.mValuePaint = paint2;
        paint2.setColor(Color.rgb(63, 63, 63));
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(9.0f));
        Paint paint3 = new Paint(1);
        this.mHighlightPaint = paint3;
        paint3.setStyle(Paint.Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, Opcodes.NEW, SettingsDataType.MORE_SETTINGS));
    }

    protected boolean isDrawingValuesAllowed(ChartInterface chart) {
        return ((float) chart.getData().getEntryCount()) < ((float) chart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleX();
    }

    public Paint getPaintValues() {
        return this.mValuePaint;
    }

    public Paint getPaintHighlight() {
        return this.mHighlightPaint;
    }

    public Paint getPaintRender() {
        return this.mRenderPaint;
    }

    protected void applyValueTextStyle(IDataSet set) {
        this.mValuePaint.setTypeface(set.getValueTypeface());
        this.mValuePaint.setTextSize(set.getValueTextSize());
    }

    public void drawValue(Canvas c2, IValueFormatter formatter, float value, Entry entry, int dataSetIndex, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c2.drawText(formatter.getFormattedValue(value, entry, dataSetIndex, this.mViewPortHandler), x, y, this.mValuePaint);
    }
}
