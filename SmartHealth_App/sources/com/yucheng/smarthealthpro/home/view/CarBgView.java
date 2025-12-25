package com.yucheng.smarthealthpro.home.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import com.yucheng.smarthealthpro.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class CarBgView extends View {
    private static final float MULTIPLE = 0.5f;
    private static int mGridWidth;
    private static int mSGridWidth;
    Context context;
    protected int mBackgroundColor;
    protected int mGridColor;
    protected int mHeight;
    protected int mInitColor;
    protected Paint mPaint;
    private Path mPath;
    protected int mSGridColor;
    private int mSecLineColor;
    protected int mWidth;
    public List<Integer> plist;
    int screenHeight;
    StaticLayout staticLayout;

    public CarBgView(Context context) {
        this(context, null);
    }

    public CarBgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarBgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.plist = new ArrayList();
        this.mGridColor = Color.parseColor("#D9D9D9");
        this.mSGridColor = Color.parseColor("#F0F0F0");
        this.mInitColor = Color.parseColor("#000000");
        this.mSecLineColor = Color.parseColor("#8C8C8C");
        this.mBackgroundColor = -1;
        this.screenHeight = 0;
        this.context = context;
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        this.screenHeight = defaultDisplay.getHeight();
        float f2 = context.getResources().getDisplayMetrics().density;
        for (int i2 = 0; i2 < ((int) (width / f2)); i2++) {
            this.plist.add(0);
        }
        this.mPaint = new Paint();
        this.mPath = new Path();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        this.mWidth = w;
        this.mHeight = h2;
        TextPaint textPaint = new TextPaint();
        Rect rect = new Rect();
        String string = this.context.getString(R.string.ecg_header_title);
        textPaint.getTextBounds(string, 0, string.length(), rect);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(1.0f);
        textPaint.setTextSize(getResources().getDisplayMetrics().density * 12.0f);
        this.staticLayout = new StaticLayout(string, textPaint, getWidth() - (((int) (getMultiple(this.context) * 10.0f)) * 4), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        super.onSizeChanged(w, h2, oldw, oldh);
    }

    public static float getMultiple(Context context) {
        return context.getResources().getDisplayMetrics().density * 0.5f;
    }

    public static int getSGridWidth() {
        return mSGridWidth;
    }

    private void initBackground(Canvas canvas) {
        int multiple = (int) (getMultiple(this.context) * 10.0f);
        mSGridWidth = multiple;
        mGridWidth = multiple * 5;
        canvas.drawColor(this.mBackgroundColor);
        int i2 = this.mWidth;
        int i3 = mSGridWidth;
        int i4 = i2 / i3;
        int i5 = ((this.mHeight / i3) / 5) * 5;
        this.mPaint.setColor(this.mSGridColor);
        this.mPaint.setStrokeWidth(2.0f);
        for (int i6 = 0; i6 < i4 + 1; i6++) {
            int i7 = mSGridWidth;
            canvas.drawLine(i6 * i7, 0.0f, i6 * i7, i7 * i5, this.mPaint);
        }
        for (int i8 = 0; i8 < i5 + 1; i8++) {
            int i9 = mSGridWidth;
            canvas.drawLine(0.0f, i8 * i9, this.mWidth, i9 * i8, this.mPaint);
        }
        int i10 = this.mWidth;
        int i11 = mGridWidth;
        int i12 = i10 / i11;
        int i13 = this.mHeight / i11;
        this.mPaint.setColor(this.mGridColor);
        this.mPaint.setStrokeWidth(3.0f);
        for (int i14 = 0; i14 < i13 + 1; i14++) {
            int i15 = mGridWidth;
            canvas.drawLine(0.0f, i14 * i15, this.mWidth, i15 * i14, this.mPaint);
        }
        for (int i16 = 0; i16 < i12 + 1; i16++) {
            this.mPaint.setColor(this.mGridColor);
            int i17 = mGridWidth;
            canvas.drawLine(i16 * i17, 0.0f, i16 * i17, i17 * i13, this.mPaint);
            if (i16 != 0 && i16 % 5 == 0) {
                this.mPaint.setColor(this.mSecLineColor);
                int i18 = mGridWidth;
                canvas.drawLine(i16 * i18, 0.0f, i16 * i18, i18 * i13, this.mPaint);
            }
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mInitColor);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPath.reset();
        this.mPath.moveTo(0.0f, mGridWidth * 3);
        this.mPath.lineTo(mSGridWidth * 2, mGridWidth * 3);
        this.mPath.lineTo(mSGridWidth * 2, mGridWidth);
        Path path = this.mPath;
        int i19 = mSGridWidth * 2;
        path.lineTo(i19 + r5, mGridWidth);
        Path path2 = this.mPath;
        int i20 = mSGridWidth * 2;
        int i21 = mGridWidth;
        path2.lineTo(i20 + i21, i21 * 3);
        Path path3 = this.mPath;
        int i22 = mSGridWidth * 4;
        int i23 = mGridWidth;
        path3.lineTo(i22 + i23, i23 * 3);
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(1.0f);
        this.mPaint.setTextSize(getResources().getDisplayMetrics().density * 12.0f);
        canvas.translate(1 == getLayoutDirection() ? mSGridWidth * (-2) : mSGridWidth * 2, (i13 - this.staticLayout.getLineCount()) * mGridWidth);
        this.staticLayout.draw(canvas);
        canvas.translate(0.0f, 0.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        initBackground(canvas);
    }
}
