package com.yucheng.smarthealthpro.home.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes5.dex */
public class StepMonthView extends MonthView {
    private static final float TEXT_SIZE = 14.0f;
    private int mH;
    private int mPadding;
    private Paint mSchemeBasicPaint;
    private String mToDay;
    private int mW;

    public StepMonthView(Context context) {
        super(context);
        Paint paint = new Paint();
        this.mSchemeBasicPaint = paint;
        paint.setAntiAlias(true);
        this.mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        this.mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        this.mSchemeBasicPaint.setColor(-13421773);
        this.mSchemeBasicPaint.setFakeBoldText(true);
        this.mPadding = dipToPx(getContext(), 4.0f);
        this.mH = dipToPx(getContext(), 2.0f);
        this.mW = dipToPx(getContext(), 8.0f);
    }

    @Override // com.haibin.calendarview.MonthView
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) throws Resources.NotFoundException {
        this.mSelectedPaint.setStyle(Paint.Style.FILL);
        this.mSelectedPaint.setColor(getContext().getResources().getColor(R.color.colorAccent, null));
        int i2 = this.mPadding;
        canvas.drawRoundRect(x + i2, i2 + y, (x + this.mItemWidth) - this.mPadding, (y + this.mItemHeight) - this.mPadding, 12.0f, 12.0f, this.mSelectedPaint);
        return true;
    }

    @Override // com.haibin.calendarview.MonthView
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) throws Resources.NotFoundException {
        this.mSchemeBasicPaint.setColor(getContext().getResources().getColor(R.color.colorAccent, null));
        canvas.drawRoundRect(((this.mItemWidth / 2) + x) - (this.mW / 2), ((this.mItemHeight + y) - (this.mH * 2)) - this.mPadding, x + (this.mItemWidth / 2) + (this.mW / 2), ((y + this.mItemHeight) - this.mH) - this.mPadding, 12.0f, 12.0f, this.mSchemeBasicPaint);
    }

    @Override // com.haibin.calendarview.MonthView
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        Paint paint;
        Paint paint2;
        Paint paint3;
        Paint paint4;
        Date date = null;
        if (calendar.isCurrentDay()) {
            this.mSelectedPaint.setColor(getContext().getResources().getColor(R.color.colorAccent, null));
            this.mSelectedPaint.setAlpha(80);
            Log.i("AAAAAAAAA", "===onDrawScheme===" + calendar.getMonth() + "-" + calendar.getDay());
            int i2 = this.mPadding;
            canvas.drawRoundRect(x + i2, i2 + y, (x + this.mItemWidth) - this.mPadding, (this.mItemHeight + y) - this.mPadding, 12.0f, 12.0f, this.mSelectedPaint);
        }
        String str = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        if (calendar2.get(2) + 1 < 10 && calendar2.get(5) >= 10) {
            this.mToDay = calendar2.get(1) + "-0" + (calendar2.get(2) + 1) + "-" + calendar2.get(5);
        }
        if (calendar2.get(5) < 10 && calendar2.get(2) + 1 >= 10) {
            this.mToDay = calendar2.get(1) + "-" + (calendar2.get(2) + 1) + "-0" + calendar2.get(5);
        }
        if (calendar2.get(2) + 1 < 10 && calendar2.get(5) < 10) {
            this.mToDay = calendar2.get(1) + "-0" + (calendar2.get(2) + 1) + "-0" + calendar2.get(5);
        }
        if (calendar2.get(2) + 1 >= 10 && calendar2.get(5) >= 10) {
            this.mToDay = calendar2.get(1) + "-" + (calendar2.get(2) + 1) + "-" + calendar2.get(5);
        }
        try {
            date = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(this.mToDay);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        boolean zDateIsBeforeDay = AppDateMgr.dateIsBeforeDay(str, getPastDate(30, date));
        if (!AppDateMgr.dateIsBeforeDay(str, this.mToDay) && zDateIsBeforeDay) {
            float f2 = this.mTextBaseLine + y;
            int i3 = x + (this.mItemWidth / 2);
            if (isSelected) {
                canvas.drawText(String.valueOf(calendar.getDay()), i3, f2, this.mSelectTextPaint);
                return;
            }
            if (hasScheme) {
                String strValueOf = String.valueOf(calendar.getDay());
                float f3 = i3;
                if (calendar.isCurrentDay()) {
                    paint4 = this.mCurDayTextPaint;
                } else {
                    paint4 = calendar.isCurrentMonth() ? this.mSchemeTextPaint : this.mOtherMonthTextPaint;
                }
                canvas.drawText(strValueOf, f3, f2, paint4);
                return;
            }
            this.mCurDayTextPaint.setColor(-1);
            String strValueOf2 = String.valueOf(calendar.getDay());
            float f4 = i3;
            if (calendar.isCurrentDay()) {
                paint3 = this.mCurDayTextPaint;
            } else {
                paint3 = calendar.isCurrentMonth() ? this.mCurMonthTextPaint : this.mOtherMonthTextPaint;
            }
            canvas.drawText(strValueOf2, f4, f2, paint3);
            return;
        }
        float f5 = this.mTextBaseLine + y;
        int i4 = x + (this.mItemWidth / 2);
        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()), i4, f5, this.mSelectTextPaint);
            return;
        }
        if (hasScheme) {
            String strValueOf3 = String.valueOf(calendar.getDay());
            float f6 = i4;
            if (calendar.isCurrentDay()) {
                paint2 = this.mCurDayTextPaint;
            } else {
                paint2 = calendar.isCurrentMonth() ? this.mOtherMonthTextPaint : this.mSchemeTextPaint;
            }
            canvas.drawText(strValueOf3, f6, f5, paint2);
            return;
        }
        String strValueOf4 = String.valueOf(calendar.getDay());
        float f7 = i4;
        if (calendar.isCurrentDay()) {
            paint = this.mCurDayTextPaint;
        } else {
            paint = calendar.isCurrentMonth() ? this.mOtherMonthTextPaint : this.mSchemeTextPaint;
        }
        canvas.drawText(strValueOf4, f7, f5, paint);
    }

    private static int dipToPx(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String getPastDate(int past, Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - past);
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(calendar.getTime());
    }
}
