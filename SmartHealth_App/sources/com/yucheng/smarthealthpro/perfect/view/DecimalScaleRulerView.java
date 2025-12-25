package com.yucheng.smarthealthpro.perfect.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import com.yucheng.smarthealthpro.perfect.utils.DrawUtil;
import com.yucheng.smarthealthpro.perfect.utils.TextUtil;

/* loaded from: classes5.dex */
public class DecimalScaleRulerView extends View {
    protected int mHeight;
    protected int mItemSpacing;
    protected int mLastX;
    protected Paint mLinePaint;
    protected int mLineWidth;
    protected OnValueChangeListener mListener;
    protected int mMaxLineHeight;
    protected int mMaxOffset;
    protected float mMaxValue;
    protected int mMiddleLineHeight;
    protected int mMinLineHeight;
    protected float mMinValue;
    protected int mMinVelocity;
    protected int mMove;
    protected float mOffset;
    protected int mPerSpanValue;
    protected Scroller mScroller;
    protected float mTextHeight;
    protected int mTextMarginTop;
    protected Paint mTextPaint;
    protected int mTotalLine;
    protected float mValue;
    protected VelocityTracker mVelocityTracker;
    protected int mWidth;

    public interface OnValueChangeListener {
        void onValueChange(float value);
    }

    public DecimalScaleRulerView(Context context) {
        this(context, null);
    }

