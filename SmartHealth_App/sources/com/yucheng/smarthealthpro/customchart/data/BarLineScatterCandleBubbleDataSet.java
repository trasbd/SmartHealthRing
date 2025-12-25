package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.Color;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.yucheng.smarthealthpro.me.setting.SettingsDataType;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry> extends DataSet<T> implements IBarLineScatterCandleBubbleDataSet<T> {
    protected int mHighLightColor;

    public BarLineScatterCandleBubbleDataSet(List<T> yVals, String label) {
        super(yVals, label);
        this.mHighLightColor = Color.rgb(255, Opcodes.NEW, SettingsDataType.MORE_SETTINGS);
    }

    public void setHighLightColor(int color) {
        this.mHighLightColor = color;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarLineScatterCandleBubbleDataSet
    public int getHighLightColor() {
        return this.mHighLightColor;
    }

    protected void copy(BarLineScatterCandleBubbleDataSet barLineScatterCandleBubbleDataSet) {
        super.copy((DataSet) barLineScatterCandleBubbleDataSet);
        barLineScatterCandleBubbleDataSet.mHighLightColor = this.mHighLightColor;
    }
}
