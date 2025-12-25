package com.yucheng.smarthealthpro.me.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* loaded from: classes5.dex */
public class RoundImageView extends AppCompatImageView {
    float height;
    private int round;
    float width;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.round = dipToPx(getContext(), 8.0f);
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.width = getWidth();
        this.height = getHeight();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        float f2 = this.width;
        int i2 = this.round;
        if (f2 > i2 && this.height > i2) {
            Path path = new Path();
            path.moveTo(this.round, 0.0f);
            path.lineTo(this.width - this.round, 0.0f);
            float f3 = this.width;
            path.quadTo(f3, 0.0f, f3, this.round);
            path.lineTo(this.width, this.height - this.round);
            float f4 = this.width;
            float f5 = this.height;
            path.quadTo(f4, f5, f4 - this.round, f5);
            path.lineTo(this.round, this.height);
            float f6 = this.height;
            path.quadTo(0.0f, f6, 0.0f, f6 - this.round);
            path.lineTo(0.0f, this.round);
            path.quadTo(0.0f, 0.0f, this.round, 0.0f);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

    private static int dipToPx(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
