package com.yucheng.smarthealthpro.me.setting.contacts.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;

/* loaded from: classes5.dex */
public class TextDrawable extends ShapeDrawable {
    private static final float SHADE_FACTOR = 0.9f;
    private final Paint borderPaint;
    private final int borderThickness;
    private final int color;
    private final int fontSize;
    private final int height;
    private final float radius;
    private final RectShape shape;
    private final String text;
    private final Paint textPaint;
    private final int width;

    public interface IBuilder {
        TextDrawable build(String text, int color);
    }

    public interface IConfigBuilder {
        IConfigBuilder bold();

        IShapeBuilder endConfig();

        IConfigBuilder fontSize(int size);

        IConfigBuilder height(int height);

        IConfigBuilder textColor(int color);

        IConfigBuilder toUpperCase();

        IConfigBuilder useFont(Typeface font);

        IConfigBuilder width(int width);

        IConfigBuilder withBorder(int thickness);
    }

    public interface IShapeBuilder {
        IConfigBuilder beginConfig();

        TextDrawable buildRect(String text, int color);

        TextDrawable buildRound(String text, int color);

        TextDrawable buildRoundRect(String text, int color, int radius);

        IBuilder rect();

        IBuilder round();

        IBuilder roundRect(int radius);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    private TextDrawable(Builder builder) {
        super(builder.shape);
        this.shape = builder.shape;
        this.height = builder.height;
        this.width = builder.width;
        this.radius = builder.radius;
        this.text = builder.toUpperCase ? builder.text.toUpperCase() : builder.text;
        int i2 = builder.color;
        this.color = i2;
        this.fontSize = builder.fontSize;
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setColor(builder.textColor);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(builder.isBold);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(builder.font);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(builder.borderThickness);
        int i3 = builder.borderThickness;
        this.borderThickness = i3;
        Paint paint2 = new Paint();
        this.borderPaint = paint2;
        paint2.setColor(getDarkerShade(i2));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(i3);
        getPaint().setColor(i2);
    }

    private int getDarkerShade(int color) {
        return Color.rgb((int) (Color.red(color) * 0.9f), (int) (Color.green(color) * 0.9f), (int) (Color.blue(color) * 0.9f));
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect bounds = getBounds();
        if (this.borderThickness > 0) {
            drawBorder(canvas);
        }
        int iSave = canvas.save();
        canvas.translate(bounds.left, bounds.top);
        int iWidth = this.width;
        if (iWidth < 0) {
            iWidth = bounds.width();
        }
        int iHeight = this.height;
        if (iHeight < 0) {
            iHeight = bounds.height();
        }
        int iMin = this.fontSize;
        if (iMin < 0) {
            iMin = Math.min(iWidth, iHeight) / 2;
        }
        this.textPaint.setTextSize(iMin);
        canvas.drawText(this.text, iWidth / 2, (iHeight / 2) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f), this.textPaint);
        canvas.restoreToCount(iSave);
    }

    private void drawBorder(Canvas canvas) {
        RectF rectF = new RectF(getBounds());
        int i2 = this.borderThickness;
        rectF.inset(i2 / 2, i2 / 2);
        RectShape rectShape = this.shape;
        if (rectShape instanceof OvalShape) {
            canvas.drawOval(rectF, this.borderPaint);
        } else if (rectShape instanceof RoundRectShape) {
            float f2 = this.radius;
            canvas.drawRoundRect(rectF, f2, f2, this.borderPaint);
        } else {
            canvas.drawRect(rectF, this.borderPaint);
        }
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.textPaint.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        this.textPaint.setColorFilter(cf);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.width;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.height;
    }

    public static IShapeBuilder builder() {
        return new Builder();
    }

    public static class Builder implements IConfigBuilder, IShapeBuilder, IBuilder {
        private int borderThickness;
        private int color;
        private Typeface font;
        private int fontSize;
        private int height;
        private boolean isBold;
        public float radius;
        private RectShape shape;
        private String text;
        public int textColor;
        private boolean toUpperCase;
        private int width;

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IShapeBuilder
        public IConfigBuilder beginConfig() {
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IShapeBuilder endConfig() {
            return this;
        }

        private Builder() {
            this.text = "";
            this.color = -7829368;
            this.textColor = -1;
            this.borderThickness = 0;
            this.width = -1;
            this.height = -1;
            this.shape = new RectShape();
            this.font = Typeface.create("sans-serif-light", 0);
            this.fontSize = -1;
            this.isBold = false;
            this.toUpperCase = false;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder width(int width) {
            this.width = width;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder height(int height) {
            this.height = height;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder textColor(int color) {
            this.textColor = color;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder withBorder(int thickness) {
            this.borderThickness = thickness;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder useFont(Typeface font) {
            this.font = font;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder fontSize(int size) {
            this.fontSize = size;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder bold() {
            this.isBold = true;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IConfigBuilder
        public IConfigBuilder toUpperCase() {
            this.toUpperCase = true;
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IShapeBuilder
        public IBuilder rect() {
            this.shape = new RectShape();
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IShapeBuilder
        public IBuilder round() {
            this.shape = new OvalShape();
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IShapeBuilder
        public IBuilder roundRect(int radius) {
            float f2 = radius;
            this.radius = f2;
            this.shape = new RoundRectShape(new float[]{f2, f2, f2, f2, f2, f2, f2, f2}, null, null);
            return this;
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IShapeBuilder
        public TextDrawable buildRect(String text, int color) {
            rect();
            return build(text, color);
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IShapeBuilder
        public TextDrawable buildRoundRect(String text, int color, int radius) {
            roundRect(radius);
            return build(text, color);
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IShapeBuilder
        public TextDrawable buildRound(String text, int color) {
            round();
            return build(text, color);
        }

        @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable.IBuilder
        public TextDrawable build(String text, int color) {
            this.color = color;
            this.text = text;
            return new TextDrawable(this);
        }
    }
}
