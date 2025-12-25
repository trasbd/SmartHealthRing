package com.yucheng.smarthealthpro.customchart.buffer;

/* loaded from: classes4.dex */
public abstract class AbstractBuffer<T> {
    public final float[] buffer;
    protected float phaseX = 1.0f;
    protected float phaseY = 1.0f;
    protected int mFrom = 0;
    protected int mTo = 0;
    protected int index = 0;

    public abstract void feed(T data);

    public AbstractBuffer(int size) {
        this.buffer = new float[size];
    }

    public void limitFrom(int from) {
        if (from < 0) {
            from = 0;
        }
        this.mFrom = from;
    }

    public void limitTo(int to) {
        if (to < 0) {
            to = 0;
        }
        this.mTo = to;
    }

    public void reset() {
        this.index = 0;
    }

    public int size() {
        return this.buffer.length;
    }

    public void setPhases(float phaseX, float phaseY) {
        this.phaseX = phaseX;
        this.phaseY = phaseY;
    }
}
