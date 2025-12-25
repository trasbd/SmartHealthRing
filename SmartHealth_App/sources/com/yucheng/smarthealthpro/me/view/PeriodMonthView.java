package com.yucheng.smarthealthpro.me.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.internal.view.SupportMenu;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;
import com.yucheng.smarthealthpro.me.bean.PhysiologicalType;

/* loaded from: classes5.dex */
public class PeriodMonthView extends MonthView {
    private int mH;
    private int mPadding;
    private Paint mPointPaint;
    private float mPointRadius;
    private int mW;

    @Override // com.haibin.calendarview.MonthView
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        return true;
    }

    public PeriodMonthView(Context context) {
        super(context);
        this.mPointPaint = new Paint();
        this.mPadding = dipToPx(getContext(), 4.0f);
        this.mH = dipToPx(getContext(), 2.0f);
        this.mW = dipToPx(getContext(), 8.0f);
        this.mPointPaint.setAntiAlias(true);
        this.mPointPaint.setStyle(Paint.Style.FILL);
        this.mPointPaint.setTextAlign(Paint.Align.CENTER);
        this.mPointPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPointRadius = dipToPx(context, 2.0f);
    }

    @Override // com.haibin.calendarview.MonthView
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        if (PhysiologicalType.MENSTRUATION.equals(calendar.getScheme())) {
            int i2 = this.mPadding;
            canvas.drawRoundRect(x + i2, i2 + y, (x + this.mItemWidth) - this.mPadding, (y + this.mItemHeight) - this.mPadding, 12.0f, 12.0f, this.mSchemePaint);
        } else if (PhysiologicalType.OVULATION.equals(calendar.getScheme())) {
            this.mPointPaint.setColor(-1781765);
            canvas.drawRect(((this.mItemWidth / 2) + x) - (this.mW / 2), ((this.mItemHeight + y) - (this.mH * 2)) - this.mPadding, x + (this.mItemWidth / 2) + (this.mW / 2), ((y + this.mItemHeight) - this.mH) - this.mPadding, this.mPointPaint);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x001d  */
    @Override // com.haibin.calendarview.MonthView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onDrawText(android.graphics.Canvas r5, com.haibin.calendarview.Calendar r6, int r7, int r8, boolean r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 404
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.view.PeriodMonthView.onDrawText(android.graphics.Canvas, com.haibin.calendarview.Calendar, int, int, boolean, boolean):void");
    }

    private static int dipToPx(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
