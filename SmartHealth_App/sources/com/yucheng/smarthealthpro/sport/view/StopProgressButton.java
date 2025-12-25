package com.yucheng.smarthealthpro.sport.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes5.dex */
public class StopProgressButton extends View {
    private boolean isFinish;
    private float mBigRingRadius;
    private int mCircleColor;
    private Paint mCirclePaint;
    private int mProgress;
    private ProgressButtonFinishCallback mProgressButtonFinishCallback;
    private float mRadius;
    private int mRingC3Color;
    private Paint mRingC3Paint;
    private int mRingColor;
    private Paint mRingPaint;
    private float mRingRadius;
    private float mStrokeWidth;
    private int mTotalProgress;
    private int mXCenter;
    private int mYCenter;
    private ValueAnimator startAnimator;
    private ValueAnimator stopAnimator;

    public interface ProgressButtonFinishCallback {
        void onCancel();

        void onFinish();
    }

    public StopProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTotalProgress = 300;
        initAttrs(context, attrs);
        initVariable();
        setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.sport.view.StopProgressButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == 0) {
                    StopProgressButton.this.startAnimationProgress(300);
                } else if (action == 1) {
                    if (StopProgressButton.this.mProgress >= 300 && !StopProgressButton.this.isFinish) {
                        StopProgressButton.this.mProgressButtonFinishCallback.onFinish();
                        return false;
                    }
                    if (StopProgressButton.this.mProgress != 300 && StopProgressButton.this.mProgress < 300) {
                        StopProgressButton stopProgressButton = StopProgressButton.this;
                        stopProgressButton.stopAnimationProgress(stopProgressButton.mProgress);
                        StopProgressButton.this.mProgressButtonFinishCallback.onCancel();
                    }
                }
                return false;
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TasksCompletedView, 0, 0);
        this.mRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.TasksCompletedView_radius, 80.0f);
        this.mStrokeWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.TasksCompletedView_strokeWidth, 10.0f);
        this.mCircleColor = typedArrayObtainStyledAttributes.getColor(R.styleable.TasksCompletedView_circleColor, -1);
        this.mRingColor = typedArrayObtainStyledAttributes.getColor(R.styleable.TasksCompletedView_ringColor, 13421772);
        this.mRingC3Color = typedArrayObtainStyledAttributes.getColor(R.styleable.TasksCompletedView_ringCColor, 13421772);
        float f2 = this.mRadius;
        float f3 = this.mStrokeWidth;
        this.mRingRadius = f2 + f3;
        this.mBigRingRadius = f2 + (f3 * 2.0f);
    }

    private void initVariable() {
        Paint paint = new Paint();
        this.mCirclePaint = paint;
        paint.setAntiAlias(true);
        this.mCirclePaint.setColor(this.mCircleColor);
        this.mCirclePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mRingPaint = paint2;
        paint2.setAntiAlias(true);
        this.mRingPaint.setColor(this.mRingColor);
        this.mRingPaint.setStyle(Paint.Style.STROKE);
        this.mRingPaint.setStrokeWidth(this.mStrokeWidth);
        Paint paint3 = new Paint();
        this.mRingC3Paint = paint3;
        paint3.setAntiAlias(true);
        this.mRingC3Paint.setColor(this.mRingC3Color);
        this.mRingC3Paint.setStyle(Paint.Style.STROKE);
        this.mRingC3Paint.setStrokeWidth(this.mStrokeWidth - 2.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mXCenter = getWidth() / 2;
        this.mYCenter = getHeight() / 2;
        int i2 = this.mProgress;
        if (i2 == 300) {
            RectF rectF = new RectF();
            rectF.left = this.mXCenter - this.mRingRadius;
            rectF.top = this.mYCenter - this.mRingRadius;
            rectF.right = this.mRingRadius + this.mXCenter;
            rectF.bottom = this.mRingRadius + this.mYCenter;
            return;
        }
        if (i2 > 0) {
            RectF rectF2 = new RectF();
            rectF2.left = this.mXCenter - this.mRingRadius;
            rectF2.top = this.mYCenter - this.mRingRadius;
            rectF2.right = this.mRingRadius + this.mXCenter;
            rectF2.bottom = this.mRingRadius + this.mYCenter;
            canvas.drawArc(rectF2, -90.0f, (this.mProgress / this.mTotalProgress) * 360.0f, false, this.mRingC3Paint);
        }
    }

    public void setListener(ProgressButtonFinishCallback progressButtonFinishCallback) {
        this.mProgressButtonFinishCallback = progressButtonFinishCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimationProgress(int progress) {
        this.isFinish = false;
        ValueAnimator valueAnimator = this.stopAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.stopAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, progress);
        this.startAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.sport.view.StopProgressButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                StopProgressButton.this.mProgress = ((Integer) animation.getAnimatedValue()).intValue();
                StopProgressButton.this.invalidate();
                if (StopProgressButton.this.mProgress < 300 || StopProgressButton.this.isFinish) {
                    return;
                }
                StopProgressButton.this.isFinish = true;
                StopProgressButton.this.mProgressButtonFinishCallback.onFinish();
                Log.v("startAnimationProgress", StopProgressButton.this.mProgress + "");
            }
        });
        this.startAnimator.setInterpolator(new OvershootInterpolator());
        this.startAnimator.setDuration(2000L);
        this.startAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAnimationProgress(int progress) {
        ValueAnimator valueAnimator = this.startAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.startAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(progress, 0);
        this.stopAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.sport.view.StopProgressButton.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                StopProgressButton.this.mProgress = ((Integer) animation.getAnimatedValue()).intValue();
                StopProgressButton.this.invalidate();
            }
        });
        this.stopAnimator.setInterpolator(new OvershootInterpolator());
        this.stopAnimator.setDuration(2000L);
        this.stopAnimator.start();
    }
}
