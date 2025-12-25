package com.yucheng.smarthealthpro.customchart.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.yucheng.smarthealthpro.customchart.utils.ObjectPool;
import java.util.List;

/* loaded from: classes4.dex */
public class MPPointF extends ObjectPool.Poolable {
    public static final Parcelable.Creator<MPPointF> CREATOR;
    private static ObjectPool<MPPointF> pool;
    public float x;
    public float y;

    static {
        ObjectPool<MPPointF> objectPoolCreate = ObjectPool.create(32, new MPPointF(0.0f, 0.0f));
        pool = objectPoolCreate;
        objectPoolCreate.setReplenishPercentage(0.5f);
        CREATOR = new Parcelable.Creator<MPPointF>() { // from class: com.yucheng.smarthealthpro.customchart.utils.MPPointF.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MPPointF createFromParcel(Parcel in) {
                MPPointF mPPointF = new MPPointF(0.0f, 0.0f);
                mPPointF.my_readFromParcel(in);
                return mPPointF;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MPPointF[] newArray(int size) {
                return new MPPointF[size];
            }
        };
    }

    public MPPointF() {
    }

    public MPPointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static MPPointF getInstance(float x, float y) {
        MPPointF mPPointF = (MPPointF) pool.get();
        mPPointF.x = x;
        mPPointF.y = y;
        return mPPointF;
    }

    public static MPPointF getInstance() {
        return (MPPointF) pool.get();
    }

    public static MPPointF getInstance(MPPointF copy) {
        MPPointF mPPointF = (MPPointF) pool.get();
        mPPointF.x = copy.x;
        mPPointF.y = copy.y;
        return mPPointF;
    }

    public static void recycleInstance(MPPointF instance) {
        pool.recycle((ObjectPool<MPPointF>) instance);
    }

    public static void recycleInstances(List<MPPointF> instances) {
        pool.recycle(instances);
    }

    public void my_readFromParcel(Parcel in) {
        this.x = in.readFloat();
        this.y = in.readFloat();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override // com.yucheng.smarthealthpro.customchart.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new MPPointF(0.0f, 0.0f);
    }
}
