package com.yucheng.smarthealthpro.me.setting.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.ArrayList;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes5.dex */
public class OverCameraView extends AppCompatImageView {
    private Context context;
    private boolean isFoucuing;
    private Paint touchFocusPaint;
    private Rect touchFocusRect;

    public OverCameraView(Context context) {
        this(context, null, 0);
    }

    public OverCameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Paint paint = new Paint();
        this.touchFocusPaint = paint;
        paint.setColor(-16711936);
        this.touchFocusPaint.setStyle(Paint.Style.STROKE);
        this.touchFocusPaint.setStrokeWidth(3.0f);
    }

    public boolean isFoucuing() {
        return this.isFoucuing;
    }

    public void setFoucuing(boolean foucuing) {
        this.isFoucuing = foucuing;
    }

    public void setTouchFoucusRect(Camera camera, Camera.AutoFocusCallback autoFocusCallback, float x, float y) {
        Rect rect = new Rect((int) (x - 100.0f), (int) (y - 100.0f), (int) (x + 100.0f), (int) (y + 100.0f));
        this.touchFocusRect = rect;
        int windowWidth = ((rect.left * 2000) / getWindowWidth(this.context)) - 1000;
        int windowHeight = ((this.touchFocusRect.top * 2000) / getWindowHeight(this.context)) - 1000;
        int windowWidth2 = ((this.touchFocusRect.right * 2000) / getWindowWidth(this.context)) - 1000;
        int windowHeight2 = ((this.touchFocusRect.bottom * 2000) / getWindowHeight(this.context)) - 1000;
        if (windowWidth < -1000) {
            windowWidth = -1000;
        }
        if (windowHeight < -1000) {
            windowHeight = -1000;
        }
        if (windowWidth2 > 1000) {
            windowWidth2 = 1000;
        }
        doTouchFocus(camera, autoFocusCallback, new Rect(windowWidth, windowHeight, windowWidth2, windowHeight2 <= 1000 ? windowHeight2 : 1000));
        postInvalidate();
    }

    public void doTouchFocus(Camera camera, Camera.AutoFocusCallback autoFocusCallback, final Rect tfocusRect) {
        if (camera == null || this.isFoucuing) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(tfocusRect, 1000));
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFocusAreas(arrayList);
            parameters.setMeteringAreas(arrayList);
            parameters.setFocusMode(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
            camera.cancelAutoFocus();
            camera.setParameters(parameters);
            camera.autoFocus(autoFocusCallback);
            this.isFoucuing = true;
        } catch (Exception e2) {
            Log.e("设置相机参数异常", e2.getMessage());
        }
    }

    public void disDrawTouchFocusRect() {
        this.touchFocusRect = null;
        postInvalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        drawTouchFocusRect(canvas);
        super.onDraw(canvas);
    }

    public static int getWindowHeight(Context cxt) {
        return ((WindowManager) cxt.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static int getWindowWidth(Context cxt) {
        return ((WindowManager) cxt.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    private void drawTouchFocusRect(Canvas canvas) {
        if (this.touchFocusRect != null) {
            canvas.drawRect(r0.left - 2, this.touchFocusRect.bottom, this.touchFocusRect.left + 20, this.touchFocusRect.bottom + 2, this.touchFocusPaint);
            canvas.drawRect(this.touchFocusRect.left - 2, this.touchFocusRect.bottom - 20, this.touchFocusRect.left, this.touchFocusRect.bottom, this.touchFocusPaint);
            canvas.drawRect(this.touchFocusRect.left - 2, this.touchFocusRect.top - 2, this.touchFocusRect.left + 20, this.touchFocusRect.top, this.touchFocusPaint);
            canvas.drawRect(this.touchFocusRect.left - 2, this.touchFocusRect.top, this.touchFocusRect.left, this.touchFocusRect.top + 20, this.touchFocusPaint);
            canvas.drawRect(this.touchFocusRect.right - 20, this.touchFocusRect.top - 2, this.touchFocusRect.right + 2, this.touchFocusRect.top, this.touchFocusPaint);
            canvas.drawRect(this.touchFocusRect.right, this.touchFocusRect.top, this.touchFocusRect.right + 2, this.touchFocusRect.top + 20, this.touchFocusPaint);
            canvas.drawRect(this.touchFocusRect.right - 20, this.touchFocusRect.bottom, this.touchFocusRect.right + 2, this.touchFocusRect.bottom + 2, this.touchFocusPaint);
            canvas.drawRect(this.touchFocusRect.right, this.touchFocusRect.bottom - 20, this.touchFocusRect.right + 2, this.touchFocusRect.bottom, this.touchFocusPaint);
        }
    }
}
