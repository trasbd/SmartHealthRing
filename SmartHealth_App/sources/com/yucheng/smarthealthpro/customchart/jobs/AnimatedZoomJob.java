package com.yucheng.smarthealthpro.customchart.jobs;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.view.View;
import com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public class AnimatedZoomJob extends AnimatedViewPortJob implements Animator.AnimatorListener {
    private static ObjectPool<AnimatedZoomJob> pool = ObjectPool.create(8, new AnimatedZoomJob(null, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0));
    protected Matrix mOnAnimationUpdateMatrixBuffer;
    protected float xAxisRange;
    protected YAxis yAxis;
    protected float zoomCenterX;
    protected float zoomCenterY;
    protected float zoomOriginX;
    protected float zoomOriginY;

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animation) {
    }

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animation) {
    }

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animation) {
    }

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob
    public void recycleSelf() {
    }

    public static AnimatedZoomJob getInstance(ViewPortHandler viewPortHandler, View v, Transformer trans, YAxis axis, float xAxisRange, float scaleX, float scaleY, float xOrigin, float yOrigin, float zoomCenterX, float zoomCenterY, float zoomOriginX, float zoomOriginY, long duration) {
        AnimatedZoomJob animatedZoomJob = (AnimatedZoomJob) pool.get();
        animatedZoomJob.mViewPortHandler = viewPortHandler;
        animatedZoomJob.xValue = scaleX;
        animatedZoomJob.yValue = scaleY;
        animatedZoomJob.mTrans = trans;
        animatedZoomJob.view = v;
        animatedZoomJob.xOrigin = xOrigin;
        animatedZoomJob.yOrigin = yOrigin;
        animatedZoomJob.yAxis = axis;
        animatedZoomJob.xAxisRange = xAxisRange;
        animatedZoomJob.resetAnimator();
        animatedZoomJob.animator.setDuration(duration);
        return animatedZoomJob;
    }

    public AnimatedZoomJob(ViewPortHandler viewPortHandler, View v, Transformer trans, YAxis axis, float xAxisRange, float scaleX, float scaleY, float xOrigin, float yOrigin, float zoomCenterX, float zoomCenterY, float zoomOriginX, float zoomOriginY, long duration) {
        super(viewPortHandler, scaleX, scaleY, trans, v, xOrigin, yOrigin, duration);
        this.mOnAnimationUpdateMatrixBuffer = new Matrix();
        this.zoomCenterX = zoomCenterX;
        this.zoomCenterY = zoomCenterY;
        this.zoomOriginX = zoomOriginX;
        this.zoomOriginY = zoomOriginY;
        this.animator.addListener(this);
        this.yAxis = axis;
        this.xAxisRange = xAxisRange;
    }

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob, android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator animation) {
        float f2 = this.xOrigin + ((this.xValue - this.xOrigin) * this.phase);
        float f3 = this.yOrigin + ((this.yValue - this.yOrigin) * this.phase);
        Matrix matrix = this.mOnAnimationUpdateMatrixBuffer;
        this.mViewPortHandler.setZoom(f2, f3, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        float scaleY = this.yAxis.mAxisRange / this.mViewPortHandler.getScaleY();
        float scaleX = this.xAxisRange / this.mViewPortHandler.getScaleX();
        float[] fArr = this.pts;
        float f4 = this.zoomOriginX;
        fArr[0] = f4 + (((this.zoomCenterX - (scaleX / 2.0f)) - f4) * this.phase);
        float[] fArr2 = this.pts;
        float f5 = this.zoomOriginY;
        fArr2[1] = f5 + (((this.zoomCenterY + (scaleY / 2.0f)) - f5) * this.phase);
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.translate(this.pts, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, true);
    }

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        ((BarLineChartBase) this.view).calculateOffsets();
        this.view.postInvalidate();
    }

    @Override // com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new AnimatedZoomJob(null, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L);
    }
}
