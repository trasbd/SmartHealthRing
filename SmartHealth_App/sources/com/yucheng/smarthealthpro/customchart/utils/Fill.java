package com.yucheng.smarthealthpro.customchart.utils;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;

/* loaded from: classes4.dex */
public class Fill {
    private int mAlpha;
    private Integer mColor;
    protected Drawable mDrawable;
    private Integer mFinalColor;
    private int[] mGradientColors;
    private float[] mGradientPositions;
    private Type mType;

    public enum Direction {
        DOWN,
        UP,
        RIGHT,
        LEFT
    }

    public enum Type {
        EMPTY,
        COLOR,
        LINEAR_GRADIENT,
        DRAWABLE
    }

    public Fill() {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
    }

    public Fill(int color) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.COLOR;
        this.mColor = Integer.valueOf(color);
        calculateFinalColor();
    }

    public Fill(int startColor, int endColor) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.LINEAR_GRADIENT;
        this.mGradientColors = new int[]{startColor, endColor};
    }

    public Fill(int[] gradientColors) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.LINEAR_GRADIENT;
        this.mGradientColors = gradientColors;
    }

    public Fill(int[] gradientColors, float[] gradientPositions) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.LINEAR_GRADIENT;
        this.mGradientColors = gradientColors;
        this.mGradientPositions = gradientPositions;
    }

    public Fill(Drawable drawable) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.DRAWABLE;
        this.mDrawable = drawable;
    }

    public Type getType() {
        return this.mType;
    }

    public void setType(Type type) {
        this.mType = type;
    }

    public Integer getColor() {
        return this.mColor;
    }

    public void setColor(int color) {
        this.mColor = Integer.valueOf(color);
        calculateFinalColor();
    }

    public int[] getGradientColors() {
        return this.mGradientColors;
    }

    public void setGradientColors(int[] colors) {
        this.mGradientColors = colors;
    }

    public float[] getGradientPositions() {
        return this.mGradientPositions;
    }

    public void setGradientPositions(float[] positions) {
        this.mGradientPositions = positions;
    }

    public void setGradientColors(int startColor, int endColor) {
        this.mGradientColors = new int[]{startColor, endColor};
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(int alpha) {
        this.mAlpha = alpha;
        calculateFinalColor();
    }

    private void calculateFinalColor() {
        if (this.mColor == null) {
            this.mFinalColor = null;
        } else {
            this.mFinalColor = Integer.valueOf((((int) Math.floor((((r0.intValue() >> 24) / 255.0d) * (this.mAlpha / 255.0d)) * 255.0d)) << 24) | (this.mColor.intValue() & ViewCompat.MEASURED_SIZE_MASK));
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.customchart.utils.Fill$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$utils$Fill$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$utils$Fill$Type = iArr;
            try {
                iArr[Type.EMPTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$utils$Fill$Type[Type.COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$utils$Fill$Type[Type.LINEAR_GRADIENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$utils$Fill$Type[Type.DRAWABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void fillRect(Canvas c2, Paint paint, float left, float top, float right, float bottom, Direction gradientDirection) {
        float f2;
        float f3;
        Drawable drawable;
        int i2 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$utils$Fill$Type[this.mType.ordinal()];
        if (i2 == 2) {
            if (this.mFinalColor == null) {
                return;
            }
            if (isClipPathSupported()) {
                int iSave = c2.save();
                c2.clipRect(left, top, right, bottom);
                c2.drawColor(this.mFinalColor.intValue());
                c2.restoreToCount(iSave);
                return;
            }
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.mFinalColor.intValue());
            c2.drawRect(left, top, right, bottom, paint);
            paint.setColor(color);
            paint.setStyle(style);
            return;
        }
        if (i2 != 3) {
            if (i2 == 4 && (drawable = this.mDrawable) != null) {
                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                this.mDrawable.draw(c2);
                return;
            }
            return;
        }
        if (this.mGradientColors == null) {
            return;
        }
        if (gradientDirection == Direction.RIGHT) {
            f2 = right;
        } else {
            Direction direction = Direction.LEFT;
            f2 = left;
        }
        float f4 = (int) f2;
        if (gradientDirection == Direction.UP) {
            f3 = bottom;
        } else {
            Direction direction2 = Direction.DOWN;
            f3 = top;
        }
        paint.setShader(new LinearGradient(f4, (int) f3, (int) ((gradientDirection != Direction.RIGHT && gradientDirection == Direction.LEFT) ? right : left), (int) ((gradientDirection != Direction.UP && gradientDirection == Direction.DOWN) ? bottom : top), this.mGradientColors, this.mGradientPositions, Shader.TileMode.MIRROR));
        c2.drawRect(left, top, right, bottom, paint);
    }

    public void fillPath(Canvas c2, Path path, Paint paint, RectF clipRect) {
        int i2 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$utils$Fill$Type[this.mType.ordinal()];
        if (i2 != 2) {
            if (i2 == 3) {
                if (this.mGradientColors == null) {
                    return;
                }
                paint.setShader(new LinearGradient(0.0f, 0.0f, c2.getWidth(), c2.getHeight(), this.mGradientColors, this.mGradientPositions, Shader.TileMode.MIRROR));
                c2.drawPath(path, paint);
                return;
            }
            if (i2 == 4 && this.mDrawable != null) {
                ensureClipPathSupported();
                int iSave = c2.save();
                c2.clipPath(path);
                this.mDrawable.setBounds(clipRect == null ? 0 : (int) clipRect.left, clipRect != null ? (int) clipRect.top : 0, clipRect == null ? c2.getWidth() : (int) clipRect.right, clipRect == null ? c2.getHeight() : (int) clipRect.bottom);
                this.mDrawable.draw(c2);
                c2.restoreToCount(iSave);
                return;
            }
            return;
        }
        if (this.mFinalColor == null) {
            return;
        }
        if (clipRect != null && isClipPathSupported()) {
            int iSave2 = c2.save();
            c2.clipPath(path);
            c2.drawColor(this.mFinalColor.intValue());
            c2.restoreToCount(iSave2);
            return;
        }
        Paint.Style style = paint.getStyle();
        int color = paint.getColor();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.mFinalColor.intValue());
        c2.drawPath(path, paint);
        paint.setColor(color);
        paint.setStyle(style);
    }

    private boolean isClipPathSupported() {
        return Utils.getSDKInt() >= 18;
    }

    private void ensureClipPathSupported() {
        if (Utils.getSDKInt() < 18) {
            throw new RuntimeException("Fill-drawables not (yet) supported below API level 18, this code was run on API level " + Utils.getSDKInt() + ".");
        }
    }
}
