package com.yucheng.smarthealthpro.customchart.jobs;

import android.graphics.Matrix;
import android.view.View;
import com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public class ZoomJob extends ViewPortJob {
    private static ObjectPool<ZoomJob> pool;
    protected YAxis.AxisDependency axisDependency;
    protected Matrix mRunMatrixBuffer;
    protected float scaleX;
    protected float scaleY;

    static {
        ObjectPool<ZoomJob> objectPoolCreate = ObjectPool.create(1, new ZoomJob(null, 0.0f, 0.0f, 0.0f, 0.0f, null, null, null));
        pool = objectPoolCreate;
        objectPoolCreate.setReplenishPercentage(0.5f);
    }

    public static ZoomJob getInstance(ViewPortHandler viewPortHandler, float scaleX, float scaleY, float xValue, float yValue, Transformer trans, YAxis.AxisDependency axis, View v) {
        ZoomJob zoomJob = (ZoomJob) pool.get();
        zoomJob.xValue = xValue;
        zoomJob.yValue = yValue;
        zoomJob.scaleX = scaleX;
        zoomJob.scaleY = scaleY;
        zoomJob.mViewPortHandler = viewPortHandler;
        zoomJob.mTrans = trans;
        zoomJob.axisDependency = axis;
        zoomJob.view = v;
        return zoomJob;
    }

    public static void recycleInstance(ZoomJob instance) {
        pool.recycle((ObjectPool<ZoomJob>) instance);
    }

    public ZoomJob(ViewPortHandler viewPortHandler, float scaleX, float scaleY, float xValue, float yValue, Transformer trans, YAxis.AxisDependency axis, View v) {
        super(viewPortHandler, xValue, yValue, trans, v);
        this.mRunMatrixBuffer = new Matrix();
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.axisDependency = axis;
    }

    @Override // java.lang.Runnable
    public void run() {
        Matrix matrix = this.mRunMatrixBuffer;
        this.mViewPortHandler.zoom(this.scaleX, this.scaleY, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        float scaleY = ((BarLineChartBase) this.view).getAxis(this.axisDependency).mAxisRange / this.mViewPortHandler.getScaleY();
        this.pts[0] = this.xValue - ((((BarLineChartBase) this.view).getXAxis().mAxisRange / this.mViewPortHandler.getScaleX()) / 2.0f);
        this.pts[1] = this.yValue + (scaleY / 2.0f);
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.translate(this.pts, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        ((BarLineChartBase) this.view).calculateOffsets();
        this.view.postInvalidate();
        recycleInstance(this);
    }

    @Override // com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new ZoomJob(null, 0.0f, 0.0f, 0.0f, 0.0f, null, null, null);
    }
}
