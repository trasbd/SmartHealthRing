package com.yucheng.smarthealthpro.customchart.jobs;

import android.animation.ValueAnimator;
import android.view.View;
import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public class AnimatedMoveViewJob extends AnimatedViewPortJob {
    private static ObjectPool<AnimatedMoveViewJob> pool;

    static {
        ObjectPool<AnimatedMoveViewJob> objectPoolCreate = ObjectPool.create(4, new AnimatedMoveViewJob(null, 0.0f, 0.0f, null, null, 0.0f, 0.0f, 0L));
        pool = objectPoolCreate;
        objectPoolCreate.setReplenishPercentage(0.5f);
    }

    public static AnimatedMoveViewJob getInstance(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v, float xOrigin, float yOrigin, long duration) {
        AnimatedMoveViewJob animatedMoveViewJob = (AnimatedMoveViewJob) pool.get();
        animatedMoveViewJob.mViewPortHandler = viewPortHandler;
        animatedMoveViewJob.xValue = xValue;
        animatedMoveViewJob.yValue = yValue;
        animatedMoveViewJob.mTrans = trans;
        animatedMoveViewJob.view = v;
        animatedMoveViewJob.xOrigin = xOrigin;
        animatedMoveViewJob.yOrigin = yOrigin;
        animatedMoveViewJob.animator.setDuration(duration);
        return animatedMoveViewJob;
    }

    public static void recycleInstance(AnimatedMoveViewJob instance) {
        pool.recycle((ObjectPool<AnimatedMoveViewJob>) instance);
    }

    public AnimatedMoveViewJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v, float xOrigin, float yOrigin, long duration) {
        super(viewPortHandler, xValue, yValue, trans, v, xOrigin, yOrigin, duration);
    }

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob, android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator animation) {
        this.pts[0] = this.xOrigin + ((this.xValue - this.xOrigin) * this.phase);
        this.pts[1] = this.yOrigin + ((this.yValue - this.yOrigin) * this.phase);
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.centerViewPort(this.pts, this.view);
    }

    @Override // com.yucheng.smarthealthpro.customchart.jobs.AnimatedViewPortJob
    public void recycleSelf() {
        recycleInstance(this);
    }

    @Override // com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new AnimatedMoveViewJob(null, 0.0f, 0.0f, null, null, 0.0f, 0.0f, 0L);
    }
}
