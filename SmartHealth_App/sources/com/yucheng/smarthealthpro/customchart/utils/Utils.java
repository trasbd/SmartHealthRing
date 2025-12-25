package com.yucheng.smarthealthpro.customchart.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.dd.plist.ASCIIPropertyListParser;
import com.realsil.sdk.dfu.DfuConstants;
import com.tencent.bugly.BuglyStrategy;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.customchart.formatter.DefaultValueFormatter;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import java.util.List;
import kotlin.time.DurationKt;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public abstract class Utils {
    public static final double DEG2RAD = 0.017453292519943295d;
    public static final float FDEG2RAD = 0.017453292f;
    private static int mMaximumFlingVelocity = 8000;
    private static DisplayMetrics mMetrics = null;
    private static int mMinimumFlingVelocity = 50;
    public static final double DOUBLE_EPSILON = Double.longBitsToDouble(1);
    public static final float FLOAT_EPSILON = Float.intBitsToFloat(1);
    private static Rect mCalcTextHeightRect = new Rect();
    private static Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
    private static Rect mCalcTextSizeRect = new Rect();
    private static final int[] POW_10 = {1, 10, 100, 1000, DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME, BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH, DurationKt.NANOS_IN_MILLIS, 10000000, 100000000, 1000000000};
    private static IValueFormatter mDefaultValueFormatter = generateDefaultValueFormatter();
    private static Rect mDrawableBoundsCache = new Rect();
    private static Rect mDrawTextRectBuffer = new Rect();
    private static Paint.FontMetrics mFontMetricsBuffer = new Paint.FontMetrics();

    public static float getNormalizedAngle(float angle) {
        while (angle < 0.0f) {
            angle += 360.0f;
        }
        return angle % 360.0f;
    }

    public static void init(Context context) {
        if (context == null) {
            mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
            Log.e("MPChartLib-Utils", "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");
        } else {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            mMetrics = context.getResources().getDisplayMetrics();
        }
    }

    @Deprecated
    public static void init(Resources res) {
        mMetrics = res.getDisplayMetrics();
        mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
        mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
    }

    public static float convertDpToPixel(float dp) {
        DisplayMetrics displayMetrics = mMetrics;
        if (displayMetrics == null) {
            Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
            return dp;
        }
        return dp * displayMetrics.density;
    }

    public static float convertPixelsToDp(float px) {
        DisplayMetrics displayMetrics = mMetrics;
        if (displayMetrics == null) {
            Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertPixelsToDp(...). Otherwise conversion does not take place.");
            return px;
        }
        return px / displayMetrics.density;
    }

    public static int calcTextWidth(Paint paint, String demoText) {
        return (int) paint.measureText(demoText);
    }

    public static int calcTextHeight(Paint paint, String demoText) {
        Rect rect = mCalcTextHeightRect;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(demoText, 0, demoText.length(), rect);
        return rect.height();
    }

    public static float getLineHeight(Paint paint) {
        return getLineHeight(paint, mFontMetrics);
    }

    public static float getLineHeight(Paint paint, Paint.FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return fontMetrics.descent - fontMetrics.ascent;
    }

    public static float getLineSpacing(Paint paint) {
        return getLineSpacing(paint, mFontMetrics);
    }

    public static float getLineSpacing(Paint paint, Paint.FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return (fontMetrics.ascent - fontMetrics.top) + fontMetrics.bottom;
    }

    public static FSize calcTextSize(Paint paint, String demoText) {
        FSize fSize = FSize.getInstance(0.0f, 0.0f);
        calcTextSize(paint, demoText, fSize);
        return fSize;
    }

    public static void calcTextSize(Paint paint, String demoText, FSize outputFSize) {
        Rect rect = mCalcTextSizeRect;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(demoText, 0, demoText.length(), rect);
        outputFSize.width = rect.width();
        outputFSize.height = rect.height();
    }

    private static IValueFormatter generateDefaultValueFormatter() {
        return new DefaultValueFormatter(1);
    }

    public static IValueFormatter getDefaultValueFormatter() {
        return mDefaultValueFormatter;
    }

    public static String formatNumber(float number, int digitCount, boolean separateThousands) {
        return formatNumber(number, digitCount, separateThousands, ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    public static String formatNumber(float number, int digitCount, boolean separateThousands, char separateChar) {
        boolean z;
        float f2 = number;
        char[] cArr = new char[35];
        if (f2 == 0.0f) {
            return "0";
        }
        int i2 = 0;
        boolean z2 = f2 < 1.0f && f2 > -1.0f;
        if (f2 < 0.0f) {
            f2 = -f2;
            z = true;
        } else {
            z = false;
        }
        int[] iArr = POW_10;
        int length = digitCount > iArr.length ? iArr.length - 1 : digitCount;
        long jRound = Math.round(f2 * iArr[length]);
        int i3 = 34;
        boolean z3 = false;
        while (true) {
            if (jRound == 0 && i2 >= length + 1) {
                break;
            }
            int i4 = (int) (jRound % 10);
            jRound /= 10;
            int i5 = i3 - 1;
            cArr[i3] = (char) (i4 + 48);
            int i6 = i2 + 1;
            if (i6 == length) {
                i3 -= 2;
                cArr[i5] = ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN;
                i2 += 2;
                z3 = true;
            } else {
                if (separateThousands && jRound != 0 && i6 > length) {
                    if (z3) {
                        if ((i6 - length) % 4 == 0) {
                            i3 -= 2;
                            cArr[i5] = separateChar;
                            i2 += 2;
                        }
                    } else if ((i6 - length) % 4 == 3) {
                        i3 -= 2;
                        cArr[i5] = separateChar;
                        i2 += 2;
                    }
                }
                i2 = i6;
                i3 = i5;
            }
        }
        if (z2) {
            cArr[i3] = '0';
            i2++;
            i3--;
        }
        if (z) {
            cArr[i3] = ASCIIPropertyListParser.DATE_DATE_FIELD_DELIMITER;
            i2++;
        }
        int i7 = 35 - i2;
        return String.valueOf(cArr, i7, 35 - i7);
    }

    public static float roundToNextSignificant(double number) {
        if (Double.isInfinite(number) || Double.isNaN(number) || number == 0.0d) {
            return 0.0f;
        }
        return Math.round(number * r0) / ((float) Math.pow(10.0d, 1 - ((int) Math.ceil((float) Math.log10(number < 0.0d ? -number : number)))));
    }

    public static int getDecimals(float number) {
        float fRoundToNextSignificant = roundToNextSignificant(number);
        if (Float.isInfinite(fRoundToNextSignificant)) {
            return 0;
        }
        return ((int) Math.ceil(-Math.log10(fRoundToNextSignificant))) + 2;
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] iArr = new int[integers.size()];
        copyIntegers(integers, iArr);
        return iArr;
    }

    public static void copyIntegers(List<Integer> from, int[] to) {
        int length = to.length < from.size() ? to.length : from.size();
        for (int i2 = 0; i2 < length; i2++) {
            to[i2] = from.get(i2).intValue();
        }
    }

    public static String[] convertStrings(List<String> strings) {
        int size = strings.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = strings.get(i2);
        }
        return strArr;
    }

    public static void copyStrings(List<String> from, String[] to) {
        int length = to.length < from.size() ? to.length : from.size();
        for (int i2 = 0; i2 < length; i2++) {
            to[i2] = from.get(i2);
        }
    }

    public static double nextUp(double d2) {
        if (d2 == Double.POSITIVE_INFINITY) {
            return d2;
        }
        double d3 = d2 + 0.0d;
        return Double.longBitsToDouble(Double.doubleToRawLongBits(d3) + (d3 >= 0.0d ? 1L : -1L));
    }

    public static MPPointF getPosition(MPPointF center, float dist, float angle) {
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        getPosition(center, dist, angle, mPPointF);
        return mPPointF;
    }

    public static void getPosition(MPPointF center, float dist, float angle, MPPointF outputPoint) {
        double d2 = dist;
        double d3 = angle;
        outputPoint.x = (float) (center.x + (Math.cos(Math.toRadians(d3)) * d2));
        outputPoint.y = (float) (center.y + (d2 * Math.sin(Math.toRadians(d3))));
    }

    public static void velocityTrackerPointerUpCleanUpIfNecessary(MotionEvent ev, VelocityTracker tracker) {
        tracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
        int actionIndex = ev.getActionIndex();
        int pointerId = ev.getPointerId(actionIndex);
        float xVelocity = tracker.getXVelocity(pointerId);
        float yVelocity = tracker.getYVelocity(pointerId);
        int pointerCount = ev.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            if (i2 != actionIndex) {
                int pointerId2 = ev.getPointerId(i2);
                if ((tracker.getXVelocity(pointerId2) * xVelocity) + (tracker.getYVelocity(pointerId2) * yVelocity) < 0.0f) {
                    tracker.clear();
                    return;
                }
            }
        }
    }

    public static void postInvalidateOnAnimation(View view) {
        view.postInvalidateOnAnimation();
    }

    public static int getMinimumFlingVelocity() {
        return mMinimumFlingVelocity;
    }

    public static int getMaximumFlingVelocity() {
        return mMaximumFlingVelocity;
    }

    public static void drawImage(Canvas canvas, Drawable drawable, int x, int y, int width, int height) {
        MPPointF mPPointF = MPPointF.getInstance();
        mPPointF.x = x - (width / 2);
        mPPointF.y = y - (height / 2);
        drawable.copyBounds(mDrawableBoundsCache);
        drawable.setBounds(mDrawableBoundsCache.left, mDrawableBoundsCache.top, mDrawableBoundsCache.left + width, mDrawableBoundsCache.top + width);
        int iSave = canvas.save();
        canvas.translate(mPPointF.x, mPPointF.y);
        drawable.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    public static void drawXAxisValue(Canvas c2, String text, float x, float y, Paint paint, MPPointF anchor, float angleDegrees) {
        float fontMetrics = paint.getFontMetrics(mFontMetricsBuffer);
        paint.getTextBounds(text, 0, text.length(), mDrawTextRectBuffer);
        float fWidth = 0.0f - mDrawTextRectBuffer.left;
        float f2 = (-mFontMetricsBuffer.ascent) + 0.0f;
        Paint.Align textAlign = paint.getTextAlign();
        paint.setTextAlign(Paint.Align.LEFT);
        float fDp2px = DpUtil.dp2px(MyApplication.getInstance().getApplicationContext(), 3.0f);
        if (angleDegrees != 0.0f) {
            float fWidth2 = fWidth - (mDrawTextRectBuffer.width() * 0.5f);
            float f3 = f2 - (fontMetrics * 0.5f);
            if (anchor.x != 0.5f || anchor.y != 0.5f) {
                FSize sizeOfRotatedRectangleByDegrees = getSizeOfRotatedRectangleByDegrees(mDrawTextRectBuffer.width(), fontMetrics, angleDegrees);
                x -= sizeOfRotatedRectangleByDegrees.width * (anchor.x - 0.5f);
                y -= sizeOfRotatedRectangleByDegrees.height * (anchor.y - 0.5f);
                FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
            }
            c2.save();
            c2.translate(x, y);
            c2.rotate(angleDegrees);
            c2.drawText(text, fWidth2, f3 - fDp2px, paint);
            c2.restore();
        } else {
            if (anchor.x != 0.0f || anchor.y != 0.0f) {
                fWidth -= mDrawTextRectBuffer.width() * anchor.x;
                f2 -= fontMetrics * anchor.y;
            }
            c2.drawText(text, fWidth + x, (f2 + y) - fDp2px, paint);
        }
        paint.setTextAlign(textAlign);
    }

    public static void drawMultilineText(Canvas c2, StaticLayout textLayout, float x, float y, TextPaint paint, MPPointF anchor, float angleDegrees) {
        float fontMetrics = paint.getFontMetrics(mFontMetricsBuffer);
        float width = textLayout.getWidth();
        float lineCount = textLayout.getLineCount() * fontMetrics;
        float f2 = 0.0f - mDrawTextRectBuffer.left;
        float f3 = lineCount + 0.0f;
        Paint.Align textAlign = paint.getTextAlign();
        paint.setTextAlign(Paint.Align.LEFT);
        if (angleDegrees != 0.0f) {
            float f4 = f2 - (width * 0.5f);
            float f5 = f3 - (lineCount * 0.5f);
            if (anchor.x != 0.5f || anchor.y != 0.5f) {
                FSize sizeOfRotatedRectangleByDegrees = getSizeOfRotatedRectangleByDegrees(width, lineCount, angleDegrees);
                x -= sizeOfRotatedRectangleByDegrees.width * (anchor.x - 0.5f);
                y -= sizeOfRotatedRectangleByDegrees.height * (anchor.y - 0.5f);
                FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
            }
            c2.save();
            c2.translate(x, y);
            c2.rotate(angleDegrees);
            c2.translate(f4, f5);
            textLayout.draw(c2);
            c2.restore();
        } else {
            if (anchor.x != 0.0f || anchor.y != 0.0f) {
                f2 -= width * anchor.x;
                f3 -= lineCount * anchor.y;
            }
            c2.save();
            c2.translate(f2 + x, f3 + y);
            textLayout.draw(c2);
            c2.restore();
        }
        paint.setTextAlign(textAlign);
    }

    public static void drawMultilineText(Canvas c2, String text, float x, float y, TextPaint paint, FSize constrainedToSize, MPPointF anchor, float angleDegrees) {
        drawMultilineText(c2, new StaticLayout(text, 0, text.length(), paint, (int) Math.max(Math.ceil(constrainedToSize.width), 1.0d), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false), x, y, paint, anchor, angleDegrees);
    }

    public static FSize getSizeOfRotatedRectangleByDegrees(FSize rectangleSize, float degrees) {
        return getSizeOfRotatedRectangleByRadians(rectangleSize.width, rectangleSize.height, degrees * 0.017453292f);
    }

    public static FSize getSizeOfRotatedRectangleByRadians(FSize rectangleSize, float radians) {
        return getSizeOfRotatedRectangleByRadians(rectangleSize.width, rectangleSize.height, radians);
    }

    public static FSize getSizeOfRotatedRectangleByDegrees(float rectangleWidth, float rectangleHeight, float degrees) {
        return getSizeOfRotatedRectangleByRadians(rectangleWidth, rectangleHeight, degrees * 0.017453292f);
    }

    public static FSize getSizeOfRotatedRectangleByRadians(float rectangleWidth, float rectangleHeight, float radians) {
        double d2 = radians;
        return FSize.getInstance(Math.abs(((float) Math.cos(d2)) * rectangleWidth) + Math.abs(((float) Math.sin(d2)) * rectangleHeight), Math.abs(rectangleWidth * ((float) Math.sin(d2))) + Math.abs(rectangleHeight * ((float) Math.cos(d2))));
    }

    public static int getSDKInt() {
        return Build.VERSION.SDK_INT;
    }
}
