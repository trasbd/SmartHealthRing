package com.yucheng.smarthealthpro.customchart.jobs;

import android.view.View;
import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public class MoveViewJob extends ViewPortJob {
    private static ObjectPool<MoveViewJob> pool;

    static {
        ObjectPool<MoveViewJob> objectPoolCreate = ObjectPool.create(2, new MoveViewJob(null, 0.0f, 0.0f, null, null));
        pool = objectPoolCreate;
        objectPoolCreate.setReplenishPercentage(0.5f);
    }

    public static MoveViewJob getInstance(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) {
        MoveViewJob moveViewJob = (MoveViewJob) pool.get();
        moveViewJob.mViewPortHandler = viewPortHandler;
        moveViewJob.xValue = xValue;
        moveViewJob.yValue = yValue;
        moveViewJob.mTrans = trans;
        moveViewJob.view = v;
        return moveViewJob;
    }

    public static void recycleInstance(MoveViewJob instance) {
        pool.recycle((ObjectPool<MoveViewJob>) instance);
    }

    public MoveViewJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) {
        super(viewPortHandler, xValue, yValue, trans, v);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.pts[0] = this.xValue;
        this.pts[1] = this.yValue;
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.centerViewPort(this.pts, this.view);
        recycleInstance(this);
    }

    @Override // com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new MoveViewJob(this.mViewPortHandler, this.xValue, this.yValue, this.mTrans, this.view);
    }
}
