package com.yucheng.smarthealthpro.customchart.components;

import android.graphics.Paint;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Utils;

/* loaded from: classes4.dex */
public class Description extends ComponentBase {
    private MPPointF mPosition;
    private String text = "Description Label";
    private Paint.Align mTextAlign = Paint.Align.RIGHT;

    public Description() {
        this.mTextSize = Utils.convertDpToPixel(8.0f);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setPosition(float x, float y) {
        MPPointF mPPointF = this.mPosition;
        if (mPPointF == null) {
            this.mPosition = MPPointF.getInstance(x, y);
        } else {
            mPPointF.x = x;
            this.mPosition.y = y;
        }
    }

    public MPPointF getPosition() {
        return this.mPosition;
    }

    public void setTextAlign(Paint.Align align) {
        this.mTextAlign = align;
    }

    public Paint.Align getTextAlign() {
        return this.mTextAlign;
    }
}