    public DecimalScaleRulerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DecimalScaleRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mValue = 50.0f;
        this.mMaxValue = 100.0f;
        this.mMinValue = 0.0f;
        this.mPerSpanValue = 1;
        init(context);
    }

    protected void init(Context context) {
        this.mScroller = new Scroller(context);
        this.mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
        this.mItemSpacing = DrawUtil.dip2px(14.0f);
        this.mLineWidth = DrawUtil.dip2px(2.0f);
        this.mMaxLineHeight = DrawUtil.dip2px(42.0f);
        this.mMiddleLineHeight = DrawUtil.dip2px(31.0f);
        this.mMinLineHeight = DrawUtil.dip2px(17.0f);
        this.mTextMarginTop = DrawUtil.dip2px(11.0f);
        Paint paint = new Paint(1);
        this.mTextPaint = paint;
        paint.setTextSize(DrawUtil.sp2px(16.0f));
        this.mTextPaint.setColor(-2145246686);
        this.mTextHeight = TextUtil.getFontHeight(this.mTextPaint);
        Paint paint2 = new Paint(1);
        this.mLinePaint = paint2;
        paint2.setStrokeWidth(this.mLineWidth);
        this.mLinePaint.setColor(-2145246686);
    }

    public void setParam(int itemSpacing, int maxLineHeight, int middleLineHeight, int minLineHeight, int textMarginTop, int textSize) {
        this.mItemSpacing = itemSpacing;
        this.mMaxLineHeight = maxLineHeight;
        this.mMiddleLineHeight = middleLineHeight;
        this.mMinLineHeight = minLineHeight;
        this.mTextMarginTop = textMarginTop;
        this.mTextPaint.setTextSize(textSize);
    }

    public void initViewParam(float defaultValue, float minValue, float maxValue, int spanValue) {
        this.mValue = defaultValue;
        this.mMaxValue = maxValue;
        this.mMinValue = minValue;
        this.mPerSpanValue = spanValue;
        int i2 = ((int) ((maxValue * 10.0f) - (minValue * 10.0f))) / spanValue;
        this.mTotalLine = i2 + 1;
        int i3 = this.mItemSpacing;
        this.mMaxOffset = (-i2) * i3;
        this.mOffset = ((minValue - defaultValue) / spanValue) * i3 * 10.0f;
        invalidate();
        setVisibility(0);
    }

    public void setValueChangeListener(OnValueChangeListener listener) {
        this.mListener = listener;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        super.onSizeChanged(w, h2, oldw, oldh);
        if (w <= 0 || h2 <= 0) {
            return;
        }
        this.mWidth = w;
        this.mHeight = h2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        int i3 = this.mWidth / 2;
        for (int i4 = 0; i4 < this.mTotalLine; i4++) {
            float f2 = i3;
            float f3 = this.mOffset + f2 + (this.mItemSpacing * i4);
            if (f3 >= 0.0f && f3 <= this.mWidth) {
                int i5 = i4 % 10;
                if (i5 == 0) {
                    i2 = this.mMaxLineHeight;
                } else if (i4 % 5 == 0) {
                    i2 = this.mMiddleLineHeight;
                } else {
                    i2 = this.mMinLineHeight;
                }
                float f4 = i2;
                float fAbs = 1.0f - (Math.abs(f3 - f2) / f2);
                int i6 = (int) (255.0f * fAbs * fAbs);
                this.mLinePaint.setAlpha(i6);
                canvas.drawLine(f3, 0.0f, f3, f4, this.mLinePaint);
                if (i5 == 0) {
                    String strValueOf = String.valueOf((int) (this.mMinValue + ((this.mPerSpanValue * i4) / 10)));
                    this.mTextPaint.setAlpha(i6);
                    canvas.drawText(strValueOf, f3 - (this.mTextPaint.measureText(strValueOf) / 2.0f), ((f4 + this.mTextMarginTop) + this.mTextHeight) - DrawUtil.dip2px(3.0f), this.mTextPaint);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0022, code lost:
    
        if (r0 != 3) goto L17;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            float r1 = r5.getX()
            int r1 = (int) r1
            android.view.VelocityTracker r2 = r4.mVelocityTracker
            if (r2 != 0) goto L13
            android.view.VelocityTracker r2 = android.view.VelocityTracker.obtain()
            r4.mVelocityTracker = r2
        L13:
            android.view.VelocityTracker r2 = r4.mVelocityTracker
            r2.addMovement(r5)
            r5 = 0
            r2 = 1
            if (r0 == 0) goto L35
            if (r0 == r2) goto L2e
            r3 = 2
            if (r0 == r3) goto L25
            r3 = 3
            if (r0 == r3) goto L2e
            goto L3e
        L25:
            int r5 = r4.mLastX
            int r5 = r5 - r1
            r4.mMove = r5
            r4.changeMoveAndValue()
            goto L3e
        L2e:
            r4.countMoveEnd()
            r4.countVelocityTracker()
            return r5
        L35:
            android.widget.Scroller r0 = r4.mScroller
            r0.forceFinished(r2)
            r4.mLastX = r1
            r4.mMove = r5
        L3e:
            r4.mLastX = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.perfect.view.DecimalScaleRulerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    protected void countVelocityTracker() {
        this.mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = this.mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) > this.mMinVelocity) {
            this.mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        }
    }

    protected void countMoveEnd() {
        float f2 = this.mOffset - this.mMove;
        this.mOffset = f2;
        int i2 = this.mMaxOffset;
        if (f2 <= i2) {
            this.mOffset = i2;
        } else if (f2 >= 0.0f) {
            this.mOffset = 0.0f;
        }
        this.mLastX = 0;
        this.mMove = 0;
        float f3 = this.mMinValue;
        int iRound = Math.round((Math.abs(this.mOffset) * 1.0f) / this.mItemSpacing);
        int i3 = this.mPerSpanValue;
        float f4 = f3 + ((iRound * i3) / 10.0f);
        this.mValue = f4;
        this.mOffset = (((this.mMinValue - f4) * 10.0f) / i3) * this.mItemSpacing;
        notifyValueChange();
        postInvalidate();
    }

    protected void changeMoveAndValue() {
        float f2 = this.mOffset - this.mMove;
        this.mOffset = f2;
        int i2 = this.mMaxOffset;
        if (f2 <= i2) {
            this.mOffset = i2;
            this.mMove = 0;
            this.mScroller.forceFinished(true);
        } else if (f2 >= 0.0f) {
            this.mOffset = 0.0f;
            this.mMove = 0;
            this.mScroller.forceFinished(true);
        }
        this.mValue = this.mMinValue + ((Math.round((Math.abs(this.mOffset) * 1.0f) / this.mItemSpacing) * this.mPerSpanValue) / 10.0f);
        notifyValueChange();
        postInvalidate();
    }

    protected void notifyValueChange() {
        OnValueChangeListener onValueChangeListener = this.mListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.onValueChange(this.mValue);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.mScroller.computeScrollOffset()) {
            if (this.mScroller.getCurrX() == this.mScroller.getFinalX()) {
                countMoveEnd();
                return;
            }
            int currX = this.mScroller.getCurrX();
            this.mMove = this.mLastX - currX;
            changeMoveAndValue();
            this.mLastX = currX;
        }
    }
}
