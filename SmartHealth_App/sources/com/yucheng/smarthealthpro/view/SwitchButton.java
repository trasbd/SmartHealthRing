package com.yucheng.smarthealthpro.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.utils.Tools;

/* loaded from: classes5.dex */
public class SwitchButton extends View implements View.OnClickListener {
    private float mCurrentX;
    private int mDeltX;
    private Rect mDest;
    private boolean mFlag;
    private float mLastX;
    private OnChangeListener mListener;
    private int mMoveLength;
    private Paint mPaint;
    private Rect mSrc;
    private Bitmap mSwitchBottom;
    private Bitmap mSwitchFrame;
    private Bitmap mSwitchMask;
    private boolean mSwitchOn;
    private Bitmap mSwitchThumb;

    public boolean ismSwitchOn() {
        return this.mSwitchOn;
    }

    public void setmSwitchOn(boolean mSwitchOn) {
        this.mSwitchOn = !mSwitchOn;
        invalidate();
    }

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCurrentX = 0.0f;
        this.mSwitchOn = true;
        this.mLastX = 0.0f;
        this.mDest = null;
        this.mSrc = null;
        this.mDeltX = 0;
        this.mPaint = null;
        this.mListener = null;
        this.mFlag = false;
        setOnClickListener(this);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.view.SwitchButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        init();
    }

    public void init() {
        this.mSwitchBottom = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_bottom);
        this.mSwitchThumb = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_btn_pressed);
        this.mSwitchFrame = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_frame);
        this.mSwitchMask = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_mask);
        this.mSwitchBottom = Tools.small(this.mSwitchBottom, 1.0f);
        this.mSwitchThumb = Tools.small(this.mSwitchThumb, 1.0f);
        this.mSwitchFrame = Tools.small(this.mSwitchFrame, 1.0f);
        this.mSwitchMask = Tools.small(this.mSwitchMask, 1.0f);
        this.mMoveLength = this.mSwitchBottom.getWidth() - this.mSwitchFrame.getWidth();
        this.mDest = new Rect(0, 0, this.mSwitchFrame.getWidth(), this.mSwitchFrame.getHeight());
        this.mSrc = new Rect();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setAlpha(255);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mSwitchFrame.getWidth(), this.mSwitchFrame.getHeight());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Rect rect;
        super.onDraw(canvas);
        int i2 = this.mDeltX;
        if (i2 > 0 || (i2 == 0 && this.mSwitchOn)) {
            Rect rect2 = this.mSrc;
            if (rect2 != null) {
                rect2.set(this.mMoveLength - i2, 0, this.mSwitchBottom.getWidth() - this.mDeltX, this.mSwitchFrame.getHeight());
            }
        } else if ((i2 < 0 || (i2 == 0 && !this.mSwitchOn)) && (rect = this.mSrc) != null) {
            rect.set(-i2, 0, this.mSwitchFrame.getWidth() - this.mDeltX, this.mSwitchFrame.getHeight());
        }
        int iSaveLayer = canvas.saveLayer(new RectF(this.mDest), null, 31);
        canvas.drawBitmap(this.mSwitchBottom, this.mSrc, this.mDest, (Paint) null);
        canvas.drawBitmap(this.mSwitchFrame, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(this.mSwitchMask, 0.0f, 0.0f, this.mPaint);
        canvas.drawBitmap(this.mSwitchThumb, this.mSrc, this.mDest, (Paint) null);
        canvas.restoreToCount(iSaveLayer);
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.mListener = listener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        boolean z = this.mSwitchOn;
        int i2 = this.mMoveLength;
        if (!z) {
            i2 = -i2;
        }
        this.mDeltX = i2;
        this.mSwitchOn = !z;
        OnChangeListener onChangeListener = this.mListener;
        if (onChangeListener != null) {
            onChangeListener.onChange(this, z);
        }
        invalidate();
        this.mDeltX = 0;
    }
}
