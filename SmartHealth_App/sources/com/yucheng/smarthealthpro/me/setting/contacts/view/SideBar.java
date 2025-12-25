package com.yucheng.smarthealthpro.me.setting.contacts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;

/* loaded from: classes5.dex */
public class SideBar extends View {
    private final int TOP_MARGIN;
    private final int TOTAL_MARGIN;
    private String indexStr;
    private indexChangeListener listener;
    private Context mContext;
    private int mHeight;
    private Paint mPaint;
    private int mWidth;
    private int singleHeight;

    public interface indexChangeListener {
        void indexChanged(String tag);
    }

    public SideBar(Context context) {
        this(context, null);
    }

    public SideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.TOTAL_MARGIN = 160;
        this.TOP_MARGIN = 80;
        this.indexStr = "ABCDEFGHIJKLMNOPQRSTUVWXY#";
        this.mContext = context;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setDither(true);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-7829368);
        this.mPaint.setTextSize(35.0f);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        super.onSizeChanged(w, h2, oldw, oldh);
        this.mHeight = (int) (h2 - DpUtil.dp2px(this.mContext, 160.0f));
        this.mWidth = w;
        if (this.indexStr.length() != 0) {
            this.singleHeight = this.mHeight / this.indexStr.length();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2 = 0;
        while (i2 < this.indexStr.length()) {
            int i3 = i2 + 1;
            String strSubstring = this.indexStr.substring(i2, i3);
            canvas.drawText(strSubstring, (this.mWidth - this.mPaint.measureText(strSubstring)) / 2.0f, (this.singleHeight * i3) + DpUtil.dp2px(this.mContext, 80.0f), this.mPaint);
            i2 = i3;
        }
    }

    public void setIndexStr(String indexStr) {
        this.indexStr = indexStr;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                ((IndexBar) getParent()).setTagStatus(false);
                this.mPaint.setColor(-7829368);
                invalidate();
            } else if (action == 2) {
            }
            return true;
        }
        this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        invalidate();
        int y = (int) ((((event.getY() - getTop()) - DpUtil.dp2px(this.mContext, 80.0f)) / this.mHeight) * this.indexStr.toCharArray().length);
        if (y >= 0 && y < this.indexStr.length()) {
            ((IndexBar) getParent()).setDrawData(event.getY(), String.valueOf(this.indexStr.toCharArray()[y]), y);
            indexChangeListener indexchangelistener = this.listener;
            if (indexchangelistener != null) {
                indexchangelistener.indexChanged(this.indexStr.substring(y, y + 1));
            }
        }
        return true;
    }

    public void setIndexChangeListener(indexChangeListener listener) {
        this.listener = listener;
    }
}
