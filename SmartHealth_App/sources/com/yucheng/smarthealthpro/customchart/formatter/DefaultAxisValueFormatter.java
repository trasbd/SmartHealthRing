package com.yucheng.smarthealthpro.customchart.formatter;

import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import java.text.DecimalFormat;

/* loaded from: classes4.dex */
public class DefaultAxisValueFormatter implements IAxisValueFormatter {
    protected int digits;
    protected DecimalFormat mFormat;

    public DefaultAxisValueFormatter(int digits) {
        this.digits = digits;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < digits; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        return this.mFormat.format(value);
    }

    public int getDecimalDigits() {
        return this.digits;
    }
}
