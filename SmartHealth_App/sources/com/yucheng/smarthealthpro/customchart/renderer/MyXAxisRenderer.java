package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public class MyXAxisRenderer extends XAxisRenderer {
    protected int interval;

    public MyXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
        super(viewPortHandler, xAxis, trans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.XAxisRenderer
    protected void drawLabels(Canvas c2, float pos, MPPointF anchor) {
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        boolean zIsCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        int i2 = this.mXAxis.mEntryCount * 2;
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3 += 2) {
            if (zIsCenterAxisLabelsEnabled) {
                fArr[i3] = this.mXAxis.mCenteredEntries[i3 / 2];
            } else {
                fArr[i3] = this.mXAxis.mEntries[i3 / 2];
            }
        }
        this.mTrans.pointValuesToPixel(fArr);
        for (int i4 = 0; i4 < i2; i4 += 2) {
            float f2 = fArr[i4];
            if (this.mViewPortHandler.isInBoundsX(f2)) {
                String formattedValue = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i4 / 2], this.mXAxis);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled()) {
                    float fCalcTextWidth = Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue);
                    if (i4 == (this.mXAxis.mEntryCount * 2) - 2 && this.mXAxis.mEntryCount > 1) {
                        f2 -= (fCalcTextWidth / 2.0f) + this.interval;
                    } else if (i4 == 0) {
                        f2 += (fCalcTextWidth / 2.0f) + this.interval;
                    }
                }
                drawLabel(c2, formattedValue, f2, pos, anchor, labelRotationAngle);
            }
        }
    }
}
