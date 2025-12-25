package com.yucheng.smarthealthpro.customchart.utils;

import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import java.util.List;

/* loaded from: classes4.dex */
public final class FSize extends ObjectPool.Poolable {
    private static ObjectPool<FSize> pool;
    public float height;
    public float width;

    static {
        ObjectPool<FSize> objectPoolCreate = ObjectPool.create(256, new FSize(0.0f, 0.0f));
        pool = objectPoolCreate;
        objectPoolCreate.setReplenishPercentage(0.5f);
    }

    @Override // com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new FSize(0.0f, 0.0f);
    }

    public static FSize getInstance(final float width, final float height) {
        FSize fSize = (FSize) pool.get();
        fSize.width = width;
        fSize.height = height;
        return fSize;
    }

    public static void recycleInstance(FSize instance) {
        pool.recycle((ObjectPool<FSize>) instance);
    }

    public static void recycleInstances(List<FSize> instances) {
        pool.recycle(instances);
    }

    public FSize() {
    }

    public FSize(final float width, final float height) {
        this.width = width;
        this.height = height;
    }

    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FSize)) {
            return false;
        }
        FSize fSize = (FSize) obj;
        return this.width == fSize.width && this.height == fSize.height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.width) ^ Float.floatToIntBits(this.height);
    }
}
