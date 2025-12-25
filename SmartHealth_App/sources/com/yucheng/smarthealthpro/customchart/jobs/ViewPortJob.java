package com.yucheng.smarthealthpro.customchart.jobs;

import android.view.View;
import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public abstract class ViewPortJob extends ObjectPool.Poolable implements Runnable {
    protected Transformer mTrans;
    protected ViewPortHandler mViewPortHandler;
    protected float[] pts = new float[2];
    protected View view;
    protected float xValue;
    protected float yValue;

    public ViewPortJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) {
        this.mViewPortHandler = viewPortHandler;
        this.xValue = xValue;
        this.yValue = yValue;
        this.mTrans = trans;
        this.view = v;
    }

    public float getXValue() {
        return this.xValue;
    }

    public float getYValue() {
        return this.yValue;
    }
}
