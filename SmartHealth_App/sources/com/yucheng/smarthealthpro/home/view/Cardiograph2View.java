package com.yucheng.smarthealthpro.home.view;

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
import android.view.View;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Cardiograph2View extends View {
    private static final float MULTIPLE = 0.5f;
    public static final int state = 10;
    private Context context;
    private boolean isHome;
    protected int mGridColor;
    protected int mGridWidth;
    protected int mHeight;
    protected int mInitColor;
    protected int mLineColor;
    protected Paint mPaint;
    protected Path mPath;
    protected int mSGridColor;
    protected int mSGridWidth;
    private int mSecLineColor;
    protected int mWidth;
    private List<Integer> plist;
    StaticLayout staticLayout;

    public Cardiograph2View(Context context) {
        this(context, null);
    }

    public Cardiograph2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Cardiograph2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isHome = false;
        this.plist = new ArrayList();
        this.mInitColor = Color.parseColor("#000000");
        this.mLineColor = Color.parseColor("#FB3159");
        this.mGridColor = Color.parseColor("#D9D9D9");
        this.mSGridColor = Color.parseColor("#F0F0F0");
        this.mSecLineColor = Color.parseColor("#8C8C8C");
        this.context = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = new Path();
    }

    public void setDatas(List<Integer> native_list, boolean isHome) {
        this.isHome = isHome;
        if (native_list != null) {
            int size = native_list.size();
            if (size > 6250) {
                this.plist.clear();
                this.plist.addAll(NativeListToBList.nativeListToBList(native_list));
                return;
            }
            for (int i2 = 0; i2 < size && size > 250; i2++) {
                if (native_list.get(i2).intValue() > 1000) {
                    this.plist.clear();
                    this.plist.addAll(NativeListToBList.nativeListToBList(native_list));
                    return;
                }
            }
            this.plist.clear();
            this.plist.addAll(NativeListToBList.oldListTobList(native_list));
        }
    }

    public List<Integer> getDatas() {
        return this.plist;
    }

    public void make(int w) {
        this.mWidth = w;
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
        textPaint.setTextSize(getResources().getDisplayMetrics().density * 10.0f);
        this.staticLayout = new StaticLayout(string, textPaint, getWidth() - (((int) (getMultiple(this.context) * 10.0f)) * 4), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        super.onSizeChanged(w, h2, oldw, oldh);
    }

    public static float getMultiple(Context context) {
        return context.getResources().getDisplayMetrics().density * 0.5f;
    }

    public void initBackground(Canvas canvas) {
        int multiple = (int) (getMultiple(this.context) * 10.0f);
        this.mSGridWidth = multiple;
        this.mGridWidth = multiple * 5;
        canvas.drawColor(-1);
        int i2 = this.mWidth;
        int i3 = this.mSGridWidth;
        int i4 = i2 / i3;
        int i5 = ((this.mHeight / i3) / 5) * 5;
        this.mPaint.setColor(this.mSGridColor);
        this.mPaint.setStrokeWidth(2.0f);
        for (int i6 = 0; i6 < i4 + 1; i6++) {
            int i7 = this.mSGridWidth;
            canvas.drawLine(i6 * i7, 0.0f, i6 * i7, i7 * i5, this.mPaint);
        }
        for (int i8 = 0; i8 < i5 + 1; i8++) {
            int i9 = this.mSGridWidth;
            canvas.drawLine(0.0f, i8 * i9, this.mWidth, i9 * i8, this.mPaint);
        }
        int i10 = this.mWidth;
        int i11 = this.mGridWidth;
        int i12 = i10 / i11;
        int i13 = this.mHeight / i11;
        this.mPaint.setColor(this.mGridColor);
        this.mPaint.setStrokeWidth(3.0f);
        for (int i14 = 0; i14 < i13 + 1; i14++) {
            int i15 = this.mGridWidth;
            canvas.drawLine(0.0f, i14 * i15, this.mWidth, i15 * i14, this.mPaint);
        }
        for (int i16 = 0; i16 < i12 + 1; i16++) {
            this.mPaint.setColor(this.mGridColor);
            int i17 = this.mGridWidth;
            canvas.drawLine(i16 * i17, 0.0f, i16 * i17, i17 * i13, this.mPaint);
            if (i16 != 0 && i16 % 5 == 0) {
                this.mPaint.setColor(this.mSecLineColor);
                int i18 = this.mGridWidth;
                canvas.drawLine(i16 * i18, 0.0f, i16 * i18, i18 * i13, this.mPaint);
            }
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mInitColor);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPath.reset();
        this.mPath.moveTo(0.0f, this.mGridWidth * 3);
        this.mPath.lineTo(this.mSGridWidth * 2, this.mGridWidth * 3);
        this.mPath.lineTo(this.mSGridWidth * 2, this.mGridWidth);
        Path path = this.mPath;
        int i19 = this.mSGridWidth * 2;
        path.lineTo(i19 + r5, this.mGridWidth);
        Path path2 = this.mPath;
        int i20 = this.mSGridWidth * 2;
        int i21 = this.mGridWidth;
        path2.lineTo(i20 + i21, i21 * 3);
        Path path3 = this.mPath;
        int i22 = this.mSGridWidth * 4;
        int i23 = this.mGridWidth;
        path3.lineTo(i22 + i23, i23 * 3);
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.save();
        this.staticLayout.getLineCount();
        int height = this.staticLayout.getHeight();
        if (!this.isHome) {
            canvas.translate(1 == getLayoutDirection() ? this.mSGridWidth * (-2) : this.mSGridWidth * 2, ((i13 - 1) * this.mGridWidth) - height);
        } else {
            canvas.translate(1 == getLayoutDirection() ? this.mSGridWidth * (-2) : this.mSGridWidth * 2, ((i13 - 1) * this.mGridWidth) - height);
        }
        this.staticLayout.draw(canvas);
        canvas.restore();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mLineColor);
        this.mPaint.setStrokeWidth(this.context.getResources().getDisplayMetrics().density * 1.25f);
        for (int i24 = 0; i24 < this.plist.size(); i24++) {
            if (i24 > 0) {
                Integer num = this.plist.get(i24 - 1);
                Integer num2 = this.plist.get(i24);
                float multiple2 = r2 * 3 * getMultiple(this.context);
                int i25 = this.mHeight / 2;
                int i26 = this.mGridWidth;
                float fIntValue = ((i25 / i26) * i26) - (((num.intValue() * 10) * this.mSGridWidth) / 1000.0f);
                float multiple3 = i24 * 3 * getMultiple(this.context);
                int i27 = this.mHeight / 2;
                int i28 = this.mGridWidth;
                canvas.drawLine(multiple2, fIntValue, multiple3, ((i27 / i28) * i28) - (((num2.intValue() * 10) * this.mSGridWidth) / 1000.0f), this.mPaint);
            }
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        initBackground(canvas);
    }
}
