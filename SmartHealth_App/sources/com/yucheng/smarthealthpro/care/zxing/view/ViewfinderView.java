package com.yucheng.smarthealthpro.care.zxing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.zxing.ResultPoint;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.care.zxing.camera.CameraManager;
import java.util.Collection;
import java.util.HashSet;

/* loaded from: classes4.dex */
public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 10;
    private static final int CORNER_RECT_HEIGHT = 40;
    private static final int CORNER_RECT_WIDTH = 8;
    private static final int OPAQUE = 255;
    private static final int SCANNER_LINE_HEIGHT = 10;
    private static final int SCANNER_LINE_MOVE_DISTANCE = 5;
    private final int cornerColor;
    private final int frameColor;
    private final String labelText;
    private final int labelTextColor;
    private final float labelTextSize;
    private final int laserColor;
    private Collection<ResultPoint> lastPossibleResultPoints;
    private final int maskColor;
    private final Paint paint;
    private Collection<ResultPoint> possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultColor;
    private final int resultPointColor;
    private int scannerAlpha;
    private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    public static int scannerStart = 0;
    public static int scannerEnd = 0;

    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ViewfinderView);
        this.laserColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_laser_color, MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        this.cornerColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_corner_color, MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        this.frameColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_frame_color, ViewCompat.MEASURED_SIZE_MASK);
        this.resultPointColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_result_point_color, -1056964864);
        this.maskColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_mask_color, 1610612736);
        this.resultColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_result_color, -1342177280);
        this.labelTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_label_text_color, -1862270977);
        this.labelText = typedArrayObtainStyledAttributes.getString(R.styleable.ViewfinderView_label_text);
        this.labelTextSize = typedArrayObtainStyledAttributes.getFloat(R.styleable.ViewfinderView_label_text_size, 36.0f);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.scannerAlpha = 0;
        this.possibleResultPoints = new HashSet(5);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect framingRect = CameraManager.get().getFramingRect();
        if (framingRect == null) {
            return;
        }
        if (scannerStart == 0 || scannerEnd == 0) {
            scannerStart = framingRect.top;
            scannerEnd = framingRect.bottom;
        }
        drawExterior(canvas, framingRect, canvas.getWidth(), canvas.getHeight());
        if (this.resultBitmap != null) {
            this.paint.setAlpha(255);
            canvas.drawBitmap(this.resultBitmap, framingRect.left, framingRect.top, this.paint);
            return;
        }
        drawFrame(canvas, framingRect);
        drawCorner(canvas, framingRect);
        drawTextInfo(canvas, framingRect);
        drawLaserScanner(canvas, framingRect);
        Collection<ResultPoint> collection = this.possibleResultPoints;
        Collection<ResultPoint> collection2 = this.lastPossibleResultPoints;
        if (collection.isEmpty()) {
            this.lastPossibleResultPoints = null;
        } else {
            this.possibleResultPoints = new HashSet(5);
            this.lastPossibleResultPoints = collection;
            this.paint.setAlpha(255);
            this.paint.setColor(this.resultPointColor);
            for (ResultPoint resultPoint : collection) {
                canvas.drawCircle(framingRect.left + resultPoint.getX(), framingRect.top + resultPoint.getY(), 6.0f, this.paint);
            }
        }
        if (collection2 != null) {
            this.paint.setAlpha(127);
            this.paint.setColor(this.resultPointColor);
            for (ResultPoint resultPoint2 : collection2) {
                canvas.drawCircle(framingRect.left + resultPoint2.getX(), framingRect.top + resultPoint2.getY(), 3.0f, this.paint);
            }
        }
        postInvalidateDelayed(ANIMATION_DELAY, framingRect.left, framingRect.top, framingRect.right, framingRect.bottom);
    }

    private void drawTextInfo(Canvas canvas, Rect frame) {
        this.paint.setColor(this.labelTextColor);
        this.paint.setTextSize(this.labelTextSize);
        this.paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.labelText, frame.left + (frame.width() / 2), frame.bottom + 100, this.paint);
    }

    private void drawCorner(Canvas canvas, Rect frame) {
        this.paint.setColor(this.cornerColor);
        canvas.drawRect(frame.left, frame.top, frame.left + 8, frame.top + 40, this.paint);
        canvas.drawRect(frame.left, frame.top, frame.left + 40, frame.top + 8, this.paint);
        canvas.drawRect(frame.right - 8, frame.top, frame.right, frame.top + 40, this.paint);
        canvas.drawRect(frame.right - 40, frame.top, frame.right, frame.top + 8, this.paint);
        canvas.drawRect(frame.left, frame.bottom - 8, frame.left + 40, frame.bottom, this.paint);
        canvas.drawRect(frame.left, frame.bottom - 40, frame.left + 8, frame.bottom, this.paint);
        canvas.drawRect(frame.right - 8, frame.bottom - 40, frame.right, frame.bottom, this.paint);
        canvas.drawRect(frame.right - 40, frame.bottom - 8, frame.right, frame.bottom, this.paint);
    }

    private void drawLaserScanner(Canvas canvas, Rect frame) {
        this.paint.setColor(this.laserColor);
        float fWidth = frame.left + (frame.width() / 2);
        float f2 = scannerStart + 5;
        int i2 = this.laserColor;
        this.paint.setShader(new RadialGradient(fWidth, f2, 360.0f, i2, shadeColor(i2), Shader.TileMode.MIRROR));
        if (scannerStart <= scannerEnd) {
            canvas.drawOval(new RectF(frame.left + 20, scannerStart, frame.right - 20, scannerStart + 10), this.paint);
            scannerStart += 5;
        } else {
            scannerStart = frame.top;
        }
        this.paint.setShader(null);
    }

    public int shadeColor(int color) {
        return Integer.valueOf("20" + Integer.toHexString(color).substring(2), 16).intValue();
    }

    private void drawFrame(Canvas canvas, Rect frame) {
        this.paint.setColor(this.frameColor);
        canvas.drawRect(frame.left, frame.top, frame.right + 1, frame.top + 2, this.paint);
        canvas.drawRect(frame.left, frame.top + 2, frame.left + 2, frame.bottom - 1, this.paint);
        canvas.drawRect(frame.right - 1, frame.top, frame.right + 1, frame.bottom - 1, this.paint);
        canvas.drawRect(frame.left, frame.bottom - 1, frame.right + 1, frame.bottom + 1, this.paint);
    }

    private void drawExterior(Canvas canvas, Rect frame, int width, int height) {
        this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
        float f2 = width;
        canvas.drawRect(0.0f, 0.0f, f2, frame.top, this.paint);
        canvas.drawRect(0.0f, frame.top, frame.left, frame.bottom + 1, this.paint);
        canvas.drawRect(frame.right + 1, frame.top, f2, frame.bottom + 1, this.paint);
        canvas.drawRect(0.0f, frame.bottom + 1, f2, height, this.paint);
    }

    public void drawViewfinder() {
        this.resultBitmap = null;
        invalidate();
    }

    public void drawResultBitmap(Bitmap barcode) {
        this.resultBitmap = barcode;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        this.possibleResultPoints.add(point);
    }
}
