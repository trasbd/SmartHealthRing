package com.yucheng.smarthealthpro.customchart.formatter;

import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.text.DecimalFormat;

/* loaded from: classes4.dex */
public class DefaultValueFormatter implements IValueFormatter {
    protected int mDecimalDigits;
    protected DecimalFormat mFormat;

    public DefaultValueFormatter(int digits) {
        setup(digits);
    }

    public void setup(int digits) {
        this.mDecimalDigits = digits;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < digits; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return this.mFormat.format(value);
    }

    public int getDecimalDigits() {
        return this.mDecimalDigits;
    }
}
