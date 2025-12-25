package com.yucheng.smarthealthpro.perfect.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.yucheng.smarthealthpro.perfect.utils.DrawUtil;
import com.yucheng.smarthealthpro.utils.FormatUtil;

/* loaded from: classes5.dex */
public class DecimalScaleRulerViewZhiLian extends DecimalScaleRulerView {
    public DecimalScaleRulerViewZhiLian(Context context) {
        this(context, null);
    }

    public DecimalScaleRulerViewZhiLian(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DecimalScaleRulerViewZhiLian(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override // com.yucheng.smarthealthpro.perfect.view.DecimalScaleRulerView
    protected void init(Context context) {
        super.init(context);
        this.mLineWidth = DrawUtil.dip2px(4.0f);
        this.mLinePaint = new Paint(1);
        this.mLinePaint.setStrokeWidth(this.mLineWidth);
        this.mLinePaint.setColor(-2145246686);
    }

    @Override // com.yucheng.smarthealthpro.perfect.view.DecimalScaleRulerView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        int i3 = this.mWidth / 2;
        canvas.drawLine(0.0f, 0.0f, this.mWidth, 0.0f, this.mLinePaint);
        for (int i4 = 0; i4 < this.mTotalLine; i4++) {
            float f2 = i3 + this.mOffset + (this.mItemSpacing * i4);
            if (f2 >= 0.0f && f2 <= this.mWidth) {
                int i5 = i4 % 10;
                if (i5 == 0) {
                    i2 = this.mMaxLineHeight;
                } else if (i4 % 5 == 0) {
                    i2 = this.mMiddleLineHeight;
                } else {
                    int i6 = this.mMinLineHeight;
                }
                float f3 = i2;
                canvas.drawLine(f2, 0.0f, f2, f3, this.mLinePaint);
                if (i5 == 0) {
                    String strKeep2 = FormatUtil.keep2((this.mMinValue + ((this.mPerSpanValue * i4) / 10)) / 100.0f);
                    canvas.drawText(strKeep2, f2 - (this.mTextPaint.measureText(strKeep2) / 2.0f), ((f3 + this.mTextMarginTop) + this.mTextHeight) - DrawUtil.dip2px(3.0f), this.mTextPaint);
                }
            }
        }
    }
}
