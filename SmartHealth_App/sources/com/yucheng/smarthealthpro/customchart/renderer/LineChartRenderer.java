package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.data.LineData;
import com.yucheng.smarthealthpro.customchart.data.LineDataSet;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;
import com.yucheng.smarthealthpro.customchart.renderer.BarLineScatterCandleBubbleRenderer;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class LineChartRenderer extends LineRadarRenderer {
    protected Path cubicFillPath;
    protected Path cubicPath;
    protected Canvas mBitmapCanvas;
    protected Bitmap.Config mBitmapConfig;
    protected LineDataProvider mChart;
    protected Paint mCirclePaintInner;
    private float[] mCirclesBuffer;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mGenerateFilledPathBuffer;
    private HashMap<IDataSet, DataSetImageCache> mImageCaches;
    private float[] mLineBuffer;

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void setRoundBar(boolean isRound) {
    }

    public LineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mBitmapConfig = Bitmap.Config.ARGB_8888;
        this.cubicPath = new Path();
        this.cubicFillPath = new Path();
        this.mLineBuffer = new float[4];
        this.mGenerateFilledPathBuffer = new Path();
        this.mImageCaches = new HashMap<>();
        this.mCirclesBuffer = new float[2];
        this.mChart = chart;
        Paint paint = new Paint(1);
        this.mCirclePaintInner = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mCirclePaintInner.setColor(-1);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawData(Canvas c2) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        Bitmap bitmapCreateBitmap = weakReference == null ? null : weakReference.get();
        if (bitmapCreateBitmap == null || bitmapCreateBitmap.getWidth() != chartWidth || bitmapCreateBitmap.getHeight() != chartHeight) {
            if (chartWidth <= 0 || chartHeight <= 0) {
                return;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(chartWidth, chartHeight, this.mBitmapConfig);
            this.mDrawBitmap = new WeakReference<>(bitmapCreateBitmap);
            this.mBitmapCanvas = new Canvas(bitmapCreateBitmap);
        }
        bitmapCreateBitmap.eraseColor(0);
        for (T t : this.mChart.getLineData().getDataSets()) {
            if (t.isVisible()) {
                drawDataSet(c2, t);
            }
        }
        c2.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, this.mRenderPaint);
    }

    protected void drawDataSet(Canvas c2, ILineDataSet dataSet) {
        if (dataSet.getEntryCount() < 1) {
            return;
        }
        this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
        this.mRenderPaint.setPathEffect(dataSet.getDashPathEffect());
        int i2 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$data$LineDataSet$Mode[dataSet.getMode().ordinal()];
        if (i2 == 3) {
            drawCubicBezier(dataSet);
        } else if (i2 != 4) {
            drawLinear(c2, dataSet);
        } else {
            drawHorizontalBezier(dataSet);
        }
        this.mRenderPaint.setPathEffect(null);
    }

    /* renamed from: com.yucheng.smarthealthpro.customchart.renderer.LineChartRenderer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$data$LineDataSet$Mode;

        static {
            int[] iArr = new int[LineDataSet.Mode.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$data$LineDataSet$Mode = iArr;
            try {
                iArr[LineDataSet.Mode.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$data$LineDataSet$Mode[LineDataSet.Mode.STEPPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$data$LineDataSet$Mode[LineDataSet.Mode.CUBIC_BEZIER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$data$LineDataSet$Mode[LineDataSet.Mode.HORIZONTAL_BEZIER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v8, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r3v6, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    protected void drawHorizontalBezier(ILineDataSet iLineDataSet) {
        float phaseY = this.mAnimator.getPhaseY();
        Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, iLineDataSet);
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            ?? entryForIndex = iLineDataSet.getEntryForIndex(this.mXBounds.min);
            this.cubicPath.moveTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
            int i2 = this.mXBounds.min + 1;
            Entry entry = entryForIndex;
            while (i2 <= this.mXBounds.range + this.mXBounds.min) {
                ?? entryForIndex2 = iLineDataSet.getEntryForIndex(i2);
                float x = entry.getX() + ((entryForIndex2.getX() - entry.getX()) / 2.0f);
                this.cubicPath.cubicTo(x, entry.getY() * phaseY, x, entryForIndex2.getY() * phaseY, entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                i2++;
                entry = entryForIndex2;
            }
        }
        if (iLineDataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, iLineDataSet, this.cubicFillPath, transformer, this.mXBounds);
        }
        this.mRenderPaint.setColor(iLineDataSet.getColor());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        transformer.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r9v6, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    protected void drawCubicBezier(ILineDataSet iLineDataSet) {
        float phaseY = this.mAnimator.getPhaseY();
        Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, iLineDataSet);
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            int i2 = this.mXBounds.min;
            int i3 = this.mXBounds.min;
            int i4 = this.mXBounds.range;
            T entryForIndex = iLineDataSet.getEntryForIndex(Math.max(i2 - 1, 0));
            ?? entryForIndex2 = iLineDataSet.getEntryForIndex(Math.max(i2, 0));
            if (entryForIndex2 != 0) {
                this.cubicPath.moveTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                int i5 = -1;
                int i6 = this.mXBounds.min + 1;
                Entry entry = entryForIndex2;
                Entry entry2 = entryForIndex2;
                Entry entry3 = entryForIndex;
                while (true) {
                    Entry entryForIndex3 = entry2;
                    if (i6 > this.mXBounds.range + this.mXBounds.min) {
                        break;
                    }
                    if (i5 != i6) {
                        entryForIndex3 = iLineDataSet.getEntryForIndex(i6);
                    }
                    int i7 = i6 + 1;
                    if (i7 < iLineDataSet.getEntryCount()) {
                        i6 = i7;
                    }
                    ?? entryForIndex4 = iLineDataSet.getEntryForIndex(i6);
                    float x = (entryForIndex3.getX() - entry3.getX()) * 0.0f;
                    float y = (entryForIndex3.getY() - entry3.getY()) * 0.0f;
                    float x2 = (entryForIndex4.getX() - entry.getX()) * 0.0f;
                    float y2 = (entryForIndex4.getY() - entry.getY()) * 0.0f;
                    if ((entry.getY() + y) * phaseY == 0.0f && entryForIndex3.getY() * phaseY != 0.0f) {
                        this.cubicPath.lineTo(entryForIndex3.getX(), 0.0f);
                        this.cubicPath.lineTo(entryForIndex3.getX(), entryForIndex3.getY() * phaseY);
                    } else if ((entry.getY() + y) * phaseY != 0.0f && entryForIndex3.getY() * phaseY == 0.0f) {
                        this.cubicPath.lineTo(entry.getX() + x, 0.0f);
                        this.cubicPath.lineTo(entryForIndex3.getX(), 0.0f);
                    } else {
                        this.cubicPath.cubicTo(x + entry.getX(), (entry.getY() + y) * phaseY, entryForIndex3.getX() - x2, (entryForIndex3.getY() - y2) * phaseY, entryForIndex3.getX(), entryForIndex3.getY() * phaseY);
                    }
                    entry3 = entry;
                    entry = entryForIndex3;
                    entry2 = entryForIndex4;
                    int i8 = i6;
                    i6 = i7;
                    i5 = i8;
                }
            } else {
                return;
            }
        }
        if (iLineDataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, iLineDataSet, this.cubicFillPath, transformer, this.mXBounds);
        }
        this.mRenderPaint.setColor(iLineDataSet.getColor());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        transformer.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r7v13, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r7v18, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r7v23, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r7v28, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    protected void drawCubicFill(Canvas c2, ILineDataSet dataSet, Path spline, Transformer trans, BarLineScatterCandleBubbleRenderer.XBounds bounds) {
        float fillLinePosition = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart);
        spline.lineTo(dataSet.getEntryForIndex(bounds.min + bounds.range).getX(), fillLinePosition);
        spline.lineTo(dataSet.getEntryForIndex(bounds.min).getX(), fillLinePosition);
        spline.close();
        trans.pathValueToPixel(spline);
        Drawable fillDrawable = dataSet.getFillDrawable();
        if (fillDrawable != null) {
            drawFilledPath(c2, spline, fillDrawable);
            return;
        }
        Log.i("KKKKKKKK", "drawCubicFill---1---" + dataSet.getType() + "----" + ((int) dataSet.getEntryForIndex(bounds.min).getY()));
        if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(bounds.min).getY()) == 5) {
            drawFilledPath(c2, spline, dataSet.getDPColor(), dataSet.getFillAlpha());
            return;
        }
        if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(bounds.min).getY()) == 10) {
            drawFilledPath(c2, spline, dataSet.getLightColor(), dataSet.getFillAlpha());
            return;
        }
        if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(bounds.min).getY()) == 15) {
            drawFilledPath(c2, spline, dataSet.getRemColor(), dataSet.getFillAlpha());
        } else if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(bounds.min).getY()) == 15) {
            drawFilledPath(c2, spline, dataSet.getNapsColor(), dataSet.getFillAlpha());
        } else {
            drawFilledPath(c2, spline, dataSet.getFillColor(), dataSet.getFillAlpha());
        }
    }

    /* JADX WARN: Type inference failed for: r10v12, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r12v3, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r13v20, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r13v8, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    protected void drawLinear(Canvas c2, ILineDataSet dataSet) {
        int entryCount = dataSet.getEntryCount();
        boolean zIsDrawSteppedEnabled = dataSet.isDrawSteppedEnabled();
        char c3 = 4;
        int i2 = zIsDrawSteppedEnabled ? 4 : 2;
        Transformer transformer = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        Canvas canvas = dataSet.isDashedLineEnabled() ? this.mBitmapCanvas : c2;
        this.mXBounds.set(this.mChart, dataSet);
        if (dataSet.isDrawFilledEnabled() && entryCount > 0) {
            drawLinearFill(c2, dataSet, transformer, this.mXBounds);
        }
        char c4 = 1;
        if (dataSet.getColors().size() > 1) {
            int i3 = i2 * 2;
            if (this.mLineBuffer.length <= i3) {
                this.mLineBuffer = new float[i2 * 4];
            }
            int i4 = this.mXBounds.min + this.mXBounds.range;
            int i5 = this.mXBounds.min;
            while (i5 < i4) {
                ?? entryForIndex = dataSet.getEntryForIndex(i5);
                if (entryForIndex != 0) {
                    this.mLineBuffer[0] = entryForIndex.getX();
                    this.mLineBuffer[c4] = entryForIndex.getY() * phaseY;
                    if (i5 < this.mXBounds.max) {
                        ?? entryForIndex2 = dataSet.getEntryForIndex(i5 + 1);
                        if (entryForIndex2 == 0) {
                            break;
                        }
                        if (zIsDrawSteppedEnabled) {
                            this.mLineBuffer[2] = entryForIndex2.getX();
                            float[] fArr = this.mLineBuffer;
                            float f2 = fArr[c4];
                            fArr[3] = f2;
                            fArr[c3] = fArr[2];
                            fArr[5] = f2;
                            fArr[6] = entryForIndex2.getX();
                            this.mLineBuffer[7] = entryForIndex2.getY() * phaseY;
                        } else {
                            this.mLineBuffer[2] = entryForIndex2.getX();
                            this.mLineBuffer[3] = entryForIndex2.getY() * phaseY;
                        }
                    } else {
                        float[] fArr2 = this.mLineBuffer;
                        fArr2[2] = fArr2[0];
                        fArr2[3] = fArr2[c4];
                    }
                    float[] fArr3 = this.mLineBuffer;
                    float f3 = fArr3[0];
                    float f4 = fArr3[c4];
                    float f5 = fArr3[i3 - 2];
                    float f6 = fArr3[i3 - 1];
                    if (f3 != f5 || f4 != f6) {
                        transformer.pointValuesToPixel(fArr3);
                        if (!this.mViewPortHandler.isInBoundsRight(f3)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(f5) && this.mViewPortHandler.isInBoundsTop(Math.max(f4, f6)) && this.mViewPortHandler.isInBoundsBottom(Math.min(f4, f6))) {
                            this.mRenderPaint.setColor(dataSet.getColor(i5));
                            canvas.drawLines(this.mLineBuffer, 0, i3, this.mRenderPaint);
                        }
                    }
                }
                i5++;
                c3 = 4;
                c4 = 1;
            }
        } else {
            int i6 = entryCount * i2;
            if (this.mLineBuffer.length < Math.max(i6, i2) * 2) {
                this.mLineBuffer = new float[Math.max(i6, i2) * 4];
            }
            if (dataSet.getEntryForIndex(this.mXBounds.min) != 0) {
                int i7 = this.mXBounds.min;
                int i8 = 0;
                while (i7 <= this.mXBounds.range + this.mXBounds.min) {
                    ?? entryForIndex3 = dataSet.getEntryForIndex(i7 == 0 ? 0 : i7 - 1);
                    ?? entryForIndex4 = dataSet.getEntryForIndex(i7);
                    if (entryForIndex3 != 0 && entryForIndex4 != 0) {
                        this.mLineBuffer[i8] = entryForIndex3.getX();
                        int i9 = i8 + 2;
                        this.mLineBuffer[i8 + 1] = entryForIndex3.getY() * phaseY;
                        if (zIsDrawSteppedEnabled) {
                            this.mLineBuffer[i9] = entryForIndex4.getX();
                            this.mLineBuffer[i8 + 3] = entryForIndex3.getY() * phaseY;
                            this.mLineBuffer[i8 + 4] = entryForIndex4.getX();
                            i9 = i8 + 6;
                            this.mLineBuffer[i8 + 5] = entryForIndex3.getY() * phaseY;
                        }
                        this.mLineBuffer[i9] = entryForIndex4.getX();
                        this.mLineBuffer[i9 + 1] = entryForIndex4.getY() * phaseY;
                        i8 = i9 + 2;
                    }
                    i7++;
                }
                if (i8 > 0) {
                    transformer.pointValuesToPixel(this.mLineBuffer);
                    int iMax = Math.max((this.mXBounds.range + 1) * i2, i2) * 2;
                    this.mRenderPaint.setColor(dataSet.getColor());
                    canvas.drawLines(this.mLineBuffer, 0, iMax, this.mRenderPaint);
                }
            }
        }
        this.mRenderPaint.setPathEffect(null);
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r0v22, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    protected void drawLinearFill(Canvas c2, ILineDataSet dataSet, Transformer trans, BarLineScatterCandleBubbleRenderer.XBounds bounds) {
        int i2;
        int i3;
        Path path = this.mGenerateFilledPathBuffer;
        int i4 = bounds.min;
        int i5 = bounds.min + bounds.range;
        int i6 = 0;
        do {
            i2 = i4 + i6;
            int i7 = i2 + 1;
            i3 = i7 > i5 ? i5 : i7;
            if (i2 <= i3) {
                generateFilledPath(dataSet, i2, i3, path, dataSet.getType());
                trans.pathValueToPixel(path);
                Drawable fillDrawable = dataSet.getFillDrawable();
                if (fillDrawable != null) {
                    drawFilledPath(c2, path, fillDrawable);
                } else if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(i2).getY()) == 5) {
                    drawFilledPath(c2, path, dataSet.getDPColor(), dataSet.getFillAlpha());
                } else if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(i2).getY()) == 10) {
                    drawFilledPath(c2, path, dataSet.getLightColor(), dataSet.getFillAlpha());
                } else if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(i2).getY()) == 15) {
                    drawFilledPath(c2, path, dataSet.getRemColor(), dataSet.getFillAlpha());
                } else if (dataSet.getType() == 1 && ((int) dataSet.getEntryForIndex(i2).getY()) == 15) {
                    drawFilledPath(c2, path, dataSet.getNapsColor(), dataSet.getFillAlpha());
                } else {
                    drawFilledPath(c2, path, dataSet.getFillColor(), dataSet.getFillAlpha());
                }
            }
            i6++;
        } while (i2 <= i3);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    private void generateFilledPath(ILineDataSet iLineDataSet, int i2, int i3, Path path, int i4) {
        float fillLinePosition = iLineDataSet.getFillFormatter().getFillLinePosition(iLineDataSet, this.mChart);
        float phaseY = this.mAnimator.getPhaseY();
        boolean z = iLineDataSet.getMode() == LineDataSet.Mode.STEPPED;
        path.reset();
        ?? entryForIndex = iLineDataSet.getEntryForIndex(i2);
        if (iLineDataSet.getMode() == LineDataSet.Mode.SLEEP_MODE) {
            fillLinePosition = (entryForIndex.getY() * phaseY) - 7.0f;
        }
        path.moveTo(entryForIndex.getX(), fillLinePosition);
        path.lineTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
        int i5 = i2 + 1;
        Entry entry = null;
        Entry entry2 = entryForIndex;
        while (i5 <= i3) {
            ?? entryForIndex2 = iLineDataSet.getEntryForIndex(i5);
            if (z) {
                path.lineTo(entryForIndex2.getX(), entry2.getY() * phaseY);
            }
            if (entryForIndex2.getY() != entry2.getY() && iLineDataSet.getType() == 1) {
                path.lineTo(entry2.getX(), entryForIndex2.getY() * phaseY);
                path.lineTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
            } else {
                path.lineTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
            }
            i5++;
            entry2 = entryForIndex2;
            entry = entryForIndex2;
        }
        if (entry != null) {
            path.lineTo(entry.getX(), fillLinePosition);
        }
        path.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawValues(Canvas c2) {
        int i2;
        MPPointF mPPointF;
        float f2;
        float f3;
        if (isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = this.mChart.getLineData().getDataSets();
            for (int i3 = 0; i3 < dataSets.size(); i3++) {
                ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i3);
                if (shouldDrawValues(iLineDataSet) && iLineDataSet.getEntryCount() >= 1) {
                    applyValueTextStyle(iLineDataSet);
                    Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
                    int circleRadius = (int) (iLineDataSet.getCircleRadius() * 1.75f);
                    if (!iLineDataSet.isDrawCirclesEnabled()) {
                        circleRadius /= 2;
                    }
                    int i4 = circleRadius;
                    this.mXBounds.set(this.mChart, iLineDataSet);
                    float[] fArrGenerateTransformedValuesLine = transformer.generateTransformedValuesLine(iLineDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    MPPointF mPPointF2 = MPPointF.getInstance(iLineDataSet.getIconsOffset());
                    mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                    mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                    int i5 = 0;
                    while (i5 < fArrGenerateTransformedValuesLine.length) {
                        float f4 = fArrGenerateTransformedValuesLine[i5];
                        float f5 = fArrGenerateTransformedValuesLine[i5 + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(f4)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(f4) && this.mViewPortHandler.isInBoundsY(f5)) {
                            int i6 = i5 / 2;
                            ?? entryForIndex = iLineDataSet.getEntryForIndex(this.mXBounds.min + i6);
                            if (iLineDataSet.isDrawValuesEnabled()) {
                                f2 = f5;
                                f3 = f4;
                                i2 = i5;
                                mPPointF = mPPointF2;
                                drawValue(c2, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i3, f4, f5 - i4, iLineDataSet.getValueTextColor(i6));
                            } else {
                                f2 = f5;
                                f3 = f4;
                                i2 = i5;
                                mPPointF = mPPointF2;
                            }
                            if (entryForIndex.getIcon() != null && iLineDataSet.isDrawIconsEnabled()) {
                                Drawable icon = entryForIndex.getIcon();
                                Utils.drawImage(c2, icon, (int) (f3 + mPPointF.x), (int) (f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        } else {
                            i2 = i5;
                            mPPointF = mPPointF2;
                        }
                        i5 = i2 + 2;
                        mPPointF2 = mPPointF;
                    }
                    MPPointF.recycleInstance(mPPointF2);
                }
            }
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawExtras(Canvas c2) {
        drawCircles(c2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v2, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8 */
    protected void drawCircles(Canvas canvas) {
        DataSetImageCache dataSetImageCache;
        Bitmap bitmap;
        this.mRenderPaint.setStyle(Paint.Style.FILL);
        float phaseY = this.mAnimator.getPhaseY();
        float[] fArr = this.mCirclesBuffer;
        boolean z = false;
        float f2 = 0.0f;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        List dataSets = this.mChart.getLineData().getDataSets();
        int i2 = 0;
        while (i2 < dataSets.size()) {
            ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i2);
            if (iLineDataSet.isVisible() && iLineDataSet.isDrawCirclesEnabled() && iLineDataSet.getEntryCount() != 0) {
                this.mCirclePaintInner.setColor(iLineDataSet.getCircleHoleColor());
                Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
                this.mXBounds.set(this.mChart, iLineDataSet);
                float circleRadius = iLineDataSet.getCircleRadius();
                float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
                boolean z2 = (!iLineDataSet.isDrawCircleHoleEnabled() || circleHoleRadius >= circleRadius || circleHoleRadius <= f2) ? z ? 1 : 0 : true;
                boolean z3 = (z2 && iLineDataSet.getCircleHoleColor() == 1122867) ? true : z ? 1 : 0;
                if (this.mImageCaches.containsKey(iLineDataSet)) {
                    dataSetImageCache = this.mImageCaches.get(iLineDataSet);
                } else {
                    dataSetImageCache = new DataSetImageCache();
                    this.mImageCaches.put(iLineDataSet, dataSetImageCache);
                }
                if (dataSetImageCache.init(iLineDataSet)) {
                    dataSetImageCache.fill(iLineDataSet, z2, z3);
                }
                int i3 = this.mXBounds.range + this.mXBounds.min;
                int i4 = this.mXBounds.min;
                ?? r3 = z;
                while (i4 <= i3) {
                    ?? entryForIndex = iLineDataSet.getEntryForIndex(i4);
                    if (entryForIndex == 0) {
                        break;
                    }
                    this.mCirclesBuffer[r3] = entryForIndex.getX();
                    this.mCirclesBuffer[1] = entryForIndex.getY() * phaseY;
                    transformer.pointValuesToPixel(this.mCirclesBuffer);
                    if (!this.mViewPortHandler.isInBoundsRight(this.mCirclesBuffer[r3])) {
                        break;
                    }
                    if (this.mViewPortHandler.isInBoundsLeft(this.mCirclesBuffer[r3]) && this.mViewPortHandler.isInBoundsY(this.mCirclesBuffer[1]) && (bitmap = dataSetImageCache.getBitmap(i4)) != null) {
                        float[] fArr2 = this.mCirclesBuffer;
                        canvas.drawBitmap(bitmap, fArr2[r3] - circleRadius, fArr2[1] - circleRadius, (Paint) null);
                    }
                    i4++;
                    r3 = 0;
                }
            }
            i2++;
            z = false;
            f2 = 0.0f;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawHighlighted(Canvas c2, Highlight[] indices) {
        LineData lineData = this.mChart.getLineData();
        for (Highlight highlight : indices) {
            ILineDataSet iLineDataSet = (ILineDataSet) lineData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iLineDataSet != null && iLineDataSet.isHighlightEnabled()) {
                ?? entryForXValue = iLineDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iLineDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iLineDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    drawHighlightLines(c2, (float) pixelForValues.x, (float) pixelForValues.y, iLineDataSet);
                }
            }
        }
    }

    public void setBitmapConfig(Bitmap.Config config) {
        this.mBitmapConfig = config;
        releaseBitmap();
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public void releaseBitmap() {
        Canvas canvas = this.mBitmapCanvas;
        if (canvas != null) {
            canvas.setBitmap(null);
            this.mBitmapCanvas = null;
        }
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (weakReference != null) {
            Bitmap bitmap = weakReference.get();
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }

    private class DataSetImageCache {
        private Bitmap[] circleBitmaps;
        private Path mCirclePathBuffer;

        private DataSetImageCache() {
            this.mCirclePathBuffer = new Path();
        }

        protected boolean init(ILineDataSet set) {
            int circleColorCount = set.getCircleColorCount();
            Bitmap[] bitmapArr = this.circleBitmaps;
            if (bitmapArr == null) {
                this.circleBitmaps = new Bitmap[circleColorCount];
                return true;
            }
            if (bitmapArr.length == circleColorCount) {
                return false;
            }
            this.circleBitmaps = new Bitmap[circleColorCount];
            return true;
        }

        protected void fill(ILineDataSet set, boolean drawCircleHole, boolean drawTransparentCircleHole) {
            int circleColorCount = set.getCircleColorCount();
            float circleRadius = set.getCircleRadius();
            float circleHoleRadius = set.getCircleHoleRadius();
            for (int i2 = 0; i2 < circleColorCount; i2++) {
                int i3 = (int) (circleRadius * 2.1d);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i3, Bitmap.Config.ARGB_4444);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                this.circleBitmaps[i2] = bitmapCreateBitmap;
                LineChartRenderer.this.mRenderPaint.setColor(set.getCircleColor(i2));
                if (drawTransparentCircleHole) {
                    this.mCirclePathBuffer.reset();
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleRadius, Path.Direction.CW);
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleHoleRadius, Path.Direction.CCW);
                    canvas.drawPath(this.mCirclePathBuffer, LineChartRenderer.this.mRenderPaint);
                } else {
                    canvas.drawCircle(circleRadius, circleRadius, circleRadius, LineChartRenderer.this.mRenderPaint);
                    if (drawCircleHole) {
                        canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, LineChartRenderer.this.mCirclePaintInner);
                    }
                }
            }
        }

        protected Bitmap getBitmap(int index) {
            Bitmap[] bitmapArr = this.circleBitmaps;
            return bitmapArr[index % bitmapArr.length];
        }
    }
}
