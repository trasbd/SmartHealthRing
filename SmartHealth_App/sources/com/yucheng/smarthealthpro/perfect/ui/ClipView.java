package com.yucheng.smarthealthpro.perfect.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.orhanobut.logger.Logger;

/* loaded from: classes5.dex */
public class ClipView extends View {
    private Paint borderPaint;
    private int clipBorderWidth;
    private int clipHeight;
    private int clipRadiusHeight;
    private int clipRadiusWidth;
    private ClipType clipType;
    private int clipWidth;
    private float mHorizontalPadding;
    private Paint paint;
    private Xfermode xfermode;

    public enum ClipType {
        CIRCLE,
        RECTANGLE
    }

    public ClipView(Context context) {
        this(context, null);
    }

    public ClipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.paint = new Paint();
        this.borderPaint = new Paint();
        this.clipType = ClipType.CIRCLE;
        this.paint.setAntiAlias(true);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setColor(-1);
        this.borderPaint.setStrokeWidth(this.clipBorderWidth);
        this.borderPaint.setAntiAlias(true);
        this.xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
        canvas.drawColor(Color.parseColor("#a8000000"));
        this.paint.setXfermode(this.xfermode);
        if (this.clipType == ClipType.CIRCLE) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.clipRadiusWidth, this.paint);
            this.clipRadiusHeight = this.clipRadiusWidth;
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.clipRadiusWidth, this.borderPaint);
        } else if (this.clipType == ClipType.RECTANGLE) {
            canvas.drawRect(this.mHorizontalPadding + ((getWidth() - this.clipWidth) / 2), (getHeight() - this.clipHeight) / 2, ((getWidth() + this.clipWidth) / 2) - this.mHorizontalPadding, (getHeight() + this.clipHeight) / 2, this.paint);
            canvas.drawRect(this.mHorizontalPadding + ((getWidth() - this.clipWidth) / 2), (getHeight() - this.clipHeight) / 2, ((getWidth() + this.clipWidth) / 2) - this.mHorizontalPadding, (getHeight() + this.clipHeight) / 2, this.borderPaint);
        }
        canvas.restore();
    }

    public Rect getClipRect() {
        Rect rect = new Rect();
        rect.left = (getWidth() - this.clipWidth) / 2;
        rect.right = (getWidth() + this.clipWidth) / 2;
        rect.top = (getHeight() - this.clipHeight) / 2;
        rect.bottom = (getHeight() + this.clipHeight) / 2;
        return rect;
    }

    public void setClipBorderWidth(int clipBorderWidth) {
        this.clipBorderWidth = clipBorderWidth;
        this.borderPaint.setStrokeWidth(clipBorderWidth);
        invalidate();
    }

    public void setmHorizontalPadding(float mHorizontalPadding) {
        this.mHorizontalPadding = mHorizontalPadding;
        int screenWidth = ((int) (getScreenWidth(getContext()) - (mHorizontalPadding * 2.0f))) / 2;
        this.clipRadiusWidth = screenWidth;
        int i2 = screenWidth * 2;
        this.clipWidth = i2;
        this.clipRadiusHeight = screenWidth;
        this.clipHeight = i2;
    }

    public void setClipRadiusWidth(int width, int height) {
        Logger.d("chong------clipwidth==" + width + "--height==" + height);
        this.clipWidth = width;
        this.clipRadiusWidth = width / 2;
        this.clipHeight = height;
        this.clipRadiusHeight = height / 2;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public void setClipType(ClipType clipType) {
        this.clipType = clipType;
    }
}
