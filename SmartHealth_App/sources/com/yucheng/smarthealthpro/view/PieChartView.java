package com.yucheng.smarthealthpro.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class PieChartView extends View {
    private long animDuration;
    private ValueAnimator animator;
    private int backGroundColor;
    private int cell;
    private Point centerPoint;
    private int defaultStartAngle;
    private Path drawLinePath;
    private Point endPoint;
    private Point firstPoint;
    private int height;
    private float innerRadius;
    private List<Point> itemPoints;
    private int itemTextSize;
    private List<ItemType> itemTypeList;
    private List<ItemType> leftTypeList;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private float offLine;
    private float offRadius;
    private float pieCell;
    private RectF pieRectF;
    private int radius;
    private List<ItemType> rightTypeList;
    private Point startPoint;
    private Point tempPoint;
    private RectF tempRectF;
    private int textAlpha;
    private int textPadding;
    private int width;

    public PieChartView(Context context) {
        super(context);
        this.drawLinePath = new Path();
        this.mPathMeasure = new PathMeasure();
        this.cell = 0;
        this.innerRadius = 0.0f;
        this.offRadius = 0.0f;
        this.backGroundColor = -1;
        this.itemTextSize = 30;
        this.textPadding = 8;
        this.defaultStartAngle = -90;
        this.animDuration = 1000L;
        this.startPoint = new Point();
        this.centerPoint = new Point();
        this.endPoint = new Point();
        this.tempPoint = new Point();
        init();
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.drawLinePath = new Path();
        this.mPathMeasure = new PathMeasure();
        this.cell = 0;
        this.innerRadius = 0.0f;
        this.offRadius = 0.0f;
        this.backGroundColor = -1;
        this.itemTextSize = 30;
        this.textPadding = 8;
        this.defaultStartAngle = -90;
        this.animDuration = 1000L;
        this.startPoint = new Point();
        this.centerPoint = new Point();
        this.endPoint = new Point();
        this.tempPoint = new Point();
        init();
    }

    private void init() {
        this.mPaint = new Paint(5);
        this.mPath = new Path();
        this.pieRectF = new RectF();
        this.tempRectF = new RectF();
        this.itemTypeList = new ArrayList();
        this.leftTypeList = new ArrayList();
        this.rightTypeList = new ArrayList();
        this.itemPoints = new ArrayList();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnim();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    private void startAnim() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 360.0f);
        this.animator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.animDuration);
        this.animator.setInterpolator(new DecelerateInterpolator(4.0f));
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.view.PieChartView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float fFloatValue = ((Float) animation.getAnimatedValue()).floatValue();
                if (fFloatValue < 360.0f) {
                    PieChartView.this.offRadius = fFloatValue;
                    PieChartView.this.offLine = 0.0f;
                    PieChartView.this.textAlpha = 0;
                } else if (fFloatValue >= 360.0f) {
                    PieChartView.this.offRadius = 360.0f;
                    PieChartView.this.offLine = (fFloatValue - 360.0f) / 360.0f;
                    if (PieChartView.this.offLine > 0.5f) {
                        PieChartView pieChartView = PieChartView.this;
                        pieChartView.textAlpha = (int) (((pieChartView.offLine - 0.5f) / 0.5f) * 255.0f);
                        if (PieChartView.this.textAlpha > 255) {
                            PieChartView.this.textAlpha = 255;
                        }
                    } else {
                        PieChartView.this.textAlpha = 0;
                    }
                } else if (fFloatValue == 720.0f) {
                    PieChartView.this.offRadius = 360.0f;
                    PieChartView.this.offLine = 1.0f;
                    PieChartView.this.textAlpha = 255;
                }
                PieChartView.this.postInvalidate();
            }
        });
        this.animator.start();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        super.onSizeChanged(w, h2, oldw, oldh);
        this.width = w;
        this.height = h2;
        this.radius = Math.min(w, h2) / 4;
        RectF rectF = this.pieRectF;
        int i2 = this.width;
        int i3 = this.height;
        rectF.set((i2 / 2) - r3, (i3 / 2) - r3, (i2 / 2) + r3, (i3 / 2) + r3);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            this.mCanvas = canvas;
            drawPie();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void drawTitle() {
        int i2;
        double d2;
        double d3;
        int i3;
        resetPaint();
        float f2 = this.defaultStartAngle;
        int size = this.rightTypeList.size();
        if (size > 1) {
            i2 = (this.radius * 2) / (size - 1);
        } else {
            i2 = this.radius;
        }
        int i4 = 0;
        while (true) {
            d2 = 360.0d;
            d3 = 6.283185307179586d;
            if (i4 >= size) {
                break;
            }
            this.mPath.reset();
            ItemType itemType = this.rightTypeList.get(i4);
            double d4 = (((itemType.radius / 2.0f) + f2) / 360.0d) * 6.283185307179586d;
            int i5 = i2;
            this.startPoint.set((int) ((this.width / 2) + (this.radius * Math.cos(d4))), (int) ((this.height / 2) + (this.radius * Math.sin(d4))));
            Point point = this.centerPoint;
            float f3 = this.width / 2;
            int i6 = this.radius;
            point.set((int) (f3 + (i6 * 1.2f)), ((this.height / 2) - i6) + (i5 * i4));
            this.endPoint.set((int) (this.width * 0.98f), this.centerPoint.y);
            this.mPath.moveTo(this.startPoint.x, this.startPoint.y);
            this.mPath.lineTo(this.centerPoint.x, this.centerPoint.y);
            this.mPath.lineTo(this.endPoint.x, this.endPoint.y);
            resetPaint();
            this.mPaint.setStrokeWidth(2.0f);
            this.mPaint.setColor(itemType.color);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPathMeasure.setPath(this.mPath, false);
            this.drawLinePath.reset();
            PathMeasure pathMeasure = this.mPathMeasure;
            pathMeasure.getSegment(0.0f, pathMeasure.getLength() * this.offLine, this.drawLinePath, true);
            this.mCanvas.drawPath(this.drawLinePath, this.mPaint);
            f2 += itemType.radius;
            if (this.textAlpha > 0) {
                this.mPaint.setTextSize(this.itemTextSize);
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setTextAlign(Paint.Align.CENTER);
                this.mPaint.setAlpha(this.textAlpha);
                this.mCanvas.drawText(itemType.type, this.centerPoint.x + ((this.endPoint.x - this.centerPoint.x) / 2), this.centerPoint.y - this.textPadding, this.mPaint);
                this.mPaint.setTextSize((this.itemTextSize * 4) / 5);
                this.mCanvas.drawText(itemType.getPercent(), this.centerPoint.x + ((this.endPoint.x - this.centerPoint.x) / 2), this.centerPoint.y + (((this.itemTextSize + this.textPadding) * 4) / 5), this.mPaint);
            }
            i4++;
            i2 = i5;
        }
        int size2 = this.leftTypeList.size();
        if (size2 > 1) {
            i3 = (this.radius * 2) / (size2 - 1);
        } else {
            i3 = this.radius;
        }
        int i7 = 0;
        while (i7 < size2) {
            this.mPath.reset();
            ItemType itemType2 = this.leftTypeList.get(i7);
            double d5 = (((itemType2.radius / 2.0f) + f2) / d2) * d3;
            this.startPoint.set((int) ((this.width / 2) + (this.radius * Math.cos(d5))), (int) ((this.height / 2) + (this.radius * Math.sin(d5))));
            Point point2 = this.centerPoint;
            float f4 = this.width / 2;
            int i8 = this.radius;
            point2.set((int) (f4 - (i8 * 1.2f)), ((this.height / 2) - i8) + (((size2 - 1) - i7) * i3));
            this.endPoint.set((int) (this.width * 0.02f), this.centerPoint.y);
            this.mPath.moveTo(this.startPoint.x, this.startPoint.y);
            this.mPath.lineTo(this.centerPoint.x, this.centerPoint.y);
            this.mPath.lineTo(this.endPoint.x, this.endPoint.y);
            resetPaint();
            this.mPaint.setStrokeWidth(2.0f);
            this.mPaint.setColor(itemType2.color);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setDither(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPathMeasure.setPath(this.mPath, false);
            this.drawLinePath.reset();
            PathMeasure pathMeasure2 = this.mPathMeasure;
            pathMeasure2.getSegment(0.0f, pathMeasure2.getLength() * this.offLine, this.drawLinePath, true);
            this.mCanvas.drawPath(this.drawLinePath, this.mPaint);
            f2 += itemType2.radius;
            if (this.textAlpha > 0) {
                this.mPaint.setTextSize(this.itemTextSize);
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setTextAlign(Paint.Align.CENTER);
                this.mPaint.setAlpha(this.textAlpha);
                this.mCanvas.drawText(itemType2.type, this.centerPoint.x + ((this.endPoint.x - this.centerPoint.x) / 2), this.centerPoint.y - this.textPadding, this.mPaint);
                this.mPaint.setTextSize((this.itemTextSize * 4) / 5);
                this.mCanvas.drawText(itemType2.getPercent(), this.centerPoint.x + ((this.endPoint.x - this.centerPoint.x) / 2), this.centerPoint.y + (((this.itemTextSize + this.textPadding) * 4) / 5), this.mPaint);
            }
            i7++;
            d2 = 360.0d;
            d3 = 6.283185307179586d;
        }
        if (this.textAlpha == 1.0f) {
            this.itemTypeList.clear();
            this.leftTypeList.clear();
            this.rightTypeList.clear();
            this.itemPoints.clear();
        }
    }

    private void drawPie() {
        ItemType itemType;
        Canvas canvas = this.mCanvas;
        if (canvas == null) {
            return;
        }
        canvas.drawColor(this.backGroundColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        Iterator<ItemType> it2 = this.itemTypeList.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            i2 += it2.next().widget;
        }
        float f2 = 360.0f / i2;
        float f3 = this.defaultStartAngle;
        this.leftTypeList.clear();
        this.rightTypeList.clear();
        this.itemPoints.clear();
        Iterator<ItemType> it3 = this.itemTypeList.iterator();
        float f4 = 0.0f;
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            ItemType next = it3.next();
            next.radius = next.widget * f2;
            double d2 = ((f3 + 90.0f) / 360.0d) * 6.283185307179586d;
            float f5 = f4;
            this.tempPoint.set((int) ((this.width / 2) + (this.radius * Math.sin(d2))), (int) ((this.height / 2) - (this.radius * Math.cos(d2))));
            if (this.cell > 0 && f3 == this.defaultStartAngle) {
                this.firstPoint = this.tempPoint;
            }
            double d3 = (((next.radius / 2.0f) + f3) / 360.0d) * 6.283185307179586d;
            double d4 = -Math.sin(d3);
            double d5 = -Math.cos(d3);
            if (d5 > 0.0d) {
                this.leftTypeList.add(next);
            } else {
                this.rightTypeList.add(next);
            }
            float fAbs = f5 + Math.abs(next.radius);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(next.color);
            if (this.pieCell > 0.0f) {
                if (fAbs <= this.offRadius) {
                    this.tempRectF.set(this.pieRectF.left - ((float) (this.pieCell * d5)), this.pieRectF.top - ((float) (this.pieCell * d4)), this.pieRectF.right - ((float) (this.pieCell * d5)), this.pieRectF.bottom - ((float) (this.pieCell * d4)));
                    itemType = next;
                    this.mCanvas.drawArc(this.tempRectF, f3, next.radius, true, this.mPaint);
                    f3 += itemType.radius;
                    f4 = fAbs;
                } else {
                    this.mCanvas.drawArc(this.tempRectF, f3, next.radius - Math.abs(this.offRadius - fAbs), true, this.mPaint);
                    break;
                }
            } else {
                itemType = next;
                if (fAbs <= this.offRadius) {
                    this.mCanvas.drawArc(this.pieRectF, f3, itemType.radius, true, this.mPaint);
                    f3 += itemType.radius;
                    f4 = fAbs;
                } else {
                    this.mCanvas.drawArc(this.pieRectF, f3, itemType.radius - Math.abs(this.offRadius - fAbs), true, this.mPaint);
                    break;
                }
            }
        }
        float f6 = this.defaultStartAngle;
        for (ItemType itemType2 : this.itemTypeList) {
            itemType2.radius = itemType2.widget * f2;
            double d6 = ((f6 + 90.0f) / 360.0d) * 6.283185307179586d;
            this.tempPoint.set((int) ((this.width / 2) + (this.radius * Math.sin(d6))), (int) ((this.height / 2) - (this.radius * Math.cos(d6))));
            if (this.cell > 0 && this.pieCell == 0.0f) {
                this.mPaint.setColor(this.backGroundColor);
                this.mPaint.setStrokeWidth(this.cell);
                this.mCanvas.drawLine(getWidth() / 2, getHeight() / 2, this.tempPoint.x, this.tempPoint.y, this.mPaint);
            }
            f6 += itemType2.radius;
        }
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.backGroundColor);
        float f7 = this.innerRadius;
        if (f7 <= 0.0f || this.pieCell != 0.0f) {
            return;
        }
        this.mCanvas.drawCircle(this.width / 2, this.height / 2, this.radius * f7, this.mPaint);
    }

    public void resetPaint() {
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setAlpha(255);
    }

    public void addItemType(ItemType itemType) {
        List<ItemType> list = this.itemTypeList;
        if (list != null) {
            list.add(itemType);
        }
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0006 A[PHI: r0
  0x0006: PHI (r0v2 float) = (r0v0 float), (r0v1 float) binds: [B:3:0x0004, B:6:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setInnerRadius(float r3) {
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
            r2.innerRadius = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.view.PieChartView.setInnerRadius(float):void");
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public void setItemTextSize(int itemTextSize) {
        this.itemTextSize = itemTextSize;
    }

    public void setTextPadding(int textPadding) {
        this.textPadding = textPadding;
    }

    public void setAnimDuration(long animDuration) {
        this.animDuration = animDuration;
    }

    @Deprecated
    public void setPieCell(int pieCell) {
        this.pieCell = pieCell;
    }

    public static class ItemType {
        private static final DecimalFormat df = new DecimalFormat("0.0%");
        int color;
        float radius;
        String type;
        int widget;

        public ItemType(String type, int widget, int color) {
            this.type = type;
            this.widget = widget;
            this.color = color;
        }

        public String getPercent() {
            return df.format(this.radius / 360.0f);
        }
    }
}
