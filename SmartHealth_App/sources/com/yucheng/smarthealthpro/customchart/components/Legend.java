package com.yucheng.smarthealthpro.customchart.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.yucheng.smarthealthpro.customchart.utils.FSize;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class Legend extends ComponentBase {
    private List<Boolean> mCalculatedLabelBreakPoints;
    private List<FSize> mCalculatedLabelSizes;
    private List<FSize> mCalculatedLineSizes;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private LegendEntry[] mEntries;
    private LegendEntry[] mExtraEntries;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mEntries = new LegendEntry[0];
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mFormLineWidth = 3.0f;
        this.mFormLineDashEffect = null;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new ArrayList(16);
        this.mCalculatedLabelBreakPoints = new ArrayList(16);
        this.mCalculatedLineSizes = new ArrayList(16);
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(3.0f);
    }

    public Legend(LegendEntry[] entries) {
        this();
        if (entries == null) {
            throw new IllegalArgumentException("entries array is NULL");
        }
        this.mEntries = entries;
    }

    public void setEntries(List<LegendEntry> entries) {
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }

    public LegendEntry[] getEntries() {
        return this.mEntries;
    }

    public float getMaximumEntryWidth(Paint p) {
        float fConvertDpToPixel = Utils.convertDpToPixel(this.mFormToTextSpace);
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            float fConvertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? this.mFormSize : legendEntry.formSize);
            if (fConvertDpToPixel2 > f3) {
                f3 = fConvertDpToPixel2;
            }
            String str = legendEntry.label;
            if (str != null) {
                float fCalcTextWidth = Utils.calcTextWidth(p, str);
                if (fCalcTextWidth > f2) {
                    f2 = fCalcTextWidth;
                }
            }
        }
        return f2 + f3 + fConvertDpToPixel;
    }

    public float getMaximumEntryHeight(Paint p) {
        float f2 = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            String str = legendEntry.label;
            if (str != null) {
                float fCalcTextHeight = Utils.calcTextHeight(p, str);
                if (fCalcTextHeight > f2) {
                    f2 = fCalcTextHeight;
                }
            }
        }
        return f2;
    }

    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }

    public void setExtra(List<LegendEntry> entries) {
        this.mExtraEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }

    public void setExtra(LegendEntry[] entries) {
        if (entries == null) {
            entries = new LegendEntry[0];
        }
        this.mExtraEntries = entries;
    }

    public void setExtra(int[] colors, String[] labels) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < Math.min(colors.length, labels.length); i2++) {
            LegendEntry legendEntry = new LegendEntry();
            legendEntry.formColor = colors[i2];
            legendEntry.label = labels[i2];
            if (legendEntry.formColor == 1122868 || legendEntry.formColor == 0) {
                legendEntry.form = LegendForm.NONE;
            } else if (legendEntry.formColor == 1122867) {
                legendEntry.form = LegendForm.EMPTY;
            }
            arrayList.add(legendEntry);
        }
        this.mExtraEntries = (LegendEntry[]) arrayList.toArray(new LegendEntry[arrayList.size()]);
    }

    public void setCustom(LegendEntry[] entries) {
        this.mEntries = entries;
        this.mIsLegendCustom = true;
    }

    public void setCustom(List<LegendEntry> entries) {
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
        this.mIsLegendCustom = true;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment value) {
        this.mHorizontalAlignment = value;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment value) {
        this.mVerticalAlignment = value;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation value) {
        this.mOrientation = value;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean value) {
        this.mDrawInside = value;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection pos) {
        this.mDirection = pos;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm shape) {
        this.mShape = shape;
    }

    public void setFormSize(float size) {
        this.mFormSize = size;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float size) {
        this.mFormLineWidth = size;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float space) {
        this.mXEntrySpace = space;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float space) {
        this.mYEntrySpace = space;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float space) {
        this.mFormToTextSpace = space;
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float space) {
        this.mStackSpace = space;
    }

    public void setWordWrapEnabled(boolean enabled) {
        this.mWordWrapEnabled = enabled;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float maxSize) {
        this.mMaxSizePercent = maxSize;
    }

    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    public void calculateDimensions(Paint labelpaint, ViewPortHandler viewPortHandler) {
        float fMax;
        float f2;
        float f3;
        float f4;
        float fMax2;
        float f5;
        float fConvertDpToPixel = Utils.convertDpToPixel(this.mFormSize);
        float fConvertDpToPixel2 = Utils.convertDpToPixel(this.mStackSpace);
        float fConvertDpToPixel3 = Utils.convertDpToPixel(this.mFormToTextSpace);
        float fConvertDpToPixel4 = Utils.convertDpToPixel(this.mXEntrySpace);
        float fConvertDpToPixel5 = Utils.convertDpToPixel(this.mYEntrySpace);
        boolean z = this.mWordWrapEnabled;
        LegendEntry[] legendEntryArr = this.mEntries;
        int length = legendEntryArr.length;
        this.mTextWidthMax = getMaximumEntryWidth(labelpaint);
        this.mTextHeightMax = getMaximumEntryHeight(labelpaint);
        int i2 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation[this.mOrientation.ordinal()];
        if (i2 == 1) {
            float lineHeight = Utils.getLineHeight(labelpaint);
            boolean z2 = false;
            float f6 = 0.0f;
            float f7 = 0.0f;
            float fMax3 = 0.0f;
            for (int i3 = 0; i3 < length; i3++) {
                LegendEntry legendEntry = legendEntryArr[i3];
                boolean z3 = legendEntry.form != LegendForm.NONE;
                float fConvertDpToPixel6 = Float.isNaN(legendEntry.formSize) ? fConvertDpToPixel : Utils.convertDpToPixel(legendEntry.formSize);
                String str = legendEntry.label;
                if (!z2) {
                    f7 = 0.0f;
                }
                if (z3) {
                    if (z2) {
                        f7 += fConvertDpToPixel2;
                    }
                    f7 += fConvertDpToPixel6;
                }
                if (str != null) {
                    if (!z3 || z2) {
                        if (z2) {
                            fMax = Math.max(fMax3, f7);
                            f6 += lineHeight + fConvertDpToPixel5;
                            z2 = false;
                            f2 = 0.0f;
                        }
                        float fCalcTextWidth = f2 + Utils.calcTextWidth(labelpaint, str);
                        f6 += lineHeight + fConvertDpToPixel5;
                        fMax3 = fMax;
                        f7 = fCalcTextWidth;
                    } else {
                        f7 += fConvertDpToPixel3;
                    }
                    float f8 = fMax3;
                    f2 = f7;
                    fMax = f8;
                    float fCalcTextWidth2 = f2 + Utils.calcTextWidth(labelpaint, str);
                    f6 += lineHeight + fConvertDpToPixel5;
                    fMax3 = fMax;
                    f7 = fCalcTextWidth2;
                } else {
                    f7 += fConvertDpToPixel6;
                    if (i3 < length - 1) {
                        f7 += fConvertDpToPixel2;
                    }
                    z2 = true;
                }
                fMax3 = Math.max(fMax3, f7);
            }
            this.mNeededWidth = fMax3;
            this.mNeededHeight = f6;
        } else if (i2 == 2) {
            float lineHeight2 = Utils.getLineHeight(labelpaint);
            float lineSpacing = Utils.getLineSpacing(labelpaint) + fConvertDpToPixel5;
            float fContentWidth = viewPortHandler.contentWidth() * this.mMaxSizePercent;
            this.mCalculatedLabelBreakPoints.clear();
            this.mCalculatedLabelSizes.clear();
            this.mCalculatedLineSizes.clear();
            int i4 = 0;
            int i5 = -1;
            float fMax4 = 0.0f;
            float f9 = 0.0f;
            float f10 = 0.0f;
            while (i4 < length) {
                LegendEntry legendEntry2 = legendEntryArr[i4];
                float f11 = fConvertDpToPixel;
                boolean z4 = legendEntry2.form != LegendForm.NONE;
                float fConvertDpToPixel7 = Float.isNaN(legendEntry2.formSize) ? f11 : Utils.convertDpToPixel(legendEntry2.formSize);
                String str2 = legendEntry2.label;
                float f12 = fConvertDpToPixel4;
                LegendEntry[] legendEntryArr2 = legendEntryArr;
                this.mCalculatedLabelBreakPoints.add(false);
                float f13 = i5 == -1 ? 0.0f : f9 + fConvertDpToPixel2;
                if (str2 != null) {
                    f3 = fConvertDpToPixel2;
                    this.mCalculatedLabelSizes.add(Utils.calcTextSize(labelpaint, str2));
                    f4 = f13 + (z4 ? fConvertDpToPixel3 + fConvertDpToPixel7 : 0.0f) + this.mCalculatedLabelSizes.get(i4).width;
                } else {
                    f3 = fConvertDpToPixel2;
                    float f14 = fConvertDpToPixel7;
                    this.mCalculatedLabelSizes.add(FSize.getInstance(0.0f, 0.0f));
                    f4 = f13 + (z4 ? f14 : 0.0f);
                    if (i5 == -1) {
                        i5 = i4;
                    }
                }
                if (str2 != null || i4 == length - 1) {
                    float f15 = f10;
                    float f16 = f15 == 0.0f ? 0.0f : f12;
                    if (!z || f15 == 0.0f || fContentWidth - f15 >= f16 + f4) {
                        float f17 = f15 + f16 + f4;
                        fMax2 = fMax4;
                        f5 = f17;
                    } else {
                        this.mCalculatedLineSizes.add(FSize.getInstance(f15, lineHeight2));
                        fMax2 = Math.max(fMax4, f15);
                        this.mCalculatedLabelBreakPoints.set(i5 > -1 ? i5 : i4, true);
                        f5 = f4;
                    }
                    if (i4 == length - 1) {
                        this.mCalculatedLineSizes.add(FSize.getInstance(f5, lineHeight2));
                        fMax4 = Math.max(fMax2, f5);
                    } else {
                        fMax4 = fMax2;
                    }
                    f10 = f5;
                }
                if (str2 != null) {
                    i5 = -1;
                }
                i4++;
                fConvertDpToPixel2 = f3;
                fConvertDpToPixel = f11;
                legendEntryArr = legendEntryArr2;
                f9 = f4;
                fConvertDpToPixel4 = f12;
            }
            this.mNeededWidth = fMax4;
            this.mNeededHeight = (lineHeight2 * this.mCalculatedLineSizes.size()) + (lineSpacing * (this.mCalculatedLineSizes.size() == 0 ? 0 : this.mCalculatedLineSizes.size() - 1));
        }
        this.mNeededHeight += this.mYOffset;
        this.mNeededWidth += this.mXOffset;
    }

    /* renamed from: com.yucheng.smarthealthpro.customchart.components.Legend$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation;

        static {
            int[] iArr = new int[LegendOrientation.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation = iArr;
            try {
                iArr[LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation[LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
