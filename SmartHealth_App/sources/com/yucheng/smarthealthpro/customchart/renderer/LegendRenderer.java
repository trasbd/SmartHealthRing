package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import com.yucheng.smarthealthpro.customchart.components.Legend;
import com.yucheng.smarthealthpro.customchart.components.LegendEntry;
import com.yucheng.smarthealthpro.customchart.utils.FSize;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries;
    protected Paint.FontMetrics legendFontMetrics;
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath;

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.computedEntries = new ArrayList(16);
        this.legendFontMetrics = new Paint.FontMetrics();
        this.mLineFormPath = new Path();
        this.mLegend = legend;
        Paint paint = new Paint(1);
        this.mLegendLabelPaint = paint;
        paint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        Paint paint2 = new Paint(1);
        this.mLegendFormPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0167  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void computeLegend(com.yucheng.smarthealthpro.customchart.data.ChartData<?> r21) {
        /*
            Method dump skipped, instructions count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.customchart.renderer.LegendRenderer.computeLegend(com.yucheng.smarthealthpro.customchart.data.ChartData):void");
    }

    public void renderLegend(Canvas c2) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        List<Boolean> list;
        float f8;
        List<FSize> list2;
        Canvas canvas;
        int i2;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float fContentTop;
        float f15;
        float f16;
        Legend.LegendDirection legendDirection;
        LegendEntry legendEntry;
        float fCalcTextWidth;
        float fContentBottom;
        float fContentRight;
        float fContentLeft;
        double d2;
        if (this.mLegend.isEnabled()) {
            Typeface typeface = this.mLegend.getTypeface();
            if (typeface != null) {
                this.mLegendLabelPaint.setTypeface(typeface);
            }
            this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
            this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
            float lineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.legendFontMetrics);
            float lineSpacing = Utils.getLineSpacing(this.mLegendLabelPaint, this.legendFontMetrics) + Utils.convertDpToPixel(this.mLegend.getYEntrySpace());
            float fCalcTextHeight = lineHeight - (Utils.calcTextHeight(this.mLegendLabelPaint, "ABC") / 2.0f);
            LegendEntry[] entries = this.mLegend.getEntries();
            float fConvertDpToPixel = Utils.convertDpToPixel(this.mLegend.getFormToTextSpace());
            float fConvertDpToPixel2 = Utils.convertDpToPixel(this.mLegend.getXEntrySpace());
            Legend.LegendOrientation orientation = this.mLegend.getOrientation();
            Legend.LegendHorizontalAlignment horizontalAlignment = this.mLegend.getHorizontalAlignment();
            Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
            Legend.LegendDirection direction = this.mLegend.getDirection();
            float fConvertDpToPixel3 = Utils.convertDpToPixel(this.mLegend.getFormSize());
            float fConvertDpToPixel4 = Utils.convertDpToPixel(this.mLegend.getStackSpace());
            float yOffset = this.mLegend.getYOffset();
            float xOffset = this.mLegend.getXOffset();
            int i3 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment[horizontalAlignment.ordinal()];
            float f17 = fConvertDpToPixel4;
            float f18 = fConvertDpToPixel2;
            if (i3 != 1) {
                if (i3 == 2) {
                    f2 = lineHeight;
                    f3 = lineSpacing;
                    if (orientation == Legend.LegendOrientation.VERTICAL) {
                        fContentRight = this.mViewPortHandler.getChartWidth();
                    } else {
                        fContentRight = this.mViewPortHandler.contentRight();
                    }
                    f5 = fContentRight - xOffset;
                    if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        f5 -= this.mLegend.mNeededWidth;
                    }
                } else if (i3 != 3) {
                    f2 = lineHeight;
                    f3 = lineSpacing;
                    f4 = 0.0f;
                } else {
                    if (orientation == Legend.LegendOrientation.VERTICAL) {
                        fContentLeft = this.mViewPortHandler.getChartWidth() / 2.0f;
                    } else {
                        fContentLeft = this.mViewPortHandler.contentLeft() + (this.mViewPortHandler.contentWidth() / 2.0f);
                    }
                    f5 = fContentLeft + (direction == Legend.LegendDirection.LEFT_TO_RIGHT ? xOffset : -xOffset);
                    if (orientation == Legend.LegendOrientation.VERTICAL) {
                        f3 = lineSpacing;
                        double d3 = f5;
                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            f2 = lineHeight;
                            d2 = ((-this.mLegend.mNeededWidth) / 2.0d) + xOffset;
                        } else {
                            f2 = lineHeight;
                            d2 = (this.mLegend.mNeededWidth / 2.0d) - xOffset;
                        }
                        f5 = (float) (d3 + d2);
                    } else {
                        f2 = lineHeight;
                        f3 = lineSpacing;
                    }
                }
                f4 = f5;
            } else {
                f2 = lineHeight;
                f3 = lineSpacing;
                if (orientation != Legend.LegendOrientation.VERTICAL) {
                    xOffset += this.mViewPortHandler.contentLeft();
                }
                if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                    f5 = this.mLegend.mNeededWidth + xOffset;
                    f4 = f5;
                } else {
                    f4 = xOffset;
                }
            }
            int i4 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation[orientation.ordinal()];
            if (i4 != 1) {
                if (i4 != 2) {
                    return;
                }
                int i5 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment[verticalAlignment.ordinal()];
                if (i5 == 1) {
                    fContentTop = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? 0.0f : this.mViewPortHandler.contentTop()) + yOffset;
                } else if (i5 == 2) {
                    if (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER) {
                        fContentBottom = this.mViewPortHandler.getChartHeight();
                    } else {
                        fContentBottom = this.mViewPortHandler.contentBottom();
                    }
                    fContentTop = fContentBottom - (this.mLegend.mNeededHeight + yOffset);
                } else {
                    fContentTop = i5 != 3 ? 0.0f : ((this.mViewPortHandler.getChartHeight() / 2.0f) - (this.mLegend.mNeededHeight / 2.0f)) + this.mLegend.getYOffset();
                }
                float f19 = fContentTop;
                float f20 = 0.0f;
                boolean z = false;
                int i6 = 0;
                while (i6 < entries.length) {
                    LegendEntry legendEntry2 = entries[i6];
                    boolean z2 = legendEntry2.form != Legend.LegendForm.NONE;
                    float fConvertDpToPixel5 = Float.isNaN(legendEntry2.formSize) ? fConvertDpToPixel3 : Utils.convertDpToPixel(legendEntry2.formSize);
                    if (z2) {
                        fCalcTextWidth = direction == Legend.LegendDirection.LEFT_TO_RIGHT ? f4 + f20 : f4 - (fConvertDpToPixel5 - f20);
                        f16 = f17;
                        f15 = fCalcTextHeight;
                        legendDirection = direction;
                        drawForm(c2, fCalcTextWidth, f19 + fCalcTextHeight, legendEntry2, this.mLegend);
                        if (legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            fCalcTextWidth += fConvertDpToPixel5;
                        }
                        legendEntry = legendEntry2;
                    } else {
                        f15 = fCalcTextHeight;
                        f16 = f17;
                        legendDirection = direction;
                        legendEntry = legendEntry2;
                        fCalcTextWidth = f4;
                    }
                    if (legendEntry.label != null) {
                        if (z2 && !z) {
                            fCalcTextWidth += legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT ? fConvertDpToPixel : -fConvertDpToPixel;
                        } else if (z) {
                            fCalcTextWidth = f4;
                        }
                        if (legendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            fCalcTextWidth -= Utils.calcTextWidth(this.mLegendLabelPaint, legendEntry.label);
                        }
                        float f21 = fCalcTextWidth;
                        if (!z) {
                            drawLabel(c2, f21, f19 + f2, legendEntry.label);
                        } else {
                            f19 += f2 + f3;
                            drawLabel(c2, f21, f19 + f2, legendEntry.label);
                        }
                        f19 += f2 + f3;
                        f20 = 0.0f;
                    } else {
                        f20 += fConvertDpToPixel5 + f16;
                        z = true;
                    }
                    i6++;
                    f17 = f16;
                    direction = legendDirection;
                    fCalcTextHeight = f15;
                }
                return;
            }
            float f22 = f17;
            List<FSize> calculatedLineSizes = this.mLegend.getCalculatedLineSizes();
            List<FSize> calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
            List<Boolean> calculatedLabelBreakPoints = this.mLegend.getCalculatedLabelBreakPoints();
            int i7 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment[verticalAlignment.ordinal()];
            if (i7 != 1) {
                if (i7 == 2) {
                    yOffset = (this.mViewPortHandler.getChartHeight() - yOffset) - this.mLegend.mNeededHeight;
                } else {
                    yOffset = i7 != 3 ? 0.0f : yOffset + ((this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0f);
                }
            }
            int length = entries.length;
            float f23 = f4;
            int i8 = 0;
            int i9 = 0;
            while (i8 < length) {
                float f24 = f22;
                LegendEntry legendEntry3 = entries[i8];
                int i10 = length;
                boolean z3 = legendEntry3.form != Legend.LegendForm.NONE;
                float fConvertDpToPixel6 = Float.isNaN(legendEntry3.formSize) ? fConvertDpToPixel3 : Utils.convertDpToPixel(legendEntry3.formSize);
                if (i8 >= calculatedLabelBreakPoints.size() || !calculatedLabelBreakPoints.get(i8).booleanValue()) {
                    f6 = f23;
                    f7 = yOffset;
                } else {
                    f7 = yOffset + f2 + f3;
                    f6 = f4;
                }
                if (f6 == f4 && horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && i9 < calculatedLineSizes.size()) {
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f14 = calculatedLineSizes.get(i9).width;
                    } else {
                        f14 = -calculatedLineSizes.get(i9).width;
                    }
                    f6 += f14 / 2.0f;
                    i9++;
                }
                int i11 = i9;
                boolean z4 = legendEntry3.label == null;
                if (z3) {
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f6 -= fConvertDpToPixel6;
                    }
                    float f25 = f6;
                    f8 = f4;
                    i2 = i8;
                    list = calculatedLabelBreakPoints;
                    list2 = calculatedLineSizes;
                    canvas = c2;
                    drawForm(c2, f25, f7 + fCalcTextHeight, legendEntry3, this.mLegend);
                    f6 = direction == Legend.LegendDirection.LEFT_TO_RIGHT ? f25 + fConvertDpToPixel6 : f25;
                } else {
                    list = calculatedLabelBreakPoints;
                    f8 = f4;
                    list2 = calculatedLineSizes;
                    canvas = c2;
                    i2 = i8;
                }
                if (!z4) {
                    if (z3) {
                        f6 += direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -fConvertDpToPixel : fConvertDpToPixel;
                    }
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f6 -= calculatedLabelSizes.get(i2).width;
                    }
                    drawLabel(canvas, f6, f7 + f2, legendEntry3.label);
                    if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        f6 += calculatedLabelSizes.get(i2).width;
                    }
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f9 = f18;
                        f13 = -f9;
                    } else {
                        f9 = f18;
                        f13 = f9;
                    }
                    f12 = f6 + f13;
                    f10 = f24;
                } else {
                    f9 = f18;
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f10 = f24;
                        f11 = -f10;
                    } else {
                        f10 = f24;
                        f11 = f10;
                    }
                    f12 = f6 + f11;
                }
                f18 = f9;
                f22 = f10;
                i8 = i2 + 1;
                yOffset = f7;
                i9 = i11;
                f4 = f8;
                calculatedLabelBreakPoints = list;
                calculatedLineSizes = list2;
                f23 = f12;
                length = i10;
            }
        }
    }

    protected void drawForm(Canvas c2, float x, float y, LegendEntry entry, Legend legend) {
        float formSize;
        float formLineWidth;
        DashPathEffect formLineDashEffect;
        if (entry.formColor == 1122868 || entry.formColor == 1122867 || entry.formColor == 0) {
            return;
        }
        int iSave = c2.save();
        Legend.LegendForm form = entry.form;
        if (form == Legend.LegendForm.DEFAULT) {
            form = legend.getForm();
        }
        this.mLegendFormPaint.setColor(entry.formColor);
        if (Float.isNaN(entry.formSize)) {
            formSize = legend.getFormSize();
        } else {
            formSize = entry.formSize;
        }
        float fConvertDpToPixel = Utils.convertDpToPixel(formSize);
        float f2 = fConvertDpToPixel / 2.0f;
        int i2 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm[form.ordinal()];
        if (i2 == 3 || i2 == 4) {
            this.mLegendFormPaint.setStyle(Paint.Style.FILL);
            c2.drawCircle(x + f2, y, f2, this.mLegendFormPaint);
        } else if (i2 == 5) {
            this.mLegendFormPaint.setStyle(Paint.Style.FILL);
            c2.drawRect(x, y - f2, x + fConvertDpToPixel, y + f2, this.mLegendFormPaint);
        } else if (i2 == 6) {
            if (Float.isNaN(entry.formLineWidth)) {
                formLineWidth = legend.getFormLineWidth();
            } else {
                formLineWidth = entry.formLineWidth;
            }
            float fConvertDpToPixel2 = Utils.convertDpToPixel(formLineWidth);
            if (entry.formLineDashEffect == null) {
                formLineDashEffect = legend.getFormLineDashEffect();
            } else {
                formLineDashEffect = entry.formLineDashEffect;
            }
            this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
            this.mLegendFormPaint.setStrokeWidth(fConvertDpToPixel2);
            this.mLegendFormPaint.setPathEffect(formLineDashEffect);
            this.mLineFormPath.reset();
            this.mLineFormPath.moveTo(x, y);
            this.mLineFormPath.lineTo(x + fConvertDpToPixel, y);
            c2.drawPath(this.mLineFormPath, this.mLegendFormPaint);
        }
        c2.restoreToCount(iSave);
    }

    /* renamed from: com.yucheng.smarthealthpro.customchart.renderer.LegendRenderer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm;
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment;
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment;

        static {
            int[] iArr = new int[Legend.LegendForm.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm = iArr;
            try {
                iArr[Legend.LegendForm.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm[Legend.LegendForm.EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm[Legend.LegendForm.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm[Legend.LegendForm.CIRCLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm[Legend.LegendForm.SQUARE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendForm[Legend.LegendForm.LINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[Legend.LegendOrientation.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation = iArr2;
            try {
                iArr2[Legend.LegendOrientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation[Legend.LegendOrientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[Legend.LegendVerticalAlignment.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment[Legend.LegendVerticalAlignment.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment[Legend.LegendVerticalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr4 = new int[Legend.LegendHorizontalAlignment.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment = iArr4;
            try {
                iArr4[Legend.LegendHorizontalAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment[Legend.LegendHorizontalAlignment.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment[Legend.LegendHorizontalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    protected void drawLabel(Canvas c2, float x, float y, String label) {
        c2.drawText(label, x, y, this.mLegendLabelPaint);
    }
}
