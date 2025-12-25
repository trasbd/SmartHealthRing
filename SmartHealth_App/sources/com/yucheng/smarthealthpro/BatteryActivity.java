package com.yucheng.smarthealthpro;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.customchart.utils.HourToMinute;
import com.yucheng.smarthealthpro.databinding.ActivityBatteryBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* compiled from: BatteryActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0015J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ&\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020#0\"H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006$"}, d2 = {"Lcom/yucheng/smarthealthpro/BatteryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/yucheng/smarthealthpro/databinding/ActivityBatteryBinding;", "getBinding", "()Lcom/yucheng/smarthealthpro/databinding/ActivityBatteryBinding;", "setBinding", "(Lcom/yucheng/smarthealthpro/databinding/ActivityBatteryBinding;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "setHandler", "(Landroid/os/Handler;)V", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "formatDate", "", "time", "", "showPieChart", "pieChart", "Lcom/github/mikephil/charting/charts/PieChart;", "typeMap", "", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BatteryActivity extends AppCompatActivity {
    public ActivityBatteryBinding binding;
    private Handler handler = new Handler(Looper.getMainLooper());
    private ProgressDialog progressDialog;

    public final ActivityBatteryBinding getBinding() {
        ActivityBatteryBinding activityBatteryBinding = this.binding;
        if (activityBatteryBinding != null) {
            return activityBatteryBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivityBatteryBinding activityBatteryBinding) {
        Intrinsics.checkNotNullParameter(activityBatteryBinding, "<set-?>");
        this.binding = activityBatteryBinding;
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public final void setHandler(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        this.handler = handler;
    }

    public final ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public final void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBatteryBinding activityBatteryBindingInflate = ActivityBatteryBinding.inflate(getLayoutInflater());
        setBinding(activityBatteryBindingInflate);
        setContentView(activityBatteryBindingInflate.getRoot());
        getBinding().toolbarTitle.setText(getString(R.string.power_statistics));
        getBinding().toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.BatteryActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.finish();
            }
        });
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            this.progressDialog = ProgressDialog.show(this, getString(R.string.prompt), getString(R.string.obtaining_statistics), true, true);
        } else if (progressDialog != null) {
            progressDialog.show();
        }
        YCBTClient.getPowerStatistics(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.BatteryActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public final void onDataResponse(int i2, float f2, HashMap map) {
                BatteryActivity.onCreate$lambda$3(this.f$0, i2, f2, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(final BatteryActivity batteryActivity, final int i2, float f2, final HashMap map) {
        batteryActivity.handler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.BatteryActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                BatteryActivity.onCreate$lambda$3$lambda$2(this.f$0, i2, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$2(BatteryActivity batteryActivity, int i2, HashMap map) throws Resources.NotFoundException {
        try {
            ProgressDialog progressDialog = batteryActivity.progressDialog;
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            if (i2 == 0 && map != null) {
                Logger.w(map + StringUtils.SPACE + Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper()), new Object[0]);
                batteryActivity.getBinding().lastChargingTime.setText(batteryActivity.getString(R.string.last_charging) + ":" + batteryActivity.formatDate(Long.parseLong(String.valueOf(map.get("lastChargingTime")))));
                TextView textView = batteryActivity.getBinding().usageTime;
                String string = batteryActivity.getString(R.string.watch_uses_time);
                Object obj = map.get("usageTime");
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
                textView.setText(string + ":" + HourToMinute.secToTime2(((Long) obj).longValue()));
                TextView textView2 = batteryActivity.getBinding().screenDuration;
                String string2 = batteryActivity.getString(R.string.bright_screen_duration);
                Object obj2 = map.get("screenDuration");
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Long");
                textView2.setText(string2 + ":" + HourToMinute.secToTime2(((Long) obj2).longValue()));
                TextView textView3 = batteryActivity.getBinding().callDuration;
                String string3 = batteryActivity.getString(R.string.call_time);
                Object obj3 = map.get("callDuration");
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Long");
                textView3.setText(string3 + ":" + HourToMinute.secToTime2(((Long) obj3).longValue()));
                TextView textView4 = batteryActivity.getBinding().musicDuration;
                String string4 = batteryActivity.getString(R.string.duration_of_music);
                Object obj4 = map.get("musicDuration");
                Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Long");
                textView4.setText(string4 + ":" + HourToMinute.secToTime2(((Long) obj4).longValue()));
                TextView textView5 = batteryActivity.getBinding().healthMeasurementDuration;
                String string5 = batteryActivity.getString(R.string.health_measurement_duration);
                Object obj5 = map.get("healthMeasurementDuration");
                Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.Long");
                textView5.setText(string5 + ":" + HourToMinute.secToTime2(((Long) obj5).longValue()));
                batteryActivity.getBinding().messagesNumber.setText(batteryActivity.getString(R.string.message_push_number) + ":" + map.get("messagesNumber"));
                TextView textView6 = batteryActivity.getBinding().aratedBloodPressure;
                String string6 = batteryActivity.getString(R.string.arated_lood_ressure);
                Object obj6 = map.get("aratedBloodPressure");
                Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.Long");
                textView6.setText(string6 + ":" + HourToMinute.secToTime2(((Long) obj6).longValue()));
                batteryActivity.getBinding().lastChargingEndBattery.setText(batteryActivity.getString(R.string.end_power_after_the_last_charging) + ":" + map.get("lastChargingEndBattery") + "%");
                batteryActivity.getBinding().battery.setText(batteryActivity.getString(R.string.current_electricity) + "：" + map.get("batteryLevel") + "%");
                Object obj7 = map.get("usageTime");
                Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.Long");
                float fLongValue = ((Long) obj7).longValue();
                Object obj8 = map.get("screenDuration");
                Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.Long");
                float fLongValue2 = ((Long) obj8).longValue();
                Object obj9 = map.get("callDuration");
                Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type kotlin.Long");
                float fLongValue3 = ((Long) obj9).longValue();
                Object obj10 = map.get("musicDuration");
                Intrinsics.checkNotNull(obj10, "null cannot be cast to non-null type kotlin.Long");
                float fLongValue4 = ((Long) obj10).longValue();
                Object obj11 = map.get("healthMeasurementDuration");
                Intrinsics.checkNotNull(obj11, "null cannot be cast to non-null type kotlin.Long");
                float fLongValue5 = ((Long) obj11).longValue();
                Object obj12 = map.get("aratedBloodPressure");
                Intrinsics.checkNotNull(obj12, "null cannot be cast to non-null type kotlin.Long");
                float fLongValue6 = ((Long) obj12).longValue();
                float f2 = 100;
                float fKeep1F = FormatUtil.keep1F((fLongValue2 / fLongValue) * f2);
                float fKeep1F2 = FormatUtil.keep1F((fLongValue3 / fLongValue) * f2);
                float fKeep1F3 = FormatUtil.keep1F((fLongValue4 / fLongValue) * f2);
                float fKeep1F4 = FormatUtil.keep1F((fLongValue5 / fLongValue) * f2);
                float fKeep1F5 = FormatUtil.keep1F((fLongValue6 / fLongValue) * f2);
                float fKeep1F6 = FormatUtil.keep1F((((100.0f - fKeep1F) - fKeep1F2) - fKeep1F3) - fKeep1F4);
                HashMap map2 = new HashMap();
                map2.put(batteryActivity.getString(R.string.call) + ":" + fKeep1F2 + "%", Float.valueOf(fKeep1F2));
                map2.put(batteryActivity.getString(R.string.music) + ":" + fKeep1F3 + "%", Float.valueOf(fKeep1F3));
                map2.put(batteryActivity.getString(R.string.bright_screen) + ":" + fKeep1F + "%", Float.valueOf(fKeep1F));
                map2.put(batteryActivity.getString(R.string.measurement) + ":" + fKeep1F4 + "%", Float.valueOf(fKeep1F4));
                map2.put(batteryActivity.getString(R.string.inflation_blood_pressure) + ":" + fKeep1F5 + "%", Float.valueOf(fKeep1F5));
                map2.put(batteryActivity.getString(R.string.standby) + ":" + fKeep1F6 + "%", Float.valueOf(fKeep1F6));
                PieChart pieChartView = batteryActivity.getBinding().pieChartView;
                Intrinsics.checkNotNullExpressionValue(pieChartView, "pieChartView");
                batteryActivity.showPieChart(pieChartView, map2);
                ProgressDialog progressDialog2 = batteryActivity.progressDialog;
                if (progressDialog2 != null) {
                    progressDialog2.dismiss();
                }
                batteryActivity.progressDialog = null;
                return;
            }
            ToastUtil.getInstance(batteryActivity.getApplicationContext()).toast(R.string.obtaining_power_statistics_failed);
            batteryActivity.finish();
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastUtil.getInstance(batteryActivity.getApplicationContext()).toast(R.string.obtaining_power_statistics_failed);
            batteryActivity.finish();
        }
    }

    public final String formatDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(time));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void showPieChart$default(BatteryActivity batteryActivity, PieChart pieChart, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            map = new HashMap();
        }
        batteryActivity.showPieChart(pieChart, map);
    }

    private final void showPieChart(PieChart pieChart, Map<String, Float> typeMap) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(Color.parseColor("#9CB869")));
        arrayList.add(Integer.valueOf(Color.parseColor("#C8C479")));
        arrayList.add(Integer.valueOf(Color.parseColor("#00A3DE")));
        arrayList.add(Integer.valueOf(Color.parseColor("#DF7C92")));
        arrayList.add(Integer.valueOf(Color.parseColor("#3ca567")));
        arrayList.add(Integer.valueOf(Color.parseColor("#CCEBE9")));
        ArrayList arrayList2 = new ArrayList();
        String string = getString(R.string.percentage);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        for (String str : typeMap.keySet()) {
            Float f2 = typeMap.get(str);
            Intrinsics.checkNotNull(f2);
            arrayList2.add(new PieEntry(f2.floatValue(), str));
        }
        PieDataSet pieDataSet = new PieDataSet(arrayList2, string);
        pieDataSet.setValueTextSize(12.0f);
        pieDataSet.setColors(arrayList);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);
        pieChart.setCenterText(getString(R.string.power_statistics));
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setText("");
        pieChart.setRotationEnabled(false);
        Legend legend = pieChart.getLegend();
        Intrinsics.checkNotNullExpressionValue(legend, "getLegend(...)");
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setXOffset(10.0f);
        legend.setYOffset(10.0f);
        pieChart.invalidate();
    }
}
