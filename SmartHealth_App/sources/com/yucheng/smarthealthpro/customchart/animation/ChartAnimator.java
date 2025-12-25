package com.yucheng.smarthealthpro.customchart.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import com.yucheng.smarthealthpro.customchart.animation.Easing;

/* loaded from: classes4.dex */
public class ChartAnimator {
    private ValueAnimator.AnimatorUpdateListener mListener;
    protected float mPhaseY = 1.0f;
    protected float mPhaseX = 1.0f;

    public ChartAnimator() {
    }

    public ChartAnimator(ValueAnimator.AnimatorUpdateListener listener) {
        this.mListener = listener;
    }

    private ObjectAnimator xAnimator(int duration, Easing.EasingFunction easing) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        objectAnimatorOfFloat.setInterpolator(easing);
        objectAnimatorOfFloat.setDuration(duration);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator yAnimator(int duration, Easing.EasingFunction easing) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        objectAnimatorOfFloat.setInterpolator(easing);
        objectAnimatorOfFloat.setDuration(duration);
        return objectAnimatorOfFloat;
    }

    public void animateX(int durationMillis) {
        animateX(durationMillis, Easing.Linear);
    }

    public void animateX(int durationMillis, Easing.EasingFunction easing) {
        ObjectAnimator objectAnimatorXAnimator = xAnimator(durationMillis, easing);
        objectAnimatorXAnimator.addUpdateListener(this.mListener);
        objectAnimatorXAnimator.start();
    }

    public void animateXY(int durationMillisX, int durationMillisY) {
        animateXY(durationMillisX, durationMillisY, Easing.Linear, Easing.Linear);
    }

    public void animateXY(int durationMillisX, int durationMillisY, Easing.EasingFunction easing) {
        ObjectAnimator objectAnimatorXAnimator = xAnimator(durationMillisX, easing);
        ObjectAnimator objectAnimatorYAnimator = yAnimator(durationMillisY, easing);
        if (durationMillisX > durationMillisY) {
            objectAnimatorXAnimator.addUpdateListener(this.mListener);
        } else {
            objectAnimatorYAnimator.addUpdateListener(this.mListener);
        }
        objectAnimatorXAnimator.start();
        objectAnimatorYAnimator.start();
    }

    public void animateXY(int durationMillisX, int durationMillisY, Easing.EasingFunction easingX, Easing.EasingFunction easingY) {
        ObjectAnimator objectAnimatorXAnimator = xAnimator(durationMillisX, easingX);
        ObjectAnimator objectAnimatorYAnimator = yAnimator(durationMillisY, easingY);
        if (durationMillisX > durationMillisY) {
            objectAnimatorXAnimator.addUpdateListener(this.mListener);
        } else {
            objectAnimatorYAnimator.addUpdateListener(this.mListener);
        }
        objectAnimatorXAnimator.start();
        objectAnimatorYAnimator.start();
    }

    public void animateY(int durationMillis) {
        animateY(durationMillis, Easing.Linear);
    }

    public void animateY(int durationMillis, Easing.EasingFunction easing) {
        ObjectAnimator objectAnimatorYAnimator = yAnimator(durationMillis, easing);
        objectAnimatorYAnimator.addUpdateListener(this.mListener);
        objectAnimatorYAnimator.start();
    }

    public float getPhaseY() {
        return this.mPhaseY;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0006 A[PHI: r0
  0x0006: PHI (r0v2 float) = (r0v0 float), (r0v1 float) binds: [B:3:0x0004, B:6:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setPhaseY(float r3) {
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
            r2.mPhaseY = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.customchart.animation.ChartAnimator.setPhaseY(float):void");
    }

    public float getPhaseX() {
        return this.mPhaseX;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0006 A[PHI: r0
  0x0006: PHI (r0v2 float) = (r0v0 float), (r0v1 float) binds: [B:3:0x0004, B:6:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setPhaseX(float r3) {
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
            r2.mPhaseX = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.customchart.animation.ChartAnimator.setPhaseX(float):void");
    }
}
