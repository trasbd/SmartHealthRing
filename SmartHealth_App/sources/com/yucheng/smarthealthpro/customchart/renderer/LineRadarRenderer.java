package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public abstract class LineRadarRenderer extends LineScatterCandleRadarRenderer {
    public LineRadarRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
    }

    protected void drawFilledPath(Canvas c2, Path filledPath, Drawable drawable) {
        if (clipPathSupported()) {
            int iSave = c2.save();
            c2.clipPath(filledPath);
            drawable.setBounds((int) this.mViewPortHandler.contentLeft(), (int) this.mViewPortHandler.contentTop(), (int) this.mViewPortHandler.contentRight(), (int) this.mViewPortHandler.contentBottom());
            drawable.draw(c2);
            c2.restoreToCount(iSave);
            return;
        }
        throw new RuntimeException("Fill-drawables not (yet) supported below API level 18, this code was run on API level " + Utils.getSDKInt() + ".");
    }

    protected void drawFilledPath(Canvas c2, Path filledPath, int fillColor, int fillAlpha) {
        int i2 = (fillColor & ViewCompat.MEASURED_SIZE_MASK) | (fillAlpha << 24);
        if (clipPathSupported()) {
            int iSave = c2.save();
            c2.clipPath(filledPath);
            c2.drawColor(i2);
            c2.restoreToCount(iSave);
            return;
        }
        Paint.Style style = this.mRenderPaint.getStyle();
        int color = this.mRenderPaint.getColor();
        this.mRenderPaint.setStyle(Paint.Style.FILL);
        this.mRenderPaint.setColor(i2);
        c2.drawPath(filledPath, this.mRenderPaint);
        this.mRenderPaint.setColor(color);
        this.mRenderPaint.setStyle(style);
    }

    private boolean clipPathSupported() {
        return Utils.getSDKInt() >= 18;
    }
}
