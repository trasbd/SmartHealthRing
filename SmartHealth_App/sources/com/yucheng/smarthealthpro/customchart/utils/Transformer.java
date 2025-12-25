package com.yucheng.smarthealthpro.customchart.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.yucheng.smarthealthpro.customchart.data.CandleEntry;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBubbleDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IScatterDataSet;
import java.util.List;

/* loaded from: classes4.dex */
public class Transformer {
    protected ViewPortHandler mViewPortHandler;
    protected Matrix mMatrixValueToPx = new Matrix();
    protected Matrix mMatrixOffset = new Matrix();
    protected float[] valuePointsForGenerateTransformedValuesScatter = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesBubble = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesLine = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesCandle = new float[1];
    protected Matrix mPixelToValueMatrixBuffer = new Matrix();
    float[] ptsBuffer = new float[2];
    private Matrix mMBuffer1 = new Matrix();
    private Matrix mMBuffer2 = new Matrix();

    public Transformer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }

    public void prepareMatrixValuePx(float xChartMin, float deltaX, float deltaY, float yChartMin) {
        float fContentWidth = this.mViewPortHandler.contentWidth() / deltaX;
        float fContentHeight = this.mViewPortHandler.contentHeight() / deltaY;
        if (Float.isInfinite(fContentWidth)) {
            fContentWidth = 0.0f;
        }
        if (Float.isInfinite(fContentHeight)) {
            fContentHeight = 0.0f;
        }
        this.mMatrixValueToPx.reset();
        this.mMatrixValueToPx.postTranslate(-xChartMin, -yChartMin);
        this.mMatrixValueToPx.postScale(fContentWidth, -fContentHeight);
    }

    public void prepareMatrixOffset(boolean inverted) {
        this.mMatrixOffset.reset();
        if (!inverted) {
            this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
        } else {
            this.mMatrixOffset.setTranslate(this.mViewPortHandler.offsetLeft(), -this.mViewPortHandler.offsetTop());
            this.mMatrixOffset.postScale(1.0f, -1.0f);
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    public float[] generateTransformedValuesScatter(IScatterDataSet data, float phaseX, float phaseY, int from, int to) {
        int i2 = ((int) (((to - from) * phaseX) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesScatter.length != i2) {
            this.valuePointsForGenerateTransformedValuesScatter = new float[i2];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesScatter;
        for (int i3 = 0; i3 < i2; i3 += 2) {
            ?? entryForIndex = data.getEntryForIndex((i3 / 2) + from);
            if (entryForIndex != 0) {
                fArr[i3] = entryForIndex.getX();
                fArr[i3 + 1] = entryForIndex.getY() * phaseY;
            } else {
                fArr[i3] = 0.0f;
                fArr[i3 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    public float[] generateTransformedValuesBubble(IBubbleDataSet data, float phaseY, int from, int to) {
        int i2 = ((to - from) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesBubble.length != i2) {
            this.valuePointsForGenerateTransformedValuesBubble = new float[i2];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesBubble;
        for (int i3 = 0; i3 < i2; i3 += 2) {
            ?? entryForIndex = data.getEntryForIndex((i3 / 2) + from);
            if (entryForIndex != 0) {
                fArr[i3] = entryForIndex.getX();
                fArr[i3 + 1] = entryForIndex.getY() * phaseY;
            } else {
                fArr[i3] = 0.0f;
                fArr[i3 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.yucheng.smarthealthpro.customchart.data.Entry] */
    public float[] generateTransformedValuesLine(ILineDataSet data, float phaseX, float phaseY, int min, int max) {
        int i2 = (((int) ((max - min) * phaseX)) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesLine.length != i2) {
            this.valuePointsForGenerateTransformedValuesLine = new float[i2];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesLine;
        for (int i3 = 0; i3 < i2; i3 += 2) {
            ?? entryForIndex = data.getEntryForIndex((i3 / 2) + min);
            if (entryForIndex != 0) {
                fArr[i3] = entryForIndex.getX();
                fArr[i3 + 1] = entryForIndex.getY() * phaseY;
            } else {
                fArr[i3] = 0.0f;
                fArr[i3 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float[] generateTransformedValuesCandle(ICandleDataSet data, float phaseX, float phaseY, int from, int to) {
        int i2 = ((int) (((to - from) * phaseX) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesCandle.length != i2) {
            this.valuePointsForGenerateTransformedValuesCandle = new float[i2];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesCandle;
        for (int i3 = 0; i3 < i2; i3 += 2) {
            CandleEntry candleEntry = (CandleEntry) data.getEntryForIndex((i3 / 2) + from);
            if (candleEntry != null) {
                fArr[i3] = candleEntry.getX();
                fArr[i3 + 1] = candleEntry.getHigh() * phaseY;
            } else {
                fArr[i3] = 0.0f;
                fArr[i3 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public void pathValueToPixel(Path path) {
        path.transform(this.mMatrixValueToPx);
        path.transform(this.mViewPortHandler.getMatrixTouch());
        path.transform(this.mMatrixOffset);
    }

    public void pathValuesToPixel(List<Path> paths) {
        for (int i2 = 0; i2 < paths.size(); i2++) {
            pathValueToPixel(paths.get(i2));
        }
    }

    public void pointValuesToPixel(float[] pts) {
        this.mMatrixValueToPx.mapPoints(pts);
        this.mViewPortHandler.getMatrixTouch().mapPoints(pts);
        this.mMatrixOffset.mapPoints(pts);
    }

    public void rectValueToPixel(RectF r) {
        this.mMatrixValueToPx.mapRect(r);
        this.mViewPortHandler.getMatrixTouch().mapRect(r);
        this.mMatrixOffset.mapRect(r);
    }

    public void rectToPixelPhase(RectF r, float phaseY) {
        r.top *= phaseY;
        r.bottom *= phaseY;
        this.mMatrixValueToPx.mapRect(r);
        this.mViewPortHandler.getMatrixTouch().mapRect(r);
        this.mMatrixOffset.mapRect(r);
    }

    public void rectToPixelPhaseHorizontal(RectF r, float phaseY) {
        r.left *= phaseY;
        r.right *= phaseY;
        this.mMatrixValueToPx.mapRect(r);
        this.mViewPortHandler.getMatrixTouch().mapRect(r);
        this.mMatrixOffset.mapRect(r);
    }

    public void rectValueToPixelHorizontal(RectF r) {
        this.mMatrixValueToPx.mapRect(r);
        this.mViewPortHandler.getMatrixTouch().mapRect(r);
        this.mMatrixOffset.mapRect(r);
    }

    public void rectValueToPixelHorizontal(RectF r, float phaseY) {
        r.left *= phaseY;
        r.right *= phaseY;
        this.mMatrixValueToPx.mapRect(r);
        this.mViewPortHandler.getMatrixTouch().mapRect(r);
        this.mMatrixOffset.mapRect(r);
    }

    public void rectValuesToPixel(List<RectF> rects) {
        Matrix valueToPixelMatrix = getValueToPixelMatrix();
        for (int i2 = 0; i2 < rects.size(); i2++) {
            valueToPixelMatrix.mapRect(rects.get(i2));
        }
    }

    public void pixelsToValue(float[] pixels) {
        Matrix matrix = this.mPixelToValueMatrixBuffer;
        matrix.reset();
        this.mMatrixOffset.invert(matrix);
        matrix.mapPoints(pixels);
        this.mViewPortHandler.getMatrixTouch().invert(matrix);
        matrix.mapPoints(pixels);
        this.mMatrixValueToPx.invert(matrix);
        matrix.mapPoints(pixels);
    }

    public MPPointD getValuesByTouchPoint(float x, float y) {
        MPPointD mPPointD = MPPointD.getInstance(0.0d, 0.0d);
        getValuesByTouchPoint(x, y, mPPointD);
        return mPPointD;
    }

    public void getValuesByTouchPoint(float x, float y, MPPointD outputPoint) {
        float[] fArr = this.ptsBuffer;
        fArr[0] = x;
        fArr[1] = y;
        pixelsToValue(fArr);
        outputPoint.x = this.ptsBuffer[0];
        outputPoint.y = this.ptsBuffer[1];
    }

    public MPPointD getPixelForValues(float x, float y) {
        float[] fArr = this.ptsBuffer;
        fArr[0] = x;
        fArr[1] = y;
        pointValuesToPixel(fArr);
        float[] fArr2 = this.ptsBuffer;
        return MPPointD.getInstance(fArr2[0], fArr2[1]);
    }

    public Matrix getValueMatrix() {
        return this.mMatrixValueToPx;
    }

    public Matrix getOffsetMatrix() {
        return this.mMatrixOffset;
    }

    public Matrix getValueToPixelMatrix() {
        this.mMBuffer1.set(this.mMatrixValueToPx);
        this.mMBuffer1.postConcat(this.mViewPortHandler.mMatrixTouch);
        this.mMBuffer1.postConcat(this.mMatrixOffset);
        return this.mMBuffer1;
    }

    public Matrix getPixelToValueMatrix() {
        getValueToPixelMatrix().invert(this.mMBuffer2);
        return this.mMBuffer2;
    }
}
