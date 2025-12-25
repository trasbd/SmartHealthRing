package com.yucheng.smarthealthpro.customchart.utils;

import com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable;
import java.util.List;

/* loaded from: classes4.dex */
public class ObjectPool<T extends Poolable> {
    private static int ids;
    private int desiredCapacity;
    private T modelObject;
    private Object[] objects;
    private int objectsPointer;
    private int poolId;
    private float replenishPercentage;

    public static abstract class Poolable {
        public static int NO_OWNER = -1;
        int currentOwnerId = NO_OWNER;

        protected abstract Poolable instantiate();
    }

    public int getPoolId() {
        return this.poolId;
    }

    /* JADX WARN: In static synchronized method top region not synchronized by class const: (wrap:java.lang.Class:0x0000: CONST_CLASS  A[WRAPPED] com.yucheng.smarthealthpro.customchart.utils.ObjectPool.class) */
    public static synchronized ObjectPool create(int withCapacity, Poolable object) {
        ObjectPool objectPool;
        synchronized (ObjectPool.class) {
            objectPool = new ObjectPool(withCapacity, object);
            int i2 = ids;
            objectPool.poolId = i2;
            ids = i2 + 1;
        }
        return objectPool;
    }

    private ObjectPool(int withCapacity, T object) {
        if (withCapacity <= 0) {
            throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
        }
        this.desiredCapacity = withCapacity;
        this.objects = new Object[withCapacity];
        this.objectsPointer = 0;
        this.modelObject = object;
        this.replenishPercentage = 1.0f;
        refillPool();
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0006 A[PHI: r0
  0x0006: PHI (r0v2 float) = (r0v0 float), (r0v1 float) binds: [B:3:0x0004, B:6:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setReplenishPercentage(float r3) {
        /*
            r2 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L8
        L6:
            r3 = r0
            goto Le
        L8:
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto Le
            goto L6
        Le:
            r2.replenishPercentage = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.customchart.utils.ObjectPool.setReplenishPercentage(float):void");
    }

    public float getReplenishPercentage() {
        return this.replenishPercentage;
    }

    private void refillPool() {
        refillPool(this.replenishPercentage);
    }

    private void refillPool(float percentage) {
        int i2 = this.desiredCapacity;
        int i3 = (int) (i2 * percentage);
        if (i3 < 1) {
            i2 = 1;
        } else if (i3 <= i2) {
            i2 = i3;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            this.objects[i4] = this.modelObject.instantiate();
        }
        this.objectsPointer = i2 - 1;
    }

    public synchronized T get() {
        T t;
        if (this.objectsPointer == -1 && this.replenishPercentage > 0.0f) {
            refillPool();
        }
        t = (T) this.objects[this.objectsPointer];
        t.currentOwnerId = Poolable.NO_OWNER;
        this.objectsPointer--;
        return t;
    }

    public synchronized void recycle(T object) {
        if (object.currentOwnerId != Poolable.NO_OWNER) {
            if (object.currentOwnerId == this.poolId) {
                throw new IllegalArgumentException("The object passed is already stored in this pool!");
            }
            throw new IllegalArgumentException("The object to recycle already belongs to poolId " + object.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
        }
        int i2 = this.objectsPointer + 1;
        this.objectsPointer = i2;
        if (i2 >= this.objects.length) {
            resizePool();
        }
        object.currentOwnerId = this.poolId;
        this.objects[this.objectsPointer] = object;
    }

    public synchronized void recycle(List<T> objects) {
        while (objects.size() + this.objectsPointer + 1 > this.desiredCapacity) {
            resizePool();
        }
        int size = objects.size();
        for (int i2 = 0; i2 < size; i2++) {
            T t = objects.get(i2);
            if (t.currentOwnerId != Poolable.NO_OWNER) {
                if (t.currentOwnerId == this.poolId) {
                    throw new IllegalArgumentException("The object passed is already stored in this pool!");
                }
                throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
            }
            t.currentOwnerId = this.poolId;
            this.objects[this.objectsPointer + 1 + i2] = t;
        }
        this.objectsPointer += size;
    }

    private void resizePool() {
        int i2 = this.desiredCapacity;
        int i3 = i2 * 2;
        this.desiredCapacity = i3;
        Object[] objArr = new Object[i3];
        for (int i4 = 0; i4 < i2; i4++) {
            objArr[i4] = this.objects[i4];
        }
        this.objects = objArr;
    }

    public int getPoolCapacity() {
        return this.objects.length;
    }

    public int getPoolCount() {
        return this.objectsPointer + 1;
    }
}
