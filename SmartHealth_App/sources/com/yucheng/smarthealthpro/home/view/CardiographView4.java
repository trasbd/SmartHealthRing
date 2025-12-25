package com.yucheng.smarthealthpro.home.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class CardiographView4 extends View {
    public static final int state = 10;
    public int HightDots;
    public int Is_Ver_or_Hor;
    public int WidthDots;
    Context context;
    protected int mHeight;
    protected int mLineColor;
    protected Paint mPaint;
    protected Path mPath;
    protected int mWidth;
    public List<Integer> plist;

    public CardiographView4(Context context) {
        this(context, null);
    }

    public CardiographView4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardiographView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.plist = Collections.synchronizedList(new ArrayList());
        this.mLineColor = Color.parseColor("#FB3159");
        this.WidthDots = 0;
        this.HightDots = 0;
        this.Is_Ver_or_Hor = 0;
        this.context = context;
        initList();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = new Path();
    }

    public void initList() {
        this.plist.clear();
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        Logger.d("chong----------screenWidth=" + this.mWidth + " screenHeight" + this.mHeight);
        this.WidthDots = (int) ((this.mWidth / CarBgView.getMultiple(this.context)) / 3.0f);
        this.HightDots = (int) (this.mHeight / CarBgView.getMultiple(this.context));
        if (this.mWidth > this.mHeight) {
            this.Is_Ver_or_Hor = 1;
        } else {
            this.Is_Ver_or_Hor = 0;
        }
        Logger.d("shl Is_Ver_or_Hor=" + this.Is_Ver_or_Hor + StringUtils.SPACE + this.WidthDots);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        this.mWidth = w;
        this.mHeight = h2;
        super.onSizeChanged(w, h2, oldw, oldh);
    }

    private void initBackground(Canvas canvas) {
        canvas.drawColor(0);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mLineColor);
        this.mPaint.setStrokeWidth(this.context.getResources().getDisplayMetrics().density * 1.25f);
        this.mPath.reset();
        this.mPath.moveTo(0.0f, this.mHeight / 2);
        for (int i2 = 0; i2 < this.plist.size(); i2++) {
            this.mPath.lineTo(i2 * 3 * CarBgView.getMultiple(this.context), (this.mHeight / 2) - (((this.plist.get(i2).intValue() * 10) * CarBgView.getSGridWidth()) / 1000.0f));
        }
        canvas.drawPath(this.mPath, this.mPaint);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        initBackground(canvas);
    }
}
