package com.yucheng.smarthealthpro.customchart.highlight;

import com.yucheng.smarthealthpro.customchart.components.YAxis;

/* loaded from: classes4.dex */
public class Highlight {
    private YAxis.AxisDependency axis;
    private int mDataIndex;
    private int mDataSetIndex;
    private float mDrawX;
    private float mDrawY;
    private int mStackIndex;
    private float mX;
    private float mXPx;
    private float mY;
    private float mYPx;

    public Highlight(float x, float y, int dataSetIndex, int dataIndex) {
        this.mStackIndex = -1;
        this.mX = x;
        this.mY = y;
        this.mDataSetIndex = dataSetIndex;
        this.mDataIndex = dataIndex;
    }

    public Highlight(float x, float y, int dataSetIndex) {
        this.mStackIndex = -1;
        this.mX = x;
        this.mY = y;
        this.mDataSetIndex = dataSetIndex;
        this.mDataIndex = -1;
    }

    public Highlight(float x, int dataSetIndex, int stackIndex) {
        this(x, Float.NaN, dataSetIndex);
        this.mStackIndex = stackIndex;
    }

    public Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, YAxis.AxisDependency axis) {
        this.mDataIndex = -1;
        this.mStackIndex = -1;
        this.mX = x;
        this.mY = y;
        this.mXPx = xPx;
        this.mYPx = yPx;
        this.mDataSetIndex = dataSetIndex;
        this.axis = axis;
    }

    public Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, int stackIndex, YAxis.AxisDependency axis) {
        this(x, y, xPx, yPx, dataSetIndex, axis);
        this.mStackIndex = stackIndex;
    }

    public float getX() {
        return this.mX;
    }

    public float getY() {
        return this.mY;
    }

    public float getXPx() {
        return this.mXPx;
    }

    public float getYPx() {
        return this.mYPx;
    }

    public int getDataIndex() {
        return this.mDataIndex;
    }

    public void setDataIndex(int mDataIndex) {
        this.mDataIndex = mDataIndex;
    }

    public int getDataSetIndex() {
        return this.mDataSetIndex;
    }

    public int getStackIndex() {
        return this.mStackIndex;
    }

    public boolean isStacked() {
        return this.mStackIndex >= 0;
    }

    public YAxis.AxisDependency getAxis() {
        return this.axis;
    }

    public void setDraw(float x, float y) {
        this.mDrawX = x;
        this.mDrawY = y;
    }

    public float getDrawX() {
        return this.mDrawX;
    }

    public float getDrawY() {
        return this.mDrawY;
    }

    public boolean equalTo(Highlight h2) {
        return h2 != null && this.mDataSetIndex == h2.mDataSetIndex && this.mX == h2.mX && this.mStackIndex == h2.mStackIndex && this.mDataIndex == h2.mDataIndex;
    }

    public String toString() {
        return "Highlight, x: " + this.mX + ", y: " + this.mY + ", dataSetIndex: " + this.mDataSetIndex + ", stackIndex (only stacked barentry): " + this.mStackIndex;
    }
}
