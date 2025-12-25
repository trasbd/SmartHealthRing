package com.yucheng.smarthealthpro.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes5.dex */
public class Vp2IndicatorView extends View {
    public static final int STYLE_CIRCLR_CIRCLE = 0;
    private int circleCircleRadius;
    private ColorStateList mColorSelected;
    private ColorStateList mColorUnSelected;
    private int mCurrentSeletedPosition;
    private int mIndicatorItemCount;
    private int mIndicatorItemDistance;
    private int mIndicatorItemHeight;
    private RectF mIndicatorItemRectF;
    private int mIndicatorItemWidth;
    private int mIndicatorStyle;
    private Paint mSelectedPaint;
    private Paint mUnSelectedPaint;

    public Vp2IndicatorView(Context context) {
        this(context, null);
    }

    public Vp2IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Vp2IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mIndicatorStyle = 0;
        this.circleCircleRadius = 0;
        this.mIndicatorItemCount = 0;
        this.mCurrentSeletedPosition = 0;
        init();
        intPaint();
        verifyItemCount();
    }

    private void init() {
        this.mColorSelected = getContext().getColorStateList(R.color.colorAccent);
        this.mColorUnSelected = getContext().getColorStateList(R.color.unselect_dot);
        this.mIndicatorItemDistance = dip2px(12.0f);
        this.mIndicatorStyle = 0;
        this.circleCircleRadius = dip2px(3.0f);
    }

    private void intPaint() {
        Paint paint = new Paint();
        this.mUnSelectedPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mUnSelectedPaint.setAntiAlias(true);
        Paint paint2 = this.mUnSelectedPaint;
        ColorStateList colorStateList = this.mColorUnSelected;
        paint2.setColor(colorStateList == null ? -7829368 : colorStateList.getDefaultColor());
        Paint paint3 = new Paint();
        this.mSelectedPaint = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.mSelectedPaint.setAntiAlias(true);
        Paint paint4 = this.mSelectedPaint;
        ColorStateList colorStateList2 = this.mColorSelected;
        paint4.setColor(colorStateList2 == null ? SupportMenu.CATEGORY_MASK : colorStateList2.getDefaultColor());
        this.mIndicatorItemRectF = new RectF();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        View.MeasureSpec.getSize(widthMeasureSpec);
        int i2 = this.circleCircleRadius;
        int i3 = this.mIndicatorItemCount;
        this.mIndicatorItemWidth = (i2 * 2 * i3) + ((i3 - 1) * this.mIndicatorItemDistance);
        int iMax = Math.max(size, i2 * 2);
        this.mIndicatorItemHeight = iMax;
        setMeasuredDimension(this.mIndicatorItemWidth, iMax);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f2 = this.mIndicatorItemHeight / 2;
        int i2 = 0;
        while (i2 < this.mIndicatorItemCount) {
            int i3 = i2 + 1;
            canvas.drawCircle((i3 * r3) + (this.mIndicatorItemDistance * i2), f2, this.circleCircleRadius, i2 == this.mCurrentSeletedPosition ? this.mSelectedPaint : this.mUnSelectedPaint);
            i2 = i3;
        }
    }

    public void attachToViewPager2(final ViewPager2 vp2) {
        final RecyclerView.Adapter adapter = vp2.getAdapter();
        if (adapter != null) {
            this.mIndicatorItemCount = adapter.getItemCount();
            this.mCurrentSeletedPosition = vp2.getCurrentItem();
            verifyItemCount();
        }
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.yucheng.smarthealthpro.view.Vp2IndicatorView.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                ViewPager2 viewPager2 = vp2;
                if (viewPager2 != null && adapter != null) {
                    Vp2IndicatorView.this.mCurrentSeletedPosition = viewPager2.getCurrentItem();
                }
                Vp2IndicatorView.this.postInvalidate();
            }
        });
    }

    private void verifyItemCount() {
        int i2 = this.mCurrentSeletedPosition;
        int i3 = this.mIndicatorItemCount;
        if (i2 >= i3) {
            this.mCurrentSeletedPosition = i3 - 1;
        }
        setVisibility(i3 <= 1 ? 8 : 0);
    }

    public int dip2px(float dpValue) {
        return (int) ((dpValue * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
