package com.yucheng.smarthealthpro.home.activity.ecg.photo;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* loaded from: classes5.dex */
class CustomGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = -1;
    private int mActivePointerIndex = 0;
    private final ScaleGestureDetector mDetector;
    private boolean mIsDragging;
    private float mLastTouchX;
    private float mLastTouchY;
    private OnGestureListener mListener;
    private final float mMinimumVelocity;
    private final float mTouchSlop;
    private VelocityTracker mVelocityTracker;

    CustomGestureDetector(Context context, OnGestureListener listener) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mListener = listener;
        this.mDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.photo.CustomGestureDetector.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector detector) {
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector detector) {
                float scaleFactor = detector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                if (scaleFactor < 0.0f) {
                    return true;
                }
                CustomGestureDetector.this.mListener.onScale(scaleFactor, detector.getFocusX(), detector.getFocusY());
                return true;
            }
        });
    }

    private float getActiveX(MotionEvent ev) {
        try {
            return ev.getX(this.mActivePointerIndex);
        } catch (Exception unused) {
            return ev.getX();
        }
    }

    private float getActiveY(MotionEvent ev) {
        try {
            return ev.getY(this.mActivePointerIndex);
        } catch (Exception unused) {
            return ev.getY();
        }
    }

    public boolean isScaling() {
        return this.mDetector.isInProgress();
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        try {
            this.mDetector.onTouchEvent(ev);
            return processTouchEvent(ev);
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    private boolean processTouchEvent(MotionEvent ev) {
        int action = ev.getAction() & 255;
        if (action == 0) {
            this.mActivePointerId = ev.getPointerId(0);
            VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
            this.mVelocityTracker = velocityTrackerObtain;
            if (velocityTrackerObtain != null) {
                velocityTrackerObtain.addMovement(ev);
            }
            this.mLastTouchX = getActiveX(ev);
            this.mLastTouchY = getActiveY(ev);
            this.mIsDragging = false;
        } else if (action == 1) {
            this.mActivePointerId = -1;
            if (this.mIsDragging && this.mVelocityTracker != null) {
                this.mLastTouchX = getActiveX(ev);
                this.mLastTouchY = getActiveY(ev);
                this.mVelocityTracker.addMovement(ev);
                this.mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = this.mVelocityTracker.getXVelocity();
                float yVelocity = this.mVelocityTracker.getYVelocity();
                if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.mMinimumVelocity) {
                    this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -xVelocity, -yVelocity);
                }
            }
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
            }
        } else if (action == 2) {
            float activeX = getActiveX(ev);
            float activeY = getActiveY(ev);
            float f2 = activeX - this.mLastTouchX;
            float f3 = activeY - this.mLastTouchY;
            if (!this.mIsDragging) {
                this.mIsDragging = Math.sqrt((double) ((f2 * f2) + (f3 * f3))) >= ((double) this.mTouchSlop);
            }
            if (this.mIsDragging) {
                this.mListener.onDrag(f2, f3);
                this.mLastTouchX = activeX;
                this.mLastTouchY = activeY;
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.addMovement(ev);
                }
            }
        } else if (action == 3) {
            this.mActivePointerId = -1;
            VelocityTracker velocityTracker3 = this.mVelocityTracker;
            if (velocityTracker3 != null) {
                velocityTracker3.recycle();
                this.mVelocityTracker = null;
            }
        } else if (action == 6) {
            int pointerIndex = Util.getPointerIndex(ev.getAction());
            if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
                int i2 = pointerIndex == 0 ? 1 : 0;
                this.mActivePointerId = ev.getPointerId(i2);
                this.mLastTouchX = ev.getX(i2);
                this.mLastTouchY = ev.getY(i2);
            }
        }
        int i3 = this.mActivePointerId;
        this.mActivePointerIndex = ev.findPointerIndex(i3 != -1 ? i3 : 0);
        return true;
    }
}
