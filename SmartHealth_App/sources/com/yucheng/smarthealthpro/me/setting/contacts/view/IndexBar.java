package com.yucheng.smarthealthpro.me.setting.contacts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.internal.view.SupportMenu;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.ColorUtil;

/* loaded from: classes5.dex */
public class IndexBar extends ViewGroup {
    private float centerY;
    private int childWidth;
    private final float circleRadius;
    private boolean isShowTag;
    private Context mContext;
    private int mHeight;
    private Paint mPaint;
    private int mWidth;
    private int position;
    private String tag;

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.tag = "";
        this.circleRadius = 100.0f;
        this.mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setWillNotDraw(false);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        super.onSizeChanged(w, h2, oldw, oldh);
        this.mHeight = h2;
        this.mWidth = w;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            this.mWidth = size;
        }
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            this.mHeight = size2;
        }
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b2) {
        if (getChildCount() <= 0) {
            return;
        }
        View childAt = getChildAt(0);
        int measuredWidth = childAt.getMeasuredWidth();
        this.childWidth = measuredWidth;
        int i2 = this.mWidth;
        childAt.layout(i2 - measuredWidth, 0, i2, this.mHeight);
    }

    public void setDrawData(float centerY, String tag, int position) {
        this.position = position;
        this.centerY = centerY;
        this.tag = tag;
        this.isShowTag = true;
        invalidate();
    }

    public void setTagStatus(boolean isShowTag) {
        this.isShowTag = isShowTag;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isShowTag) {
            ColorUtil.setPaintColor(this.mPaint, this.position);
            canvas.drawCircle((this.mWidth - this.childWidth) / 2, this.centerY, 100.0f, this.mPaint);
            this.mPaint.setColor(-1);
            this.mPaint.setTextSize(50.0f);
            String str = this.tag;
            canvas.drawText(str, ((this.mWidth - this.childWidth) - this.mPaint.measureText(str)) / 2.0f, this.centerY - ((this.mPaint.ascent() + this.mPaint.descent()) / 2.0f), this.mPaint);
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return super.generateLayoutParams(p);
    }
}
