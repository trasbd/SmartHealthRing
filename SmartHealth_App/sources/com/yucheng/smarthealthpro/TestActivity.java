package com.yucheng.smarthealthpro;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.yucheng.smarthealthpro.base.BaseActivity;
import com.yucheng.smarthealthpro.customchart.MyTempDayCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarDataSet;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class TestActivity extends BaseActivity {
    BarChart mChart;
    private String[] mLabels;

    @Override // com.yucheng.smarthealthpro.base.BaseActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BarChart barChart = (BarChart) findViewById(R.id.chart1);
        this.mChart = barChart;
        barChart.getDescription().setEnabled(false);
        this.mChart.setPinchZoom(false);
        this.mChart.setDrawGridBackground(false);
        this.mChart.setExtraBottomOffset(15.0f);
        this.mChart.getLegend().setEnabled(false);
        XAxis xAxis = this.mChart.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        YAxis axisLeft = this.mChart.getAxisLeft();
        axisLeft.setDrawGridLines(false);
        axisLeft.setSpaceTop(35.0f);
        axisLeft.setAxisMinimum(0.0f);
        this.mChart.getAxisRight().setEnabled(false);
        this.mChart.getAxisLeft().setEnabled(false);
        this.mChart.getXAxis().setAxisLineColor(0);
        setData();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setData() {
        BarEntry barEntry;
        this.mLabels = new String[]{"甲骨文", "海康", "大华", "飞翔", "阿里巴巴", "网易"};
        this.mChart.getXAxis().setValueFormatter(new MyTempDayCustomXAxisValueFormatter(true));
        this.mChart.getXAxis().setLabelCount(24, true);
        this.mChart.setXAxisRenderer(new MyXAxisRenderer(this.mChart.getViewPortHandler(), this.mChart.getXAxis(), this.mChart.getTransformer(YAxis.AxisDependency.LEFT)));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        float f2 = 100 * 100.0f;
        BarData barData = new BarData();
        int i2 = 0;
        for (int i3 = 24; i2 < i3; i3 = 24) {
            arrayList = new ArrayList();
            if (i2 > 18) {
                barEntry = new BarEntry(i2, 0.0f);
            } else {
                barEntry = new BarEntry(i2, (float) (f2 * Math.random()));
            }
            arrayList.add(barEntry);
            float f3 = i2;
            double d2 = f2;
            ArrayList arrayList5 = arrayList2;
            arrayList5.add(new BarEntry(f3, (float) (Math.random() * d2)));
            arrayList3.add(new BarEntry(f3, (float) (Math.random() * d2)));
            arrayList4.add(new BarEntry(f3, (float) (Math.random() * d2)));
            BarDataSet barDataSet = new BarDataSet(arrayList, "商品A销量");
            if (barEntry.getY() < 3333.0f) {
                barDataSet.setColor(SupportMenu.CATEGORY_MASK);
            } else if (barEntry.getY() < 6666.0f) {
                barDataSet.setColor(-16776961);
            } else {
                barDataSet.setColor(-16711936);
            }
            barData.addDataSet(barDataSet);
            i2++;
            arrayList2 = arrayList5;
        }
        ArrayList arrayList6 = arrayList2;
        if (this.mChart.getData() != null && ((BarData) this.mChart.getData()).getDataSetCount() > 0) {
            BarDataSet barDataSet2 = (BarDataSet) ((BarData) this.mChart.getData()).getDataSetByIndex(0);
            BarDataSet barDataSet3 = (BarDataSet) ((BarData) this.mChart.getData()).getDataSetByIndex(1);
            BarDataSet barDataSet4 = (BarDataSet) ((BarData) this.mChart.getData()).getDataSetByIndex(2);
            BarDataSet barDataSet5 = (BarDataSet) ((BarData) this.mChart.getData()).getDataSetByIndex(3);
            barDataSet2.setEntries(arrayList);
            barDataSet3.setEntries(arrayList6);
            barDataSet4.setEntries(arrayList3);
            barDataSet5.setEntries(arrayList4);
            ((BarData) this.mChart.getData()).notifyDataChanged();
            this.mChart.notifyDataSetChanged();
        } else {
            new BarDataSet(arrayList, "商品A销量").setColor(Color.rgb(0, Opcodes.IFEQ, 255));
            new BarDataSet(arrayList6, "商品B销量").setColor(Color.rgb(255, Opcodes.IFEQ, 102));
            new BarDataSet(arrayList3, "商品C销量").setColor(Color.rgb(51, Opcodes.IFEQ, Opcodes.IFEQ));
            new BarDataSet(arrayList4, "商品D销量").setColor(Color.rgb(255, 102, 0));
            this.mChart.setData(barData);
        }
        this.mChart.setDrawBarShadow(true);
        this.mChart.getBarData().setBarWidth(0.85f);
        this.mChart.getBarData().setValueTextSize(0.0f);
        this.mChart.getXAxis().setEnabled(false);
        this.mChart.invalidate();
        this.mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.yucheng.smarthealthpro.TestActivity.1
            @Override // com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener
            public void onValueSelected(Entry e2, Highlight h2) {
            }

            @Override // com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
                Log.i("Activity", "Nothing selected.");
            }
        });
    }
}
