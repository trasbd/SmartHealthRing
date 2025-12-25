package com.yucheng.smarthealthpro.customchart.listener;

import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase;
import com.yucheng.smarthealthpro.customchart.charts.HorizontalBarChart;
import com.yucheng.smarthealthpro.customchart.data.BarLineScatterCandleBubbleData;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet;
import com.yucheng.smarthealthpro.customchart.listener.ChartTouchListener;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public class BarLineChartTouchListener extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {
    private IDataSet mClosestDataSetToTouch;
    private MPPointF mDecelerationCurrentPoint;
    private long mDecelerationLastTime;
    private MPPointF mDecelerationVelocity;
    private float mDragTriggerDist;
    private Matrix mMatrix;
    private float mMinScalePointerDistance;
    private float mSavedDist;
    private Matrix mSavedMatrix;
    private float mSavedXDist;
    private float mSavedYDist;
    private MPPointF mTouchPointCenter;
    private MPPointF mTouchStartPoint;
    private VelocityTracker mVelocityTracker;

    public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> chart, Matrix touchMatrix, float dragTriggerDistance) {
        super(chart);
        this.mMatrix = new Matrix();
        this.mSavedMatrix = new Matrix();
        this.mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);
        this.mTouchPointCenter = MPPointF.getInstance(0.0f, 0.0f);
        this.mSavedXDist = 1.0f;
        this.mSavedYDist = 1.0f;
        this.mSavedDist = 1.0f;
        this.mDecelerationLastTime = 0L;
        this.mDecelerationCurrentPoint = MPPointF.getInstance(0.0f, 0.0f);
        this.mDecelerationVelocity = MPPointF.getInstance(0.0f, 0.0f);
        this.mMatrix = touchMatrix;
        this.mDragTriggerDist = Utils.convertDpToPixel(dragTriggerDistance);
        this.mMinScalePointerDistance = Utils.convertDpToPixel(3.5f);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        VelocityTracker velocityTracker;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(event);
        if (event.getActionMasked() == 3 && (velocityTracker = this.mVelocityTracker) != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        if (this.mTouchMode == 0) {
            this.mGestureDetector.onTouchEvent(event);
        }
        if (!((BarLineChartBase) this.mChart).isDragEnabled() && !((BarLineChartBase) this.mChart).isScaleXEnabled() && !((BarLineChartBase) this.mChart).isScaleYEnabled()) {
            return true;
        }
        int action = event.getAction() & 255;
        if (action == 0) {
            startAction(event);
            stopDeceleration();
            saveTouchStart(event);
        } else if (action == 1) {
            VelocityTracker velocityTracker2 = this.mVelocityTracker;
            int pointerId = event.getPointerId(0);
            velocityTracker2.computeCurrentVelocity(1000, Utils.getMaximumFlingVelocity());
            float yVelocity = velocityTracker2.getYVelocity(pointerId);
            float xVelocity = velocityTracker2.getXVelocity(pointerId);
            if ((Math.abs(xVelocity) > Utils.getMinimumFlingVelocity() || Math.abs(yVelocity) > Utils.getMinimumFlingVelocity()) && this.mTouchMode == 1 && ((BarLineChartBase) this.mChart).isDragDecelerationEnabled()) {
                stopDeceleration();
                this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                this.mDecelerationCurrentPoint.x = event.getX();
                this.mDecelerationCurrentPoint.y = event.getY();
                this.mDecelerationVelocity.x = xVelocity;
                this.mDecelerationVelocity.y = yVelocity;
                Utils.postInvalidateOnAnimation(this.mChart);
            }
            if (this.mTouchMode == 2 || this.mTouchMode == 3 || this.mTouchMode == 4 || this.mTouchMode == 5) {
                ((BarLineChartBase) this.mChart).calculateOffsets();
                ((BarLineChartBase) this.mChart).postInvalidate();
            }
            this.mTouchMode = 0;
            ((BarLineChartBase) this.mChart).enableScroll();
            VelocityTracker velocityTracker3 = this.mVelocityTracker;
            if (velocityTracker3 != null) {
                velocityTracker3.recycle();
                this.mVelocityTracker = null;
            }
            endAction(event);
        } else if (action != 2) {
            if (action == 3) {
                this.mTouchMode = 0;
                endAction(event);
            } else if (action != 5) {
                if (action == 6) {
                    Utils.velocityTrackerPointerUpCleanUpIfNecessary(event, this.mVelocityTracker);
                    this.mTouchMode = 5;
                }
            } else if (event.getPointerCount() >= 2) {
                ((BarLineChartBase) this.mChart).disableScroll();
                saveTouchStart(event);
                this.mSavedXDist = getXDist(event);
                this.mSavedYDist = getYDist(event);
                float fSpacing = spacing(event);
                this.mSavedDist = fSpacing;
                if (fSpacing > 10.0f) {
                    if (((BarLineChartBase) this.mChart).isPinchZoomEnabled()) {
                        this.mTouchMode = 4;
                    } else if (((BarLineChartBase) this.mChart).isScaleXEnabled() != ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                        this.mTouchMode = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? 2 : 3;
                    } else {
                        this.mTouchMode = this.mSavedXDist > this.mSavedYDist ? 2 : 3;
                    }
                }
                midPoint(this.mTouchPointCenter, event);
            }
        } else if (this.mTouchMode == 1) {
            ((BarLineChartBase) this.mChart).disableScroll();
            performDrag(event, ((BarLineChartBase) this.mChart).isDragXEnabled() ? event.getX() - this.mTouchStartPoint.x : 0.0f, ((BarLineChartBase) this.mChart).isDragYEnabled() ? event.getY() - this.mTouchStartPoint.y : 0.0f);
        } else if (this.mTouchMode == 2 || this.mTouchMode == 3 || this.mTouchMode == 4) {
            ((BarLineChartBase) this.mChart).disableScroll();
            if (((BarLineChartBase) this.mChart).isScaleXEnabled() || ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                performZoom(event);
            }
        } else if (this.mTouchMode == 0 && Math.abs(distance(event.getX(), this.mTouchStartPoint.x, event.getY(), this.mTouchStartPoint.y)) > this.mDragTriggerDist && ((BarLineChartBase) this.mChart).isDragEnabled()) {
            if (!((BarLineChartBase) this.mChart).isFullyZoomedOut() || !((BarLineChartBase) this.mChart).hasNoDragOffset()) {
                float fAbs = Math.abs(event.getX() - this.mTouchStartPoint.x);
                float fAbs2 = Math.abs(event.getY() - this.mTouchStartPoint.y);
                if ((((BarLineChartBase) this.mChart).isDragXEnabled() || fAbs2 >= fAbs) && (((BarLineChartBase) this.mChart).isDragYEnabled() || fAbs2 <= fAbs)) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                    this.mTouchMode = 1;
                }
            } else if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                    performHighlightDrag(event);
                }
            }
        }
        this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, true);
        return true;
    }

    private void saveTouchStart(MotionEvent event) {
        this.mSavedMatrix.set(this.mMatrix);
        this.mTouchStartPoint.x = event.getX();
        this.mTouchStartPoint.y = event.getY();
        this.mClosestDataSetToTouch = ((BarLineChartBase) this.mChart).getDataSetByTouchPoint(event.getX(), event.getY());
    }

    private void performDrag(MotionEvent event, float distanceX, float distanceY) {
        this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
        this.mMatrix.set(this.mSavedMatrix);
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (inverted()) {
            if (this.mChart instanceof HorizontalBarChart) {
                distanceX = -distanceX;
            } else {
                distanceY = -distanceY;
            }
        }
        this.mMatrix.postTranslate(distanceX, distanceY);
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartTranslate(event, distanceX, distanceY);
        }
    }

    private void performZoom(MotionEvent event) {
        boolean zCanZoomInMoreY;
        boolean zCanZoomInMoreX;
        boolean zCanZoomInMoreX2;
        boolean zCanZoomInMoreY2;
        if (event.getPointerCount() >= 2) {
            OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
            float fSpacing = spacing(event);
            if (fSpacing > this.mMinScalePointerDistance) {
                MPPointF trans = getTrans(this.mTouchPointCenter.x, this.mTouchPointCenter.y);
                ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
                if (this.mTouchMode == 4) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.PINCH_ZOOM;
                    float f2 = fSpacing / this.mSavedDist;
                    boolean z = f2 < 1.0f;
                    if (z) {
                        zCanZoomInMoreX2 = viewPortHandler.canZoomOutMoreX();
                    } else {
                        zCanZoomInMoreX2 = viewPortHandler.canZoomInMoreX();
                    }
                    if (z) {
                        zCanZoomInMoreY2 = viewPortHandler.canZoomOutMoreY();
                    } else {
                        zCanZoomInMoreY2 = viewPortHandler.canZoomInMoreY();
                    }
                    float f3 = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? f2 : 1.0f;
                    float f4 = ((BarLineChartBase) this.mChart).isScaleYEnabled() ? f2 : 1.0f;
                    if (zCanZoomInMoreY2 || zCanZoomInMoreX2) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(f3, f4, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(event, f3, f4);
                        }
                    }
                } else if (this.mTouchMode == 2 && ((BarLineChartBase) this.mChart).isScaleXEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.X_ZOOM;
                    float xDist = getXDist(event) / this.mSavedXDist;
                    if (xDist < 1.0f) {
                        zCanZoomInMoreX = viewPortHandler.canZoomOutMoreX();
                    } else {
                        zCanZoomInMoreX = viewPortHandler.canZoomInMoreX();
                    }
                    if (zCanZoomInMoreX) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(xDist, 1.0f, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(event, xDist, 1.0f);
                        }
                    }
                } else if (this.mTouchMode == 3 && ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.Y_ZOOM;
                    float yDist = getYDist(event) / this.mSavedYDist;
                    if (yDist < 1.0f) {
                        zCanZoomInMoreY = viewPortHandler.canZoomOutMoreY();
                    } else {
                        zCanZoomInMoreY = viewPortHandler.canZoomInMoreY();
                    }
                    if (zCanZoomInMoreY) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(1.0f, yDist, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(event, 1.0f, yDist);
                        }
                    }
                }
                MPPointF.recycleInstance(trans);
            }
        }
    }

    private void performHighlightDrag(MotionEvent e2) {
        Highlight highlightByTouchPoint = ((BarLineChartBase) this.mChart).getHighlightByTouchPoint(e2.getX(), e2.getY());
        if (highlightByTouchPoint == null || highlightByTouchPoint.equalTo(this.mLastHighlighted)) {
            return;
        }
        this.mLastHighlighted = highlightByTouchPoint;
        ((BarLineChartBase) this.mChart).highlightValue(highlightByTouchPoint, true);
    }

    private static void midPoint(MPPointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.x = x / 2.0f;
        point.y = y / 2.0f;
    }

    private static float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private static float getXDist(MotionEvent e2) {
        return Math.abs(e2.getX(0) - e2.getX(1));
    }

    private static float getYDist(MotionEvent e2) {
        return Math.abs(e2.getY(0) - e2.getY(1));
    }

    public MPPointF getTrans(float x, float y) {
        float f2;
        ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
        float fOffsetLeft = x - viewPortHandler.offsetLeft();
        if (inverted()) {
            f2 = -(y - viewPortHandler.offsetTop());
        } else {
            f2 = -((((BarLineChartBase) this.mChart).getMeasuredHeight() - y) - viewPortHandler.offsetBottom());
        }
        return MPPointF.getInstance(fOffsetLeft, f2);
    }

    private boolean inverted() {
        return (this.mClosestDataSetToTouch == null && ((BarLineChartBase) this.mChart).isAnyAxisInverted()) || (this.mClosestDataSetToTouch != null && ((BarLineChartBase) this.mChart).isInverted(this.mClosestDataSetToTouch.getAxisDependency()));
    }

    public Matrix getMatrix() {
        return this.mMatrix;
    }

    public void setDragTriggerDist(float dragTriggerDistance) {
        this.mDragTriggerDist = Utils.convertDpToPixel(dragTriggerDistance);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent e2) {
        this.mLastGesture = ChartTouchListener.ChartGesture.DOUBLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartDoubleTapped(e2);
        }
        if (((BarLineChartBase) this.mChart).isDoubleTapToZoomEnabled() && ((BarLineScatterCandleBubbleData) ((BarLineChartBase) this.mChart).getData()).getEntryCount() > 0) {
            MPPointF trans = getTrans(e2.getX(), e2.getY());
            float f2 = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? 1.4f : 1.0f;
            float f3 = ((BarLineChartBase) this.mChart).isScaleYEnabled() ? 1.4f : 1.0f;
            ((BarLineChartBase) this.mChart).zoom(f2, f3, trans.x, trans.y);
            if (((BarLineChartBase) this.mChart).isLogEnabled()) {
                Log.i("BarlineChartTouch", "Double-Tap, Zooming In, x: " + trans.x + ", y: " + trans.y);
            }
            if (onChartGestureListener != null) {
                onChartGestureListener.onChartScale(e2, f2, f3);
            }
            MPPointF.recycleInstance(trans);
        }
        return super.onDoubleTap(e2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent e2) {
        this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(e2);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent e2) {
        this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(e2);
        }
        if (!((BarLineChartBase) this.mChart).isHighlightPerTapEnabled()) {
            return false;
        }
        performHighlight(((BarLineChartBase) this.mChart).getHighlightByTouchPoint(e2.getX(), e2.getY()), e2);
        return super.onSingleTapUp(e2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.mLastGesture = ChartTouchListener.ChartGesture.FLING;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartFling(e1, e2, velocityX, velocityY);
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    public void stopDeceleration() {
        this.mDecelerationVelocity.x = 0.0f;
        this.mDecelerationVelocity.y = 0.0f;
    }

    public void computeScroll() {
        if (this.mDecelerationVelocity.x == 0.0f && this.mDecelerationVelocity.y == 0.0f) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.mDecelerationVelocity.x *= ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef();
        this.mDecelerationVelocity.y *= ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef();
        float f2 = (jCurrentAnimationTimeMillis - this.mDecelerationLastTime) / 1000.0f;
        float f3 = this.mDecelerationVelocity.x * f2;
        float f4 = this.mDecelerationVelocity.y * f2;
        this.mDecelerationCurrentPoint.x += f3;
        this.mDecelerationCurrentPoint.y += f4;
        MotionEvent motionEventObtain = MotionEvent.obtain(jCurrentAnimationTimeMillis, jCurrentAnimationTimeMillis, 2, this.mDecelerationCurrentPoint.x, this.mDecelerationCurrentPoint.y, 0);
        performDrag(motionEventObtain, ((BarLineChartBase) this.mChart).isDragXEnabled() ? this.mDecelerationCurrentPoint.x - this.mTouchStartPoint.x : 0.0f, ((BarLineChartBase) this.mChart).isDragYEnabled() ? this.mDecelerationCurrentPoint.y - this.mTouchStartPoint.y : 0.0f);
        motionEventObtain.recycle();
        this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, false);
        this.mDecelerationLastTime = jCurrentAnimationTimeMillis;
        if (Math.abs(this.mDecelerationVelocity.x) >= 0.01d || Math.abs(this.mDecelerationVelocity.y) >= 0.01d) {
            Utils.postInvalidateOnAnimation(this.mChart);
            return;
        }
        ((BarLineChartBase) this.mChart).calculateOffsets();
        ((BarLineChartBase) this.mChart).postInvalidate();
        stopDeceleration();
    }
}
