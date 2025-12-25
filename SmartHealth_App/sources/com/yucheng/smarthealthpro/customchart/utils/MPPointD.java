package com.yucheng.smarthealthpro.customchart.utils;

import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import java.util.List;

/* loaded from: classes4.dex */
public class MPPointD extends ObjectPool.Poolable {
    private static ObjectPool<MPPointD> pool;
    public double x;
    public double y;

    static {
        ObjectPool<MPPointD> objectPoolCreate = ObjectPool.create(64, new MPPointD(0.0d, 0.0d));
        pool = objectPoolCreate;
        objectPoolCreate.setReplenishPercentage(0.5f);
    }

    public static MPPointD getInstance(double x, double y) {
        MPPointD mPPointD = (MPPointD) pool.get();
        mPPointD.x = x;
        mPPointD.y = y;
        return mPPointD;
    }

    public static void recycleInstance(MPPointD instance) {
        pool.recycle((ObjectPool<MPPointD>) instance);
    }

    public static void recycleInstances(List<MPPointD> instances) {
        pool.recycle(instances);
    }

    @Override // com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new MPPointD(0.0d, 0.0d);
    }

    private MPPointD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "MPPointD, x: " + this.x + ", y: " + this.y;
    }
}
