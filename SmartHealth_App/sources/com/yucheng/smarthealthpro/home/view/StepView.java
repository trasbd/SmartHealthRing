package com.yucheng.smarthealthpro.home.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes5.dex */
public class StepView extends View {
    private boolean isGoneText;
    private int mBorderWidth;
    private int mCurrentStep;
    private Paint mInnPaint;
    private int mInnerColor;
    private Paint mOutPaint;
    private int mOuterColor;
    private int mStepMax;
    private int mStepTextColor;
    private int mStepTextSize;
    private Paint mTextPaint;

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mOuterColor = SupportMenu.CATEGORY_MASK;
        this.mInnerColor = -16776961;
        this.mBorderWidth = 20;
        this.mStepMax = DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME;
        this.mCurrentStep = 0;
        this.isGoneText = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.StepView);
        this.mOuterColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StepView_outerColor, this.mOuterColor);
        this.mInnerColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StepView_innerColor, this.mInnerColor);
        this.mBorderWidth = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.StepView_borderWidth, this.mBorderWidth);
        this.mStepTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.StepView_stepTextSize, this.mStepTextSize);
        this.mStepTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StepView_stepTextColor, this.mStepTextColor);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mOutPaint = paint;
        paint.setAntiAlias(true);
        this.mOutPaint.setStrokeWidth(this.mBorderWidth);
        this.mOutPaint.setColor(this.mOuterColor);
        this.mOutPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mOutPaint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.mInnPaint = paint2;
        paint2.setAntiAlias(true);
        this.mInnPaint.setStrokeWidth(this.mBorderWidth);
        this.mInnPaint.setColor(this.mInnerColor);
        this.mInnPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mInnPaint.setStyle(Paint.Style.STROKE);
        Paint paint3 = new Paint();
        this.mTextPaint = paint3;
        paint3.setAntiAlias(true);
        this.mTextPaint.setColor(this.mStepTextColor);
        this.mTextPaint.setTextSize(this.mStepTextSize);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int i2 = size > size2 ? size2 : size;
        if (size > size2) {
            size = size2;
        }
        setMeasuredDimension(i2, size);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int width2 = (getWidth() / 2) - (this.mBorderWidth / 2);
        float f2 = width - width2;
        float f3 = width + width2;
        RectF rectF = new RectF(f2, f2, f3, f3);
        canvas.drawArc(rectF, -90.0f, 360.0f, false, this.mOutPaint);
        int i2 = this.mStepMax;
        if (i2 == 0) {
            return;
        }
        canvas.drawArc(rectF, -90.0f, (this.mCurrentStep / i2) * 360.0f, false, this.mInnPaint);
        String str = this.mCurrentStep + "";
        Rect rect = new Rect();
        this.mTextPaint.getTextBounds(str, 0, str.length(), rect);
        int width3 = (getWidth() / 2) - (rect.width() / 2);
        Paint.FontMetricsInt fontMetricsInt = this.mTextPaint.getFontMetricsInt();
        int width4 = (getWidth() / 2) + (((fontMetricsInt.bottom - fontMetricsInt.top) / 2) - fontMetricsInt.bottom);
        if (this.isGoneText) {
            return;
        }
        canvas.drawText(str, width3, width4, this.mTextPaint);
    }

    public synchronized void setStepMax(int stepMax) {
        this.mStepMax = stepMax;
    }

    public synchronized void isGoneText(boolean isGoneTexts) {
        this.isGoneText = isGoneTexts;
    }

    public synchronized void setCurrentStep(int currentStep) {
        this.mCurrentStep = currentStep;
        invalidate();
    }
}
