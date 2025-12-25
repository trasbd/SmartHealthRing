package com.yucheng.smarthealthpro.perfect.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes5.dex */
public class SlantedTextView extends View {
    public static final int MODE_LEFT = 0;
    public static final int MODE_LEFT_BOTTOM = 2;
    public static final int MODE_LEFT_BOTTOM_TRIANGLE = 6;
    public static final int MODE_LEFT_TRIANGLE = 4;
    public static final int MODE_RIGHT = 1;
    public static final int MODE_RIGHT_BOTTOM = 3;
    public static final int MODE_RIGHT_BOTTOM_TRIANGLE = 7;
    public static final int MODE_RIGHT_TRIANGLE = 5;
    public static final int ROTATE_ANGLE = 45;
    private int mMode;
    private Paint mPaint;
    private int mSlantedBackgroundColor;
    private float mSlantedLength;
    private String mSlantedText;
    private int mTextColor;
    private TextPaint mTextPaint;
    private float mTextSize;

    public SlantedTextView(Context context) {
        this(context, null);
    }

    public SlantedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SlantedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mSlantedLength = 40.0f;
        this.mTextSize = 16.0f;
        this.mSlantedBackgroundColor = 0;
        this.mTextColor = -1;
        this.mSlantedText = "";
        this.mMode = 0;
        init(attrs);
    }

    public SlantedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mSlantedLength = 40.0f;
        this.mTextSize = 16.0f;
        this.mSlantedBackgroundColor = 0;
        this.mTextColor = -1;
        this.mSlantedText = "";
        this.mMode = 0;
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.SlantedTextView);
        this.mTextSize = typedArrayObtainStyledAttributes.getDimension(R.styleable.SlantedTextView_slantedTextSize, this.mTextSize);
        this.mTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SlantedTextView_slantedTextColor, this.mTextColor);
        this.mSlantedLength = typedArrayObtainStyledAttributes.getDimension(R.styleable.SlantedTextView_slantedLength, this.mSlantedLength);
        this.mSlantedBackgroundColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SlantedTextView_slantedBackgroundColor, this.mSlantedBackgroundColor);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SlantedTextView_slantedText)) {
            this.mSlantedText = typedArrayObtainStyledAttributes.getString(R.styleable.SlantedTextView_slantedText);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SlantedTextView_slantedMode)) {
            this.mMode = typedArrayObtainStyledAttributes.getInt(R.styleable.SlantedTextView_slantedMode, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mSlantedBackgroundColor);
        TextPaint textPaint = new TextPaint(1);
        this.mTextPaint = textPaint;
        textPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        this.mTextPaint.setColor(this.mTextColor);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawText(canvas);
    }

    private void drawBackground(Canvas canvas) {
        Path path = new Path();
        int width = getWidth();
        int height = getHeight();
        if (width != height) {
            throw new IllegalStateException("SlantedTextView's width must equal to height");
        }
        switch (this.mMode) {
            case 0:
                path = getModeLeftPath(path, width, height);
                break;
            case 1:
                path = getModeRightPath(path, width, height);
                break;
            case 2:
                path = getModeLeftBottomPath(path, width, height);
                break;
            case 3:
                path = getModeRightBottomPath(path, width, height);
                break;
            case 4:
                path = getModeLeftTrianglePath(path, width, height);
                break;
            case 5:
                path = getModeRightTrianglePath(path, width, height);
                break;
            case 6:
                path = getModeLeftBottomTrianglePath(path, width, height);
                break;
            case 7:
                path = getModeRightBottomTrianglePath(path, width, height);
                break;
        }
        path.close();
        canvas.drawPath(path, this.mPaint);
        canvas.save();
    }

    private Path getModeLeftPath(Path path, int w, int h2) {
        float f2 = w;
        path.moveTo(f2, 0.0f);
        float f3 = h2;
        path.lineTo(0.0f, f3);
        path.lineTo(0.0f, f3 - this.mSlantedLength);
        path.lineTo(f2 - this.mSlantedLength, 0.0f);
        return path;
    }

    private Path getModeRightPath(Path path, int w, int h2) {
        float f2 = w;
        float f3 = h2;
        path.lineTo(f2, f3);
        path.lineTo(f2, f3 - this.mSlantedLength);
        path.lineTo(this.mSlantedLength, 0.0f);
        return path;
    }

    private Path getModeLeftBottomPath(Path path, int w, int h2) {
        float f2 = w;
        float f3 = h2;
        path.lineTo(f2, f3);
        path.lineTo(f2 - this.mSlantedLength, f3);
        path.lineTo(0.0f, this.mSlantedLength);
        return path;
    }

    private Path getModeRightBottomPath(Path path, int w, int h2) {
        float f2 = h2;
        path.moveTo(0.0f, f2);
        path.lineTo(this.mSlantedLength, f2);
        float f3 = w;
        path.lineTo(f3, this.mSlantedLength);
        path.lineTo(f3, 0.0f);
        return path;
    }

    private Path getModeLeftTrianglePath(Path path, int w, int h2) {
        path.lineTo(0.0f, h2);
        path.lineTo(w, 0.0f);
        return path;
    }

    private Path getModeRightTrianglePath(Path path, int w, int h2) {
        float f2 = w;
        path.lineTo(f2, 0.0f);
        path.lineTo(f2, h2);
        return path;
    }

    private Path getModeLeftBottomTrianglePath(Path path, int w, int h2) {
        float f2 = h2;
        path.lineTo(w, f2);
        path.lineTo(0.0f, f2);
        return path;
    }

    private Path getModeRightBottomTrianglePath(Path path, int w, int h2) {
        float f2 = h2;
        path.moveTo(0.0f, f2);
        float f3 = w;
        path.lineTo(f3, f2);
        path.lineTo(f3, 0.0f);
        return path;
    }

    private void drawText(Canvas canvas) {
        float[] fArrCalculateXY = calculateXY(canvas, (int) (canvas.getWidth() - (this.mSlantedLength / 2.0f)), (int) (canvas.getHeight() - (this.mSlantedLength / 2.0f)));
        float f2 = fArrCalculateXY[0];
        float f3 = fArrCalculateXY[1];
        canvas.rotate(fArrCalculateXY[4], fArrCalculateXY[2], fArrCalculateXY[3]);
        canvas.drawText(this.mSlantedText, f2, f3, this.mTextPaint);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private float[] calculateXY(Canvas canvas, int w, int h2) {
        float[] fArr = new float[5];
        int i2 = (int) (this.mSlantedLength / 2.0f);
        switch (this.mMode) {
            case 0:
            case 4:
                RectF rectF = new RectF(new Rect(0, 0, w, h2));
                TextPaint textPaint = this.mTextPaint;
                String str = this.mSlantedText;
                rectF.right = textPaint.measureText(str, 0, str.length());
                rectF.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
                rectF.left += (r4.width() - rectF.right) / 2.0f;
                rectF.top += (r4.height() - rectF.bottom) / 2.0f;
                fArr[0] = rectF.left;
                fArr[1] = rectF.top - this.mTextPaint.ascent();
                fArr[2] = w / 2;
                fArr[3] = h2 / 2;
                fArr[4] = -45.0f;
                return fArr;
            case 1:
            case 5:
                RectF rectF2 = new RectF(new Rect(i2, 0, w + i2, h2));
                TextPaint textPaint2 = this.mTextPaint;
                String str2 = this.mSlantedText;
                rectF2.right = textPaint2.measureText(str2, 0, str2.length());
                rectF2.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
                rectF2.left += (r6.width() - rectF2.right) / 2.0f;
                rectF2.top += (r6.height() - rectF2.bottom) / 2.0f;
                fArr[0] = rectF2.left;
                fArr[1] = rectF2.top - this.mTextPaint.ascent();
                fArr[2] = (w / 2) + i2;
                fArr[3] = h2 / 2;
                fArr[4] = 45.0f;
                return fArr;
            case 2:
            case 6:
                RectF rectF3 = new RectF(new Rect(0, i2, w, h2 + i2));
                TextPaint textPaint3 = this.mTextPaint;
                String str3 = this.mSlantedText;
                rectF3.right = textPaint3.measureText(str3, 0, str3.length());
                rectF3.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
                rectF3.left += (r6.width() - rectF3.right) / 2.0f;
                rectF3.top += (r6.height() - rectF3.bottom) / 2.0f;
                fArr[0] = rectF3.left;
                fArr[1] = rectF3.top - this.mTextPaint.ascent();
                fArr[2] = w / 2;
                fArr[3] = (h2 / 2) + i2;
                fArr[4] = 45.0f;
                return fArr;
            case 3:
            case 7:
                RectF rectF4 = new RectF(new Rect(i2, i2, w + i2, h2 + i2));
                TextPaint textPaint4 = this.mTextPaint;
                String str4 = this.mSlantedText;
                rectF4.right = textPaint4.measureText(str4, 0, str4.length());
                rectF4.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
                rectF4.left += (r6.width() - rectF4.right) / 2.0f;
                rectF4.top += (r6.height() - rectF4.bottom) / 2.0f;
                fArr[0] = rectF4.left;
                fArr[1] = rectF4.top - this.mTextPaint.ascent();
                fArr[2] = (w / 2) + i2;
                fArr[3] = (h2 / 2) + i2;
                fArr[4] = -45.0f;
                return fArr;
            default:
                return fArr;
        }
    }

    public SlantedTextView setText(String str) {
        this.mSlantedText = str;
        postInvalidate();
        return this;
    }

    public SlantedTextView setText(int res) throws Resources.NotFoundException {
        String string = getResources().getString(res);
        if (!TextUtils.isEmpty(string)) {
            setText(string);
        }
        return this;
    }

    public String getText() {
        return this.mSlantedText;
    }

    public SlantedTextView setSlantedBackgroundColor(int color) {
        this.mSlantedBackgroundColor = color;
        this.mPaint.setColor(color);
        postInvalidate();
        return this;
    }

    public SlantedTextView setTextColor(int color) {
        this.mTextColor = color;
        this.mTextPaint.setColor(color);
        postInvalidate();
        return this;
    }

    public SlantedTextView setMode(int mode) {
        int i2 = this.mMode;
        if (i2 > 7 || i2 < 0) {
            throw new IllegalArgumentException(mode + "is illegal argument ,please use right value");
        }
        this.mMode = mode;
        postInvalidate();
        return this;
    }

    public int getMode() {
        return this.mMode;
    }

    public SlantedTextView setTextSize(int size) {
        float f2 = size;
        this.mTextSize = f2;
        this.mPaint.setTextSize(f2);
        postInvalidate();
        return this;
    }

    public SlantedTextView setSlantedLength(int length) {
        this.mSlantedLength = length;
        postInvalidate();
        return this;
    }
}
