package com.yucheng.smarthealthpro.customchart.jobs;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public abstract class AnimatedViewPortJob extends ViewPortJob implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    protected ObjectAnimator animator;
    protected float phase;
    protected float xOrigin;
    protected float yOrigin;

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animation) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animation) {
    }

    public void onAnimationUpdate(ValueAnimator animation) {
    }

    public abstract void recycleSelf();

    public AnimatedViewPortJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v, float xOrigin, float yOrigin, long duration) {
        super(viewPortHandler, xValue, yValue, trans, v);
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, TypedValues.CycleType.S_WAVE_PHASE, 0.0f, 1.0f);
        this.animator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(duration);
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.animator.start();
    }

    public float getPhase() {
        return this.phase;
    }

    public void setPhase(float phase) {
        this.phase = phase;
    }

    public float getXOrigin() {
        return this.xOrigin;
    }

    public float getYOrigin() {
        return this.yOrigin;
    }

    protected void resetAnimator() {
        this.animator.removeAllListeners();
        this.animator.removeAllUpdateListeners();
        this.animator.reverse();
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animation) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }
}
