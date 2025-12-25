package com.yucheng.smarthealthpro.customchart.components;

import android.graphics.DashPathEffect;
import com.yucheng.smarthealthpro.customchart.components.Legend;

/* loaded from: classes4.dex */
public class LegendEntry {
    public Legend.LegendForm form;
    public int formColor;
    public DashPathEffect formLineDashEffect;
    public float formLineWidth;
    public float formSize;
    public String label;

    public LegendEntry() {
        this.form = Legend.LegendForm.DEFAULT;
        this.formSize = Float.NaN;
        this.formLineWidth = Float.NaN;
        this.formLineDashEffect = null;
        this.formColor = 1122867;
    }

    public LegendEntry(String label, Legend.LegendForm form, float formSize, float formLineWidth, DashPathEffect formLineDashEffect, int formColor) {
        Legend.LegendForm legendForm = Legend.LegendForm.DEFAULT;
        this.label = label;
        this.form = form;
        this.formSize = formSize;
        this.formLineWidth = formLineWidth;
        this.formLineDashEffect = formLineDashEffect;
        this.formColor = formColor;
    }
}
